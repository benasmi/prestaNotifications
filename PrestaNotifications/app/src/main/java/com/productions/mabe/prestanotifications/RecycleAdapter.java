package com.productions.mabe.prestanotifications;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    public Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<InfoHolder> infoHolder;

    public RecycleAdapter(Context context, ArrayList<InfoHolder> infoHolder) {
        this.infoHolder = infoHolder;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void remove(int position) {
        infoHolder.remove(position);
        notifyItemRemoved(position);

        SharedPreferences sharedPreferences = context.getSharedPreferences("notifications", context.MODE_PRIVATE);
        String notification_data = sharedPreferences.getString("notification_data", "");

        if (!notification_data.isEmpty()) {
            try {
                JSONArray jsonArray = new JSONArray(notification_data);
                jsonArray = CheckingUtils.remove(position, jsonArray);

                sharedPreferences.edit().putString("notification_data", jsonArray.toString()).commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public void add(InfoHolder info) {
        infoHolder.add(0,info);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        InfoHolder data = infoHolder.get(position);
        int type = Integer.valueOf(data.getType());


        return type;
    }

    @Override
    public int getItemCount() {
        return infoHolder.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:

                View view = layoutInflater.inflate(R.layout.list_item, parent, false);
                ViewHolder holder = new ViewHolder(view, 0);
                return holder;

            case 1:

                View view1 = layoutInflater.inflate(R.layout.message_list_item, parent, false);
                ViewHolder holder1 = new ViewHolder(view1, 1);
                return holder1;

        }

        Log.i("TEST", "ViewType:" + String.valueOf(viewType));

        return null;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoHolder data = infoHolder.get(position);


        String dataType = data.getType();
        if (dataType.equals("0")) {
            holder.date.setText(data.getOrder_date());
            holder.order_reference.setText(data.getOrder_reference());
            holder.order_status.setText(data.getOrder_status());
            holder.buyer_name.setText(data.getUrl());
            holder.url.setText("New order");
            holder.cost.setText(data.getCost());
            holder.payment_method.setText(data.getPayment_method());
        } else {
            holder.message.setText(data.getMessage());
            holder.message_buyer_name.setText(data.getUrl()+":");
            holder.message_date.setText(data.getOrder_date());
            holder.message_url.setText(data.getUrl());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //ORDER layout
        private TextView date;
        private TextView order_reference;
        private TextView buyer_name;
        private TextView url;
        private TextView cost;
        private TextView payment_method;
        private TextView order_status;
        private RelativeLayout order_layout;

        //Message layout
        private TextView message_date;
        private TextView message_buyer_name;
        private TextView message;
        private TextView message_url;
        private RelativeLayout message_layout;

        private boolean isClicked = true;


        public ViewHolder(View itemView, int type) {

            super(itemView);
            Log.i("TEST", String.valueOf(type));

            switch (type) {
                case 0:
                    order_layout = (RelativeLayout) itemView.findViewById(R.id.text_wrap);
                    date = (TextView) itemView.findViewById(R.id.date);
                    order_reference = (TextView) itemView.findViewById(R.id.order_reference);
                    buyer_name = (TextView) itemView.findViewById(R.id.message_buyer_name);
                    url = (TextView) itemView.findViewById(R.id.url);
                    cost = (TextView) itemView.findViewById(R.id.cost);
                    payment_method = (TextView) itemView.findViewById(R.id.payment_method);
                    order_status = (TextView) itemView.findViewById(R.id.order_status);
                    order_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ResizeAnimation expand = new ResizeAnimation(v, (int) CheckingUtils.convertPixelsToDp(110, context), (int) CheckingUtils.convertPixelsToDp(55, context));
                            expand.setDuration(200);
                            ResizeAnimation shrink = new ResizeAnimation(v, (int) CheckingUtils.convertPixelsToDp(55, context), (int) CheckingUtils.convertPixelsToDp(110, context));
                            shrink.setDuration(200);
                            v.startAnimation(isClicked ? expand : shrink);

                            isClicked = !isClicked;
                        }
                    });

                    break;
                case 1:
                    message_layout = (RelativeLayout) itemView.findViewById(R.id.message_text_wrap);
                    message_buyer_name = (TextView) itemView.findViewById(R.id.message_buyer_name);
                    message = (TextView) itemView.findViewById(R.id.message);
                    message_url = (TextView) itemView.findViewById(R.id.message_url);
                    message_date = (TextView) itemView.findViewById(R.id.message_date);
                    message_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ResizeAnimation expand = new ResizeAnimation(v, (int) CheckingUtils.convertPixelsToDp(95  , context) + message.getLayout().getHeight(), (int) CheckingUtils.convertPixelsToDp(55, context));
                            expand.setDuration(200);
                            ResizeAnimation shrink = new ResizeAnimation(v, (int) CheckingUtils.convertPixelsToDp(55, context), (int) CheckingUtils.convertPixelsToDp(95 , context) + message.getLayout().getHeight());
                            shrink.setDuration(200);
                            v.startAnimation(isClicked ? expand : shrink);
                            isClicked = !isClicked;

                        }
                    });
                    break;
            }


        }


    }

}
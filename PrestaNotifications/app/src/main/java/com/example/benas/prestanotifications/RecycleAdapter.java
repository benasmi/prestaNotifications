package com.example.benas.prestanotifications;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {




    public Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<InfoHolder> infoHolder;

    public RecycleAdapter(Context context, ArrayList<InfoHolder> infoHolder) {
        this.infoHolder = infoHolder;
        layoutInflater = LayoutInflater.from(context);
        this.context=context;
    }

    public void remove(int position) {
        infoHolder.remove(position);
        notifyItemRemoved(position);
    }





    @Override
    public int getItemCount() {
        return infoHolder.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoHolder data = infoHolder.get(position);

        String dataType =  data.getType();

        holder.date.setText(data.getOrder_date());
        holder.order_reference.setText(data.getOrder_date());
        holder.order_status.setText(data.getOrder_date());
        holder.buyer_name.setText(data.getOrder_date());
        holder.order_amount.setText(data.getOrder_date());
        holder.cost.setText(data.getOrder_date());
        holder.payment_method.setText(data.getOrder_date());

        if(dataType.equals("0")){
            holder.message_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.dollar_icon));
            holder.layout.setBackgroundColor(Color.parseColor("#f5b061"));
            holder.type.setText("New order...");
           }
        if(dataType.equals("1")){

            holder.message_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.message_icon));
            holder.layout.setBackgroundColor(Color.parseColor("#86d3ff"));
            holder.type.setText("New message...");

        }




    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView date;

        private TextView type;
        private TextView order_reference;
        private TextView buyer_name;
        private TextView order_amount;
        private TextView cost;
        private TextView payment_method;
        private TextView order_status;
        private LinearLayout layout;
        private ImageView message_icon;
        private ImageView arrow;
        private boolean isClicked = true;


        @Override
        public void onClick(View v) {
            ResizeAnimation expand = new ResizeAnimation(v,530,120);
            expand.setDuration(200);
            ResizeAnimation shrink = new ResizeAnimation(v,120,520);
            shrink.setDuration(200);

            v.startAnimation(isClicked ? expand : shrink);
            arrow.setImageDrawable(isClicked ? context.getResources().getDrawable(R.drawable.up) : context.getResources().getDrawable(R.drawable.down));
            isClicked=!isClicked;
        }

        public ViewHolder(View itemView) {

            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.text_wrap);
            date = (TextView) itemView.findViewById(R.id.date);
            type = (TextView) itemView.findViewById(R.id.type);
            order_reference = (TextView) itemView.findViewById(R.id.order_reference);
            buyer_name = (TextView) itemView.findViewById(R.id.buyer_name);
            order_amount = (TextView) itemView.findViewById(R.id.order_amount);
            cost = (TextView) itemView.findViewById(R.id.cost);
            payment_method = (TextView) itemView.findViewById(R.id.payment_method);
            order_status = (TextView) itemView.findViewById(R.id.order_status);

            message_icon = (ImageView) itemView.findViewById(R.id.message_icon);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            layout.setOnClickListener(this);
        }


    }
}



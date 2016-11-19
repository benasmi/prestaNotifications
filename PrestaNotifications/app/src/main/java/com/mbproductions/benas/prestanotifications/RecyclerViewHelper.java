package com.mbproductions.benas.prestanotifications;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class RecyclerViewHelper extends ItemTouchHelper.SimpleCallback {
    private RecycleAdapter mMovieAdapter;



    public RecyclerViewHelper(RecycleAdapter movieAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mMovieAdapter = movieAdapter;


    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //TODO: Not implemented here
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //Remove item
        mMovieAdapter.remove(viewHolder.getAdapterPosition());

        if(mMovieAdapter.getItemCount()==0){
            NotificationActivity.no_notifs.setVisibility(View.VISIBLE);
        }

    }

}
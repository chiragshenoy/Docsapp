package com.example.chiragshenoy.docsapp;

/**
 * Created by Chirag Shenoy on 19-Jan-16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wasabeef on 2015/01/03.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<RealmMessage> mDataSet;

    public MainAdapter(Context context, ArrayList<RealmMessage> dataSet) {
        mContext = context;
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mDataSet.get(position).getisMe()) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.RIGHT;

            holder.text.setLayoutParams(params);
            holder.text.setBackgroundResource(R.drawable.bubble_a);
            holder.text.setText(mDataSet.get(position).getMessage());
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.LEFT;
            holder.text.setLayoutParams(params);
            holder.text.setBackgroundResource(R.drawable.bubble_b);
            holder.text.setText(mDataSet.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void remove(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void add(RealmMessage msg, int position) {
        mDataSet.add(position, msg);
        notifyItemInserted(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
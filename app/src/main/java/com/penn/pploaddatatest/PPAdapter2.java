package com.penn.pploaddatatest;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.os.Looper.getMainLooper;

/**
 * Created by penn on 11/05/2017.
 */

public class PPAdapter2 extends PPLoadDataAdapter<Long, Long> {

    public PPAdapter2(List<Long> data) {
        this.data = data;
    }

    @Override
    protected RecyclerView.ViewHolder ppOnCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        return new PPViewHolder(v);
    }

    @Override
    protected int ppGetItemViewType(int position) {
        return 0;
    }

    @Override
    public void ppOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PPViewHolder) holder).textView.setText(data.get(position).toString());
    }

//    @Override
//    public long ppGetItemId(int position) {
//        return data.get(position);
//    }

    @Override
    protected void addLoadMoreData(final List data) {
        int oldSize = this.data.size();
        int size = data.size();

        this.data.addAll(data);

        notifyItemRangeInserted(oldSize, size);
        //notifyDataSetChanged();
    }

    @Override
    protected void addRefreshData(List data) {
        int oldSize = this.data.size();
        int size = data.size();

        this.data.clear();
        notifyItemRangeRemoved(0, oldSize);

        this.data.addAll(data);
        Log.v("pplog", "addRefreshData:" + size);
        notifyItemRangeInserted(0, size);
        //notifyDataSetChanged();
    }

    public class PPViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PPViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}

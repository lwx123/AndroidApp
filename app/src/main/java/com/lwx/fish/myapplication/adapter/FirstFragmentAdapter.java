package com.lwx.fish.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lwx.fish.myapplication.R;

import java.util.List;

/**
 * Created by wuxing on 2019/11/7.
 */
public class FirstFragmentAdapter extends BaseAdapter {


    private final Context mContext;
    private final List<String> datas;
    private LayoutInflater inflater;

    public FirstFragmentAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_first_layout,null);
            holder.tv_name = view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_name.setText(datas.get(i));

        return view;
    }

    private final class ViewHolder {
        private TextView tv_name;
    }
}

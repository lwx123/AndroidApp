package com.lwx.fish.myapplication.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.fish.myapplication.R;
import com.lwx.fish.myapplication.activity.OkHttpActivity;
import com.lwx.fish.myapplication.activity.ServiceActivity;
import com.lwx.fish.myapplication.activity.ThreadActivity;
import com.lwx.fish.myapplication.activity.ViewActivity;
import com.lwx.fish.myapplication.adapter.FirstFragmentAdapter;
import com.lwx.fish.myapplication.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxing on 2019/11/6.
 */
public class FirstFragment extends BaseFragment {
    private static final String TAG = FirstFragment.class.getSimpleName();
    private ListView listView;
    private List<String> list;

    @Override
    protected View initView() {
        Log.e(TAG,"initView");
        View view = View.inflate(mContext, R.layout.fragment_first_layout,null);
        listView = view.findViewById(R.id.lv_first);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = list.get(i);
                switch (str){
                    case "okhttp":
                        startActivity(new Intent(mContext, OkHttpActivity.class));
                        break;
                    case "service":
                        startActivity(new Intent(mContext, ServiceActivity.class));
                        break;
                    case "Thread":
                        startActivity(new Intent(mContext, ThreadActivity.class));
                        break;
                    case "view":
                        startActivity(new Intent(mContext, ViewActivity.class));
                        break;
                    default:
                        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"initData");
        String[] str = {"okhttp","RecyclerView","service","Thread","view"};
        list = new ArrayList<>();
        for (int i = 0;i < str.length;i++){
            list.add(str[i]);
        }
        FirstFragmentAdapter adapter = new FirstFragmentAdapter(mContext, list);
        listView.setAdapter(adapter);
    }
}

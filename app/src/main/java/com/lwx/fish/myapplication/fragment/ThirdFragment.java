package com.lwx.fish.myapplication.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lwx.fish.myapplication.base.BaseFragment;

/**
 * Created by wuxing on 2019/11/6.
 */
public class ThirdFragment extends BaseFragment {
    private static final String TAG = ThirdFragment.class.getSimpleName();
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG,"initView");
        textView = new TextView(mContext);
        textView.setText("ThirdFragment");
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"initData");
    }
}

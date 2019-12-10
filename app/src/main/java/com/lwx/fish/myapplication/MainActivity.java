package com.lwx.fish.myapplication;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lwx.fish.myapplication.base.BaseFragment;
import com.lwx.fish.myapplication.fragment.FirstFragment;
import com.lwx.fish.myapplication.fragment.FourFragment;
import com.lwx.fish.myapplication.fragment.SecondFragment;
import com.lwx.fish.myapplication.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    private RelativeLayout title;
    private FrameLayout flMain;
    private RadioGroup rgBottom;

    private List<BaseFragment> fragmentList;
    private int position = 0;
    private BaseFragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initFragment();
        rgBottom.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rgBottom.check(R.id.rb_bottom1);
    }
    private void findViews() {
        title = (RelativeLayout)findViewById( R.id.title );
        flMain = (FrameLayout)findViewById( R.id.fl_main );
        rgBottom = (RadioGroup)findViewById( R.id.rg_bottom );
    }

    private void initFragment(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
        fragmentList.add(new FourFragment());
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_bottom1:
//                    Toast.makeText(MainActivity.this,"1",Toast.LENGTH_SHORT).show();
                    position = 0;
                    break;
                case R.id.rb_bottom2:
//                    Toast.makeText(MainActivity.this,"2",Toast.LENGTH_SHORT).show();
                    position = 1;
                    break;
                case R.id.rb_bottom3:
//                    Toast.makeText(MainActivity.this,"3",Toast.LENGTH_SHORT).show();
                    position = 2;
                    break;
                case R.id.rb_bottom4:
//                    Toast.makeText(MainActivity.this,"4",Toast.LENGTH_SHORT).show();
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            BaseFragment to = fragmentList.get(position);
            switchFragment(currentFragment,to);
        }
    }
    private void switchFragment(BaseFragment from,BaseFragment to){

        if (from != to){
            currentFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()){
                if (from != null){
                    ft.hide(from);
                }
                if (to != null){
                    ft.add(R.id.fl_main,to);
                    ft.commit();
                }
            }else {
                if (from != null){
                    ft.hide(from);
                }
                if (to != null){
                    ft.show(to);
                    ft.commit();
                }
            }

        }
    }

//    private void switchFragment(){
//        BaseFragment currentFragment = fragmentList.get(position);
//        //得到FragmentManager
//        FragmentManager fm = getSupportFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //替换
//        transaction.replace(R.id.fl_main,currentFragment);
//        //提交
//        transaction.commit();
//    }
}

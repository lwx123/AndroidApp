package com.lwx.fish.myapplication.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.fish.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusSendActivity extends Activity implements View.OnClickListener {
    private Button btnEventbutSend;
    private Button btnEventbutSticky;
    private TextView tvEventbusResult;

    private boolean isFirstFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);
        findViews();
    }

    private void findViews() {
        btnEventbutSend = (Button)findViewById( R.id.btn_eventbut_send );
        btnEventbutSticky = (Button)findViewById( R.id.btn_eventbut_sticky );
        tvEventbusResult = (TextView)findViewById( R.id.tv_eventbus_result );

        btnEventbutSend.setOnClickListener( this );
        btnEventbutSticky.setOnClickListener( this );
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageStickyEvent(MessageStickyEvent event){
        Log.e("---bbbb--",""+event.getMessage());
        tvEventbusResult.setText(event.getMessage());
    }

    @Override
    public void onClick(View v) {
        if ( v == btnEventbutSend ) {
            MessageEvent event = new MessageEvent();
            event.setMessage("大家好，hello everyone!");
            Log.e("---222--",event.getMessage());
            EventBus.getDefault().post(event);
            finish();
        } else if ( v == btnEventbutSticky ) {
            if (isFirstFlag) {
                isFirstFlag = false;
                EventBus.getDefault().register(this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}

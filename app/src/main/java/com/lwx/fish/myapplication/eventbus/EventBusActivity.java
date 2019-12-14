package com.lwx.fish.myapplication.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.fish.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends Activity implements View.OnClickListener {

    private Button btnEventbutSend;
    private Button btnEventbutSticky;
    private TextView tvEventbusResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        findViews();
        EventBus.getDefault().register(this);
    }

    private void findViews() {
        btnEventbutSend = (Button)findViewById( R.id.btn_eventbut_send );
        btnEventbutSticky = (Button)findViewById( R.id.btn_eventbut_sticky );
        tvEventbusResult = (TextView)findViewById( R.id.tv_eventbus_result );

        btnEventbutSend.setOnClickListener( this );
        btnEventbutSticky.setOnClickListener( this );
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        Log.e("---111--",""+event.getMessage());
        tvEventbusResult.setText(event.getMessage());
    }

//    @Subscribe
//    public void handleSomethingElse(SomeOtherEvent event) {
//        doSomethingWith(event);
//    }


    @Override
    public void onClick(View v) {
        if ( v == btnEventbutSend ) {
            startActivity(new Intent(this, EventBusSendActivity.class));
        } else if ( v == btnEventbutSticky ) {
            MessageStickyEvent event = new MessageStickyEvent();
            event.setMessage("大家好，i'm is sticky event!");
            EventBus.getDefault().postSticky(event);
            startActivity(new Intent(this, EventBusSendActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

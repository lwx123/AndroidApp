package com.lwx.fish.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lwx.fish.myapplication.R;
import com.lwx.fish.myapplication.base.BaseActivity;
import com.lwx.fish.myapplication.view.MyTextView;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class ThreadActivity extends BaseActivity implements View.OnClickListener {

    private Button button;
    private MyTextView tv_test;
    private EditText et_money;

    private Handler mHandler1;
    private Handler mHandler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        findViews();
    }
    private void findViews() {
        button = (Button)findViewById( R.id.button );
        tv_test = findViewById(R.id.tv_test);
        et_money = findViewById(R.id.editText);

        button.setOnClickListener( this );
        tv_test.setOnClickListener( this );


        mHandler1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                button.setText("倒计时："+msg.what);
            }
        };
    }
    @Override
    public void onClick(View v) {
        if ( v == button ) {

            Map<String, SoftReference<Object>> map = new HashMap<>();
            for (int i = 1; i <= 10; i++) {
                //1.通过Handler + Message的方式实现倒计时
//                Message msg = Message.obtain(mHandler1);
//                msg.what = 10 - i;
//                mHandler1.sendMessageDelayed(msg,1000 * i);

                //2.通过Handler + Runnable的方式实现倒计时
                final int second = i;
                mHandler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setText("倒计时："+(10-second));
                    }
                },1000*i);
            }
        }else if (v == tv_test){
            String money = et_money.getText().toString().trim();
            digitUppercase(money);
        }
    }

    /**
     * 数字金额大写转换
     * 要用到正则表达式
     */
    public static String digitUppercase(String money){
        if (money.length() == 0)
            return "";
        if (Double.parseDouble(money) == 0)
            return "零圆整";

        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"圆", "万", "亿"}, {"", "拾", "佰", "仟"}};

        String [] numArray = money.split("\\.");

        String amountInWords = "";

        double n = Double.parseDouble(money);
        int integerPart = (int)Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String temp ="";
            int tempNum = integerPart%10000;
            if (tempNum != 0 || i == 0) {
                for (int j = 0; j < unit[1].length && integerPart > 0; j++) {
                    temp = digit[integerPart%10]+unit[1][j] + temp;
                    integerPart = integerPart/10;
                }
                /*
                 *正则替换，加上单位
                 *把零佰零仟这种去掉，再去掉多余的零
                 */
                amountInWords = temp.replaceAll("(零.)+", "零").replaceAll("^$", "零").replaceAll("(零零)+", "零") + unit[0][i] + amountInWords;
            } else {
                integerPart /= 10000;
                temp = "零";
                amountInWords = temp + amountInWords;
            }
            amountInWords = amountInWords.replace("零" + unit[0][i], unit[0][i] + "零");
            if (i > 0) amountInWords = amountInWords.replace("零" + unit[0][i-1], unit[0][i-1] + "零");
        }

        String fWordsStr = "";
        if (numArray.length > 1) {
            String fStr = numArray[1];
            int iLen = fraction.length < fStr.length() ? fraction.length : fStr.length();
            for (int i = 0; i < iLen; i++) {
                int numInt = Integer.parseInt(fStr.substring(i, i+ 1));
                if (numInt == 0) continue;
                if (amountInWords.length() > 0 && fWordsStr.length() == 0 && i > 0)
                    fWordsStr = "零";
                fWordsStr += (digit[numInt] + fraction[i]);
            }
        }
        if (fWordsStr.length() == 0) fWordsStr = "整";
        amountInWords = amountInWords + fWordsStr;
        amountInWords = amountInWords.replaceAll("(零零)+", "零").replace("零整", "整");

        return  amountInWords;
    }

}

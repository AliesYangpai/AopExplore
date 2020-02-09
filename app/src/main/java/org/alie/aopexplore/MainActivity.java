package org.alie.aopexplore;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.alie.aopexplore.annotation.BehaviorConnectFunction;
import org.alie.aopexplore.annotation.BehaviorTrace;

import java.util.Random;

/**
 * 本类来实现点击控件之后执行相关呼叫业务，之后还要统计到这些业务的的相关耗时；
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {
    public static final String TAG = "Main";
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

    }

    private void initListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                doSingleConnect("11");
                break;
            case R.id.btn2:
                doSingleEmergencyConnect("22");
                break;
            case R.id.btn3:
                doDoubleConnect("33");
                break;
            case R.id.btn4:
                doDoubleEmergencyConnect("44");
                break;
        }
    }

//    @BehaviorTrace("单工呼叫")
    @BehaviorConnectFunction(name = "单工呼叫()")
    private void doSingleConnect(String id) {
        SystemClock.sleep(new Random().nextInt(2000));
    }

//    @BehaviorTrace("单工紧急呼叫")
    @BehaviorConnectFunction(name = "单工紧急呼叫()")
    private void doSingleEmergencyConnect(String id) {
        SystemClock.sleep(new Random().nextInt(2000));
    }

//    @BehaviorTrace("全双工呼叫")
    @BehaviorConnectFunction(name = "全双工呼叫()")
    private void doDoubleConnect(String id) {
        SystemClock.sleep(new Random().nextInt(2000));
    }

//    @BehaviorTrace("全双工紧急呼叫")
    @BehaviorConnectFunction(name = "全双工紧急呼叫（）")
    private void doDoubleEmergencyConnect(String id) {
        SystemClock.sleep(new Random().nextInt(2000));
    }
}

package com.hurui.gitapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyTouchView touchView ;//滑动View

    private TextView xTv ; //显示x轴上面的速度
    private TextView yTv ; //显示y轴上面的速度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchView = (MyTouchView) findViewById(R.id.myView);

        xTv = (TextView) findViewById(R.id.xTv);

        yTv = (TextView) findViewById(R.id.yTv);


        touchView.setCallBack(new MyTouchView.CallBack() {
            @Override
            public void shudu(int xVeloct, int yVeloct) {
                xTv.setText(xVeloct + "px/s");
                yTv.setText(yVeloct + "px/s");
            }
        });
    }
}

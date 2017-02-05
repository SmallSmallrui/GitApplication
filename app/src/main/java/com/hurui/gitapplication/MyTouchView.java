package com.hurui.gitapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by lenovo on 2017/2/5.
 */

public class MyTouchView extends View {

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        public void shudu(int xVeloct, int yVeloct);
    }

    private VelocityTracker velocityTracker;
    private int mPointerId;

    public MyTouchView(Context context) {
        super(context);
    }

    public MyTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointerId = event.getPointerId(0);
                Log.e("test" , "ACTION_DOWN" ) ;
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.computeCurrentVelocity(1000);
                int xVelocty = (int) velocityTracker.getXVelocity();//x轴上面的速度
                int yVelocty = (int) velocityTracker.getYVelocity();//y轴上面的速度
                if (callBack != null) {
                    callBack.shudu(xVelocty, yVelocty);
                }
                Log.e("test" , "action_move" ) ;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test" , "action_up") ;
                smoothScrollTo((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_CANCEL:
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    Scroller scroller = new Scroller(getContext());

    private void smoothScrollTo(int destX , int dextY){
        int scrollX = getScrollX() ;
        int delta = destX - scrollX ;
        scroller.startScroll(scrollX , 0 , delta , 0 , 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX() , scroller.getCurrY() );
            postInvalidate();

        }
    }
}

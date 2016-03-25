package com.diandian.ycdyus.moneymanager.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by ycdyus on 2016/3/1.
 */
public class MyImageView extends ImageView{
    private float currX;
    private float currY;
    private int centerX;
    private int centerY;
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_CENTER = 4;
    private MyImageViewMoveEvent moveEvent;
    private int direction;

    public MyImageViewMoveEvent getMoveEvent() {
        return moveEvent;
    }

    public void setMoveEvent(MyImageViewMoveEvent moveEvent) {
        this.moveEvent = moveEvent;
    }

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                currX = event.getX();
                currY = event.getY();
                if(event.getX()<=centerX+150&&event.getX()>=centerX-150&&event.getY()<=centerY+150&&event.getY()>=centerY-150){
                    if(moveEvent!=null)
                        moveEvent.onCenterClick();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(event.getX()<=centerX+100&&event.getX()>=centerX-100&&event.getY()<=centerY+100&&event.getY()>=centerY-100){
                    if(moveEvent!=null){
                        moveEvent.onMoveEvent(DIRECTION_CENTER);
                        direction = DIRECTION_CENTER;
                    }
                }else{
                    if(Math.abs(event.getY()-currY)>Math.abs(event.getX()-currX)){
                        //上下滑动中
                        if(event.getY()>currY){
                            if(moveEvent!=null) {
                                moveEvent.onMoveEvent(DIRECTION_DOWN);
                                direction = DIRECTION_DOWN;
                            }
                        }else{
                            if(moveEvent!=null) {
                                moveEvent.onMoveEvent(DIRECTION_UP);
                                direction = DIRECTION_UP;
                            }
                        }
                    }else{
                        //左右滑动中
                        if(currX>event.getX()){
                            if(moveEvent!=null) {
                                moveEvent.onMoveEvent(DIRECTION_LEFT);
                                direction = DIRECTION_LEFT;
                            }
                        }else{
                            if (moveEvent!=null) {
                                moveEvent.onMoveEvent(DIRECTION_RIGHT);
                                direction = DIRECTION_RIGHT;
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(moveEvent!=null)
                    moveEvent.onCenterClickComplete(direction);
                direction = DIRECTION_CENTER;
                break;
        }
        return true;
    }

    public interface MyImageViewMoveEvent{
        void onMoveEvent(int direction);
        void onCenterClick();
        void onCenterClickComplete(int direction);
    }
}

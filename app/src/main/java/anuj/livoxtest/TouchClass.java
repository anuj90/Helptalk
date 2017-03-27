package anuj.livoxtest;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

public class TouchClass extends View {

    private float x, y;
    float X, Y;
    float size = 0;
    private float touchMajor, touchMinor;
    boolean touching = false;

    public TouchClass(Context context) {
        super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        size =event.getSize();      // get size of the touch

        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                touchMajor = event.getTouchMajor();
                touchMinor = event.getTouchMinor();

                touching = true;
                break;
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                touchMajor = event.getTouchMajor();
                touchMinor = event.getTouchMinor();
                touching = true;
                break;
            case MotionEvent.ACTION_UP:
                touching = false;
                break;
            default:

                touching = false;
        }

        X = x + (touchMinor/2);     //Calculate the x coordinate
        Y = y + (touchMajor/2);     //Calculate the y coordinate

        invalidate();
        return true;
    }

}


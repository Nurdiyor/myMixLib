package uz.micro.star.mixlib;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MySwitchButton extends View {
    private static final String TAG = "ABC";
    private static final boolean DEBUG = true;
    private boolean switched;
    private OnSwitchListener onSwitchListener;

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        this.onSwitchListener = onSwitchListener;
    }

    public MySwitchButton(Context context) {
        super(context);
        init();
    }

    public MySwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setMinimumHeight(dpToPx(80));
//        setMinimumWidth(dpToPx(160));
        setSwitched(false);
//        super.setOnClickListener(this::click);
        setClickable(true);
        super.setOnTouchListener(this::touch);
//        LayoutInflater
//        addView()
    }

    public boolean touch(View v, MotionEvent event) {
        if (DEBUG) {
            Log.d(TAG, "touch: getX: " + event.getX());
            Log.d(TAG, "touch: getY: " + event.getY());
            Log.d(TAG, "touch: getRawX: " + event.getRawX());
            Log.d(TAG, "touch: getRawY: " + event.getRawY());
        }
        if (event.getX() >= 0
                && event.getX() <= getWidth()
                && event.getY() >= 0
                && event.getY() <= getHeight()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (getWidth() / 2 >= event.getX() && !isSwitched()) {
                    setSwitched(true);
                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (getWidth() / 2 <= event.getX() && isSwitched()) {
                    setSwitched(false);
                }
            }
        }
        return false;
    }

    public boolean isSwitched() {
        return switched;
    }

    public void setSwitched(boolean switched) {
        this.switched = switched;
        setBackgroundResource(switched ? R.drawable.switch_button_on : R.drawable.switch_button_off);
        if (onSwitchListener != null) {
            onSwitchListener.onSwitch(switched);
        }
    }

    public interface OnSwitchListener {
        void onSwitch(boolean isSwitched);
    }

    @Override
    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int k = Math.min(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(2 * k, k);
    }
}

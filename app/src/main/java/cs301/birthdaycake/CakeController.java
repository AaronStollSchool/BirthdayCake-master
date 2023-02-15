package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener, View.OnTouchListener{
    private final CakeView view;
    private CakeModel model;

    public CakeController(CakeView object){
        view = object;
        model = view.getModel();
    }

    @Override
    public void onClick(View button) {
        Log.d("click", "click");
        model.lit = false;
        view.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d("change", "changed");
        if(b){
            model.candles = true;
        }
        else{
            model.candles = false;
        }
        view.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("bchange", "bar changed");
        model.numCandles = i;
        view.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("cc", "cake clicked");
        model.touchX = motionEvent.getX();
        model.touchY = motionEvent.getY();
        addBalloon(model.touchX,model.touchY);
        addRect(motionEvent.getX(), motionEvent.getY());
        return false;
    }

    public void addBalloon(float x, float y){
        model.touchX = x;
        model.touchY = y;
        model.hasTouched = true;
        view.invalidate();
    }

    //    add rectangle at a given coordinate
    public void addRect(float x, float y){
        model.x = x;
        model.y = y;
        model.hasTouched = true;
        view.invalidate();
    }
}

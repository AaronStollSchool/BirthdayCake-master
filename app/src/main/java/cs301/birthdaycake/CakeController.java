package cs301.birthdaycake;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener{
    private CakeView view;
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
}
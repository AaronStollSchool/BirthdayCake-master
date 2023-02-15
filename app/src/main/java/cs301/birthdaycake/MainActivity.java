package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView view = findViewById(R.id.cakeview);

        CakeController control = new CakeController(view);

        View blowout = findViewById(R.id.extinguish);
        blowout.setOnClickListener(control);

        CompoundButton candles = findViewById(R.id.candles);
        candles.setOnCheckedChangeListener(control);

        SeekBar numCandle = findViewById(R.id.seekBar2);
        numCandle.setOnSeekBarChangeListener(control);


        findViewById(R.id.cakeview).setOnTouchListener(control);
    }

    public void goodbye(View button){
        Log.i("button2", "Goodbye");
    }
}

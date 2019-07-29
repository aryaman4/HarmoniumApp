package aryaman.harmonium;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saButton = (Button) findViewById(R.id.saButton);
        saButton.setOnTouchListener(this);
        mp = MediaPlayer.create(this, R.raw.hedwig);

    }
    @Override
    public boolean onTouch (View v, MotionEvent event) {
        switch (event.getAction())
        {

            case MotionEvent.ACTION_DOWN:
            {
                mp.setLooping(true);
                mp.start();
            }

            break;
            case MotionEvent.ACTION_UP:
            {
                mp.pause();
            }
            break;
        }

        return true;
    }
}

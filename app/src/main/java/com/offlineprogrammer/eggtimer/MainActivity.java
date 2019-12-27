package com.offlineprogrammer.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView countdownTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void buttonClicked (View view){
        Log.i("Button Clicked","Working");

        if (counterIsActive == false) {

            timerSeekBar.setEnabled(false);
            goButton.setText("STOP!");
            counterIsActive = true;

             countDownTimer =  new CountDownTimer((timerSeekBar.getProgress()*1000) + 100,1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished/1000);

                }

                @Override
                public void onFinish() {

                    Log.i("Finished", "Timer all done");
                    counterIsActive = false;
                    timerSeekBar.setEnabled(true);
                    goButton.setText("GO!");

                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();

        } else {
            timerSeekBar.setProgress(30); // 30 seconds
            countdownTextView.setText("0:30");
            countDownTimer.cancel();
            counterIsActive = false;
            timerSeekBar.setEnabled(true);
            goButton.setText("GO!");
            return;
        }





    }

    public void updateTimer (int secondsLef) {

        int minutes = secondsLef/60;
        int seconds = secondsLef - (minutes * 60);

        String secondsString = Integer.toString(seconds);

        if (secondsString.length() == 1) {
            secondsString = "0" + secondsString;
        }

        countdownTextView.setText(Integer.toString(minutes) + ":" + secondsString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
         countdownTextView = (TextView) findViewById(R.id.countdownTextView);
         goButton = (Button) findViewById(R.id.goButton);

        timerSeekBar.setMax(600); // 10 min
        timerSeekBar.setProgress(30); // 30 seconds

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

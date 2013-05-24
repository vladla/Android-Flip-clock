package com.flip.clock;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FlipclockActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final FlipClock flipClock = (FlipClock) findViewById(R.id.flipCLock);
        Button buttonSwitch = (Button) findViewById(R.id.switch_me);
        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipClock.changeNumber();
            }
        });
        final boolean[] continueSelf = {true};
        buttonSwitch.setLongClickable(true);
        buttonSwitch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                continueSelf[0]=true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (continueSelf[0]) {
                            FlipclockActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    flipClock.changeNumber();
                                }
                            });
                            try {
                                Thread.sleep(350);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

                return true;
            }
        });

        final Button[] clearCounter = {(Button) findViewById(R.id.clear_counter)};
        clearCounter[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipClock.clearCounter();
            }
        });
        clearCounter[0].setLongClickable(true);
        clearCounter[0].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                continueSelf[0] = false;
                return true;
            }
        });

    }
}
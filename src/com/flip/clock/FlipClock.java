package com.flip.clock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FlipClock extends LinearLayout {
	
	private FlipClockDigit firstDigit;
	private FlipClockDigit secondDigit;
	private FlipClockDigit thirdDigit;
	private FlipClockDigit fourthDigit;
    private FlipClockDigit fifthDigit;
	private Handler handy;

	public FlipClock(Context context) {
		super(context);
		setupClock(context);
	}
	
	public FlipClock(Context context,AttributeSet attrs){
		super(context,attrs);
		setupClock(context);
	}
	
	public FlipClock(Context context,AttributeSet attrs,int defStyle){
		super(context,attrs,defStyle);
		setupClock(context);
	}
	
	private void setupClock(Context c){
		setOrientation(LinearLayout.HORIZONTAL);
		handy = new Handler(){
			public void handleMessage(Message m){
				fifthDigit.flipDigit();
			}
		};
		
		firstDigit = new FlipClockDigit(c){
			@Override
			public void onOverflow() {
				clearCounters();
			}
		};
		firstDigit.maxDigit = 9;
		addView(firstDigit);

		secondDigit = new FlipClockDigit(c){
			@Override
			public void onOverflow() {
				firstDigit.flipDigit();
			}
		};
		secondDigit.maxDigit = 9;
		addView(secondDigit);

        thirdDigit = new FlipClockDigit(c){
			@Override
			public void onOverflow() {
				secondDigit.flipDigit();
			}
		};
		thirdDigit.maxDigit = 9;
		addView(thirdDigit);

        fourthDigit = new FlipClockDigit(c){
			@Override
			public void onOverflow() {
				thirdDigit.flipDigit();
			}
		};
		fourthDigit.maxDigit = 9;
		addView(fourthDigit);

        fifthDigit = new FlipClockDigit(c) {
            @Override
            public void onOverflow() {
                fourthDigit.flipDigit();
            }
        };
        fifthDigit.maxDigit = 9;
        addView(fifthDigit);
	}


    public void changeNumber() {
            handy.sendEmptyMessage(0);
    }

    public void clearCounter() {
        firstDigit.clearCounters();
        secondDigit.clearCounters();
        thirdDigit.clearCounters();
        fourthDigit.clearCounters();
        fifthDigit.clearCounters();
    }
}

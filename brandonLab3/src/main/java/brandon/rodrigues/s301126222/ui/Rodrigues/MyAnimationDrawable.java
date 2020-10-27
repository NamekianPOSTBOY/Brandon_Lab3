package brandon.rodrigues.s301126222.ui.Rodrigues;

import android.graphics.drawable.AnimationDrawable;
import android.os.SystemClock;

public class MyAnimationDrawable extends AnimationDrawable {
    private volatile int duration;//its volatile because another thread will update its value
    private int currentFrame;

    public MyAnimationDrawable() {
        currentFrame = 0;
    }

    @Override
    public void run() {
        int n = getNumberOfFrames();
        currentFrame++;
        if (currentFrame >= n) {
            currentFrame = 0;
        }
        selectDrawable(currentFrame);
        scheduleSelf(this, SystemClock.uptimeMillis() + duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
        //we have to do the following or the next frame will be displayed after the old duration
        unscheduleSelf(this);
        selectDrawable(currentFrame);
        scheduleSelf(this, SystemClock.uptimeMillis()+duration);
    }

}
// Brandon Aaron Rodrigues 301126222 SEC.02 Extension taken from:
// https://stackoverflow.com/questions/4371105/how-can-i-change-the-duration-for-an-android-animationdrawable-animation-on-the/5607129#5607129
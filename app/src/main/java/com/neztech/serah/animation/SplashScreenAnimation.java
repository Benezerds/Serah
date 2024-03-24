package com.neztech.serah.animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SplashScreenAnimation {
    private ImageView imageView;

    public SplashScreenAnimation(ImageView imageView) {
        this.imageView = imageView;
    }

    public void startAnimation() {
        // Create a new translation animation
        TranslateAnimation animation = new TranslateAnimation(0, 100, 0, 100); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(1000); // set duration in ms

        imageView.startAnimation(animation);
    }
}


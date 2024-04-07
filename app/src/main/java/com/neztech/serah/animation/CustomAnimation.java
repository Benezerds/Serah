package com.neztech.serah.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.neztech.serah.R;

public class CustomAnimation {
    public static void cardViewClickAnimation(Context context, MaterialCardView cardView){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation onclick_effect = AnimationUtils.loadAnimation(context, R.anim.onclick_effect);
                cardView.startAnimation(onclick_effect);
            }
        });
    }

    public static void textViewClickAnimation(Context context, MaterialTextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation onclick_effect = AnimationUtils.loadAnimation(context, R.anim.onclick_effect);
                textView.startAnimation(onclick_effect);
            }
        });
    }

    public static void buttonClickAnimation(Context context, MaterialButton materialButton){
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation onclick_effect = AnimationUtils.loadAnimation(context, R.anim.onclick_effect);
                materialButton.startAnimation(onclick_effect);
            }
        });
    }

    public static void imageViewClickAnimation(Context context, ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation onclick_effect = AnimationUtils.loadAnimation(context, R.anim.onclick_effect);
                imageView.startAnimation(onclick_effect);
            }
        });
    }






}

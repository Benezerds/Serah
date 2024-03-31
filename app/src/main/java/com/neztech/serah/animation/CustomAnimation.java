package com.neztech.serah.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.cardview.widget.CardView;

import com.google.android.material.card.MaterialCardView;
import com.neztech.serah.R;

public class CustomAnimation {
    private MaterialCardView cardView;

    public CustomAnimation(MaterialCardView cardView) {
        this.cardView = cardView;
    }

    public void cardViewClickAnimation(Context context, CardView cardView){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation onclick_effect = AnimationUtils.loadAnimation(context, R.anim.onclick_effect);
                cardView.startAnimation(onclick_effect);
            }
        });
    }



}

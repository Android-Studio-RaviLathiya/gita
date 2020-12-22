package com.gita.bhagwatgita;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import androidx.recyclerview.widget.ItemTouchHelper;

public class Entry extends Activity implements Animation.AnimationListener {
    public static final String DEFAULT = "0";
    Animation animFadein;
//    ImageView cornerup1;
//    ImageView cornerup2;
//    ImageView cornerup3;
//    ImageView cornerup4;
    ImageView imgbhagwatgeeta;
//    ImageView imgtextdown;
//    ImageView imgtextup;
    private PendingIntent pendingIntent;

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_entry);

        this.imgbhagwatgeeta = (ImageView) findViewById(R.id.imageView13);
//        this.imgtextup = (ImageView) findViewById(R.id.imageView14);
//        this.imgtextdown = (ImageView) findViewById(R.id.imageView15);
//        this.cornerup1 = (ImageView) findViewById(R.id.imageView);
//        this.cornerup2 = (ImageView) findViewById(R.id.imageView2);
//        this.cornerup3 = (ImageView) findViewById(R.id.imageView3);
//        this.cornerup4 = (ImageView) findViewById(R.id.imageView4);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -200.0f, 0.0f);
                translateAnimation.setDuration(1000);
                TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, 200.0f, 0.0f);
                translateAnimation2.setDuration(1000);
//                Entry.this.imgtextup.setAnimation(translateAnimation);
//                Entry.this.imgtextdown.setAnimation(translateAnimation2);
//                Entry.this.imgtextup.setVisibility(0);
//                Entry.this.imgtextdown.setVisibility(0);
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(Entry.this.imgbhagwatgeeta, "alpha", new float[]{0.0f, 1.0f});
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(Entry.this.imgbhagwatgeeta, "scaleX", new float[]{1.1f});
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(Entry.this.imgbhagwatgeeta, "scaleY", new float[]{1.1f});
//                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(Entry.this.cornerup1, "alpha", new float[]{0.0f, 1.0f});
//                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(Entry.this.cornerup2, "alpha", new float[]{0.0f, 1.0f});
//                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(Entry.this.cornerup3, "alpha", new float[]{0.0f, 1.0f});
//                ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(Entry.this.cornerup4, "alpha", new float[]{0.0f, 1.0f});
                ofFloat.setDuration(1000);
//                ofFloat4.setDuration(1000);
//                ofFloat5.setDuration(1000);
//                ofFloat6.setDuration(1000);
//                ofFloat7.setDuration(1000);
                ofFloat2.setDuration(1000);
                ofFloat3.setDuration(1000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
//                animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4).with(ofFloat5).with(ofFloat6).with(ofFloat7);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                    }
                });
                animatorSet.start();
                Entry.this.imgbhagwatgeeta.setVisibility(0);
//                Entry.this.cornerup1.setVisibility(0);
//                Entry.this.cornerup2.setVisibility(0);
//                Entry.this.cornerup3.setVisibility(0);
//                Entry.this.cornerup4.setVisibility(0);
            }
        }, 1000);
        new Thread() {
            int wait = ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;

            public void run() {
                Intent intent;
                try {
                    super.run();
                    while (this.wait < 400) {
                        sleep(1000);
                        this.wait += 100;
                    }
                    intent = new Intent(Entry.this.getBaseContext(), Entry2.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    intent = new Intent(Entry.this.getBaseContext(), Entry2.class);
                } catch (Throwable th) {
                    Entry.this.startActivity(new Intent(Entry.this.getBaseContext(), Entry2.class));
                    Entry.this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    Entry.this.finish();
                    throw th;
                }
                Entry.this.startActivity(intent);
                Entry.this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                Entry.this.finish();
            }
        }.start();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation != 2) {
            super.onConfigurationChanged(configuration);
        }
    }
}

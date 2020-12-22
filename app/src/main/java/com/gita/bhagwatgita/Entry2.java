package com.gita.bhagwatgita;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class Entry2 extends Activity implements BillingProcessor.IBillingHandler {

    ImageButton aboutusicon;
    InterstitialAd mInterstitialAd;
    ImageView myView;
    ImageView next1;
    ImageView next2;
    ImageView next3;
    ImageButton questionicon;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv5;
    RelativeLayout box, box2, box3;
    ImageView share;
    BillingProcessor bp;
    String produck_key = "bhagvat_gita_month";
    String listion_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk4RRYg/UdhNY1dso//kPOq+CHNptGK1Pbsz9viEuAw239G+3wxhsOdsfYwC1MA6KluS86Xtvvu3f0UncVZpyd18pb0kt82Qh3taOELUOt+yQTnS6KwhMT1DwhwZe4YvbzWG8kX0Kgyzl86lRqynvJ+cE+A1nnjx0DNWtDiBiBFIFrPtu5u7hTHG89Udy80VaB3/FdjczYFPSZFsQkAj/ky7lfdnctABBVfzQppikH5NHNBHQvjWSS7GyCQSRAEEJlDZZqGbfXZZIbdBtGevbOgSVdP62EAFTJd7LLSvl3t8egMlf3QOtoCfA+5mrDBYSURDL25poTfScZB32eAhXcQIDAQAB";

    String week_key = "bhagwatgita_week";
    String month_key = "bhagwatgita_month";
    String sixmonth_key = "bhagwatgita_sixmonth";
    String year_key = "bhagwatgita_year";


    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_entry2);


        bp = new BillingProcessor(this, listion_key, this);

        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-7528823107761126/4531509517");
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                Entry2.this.requestNewInterstitial();
            }
        });


        requestNewInterstitial();


        this.box = (RelativeLayout) findViewById(R.id.box);
        this.box2 = (RelativeLayout) findViewById(R.id.box2);
        this.box3 = (RelativeLayout) findViewById(R.id.box3);
        this.share = (ImageView) findViewById(R.id.share);

        this.tv1 = (TextView) findViewById(R.id.textView3);
        this.tv2 = (TextView) findViewById(R.id.textView4);
        this.tv3 = (TextView) findViewById(R.id.textView6);
        this.aboutusicon = (ImageButton) findViewById(R.id.imageButton3);
        this.questionicon = (ImageButton) findViewById(R.id.imageButton4);
        this.myView = (ImageView) findViewById(R.id.imageView9);
        this.next1 = (ImageView) findViewById(R.id.imageView10);
        this.next2 = (ImageView) findViewById(R.id.imageView11);
        this.next3 = (ImageView) findViewById(R.id.imageView12);

        Typeface createFromAsset = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lohit-Gujarati.ttf");

        this.tv1.setText("भगवद् गीता");
        this.tv2.setTypeface(createFromAsset);


        if (prefrence.getinappStatus() == false) {
            this.tv2.setText("ભગવદ્ ગીતા BUY");
        } else {
            this.tv2.setText("ભગવદ્ ગીતા");
        }


        this.tv3.setText("Bhagavad Geeta");

        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -200.0f, 0.0f);
        translateAnimation.setDuration(2000);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, -270.0f, 0.0f);
        translateAnimation2.setDuration(2000);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(0.0f, 0.0f, -350.0f, 0.0f);
        translateAnimation3.setDuration(2000);

        this.tv1.setAnimation(translateAnimation);
        this.tv2.setAnimation(translateAnimation2);
        this.tv3.setAnimation(translateAnimation3);

        this.questionicon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Entry2.this.startActivity(new Intent(Entry2.this.getApplicationContext(), Questions.class));
                Entry2.this.mInterstitialAd.show();
            }
        });
        this.aboutusicon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Entry2.this.startActivity(new Intent(Entry2.this.getApplicationContext(), AboutUs.class));
                Entry2.this.mInterstitialAd.show();
            }
        });


        this.tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                intent.putExtra("lang", 1);
                Entry2.this.startActivity(intent);
                Entry2.this.mInterstitialAd.show();
            }
        });

        this.next1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                intent.putExtra("lang", 1);
                Entry2.this.startActivity(intent);
                Entry2.this.mInterstitialAd.show();
            }
        });

        this.box.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                intent.putExtra("lang", 1);
                Entry2.this.startActivity(intent);
                Entry2.this.mInterstitialAd.show();

            }
        });


        this.tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (prefrence.getinappStatus() == false) {
                    inapppurches();

                } else {
                    Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                    intent.putExtra("lang", 0);
                    Entry2.this.startActivity(intent);
                    Entry2.this.mInterstitialAd.show();

                }
            }
        });
        this.next2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (prefrence.getinappStatus() == false) {
                    inapppurches();
                } else {
                    Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                    intent.putExtra("lang", 0);
                    Entry2.this.startActivity(intent);
                    Entry2.this.mInterstitialAd.show();
                }


            }
        });
        this.box2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (prefrence.getinappStatus() == false) {
                    inapppurches();
                } else {
                    Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                    intent.putExtra("lang", 0);
                    Entry2.this.startActivity(intent);
                    Entry2.this.mInterstitialAd.show();

                }
            }
        });


        this.tv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                intent.putExtra("lang", 2);
                Entry2.this.startActivity(intent);
                Entry2.this.mInterstitialAd.show();
            }
        });
        this.next3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                intent.putExtra("lang", 2);
                Entry2.this.startActivity(intent);
                Entry2.this.mInterstitialAd.show();
            }
        });

        this.box3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Entry2.this.getApplicationContext(), Adhyaylist.class);
                intent.putExtra("lang", 2);
                Entry2.this.startActivity(intent);
                Entry2.this.mInterstitialAd.show();
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shareBody = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));

            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                final ObjectAnimator ofFloat = ObjectAnimator.ofFloat(Entry2.this.myView, "alpha", new float[]{0.0f, 1.0f});
                final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(Entry2.this.myView, "scaleX", new float[]{1.15f});
                final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(Entry2.this.myView, "scaleY", new float[]{1.15f});
                ofFloat.setDuration(2500);
                ofFloat2.setDuration(2000);
                ofFloat3.setDuration(2000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
                final AnimatorSet animatorSet2 = animatorSet;
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        animatorSet2.play(ofFloat).with(ofFloat2).with(ofFloat3).after(10000);
                    }
                });
                animatorSet.start();
                Entry2.this.myView.setVisibility(0);
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -15.0f, 0.0f, 0.0f);
                translateAnimation.setDuration(1500);
                translateAnimation.setRepeatMode(1);
                translateAnimation.setRepeatCount(-1);
                Entry2.this.next1.setAnimation(translateAnimation);
                Entry2.this.next2.setAnimation(translateAnimation);
                Entry2.this.next3.setAnimation(translateAnimation);
                Entry2.this.next1.setVisibility(0);
                Entry2.this.next2.setVisibility(0);
                Entry2.this.next3.setVisibility(0);

                box.setVisibility(View.VISIBLE);
                box2.setVisibility(View.VISIBLE);
                box3.setVisibility(View.VISIBLE);

            }
        }, 2500);
        ObjectAnimator.ofFloat(this.myView, "alpha", new float[]{1.0f, 0.0f}).setDuration(2000);


        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inapp();
            }
        });
    }


    private void inapppurches() {

        final String[] listItems = {"Week Package", "Month Package", "Six Month Package", "Year Package"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Entry2.this);
        builder.setTitle("Choose Package...");
        builder.setSingleChoiceItems(listItems, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (i == 0) {
                    bp.purchase(Entry2.this, week_key);

                } else if (i == 1) {
                    bp.purchase(Entry2.this, month_key);


                } else if (i == 2) {
                    bp.purchase(Entry2.this, sixmonth_key);


                } else if (i == 3) {
                    bp.purchase(Entry2.this, year_key);


                }
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    private void inapp() {


        bp.purchase(Entry2.this, produck_key);

    }

    /* access modifiers changed from: private */
    public void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("test devices id").build());
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation != 2) {
            super.onConfigurationChanged(configuration);
        }
    }


    //Billing method
    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        prefrence.setinappStatus(true);
        recreate();
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPurchaseHistoryRestored() {
//        Toast.makeText(this, "Restored", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        Toast.makeText(this, "Your transaction cannot be completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {
//        Toast.makeText(this, "BillingInitialized", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);

//            Toast.makeText(this, "ok result", Toast.LENGTH_SHORT).show();

        }else {

//            Toast.makeText(this, "not ok result", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

}

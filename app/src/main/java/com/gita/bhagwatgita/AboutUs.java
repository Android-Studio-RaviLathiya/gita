package com.gita.bhagwatgita;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdError;

public class AboutUs extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    /* access modifiers changed from: protected */
    ImageView imageView22, imglogo;
    private static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk4RRYg/UdhNY1dso//kPOq+CHNptGK1Pbsz9viEuAw239G+3wxhsOdsfYwC1MA6KluS86Xtvvu3f0UncVZpyd18pb0kt82Qh3taOELUOt+yQTnS6KwhMT1DwhwZe4YvbzWG8kX0Kgyzl86lRqynvJ+cE+A1nnjx0DNWtDiBiBFIFrPtu5u7hTHG89Udy80VaB3/FdjczYFPSZFsQkAj/ky7lfdnctABBVfzQppikH5NHNBHQvjWSS7GyCQSRAEEJlDZZqGbfXZZIbdBtGevbOgSVdP62EAFTJd7LLSvl3t8egMlf3QOtoCfA+5mrDBYSURDL25poTfScZB32eAhXcQIDAQAB";
    //    private static final String PRODUCT_ID = "bhagvat_gita_month";
    BillingProcessor bp;

    /*subscribtion in-app purchase*/
    private static final String week_key = "bhagwatgita_week";
    private static final String month_key = "bhagwatgita_month";
    private static final String sixmonth_key = "bhagwatgita_sixmonth";
    private static final String year_key = "bhagwatgita_year";

    //in app purchase
    public void onBillingError(int i, Throwable th) {
        Toast.makeText(this, "Error " + th, Toast.LENGTH_SHORT).show();
    }

    public void onBillingInitialized() {

    }

    public void onProductPurchased(String str, TransactionDetails transactionDetails) {
        Toast.makeText(this, "Payment Success Enjoy", Toast.LENGTH_SHORT).show();
    }

    public void onPurchaseHistoryRestored() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_about_us);

        imageView22 = findViewById(R.id.imageView22);
        imglogo = findViewById(R.id.imglogo);
        inapppurchase(AboutUs.this);


        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AboutUs.this.bp.purchase(AboutUs.this, AboutUs.PRODUCT_ID);

            }
        });

        imglogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (prefrence.getinapppurchase().equals("ok")) {

                    startActivity(new Intent(AboutUs.this, MainActivity.class));

                } else if (prefrence.getinapppurchase().equals("nook")) {

                    final String[] listItems = {"Week Package", "Month Package", "Six Month Package", "Year Package"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(AboutUs.this);
                    builder.setTitle("Choose Package...");
                    builder.setSingleChoiceItems(listItems, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            if (i == 0) {
//                            bp.purchase(AboutUs.this, week_key);
                                AboutUs.this.bp.purchase(AboutUs.this, AboutUs.week_key);

                            } else if (i == 1) {
//                            bp.purchase(AboutUs.this, month_key);
                                AboutUs.this.bp.purchase(AboutUs.this, AboutUs.month_key);

                            } else if (i == 2) {
//                            bp.purchase(AboutUs.this, sixmonth_key);
                                AboutUs.this.bp.purchase(AboutUs.this, AboutUs.sixmonth_key);


                            } else if (i == 3) {
//                            bp.purchase(AboutUs.this, year_key);
                                AboutUs.this.bp.purchase(AboutUs.this, AboutUs.year_key);

                            }
                            dialog.dismiss();

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }


            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inapppurchase(Context context) {

        BillingProcessor billingProcessor = new BillingProcessor(this, LICENSE_KEY, AboutUs.this);
        this.bp = billingProcessor;
        billingProcessor.initialize();

        if (this.bp.isPurchased(week_key)) {
//            Toast.makeText(context, "Purches week", Toast.LENGTH_SHORT).show();
            prefrence.setinapppurchase("ok");
        } else {
//            Toast.makeText(context, "Purches Not", Toast.LENGTH_SHORT).show();
            prefrence.setinapppurchase("nook");

        }


    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {

        BillingProcessor billingProcessor = this.bp;
        if (billingProcessor != null) {
            billingProcessor.release();
        }
        super.onDestroy();


    }
}

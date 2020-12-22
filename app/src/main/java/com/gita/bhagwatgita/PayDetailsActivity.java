package com.gita.bhagwatgita;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.anjlab.android.iab.v3.BillingProcessor;
//import com.iludate.dating.R;
//import com.iludate.dating.domain.CoinPayModel;
//import com.iludate.dating.home.HomeActivity;
//import com.iludate.dating.model.Localization;
//import com.iludate.dating.preference.Preferences;
//
//import org.parceler.Parcels;

import java.util.Arrays;
import java.util.List;

public class PayDetailsActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    private Button btPayByGoogle;
    private BillingClient billingClient;
    String key = "bhagvat_gita_1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_details);

        initilizeView();
        setUpBillingClient();


    }



    private void initilizeView() {
        btPayByGoogle = findViewById(R.id.btPayByGoogle);
        btPayByGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

    }

    private void setUpBillingClient() {
        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    Toast.makeText(PayDetailsActivity.this, "Success to connect billing", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(PayDetailsActivity.this, "Failed to connect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }
    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {

    }
}

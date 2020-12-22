package com.gita.bhagwatgita;

import android.app.Application;
import android.content.SharedPreferences;

public class prefrence extends Application {


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("my", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setinappStatus(boolean inappstatus) {
        editor.putBoolean("inappstatus", inappstatus).commit();
    }

    public static boolean getinappStatus() {
        boolean b = sharedPreferences.getBoolean("inappstatus", false);
        return b;
    }


    public static void setinapppurchase(String inapppurchase) {
        editor.putString("inapppurchase", inapppurchase).commit();
    }

    public static String getinapppurchase() {
        return sharedPreferences.getString("inapppurchase", "");
    }




}

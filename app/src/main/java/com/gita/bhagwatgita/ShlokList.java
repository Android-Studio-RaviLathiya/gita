package com.gita.bhagwatgita;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hotchemi.android.rate.AppRate;

public class ShlokList extends AppCompatActivity {
    public static final String DEFAULT = "0";
    int SetSlokList;
    AdView adView;
    String favflagsave;
    int langu;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.shlok_list);


        this.adView = (AdView) findViewById(R.id.adView1);
        this.adView.loadAd(new AdRequest.Builder().build());
        this.SetSlokList = getIntent().getExtras().getInt("AdhyayPos");
        this.langu = getIntent().getExtras().getInt("Language");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
//        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
//            this.adView.setVisibility(8);
//        }


        AppRate.with(this)
                .setInstallDays(0)     //ONE DAY HOW MANY SHOW DIALOG
                .setLaunchTimes(1)        //APP REMOVIWE 2 TIME AND SHOW DIALOG
                .setRemindInterval(1)        //CLICK NO THANK ANFTER 2 DAY SHOW DIALOG
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);    //SHOW DIALOG




















        int i = this.SetSlokList;
        if (1 == i) {
            setTitle("Adhyay 1");
            int[] iArr = new int[47];
            SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
            for (int i2 = 1; i2 < 47; i2++) {
                this.favflagsave = sharedPreferences.getString("A1" + i2, "0");
                int parseInt = Integer.parseInt(this.favflagsave);
                if (parseInt == 0) {
                    iArr[i2] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt) {
                    iArr[i2] = R.drawable.favoritestars;
                }
            }
            int i3 = this.langu;
            if (1 == i3) {
                ListAdpater listAdpater = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०१)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४-५-६", "श्लोक ७-८-९", "श्लोक १०", "श्लोक ११", "श्लोक १२-१३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७-१८", "श्लोक १९", "श्लोक २०-२१", "श्लोक २२-२३", "श्लोक २४-२५", "श्लोक २६-२७", "श्लोक २८-२९-३०", "श्लोक ३१-३२", "श्लोक ३३-३४", "श्लोक ३५", "श्लोक ३६-३७", "श्लोक ३८-३९-४०", "श्लोक ४१", "श्लोक ४२-४३-४४-४५", "श्लोक ४६", "श्लोक ४७"}, iArr);
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(listAdpater);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay1.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i3 == 0) {
                ListAdpater listAdpater2 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય (અધ્યાય ૦૧)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪-૫-૬", "શ્લોક ૭-૮-૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨-૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭-૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦-૨૧", "શ્લોક ૨૨-૨૩", "શ્લોક ૨૪-૨૫", "શ્લોક ૨૬-૨૭", "શ્લોક ૨૮-૨૯-૩૦", "શ્લોક ૩૧-૩૨", "શ્લોક ૩૩-૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬-૩૭", "શ્લોક ૩૮-૩૯-૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨-૪૩-૪૪-૪૫", "શ્લોક ૪૬", "શ્લોક ૪૭"}, iArr);
                ListView listView2 = (ListView) findViewById(R.id.listView);
                listView2.setAdapter(listAdpater2);
                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay1.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i3) {
                ListAdpater listAdpater3 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 01) ", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4-5-6", "Shlok 7-8-9", "Shlok 10", "Shlok 11", "Shlok 12-13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17-18", "Shlok 19", "Shlok 20-21", "Shlok 22-23", "Shlok 24-25", "Shlok 26-27", "Shlok 28-29-30", "Shlok 31-32", "Shlok 33-34", "Shlok 35", "Shlok 36-37", "Shlok 38-39-40", "Shlok 41", "Shlok 42-43-44-45", "Shlok 46", "Shlok 47"}, iArr);
                ListView listView3 = (ListView) findViewById(R.id.listView);
                listView3.setAdapter(listAdpater3);
                listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay1.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (2 == i) {
            setTitle("Adhyay 2");
            int[] iArr2 = new int[72];
            SharedPreferences sharedPreferences2 = getSharedPreferences("Favorite", 0);
            for (int i4 = 1; i4 < 72; i4++) {
                this.favflagsave = sharedPreferences2.getString("A2" + i4, "0");
                int parseInt2 = Integer.parseInt(this.favflagsave);
                if (parseInt2 == 0) {
                    iArr2[i4] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt2) {
                    iArr2[i4] = R.drawable.favoritestars;
                }
            }
            int i5 = this.langu;
            if (1 == i5) {
                ListAdpater listAdpater4 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०२)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६", "श्लोक ३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१", "श्लोक ४२-४३-४४", "श्लोक ४५", "श्लोक ४६", "श्लोक ४७", "श्लोक ४८", "श्लोक ४९", "श्लोक ५०", "श्लोक ५१", "श्लोक ५२", "श्लोक ५३", "श्लोक ५४", "श्लोक ५५", "श्लोक ५६", "श्लोक ५७", "श्लोक ५८", "श्लोक ५९", "श्लोक ६०", "श्लोक ६१", "श्लोक ६२", "श्लोक ६३", "श्लोक ६४", "श्लोक ६५", "श्लोक ६६", "श्लोक ६७", "श्लोक ६८", "श्लोक ६९", "श्लोक ७०", "श्लोक ७१", "श्लोक ७२"}, iArr2);
                ListView listView4 = (ListView) findViewById(R.id.listView);
                listView4.setAdapter(listAdpater4);
                listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay2.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i5 == 0) {
                ListAdpater listAdpater5 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૨)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬", "શ્લોક ૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨-૪૩-૪૪", "શ્લોક ૪૫", "શ્લોક ૪૬", "શ્લોક ૪૭", "શ્લોક ૪૮", "શ્લોક ૪૯", "શ્લોક ૫૦", "શ્લોક ૫૧", "શ્લોક ૫૨", "શ્લોક ૫૩", "શ્લોક ૫૪", "શ્લોક ૫૫", "શ્લોક ૫૬", "શ્લોક ૫૭", "શ્લોક ૫૮", "શ્લોક ૫૯", "શ્લોક ૬૦", "શ્લોક ૬૧", "શ્લોક ૬૨", "શ્લોક ૬૩", "શ્લોક ૬૪", "શ્લોક ૬૫", "શ્લોક ૬૬", "શ્લોક ૬૭", "શ્લોક ૬૮", "શ્લોક ૬૯", "શ્લોક ૭૦", "શ્લોક ૭૧", "શ્લોક ૭૨"}, iArr2);
                ListView listView5 = (ListView) findViewById(R.id.listView);
                listView5.setAdapter(listAdpater5);
                listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay2.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i5) {
                ListAdpater listAdpater6 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 02)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36", "Shlok 37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41", "Shlok 42-43-44", "Shlok 45", "Shlok 46", "Shlok 47", "Shlok 48", "Shlok 49", "Shlok 50", "Shlok 51", "Shlok 52", "Shlok 53", "Shlok 54", "Shlok 55", "Shlok 56", "Shlok 57", "Shlok 58", "Shlok 59", "Shlok 60", "Shlok 61", "Shlok 62", "Shlok 63", "Shlok 64", "Shlok 65", "Shlok 66", "Shlok 67", "Shlok 68", "Shlok 69", "Shlok 70", "Shlok 71", "Shlok 72"}, iArr2);
                ListView listView6 = (ListView) findViewById(R.id.listView);
                listView6.setAdapter(listAdpater6);
                listView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay2.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (3 == i) {
            setTitle("Adhyay 3");
            int[] iArr3 = new int[43];
            SharedPreferences sharedPreferences3 = getSharedPreferences("Favorite", 0);
            for (int i6 = 1; i6 < 43; i6++) {
                this.favflagsave = sharedPreferences3.getString("A3" + i6, "0");
                int parseInt3 = Integer.parseInt(this.favflagsave);
                if (parseInt3 == 0) {
                    iArr3[i6] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt3) {
                    iArr3[i6] = R.drawable.favoritestars;
                }
            }
            int i7 = this.langu;
            if (1 == i7) {
                ListAdpater listAdpater7 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०३)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२-१३", "श्लोक १४-१५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३-२४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१-३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६", "श्लोक ३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१", "श्लोक ४२", "श्लोक ४३"}, iArr3);
                ListView listView7 = (ListView) findViewById(R.id.listView);
                listView7.setAdapter(listAdpater7);
                listView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay3.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i7 == 0) {
                ListAdpater listAdpater8 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૩)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨-૧૩", "શ્લોક ૧૪-૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩-૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧-૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬", "શ્લોક ૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨", "શ્લોક ૪૩"}, iArr3);
                ListView listView8 = (ListView) findViewById(R.id.listView);
                listView8.setAdapter(listAdpater8);
                listView8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay3.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i7) {
                ListAdpater listAdpater9 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 03)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12-13", "Shlok 14-15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23-24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31-32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36", "Shlok 37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41", "Shlok 42", "Shlok 43"}, iArr3);
                ListView listView9 = (ListView) findViewById(R.id.listView);
                listView9.setAdapter(listAdpater9);
                listView9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay3.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (4 == i) {
            setTitle("Adhyay 4");
            int[] iArr4 = new int[42];
            SharedPreferences sharedPreferences4 = getSharedPreferences("Favorite", 0);
            for (int i8 = 1; i8 < 42; i8++) {
                this.favflagsave = sharedPreferences4.getString("A4" + i8, "0");
                int parseInt4 = Integer.parseInt(this.favflagsave);
                if (parseInt4 == 0) {
                    iArr4[i8] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt4) {
                    iArr4[i8] = R.drawable.favoritestars;
                }
            }
            int i9 = this.langu;
            if (1 == i9) {
                ListAdpater listAdpater10 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय  ०४)", "श्लोक १-२-३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७-८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९-३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६", "श्लोक ३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१", "श्लोक ४२"}, iArr4);
                ListView listView10 = (ListView) findViewById(R.id.listView);
                listView10.setAdapter(listAdpater10);
                listView10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay4.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i9 == 0) {
                ListAdpater listAdpater11 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૪)", "શ્લોક ૧-૨-૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭-૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯-૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬", "શ્લોક ૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨"}, iArr4);
                ListView listView11 = (ListView) findViewById(R.id.listView);
                listView11.setAdapter(listAdpater11);
                listView11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay4.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i9) {
                ListAdpater listAdpater12 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 04)", "Shlok 1-2-3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7-8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29-30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36", "Shlok 37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41", "Shlok 42"}, iArr4);
                ListView listView12 = (ListView) findViewById(R.id.listView);
                listView12.setAdapter(listAdpater12);
                listView12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay4.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (5 == i) {
            setTitle("Adhyay 5");
            int[] iArr5 = new int[29];
            SharedPreferences sharedPreferences5 = getSharedPreferences("Favorite", 0);
            for (int i10 = 1; i10 < 29; i10++) {
                this.favflagsave = sharedPreferences5.getString("A5" + i10, "0");
                int parseInt5 = Integer.parseInt(this.favflagsave);
                if (parseInt5 == 0) {
                    iArr5[i10] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt5) {
                    iArr5[i10] = R.drawable.favoritestars;
                }
            }
            int i11 = this.langu;
            if (1 == i11) {
                ListAdpater listAdpater13 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय  ०५)", "श्लोक १", "श्लोक २", "श्लोक३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८-९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७-२८", "श्लोक २९"}, iArr5);
                ListView listView13 = (ListView) findViewById(R.id.listView);
                listView13.setAdapter(listAdpater13);
                listView13.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay5.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i11 == 0) {
                ListAdpater listAdpater14 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૫)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮-૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭-૨૮", "શ્લોક ૨૯"}, iArr5);
                ListView listView14 = (ListView) findViewById(R.id.listView);
                listView14.setAdapter(listAdpater14);
                listView14.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay5.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i11) {
                ListAdpater listAdpater15 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 05)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8-9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27-28", "Shlok 29"}, iArr5);
                ListView listView15 = (ListView) findViewById(R.id.listView);
                listView15.setAdapter(listAdpater15);
                listView15.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay5.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (6 == i) {
            setTitle("Adhyay 6");
            int[] iArr6 = new int[47];
            SharedPreferences sharedPreferences6 = getSharedPreferences("Favorite", 0);
            for (int i12 = 1; i12 < 47; i12++) {
                this.favflagsave = sharedPreferences6.getString("A6" + i12, "0");
                int parseInt6 = Integer.parseInt(this.favflagsave);
                if (parseInt6 == 0) {
                    iArr6[i12] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt6) {
                    iArr6[i12] = R.drawable.favoritestars;
                }
            }
            int i13 = this.langu;
            if (1 == i13) {
                ListAdpater listAdpater16 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०६)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३-१४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०-२१-२२-२३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६", "श्लोक ३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१", "श्लोक ४२", "श्लोक ४३", "श्लोक ४४", "श्लोक ४५", "श्लोक ४६", "श्लोक ४७"}, iArr6);
                ListView listView16 = (ListView) findViewById(R.id.listView);
                listView16.setAdapter(listAdpater16);
                listView16.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay6.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i13 == 0) {
                ListAdpater listAdpater17 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૬)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩-૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦-૨૧-૨૨-૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬", "શ્લોક ૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨", "શ્લોક ૪૩", "શ્લોક ૪૪", "શ્લોક ૪૫", "શ્લોક ૪૬", "શ્લોક ૪૭"}, iArr6);
                ListView listView17 = (ListView) findViewById(R.id.listView);
                listView17.setAdapter(listAdpater17);
                listView17.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay6.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i13) {
                ListAdpater listAdpater18 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 06)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13-14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20-21-22-23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36", "Shlok 37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41", "Shlok 42", "Shlok 43", "Shlok 44", "Shlok 45", "Shlok 46", "Shlok 47"}, iArr6);
                ListView listView18 = (ListView) findViewById(R.id.listView);
                listView18.setAdapter(listAdpater18);
                listView18.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay6.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (7 == i) {
            setTitle("Adhyay 7");
            int[] iArr7 = new int[30];
            SharedPreferences sharedPreferences7 = getSharedPreferences("Favorite", 0);
            for (int i14 = 1; i14 < 30; i14++) {
                this.favflagsave = sharedPreferences7.getString("A7" + i14, "0");
                int parseInt7 = Integer.parseInt(this.favflagsave);
                if (parseInt7 == 0) {
                    iArr7[i14] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt7) {
                    iArr7[i14] = R.drawable.favoritestars;
                }
            }
            int i15 = this.langu;
            if (1 == i15) {
                ListAdpater listAdpater19 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०७)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४-५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०"}, iArr7);
                ListView listView19 = (ListView) findViewById(R.id.listView);
                listView19.setAdapter(listAdpater19);
                listView19.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay7.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i15 == 0) {
                ListAdpater listAdpater20 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૭)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪-૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦"}, iArr7);
                ListView listView20 = (ListView) findViewById(R.id.listView);
                listView20.setAdapter(listAdpater20);
                listView20.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay7.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i15) {
                ListAdpater listAdpater21 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 07)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4-5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30"}, iArr7);
                ListView listView21 = (ListView) findViewById(R.id.listView);
                listView21.setAdapter(listAdpater21);
                listView21.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay7.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (8 == i) {
            setTitle("Adhyay 8");
            int[] iArr8 = new int[28];
            SharedPreferences sharedPreferences8 = getSharedPreferences("Favorite", 0);
            for (int i16 = 1; i16 < 28; i16++) {
                this.favflagsave = sharedPreferences8.getString("A8" + i16, "0");
                int parseInt8 = Integer.parseInt(this.favflagsave);
                if (parseInt8 == 0) {
                    iArr8[i16] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt8) {
                    iArr8[i16] = R.drawable.favoritestars;
                }
            }
            int i17 = this.langu;
            if (1 == i17) {
                ListAdpater listAdpater22 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०८)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२- १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८"}, iArr8);
                ListView listView22 = (ListView) findViewById(R.id.listView);
                listView22.setAdapter(listAdpater22);
                listView22.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay8.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i17 == 0) {
                ListAdpater listAdpater23 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૮)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨-૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮"}, iArr8);
                ListView listView23 = (ListView) findViewById(R.id.listView);
                listView23.setAdapter(listAdpater23);
                listView23.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay8.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i17) {
                ListAdpater listAdpater24 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 08)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12-13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28"}, iArr8);
                ListView listView24 = (ListView) findViewById(R.id.listView);
                listView24.setAdapter(listAdpater24);
                listView24.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay8.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (9 == i) {
            setTitle("Adhyay 9");
            int[] iArr9 = new int[35];
            SharedPreferences sharedPreferences9 = getSharedPreferences("Favorite", 0);
            for (int i18 = 1; i18 < 35; i18++) {
                this.favflagsave = sharedPreferences9.getString("A9" + i18, "0");
                int parseInt9 = Integer.parseInt(this.favflagsave);
                if (parseInt9 == 0) {
                    iArr9[i18] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt9) {
                    iArr9[i18] = R.drawable.favoritestars;
                }
            }
            int i19 = this.langu;
            if (1 == i19) {
                ListAdpater listAdpater25 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ०९)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४"}, iArr9);
                ListView listView25 = (ListView) findViewById(R.id.listView);
                listView25.setAdapter(listAdpater25);
                listView25.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay9.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i19 == 0) {
                ListAdpater listAdpater26 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૦૯)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪"}, iArr9);
                ListView listView26 = (ListView) findViewById(R.id.listView);
                listView26.setAdapter(listAdpater26);
                listView26.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay9.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i19) {
                ListAdpater listAdpater27 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 09)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34"}, iArr9);
                ListView listView27 = (ListView) findViewById(R.id.listView);
                listView27.setAdapter(listAdpater27);
                listView27.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay9.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (10 == i) {
            setTitle("Adhyay 10");
            int[] iArr10 = new int[42];
            SharedPreferences sharedPreferences10 = getSharedPreferences("Favorite", 0);
            for (int i20 = 1; i20 < 42; i20++) {
                this.favflagsave = sharedPreferences10.getString("A10" + i20, "0");
                int parseInt10 = Integer.parseInt(this.favflagsave);
                if (parseInt10 == 0) {
                    iArr10[i20] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt10) {
                    iArr10[i20] = R.drawable.favoritestars;
                }
            }
            int i21 = this.langu;
            if (1 == i21) {
                ListAdpater listAdpater28 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १०)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४-५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२-१३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६", "श्लोक ३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१", "श्लोक ४२"}, iArr10);
                ListView listView28 = (ListView) findViewById(R.id.listView);
                listView28.setAdapter(listAdpater28);
                listView28.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay10.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i21 == 0) {
                ListAdpater listAdpater29 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૦)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪-૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨-૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬", "શ્લોક ૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨"}, iArr10);
                ListView listView29 = (ListView) findViewById(R.id.listView);
                listView29.setAdapter(listAdpater29);
                listView29.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay10.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i21) {
                ListAdpater listAdpater30 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 10)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4-5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12-13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36", "Shlok 37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41", "Shlok 42"}, iArr10);
                ListView listView30 = (ListView) findViewById(R.id.listView);
                listView30.setAdapter(listAdpater30);
                listView30.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay10.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (11 == i) {
            setTitle("Adhyay 11");
            int[] iArr11 = new int[55];
            SharedPreferences sharedPreferences11 = getSharedPreferences("Favorite", 0);
            for (int i22 = 1; i22 < 55; i22++) {
                this.favflagsave = sharedPreferences11.getString("A11" + i22, "0");
                int parseInt11 = Integer.parseInt(this.favflagsave);
                if (parseInt11 == 0) {
                    iArr11[i22] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt11) {
                    iArr11[i22] = R.drawable.favoritestars;
                }
            }
            int i23 = this.langu;
            if (1 == i23) {
                ListAdpater listAdpater31 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय ११)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०-११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६-२७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६", "श्लोक ३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१-४२", "श्लोक ४३", "श्लोक ४४", "श्लोक ४५", "श्लोक ४६", "श्लोक ४७", "श्लोक ४८", "श्लोक ४९", "श्लोक ५०", "श्लोक ५१", "श्लोक ५२", "श्लोक ५३", "श्लोक ५४", "श्लोक ५५"}, iArr11);
                ListView listView31 = (ListView) findViewById(R.id.listView);
                listView31.setAdapter(listAdpater31);
                listView31.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay11.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i23 == 0) {
                ListAdpater listAdpater32 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૧)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦-૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬-૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬", "શ્લોક ૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧-૪૨", "શ્લોક ૪૩", "શ્લોક ૪૪", "શ્લોક ૪૫", "શ્લોક ૪૬", "શ્લોક ૪૭", "શ્લોક ૪૮", "શ્લોક ૪૯", "શ્લોક ૫૦", "શ્લોક ૫૧", "શ્લોક ૫૨", "શ્લોક ૫૩", "શ્લોક ૫૪", "શ્લોક ૫૫"}, iArr11);
                ListView listView32 = (ListView) findViewById(R.id.listView);
                listView32.setAdapter(listAdpater32);
                listView32.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay11.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i23) {
                ListAdpater listAdpater33 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 11)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10-11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26-27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36", "Shlok 37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41-42", "Shlok 43", "Shlok 44", "Shlok 45", "Shlok 46", "Shlok 47", "Shlok 48", "Shlok 49", "Shlok 50", "Shlok 51", "Shlok 52", "Shlok 53", "Shlok 54", "Shlok 55"}, iArr11);
                ListView listView33 = (ListView) findViewById(R.id.listView);
                listView33.setAdapter(listAdpater33);
                listView33.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay11.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (12 == i) {
            setTitle("Adhyay 12");
            int[] iArr12 = new int[20];
            SharedPreferences sharedPreferences12 = getSharedPreferences("Favorite", 0);
            for (int i24 = 1; i24 < 20; i24++) {
                this.favflagsave = sharedPreferences12.getString("A12" + i24, "0");
                int parseInt12 = Integer.parseInt(this.favflagsave);
                if (parseInt12 == 0) {
                    iArr12[i24] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt12) {
                    iArr12[i24] = R.drawable.favoritestars;
                }
            }
            int i25 = this.langu;
            if (1 == i25) {
                ListAdpater listAdpater34 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १२)", "श्लोक १", "श्लोक २", "श्लोक ३-४-५", "श्लोक ६-७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३-१४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८-१९", "श्लोक २०"}, iArr12);
                ListView listView34 = (ListView) findViewById(R.id.listView);
                listView34.setAdapter(listAdpater34);
                listView34.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay12.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i25 == 0) {
                ListAdpater listAdpater35 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૨)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩-૪-૫", "શ્લોક ૬-૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩-૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮-૧૯", "શ્લોક ૨૦"}, iArr12);
                ListView listView35 = (ListView) findViewById(R.id.listView);
                listView35.setAdapter(listAdpater35);
                listView35.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay12.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i25) {
                ListAdpater listAdpater36 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 12)", "Shlok 1", "Shlok 2", "Shlok 3-4-5", "Shlok 6-7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13-14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18-19", "Shlok 20"}, iArr12);
                ListView listView36 = (ListView) findViewById(R.id.listView);
                listView36.setAdapter(listAdpater36);
                listView36.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay12.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (13 == i) {
            setTitle("Adhyay 13");
            int[] iArr13 = new int[35];
            SharedPreferences sharedPreferences13 = getSharedPreferences("Favorite", 0);
            for (int i26 = 1; i26 < 35; i26++) {
                this.favflagsave = sharedPreferences13.getString("A13" + i26, "0");
                int parseInt13 = Integer.parseInt(this.favflagsave);
                if (parseInt13 == 0) {
                    iArr13[i26] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt13) {
                    iArr13[i26] = R.drawable.favoritestars;
                }
            }
            int i27 = this.langu;
            if (1 == i27) {
                ListAdpater listAdpater37 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १३)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४"}, iArr13);
                ListView listView37 = (ListView) findViewById(R.id.listView);
                listView37.setAdapter(listAdpater37);
                listView37.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay13.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i27 == 0) {
                ListAdpater listAdpater38 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૩)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪"}, iArr13);
                ListView listView38 = (ListView) findViewById(R.id.listView);
                listView38.setAdapter(listAdpater38);
                listView38.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay13.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i27) {
                ListAdpater listAdpater39 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 13)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34"}, iArr13);
                ListView listView39 = (ListView) findViewById(R.id.listView);
                listView39.setAdapter(listAdpater39);
                listView39.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay13.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (14 == i) {
            setTitle("Adhyay 14");
            int[] iArr14 = new int[28];
            SharedPreferences sharedPreferences14 = getSharedPreferences("Favorite", 0);
            for (int i28 = 1; i28 < 28; i28++) {
                this.favflagsave = sharedPreferences14.getString("A14" + i28, "0");
                int parseInt14 = Integer.parseInt(this.favflagsave);
                if (parseInt14 == 0) {
                    iArr14[i28] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt14) {
                    iArr14[i28] = R.drawable.favoritestars;
                }
            }
            int i29 = this.langu;
            if (1 == i29) {
                ListAdpater listAdpater40 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १४)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७"}, iArr14);
                ListView listView40 = (ListView) findViewById(R.id.listView);
                listView40.setAdapter(listAdpater40);
                listView40.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay14.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i29 == 0) {
                ListAdpater listAdpater41 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૪)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭"}, iArr14);
                ListView listView41 = (ListView) findViewById(R.id.listView);
                listView41.setAdapter(listAdpater41);
                listView41.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay14.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i29) {
                ListAdpater listAdpater42 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 14)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27"}, iArr14);
                ListView listView42 = (ListView) findViewById(R.id.listView);
                listView42.setAdapter(listAdpater42);
                listView42.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay14.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (15 == i) {
            setTitle("Adhyay 15");
            int[] iArr15 = new int[21];
            SharedPreferences sharedPreferences15 = getSharedPreferences("Favorite", 0);
            for (int i30 = 1; i30 < 21; i30++) {
                this.favflagsave = sharedPreferences15.getString("A15" + i30, "0");
                int parseInt15 = Integer.parseInt(this.favflagsave);
                if (parseInt15 == 0) {
                    iArr15[i30] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt15) {
                    iArr15[i30] = R.drawable.favoritestars;
                }
            }
            int i31 = this.langu;
            if (1 == i31) {
                ListAdpater listAdpater43 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १५)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०"}, iArr15);
                ListView listView43 = (ListView) findViewById(R.id.listView);
                listView43.setAdapter(listAdpater43);
                listView43.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay15.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i31 == 0) {
                ListAdpater listAdpater44 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૫)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦"}, iArr15);
                ListView listView44 = (ListView) findViewById(R.id.listView);
                listView44.setAdapter(listAdpater44);
                listView44.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay15.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i31) {
                ListAdpater listAdpater45 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 15)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20"}, iArr15);
                ListView listView45 = (ListView) findViewById(R.id.listView);
                listView45.setAdapter(listAdpater45);
                listView45.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay15.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (16 == i) {
            setTitle("Adhyay 16");
            int[] iArr16 = new int[24];
            SharedPreferences sharedPreferences16 = getSharedPreferences("Favorite", 0);
            for (int i32 = 1; i32 < 24; i32++) {
                this.favflagsave = sharedPreferences16.getString("A16" + i32, "0");
                int parseInt16 = Integer.parseInt(this.favflagsave);
                if (parseInt16 == 0) {
                    iArr16[i32] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt16) {
                    iArr16[i32] = R.drawable.favoritestars;
                }
            }
            int i33 = this.langu;
            if (1 == i33) {
                ListAdpater listAdpater46 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १६)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५-१६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४"}, iArr16);
                ListView listView46 = (ListView) findViewById(R.id.listView);
                listView46.setAdapter(listAdpater46);
                listView46.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay16.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i33 == 0) {
                ListAdpater listAdpater47 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૬)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫-૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪"}, iArr16);
                ListView listView47 = (ListView) findViewById(R.id.listView);
                listView47.setAdapter(listAdpater47);
                listView47.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay16.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i33) {
                ListAdpater listAdpater48 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 16)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15-16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24"}, iArr16);
                ListView listView48 = (ListView) findViewById(R.id.listView);
                listView48.setAdapter(listAdpater48);
                listView48.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay16.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (17 == i) {
            setTitle("Adhyay 17");
            int[] iArr17 = new int[29];
            SharedPreferences sharedPreferences17 = getSharedPreferences("Favorite", 0);
            for (int i34 = 1; i34 < 29; i34++) {
                this.favflagsave = sharedPreferences17.getString("A17" + i34, "0");
                int parseInt17 = Integer.parseInt(this.favflagsave);
                if (parseInt17 == 0) {
                    iArr17[i34] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt17) {
                    iArr17[i34] = R.drawable.favoritestars;
                }
            }
            int i35 = this.langu;
            if (1 == i35) {
                ListAdpater listAdpater49 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १७)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८"}, iArr17);
                ListView listView49 = (ListView) findViewById(R.id.listView);
                listView49.setAdapter(listAdpater49);
                listView49.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay17.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i35 == 0) {
                ListAdpater listAdpater50 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૭)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮"}, iArr17);
                ListView listView50 = (ListView) findViewById(R.id.listView);
                listView50.setAdapter(listAdpater50);
                listView50.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay17.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i35) {
                ListAdpater listAdpater51 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 17)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28"}, iArr17);
                ListView listView51 = (ListView) findViewById(R.id.listView);
                listView51.setAdapter(listAdpater51);
                listView51.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay17.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        } else if (18 == i) {
            setTitle("Adhyay 18");
            int[] iArr18 = new int[78];
            SharedPreferences sharedPreferences18 = getSharedPreferences("Favorite", 0);
            for (int i36 = 1; i36 < 78; i36++) {
                this.favflagsave = sharedPreferences18.getString("A18" + i36, "0");
                int parseInt18 = Integer.parseInt(this.favflagsave);
                if (parseInt18 == 0) {
                    iArr18[i36] = R.drawable.fvoritestaroutlinetransparent;
                } else if (1 == parseInt18) {
                    iArr18[i36] = R.drawable.favoritestars;
                }
            }
            int i37 = this.langu;
            if (1 == i37) {
                ListAdpater listAdpater52 = new ListAdpater(getApplicationContext(), new String[]{"परिचय (अध्याय १८)", "श्लोक १", "श्लोक २", "श्लोक ३", "श्लोक ४", "श्लोक ५", "श्लोक ६", "श्लोक ७", "श्लोक ८", "श्लोक ९", "श्लोक १०", "श्लोक ११", "श्लोक १२", "श्लोक १३", "श्लोक १४", "श्लोक १५", "श्लोक १६", "श्लोक १७", "श्लोक १८", "श्लोक १९", "श्लोक २०", "श्लोक २१", "श्लोक २२", "श्लोक २३", "श्लोक २४", "श्लोक २५", "श्लोक २६", "श्लोक २७", "श्लोक २८", "श्लोक २९", "श्लोक ३०", "श्लोक ३१", "श्लोक ३२", "श्लोक ३३", "श्लोक ३४", "श्लोक ३५", "श्लोक ३६-३७", "श्लोक ३८", "श्लोक ३९", "श्लोक ४०", "श्लोक ४१", "श्लोक ४२", "श्लोक ४३", "श्लोक ४४", "श्लोक ४५", "श्लोक ४६", "श्लोक ४७", "श्लोक ४८", "श्लोक ४९", "श्लोक ५०", "श्लोक ५१-५२-५३", "श्लोक ५४", "श्लोक ५५", "श्लोक ५६", "श्लोक ५७", "श्लोक ५८", "श्लोक ५९", "श्लोक ६०", "श्लोक ६१", "श्लोक ६२", "श्लोक ६३", "श्लोक ६४", "श्लोक ६५", "श्लोक ६६", "श्लोक ६७", "श्लोक ६८", "श्लोक ६९", "श्लोक ७०", "श्लोक ७१", "श्लोक ७२", "श्लोक ७३", "श्लोक ७४", "श्लोक ७५", "श्लोक ७६", "श्लोक ७७", "श्लोक ७८"}, iArr18);
                ListView listView52 = (ListView) findViewById(R.id.listView);
                listView52.setAdapter(listAdpater52);
                listView52.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay18.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (i37 == 0) {
                ListAdpater listAdpater53 = new ListAdpater(getApplicationContext(), new String[]{"પરિચય(અધ્યાય ૧૮)", "શ્લોક ૧", "શ્લોક ૨", "શ્લોક ૩", "શ્લોક ૪", "શ્લોક ૫", "શ્લોક ૬", "શ્લોક ૭", "શ્લોક ૮", "શ્લોક ૯", "શ્લોક ૧૦", "શ્લોક ૧૧", "શ્લોક ૧૨", "શ્લોક ૧૩", "શ્લોક ૧૪", "શ્લોક ૧૫", "શ્લોક ૧૬", "શ્લોક ૧૭", "શ્લોક ૧૮", "શ્લોક ૧૯", "શ્લોક ૨૦", "શ્લોક ૨૧", "શ્લોક ૨૨", "શ્લોક ૨૩", "શ્લોક ૨૪", "શ્લોક ૨૫", "શ્લોક ૨૬", "શ્લોક ૨૭", "શ્લોક ૨૮", "શ્લોક ૨૯", "શ્લોક ૩૦", "શ્લોક ૩૧", "શ્લોક ૩૨", "શ્લોક ૩૩", "શ્લોક ૩૪", "શ્લોક ૩૫", "શ્લોક ૩૬-૩૭", "શ્લોક ૩૮", "શ્લોક ૩૯", "શ્લોક ૪૦", "શ્લોક ૪૧", "શ્લોક ૪૨", "શ્લોક ૪૩", "શ્લોક ૪૪", "શ્લોક ૪૫", "શ્લોક ૪૬", "શ્લોક ૪૭", "શ્લોક ૪૮", "શ્લોક ૪૯", "શ્લોક ૫૦", "શ્લોક ૫૧-૫૨-૫૩", "શ્લોક ૫૪", "શ્લોક ૫૫", "શ્લોક ૫૬", "શ્લોક ૫૭", "શ્લોક ૫૮", "શ્લોક ૫૯", "શ્લોક ૬૦", "શ્લોક ૬૧", "શ્લોક ૬૨", "શ્લોક ૬૩", "શ્લોક ૬૪", "શ્લોક ૬૫", "શ્લોક ૬૬", "શ્લોક ૬૭", "શ્લોક ૬૮", "શ્લોક ૬૯", "શ્લોક ૭૦", "શ્લોક ૭૧", "શ્લોક ૭૨", "શ્લોક ૭૩", "શ્લોક ૭૪", "શ્લોક ૭૫", "શ્લોક ૭૬", "શ્લોક ૭૭", "શ્લોક ૭૮"}, iArr18);
                ListView listView53 = (ListView) findViewById(R.id.listView);
                listView53.setAdapter(listAdpater53);
                listView53.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay18.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            } else if (2 == i37) {
                ListAdpater listAdpater54 = new ListAdpater(getApplicationContext(), new String[]{"Introduction(Adhyay 18)", "Shlok 1", "Shlok 2", "Shlok 3", "Shlok 4", "Shlok 5", "Shlok 6", "Shlok 7", "Shlok 8", "Shlok 9", "Shlok 10", "Shlok 11", "Shlok 12", "Shlok 13", "Shlok 14", "Shlok 15", "Shlok 16", "Shlok 17", "Shlok 18", "Shlok 19", "Shlok 20", "Shlok 21", "Shlok 22", "Shlok 23", "Shlok 24", "Shlok 25", "Shlok 26", "Shlok 27", "Shlok 28", "Shlok 29", "Shlok 30", "Shlok 31", "Shlok 32", "Shlok 33", "Shlok 34", "Shlok 35", "Shlok 36-37", "Shlok 38", "Shlok 39", "Shlok 40", "Shlok 41", "Shlok 42", "Shlok 43", "Shlok 44", "Shlok 45", "Shlok 46", "Shlok 47", "Shlok 48", "Shlok 49", "Shlok 50", "Shlok 51-52-53", "Shlok 54", "Shlok 55", "Shlok 56", "Shlok 57", "Shlok 58", "Shlok 59", "Shlok 60", "Shlok 61", "Shlok 62", "Shlok 63", "Shlok 64", "Shlok 65", "Shlok 66", "Shlok 67", "Shlok 68", "Shlok 69", "Shlok 70", "Shlok 71", "Shlok 72", "Shlok 73", "Shlok 74", "Shlok 75", "Shlok 76", "Shlok 77", "Shlok 78"}, iArr18);
                ListView listView54 = (ListView) findViewById(R.id.listView);
                listView54.setAdapter(listAdpater54);
                listView54.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(ShlokList.this.getApplicationContext(), ShlokListPlay18.class);
                        intent.putExtra("pos", i);
                        intent.putExtra("callf", ShlokList.this.langu);
                        ShlokList.this.startActivity(intent);
                    }
                });
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }

    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    class ListAdpater extends BaseAdapter {
        LayoutInflater inflater;
        private Context mContext;
        public String[] mImage;
        public int[] mImage1;
        Typeface myfonts = Typeface.createFromAsset(ShlokList.this.getApplicationContext().getAssets(), "NotoSansGujarati-Bold.ttf");

        public long getItemId(int i) {
            return 0;
        }

        public ListAdpater(Context context, String[] strArr, int[] iArr) {
            this.mContext = context;
            this.inflater = LayoutInflater.from(context);
            this.mImage = strArr;
            this.mImage1 = iArr;
        }

        public int getCount() {
            return this.mImage.length;
        }

        public Object getItem(int i) {
            return this.mImage[i];
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.inflater.inflate(R.layout.shlok_single_row, (ViewGroup) null);
                if (1 == ShlokList.this.langu) {
                    viewHolder.tv11 = (TextView) view2.findViewById(R.id.textView);
                    viewHolder.imgv11 = (ImageView) view2.findViewById(R.id.imageView5);
                    viewHolder.imgfav = (ImageView) view2.findViewById(R.id.imageView8);
                    view2.setTag(viewHolder);
                } else if (ShlokList.this.langu == 0) {
                    viewHolder.tv11 = (TextView) view2.findViewById(R.id.textView);
                    viewHolder.tv11.setTypeface(this.myfonts);
                    viewHolder.imgv11 = (ImageView) view2.findViewById(R.id.imageView5);
                    viewHolder.imgfav = (ImageView) view2.findViewById(R.id.imageView8);
                    view2.setTag(viewHolder);
                } else if (2 == ShlokList.this.langu) {
                    viewHolder.tv11 = (TextView) view2.findViewById(R.id.textView);
                    viewHolder.imgv11 = (ImageView) view2.findViewById(R.id.imageView5);
                    viewHolder.imgfav = (ImageView) view2.findViewById(R.id.imageView8);
                    view2.setTag(viewHolder);
                }
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tv11.setText(this.mImage[i].toString());
            viewHolder.imgfav.setBackgroundResource(this.mImage1[i]);
            return view2;
        }
    }

    private class ViewHolder {
        ImageView imgfav;
        ImageView imgv11;
        TextView tv11;

        private ViewHolder() {
        }
    }
}

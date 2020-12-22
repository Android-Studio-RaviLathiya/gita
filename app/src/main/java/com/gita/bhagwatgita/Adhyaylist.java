package com.gita.bhagwatgita;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Adhyaylist extends AppCompatActivity {
    AdView adView;
    String[] arr1;
    String[] arr2;
    int langua;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.adhyay_list);
        this.adView = (AdView) findViewById(R.id.adView2);
        this.adView.loadAd(new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build());
        this.langua = getIntent().getExtras().getInt("lang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
//        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
//            this.adView.setVisibility(8);
//        }


        int i = this.langua;
        if (1 == i) {
            this.arr1 = new String[]{"अध्याय -०१", "अध्याय -०२", "अध्याय -०३", "अध्याय -०४", "अध्याय -०५", "अध्याय -०६", "अध्याय -०७", "अध्याय -०८", "अध्याय -०९", "अध्याय -१०", "अध्याय -११", "अध्याय -१२", "अध्याय -१३", "अध्याय -१४", "अध्याय -१५", "अध्याय -१६", "अध्याय-१७", "अध्याय -१८"};
            this.arr2 = new String[]{"(अर्जुनविषादयोग)", "(सांख्ययोग)", "(कर्मयोग)", "(ज्ञानकर्मसंन्यासयोग)", "(कर्मसंन्यासयोग)", "(आत्मसंयमयोग)", "(ज्ञानविज्ञानयोग)", "(अक्षरब्रह्मयोग)", "(राजविद्याराजगुह्ययोग)", "(विभूतियोग)", "(विश्वरूपदर्शनयोग)", "(भक्तियोग)", "(क्षेत्र-क्षेत्रज्ञविभागयोग)", "(गुणत्रयविभागयोग)", "(पुरुषोत्तमयोग)", "(दैवासुरसम्पद्विभागयोग)", "(श्रद्धात्रयविभागयोग)", "(मोक्षसंन्यासयोग)"};
        } else if (i == 0) {
            this.arr1 = new String[]{"અધ્યાય-૦૧", "અધ્યાય-૦૨", "અધ્યાય-૦૩", "અધ્યાય-૦૪", "અધ્યાય-૦૫", "અધ્યાય-૦૬", "અધ્યાય-૦૭", "અધ્યાય-૦૮", "અધ્યાય-૦૯", "અધ્યાય-૧૦", "અધ્યાય-૧૧", "અધ્યાય-૧૨", "અધ્યાય-૧૩", "અધ્યાય-૧૪", "અધ્યાય-૧૫", "અધ્યાય-૧૬", "અધ્યાય-૧૭", "અધ્યાય-૧૮"};
            this.arr2 = new String[]{"(અર્જુન-વિષાદ-યોગ)", "(સાંખ્ય-યોગ)", "(કર્મયોગ)", "(જ્ઞાન-કર્મ-સન્યાસ-યોગ)", "(કર્મ-સન્યાસ-યોગ)", "(આત્મ-સન્યાસ-યોગ)", "(જ્ઞાન-વિજ્ઞાન-યોગ)", "(અક્ષર-બ્રહ્મ-યોગ)", "(રાજવિદ્યા-રાજ-ગુહ્ય-યોગ)", "(વિભૂતિ-યોગ)", "(વિશ્વરૂપ-દર્શન-યોગ)", "(ભક્તિ-યોગ)", "(ક્ષેત્ર-ક્ષેત્રજ્ઞ-વિભાગ-યોગ)", "(ગુણત્ર-વિભાગ-યોગ)", "(પુરુષોત્તમ-યોગ)", "(દૈવાસુર- સંપદ્વિભાગ- યોગ)", "(શ્રદ્ધાત્રય- વિભાગ- યોગ)", "(મોક્ષ-સંન્યાસ-યોગ)"};
        } else if (2 == i) {
            this.arr1 = new String[]{"Adhyay-01", "Adhyay-02", "Adhyay-03", "Adhyay-04", "Adhyay-05", "Adhyay-06", "Adhyay-07", "Adhyay-08", "Adhyay-09", "Adhyay-10", "Adhyay-11", "Adhyay-12", "Adhyay-13", "Adhyay-14", "Adhyay-15", "Adhyay-16", "Adhyay-17", "Adhyay-18"};
            this.arr2 = new String[]{"(Arjun-vishad-yog)", "(sankhya-yog)", "(karma-yog)", "(gnan-karma-sanyas-yog)", "(karma-sanyas-yog)", "(aatm-sanyas-yog)", "(gnan-vignan-yog)", "(akshar-brahma-yog)", "(rajavidhya-raj-gruhya-yog)", "(vibhuti-yog)", "(vishvarup-darshan-yog)", "(bhakti-yog)", "(kshetra-kshetragn-vibhag-yog)", "(gunatra-vibhaga-yog)", "(purushottam-yoga)", "(daivasur-sampadvibhag-yog)", "(shraddhatray-vibhag-yog)", "(moksh-sannyas-yoga)"};
        }
        ListAdpater listAdpater = new ListAdpater(getApplicationContext(), this.arr1, this.arr2);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(listAdpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Adhyaylist.this.overridePendingTransition(0, 0);
                Intent intent = new Intent(Adhyaylist.this.getApplicationContext(), ShlokList.class);
                intent.putExtra("AdhyayPos", i + 1);
                intent.addFlags(65536);
                intent.putExtra("Language", Adhyaylist.this.langua);
                Adhyaylist.this.overridePendingTransition(0, 0);
                Adhyaylist.this.startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }

    class ListAdpater extends BaseAdapter {
        LayoutInflater inflater;
        private Context mContext;
        public String[] mImage1;
        public String[] mImage2;
        Typeface myfont1 = Typeface.createFromAsset(Adhyaylist.this.getApplicationContext().getAssets(), "NotoSansGujarati-Bold.ttf");
        Typeface myfonts = Typeface.createFromAsset(Adhyaylist.this.getApplicationContext().getAssets(), "Lohit-Gujarati.ttf");

        public long getItemId(int i) {
            return 0;
        }

        public ListAdpater(Context context, String[] strArr, String[] strArr2) {
            this.mContext = context;
            this.inflater = LayoutInflater.from(context);
            this.mImage1 = strArr;
            this.mImage2 = strArr2;
        }

        public int getCount() {
            return this.mImage1.length;
        }

        public Object getItem(int i) {
            return this.mImage1[i];
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.inflater.inflate(R.layout.adhyay_single_row, (ViewGroup) null);
                if (1 == Adhyaylist.this.langua) {
                    viewHolder.tv11 = (TextView) view2.findViewById(R.id.textView);
                    viewHolder.tv22 = (TextView) view2.findViewById(R.id.textView4);
                    viewHolder.imgv11 = (ImageView) view2.findViewById(R.id.imageView5);
                    view2.setTag(viewHolder);
                } else if (Adhyaylist.this.langua == 0) {
                    viewHolder.tv11 = (TextView) view2.findViewById(R.id.textView);
                    viewHolder.tv11.setTypeface(this.myfont1);
                    viewHolder.tv22 = (TextView) view2.findViewById(R.id.textView4);
                    viewHolder.tv22.setTypeface(this.myfonts);
                    viewHolder.imgv11 = (ImageView) view2.findViewById(R.id.imageView5);
                    view2.setTag(viewHolder);
                } else if (2 == Adhyaylist.this.langua) {
                    viewHolder.tv11 = (TextView) view2.findViewById(R.id.textView);
                    viewHolder.tv22 = (TextView) view2.findViewById(R.id.textView4);
                    viewHolder.imgv11 = (ImageView) view2.findViewById(R.id.imageView5);
                    view2.setTag(viewHolder);
                }
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tv11.setText(this.mImage1[i].toString());
            viewHolder.tv22.setText(this.mImage2[i].toString());
            return view2;
        }
    }

    private class ViewHolder {
        ImageView imgv11;
        TextView tv11;
        TextView tv22;

        private ViewHolder() {
        }
    }
}

package com.gita.bhagwatgita;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ShlokListPlay13 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static final String DEFAULT = "0";
    AdView adView;
    Button button1;
    Button button2;
    Button button4;
    Button buttonNext;
    Button buttonPrev;
    int callf1;
    int callf2;
    int calll;
    int calllang;
    int favflag;
    String favflagsave;
    ImageButton imgfav;
    Typeface myfonts;
    Typeface myfonts1;
    LinearLayout rellay1;
    TextView tv1;
    TextView tv2;

    Button btn_slock, btn_slocktras, btn_slock_silent, btn_slocktras_silent;
    TextToSpeech tts;


    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.shlok_list_play);

        btn_slock = findViewById(R.id.btn_slock);
        btn_slocktras = findViewById(R.id.btn_slocktras);
        btn_slock_silent = findViewById(R.id.btn_slock_silent);
        btn_slocktras_silent = findViewById(R.id.btn_slocktras_silent);


        this.adView = (AdView) findViewById(R.id.adView);
        this.adView.loadAd(new AdRequest.Builder().build());
        Intent intent = getIntent();
        this.calll = intent.getExtras().getInt("pos");
        this.calllang = intent.getExtras().getInt("callf");
        int i = this.calllang;
        this.callf1 = i;
        this.callf2 = i;
        this.tv1 = (TextView) findViewById(R.id.tv1);
        this.tv2 = (TextView) findViewById(R.id.tv2);
        this.buttonPrev = (Button) findViewById(R.id.btnprevious);
        this.buttonNext = (Button) findViewById(R.id.btnnext);
        this.button1 = (Button) findViewById(R.id.button2);
        this.button2 = (Button) findViewById(R.id.button3);
        this.button4 = (Button) findViewById(R.id.button4);
        this.imgfav = (ImageButton) findViewById(R.id.imageButton);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView2);
        this.rellay1 = (LinearLayout) findViewById(R.id.rellay1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.myfonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "Sanskrit-new.ttf");
        this.myfonts1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lohit-Gujarati.ttf");
        this.tv2.setTypeface(this.myfonts1);
        slkset();
        this.imgfav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ShlokListPlay13.this.favflag == 0) {
                    ShlokListPlay13.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.favflag = 1;
                    shlokListPlay13.save();
                } else if (1 == ShlokListPlay13.this.favflag) {
                    ShlokListPlay13.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay13 shlokListPlay132 = ShlokListPlay13.this;
                    shlokListPlay132.favflag = 0;
                    shlokListPlay132.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay13.this.calll++;
                ShlokListPlay13.this.slkset();
                scrollView.pageScroll(33);

                tts.stop();
                btn_slock_silent.setVisibility(View.VISIBLE);
                btn_slock.setVisibility(View.GONE);
                btn_slocktras.setVisibility(View.GONE);
                btn_slocktras_silent.setVisibility(View.VISIBLE);


            }
        });
        this.buttonPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                shlokListPlay13.calll--;
                ShlokListPlay13.this.slkset();
                scrollView.pageScroll(33);

                tts.stop();
                btn_slock_silent.setVisibility(View.VISIBLE);
                btn_slock.setVisibility(View.GONE);
                btn_slocktras.setVisibility(View.GONE);
                btn_slocktras_silent.setVisibility(View.VISIBLE);


            }
        });
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            this.adView.setVisibility(8);
        }


        btn_slock = findViewById(R.id.btn_slock);
        btn_slocktras = findViewById(R.id.btn_slocktras);
        /*voice*/

        btn_slock_silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = tv1.getText().toString();
                tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);

                btn_slock.setVisibility(View.VISIBLE);
                btn_slock_silent.setVisibility(View.GONE);


                btn_slocktras.setVisibility(View.GONE);
                btn_slocktras_silent.setVisibility(View.VISIBLE);

            }
        });

        btn_slock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_slock.setVisibility(View.GONE);
                btn_slock_silent.setVisibility(View.VISIBLE);
                tts.stop();

            }
        });


        btn_slocktras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn_slocktras_silent.setVisibility(View.VISIBLE);
                btn_slocktras.setVisibility(View.GONE);
                tts.stop();
            }
        });

        btn_slocktras_silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = tv2.getText().toString();
                tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);

                btn_slocktras.setVisibility(View.VISIBLE);
                btn_slocktras_silent.setVisibility(View.GONE);

                btn_slock.setVisibility(View.GONE);
                btn_slock_silent.setVisibility(View.VISIBLE);

            }
        });
        Intent s = new Intent();
        s.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(s, 1);

    }

    public void save() {
        SharedPreferences.Editor edit = getSharedPreferences("Favorite", 0).edit();
        int i = this.calll;
        String valueOf = String.valueOf(this.favflag);
        edit.putString("A13" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A13" + this.calll, "0");
        this.favflag = Integer.parseInt(this.favflagsave);
        int i = this.favflag;
        if (i == 0) {
            this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
        } else if (1 == i) {
            this.imgfav.setBackgroundResource(R.drawable.favoritestars);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }

    public void slkset() {
        if (this.calll == 0) {
            this.rellay1.setVisibility(4);
            this.buttonPrev.setVisibility(4);
            this.tv2.setVisibility(4);
            this.imgfav.setVisibility(4);
        } else {
            this.rellay1.setVisibility(0);
            this.buttonPrev.setVisibility(0);
            this.tv2.setVisibility(0);
            this.imgfav.setVisibility(0);
        }
        if (35 == this.calll) {
            this.buttonNext.setVisibility(4);
        } else {
            this.buttonNext.setVisibility(0);
        }
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setLineSpacing(7.0f, 1.0f);
            this.tv1.setTextSize(18.0f);
            this.tv1.setPadding(15, 14, 15, 14);
            if (this.calll == 0) {
                this.button1.setText("हिंदी");
            } else {
                this.button1.setText("हिंदी / संस्कृत");
            }
            TextView textView = this.tv1;
            textView.setTypeface(textView.getTypeface(), 1);
            this.button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.callf1 = 0;
                    shlokListPlay13.slkset();
                }
            });
        } else if (i == 0) {
            this.tv1.setTypeface(this.myfonts1);
            this.tv1.setLineSpacing(10.0f, 1.0f);
            this.tv1.setTextSize(17.0f);
            this.tv1.setPadding(15, 14, 15, 14);
            this.button1.setTypeface(this.myfonts1);
            this.button1.setText("ગુજરાતી");
            TextView textView2 = this.tv1;
            textView2.setTypeface(textView2.getTypeface(), 1);
            this.button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.callf1 = 2;
                    shlokListPlay13.slkset();
                }
            });
        } else if (2 == i) {
            this.tv1.setLineSpacing(1.0f, 1.0f);
            this.tv1.setTextSize(17.0f);
            this.tv1.setPadding(15, 15, 15, 15);
            this.button1.setText("English");
            TextView textView3 = this.tv1;
            textView3.setTypeface(textView3.getTypeface(), 0);
            this.button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.callf1 = 1;
                    shlokListPlay13.slkset();
                }
            });
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setTextSize(17.0f);
            this.tv2.setLineSpacing(7.0f, 1.0f);
            this.tv2.setPadding(2, 12, 2, 12);
            this.button2.setText("हिंदी");
            TextView textView4 = this.tv2;
            textView4.setTypeface(textView4.getTypeface(), 0);
            this.button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.callf2 = 0;
                    shlokListPlay13.slkset();
                }
            });
        } else if (i2 == 0) {
            this.tv2.setTypeface(this.myfonts1);
            this.tv2.setTextSize(16.0f);
            this.tv2.setLineSpacing(7.0f, 1.0f);
            this.tv2.setPadding(2, 15, 2, 15);
            this.button2.setTypeface(this.myfonts1);
            this.button2.setText("ગુજરાતી");
            TextView textView5 = this.tv2;
            textView5.setTypeface(textView5.getTypeface(), 0);
            this.button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.callf2 = 2;
                    shlokListPlay13.slkset();
                }
            });
        } else if (2 == i2) {
            this.tv2.setLineSpacing(1.0f, 1.0f);
            this.tv2.setPadding(1, 20, 1, 20);
            this.tv2.setTextSize(16.0f);
            TextView textView6 = this.tv2;
            textView6.setTypeface(textView6.getTypeface(), 0);
            this.button2.setText("English");
            this.button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay13 shlokListPlay13 = ShlokListPlay13.this;
                    shlokListPlay13.callf2 = 1;
                    shlokListPlay13.slkset();
                }
            });
        }
        int i3 = this.calll;
        if (i3 == 0) {
            shlok0();
        } else if (1 == i3) {
            shlok1();
        } else if (2 == i3) {
            shlok2();
        } else if (3 == i3) {
            shlok3();
        } else if (4 == i3) {
            shlok4();
        } else if (5 == i3) {
            shlok5();
        } else if (6 == i3) {
            shlok6();
        } else if (7 == i3) {
            shlok7();
        } else if (8 == i3) {
            shlok8();
        } else if (9 == i3) {
            shlok9();
        } else if (10 == i3) {
            shlok10();
        } else if (11 == i3) {
            shlok11();
        } else if (12 == i3) {
            shlok12();
        } else if (13 == i3) {
            shlok13();
        } else if (14 == i3) {
            shlok14();
        } else if (15 == i3) {
            shlok15();
        } else if (16 == i3) {
            shlok16();
        } else if (17 == i3) {
            shlok17();
        } else if (18 == i3) {
            shlok18();
        } else if (19 == i3) {
            shlok19();
        } else if (20 == i3) {
            shlok20();
        } else if (21 == i3) {
            shlok21();
        } else if (22 == i3) {
            shlok22();
        } else if (23 == i3) {
            shlok23();
        } else if (24 == i3) {
            shlok24();
        } else if (25 == i3) {
            shlok25();
        } else if (26 == i3) {
            shlok26();
        } else if (27 == i3) {
            shlok27();
        } else if (28 == i3) {
            shlok28();
        } else if (29 == i3) {
            shlok29();
        } else if (30 == i3) {
            shlok30();
        } else if (31 == i3) {
            shlok31();
        } else if (32 == i3) {
            shlok32();
        } else if (33 == i3) {
            shlok33();
        } else if (34 == i3) {
            shlok34();
        } else if (35 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 13");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १३ \nक्षेत्र-क्षेत्रज्ञविभागयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૩ \nક્ષેત્ર ક્ષેત્રજ્ઞ વિભાગ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 13\nkshetra kshetragn vibhag yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा हैं की, जो व्यक्ति शरीर, आत्मा तथा इनसे भी परे परमात्मा के अन्तर को समझ लेता है, उसे इस भौतिक जगत से मोक्ष प्राप्त होता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૩ માં ક્ષેત્ર અને ક્ષેત્રજ્ઞ વિષે સમજાવતા કહે છે કે શરીર ને ક્ષેત્ર કહેવાય છે, અને તેને જે જાણે છે તેને ક્ષેત્રજ્ઞ કહે છે.  'પ્રકૃતિ' અને 'પુરુષ' , બન્ને ને તું અનાદિ અને નિત્ય છે,, શરીરના રાગ-દ્વેષાદિ, સત્વ આદિ વિકારો 'પ્રકૃતિ' થી ઉત્પન્ન થયેલા છે. એવું વર્ણન છે. અંતે કહે છે કે જેમ સૂર્ય સર્વ લોકને પ્રકાશિત કરે છે તેમ એક જ 'ક્ષેત્રજ્ઞ' (આત્મા-પરમાત્મા), સર્વ 'ક્ષેત્ર' ને (શરીરને) પ્રકાશિત કરે છે.");
        } else if (2 == i) {
            this.tv1.setText("Thirteenth adhyay tells, One who understands the difference between the body, the soul and the Supersoul beyond them both attains liberation from this material world.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nइदं शरीरं कौन्तेय क्षेत्रमित्यभिधीयते।\nएतद्यो वेत्ति तं प्राहुः क्षेत्रज्ञ इति तद्विदः॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઇદં શરીરં કૌન્તેય ક્ષેત્રમિત્યભિધીયતે,\nએતદ્યો વેત્તિ તં પ્રાહુઃ ક્ષેત્રજ્ઞ ઇતિ તદ્વિદઃ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nIdam shareeram kaunteya kshetramityabhidheeyate;\nEtadyo vetti tam praahuh kshetrajna iti tadvidah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे अर्जुन! यह शरीर 'क्षेत्र' (जैसे खेत में बोए हुए बीजों का उनके अनुरूप फल समय पर प्रकट होता है, वैसे ही इसमें बोए हुए कर्मों के संस्कार रूप बीजों का फल समय पर प्रकट होता है, इसलिए इसका नाम 'क्षेत्र' ऐसा कहा है) इस नाम से कहा जाता है और इसको जो जानता है, उसको 'क्षेत्रज्ञ' इस नाम से उनके तत्व को जानने वाले ज्ञानीजन कहते हैं॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("ભગવાન કહે: હે કોંતેય ! આ દેહ “ક્ષેત્ર ‘કહેવાય છે અને તેને જાણે છે તે તત્વજ્ઞ મનુષ્ય “ક્ષેત્રજ્ઞ “કહેવાય છે.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Blessed Lord said:This body, O Arjuna, is called the Field; he who knows it is called the Knower of the Field by those who know of them, that is, by the sages.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("क्षेत्रज्ञं चापि मां विद्धि सर्वक्षेत्रेषु भारत।\nक्षेत्रक्षेत्रज्ञयोर्ज्ञानं यत्तज्ज्ञानं मतं मम॥");
        } else if (i == 0) {
            this.tv1.setText("ક્ષેત્રજ્ઞં ચાપિ માં વિદ્ધિ સર્વક્ષેત્રેષુ ભારત,\nક્ષેત્રક્ષેત્રજ્ઞયોર્જ્ઞાનં યત્તજ્જ્ઞાનં મતં મમ.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Kshetrajnam chaapi maam viddhi sarvakshetreshu bhaarata;\nKshetrakshetrajnayor jnaanam yattat jnaanam matam mama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! तू सब क्षेत्रों में क्षेत्रज्ञ अर्थात जीवात्मा भी मुझे ही जान (गीता अध्याय 15 श्लोक 7 और उसकी टिप्पणी देखनी चाहिए) और क्षेत्र-क्षेत्रज्ञ को अर्थात विकार सहित प्रकृति और पुरुष का जो तत्व से जानना है (गीता अध्याय 13 श्लोक 23 और उसकी टिप्पणी देखनी चाहिए) वह ज्ञान है- ऐसा मेरा मत है॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! સર્વ ક્ષેત્રો માં જે ક્ષેત્રજ્ઞ છે તે પણ હું જ છું એમ સમજ. ક્ષેત્ર તથા ક્ષેત્રજ્ઞ નું જે જ્ઞાન છે તે શ્રેષ્ઠ જ્ઞાન છે તેવો મારો મત છે.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("Do thou also know Me as the Knower of the Field in all fields, O Arjuna! Knowledge of both the Field and the Knower of the Field is considered by Me to be the knowledge.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्क्षेत्रं यच्च यादृक्च यद्विकारि यतश्च यत्‌।\nस च यो यत्प्रभावश्च तत्समासेन मे श्रृणु॥");
        } else if (i == 0) {
            this.tv1.setText("તત્ક્ષેત્રં યચ્ચ યાદૃક્ ચ યદ્વિકારિ યતશ્ચ યત્,\nસ ચ યો યત્પ્રભાવશ્ચ તત્સમાસેન મે શ્રૃણુ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Tat kshetram yaccha yaadrik cha yadvikaari yatashcha yat;\nSa cha yo yatprabhaavashcha tatsamaasena me shrinu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह क्षेत्र जो और जैसा है तथा जिन विकारों वाला है और जिस कारण से जो हुआ है तथा वह क्षेत्रज्ञ भी जो और जिस प्रभाववाला है- वह सब संक्षेप में मुझसे सुन॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("ક્ષેત્ર શું અને એનું સ્વરૂપ શું?તેના વિકારો કયા? અને તે ક્યાંથી આવે છે? અને ક્ષેત્રજ્ઞ કોણ છે? તેની શક્તિ ઓ શી? તે ક્ષેત્ર તથા ક્ષેત્રજ્ઞ ના સ્વરૂપ ને મારી પાસેથી ટૂંક માં સાંભળ.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("What the Field is and of what nature, what its modifications are and whence it is, and also who He is and what His powers are—hear all that from Me in brief.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ऋषिभिर्बहुधा गीतं छन्दोभिर्विविधैः पृथक्‌ ।\nब्रह्मसूत्रपदैश्चैव हेतुमद्भिर्विनिश्चितैः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઋષિભિર્બહુધા ગીતં છન્દોભિર્વિવિધૈઃ પૃથક્,\nબ્રહ્મસૂત્રપદૈશ્ચૈવ હેતુમદ્ભિર્વિનિશ્ચિતૈઃ.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Rishibhirbahudhaa geetam cchandobhirvividhaih prithak;\nBrahmasootrapadaishchaiva hetumadbhirvinishchitaih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" यह क्षेत्र और क्षेत्रज्ञ का तत्व ऋषियों द्वारा बहुत प्रकार से कहा गया है और विविध वेदमन्त्रों द्वारा भी विभागपूर्वक कहा गया है तथा भलीभाँति निश्चय किए हुए युक्तियुक्त ब्रह्मसूत्र के पदों द्वारा भी कहा गया है॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ જ્ઞાન ઋષિઓએ વિવિધ રીતે નીરૂપેલું છે ,વિવિધ વેદોએ વિભાગ પૂર્વક કરેલું છે. અને યુક્તિ થી યુક્ત તથા નિશ્વિત અર્થ વાળા બ્રહ્મસુત્ર ના પદો દ્વારા પણ વર્ણવેલું છે.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("Sages have sung in many ways, in various distinctive chants and also in the suggestive words indicative of the Absolute, full of reasoning and decisive.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("महाभूतान्यहङ्‍कारो बुद्धिरव्यक्तमेव च ।\nइन्द्रियाणि दशैकं च पञ्च चेन्द्रियगोचराः ॥");
        } else if (i == 0) {
            this.tv1.setText("મહાભૂતાન્યહઙ્કારો બુદ્ધિરવ્યક્તમેવ ચ,\nઇન્દ્રિયાણિ દશૈકં ચ પઞ્ચ ચેન્દ્રિયગોચરાઃ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Mahaabhootaanyahankaaro buddhiravyaktameva cha;\nIndriyaani dashaikam cha pancha chendriyagocharaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" पाँच महाभूत, अहंकार, बुद्धि और मूल प्रकृति भी तथा दस इन्द्रियाँ, एक मन और पाँच इन्द्रियों के विषय अर्थात शब्द, स्पर्श, रूप, रस और गंध॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("પંચમહાભૂત, અહંકાર, બુદ્ધિ, મહતત્વ, દશ ઇન્દ્રિયો, મન અને ક્ષેત્રાદિક જ્ઞાનેન્દ્રિયો ના શબ્દાદિક પાંચ વિષયો.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("The great elements, egoism, intellect and also unmanifested Nature, the ten senses and one, and the five objects of the senses,(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इच्छा द्वेषः सुखं दुःखं सङ्‍घातश्चेतना धृतिः ।\nएतत्क्षेत्रं समासेन सविकारमुदाहृतम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇચ્છા દ્વેષઃ સુખં દુઃખં સઙ્ઘાતશ્ચેતનાધૃતિઃ,\nએતત્ક્ષેત્રં સમાસેન સવિકારમુદાહૃતમ્.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Icchaa dweshah sukham duhkham sanghaatashchetanaa dhritih;\nEtat kshetram samaasena savikaaramudaahritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तथा इच्छा, द्वेष, सुख, दुःख, स्थूल देहका पिण्ड, चेतना  और धृति - इस प्रकार विकारों  के सहित यह क्षेत्र संक्षेप में कहा गया॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("વાણી આદિ પાંચ કર્મેન્દ્રિયોના પંચ વિષયો, ઈચ્છા, દ્વેષ, સુખ-દુખ, સંઘાત,ચેતના,ધૈર્ય- એ વિકારો થી યુક્ત આ ક્ષેત્ર (દેહ) છે તે મેં ટૂંક માં કહ્યું.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("The great elements, egoism, intellect and also unmanifested Nature, the ten senses and one, and the five objects of the senses,(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अमानित्वमदम्भित्वमहिंसा क्षान्तिरार्जवम्‌ ।\nआचार्योपासनं शौचं स्थैर्यमात्मविनिग्रहः ॥");
        } else if (i == 0) {
            this.tv1.setText("અમાનિત્વમદમ્ભિત્વમહિંસા ક્ષાન્તિરાર્જવમ્,\nઆચાર્યોપાસનં શૌચં સ્થૈર્યમાત્મવિનિગ્રહઃ. (૭) ");
        } else if (2 == i) {
            this.tv1.setText("Amaanitwam adambhitwam ahimsaa kshaantiraarjavam;\nAachaaryopaasanam shaucham sthairyamaatmavinigrahah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्रेष्ठता के अभिमान का अभाव, दम्भाचरण का अभाव, किसी भी प्राणी को किसी प्रकार भी न सताना, क्षमाभाव, मन-वाणी आदि की सरलता, श्रद्धा-भक्ति सहित गुरु की सेवा, बाहर-भीतर की शुद्धि  अन्तःकरण की स्थिरता और मन-इन्द्रियों सहित शरीर का निग्रह॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("અમાનીપણું, અદંભીપણું ,અહિંસા, ક્ષમા,સરળતા, આચાર્યની ઉપાસના,પવિત્રતા,એક નિષ્ઠા, અને આત્મ સંયમ; (૭)");
        } else if (2 == i2) {
            this.tv2.setText("Desire, hatred, pleasure, pain, the aggregate (the body), fortitude and intelligence—the Field has thus been described briefly with its modifications.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इन्द्रियार्थेषु वैराग्यमनहङ्‍कार एव च ।\nजन्ममृत्युजराव्याधिदुःखदोषानुदर्शनम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇન્દ્રિયાર્થેષુ વૈરાગ્યમનહઙ્કાર એવ ચ,\nજન્મમૃત્યુજરાવ્યાધિદુઃખદોષાનુદર્શનમ્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Indriyaartheshu vairaagyamanahankaara eva cha;\nJanmamrityujaraavyaadhi duhkhadoshaanu darshanam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस लोक और परलोक के सम्पूर्ण भोगों में आसक्ति का अभाव और अहंकार का भी अभाव, जन्म, मृत्यु, जरा और रोग आदि में दुःख और दोषों का बार-बार विचार करना॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("ઇન્દ્રિયાદી વિષયો માં વૈરાગ્ય,તેમજ અહંકાર રહિતપણું, જન્મ, મૃત્યુ, જરા, વ્યાધિ તથા દુઃખો પ્રત્યે ના દોષો જોવા; (૮)");
        } else if (2 == i2) {
            this.tv2.setText("Indifference to the objects of the senses, also absence of egoism, perception of (or reflection on) the evil in birth, death, old age, sickness and pain,(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("असक्तिरनभिष्वङ्‍ग: पुत्रदारगृहादिषु ।\nनित्यं च समचित्तत्वमिष्टानिष्टोपपत्तिषु ॥");
        } else if (i == 0) {
            this.tv1.setText("અસક્તિરનભિષ્વઙ્ગઃ પુત્રદારગૃહાદિષુ,\nનિત્યં ચ સમચિત્તત્વમિષ્ટાનિષ્ટોપપત્તિષુ.(૯) ");
        } else if (2 == i) {
            this.tv1.setText("Asaktiranabhishwangah putradaaragrihaadishu;\nNityam cha samachittatwam ishtaanishtopapattishu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पुत्र, स्त्री, घर और धन आदि में आसक्ति का अभाव, ममता का न होना तथा प्रिय और अप्रिय की प्राप्ति में सदा ही चित्त का सम रहना॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("પુત્ર, સ્ત્રી, ઘર વગેરે પદાર્થોમાં પ્રીતિનો અભાવ, અહં-મમતાનો અભાવ અને ઇષ્ટની તથા અનિષ્ટની પ્રાપ્તિ માં સદા સમાનભાવ રાખવો; (૯)");
        } else if (2 == i2) {
            this.tv2.setText("Non-attachment, non-identification of the Self with son, wife, home and the rest, and constant even-mindedness on the attainment of the desirable and the undesirable,(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मयि चानन्ययोगेन भक्तिरव्यभिचारिणी ।\nविविक्तदेशसेवित्वमरतिर्जनसंसदि ॥");
        } else if (i == 0) {
            this.tv1.setText("મયિ ચાનન્યયોગેન ભક્તિરવ્યભિચારિણી,\nવિવિક્તદેશસેવિત્વમરતિર્જનસંસદિ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Mayi chaananyayogena bhaktiravyabhichaarinee;\nViviktadesha sevitwam aratir janasamsadi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मुझ परमेश्वर में अनन्य योग द्वारा अव्यभिचारिणी भक्ति  तथा एकान्त और शुद्ध देश में रहने का स्वभाव और विषयासक्त मनुष्यों के समुदाय में प्रेम का न होना॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારામાં અનન્ય ભાવથી નિર્દોષ ભક્તિ હોવી, એકાંતવાસ પર પ્રેમ અને લોકસમુદાય માં રહેવા પ્રત્યે અપ્રીતિ હોવી. (૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("Unswerving devotion unto Me by the Yoga of non-separation, resort to solitary places, distaste for the society of men,(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्यात्मज्ञाननित्यत्वं तत्वज्ञानार्थदर्शनम्‌ ।\nएतज्ज्ञानमिति प्रोक्तमज्ञानं यदतोऽन्यथा ॥");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાત્મજ્ઞાનનિત્યત્વં તત્ત્વજ્ઞાનાર્થદર્શનમ્,\nએતજ્જ્ઞાનમિતિ પ્રોક્તમજ્ઞાનં યદતોન્યથા.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Adhyaatma jnaana nityatwam tattwa jnaanaartha darshanam;\nEtajjnaanamiti proktam ajnaanam yadato’nyathaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अध्यात्म ज्ञान में नित्य स्थिति और तत्वज्ञान के अर्थरूप परमात्मा को ही देखना- यह सब ज्ञानहै और जो इसके विपरीत है वह अज्ञान है- ऐसा कहा है॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("અધ્યાત્મજ્ઞાન માં નિષ્ઠા રાખવી,તત્વજ્ઞાન નો વિચાર કરવો.આ જ્ઞાન કહેવાય છે. આનાથી વિરુદ્ધ  છે તે અજ્ઞાન કહેવાય છે. (૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("Constancy in Self-knowledge, perception of the end of true knowledge—this is declared to be knowledge, and what is opposed to it is ignorance.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञेयं यत्तत्वप्रवक्ष्यामि यज्ज्ञात्वामृतमश्नुते ।\nअनादिमत्परं ब्रह्म न सत्तन्नासदुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞેયં યત્તત્પ્રવક્ષ્યામિ યજ્જ્ઞાત્વામૃતમશ્નુતે,\nઅનાદિમત્પરં બ્રહ્મ ન સત્તન્નાસદુચ્યતે.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Jneyam yattat pravakshyaami yajjnaatwaa’mritamashnute;\nAnaadimatparam brahma na sattannaasaduchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो जानने योग्य है तथा जिसको जानकर मनुष्य परमानन्द को प्राप्त होता है, उसको भलीभाँति कहूँगा। वह अनादिवाला परमब्रह्म न सत्‌ ही कहा जाता है, न असत्‌ ही॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે જાણવા યોગ્ય છે,જેને જાણવાથી જીવ ને મોક્ષ મળે છે,તે વિષે હવે તને કહું છું, તે અનાદિ સર્વોત્કૃષ્ટ બ્રહ્મને સત્ પણ કહી શકાય તેમ નથી અને અસત્ પણ કહી શકાય તેમ નથી.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("I shall now explain the knowable, knowing which you will taste the eternal. Brahman, the spirit, beginningless and subordinate to Me, lies beyond the cause and effect of this material world.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वतः पाणिपादं तत्सर्वतोऽक्षिशिरोमुखम्‌ ।\nसर्वतः श्रुतिमल्लोके सर्वमावृत्य तिष्ठति ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વતઃ પાણિપાદં તત્સર્વતોક્ષિશિરોમુખમ્,\nસર્વતઃ શ્રુતિમલ્લોકે સર્વમાવૃત્ય તિષ્ઠતિ.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Sarvatah paanipaadam tat sarvato’kshishiromukham;\nSarvatah shrutimalloke sarvamaavritya tishthati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह सब ओर हाथ-पैर वाला, सब ओर नेत्र, सिर और मुख वाला तथा सब ओर कान वाला है, क्योंकि वह संसार में सबको व्याप्त करके स्थित है।॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("તેને સર્વ તરફ હાથ,પગ,નેત્ર,શિર,મુખ અને કાન છે અને એવા સર્વજ્ઞ શક્તિમાન રૂપે આ લોકમાં,ચરાચર જગતમાં,તે સર્વત્ર વ્યાપેલું છે.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("Everywhere are His hands and legs, His eyes, heads and faces, and He has ears everywhere. In this way the Supersoul exists, pervading everything.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वेन्द्रियगुणाभासं सर्वेन्द्रियविवर्जितम्‌ ।\nअसक्तं सर्वभृच्चैव निर्गुणं गुणभोक्तृ च ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વેન્દ્રિયગુણાભાસં સર્વેન્દ્રિયવિવર્જિતમ્,\nઅસક્તં સર્વભૃચ્ચૈવ નિર્ગુણં ગુણભોક્તૃ ચ.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Sarvendriyagunaabhaasam sarvendriyavivarjitam;\nAsaktam sarvabhricchaiva nirgunam gunabhoktru cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" वह सम्पूर्ण इन्द्रियों के विषयों को जानने वाला है, परन्तु वास्तव में सब इन्द्रियों से रहित है तथा आसक्ति रहित होने पर भी सबका धारण-पोषण करने वाला और निर्गुण होने पर भी गुणों को भोगने वाला है॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે સર્વ ઇન્દ્રિયો નું જ્ઞાન કરાવનાર હોવા છતાં સર્વ ઇન્દ્રિયોથી રહિત છે.તે ક્યાંય આસક્તિ રાખતો નથી. છતાં સર્વ ને ધારણ કરે છે.તે ગુણ રહિત હોવા છતાં ગુણ નો ઉપભોગ કરે છે.(૧૪)  ");
        } else if (2 == i2) {
            this.tv2.setText("The Supersoul is the original source of all senses, yet He is without senses. He is unattached, although He is the maintainer of all living beings. He transcends the modes of nature, and at the same time He is the master of all the modes of material nature.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बहिरन्तश्च भूतानामचरं चरमेव च ।\nसूक्ष्मत्वात्तदविज्ञयं दूरस्थं चान्तिके च तत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("બહિરન્તશ્ચ ભૂતાનામચરં ચરમેવ ચ,\nસૂક્ષ્મત્વાત્તદવિજ્ઞેયં દૂરસ્થં ચાન્તિકે ચ તત્.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Bahirantashcha bhootaanaam acharam charameva cha;\nSookshmatwaat tadavijneyam doorastham chaantike cha tat");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह चराचर सब भूतों के बाहर-भीतर परिपूर्ण है और चर-अचर भी वही है। और वह सूक्ष्म होने से अविज्ञेय  है तथा अति समीप में और दूर में भी स्थित वही है॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે જ્ઞેય ભૂતોની બહાર અને અંદર તેમજ સ્થાવર રૂપ તથા જંગમ પ્રાણી સમુદાય રૂપ છે.તે સૂક્ષ્મ હોવાથી જાણી શકાય તેવું નથી તથા દુર રહેલું છે અને અત્યંત સમીપ માં છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Truth exists outside and inside of all living beings, the moving and the nonmoving. Because He is subtle, He is beyond the power of the material senses to see or to know. Although far, far away, He is also near to all.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अविभक्तं च भूतेषु विभक्तमिव च स्थितम्‌ ।\nभूतभर्तृ च तज्ज्ञेयं ग्रसिष्णु प्रभविष्णु च ॥");
        } else if (i == 0) {
            this.tv1.setText("અવિભક્તં ચ ભૂતેષુ વિભક્તમિવ ચ સ્થિતમ્,\nભૂતભર્તૃ ચ તજ્જ્ઞેયં ગ્રસિષ્ણુ પ્રભવિષ્ણુ ચ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Avibhaktam cha bhooteshu vibhaktamiva cha sthitam;\nBhootabhartru cha tajjneyam grasishnu prabhavishnu cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" वह परमात्मा विभागरहित एक रूप से आकाश के सदृश परिपूर्ण होने पर भी चराचर सम्पूर्ण भूतों में विभक्त-सा स्थित प्रतीत होता है  तथा वह जानने योग्य परमात्मा विष्णुरूप से भूतों को धारण-पोषण करने वाला और रुद्ररूप से संहार करने वाला तथा ब्रह्मारूप से सबको उत्पन्न करने वाला है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("અને તે બ્રહ્મ સર્વ ભૂતો માં એક છે, છતાં જાણે ભિન્ન હોય એવી રીતે રહેલું છે.તે સર્વ ભૂતોને ધારણ કરનાર,પ્રલયકાળે સર્વ નો સંહાર કરનાર તથા સર્વ ને ઉત્પન્ન કરવાના સામર્થ્યવાળું જાણવું.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("Although the Supersoul appears to be divided among all beings, He is never divided. He is situated as one. Although He is the maintainer of every living entity, it is to be understood that He devours and develops all.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्योतिषामपि तज्ज्योतिस्तमसः परमुच्यते ।\nज्ञानं ज्ञेयं ज्ञानगम्यं हृदि सर्वस्य विष्ठितम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્યોતિષામપિ તજ્જ્યોતિસ્તમસઃ પરમુચ્યતે,\nજ્ઞાનં જ્ઞેયં જ્ઞાનગમ્યં હૃદિ સર્વસ્ય વિષ્ઠિતમ્.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Jyotishaamapi tajjyotistamasah paramuchyate;\nJnaanam jneyam jnaanagamyam hridi sarvasya vishthitam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" वह परब्रह्म ज्योतियों का भी ज्योति (गीता अध्याय 15 श्लोक 12 में देखना चाहिए) एवं माया से अत्यन्त परे कहा जाता है। वह परमात्मा बोधस्वरूप, जानने के योग्य एवं तत्वज्ञान से प्राप्त करने योग्य है और सबके हृदय में विशेष रूप से स्थित है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે બ્રહ્મ ચંદ્ર-સુર્યાદિક ને પણ પ્રકાશ આપેછે.તે અજ્ઞાનરૂપી અંધકારની પેલી બાજુએ છે એમ જાણવું.તે જ્ઞાન સ્વરૂપ,જ્ઞેય સ્વરૂપ તથા જ્ઞાનથી પ્રાપ્ત કરવા યોગ્ય છે તે જ સર્વ ના હૃદય માં વિધમાન છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("He is the source of light in all luminous objects. He is beyond the darkness of matter and is unmanifested. He is knowledge, He is the object of knowledge, and He is the goal of knowledge. He is situated in everyone’s heart.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इति क्षेत्रं तथा ज्ञानं ज्ञेयं चोक्तं समासतः ।\nमद्भक्त एतद्विज्ञाय मद्भावायोपपद्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇતિ ક્ષેત્રં તથા જ્ઞાનં જ્ઞેયં ચોક્તં સમાસતઃ,\nમદ્ભક્ત એતદ્વિજ્ઞાય મદ્ભાવાયોપપદ્યતે.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Iti kshetram tathaa jnaanam jneyam choktam samaasatah;\nMadbhakta etadvijnaaya madbhaavaayopapadyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस प्रकार क्षेत्र तथा ज्ञान और जानने योग्य परमात्मा का स्वरूपसंक्षेप में कहा गया। मेरा भक्त इसको तत्व से जानकर मेरे स्वरूप को प्राप्त होता है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ પ્રમાણે ક્ષેત્ર, જ્ઞાન અને જ્ઞેય તને ટુંકાણમાં સંભળાવ્યાં.એમને જાણવાથી મારો ભક્ત મારા ભાવને (સ્વ-રૂપને) પ્રાપ્ત કરે છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("Thus the field of activities [the body], knowledge and the knowable have been summarily described by Me. Only My devotees can understand this thoroughly and thus attain to My nature.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रकृतिं पुरुषं चैव विद्ध्‌यनादी उभावपि ।\nविकारांश्च गुणांश्चैव विद्धि प्रकृतिसम्भवान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રકૃતિં પુરુષં ચૈવ વિદ્ધ્યનાદી ઉભાવપિ,\nવિકારાંશ્ચ ગુણાંશ્ચૈવ વિદ્ધિ પ્રકૃતિસંભવાન્.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Prakritim purusham chaiva viddhyaanaadee ubhaavapi;\nVikaaraamshcha gunaamshchaiva viddhi prakritisambhavaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("प्रकृति और पुरुष- इन दोनों को ही तू अनादि जान और राग-द्वेषादि विकारों को तथा त्रिगुणात्मक सम्पूर्ण पदार्थों को भी प्रकृति से ही उत्पन्न जान॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("ક્ષેત્રરૂપ પરાપ્રકૃતિ તથા ક્ષેત્રજ્ઞરૂપ અપરા પ્રકૃતિ બંનેને પણ તું નિત્ય જ જાણ,તથા વિકારો અને ગુણોને પ્રકૃતિથી ઉત્પન્ન થયેલા તું જાણ.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Material nature and the living entities should be understood to be beginningless. Their transformations and the modes of matter are products of material nature.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कार्यकरणकर्तृत्वे हेतुः प्रकृतिरुच्यते ।\nपुरुषः सुखदुःखानां भोक्तृत्वे हेतुरुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("કાર્યકારણકર્તૃત્વે હેતુઃ પ્રકૃતિરુચ્યતે,\nપુરુષઃ સુખદુઃખાનાં ભોક્તૃત્વે હેતુરુચ્યતે.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Kaaryakaaranakartrutwe hetuh prakritiruchyate;\nPurushah sukhaduhkhaanaam bhoktritwe heturuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  कार्य (आकाश, वायु, अग्नि, जल और पृथ्वी तथा शब्द, स्पर्श, रूप, रस, गंध -इनका नाम 'कार्य' है) और करण (बुद्धि, अहंकार और मन तथा श्रोत्र, त्वचा, रसना, नेत्र और घ्राण एवं वाक्‌, हस्त, पाद, उपस्थ और गुदा- इन 13 का नाम 'करण' है) को उत्पन्न करने में हेतु प्रकृति कही जाती है और जीवात्मा सुख-दुःखों के भोक्तपन में अर्थात भोगने में हेतु कहा जाता है॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("કાર્ય કરણ ના કર્તાપણામાં પ્રકૃતિ કારણ કહેવાય છે. સુખ-દુઃખોના ભોક્તાપણમાં ક્ષેત્રજ્ઞ આત્મા કારણ કહેવાય છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Nature is said to be the cause of all material causes and effects, whereas the living entity is the cause of the various sufferings and enjoyments in this world.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पुरुषः प्रकृतिस्थो हि भुङ्‍क्ते प्रकृतिजान्गुणान्‌ ।\nकारणं गुणसंगोऽस्य सदसद्योनिजन्मसु ॥");
        } else if (i == 0) {
            this.tv1.setText("પુરુષઃ પ્રકૃતિસ્થો હિ ભુઙ્ક્તે પ્રકૃતિજાન્ગુણાન્,\nકારણં ગુણસઙ્ગોસ્ય સદસદ્યોનિજન્મસુ.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Purushah prakritistho hi bhungkte prakritijaan gunaan;\nKaaranam gunasango’sya sadasadyoni janmasu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("प्रकृति में स्थित ही पुरुष प्रकृति से उत्पन्न त्रिगुणात्मक पदार्थों को भोगता है और इन गुणों का संग ही इस जीवात्मा के अच्छी-बुरी योनियों में जन्म लेने का कारण है।  ॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("ક્ષેત્રજ્ઞ પ્રકૃતિમાં રહેલો,પ્રકૃતિથી ઉત્પન્ન થયેલા સુખદુઃખાદિક ગુણોને ભોગવે છે.એ પુરુષના સારીનરસી યોનિમાં જન્મનું  કારણ ગુણનો સંગ જ છે.(૨૧) ");
        } else if (2 == i2) {
            this.tv2.setText("The living entity in material nature thus follows the ways of life, enjoying the three modes of nature. This is due to his association with that material nature. Thus he meets with good and evil among various species.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उपद्रष्टानुमन्ता च भर्ता भोक्ता महेश्वरः ।\nपरमात्मेति चाप्युक्तो देहेऽस्मिन्पुरुषः परः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉપદ્રષ્ટાનુમન્તા ચ ભર્તા ભોક્તા મહેશ્વરઃ,\nપરમાત્મેતિ ચાપ્યુક્તો દેહેસ્મિન્પુરુષઃ પરઃ.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Upadrashtaanumantaa cha bhartaa bhoktaa maheshwarah;\nParamaatmeti chaapyukto dehe’smin purushah parah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस देह में स्थित यह आत्मा वास्तव में परमात्मा ही है। वह साक्षी होने से उपद्रष्टा और यथार्थ सम्मति देने वाला होने से अनुमन्ता, सबका धारण-पोषण करने वाला होने से भर्ता, जीवरूप से भोक्ता, ब्रह्मा आदि का भी स्वामी होने से महेश्वर और शुद्ध सच्चिदानन्दघन होने से परमात्मा- ऐसा कहा गया है॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ દેહ માં સર્વ ભિન્ન પુરુષ સાક્ષી અને અનુમતિ આપનારો ભર્તા અને ભોક્તા,મહેશ્વર અને પરમાત્મા એ નામ વડે પણ કહ્યો છે.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("Yet in this body there is another, a transcendental enjoyer, who is the Lord, the supreme proprietor, who exists as the overseer and permitter, and who is known as the Supersoul.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("य एवं वेत्ति पुरुषं प्रकृतिं च गुणैः सह ।\nसर्वथा वर्तमानोऽपि न स भूयोऽभिजायते ॥ ");
        } else if (i == 0) {
            this.tv1.setText("ય એવં વેત્તિ પુરુષં પ્રકૃતિં ચ ગુણૈઃસહ,\nસર્વથા વર્તમાનોપિ ન સ ભૂયોભિજાયતે.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Ya evam vetti purusham prakritim cha gunaih saha;\nSarvathaa vartamaano’pi na sa bhooyo’bhijaayate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस प्रकार पुरुष को और गुणों के सहित प्रकृति को जो मनुष्य तत्व से जानता है वह सब प्रकार से कर्तव्य कर्म करता हुआ भी फिर नहीं जन्मता॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે ઉપરોક્ત પ્રકારે ક્ષેત્રજ્ઞ ને સર્વ વિકારો સહિત પ્રકૃતિને જાણે છે,તે સર્વ પ્રકારે વર્તતો હોવા છતાં ફરીથી જન્મ પામતો નથી. (૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("One who understands this philosophy concerning material nature, the living entity and the interaction of the modes of nature is sure to attain liberation. He will not take birth here again, regardless of his present position.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ध्यानेनात्मनि पश्यन्ति केचिदात्मानमात्मना ।\nअन्ये साङ्‍ख्येन योगेन कर्मयोगेन चापरे ॥");
        } else if (i == 0) {
            this.tv1.setText("ધ્યાનેનાત્મનિ પશ્યન્તિ કેચિદાત્માનમાત્મના,\nઅન્યે સાંખ્યેન યોગેન કર્મયોગેન ચાપરે.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Dhyaanenaatmani pashyanti kechidaatmaanamaatmanaa;\nAnye saankhyena yogena karmayogena chaapare.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" उस परमात्मा को कितने ही मनुष्य तो शुद्ध हुई सूक्ष्म बुद्धि से ध्यान द्वारा हृदय में देखते हैं, अन्य कितने ही ज्ञानयोग द्वारा और दूसरे कितने ही कर्मयोग  द्वारा देखते हैं अर्थात प्राप्त करते हैं॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેટલાક ધ્યાન વડે હૃદયમાં આત્માને શુદ્ધ અંત:કરણ વડે જુવે છે.કેટલાક સાંખ્યયોગ વડે અને બીજાઓ કર્મયોગ વડે પોતામાં આત્મા ને જુવે છે. (૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Some perceive the Supersoul within themselves through meditation, others through the cultivation of knowledge, and still others through working without fruitive desires.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अन्ये त्वेवमजानन्तः श्रुत्वान्येभ्य उपासते ।\nतेऽपि चातितरन्त्येव मृत्युं श्रुतिपरायणाः ॥");
        } else if (i == 0) {
            this.tv1.setText("અન્યે ત્વેવમજાનન્તઃ શ્રુત્વાન્યેભ્ય ઉપાસતે,\nતેપિ ચાતિતરન્ત્યેવ મૃત્યું શ્રુતિપરાયણાઃ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Anye twevamajaanantah shrutwaanyebhya upaasate;\nTe’pi chaatitarantyeva mrityum shrutiparaayanaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु इनसे दूसरे अर्थात जो मंदबुद्धिवाले पुरुष हैं, वे इस प्रकार न जानते हुए दूसरों से अर्थात तत्व के जानने वाले पुरुषों से सुनकर ही तदनुसार उपासना करते हैं और वे श्रवणपरायण पुरुष भी मृत्युरूप संसार-सागर को निःसंदेह तर जाते हैं॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી બીજા એ પ્રમાણે આત્માને નહિ જાણતાં છતાં બીજાઓથી શ્રવણ કરી આત્માને ઉપાસે છે. તેઓ પણ ગુરુ ઉપદેશ શ્રવણમાં તત્પર રહી મૃત્યુ ને તરી જાય છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Again there are those who, although not conversant in spiritual knowledge, begin to worship the Supreme Person upon hearing about Him from others. Because of their tendency to hear from authorities, they also transcend the path of birth and death.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यावत्सञ्जायते किञ्चित्सत्त्वं स्थावरजङ्‍गमम्‌ ।\nक्षेत्रक्षेत्रज्ञसंयोगात्तद्विद्धि भरतर्षभ ॥");
        } else if (i == 0) {
            this.tv1.setText("યાવત્સઞ્જાયતે કિઞ્ચિત્સત્ત્વં સ્થાવરજઙ્ગમમ્,\nક્ષેત્રક્ષેત્રજ્ઞસંયોગાત્તદ્વિદ્ધિ ભરતર્ષભ.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Yaavat sanjaayate kinchit sattwam sthaavarajangamam;\nKshetrakshetrajnasamyogaat tadviddhi bharatarshabha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! यावन्मात्र जितने भी स्थावर-जंगम प्राणी उत्पन्न होते हैं, उन सबको तू क्षेत्र और क्षेत्रज्ञ के संयोग से ही उत्पन्न जान॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("સ્થાવર અને જંગમ,કોઈ પણ પ્રાણી,ક્ષેત્ર ને ક્ષેત્રજ્ઞ ના સંયોગ થી પેદા થાય છે.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("O chief of the Bharatas, know that whatever you see in existence, both the moving and the nonmoving, is only a combination of the field of activities and the knower of the field.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("समं सर्वेषु भूतेषु तिष्ठन्तं परमेश्वरम्‌ ।\nविनश्यत्स्वविनश्यन्तं यः पश्यति स पश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("સમં સર્વેષુ ભૂતેષુ તિષ્ઠન્તં પરમેશ્વરમ્,\nવિનશ્યત્સ્વવિનશ્યન્તં યઃ પશ્યતિ સ પશ્યતિ.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Samam sarveshu bhooteshu tishthantam parameshwaram;\nVinashyatswavinashyantam yah pashyati sa pashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष नष्ट होते हुए सब चराचर भूतों में परमेश्वर को नाशरहित और समभाव से स्थित देखता है वही यथार्थ देखता है॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("વિનાશ પામનારાં સર્વ ભૂતોમાં સમભાવે રહેલા અવિનાશી પરમેશ્વર ને જે જુવે છે તે યથાર્થ જુવેછે. અને તે જ ખરો જ્ઞાની છે. (૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("One who sees the Supersoul accompanying the individual soul in all bodies, and who understands that neither the soul nor the Supersoul within the destructible body is ever destroyed, actually sees.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("समं पश्यन्हि सर्वत्र समवस्थितमीश्वरम्‌ ।\nन हिनस्त्यात्मनात्मानं ततो याति परां गतिम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સમં પશ્યન્હિ સર્વત્ર સમવસ્થિતમીશ્વરમ્,\nન હિનસ્ત્યાત્મનાત્માનં તતો યાતિ પરાં ગતિમ્.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Samam pashyan hi sarvatra samavasthitameeshwaram;\nNa hinastyaatmanaa’tmaanam tato yaati paraam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि जो पुरुष सबमें समभाव से स्थित परमेश्वर को समान देखता हुआ अपने द्वारा अपने को नष्ट नहीं करता, इससे वह परम गति को प्राप्त होता है॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વત્ર સમભાવે રહેલા ઈશ્વરને ખરેખર સમભાવે જોતો પુરુષ આત્મા વડે આત્મા ને હણતો નથી. તેથી પરમગતિ ને પામે છે.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("One who sees the Supersoul equally present everywhere, in every living being, does not degrade himself by his mind. Thus he approaches the transcendental destination.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रकृत्यैव च कर्माणि क्रियमाणानि सर्वशः ।\nयः पश्यति तथात्मानमकर्तारं स पश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રકૃત્યૈવ ચ કર્માણિ ક્રિયમાણાનિ સર્વશઃ,\nયઃ પશ્યતિ તથાત્માનમકર્તારં સ પશ્યતિ.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Prakrityaiva cha karmaani kriyamaanaani sarvashah;\nYah pashyati tathaa’tmaanam akartaaram sa pashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" और जो पुरुष सम्पूर्ण कर्मों को सब प्रकार से प्रकृति द्वारा ही किए जाते हुए देखता है और आत्मा को अकर्ता देखता है, वही यथार्थ देखता है॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("તથા પ્રકૃતિ વડે જ સર્વ પ્રકારે કર્મો કરાય છે,એમ જે જુવે છે,તેમજ આત્માને અકર્તા જુવે છે તે યથાર્થ જુવે છે.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("One who can see that all activities are performed by the body, which is created of material nature, and sees that the self does nothing, actually sees.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा भूतपृथग्भावमेकस्थमनुपश्यति ।\nतत एव च विस्तारं ब्रह्म सम्पद्यते तदा ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા ભૂતપૃથગ્ભાવમેકસ્થમનુપશ્યતિ,\nતત એવ ચ વિસ્તારં બ્રહ્મ સમ્પદ્યતે તદા.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa bhootaprithagbhaavam ekastham anupashyati;\nTata eva cha vistaaram brahma sampadyate tadaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस क्षण यह पुरुष भूतों के पृथक-पृथक भाव को एक परमात्मा में ही स्थित तथा उस परमात्मा से ही सम्पूर्ण भूतों का विस्तार देखता है, उसी क्षण वह सच्चिदानन्दघन ब्रह्म को प्राप्त हो जाता है॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("જયારે મનુષ્ય સર્વ ભૂતોના ભિન્નપણા ને એક આત્મામાં રહેલો જુવે છે તથા આત્માથી તે ભૂતોના વિસ્તારને જુવે છે, ત્યારે બ્રહ્મરૂપને પામે છે.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("When a sensible man ceases to see different identities due to different material bodies and he sees how beings are expanded everywhere, he attains to the Brahman conception.(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        setTitle("Shlok 31");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनादित्वान्निर्गुणत्वात्परमात्मायमव्ययः ।\nशरीरस्थोऽपि कौन्तेय न करोति न लिप्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("અનાદિત્વાન્નિર્ગુણત્વાત્પરમાત્માયમવ્યયઃ,\nશરીરસ્થોપિ કૌન્તેય ન કરોતિ ન લિપ્યતે.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Anaaditwaan nirgunatwaat paramaatmaayam avyayah;\nShareerastho’pi kaunteya na karoti na lipyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! अनादि होने से और निर्गुण होने से यह अविनाशी परमात्मा शरीर में स्थित होने पर भी वास्तव में न तो कुछ करता है और न लिप्त ही होता है॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાંન્તેય ! અનાદિ નિર્ગુણ હોવાથી આ પરમાત્મા અવિકારી છે,તે દેહ માં હોવા છતાં પણ કંઈ કરતા નથી તથા કશાથી લેપાતા નથી.(૩૧)");
        } else if (2 == i2) {
            this.tv2.setText("Those with the vision of eternity can see that the imperishable soul is transcendental, eternal, and beyond the modes of nature. Despite contact with the material body, O Arjuna, the soul neither does anything nor is entangled.(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        setTitle("Shlok 32");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथा सर्वगतं सौक्ष्म्यादाकाशं नोपलिप्यते ।\nसर्वत्रावस्थितो देहे तथात्मा नोपलिप्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યથા સર્વગતં સૌક્ષ્મ્યાદાકાશં નોપલિપ્યતે,\nસર્વત્રાવસ્થિતો દેહે તથાત્મા નોપલિપ્યતે.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Yathaa sarvagatam saukshmyaadaakaasham nopalipyate;\nSarvatraavasthito dehe tathaatmaa nopalipyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जिस प्रकार सर्वत्र व्याप्त आकाश सूक्ष्म होने के कारण लिप्त नहीं होता, वैसे ही देह में सर्वत्र स्थित आत्मा निर्गुण होने के कारण देह के गुणों से लिप्त नहीं होता॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેમ સર્વવ્યાપક આકાશ સૂક્ષ્મપણા ને લીધે લેપાતું નથી. તેવી રીતે સર્વ દેહોમાં રહેલો આત્મા લેપાતો નથી.(૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("The sky, due to its subtle nature, does not mix with anything, although it is all-pervading. Similarly, the soul situated in Brahman vision does not mix with the body, though situated in that body.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथा प्रकाशयत्येकः कृत्स्नं लोकमिमं रविः ।\nक्षेत्रं क्षेत्री तथा कृत्स्नं प्रकाशयति भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("યથા પ્રકાશયત્યેકઃ કૃત્સ્નં લોકમિમં રવિઃ,\nક્ષેત્રં ક્ષેત્રી તથા કૃત્સ્નં પ્રકાશયતિ ભારત.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Yathaa prakaashayatyekah kritsnam lokamimam ravih;\nKshetram kshetree tathaa kritsnam prakaashayati bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! जिस प्रकार एक ही सूर्य इस सम्पूर्ण ब्रह्माण्ड को प्रकाशित करता है, उसी प्रकार एक ही आत्मा सम्पूर्ण क्षेत्र को प्रकाशित करता है॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! જેમ એક સૂર્ય આ સર્વ લોકને પ્રકાશિત કરેછે તેમ ક્ષેત્રજ્ઞ સર્વ ક્ષેત્ર ને પ્રકાશિત કરે છે.(૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Bharata, as the sun alone illuminates all this universe, so does the living entity, one within the body, illuminate the entire body by consciousness.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("क्षेत्रक्षेत्रज्ञयोरेवमन्तरं ज्ञानचक्षुषा ।\nभूतप्रकृतिमोक्षं च ये विदुर्यान्ति ते परम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ષેત્રક્ષેત્રજ્ઞયોરેવમન્તરં જ્ઞાનચક્ષુષા,\nભૂતપ્રકૃતિમોક્ષં ચ યે વિદુર્યાન્તિ તે પરમ્.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Kshetrakshetrajnayor evam antaram jnaanachakshushaa;\nBhootaprakritimoksham cha ye vidur yaanti te param.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस प्रकार क्षेत्र और क्षेत्रज्ञ के भेद को (क्षेत्र को जड़, विकारी, क्षणिक और नाशवान तथा क्षेत्रज्ञ को नित्य, चेतन, अविकारी और अविनाशी जानना ही 'उनके भेद को जानना' है) तथा कार्य सहित प्रकृति से मुक्त होने को जो पुरुष ज्ञान नेत्रों द्वारा तत्व से जानते हैं, वे महात्माजन परम ब्रह्म परमात्मा को प्राप्त होते हैं॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવો ક્ષેત્ર તથા ક્ષેત્રજ્ઞ ના ભેદ ને એ પ્રમાણે જ્ઞાનરૂપી નેત્રો વડે અને ભૂતોના મોક્ષને કારણરૂપ જાણે છે, તેઓ બ્રહ્મને પામે છે.(૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("Those who see with eyes of knowledge the difference between the body and the knower of the body, and can also understand the process of liberation from bondage in material nature, attain to the supreme goal.(34)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay13.this, ShlokListPlay13.this);
            } else {
                Intent i = new Intent();
                i.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(i);
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {
//            Toast.makeText(getApplicationContext(), "engine installed",1000).show();
        }
        if (status == TextToSpeech.ERROR) {
//            Toast.makeText(getApplicationContext(), "engine not installed", 1000).show();
        }
    }

    boolean staus = false;

    @Override
    public void onBackPressed() {
        if (staus) {

        } else {
            super.onBackPressed();
            tts.stop();

            staus = true;
        }

    }


}

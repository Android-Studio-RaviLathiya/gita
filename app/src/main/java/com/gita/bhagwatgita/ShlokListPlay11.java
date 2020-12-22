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

public class ShlokListPlay11 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay11.this.favflag == 0) {
                    ShlokListPlay11.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.favflag = 1;
                    shlokListPlay11.save();
                    scrollView.pageScroll(33);
                } else if (1 == ShlokListPlay11.this.favflag) {
                    ShlokListPlay11.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay11 shlokListPlay112 = ShlokListPlay11.this;
                    shlokListPlay112.favflag = 0;
                    shlokListPlay112.save();
                    scrollView.pageScroll(33);
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay11.this.calll++;
                ShlokListPlay11.this.slkset();
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
                ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                shlokListPlay11.calll--;
                ShlokListPlay11.this.slkset();
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
        edit.putString("A11" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A11" + this.calll, "0");
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
        if (53 == this.calll) {
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
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.callf1 = 0;
                    shlokListPlay11.slkset();
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
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.callf1 = 2;
                    shlokListPlay11.slkset();
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
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.callf1 = 1;
                    shlokListPlay11.slkset();
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
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.callf2 = 0;
                    shlokListPlay11.slkset();
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
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.callf2 = 2;
                    shlokListPlay11.slkset();
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
                    ShlokListPlay11 shlokListPlay11 = ShlokListPlay11.this;
                    shlokListPlay11.callf2 = 1;
                    shlokListPlay11.slkset();
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
            shlok10_11();
        } else if (11 == i3) {
            shlok12();
        } else if (12 == i3) {
            shlok13();
        } else if (13 == i3) {
            shlok14();
        } else if (14 == i3) {
            shlok15();
        } else if (15 == i3) {
            shlok16();
        } else if (16 == i3) {
            shlok17();
        } else if (17 == i3) {
            shlok18();
        } else if (18 == i3) {
            shlok19();
        } else if (19 == i3) {
            shlok20();
        } else if (20 == i3) {
            shlok21();
        } else if (21 == i3) {
            shlok22();
        } else if (22 == i3) {
            shlok23();
        } else if (23 == i3) {
            shlok24();
        } else if (24 == i3) {
            shlok25();
        } else if (25 == i3) {
            shlok26_27();
        } else if (26 == i3) {
            shlok28();
        } else if (27 == i3) {
            shlok29();
        } else if (28 == i3) {
            shlok30();
        } else if (29 == i3) {
            shlok31();
        } else if (30 == i3) {
            shlok32();
        } else if (31 == i3) {
            shlok33();
        } else if (32 == i3) {
            shlok34();
        } else if (33 == i3) {
            shlok35();
        } else if (34 == i3) {
            shlok36();
        } else if (35 == i3) {
            shlok37();
        } else if (36 == i3) {
            shlok38();
        } else if (37 == i3) {
            shlok39();
        } else if (38 == i3) {
            shlok40();
        } else if (39 == i3) {
            shlok41_42();
        } else if (40 == i3) {
            shlok43();
        } else if (41 == i3) {
            shlok44();
        } else if (42 == i3) {
            shlok45();
        } else if (43 == i3) {
            shlok46();
        } else if (44 == i3) {
            shlok47();
        } else if (45 == i3) {
            shlok48();
        } else if (46 == i3) {
            shlok49();
        } else if (47 == i3) {
            shlok50();
        } else if (48 == i3) {
            shlok51();
        } else if (49 == i3) {
            shlok52();
        } else if (50 == i3) {
            shlok53();
        } else if (51 == i3) {
            shlok54();
        } else if (52 == i3) {
            shlok55();
        } else if (53 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 11");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ११ \nविश्वरूपदर्शनयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૧ \nવિશ્વરૂપ દર્શન યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 11\nvishvarup darshan yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा हैं की, भगवान कृष्ण अर्जुन को दिव्य द्रष्टी प्रदान करते हैं और विश्व-रूप में अपना अदभुत असीम रूप प्रकट करते हैं। इस प्रकार वे अपनी दिव्यता स्थापित करते हैं। कृष्ण बतलाते हैं की उनका सर्व आकर्षक मानव-रूप ही ईश्वर हा आदि रूप है। मनुष्य शुद्ध भक्ति के द्वारा ही इस रूप का दर्शन कर सकता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૧ માં કૃષ્ણે અર્જુન ને વિશ્વરૂપ-વિરાટ સ્વરૂપ નું દિવ્ય ચક્ષુ આપી દર્શન કરાવ્યું. કે જે માત્ર અનન્ય ભક્તિ વડે જ જોવાનું શક્ય છે. જે જોઈ અર્જુન હર્ષ અને ભય ને પામે છે. અને તેની વિનંતી થી કૃષ્ણ પાછા મૂળ સ્વરૂપ ને ધારણ કરે છે.");
        } else if (2 == i) {
            this.tv1.setText("Eleventh adhyay tells, Lord Krishna grants Arjuna divine vision and reveals His spectacular unlimited form as the cosmic universe. Thus He conclusively establishes His divinity. Krishna explains that His own all-beautiful humanlike form is the original form of Godhead. One can perceive this form only by pure devotional service.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nमदनुग्रहाय परमं गुह्यमध्यात्मसञ्ज्ञितम्‌ ।\nयत्त्वयोक्तं वचस्तेन मोहोऽयं विगतो मम ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nમદનુગ્રહાય પરમં ગુહ્યમધ્યાત્મસંજ્ઞિતમ્,\nયત્ત્વયોક્તં વચસ્તેન મોહોયં વિગતો મમ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nMadanugrahaaya paramam guhyamadhyaatmasamjnitam;\nYattwayoktam vachastena moho’yam vigato mama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- मुझ पर अनुग्रह करने के लिए आपने जो परम गोपनीय अध्यात्म विषयक वचन अर्थात उपदेश कहा, उससे मेरा यह अज्ञान नष्ट हो गया है॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે : ભગવાન ! મારા પર કૃપા કરવા આપે અધ્યાત્મ તત્વનો અતિ ગુહ્ય તથા ભ્રમનાશક જે ઉપદેશ આપ્યો તેનાથી મારા સર્વ મોહનો લોપ થયો છે.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: By my hearing the instructions You have kindly given me about these most confidential spiritual subjects, my illusion has now been dispelled.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भवाप्ययौ हि भूतानां श्रुतौ विस्तरशो मया ।\nत्वतः कमलपत्राक्ष माहात्म्यमपि चाव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ભવાપ્યયૌ હિ ભૂતાનાં શ્રુતૌ વિસ્તરશો મયા,\nત્વત્તઃ કમલપત્રાક્ષ માહાત્મ્યમપિ ચાવ્યયમ્.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Bhavaapyayau hi bhootaanaam shrutau vistarasho mayaa;\nTwattah kamalapatraaksha maahaatmyamapi chaavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि हे कमलनेत्र! मैंने आपसे भूतों की उत्पत्ति और प्रलय विस्तारपूर्वक सुने हैं तथा आपकी अविनाशी महिमा भी सुनी है॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કમળ નયન ! આપની પાસેથી મેં ભૂતોની ઉત્પત્તિ અને પ્રલય વિસ્તારથી સાંભળ્યા છે તથા આપનો અવિનાશી પ્રભાવ પણ સાંભળ્યો છે.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("O lotus-eyed one, I have heard from You in detail about the appearance and disappearance of every living entity and have realized Your inexhaustible glories.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एवमेतद्यथात्थ त्वमात्मानं परमेश्वर ।\nद्रष्टुमिच्छामि ते रूपमैश्वरं पुरुषोत्तम ॥");
        } else if (i == 0) {
            this.tv1.setText("એવમેતદ્યથાત્થ ત્વમાત્માનં પરમેશ્વર,\nદ્રષ્ટુમિચ્છામિ તે રૂપમૈશ્વરં પુરુષોત્તમ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Evametadyathaattha twamaatmaanam parameshwara;\nDrashtumicchaami te roopamaishwaram purushottama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे परमेश्वर! आप अपने को जैसा कहते हैं, यह ठीक ऐसा ही है, परन्तु हे पुरुषोत्तम! आपके ज्ञान, ऐश्वर्य, शक्ति, बल, वीर्य और तेज से युक्त ऐश्वर्य-रूप को मैं प्रत्यक्ष देखना चाहता हूँ॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરમેશ્વર ! આપના સ્વરૂપનું જેવું આપે વર્ણન કર્યું છે તે યથાર્થ જ છે. પરંતુ હે પુરુષોત્તમ !હું આપનું ઈશ્વરી રૂપ જોવા ઈચ્છું છું.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("O greatest of all personalities, O supreme form, though I see You here before me in Your actual position, as You have described Yourself, I wish to see how You have entered into this cosmic manifestation. I want to see that form of Yours.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मन्यसे यदि तच्छक्यं मया द्रष्टुमिति प्रभो ।\nयोगेश्वर ततो मे त्वं दर्शयात्मानमव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("મન્યસે યદિ તચ્છક્યં મયા દ્રષ્ટુમિતિ પ્રભો,\nયોગેશ્વર તતો મે ત્વં દર્શયાત્માનમવ્યયમ્.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Manyase yadi tacchakyam mayaa drashtumiti prabho;\nYogeshwara tato me twam darshayaatmaanamavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे प्रभो! (उत्पत्ति, स्थिति और प्रलय तथा अन्तर्यामी रूप से शासन करने वाला होने से भगवान का नाम 'प्रभु' है) यदि मेरे द्वारा आपका वह रूप देखा जाना शक्य है- ऐसा आप मानते हैं, तो हे योगेश्वर! उस अविनाशी स्वरूप का मुझे दर्शन कराइए॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પ્રભો ! તે સ્વરૂપ મારાથી જોઈ શકાય તેમ હોય, એમ આપ માનતા હો તો હે યોગેશ્વર ! તે અવિનાશી સ્વરૂપના મને દર્શન કરાવો.(૪) ");
        } else if (2 == i2) {
            this.tv2.setText("If You think that I am able to behold Your cosmic form, O my Lord, O master of all mystic power, then kindly show me that unlimited universal Self.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nपश्य मे पार्थ रूपाणि शतशोऽथ सहस्रशः ।\nनानाविधानि दिव्यानि नानावर्णाकृतीनि च ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nપશ્ય મે પાર્થ રૂપાણિ શતશોથ સહસ્રશઃ,\nનાનાવિધાનિ દિવ્યાનિ નાનાવર્ણાકૃતીનિ ચ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nPashya me paartha roopaani shatasho’tha sahasrashah;\nNaanaavidhaani divyaani naanaavarnaakriteeni cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे पार्थ! अब तू मेरे सैकड़ों-हजारों नाना प्रकार के और नाना वर्ण तथा नाना आकृतिवाले अलौकिक रूपों को देख॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન બોલ્યા : હે પાર્થ ! અનેક પ્રકારનાં, અનેક વર્ણ અને અનેક આકાર નાં મારા સેંકડો અને હજારો નાના પ્રકાર નાં દિવ્ય રૂપોને નિહાળ.(૫) ");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: My dear Arjuna, O son of Pritha, see now My opulences, hundreds of thousands of varied divine and multicolored forms.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पश्यादित्यान्वसून्रुद्रानश्विनौ मरुतस्तथा ।\nबहून्यदृष्टपूर्वाणि पश्याश्चर्याणि भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("પશ્યાદિત્યાન્વસૂન્રુદ્રાનશ્વિનૌ મરુતસ્તથા,\nબહૂન્યદૃષ્ટપૂર્વાણિ પશ્યાશ્ચર્યાણિ ભારત.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Pashyaadityaan vasoon rudraan ashwinau marutastathaa;\nBahoonyadrishtapoorvaani pashyaashcharyaani bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भरतवंशी अर्जुन! तू मुझमें आदित्यों को अर्थात अदिति के द्वादश पुत्रों को, आठ वसुओं को, एकादश रुद्रों को, दोनों अश्विनीकुमारों को और उनचास मरुद्गणों को देख तथा और भी बहुत से पहले न देखे हुए आश्चर्यमय रूपों को देख॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! આદિત્યોને, વસુઓને, રુદ્રોને, અશ્વિનીકુમાંરોને તથા મરુતોને તું નીહાળ વળી પૂર્વે ન જોયેલંl એવા ઘણા આશ્વર્યોને તું જો.(૬)  ");
        } else if (2 == i2) {
            this.tv2.setText("O best of the Bharatas, see here the different manifestations of Adityas, Vasus, Rudras, Asvini-kumaras and all the other demigods. Behold the many wonderful things which no one has ever seen or heard of before.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इहैकस्थं जगत्कृत्स्नं पश्याद्य सचराचरम्‌ ।\nमम देहे गुडाकेश यच्चान्यद्द्रष्टमिच्छसि ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇહૈકસ્થં જગત્કૃત્સ્નં પશ્યાદ્ય સચરાચરમ્,\nમમ દેહે ગુડાકેશ યચ્ચાન્યદ્દ્રષ્ટુમિચ્છસિ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Ihaikastham jagatkritsnam pashyaadya sacharaacharam;\nMama dehe gudaakesha yachchaanyad drashtumicchasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! अब इस मेरे शरीर में एक जगह स्थित चराचर सहित सम्पूर्ण जगत को देख तथा और भी जो कुछ देखना चाहता हो सो देख॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ગુડાકેશ ! અહી મારા દેહમાં એકજ સ્થળે રહેલા સ્થાવર-જંગમ સહિત સમગ્ર જગતને આજે તું જો. અને બીજું જે કંઈ જોવા ઈચ્છતો હોય તે પણ જો.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("O Arjuna, whatever you wish to see, behold at once in this body of Mine! This universal form can show you whatever you now desire to see and whatever you may want to see in the future. Everything—moving and nonmoving—is here completely, in one place.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न तु मां शक्यसे द्रष्टमनेनैव स्वचक्षुषा ।\nदिव्यं ददामि ते चक्षुः पश्य मे योगमैश्वरम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ન તુ માં શક્યસે દ્રષ્ટુમનેનૈવ સ્વચક્ષુષા,\nદિવ્યં દદામિ તે ચક્ષુઃ પશ્ય મે યોગમૈશ્વરમ્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Na tu maam shakyase drashtum anenaiva swachakshushaa;\nDivyam dadaami te chakshuh pashya me yogamaishwaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु मुझको तू इन अपने प्राकृत नेत्रों द्वारा देखने में निःसंदेह समर्थ नहीं है, इसी से मैं तुझे दिव्य अर्थात अलौकिक चक्षु देता हूँ, इससे तू मेरी ईश्वरीय योग शक्ति को देख॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("પરંતુ તારાં આ ચર્મચક્ષુ વડે તું મને નિહાળી શકીશ નહિ. તે માટે હું તને દિવ્ય દ્રષ્ટિ આપુ છું ,મારા અલૈlકિક સામર્થ્યને તું જો.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("But you cannot see Me with your present eyes. Therefore I give you divine eyes. Behold My mystic opulence!(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nएवमुक्त्वा ततो राजन्महायोगेश्वरो हरिः ।\nदर्शयामास पार्थाय परमं रूपमैश्वरम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજય  ઉવાચ-\nએવમુક્ત્વા તતો રાજન્મહાયોગેશ્વરો હરિઃ,\nદર્શયામાસ પાર્થાય પરમં રૂપમૈશ્વરમ્.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nEvamuktwaa tato raajan mahaayogeshwaro harih;\nDarshayaamaasa paarthaaya paramam roopamaishwaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संजय बोले- हे राजन्‌! महायोगेश्वर और सब पापों के नाश करने वाले भगवान ने इस प्रकार कहकर उसके पश्चात अर्जुन को परम ऐश्वर्ययुक्त दिव्यस्वरूप दिखलाया॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે : હે રાજન ! મહાયોગેશ્વર નારાયણે એ પ્રમાણે અર્જુનને કહ્યું. પછી તેને પોતાનું દિવ્ય પરમ ઐશ્વર્યરૂપ વિરાટ સ્વરૂપ બતાવ્યું.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said: O King, having spoken thus, the Supreme Lord of all mystic power, the Personality of Godhead, displayed His universal form to Arjuna.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10_11() {
        setTitle("Shlok 10,11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनेकवक्त्रनयनमनेकाद्भुतदर्शनम्‌ ।\nअनेकदिव्याभरणं दिव्यानेकोद्यतायुधम्‌ ॥\nदिव्यमाल्याम्बरधरं दिव्यगन्धानुलेपनम्‌ ।\nसर्वाश्चर्यमयं देवमनन्तं विश्वतोमुखम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અનેકવક્ત્રનયનમનેકાદ્ભુતદર્શનમ્,\nઅનેકદિવ્યાભરણં દિવ્યાનેકોદ્યતાયુધમ્.(૧૦)\nદિવ્યમાલ્યામ્બરધરં દિવ્યગન્ધાનુલેપનમ્,\nસર્વાશ્ચર્યમયં દેવમનન્તં વિશ્વતોમુખમ્.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Anekavaktra nayanam anekaadbhuta darshanam;\nAnekadivyaabharanam divyaanekodyataayudham.\nDivyamaalyaambaradharam divyagandhaanulepanam;\nSarvaashcharyamayam devam anantam vishwatomukham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अनेक मुख और नेत्रों से युक्त, अनेक अद्भुत दर्शनों वाले, बहुत से दिव्य भूषणों से युक्त और बहुत से दिव्य शस्त्रों को धारण किए हुए और दिव्य गंध का सारे शरीर में लेप किए हुए, सब प्रकार के आश्चर्यों से युक्त, सीमारहित और सब ओर मुख किए हुए विराट्स्वरूप परमदेव परमेश्वर को अर्जुन ने देखा॥10-11॥");
        } else if (i2 == 0) {
            this.tv2.setText("અનેક મુખ તથા આંખોવાળું, અનેક અદભુત દર્શનવાળું, અનેક દિવ્ય આભુષણવાળું અને અનેક ઉગામેલા દિવ્ય આયુધોવાળું એ સ્વરૂપ હતું. દિવ્ય-માળા અને વસ્ત્રો ધારણ કરેલું, દિવ્ય સુગંધી દ્રવ્યોથી લેપન કરેલું, સર્વ આશ્વર્યમય પ્રકાશરૂપ,અનંત અને સર્વ બાજુ મુખ વાળું તે સ્વરૂપ અર્જુને જોયું.(૧૦-૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna saw in that universal form unlimited mouths, unlimited eyes, unlimited wonderful visions. The form was decorated with many celestial ornaments and bore many divine upraised weapons. He wore celestial garlands and garments, and many divine scents were smeared over His body. All was wondrous, brilliant, unlimited, all-expanding.(10-11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दिवि सूर्यसहस्रस्य भवेद्युगपदुत्थिता ।\nयदि भाः सदृशी सा स्याद्भासस्तस्य महात्मनः ॥");
        } else if (i == 0) {
            this.tv1.setText("દિવિ સૂર્યસહસ્રસ્ય ભવેદ્યુગપદુત્થિતા,\nયદિ ભાઃ સદૃશી સા સ્યાદ્ભાસસ્તસ્ય મહાત્મનઃ.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Divi sooryasahasrasya bhavedyugapadutthitaa;\nYadi bhaah sadrishee saa syaadbhaasastasya mahaatmanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आकाश में हजार सूर्यों के एक साथ उदय होने से उत्पन्न जो प्रकाश हो, वह भी उस विश्व रूप परमात्मा के प्रकाश के सदृश कदाचित्‌ ही हो॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("આકાશમાં એક સાથે હજારો સૂર્યોનું તેજ પ્રકાશી ઊઠે તો પણ તે વિશ્વસ્વરૂપ પરમાત્માના તેજની તોલે કદાચ જ આવે.(૧૨) ");
        } else if (2 == i2) {
            this.tv2.setText("If hundreds of thousands of suns were to rise at once into the sky, their radiance might resemble the effulgence of the Supreme Person in that universal form.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्रैकस्थं जगत्कृत्स्नं प्रविभक्तमनेकधा ।\nअपश्यद्देवदेवस्य शरीरे पाण्डवस्तदा ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્રૈકસ્થં જગત્કૃત્સ્નં પ્રવિભક્તમનેકધા,\nઅપશ્યદ્દેવદેવસ્ય શરીરે પાણ્ડવસ્તદા.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Tatraikastham jagatkritsnam pravibhaktamanekadhaa;\nApashyaddevadevasya shareere paandavastadaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पाण्डुपुत्र अर्जुन ने उस समय अनेक प्रकार से विभक्त अर्थात पृथक-पृथक सम्पूर्ण जगत को देवों के देव श्रीकृष्ण भगवान के उस शरीर में एक जगह स्थित देखा॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે સમયે અર્જુને દેવાધિદેવ શ્રી કૃષ્ણના દિવ્ય સ્વરૂપમાં અનેક વિભાગોમાં વિભક્ત થયેલું સર્વ જગત સ્થિત થયેલું જોયું.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("At that time Arjuna could see in the universal form of the Lord the unlimited expansions of the universe situated in one place although divided into many, many thousands.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ततः स विस्मयाविष्टो हृष्टरोमा धनञ्जयः ।\nप्रणम्य शिरसा देवं कृताञ्जलिरभाषत ॥");
        } else if (i == 0) {
            this.tv1.setText("તતઃ સ વિસ્મયાવિષ્ટો હૃષ્ટરોમા ધનઞ્જયઃ,\nપ્રણમ્ય શિરસા દેવં કૃતાઞ્જલિરભાષત.(૧૪)\n");
        } else if (2 == i) {
            this.tv1.setText("Tatah sa vismayaavishto hrishtaromaa dhananjayah;\nPranamya shirasaa devam kritaanjalirabhaashata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उसके अनंतर आश्चर्य से चकित और पुलकित शरीर अर्जुन प्रकाशमय विश्वरूप परमात्मा को श्रद्धा-भक्ति सहित सिर से प्रणाम करके हाथ जोड़कर बोले॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("ત્યાર પછી આશ્વર્યચકિત અને રોમાંચિત થયેલો ધનંજય ભગવાન શ્રી હરિને પ્રણામ કરી, બે હાથ જોડી કહેવા લાગ્યો.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("Then, bewildered and astonished, his hair standing on end, Arjuna bowed his head to offer obeisances and with folded hands began to pray to the Supreme Lord.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nपश्यामि देवांस्तव देव देहे सर्वांस्तथा भूतविशेषसङ्‍घान्‌ ।\nब्रह्माणमीशं कमलासनस्थमृषींश्च सर्वानुरगांश्च दिव्यान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nપશ્યામિ દેવાંસ્તવ દેવ દેહેસર્વાંસ્તથા ભૂતવિશેષસઙ્ઘાન્,\nબ્રહ્માણમીશં કમલાસનસ્થ-મૃષીંશ્ચ સર્વાનુરગાંશ્ચ દિવ્યાન્.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nPashyaami devaamstava deva dehe\nSarvaamstathaa bhootavisheshasanghaan;\nBrahmaanameesham kamalaasanasthaMrisheemshcha\nsarvaanuragaamshcha divyaan");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे देव! मैं आपके शरीर में सम्पूर्ण देवों को तथा अनेक भूतों के समुदायों को, कमल के आसन पर विराजित ब्रह्मा को, महादेव को और सम्पूर्ण ऋषियों को तथा दिव्य सर्पों को देखता हूँ॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન બોલ્યો : હે ભગવાન ! આપના દેહમાં હું સર્વ દેવોને, ભિન્ન ભિન્ન ભૂતોના સમુદાયને, કમળ પર બિરાજમાન  સર્વના નિયંતા બ્રહ્માજીને, સર્વ ઋષિઓને તેમજ દિવ્ય સર્પોને જોઈ રહ્યો છું.(૧૫)  ");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: My dear Lord Krishna, I see assembled in Your body all the demigods and various other living entities. I see Brahma sitting on the lotus flower, as well as Lord Shiva and all the sages and divine serpents.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनेकबाहूदरवक्त्रनेत्रंपश्यामि त्वां सर्वतोऽनन्तरूपम्‌ ।\nनान्तं न मध्यं न पुनस्तवादिंपश्यामि विश्वेश्वर विश्वरूप ॥");
        } else if (i == 0) {
            this.tv1.setText("અનેકબાહૂદરવક્ત્રનેત્રંપશ્યામિ ત્વાં સર્વતોનન્તરૂપમ્,\nનાન્તં ન મધ્યં ન પુનસ્તવાદિંપશ્યામિ વિશ્વેશ્વર વિશ્વરૂપ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Anekabaahoodaravaktranetram\nPashyaami twaam sarvato’nantaroopam;\nNaantam na madhyam na punastavaadim\nPashyaami vishweshwara vishwaroopa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे सम्पूर्ण विश्व के स्वामिन्! आपको अनेक भुजा, पेट, मुख और नेत्रों से युक्त तथा सब ओर से अनन्त रूपों वाला देखता हूँ। हे विश्वरूप! मैं आपके न अन्त को देखता हूँ, न मध्य को और न आदि को ही॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિશ્વેશ્વર ! હે વિશ્વરૂપ ! આપના અગણિત બાહુ, ઉદરો, મુખો અને નેત્રો દેખાઈ રહ્યા છે. એથી સર્વ બાજુ હું આપને અનંત રૂપવાળા જોઉં છું, વળી આપનો આદિ, મધ્ય કે અંત ક્યાંય દેખાતો નથી.(૧૬)  ");
        } else if (2 == i2) {
            this.tv2.setText("O Lord of the universe, O universal form, I see in Your body many, many arms, bellies, mouths and eyes, expanded everywhere, without limit. I see in You no end, no middle and no beginning.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("किरीटिनं गदिनं चक्रिणं च तेजोराशिं सर्वतो दीप्तिमन्तम्‌ ।\nपश्यामि त्वां दुर्निरीक्ष्यं समन्ताद्दीप्तानलार्कद्युतिमप्रमेयम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કિરીટિનં ગદિનં ચક્રિણં ચતેજોરાશિં સર્વતોદીપ્તિમન્તમ્,\nપશ્યામિ ત્વાં દુર્નિરીક્ષ્યં સમન્તા-દ્દીપ્તાનલાર્કદ્યુતિમપ્રમેયમ્.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Kireetinam gadinam chakrinam cha,\nTejoraashim sarvato deeptimantam;\nPashyaami twaam durnireekshyam samantaad\nDeeptaanalaarkadyutimaprameyam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आपको मैं मुकुटयुक्त, गदायुक्त और चक्रयुक्त तथा सब ओर से प्रकाशमान तेज के पुंज, प्रज्वलित अग्नि और सूर्य के सदृश ज्योतियुक्त, कठिनता से देखे जाने योग्य और सब ओर से अप्रमेयस्वरूप देखता हूँ॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરમેશ્વર ! મુકુટ યુક્ત, હસ્તમાં ગદા અને ચક્ર ધારણ કરેલા, તેજ ના સમૂહ રૂપ સર્વ બાજુથી પ્રકાશિત, મુશ્કેલીથી નિહાળી શકાય તેવા, પ્રજ્જવલિત અગ્નિ તથા સૂર્યની ક્રાંતિ સમાન, નિશ્વિત કરવાને અશક્ય એવા આપને હું સર્વ તરફથી નિહાળી રહ્યો છું.(૧૭)     ");
        } else if (2 == i2) {
            this.tv2.setText("Your form is difficult to see because of its glaring effulgence, spreading on all sides, like blazing fire or the immeasurable radiance of the sun. Yet I see this glowing form everywhere, adorned with various crowns, clubs and discs.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्वमक्षरं परमं वेदितव्यंत्वमस्य विश्वस्य परं निधानम्‌ ।\nत्वमव्ययः शाश्वतधर्मगोप्ता सनातनस्त्वं पुरुषो मतो मे ॥");
        } else if (i == 0) {
            this.tv1.setText("ત્વમક્ષરં પરમં વેદિતવ્યંત્વમસ્ય વિશ્વસ્ય પરં નિધાનમ્,\nત્વમવ્યયઃ શાશ્વતધર્મગોપ્તાસનાતનસ્ત્વં પુરુષો મતો મે.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Twamaksharam paramam veditavyam\nTwamasya vishwasya param nidhaanam;\nTwamavyayah shaashwatadharmagoptaa\nSanaatanastwam purusho mato me.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आप ही जानने योग्य परम अक्षर अर्थात परब्रह्म परमात्मा हैं। आप ही इस जगत के परम आश्रय हैं, आप ही अनादि धर्म के रक्षक हैं और आप ही अविनाशी सनातन पुरुष हैं। ऐसा मेरा मत है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરમેશ્વર ! આપ જાણવા યોગ્ય પરમ અક્ષર છો, આપ આ વિશ્વના પરમ આશ્રય છો. આપ અવિનાશી છો. આપ સનાતન ધર્મ ના રક્ષક છો. આપ પુરાણપુરુષ છો એમ હું માનું છું.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("You are the supreme primal objective. You are the ultimate resting place of all this universe. You are inexhaustible, and You are the oldest. You are the maintainer of the eternal religion, the Personality of Godhead. This is my opinion.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनादिमध्यान्तमनन्तवीर्यमनन्तबाहुं शशिसूर्यनेत्रम्‌ ।\nपश्यामि त्वां दीप्तहुताशवक्त्रंस्वतेजसा विश्वमिदं तपन्तम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અનાદિમધ્યાન્તમનન્તવીર્ય-મનન્તબાહું શશિસૂર્યનેત્રમ્,\nપશ્યામિ ત્વાં દીપ્તહુતાશવક્ત્રમ્સ્વતેજસા વિશ્વમિદં તપન્તમ્.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Anaadimadhyaantamanantaveeryam\nAnantabaahum shashisooryanetram;\nPashyaami twaam deeptahutaashavaktram\nSwatejasaa vishwamidam tapantam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आपको आदि, अंत और मध्य से रहित, अनन्त सामर्थ्य से युक्त, अनन्त भुजावाले, चन्द्र-सूर्य रूप नेत्रों वाले, प्रज्वलित अग्निरूप मुखवाले और अपने तेज से इस जगत को संतृप्त करते हुए देखता हूँ॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિભુ ! આપનો આદિ, મધ્ય કે અંત નથી, આપ અનંત શક્તિવાળા, અનંત બાહુ વાળા, ચંદ્રસૂર્યરૂપી નેત્રોવાળા, મુખમાં પ્રજ્જવલિત અગ્નિવાળા, પોતાના પરમ તેજથી વિશ્વને તપાવનારા આપને હું જોઈ રહ્યો છું.(૧૯)        ");
        } else if (2 == i2) {
            this.tv2.setText("You are without origin, middle or end. Your glory is unlimited. You have numberless arms, and the sun and moon are Your eyes. I see You with blazing fire coming forth from Your mouth, burning this entire universe by Your own radiance.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("द्यावापृथिव्योरिदमन्तरं हि व्याप्तं त्वयैकेन दिशश्च सर्वाः ।\nदृष्ट्वाद्भुतं रूपमुग्रं तवेदंलोकत्रयं प्रव्यथितं महात्मन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("દ્યાવાપૃથિવ્યોરિદમન્તરં હિવ્યાપ્તં ત્વયૈકેન દિશશ્ચ સર્વાઃ,\nદૃષ્ટ્વાદ્ભુતં રૂપમુગ્રં તવેદંલોકત્રયં પ્રવ્યથિતં મહાત્મન્.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Dyaavaaprithivyoridamantaram hi\nVyaaptam twayaikena dishashcha sarvaah;\nDrishtwaa’dbhutam roopamugram tavedam\nLokatrayam pravyathitam mahaatman.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे महात्मन्‌! यह स्वर्ग और पृथ्वी के बीच का सम्पूर्ण आकाश तथा सब दिशाएँ एक आपसे ही परिपूर्ण हैं तथा आपके इस अलौकिक और भयंकर रूप को देखकर तीनों लोक अतिव्यथा को प्राप्त हो रहे हैं॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાત્મન ! આપ એકલા એ  જ આકાશ અને પૃથ્વીનું સઘળું અંતર વ્યાપ્ત કર્યું છે. તથા સર્વ દિશાઓ  આપનાથી વ્યાપ્ત દેખાય છે. આપના અદભુત અને અતિ ઉગ્રરૂપને જોઇને ત્રણેલોક  અત્યંત ભયભીત બની ગયાં છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Although You are one, You spread throughout the sky and the planets and all space between. O great one, seeing this wondrous and terrible form, all the planetary systems are perturbed.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अमी हि त्वां सुरसङ्‍घा विशन्ति केचिद्भीताः प्राञ्जलयो गृणन्ति।\nस्वस्तीत्युक्त्वा महर्षिसिद्धसङ्‍घा: स्तुवन्ति त्वां स्तुतिभिः पुष्कलाभिः ॥");
        } else if (i == 0) {
            this.tv1.setText("અમી હિ ત્વાં સુરસઙ્ઘાઃ વિશન્તિકેચિદ્ભીતાઃ પ્રાઞ્જલયો ગૃણન્તિ,\nસ્વસ્તીત્યુક્ત્વા મહર્ષિસિદ્ધસઙ્ઘાઃસ્તુવન્તિ ત્વાં સ્તુતિભિઃ પુષ્કલાભિઃ.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Amee hi twaam surasanghaah vishanti\nKechid bheetaah praanjalayo grinanti;\nSwasteetyuktwaa maharshisiddhasanghaah\nStuvanti twaam stutibhih pushkalaabhih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे ही देवताओं के समूह आप में प्रवेश करते हैं और कुछ भयभीत होकर हाथ जोड़े आपके नाम और गुणों का उच्चारण करते हैं तथा महर्षि और सिद्धों के समुदाय 'कल्याण हो' ऐसा कहकर उत्तम-उत्तम स्तोत्रों द्वारा आपकी स्तुति करते हैं॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ દેવોનો સમૂહ આપનામાં જ પ્રવેશે છે.કેટલાક ભયભીત થઈને બે હાથ જોડી આપની સ્તુતિ કરે છે. મહર્ષિ અને સિદ્ધોનો સમૂહ ” કલ્યાણ થાઓ ” એમ બોલીને પરિપૂર્ણ અર્થ બોધ કરનારા સ્તુતિ વચનો વડે આપની સ્તુતિ કરે છે.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("All the hosts of demigods are surrendering before You and entering into You. Some of them, very much afraid, are offering prayers with folded hands. Hosts of great sages and perfected beings, crying “All peace!” are praying to You by singing the Vedic hymns.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रुद्रादित्या वसवो ये च साध्याविश्वेऽश्विनौ मरुतश्चोष्मपाश्च ।\nगंधर्वयक्षासुरसिद्धसङ्‍घावीक्षन्ते त्वां विस्मिताश्चैव सर्वे ॥");
        } else if (i == 0) {
            this.tv1.setText("રુદ્રાદિત્યા વસવો યે ચ સાધ્યાવિશ્વેશ્વિનૌ મરુતશ્ચોષ્મપાશ્ચ।,\nગન્ધર્વયક્ષાસુરસિધ્દસઙ્ઘાવીક્ષન્તે ત્વાં વિસ્મિતાશ્ચૈવ સર્વે.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Rudraadityaa vasavo ye cha saadhyaa\nVishwe’shvinau marutashchoshmapaashcha;\nGandharvayakshaasurasiddhasanghaa\nVeekshante twaam vismitaashchaiva sarve.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो ग्यारह रुद्र और बारह आदित्य तथा आठ वसु, साध्यगण, विश्वेदेव, अश्विनीकुमार तथा मरुद्गण और पितरों का समुदाय तथा गंधर्व, यक्ष, राक्षस और सिद्धों के समुदाय हैं- वे सब ही विस्मित होकर आपको देखते हैं॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિભુ ! રુદ્ર, આદિત્યો, વસુઓ, સાધ્ય દેવો, વિશ્વદેવો, અશ્વિનીકુમારો,મરુતો, પિતૃઓ, ગંધર્વ, યક્ષ, અસુર, સિદ્ધોનો સમૂહ વગેરે સર્વ વિસ્મ્સ્ય થયેલા આપને જોઈ રહ્યા છે.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("All the various manifestations of Lord Shiva, the Adityas, the Vasus, the Sadhyas, the Visvedevas, the two Asvis, the Maruts, the forefathers, the Gandharvas, the Yakshas, the Asuras and the perfected demigods are beholding You in wonder.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रूपं महत्ते बहुवक्त्रनेत्रंमहाबाहो बहुबाहूरूपादम्‌ ।\nबहूदरं बहुदंष्ट्राकरालंदृष्टवा लोकाः प्रव्यथितास्तथाहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("રૂપં મહત્તે બહુવક્ત્રનેત્રંમહાબાહો બહુબાહૂરુપાદમ્,\nબહૂદરં બહુદંષ્ટ્રાકરાલંદૃષ્ટ્વા લોકાઃ પ્રવ્યથિતાસ્તથાહમ્.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Roopam mahat te bahuvaktranetram\nMahaabaaho bahubaahoorupaadam;\nBahoodaram bahudamshtraakaraalam\nDrishtwaa lokaah pravyathitaastathaa’ham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे महाबाहो! आपके बहुत मुख और नेत्रों वाले, बहुत हाथ, जंघा और पैरों वाले, बहुत उदरों वाले और बहुत-सी दाढ़ों के कारण अत्यन्त विकराल महान रूप को देखकर सब लोग व्याकुल हो रहे हैं तथा मैं भी व्याकुल हो रहा हूँ॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો ! બહુ મુખ તથા નેત્રવાળા, ઘણા હાથ -પગવાળા, ઘણા ઉદર વાળા, ઘણી વિકરાળ દાઢોવાળા આપના આ વિશાળ રૂપને જોઇને લોકો ભય પામી રહ્યા છે તેમજ હું પણ વ્યથિત થઇ રહ્યો છું.(૨૩)   ");
        } else if (2 == i2) {
            this.tv2.setText("O mighty-armed one, all the planets with their demigods are disturbed at seeing Your great form, with its many faces, eyes, arms, thighs, legs, and bellies and Your many terrible teeth; and as they are disturbed, so am I.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नभःस्पृशं दीप्तमनेकवर्णंव्यात्ताननं दीप्तविशालनेत्रम्‌ ।\nदृष्टवा हि त्वां प्रव्यथितान्तरात्मा धृतिं न विन्दामि शमं च विष्णो ॥");
        } else if (i == 0) {
            this.tv1.setText("નભઃસ્પૃશં દીપ્તમનેકવર્ણંવ્યાત્તાનનં દીપ્તવિશાલનેત્રમ્,\nદૃષ્ટ્વા હિ ત્વાં પ્રવ્યથિતાન્તરાત્માધૃતિં ન વિન્દામિ શમં ચ વિષ્ણો.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Nabhahsprisham deeptamanekavarnam\nVyaattaananam deeptavishaalanetram;\nDrishtwaa hi twaam pravyathitaantaraatmaa\nDhritim na vindaami shamam cha vishno.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि हे विष्णो! आकाश को स्पर्श करने वाले, दैदीप्यमान, अनेक वर्णों से युक्त तथा फैलाए हुए मुख और प्रकाशमान विशाल नेत्रों से युक्त आपको देखकर भयभीत अन्तःकरण वाला मैं धीरज और शान्ति नहीं पाता हूँ॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિષ્ણુ ! આકાશને સ્પર્શ કરતા, પ્રજ્જવલિત અનેક વર્ણવાળા, ઉઘાડા મુખવાળા, વિશાળ તેજસ્વી આંખોવાળા આપને નિહાળી ને નિશ્વય થી મારો અંતરાત્મા વ્યાકુળ થઇ રહ્યો છે. આથી મારું મન  ધીરજ ન ધરવા થી હું શાંતિ ને પામી શકતો નથી.(૨૪)     ");
        } else if (2 == i2) {
            this.tv2.setText("O all-pervading Vishnu, seeing You with Your many radiant colors touching the sky, Your gaping mouths, and Your great glowing eyes, my mind is perturbed by fear. I can no longer maintain my steadiness or equilibrium of mind.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दंष्ट्राकरालानि च ते मुखानिदृष्टैव कालानलसन्निभानि ।\nदिशो न जाने न लभे च शर्म प्रसीद देवेश जगन्निवास ॥");
        } else if (i == 0) {
            this.tv1.setText("દંષ્ટ્રાકરાલાનિ ચ તે મુખાનિદૃષ્ટ્વૈવ કાલાનલસન્નિભાનિ,\nદિશો ન જાને ન લભે ચ શર્મપ્રસીદ દેવેશ જગન્નિવાસ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Damshtraakaraalaani cha te mukhaani\nDrishtwaiva kaalaanalasannibhaani;\nDisho na jaane na labhe cha sharma\nPraseeda devesha jagannivaasa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("दाढ़ों के कारण विकराल और प्रलयकाल की अग्नि के समान प्रज्वलित आपके मुखों को देखकर मैं दिशाओं को नहीं जानता हूँ और सुख भी नहीं पाता हूँ। इसलिए हे देवेश! हे जगन्निवास! आप प्रसन्न हों॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે દેવેશ ! આપની વિકરાળ દાઢોવાળા, પ્રલયકાળ ના અગ્નિ સમાન આપના મુખો જોઈને હું દિશાઓને પણ સમજી શકતો નથી તથા મને સુખ મળતું નથી. હે જગનિવાસ ! આપ મારા પર પ્રસન્ન થાઓ.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("O Lord of lords, O refuge of the worlds, please be gracious to me. I cannot keep my balance seeing thus Your blazing deathlike faces and awful teeth. In all directions I am bewildered.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26_27() {
        setTitle("Shlok 26,27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अमी च त्वां धृतराष्ट्रस्य पुत्राः सर्वे सहैवावनिपालसंघैः ।\nभीष्मो द्रोणः सूतपुत्रस्तथासौ सहास्मदीयैरपि योधमुख्यैः ॥\nवक्त्राणि ते त्वरमाणा विशन्ति दंष्ट्राकरालानि भयानकानि ।\nकेचिद्विलग्ना दशनान्तरेषु सन्दृश्यन्ते चूर्णितैरुत्तमाङ्‍गै ॥");
        } else if (i == 0) {
            this.tv1.setText("અમી ચ ત્વાં ધૃતરાષ્ટ્રસ્ય પુત્રાઃસર્વે સહૈવાવનિપાલસઙ્ઘૈઃ,\nભીષ્મો દ્રોણઃ સૂતપુત્રસ્તથાસૌસહાસ્મદીયૈરપિ યોધમુખ્યૈઃ.(૨૬)\nવક્ત્રાણિ તે ત્વરમાણા વિશન્તિદંષ્ટ્રાકરાલાનિ ભયાનકાનિ,\nકેચિદ્વિલગ્ના દશનાન્તરેષુસંદૃશ્યન્તે ચૂર્ણિતૈરુત્તમાઙ્ગૈઃ.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Amee cha twaam dhritaraashtrasya putraah\nSarve sahaivaavanipaalasanghaih;\nBheeshmo dronah sootaputrastathaa’sau\nSahaasmadeeyairapi yodhamukhyaih.\nVaktraani te twaramaanaa vishanti\nDamshtraakaraalaani bhayaanakaani;\nKechidwilagnaa dashanaantareshu\nSandrishyante choornitairuttamaangaih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे सभी धृतराष्ट्र के पुत्र राजाओं के समुदाय सहित आप में प्रवेश कर रहे हैं और भीष्म पितामह, द्रोणाचार्य तथा वह कर्ण और हमारे पक्ष के भी प्रधान योद्धाओं के सहित सबके सब आपके दाढ़ों के कारण विकराल भयानक मुखों में बड़े वेग से दौड़ते हुए प्रवेश कर रहे हैं और कई एक चूर्ण हुए सिरों सहित आपके दाँतों के बीच में लगे हुए दिख रहे हैं॥26-27॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિભો ! રાજાઓના સમૂહ સહીત ધૃતરાષ્ટ્રના સર્વ પુત્રો આપનામાં પ્રવેશ કરી રહ્યા છે.ભીષ્મ, દ્રોણાચાર્ય, સુતપુત્ર કર્ણ, અને અમારા સંબંધરૂપ અનેક પ્રમુખ યોદ્ધાઓ. વિકરાળ દાઢોવાળા આપના ભયાનક મુખોમાં વેગપૂર્વક પ્રવેશી રહ્યા છે. કેટલાક યોદ્ધાઓ ચૂર્ણ થયેલાં મસ્તકો સહિત આપના દાંતોની વચ્ચે વળગેલા છે.(૨૬-૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("All the sons of Dhritarashtra with the hosts of kings of the earth, Bhishma, Drona and Karna, with the chief among all our warriors, They hurriedly enter into Thy mouths with terrible teeth and fearful to behold. Some are found sticking in the gaps between the teeth, with their heads crushed to powder(26-27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथा नदीनां बहवोऽम्बुवेगाः समुद्रमेवाभिमुखा द्रवन्ति ।\nतथा तवामी नरलोकवीराविशन्ति वक्त्राण्यभिविज्वलन्ति ॥");
        } else if (i == 0) {
            this.tv1.setText("યથા નદીનાં બહવોમ્બુવેગાઃસમુદ્રમેવાભિમુખાઃ દ્રવન્તિ,\nતથા તવામી નરલોકવીરાવિશન્તિ વક્ત્રાણ્યભિવિજ્વલન્તિ.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Yathaa nadeenaam bahavo’mbuvegaah\nSamudramevaabhimukhaah dravanti;\nTathaa tavaamee naralokaveeraah\nVishanti vaktraanyabhivijwalanti.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जैसे नदियों के बहुत-से जल के प्रवाह स्वाभाविक ही समुद्र के ही सम्मुख दौड़ते हैं अर्थात समुद्र में प्रवेश करते हैं, वैसे ही वे नरलोक के वीर भी आपके प्रज्वलित मुखों में प्रवेश कर रहे हैं॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેમ નદીઓના ઘણા જળપ્રવાહો સાગર તરફ વહેતાં વહેતાં સાગરમાં સમાઈ જાય છે, તેમ આ લોક નાયકો આપના પ્રકાશમાન મુખોમાં પ્રવેશ કરે છે.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("As the many waves of the rivers flow into the ocean, so do all these great warriors enter blazing into Your mouths.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथा प्रदीप्तं ज्वलनं पतंगाविशन्ति नाशाय समृद्धवेगाः ।\nतथैव नाशाय विशन्ति लोकास्तवापि वक्त्राणि समृद्धवेगाः ॥");
        } else if (i == 0) {
            this.tv1.setText("યથા પ્રદીપ્તં જ્વલનં પતઙ્ગાવિશન્તિ નાશાય સમૃદ્ધવેગાઃ,\nતથૈવ નાશાય વિશન્તિ લોકા-સ્તવાપિ વક્ત્રાણિ સમૃદ્ધવેગાઃ.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Yathaa pradeeptam jwalanam patangaa\nVishanti naashaaya samriddhavegaah;\nTathaiva naashaaya vishanti lokaas\nTavaapi vaktraani samriddhavegaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जैसे पतंग मोहवश नष्ट होने के लिए प्रज्वलित अग्नि में अतिवेग से दौड़ते हुए प्रवेश करते हैं, वैसे ही ये सब लोग भी अपने नाश के लिए आपके मुखों में अतिवेग से दौड़ते हुए प्रवेश कर रहे हैं॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેમ પ્રજ્જવલિત અગ્નિમાં નાશ પામવા માટે પતંગિયાં વેગપૂર્વક પ્રવેશ કરી જાય છે,તેમ આ સર્વ લોકો પણ અત્યંત વેગવાળા થઈને નાશ પામવા માટે જ આપના પ્રજ્જવલિત મુખમાં પ્રવેશ કરતા જાય છે.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("As moths hurriedly rush into a blazing fire for (their own) destruction, so also these creatures hurriedly rush into Thy mouths for (their own) destruction.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("लेलिह्यसे ग्रसमानः समन्ताल्लोकान्समग्रान्वदनैर्ज्वलद्भिः ।\nतेजोभिरापूर्य जगत्समग्रंभासस्तवोग्राः प्रतपन्ति विष्णो ॥");
        } else if (i == 0) {
            this.tv1.setText("લેલિહ્યસે ગ્રસમાનઃ સમન્તા-લ્લોકાન્સમગ્રાન્વદનૈર્જ્વલદ્ભિઃ,\nતેજોભિરાપૂર્ય જગત્સમગ્રંભાસસ્તવોગ્રાઃ પ્રતપન્તિ વિષ્ણો.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Lelihyase grasamaanah samantaal\nLokaan samagraan vadanair jwaladbhih;\nTejobhiraapoorya jagatsamagram\nBhaasastavograah pratapanti vishno.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आप उन सम्पूर्ण लोकों को प्रज्वलित मुखों द्वारा ग्रास करते हुए सब ओर से बार-बार चाट रहे हैं। हे विष्णो! आपका उग्र प्रकाश सम्पूर्ण जगत को तेज द्वारा परिपूर्ण करके तपा रहा है॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિષ્ણુ ! આપના પ્રજ્જવલિત મુખો વડે સમગ્ર લોકોને ગળી જવાના હો તેમ આપ ચારે બાજુથી ચાટી રહ્યા છો.આપનું અતિ ઉગ્ર તેજ સંપૂર્ણ જગતને સંતાપી રહ્યું છે.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("Thou lickest up, devouring all the worlds on every side with Thy flaming mouths. Thy fierce rays, filling the whole world with radiance, are burning, O Vishnu!(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        setTitle("Shlok 31");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आख्याहि मे को भवानुग्ररूपोनमोऽस्तु ते देववर प्रसीद ।\nविज्ञातुमिच्छामि भवन्तमाद्यंन हि प्रजानामि तव प्रवृत्तिम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("આખ્યાહિ મે કો ભવાનુગ્રરૂપોનમોસ્તુ તે દેવવર પ્રસીદ,\nવિજ્ઞાતુમિચ્છામિ ભવન્તમાદ્યંન હિ પ્રજાનામિ તવ પ્રવૃત્તિમ્.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Aakhyaahi me ko bhavaanugraroopo\nNamo’stu te devavara praseeda;\nVijnaatumicchaami bhavantamaadyam\nNa hi prajaanaami tava pravrittim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मुझे बतलाइए कि आप उग्ररूप वाले कौन हैं? हे देवों में श्रेष्ठ! आपको नमस्कार हो। आप प्रसन्न होइए। आदि पुरुष आपको मैं विशेष रूप से जानना चाहता हूँ क्योंकि मैं आपकी प्रवृत्ति को नहीं जानता॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે દેવશ્રેષ્ઠ ! આવા અતિ ઉગ્ર સ્વરૂપવાળા આપ કોણ છો ! આપ પ્રસન્ન થાઓ. હું આપને નમસ્કાર કરું છું.સર્વના આદ્ય રૂપ આપને હું જાણવાની ઈચ્છા રાખું છું.કેમકે આપની ગુઢ ચેષ્ટાઓને હું જાણતો નથી.(૩૧)");
        } else if (2 == i2) {
            this.tv2.setText(" Tell me, who Thou art, so fierce in form. Salutations to Thee, O God Supreme! Have mercy; I desire to know Thee, the original Being. I know not indeed Thy doing(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        setTitle("Shlok 32");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nकालोऽस्मि लोकक्षयकृत्प्रवृद्धोलोकान्समाहर्तुमिह प्रवृत्तः ।\nऋतेऽपि त्वां न भविष्यन्ति सर्वे येऽवस्थिताः प्रत्यनीकेषु योधाः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nકાલોસ્મિ લોકક્ષયકૃત્પ્રવૃદ્ધોલોકાન્સમાહર્તુમિહ પ્રવૃત્તઃ,\nઋતેપિ ત્વાં ન ભવિષ્યન્તિ સર્વેયેવસ્થિતાઃ પ્રત્યનીકેષુ યોધાઃ.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nKaalo’smi lokakshayakrit pravriddho\nLokaan samaahartumiha pravrittah;\nRite’pi twaam na bhavishyanti sarve\nYe’wasthitaah pratyaneekeshu yodhaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- मैं लोकों का नाश करने वाला बढ़ा हुआ महाकाल हूँ। इस समय इन लोकों को नष्ट करने के लिए प्रवृत्त हुआ हूँ। इसलिए जो प्रतिपक्षियों की सेना में स्थित योद्धा लोग हैं, वे सब तेरे बिना भी नहीं रहेंगे अर्थात तेरे युद्ध न करने पर भी इन सबका नाश हो जाएगा॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન બોલ્યા : લોકોનો સંહાર કરનારો, અત્યંત વૃદ્ધિ પામેલો મહાન કાળ હું છું,હાલ આ લોકોનો નાશ કરવા માટે હું પ્રવૃત થયો છું, પ્રતિપક્ષીઓની સેનામાં જે યોદ્ધાઓ ઉભા છે તે તારા વગર પણ જીવંત રહેવાના નથી.(૩૨)  ");
        } else if (2 == i2) {
            this.tv2.setText("I am the mighty world-destroying Time, now engaged in destroying the worlds. Even without thee, none of the warriors arrayed in the hostile armies shall live.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मात्त्वमुक्तिष्ठ यशो लभस्व जित्वा शत्रून्भुङ्‍क्ष्व राज्यं समृद्धम्‌ ।\nमयैवैते निहताः पूर्वमेव निमित्तमात्रं भव सव्यसाचिन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માત્ત્વમુત્તિષ્ઠ યશો લભસ્વજિત્વા શત્રૂન્ ભુઙ્ક્ષ્વ રાજ્યં સમૃદ્ધમ્,\nમયૈવૈતે નિહતાઃ પૂર્વમેવનિમિત્તમાત્રં ભવ સવ્યસાચિન્.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaat twam uttishtha yasho labhaswa\nJitwaa shatroon bhungkshwa raajyam samriddham;\nMayaivaite nihataah poorvameva\nNimittamaatram bhava savyasaachin.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अतएव तू उठ! यश प्राप्त कर और शत्रुओं को जीतकर धन-धान्य से सम्पन्न राज्य को भोग। ये सब शूरवीर पहले ही से मेरे ही द्वारा मारे हुए हैं। हे सव्यसाचिन! (बाएँ हाथ से भी बाण चलाने का अभ्यास होने से अर्जुन का नाम 'सव्यसाची' हुआ था) तू तो केवल निमित्तमात्र बन जा॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે સર્વસાચિ  ! માટે તું યુદ્ધ કરવા ઉભો થઇ જા. શત્રુઓને જીતીને યશ મેળવ અને ઐશ્વર્યસંપન્ન રાજ્ય ભોગાવ. તારા આ શત્રુઓ ખરેખર તો મેં પહેલેથી જ મારી નાખ્યા છે. તું કેવળ નિમિત્તરૂપ બન.(૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore get up. Prepare to fight and win glory. Conquer your enemies and enjoy a flourishing kingdom. They are already put to death by My arrangement, and you, O Savyasaci, can be but an instrument in the fight.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("द्रोणं च भीष्मं च जयद्रथं च कर्णं तथान्यानपि योधवीरान्‌ ।\nमया हतांस्त्वं जहि मा व्यथिष्ठायुध्यस्व जेतासि रणे सपत्नान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("દ્રોણં ચ ભીષ્મં ચ જયદ્રથં ચકર્ણં તથાન્યાનપિ યોધવીરાન્,\nમયા હતાંસ્ત્વં જહિ મા વ્યથિષ્ઠાયુધ્યસ્વ જેતાસિ રણે સપત્નાન્.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Dronam cha bheeshmam cha jayadratham cha\nKarnam tathaa’nyaanapi yodhaveeraan;\nMayaa hataamstwam jahi maa vyathishthaa\nYudhyaswa jetaasi rane sapatnaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("द्रोणाचार्य और भीष्म पितामह तथा जयद्रथ और कर्ण तथा और भी बहुत से मेरे द्वारा मारे हुए शूरवीर योद्धाओं को तू मार। भय मत कर। निःसंदेह तू युद्ध में वैरियों को जीतेगा। इसलिए युद्ध कर॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("દ્રોણને તથા ભીષ્મને, જયદ્રથને તથા કર્ણને અને બીજા મહારથી યોદ્ધાઓને મેં હણેલા જ છે તેમને તું હણ. ભયને લીધે તું વ્યથિત ન થા. હે પાર્થ ! તું યુદ્ધ કર.રણમાં દુશ્મનો પર તું અવશ્ય વિજય મેળવીશ.(૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("Drona, Bhishma, Jayadratha, Karna and all the other courageous warriors—these have already been slain by Me; do thou kill; be not distressed with fear; fight and thou shalt conquer thy enemies in battle.(34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        setTitle("Shlok 35");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nएतच्छ्रुत्वा वचनं केशवस्य कृतांजलिर्वेपमानः किरीटी ।\nनमस्कृत्वा भूय एवाह कृष्णंसगद्गदं भीतभीतः प्रणम्य ॥");
        } else if (i == 0) {
            this.tv1.setText("સઞ્જય ઉવાચ-\nએતચ્છ્રુત્વા વચનં કેશવસ્યકૃતાઞ્જલિર્વેપમાનઃ કિરીટી, \nનમસ્કૃત્વા ભૂય એવાહ કૃષ્ણંસગદ્ગદં ભીતભીતઃ પ્રણમ્ય.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nEtacchrutwaa vachanam keshavasya\nKritaanjalirvepamaanah kireetee;\nNamaskritwaa bhooya evaaha krishnam\nSagadgadam bheetabheetah pranamya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संजय बोले- केशव भगवान के इस वचन को सुनकर मुकुटधारी अर्जुन हाथ जोड़कर काँपते हुए नमस्कार करके, फिर भी अत्यन्त भयभीत होकर प्रणाम करके भगवान श्रीकृष्ण के प्रति गद्‍गद्‍ वाणी से बोले॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે : ભગવાન કેશવના આ વચનો સાંભળી, બે હાથ જોડી, સંભ્રમથી કંપતો, મનમાં અત્યંત ભયભીત થતો અર્જુન નમસ્કાર કરી અત્યંત નમ્ર અને ગદ્દ ગદ્દ કંઠે ફરીથી ભગવાન શ્રી કૃષ્ણને આ પ્રમાણે કહેવા લાગ્યો.(૩૫)");
        } else if (2 == i2) {
            this.tv2.setText("Having heard that speech of Lord Krishna, the crowned one (Arjuna), with joined palms, trembling, prostrating himself, again addressed Krishna, in a choked voice, bowing down, overwhelmed with fear(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36() {
        setTitle("Shlok 36");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nस्थाने हृषीकेश तव प्रकीर्त्या जगत्प्रहृष्यत्यनुरज्यते च ।\nरक्षांसि भीतानि दिशो द्रवन्ति सर्वे नमस्यन्ति च सिद्धसङ्‍घा: ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nસ્થાને હૃષીકેશ તવ પ્રકીર્ત્યાજગત્ પ્રહૃષ્યત્યનુરજ્યતે ચ,\nરક્ષાંસિ ભીતાનિ દિશો દ્રવન્તિસર્વે નમસ્યન્તિ ચ સિદ્ધસઙ્ઘાઃ.(૩૬)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nSthaane hrisheekesha tava prakeertyaa\nJagat prahrishyatyanurajyate cha;\nRakshaamsi bheetaani disho dravanti\nSarve namasyanti cha siddhasanghaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे अन्तर्यामिन्! यह योग्य ही है कि आपके नाम, गुण और प्रभाव के कीर्तन से जगत अति हर्षित हो रहा है और अनुराग को भी प्राप्त हो रहा है तथा भयभीत राक्षस लोग दिशाओं में भाग रहे हैं और सब सिद्धगणों के समुदाय नमस्कार कर रहे हैं॥36॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે ઋષિકેશ ! આપના શ્રવણ અને કીર્તનથી જગત હર્ષ પામે છે અને અનુરાગ પામે છે.રાક્ષસો ભય પામીને સર્વ દિશાઓમાં નાસે છે અને બધા સિદ્ધો ના સમૂહ આપને નમસ્કાર કરે છે તે યોગ્ય છે.(૩૬)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O master of the senses, the world becomes joyful upon hearing Your name, and thus everyone becomes attached to You. Although the perfected beings offer You their respectful homage, the demons are afraid, and they flee here and there. All this is rightly done.(36)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok37() {
        setTitle("Shlok 37");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कस्माच्च ते न नमेरन्महात्मन्‌ गरीयसे ब्रह्मणोऽप्यादिकर्त्रे।\nअनन्त देवेश जगन्निवास त्वमक्षरं सदसत्तत्परं यत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કસ્માચ્ચ તે ન નમેરન્મહાત્મન્ગરીયસે બ્રહ્મણોપ્યાદિકર્ત્રે,\nઅનન્ત દેવેશ જગન્નિવાસત્વમક્ષરં સદસત્તત્પરં યત્.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Kasmaachcha te na nameran mahaatman\nGareeyase brahmano’pyaadikartre;\nAnanta devesha jagannivaasa\nTwamaksharam sadasattatparam yat");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे महात्मन्‌! ब्रह्मा के भी आदिकर्ता और सबसे बड़े आपके लिए वे कैसे नमस्कार न करें क्योंकि हे अनन्त! हे देवेश! हे जगन्निवास! जो सत्‌, असत्‌ और उनसे परे अक्षर अर्थात सच्चिदानन्दघन ब्रह्म है, वह आप ही हैं॥37॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાત્મન ! હે અનંત ! હે દેવેશ ! હે જગનિવાસ  ! બ્રહ્મના પણ આપ ગુરુરૂપ છો. આદિકર્તા તે સર્વ આપને શા માટે નમસ્કાર ન કરે ? આપ સત્ છો, આપ અસત્ છો. આપ તેનાથી ય પર છો. અક્ષર બ્રહ્મ પણ આપ જ છો.(૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("O great one, greater even than Brahma, You are the original creator. Why then should they not offer their respectful obeisances unto You? O limitless one, God of gods, refuge of the universe! You are the invincible source, the cause of all causes, transcendental to this material manifestation.(37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        setTitle("Shlok 38");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्वमादिदेवः पुरुषः पुराणस्त्वमस्य विश्वस्य परं निधानम्‌ ।\nवेत्तासि वेद्यं च परं च धाम त्वया ततं विश्वमनन्तरूप । ।");
        } else if (i == 0) {
            this.tv1.setText("ત્વમાદિદેવઃ \nપુરુષઃ પુરાણ-સ્ત્વમસ્ય વિશ્વસ્ય પરં નિધાનમ્,\nવેત્તાસિ વેદ્યં ચ પરં ચ ધામત્વયા તતં વિશ્વમનન્તરૂપ.(૩૮)");
        } else if (2 == i) {
            this.tv1.setText("Twamaadidevah purushah puraanas\nTwamasya vishwasya param nidhaanam;\nVettaasi vedyam cha param cha dhaama\nTwayaa tatam vishwamanantaroopa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आप आदिदेव और सनातन पुरुष हैं, आप इन जगत के परम आश्रय और जानने वाले तथा जानने योग्य और परम धाम हैं। हे अनन्तरूप! आपसे यह सब जगत व्याप्त अर्थात परिपूर्ण हैं॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અનંતરૂપ ! હે આદિદેવ ! આપ જ પુરાણપુરુષ  છો.આપ આ વિશ્વના લયસ્થાન રૂપ છો.આપ જ્ઞાતા છો, અને જ્ઞેય છો અને આપ જ પરમ ધામ છો.(૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("You are the original Personality of Godhead, the oldest, the ultimate sanctuary of this manifested cosmic world. You are the knower of everything, and You are all that is knowable. You are the supreme refuge, above the material modes. O limitless form! This whole cosmic manifestation is pervaded by You!(38)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        setTitle("Shlok 39");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वायुर्यमोऽग्निर्वरुणः शशाङ्‍क: प्रजापतिस्त्वं प्रपितामहश्च।\nनमो नमस्तेऽस्तु सहस्रकृत्वः पुनश्च भूयोऽपि नमो नमस्ते ॥");
        } else if (i == 0) {
            this.tv1.setText("વાયુર્યમોગ્નિર્વરુણઃ શશાઙ્કઃપ્રજાપતિસ્ત્વં પ્રપિતામહશ્ચ,\nનમો નમસ્તેસ્તુ સહસ્રકૃત્વઃપુનશ્ચ ભૂયોપિ નમો નમસ્તે.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Vaayuryamo’gnirvarunah shashaankah\nPrajaapatistwam prapitaamahashcha;\nNamo namaste’stu sahasrakritwah\nPunashcha bhooyo’pi namo namaste.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आप वायु, यमराज, अग्नि, वरुण, चन्द्रमा, प्रजा के स्वामी ब्रह्मा और ब्रह्मा के भी पिता हैं। आपके लिए हजारों बार नमस्कार! नमस्कार हो!! आपके लिए फिर भी बार-बार नमस्कार! नमस्कार!!॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("વાયુ, યમ, અગ્નિ, વરુણ, ચંદ્ર, કશ્ય પાદિ પ્રજાપતિ અને બ્રહ્મદેવના જનક પણ આપ જ છો.આપને હજારો વાર નમસ્કાર હો.અને વારંવાર નમસ્કાર હો.(૩૯)");
        } else if (2 == i2) {
            this.tv2.setText("You are air, and You are the supreme controller! You are fire, You are water, and You are the moon! You are Brahma, the first living creature, and You are the great-grandfather. I therefore offer my respectful obeisances unto You a thousand times, and again and yet again!(39)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        setTitle("Shlok 40");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नमः पुरस्तादथ पृष्ठतस्ते नमोऽस्तु ते सर्वत एव सर्व। \nअनन्तवीर्यामितविक्रमस्त्वंसर्वं समाप्नोषि ततोऽसि सर्वः ॥");
        } else if (i == 0) {
            this.tv1.setText("નમઃ પુરસ્તાદથ પૃષ્ઠતસ્તેનમોસ્તુ તે સર્વત એવ સર્વ,\nઅનન્તવીર્યામિતવિક્રમસ્ત્વંસર્વં સમાપ્નોષિ તતોસિ સર્વઃ.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Namah purastaadatha prishthataste\nNamo’stu te sarvata eva sarva;\nAnantaveeryaamitavikramastwam\nSarvam samaapnoshi tato’si sarvah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अनन्त सामर्थ्यवाले! आपके लिए आगे से और पीछे से भी नमस्कार! हे सर्वात्मन्‌! आपके लिए सब ओर से ही नमस्कार हो, क्योंकि अनन्त पराक्रमशाली आप समस्त संसार को व्याप्त किए हुए हैं, इससे आप ही सर्वरूप हैं॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે સર્વરૂપ પરમેશ્વર ! આપને સામેથી, પાછળથી, સર્વ તરફથી નમસ્કાર હો.આપના બળ અને પરાક્રમ અપાર છે. આપનાથી  આ સંપૂર્ણ જગત વ્યાપ્ત છે.તો પછી આપ જ સર્વ સ્વરૂપ છો.(૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("Obeisances to You from the front, from behind and from all sides! O unbounded power, You are the master of limitless might! You are all-pervading, and thus You are everything!(40)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41_42() {
        setTitle("Shlok 41,42");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सखेति मत्वा प्रसभं यदुक्तं हे कृष्ण हे यादव हे सखेति।\nअजानता महिमानं तवेदंमया प्रमादात्प्रणयेन वापि ॥\nयच्चावहासार्थमसत्कृतोऽसि विहारशय्यासनभोजनेषु ।\nएकोऽथवाप्यच्युत तत्समक्षंतत्क्षामये त्वामहमप्रमेयम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સખેતિ મત્વા પ્રસભં યદુક્તંહે કૃષ્ણ હે યાદવ હે સખેતિ,\nઅજાનતા મહિમાનં તવેદંમયા પ્રમાદાત્પ્રણયેન વાપિ.(૪૧)\nયચ્ચાવહાસાર્થમસત્કૃતોસિવિહારશય્યાસનભોજનેષુ,\nએકોથવાપ્યચ્યુત તત્સમક્ષંતત્ક્ષામયે ત્વામહમપ્રમેયમ્.(૪૨)");
        } else if (2 == i) {
            this.tv1.setText("Sakheti matwaa prasabham yaduktam\nHe krishna he yaadava he sakheti;\nAjaanataa mahimaanam tavedam\nMayaa pramaadaat pranayena vaapi.\nYachchaavahaasaartham asatkrito’si\nVihaarashayyaasanabhojaneshu;\nEko’thavaapyachyuta tatsamaksham\nTatkshaamaye twaamaham aprameyam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आपके इस प्रभाव को न जानते हुए, आप मेरे सखा हैं ऐसा मानकर प्रेम से अथवा प्रमाद से भी मैंने 'हे कृष्ण!', 'हे यादव !' 'हे सखे!' इस प्रकार जो कुछ बिना सोचे-समझे हठात्‌ कहा है और हे अच्युत! आप जो मेरे द्वारा विनोद के लिए विहार, शय्या, आसन और भोजनादि में अकेले अथवा उन सखाओं के सामने भी अपमानित किए गए हैं- वह सब अपराध अप्रमेयस्वरूप अर्थात अचिन्त्य प्रभाव वाले आपसे मैं क्षमा करवाता हूँ॥41-42॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે વિભુ ! આપના આ મહિમાને ન જાણનારા મેં, આપ મારા મિત્ર છો એમ માની ને ચિત્તની ચંચળતાથી અથવા પ્રેમવશ હે કૃષ્ણ ! હે યાદવ ! હે સખા ! એ પ્રમાણે હઠપૂર્વક જે કંઈ કહ્યું હોય તે સર્વ પાપ મને ક્ષમા કરો. હે અચુય્ત ! પરિહાસથી, વિહારમાં, સૂતાં, બેસતાં, ખાતાં-પીતાં, એકલા અથવા કદાચિત મિત્રોની સમક્ષ વિનોદાર્થે મેં આપનું જે કંઈ અપમાન કર્યું હોય તે બધા માટે અચિંત્ય પ્રભાવવાળા આપ મને ક્ષમા કરો.(૪૧-૪૨)");
        } else if (2 == i2) {
            this.tv2.setText("Thinking of You as my friend, I have rashly addressed You “O Krishna,” “O Yadava,” “O my friend,” not knowing Your glories. Please forgive whatever I may have done in madness or in love. I have dishonored You many times, jesting as we relaxed, lay on the same bed, or sat or ate together, sometimes alone and sometimes in front of many friends. O infallible one, please excuse me for all those offenses.(41-42)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok43() {
        setTitle("Shlok 43");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पितासि लोकस्य चराचरस्य त्वमस्य पूज्यश्च गुरुर्गरीयान्‌।\nन त्वत्समोऽस्त्यभ्यधिकः कुतोऽन्योलोकत्रयेऽप्यप्रतिमप्रभाव ॥");
        } else if (i == 0) {
            this.tv1.setText("પિતાસિ લોકસ્ય ચરાચરસ્યત્વમસ્ય પૂજ્યશ્ચ ગુરુર્ગરીયાન્। \nન ત્વત્સમોસ્ત્યભ્યધિકઃ કુતોન્યોલોકત્રયેપ્ય પ્રતિમપ્રભાવ.(૪૩)");
        } else if (2 == i) {
            this.tv1.setText("Pitaasi lokasya charaacharasya\nTwamasya poojyashcha gururgareeyaan;\nNa twatsamo’styabhyadhikah kuto’nyo\nLokatraye’pyapratimaprabhaava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आप इस चराचर जगत के पिता और सबसे बड़े गुरु एवं अति पूजनीय हैं। हे अनुपम प्रभाववाले! तीनों लोकों में आपके समान भी दूसरा कोई नहीं हैं, फिर अधिक तो कैसे हो सकता है॥43॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અનુપમ પ્રભાવ વાળા ! આપ આ ચરાચર જગત ના પિતા છો, પૂજ્ય પરમગુરુ છો. અધિક ગૌરવ વાળા છો. ત્રણે લોકમાં આપના સમાન બીજો કોઈ નથી.તો આપના થી અધિક તો ક્યાંથી હોય ? (૪૩)");
        } else if (2 == i2) {
            this.tv2.setText("You are the father of this complete cosmic manifestation, of the moving and the nonmoving. You are its worshipable chief, the supreme spiritual master. No one is equal to You, nor can anyone be one with You. How then could there be anyone greater than You within the three worlds, O Lord of immeasurable power?(43)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok44() {
        setTitle("Shlok 44");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मात्प्रणम्य प्रणिधाय कायंप्रसादये त्वामहमीशमीड्यम्‌।\nपितेव पुत्रस्य सखेव सख्युः प्रियः प्रियायार्हसि देव सोढुम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માત્પ્રણમ્ય પ્રણિધાય કાયંપ્રસાદયે ત્વામહમીશમીડ્યમ્,\nપિતેવ પુત્રસ્ય સખેવ સખ્યુઃપ્રિયઃ પ્રિયાયાર્હસિ દેવ સોઢુમ્.(૪૪)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaatpranamya pranidhaaya kaayam\nPrasaadaye twaamahameeshameedyam;\nPiteva putrasya sakheva sakhyuh\nPriyah priyaayaarhasi deva sodhum.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अतएव हे प्रभो! मैं शरीर को भलीभाँति चरणों में निवेदित कर, प्रणाम करके, स्तुति करने योग्य आप ईश्वर को प्रसन्न होने के लिए प्रार्थना करता हूँ। हे देव! पिता जैसे पुत्र के, सखा जैसे सखा के और पति जैसे प्रियतमा पत्नी के अपराध सहन करते हैं- वैसे ही आप भी मेरे अपराध को सहन करने योग्य हैं। ॥44॥");
        } else if (i2 == 0) {
            this.tv2.setText("એટલા માટે હે ભગવન્  ! હું સાષ્ટાંગ પ્રણામ કરી ને સ્તુતિ કરવા યોગ્ય અને સમર્થ એવા આપને પ્રસન્ન કરવા માટે પ્રાર્થના કરું છું.જેમ પિતા પુત્રના અપરાધ, મિત્ર મિત્રના અપરાધ અને પુરુષ પોતાની પ્રિયાના અપરાધ સહન કરે છે, તેમ આપ મારા અપરાધ સહન કરવા યોગ્ય છો.(૪૪)  ");
        } else if (2 == i2) {
            this.tv2.setText("You are the Supreme Lord, to be worshiped by every living being. Thus I fall down to offer You my respectful obeisances and ask Your mercy. As a father tolerates the impudence of his son, or a friend tolerates the impertinence of a friend, or a wife tolerates the familiarity of her partner, please tolerate the wrongs I may have done You.(44)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok45() {
        setTitle("Shlok 45");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अदृष्टपूर्वं हृषितोऽस्मि दृष्ट्वा भयेन च प्रव्यथितं मनो मे।\nतदेव मे दर्शय देवरूपंप्रसीद देवेश जगन्निवास ॥");
        } else if (i == 0) {
            this.tv1.setText("અદૃષ્ટપૂર્વં હૃષિતોસ્મિ દૃષ્ટ્વાભયેન ચ પ્રવ્યથિતં મનો મે,\nતદેવ મે દર્શય દેવ રૂપંપ્રસીદ દેવેશ જગન્નિવાસ.(૪૫)");
        } else if (2 == i) {
            this.tv1.setText("Adrishtapoorvam hrishito’smi drishtwaa\nBhayena cha pravyathitam mano me;\nTadeva me darshaya deva roopam\nPraseeda devesha jagannivaasa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं पहले न देखे हुए आपके इस आश्चर्यमय रूप को देखकर हर्षित हो रहा हूँ और मेरा मन भय से अति व्याकुल भी हो रहा है, इसलिए आप उस अपने चतुर्भुज विष्णु रूप को ही मुझे दिखलाइए। हे देवेश! हे जगन्निवास! प्रसन्न होइए॥45॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે દેવેશ ! હે જગ નિવાસ ! પહેલાં કદી ન જોયેલાં એવા આપના દિવ્ય વિશ્વરૂપને જોઈને મને હર્ષ થયો છે અને ભયથી મારું ચિત્ત અતિ વ્યાકુળ થયું છે. માટે હે દેવ આપ પ્રસન્ન થાઓ અને મને આપનું પહેલાં નું મનુષ્ય સ્વરૂપ દેખાડો.(૪૫)");
        } else if (2 == i2) {
            this.tv2.setText("After seeing this universal form, which I have never seen before, I am gladdened, but at the same time my mind is disturbed with fear. Therefore please bestow Your grace upon me and reveal again Your form as the Personality of Godhead, O Lord of lords, O abode of the universe.(45)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok46() {
        setTitle("Shlok 46");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("किरीटिनं गदिनं चक्रहस्तमिच्छामि त्वां द्रष्टुमहं तथैव।\nतेनैव रूपेण चतुर्भुजेनसहस्रबाहो भव विश्वमूर्ते॥");
        } else if (i == 0) {
            this.tv1.setText("કિરીટિનં ગદિનં ચક્રહસ્ત-મિચ્છામિ ત્વાં દ્રષ્ટુમહં તથૈવ,\nતેનૈવ રૂપેણ ચતુર્ભુજેનસહસ્રબાહો ભવ વિશ્વમૂર્તે.(૪૬)");
        } else if (2 == i) {
            this.tv1.setText("Kireetinam gadinam chakrahastam\nIcchaami twaam drashtumaham tathaiva;\nTenaiva roopena chaturbhujena\nSahasrabaaho bhava vishwamoorte");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं वैसे ही आपको मुकुट धारण किए हुए तथा गदा और चक्र हाथ में लिए हुए देखना चाहता हूँ। इसलिए हे विश्वस्वरूप! हे सहस्रबाहो! आप उसी चतुर्भुज रूप से प्रकट होइए॥46॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે હજારભુજાવાળા ! હે વિશ્વમૂર્તિ  ! આપને મુકુટધારી, હાથમાં ગદા- ચક્ર  ધારણ કરેલા જોવાની મારી ઈચ્છા છે.  માટે આપ પહેલાં ની જેમ ચતુર્ભુજ સ્વરૂપ વાળા થવાની કૃપા કરો.(૪૬)");
        } else if (2 == i2) {
            this.tv2.setText("O universal form, O thousand-armed Lord, I wish to see You in Your four-armed form, with helmeted head and with club, wheel, conch and lotus flower in Your hands. I long to see You in that form.(46)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok47() {
        setTitle("Shlok 47");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nमया प्रसन्नेन तवार्जुनेदंरूपं परं दर्शितमात्मयोगात्‌ ।\nतेजोमयं विश्वमनन्तमाद्यंयन्मे त्वदन्येन न दृष्टपूर्वम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nમયા પ્રસન્નેન તવાર્જુનેદંરૂપં પરં દર્શિતમાત્મયોગાત્,\nતેજોમયં વિશ્વમનન્તમાદ્યંયન્મે ત્વદન્યેન ન દૃષ્ટપૂર્વમ્.(૪૭)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nMayaa prasannena tavaarjunedam\nRoopam param darshitamaatmayogaat;\nTejomayam vishwamanantamaadyam\nYanme twadanyena na drishtapoorvam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे अर्जुन! अनुग्रहपूर्वक मैंने अपनी योगशक्ति के प्रभाव से यह मेरे परम तेजोमय, सबका आदि और सीमारहित विराट् रूप तुझको दिखाया है, जिसे तेरे अतिरिक्त दूसरे किसी ने पहले नहीं देखा था॥47॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : હે અર્જુન ! તારા  પર  પ્રસન્ન થઈને  મેં મારા આત્મયોગના સામર્થ્ય થી તને મારું આ પરમ તેજોમય, સમસ્ત, વિશ્વરૂપ , અનંત, અનાદિ એવું આ શ્રેષ્ઠરૂપ દેખાડ્યું છે. મારું આ રૂપ પહેલાં કોઈએ નિહાળ્યું નથી .(૪૭)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: My dear Arjuna, happily have I shown you, by My internal potency, this supreme universal form within the material world. No one before you has ever seen this primal form, unlimited and full of glaring effulgence.(47)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok48() {
        setTitle("Shlok 48");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न वेदयज्ञाध्ययनैर्न दानैर्न च क्रियाभिर्न तपोभिरुग्रैः।\nएवं रूपः शक्य अहं नृलोके द्रष्टुं त्वदन्येन कुरुप्रवीर ॥");
        } else if (i == 0) {
            this.tv1.setText("ન વેદયજ્ઞાધ્યયનૈર્ન દાનૈ-ર્ન ચ ક્રિયાભિર્ન તપોભિરુગ્રૈઃ,\nએવંરૂપઃ શક્ય અહં નૃલોકેદ્રષ્ટું ત્વદન્યેન કુરુપ્રવીર.(૪૮)");
        } else if (2 == i) {
            this.tv1.setText("Na vedayajnaadhyayanairna daanair\nNa cha kriyaabhirna tapobhirugraih;\nEvam roopah shakya aham nriloke\nDrashtum twadanyena karupraveera.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! मनुष्य लोक में इस प्रकार विश्व रूप वाला मैं न वेद और यज्ञों के अध्ययन से, न दान से, न क्रियाओं से और न उग्र तपों से ही तेरे अतिरिक्त दूसरे द्वारा देखा जा सकता हूँ।48॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કુરુશ્રેષ્ઠ ! વેદોના તથા યજ્ઞોના પ્રભાવથી, દાન વડે, ક્રિયા કર્મ વડે  અથવા ઉગ્ર તપસ્યા વડે મારું આ વિશ્વરૂપ આ મનુષ્યલોકમાં કોઈને મેં કદી પણ દેખાડ્યું નથી.કેવળ તું જ આ સ્વરૂપ જોઈ શક્યો.(૪૮)");
        } else if (2 == i2) {
            this.tv2.setText("O best of the Kuru warriors, no one before you has ever seen this universal form of Mine, for neither by studying the Vedas, nor by performing sacrifices, nor by charity, nor by pious activities, nor by severe penances can I be seen in this form in the material world.(48)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok49() {
        setTitle("Shlok 49");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मा ते व्यथा मा च विमूढभावोदृष्ट्वा रूपं घोरमीदृङ्‍ममेदम्‌।\nव्यतेपभीः प्रीतमनाः पुनस्त्वंतदेव मे रूपमिदं प्रपश्य ॥");
        } else if (i == 0) {
            this.tv1.setText("મા તે વ્યથા મા ચ વિમૂઢભાવોદૃષ્ટ્વા રૂપં ઘોરમીદૃઙ્મમેદમ્,\nવ્યપેતભીઃ પ્રીતમનાઃ પુનસ્ત્વંતદેવ મે રૂપમિદં પ્રપશ્ય.(૪૯)");
        } else if (2 == i) {
            this.tv1.setText("Maa te vyathaa maa cha vimoodhabhaavo\nDrishtwaa roopam ghorameedringmamedam;\nVyapetabheeh preetamanaah punastwam\nTadeva me roopamidam prapashya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मेरे इस प्रकार के इस विकराल रूप को देखकर तुझको व्याकुलता नहीं होनी चाहिए और मूढ़भाव भी नहीं होना चाहिए। तू भयरहित और प्रीतियुक्त मनवाला होकर उसी मेरे इस शंख-चक्र-गदा-पद्मयुक्त चतुर्भुज रूप को फिर देख॥49॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારા આ પ્રકારના આ ધોર સ્વરૂપને જોઈને તું વ્યથિત ન થા.અને વ્યાકુળ પણ ન થા.તું ફરી ભય રહિત અને પ્રસન્ન ચિત્તવાળો થઈને મારું પહેલાંનું જ ચતુર્ભુજ સ્વરૂપ નીહાળ.(૪૯)");
        } else if (2 == i2) {
            this.tv2.setText("You have been perturbed and bewildered by seeing this horrible feature of Mine. Now let it be ﬁnished. My devotee, be free again from all disturbances. With a peaceful mind you can now see the form you desire.(49)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok50() {
        setTitle("Shlok 50");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nइत्यर्जुनं वासुदेवस्तथोक्त्वा स्वकं रूपं दर्शयामास भूयः ।\nआश्वासयामास च भीतमेनंभूत्वा पुनः सौम्यवपुर्महात्मा ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજય ઉવાચ-\nઇત્યર્જુનં વાસુદેવસ્તથોક્ત્વાસ્વકં રૂપં દર્શયામાસ ભૂયઃ,\nઆશ્વાસયામાસ ચ ભીતમેનંભૂત્વા પુનઃ સૌમ્યવપુર્મહાત્મા.(૫૦)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nItyarjunam vaasudevastathoktwaa\nSwakam roopam darshayaamaasa bhooyah;\nAashwaasayaamaasa cha bheetamenam\nBhootwaa punah saumyavapurmahaatmaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संजय बोले- वासुदेव भगवान ने अर्जुन के प्रति इस प्रकार कहकर फिर वैसे ही अपने चतुर्भुज रूप को दिखाया और फिर महात्मा श्रीकृष्ण ने सौम्यमूर्ति होकर इस भयभीत अर्जुन को धीरज दिया॥50॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે - આમ વાસુદેવ પોતાના પરમ ભક્ત અર્જુનને આ પ્રમાણે કહીને ફરી પોતાનું પૂર્વે હતું તે શરીર ધારણ કરી બતાવ્યું. આમ સૌમ્ય દેહવાળા ભગવાને પોતાના ભય પામેલા ભક્ત અર્જુનને આશ્વાસન આપ્યું.(૫૦)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said to Dhritarashtra: The Supreme Personality of Godhead, Krishna, having spoken thus to Arjuna, displayed His real four-armed form and at last showed His two-armed form, thus encouraging the fearful Arjuna.(50)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok51() {
        setTitle("Shlok 51");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nदृष्ट्वेदं मानुषं रूपं तव सौम्यं जनार्दन।\nइदानीमस्मि संवृत्तः सचेताः प्रकृतिं गतः॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nદૃષ્ટ્વેદં માનુષં રૂપં તવસૌમ્યં જનાર્દન,\nઇદાનીમસ્મિ સંવૃત્તઃ સચેતાઃ પ્રકૃતિં ગતઃ.(૫૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nDrishtwedam maanusham roopam tava saumyam janaardana;\nIdaaneemasmi samvrittah sachetaah prakritim gatah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे जनार्दन! आपके इस अतिशांत मनुष्य रूप को देखकर अब मैं स्थिरचित्त हो गया हूँ और अपनी स्वाभाविक स्थिति को प्राप्त हो गया हूँ॥51॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે જનાર્દન ! આપના આ સૌમ્ય મનુષ્યરૂપ ને જોઈને હવે હું પ્રસન્ન ચિત્તવાળો થયો છું તથા મારું મન પહેલાં જેવું સ્વસ્થ બની ગયું છે.(૫૧)");
        } else if (2 == i2) {
            this.tv2.setText("When Arjuna thus saw Krishna in His original form, he said: O Janardana, seeing this humanlike form, so very beautiful, I am now composed in mind, and I am restored to my original nature.(51)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok52() {
        setTitle("Shlok 52");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nसुदुर्दर्शमिदं रूपं दृष्टवानसि यन्मम।\nदेवा अप्यस्य रूपस्य नित्यं दर्शनकाङ्‍क्षिणः॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nસુદુર્દર્શમિદં રૂપં દૃષ્ટવાનસિ યન્મમ,\nદેવા અપ્યસ્ય રૂપસ્ય નિત્યં દર્શનકાઙ્ક્ષિણઃ.(૫૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nSudurdarshamidam roopam drishtavaanasi yanmama;\nDevaa apyasya roopasya nityam darshanakaangkshinah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- मेरा जो चतुर्भज रूप तुमने देखा है, वह सुदुर्दर्श है अर्थात्‌ इसके दर्शन बड़े ही दुर्लभ हैं। देवता भी सदा इस रूप के दर्शन की आकांक्षा करते रहते हैं॥52॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે - મારું જે  વિરાટ સ્વરૂપ તેં હમણાં જોયું તે રૂપ જોવાનું અત્યંત દુર્લભ છે. દેવો પણ નિરંતર આ રૂપનાં દર્શન કરવાની ઈચ્છા રાખે છે.(૫૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: My dear Arjuna, this form of Mine you are nowseeing is very difficult to behold. Even the demigods are ever seeking the opportunity to see this form, which is so dear.(52)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok53() {
        setTitle("Shlok 53");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नाहं वेदैर्न तपसा न दानेन न चेज्यया।\nशक्य एवं विधो द्रष्टुं दृष्ट्वानसि मां यथा ॥");
        } else if (i == 0) {
            this.tv1.setText("નાહં વેદૈર્ન તપસા ન દાનેન ન ચેજ્યયા,\nશક્ય એવંવિધો દ્રષ્ટું દૃષ્ટવાનસિ માં યથા.(૫૩)");
        } else if (2 == i) {
            this.tv1.setText("Naa ham vedairna tapasaa na daanena na chejyayaa;\nShakya evamvidho drashtum drishtavaanasi maam yathaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस प्रकार तुमने मुझको देखा है- इस प्रकार चतुर्भुज रूप वाला मैं न वेदों से, न तप से, न दान से और न यज्ञ से ही देखा जा सकता हूँ॥53॥");
        } else if (i2 == 0) {
            this.tv2.setText("તેં જે સ્વરૂપ વાળો હમણાં મને  જોયો તે સ્વરૂપવાળો હું વેદ્શાસ્ત્રના અધ્યયનથી, ચન્દ્રાયણાદિ તાપથી, દાનથી અને યજ્ઞો થી પણ શક્ય નથી.(૫૩)  ");
        } else if (2 == i2) {
            this.tv2.setText("The form you are seeing with your transcendental eyes cannot be understood simply by studying the Vedas, nor by undergoing serious penances, nor by charity, nor by worship. It is not by these means that one can see Me as I am.(53)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok54() {
        setTitle("Shlok 54");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भक्त्या त्वनन्यया शक्य अहमेवंविधोऽर्जुन ।\nज्ञातुं द्रष्टुं च तत्वेन प्रवेष्टुं च परन्तप ॥");
        } else if (i == 0) {
            this.tv1.setText("ભક્ત્યા ત્વનન્યયા શક્યમહમેવંવિધોર્જુન,\nજ્ઞાતું દૃષ્ટું ચ તત્ત્વેન પ્રવેષ્ટું ચ પરંતપ.(૫૪)");
        } else if (2 == i) {
            this.tv1.setText("Bhaktyaa twananyayaa shakyam aham evamvidho’rjuna;\nJnaatum drashtum cha tattwena praveshtum cha parantapa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु हे परंतप अर्जुन! अनन्य भक्ति (अनन्यभक्ति का भाव अगले श्लोक में विस्तारपूर्वक कहा है।) के द्वारा इस प्रकार चतुर्भुज रूपवाला मैं प्रत्यक्ष देखने के लिए, तत्व से जानने के लिए तथा प्रवेश करने के लिए अर्थात एकीभाव से प्राप्त होने के लिए भी शक्य हूँ॥54॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરંતપ ! હે અર્જુન ! મારા વિશ્વરૂપને ખરેખર જાણવાનું, જોવાનું અને તદ્રુપ થવાનું એક માત્ર સાધન કેવળ અનન્ય ભક્તિ જ છે.(૫૪)");
        } else if (2 == i2) {
            this.tv2.setText("My dear Arjuna, only by undivided devotional service can I be understood as I am, standing before you, and can thus be seen directly. Only in this way can you enter into the mysteries of My understanding.(54)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok55() {
        setTitle("Shlok 55");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मत्कर्मकृन्मत्परमो मद्भक्तः सङ्‍गवर्जितः ।\nनिर्वैरः सर्वभूतेषु यः स मामेति पाण्डव ॥");
        } else if (i == 0) {
            this.tv1.setText("મત્કર્મકૃન્મત્પરમો મદ્ભક્તઃ સઙ્ગવર્જિતઃ,\nનિર્વૈરઃ સર્વભૂતેષુ યઃ સ મામેતિ પાણ્ડવ.(૫૫)");
        } else if (2 == i) {
            this.tv1.setText("Matkarmakrinmatparamo madbhaktah sangavarjitah;\nNirvairah sarvabhooteshu yah sa maameti paandava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जो पुरुष केवल मेरे ही लिए सम्पूर्ण कर्तव्य कर्मों को करने वाला है, मेरे परायण है, मेरा भक्त है, आसक्तिरहित है और सम्पूर्ण भूतप्राणियों में वैरभाव से रहित है (सर्वत्र भगवद्बुद्धि हो जाने से उस पुरुष का अति अपराध करने वाले में भी वैरभाव नहीं होता है, फिर औरों में तो कहना ही क्या है), वह अनन्यभक्तियुक्त पुरुष मुझको ही प्राप्त होता है॥55॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાંડવ ! મને જે પ્રાપ્ત કરવાના ઉદેશથી કર્મ કરનાર, મને જ સર્વસ્વ માનનાર, ઉપાધિરહિત અને સર્વ ભૂતોમાં જે વેર રહિત છે તે જ મારો ભક્ત છે.અને તે જ મને પામે છે.(૫૫)  ");
        } else if (2 == i2) {
            this.tv2.setText("My dear Arjuna, he who engages in My pure devotional service, free from the contaminations of fruitive activities and mental speculation, he who works for Me, who makes Me the supreme goal of his life, and who is friendly to every living being—he certainly comes to Me.(55)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay11.this, ShlokListPlay11.this);
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

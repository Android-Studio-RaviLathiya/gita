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

public class ShlokListPlay8 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
    Button btn_slock, btn_slocktras
            , btn_slock_silent, btn_slocktras_silent;
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
                if (ShlokListPlay8.this.favflag == 0) {
                    ShlokListPlay8.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.favflag = 1;
                    shlokListPlay8.save();
                } else if (1 == ShlokListPlay8.this.favflag) {
                    ShlokListPlay8.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay8 shlokListPlay82 = ShlokListPlay8.this;
                    shlokListPlay82.favflag = 0;
                    shlokListPlay82.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay8.this.calll++;
                ShlokListPlay8.this.slkset();
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
                ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                shlokListPlay8.calll--;
                ShlokListPlay8.this.slkset();
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
        edit.putString("A8" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A8" + this.calll, "0");
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
        if (28 == this.calll) {
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
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.callf1 = 0;
                    shlokListPlay8.slkset();
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
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.callf1 = 2;
                    shlokListPlay8.slkset();
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
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.callf1 = 1;
                    shlokListPlay8.slkset();
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
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.callf2 = 0;
                    shlokListPlay8.slkset();
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
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.callf2 = 2;
                    shlokListPlay8.slkset();
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
                    ShlokListPlay8 shlokListPlay8 = ShlokListPlay8.this;
                    shlokListPlay8.callf2 = 1;
                    shlokListPlay8.slkset();
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
            shlok12_13();
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
            shlok26();
        } else if (26 == i3) {
            shlok27();
        } else if (27 == i3) {
            shlok28();
        } else if (28 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 8");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ०८ \nअक्षरब्रह्मयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૮ \nઅક્ષર બ્રહ્મ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 08\nakshar brahma yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, भक्तिपूर्वक भगवान कृष्ण का आजीवन स्मरण करते रहने से और विशेषतया मृत्यु के समय ऐसा करने से मनुष्य परम धाम को प्राप्त कर सकता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૮ માં બ્રહ્મ, અધ્યાત્મ, કર્મ, અધિભૂત, અધિદૈવ, અધિ યજ્ઞ ની વ્યાખ્યા આપી સમજાવ્યું છે. વળી મરણ સમયે પરમાત્મા નું સ્મરણ કરતાં કરતાં શરીર છોડવું તે બતાવેલ છે.");
        } else if (2 == i) {
            this.tv1.setText("Eight adhyay tells, By remembering Lord Krishna in devotion throughout one's life, and especially at the time of death, one can attain to His supreme abode, beyond the material world.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nकिं तद्ब्रह्म किमध्यात्मं किं पुरुषोत्तम ।\nअधिभूतं च किं प्रोक्तमधिदैवं किमुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nકિં તદ્બ્રહ્મ કિમધ્યાત્મં કિં કર્મ પુરુષોત્તમ,\nઅધિભૂતં ચ કિં પ્રોક્તમધિદૈવં કિમુચ્યતે.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nKim tadbrahma kim adhyaatmam kim karma purushottama;\nAdhibhootam cha kim proktam adhidaivam kimuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन ने कहा- हे पुरुषोत्तम! वह ब्रह्म क्या है? अध्यात्म क्या है? कर्म क्या है? अधिभूत नाम से क्या कहा गया है और अधिदैव किसको कहते हैं॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે પુરુષોત્તમ ! બ્રહ્મ એટલે શું? અધ્યાત્મ એટલે શું? કર્મ એટલે શું? અધિભૂત શાને કહે છે? અને અધિદૈવ કોને કહે છે?(૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna inquired: O my Lord, O Supreme Person, what is Brahman? What is the self? What are fruitive activities? What is this material manifestation? And what are the demigods? Please explain this to me.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अधियज्ञः कथं कोऽत्र देहेऽस्मिन्मधुसूदन ।\nप्रयाणकाले च कथं ज्ञेयोऽसि नियतात्मभिः ॥");
        } else if (i == 0) {
            this.tv1.setText("અધિયજ્ઞઃ કથં કોત્ર દેહેસ્મિન્મધુસૂદન,\nપ્રયાણકાલે ચ કથં જ્ઞેયોસિ નિયતાત્મભિઃ.(૨) ");
        } else if (2 == i) {
            this.tv1.setText("Adhiyajnah katham ko’tra dehe’smin madhusoodana;\nPrayaanakaale cha katham jneyo’si niyataatmabhih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे मधुसूदन! यहाँ अधियज्ञ कौन है? और वह इस शरीर में कैसे है? तथा युक्त चित्त वाले पुरुषों द्वारा अंत समय में आप किस प्रकार जानने में आते हैं॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મધુ સુદન ! આ દેહ માં અધિયજ્ઞ કોણ છે ? તે કેવો છે ? જેણે અંત: કરણને જીતી લીધુછે , એવો યોગી મરણ સમયે તમને કેવીરીતે જાણે છે ? (૨)");
        } else if (2 == i2) {
            this.tv2.setText("Who is the Lord of sacrifice, and how does He live in the body, O Madhusudana? And how can those engaged in devotional service know You at the time of death?(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nअक्षरं ब्रह्म परमं स्वभावोऽध्यात्ममुच्यते ।\nभूतभावोद्भवकरो विसर्गः कर्मसंज्ञितः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઅક્ષરં બ્રહ્મ પરમં સ્વભાવોધ્યાત્મમુચ્યતે,\nભૂતભાવોદ્ભવકરો વિસર્ગઃ કર્મસંજ્ઞિતઃ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nAksharam brahma paramam swabhaavo’dhyaatmamuchyate;\nBhootabhaavodbhavakaro visargah karmasamjnitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान ने कहा- परम अक्षर 'ब्रह्म' है, अपना स्वरूप अर्थात जीवात्मा 'अध्यात्म' नाम से कहा जाता है तथा भूतों के भाव को उत्पन्न करने वाला जो त्याग है, वह 'कर्म' नाम से कहा गया है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે છે : બ્રહ્મ અવિનાશી અને સર્વ શ્રેષ્ઠ છે. તેનો સ્વ-ભાવ અધ્યાત્મ છે. પ્રાણીની ઉત્પતિ ને લીધે જે વિસર્ગ, દેવોને ઉદ્દેશી યજ્ઞમાં કરેલું દ્રવ્યપ્રદાન, તેને  કર્મ કહે છે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: The indestructible, transcendental living entity is called Brahman, and his eternal nature is called adhyatma, the self. Action pertaining to the development of the material bodies of the living entities is called karma, or fruitive activities.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अधिभूतं क्षरो भावः पुरुषश्चाधिदैवतम्‌ ।\nअधियज्ञोऽहमेवात्र देहे देहभृतां वर ॥");
        } else if (i == 0) {
            this.tv1.setText("અધિભૂતં ક્ષરો ભાવઃ પુરુષશ્ચાધિદૈવતમ્,\nઅધિયજ્ઞોહમેવાત્ર દેહે દેહભૃતાં વર.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Adhibhootam ksharo bhaavah purushashchaadhidaivatam;\nAdhiyajno’hamevaatra dehe dehabhritaam vara.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उत्पत्ति-विनाश धर्म वाले सब पदार्थ अधिभूत हैं, हिरण्यमय पुरुष (जिसको शास्त्रों में सूत्रात्मा, हिरण्यगर्भ, प्रजापति, ब्रह्मा इत्यादि नामों से कहा गया है) अधिदैव है और हे देहधारियों में श्रेष्ठ अर्जुन! इस शरीर में मैं वासुदेव ही अन्तर्यामी रूप से अधियज्ञ हूँ॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે નરશ્રેષ્ઠ ! જે નાશવંત  પદાર્થો છે તે અધિભૂત છે. પુરુષ ( ચૈતન્ય અધિષ્ઠાતા ) અધિદૈવ છે. આ દેહમાં જે સાક્ષીભૂત છે તે હું અધિયજ્ઞ છું.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("O best of the embodied beings, the physical nature, which is constantly changing, is called adhibhuta [the material manifestation]. The universal form of the Lord, which includes all the demigods, like those of the sun and moon, is called adhidaiva. And I, the Supreme Lord, represented as the Supersoul in the heart of every embodied being, am called adhiyajna [the Lord of sacrifice].(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अंतकाले च मामेव स्मरन्मुक्त्वा कलेवरम्‌ ।\nयः प्रयाति स मद्भावं याति नास्त्यत्र संशयः ॥");
        } else if (i == 0) {
            this.tv1.setText("અન્તકાલે ચ મામેવ સ્મરન્મુક્ત્વા કલેવરમ્,\nયઃ પ્રયાતિ સ મદ્ભાવં યાતિ નાસ્ત્યત્ર સંશયઃ.(૫)  ");
        } else if (2 == i) {
            this.tv1.setText("Antakaale cha maameva smaran muktwaa kalevaram;\nYah prayaati sa madbhaavam yaati naastyatra samshayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष अंतकाल में भी मुझको ही स्मरण करता हुआ शरीर को त्याग कर जाता है, वह मेरे साक्षात स्वरूप को प्राप्त होता है- इसमें कुछ भी संशय नहीं है॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી જે અંત:કાળે મારું સ્મરણ કરતાં કરતાં શરીર નો ત્યાગ કરે છે, તે મારા સ્વરૂપમાં સમાઈ જાય છે,તેમાં શંકા ને સ્થાન નથી.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("And whoever, at the end of his life, quits his body, remembering Me alone, at once attains My nature. Of this there is no doubt.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यं यं वापि स्मरन्भावं त्यजत्यन्ते कलेवरम्‌ ।\nतं तमेवैति कौन्तेय सदा तद्भावभावितः ॥");
        } else if (i == 0) {
            this.tv1.setText("યં યં વાપિ સ્મરન્ભાવં ત્યજત્યન્તે કલેવરમ્,\nતં તમેવૈતિ કૌન્તેય સદા તદ્ભાવભાવિતઃ.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Yam yam vaapi smaran bhaavam tyajatyante kalevaram;\nTam tamevaiti kaunteya sadaa tadbhaavabhaavitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे कुन्ती पुत्र अर्जुन! यह मनुष्य अंतकाल में जिस-जिस भी भाव को स्मरण करता हुआ शरीर त्याग करता है, उस-उसको ही प्राप्त होता है क्योंकि वह सदा उसी भाव से भावित रहा है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("અથવા હે કાંતેય ! જે મનુષ્યો મનમાં જે જે ભાવ રાખીને અંતે દેહ છોડે છે, તે બીજા જન્મમાં તે તે ભાવથી યુક્ત થઈને તે જન્મે છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("Whatever state of being one remembers when he quits his body, O son of Kunti, that state he will attain without fail.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मात्सर्वेषु कालेषु मामनुस्मर युद्ध च ।\nमय्यर्पितमनोबुद्धिर्मामेवैष्यस्यसंशयम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માત્સર્વેષુ કાલેષુ મામનુસ્મર યુધ્ય ચ,\nમય્યર્પિતમનોબુદ્ધિર્મામેવૈષ્યસ્યસંશયમ્.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaat sarveshu kaaleshu maamanusmara yudhya cha;\nMayyarpitamanobuddhir maamevaishyasyasamshayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए हे अर्जुन! तू सब समय में निरंतर मेरा स्मरण कर और युद्ध भी कर। इस प्रकार मुझमें अर्पण किए हुए मन-बुद्धि से युक्त होकर तू निःसंदेह मुझको ही प्राप्त होगा॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("માટે હે પાર્થ ! મન અને બુદ્ધિને મારામાં અર્પણ કરીને સદૈવ મારું ચિંતન કર અને યુદ્ધ કર, એટલે તે કર્મ મારામાં જ આવી મળશે તેમાં સંશય નથી. (૭)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, Arjuna, you should always think of Me in the form of Krishna and at the same time carry out your prescribed duty of fighting. With your activities dedicated to Me and your mind and intelligence fixed on Me, you will attain Me without doubt.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अभ्यासयोगयुक्तेन चेतसा नान्यगामिना ।\nपरमं पुरुषं दिव्यं याति पार्थानुचिन्तयन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અભ્યાસયોગયુક્તેન ચેતસા નાન્યગામિના,\nપરમં પુરુષં દિવ્યં યાતિ પાર્થાનુચિન્તયન્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Abhyaasayogayuktena chetasaa naanyagaaminaa;\nParamam purusham divyam yaati paarthaanuchintayan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! यह नियम है कि परमेश्वर के ध्यान के अभ्यास रूप योग से युक्त, दूसरी ओर न जाने वाले चित्त से निरंतर चिंतन करता हुआ मनुष्य परम प्रकाश रूप दिव्य पुरुष को अर्थात परमेश्वर को ही प्राप्त होता है॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! પોતાના ચિત્તને ક્યાંય ન જવા દેતાં યોગાભ્યાસ ના સાધનથી ચિત્તને એકાગ્ર કરીને જે મારું ચિંતન કરે છે, તે તેજોમય પુરુષમાં મળી જાય છે. (૮)");
        } else if (2 == i2) {
            this.tv2.setText("He who meditates on Me as the Supreme Personality of Godhead, his mind constantly engaged in remembering Me, undeviated from the path, he, O Partha, is sure to reach Me.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कविं पुराणमनुशासितार-मणोरणीयांसमनुस्मरेद्यः ।\nसर्वस्य धातारमचिन्त्यरूप-मादित्यवर्णं तमसः परस्तात्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કવિં પુરાણમનુશાસિતાર-મણોરણીયાંસમનુસ્મરેદ્યઃ,\nસર્વસ્ય ધાતારમચિન્ત્યરૂપ-માદિત્યવર્ણં તમસઃ પરસ્તાત્.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Kavim puraanamanushaasitaaram\nAnoraneeyaamsam anusmaredyah;\nSarvasya dhaataaram achintyaroopam\nAadityavarnam tamasah parastaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष सर्वज्ञ, अनादि, सबके नियंता (अंतर्यामी रूप से सब प्राणियों के शुभ और अशुभ कर्म के अनुसार शासन करने वाला) सूक्ष्म से भी अति सूक्ष्म, सबके धारण-पोषण करने वाले अचिन्त्य-स्वरूप, सूर्य के सदृश नित्य चेतन प्रकाश रूप और अविद्या से अति परे, शुद्ध सच्चिदानन्दघन परमेश्वर का स्मरण करता है॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વજ્ઞ ,સર્વના નિયંતા,આદિ,સુક્ષ્માતિસુક્ષ્મ ,સર્વના પોષક ,અચિંત્યરૂપ સૂર્ય જેવા તેજસ્વી અને તમોગુણથી અલિપ્ત એવા દિવ્ય પરમ પુરુષનું ચિંતન કરેછે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("One should meditate upon the Supreme Person as the one who knows everything, as He who is the oldest, who is the controller, who is smaller than the smallest, who is the maintainer of everything, who is beyond all material conception, who is inconceivable, and who is always a person. He is luminous like the sun, and He is transcendental, beyond this material nature.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रयाण काले मनसाचलेन भक्त्या युक्तो योगबलेन चैव ।\nभ्रुवोर्मध्ये प्राणमावेश्य सम्यक्‌- स तं परं पुरुषमुपैति दिव्यम्‌ ।");
        } else if (i == 0) {
            this.tv1.setText("પ્રયાણકાલે મનસાચલેનભક્ત્યા યુક્તો યોગબલેન ચૈવ,\nભ્રુવોર્મધ્યે પ્રાણમાવેશ્ય સમ્યક્સ તં પરં પુરુષમુપૈતિ દિવ્યમ્.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Prayaanakaale manasaachalena\nBhaktyaa yukto yogabalena chaiva;\nBhruvormadhye praanamaaveshya samyak\nSa tam param purusham upaiti divyam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह भक्ति युक्त पुरुष अन्तकाल में भी योगबल से भृकुटी के मध्य में प्राण को अच्छी प्रकार स्थापित करके, फिर निश्चल मन से स्मरण करता हुआ उस दिव्य रूप परम पुरुष परमात्मा को ही प्राप्त होता है॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("અંતકાળે જે મનુષ્ય મન સ્થિરકરી ભક્તિ વાળો થઈને યોગબળે બે ભ્રમરોની વચ્ચે પ્રાણનેઉત્તમ પ્રકારે સ્થિર કરે છે ,એ તે દિવ્ય પરમ પુરુષમાં લીન થઇ જાય છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("One who, at the time of death, fixes his life air between the eyebrows and, by the strength of yoga, with an undeviating mind, engages himself in remembering the Supreme Lord in full devotion, will certainly attain to the Supreme Personality of Godhead.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदक्षरं वेदविदो वदन्ति विशन्ति यद्यतयो वीतरागाः ।\nयदिच्छन्तो ब्रह्मचर्यं चरन्ति तत्ते पदं संग्रहेण प्रवक्ष्ये ॥");
        } else if (i == 0) {
            this.tv1.setText("યદક્ષરં વેદવિદો વદન્તિવિશન્તિ યદ્યતયો વીતરાગાઃ,\nયદિચ્છન્તો બ્રહ્મચર્યં ચરન્તિતત્તે પદં સંગ્રહેણ પ્રવક્ષ્યે.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Yadaksharam vedavido vadanti\nVishanti yadyatayo veetaraagaah;\nYadicchanto brahmacharyam charanti\nTatte padam samgrahena pravakshye.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वेद के जानने वाले विद्वान जिस सच्चिदानन्दघनरूप परम पद को अविनाश कहते हैं, आसक्ति रहित यत्नशील संन्यासी महात्माजन, जिसमें प्रवेश करते हैं और जिस परम पद को चाहने वाले ब्रह्मचारी लोग ब्रह्मचर्य का आचरण करते हैं, उस परम पद को मैं तेरे लिए संक्षेप में कहूँगा॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("વેદવેત્તાઓ જે પરમ તત્વને અક્ષર કહે છે, તે,જેમના કામ ક્રોધનો નાશ થયો છે એવા સંન્યાસી જે સ્વરૂપને પ્રાપ્ત કરે છે અને જેની પ્રાપ્તિ માટે બ્રહ્મચારીઓ બ્રહ્મચર્ય વ્રત પાળે છે તે પદને હું તને ટૂંક માં કહીશ.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("Persons who are learned in the Vedas, who utter omkara and who are great sages in the renounced order enter into Brahman. Desiring such perfection, one practices celibacy. I shall now briefly explain to you this process by which one may attain salvation.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12_13() {
        setTitle("Shlok 12,13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वद्वाराणि संयम्य मनो हृदि निरुध्य च ।\nमूर्ध्न्याधायात्मनः प्राणमास्थितो योगधारणाम्‌ ॥\nओमित्येकाक्षरं ब्रह्म व्याहरन्मामनुस्मरन्‌ ।\nयः प्रयाति त्यजन्देहं स याति परमां गतिम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વદ્વારાણિ સંયમ્ય મનો હૃદિ નિરુધ્ય ચ,\nમૂર્ધ્ન્યાધાયાત્મનઃ પ્રાણમાસ્થિતો યોગધારણામ્.(૧૨)\nઓમિત્યેકાક્ષરં બ્રહ્મ વ્યાહરન્મામનુસ્મરન્,\nયઃ પ્રયાતિ ત્યજન્દેહં સ યાતિ પરમાં ગતિમ્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Sarvadwaaraani samyamya mano hridi nirudhya cha;\nMoordhnyaadhaayaatmanah praanamaasthito yogadhaaranaam.\nOmityekaaksharam brahma vyaaharan maamanusmaran;\nYah prayaati tyajan deham sa yaati paramaam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सब इंद्रियों के द्वारों को रोककर तथा मन को हृद्देश में स्थिर करके, फिर उस जीते हुए मन द्वारा प्राण को मस्तक में स्थापित करके, परमात्म संबंधी योगधारणा में स्थित होकर जो पुरुष 'ॐ' इस एक अक्षर रूप ब्रह्म को उच्चारण करता हुआ और उसके अर्थस्वरूप मुझ निर्गुण ब्रह्म का चिंतन करता हुआ शरीर को त्यागकर जाता है, वह पुरुष परम गति को प्राप्त होता है॥12-13॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે  ઈન્દ્રિયોરૂપી સર્વ દ્વારોનો નિરોધ કરી ,ચિત્તને હદયમાં સ્થિર કરી ,ભ્રુકુટી ના મધ્યભાગમાં પોતાના પ્રાણવાયુને સ્થિર કરી યોગાભ્યાસમાં સ્થિર થાય.બ્રહ્મવાચક એકાક્ષર ॐ નો ઉચ્ચાર કરીને મારું જે સ્મરણ કરતો દેહત્યાગ કરે છે તે ઉત્તમ ગતિને પામેછે.(૧૨-૧૩) ");
        } else if (2 == i2) {
            this.tv2.setText("The yogic situation is that of detachment from all sensual engagements. Closing all the doors of the senses and fixing the mind on the heart and the life air at the top of the head, one establishes himself in yoga.After being situated in this yoga practice and vibrating the sacred syllable om, the supreme combination of letters, if one thinks of the Supreme Personality of Godhead and quits his body, he will certainly reach the spiritual planets.(12-13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनन्यचेताः सततं यो मां स्मरति नित्यशः ।\nतस्याहं सुलभः पार्थ नित्ययुक्तस्य योगिनीः ॥");
        } else if (i == 0) {
            this.tv1.setText("અનન્યચેતાઃ સતતં યો માં સ્મરતિ નિત્યશઃ,\nતસ્યાહં સુલભઃ પાર્થ નિત્યયુક્તસ્ય યોગિનઃ.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Ananyachetaah satatam yo maam smarati nityashah;\nTasyaaham sulabhah paartha nityayuktasya yoginah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जो पुरुष मुझमें अनन्य-चित्त होकर सदा ही निरंतर मुझ पुरुषोत्तम को स्मरण करता है, उस नित्य-निरंतर मुझमें युक्त हुए योगी के लिए मैं सुलभ हूँ, अर्थात उसे सहज ही प्राप्त हो जाता हूँ॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! જે યોગી એકાગ્રચિત્તે સદા મારું સ્મરણ કરે છે, જે સદા સમાધાન યુક્ત હોય છે , તેને હું સહજતાથી પ્રાપ્ત થાઉં છું.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("For one who always remembers Me without deviation, I am easy to obtain, O son of Pritha, because of his constant engagement in devotional service.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मामुपेत्य पुनर्जन्म दुःखालयमशाश्वतम्‌ ।\nनाप्नुवन्ति महात्मानः संसिद्धिं परमां गताः ॥");
        } else if (i == 0) {
            this.tv1.setText("મામુપેત્ય પુનર્જન્મ દુઃખાલયમશાશ્વતમ્,\nનાપ્નુવન્તિ મહાત્માનઃ સંસિદ્ધિં પરમાં ગતાઃ.(૧૫)\n");
        } else if (2 == i) {
            this.tv1.setText("Maamupetya punarjanma duhkhaalayamashaashwatam;\nNaapnuvanti mahaatmaanah samsiddhim paramaam gataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परम सिद्धि को प्राप्त महात्माजन मुझको प्राप्त होकर दुःखों के घर एवं क्षणभंगुर पुनर्जन्म को नहीं प्राप्त होते॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ પરમ સિદ્ધિ પ્રાપ્ત મહાત્માઓ પછી દુઃખનું સ્થાન અને અશાશ્વત એવા જન્મને પામતા નથી.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("After attaining Me, the great souls, who are yogis in devotion, never return to this temporary world, which is full of miseries, because they have attained the highest perfection.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आब्रह्मभुवनाल्लोकाः पुनरावर्तिनोऽर्जुन ।\nमामुपेत्य तु कौन्तेय पुनर्जन्म न विद्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("આબ્રહ્મભુવનાલ્લોકાઃ પુનરાવર્તિનોર્જુન,\nમામુપેત્ય તુ કૌન્તેય પુનર્જન્મ ન વિદ્યતે.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Aabrahmabhuvanaallokaah punaraavartino’rjuna;\nMaamupetya tu kaunteya punarjanma na vidyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! ब्रह्मलोकपर्यंत सब लोक पुनरावर्ती हैं, परन्तु हे कुन्तीपुत्र! मुझको प्राप्त होकर पुनर्जन्म नहीं होता, क्योंकि मैं कालातीत हूँ और ये सब ब्रह्मादि के लोक काल द्वारा सीमित होने से अनित्य हैं॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! બ્રહ્મલોક સુધીના સર્વલોક ઉત્પતિ અને વિનાશને આધીન છે. પરંતુ હે કાંતેય ! ફક્ત મારી પ્રાપ્તિ થયા પછી પુનર્જન્મ થતો નથી.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("From the highest planet in the material world down to the lowest, all are places of misery wherein repeated birth and death take place. But one who attains to My abode, O son of Kunti, never takes birth again.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सहस्रयुगपर्यन्तमहर्यद्ब्रह्मणो विदुः ।\nरात्रिं युगसहस्रान्तां तेऽहोरात्रविदो जनाः ॥");
        } else if (i == 0) {
            this.tv1.setText("સહસ્રયુગપર્યન્તમહર્યદ્બ્રહ્મણો વિદુઃ,\nરાત્રિં યુગસહસ્રાન્તાં તેહોરાત્રવિદો જનાઃ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Sahasrayugaparyantam aharyad brahmano viduh;\nRaatrim yugasahasraantaam te’horaatravido janaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("ब्रह्मा का जो एक दिन है, उसको एक हजार चतुर्युगी तक की अवधि वाला और रात्रि को भी एक हजार चतुर्युगी तक की अवधि वाला जो पुरुष तत्व से जानते हैं, वे योगीजन काल के तत्व को जानने वाले हैं॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેમકે ચાર હજાર યુગ વિતે છે ત્યારે બ્રહ્મદેવનો એક દિવસ થાય છે અને પછી  તેટલા જ  સમય ની રાત્રિ આવે છે. આ વાત રાત્રિ-દિવસને  જાણનારા મનુષ્યો જ જાણે છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("By human calculation, a thousand ages taken together form the duration of Brahma’s one day. And such also is the duration of his night.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अव्यक्ताद्व्यक्तयः सर्वाः प्रभवन्त्यहरागमे ।\nरात्र्यागमे प्रलीयन्ते तत्रैवाव्यक्तसंज्ञके ॥");
        } else if (i == 0) {
            this.tv1.setText("અવ્યક્તાદ્વ્યક્તયઃ સર્વાઃ પ્રભવન્ત્યહરાગમે,\nરાત્ર્યાગમે પ્રલીયન્તે તત્રૈવાવ્યક્તસંજ્ઞકે.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Avyaktaadvyaktayah sarvaah prabhavantyaharaagame;\nRaatryaagame praleeyante tatraivaavyaktasamjnake.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संपूर्ण चराचर भूतगण ब्रह्मा के दिन के प्रवेश काल में अव्यक्त से अर्थात ब्रह्मा के सूक्ष्म शरीर से उत्पन्न होते हैं और ब्रह्मा की रात्रि के प्रवेशकाल में उस अव्यक्त नामक ब्रह्मा के सूक्ष्म शरीर में ही लीन हो जाते हैं॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("દિવસ શરૂ થતાં અવ્યક્ત માંથી સર્વ ભૂતોનો ઉદય થાય છે. અને રાત્રિ નું આગમન થતાં જ તે સર્વ અવ્યક્ત માં લય પામે છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("At the beginning of Brahma’s day, all living entities become manifest from the unmanifest state, and thereafter, when the night falls, they are merged into the unmanifest again.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भूतग्रामः स एवायं भूत्वा भूत्वा प्रलीयते ।\nरात्र्यागमेऽवशः पार्थ प्रभवत्यहरागमे ॥");
        } else if (i == 0) {
            this.tv1.setText("ભૂતગ્રામઃ સ એવાયં ભૂત્વા ભૂત્વા પ્રલીયતે,\nરાત્ર્યાગમેવશઃ પાર્થ પ્રભવત્યહરાગમે.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Bhootagraamah sa evaayam bhootwaa bhootwaa praleeyate;\nRaatryaagame’vashah paartha prabhavatyaharaagame.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! वही यह भूतसमुदाय उत्पन्न हो-होकर प्रकृति वश में हुआ रात्रि के प्रवेश काल में लीन होता है और दिन के प्रवेश काल में फिर उत्पन्न होता है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! તે સર્વ ચરાચર ભૂતોનો સમુદાય પરાધીન હોવાથી ફરી ફરી ઉત્પન થાય છે અને રાત્રિ આવતાં લય પામે છે. અને ફરી દિવસ થતાં પુન: ઉત્પન થાય છે.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Again and again, when Brahma’s day arrives, all living entities come into being, and with the arrival of Brahma’s night they are helplessly annihilated.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("परस्तस्मात्तु भावोऽन्योऽव्यक्तोऽव्यक्तात्सनातनः ।\nयः स सर्वेषु भूतेषु नश्यत्सु न विनश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("પરસ્તસ્માત્તુ ભાવોન્યોવ્યક્તોવ્યક્તાત્સનાતનઃ,\nયઃ સ સર્વેષુ ભૂતેષુ નશ્યત્સુ ન વિનશ્યતિ.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Parastasmaat tu bhaavo’nyo’vyakto’vyaktaatsanaatanah;\nYah sa sarveshu bhooteshu nashyatsu na vinashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उस अव्यक्त से भी अति परे दूसरा अर्थात विलक्षण जो सनातन अव्यक्त भाव है, वह परम दिव्य पुरुष सब भूतों के नष्ट होने पर भी नष्ट नहीं होता॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ ચરાચરનો  નાશ થયા પછી પણ જે નાશ પામતો નથી , એ તે અવ્યક્તથી પર , ઇન્દ્રિયોથી અગોચર તથા અવિનાશી બીજો ભાવ છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Yet there is another unmanifest nature, which is eternal and is transcendental to this manifested and unmanifested matter. It is supreme and is never annihilated. When all in this world is annihilated, that part remains as it is.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("\nअव्यक्तोऽक्षर इत्युक्तस्तमाहुः परमां गतिम्‌ ।\nयं प्राप्य न निवर्तन्ते तद्धाम परमं मम ॥");
        } else if (i == 0) {
            this.tv1.setText("અવ્યક્તોક્ષર ઇત્યુક્તસ્તમાહુઃ પરમાં ગતિમ્,\nયં પ્રાપ્ય ન નિવર્તન્તે તદ્ધામ પરમં મમ.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Avyakto’kshara ityuktastamaahuh paramaam gatim;\nYam praapya na nivartante taddhaama paramam mama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो अव्यक्त 'अक्षर' इस नाम से कहा गया है, उसी अक्षर नामक अव्यक्त भाव को परमगति कहते हैं तथा जिस सनातन अव्यक्त भाव को प्राप्त होकर मनुष्य वापस नहीं आते, वह मेरा परम धाम है॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે અવ્યક્ત ભાવ અક્ષર સંજ્ઞાથી પ્રસિદ્ધ છે તેને જ પરમગતિ કહેવામાં આવે છે. જ્યાં જ્ઞાનીઓ પહોચ્યા પછી પુન: પાછા આવતા નથી તે જ મારું પરમધામ છે.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("That which the Vedantists describe as unmanifest and infallible, that which is known as the supreme destination, that place from which, having attained it, one never returns—that is My supreme abode.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पुरुषः स परः पार्थ भक्त्या लभ्यस्त्वनन्यया ।\nयस्यान्तः स्थानि भूतानि येन सर्वमिदं ततम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પુરુષઃ સ પરઃ પાર્થ ભક્ત્યા લભ્યસ્ત્વનન્યયા,\nયસ્યાન્તઃસ્થાનિ ભૂતાનિ યેન સર્વમિદં તતમ્.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Purushah sa parah paartha bhaktyaa labhyastwananyayaa;\nYasyaantahsthaani bhootaani yena sarvamidam tatam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! जिस परमात्मा के अंतर्गत सर्वभूत है और जिस सच्चिदानन्दघन परमात्मा से यह समस्त जगत परिपूर्ण है (गीता अध्याय 9 श्लोक 4 में देखना चाहिए), वह सनातन अव्यक्त परम पुरुष तो अनन्य (गीता अध्याय 11 श्लोक 55 में इसका विस्तार देखना चाहिए) भक्ति से ही प्राप्त होने योग्य है ॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! જેમાં સર્વ ભૂતોનો સમાવેશ થાય છે અને જેનાથી આ સમસ્ત જગત વ્યાપ્ત છે, તે પરમ પુરુષ અનન્ય ભક્તિથી જ પ્રાપ્ત થાય છે.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead, who is greater than all, is attainable by unalloyed devotion. Although He is present in His abode, He is all-pervading, and everything is situated within Him.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्र काले त्वनावत्तिमावृत्तिं चैव योगिनः ।\nप्रयाता यान्ति तं कालं वक्ष्यामि भरतर्षभ ॥");
        } else if (i == 0) {
            this.tv1.setText("યત્ર કાલે ત્વનાવૃત્તિમાવૃત્તિં ચૈવ યોગિનઃ,\nપ્રયાતા યાન્તિ તં કાલં વક્ષ્યામિ ભરતર્ષભ.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Yatra kaale twanaavrittim aavrittim chaiva yoginah;\nPrayaataa yaanti tam kaalam vakshyaami bharatarshabha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जिस काल में (यहाँ काल शब्द से मार्ग समझना चाहिए, क्योंकि आगे के श्लोकों में भगवान ने इसका नाम 'सृति', 'गति' ऐसा कहा है।) शरीर त्याग कर गए हुए योगीजन तो वापस न लौटने वाली गति को और जिस काल में गए हुए वापस लौटने वाली गति को ही प्राप्त होते हैं, उस काल को अर्थात दोनों मार्गों को कहूँगा॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભરતશ્રેષ્ઠ ! જે કાળે યોગીઓ મૃત્યુ પામી, પાછા જન્મતા નથી અને જે કાળે મૃત્યુ પામીને પાછા જન્મે છે, તે કાળ હું તને કહું છું.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("O best of the Bharatas, I shall now explain to you the different times at which, passing away from this world, the yogi does or does not come back.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अग्निर्ज्योतिरहः शुक्लः षण्मासा उत्तरायणम्‌ ।\nतत्र प्रयाता गच्छन्ति ब्रह्म ब्रह्मविदो जनाः ॥");
        } else if (i == 0) {
            this.tv1.setText("અગ્નિર્જ્યોતિરહઃ શુક્લઃ ષણ્માસા ઉત્તરાયણમ્,\nતત્ર પ્રયાતા ગચ્છન્તિ બ્રહ્મ બ્રહ્મવિદો જનાઃ.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Agnijyotirahah shuklah shanmaasaa uttaraayanam;\nTatra prayaataa gacchanti brahma brahmavido janaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस मार्ग में ज्योतिर्मय अग्नि-अभिमानी देवता हैं, दिन का अभिमानी देवता है, शुक्ल पक्ष का अभिमानी देवता है और उत्तरायण के छः महीनों का अभिमानी देवता है, उस मार्ग में मरकर गए हुए ब्रह्मवेत्ता योगीजन उपयुक्त देवताओं द्वारा क्रम से ले जाए जाकर ब्रह्म को प्राप्त होते हैं। ॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("અગ્નિ ,જ્યોતિ,દિવસ, શુક્લપક્ષ અને ઉત્તરાયણના છ માસ માં મૃત્યુ પામનાર બ્રહ્મવેત્તાઓ  બ્રહ્મ ને જઈ મળે છે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Those who know the Supreme Brahman attain that Supreme by passing away from the world during the influence of the fiery god, in the light, at an auspicious moment of the day, during the fortnight of the waxing moon, or during the six months when the sun travels in the north.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("धूमो रात्रिस्तथा कृष्ण षण्मासा दक्षिणायनम्‌ ।\nतत्र चान्द्रमसं ज्योतिर्योगी प्राप्य निवर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("ધૂમો રાત્રિસ્તથા કૃષ્ણઃ ષણ્માસા દક્ષિણાયનમ્,\nતત્ર ચાન્દ્રમસં જ્યોતિર્યોગી પ્રાપ્ય નિવર્તતે.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Dhoomo raatristathaa krishnah shanmaasaa dakshinaayanam;\nTatra chaandramasam jyotir yogee praapya nivartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस मार्ग में धूमाभिमानी देवता है, रात्रि अभिमानी देवता है तथा कृष्ण पक्ष का अभिमानी देवता है और दक्षिणायन के छः महीनों का अभिमानी देवता है, उस मार्ग में मरकर गया हुआ सकाम कर्म करने वाला योगी उपयुक्त देवताओं द्वारा क्रम से ले गया हुआ चंद्रमा की ज्योत को प्राप्त होकर स्वर्ग में अपने शुभ कर्मों का फल भोगकर वापस आता है॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("ધૂમ્ર, રાત, કૃષ્ણપક્ષ  તથા દક્ષિણાયન ના છ માસ માં મૃત્યુ પામનાર યોગી ચન્દ્ર્લોકમાં ભોગો ભોગવી આગળ ન  જતાં પાછા વળે છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("The mystic who passes away from this world during the smoke, the night, the fortnight of the waning moon, or the six months when the sun passes to the south reaches the moon planet but again comes back.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शुक्ल कृष्णे गती ह्येते जगतः शाश्वते मते ।\nएकया यात्यनावृत्ति मन्ययावर्तते पुनः ॥");
        } else if (i == 0) {
            this.tv1.setText("શુક્લકૃષ્ણે ગતી હ્યેતે જગતઃ શાશ્વતે મતે,\nએકયા યાત્યનાવૃત્તિમન્યયાવર્તતે પુનઃ.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Shuklakrishne gatee hyete jagatah shaashwate mate;\nEkayaa yaatyanaavrittim anyayaa’vartate punah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि जगत के ये दो प्रकार के- शुक्ल और कृष्ण अर्थात देवयान और पितृयान मार्ग सनातन माने गए हैं। इनमें एक द्वारा गया हुआ (अर्थात इसी अध्याय के श्लोक 24 के अनुसार अर्चिमार्ग से गया हुआ योगी।)-- जिससे वापस नहीं लौटना पड़ता, उस परमगति को प्राप्त होता है और दूसरे के द्वारा गया हुआ ( अर्थात इसी अध्याय के श्लोक 25 के अनुसार धूममार्ग से गया हुआ सकाम कर्मयोगी।) फिर वापस आता है अर्थात्‌ जन्म-मृत्यु को प्राप्त होता है॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ જગતની  શુક્લ અને કૃષ્ણ એમ બે ગતિ શાશ્વત માનવામાં આવી છે. એક ગતિથી જનાર યોગીને પાછા ફરવું પડતું નથી અને બીજી ગતિથી  જનાર યોગીને પાછા ફરવું પડે છે.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("Although the devotees know these two paths, O Arjuna, they are never bewildered. Therefore be always fixed in devotion.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नैते सृती पार्थ जानन्योगी मुह्यति कश्चन ।\nतस्मात्सर्वेषु कालेषु योगयुक्तो भवार्जुन ॥");
        } else if (i == 0) {
            this.tv1.setText("નૈતે સૃતી પાર્થ જાનન્યોગી મુહ્યતિ કશ્ચન,\nતસ્માત્સર્વેષુ કાલેષુ યોગયુક્તો ભવાર્જુન.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Naite sritee paartha jaanan yogee muhyati kashchana;\nTasmaat sarveshu kaaleshu yogayukto bhavaarjuna.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! इस प्रकार इन दोनों मार्गों को तत्त्व से जानकर कोई भी योगी मोहित नहीं होता। इस कारण हे अर्जुन! तू सब काल में समबुद्धि रूप से योग से युक्त हो अर्थात निरंतर मेरी प्राप्ति के लिए साधन करने वाला हो॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! આ બે માર્ગને જાણનારો કોઈ પણ યોગી મોહમાં ફસાતો નથી. એટલા માટે તું સર્વ કાળમાં યોગયુક્ત બન.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("Although the devotees know these two paths, O Arjuna, they are never bewildered. Therefore be always fixed in devotion.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वेदेषु यज्ञेषु तपःसु चैव दानेषु यत्पुण्यफलं प्रदिष्टम्‌ ।\nअत्येत तत्सर्वमिदं विदित्वा योगी परं स्थानमुपैति चाद्यम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("વેદેષુ યજ્ઞેષુ તપઃસુ ચૈવદાનેષુ યત્પુણ્યફલં પ્રદિષ્ટમ્,\nઅત્યેતિ તત્સર્વમિદં વિદિત્વાયોગી પરં સ્થાનમુપૈતિ ચાદ્યમ્.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Vedeshu yajneshu tapahsu chaiva\nDaaneshu yat punyaphalam pradishtam:\nAtyeti tatsarvam idam viditwaa\nYogee param sthaanamupaiti chaadyam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("भावार्थ :  योगी पुरुष इस रहस्य को तत्त्व से जानकर वेदों के पढ़ने में तथा यज्ञ, तप और दानादि के करने में जो पुण्यफल कहा है, उन सबको निःसंदेह उल्लंघन कर जाता है और सनातन परम पद को प्राप्त होता है॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ બધું જાણ્યા પછી વેદ,યજ્ઞ , તપ અને દાન દ્વારા થતી જે પુણ્યફળની  પ્રાપ્તિ કહી છે, તે સર્વ પુણ્ય પ્રાપ્તિનું  અતિક્રમણ કરીને યોગી આદ્ય તથા ઉત્કૃષ્ટ સ્થાનને જ પ્રાપ્ત કરે છે.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("A person who accepts the path of devotional service is not bereft of the results derived from studying the Vedas, performing austere sacrifices, giving charity or pursuing philosophical and fruitive activities. Simply by performing devotional service, he attains all these, and at the end he reaches the supreme eternal abode.(28)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay8.this, ShlokListPlay8.this);
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

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

public class ShlokListPlay14 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay14.this.favflag == 0) {
                    ShlokListPlay14.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.favflag = 1;
                    shlokListPlay14.save();
                } else if (1 == ShlokListPlay14.this.favflag) {
                    ShlokListPlay14.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay14 shlokListPlay142 = ShlokListPlay14.this;
                    shlokListPlay142.favflag = 0;
                    shlokListPlay142.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay14.this.calll++;
                ShlokListPlay14.this.slkset();
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
                ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                shlokListPlay14.calll--;
                ShlokListPlay14.this.slkset();
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
        edit.putString("A14" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A14" + this.calll, "0");
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
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.callf1 = 0;
                    shlokListPlay14.slkset();
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
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.callf1 = 2;
                    shlokListPlay14.slkset();
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
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.callf1 = 1;
                    shlokListPlay14.slkset();
                }
            });
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setTextSize(16.0f);
            this.button2.setText("हिंदी");
            TextView textView4 = this.tv2;
            textView4.setTypeface(textView4.getTypeface(), 0);
            this.button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.callf2 = 0;
                    shlokListPlay14.slkset();
                }
            });
        } else if (i2 == 0) {
            this.tv2.setTypeface(this.myfonts1);
            this.button2.setTypeface(this.myfonts1);
            this.button2.setText("ગુજરાતી");
            this.button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.callf2 = 2;
                    shlokListPlay14.slkset();
                }
            });
        } else if (2 == i2) {
            this.button2.setText("English");
            this.button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShlokListPlay14 shlokListPlay14 = ShlokListPlay14.this;
                    shlokListPlay14.callf2 = 1;
                    shlokListPlay14.slkset();
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
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 14");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १४ \nगुणत्रयविभागयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૪ \nગુણત્ર વિભાગ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 14\ngunatra vibhaga yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा हैं की, सारे देहधारी जीव भौतिक प्रकृति के तीन गुणों के अधीन हैं - ये हैं सतोगुण, रजोगुण तथा तमोगुण। कृष्ण बतलाते हैं की ये गुण क्या हैं? ये हम पर किस प्रकार क्रिया करते हैं? कोई इनको कैसे पार कर सकता हैं? और दिव्य पद को प्राप्त व्यक्ति के कोण कोण से लक्षण हैं?");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૪ માં પ્રકૃતિ, ગુણો અને ગુણાતીત વિષે સમજાવ્યું છે. અને કહે છે કે મારી 'મૂળ પ્રકૃતિ' (મહદ બ્રહ્મ પ્રકૃતિ) એ સર્વ ભૂતોની યોનિ સ્થાન (ગર્ભ સ્થાન) છે. તેમાં હું જ પિતા તરીકે ચેતન ના અંશ રૂપ બીજ મુકું છું અને હું જ માતા તરીકે ગર્ભ ધારણ કરું છું. જેના થી સર્વ ભૂતોની ઉત્પત્તિ થાય છે .સત્વ,રજસ અને તમસ એ ત્રણ ગુણો પ્રકૃતિ માં થી ઉત્પન્ન થયેલા છે, અને આ ત્રણ ગુણો, દેહમાં રહેલા અવિનાશી જીવાત્મા ને બાંધે છે. ભક્તિ યોગ થી આ ત્રણ ગુણો થી પર જઈ ગુણાતીત (બ્રહ્મ ભાવ) પામવા યોગ્ય બનાય છે.");
        } else if (2 == i) {
            this.tv1.setText("Forteenth adhyay tells, All embodied souls are under the control of the three modes, or qualities, of material nature: goodness, passion, and ignorance. Lord Krishna explains what these modes are, how they act upon us, how one transcends them, and the symptoms of one who has attained the transcendental state.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nपरं भूयः प्रवक्ष्यामि ज्ञानानं मानमुत्तमम्‌ । \nयज्ज्ञात्वा मुनयः सर्वे परां सिद्धिमितो गताः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ- \nપરં ભૂયઃ પ્રવક્ષ્યામિ જ્ઞાનાનાં જ્ઞાનમુત્તમમ્,\nયજ્જ્ઞાત્વા મુનયઃ સર્વે પરાં સિદ્ધિમિતો ગતાઃ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nParam bhooyah pravakshyaami jnaanaanaam jnaanamuttamam;\nYajjnaatwaa munayah sarve paraam siddhimito gataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्री भगवान बोले- ज्ञानों में भी अतिउत्तम उस परम ज्ञान को मैं फिर कहूँगा, जिसको जानकर सब मुनिजन इस संसार से मुक्त होकर परम सिद्धि को प्राप्त हो गए हैं॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : જે જ્ઞાનને જાણીને સર્વ મુનિઓ આ સંસારમાંથી પરમ સિદ્ધિને પામ્યા છે.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: Again I shall declare to you this supreme wisdom, the best of all knowledge, knowing which all the sages have attained the supreme perfection.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इदं ज्ञानमुपाश्रित्य मम साधर्म्यमागताः । \nसर्गेऽपि नोपजायन्ते प्रलये न व्यथन्ति च ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇદં જ્ઞાનમુપાશ્રિત્ય મમ સાધર્મ્યમાગતાઃ,\nસર્ગેપિ નોપજાયન્તે પ્રલયે ન વ્યથન્તિ ચ.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Idam jnaanam upaashritya mama saadharmyam aagataah;\nSarge’pi nopajaayante pralaye na vyathanti cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस ज्ञान को आश्रय करके अर्थात धारण करके मेरे स्वरूप को प्राप्त हुए पुरुष सृष्टि के आदि में पुनः उत्पन्न नहीं होते और प्रलयकाल में भी व्याकुल नहीं होते॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ જ્ઞાનનો આશ્રય લઈને જે મારામાં એકરૂપ થઇ ગયા છે, તે સૃષ્ટિના ઉત્પતિ કાળમાં જન્મતા નથી કે પ્રલયમાં વ્યથા પામતા નથી.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("By becoming fixed in this knowledge, one can attain to the transcendental nature like My own. Thus established, one is not born at the time of creation or disturbed at the time of dissolution.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मम योनिर्महद्ब्रह्म तस्मिन्गर्भं दधाम्यहम्‌ । \nसम्भवः सर्वभूतानां ततो भवति भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("મમ યોનિર્મહદ્બ્રહ્મ તસ્મિન્ ગર્ભં દધામ્યહમ્,\nસંભવઃ સર્વભૂતાનાં તતો ભવતિ ભારત.(૩)\n");
        } else if (2 == i) {
            this.tv1.setText("Mama yonirmahadbrahma tasmin garbham dadhaamyaham;\nSambhavah sarvabhootaanaam tato bhavati bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! मेरी महत्‌-ब्रह्मरूप मूल-प्रकृति सम्पूर्ण भूतों की योनि है अर्थात गर्भाधान का स्थान है और मैं उस योनि में चेतन समुदायरूप गर्भ को स्थापन करता हूँ। उस जड़-चेतन के संयोग से सब भूतों की उत्पति होती है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! મૂળ પ્રધાન પ્રકૃતિ બ્રહ્મ મારું ગર્ભાધાન કરવાનું સ્થાન છે. તેમા હું ગર્ભને ધારણ કરું છું. આથી સર્વ ભૂતોની ઉત્પતિ થાય છે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("The total material substance, called Brahman, is the source of birth, and it is that Brahman that I impregnate, making possible the births of all living beings, O son of Bharata.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वयोनिषु कौन्तेय मूर्तयः सम्भवन्ति याः । \nतासां ब्रह्म महद्योनिरहं बीजप्रदः पिता ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વયોનિષુ કૌન્તેય મૂર્તયઃ સમ્ભવન્તિ યાઃ,\nતાસાં બ્રહ્મ મહદ્યોનિરહં બીજપ્રદઃ પિતા.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Sarvayonishu kaunteya moortayah sambhavanti yaah;\nTaasaam brahma mahadyonir aham beejapradah pitaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! नाना प्रकार की सब योनियों में जितनी मूर्तियाँ अर्थात शरीरधारी प्राणी उत्पन्न होते हैं, प्रकृति तो उन सबकी गर्भधारण करने वाली माता है और मैं बीज को स्थापन करने वाला पिता हूँ॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય  ! સર્વ યોનીમાં જે પ્રાણી ઉત્પન થાય છે, તે પ્રાણીઓની પ્રકૃતિ-માયા માતા છે તથા હું ગર્ભાધાન કરનારો પિતા છું.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("It should be understood that all species of life, O son of Kunti, are made possible by birth in this material nature, and that I am the seed-giving father.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सत्त्वं रजस्तम इति गुणाः प्रकृतिसम्भवाः ।\nनिबध्नन्ति महाबाहो देहे देहिनमव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સત્ત્વં રજસ્તમ ઇતિ ગુણાઃ પ્રકૃતિસંભવાઃ,\nનિબધ્નન્તિ મહાબાહો દેહે દેહિનમવ્યયમ્.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Sattwam rajastama iti gunaah prakriti sambhavaah;\nNibadhnanti mahaabaaho dehe dehinam avyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! सत्त्वगुण, रजोगुण और तमोगुण -ये प्रकृति से उत्पन्न तीनों गुण अविनाशी जीवात्मा को शरीर में बाँधते हैं॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો  ! સત્વ, રજ અને તમ એ ત્રણ ગુણો પ્રકૃતિમાંથી જ ઉત્પન્ન થયા છે. તેઓ આ શરીરમાં અવિનાશી જીવાત્માને બાંધે છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("Material nature consists of three modes—goodness, passion and ignorance. When the eternal living entity comes in contact with nature, O mighty-armed Arjuna, he becomes conditioned by these modes.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्र सत्त्वं निर्मलत्वात्प्रकाशकमनामयम्‌ ।\nसुखसङ्‍गेन बध्नाति ज्ञानसङ्‍गेन चानघ ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્ર સત્ત્વં નિર્મલત્વાત્પ્રકાશકમનામયમ્,\nસુખસઙ્ગેન બધ્નાતિ જ્ઞાનસઙ્ગેન ચાનઘ.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Tatra sattwam nirmalatwaat prakaashakam anaamayam;\nSukhasangena badhnaati jnaanasangena chaanagha");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे निष्पाप! उन तीनों गुणों में सत्त्वगुण तो निर्मल होने के कारण प्रकाश करने वाला और विकार रहित है, वह सुख के सम्बन्ध से और ज्ञान के सम्बन्ध से अर्थात उसके अभिमान से बाँधता है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અનધ   ! તે ત્રણ ગુણોમાં સત્વગુણ નિર્મળપણાને લીધે પ્રકાશ કરનાર, ઉપદ્રવરહિત સુખના સંગથી અને જ્ઞાનના સંગથી બાંધે છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("O sinless one, the mode of goodness, being purer than the others, is illuminating, and it frees one from all sinful reactions. Those situated in that mode become conditioned by a sense of happiness and knowledge.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रजो रागात्मकं विद्धि तृष्णासङ्‍गसमुद्भवम्‌ ।\nतन्निबध्नाति कौन्तेय कर्मसङ्‍गेन देहिनम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("રજો રાગાત્મકં વિદ્ધિ તૃષ્ણાસઙ્ગસમુદ્ભવમ્,\nતન્નિબધ્નાતિ કૌન્તેય કર્મસઙ્ગેન દેહિનમ્.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Rajo raagaatmakam viddhi trishnaasangasamudbhavam;\nTannibadhnaati kaunteya karmasangena dehinam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! रागरूप रजोगुण को कामना और आसक्ति से उत्पन्न जान। वह इस जीवात्मा को कर्मों और उनके फल के सम्बन्ध में बाँधता है॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય ! પ્રીતિસ્વરૂપ જે રજોગુણ તે આશા અને આસક્તિના સંબંધ થી જ ઉત્પન્ન થયેલો છે. તે જીવાત્માને કર્મની આસક્તિ દ્વારા દેહમાં બાંધે છે.(૭) ");
        } else if (2 == i2) {
            this.tv2.setText("The mode of passion is born of unlimited desires and longings, O son of Kunti, and because of this the embodied living entity is bound to material fruitive actions.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तमस्त्वज्ञानजं विद्धि मोहनं सर्वदेहिनाम्‌ ।\nप्रमादालस्यनिद्राभिस्तन्निबध्नाति भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("તમસ્ત્વજ્ઞાનજં વિદ્ધિ મોહનં સર્વદેહિનામ્,\nપ્રમાદાલસ્યનિદ્રાભિસ્તન્નિબધ્નાતિ ભારત.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Tamastwajnaanajam viddhi mohanam sarvadehinaam;\nPramaadaalasyanidraabhis tannibadhnaati bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! सब देहाभिमानियों को मोहित करने वाले तमोगुण को तो अज्ञान से उत्पन्न जान। वह इस जीवात्मा को प्रमाद, आलस्य और निद्रा द्वारा बाँधता है॥8॥    ");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત  ! વળી તમોગુણને અજ્ઞાનથી ઉત્પન્ન થયેલો તથા સર્વ જીવાત્માઓને મોહમાં નાખનારો જાણ. તે જીવાત્મા ને પ્રમાદ, નિદ્રા વગેરે વડે બાંધે છે.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Bharata, know that the mode of darkness, born of ignorance, is the delusion of all embodied living entities. The results of this mode are madness, indolence and sleep, which bind the conditioned soul.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सत्त्वं सुखे सञ्जयति रजः कर्मणि भारत ।\nज्ञानमावृत्य तु तमः प्रमादे सञ्जयत्युत ॥");
        } else if (i == 0) {
            this.tv1.setText("સત્ત્વં સુખે સઞ્જયતિ રજઃ કર્મણિ ભારત,\nજ્ઞાનમાવૃત્ય તુ તમઃ પ્રમાદે સઞ્જયત્યુત.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Sattwam sukhe sanjayati rajah karmani bhaarata;\nJnaanamaavritya tu tamah pramaade sanjayatyuta.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! सत्त्वगुण सुख में लगाता है और रजोगुण कर्म में तथा तमोगुण तो ज्ञान को ढँककर प्रमाद में भी लगाता है॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! સત્વગુણ આત્માને સુખમાં જોડે છે, રજોગુણ આત્માને કર્મમાં જોડે છે અને તમોગુણ તો જ્ઞાનને ઢાંકી દઈને આત્માને કર્તવ્યવિમુખ બનાવે છે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Bharata, the mode of goodness conditions one to happiness; passion conditions one to fruitive action; and ignorance, covering one’s knowledge, binds one to madness.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रजस्तमश्चाभिभूय सत्त्वं भवति भारत ।\nरजः सत्त्वं तमश्चैव तमः सत्त्वं रजस्तथा ॥");
        } else if (i == 0) {
            this.tv1.setText("રજસ્તમશ્ચાભિભૂય સત્ત્વં ભવતિ ભારત,\nરજઃ સત્ત્વં તમશ્ચૈવ તમઃ સત્ત્વં રજસ્તથા.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Rajastamashchaabhibhooya sattwam bhavati bhaarata;\nRajah sattwam tamashchaiva tamah sattwam rajastathaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! रजोगुण और तमोगुण को दबाकर सत्त्वगुण, सत्त्वगुण और तमोगुण को दबाकर रजोगुण, वैसे ही सत्त्वगुण और रजोगुण को दबाकर तमोगुण होता है अर्थात बढ़ता है॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! રજોગુણ,  સત્વગુણ અને તમોગુણને જીતી વૃદ્ધિ પામે છે. તમોગુણ,  સત્વગુણ અને રજોગુણને જીતીને વૃદ્ધિ પામે છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("Sometimes the mode of goodness becomes prominent, defeating the modes of passion and ignorance, O son of Bharata. Sometimes the mode of passion defeats goodness and ignorance, and at other times ignorance defeats goodness and passion. In this way there is always competition for supremacy.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वद्वारेषु देहेऽस्मिन्प्रकाश उपजायते ।\nज्ञानं यदा तदा विद्याद्विवृद्धं सत्त्वमित्युत ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વદ્વારેષુ દેહેસ્મિન્પ્રકાશ ઉપજાયતે,\nજ્ઞાનં યદા તદા વિદ્યાદ્વિવૃદ્ધં સત્ત્વમિત્યુત.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Sarvadwaareshu dehe’smin prakaasha upajaayate;\nJnaanam yadaa tadaa vidyaa dvivriddham sattwamityuta.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  जिस समय इस देह में तथा अन्तःकरण और इन्द्रियों में चेतनता और विवेक शक्ति उत्पन्न होती है, उस समय ऐसा जानना चाहिए कि सत्त्वगुण बढ़ा है॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("દેહમાં સર્વ ઇન્દ્રિયોમાં જયારે જ્ઞાનનો પ્રકાશ પડે, ત્યારે સત્વની વૃદ્ધિ થઇ છે એમ માનવું.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("The manifestations of the mode of goodness can be experienced when all the gates of the body are illuminated by knowledge.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("लोभः प्रवृत्तिरारम्भः कर्मणामशमः स्पृहा ।\nरजस्येतानि जायन्ते विवृद्धे भरतर्षभ ॥");
        } else if (i == 0) {
            this.tv1.setText("લોભઃ પ્રવૃત્તિરારમ્ભઃ કર્મણામશમઃ સ્પૃહા,\nરજસ્યેતાનિ જાયન્તે વિવૃદ્ધે ભરતર્ષભ.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Lobhah pravrittir aarambhah karmanaam ashamah sprihaa;\nRajasyetaani jaayante vivriddhe bharatarshabha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! रजोगुण के बढ़ने पर लोभ, प्रवृत्ति, स्वार्थबुद्धि से कर्मों का सकामभाव से आरम्भ, अशान्ति और विषय भोगों की लालसा- ये सब उत्पन्न होते हैं॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભરતશ્રેષ્ઠ ! લોભ,પ્રવૃત્તિ,કર્માંરંભ, ઉચ્છુંખલતા અને ઈચ્છા એ સર્વ ચિન્હો રજોગુણના વધવાથી ઉત્પન્ન થાય છે.(૧૨) ");
        } else if (2 == i2) {
            this.tv2.setText("O chief of the Bharatas, when there is an increase in the mode of passion the symptoms of great attachment, fruitive activity, intense endeavor, and uncontrollable desire and hankering develop.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अप्रकाशोऽप्रवृत्तिश्च प्रमादो मोह एव च ।\nतमस्येतानि जायन्ते विवृद्धे कुरुनन्दन ॥");
        } else if (i == 0) {
            this.tv1.setText("અપ્રકાશોપ્રવૃત્તિશ્ચ પ્રમાદો મોહ એવ ચ,\nતમસ્યેતાનિ જાયન્તે વિવૃદ્ધે કુરુનન્દન.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Aprakaasho’pravrittishcha pramaado moha eva cha;\nTamasyetaani jaayante vivriddhe kurunandana.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  हे अर्जुन! तमोगुण के बढ़ने पर अन्तःकरण और इंन्द्रियों में अप्रकाश, कर्तव्य-कर्मों में अप्रवृत्ति और प्रमाद अर्थात व्यर्थ चेष्टा और निद्रादि अन्तःकरण की मोहिनी वृत्तियाँ - ये सब ही उत्पन्न होते हैं॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("વિવેકનો નાશ , કંટાળો, દુર્લક્ષ અને મોહ  એ  તમોગુણના વધવાથી ઉત્પન્ન થાય છે.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("When there is an increase in the mode of ignorance, O son of Kuru, darkness, inertia, madness and illusion are manifested.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा सत्त्वे प्रवृद्धे तु प्रलयं याति देहभृत्‌ ।\nतदोत्तमविदां लोकानमलान्प्रतिपद्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા સત્ત્વે પ્રવૃદ્ધે તુ પ્રલયં યાતિ દેહભૃત્,\nતદોત્તમવિદાં લોકાનમલાન્પ્રતિપદ્યતે.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa sattwe pravriddhe tu pralayam yaati dehabhrit;\nTadottamavidaam lokaan amalaan pratipadyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जब यह मनुष्य सत्त्वगुण की वृद्धि में मृत्यु को प्राप्त होता है, तब तो उत्तम कर्म करने वालों के निर्मल दिव्य स्वर्गादि लोकों को प्राप्त होता है॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("સત્વગુણની વૃદ્ધિ થઇ હોય ત્યારે પ્રાણીમૃત્યુ પામે, તો તે મહતત્વાદિકને જાણનારા લોકોને જે ઉત્તમ લોકની પ્રાપ્તિ થાય છે તે ઉત્તમલોકમાં જાય છે.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("When one dies in the mode of goodness, he attains to the pure higher planets of the great sages.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रजसि प्रलयं गत्वा कर्मसङ्‍गिषु जायते ।\nतथा प्रलीनस्तमसि मूढयोनिषु जायते ॥");
        } else if (i == 0) {
            this.tv1.setText("રજસિ પ્રલયં ગત્વા કર્મસઙ્ગિષુ જાયતે,\nતથા પ્રલીનસ્તમસિ મૂઢયોનિષુ જાયતે.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Rajasi pralayam gatwaa karmasangishu jaayate;\nTathaa praleenastamasi moodhayonishu jaayate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("रजोगुण के बढ़ने पर मृत्यु को प्राप्त होकर कर्मों की आसक्ति वाले मनुष्यों में उत्पन्न होता है तथा तमोगुण के बढ़ने पर मरा हुआ मनुष्य कीट, पशु आदि मूढ़योनियों में उत्पन्न होता है॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("રજોગુણની વૃદ્ધિ થઇ હોય ત્યારે પ્રાણી મૃત્યુ પામે તો તે કર્મોમાં આસક્તિ રાખનાર પ્રાણીઓમાં જન્મે છે. અને  તમોગુણની વૃદ્ધિ થઇ હોય ત્યારે પ્રાણી મૃત્યુ પામે તો તેનો પશુઆદિ મૂઢ યોનીમાં જન્મ થાય છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("When one dies in the mode of passion, he takes birth among those engaged in fruitive activities; and when one dies in the mode of ignorance, he takes birth in the animal kingdom.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मणः सुकृतस्याहुः सात्त्विकं निर्मलं फलम्‌ ।\nरजसस्तु फलं दुःखमज्ञानं तमसः फलम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મણઃ સુકૃતસ્યાહુઃ સાત્ત્વિકં નિર્મલં ફલમ્,\nરજસસ્તુ ફલં દુઃખમજ્ઞાનં તમસઃ ફલમ્.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Karmanah sukritasyaahuh saattwikam nirmalam phalam;\nRajasastu phalam duhkham ajnaanam tamasah phalam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्रेष्ठ कर्म का तो सात्त्विक अर्थात् सुख, ज्ञान और वैराग्यादि निर्मल फल कहा है, राजस कर्म का फल दुःख एवं तामस कर्म का फल अज्ञान कहा है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("પુણ્ય કર્મનું ફળ સાત્વિક અને નિર્મળ જાણવું, રજોગુણનું ફળ દુઃખદ અને તમોગુણનું ફળ અજ્ઞાન જાણવું.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("The result of pious action is pure and is said to be in the mode of goodness. But action done in the mode of passion results in misery, and action performed in the mode of ignorance results in foolishness.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सत्त्वात्सञ्जायते ज्ञानं रजसो लोभ एव च ।\nप्रमादमोहौ तमसो भवतोऽज्ञानमेव च ॥");
        } else if (i == 0) {
            this.tv1.setText("સત્ત્વાત્સઞ્જાયતે જ્ઞાનં રજસો લોભ એવ ચ,\nપ્રમાદમોહૌ તમસો ભવતોજ્ઞાનમેવ ચ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Sattwaat sanjaayate jnaanam rajaso lobha eva cha;\nPramaadamohau tamaso bhavato’jnaanameva cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" सत्त्वगुण से ज्ञान उत्पन्न होता है और रजोगुण से निःसन्देह लोभ तथा तमोगुण से प्रमाद       और मोह   उत्पन्न होते हैं और अज्ञान भी होता है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("સત્વગુણમાંથી જ્ઞાન, રજોગુણમાંથી લોભ અને તમોગુણમાંથી આળસ,મોહ અને અજ્ઞાન ઉત્પન્ન થાય છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("From the mode of goodness, real knowledge develops; from the mode of passion, greed develops; and from the mode of ignorance develop foolishness, madness and illusion.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ऊर्ध्वं गच्छन्ति सत्त्वस्था मध्ये तिष्ठन्ति राजसाः ।\nजघन्यगुणवृत्तिस्था अधो गच्छन्ति तामसाः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઊર્ધ્વં ગચ્છન્તિ સત્ત્વસ્થા મધ્યે તિષ્ઠન્તિ રાજસાઃ,\nજઘન્યગુણવૃત્તિસ્થા અધો ગચ્છન્તિ તામસાઃ.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Oordhwam gacchanti sattwasthaa madhye tishthanti raajasaah;\nJaghanyagunavrittisthaa adho gacchanti taamasaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सत्त्वगुण में स्थित पुरुष स्वर्गादि उच्च लोकों को जाते हैं, रजोगुण में स्थित राजस पुरुष मध्य में अर्थात मनुष्य लोक में ही रहते हैं और तमोगुण के कार्यरूप निद्रा, प्रमाद और आलस्यादि में स्थित तामस पुरुष अधोगति को अर्थात कीट, पशु आदि नीच योनियों को तथा नरकों को प्राप्त होते हैं॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સત્વગુણી હોય છે તેઓ દેવોની યોનિમાં જાય છે. રજોગુણી મનુષ્ય યોનિમાં જાય છે અને તમોગુણી કનિષ્ટ ગુણમાં રત રહી પશુ યોનિ પામે છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("Those situated in the mode of goodness gradually go upward to the higher planets; those in the mode of passion live on the earthly planets; and those in the abominable mode of ignorance go down to the hellish worlds.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नान्यं गुणेभ्यः कर्तारं यदा द्रष्टानुपश्यति ।\nगुणेभ्यश्च परं वेत्ति मद्भावं सोऽधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("નાન્યં ગુણેભ્યઃ કર્તારં યદા દ્રષ્ટાનુપશ્યતિ,\nગુણેભ્યશ્ચ પરં વેત્તિ મદ્ભાવં સોધિગચ્છતિ.(૧૯)\n");
        } else if (2 == i) {
            this.tv1.setText("Naanyam gunebhyah kartaaram yadaa drashtaanupashyati;\nGunebhyashcha param vetti madbhaavam so’dhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जिस समय दृष्टा तीनों गुणों के अतिरिक्त अन्य किसी को कर्ता नहीं देखता और तीनों गुणों से अत्यन्त परे सच्चिदानन्दघनस्वरूप मुझ परमात्मा को तत्त्व से जानता है, उस समय वह मेरे स्वरूप को प्राप्त होता है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("જીવાત્મા જયારે આ ત્રણે ગુણોથી ભિન્ન કર્તા બીજા કોઈ નથી એમ સમજે છે અને પોતાના ગુણોને અતીત સમજે  છે ત્યારે તે મારા સ્વરૂપ ને પામે છે.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("When one properly sees that in all activities no other performer is at work than these modes of nature and he knows the Supreme Lord, who is transcendental to all these modes, he attains My spiritual nature.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("गुणानेतानतीत्य त्रीन्देही देहसमुद्भवान्‌ ।\nजन्ममृत्युजरादुःखैर्विमुक्तोऽमृतमश्नुते ॥");
        } else if (i == 0) {
            this.tv1.setText("ગુણાનેતાનતીત્ય ત્રીન્દેહી દેહસમુદ્ભવાન્,\nજન્મમૃત્યુજરાદુઃખૈર્વિમુક્તોમૃતમશ્નુતે.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Gunaanetaanateetya treen dehee dehasamudbhavaan;\nJanmamrityujaraaduhkhair vimukto’mritamashnute.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यह पुरुष शरीर की  उत्पत्ति के कारणरूप इन तीनों गुणों को उल्लंघन करके जन्म, मृत्यु, वृद्धावस्था और सब प्रकार के दुःखों से मुक्त हुआ परमानन्द को प्राप्त होता है॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("જીવ દેહ દ્વારા ઉત્પન્ન થયેલા ત્રણે ગુણોને અતિક્રમી જન્મ, મૃત્યુ, વૃદ્ધાવસ્થા વગેરે દુઃખોથી મુક્ત થઇ મોક્ષને પ્રાપ્ત કરે છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("When the embodied being is able to transcend these three modes associated with the material body, he can become free from birth, death, old age and their distresses and can enjoy nectar even in this life.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nकैर्लिङ्‍गैस्त्रीन्गुणानेतानतीतो भवति प्रभो ।\nकिमाचारः कथं चैतांस्त्रीन्गुणानतिवर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nકૈર્લિંગૈસ્ત્રીન્ગુણાનેતાનતીતો ભવતિ પ્રભો,\nકિમાચારઃ કથં ચૈતાંસ્ત્રીન્ગુણાનતિવર્તતે.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nKairlingais treen gunaanetaan ateeto bhavati prabho;\nKimaachaarah katham chaitaam streen gunaan ativartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  अर्जुन बोले- इन तीनों गुणों से अतीत पुरुष किन-किन लक्षणों से युक्त होता है और किस प्रकार के आचरणों वाला होता है तथा हे प्रभो! मनुष्य किस उपाय से इन तीनों गुणों से अतीत होता है?॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે પ્રભો ! આ ત્રણે ગુણોનો ત્યાગ કરીને આગળ વધેલા જીવને કેવી રીતે જાણવો? તેનો આચાર કેવો હોય ? અને તે ત્રણે ગુણોને કેવી રીતે ઓળંગી જાય છે?(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna inquired: O my dear Lord, by which symptoms is one known who is transcendental to these three modes? What is his behavior? And how does he transcend the modes of nature?(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nप्रकाशं च प्रवृत्तिं च मोहमेव च पाण्डव ।\nन द्वेष्टि सम्प्रवृत्तानि न निवृत्तानि काङ्‍क्षति ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nપ્રકાશં ચ પ્રવૃત્તિં ચ મોહમેવ ચ પાણ્ડવ,\nન દ્વેષ્ટિ સમ્પ્રવૃત્તાનિ ન નિવૃત્તાનિ કાઙ્ક્ષતિ.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nPrakaasham cha pravrittim cha mohameva cha paandava;\nNa dweshti sampravrittaani na nivrittaani kaangkshati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्री भगवान बोले- हे अर्जुन! जो पुरुष सत्त्वगुण के कार्यरूप प्रकाशको और रजोगुण के कार्यरूप प्रवृत्ति को तथा तमोगुण के कार्यरूप मोह को     भी न तो प्रवृत्त होने पर उनसे द्वेष करता है और न निवृत्त होने पर उनकी आकांक्षा करता है। ॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : હે પાંડવ ! જે જ્ઞાન, કર્મવૃત્તિ અને અજ્ઞાન પ્રાપ્ત થવા છતાંય દ્વેષ કરતો નથી અને તેનો નાશ થતાં તેની કામના કરતો નથી.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Blessed Lord said: Light, activity and delusion,—when they are present, O Arjuna, he hates not, nor does he long for them when they are absent!(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उदासीनवदासीनो गुणैर्यो न विचाल्यते ।\nगुणा वर्तन्त इत्येव योऽवतिष्ठति नेङ्‍गते ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉદાસીનવદાસીનો ગુણૈર્યો ન વિચાલ્યતે,\nગુણા વર્તન્ત ઇત્યેવ યોવતિષ્ઠતિ નેઙ્ગતે.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Udaaseenavadaaseeno gunairyo na vichaalyate;\nGunaa vartanta ityeva yo’vatishthati nengate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो साक्षी के सदृश स्थित हुआ गुणों द्वारा विचलित नहीं किया जा सकता और गुण ही गुणों में बरतते  हैं- ऐसा समझता हुआ जो सच्चिदानन्दघन परमात्मा में एकीभाव से स्थित रहता है एवं उस स्थिति से कभी विचलित नहीं होता॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે ઉદાસીન રહી એ ત્રણે ગુણોથી વિકાર પામતો નથી અને ગુણો જ કર્તા છે એમ માની સ્થિર રહે છે, પોતે કંઈ જ કરતો નથી.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("He who, seated like one unconcerned, is not moved by the qualities, and who, knowing that the qualities are active, is self-centred and moves not,(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("समदुःखसुखः स्वस्थः समलोष्टाश्मकाञ्चनः ।\nतुल्यप्रियाप्रियो धीरस्तुल्यनिन्दात्मसंस्तुतिः ॥");
        } else if (i == 0) {
            this.tv1.setText("સમદુઃખસુખઃ સ્વસ્થઃ સમલોષ્ટાશ્મકાઞ્ચનઃ,\nતુલ્યપ્રિયાપ્રિયો ધીરસ્તુલ્યનિન્દાત્મસંસ્તુતિઃ.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Samaduhkhasukhah swasthah samaloshtaashmakaanchanah;\nTulyapriyaapriyo dheeras tulyanindaatma samstutih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो निरन्तर आत्म भाव में स्थित, दुःख-सुख को समान समझने वाला, मिट्टी, पत्थर और स्वर्ण में समान भाव वाला, ज्ञानी, प्रिय तथा अप्रिय को एक-सा मानने वाला और अपनी निन्दा-स्तुति में भी समान भाव वाला है॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સુખ-દુઃખને સમાન ગણે છે, આત્મસ્વરૂપમાં સ્થિર રહે છે, માટી, પથ્થર અને સોનાને સમાન ગણે છે, પ્રિય અને અપ્રિય ને સમાન ગણે છે, નિંદા અને સ્તુતિને સમાન ગણે છે અને જે ધીરજ વાળો છે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Alike in pleasure and pain, who dwells in the Self, to whom a clod of earth, stone and gold are alike, to whom the dear and the unfriendly are alike, firm, the same in censure and praise,(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मानापमानयोस्तुल्यस्तुल्यो मित्रारिपक्षयोः ।\nसर्वारम्भपरित्यागी गुणातीतः सा उच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("માનાપમાનયોસ્તુલ્યસ્તુલ્યો મિત્રારિપક્ષયોઃ,\nસર્વારમ્ભપરિત્યાગી ગુણાતીતઃ સ ઉચ્યતે.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Maanaapamaanayostulyas tulyo mitraaripakshayoh;\nSarvaarambhaparityaagee gunaateetah sa uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  जो मान और अपमान में सम है, मित्र और वैरी के पक्ष में भी सम है एवं सम्पूर्ण आरम्भों में कर्तापन के अभिमान से रहित है, वह पुरुष गुणातीत कहा जाता है॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેને માટે માન-અપમાન સમાન છે, જે મિત્ર અને શત્રુને સમાન ગણે છે અને જેણે સર્વ કર્મોનો પરિત્યાગ કર્યો છે, તે ગુણાતીત કહેવાય છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("The same in honour and dishonour, the same to friend and foe, abandoning all undertakings—he is said to have crossed the qualities.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मां च योऽव्यभिचारेण भक्तियोगेन सेवते ।\nस गुणान्समतीत्येतान्ब्रह्मभूयाय कल्पते ॥");
        } else if (i == 0) {
            this.tv1.setText("માં ચ યોવ્યભિચારેણ ભક્તિયોગેન સેવતેસ,\nસ ગુણાન્સમતીત્યૈતાન્ બ્રહ્મભૂયાય કલ્પતે.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Maam cha yo’vyabhichaarena bhaktiyogena sevate;\nSa gunaan samateetyaitaan brahmabhooyaaya kalpate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और जो पुरुष अव्यभिचारी भक्ति योग द्वारा मुझको निरन्तर भजता है, वह भी इन तीनों गुणों को भलीभाँति लाँघकर सच्चिदानन्दघन ब्रह्म को प्राप्त होने के लिए योग्य बन जाता है॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે એકનિષ્ઠ ભક્તિથી મારી સેવા કરે છે, તે આ ત્રણે ગુણોને શ્રેષ્ઠ રીતે જીતી બ્રહ્મસ્વરૂપ થવાને યોગ્ય બને છે.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("And he who serves Me with unswerving devotion, he, crossing beyond the qualities, is fit for becoming Brahman.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ब्रह्मणो हि प्रतिष्ठाहममृतस्याव्ययस्य च ।\nशाश्वतस्य च धर्मस्य सुखस्यैकान्तिकस्य च ॥");
        } else if (i == 0) {
            this.tv1.setText("બ્રહ્મણો હિ પ્રતિષ્ઠાહમમૃતસ્યાવ્યયસ્ય ચ,\nશાશ્વતસ્ય ચ ધર્મસ્ય સુખસ્યૈકાન્તિકસ્ય ચ.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Brahmano hi pratishthaa’ham amritasyaavyayasya cha;\nShaashwatasya cha dharmasya sukhasyaikaantikasya cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि उस अविनाशी परब्रह्म का और अमृत का तथा नित्य धर्म का और अखण्ड एकरस आनन्द का आश्रय मैं हूँ॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેમ કે અવિનાશી અને નિર્વિકાર બ્રહ્મનું, સનાતન ધર્મનું અને શાશ્વત સુખનું સ્થાન હું જ છું.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("For I am the abode of Brahman, the immortal and the immutable, of everlasting Dharma and of absolute bliss.(27)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay14.this, ShlokListPlay14.this);
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

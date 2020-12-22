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

public class ShlokListPlay6 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay6.this.favflag == 0) {
                    ShlokListPlay6.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.favflag = 1;
                    shlokListPlay6.save();
                } else if (1 == ShlokListPlay6.this.favflag) {
                    ShlokListPlay6.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay6 shlokListPlay62 = ShlokListPlay6.this;
                    shlokListPlay62.favflag = 0;
                    shlokListPlay62.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay6.this.calll++;
                ShlokListPlay6.this.slkset();
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
                ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                shlokListPlay6.calll--;
                ShlokListPlay6.this.slkset();
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
        edit.putString("A6" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A6" + this.calll, "0");
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
        if (44 == this.calll) {
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
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.callf1 = 0;
                    shlokListPlay6.slkset();
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
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.callf1 = 2;
                    shlokListPlay6.slkset();
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
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.callf1 = 1;
                    shlokListPlay6.slkset();
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
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.callf2 = 0;
                    shlokListPlay6.slkset();
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
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.callf2 = 2;
                    shlokListPlay6.slkset();
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
                    ShlokListPlay6 shlokListPlay6 = ShlokListPlay6.this;
                    shlokListPlay6.callf2 = 1;
                    shlokListPlay6.slkset();
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
            shlok13_14();
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
            shlok20_21_22_23();
        } else if (20 == i3) {
            shlok24();
        } else if (21 == i3) {
            shlok25();
        } else if (22 == i3) {
            shlok26();
        } else if (23 == i3) {
            shlok27();
        } else if (24 == i3) {
            shlok28();
        } else if (25 == i3) {
            shlok29();
        } else if (26 == i3) {
            shlok30();
        } else if (27 == i3) {
            shlok31();
        } else if (28 == i3) {
            shlok32();
        } else if (29 == i3) {
            shlok33();
        } else if (30 == i3) {
            shlok34();
        } else if (31 == i3) {
            shlok35();
        } else if (32 == i3) {
            shlok36();
        } else if (33 == i3) {
            shlok37();
        } else if (34 == i3) {
            shlok38();
        } else if (35 == i3) {
            shlok39();
        } else if (36 == i3) {
            shlok40();
        } else if (37 == i3) {
            shlok41();
        } else if (38 == i3) {
            shlok42();
        } else if (39 == i3) {
            shlok43();
        } else if (40 == i3) {
            shlok44();
        } else if (41 == i3) {
            shlok45();
        } else if (42 == i3) {
            shlok46();
        } else if (43 == i3) {
            shlok47();
        } else if (44 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 6");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ०६ \nआत्मसंयमयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૬ \nઆત્મ સન્યાસ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 06\naatm sanyas yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, अष्टांगयोग मन तथा इन्द्रियों को नियंत्रित करता है और ध्यान को परमात्मा पर केंद्रित करता है। इस विधि की परिणति समाधी में होती है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૬ માં યોગ તત્વ પ્રાપ્ત કરવાન આસનો, અષ્ટાંગ યોગ, ચંચળ મન ને અભ્યાસ થી વશ કરવું , આત્મા વડે આત્મા નો ઉદ્ધાર કરવો વગેરે નું વર્ણન કરેલ છે. ફળની આશા વગર પોતાનું કર્તવ્ય કર્મ કરનાર તે સંન્યાસી અને યોગી છે. સંકલ્પ નો સંન્યાસ (ત્યાગ) કર્યા વિના યોગી થઇ શકતું નથી. યોગ પ્રાપ્તિ માટે યોગીને 'કર્મ' એ 'સાધન' છે. તે જ યોગી યોગ પ્રાપ્ત કરે પછી કર્મત્યાગ એ 'સાધન' છે. કૃષ્ણ અર્જુન ને યોગી થવાનું કહે છે.");
        } else if (2 == i) {
            this.tv1.setText("Sixth adhyay tells, Astanga-yoga, a mechanical meditative practice, controls the mind and the senses and focuses concentration on Paramatma (the Supersoul, the form of the Lord situated in the heart). This practice culminates in samadhi, full consciousness of the Supreme.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nअनाश्रितः कर्मफलं कार्यं कर्म करोति यः ।\nस सन्न्यासी च योगी च न निरग्निर्न चाक्रियः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઅનાશ્રિતઃ કર્મફલં કાર્યં કર્મ કરોતિ યઃ,\nસ સંન્યાસી ચ યોગી ચ ન નિરગ્નિર્ન ચાક્રિયઃ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nAnaashritah karmaphalam kaaryam karma karoti yah;\nSa sannyaasi cha yogee cha na niragnirna chaakriyah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- जो पुरुष कर्मफल का आश्रय न लेकर करने योग्य कर्म करता है, वह संन्यासी तथा योगी है और केवल अग्नि का त्याग करने वाला संन्यासी नहीं है तथा केवल क्रियाओं का त्याग करने वाला योगी नहीं है॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે: હે પાર્થ ! કર્મ ના ફળને ન ચાહીને કરવા યોગ્ય કર્મ કરેછે તેજ સંન્યાસી અને કર્મયોગી છે.કેવળ અગ્નિનો ત્યાગ કરનારો સંન્યાસી નથી તેમજ કેવળ ક્રિયાઓને ત્યાગનારો પણ સંન્યાસી કે યોગી નથી.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: One who is unattached to the fruits of his work and who works as he is obligated is in the renounced order of life, and he is the true mystic, not he who lights no fire and performs no duty.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यं सन्न्यासमिति प्राहुर्योगं तं विद्धि पाण्डव ।\nन ह्यसन्न्यस्तसङ्‍कल्पो योगी भवति कश्चन ॥");
        } else if (i == 0) {
            this.tv1.setText("યં સંન્યાસમિતિ પ્રાહુર્યોગં તં વિદ્ધિ પાણ્ડવ,\nન હ્યસંન્યસ્તસઙ્કલ્પો યોગી ભવતિ કશ્ચન.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Yam sannyaasamiti praahuryogam tam viddhi paandava;\nNa hyasannyastasankalpo yogee bhavati kashchana.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जिसको संन्यास (गीता अध्याय 3 श्लोक 3 की टिप्पणी में इसका खुलासा अर्थ लिखा है।) ऐसा कहते हैं, उसी को तू योग (गीता अध्याय 3 श्लोक 3 की टिप्पणी में इसका खुलासा अर्थ लिखा है।) जान क्योंकि संकल्पों का त्याग न करने वाला कोई भी पुरुष योगी नहीं होता॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાંડવ ! જેને સંન્યાસ કહે છે તેને જ યોગ સમજ.મનના સંકલ્પોને ત્યાગ કર્યા સિવાય કોઈ પણ મનુષ્ય કર્મયોગી થઇ શકતો નથી.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("What is called renunciation you should know to be the same as yoga, or linking oneself with the Supreme, O son of Pandu, for one can never become a yogi unless he renounces the desire for sense gratification.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आरुरुक्षोर्मुनेर्योगं कर्म कारणमुच्यते ।\nयोगारूढस्य तस्यैव शमः कारणमुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("આરુરુક્ષોર્મુનેર્યોગં કર્મ કારણમુચ્યતે,\nયોગારૂઢસ્ય તસ્યૈવ શમઃ કારણમુચ્યતે.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Aarurukshormuneryogam karma kaaranamuchyate;\nYogaaroodhasya tasyaiva shamah kaaranamuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("योग में आरूढ़ होने की इच्छा वाले मननशील पुरुष के लिए योग की प्राप्ति में निष्काम भाव से कर्म करना ही हेतु कहा जाता है और योगारूढ़ हो जाने पर उस योगारूढ़ पुरुष का जो सर्वसंकल्पों का अभाव है, वही कल्याण में हेतु कहा जाता है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે યોગીને ધ્યાનયોગ સિદ્ધ કરવો હોય તેને માટે વિહિત કર્મોનું આચરણ સાધન છે. પરંતુ યોગપ્રાપ્તી થઇ જાય પછી તેને સંપૂર્ણ કરવા માટે કર્મ નિવૃત્તિ જ શ્રેષ્ઠ સાધન બની જાય છે.પછી તે કર્મફળ માં લુબ્ધ થતો નથી.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("For one who is a neophyte in the eightfold yoga system, work is said to be the means; and for one who is already elevated in yoga, cessation of all material activities is said to be the means.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा हि नेन्द्रियार्थेषु न कर्मस्वनुषज्जते ।\nसर्वसङ्‍कल्पसन्न्यासी योगारूढ़स्तदोच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા હિ નેન્દ્રિયાર્થેષુ ન કર્મસ્વનુષજ્જતે,\nસર્વસઙ્કલ્પસંન્યાસી યોગારૂઢસ્તદોચ્યતે.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa hi nendriyaartheshu na karmaswanushajjate;\nSarvasankalpasannyaasee yogaaroodhas tadochyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस काल में न तो इन्द्रियों के भोगों में और न कर्मों में ही आसक्त होता है, उस काल में सर्वसंकल्पों का त्यागी पुरुष योगारूढ़ कहा जाता है॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("જયારે મનુષ્ય ઇન્દ્રિયોના વિષયમાં અને કર્મોમાં આસક્ત થતો નથી અને સર્વ સંકલ્પોને છોડી દે છે ત્યારે તે યોગારૂઢ કહેવાય છે.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("A person is said to be elevated in yoga when, having renounced all material desires, he neither acts for sense gratification nor engages in fruitive activities.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उद्धरेदात्मनाऽत्मानं नात्मानमवसादयेत्‌ ।\nआत्मैव ह्यात्मनो बन्धुरात्मैव रिपुरात्मनः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉદ્ધરેદાત્મનાત્માનં નાત્માનમવસાદયેત્,\nઆત્મૈવ હ્યાત્મનો બન્ધુરાત્મૈવ રિપુરાત્મનઃ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Uddharedaatmanaatmaanam naatmaanamavasaadayet;\nAtmaiva hyaatmano bandhuraatmaiva ripuraatmanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अपने द्वारा अपना संसार-समुद्र से उद्धार करे और अपने को अधोगति में न डाले क्योंकि यह मनुष्य आप ही तो अपना मित्र है और आप ही अपना शत्रु है॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("આત્મા વડે આત્માનો ઉદ્ધાર કરવો પરંતુ આત્માને અધોગતિ ના માર્ગે લઇ જવો નહિ, કેમ કે આત્મા જ આત્માનો બન્ધુ છે અને આત્મા જ આત્માનો શત્રુ છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("One must deliver himself with the help of his mind, and not degrade himself. The mind is the friend of the conditioned soul, and his enemy as well.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बन्धुरात्मात्मनस्तस्य येनात्मैवात्मना जितः ।\nअनात्मनस्तु शत्रुत्वे वर्तेतात्मैव शत्रुवत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("બન્ધુરાત્માત્મનસ્તસ્ય યેનાત્મૈવાત્મના જિતઃ,\nઅનાત્મનસ્તુ શત્રુત્વે વર્તેતાત્મૈવ શત્રુવત્.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Bandhuraatmaa’tmanastasya yenaatmaivaatmanaa jitah;\nAnaatmanastu shatrutwe vartetaatmaiva shatruvat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस जीवात्मा द्वारा मन और इन्द्रियों सहित शरीर जीता हुआ है, उस जीवात्मा का तो वह आप ही मित्र है और जिसके द्वारा मन तथा इन्द्रियों सहित शरीर नहीं जीता गया है, उसके लिए वह आप ही शत्रु के सदृश शत्रुता में बर्तता है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેણે આત્માને જીતેન્દ્રિય બનાવ્યો છે,જીત્યો છે,તેનો આત્મા બન્ધુ છે.પરંતુ જેના આત્મા એ ઇન્દ્રિયો પર વિજય મેળવ્યો નથી તેનો આત્મા જ તેનો શત્રુ છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("For him who has conquered the mind, the mind is the best of friends; but for one who has failed to do so, his mind will remain the greatest enemy.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("जितात्मनः प्रशान्तस्य परमात्मा समाहितः ।\nशीतोष्णसुखदुःखेषु तथा मानापमानयोः ॥");
        } else if (i == 0) {
            this.tv1.setText("જિતાત્મનઃ પ્રશાન્તસ્ય પરમાત્મા સમાહિતઃ,\nશીતોષ્ણસુખદુઃખેષુ તથા માનાપમાનયોઃ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Jitaatmanah prashaantasya paramaatmaa samaahitah;\nSheetoshna sukha duhkheshu tathaa maanaapamaanayoh.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सरदी-गरमी और सुख-दुःखादि में तथा मान और अपमान में जिसके अन्तःकरण की वृत्तियाँ भलीभाँति शांत हैं, ऐसे स्वाधीन आत्मावाले पुरुष के ज्ञान में सच्चिदानन्दघन परमात्मा सम्यक्‌ प्रकार से स्थित है अर्थात उसके ज्ञान में परमात्मा के सिवा अन्य कुछ है ही नहीं॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેણે પોતાનું મન ટાઢ-તડકો,સુખ-દુઃખ,માન-અપમાન વગેરે માં એક સરખું રાખ્યું છે, જે નિર્વિકાર રહેછે,તે સર્વ સ્થિતિ માં સમાન ભાવે રહે છે.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("For one who has conquered the mind, the Supersoul is already reached, for he has attained tranquillity. To such a man happiness and distress, heat and cold, honor and dishonor are all the same.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञानविज्ञानतृप्तात्मा कूटस्थो विजितेन्द्रियः ।\nयुक्त इत्युच्यते योगी समलोष्टाश्मकांचनः ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞાનવિજ્ઞાનતૃપ્તાત્મા કૂટસ્થો વિજિતેન્દ્રિયઃ,\nયુક્ત ઇત્યુચ્યતે યોગી સમલોષ્ટાશ્મકાઞ્ચનઃ.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Jnaana vijnaana triptaatmaa kootastho vijitendriyah;\nYuktah ityuchyate yogee samaloshtaashmakaanchanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसका अन्तःकरण ज्ञान-विज्ञान से तृप्त है, जिसकी स्थिति विकाररहित है, जिसकी इन्द्रियाँ भलीभाँति जीती हुई हैं और जिसके लिए मिट्टी, पत्थर और सुवर्ण समान हैं, वह योगी युक्त अर्थात भगवत्प्राप्त है, ऐसे कहा जाता है॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે જ્ઞાન અને વિજ્ઞાન વડે તૃપ્ત થયો છે,જે જીતેન્દ્રિય છે,જે માટી તથા સોનાને સરખું ગણે છે તે યોગી “યોગસિદ્ધ “કહેવાય છે.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("A person is said to be established in self-realization and is called a yogi [or mystic] when he is fully satisfied by virtue of acquired knowledge and realization. Such a person is situated in transcendence and is self-controlled. He sees everything—whether it be pebbles, stones or gold—as the same.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सुहृन्मित्रार्युदासीनमध्यस्थद्वेष्यबन्धुषु ।\nसाधुष्वपि च पापेषु समबुद्धिर्विशिष्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("સુહૃન્મિત્રાર્યુદાસીનમધ્યસ્થદ્વેષ્યબન્ધુષુ,\nસાધુષ્વપિ ચ પાપેષુ સમબુદ્ધિર્વિશિષ્યતે.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Suhrinmitraary udaaseena madhyastha dweshya bandhushu;\nSaadhushwapi cha paapeshu samabuddhirvishishyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सुहृद् (स्वार्थ रहित सबका हित करने वाला), मित्र, वैरी, उदासीन (पक्षपातरहित), मध्यस्थ (दोनों ओर की भलाई चाहने वाला), द्वेष्य और बन्धुगणों में, धर्मात्माओं में और पापियों में भी समान भाव रखने वाला अत्यन्त श्रेष्ठ है॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("સુહ્યદ,મિત્ર,શત્રુ,ઉદાસીન,મધ્યસ્થ,દ્વેષ ને પાત્ર અને સંબંધીજનમાં,સાધુઓમાં કે પપીઓમાં જે યોગીની સમબુદ્ધિ હોય છે,તે સર્વ માં શ્રેષ્ઠ યોગી છે. (૯)");
        } else if (2 == i2) {
            this.tv2.setText("A person is considered still further advanced when he regards honest well-wishers, affectionate benefactors, the neutral, mediators, the envious, friends and enemies, the pious and the sinners all with an equal mind.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("योगी युञ्जीत सततमात्मानं रहसि स्थितः ।\nएकाकी यतचित्तात्मा निराशीरपरिग्रहः ॥");
        } else if (i == 0) {
            this.tv1.setText("યોગી યુઞ્જીત સતતમાત્માનં રહસિ સ્થિતઃ,\nએકાકી યતચિત્તાત્મા નિરાશીરપરિગ્રહઃ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Yogee yunjeeta satatamaatmaanam rahasi sthitah;\nEkaakee yatachittaatmaa niraasheeraparigrahah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मन और इन्द्रियों सहित शरीर को वश में रखने वाला, आशारहित और संग्रहरहित योगी अकेला ही एकांत स्थान में स्थित होकर आत्मा को निरंतर परमात्मा में लगाए॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("માટે યોગીઓએ ચિત્ત ને તથા દેહ ને વશ કરી,આશારહિત અને પરીગ્રહરહિત થઈને , એકાંત માં નિવાસ કરી અંત:કરણને સદા યોગાભ્યાસ માં જોડવું.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("A transcendentalist should always engage his body, mind and self in relationship with the Supreme; he should live alone in a secluded place and should always carefully control his mind. He should be free from desires and feelings of possessiveness.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शुचौ देशे प्रतिष्ठाप्य स्थिरमासनमात्मनः ।\nनात्युच्छ्रितं नातिनीचं चैलाजिनकुशोत्तरम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શુચૌ દેશે પ્રતિષ્ઠાપ્ય સ્થિરમાસનમાત્મનઃ,\nનાત્યુચ્છ્રિતં નાતિનીચં ચૈલાજિનકુશોત્તરમ્.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Shuchau deshe pratishthaapya sthiramaasanamaatmanah;\nNaatyucchritam naatineecham chailaajinakushottaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("शुद्ध भूमि में, जिसके ऊपर क्रमशः कुशा, मृगछाला और वस्त्र बिछे हैं, जो न बहुत ऊँचा है और न बहुत नीचा, ऐसे अपने आसन को स्थिर स्थापन करके॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("યોગીએ પવિત્ર સ્થાનમાં પહેલાં દર્ભ ,તેના પર મૃગચર્મ અને તેના પર આસન પાથરવું. એ આસન પર સ્થિરતાથી બેસવું,આસન વધુ પડતું ઊંચું કે નીચું ન રહે તેનું ધ્યાન રાખવું.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("To practice yoga, one should go to a secluded place and should lay kusa grass on the ground and then cover it with a deerskin and a soft cloth. The seat should be neither too high nor too low and should be situated in a sacred place.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्रैकाग्रं मनः कृत्वा यतचित्तेन्द्रियक्रियः ।\nउपविश्यासने युञ्ज्याद्योगमात्मविशुद्धये ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્રૈકાગ્રં મનઃ કૃત્વા યતચિત્તેન્દ્રિયક્રિયઃ,\nઉપવિશ્યાસને યુઞ્જ્યાદ્યોગમાત્મવિશુદ્ધયે.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Tatraikaagram manah kritwaa yatachittendriyakriyah;\nUpavishyaasane yunjyaadyogamaatmavishuddhaye.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उस आसन पर बैठकर चित्त और इन्द्रियों की क्रियाओं को वश में रखते हुए मन को एकाग्र करके अन्तःकरण की शुद्धि के लिए योग का अभ्यास करे॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("તૈયાર કરેલા તે આસન પર બેસી ,ચિત્તને એકાગ્ર કરી ,ઈન્દ્રિયોને જીતી,પોતાના અંત:કરણની શુદ્ધિ માટે યોગ નો અભ્યાસ કરવો.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("The yogi should then sit on it very firmly and practice yoga to purify the heart by controlling his mind, senses and activities and fixing the mind on one point.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13_14() {
        setTitle("Shlok 13,14");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("समं कायशिरोग्रीवं धारयन्नचलं स्थिरः ।\nसम्प्रेक्ष्य नासिकाग्रं स्वं दिशश्चानवलोकयन्‌ ॥\nप्रशान्तात्मा विगतभीर्ब्रह्मचारिव्रते स्थितः ।\nमनः संयम्य मच्चित्तो युक्त आसीत मत्परः ॥");
        } else if (i == 0) {
            this.tv1.setText("સમં કાયશિરોગ્રીવં ધારયન્નચલં સ્થિરઃ,\nસંપ્રેક્ષ્ય નાસિકાગ્રં સ્વં દિશશ્ચાનવલોકયન્.(૧૩)\nપ્રશાન્તાત્મા વિગતભીર્બ્રહ્મચારિવ્રતે સ્થિતઃ,\nમનઃ સંયમ્ય મચ્ચિત્તો યુક્ત આસીત મત્પરઃ.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Samam kaayashirogreevam dhaarayannachalam sthirah;\nSamprekshya naasikaagram swam dishashchaanavalokayan.\nPrashaantaatmaa vigatabheer brahmachaarivrate sthitah;\nManah samyamya macchitto yukta aaseeta matparah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("काया, सिर और गले को समान एवं अचल धारण करके और स्थिर होकर, अपनी नासिका के अग्रभाग पर दृष्टि जमाकर, अन्य दिशाओं को न देखता हुआ.ब्रह्मचारी के व्रत में स्थित, भयरहित तथा भलीभाँति शांत अन्तःकरण वाला सावधान योगी मन को रोककर मुझमें चित्तवाला और मेरे परायण होकर स्थित होए॥13-14॥");
        } else if (i2 == 0) {
            this.tv2.setText("સાધકે સ્થિર થઈને પોતાનો દેહ,મસ્તક અને ડોકને સ્થિર રાખવાં,પછી પોતાની  નાસિકના અગ્રભાગ પર દ્રષ્ટિ સ્થિર કરી,આમતેમ ન જોતાં યોગનો અભ્યાસ શરૂ કરવો.યોગીએ અંત:કરણ ને શાંત બનાવી,નિર્ભયતા પૂર્વક,બ્રહ્મચર્ય વ્રતનું પાલન કરવું, પછી મનનો સંયમ કરી,મારું ચિંતન કરતાં,મારા પરાયણ થઇ ધ્યાનમગ્ન રહેવું.(૧૩-૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("One should hold one’s body, neck and head erect in a straight line and stare steadily at the tip of the nose. Thus, with an unagitated, subdued mind, devoid of fear, completely free from sex life, one should meditate upon Me within the heart and make Me the ultimate goal of life.(13-14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("युञ्जन्नेवं सदात्मानं योगी नियतमानसः ।\nशान्तिं निर्वाणपरमां मत्संस्थामधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("યુઞ્જન્નેવં સદાત્માનં યોગી નિયતમાનસઃ,\nશાન્તિં નિર્વાણપરમાં મત્સંસ્થામધિગચ્છતિ.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Yunjannevam sadaa’tmaanam yogee niyatamaanasah;\nShaantim nirvaanaparamaam matsamsthaamadhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वश में किए हुए मनवाला योगी इस प्रकार आत्मा को निरंतर मुझ परमेश्वर के स्वरूप में लगाता हुआ मुझमें रहने वाली परमानन्द की पराकाष्ठारूप शान्ति को प्राप्त होता है॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ રીતે અંત:કરણ ને નિરંતર પરમેશ્વરના  સ્વરૂપમાં લગાડીને,સ્વાધીન મનવાળો યોગી મારામાં સ્થિતિરૂપ  પરમાનંદ જ પરાકાષ્ઠાવાળી શાંતિને પ્રાપ્ત કરે છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Thus practicing constant control of the body, mind and activities, the mystic transcendentalist, his mind regulated, attains to the kingdom of God [or the abode of Krishna] by cessation of material existence.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नात्यश्नतस्तु योगोऽस्ति न चैकान्तमनश्नतः ।\nन चाति स्वप्नशीलस्य जाग्रतो नैव चार्जुन ॥");
        } else if (i == 0) {
            this.tv1.setText("નાત્યશ્નતસ્તુ યોગોસ્તિ ન ચૈકાન્તમનશ્નતઃ,\nન ચાતિસ્વપ્નશીલસ્ય જાગ્રતો નૈવ ચાર્જુન.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Naatyashnatastu yogo’sti nachaikaantamanashnatah;\nNa chaatiswapnasheelasya jaagrato naiva chaarjuna.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! यह योग न तो बहुत खाने वाले का, न बिलकुल न खाने वाले का, न बहुत शयन करने के स्वभाव वाले का और न सदा जागने वाले का ही सिद्ध होता है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! વધુ આહાર કરવાથી અથવા નિરાહાર રહેવાથી યોગ સાધી શકાતો નથી. તે જ રીતે વધુ નિદ્રા લેનાર કે અતિ ઓછી નિદ્રા લેનારથી પણ યોગ સાધી શકાતો નથી.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("There is no possibility of one’s becoming a yogi, O Arjuna, if one eats too much or eats too little, sleeps too much or does not sleep enough.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("युक्ताहारविहारस्य युक्तचेष्टस्य कर्मसु ।\nयुक्तस्वप्नावबोधस्य योगो भवति दुःखहा ॥");
        } else if (i == 0) {
            this.tv1.setText("યુક્તાહારવિહારસ્ય યુક્તચેષ્ટસ્ય કર્મસુ,\nયુક્તસ્વપ્નાવબોધસ્ય યોગો ભવતિ દુઃખહા.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Yuktaahaaravihaarasya yuktacheshtasya karmasu;\nYuktaswapnaavabodhasya yogo bhavati duhkhahaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("दुःखों का नाश करने वाला योग तो यथायोग्य आहार-विहार करने वाले का, कर्मों में यथायोग्य चेष्टा करने वाले का और यथायोग्य सोने तथा जागने वाले का ही सिद्ध होता है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેનો આહાર વિહાર યુક્ત હોય,જેનાં કર્માચરણ યોગ્ય હોય અને જેની નિદ્રા અને જાગૃતિ પ્રમાણસર ની હોય છે તે પુરુષ યોગ સાધી શકે છે.અને તેના દુઃખોનો નાશ કરી નાખેછે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("He who is regulated in his habits of eating, sleeping, recreation and work can mitigate all material pains by practicing the yoga system.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा विनियतं चित्तमात्मन्येवावतिष्ठते ।\nनिःस्पृहः सर्वकामेभ्यो युक्त इत्युच्यते तदा ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા વિનિયતં ચિત્તમાત્મન્યેવાવતિષ્ઠતે,\nનિઃસ્પૃહઃ સર્વકામેભ્યો યુક્ત ઇત્યુચ્યતે તદા.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa viniyatam chittamaatmanyevaavatishthate;\nNihsprihah sarvakaamebhyo yukta ityuchyate tadaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अत्यन्त वश में किया हुआ चित्त जिस काल में परमात्मा में ही भलीभाँति स्थित हो जाता है, उस काल में सम्पूर्ण भोगों से स्पृहारहित पुरुष योगयुक्त है, ऐसा कहा जाता है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("જયારે યોગીનું વશ થયેલું ચિત્ત આત્મામાં જ સ્થિર રહે છે,તેની સર્વ કામનાઓ નિ:સ્પૃહ બની જાય છે ત્યારે તે યોગી સમાધિષ્ઠ કહેવાય છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("When the yogi, by practice of yoga, disciplines his mental activities and becomes situated in transcendence—devoid of all material desires—he is said to be well established in yoga.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथा दीपो निवातस्थो नेंगते सोपमा स्मृता ।\nयोगिनो यतचित्तस्य युञ्जतो योगमात्मनः ॥");
        } else if (i == 0) {
            this.tv1.setText("યથા દીપો નિવાતસ્થો નેઙ્ગતે સોપમા સ્મૃતા,\nયોગિનો યતચિત્તસ્ય યુઞ્જતો યોગમાત્મનઃ(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Yathaa deepo nivaatastho nengate sopamaa smritaa;\nYogino yatachittasya yunjato yogamaatmanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस प्रकार वायुरहित स्थान में स्थित दीपक चलायमान नहीं होता, वैसी ही उपमा परमात्मा के ध्यान में लगे हुए योगी के जीते हुए चित्त की कही गई है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેમ વાયુરહિત સ્થાનમાં રહેલો દીપક ડોલતો નથી,તેમ સમાધિનિષ્ઠ યોગી નું  મન ચલિત થતું નથી.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("As a lamp in a windless place does not waver, so the transcendentalist, whose mind is controlled, remains always steady in his meditation on the transcendent self.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20_21_22_23() {
        setTitle("Shlok 20,21,22,23");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्रोपरमते चित्तं निरुद्धं योगसेवया ।\nयत्र चैवात्मनात्मानं पश्यन्नात्मनि तुष्यति ॥\nसुखमात्यन्तिकं यत्तद्‍बुद्धिग्राह्यमतीन्द्रियम्‌ ।\nवेत्ति यत्र न चैवायं स्थितश्चलति तत्त्वतः ॥\nयं लब्ध्वा चापरं लाभं मन्यते नाधिकं ततः ।\nयस्मिन्स्थितो न दुःखेन गुरुणापि विचाल्यते ॥\nतं विद्याद् दुःखसंयोगवियोगं योगसञ्ज्ञितम्।\nस निश्चयेन योक्तव्यो योगोऽनिर्विण्णचेतसा ॥");
        } else if (i == 0) {
            this.tv1.setText("યત્રોપરમતે ચિત્તં નિરુદ્ધં યોગસેવયા,\nયત્ર ચૈવાત્મનાત્માનં પશ્યન્નાત્મનિ તુષ્યતિ.(૨૦)\nસુખમાત્યન્તિકં યત્તદ્બુદ્ધિગ્રાહ્યમતીન્દ્રિયમ્,\nવેત્તિ યત્ર ન ચૈવાયં સ્થિતશ્ચલતિ તત્ત્વતઃ.(૨૧)\nયં લબ્ધ્વા ચાપરં લાભં મન્યતે નાધિકં તતઃ,\nયસ્મિન્સ્થિતો ન દુઃખેન ગુરુણાપિ વિચાલ્યતે.(૨૨)\nતં વિદ્યાદ્ દુઃખસંયોગવિયોગં યોગસંજ્ઞિતમ્,\nસ નિશ્ચયેન યોક્તવ્યો યોગોનિર્વિણ્ણચેતસા.(૨૩)\n");
        } else if (2 == i) {
            this.tv1.setText("Yatroparamate chittam niruddham yogasevayaa;\nYatra chaivaatmanaa’tmaanam pashyannaatmani tushyati.\nSukhamaatyantikam yattad buddhi graahyamateendriyam;\nVetti yatra na chaivaayam sthitashchalati tattwatah.\nYam labdhwaa chaaparam laabham manyate naadhikam tatah;\nYasmin sthito na duhkhena gurunaapi vichaalyate.\nTam vidyaad duhkhasamyogaviyogam yogasamjnitam;\nSa nishchayena yoktavyo yogo’nirvinna chetasaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("योग के अभ्यास से निरुद्ध चित्त जिस अवस्था में उपराम हो जाता है और जिस अवस्था में परमात्मा के ध्यान से शुद्ध हुई सूक्ष्म बुद्धि द्वारा परमात्मा को साक्षात करता हुआ सच्चिदानन्दघन परमात्मा में ही सन्तुष्ट रहता है. इन्द्रियों से अतीत, केवल शुद्ध हुई सूक्ष्म बुद्धि द्वारा ग्रहण करने योग्य जो अनन्त आनन्द है, उसको जिस अवस्था में अनुभव करता है, और जिस अवस्था में स्थित यह योगी परमात्मा के स्वरूप से विचलित होता ही नहीं. परमात्मा की प्राप्ति रूप जिस लाभ को प्राप्त होकर उसे अधिक दूसरा कुछ भी लाभ नहीं मानता और परमात्मा प्राप्ति रूप जिस अवस्था में स्थित योगी बड़े भारी दुःख से भी चलायमान नहीं होता. जो दुःखरूप संसार के संयोग से रहित है तथा जिसका नाम योग है, उसको जानना चाहिए। वह योग न उकताए हुए अर्थात धैर्य और उत्साहयुक्त चित्त से निश्चयपूर्वक करना कर्तव्य है॥20-21-22-23॥");
        } else if (i2 == 0) {
            this.tv2.setText("યોગાભ્યાસથી સંયમિત થયેલું ચિત્ત કર્મથી નિવૃત થાયછે,જયારે યોગી પોતાના નિર્મળ થયેલાં અંત:કરણમાં પરમાત્માનો સાક્ષાત્કાર પામી પોતાના જ સ્વરૂપમાં સંતોષ પામેછે.જયારે સુક્ષ્મબુધ્ધીથી ગ્રાહ્ય અને ઇન્દ્રિયોથી અગ્રાહ્ય એવું પરમ સુખ પામે છે ત્યારે તે સ્થિર થયેલો યોગી બ્રહ્મ્સ્વરૂપમાંથી ચલિત થતો નથી. આ સ્થિતિ પ્રાપ્ત થતાં યોગી બીજા કોઈ લાભને અધિક માનતો નથી અને ગમે તેવા દુઃખો આવે છતાં તેનું ચિત્ત સ્વરૂપાનંદથી વિચલિત થતું નથી.જેમાં જરાય દુઃખનો સંચાર થતો નથી અને જે દુઃખના સંબંધને તોડી નાખે છે તેને જ યોગ કહેવાય છે.આ યોગ પ્રસન્ન ચિત્ત વડે અને દઢ  નિશ્ચયથી સાધ્ય કરવો.(૨૦-૨૧-૨૨-૨૩) ");
        } else if (2 == i2) {
            this.tv2.setText("In the stage of perfection called trance, or samadhi, one’s mind is completely restrained from material mental activities by practice of yoga. This perfection is characterized by one’s ability to see the self by the pure mind and to relish and rejoice in the self. In that joyous state, one is situated in boundless transcendental happiness, realized through transcendental senses. Established thus, one never departs from the truth, and upon gaining this he thinks there is no greater gain. Being situated in such a position, one is never shaken, even in the midst of greatest difficulty. This indeed is actual freedom from all miseries arising from material contact.(20,21,22,23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सङ्‍कल्पप्रभवान्कामांस्त्यक्त्वा सर्वानशेषतः ।\nमनसैवेन्द्रियग्रामं विनियम्य समन्ततः ॥");
        } else if (i == 0) {
            this.tv1.setText("સઙ્કલ્પપ્રભવાન્કામાંસ્ત્યક્ત્વા સર્વાનશેષતઃ,\nમનસૈવેન્દ્રિયગ્રામં વિનિયમ્ય સમન્તતઃ.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Sankalpaprabhavaan kaamaan styaktwaa sarvaan asheshatah;\nManasaivendriyagraamam viniyamya samantatah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संकल्प से उत्पन्न होने वाली सम्पूर्ण कामनाओं को निःशेष रूप से त्यागकर और मन द्वारा इन्द्रियों के समुदाय को सभी ओर से भलीभाँति रोककर॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંકલ્પથી ઉત્પન્ન થતી સર્વ વાસનાઓનો ત્યાગ કરી ,મનથી જ સર્વ ઈન્દ્રિયોને સર્વ રીતે જીતી ને (૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("One should engage oneself in the practice of yoga with determination and faith and not be deviated from the path. One should abandon, without exception, all material desires born of mental speculation and thus control all the senses on all sides by the mind.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शनैः शनैरुपरमेद्‍बुद्धया धृतिगृहीतया।\nआत्मसंस्थं मनः कृत्वा न किंचिदपि चिन्तयेत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શનૈઃ શનૈરુપરમેદ્ બુદ્ધ્યા ધૃતિગૃહીતયા,\nઆત્મસંસ્થં મનઃ કૃત્વા ન કિઞ્ચિદપિ ચિન્તયેત્.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Shanaih shanairuparamed buddhyaa dhritigriheetayaa;\nAatmasamstham manah kritwaa na kinchidapi chintayet.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्रम-क्रम से अभ्यास करता हुआ उपरति को प्राप्त हो तथा धैर्ययुक्त बुद्धि द्वारा मन को परमात्मा में स्थित करके परमात्मा के सिवा और कुछ भी चिन्तन न करे॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("તથા ધીરજવાળી બુદ્ધિથી ધીમે ધીમે આત્મસ્વરૂપમાં સ્થિર થવું અને મનને એ રીતે સ્થિર કરી બીજું કોઈ ચિંતન કરવું નહિ.(૨૫)  ");
        } else if (2 == i2) {
            this.tv2.setText("Gradually, step by step, one should become situated in trance by means of intelligence sustained by full conviction, and thus the mind should be fixed on the self alone and should think of nothing else.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यतो यतो निश्चरति मनश्चञ्चलमस्थिरम्‌ ।\nततस्ततो नियम्यैतदात्मन्येव वशं नयेत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યતો યતો નિશ્ચરતિ મનશ્ચઞ્ચલમસ્થિરમ્,\nતતસ્તતો નિયમ્યૈતદાત્મન્યેવ વશં નયેત્. (૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Yato yato nishcharati manashchanchalamasthiram;\nTatastato niyamyaitad aatmanyeva vasham nayet.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यह स्थिर न रहने वाला और चंचल मन जिस-जिस शब्दादि विषय के निमित्त से संसार में विचरता है, उस-उस विषय से रोककर यानी हटाकर इसे बार-बार परमात्मा में ही निरुद्ध करे॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ ચંચળ મન જ્યાં જ્યાં ભટકે ત્યાંથી નિગ્રહ વડે પાછું વાળીને આત્મસ્વરૂપમાં જ સંલગ્ન કરવું.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("From wherever the mind wanders due to its flickering and unsteady nature, one must certainly withdraw it and bring it back under the control of the self.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रशान्तमनसं ह्येनं योगिनं सुखमुत्तमम्‌ ।\nउपैति शांतरजसं ब्रह्मभूतमकल्मषम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રશાન્તમનસં હ્યેનં યોગિનં સુખમુત્તમમ્,\nઉપૈતિ શાન્તરજસં બ્રહ્મભૂતમકલ્મષમ્.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Prashaantamanasam hyenam yoginam sukhamuttamam;\nUpaiti shaantarajasam brahmabhootamakalmasham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि जिसका मन भली प्रकार शांत है, जो पाप से रहित है और जिसका रजोगुण शांत हो गया है, ऐसे इस सच्चिदानन्दघन ब्रह्म के साथ एकीभाव हुए योगी को उत्तम आनंद प्राप्त होता है॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે યોગીનું ચિત્ત સંતોષ પામ્યું છે,જેનો રજોગુણ નાશ પામ્યો છે અને જે બ્રહ્મસ્વરૂપ બની નિષ્પાપ બની ગયો છે,તે યોગી બ્રહ્મસુખ મેળવે છે.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("The yogi whose mind is fixed on Me verily attains the highest perfection of transcendental happiness. He is beyond the mode of passion, he realizes his qualitative identity with the Supreme, and thus he is freed from all reactions to past deeds.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("युञ्जन्नेवं सदात्मानं योगी विगतकल्मषः ।\nसुखेन ब्रह्मसंस्पर्शमत्यन्तं सुखमश्नुते ॥");
        } else if (i == 0) {
            this.tv1.setText("યુઞ્જન્નેવં સદાત્માનં યોગી વિગતકલ્મષઃ,\nસુખેન બ્રહ્મસંસ્પર્શમત્યન્તં સુખમશ્નુતે.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Yunjannevam sadaa’tmaanam yogee vigatakalmashah;\nSukhena brahmasamsparsham atyantam sukham ashnute.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह पापरहित योगी इस प्रकार निरंतर आत्मा को परमात्मा में लगाता हुआ सुखपूर्वक परब्रह्म परमात्मा की प्राप्ति रूप अनन्त आनंद का अनुभव करता है॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ પ્રમાણે સતત આત્મ વિષયક યોગ કરનાર નિષ્પાપ યોગી ,જેમાં બ્રહ્મનો અનુભવ રહેલો છે,એવું અત્યંત સુખ અનાયાસે મેળવે છે.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("Thus the self-controlled yogi, constantly engaged in yoga practice, becomes free from all material contamination and achieves the highest stage of perfect happiness in transcendental loving service to the Lord.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वभूतस्थमात्मानं सर्वभूतानि चात्मनि ।\nईक्षते योगयुक्तात्मा सर्वत्र समदर्शनः ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વભૂતસ્થમાત્માનં સર્વભૂતાનિ ચાત્મનિ,\nઈક્ષતે યોગયુક્તાત્મા સર્વત્ર સમદર્શનઃ.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Sarvabhootasthamaatmaanam sarvabhootaani chaatmani;\nEekshate yogayuktaatmaa sarvatra samadarshanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सर्वव्यापी अनंत चेतन में एकीभाव से स्थिति रूप योग से युक्त आत्मा वाला तथा सब में समभाव से देखने वाला योगी आत्मा को सम्पूर्ण भूतों में स्थित और सम्पूर्ण भूतों को आत्मा में कल्पित देखता है॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સર્વત્ર સમદ્રષ્ટિ રાખે છે એ યોગીપુરુષ સર્વ ભૂતોમાં પોતાના આત્મા ને અને પોતાના આત્મામાં સર્વ ભૂતોને જુવેછે.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("A true yogi observes Me in all beings and also sees every being in Me. Indeed, the self-realized person sees Me, the same Supreme Lord, everywhere.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यो मां पश्यति सर्वत्र सर्वं च मयि पश्यति ।\nतस्याहं न प्रणश्यामि स च मे न प्रणश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("યો માં પશ્યતિ સર્વત્ર સર્વં ચ મયિ પશ્યતિ,\nતસ્યાહં ન પ્રણશ્યામિ સ ચ મે ન પ્રણશ્યતિ.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Yo maam pashyati sarvatra sarvam cha mayi pashyati;\nTasyaaham na pranashyaami sa cha me na pranashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष सम्पूर्ण भूतों में सबके आत्मरूप मुझ वासुदेव को ही व्यापक देखता है और सम्पूर्ण भूतों को मुझ वासुदेव के अन्तर्गत (गीता अध्याय 9 श्लोक 6 में देखना चाहिए।) देखता है, उसके लिए मैं अदृश्य नहीं होता और वह मेरे लिए अदृश्य नहीं होता॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે યોગી સર્વ ભૂતોમાં મને જુવે છે અને મારામાં સર્વ ભૂતોને જુવે છે,તેની દ્રષ્ટિ સમક્ષ જ હું રહું છું.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("For one who sees Me everywhere and sees everything in Me, I am never lost, nor is he ever lost to Me.(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        setTitle("Shlok 31");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वभूतस्थितं यो मां भजत्येकत्वमास्थितः ।\nसर्वथा वर्तमानोऽपि स योगी मयि वर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વભૂતસ્થિતં યો માં ભજત્યેકત્વમાસ્થિતઃ,\nસર્વથા વર્તમાનોપિ સ યોગી મયિ વર્તતે.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Sarvabhootasthitam yo maam bhajatyekatwamaasthitah;\nSarvathaa vartamaano’pi sa yogee mayi vartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष एकीभाव में स्थित होकर सम्पूर्ण भूतों में आत्मरूप से स्थित मुझ सच्चिदानन्दघन वासुदेव को भजता है, वह योगी सब प्रकार से बरतता हुआ भी मुझमें ही बरतता है॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે યોગી એકનિષ્ઠાથી સર્વ ભૂતોમાં રહેલા મને ભજે છે,તે કોઈ પણ રીતે વર્તતો હોય તો પણ મારા સ્વરૂપમાં જ રહે છે.(૩૧)  ");
        } else if (2 == i2) {
            this.tv2.setText("Such a yogi, who engages in the worshipful service of the Supersoul, knowing that I and the Supersoul are one, remains always in Me in all circumstances.(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        setTitle("Shlok 32");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आत्मौपम्येन सर्वत्र समं पश्यति योऽर्जुन ।\nसुखं वा यदि वा दुःखं स योगी परमो मतः ॥");
        } else if (i == 0) {
            this.tv1.setText("આત્મૌપમ્યેન સર્વત્ર સમં પશ્યતિ યોર્જુન,\nસુખં વા યદિ વા દુઃખં સઃ યોગી પરમો મતઃ.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Aatmaupamyena sarvatra samam pashyati yo’rjuna;\nSukham vaa yadi vaa duhkham sa yogee paramo matah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जो योगी अपनी भाँति (जैसे मनुष्य अपने मस्तक, हाथ, पैर और गुदादि के साथ ब्राह्मण, क्षत्रिय, शूद्र और म्लेच्छादिकों का-सा बर्ताव करता हुआ भी उनमें आत्मभाव अर्थात अपनापन समान होने से सुख और दुःख को समान ही देखता है, वैसे ही सब भूतों में देखना 'अपनी भाँति' सम देखना है।) सम्पूर्ण भूतों में सम देखता है और सुख अथवा दुःख को भी सबमें सम देखता है, वह योगी परम श्रेष्ठ माना गया है॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! જે યોગી પોતાની જેમ જ સર્વ ને સુખ-દુઃખની અનુભૂતિ થાય છે એવી સમદ્રષ્ટિ થી જુવે છે,તે મને પરમ માન્ય છે.(૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("He is a perfect yogi who, by comparison to his own self, sees the true equality of all beings, in both their happiness and their distress, O Arjuna!(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nयोऽयं योगस्त्वया प्रोक्तः साम्येन मधुसूदन ।\nएतस्याहं न पश्यामि चञ्चलत्वात्स्थितिं स्थिराम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nયોયં યોગસ્ત્વયા પ્રોક્તઃ સામ્યેન મધુસૂદન,\nએતસ્યાહં ન પશ્યામિ ચઞ્ચલત્વાત્ સ્થિતિં સ્થિરામ્.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nYo’yam yogastwayaa proktah saamyena madhusoodana;\nEtasyaaham na pashyaami chanchalatwaat sthitim sthiraam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे मधुसूदन! जो यह योग आपने समभाव से कहा है, मन के चंचल होने से मैं इसकी नित्य स्थिति को नहीं देखता हूँ॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે: હે મધુસુદન !તમે જે સમદ્રષ્ટિ નો યોગ કહ્યો તે યોગની અચલ સ્થિતિ મનની ચંચળતા ને લીધે રહી શકે તેમ લાગતું નથી.(૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O Madhusudana, the system of yoga which You have summarized appears impractical and unendurable to me, for the mind is restless and unsteady.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("चञ्चलं हि मनः कृष्ण प्रमाथि बलवद्दृढम्‌ ।\nतस्याहं निग्रहं मन्ये वायोरिव सुदुष्करम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ચઞ્ચલં હિ મનઃ કૃષ્ણ પ્રમાથિ બલવદ્દૃઢમ્,\nતસ્યાહં નિગ્રહં મન્યે વાયોરિવ સુદુષ્કરમ્.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Chanchalam hi manah krishna pramaathi balavad dridham;\nTasyaaham nigraham manye vaayoriva sudushkaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि हे श्रीकृष्ण! यह मन बड़ा चंचल, प्रमथन स्वभाव वाला, बड़ा दृढ़ और बलवान है। इसलिए उसको वश में करना मैं वायु को रोकने की भाँति अत्यन्त दुष्कर मानता हूँ॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે શ્રી કૃષ્ણ ! મન અતિ ચંચળ છે.તે કોઈ પણ કામના ને સિદ્ધ થવા દેતું નથી.તે બળવાન અને અભેદ્ય છે.તેનો નિગ્રહ કરવો એ વાયુને રોકવા જેટલું કઠીન છે,એવું મને લાગે છે.(૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("For the mind is restless, turbulent, obstinate and very strong, O Krishna, and to subdue it, I think, is more difficult than controlling the wind.(34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        setTitle("Shlok 35");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nअसंशयं महाबाहो मनो दुर्निग्रहं चलम्‌ ।\nअभ्यासेन तु कौन्तेय वैराग्येण च गृह्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઅસંશયં મહાબાહો મનો દુર્નિગ્રહં ચલં,\nઅભ્યાસેન તુ કૌન્તેય વૈરાગ્યેણ ચ ગૃહ્યતે.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nAsamshayam mahaabaaho mano durnigraham chalam;\nAbhyaasena tu kaunteya vairaagyena cha grihyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे महाबाहो! निःसंदेह मन चंचल और कठिनता से वश में होने वाला है। परन्तु हे कुंतीपुत्र अर्जुन! यह अभ्यास (गीता अध्याय 12 श्लोक 9 की टिप्पणी में इसका विस्तार देखना चाहिए।) और वैराग्य से वश में होता है॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો ! મન ચંચળ હોવાથી તેનો નિગ્રહ કરવો કઠીન જ છે, એ વાત નિ:સંશય હું માનું છું,પરંતુ હે કાંન્ન્તેય ! વૈરાગ્ય અને અભ્યાસ ના યોગ થી તેને પણ સ્વાધીન કરી શકાય છે.(૩૫)");
        } else if (2 == i2) {
            this.tv2.setText("Lord Sri Krishna said: O mighty-armed son of Kunti, it is undoubtedly very difficult to curb the restless mind, but it is possible by suitable practice and by detachment.(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36() {
        setTitle("Shlok 36");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("असंयतात्मना योगो दुष्प्राप इति मे मतिः ।\nवश्यात्मना तु यतता शक्योऽवाप्तुमुपायतः ॥");
        } else if (i == 0) {
            this.tv1.setText("અસંયતાત્મના યોગો દુષ્પ્રાપ ઇતિ મે મતિઃ,\nવશ્યાત્મના તુ યતતા શક્યોવાપ્તુમુપાયતઃ.(૩૬)");
        } else if (2 == i) {
            this.tv1.setText("Asamyataatmanaa yogo dushpraapa iti me matih;\nVashyaatmanaa tu yatataa shakyo’vaaptumupaayatah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसका मन वश में किया हुआ नहीं है, ऐसे पुरुष द्वारा योग दुष्प्राप्य है और वश में किए हुए मन वाले प्रयत्नशील पुरुष द्वारा साधन से उसका प्राप्त होना सहज है- यह मेरा मत है॥36॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે મન નો નિગ્રહ કરવાનો અભ્યાસ કરતો નથી તેને યોગ પ્રાપ્ત થતો નથી.જે અંત:કરણ ને વશ કરી મનનો નિગ્રહ કરવાનો યત્ન કરેછે,તેને પ્રયત્ન વડે યોગ પ્રાપ્ત થાય છે. એવો મારો મત છે.(૩૬)");
        } else if (2 == i2) {
            this.tv2.setText("For one whose mind is unbridled, self-realization is difficult work. But he whose mind is controlled and who strives by appropriate means is assured of success. That is My opinion.(36)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok37() {
        setTitle("Shlok 37");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nअयतिः श्रद्धयोपेतो योगाच्चलितमानसः ।\nअप्राप्य योगसंसिद्धिं कां गतिं कृष्ण गच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nઅયતિઃ શ્રદ્ધયોપેતો યોગાચ્ચલિતમાનસઃ,\nઅપ્રાપ્ય યોગસંસિદ્ધિં કાં ગતિં કૃષ્ણગચ્છતિ.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nAyatih shraddhayopeto yogaacchalitamaanasah;\nApraapya yogasamsiddhim kaam gatim krishna gacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे श्रीकृष्ण! जो योग में श्रद्धा रखने वाला है, किन्तु संयमी नहीं है, इस कारण जिसका मन अन्तकाल में योग से विचलित हो गया है, ऐसा साधक योग की सिद्धि को अर्थात भगवत्साक्षात्कार को न प्राप्त होकर किस गति को प्राप्त होता है॥37॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે: હે શ્રી કૃષ્ણ ! જે સાધક શ્રદ્ધાવાન હોવા છતાં પ્રયત્ન કરતો નથી,જેનું મનઅંતકાળે યોગ માંથી ચ્યુત થયું છે,એવા પુરુષ યોગસિધ્ધિ ન પામતાં કઈ ગતિ પામેછે?(૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O Krishna, what is the destination of the unsuccessful transcendentalist, who in the beginning takes to the process of self-realization with faith but who later desists due to worldly-mindedness and thus does not attain perfection in mysticism?(37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        setTitle("Shlok 38");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कच्चिन्नोभयविभ्रष्टश्छिन्नाभ्रमिव नश्यति ।\nअप्रतिष्ठो महाबाहो विमूढो ब्रह्मणः पथि ॥");
        } else if (i == 0) {
            this.tv1.setText("કચ્ચિન્નોભયવિભ્રષ્ટશ્છિન્નાભ્રમિવ નશ્યતિ,\nઅપ્રતિષ્ઠો મહાબાહો વિમૂઢો બ્રહ્મણઃ પથિ.(૩૮)");
        } else if (2 == i) {
            this.tv1.setText("Kacchinnobhayavibhrashtash cchinnaabhramiva nashyati;\nApratishtho mahaabaaho vimoodho brahmanah pathi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे महाबाहो! क्या वह भगवत्प्राप्ति के मार्ग में मोहित और आश्रयरहित पुरुष छिन्न-भिन्न बादल की भाँति दोनों ओर से भ्रष्ट होकर नष्ट तो नहीं हो जाता?॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે શ્રી કૃષ્ણ ! મોહવશ થયેલો યોગી બ્રહ્મમાર્ગમાં જતાં કર્મમાર્ગ અને યોગમાર્ગ એમ બંને માર્ગથી ભ્રષ્ટ થઇ વિખરાઈ જતાં વાદળોની જેમ નાશ નથી પામતો?(૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("O mighty-armed Krishna, does not such a man, who is bewildered from the path of transcendence, fall away from both spiritual and material success and perish like a riven cloud, with no position in any sphere?(38)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        setTitle("Shlok 39");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतन्मे संशयं कृष्ण छेत्तुमर्हस्यशेषतः ।\nत्वदन्यः संशयस्यास्य छेत्ता न ह्युपपद्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("એતન્મે સંશયં કૃષ્ણ છેત્તુમર્હસ્યશેષતઃ,\nત્વદન્યઃ સંશયસ્યાસ્ય છેત્તા ન હ્યુપપદ્યતે.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Etanme samshayam krishna cchettumarhasyasheshatah;\nTwadanyah samshayasyaasya cchettaa na hyupapadyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे श्रीकृष्ण! मेरे इस संशय को सम्पूर्ण रूप से छेदन करने के लिए आप ही योग्य हैं क्योंकि आपके सिवा दूसरा इस संशय का छेदन करने वाला मिलना संभव नहीं है॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે શ્રી કૃષ્ણ ! મારી આ શંકા ને નિર્મૂળ કરવા આપ જ સમર્થ છો.આ શંકા ને દુર કરવા આપ સિવાય બીજું કોઈ સમર્થ નથી.(૩૯)");
        } else if (2 == i2) {
            this.tv2.setText("This is my doubt, O Krishna, and I ask You to dispel it completely. But for You, no one is to be found who can destroy this doubt.(39)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        setTitle("Shlok 40");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nपार्थ नैवेह नामुत्र विनाशस्तस्य विद्यते ।\nन हि कल्याणकृत्कश्चिद्दुर्गतिं तात गच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nપાર્થ નૈવેહ નામુત્ર વિનાશસ્તસ્ય વિદ્યતે,\nનહિ કલ્યાણકૃત્કશ્ચિદ્દુર્ગતિં તાત ગચ્છતિ.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nPaartha naiveha naamutra vinaashas tasya vidyate;\nNahi kalyaanakrit kashchid durgatim taata gacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे पार्थ! उस पुरुष का न तो इस लोक में नाश होता है और न परलोक में ही क्योंकि हे प्यारे! आत्मोद्धार के लिए अर्थात भगवत्प्राप्ति के लिए कर्म करने वाला कोई भी मनुष्य दुर्गति को प्राप्त नहीं होता॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે: હે પાર્થ ! જે યોગની ઇચ્છાવાળો પુરુષ હોય છે તે આ લોક કે પરલોક થી વંચિત રહેતો નથી.હે તાત ! સત્કર્મો કરનાર મનુષ્યની કદી પણ દુર્ગતિ થતી નથી.(૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: Son of Pritha, a transcendentalist engaged in auspicious activities does not meet with destruction either in this world or in the spiritual world; one who does good, My friend, is never overcome by evil.(40)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        setTitle("Shlok 41");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्राप्य पुण्यकृतां लोकानुषित्वा शाश्वतीः समाः ।\nशुचीनां श्रीमतां गेहे योगभ्रष्टोऽभिजायते ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રાપ્ય પુણ્યકૃતાં લોકાનુષિત્વા શાશ્વતીઃ સમાઃ,\nશુચીનાં શ્રીમતાં ગેહે યોગભ્રષ્ટોભિજાયતે.(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Praapya punyakritaam lokaanushitwaa shaashwateeh samaah;\nShucheenaam shreemataam gehe yogabhrashto’bhijaayate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("योगभ्रष्ट पुरुष पुण्यवानों के लोकों को अर्थात स्वर्गादि उत्तम लोकों को प्राप्त होकर उनमें बहुत वर्षों तक निवास करके फिर शुद्ध आचरण वाले श्रीमान पुरुषों के घर में जन्म लेता है॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("યોગભ્રષ્ટ મનુષ્ય મહાન પુણ્યકર્મ થી મળતાં સ્વર્ગાદિ સુખો પ્રાપ્ત કરી જયારે મૃત્યુલોક માં આવેછે ત્યારે પવિત્ર તથા શ્રીમંત કુળમાં જન્મ ધારણ કરે છે.(૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("The unsuccessful yogi, after many, many years of enjoyment on the planets of the pious living entities, is born into a family of righteous people, or into a family of rich aristocracy.(41)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42() {
        setTitle("Shlok 42");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथवा योगिनामेव कुले भवति धीमताम्‌ ।\nएतद्धि दुर्लभतरं लोके जन्म यदीदृशम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અથવા યોગિનામેવ કુલે ભવતિ ધીમતામ્,\nએતદ્ધિ દુર્લભતરં લોકે જન્મ યદીદૃશમ્.(૪૨)");
        } else if (2 == i) {
            this.tv1.setText("Athavaa yoginaameva kule bhavati dheemataam;\nEtaddhi durlabhataram loke janma yadeedrisham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अथवा वैराग्यवान पुरुष उन लोकों में न जाकर ज्ञानवान योगियों के ही कुल में जन्म लेता है, परन्तु इस प्रकार का जो यह जन्म है, सो संसार में निःसंदेह अत्यन्त दुर्लभ है॥42॥");
        } else if (i2 == 0) {
            this.tv2.setText("અથવા બુદ્ધિશાળી યોગીના કુળમાં જ આવા યોગભ્રષ્ટ મનુષ્યો જન્મ લે છે, કારણકે આવા પ્રકાર નો જન્મ આ લોકમાં દુર્લભ છે.જેનો યોગીના કુળમાં જન્મ થાય છે,(૪૨)");
        } else if (2 == i2) {
            this.tv2.setText("Or [if unsuccessful after long practice of yoga] he takes his birth in a family of transcendentalists who are surely great in wisdom. Certainly, such a birth is rare in this world.(42)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok43() {
        setTitle("Shlok 43");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्र तं बुद्धिसंयोगं लभते पौर्वदेहिकम्‌ ।\nयतते च ततो भूयः संसिद्धौ कुरुनन्दन ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્ર તં બુદ્ધિસંયોગં લભતે પૌર્વદેહિકમ્,\nયતતે ચ તતો ભૂયઃ સંસિદ્ધૌ કુરુનન્દન.(૪૩)");
        } else if (2 == i) {
            this.tv1.setText("Tatra tam buddhisamyogam labhate paurvadehikam;\nYatate cha tato bhooyah samsiddhau kurunandana.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वहाँ उस पहले शरीर में संग्रह किए हुए बुद्धि-संयोग को अर्थात समबुद्धिरूप योग के संस्कारों को अनायास ही प्राप्त हो जाता है और हे कुरुनन्दन! उसके प्रभाव से वह फिर परमात्मा की प्राप्तिरूप सिद्धि के लिए पहले से भी बढ़कर प्रयत्न करता है॥43॥");
        } else if (i2 == 0) {
            this.tv2.setText("એટલે પૂર્વ જન્મની યોગબુદ્ધિ નો તેનામાં જલ્દી વિકાસ થાય છે.અને તે મનુષ્ય યોગ સિધ્ધિ માટે પુન: અભ્યાસ કરવામાં લાગી જાય છે.(૪૩)");
        } else if (2 == i2) {
            this.tv2.setText("On taking such a birth, he revives the divine consciousness of his previous life, and he again tries to make further progress in order to achieve complete success, O son of Kuru.(43)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok44() {
        setTitle("Shlok 44");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पूर्वाभ्यासेन तेनैव ह्रियते ह्यवशोऽपि सः ।\nजिज्ञासुरपि योगस्य शब्दब्रह्मातिवर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("પૂર્વાભ્યાસેન તેનૈવ હ્રિયતે હ્યવશોપિ સઃ,\nજિજ્ઞાસુરપિ યોગસ્ય શબ્દબ્રહ્માતિવર્તતે.(૪૪)");
        } else if (2 == i) {
            this.tv1.setText("Poorvaabhyaasena tenaiva hriyate hyavasho’pi sah;\nJijnaasurapi yogasya shabdabrahmaativartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह (यहाँ 'वह' शब्द से श्रीमानों के घर में जन्म लेने वाला योगभ्रष्ट पुरुष समझना चाहिए।) श्रीमानों के घर में जन्म लेने वाला योगभ्रष्ट पराधीन हुआ भी उस पहले के अभ्यास से ही निःसंदेह भगवान की ओर आकर्षित किया जाता है तथा समबुद्धि रूप योग का जिज्ञासु भी वेद में कहे हुए सकाम कर्मों के फल को उल्लंघन कर जाता है॥44॥");
        } else if (i2 == 0) {
            this.tv2.setText("ઉત્તમ કુળમાં જન્મ લઈને જો તે પરતંત્ર હોય તોયે પૂર્વજન્મના યોગના અભ્યાસને લીધે તે યોગ તરફ વળે છે.યોગના જીજ્ઞાસુઓને વેદાચરણ ના ફળ કરતાં વિશેષ ફળ મળે છે.(૪૪)");
        } else if (2 == i2) {
            this.tv2.setText("By virtue of the divine consciousness of his previous life, he automatically becomes attracted to the yogic principles—even without seeking them. Such an inquisitive transcendentalist stands always above the ritualistic principles of the scriptures.(44)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok45() {
        setTitle("Shlok 45");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रयत्नाद्यतमानस्तु योगी संशुद्धकिल्बिषः ।\nअनेकजन्मसंसिद्धस्ततो यात परां गतिम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રયત્નાદ્યતમાનસ્તુ યોગી સંશુદ્ધકિલ્બિષઃ,\nઅનેકજન્મસંસિદ્ધસ્તતો યાતિ પરાં ગતિમ્.(૪૫)");
        } else if (2 == i) {
            this.tv1.setText("Prayatnaadyatamaanastu yogee samshuddhakilbishah;\nAnekajanmasamsiddhas tato yaati paraam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु प्रयत्नपूर्वक अभ्यास करने वाला योगी तो पिछले अनेक जन्मों के संस्कारबल से इसी जन्म में संसिद्ध होकर सम्पूर्ण पापों से रहित हो फिर तत्काल ही परमगति को प्राप्त हो जाता है॥45॥");
        } else if (i2 == 0) {
            this.tv2.setText("કિન્તુ નિયમપૂર્વક અભ્યાસ કરનાર સર્વ પાપમાંથી મુક્ત થતો અને અનેક જન્મોથી એ જ અભ્યાસ કરતો રહેલો યોગી પરમગતિ ને પ્રાપ્ત થાય છે.(૪૫)");
        } else if (2 == i2) {
            this.tv2.setText("And when the yogi engages himself with sincere endeavor in making further progress, being washed of all contaminations, then ultimately, achieving perfection after many, many births of practice, he attains the supreme goal.(45)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok46() {
        setTitle("Shlok 46");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तपस्विभ्योऽधिको योगी ज्ञानिभ्योऽपि मतोऽधिकः ।\nकर्मिभ्यश्चाधिको योगी तस्माद्योगी भवार्जुन ॥");
        } else if (i == 0) {
            this.tv1.setText("તપસ્વિભ્યોધિકો યોગી જ્ઞાનિભ્યોપિ મતોધિકઃ,\nકર્મિભ્યશ્ચાધિકો યોગી તસ્માદ્યોગી ભવાર્જુન.(૪૬)");
        } else if (2 == i) {
            this.tv1.setText("Tapaswibhyo’dhiko yogee jnaanibhyo’pi mato’dhikah;\nKarmibhyashchaadhiko yogee tasmaad yogee bhavaarjuna.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("योगी तपस्वियों से श्रेष्ठ है, शास्त्रज्ञानियों से भी श्रेष्ठ माना गया है और सकाम कर्म करने वालों से भी योगी श्रेष्ठ है। इससे हे अर्जुन! तू योगी हो॥46॥");
        } else if (i2 == 0) {
            this.tv2.setText("તપસ્વી ,જ્ઞાની તથા કર્મ કરનાર કરતાંયોગી વધુ  શ્રેષ્ઠ છે,માટે હે અર્જુન ! તું યોગી બન.(૪૬)");
        } else if (2 == i2) {
            this.tv2.setText("A yogi is greater than the ascetic, greater than the empiricist and greater than the fruitive worker. Therefore, O Arjuna, in all circumstances, be a yogi.(46)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok47() {
        setTitle("Shlok 47");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("योगिनामपि सर्वेषां मद्गतेनान्तरात्मना ।\nश्रद्धावान्भजते यो मां स मे युक्ततमो मतः ॥");
        } else if (i == 0) {
            this.tv1.setText("યોગિનામપિ સર્વેષાં મદ્ગતેનાન્તરાત્મના,\nશ્રદ્ધાવાન્ભજતે યો માં સ મે યુક્તતમો મતઃ.(૪૭)");
        } else if (2 == i) {
            this.tv1.setText("Yoginaamapi sarveshaam madgatenaantaraatmanaa;\nShraddhaavaan bhajate yo maam sa me yuktatamo matah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सम्पूर्ण योगियों में भी जो श्रद्धावान योगी मुझमें लगे हुए अन्तरात्मा से मुझको निरन्तर भजता है, वह योगी मुझे परम श्रेष्ठ मान्य है॥47॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ યોગીઓમાં પણ જે યોગી મારી સાથે શ્રદ્ધાપૂર્વક એકતા પામી મને ભજે છે તે મને પરમ માન્ય છે.(૪૭)");
        } else if (2 == i2) {
            this.tv2.setText("And of all yogis, the one with great faith who always abides in Me, thinks of Me within himself, and renders transcendental loving service to Me—he is the most intimately united with Me in yoga and is the highest of all. That is My opinion.(47)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay6.this, ShlokListPlay6.this);
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

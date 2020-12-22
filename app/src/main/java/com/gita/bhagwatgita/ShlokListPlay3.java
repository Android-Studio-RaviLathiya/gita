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

public class ShlokListPlay3 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay3.this.favflag == 0) {
                    ShlokListPlay3.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.favflag = 1;
                    shlokListPlay3.save();
                } else if (1 == ShlokListPlay3.this.favflag) {
                    ShlokListPlay3.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay3 shlokListPlay32 = ShlokListPlay3.this;
                    shlokListPlay32.favflag = 0;
                    shlokListPlay32.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay3.this.calll++;
                ShlokListPlay3.this.slkset();
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
                ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                shlokListPlay3.calll--;
                ShlokListPlay3.this.slkset();
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
        edit.putString("A3" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A3" + this.calll, "0");
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
        if (40 == this.calll) {
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
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.callf1 = 0;
                    shlokListPlay3.slkset();
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
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.callf1 = 2;
                    shlokListPlay3.slkset();
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
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.callf1 = 1;
                    shlokListPlay3.slkset();
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
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.callf2 = 0;
                    shlokListPlay3.slkset();
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
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.callf2 = 2;
                    shlokListPlay3.slkset();
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
                    ShlokListPlay3 shlokListPlay3 = ShlokListPlay3.this;
                    shlokListPlay3.callf2 = 1;
                    shlokListPlay3.slkset();
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
            shlok14_15();
        } else if (14 == i3) {
            shlok16();
        } else if (15 == i3) {
            shlok17();
        } else if (16 == i3) {
            shlok18();
        } else if (17 == i3) {
            shlok19();
        } else if (18 == i3) {
            shlok20();
        } else if (19 == i3) {
            shlok21();
        } else if (20 == i3) {
            shlok22();
        } else if (21 == i3) {
            shlok23_24();
        } else if (22 == i3) {
            shlok25();
        } else if (23 == i3) {
            shlok26();
        } else if (24 == i3) {
            shlok27();
        } else if (25 == i3) {
            shlok28();
        } else if (26 == i3) {
            shlok29();
        } else if (27 == i3) {
            shlok30();
        } else if (28 == i3) {
            shlok31_32();
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
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 3");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय ०३ \nकर्मयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૩ \nકર્મયોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 03\nkarma yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा हैं की इस भौतिक जगत में हर व्यक्ति को किसी न किसी प्रकार के कर्म में प्रवृत होना पड़ता है। किन्तु ये ही कर्म उसे इस जगत से बांधते या मुक्त कराते हैं। निष्काम भाव से परमेश्वर की प्रसन्नता के लिए कर्म करने से मनुष्य कर्म के नियम से छूट सकता है और आत्मा तथा परमेश्वर विषयक दिव्य ज्ञान प्राप्त कर सकता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૩ માં માત્ર કર્મ યોગ વિષે વર્ણન છે. કર્મો પ્રકૃતિના ગુણો ને લીધે થાય છે, અને કોઈ પણ મનુષ્ય એક ક્ષણ પણ કર્મ કર્યા વગર રહી શકતો નથી. કર્મ કરવા પર નિષેધ નથી પણ કર્મ કરતાં કરતાં થતી આસક્તિ (રાગ) અને દ્વેષ એ અધ્યાત્મ માર્ગ ના વિઘ્નો છે. રજો ગુણ થી ઉત્પન્ન થતો \"કામ\" (પોતાની પાસે જે નથી તે પામવાની ઈચ્છા) વેરી છે. ટુંકમાં અહીં કર્મ યોગ નું વર્ણન કરી પાછું જ્ઞાન યોગ થી સમાપ્તિ કરી છે કે-શરીર થી ઇન્દ્રિયો પર છે,ઇન્દ્રિયો થી મન પર છે,મન થી બુદ્ધિ પર છે, બુદ્ધિ થી પર આત્મા છે.");
        } else if (2 == i) {
            this.tv1.setText("Third adhyay tells, Everyone must engage in some sort of activity in this material world. But actions can either bind one to this world or liberate one from it. By acting for the pleasure of the Supreme, without selfish motives, one can be liberated from the law of karma (action and reaction) and attain transcendental knowledge of the self and the Supreme.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nज्यायसी चेत्कर्मणस्ते मता बुद्धिर्जनार्दन ।\nतत्किं कर्मणि घोरे मां नियोजयसि केशव ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ -\nજ્યાયસી ચેત્કર્મણસ્તે મતા બુદ્ધિર્જનાર્દન,\nતત્કિં કર્મણિ ઘોરે માં નિયોજયસિ કેશવ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha\nJyaayasee chet karmanaste mataa buddhir janaardana;\nTat kim karmani ghore maam niyojayasi keshava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे जनार्दन! यदि आपको कर्म की अपेक्षा ज्ञान श्रेष्ठ मान्य है तो फिर हे केशव! मुझे भयंकर कर्म में क्यों लगाते हैं?॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે -હે જનાર્દન, જો તમે જ્ઞાનને કર્મ કરતાં વધુ શ્રેષ્ઠ માનતા હો તો મને આ યુદ્ધ કર્મમાં શા પ્રવૃત કરી રહ્યા છો? (૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O Janardana, O Keshava, why do You want to engage me in this ghastly warfare, if You think that intelligence is better than fruitive work?");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("व्यामिश्रेणेव वाक्येन बुद्धिं मोहयसीव मे ।\nतदेकं वद निश्चित्य येन श्रेयोऽहमाप्नुयाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("વ્યામિશ્રેણેવ વાક્યેન બુધ્દિં મોહયસીવ મે,\nતદેકં વદ નિશ્ચિત્ય યેન શ્રેયોહમાપ્નુયામ્.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Vyaamishreneva vaakyena buddhim mohayaseeva me;\nTadekam vada nishchitya yena shreyo’ham aapnuyaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आप मिले हुए-से वचनों से मेरी बुद्धि को मानो मोहित कर रहे हैं। इसलिए उस एक बात को निश्चित करके कहिए जिससे मैं कल्याण को प्राप्त हो जाऊँ॥2॥॥");
        } else if (i2 == 0) {
            this.tv2.setText("તમારા વચનોથી મારી બુદ્ધિ સંભ્રમિત થઈ રહી છે. કૃપા કરીને મને એ માર્ગ બતાવો જે નિશ્ચિત રીતે મારા માટે કલ્યાણકારક હોય.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("My intelligence is bewildered by Your equivocal instructions. Therefore, please tell me decisively which will be most beneficial for me.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nलोकेऽस्मिन्द्विविधा निष्ठा पुरा प्रोक्ता मयानघ ।\nज्ञानयोगेन साङ्‍ख्यानां कर्मयोगेन योगिनाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ -\nલોકેસ્મિન્દ્વિવિધા નિષ્ઠા પુરા પ્રોક્તા મયાનઘ,\nજ્ઞાનયોગેન સાંખ્યાનાં કર્મયોગેન યોગિનામ્.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nLoke’smin dwividhaa nishthaa puraa proktaa mayaanagha;\nJnaanayogena saankhyaanaam karmayogena yoginaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्रीभगवान बोले- हे निष्पाप! इस लोक में दो प्रकार की निष्ठा (साधन की परिपक्व अवस्था अर्थात पराकाष्ठा का नाम 'निष्ठा' है।) मेरे द्वारा पहले कही गई है। उनमें से सांख्य योगियों की निष्ठा तो ज्ञान योग से (माया से उत्पन्न हुए सम्पूर्ण गुण ही गुणों में बरतते हैं, ऐसे समझकर तथा मन, इन्द्रिय और शरीर द्वारा होने वाली सम्पूर्ण क्रियाओं में कर्तापन के अभिमान से रहित होकर सर्वव्यापी सच्चिदानंदघन परमात्मा में एकीभाव से स्थित रहने का नाम 'ज्ञान योग' है, इसी को 'संन्यास', 'सांख्ययोग' आदि नामों से कहा गया है।) और योगियों की निष्ठा कर्मयोग से (फल और आसक्ति को त्यागकर भगवदाज्ञानुसार केवल भगवदर्थ समत्व बुद्धि से कर्म करने का नाम 'निष्काम कर्मयोग' है, इसी को 'समत्वयोग', 'बुद्धियोग', 'कर्मयोग', 'तदर्थकर्म', 'मदर्थकर्म', 'मत्कर्म' आदि नामों से कहा गया है।) होती है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે છે - હે નિષ્પાપ, આ જગમાં શ્રેયપ્રાપ્તિના બે જુદા જુદા માર્ગો - જ્ઞાન યોગ અને કર્મ યોગ મેં તને બતાવ્યા.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: O sinless Arjuna, I have already explained that there are two classes of men who try to realize the self. Some are inclined to understand it by empirical, philosophical speculation, and others by devotional service.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न कर्मणामनारंभान्नैष्कर्म्यं पुरुषोऽश्नुते ।\nन च सन्न्यसनादेव सिद्धिं समधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("ન કર્મણામનારમ્ભાન્નૈષ્કર્મ્યં પુરુષોશ્નુતે,\nન ચ સંન્યસનાદેવ સિદ્ધિં સમધિગચ્છતિ.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Na karmanaam anaarambhaan naishkarmyam purusho’shnute;\nNa cha sannyasanaad eva siddhim samadhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" मनुष्य न तो कर्मों का आरंभ किए बिना निष्कर्मता (जिस अवस्था को प्राप्त हुए पुरुष के कर्म अकर्म हो जाते हैं अर्थात फल उत्पन्न नहीं कर सकते, उस अवस्था का नाम 'निष्कर्मता' है।) को यानी योगनिष्ठा को प्राप्त होता है और न कर्मों के केवल त्यागमात्र से सिद्धि यानी सांख्यनिष्ठा को ही प्राप्त होता है॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("સાંખ્યયોગીઓને જ્ઞાનનો માર્ગ પસંદ પડે છે જ્યારે યોગીઓને કર્મનો માર્ગ. નિષ્કર્મતા પ્રાપ્ત કરવા માટે પણ કર્મનું અનુષ્ઠાન તો કરવું જ પડે છે.  (૪)");
        } else if (2 == i2) {
            this.tv2.setText("Not by merely abstaining from work can one achieve freedom from reaction, nor by renunciation alone can one attain perfection.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न हि कश्चित्क्षणमपि जातु तिष्ठत्यकर्मकृत्‌ ।\nकार्यते ह्यवशः कर्म सर्वः प्रकृतिजैर्गुणैः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન હિ કશ્ચિત્ક્ષણમપિ જાતુ તિષ્ઠત્યકર્મકૃત્,\nકાર્યતે હ્યવશઃ કર્મ સર્વઃ પ્રકૃતિજૈર્ગુણૈઃ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Na hi kashchit kshanamapi jaatu tishthatyakarmakrit;\nKaaryate hyavashah karma sarvah prakritijair gunaih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" निःसंदेह कोई भी मनुष्य किसी भी काल में क्षणमात्र भी बिना कर्म किए नहीं रहता क्योंकि सारा मनुष्य समुदाय प्रकृति जनित गुणों द्वारा परवश हुआ कर्म करने के लिए बाध्य किया जाता है॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેવળ કર્મોનો ત્યાગ કરવાથી કોઈ સિદ્ધિને પ્રાપ્ત કરી શકતું નથી. કર્મ કર્યા વગર કોઈ દેહધારી ક્ષણ માટે પણ રહી શકતો નથી. કારણ કે પ્રકૃતિના ગુણોથી વિવશ થઈને પ્રાણીમાત્ર કર્મ કરવા માટે પ્રવૃત થાય છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("Everyone is forced to act helplessly according to the qualities he has acquired from the modes of material nature; therefore no one can refrain from doing something, not even for a moment.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मेन्द्रियाणि संयम्य य आस्ते मनसा स्मरन्‌ ।\nइन्द्रियार्थान्विमूढात्मा मिथ्याचारः स उच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મેન્દ્રિયાણિ સંયમ્ય ય આસ્તે મનસા સ્મરન્,\nઇન્દ્રિયાર્થાન્વિમૂઢાત્મા મિથ્યાચારઃ સ ઉચ્યતે.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Karmendriyaani samyamya ya aaste manasaa smaran;\nIndriyaarthaan vimoodhaatmaa mithyaachaarah sa uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो मूढ़ बुद्धि मनुष्य समस्त इन्द्रियों को हठपूर्वक ऊपर से रोककर मन से उन इन्द्रियों के विषयों का चिन्तन करता रहता है, वह मिथ्याचारी अर्थात दम्भी कहा जाता है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે મનુષ્ય બહારથી પોતાની ઈન્દ્રિયોનો બળપૂર્વક કાબુ કરે અને મનની અંદર વિષયોનું સેવન કરે છે તે ઢોંગી છે. (૬)");
        } else if (2 == i2) {
            this.tv2.setText("One who restrains the senses of action but whose mind dwells on sense objects certainly deludes himself and is called a pretender.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यस्त्विन्द्रियाणि मनसा नियम्यारभतेऽर्जुन ।\nकर्मेन्द्रियैः कर्मयोगमसक्तः स विशिष्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યસ્ત્વિન્દ્રિયાણિ મનસા નિયમ્યારભતેર્જુન,\nકર્મેન્દ્રિયૈઃ કર્મયોગમસક્તઃ સ વિશિષ્યતે.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Yastwindriyaani manasaa niyamyaarabhate’rjuna;\nKarmendriyaih karmayogam asaktah sa vishishyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("किन्तु हे अर्जुन! जो पुरुष मन से इन्द्रियों को वश में करके अनासक्त हुआ समस्त इन्द्रियों द्वारा कर्मयोग का आचरण करता है, वही श्रेष्ठ है॥7॥॥");
        } else if (i2 == 0) {
            this.tv2.setText("મનથી પોતાની ઈન્દ્રિયોનો સંયમ સાધીને જે ફલાશા વગર સહજ રીતે કર્મોનું અનુષ્ઠાન કરે છે તે ઉત્તમ છે. (૭)");
        } else if (2 == i2) {
            this.tv2.setText("On the other hand, if a sincere person tries to control the active senses by the mind and begins karma-yoga [in Krishna consciousness] without attachment, he is by far superior.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नियतं कुरु कर्म त्वं कर्म ज्यायो ह्यकर्मणः।\nशरीरयात्रापि च ते न प्रसिद्धयेदकर्मणः ॥");
        } else if (i == 0) {
            this.tv1.setText("નિયતં કુરુ કર્મ ત્વં કર્મ જ્યાયો હ્યકર્મણઃ,\nશરીરયાત્રાપિ ચ તે ન પ્રસિદ્ધ્યેદકર્મણઃ.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Niyatam kuru karma twam karma jyaayo hyakarmanah;\nShareerayaatraapi cha te na prasiddhyed akarmanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तू शास्त्रविहित कर्तव्यकर्म कर क्योंकि कर्म न करने की अपेक्षा कर्म करना श्रेष्ठ है तथा कर्म न करने से तेरा शरीर-निर्वाह भी नहीं सिद्ध होगा॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("તારે માટે જે પણ કર્મ શાસ્ત્રમાં બતાવવામાં આવ્યું છે તે તું કર કારણ કે કર્મ ન કરવા (કર્મનો ત્યાગ કરવા) કરતાં અનાસક્ત રહીને કર્મ કરવાનું શ્રેષ્ઠ કહેવાયું છે. (૮)");
        } else if (2 == i2) {
            this.tv2.setText("Perform your prescribed duty, for doing so is better than not working. One cannot even maintain one’s physical body without work.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यज्ञार्थात्कर्मणोऽन्यत्र लोकोऽयं कर्मबंधनः ।\nतदर्थं कर्म कौन्तेय मुक्तसंगः समाचर ॥");
        } else if (i == 0) {
            this.tv1.setText("યજ્ઞાર્થાત્કર્મણોન્યત્ર લોકોયં કર્મબન્ધનઃ,\nતદર્થં કર્મ કૌન્તેય મુક્તસંગઃ સમાચર.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Yajnaarthaat karmano’nyatra loko’yam karmabandhanah;\nTadartham karma kaunteya muktasangah samaachara.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" यज्ञ के निमित्त किए जाने वाले कर्मों से अतिरिक्त दूसरे कर्मों में लगा हुआ ही यह मुनष्य समुदाय कर्मों से बँधता है। इसलिए हे अर्जुन! तू आसक्ति से रहित होकर उस यज्ञ के निमित्त ही भलीभाँति कर्तव्य कर्म कर॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("જો તું કર્મ નહીં કરે તો તારો જીવનનિર્વાહ પણ કેવી રીતે થશે ? આસક્તિથી કરેલ કર્મો માનવને કર્મબંધનથી બાંધે છે.  એથી હે અર્જુન, તું કર્મ કર, પરંતુ અનાસક્ત (અલિપ્ત) રહીને કર (૯)");
        } else if (2 == i2) {
            this.tv2.setText("Work done as a sacrifice for Vishnu has to be performed, otherwise work causes bondage in this material world. Therefore, O son of Kunti, perform your prescribed duties for His satisfaction, and in that way you will always remain free from bondage.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सहयज्ञाः प्रजाः सृष्टा पुरोवाचप्रजापतिः ।\nअनेन प्रसविष्यध्वमेष वोऽस्त्विष्टकामधुक्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સહયજ્ઞાઃ પ્રજાઃ સૃષ્ટ્વા પુરોવાચ પ્રજાપતિઃ,\nઅનેન પ્રસવિષ્યધ્વમેષ વોસ્ત્વિષ્ટકામધુક્.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Sahayajnaah prajaah srishtwaa purovaacha prajaapatih;\nAnena prasavishyadhwam esha vo’stvishtakaamadhuk.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("प्रजापति ब्रह्मा ने कल्प के आदि में यज्ञ सहित प्रजाओं को रचकर उनसे कहा कि तुम लोग इस यज्ञ द्वारा वृद्धि को प्राप्त होओ और यह यज्ञ तुम लोगों को इच्छित भोग प्रदान करने वाला हो॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("બ્રહ્માએ સૃષ્ટિના આરંભમાં જ કહ્યું કે ‘યજ્ઞ (કર્મ) કરતાં રહો અને વૃદ્ધિ પામતા રહો. યજ્ઞ (કર્મ) તમારી ઈચ્છાઓની પૂર્તિનું સાધન બનો.’ (૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("In the beginning of creation, the Lord of all creatures sent forth generations of men and demigods, along with sacrifices for Vishnu, and blessed them by saying, “Be thou happy by this yajna [sacrifice] because its performance will bestow upon you everything desirable for living happily and achieving liberation.”");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("देवान्भावयतानेन ते देवा भावयन्तु वः ।\nपरस्परं भावयन्तः श्रेयः परमवाप्स्यथ ॥");
        } else if (i == 0) {
            this.tv1.setText("દેવાન્ભાવયતાનેન તે દેવા ભાવયન્તુ વઃ,\nપરસ્પરં ભાવયન્તઃ શ્રેયઃ પરમવાપ્સ્યથ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Devaan bhaavayataanena te devaa bhaavayantu vah;\nParasparam bhaavayantah shreyah param avaapsyatha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तुम लोग इस यज्ञ द्वारा देवताओं को उन्नत करो और वे देवता तुम लोगों को उन्नत करें। इस प्रकार निःस्वार्थ भाव से एक-दूसरे को उन्नत करते हुए तुम लोग परम कल्याण को प्राप्त हो जाओगे॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("યજ્ઞ કરતાં તમે દેવોને પ્રસન્ન રાખો અને દેવો તમને પ્રસન્ન રાખશે. એમ એકમેકને સંતુષ્ઠ રાખતાં તમે પરમ કલ્યાણને પ્રાપ્ત કરશો. (૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("The demigods, being pleased by sacrifices, will also please you, and thus, by cooperation between men and demigods, prosperity will reign for all.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12_13() {
        setTitle("Shlok 12,13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इष्टान्भोगान्हि वो देवा दास्यन्ते यज्ञभाविताः ।\nतैर्दत्तानप्रदायैभ्यो यो भुंक्ते स्तेन एव सः ॥\nयज्ञशिष्टाशिनः सन्तो मुच्यन्ते सर्वकिल्बिषैः ।\nभुञ्जते ते त्वघं पापा ये पचन्त्यात्मकारणात्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇષ્ટાન્ભોગાન્હિ વો દેવા દાસ્યન્તે યજ્ઞભાવિતાઃ,\nતૈર્દત્તાનપ્રદાયૈભ્યો યો ભુઙક્તે સ્તેન એવ સઃ.(૧૨)\nયજ્ઞશિષ્ટાશિનઃ સન્તો મુચ્યન્તે સર્વકિલ્બિષૈઃ,\nભુઞ્જતે તે ત્વઘં પાપા યે પચન્ત્યાત્મકારણાત્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Ishtaan bhogaan hi vo devaa daasyante yajnabhaavitaah;\nTair dattaan apradaayaibhyo yo bhungkte stena eva sah.\nYajnashishtaashinah santo muchyante sarva kilbishaih;\nBhunjate te twagham paapaa ye pachantyaatma kaaranaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यज्ञ द्वारा बढ़ाए हुए देवता तुम लोगों को बिना माँगे ही इच्छित भोग निश्चय ही देते रहेंगे। इस प्रकार उन देवताओं द्वारा दिए हुए भोगों को जो पुरुष उनको बिना दिए स्वयं भोगता है, वह चोर ही है। यज्ञ से बचे हुए अन्न को खाने वाले श्रेष्ठ पुरुष सब पापों से मुक्त हो जाते हैं और जो पापी लोग अपना शरीर-पोषण करने के लिए ही अन्न पकाते हैं, वे तो पाप को ही खाते हैं॥12-13॥");
        } else if (i2 == 0) {
            this.tv2.setText("યજ્ઞથી સંતુષ્ઠ દેવો તમને ઈચ્છિત ભોગો આપશે. એને યજ્ઞભાવથી (દેવોને સમર્પિત કર્યા પછી) આરોગવાથી વ્યક્તિ સર્વ પાપથી વિમુક્ત થશે. એ ભોગોનો ઉપભોગ જે એકલપેટા બનીને કરશે તે પાપના ભાગી થશે અને ચોર ગણાશે. (૧૨-૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("In charge of the various necessities of life, the demigods, being satisfied by the performance of yajna [sacrifice], will supply all necessities to you. But he who enjoys such gifts without offering them to the demigods in return is certainly a thief. The devotees of the Lord are released from all kinds of sins because they eat food which is offered first for sacrifice. Others, who prepare food for personal sense enjoyment, verily eat only sin.(12-13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14_15() {
        setTitle("Shlok 14,15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अन्नाद्भवन्ति भूतानि पर्जन्यादन्नसम्भवः ।\nयज्ञाद्भवति पर्जन्यो यज्ञः कर्मसमुद्भवः ॥\nकर्म ब्रह्मोद्भवं विद्धि ब्रह्माक्षरसमुद्भवम्‌ ।\nतस्मात्सर्वगतं ब्रह्म नित्यं यज्ञे प्रतिष्ठितम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અન્નાદ્ભવન્તિ ભૂતાનિ પર્જન્યાદન્નસમ્ભવઃ,\nયજ્ઞાદ્ભવતિ પર્જન્યો યજ્ઞઃ કર્મસમુદ્ભવઃ.(૧૪)\nકર્મ બ્રહ્મોદ્ભવં વિદ્ધિ બ્રહ્માક્ષરસમુદ્ભવમ્,\nતસ્માત્સર્વગતં બ્રહ્મ નિત્યં યજ્ઞે પ્રતિષ્ઠિતમ્.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Annaad bhavanti bhootaani parjanyaad anna sambhavah;\nYajnaad bhavati parjanyo yajnah karma samudbhavah.\nKarma brahmodbhavam viddhi brahmaakshara samudbhavam;\nTasmaat sarvagatam brahma nityam yajne pratishthitam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सम्पूर्ण प्राणी अन्न से उत्पन्न होते हैं, अन्न की उत्पत्ति वृष्टि से होती है, वृष्टि यज्ञ से होती है और यज्ञ विहित कर्मों से उत्पन्न होने वाला है। कर्मसमुदाय को तू वेद से उत्पन्न और वेद को अविनाशी परमात्मा से उत्पन्न हुआ जान। इससे सिद्ध होता है कि सर्वव्यापी परम अक्षर परमात्मा सदा ही यज्ञ में प्रतिष्ठित है॥14-15॥");
        } else if (i2 == 0) {
            this.tv2.setText("શરીર અન્નમય કોષ છે. બધા જીવો અન્નથી જ પેદા થાય છે અને અન્નથી જ પોષાય છે. અન્ન વરસાદ થવાથી ઉત્પન્ન થાય છે. વરસાદ યજ્ઞ કરવાથી થાય છે. યજ્ઞ કર્મથી થાય છે અને કર્મ વેદથી થાય છે. પરંતુ વેદ તો પરમાત્મા વડે ઉત્પન્ન કરાયેલ છે. એથી એમ કહી શકાય કે સર્વવ્યાપક પરમાત્મા જ યજ્ઞમાં પ્રતિષ્ઠિત થયેલા છે. (યજ્ઞ વડે પરમાત્માની જ પૂજા કરાય છે)(૧૪-૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("All living bodies subsist on food grains, which are produced from rains. Rains are produced by performance of yajna [sacrifice], and yajna is born of prescribed duties. Regulated activities are prescribed in the Vedas, and the Vedas are directly manifested from the Supreme Personality of Godhead. Consequently the all-pervading Transcendence is eternally situated in acts of sacrifice.(14-15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एवं प्रवर्तितं चक्रं नानुवर्तयतीह यः ।\nअघायुरिन्द्रियारामो मोघं पार्थ स जीवति ॥");
        } else if (i == 0) {
            this.tv1.setText("એવં પ્રવર્તિતં ચક્રં નાનુવર્તયતીહ યઃ,\nઅઘાયુરિન્દ્રિયારામો મોઘં પાર્થ સ જીવતિ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Evam pravartitam chakram naanuvartayateeha yah;\nAghaayur indriyaaraamo mogham paartha sa jeevati");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे पार्थ! जो पुरुष इस लोक में इस प्रकार परम्परा से प्रचलित सृष्टिचक्र के अनुकूल नहीं बरतता अर्थात अपने कर्तव्य का पालन नहीं करता, वह इन्द्रियों द्वारा भोगों में रमण करने वाला पापायु पुरुष व्यर्थ ही जीता है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ, જે વ્યક્તિ આ રીતે સૃષ્ટિચક્રને અનુસરીને નથી ચાલતો તે પોતાની ઈંદ્રિયોના ભોગમાં રમવાવાળો તથા વ્યર્થ જીવન જીવનાર ગણાય છે. (૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("My dear Arjuna, one who does not follow in human life the cycle of sacrifice thus established by the Vedas certainly leads a life full of sin. Living only for the satisfaction of the senses, such a person lives in vain.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यस्त्वात्मरतिरेव स्यादात्मतृप्तश्च मानवः ।\nआत्मन्येव च सन्तुष्टस्तस्य कार्यं न विद्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યસ્ત્વાત્મરતિરેવ સ્યાદાત્મતૃપ્તશ્ચ માનવઃ,\nઆત્મન્યેવ ચ સન્તુષ્ટસ્તસ્ય કાર્યં ન વિદ્યતે.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Yastwaatmaratir eva syaad aatmatriptashcha maanavah;\nAatmanyeva cha santushtas tasya kaaryam na vidyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" परन्तु जो मनुष्य आत्मा में ही रमण करने वाला और आत्मा में ही तृप्त तथा आत्मा में ही सन्तुष्ट हो, उसके लिए कोई कर्तव्य नहीं है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("પરંતુ જે વ્યક્તિ આત્મસ્થિત અને આત્મતૃપ્ત છે, પોતાના આત્મામાં જ સંતોષ માને છે, તેને કોઈ કર્મ કરવાનું રહેતું નથી. (૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("But for one who takes pleasure in the self, whose human life is one of self-realization, and who is satisfied in the self only, fully satiated—for him there is no duty.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नैव तस्य कृतेनार्थो नाकृतेनेह कश्चन ।\nन चास्य सर्वभूतेषु कश्चिदर्थव्यपाश्रयः ॥");
        } else if (i == 0) {
            this.tv1.setText("નૈવ તસ્ય કૃતેનાર્થો નાકૃતેનેહ કશ્ચન,\nન ચાસ્ય સર્વભૂતેષુ કશ્ચિદર્થવ્યપાશ્રયઃ.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Naiva tasya kritenaartho naakriteneha kashchana;\nNa chaasya sarvabhooteshu kashchidartha vyapaashrayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उस महापुरुष का इस विश्व में न तो कर्म करने से कोई प्रयोजन रहता है और न कर्मों के न करने से ही कोई प्रयोजन रहता है तथा सम्पूर्ण प्राणियों में भी इसका किञ्चिन्मात्र भी स्वार्थ का संबंध नहीं रहता॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("એવા મહાપુરુષને માટે કર્મ કરવાનું કે ન કરવાનું કશું પ્રયોજન રહેતું નથી. એને સર્વ જીવો સાથે કોઈ પ્રકારનો સ્વાર્થસંબંધ નથી રહેતો. (૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("A self-realized man has no purpose to fulfill in the discharge of his prescribed duties, nor has he any reason not to perform such work. Nor has he any need to depend on any other living being.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मादसक्तः सततं कार्यं कर्म समाचर ।\nअसक्तो ह्याचरन्कर्म परमाप्नोति पुरुषः ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માદસક્તઃ સતતં કાર્યં કર્મ સમાચર,\nઅસક્તો હ્યાચરન્કર્મ પરમાપ્નોતિ પુરુષ:(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaad asaktah satatam kaaryam karma samaachara;\nAsakto hyaacharan karma param aapnoti poorushah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए तू निरन्तर आसक्ति से रहित होकर सदा कर्तव्यकर्म को भलीभाँति करता रह क्योंकि आसक्ति से रहित होकर कर्म करता हुआ मनुष्य परमात्मा को प्राप्त हो जाता है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે પાર્થ, આસક્ત થયા વગર કર્મ કર. નિષ્કામ કર્મ કરનાર વ્યક્તિ પરમાત્માને પ્રાપ્ત કરી લે છે. (૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, without being attached to the fruits of activities, one should act as a matter of duty, for by working without attachment one attains the Supreme.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मणैव हि संसिद्धिमास्थिता जनकादयः ।\nलोकसंग्रहमेवापि सम्पश्यन्कर्तुमर्हसि ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મણૈવ હિ સંસિદ્ધિમાસ્થિતા જનકાદયઃ,\nલોકસંગ્રહમેવાપિ સંપશ્યન્કર્તુમર્હસિ.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Karmanaiva hi samsiddhim aasthitaa janakaadayah;\nLokasangraham evaapi sampashyan kartum arhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जनकादि ज्ञानीजन भी आसक्ति रहित कर्मद्वारा ही परम सिद्धि को प्राप्त हुए थे, इसलिए तथा लोकसंग्रह को देखते हुए भी तू कर्म करने के ही योग्य है अर्थात तुझे कर्म करना ही उचित है॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("મહારાજા જનક જેવા નિષ્કામ કર્મનું આચરણ કરતા જ પરમ સિદ્ધિને પામ્યા છે. વળી તારે (અંગત સ્વાર્થ માટે નહીં કરવું હોય તો પણ) લોકસંગ્રહાર્થે, સંસારના ભલા માટે (યુદ્ધ) કર્મ કરવું જ રહ્યું.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Kings such as Janaka attained perfection solely by performance of prescribed duties. Therefore, just for the sake of educating the people in general, you should perform your work.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यद्यदाचरति श्रेष्ठस्तत्तदेवेतरो जनः ।\nस यत्प्रमाणं कुरुते लोकस्तदनुवर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("યદ્યદાચરતિ શ્રેષ્ઠસ્તત્તદેવેતરો જનઃ,\nસ યત્પ્રમાણં કુરુતે લોકસ્તદનુવર્તતે.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Yadyad aacharati shreshthas tattadevetaro janah;\nSa yat pramaanam kurute lokas tad anuvartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्रेष्ठ पुरुष जो-जो आचरण करता है, अन्य पुरुष भी वैसा-वैसा ही आचरण करते हैं। वह जो कुछ प्रमाण कर देता है, समस्त मनुष्य-समुदाय उसी के अनुसार बरतने लग जाता है (यहाँ क्रिया में एकवचन है, परन्तु 'लोक' शब्द समुदायवाचक होने से भाषा में बहुवचन की क्रिया लिखी गई है।)॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રેષ્ઠ પુરુષો જે જે કરે છે એને અનુસરીને સાધારણ લોકો પોતાના કામ કરે છે. (૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("Whatever action a great man performs, common men follow. And whatever standards he sets by exemplary acts, all the world pursues.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न मे पार्थास्ति कर्तव्यं त्रिषु लोकेषु किंचन ।\nनानवाप्तमवाप्तव्यं वर्त एव च कर्मणि ॥");
        } else if (i == 0) {
            this.tv1.setText("ન મે પાર્થાસ્તિ કર્તવ્યં ત્રિષુ લોકેષુ કિઞ્ચન,\nનાનવાપ્તમવાપ્તવ્યં વર્ત એવ ચ કર્મણી (૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Na me paarthaasti kartavyam trishu lokeshu kinchana;\nNaanavaaptam avaaptavyam varta eva cha karmani.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! मुझे इन तीनों लोकों में न तो कुछ कर्तव्य है और न कोई भी प्राप्त करने योग्य वस्तु अप्राप्त है, तो भी मैं कर्म में ही बरतता हूँ॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("એમ તો મારે પણ કર્મ કરવું આવશ્યક નથી. આ સંસારમાં એવું કંઈ મેળવવાનું મારે માટે બાકી રહ્યું નથી છતાં પણ હું કર્મમાં પ્રવૃત્ત રહું છું. (૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, there is no work prescribed for Me within all the three planetary systems. Nor am I in want of anything, nor have I a need to obtain anything—and yet I am engaged in prescribed duties.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23_24() {
        setTitle("Shlok 23,24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदि ह्यहं न वर्तेयं जातु कर्मण्यतन्द्रितः ।\nमम वर्त्मानुवर्तन्ते मनुष्याः पार्थ सर्वशः ॥\nयदि उत्सीदेयुरिमे लोका न कुर्यां कर्म चेदहम्‌ ।\nसंकरस्य च कर्ता स्यामुपहन्यामिमाः प्रजाः ॥");
        } else if (i == 0) {
            this.tv1.setText("યદિ હ્યહં ન વર્તેયં જાતુ કર્મણ્યતન્દ્રિતઃ,\nમમ વર્ત્માનુવર્તન્તે મનુષ્યાઃ પાર્થ સર્વશ: (૨૩)\nઉત્સીદેયુરિમે લોકા ન કુર્યાં કર્મ ચેદહમ્,\nસઙ્કરસ્ય ચ કર્તા સ્યામુપહન્યામિમાઃ પ્રજા: (૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Yadi hyaham na varteyam jaatu karmanyatandritah;\nMama vartmaanuvartante manushyaah paartha sarvashah.\nUtseedeyur ime lokaa na kuryaam karma ched aham;\nSankarasya cha kartaa syaam upahanyaam imaah prajaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि हे पार्थ! यदि कदाचित्‌ मैं सावधान होकर कर्मों में न बरतूँ तो बड़ी हानि हो जाए क्योंकि मनुष्य सब प्रकार से मेरे ही मार्ग का अनुसरण करते हैं। इसलिए यदि मैं कर्म न करूँ तो ये सब मनुष्य नष्ट-भ्रष्ट हो जाएँ और मैं संकरता का करने वाला होऊँ तथा इस समस्त प्रजा को नष्ट करने वाला बनूँ॥23-24॥");
        } else if (i2 == 0) {
            this.tv2.setText("કારણ કે જો હું કર્મ કરવાનું છોડી દઉં તો મારું અનુસરણ કરીને બીજા લોકો પણ કર્મ કરવાનું છોડી દે. અને એમ થાય તો તેઓ પોતાનો નાશ નોંતરે અને હું એમના વિનાશનું કારણ બનું. (૨૩-૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("For if I ever failed to engage in carefully performing prescribed duties, O Partha, certainly all men would follow My path. If I did not perform prescribed duties, all these worlds would be put to ruination. I would be the cause of creating unwanted population, and I would thereby destroy the peace of all living beings.(23-24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सक्ताः कर्मण्यविद्वांसो यथा कुर्वन्ति भारत ।\nकुर्याद्विद्वांस्तथासक्तश्चिकीर्षुर्लोकसंग्रहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સક્તાઃ કર્મણ્યવિદ્વાંસો યથા કુર્વન્તિ ભારત,\nકુર્યાદ્વિદ્વાંસ્તથાસક્તશ્ચિકીર્ષુર્લોકસંગ્રહમ્.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Saktaah karmanyavidwaamso yathaa kurvanti bhaarata;\nKuryaad vidwaam stathaa saktash chikeershur lokasangraham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे भारत! कर्म में आसक्त हुए अज्ञानीजन जिस प्रकार कर्म करते हैं, आसक्तिरहित विद्वान भी लोकसंग्रह करना चाहता हुआ उसी प्रकार कर्म करे॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, કર્મ કરવું અતિ આવશ્યક છે પરંતુ અજ્ઞાની લોકોની જેમ ફળની આશાથી યુક્ત થઈને નહીં, પરંતુ જ્ઞાનીઓની પેઠે નિષ્કામ ભાવે, ફળની આસક્તિથી રહિત થઈને.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("As the ignorant perform their duties with attachment to results, the learned may similarly act, but without attachment, for the sake of leading people on the right path.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न बुद्धिभेदं जनयेदज्ञानां कर्मसङि्गनाम्‌ ।\nजोषयेत्सर्वकर्माणि विद्वान्युक्तः समाचरन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ન બુધ્દિભેદં જનયેદજ્ઞાનાં કર્મસઙગિનામ્,\nજોષયેત્સર્વકર્માણિ વિદ્વાન્ યુક્તઃસમાચારન (૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Na buddhibhedam janayed ajnaanaam karmasanginaam;\nJoshayet sarva karmaani vidwaan yuktah samaacharan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परमात्मा के स्वरूप में अटल स्थित हुए ज्ञानी पुरुष को चाहिए कि वह शास्त्रविहित कर्मों में आसक्ति वाले अज्ञानियों की बुद्धि में भ्रम अर्थात कर्मों में अश्रद्धा उत्पन्न न करे, किन्तु स्वयं शास्त्रविहित समस्त कर्म भलीभाँति करता हुआ उनसे भी वैसे ही करवाए॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("જ્ઞાની પુરુષે પોતે તો સમતાનું આચરણ કરીને કર્મનું અનુષ્ઠાન કરવું જ રહ્યું પણ સાથે સાથે જેઓ આસક્તિભાવથી કર્મ કરે છે એમનામાં અશ્રદ્ધા પણ ઉત્પન્ન ન કરવી જોઈએ. (૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("So as not to disrupt the minds of ignorant men attached to the fruitive results of prescribed duties, a learned person should not induce them to stop work. Rather, by working in the spirit of devotion, he should engage them in all sorts of activities [for the gradual development of Krishna consciousness].");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रकृतेः क्रियमाणानि गुणैः कर्माणि सर्वशः ।\nअहंकारविमूढात्मा कर्ताहमिति मन्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રકૃતેઃ ક્રિયમાણાનિ ગુણૈઃ કર્માણિ સર્વશઃ,\nઅહઙ્કારવિમૂઢાત્મા કર્તાહમિતિ મન્યતે.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Prakriteh kriyamaanaani gunaih karmaani sarvashah;\nAhamkaaravimoodhaatmaa kartaaham iti manyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" वास्तव में सम्पूर्ण कर्म सब प्रकार से प्रकृति के गुणों द्वारा किए जाते हैं, तो भी जिसका अन्तःकरण अहंकार से मोहित हो रहा है, ऐसा अज्ञानी 'मैं कर्ता हूँ' ऐसा मानता है॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ પ્રકારના કર્મો પ્રકૃતિના ગુણોથી પ્રેરાઈને થતાં હોય છે. છતાં અહંકારથી વિમૂઢ થયેલ મનુષ્ય પોતાને એનો કર્તા માને છે. (૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("Te spirit soul bewildered by the influence of false ego thinks himself the doer of activities that are in actuality carried out by the three modes of material nature.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्त्ववित्तु महाबाहो गुणकर्मविभागयोः ।\nगुणा गुणेषु वर्तन्त इति मत्वा न सज्जते ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્ત્વવિત્તુ મહાબાહો ગુણકર્મવિભાગયોઃ,\nગુણા ગુણેષુ વર્તન્ત ઇતિ મત્વા નસજ્જતે.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Tattwavittu mahaabaaho gunakarma vibhaagayoh;\nGunaa guneshu vartanta iti matwaa na sajjate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु हे महाबाहो! गुण विभाग और कर्म विभाग (त्रिगुणात्मक माया के कार्यरूप पाँच महाभूत और मन, बुद्धि, अहंकार तथा पाँच ज्ञानेन्द्रियाँ, पाँच कर्मेन्द्रियाँ और शब्दादि पाँच विषय- इन सबके समुदाय का नाम 'गुण विभाग' है और इनकी परस्पर की चेष्टाओं का नाम 'कर्म विभाग' है।) के तत्व (उपर्युक्त 'गुण विभाग' और 'कर्म विभाग' से आत्मा को पृथक अर्थात्‌ निर्लेप जानना ही इनका तत्व जानना है।) को जानने वाला ज्ञान योगी सम्पूर्ण गुण ही गुणों में बरत रहे हैं, ऐसा समझकर उनमें आसक्त नहीं होता। ॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો, પ્રકૃતિના ગુણસ્વભાવને અને કર્મના વિભાગોને યથાર્થ જાણનાર જ્ઞાની કર્મ માટે પ્રકૃતિના ગુણો જ કારણભૂત છે એવું માનીને એમાં આસક્ત થતા નથી. (૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("One who is in knowledge of the Absolute Truth, O mighty-armed, does not engage himself in the senses and sense gratification, knowing well the differences between work in devotion and work for fruitive results.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रकृतेर्गुणसम्मूढ़ाः सज्जन्ते गुणकर्मसु ।\nतानकृत्स्नविदो मन्दान्कृत्स्नविन्न विचालयेत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રકૃતેર્ગુણસમ્મૂઢાઃ સજ્જન્તે ગુણકર્મસુ,\nતાનકૃત્સ્નવિદો મન્દાન્કૃત્સ્નવિન્ન વિચાલયેત્.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Prakriter gunasammoodhaah sajjante gunakarmasu;\nTaan akritsnavido mandaan kritsnavin na vichaalayet.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("प्रकृति के गुणों से अत्यन्त मोहित हुए मनुष्य गुणों में और कर्मों में आसक्त रहते हैं, उन पूर्णतया न समझने वाले मन्दबुद्धि अज्ञानियों को पूर्णतया जानने वाला ज्ञानी विचलित न करे॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("તો સાથે સાથે જેઓ પ્રકૃતિના ગુણોથી મોહ પામીને કર્મને આસક્તિભાવે કરે છે તેમને વિચલિત કરવાની કોશિશ કરતા નથી. (૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("Bewildered by the modes of material nature, the ignorant fully engage themselves in material activities and become attached. But the wise should not unsettle them, although these duties are inferior due to the performers’ lack of knowledge.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मयि सर्वाणि कर्माणि सन्नयस्याध्यात्मचेतसा ।\nनिराशीर्निर्ममो भूत्वा युध्यस्व विगतज्वरः ॥");
        } else if (i == 0) {
            this.tv1.setText("મયિ સર્વાણિ કર્માણિ સંન્યસ્યાધ્યાત્મચેતસા,\nનિરાશીર્નિર્મમો ભૂત્વા યુધ્યસ્વ વિગતજ્વરઃ.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Mayi sarvaani karmaani sannyasyaadhyaatma chetasaa;\nNiraasheer nirmamo bhootwaa yudhyaswa vigatajwarah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मुझ अन्तर्यामी परमात्मा में लगे हुए चित्त द्वारा सम्पूर्ण कर्मों को मुझमें अर्पण करके आशारहित, ममतारहित और सन्तापरहित होकर युद्ध कर॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, મારામાં મનને સ્થિર કરી, આશા, તૃષ્ણા તથા શોકરહિત થઈને અનાસક્ત ભાવે (યુદ્ધ) કર્મમાં પ્રવૃત થા.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, O Arjuna, surrendering all your works unto Me, with full knowledge of Me, without desires for profit, with no claims to proprietorship, and free from lethargy, fight.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31_32() {
        setTitle("Shlok 31,32");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये मे मतमिदं नित्यमनुतिष्ठन्ति मानवाः ।\nश्रद्धावन्तोऽनसूयन्तो मुच्यन्ते तेऽति कर्मभिः ॥\nये त्वेतदभ्यसूयन्तो नानुतिष्ठन्ति मे मतम्‌ ।\nसर्वज्ञानविमूढांस्तान्विद्धि नष्टानचेतसः ॥");
        } else if (i == 0) {
            this.tv1.setText("યે મે મતમિદં નિત્યમનુતિષ્ઠન્તિ માનવા:,\nશ્રદ્ધાવન્તોનસૂયન્તો મુચ્યન્તે તેપિ કર્મભિઃ.(૩૧)\nયે ત્વેતદભ્યસૂયન્તો નાનુતિષ્ઠન્તિ મે મતમ્,\nસર્વજ્ઞાનવિમૂઢાંસ્તાન્વિદ્ધિ નષ્ટાનચેતસઃ.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Ye me matam idam nityam anutishthanti maanavaah;\nShraddhaavanto’nasooyanto muchyante te’pi karmabhih.\nYe twetad abhyasooyanto naanutishthanti me matam;\nSarvajnaanavimoodhaam staan viddhi nashtaan achetasah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कोई मनुष्य दोषदृष्टि से रहित और श्रद्धायुक्त होकर मेरे इस मत का सदा अनुसरण करते हैं, वे भी सम्पूर्ण कर्मों से छूट जाते हैं।  परन्तु जो मनुष्य मुझमें दोषारोपण करते हुए मेरे इस मत के अनुसार नहीं चलते हैं, उन मूर्खों को तू सम्पूर्ण ज्ञानों में मोहित और नष्ट हुए ही समझ॥31-32॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે વ્યક્તિ દોષદૃષ્ટિથી મુક્ત થઈ મારામાં પૂર્ણ શ્રદ્ધા રાખી મારા વચનોને અનુસરે છે, એ કર્મબંધનથી મુક્તિ મેળવે છે. પરંતુ જે મનુષ્ય દ્વેષબુદ્ધિથી મારા કહેલ માર્ગનું અનુસરણ નથી કરતા તેને તું વિમૂઢ, જ્ઞાનહીન તથા મૂર્ખ સમજજે. (૩૧-૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("Those persons who execute their duties according to My injunctions and who follow this teaching faithfully, without envy, become free from the bondage of fruitive actions. But those who, out of envy, disregard these teachings and do not follow them are to be considered bereft of all knowledge, befooled, and ruined in their endeavors for perfection. (31-32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33 ");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सदृशं चेष्टते स्वस्याः प्रकृतेर्ज्ञानवानपि ।\nप्रकृतिं यान्ति भूतानि निग्रहः किं करिष्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("સદૃશં ચેષ્ટતે સ્વસ્યાઃ પ્રકૃતેર્જ્ઞાનવાનપિ,\nપ્રકૃતિં યાન્તિ ભૂતાનિ નિગ્રહઃ કિં કરિષ્યતિ.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Sadrisham cheshtate swasyaah prakriter jnaanavaan api;\nPrakritim yaanti bhootaani nigrahah kim karishyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सभी प्राणी प्रकृति को प्राप्त होते हैं अर्थात अपने स्वभाव के परवश हुए कर्म करते हैं। ज्ञानवान्‌ भी अपनी प्रकृति के अनुसार चेष्टा करता है। फिर इसमें किसी का हठ क्या करेगा॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("દરેક પ્રાણી પોતાની સ્વભાવગત પ્રકૃતિને વશ થઈને કર્મ કરે છે. જ્ઞાની પણ એવી જ રીતે સ્વભાવને વશ થઈ કર્મો કરે છે. એથી મિથ્યા સંયમ કરવાનો કોઈ અર્થ નથી.(૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("Even a man of knowledge acts according to his own nature, for everyone follows the nature he has acquired from the three modes. What can repression accomplish?");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इन्द्रियस्येन्द्रियस्यार्थे रागद्वेषौ व्यवस्थितौ ।\nतयोर्न वशमागच्छेत्तौ ह्यस्य परिपन्थिनौ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇન્દ્રિયસ્યેન્દ્રિયસ્યાર્થે રાગદ્વેષૌ વ્યવસ્થિતૌ,\nતયોર્ન વશમાગચ્છેત્તૌ હ્યસ્ય પરિપન્થિનૌ.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Indriyasyendriyasyaarthe raagadweshau vyavasthitau;\nTayor na vasham aagacchet tau hyasya paripanthinau.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इन्द्रिय-इन्द्रिय के अर्थ में अर्थात प्रत्येक इन्द्रिय के विषय में राग और द्वेष छिपे हुए स्थित हैं। मनुष्य को उन दोनों के वश में नहीं होना चाहिए क्योंकि वे दोनों ही इसके कल्याण मार्ग में विघ्न करने वाले महान्‌ शत्रु हैं॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("પ્રત્યેક ઈન્દ્રિયના વિષયોમાં રાગ અને દ્વેષ રહેલા છે. રાગ અને દ્વેષ આત્મકલ્યાણના માર્ગમાં મહાન શત્રુઓ છે એટલે એને વશ ન થતો. (૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("There are principles to regulate attachment and aversion pertaining to the senses and their objects. One should not come under the control of such attachment and aversion, because they are stumbling blocks on the path of self-realization.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        setTitle("Shlok 35");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रेयान्स्वधर्मो विगुणः परधर्मात्स्वनुष्ठितात्‌ ।\nस्वधर्मे निधनं श्रेयः परधर्मो भयावहः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રેયાન્સ્વધર્મો વિગુણઃ પરધર્માત્સ્વનુષ્ઠિતાત્,\nસ્વધર્મે નિધનં શ્રેયઃ પરધર્મો ભયાવહઃ.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Shreyaan swadharmo vigunah paradharmaat swanushthitaat;\nSwadharme nidhanam shreyah paradharmo bhayaavahah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" अच्छी प्रकार आचरण में लाए हुए दूसरे के धर्म से गुण रहित भी अपना धर्म अति उत्तम है। अपने धर्म में तो मरना भी कल्याणकारक है और दूसरे का धर्म भय को देने वाला है॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("એટલું યાદ રાખજે કે પરધર્મ ગમે તેટલો સારો હોય પણ સ્વધર્મ કરતાં ઉત્તમ કદાપિ નથી. એથી તું તારા સ્વધર્મનું (ક્ષત્રિયના ધર્મ) પાલન કરીને વીરગતિને પ્રાપ્ત કરીશ તો એ પરધર્મ (સંન્યાસીના)  કરતાં ઉત્તમ અને કલ્યાણકારક છે.(૩૫)");
        } else if (2 == i2) {
            this.tv2.setText("It is far better to discharge one’s prescribed duties, even though faultily, than another’s duties perfectly. Destruction in the course of performing one’s own duty is better than engaging in another’s duties, for to follow another’s path is dangerous.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36() {
        setTitle("Shlok 36");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाचः\nअथ केन प्रयुक्तोऽयं पापं चरति पुरुषः ।\nअनिच्छन्नपि वार्ष्णेय बलादिव नियोजितः ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nઅથ કેન પ્રયુક્તોયં પાપં ચરતિ પૂરુષઃ,\nઅનિચ્છન્નપિ વાર્ષ્ણેય બલાદિવ નિયોજિતઃ.(૩૬)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nAtha kena prayukto’yam paapam charati poorushah;\nAnicchann api vaarshneya balaad iva niyojitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे कृष्ण! तो फिर यह मनुष्य स्वयं न चाहता हुआ भी बलात्‌ लगाए हुए की भाँति किससे प्रेरित होकर पाप का आचरण करता है॥36॥॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે- હે કૃષ્ણ, મનુષ્ય પોતે ઈચ્છતો ન હોવા છતાં પાપકર્મ કરવા માટે કેમ પ્રવૃત્ત થાય છે ? (૩૬)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O descendant of Vrishni, by what is one impelled to sinful acts, even unwillingly, as if engaged by force?");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok37() {
        setTitle("Shlok 37");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nकाम एष क्रोध एष रजोगुणसमुद्भवः ।\nमहाशनो महापाप्मा विद्धयेनमिह वैरिणम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nકામ એષ ક્રોધ એષ રજોગુણસમુદ્ભવઃ,\nમહાશનો મહાપાપ્મા વિદ્ધ્યેનમિહ વૈરિણમ્.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nKaama esha krodha esha rajoguna samudbhavah;\nMahaashano mahaapaapmaa viddhyenam iha vairinam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- रजोगुण से उत्पन्न हुआ यह काम ही क्रोध है। यह बहुत खाने वाला अर्थात भोगों से कभी न अघानेवाला और बड़ा पापी है। इसको ही तू इस विषय में वैरी जान॥37॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે છે- રજોગુણના પ્રભાવથી પેદા થનાર કામ તથા ક્રોધ જ મહાવિનાશી, મહાપાપી તથા મોટામાં મોટા દુશ્મન છે.(૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: It is lust only, Arjuna, which is born of contact with the material mode of passion and later transformed into wrath, and which is the all-devouring sinful enemy of this world.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        setTitle("Shlok 38");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("धूमेनाव्रियते वह्निर्यथादर्शो मलेन च।\nयथोल्बेनावृतो गर्भस्तथा तेनेदमावृतम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ધૂમેનાવ્રિયતે વહ્નિર્યથાદર્શો મલેન ચ,\nયથોલ્બેનાવૃતો ગર્ભસ્તથા તેનેદમાવૃતમ્.(૩૮)");
        } else if (2 == i) {
            this.tv1.setText("Dhoomenaavriyate vahnir yathaadarsho malena cha;\nYatholbenaavrito garbhas tathaa tenedam aavritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस प्रकार धुएँ से अग्नि और मैल से दर्पण ढँका जाता है तथा जिस प्रकार जेर से गर्भ ढँका रहता है, वैसे ही उस काम द्वारा यह ज्ञान ढँका रहता है॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેમ ધુમાડો આગને, મેલ દર્પણને, ઓર ગર્ભને ઢાંકી દે છે તેવી જ રીતે કામ તથા ક્રોધ વ્યક્તિના જ્ઞાન પર પડદો નાંખી દે છે. (૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("As fire is covered by smoke, as a mirror is covered by dust, or as the embryo is covered by the womb, the living entity is similarly covered by different degrees of this lust.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        setTitle("Shlok 39");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आवृतं ज्ञानमेतेन ज्ञानिनो नित्यवैरिणा ।\nकामरूपेण कौन्तेय दुष्पूरेणानलेन च ॥");
        } else if (i == 0) {
            this.tv1.setText("આવૃતં જ્ઞાનમેતેન જ્ઞાનિનો નિત્યવૈરિણા,\nકામરૂપેણ કૌન્તેય દુષ્પૂરેણાનલેન ચ.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Aavritam jnaanam etena jnaanino nityavairinaa;\nKaamaroopena kaunteya dushpoorenaanalena cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और हे अर्जुन! इस अग्नि के समान कभी न पूर्ण होने वाले काम रूप ज्ञानियों के नित्य वैरी द्वारा मनुष्य का ज्ञान ढँका हुआ है॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે કૌન્તેય, અગ્નિ ના સમાન જેની કદી તૃપ્તિ થતી જ નથી એવા કામ અને ક્રોધના આવેગો જ્ઞાનીના જ્ઞાન ને ઢાંકી દે છે,જ્ઞાનીઓ ના તે સૌથી મોટા દુશ્મન છે. (૩૯)");
        } else if (2 == i2) {
            this.tv2.setText("Thus the wise living entity’s pure consciousness becomes covered by his eternal enemy in the form of lust, which is never satisfied and which burns like fire.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        setTitle("Shlok 40");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इन्द्रियाणि मनो बुद्धिरस्याधिष्ठानमुच्यते ।\nएतैर्विमोहयत्येष ज्ञानमावृत्य देहिनम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇન્દ્રિયાણિ મનો બુદ્ધિરસ્યાધિષ્ઠાનમુચ્યતે,\nએતૈર્વિમોહયત્યેષ જ્ઞાનમાવૃત્ય દેહિનમ્.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Indriyaani mano buddhir asyaadhishthaanam uchyate;\nEtair vimohayatyesha jnaanam aavritya dehinam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इन्द्रियाँ, मन और बुद्धि- ये सब इसके वासस्थान कहे जाते हैं। यह काम इन मन, बुद्धि और इन्द्रियों द्वारा ही ज्ञान को आच्छादित करके जीवात्मा को मोहित करता है। ॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("મન, બુદ્ધિ અને ઈન્દ્રિય, એ કામ નું  નિવાસ સ્થાન છે,આ કામ મન,બુદ્ધિ અને ઇન્દ્રિયો ને પોતાના વશ કરી ને, જ્ઞાન અને વિવેક ને ઢાંકીને મનુષ્ય ને ભટકાવી મુકે છે..(૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("The senses, the mind and the intelligence are the sitting places of this lust. Through them lust covers the real knowledge of the living entity and bewilders him.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        setTitle("Shlok 41");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मात्त्वमिन्द्रियाण्यादौ नियम्य भरतर्षभ ।\nपाप्मानं प्रजहि ह्येनं ज्ञानविज्ञाननाशनम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માત્ત્વમિન્દ્રિયાણ્યાદૌ નિયમ્ય ભરતર્ષભ,\nપાપ્માનં પ્રજહિ હ્યેનં જ્ઞાનવિજ્ઞાનનાશનમ્.(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaat twam indriyaanyaadau niyamya bharatarshabha;\nPaapmaanam prajahi hyenam jnaana vijnaana naashanam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए हे अर्जुन! तू पहले इन्द्रियों को वश में करके इस ज्ञान और विज्ञान का नाश करने वाले महान पापी काम को अवश्य ही बलपूर्वक मार डाल॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે અર્જુન, સૌથી પ્રથમ તું ઈન્દ્રિયોને વશમાં કર અને આ પાપમયી, જ્ઞાન અને વિવેક ને હણનાર કામનામાંથી નિવૃત્તિ મેળવ. (૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, O Arjuna, best of the Bharatas, in the very beginning curb this great symbol of sin [lust] by regulating the senses, and slay this destroyer of knowledge and self-realization.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42() {
        setTitle("Shlok 42");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इन्द्रियाणि पराण्याहुरिन्द्रियेभ्यः परं मनः ।\nमनसस्तु परा बुद्धिर्यो बुद्धेः परतस्तु सः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇન્દ્રિયાણિ પરાણ્યાહુરિન્દ્રિયેભ્યઃ પરં મનઃ,\nમનસસ્તુ પરા બુદ્ધિર્યો બુદ્ધેઃ પરતસ્તુ સઃ.(૪૨)");
        } else if (2 == i) {
            this.tv1.setText("Indriyaani paraanyaahur indriyebhyah param manah;\nManasastu paraa buddhir yo buddheh paratastu sah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इन्द्रियों को स्थूल शरीर से पर यानी श्रेष्ठ, बलवान और सूक्ष्म कहते हैं। इन इन्द्रियों से पर मन है, मन से भी पर बुद्धि है और जो बुद्धि से भी अत्यन्त पर है वह आत्मा है॥42॥");
        } else if (i2 == 0) {
            this.tv2.setText("મનુષ્ય દેહમાં ઈન્દ્રિયોને બળવાન કહેવામાં આવી છે. પરંતુ મન ઈન્દ્રિયોથી બળવાન છે. બુદ્ધિ મનથી બળવાન છે અને આત્મા બુદ્ધિ કરતાં પણ શ્રેષ્ઠ છે. (૪૨)");
        } else if (2 == i2) {
            this.tv2.setText("The working senses are superior to dull matter; mind is higher than the senses; intelligence is still higher than the mind; and he [the soul] is even higher than the intelligence.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok43() {
        setTitle("Shlok 43");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एवं बुद्धेः परं बुद्धवा संस्तभ्यात्मानमात्मना ।\nजहि शत्रुं महाबाहो कामरूपं दुरासदम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("એવં બુદ્ધેઃ પરં બુદ્ધ્વા સંસ્તભ્યાત્માનમાત્મના,\nજહિ શત્રું મહાબાહો કામરૂપં દુરાસદમ્.(૪૩)");
        } else if (2 == i) {
            this.tv1.setText("Evam buddheh param buddhwaa samstabhyaatmaanam aatmanaa;\nJahi shatrum mahaabaaho kaamaroopam duraasadam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस प्रकार बुद्धि से पर अर्थात सूक्ष्म, बलवान और अत्यन्त श्रेष्ठ आत्मा को जानकर और बुद्धि द्वारा मन को वश में करके हे महाबाहो! तू इस कामरूप दुर्जय शत्रु को मार डाल॥43॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી આત્મતત્વને સૌથી બળવાન માની,બુદ્ધિ વડે મન ને વશ કરી, આ કામરૂપી દુર્જય શત્રુનો તું તરત નાશ કરી નાખ.(૪૩)");
        } else if (2 == i2) {
            this.tv2.setText("Thus knowing oneself to be transcendental to the material senses, mind and intelligence, O mighty-armed Arjuna, one should steady the mind by deliberate spiritual intelligence [Krishna consciousness] and thus—by spiritual strength—conquer this insatiable enemy known as lust.(43)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay3.this, ShlokListPlay3.this);
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

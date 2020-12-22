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

public class ShlokListPlay7 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay7.this.favflag == 0) {
                    ShlokListPlay7.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.favflag = 1;
                    shlokListPlay7.save();
                } else if (1 == ShlokListPlay7.this.favflag) {
                    ShlokListPlay7.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay7 shlokListPlay72 = ShlokListPlay7.this;
                    shlokListPlay72.favflag = 0;
                    shlokListPlay72.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay7.this.calll++;
                ShlokListPlay7.this.slkset();
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
                ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                shlokListPlay7.calll--;
                ShlokListPlay7.this.slkset();
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
        edit.putString("A7" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A7" + this.calll, "0");
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
        if (30 == this.calll) {
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
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.callf1 = 0;
                    shlokListPlay7.slkset();
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
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.callf1 = 2;
                    shlokListPlay7.slkset();
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
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.callf1 = 1;
                    shlokListPlay7.slkset();
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
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.callf2 = 0;
                    shlokListPlay7.slkset();
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
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.callf2 = 2;
                    shlokListPlay7.slkset();
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
                    ShlokListPlay7 shlokListPlay7 = ShlokListPlay7.this;
                    shlokListPlay7.callf2 = 1;
                    shlokListPlay7.slkset();
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
            shlok4_5();
        } else if (5 == i3) {
            shlok6();
        } else if (6 == i3) {
            shlok7();
        } else if (7 == i3) {
            shlok8();
        } else if (8 == i3) {
            shlok9();
        } else if (9 == i3) {
            shlok10();
        } else if (10 == i3) {
            shlok11();
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
            shlok26();
        } else if (26 == i3) {
            shlok27();
        } else if (27 == i3) {
            shlok28();
        } else if (28 == i3) {
            shlok29();
        } else if (29 == i3) {
            shlok30();
        } else if (30 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 7");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ०७ \nज्ञानविज्ञानयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૭ \nજ્ઞાન વિજ્ઞાન યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 07\ngnan vignan yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में  कहा है की, भगवान कृष्ण समस्त कारणों के कारण, परम सत्य है। महात्मागण भक्तिपूर्वक उनकी शरण ग्रहण करते हैं, किन्तु अपवित्र जान पूजा के अन्य विषयो की ओर अपने मन को मोड़ देते है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૭ માં જે જાણીને બીજું કંઇ જ જાણવાનું બાકી ના રહે તે જ્ઞાન, વિજ્ઞાન સહિત કહેલું છે. પરા અને અપરા પ્રકૃતિ નું વર્ણન છે. દોરીમાં જેમ મણકા પરોવાયેલ છે તેમ સર્વ જગત પરમાત્મા માં ગુંથાયેલું છે. ત્રિગુણાત્મક માયા ને પાર કરવા ઈશ્વરનું શરણ તે એક માત્ર ઉપાય છે. ચાર પ્રકારના જુદાજુદા ભક્તો નું વર્ણન છે. યોગમાયા થી આવૃત થયેલા પરમાત્મા સર્વ ને દેખાતા નથી, અને અવ્યક્ત હોવા છતાં અજ્ઞાની ઓ પરમાત્મા ને દેહ ધારી માને છે.");
        } else if (2 == i) {
            this.tv1.setText("Seventh adhyay tells, Lord Krishna is the Supreme Truth, the supreme cause and sustaining force of everything, both material and spiritual. Advanced souls surrender unto Him in devotion, whereas impious souls divert their minds to other objects of worship.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nमय्यासक्तमनाः पार्थ योगं युञ्जन्मदाश्रयः ।\nअसंशयं समग्रं मां यथा ज्ञास्यसि तच्छृणु ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nમય્યાસક્તમનાઃ પાર્થ યોગં યુઞ્જન્મદાશ્રયઃ,\nઅસંશયં સમગ્રં માં યથા જ્ઞાસ્યસિ તચ્છૃણુ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nMayyaasaktamanaah paartha yogam yunjanmadaashrayah;\nAsamshayam samagram maam yathaa jnaasyasi tacchrinu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे पार्थ! अनन्य प्रेम से मुझमें आसक्त चित तथा अनन्य भाव से मेरे परायण होकर योग में लगा हुआ तू जिस प्रकार से सम्पूर्ण विभूति, बल, ऐश्वर्यादि गुणों से युक्त, सबके आत्मरूप मुझको संशयरहित जानेगा, उसको सुन॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("ભગવાન કહે હે પાર્થ ! મારામાં ચિત્ત પરોવીને,કેવળ મારો જ આશ્રય કરી યોગાભ્યાસ દ્વારા મારા પૂર્ણ સ્વરૂપને તું જાણી લેશે,એમાં જરાય શંકા નથી,તો તે વિશે સાંભળ(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: Now hear, O son of Pritha, how by practicing yoga in full consciousness of Me, with mind attached to Me, you can know Me in full, free from doubt.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञानं तेऽहं सविज्ञानमिदं वक्ष्याम्यशेषतः ।\nयज्ज्ञात्वा नेह भूयोऽन्यज्ज्ञातव्यमवशिष्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞાનં તેહં સવિજ્ઞાનમિદં વક્ષ્યામ્યશેષતઃ,\nયજ્જ્ઞાત્વા નેહ ભૂયોન્યજ્જ્ઞાતવ્યમવશિષ્યતે.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Jnaanam te’ham savijnaanam idam vakshyaamyasheshatah;\nYajjnaatwaa neha bhooyo’nyaj jnaatavyamavashishyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं तेरे लिए इस विज्ञान सहित तत्व ज्ञान को सम्पूर्णतया कहूँगा, जिसको जानकर संसार में फिर और कुछ भी जानने योग्य शेष नहीं रह जाता॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું તને વિજ્ઞાનસહીત તે જ્ઞાન કહીશ.તે જાણ્યા પછી આ લોકમાં બીજું કંઈ જાણવાનું બાકી રહેતું નથી.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("I shall now declare unto you in full this knowledge, both phenomenal and numinous. This being known, nothing further shall remain for you to know.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मनुष्याणां सहस्रेषु कश्चिद्यतति सिद्धये ।\nयततामपि सिद्धानां कश्चिन्मां वेत्ति तत्वतः ॥");
        } else if (i == 0) {
            this.tv1.setText("મનુષ્યાણાં સહસ્રેષુ કશ્ચિદ્યતતિ સિધ્દયે,\nયતતામપિ સિધ્દાનાં કશ્ચિન્માં વેત્તિ તત્ત્વતઃ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Manushyaanaam sahasreshu kashchidyatati siddhaye;\nYatataamapi siddhaanaam kashchinmaam vetti tattwatah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हजारों मनुष्यों में कोई एक मेरी प्राप्ति के लिए यत्न करता है और उन यत्न करने वाले योगियों में भी कोई एक मेरे परायण होकर मुझको तत्व से अर्थात यथार्थ रूप से जानता है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હજારો મનુષ્યોમાંથી કોઈક જ મનેપામવાનો યત્ન કરે છે.મારા માટે યત્ન કરવાવાળા  સિદ્ધોમાંથી માંડ એકાદ મને સત્ય સ્વરૂપમાં ઓળખી શકેછે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("Out of many thousands among men, one may endeavor for perfection, and of those who have achieved perfection, hardly one knows Me in truth.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4_5() {
        setTitle("Shlok 4,5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भूमिरापोऽनलो वायुः खं मनो बुद्धिरेव च ।\nअहङ्‍कार इतीयं मे भिन्ना प्रकृतिरष्टधा ॥\nअपरेयमितस्त्वन्यां प्रकृतिं विद्धि मे पराम्‌ ।\nजीवभूतां महाबाहो ययेदं धार्यते जगत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ભૂમિરાપોનલો વાયુઃ ખં મનો બુદ્ધિરેવ ચ,\nઅહઙ્કાર ઇતીયં મે ભિન્ના પ્રકૃતિરષ્ટધા.(૪)\nઅપરેયમિતસ્ત્વન્યાં પ્રકૃતિં વિદ્ધિ મે પરામ્,\nજીવભૂતાં મહાબાહો યયેદં ધાર્યતે જગત્.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Bhoomiraapo’nalo vaayuh kham mano buddhireva cha;\nAhamkaara iteeyam me bhinnaa prakritirashtadhaa.\nApareyamitastwanyaam prakritim viddhi me paraam;\nJeevabhootaam mahaabaaho yayedam dhaaryate jagat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पृथ्वी, जल, अग्नि, वायु, आकाश, मन, बुद्धि और अहंकार भी- इस प्रकार ये आठ प्रकार से विभाजित मेरी प्रकृति है। यह आठ प्रकार के भेदों वाली तो अपरा अर्थात मेरी जड़ प्रकृति है और हे महाबाहो! इससे दूसरी को, जिससे यह सम्पूर्ण जगत धारण किया जाता है, मेरी जीवरूपा परा अर्थात चेतन प्रकृति जान॥4-5॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારી પ્રકૃતિ ભૂમિ,જળ,વાયુ,તેજ,આકાશ,મન,બુદ્ધિ અને અહંકાર એમ આઠ ભાગ માં વિભાજીત થયેલી છે.    હે મહાબાહો ! એતો મારી અપરા એટલે કે ગૌણ પ્રકૃતિ છે.એનાથી અલગ જે મારી જીવ ભૂત પ્રકૃતિ છે તે પરા પ્રકૃતિ છે.તેનાથી જ આ જગત ધારણ કરવામાં આવ્યું છે.(૪-૫)");
        } else if (2 == i2) {
            this.tv2.setText("Earth, water, fire, air, ether, mind, intelligence and false ego—all together these eight constitute My separated material energies.Besides these, O mighty-armed Arjuna, there is another, superior energy of Mine, which comprises the living entities who are exploiting the resources of this material, inferior nature.(4-5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतद्योनीनि भूतानि सर्वाणीत्युपधारय ।\nअहं कृत्स्नस्य जगतः प्रभवः प्रलयस्तथा ॥");
        } else if (i == 0) {
            this.tv1.setText("એતદ્યોનીનિ ભૂતાનિ સર્વાણીત્યુપધારય,\nઅહં કૃત્સ્નસ્ય જગતઃ પ્રભવઃ પ્રલયસ્તથા.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Etadyoneeni bhootaani sarvaaneetyupadhaaraya;\nAham kritsnasya jagatah prabhavah pralayastathaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! तू ऐसा समझ कि सम्पूर्ण भूत इन दोनों प्रकृतियों से ही उत्पन्न होने वाले हैं और मैं सम्पूर्ण जगत का प्रभव तथा प्रलय हूँ अर्थात्‌ सम्पूर्ण जगत का मूल कारण हूँ॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ બંને પ્રકૃતિઓથી જ સર્વ ભૂતોની ઉત્પતિ થયેલી છે. એ પ્રકૃતિ દ્વારા હું સમગ્ર વિશ્વ ની ઉત્પતિ અને લય કરું છું.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("All created beings have their source in these two natures. Of all that is material and all that is spiritual in this world, know for certain that I am both the origin and the dissolution.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मत्तः परतरं नान्यत्किञ्चिदस्ति धनञ्जय ।\nमयि सर्वमिदं प्रोतं सूत्रे मणिगणा इव ॥");
        } else if (i == 0) {
            this.tv1.setText("મત્તઃ પરતરં નાન્યત્કિઞ્ચિદસ્તિ ધનઞ્જય,\nમયિ સર્વમિદં પ્રોતં સૂત્રે મણિગણા ઇવ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Mattah parataram naanyat kinchidasti dhananjaya;\nMayi sarvamidam protam sootre maniganaa iva.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे धनंजय! मुझसे भिन्न दूसरा कोई भी परम कारण नहीं है। यह सम्पूर्ण जगत सूत्र में सूत्र के मणियों के सदृश मुझमें गुँथा हुआ है॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ધનંજય ! મારાથી પર અને શ્રેષ્ઠ બીજું કંઈ જ નથી.દોરા માં જેમ મણકા પરોવાયેલા હોય છે,તેમ આ સર્વ જગત મારા માં ઓતપોત થતું પરોવાયેલું છે.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("O conqueror of wealth, there is no truth superior to Me. Everything rests upon Me, as pearls are strung on a thread.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रसोऽहमप्सु कौन्तेय प्रभास्मि शशिसूर्ययोः ।\nप्रणवः सर्ववेदेषु शब्दः खे पौरुषं नृषु ॥");
        } else if (i == 0) {
            this.tv1.setText("રસોહમપ્સુ કૌન્તેય પ્રભાસ્મિ શશિસૂર્યયોઃ,\nપ્રણવઃ સર્વવેદેષુ શબ્દઃ ખે પૌરુષં નૃષુ.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Raso’hamapsu kaunteya prabhaasmi shashisooryayoh;\nPranavah sarvavedeshu shabdah khe paurusham nrishu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! मैं जल में रस हूँ, चन्द्रमा और सूर्य में प्रकाश हूँ, सम्पूर्ण वेदों में ओंकार हूँ, आकाश में शब्द और पुरुषों में पुरुषत्व हूँ॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાંન્ત્તેય ! જળમાં રસ હું છું, સુર્ય-ચંદ્ર માં તેજ હું છું,સર્વ વેદો માં ઓમકાર પ્રણવ હું છું. આકાશમાં શબ્દ અને પુરુષ નું પરાક્રમ હું છું.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Kunti, I am the taste of water, the light of the sun and the moon, the syllable om in the Vedic mantras; I am the sound in ether and ability in man.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पुण्यो गन्धः पृथिव्यां च तेजश्चास्मि विभावसौ ।\nजीवनं सर्वभूतेषु तपश्चास्मि तपस्विषु ॥");
        } else if (i == 0) {
            this.tv1.setText("પુણ્યો ગન્ધઃ પૃથિવ્યાં ચ તેજશ્ચાસ્મિ વિભાવસૌ,\nજીવનં સર્વભૂતેષુ તપશ્ચાસ્મિ તપસ્વિષુ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Punyo gandhah prithivyaam cha tejashchaasmi vibhaavasau;\nJeevanam sarvabhooteshu tapashchaasmi tapaswishu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं पृथ्वी में पवित्र (शब्द, स्पर्श, रूप, रस, गंध से इस प्रसंग में इनके कारण रूप तन्मात्राओं का ग्रहण है, इस बात को स्पष्ट करने के लिए उनके साथ पवित्र शब्द जोड़ा गया है।) गंध और अग्नि में तेज हूँ तथा सम्पूर्ण भूतों में उनका जीवन हूँ और तपस्वियों में तप हूँ॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે જ રીતે પૃથ્વીમાં ઉત્તમ ગંધ હું છું,અગ્નિમાં તેજ હું છું,સર્વ ભૂતોમાં જીવન હું છું અને તપસ્વીઓનું  તપ પણ હું જ છું.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("I am the original fragrance of the earth, and I am the heat in fire. I am the life of all that lives, and I am the penances of all ascetics.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बीजं मां सर्वभूतानां विद्धि पार्थ सनातनम्‌ ।\nबुद्धिर्बुद्धिमतामस्मि तेजस्तेजस्विनामहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("બીજં માં સર્વભૂતાનાં વિદ્ધિ પાર્થ સનાતનમ્,\nબુદ્ધિર્બુદ્ધિમતામસ્મિ તેજસ્તેજસ્વિનામહમ્.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Beejam maam sarvabhootaanaam viddhi paartha sanaatanam;\nBuddhir buddhimataamasmi tejastejaswinaamaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! तू सम्पूर्ण भूतों का सनातन बीज मुझको ही जान। मैं बुद्धिमानों की बुद्धि और तेजस्वियों का तेज हूँ॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! સર્વ ભૂતોનું સનાતન બીજ હું છું,બુદ્ધિશાળીઓની બુદ્ધિ અને તેજસ્વીઓનું તેજ હું છું.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, know that I am the original seed of all existences, the intelligence of the intelligent, and the prowess of all powerful men.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बलं बलवतां चाहं कामरागविवर्जितम्‌ ।\nधर्माविरुद्धो भूतेषु कामोऽस्मि भरतर्षभ ॥");
        } else if (i == 0) {
            this.tv1.setText("બલં બલવતામસ્મિ કામરાગવિવર્જિતમ્,\nધર્માવિરુદ્ધો ભૂતેષુ કામોસ્મિ ભરતર્ષભ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Balam balavataam asmi kaamaraagavivarjitam;\nDharmaaviruddho bhooteshu kaamo’smi bharatarshabha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भरतश्रेष्ठ! मैं बलवानों का आसक्ति और कामनाओं से रहित बल अर्थात सामर्थ्य हूँ और सब भूतों में धर्म के अनुकूल अर्थात शास्त्र के अनुकूल काम हूँ॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("બળવાનો માં વાસના અને દ્વેષ વિનાનું બળ હું છું,હે ભરતશ્રેષ્ઠ ! ધર્મ વિરુદ્ધ જાય નહિ તેવો સર્વ પ્રાણીઓમાં “કામ પણ હું છું.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("I am the strength of the strong, devoid of passion and desire. I am sex life which is not contrary to religious principles, O lord of the Bharatas [Arjuna].(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये चैव सात्त्विका भावा राजसास्तामसाश्चये ।\nमत्त एवेति तान्विद्धि न त्वहं तेषु ते मयि ॥");
        } else if (i == 0) {
            this.tv1.setText("યે ચૈવ સાત્ત્વિકા ભાવા રાજસાસ્તામસાશ્ચ યે,\nમત્ત એવેતિ તાન્વિદ્ધિ નત્વહં તેષુ તે મયિ.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Ye chaiva saattvikaa bhaavaa raajasaastaamasaashcha ye;\nMatta eveti taanviddhi na twaham teshu te mayi");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और भी जो सत्त्व गुण से उत्पन्न होने वाले भाव हैं और जो रजो गुण से होने वाले भाव हैं, उन सबको तू 'मुझसे ही होने वाले हैं' ऐसा जान, परन्तु वास्तव में (गीता अ. 9 श्लोक 4-5 में देखना चाहिए) उनमें मैं और वे मुझमें नहीं हैं॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સાત્વિક,રાજસ અને તામસવિકારો છે તે પણ મારાથી ઉત્પન થયેલા છે,પરંતુ હું તેમાં સમાયેલો  નથી , તેઓ મારામાં સમાયેલા છે.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("Know that all states of being—be they of goodness, passion or ignorance—are manifested by My energy. I am, in one sense, everything, but I am independent. I am not under the modes of material nature, for they, on the contrary, are within Me.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्रिभिर्गुणमयैर्भावैरेभिः सर्वमिदं जगत्‌ ।\nमोहितं नाभिजानाति मामेभ्यः परमव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ત્રિભિર્ગુણમયૈર્ભાવૈરેભિઃ સર્વમિદં જગત્,\nમોહિતં નાભિજાનાતિ મામેભ્યઃ પરમવ્યયમ્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Tribhirgunamayair bhaavairebhih sarvamidam jagat;\nMohitam naabhijaanaati maamebhyah paramavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("गुणों के कार्य रूप सात्त्विक, राजस और तामस- इन तीनों प्रकार के भावों से यह सारा संसार- प्राणिसमुदाय मोहित हो रहा है, इसीलिए इन तीनों गुणों से परे मुझ अविनाशी को नहीं जानता॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ ત્રિગુણાત્મક વિકારોથી સમસ્ત જગત મોહિત થઇ ગયું છે,તેથી ગુણાતીત અને અવિનાશી એવા મને એ જગત જાણતું નથી.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("Deluded by the three modes [goodness, passion and ignorance], the whole world does not know Me, who am above the modes and inexhaustible.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दैवी ह्येषा गुणमयी मम माया दुरत्यया ।\nमामेव ये प्रपद्यन्ते मायामेतां तरन्ति ते ॥");
        } else if (i == 0) {
            this.tv1.setText("દૈવી હ્યેષા ગુણમયી મમ માયા દુરત્યયા,\nમામેવ યે પ્રપદ્યન્તે માયામેતાં તરન્તિ તે.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Daivee hyeshaa gunamayee mama maayaa duratyayaa;\nMaameva ye prapadyante maayaametaam taranti te.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि यह अलौकिक अर्थात अति अद्भुत त्रिगुणमयी मेरी माया बड़ी दुस्तर है, परन्तु जो पुरुष केवल मुझको ही निरंतर भजते हैं, वे इस माया को उल्लंघन कर जाते हैं अर्थात्‌ संसार से तर जाते हैं॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેમકે અતિ દિવ્ય અને ત્રિગુણાત્મક એવી મારી માયા દુસ્તર છે.જે મનુષ્ય મારા શરણે આવે છે તે જ એ માયા રૂપી નદીને તરી જાય છે.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("This divine energy of Mine, consisting of the three modes of material nature, is difficult to overcome. But those who have surrendered unto Me can easily cross beyond it.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न मां दुष्कृतिनो मूढाः प्रपद्यन्ते नराधमाः ।\nमाययापहृतज्ञाना आसुरं भावमाश्रिताः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન માં દુષ્કૃતિનો મૂઢાઃ પ્રપદ્યન્તે નરાધમાઃ,\nમાયયાપહૃતજ્ઞાના આસુરં ભાવમાશ્રિતાઃ.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Na maam dushkritino moodhaah prapadyante naraadhamaah;\nMaayayaapahritajnaanaa aasuram bhaavamaashritaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("माया द्वारा जिनका ज्ञान हरा जा चुका है, ऐसे आसुर-स्वभाव को धारण किए हुए, मनुष्यों में नीच, दूषित कर्म करने वाले मूढ़ लोग मुझको नहीं भजते॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ દુસ્તર માયાથી જેમનું જ્ઞાન નષ્ટ થયું છે તથા જેમણે આસુરી પ્રકૃતિનો આશ્રય કર્યો છે તેવા પાપી,મૂઢ અને નરાધમ મનુષ્યો મારે શરણે આવતા નથી.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Those miscreants who are grossly foolish, who are lowest among mankind, whose knowledge is stolen by illusion, and who partake of the atheistic nature of demons do not surrender unto Me.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("चतुर्विधा भजन्ते मां जनाः सुकृतिनोऽर्जुन ।\nआर्तो जिज्ञासुरर्थार्थी ज्ञानी च भरतर्षभ ॥");
        } else if (i == 0) {
            this.tv1.setText("ચતુર્વિધા ભજન્તે માં જનાઃ સુકૃતિનોર્જુન,\nઆર્તો જિજ્ઞાસુરર્થાર્થી જ્ઞાની ચ ભરતર્ષભ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Chaturvidhaa bhajante maam janaah sukritino’rjuna;\nAarto jijnaasurartharthee jnaanee cha bharatarshabha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भरतवंशियों में श्रेष्ठ अर्जुन! उत्तम कर्म करने वाले अर्थार्थी (सांसारिक पदार्थों के लिए भजने वाला), आर्त (संकटनिवारण के लिए भजने वाला) जिज्ञासु (मेरे को यथार्थ रूप से जानने की इच्छा से भजने वाला) और ज्ञानी- ऐसे चार प्रकार के भक्तजन मुझको भजते हैं॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભરતશ્રેષ્ઠ ! આર્ત ,જિજ્ઞાસુ,અથાર્થી અને જ્ઞાની, એમ ચાર પ્રકારના મનુષ્યો મને ભજેછે.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("O best among the Bharatas, four kinds of pious men begin to render devotional service unto Me—the distressed, the desirer of wealth, the inquisitive, and he who is searching for knowledge of the Absolute.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तेषां ज्ञानी नित्ययुक्त एकभक्तिर्विशिष्यते ।\nप्रियो हि ज्ञानिनोऽत्यर्थमहं स च मम प्रियः ॥");
        } else if (i == 0) {
            this.tv1.setText("તેષાં જ્ઞાની નિત્યયુક્ત કભક્તિર્વિશિષ્યતે,\nપ્રિયો હિ જ્ઞાનિનોત્યર્થમહં સ ચ મમ પ્રિયઃ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Teshaam jnaanee nityayukta eka bhaktirvishishyate;\nPriyo hi jnaanino’tyarthamaham sa cha mama priyah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उनमें नित्य मुझमें एकीभाव से स्थित अनन्य प्रेमभक्ति वाला ज्ञानी भक्त अति उत्तम है क्योंकि मुझको तत्व से जानने वाले ज्ञानी को मैं अत्यन्त प्रिय हूँ और वह ज्ञानी मुझे अत्यन्त प्रिय है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("તેમાં જ્ઞાની જનો ,નિરંતર મારામાં લીન રહી એકનિષ્ઠા થી મારી ભક્તિ કરે છે,તેથી તેઓ શ્રેષ્ઠ છે. આવા જ્ઞાની જનો ને હું અત્યંત પ્રિય છું અને તેઓ મને અત્યંત પ્રિય છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("Of these, the one who is in full knowledge and who is always engaged in pure devotional service is the best. For I am very dear to him, and he is dear to Me.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उदाराः सर्व एवैते ज्ञानी त्वात्मैव मे मतम्‌ ।\nआस्थितः स हि युक्तात्मा मामेवानुत्तमां गतिम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉદારાઃ સર્વ એવૈતે જ્ઞાની ત્વાત્મૈવ મે મતમ્,\nઆસ્થિતઃ સ હિ યુક્તાત્મા મામેવાનુત્તમાં ગતિમ્.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Udaaraah sarva evaite jnaanee twaatmaiva me matam;\nAasthitah sa hi yuktaatmaa maamevaanuttamaam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("ये सभी उदार हैं, परन्तु ज्ञानी तो साक्षात्‌ मेरा स्वरूप ही है- ऐसा मेरा मत है क्योंकि वह मद्गत मन-बुद्धिवाला ज्ञानी भक्त अति उत्तम गतिस्वरूप मुझमें ही अच्छी प्रकार स्थित है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ સર્વશ્રેષ્ઠ છે,પરંતુ જ્ઞાની તો મારો આત્મા છે.એમ હું માનું છું કારણકે તે મારામાં ચિત પરોવી મને જ સર્વોતમ માની મારો આશ્રય કરે છે.(૧૮) ");
        } else if (2 == i2) {
            this.tv2.setText("All these devotees are undoubtedly magnanimous souls, but he who is situated in knowledge of Me I consider to be just like My own self. Being engaged in My transcendental service, he is sure to attain Me, the highest and most perfect goal.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बहूनां जन्मनामन्ते ज्ञानवान्मां प्रपद्यते ।\nवासुदेवः सर्वमिति स महात्मा सुदुर्लभः ॥");
        } else if (i == 0) {
            this.tv1.setText("બહૂનાં જન્મનામન્તે જ્ઞાનવાન્માં પ્રપદ્યતે,\nવાસુદેવઃ સર્વમિતિ સ મહાત્મા સુદુર્લભઃ.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Bahoonaam janmanaamante jnaanavaanmaam prapadyate;\nVaasudevah sarvamiti sa mahaatmaa sudurlabhah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("बहुत जन्मों के अंत के जन्म में तत्व ज्ञान को प्राप्त पुरुष, सब कुछ वासुदेव ही हैं- इस प्रकार मुझको भजता है, वह महात्मा अत्यन्त दुर्लभ है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("“અનેક જન્મો પછી સર્વ કંઈ વાસુદેવ રૂપ છે ” જેને એવું જ્ઞાન પરિપક્વ થયું છે, એવા જ્ઞાનીને મારી પ્રાપ્તિ થાય છે એવા મહાત્મા અતિ દુર્લભ છે.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("After many births and deaths, he who is actually in knowledge surrenders unto Me, knowing Me to be the cause of all causes and all that is. Such a great soul is very rare.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कामैस्तैस्तैर्हृतज्ञानाः प्रपद्यन्तेऽन्यदेवताः ।\nतं तं नियममास्थाय प्रकृत्या नियताः स्वया ॥");
        } else if (i == 0) {
            this.tv1.setText("કામૈસ્તૈસ્તૈર્હૃતજ્ઞાનાઃ પ્રપદ્યન્તેન્યદેવતાઃ,\nતં તં નિયમમાસ્થાય પ્રકૃત્યા નિયતાઃ સ્વયા.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Kaamaistaistairhritajnaanaah prapadyante’nyadevataah;\nTam tam niyamamaasthaaya prakrityaa niyataah swayaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उन-उन भोगों की कामना द्वारा जिनका ज्ञान हरा जा चुका है, वे लोग अपने स्वभाव से प्रेरित होकर उस-उस नियम को धारण करके अन्य देवताओं को भजते हैं अर्थात पूजते हैं॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે અજ્ઞાનીઓ નું  પોતાના સ્વભાવ ને વશ થવાથી અને વિવિધ કામનાઓથી જ્ઞાન નષ્ટ થયું છે તે મારા-આત્મરૂપ વાસુદેવથી ભિન્ન ઈતર દેવતાઓની ઉપાસના કરે છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Those whose intelligence has been stolen by material desires surrender unto demigods and follow the particular rules and regulations of worship according to their own natures.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यो यो यां यां तनुं भक्तः श्रद्धयार्चितुमिच्छति ।\nतस्य तस्याचलां श्रद्धां तामेव विदधाम्यहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યો યો યાં યાં તનું ભક્તઃ શ્રદ્ધયાર્ચિતુમિચ્છતિ,\nતસ્ય તસ્યાચલાં શ્રદ્ધાં તામેવ વિદધામ્યહમ્.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Yo yo yaam yaam tanum bhaktah shraddhayaarchitum icchati;\nTasya tasyaachalaam shraddhaam taameva vidadhaamyaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो-जो सकाम भक्त जिस-जिस देवता के स्वरूप को श्रद्धा से पूजना चाहता है, उस-उस भक्त की श्रद्धा को मैं उसी देवता के प्रति स्थिर करता हूँ॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે ભક્ત , જે દેવતામાં ભક્તિભાવથી તેની આરાધના કરે છે, તેની તે શ્રદ્ધાને તે દેવતામાં હું જ સ્થિર કરું છું.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("I am in everyone’s heart as the Supersoul. As soon as one desires to worship some demigod, I make his faith steady so that he can devote himself to that particular deity.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स तया श्रद्धया युक्तस्तस्याराधनमीहते ।\nलभते च ततः कामान्मयैव विहितान्हि तान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સ તયા શ્રદ્ધયા યુક્તસ્તસ્યારાધનમીહતે,\nલભતે ચ તતઃ કામાન્મયૈવ વિહિતાન્ હિ તાન્.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Sa tayaa shraddhayaa yuktastasyaaraadhanameehate;\nLabhate cha tatah kaamaan mayaiva vihitaan hi taan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह पुरुष उस श्रद्धा से युक्त होकर उस देवता का पूजन करता है और उस देवता से मेरे द्वारा ही विधान किए हुए उन इच्छित भोगों को निःसंदेह प्राप्त करता है॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ તે પ્રકારની શ્રદ્ધા રાખી તે દેવની આરાધના કરે છે અને પછી મેં નિર્માણ કરેલી તેની તે કામનાઓ પૂર્ણ થાય છે.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("Endowed with such a faith, he endeavors to worship a particular demigod and obtains his desires. But in actuality these benefits are bestowed by Me alone.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अन्तवत्तु फलं तेषां तद्भवत्यल्पमेधसाम्‌ ।\nदेवान्देवयजो यान्ति मद्भक्ता यान्ति मामपि ॥");
        } else if (i == 0) {
            this.tv1.setText("અન્તવત્તુ ફલં તેષાં તદ્ભવત્યલ્પમેધસામ્,\nદેવાન્દેવયજો યાન્તિ મદ્ભક્તા યાન્તિ મામપિ.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Antavattu phalam teshaam tadbhavatyalpamedhasaam;\nDevaan devayajo yaanti madbhaktaa yaanti maamapi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु उन अल्प बुद्धिवालों का वह फल नाशवान है तथा वे देवताओं को पूजने वाले देवताओं को प्राप्त होते हैं और मेरे भक्त चाहे जैसे ही भजें, अन्त में वे मुझको ही प्राप्त होते हैं॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("અન્ય દેવતાઓને ભજવાથી અજ્ઞાની મનુષ્યોને પ્રાપ્ત થયેલું તે ફળ નાશવંત હોયછે. દેવતાઓના ભક્ત દેવતાઓને પામે છે અને મારા ભક્તો મને પામે છે.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("Men of small intelligence worship the demigods, and their fruits are limited and temporary.Those who worship the demigods go to the planets of the demigods, but My devotees ultimately reach My supreme planet.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अव्यक्तं व्यक्तिमापन्नं मन्यन्ते मामबुद्धयः ।\nपरं भावमजानन्तो ममाव्ययमनुत्तमम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અવ્યક્તં વ્યક્તિમાપન્નં મન્યન્તે મામબુધ્દયઃ,\nપરં ભાવમજાનન્તો મમાવ્યયમનુત્તમમ્.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Avyaktam vyaktimaapannam manyante maamabuddhayah;\nParam bhaavamajaananto mamaavyayamanuttamam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("बुद्धिहीन पुरुष मेरे अनुत्तम अविनाशी परम भाव को न जानते हुए मन-इन्द्रियों से परे मुझ सच्चिदानन्दघन परमात्मा को मनुष्य की भाँति जन्मकर व्यक्ति भाव को प्राप्त हुआ मानते हैं॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારા ઉત્કૃષ્ટ, અવિનાશી અને અતિ ઉત્તમ ભાવને ન જાણનારા અજ્ઞાની લોકો ,હું અવ્યક્ત હોવા છતાં મને સાકાર માને છે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Unintelligent men, who do not know Me perfectly, think that I, the Supreme Personality of Godhead, Krishna, was impersonal before and have now assumed this personality. Due to their small knowledge, they do not know My higher nature, which is imperishable and supreme.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नाहं प्रकाशः सर्वस्य योगमायासमावृतः ।\nमूढोऽयं नाभिजानाति लोको मामजमव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("નાહં પ્રકાશઃ સર્વસ્ય યોગમાયાસમાવૃતઃ,\nમૂઢોયં નાભિજાનાતિ લોકો મામજમવ્યયમ્.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Naaham prakaashah sarvasya yogamaayaasamaavritah;\nMoodho’yam naabhijaanaati loko maamajamavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अपनी योगमाया से छिपा हुआ मैं सबके प्रत्यक्ष नहीं होता, इसलिए यह अज्ञानी जनसमुदाय मुझ जन्मरहित अविनाशी परमेश्वर को नहीं जानता अर्थात मुझको जन्मने-मरने वाला समझता है॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું યોગમાયાથી આવ્રાયેલો છું,આથી સર્વ ને સ્પષ્ટ પણે દેખાતો નથી.આથી મૂઢ મનુષ્યો અજન્મા અને અવિનાશી એવા મને જાણતા નથી.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("I am never manifest to the foolish and unintelligent. For them I am covered by My internal potency, and therefore they do not know that I am unborn and infallible.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वेदाहं समतीतानि वर्तमानानि चार्जुन ।\nभविष्याणि च भूतानि मां तु वेद न कश्चन ॥");
        } else if (i == 0) {
            this.tv1.setText("વેદાહં સમતીતાનિ વર્તમાનાનિ ચાર્જુન,\nભવિષ્યાણિ ચ ભૂતાનિ માં તુ વેદ ન કશ્ચન.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Vedaaham samateetaani vartamaanaani chaarjuna;\nBhavishyaani cha bhootani maam tu veda na kashchana.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! पूर्व में व्यतीत हुए और वर्तमान में स्थित तथा आगे होने वाले सब भूतों को मैं जानता हूँ, परन्तु मुझको कोई भी श्रद्धा-भक्तिरहित पुरुष नहीं जानता॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! પહેલાં થઇ ગયેલા , અત્યારે થઇ રહેલા અને હવે પછી થનારા સઘળા ભૂતોને (પ્રાણીઓને ) હું જાણું છું,પરંતુ મને કોઈ જાણતું નથી.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("O Arjuna, as the Supreme Personality of Godhead, I know everything that has happened in the past, all that is happening in the present, and all things that are yet to come. I also know all living entities; but Me no one knows.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इच्छाद्वेषसमुत्थेन द्वन्द्वमोहेन भारत ।\nसर्वभूतानि सम्मोहं सर्गे यान्ति परन्तप ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇચ્છાદ્વેષસમુત્થેન દ્વન્દ્વમોહેન ભારત,\nસર્વભૂતાનિ સંમોહં સર્ગે યાન્તિ પરન્તપ.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Icchaadweshasamutthena dwandwamohena bhaarata;\nSarvabhootaani sammoham sarge yaanti parantapa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भरतवंशी अर्जुन! संसार में इच्छा और द्वेष से उत्पन्न सुख-दुःखादि द्वंद्वरूप मोह से सम्पूर्ण प्राणी अत्यन्त अज्ञता को प्राप्त हो रहे हैं॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરંતપ ! ઈચ્છા અને ઈર્ષાથી ઉત્પન થયેલા સુખદુઃખ રૂપી મોહથી સર્વ ભૂતો (પ્રાણીઓ) પ્રમાદી બનીને ઉત્પતિ સમયે ઘણી દ્વિઘા માં પડી જાય છે.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("O scion of Bharata, O conqueror of the foe, all living entities are born into delusion, bewildered by dualities arisen from desire and hate.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("येषां त्वन्तगतं पापं जनानां पुण्यकर्मणाम्‌ ।\nते द्वन्द्वमोहनिर्मुक्ता भजन्ते मां दृढव्रताः ॥");
        } else if (i == 0) {
            this.tv1.setText("યેષાં ત્વન્તગતં પાપં જનાનાં પુણ્યકર્મણામ્,\nતે દ્વન્દ્વમોહનિર્મુક્તા ભજન્તે માં દૃઢવ્રતાઃ(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Yeshaam twantagatam paapam janaanaam punyakarmanaam;\nTe dwandwamohanirmuktaa bhajante maam dridhavrataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु निष्काम भाव से श्रेष्ठ कर्मों का आचरण करने वाले जिन पुरुषों का पाप नष्ट हो गया है, वे राग-द्वेषजनित द्वन्द्व रूप मोह से मुक्त दृढ़निश्चयी भक्त मुझको सब प्रकार से भजते हैं॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("પરંતુ સતકર્મો ના પુણ્ય ભાવે જેનાં પાપો નાશ પામ્યાં છે, તે દઢ નિશ્વયી મનુષ્યો સુખદુઃખની મોહજાળ થી મુક્ત થઇ ને મને ભજે છે.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("Persons who have acted piously in previous lives and in this life and whose sinful actions are completely eradicated are freed from the dualities of delusion, and they engage themselves in My service with determination.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("जरामरणमोक्षाय मामाश्रित्य यतन्ति ये ।\nते ब्रह्म तद्विदुः कृत्स्नमध्यात्मं कर्म चाखिलम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("જરામરણમોક્ષાય મામાશ્રિત્ય યતન્તિ યે,\nતે બ્રહ્મ તદ્વિદુઃ કૃત્સ્નમધ્યાત્મં કર્મ ચાખિલમ્.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Jaraamaranamokshaaya maamaashritya yatanti ye;\nTe brahma tadviduh kritsnam adhyaatmam karma chaakhilam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो मेरे शरण होकर जरा और मरण से छूटने के लिए यत्न करते हैं, वे पुरुष उस ब्रह्म को, सम्पूर्ण अध्यात्म को, सम्पूर्ण कर्म को जानते हैं॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેઓ મારો આશ્રય કરી જરા-મૃત્યુથી મુક્ત થવાનો યત્ન કરે છે, તેઓજ બ્રહ્મને જાણી શકે છે. યત્નથી તેઓ અધ્યાત્મ તથા સર્વ કર્મને પણ જાણે છે.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("Intelligent persons who are endeavoring for liberation from old age and death take refuge in Me in devotional service. They are actually Brahman because they entirely know everything about transcendental activities.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("साधिभूताधिदैवं मां साधियज्ञं च ये विदुः ।\nप्रयाणकालेऽपि च मां ते विदुर्युक्तचेतसः ॥");
        } else if (i == 0) {
            this.tv1.setText("સાધિભૂતાધિદૈવં માં સાધિયજ્ઞં ચ યે વિદુઃ,\nપ્રયાણકાલેપિ ચ માં તે વિદુર્યુક્તચેતસઃ.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Saadhibhootaadhidaivam maam saadhiyajnam cha ye viduh;\nPrayaanakaale’pi cha maam te vidur yuktachetasah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष अधिभूत और अधिदैव सहित तथा अधियज्ञ सहित (सबका आत्मरूप) मुझे अन्तकाल में भी जानते हैं, वे युक्तचित्तवाले पुरुष मुझे जानते हैं अर्थात प्राप्त हो जाते हैं॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે યોગી અધિભૂત, અધિદૈવ અને અધિયજ્ઞ સહીત મને જાણે છે,તે સ્વસ્થચિત્ત પુરુષો મરણ સમયે પણ મને જ જાણે છે.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("Those in full consciousness of Me, who know Me, the Supreme Lord, to be the governing principle of the material manifestation, of the demigods, and of all methods of sacrifice, can understand and know Me, the Supreme Personality of Godhead, even at the time of death.(30)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay7.this, ShlokListPlay7.this);
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

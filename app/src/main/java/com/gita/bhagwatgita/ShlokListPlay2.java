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

public class ShlokListPlay2 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
    Button btn_slock, btn_slocktras,btn_slock_silent, btn_slocktras_silent;
    TextToSpeech tts;



    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.shlok_list_play);


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
                if (ShlokListPlay2.this.favflag == 0) {
                    ShlokListPlay2.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.favflag = 1;
                    shlokListPlay2.save();
                } else if (1 == ShlokListPlay2.this.favflag) {
                    ShlokListPlay2.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay2 shlokListPlay22 = ShlokListPlay2.this;
                    shlokListPlay22.favflag = 0;
                    shlokListPlay22.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay2.this.calll++;
                ShlokListPlay2.this.slkset();
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
                ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                shlokListPlay2.calll--;
                ShlokListPlay2.this.slkset();
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
        edit.putString("A2" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A2" + this.calll, "0");
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
        if (71 == this.calll) {
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
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.callf1 = 0;
                    shlokListPlay2.slkset();
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
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.callf1 = 2;
                    shlokListPlay2.slkset();
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
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.callf1 = 1;
                    shlokListPlay2.slkset();
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
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.callf2 = 0;
                    shlokListPlay2.slkset();
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
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.callf2 = 2;
                    shlokListPlay2.slkset();
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
                    ShlokListPlay2 shlokListPlay2 = ShlokListPlay2.this;
                    shlokListPlay2.callf2 = 1;
                    shlokListPlay2.slkset();
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
            shlok35();
        } else if (36 == i3) {
            shlok36();
        } else if (37 == i3) {
            shlok37();
        } else if (38 == i3) {
            shlok38();
        } else if (39 == i3) {
            shlok39();
        } else if (40 == i3) {
            shlok40();
        } else if (41 == i3) {
            shlok41();
        } else if (42 == i3) {
            shlok42_43_44();
        } else if (43 == i3) {
            shlok45();
        } else if (44 == i3) {
            shlok46();
        } else if (45 == i3) {
            shlok47();
        } else if (46 == i3) {
            shlok48();
        } else if (47 == i3) {
            shlok49();
        } else if (48 == i3) {
            shlok50();
        } else if (49 == i3) {
            shlok51();
        } else if (50 == i3) {
            shlok52();
        } else if (51 == i3) {
            shlok53();
        } else if (52 == i3) {
            shlok54();
        } else if (53 == i3) {
            shlok55();
        } else if (54 == i3) {
            shlok56();
        } else if (55 == i3) {
            shlok57();
        } else if (56 == i3) {
            shlok58();
        } else if (57 == i3) {
            shlok59();
        } else if (58 == i3) {
            shlok60();
        } else if (59 == i3) {
            shlok61();
        } else if (60 == i3) {
            shlok62();
        } else if (61 == i3) {
            shlok63();
        } else if (62 == i3) {
            shlok64();
        } else if (63 == i3) {
            shlok65();
        } else if (64 == i3) {
            shlok66();
        } else if (65 == i3) {
            shlok67();
        } else if (66 == i3) {
            shlok68();
        } else if (67 == i3) {
            shlok69();
        } else if (68 == i3) {
            shlok70();
        } else if (69 == i3) {
            shlok71();
        } else if (70 == i3) {
            shlok72();
        } else if (71 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 2");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय ०२ \nसांख्ययोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૨ \nસાંખ્ય યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 02\nsankhya yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दूसरे अध्याय में अर्जुन शिष्य-रूप में कृष्ण की शरण ग्रहण करता है और कृष्ण उससे नश्वर भौतिक शरीर तथा नित्य आत्मा के भूलभूत अन्तर की व्याख्या करते हुए अपना उपदेश प्रारम्भ करते हैं। भगवान उसे देहान्तरण की प्रक्रिया, परमेश्वर की निष्काम सेवा तथा स्वरूपप्रसिद्ध व्यक्ति की गुणों से अवगत कराते हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૨ માં ગીતાનું બીજ રોપાય છે.શરીર અને આત્મા નું \"જ્ઞાન\"છે. \"સ્વ-ધર્મ\" અને ક્ષત્રિય તરીકે ની ફરજ નું વર્ણન છે. \"કર્મ\" નું જ્ઞાન બતાવેલ છે. સમતા રાખી, કામનાનો ત્યાગ કરી ફળ ની આશા કે ફળ પર અધિકાર નહી રાખવાનું શીખવે છે. સ્થિતપ્રજ્ઞતા ના લક્ષણો બતાવેલા છે. ટૂંક માં અહીં જ્ઞાન યોગ અને કર્મ યોગ બંને નું વર્ણન કર્યું છે.");
        } else if (2 == i) {
            this.tv1.setText("In Second adhyay, Arjuna submits to Lord Krishna as His disciple, and Krishna begins His teachings to Arjuna by explaining the fundamental distinction between the temporary material body and the eternal spiritual soul. The Lord explains the process of transmigration, the nature of selfless service to the Supreme and the characteristics of a self-realized person.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        load();
        setTitle("Shlok 1");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nतं तथा कृपयाविष्टमश्रुपूर्णाकुलेक्षणम्‌ ।\nविषीदन्तमिदं वाक्यमुवाच मधुसूदनः ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજય ઉવાચ- \nતં તથા કૃપયાવિષ્ટમશ્રુપૂર્ણાકુલેક્ષણમ્,\nવિષીદન્તમિદં વાક્યમુવાચ મધુસૂદનઃ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nTam tathaa kripayaavishtam ashrupoornaakulekshanam;\nVisheedantam idam vaakyam uvaacha madhusoodanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संजय बोले- उस प्रकार करुणा से व्याप्त और आँसुओं से पूर्ण तथा व्याकुल नेत्रों वाले शोकयुक्त उस अर्जुन के प्रति भगवान मधुसूदन ने यह वचन कहा॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે છે- આંખમાં આંસુ અને હૃદયમાં શોક તથા વિષાદ ભરેલ અર્જુનને, મધુસૂદને (કૃષ્ણે) આમ કહ્યું.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said: Seeing Arjuna full of compassion, his mind depressed, his eyes full of tears, Madhusudana, Krishna, spoke the following words.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        load();
        setTitle("Shlok 2");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nकुतस्त्वा कश्मलमिदं विषमे समुपस्थितम्‌ ।\nअनार्यजुष्टमस्वर्ग्यमकीर्तिकरमर्जुन।");
        } else if (i == 0) {
            this.tv1.setText("ભગવાન ઉવાચ -\nકુતસ્ત્વા કશ્મલમિદં વિષમે સમુપસ્થિતમ્,\nઅનાર્યજુષ્ટમસ્વર્ગ્યમકીર્તિકરમર્જુન(૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nKutastwaa kashmalam idam vishame samupasthitam;\nAnaaryajushtam aswargyam akeertikaram arjuna.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्रीभगवान बोले- हे अर्जुन! तुझे इस असमय में यह मोह किस हेतु से प्राप्त हुआ? क्योंकि न तो यह श्रेष्ठ पुरुषों द्वारा आचरित है, न स्वर्ग को देने वाला है और न कीर्ति को करने वाला ही है॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, યુદ્ધ ભૂમિમાં આ સમયે તને આવા વિચારો ક્યાંથી આવી રહ્યા છે. કારણ કે જેને લીધે ન તો સ્વર્ગ મળે છે કે ન તો કીર્તિ પ્રાપ્ત થાય છે, આવા વિચારો તારા જેવા શ્રેષ્ઠ પુરુષો કરતા નથી. (૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: My dear Arjuna, how have these impurities come upon you? They are not at all befitting a man who knows the value of life. They lead not to higher planets but to infamy.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        load();
        setTitle("Shlok 3");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("क्लैब्यं मा स्म गमः पार्थ नैतत्त्वय्युपपद्यते ।\nक्षुद्रं हृदयदौर्बल्यं त्यक्त्वोत्तिष्ठ परन्तप ॥");
        } else if (i == 0) {
            this.tv1.setText("ક્લૈબ્યં મા સ્મ ગમઃ પાર્થ નૈતત્ત્વય્યુપપદ્યતે,\nક્ષુદ્રં હૃદયદૌર્બલ્યં ત્યક્ત્વોત્તિષ્ઠ પરન્તપ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Klaibyam maa sma gamah paartha naitat twayyupapadyate;\nKshudram hridaya daurbalyam tyaktwottishtha parantapa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए हे अर्जुन! नपुंसकता को मत प्राप्त हो, तुझमें यह उचित नहीं जान पड़ती। हे परंतप! हृदय की तुच्छ दुर्बलता को त्यागकर युद्ध के लिए खड़ा हो जा॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ, તું આવા દુર્બળ અને કાયર વિચારોનો ત્યાગ કર અને યુદ્ધ કરવા માટે તૈયાર થા.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, do not yield to this degrading impotence. It does not become you. Give up such petty weakness of heart and arise, O chastiser of the enemy.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        load();
        setTitle("Shlok 4");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nकथं भीष्ममहं सङ्‍ख्ये द्रोणं च मधुसूदन ।\nइषुभिः प्रतियोत्स्यामि पूजार्हावरिसूदन ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ -\nકથં ભીષ્મમહં સંખ્યે દ્રોણં ચ મધુસૂદન,\nઇષુભિઃ પ્રતિયોત્સ્યામિ પૂજાર્હાવરિસૂદન.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nKatham bheeshmamaham sankhye dronam cha madhusoodana;\nIshubhih pratiyotsyaami poojaarhaavarisoodana.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे मधुसूदन! मैं रणभूमि में किस प्रकार बाणों से भीष्म पितामह और द्रोणाचार्य के विरुद्ध लड़ूँगा? क्योंकि हे अरिसूदन! वे दोनों ही पूजनीय हैं॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે, હે મધુસૂદન, હું કેવી રીતે યુદ્ધ ભૂમિમાં ભીષ્મ પિતામહ અને આચાર્ય દ્રોણ સાથે યુદ્ધ કરું ? હે અરિસૂદન, મારે માટે બંને પૂજનીય છે. (૪)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O killer of enemies, O killer of Madhu, how can I counterattack with arrows in battle men like Bhishma and Drona, who are worthy of my worship?(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        load();
        setTitle("Shlok 5");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("गुरूनहत्वा हि महानुभावा-\nञ्छ्रेयो भोक्तुं भैक्ष्यमपीह लोके ।\nहत्वार्थकामांस्तु गुरूनिहैव\nभुंजीय भोगान्‌ रुधिरप्रदिग्धान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ગુરૂનહત્વા હિ મહાનુભાવાન્ \nશ્રેયો ભોક્તું ભૈક્ષ્યમપીહ લોકે,\nહત્વાર્થકામાંસ્તુ ગુરૂનિહૈવ\nભુઞ્જીય ભોગાન્ રુધિરપ્રદિગ્ધાન્.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Guroon ahatwaa hi mahaanubhaavaan\nShreyo bhoktum bhaikshyam apeeha loke;\nHatwaarthakaamaamstu guroon ihaiva\nBhunjeeya bhogaan rudhirapradigdhaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए इन महानुभाव गुरुजनों को न मारकर मैं इस लोक में भिक्षा का अन्न भी खाना कल्याणकारक समझता हूँ क्योंकि गुरुजनों को मारकर भी इस लोक में रुधिर से सने हुए अर्थ और कामरूप भोगों को ही तो भोगूँगा॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("ગુરુ અને પૂજ્યજનોના લોહીથી ખરડાયેલા હાથે મળેલ રાજ્યનો ઉપભોગ કરવા કરતાં ભિક્ષા માંગી જીવન વીતાવવું મને બહેતર લાગે છે. વળી એમને મારીને મને શું મળશે - ધન અને ભોગ-વૈભવ જ ને ? (૫)");
        } else if (2 == i2) {
            this.tv2.setText("It would be better to live in this world by begging than to live at the cost of the lives of great souls who are my teachers. Even though desiring worldly gain, they are superiors. If they are killed, everything we enjoy will be tainted with blood.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        load();
        setTitle("Shlok 6");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न चैतद्विद्मः कतरन्नो गरीयो-\nयद्वा जयेम यदि वा नो जयेयुः ।\nयानेव हत्वा न जिजीविषाम-\nस्तेऽवस्थिताः प्रमुखे धार्तराष्ट्राः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન ચૈતદ્વિદમઃ કતરન્નો ગરીયો\nયદ્વા જયેમ યદિ વા નો જયેયુઃ,\nયાનેવ હત્વા ન જિજીવિષામ-\nસ્તેવસ્થિતાઃ પ્રમુખે ધાર્તરાષ્ટ્રાઃ.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Na chaitad vidmah kataran no gareeyo\nYadwaa jayema yadi vaa no jayeyuh;\nYaan eva hatwaa na jijeevishaamas\nTe’vasthitaah pramukhe dhaartaraashtraah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हम यह भी नहीं जानते कि हमारे लिए युद्ध करना और न करना- इन दोनों में से कौन-सा श्रेष्ठ है, अथवा यह भी नहीं जानते कि उन्हें हम जीतेंगे या हमको वे जीतेंगे। और जिनको मारकर हम जीना भी नहीं चाहते, वे ही हमारे आत्मीय धृतराष्ट्र के पुत्र हमारे मुकाबले में खड़े हैं॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("મને તો એ પણ ખબર નથી પડતી કે -યુદ્ધ કરવું જોઈએ કે નહીં અને એ પણ ખબર નથી કે એનું કેવું પરિણામ અમારે માટે યોગ્ય રહેશે - અમારી જીત કે કૌરવોની. ?? કારણ કે જેમને મારીને અમને જીવવાની ઈચ્છા જ ન રહે એવા ધૃતરાષ્ટ્રના પુત્રો અમારી સાથે યુદ્ધ કરવા માટે તૈયાર ઊભા છે. (૬)");
        } else if (2 == i2) {
            this.tv2.setText("Nor do we know which is better—conquering them or being conquered by them. If we killed the sons of Dhritarashtra, we should not care to live. Yet they are now standing before us on the battlefield.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        load();
        setTitle("Shlok 7");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कार्पण्यदोषोपहतस्वभावः\nपृच्छामि त्वां धर्मसम्मूढचेताः ।\nयच्छ्रेयः स्यान्निश्चितं ब्रूहि तन्मे\nशिष्यस्तेऽहं शाधि मां त्वां प्रपन्नम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કાર્પણ્યદોષોપહતસ્વભાવઃ\nપૃચ્છામિ ત્વાં ધર્મસંમૂઢચેતાઃ,\nયચ્છ્રેયઃ સ્યાન્નિશ્ચિતં બ્રૂહિ તન્મે\nશિષ્યસ્તેહં શાધિ માં ત્વાં પ્રપન્નમ્.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Kaarpanyadoshopahataswabhaavah\nPricchaami twaam dharmasammoodha chetaah;\nYacchreyah syaan nishchitam broohi tanme\nShishyaste’ham shaadhi maam twaam prapannam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए कायरता रूप दोष से उपहत हुए स्वभाव वाला तथा धर्म के विषय में मोहित चित्त हुआ मैं आपसे पूछता हूँ कि जो साधन निश्चित कल्याणकारक हो, वह मेरे लिए कहिए क्योंकि मैं आपका शिष्य हूँ, इसलिए आपके शरण हुए मुझको शिक्षा दीजिए॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારું મન દ્વિધામાં છે અને આ સ્થિતિમાં મારો શું ધર્મ છે, મારે શું કરવું જોઈએ ? એ મારી સમજમાં નથી આવતું. એથી હે કેશવ, હું આપને પૂછું છું કે -મારે માટે જે સર્વપ્રકારે યોગ્ય અને કલ્યાણકારક હોય એ માર્ગ મને બતાવો. હું આપનો શિષ્ય છું અને આપની શરણમાં આવ્યો છું. (૭)");
        } else if (2 == i2) {
            this.tv2.setText("Now I am confused about my duty and have lost all composure because of miserly weakness. In this condition I am asking You to tell me for certain what is best for me. Now I am Your disciple, and a soul surrendered unto You. Please instruct me.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        load();
        setTitle("Shlok 8");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न हि प्रपश्यामि ममापनुद्या-\nद्यच्छोकमुच्छोषणमिन्द्रियाणाम्‌ ।\nअवाप्य भूमावसपत्रमृद्धं-\nराज्यं सुराणामपि चाधिपत्यम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ન હિ પ્રપશ્યામિ મમાપનુદ્યા-\nદ્યચ્છોકમુચ્છોષણમિન્દ્રિયાણામ્,\nઅવાપ્ય ભૂમાવસપત્નમૃદ્ધમ્ \nરાજ્યં સુરાણામપિ ચાધિપત્યમ્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Na hi prapashyaami mamaapanudyaad\nYacchokam ucchoshanam indriyaanaam;\nAvaapya bhoomaavasapatnam riddham\nRaajyam suraanaam api chaadhipatyam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि भूमि में निष्कण्टक, धन-धान्य सम्पन्न राज्य को और देवताओं के स्वामीपने को प्राप्त होकर भी मैं उस उपाय को नहीं देखता हूँ, जो मेरी इन्द्रियों के सुखाने वाले शोक को दूर कर सके॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("સુખ સમૃદ્ધિથી ભરેલ પૃથ્વી તો શું સ્વર્ગનું સામ્રાજ્ય પણ મને મળી જાય તો પણ મારો શોક ટળે એમ નથી.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("I can find no means to drive away this grief which is drying up my senses. I will not be able to dispel it even if I win a prosperous, unrivaled kingdom on earth with sovereignty like the demigods in heaven.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        load();
        setTitle("Shlok 9");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच-\nएवमुक्त्वा हृषीकेशं गुडाकेशः परन्तप ।\nन योत्स्य इतिगोविन्दमुक्त्वा तूष्णीं बभूव ह ॥");
        } else if (i == 0) {
            this.tv1.setText("સઞ્જય ઉવાચ-\nએવમુક્ત્વા હૃષીકેશં ગુડાકેશઃ પરન્તપ,\nન યોત્સ્ય ઇતિ ગોવિન્દમુક્ત્વા તૂષ્ણીં બભૂવ હ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha-\nEvam uktwaa hrisheekesham gudaakeshah parantapah;\nNa yotsya iti govindam uktwaa tooshneem babhoova ha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" संजय बोले- हे राजन्‌! निद्रा को जीतने वाले अर्जुन अंतर्यामी श्रीकृष्ण महाराज के प्रति इस प्रकार कहकर फिर श्री गोविंद भगवान्‌ से 'युद्ध नहीं करूँगा' यह स्पष्ट कहकर चुप हो गए॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે છે- હે રાજન, શ્રીકૃષ્ણને અર્જુન ‘હું યુદ્ધ નહીં કરું’ એવું સ્પષ્ટ કહી શાંત (ચૂપ) થયો. (૯)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said: Having spoken thus, Arjuna, chastiser of enemies, told Krishna, “Govinda, I shall not fight,” and fell silent.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        load();
        setTitle("Shlok 10");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तमुवाच हृषीकेशः प्रहसन्निव भारत ।\nसेनयोरुभयोर्मध्ये विषीदंतमिदं वचः ॥");
        } else if (i == 0) {
            this.tv1.setText("તમુવાચ હૃષીકેશઃ પ્રહસન્નિવ ભારત,\nસેનયોરુભયોર્મધ્યે વિષીદન્તમિદં વચઃ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Tam uvaacha hrisheekeshah prahasanniva bhaarata;\nSenayor ubhayor madhye visheedantam idam vachah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे भरतवंशी धृतराष्ट्र! अंतर्यामी श्रीकृष्ण महाराज दोनों सेनाओं के बीच में शोक करते हुए उस अर्जुन को हँसते हुए से यह वचन बोले॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("ત્યારે બંને સેનાની મધ્યમાં ગ્લાનિ અને વિષાદમાં ડૂબેલ અર્જુનને સ્મિત કરતાં હૃષિકેશે આમ કહ્યું. (૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("O descendant of Bharata, at that time Krishna, smiling, in the midst of both the armies, spoke the following words to the grief-stricken Arjuna.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        load();
        setTitle("Shlok 11");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्री भगवानुवाच\nअशोच्यानन्वशोचस्त्वं प्रज्ञावादांश्च भाषसे ।\nगतासूनगतासूंश्च नानुशोचन्ति पण्डिताः ॥");
        } else if (i == 0) {
            this.tv1.setText("ભગવાન ઉવાચ -\nઅશોચ્યાનન્વશોચસ્ત્વં પ્રજ્ઞાવાદાંશ્ચ ભાષસે,\nગતાસૂનગતાસૂંશ્ચ નાનુશોચન્તિ પણ્ડિતાઃ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nAshochyaan anvashochastwam prajnaavaadaamshcha bhaashase;\nGataasoon agataasoomshcha naanushochanti panditaah");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले, हे अर्जुन! तू न शोक करने योग्य मनुष्यों के लिए शोक करता है और पण्डितों के से वचनों को कहता है, परन्तु जिनके प्राण चले गए हैं, उनके लिए और जिनके प्राण नहीं गए हैं उनके लिए भी पण्डितजन शोक नहीं करते॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, તું જેનો શોક કરવો યોગ્ય નથી,તેનો શોક કરે છે,અને વિદ્વતાનાં વચનો બોલે છે. પંડિતો જીવતાં હોય કે મૃત્યુ પામ્યા હોય - એ બંને માટે આંસુ નથી વહાવતા. જ્યારે તું તો એમને માટે શોક કરી રહ્યો છે જેઓ હજુ જીવે છે. (૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: While speaking learned words, you are mourning for what is not worthy of grief. Those who are wise lament neither for the living nor for the dead.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        load();
        setTitle("Shlok 12");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न त्वेवाहं जातु नासं न त्वं नेमे जनाधिपाः ।\nन चैव न भविष्यामः सर्वे वयमतः परम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ન ત્વેવાહં જાતુ નાસં ન ત્વં નેમે જનાધિપાઃ,\nન ચૈવ ન ભવિષ્યામઃ સર્વે વયમતઃ પરમ્.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Na twevaaham jaatu naasam na twam neme janaadhipaah;\nNa chaiva na bhavishyaamah sarve vayam atah param.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("न तो ऐसा ही है कि मैं किसी काल में नहीं था, तू नहीं था अथवा ये राजा लोग नहीं थे और न ऐसा ही है कि इससे आगे हम सब नहीं रहेंगे॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("અને વળી એવું થોડું છે કે-ભૂતકાળમાં, મારું, તારું કે આ યુદ્ધમાં શામેલ રાજાઓનું કદી મૃત્યુ જ ન થયું હોય, અથવા ભવિષ્યમાં પણ કદી મૃત્યુ થવાનું જ ન હોય ? (૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("Never was there a time when I did not exist, nor you, nor all these kings; nor in the future shall any of us cease to be.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        load();
        setTitle("Shlok 13");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("देहिनोऽस्मिन्यथा देहे कौमारं यौवनं जरा ।\nतथा देहान्तरप्राप्तिर्धीरस्तत्र न मुह्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("દેહિનોસ્મિન્યથા દેહે કૌમારં યૌવનં જરા,\nતથા દેહાન્તરપ્રાપ્તિર્ધીરસ્તત્ર ન મુહ્યતિ.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Dehino’smin yathaa dehe kaumaaram yauvanam jaraa;\nTathaa dehaantara praaptir dheeras tatra na muhyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जैसे जीवात्मा की इस देह में बालकपन, जवानी और वृद्धावस्था होती है, वैसे ही अन्य शरीर की प्राप्ति होती है, उस विषय में धीर पुरुष मोहित नहीं होता।13॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે મનુષ્ય નો દેહ (શરીર) બાળક બને છે, યુવાન બને છે અને અંતે વૃદ્ધાવસ્થાને પામે છે તેવી જ રીતે જીવનનો અંત આવ્યા પછી તેને બીજા શરીરની પ્રાપ્તિ થાય છે. એથી બુદ્ધિમાન લોકો મોહિત થઈને શોક કરવા નથી બેસતા. (૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("As the embodied soul continuously passes, in this body, from boyhood to youth to old age, the soul similarly passes into another body at death. A sober person is not bewildered by such a change.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        load();
        setTitle("Shlok 14");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मात्रास्पर्शास्तु कौन्तेय शीतोष्णसुखदुःखदाः ।\nआगमापायिनोऽनित्यास्तांस्तितिक्षस्व भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("માત્રાસ્પર્શાસ્તુ કૌન્તેય શીતોષ્ણસુખદુઃખદાઃ,\nઆગમાપાયિનોનિત્યાસ્તાંસ્તિતિક્ષસ્વ ભારત.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Maatraasparshaastu kaunteya sheetoshnasukhaduhkhadaah;\nAagamaapaayino’nityaas taamstitikshaswa bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे कुंतीपुत्र! सर्दी-गर्मी और सुख-दुःख को देने वाले इन्द्रिय और विषयों के संयोग तो उत्पत्ति-विनाशशील और अनित्य हैं, इसलिए हे भारत! उनको तू सहन कर॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કૌન્તેય, ટાઢ-તાપ કે સુખ-દુઃખનો અનુભવ કરવાવાળા ઈન્દ્રિયના પદાર્થો તો ચલાયમાન અને અનિત્ય છે. તે કાયમ માટે રહેતા નથી. એથી હે ભારત, એને સહન કરતા શીખ. (૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Kunti, the nonpermanent appearance of happiness and distress, and their disappearance in due course, are like the appearance and disappearance of winter and summer seasons. They arise from sense perception, O scion of Bharata, and one must learn to tolerate them without being disturbed.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        load();
        setTitle("Shlok 15");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यं हि न व्यथयन्त्येते पुरुषं पुरुषर्षभ ।\nसमदुःखसुखं धीरं सोऽमृतत्वाय कल्पते ॥");
        } else if (i == 0) {
            this.tv1.setText("યં હિ ન વ્યથયન્ત્યેતે પુરુષં પુરુષર્ષભ,\nસમદુઃખસુખં ધીરં સોમૃતત્વાય કલ્પતે.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Yam hi na vyathayantyete purusham purusharshabha;\nSamaduhkha sukham dheeram so’mritatwaaya kalpate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि हे पुरुषश्रेष्ठ! दुःख-सुख को समान समझने वाले जिस धीर पुरुष को ये इन्द्रिय और विषयों के संयोग व्याकुल नहीं करते, वह मोक्ष के योग्य होता है॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે ધીર પુરુષ એનાથી વ્યથિત નથી થતો તથા સુખ અને દુઃખ બંનેમાં સમ રહે છે તે મોક્ષનો અધિકારી થાય છે. (૧૫) ");
        } else if (2 == i2) {
            this.tv2.setText("O best among men [Arjuna], the person who is not disturbed by happiness and distress and is steady in both is certainly eligible for liberation.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        load();
        setTitle("Shlok 16");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नासतो विद्यते भावो नाभावो विद्यते सतः ।\nउभयोरपि दृष्टोऽन्तस्त्वनयोस्तत्वदर्शिभिः ॥");
        } else if (i == 0) {
            this.tv1.setText("નાસતો વિદ્યતે ભાવો નાભાવો વિદ્યતે સતઃ,\nઉભયોરપિ દૃષ્ટોન્તસ્ત્વનયોસ્તત્ત્વદર્શિભિઃ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Naasato vidyate bhaavo naabhaavo vidyate satah;\nUbhayorapi drishto’ntastwanayos tattwadarshibhih");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("असत्‌ वस्तु की तो सत्ता नहीं है और सत्‌ का अभाव नहीं है। इस प्रकार इन दोनों का ही तत्व तत्वज्ञानी पुरुषों द्वारा देखा गया है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("અસત્ કદી અમર નથી રહેતું ,જ્યારે સતનો કદાપિ નાશ નથી થતો તત્વદર્શીઓ એ આવો આનો નિર્ણય લીધેલો છે. (૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are seers of the truth have concluded that of the nonexistent [the material body] there is no endurance and of the eternal [the soul] there is no change. This they have concluded by studying the nature of both.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        load();
        setTitle("Shlok 17");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अविनाशि तु तद्विद्धि येन सर्वमिदं ततम्‌ ।\nविनाशमव्ययस्यास्य न कश्चित्कर्तुमर्हति ॥");
        } else if (i == 0) {
            this.tv1.setText("અવિનાશિ તુ તદ્વિધ્દિ યેન સર્વમિદં તતમ્,\nવિનાશમવ્યયસ્યાસ્ય ન કશ્ચિત્ કર્તુમર્હતિ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Avinaashi tu tad viddhi yena sarvam idam tatam;\nVinaasham avyayasyaasya na kashchit kartum arhati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("नाशरहित तो तू उसको जान, जिससे यह सम्पूर्ण जगत्‌- दृश्यवर्ग व्याप्त है। इस अविनाशी का विनाश करने में कोई भी समर्थ नहीं है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સર્વત્ર વ્યાપક છે તે તત્વ તો અવિનાશી છે, અને જે અવિનાશી હોય એનો નાશ કદાપિ થતો નથી. (૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("That which pervades the entire body you should know to be indestructible. No one is able to destroy that imperishable soul.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        load();
        setTitle("Shlok 18");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अन्तवन्त इमे देहा नित्यस्योक्ताः शरीरिणः ।\nअनाशिनोऽप्रमेयस्य तस्माद्युध्यस्व भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("અન્તવન્ત ઇમે દેહા નિત્યસ્યોક્તાઃ શરીરિણઃ,\nઅનાશિનોપ્રમેયસ્ય તસ્માદ્યુધ્યસ્વ ભારત.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Antavanta ime dehaa nityasyoktaah shareerinah;\nAnaashino’prameyasya tasmaad yudhyaswa bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस नाशरहित, अप्रमेय, नित्यस्वरूप जीवात्मा के ये सब शरीर नाशवान कहे गए हैं, इसलिए हे भरतवंशी अर्जुन! तू युद्ध कर॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ દેહ તો ક્ષણભંગુર છે, વિનાશશીલ છે પરંતુ તેમાં રહેતો આત્મા અમર છે. એનો ન તો અંત આવે છે, કે ન તેને કોઈ મારી શકે છે. એથી હે ભારત, તું યુદ્ધ કર. (૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("The material body of the indestructible, immeasurable and eternal living entity is sure to come to an end; therefore, fight, O descendant of Bharata.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        load();
        setTitle("Shlok 19");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("य एनं वेत्ति हन्तारं यश्चैनं मन्यते हतम्‌ ।\nउभौ तौ न विजानीतो नायं हन्ति न हन्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("ય એનં વેત્તિ હન્તારં યશ્ચૈનં મન્યતે હતમ્,\nઉભૌ તૌ ન વિજાનીતો નાયં હન્તિ ન હન્યતે.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Ya enam vetti hantaaram yashchainam manyate hatam;\nUbhau tau na vijaaneeto naayam hanti na hanyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो इस आत्मा को मारने वाला समझता है तथा जो इसको मरा मानता है, वे दोनों ही नहीं जानते क्योंकि यह आत्मा वास्तव में न तो किसी को मारता है और न किसी द्वारा मारा जाता है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે આત્માને વિનાશશીલ સમજે છે તથા તેને મારવા ઈચ્છે છે, તે નથી જાણતા કે આત્મા ન તો કદી જન્મે છે કે ન તો કદી મરે છે. (૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Neither he who thinks the living entity the slayer nor he who thinks it slain is in knowledge, for the self slays not nor is slain.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        load();
        setTitle("Shlok 20");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न जायते म्रियते वा कदाचि-\nन्नायं भूत्वा भविता वा न भूयः ।\nअजो नित्यः शाश्वतोऽयं पुराणो-\nन हन्यते हन्यमाने शरीरे ॥");
        } else if (i == 0) {
            this.tv1.setText("ન જાયતે મ્રિયતે વા કદાચિ-\nન્નાયં ભૂત્વા ભવિતા વા ન ભૂયઃ,\nઅજો નિત્યઃ શાશ્વતોયં પુરાણો \nન હન્યતે હન્યમાને શરીરે.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Na jaayate mriyate vaa kadaachin\nNaayam bhootwaa bhavitaa vaa na bhooyah;\nAjo nityah shaashwato’yam puraano\nNa hanyate hanyamaane shareere.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यह आत्मा किसी काल में भी न तो जन्मता है और न मरता ही है तथा न यह उत्पन्न होकर फिर होने वाला ही है क्योंकि यह अजन्मा, नित्य, सनातन और पुरातन है, शरीर के मारे जाने पर भी यह नहीं मारा जाता॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("આત્મા તો અજન્મા, અવિનાશી અને અમર છે. શરીરનો નાશ ભલે થાય પરંતુ આત્માનો નાશ કદાપિ થતો નથી. (૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("For the soul there is neither birth nor death at any time. He has not come into being, does not come into being, and will not come into being. He is unborn, eternal, ever-existing and primeval. He is not slain when the body is slain.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        load();
        setTitle("Shlok 21");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वेदाविनाशिनं नित्यं य एनमजमव्ययम्‌ ।\nकथं स पुरुषः पार्थ कं घातयति हन्ति कम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("વેદાવિનાશિનં નિત્યં ય એનમજમવ્યયમ્,\nકથં સ પુરુષઃ પાર્થ કં ઘાતયતિ હન્તિ કમ્.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Vedaavinaashinam nityam ya enam ajam avyayam;\nKatham sa purushah paartha kam ghaatayati hanti kam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे पृथापुत्र अर्जुन! जो पुरुष इस आत्मा को नाशरहित, नित्य, अजन्मा और अव्यय जानता है, वह पुरुष कैसे किसको मरवाता है और कैसे किसको मारता है?॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ, જે વ્યક્તિ આત્માને અવિનાશી, નિત્ય અને અજન્મા માને છે તે કોઈનો નાશ કેવી રીતે કરી શકવાનો છે ? અને તે પોતે પણ કેવી રીતે મરી શકવાનો છે ? (૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("O Partha, how can a person who knows that the soul is indestructible, eternal, unborn and immutable kill anyone or cause anyone to kill?(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        load();
        setTitle("Shlok 22");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वासांसि जीर्णानि यथा विहाय\nनवानि गृह्णाति नरोऽपराणि ।\nतथा शरीराणि विहाय जीर्णा-\nन्यन्यानि संयाति नवानि देही ॥");
        } else if (i == 0) {
            this.tv1.setText("વાસાંસિ જીર્ણાનિ યથા વિહાય\nનવાનિ ગૃહણાતિ નરોપરાણિ,\nતથા શરીરાણિ વિહાય જીર્ણા-\nન્યન્યાનિ સંયાતિ નવાનિ દેહી.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Vaasaamsi jeernaani yathaa vihaaya\nNavaani grihnaati naro’paraani;\nTathaa shareeraani vihaaya jeernaa\nNyanyaani samyaati navaani dehee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  जैसे मनुष्य पुराने वस्त्रों को त्यागकर दूसरे नए वस्त्रों को ग्रहण करता है, वैसे ही जीवात्मा पुराने शरीरों को त्यागकर दूसरे नए शरीरों को प्राप्त होता है॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે કોઈ વ્યક્તિ જૂનાં વસ્ત્ર ત્યજીને નવા વસ્ત્રોને ધારણ કરે છે, તેવી જ રીતે જીવાત્મા એક શરીરને છોડીને બીજા શરીરને પ્રાપ્ત કરે છે. (૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("As a person puts on new garments, giving up old ones, the soul similarly accepts new material bodies, giving up the old and useless ones.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        load();
        setTitle("Shlok 23");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नैनं छिन्दन्ति शस्त्राणि नैनं दहति पावकः ।\nन चैनं क्लेदयन्त्यापो न शोषयति मारुतः ॥");
        } else if (i == 0) {
            this.tv1.setText("નૈનં છિન્દન્તિ શસ્ત્રાણિ નૈનં દહતિ પાવકઃ,\nન ચૈનં ક્લેદયન્ત્યાપો ન શોષયતિ મારુતઃ.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Nainam cchindanti shastraani nainam dahati paavakah;\nNa chainam kledayantyaapo na shoshayati maarutah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस आत्मा को शस्त्र नहीं काट सकते, इसको आग नहीं जला सकती, इसको जल नहीं गला सकता और वायु नहीं सुखा सकता॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("આત્માને ન તો શસ્ત્ર છેદી શકે છે, ન અગ્નિ બાળી શકે છે, ન પાણી ભીંજવી શકે છે કે ન તો પવન સૂકવી શકે છે. (૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("The soul can never be cut to pieces by any weapon, nor burned by fire, nor moistened by water, nor withered by the wind.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        load();
        setTitle("Shlok 24");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अच्छेद्योऽयमदाह्योऽयमक्लेद्योऽशोष्य एव च ।\nनित्यः सर्वगतः स्थाणुरचलोऽयं सनातनः ॥");
        } else if (i == 0) {
            this.tv1.setText("અચ્છેદ્યોયમદાહ્યોયમક્લેદ્યોશોષ્ય એવ ચ,\nનિત્યઃ સર્વગતઃ સ્થાણુરચલોયં સનાતનઃ.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Acchedyo’yam adaahyo’yam akledyo’shoshya eva cha;\nNityah sarvagatah sthaanur achalo’yam sanaatanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि यह आत्मा अच्छेद्य है, यह आत्मा अदाह्य, अक्लेद्य और निःसंदेह अशोष्य है तथा यह आत्मा नित्य, सर्वव्यापी, अचल, स्थिर रहने वाला और सनातन है॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("આત્મા તો અછેદ્ય, અદાહ્ય, અશોષ્ય અને પલળે નહીં તેવો છે. આત્મા તો નિત્ય છે, સર્વવ્યાપી છે, અંતહીન છે, શાશ્વત છે. (૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("This individual soul is unbreakable and insoluble, and can be neither burned nor dried. He is everlasting, present everywhere, unchangeable, immovable and eternally the same.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        load();
        setTitle("Shlok 25");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अव्यक्तोऽयमचिन्त्योऽयमविकार्योऽयमुच्यते ।\nतस्मादेवं विदित्वैनं नानुशोचितुमर्हसि॥॥");
        } else if (i == 0) {
            this.tv1.setText("અવ્યક્તોયમચિન્ત્યોયમવિકાર્યોયમુચ્યતે,\nતસ્માદેવં વિદિત્વૈનં નાનુશોચિતુમર્હસિ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Avyakto’yam achintyo’yam avikaaryo’yam uchyate;\nTasmaad evam viditwainam naanushochitum arhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यह आत्मा अव्यक्त है, यह आत्मा अचिन्त्य है और यह आत्मा विकाररहित कहा जाता है। इससे हे अर्जुन! इस आत्मा को उपर्युक्त प्रकार से जानकर तू शोक करने के योग्य नहीं है अर्थात्‌ तुझे शोक करना उचित नहीं है॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("આત્મા ન તો સ્થૂળ આંખે જોઈ શકાય છે કે ન તો બુદ્ધિ વડે સમજી શકાય છે. આત્મા અવિકારી છે, હંમેશ માટે એક સરખો રહેનાર છે. એથી હે પાર્થ, તારે શોક કરવાની કોઈ આવશ્યકતા નથી.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("It is said that the soul is invisible, inconceivable and immutable. Knowing this, you should not grieve for the body.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        load();
        setTitle("Shlok 26");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथ चैनं नित्यजातं नित्यं वा मन्यसे मृतम्‌ ।\nतथापि त्वं महाबाहो नैवं शोचितुमर्हसि ॥");
        } else if (i == 0) {
            this.tv1.setText("અથ ચૈનં નિત્યજાતં નિત્યં વા મન્યસે મૃતમ્,\nતથાપિ ત્વં મહાબાહો નૈવં શોચિતુમર્હસિ.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Atha chainam nityajaatam nityam vaa manyase mritam;\nTathaapi twam mahaabaaho naivam shochitum arhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("किन्तु यदि तू इस आत्मा को सदा जन्मने वाला तथा सदा मरने वाला मानता हो, तो भी हे महाबाहो! तू इस प्रकार शोक करने योग्य नहीं है॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો, જો તું આત્માને વારેવારે જન્મ લેનાર અથવા મૃત્યુ પામનાર માનતો હોય, તો પણ તારે માટે શોક કરવાનું કોઈ કારણ નથી (૨૬).");
        } else if (2 == i2) {
            this.tv2.setText("If, however, you think that the soul [or the symptoms of life] is always born and dies forever, you still have no reason to lament, O mighty-armed.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        load();
        setTitle("Shlok 27");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("जातस्त हि ध्रुवो मृत्युर्ध्रुवं जन्म मृतस्य च ।\nतस्मादपरिहार्येऽर्थे न त्वं शोचितुमर्हसि ॥");
        } else if (i == 0) {
            this.tv1.setText("જાતસ્ય હિ ધ્રુવો મૃત્યુર્ધ્રુવં જન્મ મૃતસ્ય ચ,\nતસ્માદપરિહાર્યેર્થે ન ત્વં શોચિતુમર્હસિ.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Jaatasya hi dhruvo mrityur dhruvam janma mritasya cha;\nTasmaad aparihaarye’rthe na twam shochitum arhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" क्योंकि इस मान्यता के अनुसार जन्मे हुए की मृत्यु निश्चित है और मरे हुए का जन्म निश्चित है। इससे भी इस बिना उपाय वाले विषय में तू शोक करने योग्य नहीं है॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("કારણ કે જેવી રીતે દરેક જન્મ લેનારનું મૃત્યુ નિશ્ચિત છે તેવી રીતે દરેક મરનારનું ફરી જન્મવું પણ એટલું જ નિશ્ચિત છે. એ ક્રમમાં ફેરફાર કરવા માટે તું અસમર્થ છે. એટલે તારે એ વિચારી શોક કરવાની જરૂર નથી. (૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("One who has taken his birth is sure to die, and after death one is sure to take birth again. Therefore, in the unavoidable discharge of your duty, you should not lament.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        load();
        setTitle("Shlok 28");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अव्यक्तादीनि भूतानि व्यक्तमध्यानि भारत ।\nअव्यक्तनिधनान्येव तत्र का परिदेवना ॥");
        } else if (i == 0) {
            this.tv1.setText("અવ્યક્તાદીનિ ભૂતાનિ વ્યક્તમધ્યાનિ ભારત,\nઅવ્યક્તનિધનાન્યેવ તત્ર કા પરિદેવના.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Avyaktaadeeni bhootaani vyaktamadhyaani bhaarata;\nAvyakta nidhanaanyeva tatra kaa paridevanaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! सम्पूर्ण प्राणी जन्म से पहले अप्रकट थे और मरने के बाद भी अप्रकट हो जाने वाले हैं, केवल बीच में ही प्रकट हैं, फिर ऐसी स्थिति में क्या शोक करना है?॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, દરેક જીવાત્મા જન્મ પહેલાં અને મૃત્યુ પછી દેખાતો નથી. આ તો વચ્ચેની અવસ્થામાં જ તું એને જોઈ શકે છે. તો પછી એને માટે તું કેમ શોક કરે છે ? (૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("All created beings are unmanifest in their beginning, manifest in their interim state, and unmanifest again when annihilated. So what need is there for lamentation?(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        load();
        setTitle("Shlok 29");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आश्चर्यवत्पश्यति कश्चिदेन-\nमाश्चर्यवद्वदति तथैव चान्यः ।\nआश्चर्यवच्चैनमन्यः श्रृणोति\nश्रुत्वाप्येनं वेद न चैव कश्चित्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("આશ્ચર્યવત્પશ્યતિ કશ્ચિદેન-\nમાશ્ચર્યવદ્વદતિ તથૈવ ચાન્યઃ,\nઆશ્ચર્યવચ્ચૈનમન્યઃ શ્રૃણોતિ\nશ્રુત્વાપ્યેનં વેદ ન ચૈવ કશ્ચિત્.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Aashcharyavat pashyati kashchid enam\nAashcharyavad vadati tathaiva chaanyah;\nAashcharyavacchainam anyah shrinoti\nShrutwaapyenam veda na chaiva kashchit");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कोई एक महापुरुष ही इस आत्मा को आश्चर्य की भाँति देखता है और वैसे ही दूसरा कोई महापुरुष ही इसके तत्व का आश्चर्य की भाँति वर्णन करता है तथा दूसरा कोई अधिकारी पुरुष ही इसे आश्चर्य की भाँति सुनता है और कोई-कोई तो सुनकर भी इसको नहीं जानता॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("કોઈ આત્માને અચરજથી જુએ છે, કોઈ અચરજથી એના વિશે વર્ણન કરે છે, પરંતુ આત્મા વિશે સાંભળનાર અનેકોમાંથી કોઈક જ એને ખરેખર જાણી શકે છે. (૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("Some look on the soul as amazing, some describe him as amazing, and some hear of him as amazing, while others, even after hearing about him, cannot understand him at all.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        load();
        setTitle("Shlok 30");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("देही नित्यमवध्योऽयं देहे सर्वस्य भारत ।\nतस्मात्सर्वाणि भूतानि न त्वं शोचितुमर्हसि ॥");
        } else if (i == 0) {
            this.tv1.setText("દેહી નિત્યમવધ્યોયં દેહે સર્વસ્ય ભારત,\nતસ્માત્સર્વાણિ ભૂતાનિ ન ત્વં શોચિતુમર્હસિ.(૩૦) ");
        } else if (2 == i) {
            this.tv1.setText("Dehee nityam avadhyo’yam dehe sarvasya bhaarata;\nTasmaat sarvaani bhootaani na twam shochitum arhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! यह आत्मा सबके शरीर में सदा ही अवध्य (जिसका वध नहीं किया जा सके) है। इस कारण सम्पूर्ण प्राणियों के लिए तू शोक करने योग्य नहीं है॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત, આત્મા નિત્ય છે, અવિનાશી છે, એથી તારે કોઈના મૃત્યુ પામવા પર શોક કરવાની જરૂરત નથી.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("O descendant of Bharata, he who dwells in the body can never be slain. Therefore you need not grieve for any living being.(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        load();
        setTitle("Shlok 31");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स्वधर्ममपि चावेक्ष्य न विकम्पितुमर्हसि ।\nधर्म्याद्धि युद्धाच्छ्रेयोऽन्यत्क्षत्रियस्य न विद्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("સ્વધર્મમપિ ચાવેક્ષ્ય ન વિકમ્પિતુમર્હસિ,\nધર્મ્યાદ્ધિ યુદ્ધાછ્રેયોન્યત્ક્ષત્રિયસ્ય ન વિદ્યતે.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Swadharmam api chaavekshya na vikampitum arhasi;\nDharmyaaddhi yuddhaacchreyo’nyat kshatriyasya na vidyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" तथा अपने धर्म को देखकर भी तू भय करने योग्य नहीं है अर्थात्‌ तुझे भय नहीं करना चाहिए क्योंकि क्षत्रिय के लिए धर्मयुक्त युद्ध से बढ़कर दूसरा कोई कल्याणकारी कर्तव्य नहीं है॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ, તું તારા સ્વ-ધર્મ વિશે વિચાર. તું ક્ષત્રિય છે અને ન્યાય માટે લડાનાર આ યુદ્ધમાં ભાગ લેવાથી મોટું તારે માટે કોઈ કર્તવ્ય નથી. (૩૧)");
        } else if (2 == i2) {
            this.tv2.setText("Considering your specific duty as a kshatriya, you should know that there is no better engagement for you than fighting on religious principles; and so there is no need for hesitation.(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        load();
        setTitle("Shlok 32");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदृच्छया चोपपन्नां स्वर्गद्वारमपावृतम्‌ ।\nसुखिनः क्षत्रियाः पार्थ लभन्ते युद्धमीदृशम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યદૃચ્છયા ચોપપન્નં સ્વર્ગદ્વારમપાવૃતમ્,\nસુખિનઃ ક્ષત્રિયાઃ પાર્થ લભન્તે યુદ્ધમીદૃશમ્.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Yadricchayaa chopapannam swargadwaaram apaavritam;\nSukhinah kshatriyaah paartha labhante yuddham eedrisham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे पार्थ! अपने-आप प्राप्त हुए और खुले हुए स्वर्ग के द्वार रूप इस प्रकार के युद्ध को भाग्यवान क्षत्रिय लोग ही पाते हैं॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, સ્વર્ગના દ્વાર સમું આવું યુદ્ધ લડવાનું સૌભાગ્ય કોઈ ભાગ્યવાન ક્ષત્રિયને જ મળે છે. (૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("O Partha, happy are the kshatriyas to whom such fighting opportunities come unsought, opening for them the doors of the heavenly planets.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        load();
        setTitle("Shlok 33");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथ चेत्त्वमिमं धर्म्यं सङ्‍ग्रामं न करिष्यसि ।\nततः स्वधर्मं कीर्तिं च हित्वा पापमवाप्स्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("અથ ચૈત્ત્વમિમં ધર્મ્યં સંગ્રામં ન કરિષ્યસિ,\nતતઃ સ્વધર્મં કીર્તિં ચ હિત્વા પાપમવાપ્સ્યસિ.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Atha chettwam imam dharmyam samgraamam na karishyasi;\nTatah swadharmam keertim cha hitwaa paapam avaapsyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" किन्तु यदि तू इस धर्मयुक्त युद्ध को नहीं करेगा तो स्वधर्म और कीर्ति को खोकर पाप को प्राप्त होगा ॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("જો તું યુદ્ધ નહીં કરે તો તારા સ્વધર્મનું પાલન ન કરવાથી અપકીર્તિ અને પાપનો ભાગીદાર થશે. (૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("If, however, you do not perform your religious duty of fighting, then you will certainly incur sins for neglecting your duties and thus lose your reputation as a fighter.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        load();
        setTitle("Shlok 34");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अकीर्तिं चापि भूतानि\nकथयिष्यन्ति तेऽव्ययाम्‌ ।\nसम्भावितस्य चाकीर्ति-\nर्मरणादतिरिच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("અકીર્તિં ચાપિ ભૂતાનિ કથયિષ્યન્તિ તેવ્યયામ્,\nસંભાવિતસ્ય ચાકીર્તિર્મરણાદતિરિચ્યતે.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Akeertim chaapi bhootaani kathayishyanti te’vyayaam;\nSambhaavitasya chaakeertir maranaad atirichyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तथा सब लोग तेरी बहुत काल तक रहने वाली अपकीर्ति का भी कथन करेंगे और माननीय पुरुष के लिए अपकीर्ति मरण से भी बढ़कर है॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("લોકો તારી બદનામી કરશે, તારી (અકીર્તિની) વાતો કરતા થાકશે નહીં. તારા જેવા પ્રતિષ્ઠિત વ્યક્તિ માટે અપયશ મૃત્યુ કરતાં પણ બદતર સાબિત થશે. (૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("People will always speak of your infamy, and for a respectable person, dishonor is worse than death.(34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        load();
        setTitle("Shlok 35");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भयाद्रणादुपरतं मंस्यन्ते त्वां महारथाः ।\nयेषां च त्वं बहुमतो भूत्वा यास्यसि लाघवम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ભયાદ્રણાદુપરતં મંસ્યન્તે ત્વાં મહારથાઃ,\nયેષાં ચ ત્વં બહુમતો ભૂત્વા યાસ્યસિ લાઘવમ્.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Bhayaad ranaad uparatam mamsyante twaam mahaarathaah;\nYeshaam cha twam bahumato bhootwaa yaasyasi laaghavam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" और जिनकी दृष्टि में तू पहले बहुत सम्मानित होकर अब लघुता को प्राप्त होगा, वे महारथी लोग तुझे भय के कारण युद्ध से हटा हुआ मानेंगे॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("આજે તારા સામર્થ્યની પ્રસંશા કરવાવાળા મહારથી યોદ્ધાઓ તને યુદ્ધમાંથી ભાગી ગયેલો ગણશે અને એમની નજરમાંથી તું કાયમ માટે ઉતરી જઈશ.(૩૫)");
        } else if (2 == i2) {
            this.tv2.setText("The great generals who have highly esteemed your name and fame will think that you have left the battlefield out of fear only, and thus they will consider you insignificant.(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36() {
        load();
        setTitle("Shlok 36");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अवाच्यवादांश्च बहून्‌ वदिष्यन्ति तवाहिताः ।\nनिन्दन्तस्तव सामर्थ्यं ततो दुःखतरं नु किम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અવાચ્યવાદાંશ્ચ બહૂન્ વદિષ્યન્તિ તવાહિતાઃ,\nનિન્દન્તસ્તવ સામર્થ્યં તતો દુઃખતરં નુ કિમ્.(૩૬)");
        } else if (2 == i) {
            this.tv1.setText("Avaachyavaadaamshcha bahoon vadishyanti tavaahitaah;\nNindantastava saamarthyam tato duhkhataram nu kim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तेरे वैरी लोग तेरे सामर्थ्य की निंदा करते हुए तुझे बहुत से न कहने योग्य वचन भी कहेंगे, उससे अधिक दुःख और क्या होगा?॥36॥");
        } else if (i2 == 0) {
            this.tv2.setText("તારા પ્રતિસ્પર્ધીઓ તારી નિંદા કરશે અને તને ન કહેવાના કટુ વચનો કહેશે. એથી અધિક દુઃખદાયી બીજું શું હોઈ શકે  ? (૩૬)");
        } else if (2 == i2) {
            this.tv2.setText("Your enemies will describe you in many unkind words and scorn your ability. What could be more painful for you?(36)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok37() {
        load();
        setTitle("Shlok 37");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("हतो वा प्राप्स्यसि स्वर्गं जित्वा वा भोक्ष्यसे महीम्‌ ।\nतस्मादुत्तिष्ठ कौन्तेय युद्धाय कृतनिश्चयः ॥");
        } else if (i == 0) {
            this.tv1.setText("હતો વા પ્રાપ્સ્યસિ સ્વર્ગં જિત્વા વા ભોક્ષ્યસે મહીમ્,\nતસ્માદુત્તિષ્ઠ કૌન્તેય યુધ્દાય કૃતનિશ્ચયઃ.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Hato vaa praapsyasi swargam jitwaa vaa bhokshyase maheem;\nTasmaad uttishtha kaunteya yuddhaaya kritanishchayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("या तो तू युद्ध में मारा जाकर स्वर्ग को प्राप्त होगा अथवा संग्राम में जीतकर पृथ्वी का राज्य भोगेगा। इस कारण हे अर्जुन! तू युद्ध के लिए निश्चय करके खड़ा हो जा॥37॥");
        } else if (i2 == 0) {
            this.tv2.setText("હવે જરા વિચાર કર કે જો તું યુદ્ધ કરશે તો તારું શું જવાનું છે ? જો તું યુદ્ધ કરતાં મૃત્યુ પામીશ તો તને સ્વર્ગ મળશે અને જો જીવતો રહીશ (અને વિજય પ્રાપ્ત કરીશ) તો વિશાળ સામ્રાજ્યનો અધિકારી બનીશ. એથી હે કૌન્તેય, ઉઠ. (૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Kunti, either you will be killed on the battlefield and attain the heavenly planets, or you will conquer and enjoy the earthly kingdom. Therefore get up and fight with determination.(37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        load();
        setTitle("Shlok 38");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सुखदुःखे समे कृत्वा लाभालाभौ जयाजयौ ।\nततो युद्धाय युज्यस्व नैवं पापमवाप्स्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("સુખદુઃખે સમે કૃત્વા લાભાલાભૌ જયાજયૌ,\nતતો યુદ્ધાય યુજ્યસ્વ નૈવં પાપમવાપ્સ્યસિ.(૩૮)");
        } else if (2 == i) {
            this.tv1.setText("Sukhaduhkhe same kritwaa laabhaalaabhau jayaajayau;\nTato yuddhaaya yujyaswa naivam paapamavaapsyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जय-पराजय, लाभ-हानि और सुख-दुख को समान समझकर, उसके बाद युद्ध के लिए तैयार हो जा, इस प्रकार युद्ध करने से तू पाप को नहीं प्राप्त होगा॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("સુખ-દુઃખ, લાભ-હાનિ, જય-પરાજય બધાને સમાન ગણી યુદ્ધ માટે તત્પર બન. એમ કરવાથી તું પાપનો ભાગી નહીં થાય. (૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("Do thou fight for the sake of fighting, without considering happiness or distress, loss or gain, victory or defeat—and by so doing you shall never incur sin.(38)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        load();
        setTitle("Shlok 39");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एषा तेऽभिहिता साङ्‍ख्ये बुद्धिर्योगे त्विमां श्रृणु ।\nबुद्ध्‌या युक्तो यया पार्थ कर्मबन्धं प्रहास्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("એષા તેભિહિતા સાંખ્યે બુધ્દિર્યોગે ત્વિમાં શ્રૃણુ,\nબુદ્ધ્યાયુક્તો યયા પાર્થ કર્મબન્ધં પ્રહાસ્યસિ.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Eshaa tebhihitaa saankhye buddhir yoge twimaam shrinu;\nBuddhyaa yukto yayaa paartha karma bandham prahaasyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! यह बुद्धि तेरे लिए ज्ञानयोग के विषय में कही गई और अब तू इसको कर्मयोग के (अध्याय 3 श्लोक 3 की टिप्पणी में इसका विस्तार देखें।) विषय में सुन- जिस बुद्धि से युक्त हुआ तू कर्मों के बंधन को भली-भाँति त्याग देगा अर्थात सर्वथा नष्ट कर डालेगा॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("મેં અત્યાર સુધી જે વાત કરી તે જ્ઞાનની દૃષ્ટિએ કરી. હવે કર્મની દૃષ્ટિએ પણ તને સમજાવું જેથી તારા કર્મોના ફળને લઈને તને જો કોઈ ભય હોય તો તેનાથી તું મુક્ત થઈ જાય. (૩૯)");
        } else if (2 == i2) {
            this.tv2.setText("Thus far I have described this knowledge to you through analytical study. Now listen as I explain it in terms of working without fruitive results. O son of Pritha, when you act in such knowledge you can free yourself from the bondage of works.(39)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        load();
        setTitle("Shlok 40");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यनेहाभिक्रमनाशोऽस्ति प्रत्यवातो न विद्यते ।\nस्वल्पमप्यस्य धर्मस्य त्रायते महतो भयात्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("નેહાભિક્રમનાશોસ્તિ પ્રત્યવાયો ન વિદ્યતે,\nસ્વલ્પમપ્યસ્ય ધર્મસ્ય ત્રાયતે મહતો ભયાત્.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Nehaabhikramanaashosti pratyavaayo na vidyate;\nSwalpam apyasya dharmasya traayate mahato bhayaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस कर्मयोग में आरंभ का अर्थात बीज का नाश नहीं है और उलटा फलरूप दोष भी नहीं है, बल्कि इस कर्मयोग रूप धर्म का थोड़ा-सा भी साधन जन्म-मृत्यु रूप महान भय से रक्षा कर लेता है॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મયોગના હિસાબે કરેલું કોઈ પણ કર્મ વ્યર્થ નથી જતું.આ ધર્મ નું થોડું પણ આચરણ મોટા ભય થી મનુષ્ય ને બચાવે છે. (૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("In this endeavor there is no loss or diminution, and a little advancement on this path can protect one from the most dangerous type of fear.(40)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        load();
        setTitle("Shlok 41");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("व्यवसायात्मिका बुद्धिरेकेह कुरुनन्दन ।\nबहुशाका ह्यनन्ताश्च बुद्धयोऽव्यवसायिनाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("વ્યવસાયાત્મિકા બુધ્દિરેકેહ કુરુનન્દન,\nબહુશાખા હ્યનન્તાશ્ચ બુધ્દયોવ્યવસાયિનામ્.(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Vyavasaayaatmikaa buddhir ekeha kurunandana;\nBahushaakhaa hyanantaashcha buddhayovyavasaayinaam");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! इस कर्मयोग में निश्चयात्मिका बुद्धि एक ही होती है, किन्तु अस्थिर विचार वाले विवेकहीन सकाम मनुष्यों की बुद्धियाँ निश्चय ही बहुत भेदों वाली और अनन्त होती हैं॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે કર્મયોગને અનુસરે છે એની બુદ્ધિ એક લક્ષ્ય પર સ્થિર રહે છે. જ્યારે યોગથી વિહીન વ્યક્તિની બુદ્ધિ અનેક લક્ષ્યવાળી હોય છે (અર્થાત્ વિભાજીત હોય છે). (૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are on this path are resolute in purpose, and their aim is one. O beloved child of the Kurus, the intelligence of those who are irresolute is many-branched.(41)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42_43_44() {
        load();
        setTitle("Shlok 42,43,44");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यामिमां पुष्पितां वाचं प्रवदन्त्यविपश्चितः ।\nवेदवादरताः पार्थ नान्यदस्तीति वादिनः ॥\nकामात्मानः स्वर्गपरा जन्मकर्मफलप्रदाम्‌ ।\nक्रियाविश्लेषबहुलां भोगैश्वर्यगतिं प्रति ॥\nभोगैश्वर्यप्रसक्तानां तयापहृतचेतसाम्‌ ।\nव्यवसायात्मिका बुद्धिः समाधौ न विधीयते ॥");
        } else if (i == 0) {
            this.tv1.setText("યામિમાં પુષ્પિતાં વાચં પ્રવદન્ત્યવિપશ્ચિતઃ,\nવેદવાદરતાઃ પાર્થ નાન્યદસ્તીતિ વાદિનઃ.(૪૨)\nકામાત્માનઃ સ્વર્ગપરા જન્મકર્મફલપ્રદામ્,\nક્રિયાવિશેષબહુલાં ભોગૈશ્વર્યગતિં પ્રતિ.(૪૩)\nભોગૈશ્વર્યપ્રસક્તાનાં તયાપહૃતચેતસામ્,\nવ્યવસાયાત્મિકા બુધ્દિઃ સમાધૌ ન વિધીયતે.(૪૪)");
        } else if (2 == i) {
            this.tv1.setText("Yaam imaam pushpitaam vaacham pravadantyavipashchitah;\nVedavaadarataah paartha naanyad asteeti vaadinah.\nKaamaatmaanah swargaparaa janmakarmaphalapradaam;\nKriyaavisheshabahulaam bhogaishwaryagatim prati.\nBhogaishwarya prasaktaanaam tayaapahritachetasaam;\nVyavasaayaatmikaa buddhih samaadhau na vidheeyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जो भोगों में तन्मय हो रहे हैं, जो कर्मफल के प्रशंसक वेदवाक्यों में ही प्रीति रखते हैं, जिनकी बुद्धि में स्वर्ग ही परम प्राप्य वस्तु है और जो स्वर्ग से बढ़कर दूसरी कोई वस्तु ही नहीं है- ऐसा कहने वाले हैं, वे अविवेकीजन इस प्रकार की जिस पुष्पित अर्थात्‌ दिखाऊ शोभायुक्त वाणी को कहा करते हैं, जो कि जन्मरूप कर्मफल देने वाली एवं भोग तथा ऐश्वर्य की प्राप्ति के लिए नाना प्रकार की बहुत-सी क्रियाओं का वर्णन करने वाली है, उस वाणी द्वारा जिनका चित्त हर लिया गया है, जो भोग और ऐश्वर्य में अत्यन्त आसक्त हैं, उन पुरुषों की परमात्मा में निश्चियात्मिका बुद्धि नहीं होती॥42-43-44॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ, એવા યોગહીન લોકો કેવળ વેદોના સંભાષણને (કર્મ-કાંડને) જ સર્વ કાંઈ માને છે,(સ્વર્ગ અને સ્વર્ગ નાં સુખો ને જ પ્રાપ્ત કરવા યોગ્ય વસ્તુ માને છે અને બીજું કાંઇ ઉત્તમ નથી તેમ બોલે છે) તેઓ દુન્યવી ઈચ્છાઓમાં (વાસનાઓમાં) ફસાયેલ હોય છે. એવા લોકો જન્મ-મરણના ચક્રમાં ફર્યા કરે છે. ભોગ ઐશ્વર્યની ઈચ્છાથી જુદી જુદી જાતના કર્મોમાં પ્રવૃત્ત થયેલ એવા લોકોની બુદ્ધિનું હરણ થયેલું હોય છે. એથી તેઓ કર્મયોગમાં કુશળતા પામીને પ્રભુની (સમાધિદશાની) પ્રાપ્તિ કરી શકતા નથી. (૪૨-૪૩-૪૪)");
        } else if (2 == i2) {
            this.tv2.setText("Men of small knowledge are very much attached to the flowery words of the Vedas, which recommend various fruitive activities for elevation to heavenly planets, resultant good birth, power, and so forth. Being desirous of sense gratification and opulent life, they say that there is nothing more than this. In the minds of those who are too attached to sense enjoyment and material opulence, and who are bewildered by such things, the resolute determination for devotional service to the Supreme Lord does not take place.(42-43-44)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok45() {
        load();
        setTitle("Shlok 45");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्रैगुण्यविषया वेदा निस्त्रैगुण्यो भवार्जुन ।\nनिर्द्वन्द्वो नित्यसत्वस्थो निर्योगक्षेम आत्मवान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ત્રૈગુણ્યવિષયા વેદા નિસ્ત્રૈગુણ્યો ભવાર્જુન,\nનિર્દ્વન્દ્વો નિત્યસત્ત્વસ્થો નિર્યોગક્ષેમ આત્મવાન્.(૪૫)");
        } else if (2 == i) {
            this.tv1.setText("Traigunyavishayaa vedaa nistraigunyo bhavaarjuna;\nNirdwandwo nityasatwastho niryogakshema aatmavaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! वेद उपर्युक्त प्रकार से तीनों गुणों के कार्य रूप समस्त भोगों एवं उनके साधनों का प्रतिपादन करने वाले हैं, इसलिए तू उन भोगों एवं उनके साधनों में आसक्तिहीन, हर्ष-शोकादि द्वंद्वों से रहित, नित्यवस्तु परमात्मा में स्थित योग (अप्राप्त की प्राप्ति का नाम 'योग' है।) क्षेम (प्राप्त वस्तु की रक्षा का नाम 'क्षेम' है।) को न चाहने वाला और स्वाधीन अन्तःकरण वाला हो॥45॥");
        } else if (i2 == 0) {
            this.tv2.setText("વેદમાં ત્રણ ગુણોનું વર્ણન કરેલું છે. હે અર્જુન, તારે એ ત્રણે ગુણોથી પર - ગુણાતીત થઈ બધા જ દ્વંદ્વોથી મુક્તિ મેળવવાની છે. એથી તું (લડવાથી થતી) લાભ-હાનિની ચિંતા છોડ અને આત્મસ્થિત થા.(૪૫)");
        } else if (2 == i2) {
            this.tv2.setText("The Vedas deal mainly with the subject of the three modes of material nature. O Arjuna, become transcendental to these three modes. Be free from all dualities and from all anxieties for gain and safety, and be established in the self.(45)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok46() {
        load();
        setTitle("Shlok 46");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यावानर्थ उदपाने सर्वतः सम्प्लुतोदके ।\nतावान्सर्वेषु वेदेषु ब्राह्मणस्य विजानतः ॥");
        } else if (i == 0) {
            this.tv1.setText("યાવાનર્થ ઉદપાને સર્વતઃ સંપ્લુતોદકે,\nતાવાન્સર્વેષુ વેદેષુ બ્રાહ્મણસ્ય વિજાનતઃ.(૪૬)");
        } else if (2 == i) {
            this.tv1.setText("Yaavaanartha udapaane sarvatah samplutodake;\nTaavaan sarveshu vedeshu braahmanasya vijaanatah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सब ओर से परिपूर्ण जलाशय के प्राप्त हो जाने पर छोटे जलाशय में मनुष्य का जितना प्रयोजन रहता है, ब्रह्म को तत्व से जानने वाले ब्राह्मण का समस्त वेदों में उतना ही प्रयोजन रह जाता है॥46॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે સરોવરનું પાણી મળી જાય તેને કુવાના પાણીની જરૂરિયાત રહેતી નથી તેવી જ રીતે જેણે બ્રહ્મનું જ્ઞાન મેળવી લીધું હોય તેને પછી વેદનું અધ્યયન કરવાની જરૂરત રહેતી નથી. (૪૬)");
        } else if (2 == i2) {
            this.tv2.setText("All purposes served by a small well can at once be served by a great reservoir of water. Similarly, all the purposes of the Vedas can be served to one who knows the purpose behind them.(46)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok47() {
        load();
        setTitle("Shlok 47");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मण्येवाधिकारस्ते मा फलेषु कदाचन ।\nमा कर्मफलहेतुर्भुर्मा ते संगोऽस्त्वकर्मणि ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મણ્યેવાધિકારસ્તે મા ફલેષુ કદાચન,\nમા કર્મફલહેતુર્ભૂર્મા તે સઙ્ગોસ્ત્વકર્મણિ.(૪૭)");
        } else if (2 == i) {
            this.tv1.setText("Karmanyevaadhikaaraste maa phaleshu kadaachana;\nMaa karmaphalahetur bhoor maa te sango’stwakarmani.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तेरा कर्म करने में ही अधिकार है, उसके फलों में कभी नहीं। इसलिए तू कर्मों के फल हेतु मत हो तथा तेरी कर्म न करने में भी आसक्ति न हो॥47॥");
        } else if (i2 == 0) {
            this.tv2.setText("(એક વાત બરાબર સમજી લે કે) તારો “અધિકાર” માત્ર કર્મ કરવાનો છે, એનું કેવું ફળ મળે તેના પર નથી. એથી ફળ મેળવવાની આશાથી કોઈ કર્મ ન કર. (ફળ પર માત્ર પ્રભુ નો અધિકાર છે) જો તું ફળ મેળવવા માટે કર્મ કરીશ તો તને કર્મમાં આસક્તિ થશે. (૪૭)");
        } else if (2 == i2) {
            this.tv2.setText("You have a right to perform your prescribed duty, but you are not entitled to the fruits of action. Never consider yourself the cause of the results of your activities, and never be attached to not doing your duty.(47)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok48() {
        load();
        setTitle("Shlok 48");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("योगस्थः कुरु कर्माणि संग त्यक्त्वा धनंजय ।\nसिद्धयसिद्धयोः समो भूत्वा समत्वं योग उच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યોગસ્થઃ કુરુ કર્માણિ સઙ્ગં ત્યક્ત્વા ધનઞ્જય,\nસિદ્ધ્યસિદ્ધ્યોઃ સમો ભૂત્વા સમત્વં યોગ ઉચ્યતે.(૪૮)");
        } else if (2 == i) {
            this.tv1.setText("Yogasthah kuru karmaani sangam tyaktwaa dhananjaya;\nSiddhyasiddhyoh samo bhootwaa samatwam yoga uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे धनंजय! तू आसक्ति को त्यागकर तथा सिद्धि और असिद्धि में समान बुद्धिवाला होकर योग में स्थित हुआ कर्तव्य कर्मों को कर, समत्व (जो कुछ भी कर्म किया जाए, उसके पूर्ण होने और न होने में तथा उसके फल में समभाव रहने का नाम 'समत्व' है।) ही योग कहलाता है॥48॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે ધનંજય, કર્મની સફળતા કે નિષ્ફળતા - બંનેમાં સમાન ચિત્ત રહીને તથા કર્મના ફળની આશાથી રહિત થઈને કર્મ કર. આ રીતે કર્મ કરવાને (સમતા ને) જ યોગ કહેવામાં આવે છે. (૪૮)");
        } else if (2 == i2) {
            this.tv2.setText("Perform your duty equipoised, O Arjuna, abandoning all attachment to success or failure. Such equanimity is called yoga.(48)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok49() {
        load();
        setTitle("Shlok 49");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दूरेण ह्यवरं कर्म बुद्धियोगाद्धनंजय ।\nबुद्धौ शरणमन्विच्छ कृपणाः फलहेतवः ॥");
        } else if (i == 0) {
            this.tv1.setText("દૂરેણ હ્યવરં કર્મ બુદ્ધિયોગાદ્ધનઞ્જય,\nબુદ્ધૌ શરણમન્વિચ્છ કૃપણાઃ ફલહેતવઃ.(૪૯)");
        } else if (2 == i) {
            this.tv1.setText("Doorena hyavaram karma buddhiyogaad dhananjaya;\nBuddhau sharanamanwiccha kripanaah phalahetavah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस समत्वरूप बुद्धियोग से सकाम कर्म अत्यन्त ही निम्न श्रेणी का है। इसलिए हे धनंजय! तू समबुद्धि में ही रक्षा का उपाय ढूँढ अर्थात्‌ बुद्धियोग का ही आश्रय ग्रहण कर क्योंकि फल के हेतु बनने वाले अत्यन्त दीन हैं॥49॥");
        } else if (i2 == 0) {
            this.tv2.setText("રીતે (ફલેચ્છાથી રહિત અને સમત્વ બુદ્ધિથી) કરાયેલ કર્મો, ફલાશાથી કરાયેલ કર્મો કરતાં અતિ ઉત્તમ છે. (એથી સમબુદ્ધિ રાખી કર્મ કરવામાં જ સાર છે.) સમબુદ્ધિથી કર્મ કરવાવાળો વ્યક્તિ કર્મથી લેપાતો નથી (૪૯)");
        } else if (2 == i2) {
            this.tv2.setText("O Dhananjaya, keep all abominable activities far distant by devotional service, and in that consciousness surrender unto the Lord. Those who want to enjoy the fruits of their work are misers.(49)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok50() {
        load();
        setTitle("Shlok 50");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बुद्धियुक्तो जहातीह उभे सुकृतदुष्कृते ।\nतस्माद्योगाय युज्यस्व योगः कर्मसु कौशलम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("બુધ્દિયુક્તો જહાતીહ ઉભે સુકૃતદુષ્કૃતે,\nતસ્માદ્યોગાય યુજ્યસ્વ યોગઃ કર્મસુ કૌશલમ્.(૫૦)");
        } else if (2 == i) {
            this.tv1.setText("Buddhiyukto jahaateeha ubhe sukrita dushkrite;\nTasmaad yogaaya yujyaswa yogah karmasu kaushalam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("समबुद्धियुक्त पुरुष पुण्य और पाप दोनों को इसी लोक में त्याग देता है अर्थात उनसे मुक्त हो जाता है। इससे तू समत्व रूप योग में लग जा, यह समत्व रूप योग ही कर्मों में कुशलता है अर्थात कर्मबंध से छूटने का उपाय है॥50॥");
        } else if (i2 == 0) {
            this.tv2.setText("અને તે પાપ તથા પુણ્યથી પર થઈ જાય છે. એથી તું સમત્વના આ યોગમાં કુશળતા મેળવ. (કર્મ માં કુશળતા એ જ યોગ છે) કર્મબંધનથી છૂટવાનો એ જ ઉપાય છે.(૫૦)");
        } else if (2 == i2) {
            this.tv2.setText("A man engaged in devotional service rids himself of both good and bad actions even in this life. Therefore strive for yoga, which is the art of all work.(50)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok51() {
        load();
        setTitle("Shlok 51");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मजं बुद्धियुक्ता हि फलं त्यक्त्वा मनीषिणः ।\nजन्मबन्धविनिर्मुक्ताः पदं गच्छन्त्यनामयम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મજં બુધ્દિયુક્તા હિ ફલં ત્યક્ત્વા મનીષિણઃ,\nજન્મબન્ધવિનિર્મુક્તાઃ પદં ગચ્છન્ત્યનામયમ્.(૫૧)");
        } else if (2 == i) {
            this.tv1.setText("Karmajam buddhiyuktaa hi phalam tyaktwaa maneeshinah;\nJanmabandha vinirmuktaah padam gacchantyanaamayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि समबुद्धि से युक्त ज्ञानीजन कर्मों से उत्पन्न होने वाले फल को त्यागकर जन्मरूप बंधन से मुक्त हो निर्विकार परम पद को प्राप्त हो जाते हैं॥51॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે વ્યક્તિ સમબુદ્ધિથી સંપન્ન થઈને કર્મફળનો ત્યાગ કરે છે તે જન્મ-મરણના ચક્રથી છૂટી જઈને પરમપદની પ્રાપ્તિ કરે છે. (૫૧)");
        } else if (2 == i2) {
            this.tv2.setText("By thus engaging in devotional service to the Lord, great sages or devotees free themselves from the results of work in the material world. In this way they become free from the cycle of birth and death and attain the state beyond all miseries (by going back to Godhead).(51)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok52() {
        load();
        setTitle("Shlok 52");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा ते मोहकलिलं बुद्धिर्व्यतितरिष्यति ।\nतदा गन्तासि निर्वेदं श्रोतव्यस्य श्रुतस्य च ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા તે મોહકલિલં બુદ્ધિર્વ્યતિતરિષ્યતિ,\nતદા ગન્તાસિ નિર્વેદં શ્રોતવ્યસ્ય શ્રુતસ્ય ચ.(૫૨)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa te mohakalilam buddhir vyatitarishyati;\nTadaa gantaasi nirvedam shrotavyasya shrutasya cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जिस काल में तेरी बुद्धि मोहरूपी दलदल को भलीभाँति पार कर जाएगी, उस समय तू सुने हुए और सुनने में आने वाले इस लोक और परलोक संबंधी सभी भोगों से वैराग्य को प्राप्त हो जाएगा॥52॥");
        } else if (i2 == 0) {
            this.tv2.setText("યદા તે મોહકલિલં બુદ્ધિર્વ્યતિતરિષ્યતિ,તદા ગન્તાસિ નિર્વેદં શ્રોતવ્યસ્ય શ્રુતસ્ય ચ. જ્યારે તારી બુદ્ધિ મોહરૂપી અંધકારથી ઉપર ઉઠશે ત્યારે આ લોક અને પરલોકના બધા ભોગપદાર્થોથી તને વૈરાગ્ય પેદા થશે. (૫૨)");
        } else if (2 == i2) {
            this.tv2.setText("When your intelligence has passed out of the dense forest of delusion, you shall become indifferent to all that has been heard and all that is to be heard.(52)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok53() {
        load();
        setTitle("Shlok 53");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रुतिविप्रतिपन्ना ते यदा स्थास्यति निश्चला ।\nसमाधावचला बुद्धिस्तदा योगमवाप्स्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રુતિવિપ્રતિપન્ના તે યદા સ્થાસ્યતિ નિશ્ચલા,\nસમાધાવચલા બુધ્દિસ્તદા યોગમવાપ્સ્યસિ.(૫૩)");
        } else if (2 == i) {
            this.tv1.setText("Shrutivipratipannaa te yadaa sthaasyati nishchalaa;\nSamaadhaavachalaa buddhistadaa yogam avaapsyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("भाँति-भाँति के वचनों को सुनने से विचलित हुई तेरी बुद्धि जब परमात्मा में अचल और स्थिर ठहर जाएगी, तब तू योग को प्राप्त हो जाएगा अर्थात तेरा परमात्मा से नित्य संयोग हो जाएगा॥53॥");
        } else if (i2 == 0) {
            this.tv2.setText("અત્યારે વિવિધ ઉપદેશ સુણવાથી તારી મતિ ભ્રમિત થઈ છે. જ્યારે તે પરમાત્મામાં સ્થિર થઈ જશે ત્યારે તું પરમાત્માની સાથે સંયોગ (યોગ-સ્થિતિ) કરી શકશે.(૫૩)");
        } else if (2 == i2) {
            this.tv2.setText("When your mind is no longer disturbed by the flowery language of the Vedas, and when it remains fixed in the trance of self-realization, then you will have attained the divine consciousness.(53)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok54() {
        load();
        setTitle("Shlok 54");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nस्थितप्रज्ञस्य का भाषा समाधिस्थस्य केशव ।\nस्थितधीः किं प्रभाषेत किमासीत व्रजेत किम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nસ્થિતપ્રજ્ઞસ્ય કા ભાષા સમાધિસ્થસ્ય કેશવ,\nસ્થિતધીઃ કિં પ્રભાષેત કિમાસીત વ્રજેત કિમ્.(૫૪)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nSthitaprajnasya kaa bhaashaa samaadhisthasya keshava;\nSthitadheeh kim prabhaasheta kimaaseeta vrajeta kim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे केशव! समाधि में स्थित परमात्मा को प्राप्त हुए स्थिरबुद्धि पुरुष का क्या लक्षण है? वह स्थिरबुद्धि पुरुष कैसे बोलता है, कैसे बैठता है और कैसे चलता है?॥54॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે - હે કેશવ, જેમની બુદ્ધિ સમાધિમાં સ્થિર થઈ ચુકી છે એ પુરુષ કેવો હોય છે ? (એને કેવી રીતે ઓળખવો) એના કેવા લક્ષણો હોય છે ? એ કેવી રીતે પોતાનો જીવનવ્યવહાર કરે છે ? (૫૪)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O Krishna, what are the symptoms of one whose consciousness is thus merged in transcendence? How does he speak, and what is his language? How does he sit, and how does he walk?(54)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok55() {
        load();
        setTitle("Shlok 55");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nप्रजहाति यदा कामान्‌ सर्वान्पार्थ मनोगतान्‌ ।\nआत्मयेवात्मना तुष्टः स्थितप्रज्ञस्तदोच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ -\nપ્રજહાતિ યદા કામાન્ સર્વાન્ પાર્થ મનોગતાન્,\nઆત્મન્યેવાત્મના તુષ્ટઃ સ્થિતપ્રજ્ઞસ્તદોચ્યતે.(૫૫)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nPrajahaati yadaa kaamaan sarvaan paartha manogataan;\nAatmanyevaatmanaa tushtah sthitaprajnastadochyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  श्री भगवान्‌ बोले- हे अर्जुन! जिस काल में यह पुरुष मन में स्थित सम्पूर्ण कामनाओं को भलीभाँति त्याग देता है और आत्मा से आत्मा में ही संतुष्ट रहता है, उस काल में वह स्थितप्रज्ञ कहा जाता है॥55॥");
        } else if (i2 == 0) {
            this.tv2.setText("ભગવાન કહે છે -\nહે પાર્થ, જ્યારે વ્યક્તિ પોતાના મનમાં ઉઠતી બધી જ કામનાઓને ત્યાગી દે છે અને પોતાના આત્મામાં સ્થિતિ કરે છે ત્યારે તે સ્થિતપ્રજ્ઞ કહેવાય છે.(૫૫)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: O Partha, when a man gives up all varieties of desire for sense gratification, which arise from mental concoction, and when his mind, thus purified, finds satisfaction in the self alone, then he is said to be in pure transcendental consciousness.(55)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok56() {
        load();
        setTitle("Shlok 56");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दुःखेष्वनुद्विग्नमनाः सुखेषु विगतस्पृहः ।\nवीतरागभयक्रोधः स्थितधीर्मुनिरुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("દુઃખેષ્વનુદ્વિગ્નમનાઃ સુખેષુ વિગતસ્પૃહઃ,\nવીતરાગભયક્રોધઃ સ્થિતધીર્મુનિરુચ્યતે.(૫૬)");
        } else if (2 == i) {
            this.tv1.setText("Duhkheshwanudwignamanaah sukheshu vigatasprihah;\nVeetaraagabhayakrodhah sthitadheer munir uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" दुःखों की प्राप्ति होने पर जिसके मन में उद्वेग नहीं होता, सुखों की प्राप्ति में सर्वथा निःस्पृह है तथा जिसके राग, भय और क्रोध नष्ट हो गए हैं, ऐसा मुनि स्थिरबुद्धि कहा जाता है॥56॥");
        } else if (i2 == 0) {
            this.tv2.setText("સ્થિતપ્રજ્ઞ પુરુષનું મન ન તો દુઃખમાં વિચલિત થાય છે કે ન તો સુખની સ્પૃહા (ઈચ્છા-તૃષ્ણા) કરે છે. એનું મન રાગ, ભય અને ક્રોધથી મુક્ત થયેલું હોય છે. (૫૬)");
        } else if (2 == i2) {
            this.tv2.setText("One who is not disturbed in mind even amidst the threefold miseries or elated when there is happiness, and who is free from attachment, fear and anger, is called a sage of steady mind.(56)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok57() {
        load();
        setTitle("Shlok 57");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यः सर्वत्रानभिस्नेहस्तत्तत्प्राप्य शुभाशुभम्‌ ।\nनाभिनंदति न द्वेष्टि तस्य प्रज्ञा प्रतिष्ठिता ॥");
        } else if (i == 0) {
            this.tv1.setText("યઃ સર્વત્રાનભિસ્નેહસ્તત્તત્પ્રાપ્ય શુભાશુભમ્,\nનાભિનન્દતિ ન દ્વેષ્ટિ તસ્ય પ્રજ્ઞા પ્રતિષ્ઠિતા.(૫૭)");
        } else if (2 == i) {
            this.tv1.setText("Yah sarvatraanabhisnehas tattat praapya shubhaashubham;\nNaabhinandati na dweshti tasya prajnaa pratishthitaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो पुरुष सर्वत्र स्नेहरहित हुआ उस-उस शुभ या अशुभ वस्तु को प्राप्त होकर न प्रसन्न होता है और न द्वेष करता है, उसकी बुद्धि स्थिर है॥57॥");
        } else if (i2 == 0) {
            this.tv2.setText("સુખ કે દુઃખ - બંનેમાં તેની પ્રતિક્રિયા સમાન હોય છે. ગમતી-સારી વસ્તુ મળવાથી તે ન તો પ્રસન્ન (સુખી)  થાય છે કે-ન તો એના અભાવે (વસ્તુ ના મળે તો)  વિષાદગ્રસ્ત (દુઃખી). તેની બુદ્ધિ સ્થિર થયેલી હોય છે. (૫૭)");
        } else if (2 == i2) {
            this.tv2.setText("In the material world, one who is unaffected by whatever good or evil he may obtain, neither praising it nor despising it, is firmly fixed in perfect knowledge.(57)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok58() {
        load();
        setTitle("Shlok 58");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा संहरते चायं कूर्मोऽङ्गनीव सर्वशः ।\nइन्द्रियाणीन्द्रियार्थेभ्यस्तस्य प्रज्ञा प्रतिष्ठिता ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા સંહરતે ચાયં કૂર્મોઙ્ગાનીવ સર્વશઃ,\nઇન્દ્રિયાણીન્દ્રિયાર્થેભ્યસ્તસ્ય પ્રજ્ઞા પ્રતિષ્ઠિતા.(૫૮)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa samharate chaayam kurmo’ngaaneeva sarvashah;\nIndriyaaneendriyaarthebhyas tasya prajnaa pratishthitaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" और कछुवा सब ओर से अपने अंगों को जैसे समेट लेता है, वैसे ही जब यह पुरुष इन्द्रियों के विषयों से इन्द्रियों को सब प्रकार से हटा लेता है, तब उसकी बुद्धि स्थिर है (ऐसा समझना चाहिए)॥58॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે કાચબો પોતાના અંગોને અંદરની તરફ સંકેલી લે છે તેવી રીતે તે પોતાની ઈન્દ્રિયોને વિષયોમાંથી કાઢી આત્મામાં સ્થિર કરે છે. ત્યારે તેની બુદ્ધિ સ્થિર થાય છે. (૫૮)");
        } else if (2 == i2) {
            this.tv2.setText("One who is able to withdraw his senses from sense objects, as the tortoise draws its limbs within the shell, is firmly fixed in perfect consciousness.(58)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok59() {
        load();
        setTitle("Shlok 59");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("विषया विनिवर्तन्ते निराहारस्य देहिनः ।\nरसवर्जं रसोऽप्यस्य परं दृष्टवा निवर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("વિષયા વિનિવર્તન્તે નિરાહારસ્ય દેહિનઃ,\nરસવર્જં રસોપ્યસ્ય પરં દૃષ્ટ્વા નિવર્તતે.(૫૯)");
        } else if (2 == i) {
            this.tv1.setText("Vishayaa vinivartante niraahaarasya dehinah\nRasavarjam raso’pyasya param drishtwaa nivartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इन्द्रियों द्वारा विषयों को ग्रहण न करने वाले पुरुष के भी केवल विषय तो निवृत्त हो जाते हैं, परन्तु उनमें रहने वाली आसक्ति निवृत्त नहीं होती। इस स्थितप्रज्ञ पुरुष की तो आसक्ति भी परमात्मा का साक्षात्कार करके निवृत्त हो जाती है॥59॥");
        } else if (i2 == 0) {
            this.tv2.setText("જો વિષયોનો (ભોજન-વગેરેનો) ત્યાગ કેવળ બાહ્ય (બહાર નો ત્યાગ)  હોય તો એવા ત્યાગ કર્યા છતાં અંદરથી તેનો ઉપભોગ કરવાની ઇચ્છા યથાવત રહે છે. પરંતુ પરમાત્માનો સાક્ષાત્કાર થયા પછી એ પદાર્થોના ઉપભોગની ઇચ્છાનો પણ અંત આવે છે. (૫૯)");
        } else if (2 == i2) {
            this.tv2.setText("The embodied soul may be restricted from sense enjoyment, though the taste for sense objects remains. But, ceasing such engagements by experiencing a higher taste, he is fixed in consciousness.(59)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok60() {
        load();
        setTitle("Shlok 60");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यततो ह्यपि कौन्तेय पुरुषस्य विपश्चितः ।\nइन्द्रियाणि प्रमाथीनि हरन्ति प्रसभं मनः ॥");
        } else if (i == 0) {
            this.tv1.setText("યતતો હ્યપિ કૌન્તેય પુરુષસ્ય વિપશ્ચિતઃ,\nઇન્દ્રિયાણિ પ્રમાથીનિ હરન્તિ પ્રસભં મનઃ.(૬૦)");
        } else if (2 == i) {
            this.tv1.setText("Yatato hyapi kaunteya purushasya vipashchitah;\nIndriyaani pramaatheeni haranti prasabham manah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! आसक्ति का नाश न होने के कारण ये प्रमथन स्वभाव वाली इन्द्रियाँ यत्न करते हुए बुद्धिमान पुरुष के मन को भी बलात्‌ हर लेती हैं॥60॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કૌન્તેય, ઇન્દ્રિયો એટલી ચંચળ છે કે-સાવધાનીથી ઈન્દ્રિયોનો સંયમ કરી અભ્યાસ કરનાર વિદ્વાન મનુષ્ય ના મન ને પણ (પરાણે) ઈન્દ્રિયો હરી લે છે અને બળાત્કારે વિષયો તરફ ખેંચે છે. (૬૦)");
        } else if (2 == i2) {
            this.tv2.setText("The senses are so strong and impetuous, O Arjuna, that they forcibly carry away the mind even of a man of discrimination who is endeavoring to control them.(60)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok61() {
        load();
        setTitle("Shlok 61");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तानि सर्वाणि संयम्य युक्त आसीत मत्परः ।\nवशे हि यस्येन्द्रियाणि तस्य प्रज्ञा प्रतिष्ठिता ॥");
        } else if (i == 0) {
            this.tv1.setText("તાનિ સર્વાણિ સંયમ્ય યુક્ત આસીત મત્પરઃ,\nવશે હિ યસ્યેન્દ્રિયાણિ તસ્ય પ્રજ્ઞા પ્રતિષ્ઠિતા.(૬૧)");
        } else if (2 == i) {
            this.tv1.setText("Taani sarvaani samyamya yukta aaseeta matparah;\nVashe hi yasyendriyaani tasya prajnaa pratishthitaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इसलिए साधक को चाहिए कि वह उन सम्पूर्ण इन्द्रियों को वश में करके समाहित चित्त हुआ मेरे परायण होकर ध्यान में बैठे क्योंकि जिस पुरुष की इन्द्रियाँ वश में होती हैं, उसी की बुद्धि स्थिर हो जाती है॥61॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, એથી સાધકે પોતાની ઈન્દ્રિયોનો સંયમ કરી મારું (પરમાત્માનું) ધ્યાન કરવું જોઈએ. એમ કરવાથી ઈન્દ્રિયો વશમાં રહેશે અને મારામાં (પ્રભુમાં) મન-બુદ્ધિને સ્થિર કરી શકશે. (૬૧)");
        } else if (2 == i2) {
            this.tv2.setText("One who restrains his senses, keeping them under full control, and fixes his consciousness upon Me, is known as a man of steady intelligence.(61)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok62() {
        load();
        setTitle("Shlok 62");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ध्यायतो विषयान्पुंसः संगस्तेषूपजायते ।\nसंगात्संजायते कामः कामात्क्रोधोऽभिजायते ॥");
        } else if (i == 0) {
            this.tv1.setText("ધ્યાયતો વિષયાન્પુંસઃ સઙ્ગસ્તેષૂપજાયતે,\nસઙ્ગાત્ સંજાયતે કામઃ કામાત્ક્રોધોભિજાયતે.(૬૨)");
        } else if (2 == i) {
            this.tv1.setText("Dhyaayato vishayaan pumsah sangas teshupajaayate;\nSangaat sanjaayate kaamah kaamaat krodho’bhijaayate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" विषयों का चिन्तन करने वाले पुरुष की उन विषयों में आसक्ति हो जाती है, आसक्ति से उन विषयों की कामना उत्पन्न होती है और कामना में विघ्न पड़ने से क्रोध उत्पन्न होता है॥62॥");
        } else if (i2 == 0) {
            this.tv2.setText("વિષયોનું ચિંતન કરવાવાળા મનુષ્યનું મન એ પદાર્થોમાં આસક્ત થઈ જાય છે અને એની જ કામના કર્યા કરે છે. (ભોગ-પદાર્થો ની ઈચ્છા-કામના થઇ ને કામ નો જન્મ થાય છે) જ્યારે તે પદાર્થો નથી મળતા ત્યારે તે ક્રોધિત થઈ જાય છે. (કામના-કામ  માંથી ક્રોધ નો જન્મ) (૬૨)");
        } else if (2 == i2) {
            this.tv2.setText("While contemplating the objects of the senses, a person develops attachment for them, and from such attachment lust develops, and from lust anger arises.(62)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok63() {
        load();
        setTitle("Shlok 63");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("क्रोधाद्‍भवति सम्मोहः सम्मोहात्स्मृतिविभ्रमः ।\nस्मृतिभ्रंशाद् बुद्धिनाशो बुद्धिनाशात्प्रणश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("ક્રોધાદ્ભવતિ સંમોહઃ સંમોહાત્સ્મૃતિવિભ્રમઃ,\nસ્મૃતિભ્રંશાદ્ બુદ્ધિનાશો બુદ્ધિનાશાત્પ્રણશ્યતિ.(૬૩)");
        } else if (2 == i) {
            this.tv1.setText("Krodhaad bhavati sammohah sammohaat smriti vibhramah;\nSmritibhramshaad buddhinaasho buddhinaashaat pranashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" क्रोध से अत्यन्त मूढ़ भाव उत्पन्न हो जाता है, मूढ़ भाव से स्मृति में भ्रम हो जाता है, स्मृति में भ्रम हो जाने से बुद्धि अर्थात ज्ञानशक्ति का नाश हो जाता है और बुद्धि का नाश हो जाने से यह पुरुष अपनी स्थिति से गिर जाता है॥63॥");
        } else if (i2 == 0) {
            this.tv2.setText("ક્રોધ થવાથી એનું વિવેકભાન જતું રહે છે (ક્રોધ થી મૂર્ખતા નો જન્મ), એને સારા-નરસાનું ભાન રહેતું નથી અને એને સ્મૃતિભ્રમ થાય છે. (મૂર્ખતાથી સ્મૃતિનાશ થાય છે અને સ્મૃતિ નાશ થી  બુદ્ધિ-નાશ થાય છે) એવો ભ્રમિત ચિત્તવાળો (મન-બુદ્ધિ વાળો) મનુષ્ય પોતાનો સર્વનાશ નોંતરે છે. (૬૩)");
        } else if (2 == i2) {
            this.tv2.setText("From anger, complete delusion arises, and from delusion bewilderment of memory. When memory is bewildered, intelligence is lost, and when intelligence is lost one falls down again into the material pool.(63)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok64() {
        load();
        setTitle("Shlok 64");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रागद्वेषवियुक्तैस्तु विषयानिन्द्रियैश्चरन्‌ ।\nआत्मवश्यैर्विधेयात्मा प्रसादमधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("રાગદ્વેષવિયુક્તૈસ્તુ વિષયાનિન્દ્રિયૈશ્ચરન્,\nઆત્મવશ્યૈર્વિધેયાત્મા પ્રસાદમધિગચ્છતિ.(૬૪)");
        } else if (2 == i) {
            this.tv1.setText("Raagadwesha viyuktaistu vishayaanindriyaishcharan;\nAatmavashyair vidheyaatmaa prasaadamadhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" परंन्तु अपने अधीन किए हुए अन्तःकरण वाला साधक अपने वश में की हुई, राग-द्वेष रहित इन्द्रियों द्वारा विषयों में विचरण करता हुआ अन्तःकरण की प्रसन्नता को प्राप्त होता है॥64॥");
        } else if (i2 == 0) {
            this.tv2.setText("જ્યારે એથી ઉલટું, ઈન્દ્રિયોને રાગ અને દ્વેષથી મુક્ત કરી પોતાના વશમાં કરનાર મનુષ્યને અંતઃકરણની પ્રસન્નતા અને શાંતિની પ્રાપ્તિ થાય છે. (૬૪)");
        } else if (2 == i2) {
            this.tv2.setText("But a person free from all attachment and aversion and able to control his senses through regulative principles of freedom can obtain the complete mercy of the Lord.(64)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok65() {
        load();
        setTitle("Shlok 65");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रसादे सर्वदुःखानां हानिरस्योपजायते ।\nप्रसन्नचेतसो ह्याशु बुद्धिः पर्यवतिष्ठते ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રસાદે સર્વદુઃખાનાં હાનિરસ્યોપજાયતે,\nપ્રસન્નચેતસો હ્યાશુ બુદ્ધિઃ પર્યવતિષ્ઠતે.(૬૫)");
        } else if (2 == i) {
            this.tv1.setText("Prasaade sarvaduhkhaanaam haanir asyopajaayate;\nPrasannachetaso hyaashu buddhih paryavatishthate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अन्तःकरण की प्रसन्नता होने पर इसके सम्पूर्ण दुःखों का अभाव हो जाता है और उस प्रसन्नचित्त वाले कर्मयोगी की बुद्धि शीघ्र ही सब ओर से हटकर एक परमात्मा में ही भलीभाँति स्थिर हो जाती है॥65॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી ન કેવળ એના બધા દુઃખોનો અંત આવે છે પરંતુ પ્રસન્નચિત થયેલા એવા પુરુષ ની બુદ્ધિ, પરમાત્મામાં હંમેશ માટે સ્થિર બને છે.(૬૫)");
        } else if (2 == i2) {
            this.tv2.setText("For one thus satisfied [in Krishna consciousness], the threefold miseries of material existence exist no longer; in such satisfied consciousness, one’s intelligence is soon well established.(65)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok66() {
        load();
        setTitle("Shlok 66");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नास्ति बुद्धिरयुक्तस्य न चायुक्तस्य भावना ।\nन चाभावयतः शान्तिरशान्तस्य कुतः सुखम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("નાસ્તિ બુધ્દિરયુક્તસ્ય ન ચાયુક્તસ્ય ભાવના,\nન ચાભાવયતઃ શાન્તિરશાન્તસ્ય કુતઃ સુખમ્.(૬૬)");
        } else if (2 == i) {
            this.tv1.setText("Naasti buddhir ayuktasya na chaayuktasya bhaavanaa;\nNa chaabhaavayatah shaantir ashaantasya kutah sukham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" न जीते हुए मन और इन्द्रियों वाले पुरुष में निश्चयात्मिका बुद्धि नहीं होती और उस अयुक्त मनुष्य के अन्तःकरण में भावना भी नहीं होती तथा भावनाहीन मनुष्य को शान्ति नहीं मिलती और शान्तिरहित मनुष्य को सुख कैसे मिल सकता है?॥66॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેની ઈન્દ્રિયો સંયમિત નથી એની બુદ્ધિ સ્થિર રહી શકતી નથી અને એમ થવાથી એનામાં શાંતિ પેદા થતી નથી. એવો વ્યક્તિ શાંત કેવી રીતે બની શકે ? અને જે શાંત ન બને તેને વળી સુખ કેવી રીતે મળે ? (૬૬)");
        } else if (2 == i2) {
            this.tv2.setText("One who is not connected with the Supreme [in Krishna consciousness] can have neither transcendental intelligence nor a steady mind, without which there is no possibility of peace. And how can there be any happiness without peace?(66)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok67() {
        load();
        setTitle("Shlok 67");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इन्द्रियाणां हि चरतां यन्मनोऽनुविधीयते ।\nतदस्य हरति प्रज्ञां वायुर्नावमिवाम्भसि ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇન્દ્રિયાણાં હિ ચરતાં યન્મનોનુવિધીયતે,\nતદસ્ય હરતિ પ્રજ્ઞાં વાયુર્નાવમિવામ્ભસિ.(૬૭)");
        } else if (2 == i) {
            this.tv1.setText("Indriyaanaam hi charataam yanmano’nuvidheeyate;\nTadasya harati prajnaam vaayur naavam ivaambhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" क्योंकि जैसे जल में चलने वाली नाव को वायु हर लेती है, वैसे ही विषयों में विचरती हुई इन्द्रियों में से मन जिस इन्द्रिय के साथ रहता है, वह एक ही इन्द्रिय इस अयुक्त पुरुष की बुद्धि को हर लेती है॥67॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે નૌકાને હવા ખેંચી જાય છે એવી રીતે ભટકતી ઈન્દ્રિયો તેના મનને ખેંચી જાય છે. એની બુદ્ધિનું હરણ કરી લે છે. (૬૭)");
        } else if (2 == i2) {
            this.tv2.setText("As a strong wind sweeps away a boat on the water, even one of the roaming senses on which the mind focuses can carry away a man’s intelligence.(67)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok68() {
        load();
        setTitle("Shlok 68");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्माद्यस्य महाबाहो निगृहीतानि सर्वशः ।\nइन्द्रियाणीन्द्रियार्थेभ्यस्तस्य प्रज्ञा प्रतिष्ठिता ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માદ્યસ્ય મહાબાહો નિગૃહીતાનિ સર્વશઃ,\nઇન્દ્રિયાણીન્દ્રિયાર્થેભ્યસ્તસ્ય પ્રજ્ઞા પ્રતિષ્ઠિતા.(૬૮)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaad yasya mahaabaaho nigriheetaani sarvashah;\nIndriyaaneendriyaarthebhyas tasya prajnaa pratishthitaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इसलिए हे महाबाहो! जिस पुरुष की इन्द्रियाँ इन्द्रियों के विषयों में सब प्रकार निग्रह की हुई हैं, उसी की बुद्धि स्थिर है॥68॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે મહાબાહો, જેની ઈન્દ્રિયો વિષયોમાંથી નિગ્રહ પામી છે, એમની જ બુદ્ધિ સ્થિર રહે છે. (૬૮)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, O mighty-armed, one whose senses are restrained from their objects is certainly of steady intelligence.(68)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok69() {
        load();
        setTitle("Shlok 69");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("या निशा सर्वभूतानां तस्यां जागर्ति संयमी ।\nयस्यां जाग्रति भूतानि सा निशा पश्यतो मुनेः ॥");
        } else if (i == 0) {
            this.tv1.setText("યા નિશા સર્વભૂતાનાં તસ્યાં જાગર્તિ સંયમી,\nયસ્યાં જાગ્રતિ ભૂતાનિ સા નિશા પશ્યતો મુનેઃ.(૬૯)");
        } else if (2 == i) {
            this.tv1.setText("Yaanishaa sarvabhootaanaam tasyaam jaagarti samyamee;\nYasyaam jaagrati bhootaani saa nishaa pashyato muneh.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" सम्पूर्ण प्राणियों के लिए जो रात्रि के समान है, उस नित्य ज्ञानस्वरूप परमानन्द की प्राप्ति में स्थितप्रज्ञ योगी जागता है और जिस नाशवान सांसारिक सुख की प्राप्ति में सब प्राणी जागते हैं, परमात्मा के तत्व को जानने वाले मुनि के लिए वह रात्रि के समान है॥69॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંસારના ભોગોપભોગો માટે સામાન્ય મનુષ્યો પ્રવૃત્તિ કરતા દેખાય છે ત્યારે મુનિ એ માટે તદ્દન નિષ્ક્રિય રહે છે. (અર્થાત્ જે લોકો માટે દિવસ છે તે એને માટે રાત્રિ - નિષ્ક્રિય રહેવાનો સમય છે). એવી જ રીતે જે લોકો માટે રાત્રિ છે તે મુનિ માટે દિવસ છે (અર્થાત્ જેને માટે સામાન્ય મનુષ્યો પ્રયત્ન નથી કરતા તે પરમાત્માની પ્રાપ્તિ માટે મુનિ પ્રયત્ન કરે છે).(૬૯)");
        } else if (2 == i2) {
            this.tv2.setText("What is night for all beings is the time of awakening for the self-controlled; and the time of awakening for all beings is night for the introspective sage.(69)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok70() {
        load();
        setTitle("Shlok 70");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आपूर्यमाणमचलप्रतिष्ठं-\nसमुद्रमापः प्रविशन्ति यद्वत्‌ ।\nतद्वत्कामा यं प्रविशन्ति सर्वे\nस शान्तिमाप्नोति न कामकामी ॥");
        } else if (i == 0) {
            this.tv1.setText("આપૂર્યમાણમચલપ્રતિષ્ઠં \nસમુદ્રમાપઃ પ્રવિશન્તિ યદ્વત્,\nતદ્વત્કામા યં પ્રવિશન્તિ સર્વે \nસ શાન્તિમાપ્નોતિ ન કામકામી.(૭૦)");
        } else if (2 == i) {
            this.tv1.setText("Aapooryamaanam achalapratishtham\nSamudram aapah pravishanti yadwat;\nTadwat kaamaa yam pravishanti sarve\nSa shaantim aapnoti na kaamakaami.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जैसे नाना नदियों के जल सब ओर से परिपूर्ण, अचल प्रतिष्ठा वाले समुद्र में उसको विचलित न करते हुए ही समा जाते हैं, वैसे ही सब भोग जिस स्थितप्रज्ञ पुरुष में किसी प्रकार का विकार उत्पन्न किए बिना ही समा जाते हैं, वही पुरुष परम शान्ति को प्राप्त होता है, भोगों को चाहने वाला नहीं॥70॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે સરિતાનું જળ સમુદ્રને અશાંત કર્યા સિવાય સમાઈ જાય છે તેવી જ રીતે સ્થિતપ્રજ્ઞ પુરુષમાં ઉત્પન્ન થતી વૃત્તિઓ કોઈ વિકાર પેદા કર્યા વિના શાંત થઈ જાય છે. (એને વૃત્તિઓ ચલિત નથી કરતી). એવો પુરુષ પરમ શાંતિને પ્રાપ્ત કરે છે. નહીં કે સામાન્ય મનુષ્ય કે જે વૃતિઓ પાછળ ભાગતો ફરે છે.(૭૦)");
        } else if (2 == i2) {
            this.tv2.setText("A person who is not disturbed by the incessant flow of desires—that enter like rivers into the ocean, which is ever being filled but is always still—can alone achieve peace, and not the man who strives to satisfy such desires.(70)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok71() {
        load();
        setTitle("Shlok 71");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("विहाय कामान्यः सर्वान्पुमांश्चरति निःस्पृहः ।\nनिर्ममो निरहंकारः स शान्तिमधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("વિહાય કામાન્યઃ સર્વાન્પુમાંશ્ચરતિ નિઃસ્પૃહઃ,\nનિર્મમો નિરહંકારઃ સ શાંતિમધિગચ્છતિ.(૭૧)");
        } else if (2 == i) {
            this.tv1.setText("Vihaaya kaamaan yah sarvaan pumaamshcharati nihsprihah;\nNirmamo nirahankaarah sa shaantim adhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो पुरुष सम्पूर्ण कामनाओं को त्याग कर ममतारहित, अहंकाररहित और स्पृहारहित हुआ विचरता है, वही शांति को प्राप्त होता है अर्थात वह शान्ति को प्राप्त है॥71॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે અર્જુન, બધી જ કામનાઓનો ત્યાગ કર. જે મનુષ્ય મમતા, અહંકાર અને બધી જ ઈચ્છાઓથી મુક્ત થઈ જાય છે તે પરમ શાંતિને પામી લે છે. હે અર્જુન, એવો મનુષ્ય બ્રહ્મમાં સ્થિતિ કરે છે. (૭૧)");
        } else if (2 == i2) {
            this.tv2.setText("A person who has given up all desires for sense gratification, who lives free from desires, who has given up all sense of proprietorship and is devoid of false ego—he alone can attain real peace.(71)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok72() {
        load();
        setTitle("Shlok 72");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एषा ब्राह्मी स्थितिः पार्थ नैनां प्राप्य विमुह्यति ।\nस्थित्वास्यामन्तकालेऽपि ब्रह्मनिर्वाणमृच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("એષા બ્રાહ્મી સ્થિતિઃ પાર્થ નૈનાં પ્રાપ્ય વિમુહ્યતિ,\nસ્થિત્વાસ્યામન્તકાલેપિ બ્રહ્મનિર્વાણમૃચ્છતિ.(૭૨)");
        } else if (2 == i) {
            this.tv1.setText("Eshaa braahmee sthitih paartha nainaam praapya vimuhyati;\nSthitwaasyaamantakaale’pi brahmanirvaanamricchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! यह ब्रह्म को प्राप्त हुए पुरुष की स्थिति है, इसको प्राप्त होकर योगी कभी मोहित नहीं होता और अंतकाल में भी इस ब्राह्मी स्थिति में स्थित होकर ब्रह्मानन्द को प्राप्त हो जाता है॥72॥");
        } else if (i2 == 0) {
            this.tv2.setText("એવી બ્રાહ્મી સ્થિતિને પ્રાપ્ત કર્યા પછી એ સંસારના ભોગપદાર્થોથી કદી મોહિત નથી થતો અને અંત સમયે ઉત્તમ ગતિને પ્રાપ્ત કરીને મુક્તિને પામે છે.(૭૨)");
        } else if (2 == i2) {
            this.tv2.setText("That is the way of the spiritual and godly life, after attaining which a man is not bewildered. If one is thus situated even at the hour of death, one can enter into the kingdom of God.(72)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay2.this, ShlokListPlay2.this);
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

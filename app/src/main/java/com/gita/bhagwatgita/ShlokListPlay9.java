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

public class ShlokListPlay9 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay9.this.favflag == 0) {
                    ShlokListPlay9.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.favflag = 1;
                    shlokListPlay9.save();
                } else if (1 == ShlokListPlay9.this.favflag) {
                    ShlokListPlay9.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay9 shlokListPlay92 = ShlokListPlay9.this;
                    shlokListPlay92.favflag = 0;
                    shlokListPlay92.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay9.this.calll++;
                ShlokListPlay9.this.slkset();
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
                ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                shlokListPlay9.calll--;
                ShlokListPlay9.this.slkset();
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
        edit.putString("A9" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A9" + this.calll, "0");
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
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.callf1 = 0;
                    shlokListPlay9.slkset();
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
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.callf1 = 2;
                    shlokListPlay9.slkset();
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
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.callf1 = 1;
                    shlokListPlay9.slkset();
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
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.callf2 = 0;
                    shlokListPlay9.slkset();
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
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.callf2 = 2;
                    shlokListPlay9.slkset();
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
                    ShlokListPlay9 shlokListPlay9 = ShlokListPlay9.this;
                    shlokListPlay9.callf2 = 1;
                    shlokListPlay9.slkset();
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
        setTitle("Adhyay 9");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ०९ \nराजविद्याराजगुह्ययोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૯ \nરાજવિદ્યા રાજ ગુહ્ય યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 09\nrajavidhya raj gruhya yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, भगवान श्रीकृष्ण परमेश्वर हैं ओर पूज्य हैं। भक्ति के माध्यम से जीव उनसे शाश्वत सम्बन्ध है। शुद्ध भक्ति को जागृत करके मनुष्य कृष्ण के धाम को वापस जाता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૯ માં અત્યંત ગુઢ માં ગુઢ જ્ઞાન નું વર્ણન છે. પરમાત્મા નું અવ્યક્ત સ્વરૂપ છે, અને સકળ વિશ્વ તેનાથી વ્યાપ્ત છે. એનામાં સર્વ જીવો રહેલાં છે, પણ તેમનામાં એ સ્થિત નથી. જે રીતે સર્વ ગામી વાયુ આકાશ માં રહેલો છે, તેવી રીતે સર્વ જીવો તેના માં રહેલાં છે. પ્રકૃતિ નો આશ્રય લઇ કલ્પ ના અંતે તે જીવોને ફરી પેદા કરે છે. દૈવી અને અસુરી પ્રકૃતિ ના મનુષ્યો નું વર્ણન છે.");
        } else if (2 == i) {
            this.tv1.setText("Ninth adhyay tells, Lord Krishna is the Supreme Godhead and the supreme object of worship. The soul is eternally related to Him through transcendental devotional service (bhakti). By reviving one's pure devotion one returns to Krishna in the spiritual realm. ");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nइदं तु ते गुह्यतमं प्रवक्ष्याम्यनसूयवे ।\nज्ञानं विज्ञानसहितं यज्ज्ञात्वा मोक्ष्यसेऽशुभात्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઇદં તુ તે ગુહ્યતમં પ્રવક્ષ્યામ્યનસૂયવે,\nજ્ઞાનં વિજ્ઞાનસહિતં યજ્જ્ઞાત્વા મોક્ષ્યસેશુભાત્.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nIdam tu te guhyatamam pravakshyaamyanasooyave;\nJnaanam vijnaanasahitam yajjnaatwaa mokshyase’shubhaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- तुझ दोषदृष्टिरहित भक्त के लिए इस परम गोपनीय विज्ञान सहित ज्ञान को पुनः भली भाँति कहूँगा, जिसको जानकर तू दुःखरूप संसार से मुक्त हो जाएगा॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન બોલ્યા - હે અર્જુન ! જે જાણવાથી તું આ અશુભ સંસારથી મુક્ત થઈશ. એવું અત્યંત ગુહ્ય જ્ઞાન છે તે તારા જેવા નિર્મળને હું વિજ્ઞાન સહીત કહી સંભળાવું છું.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: My dear Arjuna, because you are never envious of Me, I shall impart to you this most confidential knowledge and realization, knowing which you shall be relieved of the miseries of material existence.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("राजविद्या राजगुह्यं पवित्रमिदमुत्तमम्‌ ।\nप्रत्यक्षावगमं धर्म्यं सुसुखं कर्तुमव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("રાજવિદ્યા રાજગુહ્યં પવિત્રમિદમુત્તમમ્,\nપ્રત્યક્ષાવગમં ધર્મ્યં સુસુખં કર્તુમવ્યયમ્.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Raajavidyaa raajaguhyam pavitramidamuttamam;\nPratyakshaavagamam dharmyam susukham kartumavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यह विज्ञान सहित ज्ञान सब विद्याओं का राजा, सब गोपनीयों का राजा, अति पवित्र, अति उत्तम, प्रत्यक्ष फलवाला, धर्मयुक्त, साधन करने में बड़ा सुगम और अविनाशी है॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ જ્ઞાન સર્વ વિદ્યાઓનો રાજા છે, સર્વ ગુહ્યમાં શ્રેષ્ઠ છે, પવિત્ર છે, ઉત્તમ છે, પ્રત્યક્ષ અનુભવમાં લેવાય એવું છે,ધર્માનુસાર છે,સુખપૂર્વક પ્રાપ્ત થનારું અને અવિનાશી છે.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("This knowledge is the king of education, the most secret of all secrets. It is the purest knowledge, and because it gives direct perception of the self by realization, it is the perfection of religion. It is everlasting, and it is joyfully performed.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अश्रद्दधानाः पुरुषा धर्मस्यास्य परन्तप ।\nअप्राप्य मां निवर्तन्ते मृत्युसंसारवर्त्मनि ॥");
        } else if (i == 0) {
            this.tv1.setText("અશ્રદ્દધાનાઃ પુરુષા ધર્મસ્યાસ્ય પરન્તપ,\nઅપ્રાપ્ય માં નિવર્તન્તે મૃત્યુસંસારવર્ત્મનિ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Ashraddhadhaanaah purushaa dharmasyaasya parantapa;\nApraapya maam nivartante mrityusamsaaravartmani.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे परंतप! इस उपयुक्त धर्म में श्रद्धारहित पुरुष मुझको न प्राप्त होकर मृत्युरूप संसार चक्र में भ्रमण करते रहते हैं॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરંતપ ! ધર્મમાં શ્રદ્ધા ન રાખનારા પુરુષો મારી પ્રાપ્તિ ન થવાથી મૃત્યુયુક્ત સંસારના માર્ગમાં જ ભમ્યા કરે છે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are not faithful in this devotional service cannot attain Me, O conqueror of enemies. Therefore they return to the path of birth and death in this material world.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मया ततमिदं सर्वं जगदव्यक्तमूर्तिना ।\nमत्स्थानि सर्वभूतानि न चाहं तेषवस्थितः ॥");
        } else if (i == 0) {
            this.tv1.setText("મયા તતમિદં સર્વં જગદવ્યક્તમૂર્તિના,\nમત્સ્થાનિ સર્વભૂતાનિ ન ચાહં તેષ્વવસ્થિતઃ.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Mayaa tatamidam sarvam jagadavyaktamoortinaa;\nMatsthaani sarvabhootaani na chaaham teshvavasthitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मुझ निराकार परमात्मा से यह सब जगत्‌ जल से बर्फ के सदृश परिपूर्ण है और सब भूत मेरे अंतर्गत संकल्प के आधार स्थित हैं, किंतु वास्तव में मैं उनमें स्थित नहीं हूँ॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું અવ્યક્તરૂપ છું, સકળ જગત મારાથી વ્યાપ્ત છે. મારામાં સર્વ ભૂતો સ્થિત છે, પરંતુ હું તેમનામાં સ્થિત નથી.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("By Me, in My unmanifested form, this entire universe is pervaded. All beings are in Me, but I am not in them.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        load();
        setTitle("Shlok 5");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न च मत्स्थानि भूतानि पश्य मे योगमैश्वरम्‌ ।\nभूतभृन्न च भूतस्थो ममात्मा भूतभावनः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન ચ મત્સ્થાનિ ભૂતાનિ પશ્ય મે યોગમૈશ્વરમ્,\nભૂતભૃન્ન ચ ભૂતસ્થો મમાત્મા ભૂતભાવનઃ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Na cha matsthaani bhootaani pashya me yogamaishwaram;\nBhootabhrinna cha bhootastho mamaatmaa bhootabhaavanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे सब भूत मुझमें स्थित नहीं हैं, किंतु मेरी ईश्वरीय योगशक्ति को देख कि भूतों का धारण-पोषण करने वाला और भूतों को उत्पन्न करने वाला भी मेरा आत्मा वास्तव में भूतों में स्थित नहीं है॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("ભૂતો મારામાં નથી, એવી મારી ઈશ્વરી અદભૂત ઘટના જો.  હું ભૂતોને ધારણ કરુંછું છતાં ભૂતોમાં હું રહેતો નથી. મારો આત્મા ભૂતોની ઉત્પતિ અને સંરક્ષણ કરનારો છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("And yet everything that is created does not rest in Me. Behold My mystic opulence! Although I am the maintainer of all living entities and although I am everywhere, I am not a part of this cosmic manifestation, for My Self is the very source of creation.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        load();
        setTitle("Shlok 6");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथाकाशस्थितो नित्यं वायुः सर्वत्रगो महान्‌ ।\nतथा सर्वाणि भूतानि मत्स्थानीत्युपधारय ॥");
        } else if (i == 0) {
            this.tv1.setText("યથાકાશસ્થિતો નિત્યં વાયુઃ સર્વત્રગો મહાન્,\nતથા સર્વાણિ ભૂતાનિ મત્સ્થાનીત્યુપધારય.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Yathaakaashasthito nityam vaayuh sarvatrago mahaan;\nTathaa sarvaani bhootaani matsthaaneetyupadhaaraya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जैसे आकाश से उत्पन्न सर्वत्र विचरने वाला महान्‌ वायु सदा आकाश में ही स्थित है, वैसे ही मेरे संकल्प द्वारा उत्पन्न होने से संपूर्ण भूत मुझमें स्थित हैं, ऐसा जान॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે સર્વત્ર વિચરનાર પ્રચંડ વાયુ કાયમ આકાશ માં જ હોય છે, તેમ સર્વ ભૂતો મારામાં સ્થિત છે એમ તું માન.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("Understand that as the mighty wind, blowing everywhere, rests always in the sky, all created beings rest in Me.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        load();
        setTitle("Shlok 7");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वभूतानि कौन्तेय प्रकृतिं यान्ति मामिकाम्‌ ।\nकल्पक्षये पुनस्तानि कल्पादौ विसृजाम्यहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વભૂતાનિ કૌન્તેય પ્રકૃતિં યાન્તિ મામિકામ્,\nકલ્પક્ષયે પુનસ્તાનિ કલ્પાદૌ વિસૃજામ્યહમ્.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Sarvabhootaani kaunteya prakritim yaanti maamikaam;\nKalpakshaye punastaani kalpaadau visrijaamyaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! कल्पों के अन्त में सब भूत मेरी प्रकृति को प्राप्त होते हैं अर्थात्‌ प्रकृति में लीन होते हैं और कल्पों के आदि में उनको मैं फिर रचता हूँ॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાંતેય ! સર્વ ભૂતો કલ્પ ના  અંતે મારી પ્રકૃતિમાં જ લીન થાય છે અને કલ્પ ના આરંભમાં ફરી હું જ એને ઉત્પન કરું છું.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Kunti, at the end of the millennium all material manifestations enter into My nature, and at the beginning of another millennium, by My potency, I create them again.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        load();
        setTitle("Shlok 8");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रकृतिं स्वामवष्टभ्य विसृजामि पुनः पुनः ।\nभूतग्राममिमं कृत्स्नमवशं प्रकृतेर्वशात्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રકૃતિં સ્વામવષ્ટભ્ય વિસૃજામિ પુનઃ પુનઃ,\nભૂતગ્રામમિમં કૃત્સ્નમવશં પ્રકૃતેર્વશાત્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Prakritim swaamavashtabhya visrijaami punah punah;\nBhootagraamamimam kritsnamavasham prakritervashaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अपनी प्रकृति को अंगीकार करके स्वभाव के बल से परतंत्र हुए इस संपूर्ण भूतसमुदाय को बार-बार उनके कर्मों के अनुसार रचता हूँ॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ પ્રમાણે હું મારી પોતાની પ્રકૃતિનો આશ્રય કરીને સ્વભાવથી પરતંત્ર એવા આ ભૂત સમુદાયને ફરી ફરી લીન કરું છું અને ઉત્પન કરું છું.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("The whole cosmic order is under Me. Under My will it is automatically manifested again and again, and under My will it is annihilated at the end.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        load();
        setTitle("Shlok 9");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न च मां तानि कर्माणि निबध्नन्ति धनञ्जय।\nउदासीनवदासीनमसक्तं तेषु कर्मसु ॥");
        } else if (i == 0) {
            this.tv1.setText("ન ચ માં તાનિ કર્માણિ નિબધ્નન્તિ ધનઞ્જય,\nઉદાસીનવદાસીનમસક્તં તેષુ કર્મસુ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Na cha maam taani karmaani nibadhnanti dhananjaya;\nUdaaseenavadaaseenam asaktam teshu karmasu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! उन कर्मों में आसक्तिरहित और उदासीन के सदृश (जिसके संपूर्ण कार्य कर्तृत्व भाव के बिना अपने आप सत्ता मात्र ही होते हैं उसका नाम 'उदासीन के सदृश' है।) स्थित मुझ परमात्मा को वे कर्म नहीं बाँधते॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ધનંજય ! કર્મો  પ્રત્યે ઉદાસીન પુરુષ પ્રમાણે આસક્તિ વગરના રહેલા મને તે કર્મો બંધન કરતાં નથી.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("O Dhananjaya, all this work cannot bind Me. I am ever detached from all these material activities, seated as though neutral.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        load();
        setTitle("Shlok 10");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मयाध्यक्षेण प्रकृतिः सूयते सचराचरं ।\nहेतुनानेन कौन्तेय जगद्विपरिवर्तते ॥");
        } else if (i == 0) {
            this.tv1.setText("મયાધ્યક્ષેણ પ્રકૃતિઃ સૂયતે સચરાચરમ્,\nહેતુનાનેન કૌન્તેય જગદ્વિપરિવર્તતે.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Mayaa’dhyakshena prakritih sooyate sacharaacharam;\nHetunaa’nena kaunteya jagadwiparivartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! मुझ अधिष्ठाता के सकाश से प्रकृति चराचर सहित सर्वजगत को रचती है और इस हेतु से ही यह संसारचक्र घूम रहा है॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાંતેય ! મારી અધ્યક્ષતાથી આ ત્રિગુણાત્મક પ્રકૃતિ આ ચરાચર જગતને ઉત્પન કરે  છે. એજ કારણ થી વિશ્વ ફરતું રહે છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("This material nature, which is one of My energies, is working under My direction, O son of Kunti, producing all moving and nonmoving beings. Under its rule this manifestation is created and annihilated again and again.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        load();
        setTitle("Shlok 11");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अवजानन्ति मां मूढा मानुषीं तनुमाश्रितम्‌।\nपरं भावमजानन्तो मम भूतमहेश्वरम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અવજાનન્તિ માં મૂઢા માનુષીં તનુમાશ્રિતમ્,\nપરં ભાવમજાનન્તો મમ ભૂતમહેશ્વરમ્.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Avajaananti maam moodhaah maanusheem tanumaashritam;\nParam bhaavamajaananto mama bhootamaheshwaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मेरे परमभाव को (गीता अध्याय 7 श्लोक 24 में देखना चाहिए) न जानने वाले मूढ़ लोग मनुष्य का शरीर धारण करने वाले मुझ संपूर्ण भूतों के महान्‌ ईश्वर को तुच्छ समझते हैं अर्थात्‌ अपनी योग माया से संसार के उद्धार के लिए मनुष्य रूप में विचरते हुए मुझ परमेश्वर को साधारण मनुष्य मानते हैं॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("મેં મનુષ્ય દેહ ધારણ કરેલો છે. તેથી મૂઢ મનુષ્યો મારી અવજ્ઞા કરે છે. હું સર્વ ભૂતોનો ઈશ્વર છું એવું જે મારું ઉત્કૃષ્ટ સ્વરૂપ છે તેનું જ્ઞાન તેમને હોતું નથી.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("Fools deride Me when I descend in the human form. They do not know My transcendental nature as the Supreme Lord of all that be(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        load();
        setTitle("Shlok 12");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मोघाशा मोघकर्माणो मोघज्ञाना विचेतसः ।\nराक्षसीमासुरीं चैव प्रकृतिं मोहिनीं श्रिताः ॥");
        } else if (i == 0) {
            this.tv1.setText("મોઘાશા મોઘકર્માણો મોઘજ્ઞાના વિચેતસઃ\nરાક્ષસીમાસુરીં ચૈવ પ્રકૃતિં મોહિનીં શ્રિતાઃ.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Moghaashaa moghakarmaano moghajnaanaa vichetasah;\nRaakshaseemaasureem chaiva prakritim mohineem shritaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे व्यर्थ आशा, व्यर्थ कर्म और व्यर्थ ज्ञान वाले विक्षिप्तचित्त अज्ञानीजन राक्षसी, आसुरी और मोहिनी प्रकृति को (जिसको आसुरी संपदा के नाम से विस्तारपूर्वक भगवान ने गीता अध्याय 16 श्लोक 4 तथा श्लोक 7 से 21 तक में कहा है) ही धारण किए रहते हैं॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે અજ્ઞાનીઓની આશા ,કર્મો અને જ્ઞાન – સર્વ વ્યર્થ જ છે. તેઓ વિચારશૂન્ય થઇ જાય છે અને મોહમાં બાંધનારા રાક્ષસી તથા આસુરી સ્વભાવનો જ આશ્રય કરે છે.(૧૨) ");
        } else if (2 == i2) {
            this.tv2.setText("Those who are thus bewildered are attracted by demonic and atheistic views. In that deluded condition, their hopes for liberation, their fruitive activities, and their culture of knowledge are all defeated.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        load();
        setTitle("Shlok 13");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("महात्मानस्तु मां पार्थ दैवीं प्रकृतिमाश्रिताः ।\nभजन्त्यनन्यमनसो ज्ञात्वा भूतादिमव्यम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("મહાત્માનસ્તુ માં પાર્થ દૈવીં પ્રકૃતિમાશ્રિતાઃ,\nભજન્ત્યનન્યમનસો જ્ઞાત્વા ભૂતાદિમવ્યયમ્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Mahaatmaanastu maam paartha daiveem prakritimaashritaah;\nBhajantyananyamanaso jnaatwaa bhootaadimavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परंतु हे कुन्तीपुत्र! दैवी प्रकृति के (इसका विस्तारपूर्वक वर्णन गीता अध्याय 16 श्लोक 1 से 3 तक में देखना चाहिए) आश्रित महात्माजन मुझको सब भूतों का सनातन कारण और नाशरहित अक्षरस्वरूप जानकर अनन्य मन से युक्त होकर निरंतर भजते हैं॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ  !  જેમણે દૈવી પ્રકૃતિનો આશ્રય કર્યો છે એવા એકનિષ્ઠ મહાત્માઓ જાણે જ છે કે હું ભૂતોનો આદિ અને અવિનાશી છું. તેઓ એમ સમજીને જ મને ભજે છે.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, those who are not deluded, the great souls, are under the protection of the divine nature. They are fully engaged in devotional service because they know Me as the Supreme Personality of Godhead, original and inexhaustible.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        load();
        setTitle("Shlok 14");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सततं कीर्तयन्तो मां यतन्तश्च दृढ़व्रताः ।\nनमस्यन्तश्च मां भक्त्या नित्ययुक्ता उपासते ॥");
        } else if (i == 0) {
            this.tv1.setText("સતતં કીર્તયન્તો માં યતન્તશ્ચ દૃઢવ્રતાઃ,\nનમસ્યન્તશ્ચ માં ભક્ત્યા નિત્યયુક્તા ઉપાસતે.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Satatam keertayanto maam yatantashcha dridhavrataah;\nNamasyantashcha maam bhaktyaa nityayuktaa upaasate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे दृढ़ निश्चय वाले भक्तजन निरंतर मेरे नाम और गुणों का कीर्तन करते हुए तथा मेरी प्राप्ति के लिए यत्न करते हुए और मुझको बार-बार प्रणाम करते हुए सदा मेरे ध्यान में युक्त होकर अनन्य प्रेम से मेरी उपासना करते हैं॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("નિત્ય ભક્તિપૂર્વક શમાદિ વ્રતોને દઢતાપૂર્વક પાળી તે મહાત્માઓ , નિરંતર મારું કીર્તન કરી તથા ઇન્દ્રિય દમન  અને નમસ્કાર કરતાં મારી જ ઉપાસના કરે છે.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("Always chanting My glories, endeavoring with great determination, bowing down before Me, these great souls perpetually worship Me with devotion.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        load();
        setTitle("Shlok 15");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञानयज्ञेन चाप्यन्ते यजन्तो मामुपासते।\nएकत्वेन पृथक्त्वेन बहुधा विश्वतोमुखम्।।");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞાનયજ્ઞેન ચાપ્યન્યે યજન્તો મામુપાસતે,\nએકત્વેન પૃથક્ત્વેન બહુધા વિશ્વતોમુખમ્.(૧૫)\n");
        } else if (2 == i) {
            this.tv1.setText("Jnaanayajnena chaapyanye yajanto maamupaasate;\nEkatwena prithaktwena bahudhaa vishwatomukham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("दूसरे ज्ञानयोगी मुझ निर्गुण-निराकार ब्रह्म का ज्ञानयज्ञ द्वारा अभिन्नभाव से पूजन करते हुए भी मेरी उपासना करते हैं और दूसरे मनुष्य बहुत प्रकार से स्थित मुझ विराट स्वरूप परमेश्वर की पृथक भाव से उपासना करते हैं।।15।।");
        } else if (i2 == 0) {
            this.tv2.setText("જ્ઞાનયજ્ઞથી પૂજનારા કેટલાક મનુષ્યો મારી ઉપાસના કરે છે.અને વિશ્વતોમુખે રહેલા કેટલાક મનુષ્યો મારી એકરૂપથી, ભિન્ન ભિન્ન રૂપથી મારી ઉપાસના કરે છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Others, who engage in sacrifice by the cultivation of knowledge, worship the Supreme Lord as the one without a second, as diverse in many, and in the universal form.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        load();
        setTitle("Shlok 16");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहं क्रतुरहं यज्ञः स्वधाहमहमौषधम्‌ ।\nमंत्रोऽहमहमेवाज्यमहमग्निरहं हुतम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અહં ક્રતુરહં યજ્ઞઃ સ્વધાહમહમૌષધમ્,\nમંત્રોહમહમેવાજ્યમહમગ્નિરહં હુતમ્.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Aham kraturaham yajnah swadhaa’hamahamaushadham;\nMantro’hamahamevaajyam ahamagniraham hutam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्रतु मैं हूँ, यज्ञ मैं हूँ, स्वधा मैं हूँ, औषधि मैं हूँ, मंत्र मैं हूँ, घृत मैं हूँ, अग्नि मैं हूँ और हवनरूप क्रिया भी मैं ही हूँ॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("અગ્નિહોત્ર આદિ શ્રોતયજ્ઞ, વૈશ્વદેવાદિક સ્માર્તયજ્ઞ, પિતૃઓને અર્પણ થતું  “ સ્વધા” અન્ન, ઔષધ, મંત્ર, હુત્દ્રવ્ય, અગ્નિ અને હવનકર્મ હું જ છું.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("But it is I who am the ritual, I the sacrifice, the offering to the ancestors, the healing herb, the transcendental chant. I am the butter and the fire and the offering.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        load();
        setTitle("Shlok 17");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पिताहमस्य जगतो माता धाता पितामहः ।\nवेद्यं पवित्रमोङ्कार ऋक्साम यजुरेव च ॥");
        } else if (i == 0) {
            this.tv1.setText("પિતાહમસ્ય જગતો માતા ધાતા પિતામહઃ,\nવેદ્યં પવિત્રમોંકાર ઋક્ સામ યજુરેવ ચ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Pitaahamasya jagato maataa dhaataa pitaamahah;\nVedyam pavitramonkaara riksaama yajureva cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस संपूर्ण जगत्‌ का धाता अर्थात्‌ धारण करने वाला एवं कर्मों के फल को देने वाला, पिता, माता, पितामह, जानने योग्य, (गीता अध्याय 13 श्लोक 12 से 17 तक में देखना चाहिए) पवित्र ओंकार तथा ऋग्वेद, सामवेद और यजुर्वेद भी मैं ही हूँ॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ જગતનો પિતા, માતા, પિતામહ એટલેકે કર્મફળ આપનાર બ્રહ્મદેવનો પિતા, પવિત્ર કરનાર યજ્ઞયાગાદિ કર્મો, ઓમકાર, ઋગવેદ, સામવેદ તથા યજુર્વેદ પણ હું જ છું.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("I am the father of this universe, the mother, the support and the grandsire. I am the object of knowledge, the purifier and the syllable om. I am also the Rig, the Sama and the Yajur Vedas.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        load();
        setTitle("Shlok 18");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("गतिर्भर्ता प्रभुः साक्षी निवासः शरणं सुहृत्‌ ।\nप्रभवः प्रलयः स्थानं निधानं बीजमव्ययम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("ગતિર્ભર્તા પ્રભુઃ સાક્ષી નિવાસઃ શરણં સુહૃત્,\nપ્રભવઃ પ્રલયઃ સ્થાનં નિધાનં બીજમવ્યયમ્.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Gatirbhartaa prabhuh saakshee nivaasah sharanam suhrit;\nPrabhavah pralayah sthaanam nidhaanam beejamavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("प्राप्त होने योग्य परम धाम, भरण-पोषण करने वाला, सबका स्वामी, शुभाशुभ का देखने वाला, सबका वासस्थान, शरण लेने योग्य, प्रत्युपकार न चाहकर हित करने वाला, सबकी उत्पत्ति-प्रलय का हेतु, स्थिति का आधार, निधान (प्रलयकाल में संपूर्ण भूत सूक्ष्म रूप से जिसमें लय होते हैं उसका नाम 'निधान' है) और अविनाशी कारण भी मैं ही हूँ॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("પ્રાપ્ત થવા યોગ્ય કર્મફળ ,જગતનો પોષણકર્તા,  સર્વ નો  સ્વામી ,પ્રાણીઓના શુભાશુભ કર્મોનો સાક્ષી, સર્વનું નિવાસસ્થાન,શરણાગત વત્સલ,અનપેક્ષ મિત્ર,જગતની ઉત્પતિ,પ્રલય રૂપ તથા સર્વનો આશ્રય,નિધાન અને અવિનાશી કારણ પણ હું જ છું.(૧૮) ");
        } else if (2 == i2) {
            this.tv2.setText("I am the goal, the sustainer, the master, the witness, the abode, the refuge, and the most dear friend. I am the creation and the annihilation, the basis of everything, the resting place and the eternal seed.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        load();
        setTitle("Shlok 19");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तपाम्यहमहं वर्षं निगृह्‌णाम्युत्सृजामि च ।\nअमृतं चैव मृत्युश्च सदसच्चाहमर्जुन ॥");
        } else if (i == 0) {
            this.tv1.setText("તપામ્યહમહં વર્ષં નિગૃહ્ણામ્યુત્સૃજામિ ચ,\nઅમૃતં ચૈવ મૃત્યુશ્ચ સદસચ્ચાહમર્જુન.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Tapaamyahamaham varsham nigrihnaamyutsrijaami cha;\nAmritam chaiva mrityushcha sadasacchaahamarjuna.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं ही सूर्यरूप से तपता हूँ, वर्षा का आकर्षण करता हूँ और उसे बरसाता हूँ। हे अर्जुन! मैं ही अमृत और मृत्यु हूँ और सत्‌-असत्‌ भी मैं ही हूँ॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! સુર્યરૂપે હું તપું છું, વરસાદ પાડનાર અને રોકનાર હું છું, અમૃત હું છું, મૃત્યુ હું છું,સત અને અસત પણ હું છું.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("O Arjuna, I give heat, and I withhold and send forth the rain. I am immortality, and I am also death personified. Both spirit and matter are in Me.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        load();
        setTitle("Shlok 20");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्रैविद्या मां सोमपाः पूतपापायज्ञैरिष्ट्‍वा स्वर्गतिं प्रार्थयन्ते।\nते पुण्यमासाद्य सुरेन्द्रलोकमश्नन्ति दिव्यान्दिवि देवभोगान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ત્રૈવિદ્યા માં સોમપાઃ પૂતપાપાયજ્ઞૈરિષ્ટ્વા સ્વર્ગતિં પ્રાર્થયન્તે, \nતે પુણ્યમાસાદ્ય સુરેન્દ્રલોક-મશ્નન્તિ દિવ્યાન્દિવિ. દેવભોગાન્.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Traividyaa maam somapaah pootapaapaa\nYajnairishtwaa swargatim praarthayante;\nTe punyamaasaadya surendralokaMashnanti\ndivyaan divi devabhogaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तीनों वेदों में विधान किए हुए सकाम कर्मों को करने वाले, सोम रस को पीने वाले, पापरहित पुरुष (यहाँ स्वर्ग प्राप्ति के प्रतिबंधक देव ऋणरूप पाप से पवित्र होना समझना चाहिए) मुझको यज्ञों के द्वारा पूजकर स्वर्ग की प्राप्ति चाहते हैं, वे पुरुष अपने पुण्यों के फलरूप स्वर्गलोक को प्राप्त होकर स्वर्ग में दिव्य देवताओं के भोगों को भोगते हैं॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("ત્રણ વેદ જાણનારા,સોમપાન કરનારા,અને તેના યોગથી નિષ્પાપ થયેલા, યાજ્ઞિકો યજ્ઞ વડે મારું પૂજન કરીને સ્વર્ગ પ્રાપ્તિ માટે મારી પ્રાથના કરે છે અને તેઓ દીક્ષિત પુણ્યનાપ્રભાવે સ્વર્ગમાં જઈ દેવોના ભોગો ભોગવે છે.(૨૦) ");
        } else if (2 == i2) {
            this.tv2.setText("Those who study the Vedas and drink the soma juice, seeking the heavenly planets, worship Me indirectly. Purified of sinful reactions, they take birth on the pious, heavenly planet of Indra, where they enjoy godly delights.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        load();
        setTitle("Shlok 21");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ते तं भुक्त्वा स्वर्गलोकं विशालंक्षीणे पुण्य मर्त्यलोकं विशन्ति।\nएवं त्रयीधर्ममनुप्रपन्ना गतागतं कामकामा लभन्ते ॥");
        } else if (i == 0) {
            this.tv1.setText("તે તં ભુક્ત્વા સ્વર્ગલોકં વિશાલંક્ષીણે પુણ્યે મર્ત્યલોકં વિશન્તિ,\nએવ ત્રયીધર્મમનુપ્રપન્નાગતાગતં કામકામા લભન્તે.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Te tam bhuktwaa swargalokam vishaalam\nKsheene punye martyalokam vishanti;\nEvam trayeedharmamanuprapannaa\nGataagatam kaamakaamaa labhante.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे उस विशाल स्वर्गलोक को भोगकर पुण्य क्षीण होने पर मृत्यु लोक को प्राप्त होते हैं। इस प्रकार स्वर्ग के साधनरूप तीनों वेदों में कहे हुए सकामकर्म का आश्रय लेने वाले और भोगों की कामना वाले पुरुष बार-बार आवागमन को प्राप्त होते हैं, अर्थात्‌ पुण्य के प्रभाव से स्वर्ग में जाते हैं और पुण्य क्षीण होने पर मृत्युलोक में आते हैं॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("તેઓ વિશાળસ્વર્ગલોક નો ઉપભોગ  કરી પુણ્ય સમાપ્ત થતાં પાછા મૃત્યુલોકમાં આવે છે.આમ ત્રણ વેદમાં નિર્દિષ્ટ કરેલા કેવળ વૈદિક કર્મ કરનારા કામના પ્રિય લોકો જન્મ-મરણના ચક્કરમાં પડે છે.(૨૧)  ");
        } else if (2 == i2) {
            this.tv2.setText("When they have thus enjoyed vast heavenly sense pleasure and the results of their pious activities are exhausted, they return to this mortal planet again. Thus those who seek sense enjoyment by adhering to the principles of the three Vedas achieve only repeated birth and death.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        load();
        setTitle("Shlok 22");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनन्याश्चिन्तयन्तो मां ये जनाः पर्युपासते ।\nतेषां नित्याभियुक्तानां योगक्षेमं वहाम्यहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અનન્યાસ્ચીન્તયન્તો માં યે જનાઃ પર્યુપાસતે,\nતેષાં નિત્યાભિયુક્તાનાં યોગક્ષેમં વહામ્યહમ્.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Ananyaashchintayanto maam ye janaah paryupaasate;\nTeshaam nityaabhiyuktaanaam yogakshemam vahaamyaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो अनन्यप्रेमी भक्तजन मुझ परमेश्वर को निरंतर चिंतन करते हुए निष्कामभाव से भजते हैं, उन नित्य-निरंतर मेरा चिंतन करने वाले पुरुषों का योगक्षेम (भगवत्‌स्वरूप की प्राप्ति का नाम 'योग' है और भगवत्‌प्राप्ति के निमित्त किए हुए साधन की रक्षा का नाम 'क्षेम' है) मैं स्वयं प्राप्त कर देता हूँ॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે લોકો એકનિષ્ઠ થઈને મારું ચિંતન કરતાં મારી ઉપાસના કરે છે, એ સર્વદા મારી સાથે નિષ્કામ ભક્તોના યોગક્ષેમને હું ચલાવતો રહું છું.(૨૨)  ");
        } else if (2 == i2) {
            this.tv2.setText("But those who always worship Me with exclusive devotion, meditating on My transcendental form—to them I carry what they lack, and I preserve what they have.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        load();
        setTitle("Shlok 23");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("येऽप्यन्यदेवता भक्ता यजन्ते श्रद्धयान्विताः ।\nतेऽपि मामेव कौन्तेय यजन्त्यविधिपूर्वकम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યેપ્યન્યદેવતા ભક્તા યજન્તે શ્રદ્ધયાન્વિતાઃ,\nતેપિ મામેવ કૌન્તેય યજન્ત્યવિધિપૂર્વકમ્.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Ye’pyanyadevataa bhaktaa yajante shraddhayaa’nvitaah;\nTe’pi maameva kaunteya yajantyavidhipoorvakam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! यद्यपि श्रद्धा से युक्त जो सकाम भक्त दूसरे देवताओं को पूजते हैं, वे भी मुझको ही पूजते हैं, किंतु उनका वह पूजन अविधिपूर्वक अर्थात्‌ अज्ञानपूर्वक है॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("અન્ય દેવોને ઉપાસતા લોકો શ્રધાયુક્ત થઇ તે દેવતાઓનું પૂજન-યજન કરે છે. હે કાન્તેય !તેઓ પણ મારું જ યજન કરે છે. પરંતુ તેમનું એ આચરણ અવિધિપૂર્વકનું હોય છે.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are devotees of other gods and who worship them with faith actually worship only Me, O son of Kunti, but they do so in a wrong way.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        load();
        setTitle("Shlok 24");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहं हि सर्वयज्ञानां भोक्ता च प्रभुरेव च ।\nन तु मामभिजानन्ति तत्त्वेनातश्च्यवन्ति ते ॥");
        } else if (i == 0) {
            this.tv1.setText("અહં હિ સર્વયજ્ઞાનાં ભોક્તા ચ પ્રભુરેવ ચ,\nન તુ મામભિજાનન્તિ તત્ત્વેનાતશ્ચ્યવન્તિ તે.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Aham hi sarvayajnaanaam bhoktaa cha prabhureva cha;\nNa tu maamabhijaananti tattwenaatashchyavanti te.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि संपूर्ण यज्ञों का भोक्ता और स्वामी भी मैं ही हूँ, परंतु वे मुझ परमेश्वर को तत्त्व से नहीं जानते, इसी से गिरते हैं अर्थात्‌ पुनर्जन्म को प्राप्त होते हैं॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેમ કે હું જ સર્વ યજ્ઞોનો ભોક્તા અને સ્વામી છું,અન્ય દેવોના ભક્તો મને તત્વત: જાણતા નથી. તેથી તેઓ મુખ્ય યજ્ઞફળથી વંચિત રહે છે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("I am the only enjoyer and master of all sacrifices. Therefore, those who do not recognize My true transcendental nature fall down.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        load();
        setTitle("Shlok 25");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यान्ति देवव्रता देवान्पितृन्यान्ति पितृव्रताः ।\nभूतानि यान्ति भूतेज्या यान्ति मद्याजिनोऽपि माम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યાન્તિ દેવવ્રતા દેવાન્ પિતૃ઼ન્યાન્તિ પિતૃવ્રતાઃ,\nભૂતાનિ યાન્તિ ભૂતેજ્યા યાન્તિ મદ્યાજિનોપિ મામ્.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Yaanti devavrataa devaan pitreen yaanti pitrivrataah;\nBhutaani yaanti bhutejyaa yaanti madyaajino’pi maam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("देवताओं को पूजने वाले देवताओं को प्राप्त होते हैं, पितरों को पूजने वाले पितरों को प्राप्त होते हैं, भूतों को पूजने वाले भूतों को प्राप्त होते हैं और मेरा पूजन करने वाले भक्त मुझको ही प्राप्त होते हैं। इसीलिए मेरे भक्तों का पुनर्जन्म नहीं होता (गीता अध्याय 8 श्लोक 16 में देखना चाहिए)॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("દેવોની ઉપાસના કરનારા દેવલોકમાં જાય છે,પિતૃભક્તો પિતૃલોકમાં જાય છે, ભૂતોના પુજકોને ભૂતોની પ્રાપ્તિ થાય છે અને મારું ભજન કરનારાઓને મારી પ્રાપ્તિ થાય છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Those who worship the demigods will take birth among the demigods; those who worship the ancestors go to the ancestors; those who worship ghosts and spirits will take birth among such beings; and those who worship Me will live with Me.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        load();
        setTitle("Shlok 26");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पत्रं पुष्पं फलं तोयं यो मे भक्त्या प्रयच्छति ।\nतदहं भक्त्युपहृतमश्नामि प्रयतात्मनः ॥");
        } else if (i == 0) {
            this.tv1.setText("પત્રં પુષ્પં ફલં તોયં યો મે ભક્ત્યા પ્રયચ્છતિ,\nતદહં ભક્ત્યુપહૃતમશ્નામિ પ્રયતાત્મનઃ.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Patram pushpam phalam toyam yo me bhaktyaa prayacchati;\nTadaham bhaktyupahritamashnaami prayataatmanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कोई भक्त मेरे लिए प्रेम से पत्र, पुष्प, फल, जल आदि अर्पण करता है, उस शुद्धबुद्धि निष्काम प्रेमी भक्त का प्रेमपूर्वक अर्पण किया हुआ वह पत्र-पुष्पादि मैं सगुणरूप से प्रकट होकर प्रीतिसहित खाता हूँ॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("શુદ્ધ ચિત્તવાળા ભક્તો પ્રેમ અને ભક્તિપૂર્વક મને પત્ર,પુષ્પ,ફળ,જળ વગેરે અર્પણ કરે છે. તે હું સાકારરૂપ ધારણ કરી  પ્રેમપૂર્વક ગ્રહણ કરું છું.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("If one offers Me with love and devotion a leaf, a flower, fruit or water, I will accept it.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        load();
        setTitle("Shlok 27");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्करोषि यदश्नासि यज्जुहोषि ददासि यत्‌ ।\nयत्तपस्यसि कौन्तेय तत्कुरुष्व मदर्पणम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યત્કરોષિ યદશ્નાસિ યજ્જુહોષિ દદાસિ યત્,\nયત્તપસ્યસિ કૌન્તેય તત્કુરુષ્વ મદર્પણમ્.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Yatkaroshi yadashnaasi yajjuhoshi dadaasi yat;\nYattapasyasi kaunteya tatkurushva madarpanam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! तू जो कर्म करता है, जो खाता है, जो हवन करता है, जो दान देता है और जो तप करता है, वह सब मेरे अर्पण कर॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય ! તું જે કઈ કર્મ કરે, ભક્ષણ કરે, હવન કરે, દાન આપે કે સ્વધર્માચરણરૂપ તપકરે, તે સર્વ કંઈ  મને અર્પણ કરી દે.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("Whatever you do, whatever you eat, whatever you offer or give away, and whatever austerities you perform—do that, O son of Kunti, as an offering to Me.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        load();
        setTitle("Shlok 28");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शुभाशुभफलैरेवं मोक्ष्य से कर्मबंधनैः ।\nसन्न्यासयोगमुक्तात्मा विमुक्तो मामुपैष्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("શુભાશુભફલૈરેવં મોક્ષ્યસે કર્મબન્ધનૈઃ,\nસંન્યાસયોગયુક્તાત્મા વિમુક્તો મામુપૈષ્યસિ.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Shubhaashubhaphalairevam mokshyase karmabandhanaih;\nSannyaasayogayuktaatmaa vimukto maamupaishyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस प्रकार, जिसमें समस्त कर्म मुझ भगवान के अर्पण होते हैं- ऐसे संन्यासयोग से युक्त चित्तवाला तू शुभाशुभ फलरूप कर्मबंधन से मुक्त हो जाएगा और उनसे मुक्त होकर मुझको ही प्राप्त होगा। ॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("આમ સર્વ કર્મો મને અર્પણ કરવાથી તારું અંત:કરણ સન્યાસયોગ યુક્ત થશે.આથી તું શુભ-અશુભ ફળ આપનારા કર્મબંધનથી મુક્ત થઇ જઈશ.અને એમ તું મારામાં મળી જઈશ.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("In this way you will be freed from bondage to work and its auspicious and inauspicious results. With your mind fixed on Me in this principle of renunciation, you will be liberated and come to Me.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        load();
        setTitle("Shlok 29");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("समोऽहं सर्वभूतेषु न मे द्वेष्योऽस्ति न प्रियः ।\nये भजन्ति तु मां भक्त्या मयि ते तेषु चाप्यहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સમોહં સર્વભૂતેષુ ન મે દ્વેષ્યોસ્તિ ન પ્રિયઃ,\nયે ભજન્તિ તુ માં ભક્ત્યા મયિ તે તેષુ ચાપ્યહમ્.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Samo’ham sarvabhooteshu na me dweshyo’sti na priyah;\nYe bhajanti tu maam bhaktyaa mayi te teshu chaapyaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं सब भूतों में समभाव से व्यापक हूँ, न कोई मेरा अप्रिय है और न प्रिय है, परंतु जो भक्त मुझको प्रेम से भजते हैं, वे मुझमें हैं और मैं भी उनमें प्रत्यक्ष प्रकट (जैसे सूक्ष्म रूप से सब जगह व्यापक हुआ भी अग्नि साधनों द्वारा प्रकट करने से ही प्रत्यक्ष होता है, वैसे ही सब जगह स्थित हुआ भी परमेश्वर भक्ति से भजने वाले के ही अंतःकरण में प्रत्यक्ष रूप से प्रकट होता है) हूँ॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું સર્વ ભૂતોમાં સમાન છું, મારો કોઈ શત્રુ નથી કે કોઈ મિત્ર નથી. મને જે ભક્તિથી ભજે છે તેઓ મારામાં સ્થિર છે અને હું પણ તેમનામાં રહું છું.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("I envy no one, nor am I partial to anyone. I am equal to all. But whoever renders service unto Me in devotion is a friend, is in Me, and I am also a friend to him.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        load();
        setTitle("Shlok 30");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अपि चेत्सुदुराचारो भजते मामनन्यभाक्‌ ।\nसाधुरेव स मन्तव्यः सम्यग्व्यवसितो हि सः ॥");
        } else if (i == 0) {
            this.tv1.setText("અપિ ચેત્સુદુરાચારો ભજતે મામનન્યભાક્,\nસાધુરેવ સ મન્તવ્યઃ સમ્યગ્વ્યવસિતો હિ સઃ.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Api chet suduraachaaro bhajate maamananyabhaak;\nSaadhureva sa mantavyah samyagvyavasito hi sah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यदि कोई अतिशय दुराचारी भी अनन्य भाव से मेरा भक्त होकर मुझको भजता है तो वह साधु ही मानने योग्य है, क्योंकि वह यथार्थ निश्चय वाला है। अर्थात्‌ उसने भली भाँति निश्चय कर लिया है कि परमेश्वर के भजन के समान अन्य कुछ भी नहीं है॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("અતિ દુરાચારી હોવા છતાં જે એકનિષ્ઠાથી મારું ભજન કરે તેને સાધુ સમજવો.કેમ કે તે યથાર્થ નિશ્વયવાળો હોય છે.એટલેકે તે એવું માને છે કે પ્રભુભજન સિવાય અન્ય કઇ જ નથી.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("Even if one commits the most abominable action, if he is engaged in devotional service he is to be considered saintly because he is properly situated in his determination.(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        load();
        setTitle("Shlok 31");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("क्षिप्रं भवति धर्मात्मा शश्वच्छान्तिं निगच्छति ।\nकौन्तेय प्रतिजानीहि न मे भक्तः प्रणश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("ક્ષિપ્રં ભવતિ ધર્માત્મા શશ્વચ્છાન્તિં નિગચ્છતિ,\nકૌન્તેય પ્રતિજાનીહિ ન મે ભક્તઃ પ્રણશ્યતિ.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Kshipram bhavati dharmaatmaa shashwacchaantim nigacchati;\nKaunteya pratijaaneehi na me bhaktah pranashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह शीघ्र ही धर्मात्मा हो जाता है और सदा रहने वाली परम शान्ति को प्राप्त होता है। हे अर्जुन! तू निश्चयपूर्वक सत्य जान कि मेरा भक्त नष्ट नहीं होता॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય ! તે તરત જ ધર્માત્મા બની જાય છે અને શાશ્વત, પરમ શાંતિ પામે છે. મારા ભક્તનો કદી નાશ થતો નથી, એ તું નિશ્વયપૂર્વક જાણ.(૩૧)");
        } else if (2 == i2) {
            this.tv2.setText("He quickly becomes righteous and attains lasting peace. O son of Kunti, declare it boldly that My devotee never perishes.(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        load();
        setTitle("Shlok 32");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मां हि पार्थ व्यपाश्रित्य येऽपि स्यु पापयोनयः ।\nस्त्रियो वैश्यास्तथा शूद्रास्तेऽपि यान्ति परां गतिम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("માં હિ પાર્થ વ્યપાશ્રિત્ય યેપિ સ્યુઃ પાપયોનયઃ\nસ્ત્રિયો વૈશ્યાસ્તથા શૂદ્રાસ્તેપિ યાન્તિ પરાં ગતિમ્.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Maam hi paartha vyapaashritya ye’pi syuh paapayonayah;\nStriyo vaishyaastathaa shoodraaste’pi yaanti paraam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! स्त्री, वैश्य, शूद्र तथा पापयोनि चाण्डालादि जो कोई भी हों, वे भी मेरे शरण होकर परमगति को ही प्राप्त होते हैं॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("સ્ત્રીઓ,વૈશ્ય , શુદ્ર  વગેરે જે કોઈ પાપ યોનીમાં જન્મ્યા હોય તો પણ હે પાર્થ ! તેઓ મારો આશ્રય કરે તો તેને ઉત્તમ ગતિ પ્રાપ્ત થાય છે.(૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, those who take shelter in Me, though they be of lower birth—women, vaishyas [merchants] and shudras [workers]—can attain the supreme destination.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        load();
        setTitle("Shlok 33");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("किं पुनर्ब्राह्मणाः पुण्या भक्ता राजर्षयस्तथा ।\nअनित्यमसुखं लोकमिमं प्राप्य भजस्व माम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કિં પુનર્બ્રાહ્મણાઃ પુણ્યા ભક્તા રાજર્ષયસ્તથા,\nઅનિત્યમસુખં લોકમિમં પ્રાપ્ય ભજસ્વ મામ્.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Kim punarbraahmanaah punyaa bhaktaa raajarshayastathaa;\nAnityamasukham lokam imam praapya bhajaswa maam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("फिर इसमें कहना ही क्या है, जो पुण्यशील ब्राह्मण था राजर्षि भक्तजन मेरी शरण होकर परम गति को प्राप्त होते हैं। इसलिए तू सुखरहित और क्षणभंगुर इस मनुष्य शरीर को प्राप्त होकर निरंतर मेरा ही भजन कर॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ પ્રમાણે છે તો જે પુણ્યશાળી હોય અને સાથે મારી ભક્તિ કરનારા બ્રાહ્મણ અને રાજર્ષિ હોય તો તે મને અતિ પ્રિય જ હોય. તેં આ નાશવંત અને દુઃખી એવા મૃત્યુલોકમાં જન્મ ધારણ કર્યો છે, તો મારું ભજન કર.(૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("How much more this is so of the righteous brahmanas, the devotees and the saintly kings. Therefore, having come to this temporary, miserable world, engage in loving service unto Me.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        load();
        setTitle("Shlok 34");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मन्मना भव मद्भक्तो मद्याजी मां नमस्कुरु ।\nमामेवैष्यसि युक्त्वैवमात्मानं मत्परायण: ॥");
        } else if (i == 0) {
            this.tv1.setText("મન્મના ભવ મદ્ભક્તો મદ્યાજી માં નમસ્કુરુ,\nમામેવૈષ્યસિ યુક્ત્વૈવમાત્માનં મત્પરાયણઃ.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Manmanaa bhava madbhakto madyaajee maam namaskuru;\nMaamevaishyasi yuktwaivamaatmaanam matparaayanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मुझमें मन वाला हो, मेरा भक्त बन, मेरा पूजन करने वाला हो, मुझको प्रणाम कर। इस प्रकार आत्मा को मुझमें नियुक्त करके मेरे परायण होकर तू मुझको ही प्राप्त होगा॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! તું મારામાં મન રાખ, મારો ભક્ત થા, મારા પૂજન વિષે પરાયણ થા તથા મને નમસ્કાર કર. આ પ્રકારે મારા શરણ ને પ્રાપ્ત થયેલો તું તારા અંત:કરણને મારામાં યોજવાથી મને પામીશ.(૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("Engage your mind always in thinking of Me, become My devotee, offer obeisances to Me and worship Me. Being completely absorbed in Me, surely you will come to Me.(34)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay9.this, ShlokListPlay9.this);
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

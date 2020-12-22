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

public class ShlokListPlay12 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
    Button btn_slock, btn_slocktras        , btn_slock_silent, btn_slocktras_silent
            ;
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
                if (ShlokListPlay12.this.favflag == 0) {
                    ShlokListPlay12.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.favflag = 1;
                    shlokListPlay12.save();
                } else if (1 == ShlokListPlay12.this.favflag) {
                    ShlokListPlay12.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay12 shlokListPlay122 = ShlokListPlay12.this;
                    shlokListPlay122.favflag = 0;
                    shlokListPlay122.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay12.this.calll++;
                ShlokListPlay12.this.slkset();
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
                ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                shlokListPlay12.calll--;
                ShlokListPlay12.this.slkset();
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
        edit.putString("A12" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A12" + this.calll, "0");
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
        if (16 == this.calll) {
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
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.callf1 = 0;
                    shlokListPlay12.slkset();
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
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.callf1 = 2;
                    shlokListPlay12.slkset();
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
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.callf1 = 1;
                    shlokListPlay12.slkset();
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
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.callf2 = 0;
                    shlokListPlay12.slkset();
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
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.callf2 = 2;
                    shlokListPlay12.slkset();
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
                    ShlokListPlay12 shlokListPlay12 = ShlokListPlay12.this;
                    shlokListPlay12.callf2 = 1;
                    shlokListPlay12.slkset();
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
            shlok3_4_5();
        } else if (4 == i3) {
            shlok6_7();
        } else if (5 == i3) {
            shlok8();
        } else if (6 == i3) {
            shlok9();
        } else if (7 == i3) {
            shlok10();
        } else if (8 == i3) {
            shlok11();
        } else if (9 == i3) {
            shlok12();
        } else if (10 == i3) {
            shlok13_14();
        } else if (11 == i3) {
            shlok15();
        } else if (12 == i3) {
            shlok16();
        } else if (13 == i3) {
            shlok17();
        } else if (14 == i3) {
            shlok18_19();
        } else if (15 == i3) {
            shlok20();
        } else if (16 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 12");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १२ \nभक्तियोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૨ \nભક્તિ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 12\nbhakti yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा हैं की, कृष्ण के शुद्ध प्रेम को प्राप्त करने का सबसे सुगम एवं सर्वोच्च साधन भक्तियोग है। इस परम पथ का अनुसरण करने वालो में दिव्य गुण उत्पन्न होते हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૨ માં બ્રહ્મ ના નિરાકાર કે સાકાર એ બંને માં કોણ શ્રેષ્ઠ છે? અર્જુન ના પ્રશ્ન નો કૃષ્ણ જવાબ આપે છે.\"મારામાં મન રાખીને, જે નિત્ય તત્પર રહીને શ્રદ્ધા થી મને ભજે છે, તે શ્રેષ્ઠ યોગી છે.\" ભક્તિ ને જ્ઞાન અને કર્મ ની પુરક બતાવી છે. અભ્યાસ કરતાં જ્ઞાન શ્રેષ્ઠ છે, જ્ઞાન કરતાં ધ્યાન શ્રેષ્ઠ છે, અને ધ્યાન કરતાં પણ કર્મ ફળોનો ત્યાગ શ્રેષ્ઠ છે એવું વર્ણન છે.");
        } else if (2 == i) {
            this.tv1.setText("Twelveth adhyay tells, Bhakti-yoga, pure devotional service to Lord Krishna, is the highest and most expedient means for attaining pure love for Krishna, which is the highest end of spiritual existence. Those who follow this supreme path develop divine qualities.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nएवं सततयुक्ता ये भक्तास्त्वां पर्युपासते ।\nये चाप्यक्षरमव्यक्तं तेषां के योगवित्तमाः ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nએવં સતતયુક્તા યે ભક્તાસ્ત્વાં પર્યુપાસતે,\nયેચાપ્યક્ષરમવ્યક્તં તેષાં કે યોગવિત્તમાઃ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nEvam satatayuktaa ye bhaktaastwaam paryupaasate;\nYe chaapyaksharamavyaktam teshaam ke yogavittamaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- जो अनन्य प्रेमी भक्तजन पूर्वोक्त प्रकार से निरन्तर आपके भजन-ध्यान में लगे रहकर आप सगुण रूप परमेश्वर को और दूसरे जो केवल अविनाशी सच्चिदानन्दघन निराकार ब्रह्म को ही अतिश्रेष्ठ भाव से भजते हैं- उन दोनों प्रकार के उपासकों में अति उत्तम योगवेत्ता कौन हैं?॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે-એ રીતે નિરંતર આપનું ધ્યાન ધરતા જે ભક્તો આપને સગુણ સ્વરૂપે ભજે છે, અને જે લોકો આપણી નિર્ગુણ સ્વરૂપ ની ઉપાસના કરે છે,તે બંને માં શ્રેષ્ઠ યોગવેતા કોણ? (૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna inquired: Which are considered to be more perfect, those who are always properly engaged in Your devotional service or those who worship the impersonal Brahman, the unmanifested?(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nमय्यावेश्य मनो ये मां नित्ययुक्ता उपासते ।\nश्रद्धया परयोपेतास्ते मे युक्ततमा मताः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nમય્યાવેશ્ય મનો યે માં નિત્યયુક્તા ઉપાસતે,\nશ્રદ્ધયા પરયોપેતાસ્તે મે યુક્તતમા મતાઃ.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nMayyaaveshya mano ye maam nityayuktaa upaasate;\nShraddhayaa parayopetaaste me yuktatamaa mataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- मुझमें मन को एकाग्र करके निरंतर मेरे भजन-ध्यान में लगे हुए जो भक्तजन अतिशय श्रेष्ठ श्रद्धा से युक्त होकर मुझ सगुणरूप परमेश्वर को भजते हैं, वे मुझको योगियों में अति उत्तम योगी मान्य हैं॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન બોલ્યા- જેઓ મન ને એકાગ્ર કરી ,નિરંતર ધ્યાન ધરતાં શ્રેષ્ઠ શ્રધ્ધાથી યુક્ત થઇ મને ઉપાસે છે તેમને મેં શ્રેષ્ઠ યોગવેત્તા ઓ માન્યા છે.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: Those who fix their minds on My personal form and are always engaged in worshiping Me with great and transcendental faith are considered by Me to be most perfect.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3_4_5() {
        setTitle("Shlok 3,4,5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये त्वक्षरमनिर्देश्यमव्यक्तं पर्युपासते।\nसर्वत्रगमचिन्त्यं च कूटस्थमचलं ध्रुवम्‌ ॥\nसन्नियम्येन्द्रियग्रामं सर्वत्र समबुद्धयः ।\nते प्राप्नुवन्ति मामेव सर्वभूतहिते रताः \n॥क्लेशोऽधिकतरस्तेषामव्यक्तासक्तचेतसाम्‌ ।\nअव्यक्ता हि गतिर्दुःखं देहवद्भिरवाप्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યે ત્વક્ષરમનિર્દેશ્યમવ્યક્તં પર્યુપાસતે,\nસર્વત્રગમચિન્ત્યં ચ કૂટસ્થમચલં ધ્રુવમ્.(૩)\nસંનિયમ્યેન્દ્રિયગ્રામં સર્વત્ર સમબુદ્ધયઃ,\nતે પ્રાપ્નુવન્તિ મામેવ સર્વભૂતહિતે રતાઃ.(૪)\nક્લેશોધિકતરસ્તેષામવ્યક્તાસક્તચેતસામ્,\nઅવ્યક્તા હિ ગતિર્દુઃખં દેહવદ્ભિરવાપ્યતે.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Ye twaksharamanirdeshyamavyaktam paryupaasate;\nSarvatragamachintyam cha kootasthamachalam dhruvam.\nSamniyamyendriyagraamam sarvatra samabuddhayah;\nTe praapnuvanti maameva sarvabhootahite rataah.\nKlesho’dhikatarasteshaam avyaktaasaktachetasaam;\nAvyaktaa hi gatirduhkham dehavadbhiravaapyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु जो पुरुष इन्द्रियों के समुदाय को भली प्रकार वश में करके मन-बुद्धि से परे, सर्वव्यापी, अकथनीय स्वरूप और सदा एकरस रहने वाले, नित्य, अचल, निराकार, अविनाशी, सच्चिदानन्दघन ब्रह्म को निरन्तर एकीभाव से ध्यान करते हुए भजते हैं, वे सम्पूर्ण भूतों के हित में रत और सबमें समान भाववाले योगी मुझको ही प्राप्त होते हैं| उन सच्चिदानन्दघन निराकार ब्रह्म में आसक्त चित्तवाले पुरुषों के साधन में परिश्रम विशेष है क्योंकि देहाभिमानियों द्वारा अव्यक्तविषयक गति दुःखपूर्वक प्राप्त की जाती है | ॥3-4-5॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ જીવો (ભૂતો) નું હિત કરવા માં તત્પર અને સર્વ માં સમદ્રષ્ટિ રાખવાવાળા જે પુરુષો - સર્વ ઇન્દ્રિયોનું યથાર્થ નિયમન કરીને અનિર્દ્રશ્ય,અવ્યક્ત,સર્વમાં વ્યાપેલા ,અચિંત્ય,કુટસ્થ, અચળ,શાશ્વત તથા અવિનાશી બ્રહ્મની ઉપાસના કરેછે,તેઓ મને જ પામે છે. નિર્ગુણ બ્રહ્મ ની ઉપાસના કરનારા દેહધારી મનુષ્યો કષ્ટ થી એ ઉપાસના કરે છે અને તેમને અવ્યક્ત ગતિ ઘણા યત્નથી પ્રાપ્ત થાયછે.(૩,૪,૫)");
        } else if (2 == i2) {
            this.tv2.setText("But those who fully worship the unmanifested, that which lies beyond the perception of the senses, the all-pervading, inconceivable, unchanging, fixed and immovable—the impersonal conception of the Absolute Truth—by controlling the various senses and being equally disposed to everyone, such persons, engaged in the welfare of all, at last achieve Me.For those whose minds are attached to the unmanifested, impersonal feature of the Supreme, advancement is very troublesome. To make progress in that discipline is always difficult for those who are embodied.(3-4-5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6_7() {
        setTitle("Shlok 6,7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये तु सर्वाणि कर्माणि मयि सन्नयस्य मत्पराः ।\nअनन्येनैव योगेन मां ध्यायन्त उपासते ॥\nतेषामहं समुद्धर्ता मृत्युसंसारसागरात्‌ ।\nभवामि नचिरात्पार्थ मय्यावेशितचेतसाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યે તુ સર્વાણિ કર્માણિ મયિ સંન્યસ્ય મત્પરાઃ,\nઅનન્યેનૈવ યોગેન માં ધ્યાયન્ત ઉપાસતે.(૬)\nતેષામહં સમુદ્ધર્તા મૃત્યુસંસારસાગરાત્,\nભવામિ નચિરાત્પાર્થ મય્યાવેશિતચેતસામ્.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Ye tu sarvaani karmaani mayi sannyasya matparaah;\nAnanyenaiva yogena maam dhyaayanta upaasate.\nTeshaamaham samuddhartaa mrityusamsaarasaagaraat;\nBhavaami nachiraat paartha mayyaaveshitachetasaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु जो मेरे परायण रहने वाले भक्तजन सम्पूर्ण कर्मों को मुझमें अर्पण करके मुझ सगुणरूप परमेश्वर को ही अनन्य भक्तियोग से निरन्तर चिन्तन करते हुए भजते हैं।  हे अर्जुन! उन मुझमें चित्त लगाने वाले प्रेमी भक्तों का मैं शीघ्र ही मृत्यु रूप संसार-समुद्र से उद्धार करने वाला होता हूँ ॥6-7॥");
        } else if (i2 == 0) {
            this.tv2.setText("કિન્તુ જેઓ મારા પરાયણ થઇ ને સર્વે કર્મો મને અર્પણ કરેછે અને મારુજ ધ્યાન ધરી અનન્ય શ્રધ્ધા ભાવ થી મારીજ ઉપાસના કરેછે તથા જેઓ પોતાનું ચિત્ત મને જ  સમર્પિત કરી દેછે એવા મારા ભક્તોનો હે પાર્થ ! હું જન્મ-મરણ રૂપી આ સંસાર માંથી તરત જ ઉદ્ધાર કરું છું.(૬,૭)");
        } else if (2 == i2) {
            this.tv2.setText("But those who worship Me, giving up all their activities unto Me and being devoted to Me without deviation, engaged in devotional service and always meditating upon Me, having fixed their minds upon Me, O son of Pritha—for them I am the swift deliverer from the ocean of birth and death.(6-7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मय्येव मन आधत्स्व मयि बुद्धिं निवेशय ।\nनिवसिष्यसि मय्येव अत ऊर्ध्वं न संशयः ॥");
        } else if (i == 0) {
            this.tv1.setText("મય્યેવ મન આધત્સ્વ મયિ બુદ્ધિં નિવેશય,\nનિવસિષ્યસિ મય્યેવ અત ઊર્ધ્વં ન સંશયઃ.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Mayyeva mana aadhatswa mayi buddhim niveshaya;\nNivasishyasi mayyeva ata oordhwam na samshayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" मुझमें मन को लगा और मुझमें ही बुद्धि को लगा, इसके उपरान्त तू मुझमें ही निवास करेगा, इसमें कुछ भी संशय नहीं है॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("મનને મારા વિષે સ્થિર કર અને બુદ્ધિને પણ મારા વિષે  સ્થિર કર તેમ કરવાથી આ દેહના અંત પછી તું મારા વિષે જ નિવાસ કરીશ,એમાં શંકા ને કોઈ સ્થાન નથી.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("Just fix your mind upon Me, the Supreme Personality of Godhead, and engage all your intelligence in Me. Thus you will live in Me always, without a doubt.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथ चित्तं समाधातुं न शक्रोषि मयि स्थिरम्‌ ।\nअभ्यासयोगेन ततो मामिच्छाप्तुं धनञ्जय ॥");
        } else if (i == 0) {
            this.tv1.setText("અથ ચિત્તં સમાધાતું ન શક્નોષિ મયિ સ્થિરમ્,\nઅભ્યાસયોગેન તતો મામિચ્છાપ્તું ધનઞ્જય.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Atha chittam samaadhaatum na shaknoshi mayi sthiram;\nAbhyaasayogena tato maamicchaaptum dhananjaya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यदि तू मन को मुझमें अचल स्थापन करने के लिए समर्थ नहीं है, तो हे अर्जुन! अभ्यासरूप योग द्वारा मुझको प्राप्त होने के लिए इच्छा कर॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ધનંજય, જો મારા સગુણ રૂપ માં મન સ્થાપીને સ્થિર કરવા માટે તું અસમર્થ હોય તો - અભ્યાસ ના યોગ વડે મને પામવાની ઈચ્છા કર.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("My dear Arjuna, O winner of wealth, if you cannot fix your mind upon Me without deviation, then follow the regulative principles of bhakti-yoga. In this way develop a desire to attain Me.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अभ्यासेऽप्यसमर्थोऽसि मत्कर्मपरमो भव ।\nमदर्थमपि कर्माणि कुर्वन्सिद्धिमवाप्स्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("અભ્યાસેપ્યસમર્થોસિ મત્કર્મપરમો ભવ,\nમદર્થમપિ કર્માણિ કુર્વન્ સિદ્ધિમવાપ્સ્યસિ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Abhyaase’pyasamartho’si matkarmaparamo bhava;\nMadarthamapi karmaani kurvansiddhimavaapsyasi");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यदि तू उपर्युक्त अभ्यास में भी असमर्थ है, तो केवल मेरे लिए कर्म करने के ही परायण हो जा। इस प्रकार मेरे निमित्त कर्मों को करता हुआ भी मेरी प्राप्ति रूप सिद्धि को ही प्राप्त होगा॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("અભ્યાસ નો યોગ કરવા માં પણ તું અસમર્થ હોય તો મારા ઉદ્દેશથી જ કર્મ કરતો રહે મને ઉદ્દેશીને કર્મો કરીશ તો પણ તું સિદ્ધિ ને પ્રાપ્ત કરીશ.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("If you cannot practice the regulations of bhakti-yoga, then just try to work for Me, because by working for Me you will come to the perfect stage.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथैतदप्यशक्तोऽसि कर्तुं मद्योगमाश्रितः ।\nसर्वकर्मफलत्यागं ततः कुरु यतात्मवान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અથૈતદપ્યશક્તોસિ કર્તું મદ્યોગમાશ્રિતઃ,\nસર્વકર્મફલત્યાગં તતઃ કુરુ યતાત્મવાન્.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Athaitadapyashakto’si kartum madyogamaashritah;\nSarvakarmaphalatyaagam tatah kuru yataatmavaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" यदि मेरी प्राप्ति रूप योग के आश्रित होकर उपर्युक्त साधन को करने में भी तू असमर्थ है, तो मन-बुद्धि आदि पर विजय प्राप्त करने वाला होकर सब कर्मों के फल का त्याग  कर॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("જો મને ઉદ્દેશીને કર્મો કરવામાં પણ તું અશક્ત હોય તો મારા યોગ નો આશ્રય કરી- મનનો સંયમ કર,અને અનન્ય ભાવે મારા શરણે આવી,સર્વ કર્મો નાં ફળ નો ત્યાગ કરી દે.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("If, however, you are unable to work in this consciousness of Me, then try to act giving up all results of your work and try to be self-situated.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रेयो हि ज्ञानमभ्यासाज्ज्ञानाद्धयानं विशिष्यते ।\nध्यानात्कर्मफलत्यागस्त्यागाच्छान्तिरनन्तरम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રેયો હિ જ્ઞાનમભ્યાસાજ્જ્ઞાનાદ્ધ્યાનં વિશિષ્યતે,\nધ્યાનાત્કર્મફલત્યાગસ્ત્યાગાચ્છાન્તિરનન્તરમ્.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Shreyo hi jnaanamabhyaasaat jnaanaaddhyaanam vishishyate;\nDhyaanaat karmaphalatyaagas tyaagaacchaantir anantaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मर्म को न जानकर किए हुए अभ्यास से ज्ञान श्रेष्ठ है, ज्ञान से मुझ परमेश्वर के स्वरूप का ध्यान श्रेष्ठ है और ध्यान से सब कर्मों के फल का त्याग  श्रेष्ठ है, क्योंकि त्याग से तत्काल ही परम शान्ति होती है॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("અભ્યાસ કરતાં જ્ઞાન શ્રેષ્ઠ છે અને જ્ઞાન કરતાં ધ્યાન શ્રેષ્ઠ છે અને ધ્યાન કરતાં કર્મ ના ફળ નો ત્યાગ શ્રેષ્ઠ છે કારણકે કર્મફળ ના ત્યાગથી શાંતિ શ્રેષ્ઠ છે. આ રીતે આગળ વધવાથી શાંતિ પ્રાપ્ત થાય છે.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("If you cannot take to this practice, then engage yourself in the cultivation of knowledge. Better than knowledge, however, is meditation, and better than meditation is renunciation of the fruits of action, for by such renunciation one can attain peace of mind.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13_14() {
        setTitle("Shlok 13,14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अद्वेष्टा सर्वभूतानां मैत्रः करुण एव च ।\nनिर्ममो निरहङ्‍कारः समदुःखसुखः क्षमी ॥\nसंतुष्टः सततं योगी यतात्मा दृढ़निश्चयः।\nमय्यर्पितमनोबुद्धिर्यो मद्भक्तः स मे प्रियः॥");
        } else if (i == 0) {
            this.tv1.setText("અદ્વેષ્ટા સર્વભૂતાનાં મૈત્રઃ કરુણ એવ ચ,\nનિર્મમો નિરહઙ્કારઃ સમદુઃખસુખઃ ક્ષમી.(૧૩)\nસન્તુષ્ટઃ સતતં યોગી યતાત્મા દૃઢનિશ્ચયઃ,\nમય્યર્પિતમનોબુદ્ધિર્યો મદ્ભક્તઃ સ મે પ્રિયઃ.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Adweshtaa sarvabhootaanaam maitrah karuna eva cha;\nNirmamo nirahankaarah samaduhkhasukhah kshamee.\nSantushtah satatam yogee yataatmaa dridhanishchayah;\nMayyarpitamanobuddhiryo madbhaktah sa me priyah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष सब भूतों में द्वेष भाव से रहित, स्वार्थ रहित सबका प्रेमी और हेतु रहित दयालु है तथा ममता से रहित, अहंकार से रहित, सुख-दुःखों की प्राप्ति में सम और क्षमावान है अर्थात अपराध करने वाले को भी अभय देने वाला है तथा जो योगी निरन्तर संतुष्ट है, मन-इन्द्रियों सहित शरीर को वश में किए हुए है और मुझमें दृढ़ निश्चय वाला है- वह मुझमें अर्पण किए हुए मन-बुद्धिवाला मेरा भक्त मुझको प्रिय है॥13-14॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સર્વ ભૂતો નો દ્વેષ નથી કરતો પરંતુ સર્વ નો મિત્ર છે,જે કરુણા મય છે,જે મમતા રહિત છે,જે અહંકાર રહિત છે,જે સુખ દુઃખ માં સમાન ભાવ રાખે છે ,જે ક્ષમાવાન છે,જે સદા સંતુષ્ટ રહે છે,જે સ્થિર ચિત્ત છે,જેનું મન સંયમિત છે,જે દઢ નિશ્વયી છે અને જેણે પોતાનું મન તથા બુદ્ધિ મને અર્પણ કર્યાં છે એવો મારો ભક્ત મને પ્રિય છે.(૧૩-૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("One who is not envious but is a kind friend to all living entities, who does not think himself a proprietor and is free from false ego, who is equal in both happiness and distress, who is tolerant, always satisfied, self-controlled, and engaged in devotional service with determination, his mind and intelligence fixed on Me—such a devotee of Mine is very dear to Me.(13-14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यस्मान्नोद्विजते लोको लोकान्नोद्विजते च यः।\nहर्षामर्षभयोद्वेगैर्मुक्तो यः स च मे प्रियः॥");
        } else if (i == 0) {
            this.tv1.setText("યસ્માન્નોદ્વિજતે લોકો લોકાન્નોદ્વિજતે ચ યઃ,\nહર્ષામર્ષભયોદ્વેગૈર્મુક્તો યઃ સ ચ મે પ્રિયઃ.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Yasmaannodwijate loko lokaannodwijate cha yah;\nHarshaamarshabhayodwegairmukto yah sa cha me priyah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जिससे कोई भी जीव उद्वेग को प्राप्त नहीं होता और जो स्वयं भी किसी जीव से उद्वेग को प्राप्त नहीं होता तथा जो हर्ष, अमर्ष (दूसरे की उन्नति को देखकर संताप होने का नाम 'अमर्ष' है), भय और उद्वेगादि से रहित है वह भक्त मुझको प्रिय है॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેનાથી લોકોને સંતાપ થતો નથી તથા લોકો ના સંસર્ગ થી જેને સંતાપ થતો નથી, તેમજ જે હર્ષ ,અદેખાઈ ,ભય તથા ઉદ્વેગ થી મુક્ત છે તે મને પ્રિય છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("He for whom no one is put into difficulty and who is not disturbed by anyone, who is equipoised in happiness and distress, fear and anxiety, is very dear to Me.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनपेक्षः शुचिर्दक्ष उदासीनो गतव्यथः।\nसर्वारम्भपरित्यागी यो मद्भक्तः स मे प्रियः॥");
        } else if (i == 0) {
            this.tv1.setText("અનપેક્ષઃ શુચિર્દક્ષ ઉદાસીનો ગતવ્યથઃ,\nસર્વારમ્ભપરિત્યાગી યો મદ્ભક્તઃ સ મે પ્રિયઃ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Anapekshah shuchirdaksha udaaseeno gatavyathah;\nSarvaarambhaparityaagee yo madbhaktah sa me priyah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष आकांक्षा से रहित, बाहर-भीतर से शुद्ध चतुर, पक्षपात से रहित और दुःखों से छूटा हुआ है- वह सब आरम्भों का त्यागी मेरा भक्त मुझको प्रिय है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારો  જે ભક્ત સ્પૃહારહિત ,આંતર-બાહ્ય રીતે પવિત્ર,દક્ષ,ઉદાસીન,વ્યથારહિત અને સર્વ આરંભ નો ત્યાગ કરનારો છે તે મને પ્રિય છે.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("My devotee who is not dependent on the ordinary course of activities, who is pure, expert, without cares, free from all pains, and not striving for some result, is very dear to Me.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यो न हृष्यति न द्वेष्टि न शोचति न काङ्‍क्षति।\nशुभाशुभपरित्यागी भक्तिमान्यः स मे प्रियः॥");
        } else if (i == 0) {
            this.tv1.setText("યો ન હૃષ્યતિ ન દ્વેષ્ટિ ન શોચતિ ન કાઙ્ક્ષતિ,\nશુભાશુભપરિત્યાગી ભક્તિમાન્યઃ સ મે પ્રિયઃ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Yona hrishyati na dweshti na shochati na kaangkshati;\nShubhaashubhaparityaagee bhaktimaan yah sa me priyah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो न कभी हर्षित होता है, न द्वेष करता है, न शोक करता है, न कामना करता है तथा जो शुभ और अशुभ सम्पूर्ण कर्मों का त्यागी है- वह भक्तियुक्त पुरुष मुझको प्रिय है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે હર્ષ પામતો નથી ,જે દ્વેષ કરતો નથી,જે ઈચ્છા કરતો નથી,જે શુભ અને અશુભનો ત્યાગ કરનારો ભક્તિમાન છે તે મને પ્રિય છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("One who neither rejoices nor grieves, who neither laments nor desires, and who renounces both auspicious and inauspicious things—such a devotee is very dear to Me.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18_19() {
        setTitle("Shlok 18,19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("समः शत्रौ च मित्रे च तथा मानापमानयोः।\nशीतोष्णसुखदुःखेषु समः सङ्‍गविवर्जितः॥\nतुल्यनिन्दास्तुतिर्मौनी सन्तुष्टो येन केनचित्‌।\nअनिकेतः स्थिरमतिर्भक्तिमान्मे प्रियो नरः॥");
        } else if (i == 0) {
            this.tv1.setText("સમઃ શત્રૌ ચ મિત્રે ચ તથા માનાપમાનયોઃ,\nશીતોષ્ણસુખદુઃખેષુ સમઃ સઙ્ગવિવર્જિતઃ.(૧૮)\nતુલ્યનિન્દાસ્તુતિર્મૌની સન્તુષ્ટો યેનકેનચિત્,\nઅનિકેતઃ સ્થિરમતિર્ભક્તિમાન્મે પ્રિયો નરઃ.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Samah shatrau cha mitre cha tathaa maanaapamaanayoh;\nSheetoshnasukhaduhkheshu samah sangavivarjitah.\nTulyanindaastutirmaunee santushto yena kenachit:\nAniketah sthiramatir bhaktimaan me priyo narah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो शत्रु-मित्र में और मान-अपमान में सम है तथा सर्दी, गर्मी और सुख-दुःखादि द्वंद्वों में सम है और आसक्ति से रहित है| जो निंदा-स्तुति को समान समझने वाला, मननशील और जिस किसी प्रकार से भी शरीर का निर्वाह होने में सदा ही संतुष्ट है और रहने के स्थान में ममता और आसक्ति से रहित है- वह स्थिरबुद्धि भक्तिमान पुरुष मुझको प्रिय है| ॥18-19॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે શત્રુ તથા મિત્ર માં સમાનભાવ રાખે છે,માન-અપમાન માં સમ છે ,ટાઢ-તડકો, સુખ-દુઃખ માંસમ છે,તથા સંગ થી રહિત (આસક્તિ વગરનો) છે અને જે નિંદા-સ્તુતિમાં સમાનતાથી વર્તે  છે,જે મૌન ધારણ કરેછે, જે કંઈ મળે તેમાં સંતુષ્ઠ રહેછે,જેનો નિવાસ સ્થિર નથી (સ્થળ ની આસક્તિ નથી) જેની બુદ્ધિ સ્થિર છે તે ભક્તિમાન મનુષ્ય મને પ્રિય છે.(૧૮,૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("One who is equal to friends and enemies, who is equipoised in honor and dishonor, heat and cold, happiness and distress, fame and infamy, who is always free from contaminating association, always silent and satisfied with anything, who doesn’t care for any residence, who is fixed in knowledge and who is engaged in devotional service—such a person is very dear to Me.(18-19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये तु धर्म्यामृतमिदं यथोक्तं पर्युपासते।\nश्रद्धाना मत्परमा भक्तास्तेऽतीव मे प्रियाः॥");
        } else if (i == 0) {
            this.tv1.setText("યે તુ ધર્મ્યામૃતમિદં યથોક્તં પર્યુપાસતે,\nશ્રદ્દધાના મત્પરમા ભક્તાસ્તેતીવ મે પ્રિયાઃ.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Ye tu dharmyaamritamidam yathoktam paryupaasate;\nShraddhadhaanaah matparamaa bhaktaaste’teeva me priyaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु जो श्रद्धायुक्त पुरुष मेरे परायण होकर इस ऊपर कहे हुए धर्ममय अमृत को निष्काम प्रेमभाव से सेवन करते हैं, वे भक्त मुझको अतिशय प्रिय हैं॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("પરંતુ મારામાં શ્રદ્ધા રાખીને અને મારા પરાયણ થઈને મારા જે ભક્તો અત્યાર સુધીમાં વર્ણવેલા ધર્મ રૂપ અમૃત નું સેવન કરેછે તે ભક્તો મને અત્યંત પ્રિય છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Those who follow this imperishable path of devotional service and who completely engage themselves with faith, making Me the supreme goal, are very, very dear to Me.(20)");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay12.this, ShlokListPlay12.this);
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

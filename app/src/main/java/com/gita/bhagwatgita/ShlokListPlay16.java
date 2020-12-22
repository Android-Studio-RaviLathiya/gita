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

public class ShlokListPlay16 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay16.this.favflag == 0) {
                    ShlokListPlay16.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.favflag = 1;
                    shlokListPlay16.save();
                } else if (1 == ShlokListPlay16.this.favflag) {
                    ShlokListPlay16.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay16 shlokListPlay162 = ShlokListPlay16.this;
                    shlokListPlay162.favflag = 0;
                    shlokListPlay162.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ShlokListPlay16.this.calll++;
                ShlokListPlay16.this.slkset();
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
                ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                shlokListPlay16.calll--;
                ShlokListPlay16.this.slkset();
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
        edit.putString("A16" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A16" + this.calll, "0");
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
        if (24 == this.calll) {
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
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.callf1 = 0;
                    shlokListPlay16.slkset();
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
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.callf1 = 2;
                    shlokListPlay16.slkset();
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
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.callf1 = 1;
                    shlokListPlay16.slkset();
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
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.callf2 = 0;
                    shlokListPlay16.slkset();
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
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.callf2 = 2;
                    shlokListPlay16.slkset();
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
                    ShlokListPlay16 shlokListPlay16 = ShlokListPlay16.this;
                    shlokListPlay16.callf2 = 1;
                    shlokListPlay16.slkset();
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
            shlok15_16();
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
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 16");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  १६ \nदैवासुरसम्पद्विभागयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૬ \nદૈવાસુર સંપદ્વિભાગ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 16\ndaivasur sampadvibhag yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, शास्त्रों के नियमों का पालन न करके मनमाने ढंग से जीवन व्यतीत करने वाले तथा आसुरी गुणों वाले व्यक्ति अधम योनियों को प्राप्त होते हैं और आगे भी भवबंधन में पड़े रहते हैं। किन्तु दैवी गुणों से सम्पन्न तथा शास्त्रों को आधार मानकर नियमित जीवन बिताने वाले लोग आध्यात्मिक सिद्धि प्राप्त करते हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૬ માં દૈવી અને આસુરી સંપદ નું અને તેવા મનુષ્યો નું વર્ણન છે. પુરૂષ નો નાશ કરનાર - કામ,ક્રોધ અને મોહ આ ત્રણ છે. એમ બતાવી .કરવા યોગ્ય કે ના કરવા યોગ્ય કર્મો નો નિર્ણય કરવામાં શાસ્ત્ર જ પ્રમાણ છે. માટે તેના મુજબ કરવા યોગ્ય કર્મ કરવા તે જ યોગ્ય છે. એમ કહ્યું છે.");
        } else if (2 == i) {
            this.tv1.setText("Sixteenth adhyay tells, Those who possess demoniac qualities and who live whimsically, without following the regulations of scripture, attain lower births and further material bondage. But those who possess divine qualities and regulated lives, abiding by scriptural authority, gradually attain spiritual perfection.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nअभयं सत्त्वसंशुद्धिर्ज्ञानयोगव्यवस्थितिः।\nदानं दमश्च यज्ञश्च स्वाध्यायस्तप आर्जवम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઅભયં સત્ત્વસંશુદ્ધિઃ જ્ઞાનયોગવ્યવસ્થિતિઃ,\nદાનં દમશ્ચ યજ્ઞશ્ચ સ્વાધ્યાયસ્તપ આર્જવમ્.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha-\nAbhayam sattwasamshuddhih jnaanayogavyavasthitih;\nDaanam damashcha yajnashcha swaadhyaayastapa aarjavam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्री भगवान बोले- भय का सर्वथा अभाव, अन्तःकरण की पूर्ण निर्मलता, तत्त्वज्ञान के लिए ध्यान योग में निरन्तर दृढ़ स्थिति (परमात्मा के स्वरूप को तत्त्व से जानने के लिए सच्चिदानन्दघन परमात्मा के स्वरूप में एकी भाव से ध्यान की निरन्तर गाढ़ स्थिति का ही नाम 'ज्ञानयोगव्यवस्थिति' समझना चाहिए) और सात्त्विक दान (गीता अध्याय 17 श्लोक 20 में जिसका विस्तार किया है), इन्द्रियों का दमन, भगवान, देवता और गुरुजनों की पूजा तथा अग्निहोत्र आदि उत्तम कर्मों का आचरण एवं वेद-शास्त्रों का पठन-पाठन तथा भगवान्‌ के नाम और गुणों का कीर्तन, स्वधर्म पालन के लिए कष्टसहन और शरीर तथा इन्द्रियों के सहित अन्तःकरण की सरलता॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : અભય, ચિત્તશુદ્ધિ, જ્ઞાન તથા યોગમાં એકનિષ્ઠા, દાન, ઇન્દ્રિયોનો સંયમ, યજ્ઞ, વેદોનું પઠન-મનન, તપ , સરળતા;(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Blessed Lord said:Fearlessness, purity of heart, steadfastness in Yoga and knowledge, alms-giving, control of the senses, sacrifice, study of scriptures, austerity and straightforwardness,(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहिंसा सत्यमक्रोधस्त्यागः शान्तिरपैशुनम्‌।\nदया भूतेष्वलोलुप्त्वं मार्दवं ह्रीरचापलम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("અહિંસા સત્યમક્રોધસ્ત્યાગઃ શાન્તિરપૈશુનમ્,\nદયા ભૂતેષ્વલોલુપ્ત્વં માર્દવં હ્રીરચાપલમ્.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Ahimsaa satyamakrodhas tyaagah shaantirapaishunam;\nDayaa bhooteshvaloluptwam maardavam hreerachaapalam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मन, वाणी और शरीर से किसी प्रकार भी किसी को कष्ट न देना, यथार्थ और प्रिय भाषण (अन्तःकरण और इन्द्रियों के द्वारा जैसा निश्चय किया हो, वैसे-का-वैसा ही प्रिय शब्दों में कहने का नाम 'सत्यभाषण' है), अपना अपकार करने वाले पर भी क्रोध का न होना, कर्मों में कर्तापन के अभिमान का त्याग, अन्तःकरण की उपरति अर्थात्‌ चित्त की चञ्चलता का अभाव, किसी की भी निन्दादि न करना, सब भूतप्राणियों में हेतुरहित दया, इन्द्रियों का विषयों के साथ संयोग होने पर भी उनमें आसक्ति का न होना, कोमलता, लोक और शास्त्र से विरुद्ध आचरण में लज्जा और व्यर्थ चेष्टाओं का अभाव॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("અહિંસા, સત્ય, અક્રોધ, સંન્યાસ, શાંતિ, પીઠ પાછળ નિંદા ન કરવી તે, સર્વ પ્રાણી માત્ર પર દયા, ઇન્દ્રિયોનું નિર્વિકારપણું, નમ્રતા, લોકલાજ અને સ્થિરતા;(૨)");
        } else if (2 == i2) {
            this.tv2.setText("Harmlessness, truth, absence of anger, renunciation, peacefulness, absence of crookedness, compassion towards beings, uncovetousness, gentleness, modesty, absence of fickleness.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तेजः क्षमा धृतिः शौचमद्रोहोनातिमानिता।\nभवन्ति सम्पदं दैवीमभिजातस्य भारत॥");
        } else if (i == 0) {
            this.tv1.setText("તેજઃ ક્ષમા ધૃતિઃ શૌચમદ્રોહો નાતિમાનિતા,\nભવન્તિ સમ્પદં દૈવીમભિજાતસ્ય ભારત.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Tejah kshamaa dhritih shauchamadroho naatimaanitaa;\nBhavanti sampadam daiveem abhijaatasya bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" तेज (श्रेष्ठ पुरुषों की उस शक्ति का नाम 'तेज' है कि जिसके प्रभाव से उनके सामने विषयासक्त और नीच प्रकृति वाले मनुष्य भी प्रायः अन्यायाचरण से रुककर उनके कथनानुसार श्रेष्ठ कर्मों में प्रवृत्त हो जाते हैं), क्षमा, धैर्य, बाहर की शुद्धि (गीता अध्याय 13 श्लोक 7 की टिप्पणी देखनी चाहिए) एवं किसी में भी शत्रुभाव का न होना और अपने में पूज्यता के अभिमान का अभाव- ये सब तो हे अर्जुन! दैवी सम्पदा को लेकर उत्पन्न हुए पुरुष के लक्षण हैं॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("તેજ, ક્ષમા, ધૈર્ય, પવિત્રતા, અદ્રોહ, નમ્રતા વગેરે બધા -દૈવી ગુણોવાળી સંપત્તિને સંપાદન કરીને જન્મેલા મનુષ્યને પ્રાપ્ત થાય છે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("Vigour, forgiveness, fortitude, purity, absence of hatred, absence of pride these belong to one born in a divine state, O Arjuna!(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दम्भो दर्पोऽभिमानश्च क्रोधः पारुष्यमेव च।\nअज्ञानं चाभिजातस्य पार्थ सम्पदमासुरीम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("દમ્ભો દર્પોભિમાનશ્ચ ક્રોધઃ પારુષ્યમેવ ચ,\nઅજ્ઞાનં ચાભિજાતસ્ય પાર્થ સમ્પદમાસુરીમ્.(૪");
        } else if (2 == i) {
            this.tv1.setText("Dambho darpobhimaanashcha krodhah paarushyameva cha;\nAjnaanam chaabhijaatasya paartha sampadamaasureem.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे पार्थ! दम्भ, घमण्ड और अभिमान तथा क्रोध, कठोरता और अज्ञान भी- ये सब आसुरी सम्पदा को लेकर उत्पन्न हुए पुरुष के लक्षण हैं॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! દંભ, અભિમાન, ગર્વ, ક્રોધ, મર્મભેદક વાણી અને અજ્ઞાન વગેરે લક્ષણો આસુરી સંપત્તિમાં ઉત્પન્ન થયેલા મનુષ્યોમાં રહેલાં હોય છે.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("Pride, arrogance, conceit, anger, harshness and ignorance these qualities belong to those of demoniac nature, O son of Pritha.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दैवी सम्पद्विमोक्षाय निबन्धायासुरी मता।\nमा शुचः सम्पदं दैवीमभिजातोऽसि पाण्डव॥");
        } else if (i == 0) {
            this.tv1.setText("દૈવી સમ્પદ્વિમોક્ષાય નિબન્ધાયાસુરી મતા,\nમા શુચઃ સમ્પદં દૈવીમભિજાતોસિ પાણ્ડવ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Daivee sampadvimokshaaya nibandhaayaasuree mataa;\nMaa shuchah sampadam daiveem abhijaatosi paandava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("दैवी सम्पदा मुक्ति के लिए और आसुरी सम्पदा बाँधने के लिए मानी गई है। इसलिए हे अर्जुन! तू शोक मत कर, क्योंकि तू दैवी सम्पदा को लेकर उत्पन्न हुआ है॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("દૈવી સંપત્તિ મોક્ષ આપનારી છે જયારે આસુરી સંપત્તિ બંધનમાં નાખનારી છે. હે પાંડવ ! તું વિષાદ ન કર, કેમ કે તું દૈવી સંપત્તિ સંપાદન કરીને જન્મેલો છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("The transcendental qualities are conducive to liberation, whereas the demoniac qualities make for bondage. Do not worry, O son of Pandu, for you are born with the divine qualities.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("द्वौ भूतसर्गौ लोकऽस्मिन्दैव आसुर एव च।\nदैवो विस्तरशः प्रोक्त आसुरं पार्थ में श्रृणु॥");
        } else if (i == 0) {
            this.tv1.setText("દ્વૌ ભૂતસર્ગૌ લોકેસ્મિન્ દૈવ આસુર એવ ચ,\nદૈવો વિસ્તરશઃ પ્રોક્ત આસુરં પાર્થ મે શ્રૃણુ.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Dwau bhootasargau lokesmin daiva aasura eva cha;\nDaivo vistarashah proktah aasuram paartha me shrinu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! इस लोक में भूतों की सृष्टि यानी मनुष्य समुदाय दो ही प्रकार का है, एक तो दैवी प्रकृति वाला और दूसरा आसुरी प्रकृति वाला। उनमें से दैवी प्रकृति वाला तो विस्तारपूर्वक कहा गया, अब तू आसुरी प्रकृति वाले मनुष्य समुदाय को भी विस्तारपूर्वक मुझसे सुन॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! આ લોકમાં પ્રાણીઓના બે જ પ્રકારના સ્વભાવ છે. દૈવી સ્વભાવ અને આસુરી સ્વભાવ, એમાં દૈવી પ્રકાર મેં તને વિસ્તાર પૂર્વક કહેલો છે. એટલે હવે આસુરી સ્વભાવને સાંભળ.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, in this world there are two kinds of created beings. One is called the divine and the other demoniac. I have already explained to you at length the divine qualities. Now hear from Me of the demoniac.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रवृत्तिं च निवृत्तिं च जना न विदुरासुराः।\nन शौचं नापि चाचारो न सत्यं तेषु विद्यते॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રવૃત્તિં ચ નિવૃત્તિં ચ જના ન વિદુરાસુરાઃ,\nન શૌચં નાપિ ચાચારો ન સત્યં તેષુ વિદ્યતે.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Pravrittim cha nivrittim cha janaa na viduraasuraah;\nNa shaucham naapi chaachaaro na satyam teshu vidyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आसुर स्वभाव वाले मनुष्य प्रवृत्ति और निवृत्ति- इन दोनों को ही नहीं जानते। इसलिए उनमें न तो बाहर-भीतर की शुद्धि है, न श्रेष्ठ आचरण है और न सत्य भाषण ही है॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("આસુરી વૃતિવાળા માનવીઓ પ્રવૃત્તિ તથા નિવૃતિને સમજતા નથી. અને તેમનામાં પ્રવિત્રતા હોતી નથી. તેમનામાં આચાર અને સત્યનો પણ અભાવ હોય છે.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are demoniac do not know what is to be done and what is not to be done. Neither cleanliness nor proper behavior nor truth is found in them.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("असत्यमप्रतिष्ठं ते जगदाहुरनीश्वरम्‌।\nअपरस्परसम्भूतं किमन्यत्कामहैतुकम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("અસત્યમપ્રતિષ્ઠં તે જગદાહુરનીશ્વરમ્,\nઅપરસ્પરસમ્ભૂતં કિમન્યત્કામહૈતુકમ્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Asatyamapratishtham te jagadaahuraneeshwaram;\nAparasparasambhootam kimanyat kaamahaitukam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  वे आसुरी प्रकृति वाले मनुष्य कहा करते हैं कि जगत्‌ आश्रयरहित, सर्वथा असत्य और बिना ईश्वर के, अपने-आप केवल स्त्री-पुरुष के संयोग से उत्पन्न है, अतएव केवल काम ही इसका कारण है। इसके सिवा और क्या है?॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે આસુરી મનુષ્યો જગતને અસત્ય, અપ્રતિષ્ઠિત, ઈશ્વર વગરનું, એક બીજાના સંયોગથી ઉત્પન્ન થયેલું, કામના હેતુ વાળું કહે છે. તેઓ માને છે કે આ જગતનું કામના હેતુથી ભિન્ન અન્ય શું કારણ હોઈ શકે?(૮)");
        } else if (2 == i2) {
            this.tv2.setText("They say that this world is unreal, with no foundation, no God in control. They say it is produced of sex desire and has no cause other than lust.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतां दृष्टिमवष्टभ्य नष्टात्मानोऽल्पबुद्धयः।\nप्रभवन्त्युग्रकर्माणः क्षयाय जगतोऽहिताः॥");
        } else if (i == 0) {
            this.tv1.setText("એતાં દૃષ્ટિમવષ્ટભ્ય નષ્ટાત્માનોલ્પબુદ્ધયઃ,\nપ્રભવન્ત્યુગ્રકર્માણઃ ક્ષયાય જગતોહિતાઃ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Etaam drishtimavashtabhya nashtaatmaanolpabuddhayah;\nPrabhavantyugrakarmaanah kshayaaya jagatohitaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस मिथ्या ज्ञान को अवलम्बन करके- जिनका स्वभाव नष्ट हो गया है तथा जिनकी बुद्धि मन्द है, वे सब अपकार करने वाले क्रुरकर्मी मनुष्य केवल जगत्‌ के नाश के लिए ही समर्थ होते हैं॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("આવા નાસ્તિક મતનો આશ્રય કરીને પરલોકના સાધનોથી ભ્રષ્ટ થયેલાં, અલ્પબુદ્ધિ વાળા, હિંસાદિ ઉગ્ર કર્મો કરનારા તે આસુરી મનુષ્યો જગતના નાશ માટે જ પ્રવર્તે છે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("Following such conclusions, the demoniac, who are lost to themselves and who have no intelligence, engage in unbeneficial, horrible works meant to destroy the world.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("काममाश्रित्य दुष्पूरं दम्भमानमदान्विताः।\nमोहाद्‍गृहीत्वासद्ग्राहान्प्रवर्तन्तेऽशुचिव्रताः॥");
        } else if (i == 0) {
            this.tv1.setText("કામમાશ્રિત્ય દુષ્પૂરં દમ્ભમાનમદાન્વિતાઃ,\nમોહાદ્ગૃહીત્વાસદ્ગ્રાહાન્પ્રવર્તન્તેશુચિવ્રતાઃ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Kaamamaashritya dushpooram dambhamaanamadaanvitaah;\nMohaadgriheetvaasadgraahaan pravartanteshuchivrataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे दम्भ, मान और मद से युक्त मनुष्य किसी प्रकार भी पूर्ण न होने वाली कामनाओं का आश्रय लेकर, अज्ञान से मिथ्या सिद्धांतों को ग्रहण करके भ्रष्ट आचरणों को धारण करके संसार में विचरते हैं॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("તૃપ્ત ન કરી શકાય એવા કામનો આશ્રય કરીને તેઓ દંભ,માન તથા મદથી યુક્ત થયેલા,અપવિત્ર વ્રતવાળા, અજ્ઞાનથી અશુભ નિયમોને ગ્રહણ કરીને વેદ વિરુદ્ધ કર્મો કરે છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("Taking shelter of insatiable lust and absorbed in the conceit of pride and false prestige, the demoniac, thus illusioned, are always sworn to unclean work, attracted by the impermanent.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("चिन्तामपरिमेयां च प्रलयान्तामुपाश्रिताः।\nकामोपभोगपरमा एतावदिति निश्चिताः॥");
        } else if (i == 0) {
            this.tv1.setText("ચિન્તામપરિમેયાં ચ પ્રલયાન્તામુપાશ્રિતાઃ,\nકામોપભોગપરમા તાવદિતિ નિશ્ચિતાઃ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Chintaamaparimeyaam cha pralayaantaamupaashritaah;\nKaamopabhogaparamaa etaavaditi nishchitaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" तथा वे मृत्युपर्यन्त रहने वाली असंख्य चिन्ताओं का आश्रय लेने वाले, विषयभोगों के भोगने में तत्पर रहने वाले और 'इतना ही सुख है' इस प्रकार मानने वाले होते हैं॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("તથા મૃત્યુ પ્રયન્ત અપરિચિત ચિંતાનો આશ્રય કરનારા, વિષયભોગને પરમ પુરુષાર્થ માનનારા એ પ્રમાણે નિશ્વય કરનારા હોય છે.(૧૧)    ");
        } else if (2 == i2) {
            this.tv2.setText("Giving themselves over to immeasurable cares ending only with death, regarding gratification of lust as their highest aim, and feeling sure that that is all.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आशापाशशतैर्बद्धाः कामक्रोधपरायणाः।\nईहन्ते कामभोगार्थमन्यायेनार्थसञ्चयान्‌॥");
        } else if (i == 0) {
            this.tv1.setText("આશાપાશશતૈર્બદ્ધાઃ કામક્રોધપરાયણાઃ,\nઈહન્તે કામભોગાર્થમન્યાયેનાર્થસઞ્ચયાન્(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Aashaapaashashatairbaddhaah kaamakrodhaparaayanaah;\nEehante kaamabhogaartha manyaayenaarthasanchayaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" वे आशा की सैकड़ों फाँसियों से बँधे हुए मनुष्य काम-क्रोध के परायण होकर विषय भोगों के लिए अन्यायपूर्वक धनादि पदार्थों का संग्रह करने की चेष्टा करते हैं॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("આશારૂપી સેંકડો પાશ વડે બંધાયેલા, કામ તથા ક્રોધમાં તત્પર રહેનારા તેઓ વિષયભોગ ભોગવવા અને અન્યાય થી ધનનો સંચય ઇચ્છનારા હોય છે.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("Bound by a hundred ties of hope, given over to lust and anger, they strive to obtain by unlawful means hoards of wealth for sensual enjoyment.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इदमद्य मया लब्धमिमं प्राप्स्ये मनोरथम्‌।\nइदमस्तीदमपि मे भविष्यति पुनर्धनम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("ઇદમદ્ય મયા લબ્ધમિમં પ્રાપ્સ્યે મનોરથમ્,\nઇદમસ્તીદમપિ મે ભવિષ્યતિ પુનર્ધનમ્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Idamadya mayaa labdham imam praapsye manoratham;\nIdamasteedamapi me bhavishyati punardhanam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  वे सोचा करते हैं कि मैंने आज यह प्राप्त कर लिया है और अब इस मनोरथ को प्राप्त कर लूँगा। मेरे पास यह इतना धन है और फिर भी यह हो जाएगा॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("આમે આજે મેળવ્યું, કાલે હું આ સાધ્ય કરીશ,આટલું ધન હાલ મારી પાસે છે.અને બીજું પણ ફરીથી વધારે મળવાનું છે.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("This has been gained by me today; this desire I shall obtain; this is mine and this wealth too shall be mine in future.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("असौ मया हतः शत्रुर्हनिष्ये चापरानपि।\nईश्वरोऽहमहं भोगी सिद्धोऽहं बलवान्सुखी॥");
        } else if (i == 0) {
            this.tv1.setText("અસૌ મયા હતઃ શત્રુર્હનિષ્યે ચાપરાનપિ,\nઈશ્વરોહમહં ભોગી સિદ્ધોહં બલવાન્સુખી.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Asau mayaa hatah shatrur hanishye chaaparaanapi;\nEeshwarohamaham bhogee siddhoham balavaan sukhee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वह शत्रु मेरे द्वारा मारा गया और उन दूसरे शत्रुओं को भी मैं मार डालूँगा। मैं ईश्वर हूँ, ऐश्र्वर्य को भोगने वाला हूँ। मै सब सिद्धियों से युक्त हूँ और बलवान्‌ तथा सुखी हूँ॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ શત્રુને મેં હણ્યો અને બીજાઓને પણ હણીશ.હું અતિ સમર્થ છું,હું ઈશ્વર છું,હું ભોગી છું,હું સિદ્ધિ છું. હું બળવાન અને સુખી છું.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("That enemy has been slain by me and others also I shall slay. I am the lord; I enjoy; I am perfect, powerful and happy.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15_16() {
        setTitle("Shlok 15,16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आढयोऽभिजनवानस्मि कोऽन्योऽस्ति सदृशो मया।\nयक्ष्ये दास्यामि मोदिष्य इत्यज्ञानविमोहिताः॥\nअनेकचित्तविभ्रान्ता मोहजालसमावृताः।\nप्रसक्ताः कामभोगेषु पतन्ति नरकेऽशुचौ॥");
        } else if (i == 0) {
            this.tv1.setText("આઢ્યોભિજનવાનસ્મિ કોન્યોસ્તિ સદૃશો મયા,\nયક્ષ્યે દાસ્યામિ મોદિષ્ય ઇત્યજ્ઞાનવિમોહિતાઃ.\nઅનેકચિત્તવિભ્રાન્તા મોહજાલસમાવૃતાઃ,\nપ્રસક્તાઃ કામભોગેષુ પતન્તિ નરકેશુચૌ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Aadhyobhijanavaanasmi konyosti sadrisho mayaa;\nYakshye daasyaami modishye ityajnaanavimohitaah.\nAnekachittavibhraantaah mohajaalasamaavritaah;\nPrasaktaah kaamabhogeshu patanti narakeshuchau.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं बड़ा धनी और बड़े कुटुम्ब वाला हूँ। मेरे समान दूसरा कौन है? मैं यज्ञ करूँगा, दान दूँगा और आमोद-प्रमोद करूँगा। इस प्रकार अज्ञान से मोहित रहने वाले तथा अनेक प्रकार से भ्रमित चित्त वाले मोहरूप जाल से समावृत और विषयभोगों में अत्यन्त आसक्त आसुरलोग महान्‌ अपवित्र नरक में गिरते हैं॥15-16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું ધનાઢ્ય છું, કુલીન છું, આ જગત માં મારા જેવો બીજો કોણ છે? હું યજ્ઞ કરનારાઓના કર્મોમાં અગ્રણી બનીશ. નટાદિ લોકોને વિશેષ ધન આપીશ અને આનંદ મેળવીશ.આમ તેઓ અતિ મૂઢ થઇ બક્યા કરે છે.હું ધનવાન છું,હું કુળવાન  છું, મારા જેવો અન્ય કોણ હોઈ શકે ? હું યજ્ઞ કરીશ,હું દાન આપીશ, આ પ્રકારે આસુરી મનુષ્ય અજ્ઞાનમાં મોહ પામેલા હોય છે.(૧૫,૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("I am rich and born in a noble family. Who else is equal to me? I will sacrifice. I will give (charity). I will rejoice Bewildered by many a fancy, entangled in the snare of delusion, addicted to the gratification of lust, they fall into a foul hell.(15-16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आत्मसम्भाविताः स्तब्धा धनमानमदान्विताः।\nयजन्ते नामयज्ञैस्ते दम्भेनाविधिपूर्वकम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("આત્મસમ્ભાવિતાઃ સ્તબ્ધા ધનમાનમદાન્વિતાઃ,\nયજન્તે નામયજ્ઞૈસ્તે દમ્ભેનાવિધિપૂર્વકમ્.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Aatmasambhaavitaah stabdhaa dhanamaanamadaanvitaah;\nYajante naamayajnaiste dambhenaavidhipoorvakam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" वे अपने-आपको ही श्रेष्ठ मानने वाले घमण्डी पुरुष धन और मान के मद से युक्त होकर केवल नाममात्र के यज्ञों द्वारा पाखण्ड से शास्त्रविधिरहित यजन करते हैं॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("પોતેજ પોતાની પ્રશંસા કરનાર, અક્કડ થઈને વર્તનાર તથા ધન અને માનના મદથી ઉન્મત્ત બનેલા આવા મનુષ્યો શાસ્ત્રવિધિ છોડી કે બળ દંભથી જ યજ્ઞકાર્યો  કરે છે.(૧૭) ");
        } else if (2 == i2) {
            this.tv2.setText("Self-complacent and always impudent, deluded by wealth and false prestige, they sometimes proudly perform sacrifices in name only, without following any rules or regulations.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहङ्‍कारं बलं दर्पं कामं क्रोधं च संश्रिताः।\nमामात्मपरदेहेषु प्रद्विषन्तोऽभ्यसूयकाः॥");
        } else if (i == 0) {
            this.tv1.setText("અહઙ્કારં બલં દર્પં કામં ક્રોધં ચ સંશ્રિતાઃ,\nમામાત્મપરદેહેષુ પ્રદ્વિષન્તોભ્યસૂયકાઃ.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Ahankaaram balam darpam kaamam krodham cha samshritaah;\nMaamaatmaparadeheshu pradwishantobhyasooyakaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  वे अहंकार, बल, घमण्ड, कामना और क्रोधादि के परायण और दूसरों की निन्दा करने वाले पुरुष अपने और दूसरों के शरीर में स्थित मुझ अन्तर्यामी से द्वेष करने वाले होते हैं॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("અહંતા, બળ,ગર્વ, કામ તથાં ક્રોધનો આશ્રય લઇ તેઓ તેમના તથા અન્યના દેહમાં રહેલા મારો (ઈશ્વરનો ) દ્વેષ કરે છે.વળી તેઓ અન્યનો ઉત્કર્ષ સહન કરી શકતા નથી.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("Bewildered by false ego, strength, pride, lust and anger, the demons become envious of the Supreme Personality of Godhead, who is situated in their own bodies and in the bodies of others, and blaspheme against the real religion.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तानहं द्विषतः क्रूरान्संसारेषु नराधमान्‌।\nक्षिपाम्यजस्रमशुभानासुरीष्वेव योनिषु॥");
        } else if (i == 0) {
            this.tv1.setText("તાનહં દ્વિષતઃ ક્રૂરાન્સંસારેષુ નરાધમાન્,\nક્ષિપામ્યજસ્રમશુભાનાસુરીષ્વેવ યોનિષુ.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Taanaham dwishatah krooraan samsaareshu naraadhamaan;\nKshipaamyajasram ashubhaan aasureeshweva yonishu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उन द्वेष करने वाले पापाचारी और क्रूरकर्मी नराधमों को मैं संसार में बार-बार आसुरी योनियों में ही डालता हूँ॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે સાધુઓનો દ્વેષ કરનારા, પાપી નરાધમો ને હું સંસારમાં આસુરી યોનિમાં જ નિરંતર વાળું છું.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are envious and mischievous, who are the lowest among men, I perpetually cast into the ocean of material existence, into various demoniac species of life.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आसुरीं योनिमापन्ना मूढा जन्मनि जन्मनि।\nमामप्राप्यैव कौन्तेय ततो यान्त्यधमां गतिम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("અસુરીં યોનિમાપન્ના મૂઢા જન્મનિ જન્મનિ,\nમામપ્રાપ્યૈવ કૌન્તેય તતો યાન્ત્યધમાં ગતિમ્.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Aasureem yonimaapannaa moodhaa janmani janmani;\nMaamapraapyaiva kaunteya tato yaantyadhamaam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! वे मूढ़ मुझको न प्राप्त होकर ही जन्म-जन्म में आसुरी योनि को प्राप्त होते हैं, फिर उससे भी अति नीच गति को ही प्राप्त होते हैं अर्थात्‌ घोर नरकों में पड़ते हैं॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય ! આસુરી યોનિને પ્રાપ્ત થયેલા તે પુરુષો જન્મોજન્મ મૂઢ થતાં થતાં મને ન પામતા ઉતરોત્તર અધમ ગતિને પ્રાપ્ત થતા જાય છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Attaining repeated birth amongst the species of demoniac life, O son of Kunti, such persons can never approach Me. Gradually they sink down to the most abominable type of existence.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्रिविधं नरकस्येदं द्वारं नाशनमात्मनः।\nकामः क्रोधस्तथा लोभस्तस्मादेतत्त्रयं त्यजेत्‌॥");
        } else if (i == 0) {
            this.tv1.setText("ત્રિવિધં નરકસ્યેદં દ્વારં નાશનમાત્મનઃ,\nકામઃ ક્રોધસ્તથા લોભસ્તસ્માદેતત્ત્રયં ત્યજેત્.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Trividham narakasyedam dwaaram naashanamaatmanah;\nKaamah krodhastathaa lobhas tasmaadetat trayam tyajet.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("काम, क्रोध तथा लोभ- ये तीन प्रकार के नरक के द्वार ( सर्व अनर्थों के मूल और नरक की प्राप्ति में हेतु होने से यहाँ काम, क्रोध और लोभ को 'नरक के द्वार' कहा है) आत्मा का नाश करने वाले अर्थात्‌ उसको अधोगति में ले जाने वाले हैं। अतएव इन तीनों को त्याग देना चाहिए॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("કામ,  ક્રોધ અને લોભ એ જીવને કોઈ પણ પ્રકારના પુરુષાર્થની પ્રાપ્તીન થવા દેનારા નરક નાં ત્રણ દ્વારો છે.માટે એ  ત્રણેનો ત્યાગ કરવો.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("There are three gates leading to this helllust, anger and greed. Every sane man should give these up, for they lead to the degradation of the soul.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतैर्विमुक्तः कौन्तेय तमोद्वारैस्त्रिभिर्नरः।\nआचरत्यात्मनः श्रेयस्ततो याति परां गतिम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("એતૈર્વિમુક્તઃ કૌન્તેય તમોદ્વારૈસ્ત્રિભિર્નરઃ,\nઆચરત્યાત્મનઃ શ્રેયસ્તતો યાતિ પરાં ગતિમ્.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Etairvimuktah kaunteya tamodwaaraistribhirnarah;\nAacharatyaatmanah shreyas tato yaati paraam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! इन तीनों नरक के द्वारों से मुक्त पुरुष अपने कल्याण का आचरण करता है (अपने उद्धार के लिए भगवदाज्ञानुसार बरतना ही 'अपने कल्याण का आचरण करना' है), इससे वह परमगति को जाता है अर्थात्‌ मुझको प्राप्त हो जाता है॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય ! નરક નાં આ ત્રણે દ્વારોથી જે મનુષ્ય મુક્ત થઇ જાય છે તે પોતાનું કલ્યાણ સાધે છે અને ઉતમ ગતિ ને પ્રાપ્ત થાય છે.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("The man who has escaped these three gates of hell, O son of Kunti, performs acts conducive to self-realization and thus gradually attains the supreme destination.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यः शास्त्रविधिमुत्सृज्य वर्तते कामकारतः।\nन स सिद्धिमवाप्नोति न सुखं न परां गतिम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("યઃ શાસ્ત્રવિધિમુત્સૃજ્ય વર્તતે કામકારતઃ,\nન સ સિદ્ધિમવાપ્નોતિ ન સુખં ન પરાં ગતિમ્.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Yah shaastravidhimutsrijya vartate kaamakaaratah;\nNa sa siddhimavaapnoti na sukham na paraam gatim.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष शास्त्र विधि को त्यागकर अपनी इच्छा से मनमाना आचरण करता है, वह न सिद्धि को प्राप्त होता है, न परमगति को और न सुख को ही॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે શાસ્ત્રોક્ત વિધિ છોડી પોતાની ઈચ્છા પ્રમાણે વર્તે છે, તેને સિદ્ધિ, સુખ અને ઉત્તમ ગતિ પ્રાપ્ત થતી નથી.(૨૩) ");
        } else if (2 == i2) {
            this.tv2.setText("He who discards scriptural injunctions and acts according to his own whims attains neither perfection, nor happiness, nor the supreme destination.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्माच्छास्त्रं प्रमाणं ते कार्याकार्यव्यवस्थितौ।\nज्ञात्वा शास्त्रविधानोक्तं कर्म कर्तुमिहार्हसि॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માચ્છાસ્ત્રં પ્રમાણં તે કાર્યાકાર્યવ્યવસ્થિતૌ,\nજ્ઞાત્વા શાસ્ત્રવિધાનોક્તં કર્મ કર્તુમિહાર્હસિ.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaat shaastram pramaanam te kaaryaakaaryavyavasthitau;\nJnaatwaa shaastravidhaanoktam karma kartumihaarhasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इससे तेरे लिए इस कर्तव्य और अकर्तव्य की व्यवस्था में शास्त्र ही प्रमाण है। ऐसा जानकर तू शास्त्र विधि से नियत कर्म ही करने योग्य है॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("માટે કાર્ય અને અકાર્ય નો નિર્ણય કરવામાં તારે માટે શાસ્ત્ર એ જ પ્રમાણ છે. શાસ્ત્રમાં કહ્યા અનુસાર કર્મો જાણી લઈને તેનું આ લોકમાં આચરણ કરવું એ જ તારા માટે ઉચિત છે. (૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("One should therefore understand what is duty and what is not duty by the regulations of the scriptures. Knowing such rules and regulations, one should act so that he may gradually be elevated.(24)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay16.this, ShlokListPlay16.this);
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

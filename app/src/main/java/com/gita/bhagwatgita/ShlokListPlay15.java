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

public class ShlokListPlay15 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay15.this.favflag == 0) {
                    ShlokListPlay15.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.favflag = 1;
                    shlokListPlay15.save();
                } else if (1 == ShlokListPlay15.this.favflag) {
                    ShlokListPlay15.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay15 shlokListPlay152 = ShlokListPlay15.this;
                    shlokListPlay152.favflag = 0;
                    shlokListPlay152.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay15.this.calll++;
                ShlokListPlay15.this.slkset();
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
                ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                shlokListPlay15.calll--;
                ShlokListPlay15.this.slkset();
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
        edit.putString("A15" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A15" + this.calll, "0");
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
        if (21 == this.calll) {
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
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.callf1 = 0;
                    shlokListPlay15.slkset();
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
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.callf1 = 2;
                    shlokListPlay15.slkset();
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
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.callf1 = 1;
                    shlokListPlay15.slkset();
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
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.callf2 = 0;
                    shlokListPlay15.slkset();
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
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.callf2 = 2;
                    shlokListPlay15.slkset();
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
                    ShlokListPlay15 shlokListPlay15 = ShlokListPlay15.this;
                    shlokListPlay15.callf2 = 1;
                    shlokListPlay15.slkset();
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
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 15");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १५ \nपुरुषोत्तमयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૫\nપુરુષોત્તમ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 15\npurushottam yoga \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा हैं की, वैदिक ज्ञान हा चरम लक्ष्य अपने आपको भौतिक जगत के पास से विलग करना तथा कृष्ण को भगवान मानना है। जो कृष्ण के परम स्वरूप को समझ लेता है, वह उनकी शरण ग्रहण करके उनकी भक्ति में लग जाता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૫ માં સંસાર રૂપી પીપળાના વૃક્ષ ના 'મૂળ' ઉપર છે અને શાખા ઓ નીચે છે, તથા તેનો કદી નાશ થતો નથી, એમ કહ્યું છે. વેદ ના છંદો તેના પાંદડા છે, આ રહસ્ય ને જાણનાર વેદવેતા છે. અને ક્ષર, અક્ષર અને પુરુષોત્તમ પુરુષો ને સમજાવી ગુહ્યત્તમ -અધ્યાત્મ શાસ્ત્ર સમજાવ્યું છે.");
        } else if (2 == i) {
            this.tv1.setText("Fifteenth adhyay tells, The ultimate purpose of Vedic knowledge is to detach one self from the entanglement of the material world and to understand Lord Krishna as the Supreme Personality of Godhead. One who understands Krishna 's supreme identity surrenders unto Him and engages in His devotional service.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nऊर्ध्वमूलमधः शाखमश्वत्थं प्राहुरव्ययम्‌ ।\nछन्दांसि यस्य पर्णानि यस्तं वेद स वेदवित्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ભગવાન ઉવાચ-   \nઊર્ધ્વમૂલમધઃશાખમશ્વત્થં પ્રાહુરવ્યયમ્,\nછન્દાંસિ યસ્ય પર્ણાનિ યસ્તં વેદ સ વેદવિત્.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nOordhwamoolam adhahshaakham ashwattham praahuravyayam;\nCchandaamsi yasya parnaani yastam veda sa vedavit.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्री भगवान बोले- आदिपुरुष परमेश्वर रूप मूल वाले और ब्रह्मारूप मुख्य शाखा वजिस संसार रूप पीपल वृक्ष को अविनाशीालकहते हैं, तथा वेद जिसके पत्तेे  कहे गए हैं, उस संसार रूप वृक्ष को जो पुरुष मूलसहित सत्त्व से जानता है, वह वेद के तात्पर्य को जानने वाला है।॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : આ સંસારરૂપી પીપળાના વૃક્ષનાં મૂળ ઉપર તરફ અને શાખાઓ નીચે તરફ છે.એનો કદી નાશ થતો નથી.છંદોબદ્ધ વેદ એ વૃક્ષના પાન છે. જે આ રહસ્ય ને જાણે છે તે જ વેદવત્તા છે.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: It is said that there is an imperishable banyan tree that has its roots upward and its branches down and whose leaves are the Vedic hymns. One who knows this tree is the knower of the Vedas(1).");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अधश्चोर्ध्वं प्रसृतास्तस्य शाखा गुणप्रवृद्धा विषयप्रवालाः ।\nअधश्च मूलान्यनुसन्ततानि कर्मानुबन्धीनि मनुष्यलोके ॥");
        } else if (i == 0) {
            this.tv1.setText("અધશ્ચોર્ધ્વં પ્રસૃતાસ્તસ્ય શાખાગુણપ્રવૃદ્ધા વિષયપ્રવાલાઃ,\nઅધશ્ચ મૂલાન્યનુસન્તતાનિકર્માનુબન્ધીનિ મનુષ્યલોકે. (૨)");
        } else if (2 == i) {
            this.tv1.setText("Adhashchordhwam prasritaastasya shaakhaah\nGunapravriddhaa vishayapravaalaah;\nAdhashcha moolaanyanusantataani\nKarmaanubandheeni manushyaloke.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उस संसार वृक्ष की तीनों गुणोंरूप जल के द्वारा बढ़ी हुई एवं विषय-भोग रूप कोंपलोंवाली देव, मनुष्य और तिर्यक्‌ आदि योनिरूप शाखाएँ  नीचे और ऊपर सर्वत्र फैली हुई हैं तथा मनुष्य लोक में कर्मों के अनुसार बाँधने वाली अहंता-ममता और वासना रूप जड़ें भी नीचे और ऊपर सभी लोकों में व्याप्त हो रही हैं। ॥2॥ ");
        } else if (i2 == 0) {
            this.tv2.setText("તે વૃક્ષની શાખાઓ સત્વાદિ ગુણોથી વધેલી છે. શબ્દાદિ વિષયોના પાનથી તે ઉપર-નીચે સર્વત્ર પ્રસરેલી છે. નીચે મનુષ્યલોકમાં  આ વૃક્ષના કર્મરૂપી મૂળો એક બીજામાં ગૂંથાઈ રહ્યા છે.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("The branches of this tree extend downward and upward, nourished by the three modes of material nature. The twigs are the objects of the senses. This tree also has roots going down, and these are bound to the fruitive actions of human society.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न रूपमस्येह तथोपलभ्यते नान्तो न चादिर्न च सम्प्रतिष्ठा ।\nअश्वत्थमेनं सुविरूढमूल मसङ्‍गशस्त्रेण दृढेन छित्त्वा ॥");
        } else if (i == 0) {
            this.tv1.setText("ન રૂપમસ્યેહ તથોપલભ્યતેનાન્તો ન ચાદિર્ન ચ સંપ્રતિષ્ઠા,\nઅશ્વત્થમેનં સુવિરૂઢમૂલ-મસઙ્ગશસ્ત્રેણ દૃઢેન છિત્ત્વા.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Na roopamasyeha tathopalabhyate\nNaanto na chaadirna cha sampratishthaa;\nAshwatthamenam suviroodhamoolam\nAsangashastrena dridhena cchittwaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस संसार वृक्ष का स्वरूप जैसा कहा है वैसा यहाँ विचार काल में नहीं पाया जाता कि न तो इसका आदि हैऔर न अन्त हतथा न इसकी अच्छी प्रकार से स्थिति ही है ै इसलिए इस अहंता, ममता और वासनारूप अति दृढ़ मूलों वाले संसार रूप पीपल के वृक्ष को दृढ़ वैराग्य रूप शस्त्र द्वारा काटना है।॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ પીપળાના વૃક્ષનું જે વર્ણન કર્યું છે, તેવું તેનું શુદ્ધ સ્વરૂપ અનુભવાતું નથી.એનો અંત, આદિ તથા સ્થિતિ પણ નથી.આવા બળવાન મૂળવાળા વૃક્ષને દઢ વૈરાગ્યરૂપી શસ્ત્ર વડે જ છેદીને;(૩)  ");
        } else if (2 == i2) {
            this.tv2.setText(". Its form is not perceived here as such, neither its end nor its origin, nor its foundation nor resting place; having cut asunder this firmly-rooted peepul tree with the strong axe of non-attachment,(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ततः पदं तत्परिमार्गितव्यं यस्मिन्गता न निवर्तन्ति भूयः ।\nतमेव चाद्यं पुरुषं प्रपद्ये यतः प्रवृत्तिः प्रसृता पुराणी ॥");
        } else if (i == 0) {
            this.tv1.setText("તતઃ પદં તત્પરિમાર્ગિતવ્યયસ્મિન્ગતા ન નિવર્તન્તિ ભૂયઃ,\nતમેવ ચાદ્યં પુરુષં પ્રપદ્યેયતઃ પ્રવૃત્તિઃ પ્રસૃતા પુરાણી.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Tatah padam tat parimaargitavyam\nYasmin gataa na nivartanti bhooyah;\nTameva chaadyam purusham prapadye\nYatah pravrittih prasritaa puraanee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" उसके पश्चात उस परम-पदरूप परमेश्वर को भलीभाँति खोजना चाहिए, जिसमें गए हुए पुरुष फिर लौटकर संसार में नहीं आते और जिस परमेश्वर से इस पुरातन संसार वृक्ष की प्रवृत्ति विस्तार को प्राप्त हुई है, उसी आदिपुरुष नारायण के मैं शरण हूँ- इस प्रकार दृढ़ निश्चय करके उस परमेश्वर का मनन और निदिध्यासन करना चाहिए॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("ત્યાર પછી તે પરમ પદને શોધવું જોઈએ.જે પદને પામનારા ફરીને આ સંસારમાં આવતા નથી.જેમાંથી આ સંસાર વૃક્ષની અનાદિ પ્રવૃત્તિ પ્રસરેલી છે એવા તે આદ્ય પુરુષને જ શરણે હું પ્રાપ્ત થયો છું.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("Then that goal should be sought after, whither having gone none returns again. Seek refuge in that Primeval Purusha whence streamed forth the ancient activity or energy.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("निर्मानमोहा जितसङ्गदोषाअध्यात्मनित्या विनिवृत्तकामाः ।\nद्वन्द्वैर्विमुक्ताः सुखदुःखसञ्ज्ञैर्गच्छन्त्यमूढाः पदमव्ययं तत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("નિર્માનમોહા જિતસઙ્ગદોષાઅધ્યાત્મનિત્યા વિનિવૃત્તકામાઃ,\nદ્વન્દ્વૈર્વિમુક્તાઃ સુખદુઃખસંજ્ઞૈ-ર્ગચ્છન્ત્યમૂઢાઃ પદમવ્યયં તત્.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Nirmaanamohaa jitasangadoshaa\nAdhyaatmanityaa vinivrittakaamaah;\nDwandwairvimuktaah sukhaduhkhasamjnair\n Gacchantyamoodhaah padamavyayam tat");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिनका मान और मोह नष्ट हो गया है, जिन्होंने आसक्ति रूप दोष को जीत लिया है, जिनकी परमात्मा के स्वरूप में नित्य स्थिति है और जिनकी कामनाएँ पूर्ण रूप से नष्ट हो गई हैं- वे सुख-दुःख नामक द्वन्द्वों से विमुक्त ज्ञानीजन उस अविनाशी परम पद को प्राप्त होते हैं॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("અહંકાર (અમાની) તથા મોહ વિનાના સંગદોષને જીતનારા પરમાત્મસ્વરૂપનો વિચાર કરવામાં તત્પર, જેમની કામનાઓ શાંત પામી છે તેવા સુખદુઃખરૂપી દ્વંદોથી  મુક્ત થયેલા વિદ્વાનો એ અવિનાશી પદને પામે છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are free from false prestige, illusion and false association, who understand the eternal, who are done with material lust, who are freed from the dualities of happiness and distress, and who, unbewildered, know how to surrender unto the Supreme Person attain to that eternal kingdom.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न तद्भासयते सूर्यो न शशाङ्को न पावकः ।\nयद्गत्वा न निवर्तन्ते तद्धाम परमं मम ॥");
        } else if (i == 0) {
            this.tv1.setText("ન તદ્ભાસયતે સૂર્યો ન શશાઙ્કો ન પાવકઃ,\nયદ્ગત્વા ન નિવર્તન્તે તદ્ધામ પરમં મમ.(૬");
        } else if (2 == i) {
            this.tv1.setText("Na tadbhaasayate sooryo na shashaangko na paavakah;\nYadgatwaa na nivartante taddhaama paramam mama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस परम पद को प्राप्त होकर मनुष्य लौटकर संसार में नहीं आते उस स्वयं प्रकाश परम पद को न सूर्य प्रकाशित कर सकता है, न चन्द्रमा और न अग्नि ही, वही मेरा परम धाम ('परम धाम' का अर्थ गीता अध्याय 8 श्लोक 21 में देखना चाहिए।) है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે પદને પ્રકાશિત કરવા માટે સુર્ય, ચંદ્ર કે અગ્નિ  સમર્થ નથી અને જે પદને પ્રાપ્ત થયેલા લોકો પુનઃ પાછા આવતા નથી તે મારું પરમ પદ છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("That supreme abode of Mine is not illumined by the sun or moon, nor by fire or electricity. Those who reach it never return to this material world.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ममैवांशो जीवलोके जीवभूतः सनातनः ।\nमनः षष्ठानीन्द्रियाणि प्रकृतिस्थानि कर्षति ॥");
        } else if (i == 0) {
            this.tv1.setText("મમૈવાંશો જીવલોકે જીવભૂતઃ સનાતનઃ,\nમનઃષષ્ઠાનીન્દ્રિયાણિ પ્રકૃતિસ્થાનિ કર્ષતિ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Mamaivaamsho jeevaloke jeevabhootah sanaatanah;\nManah shashthaaneendriyaani prakritisthaani karshati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस देह में यह जीवात्मा मेरा ही सनातन अंश है (जैसे विभागरहित स्थित हुआ भी महाकाश घटों में पृथक-पृथक की भाँति प्रतीत होता है, वैसे ही सब भूतों में एकीरूप से स्थित हुआ भी परमात्मा पृथक-पृथक की भाँति प्रतीत होता है, इसी से देह में स्थित जीवात्मा को भगवान ने अपना 'सनातन अंश' कहा है) और वही इन प्रकृति में स्थित मन और पाँचों इन्द्रियों को आकर्षित करता है॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ સંસારમાં મારો જ અંશ સનાતન જીવરૂપે રહેલો છે.પ્રકૃતિમાં રહેલી મન સહિત છ શ્રોતાદિક ઈન્દ્રિયોને તે આકર્ષે છે.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("The living entities in this conditioned world are My eternal fragmental parts. Due to conditioned life, they are struggling very hard with the six senses, which include the mind.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शरीरं यदवाप्नोति यच्चाप्युत्क्रामतीश्वरः ।\nगृहीत्वैतानि संयाति वायुर्गन्धानिवाशयात्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શરીરં યદવાપ્નોતિ યચ્ચાપ્યુત્ક્રામતીશ્વરઃ,\nગૃહીત્વૈતાનિ સંયાતિ વાયુર્ગન્ધાનિવાશયાત્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Shareeram yadavaapnoti yacchaapyutkraamateeshwarah;\nGriheetwaitaani samyaati vaayurgandhaanivaashayaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वायु गन्ध के स्थान से गन्ध को जैसे ग्रहण करके ले जाता है, वैसे ही देहादिका स्वामी जीवात्मा भी जिस शरीर का त्याग करता है, उससे इन मन सहित इन्द्रियों को ग्रहण करके फिर जिस शरीर को प्राप्त होता है- उसमें जाता है॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("વાયુ જેવી રીતે પુષ્પમાંથી સુવાસ લઇ જાય છે તેમ શરીર નો સ્વામી જીવાત્મા જે પૂર્ણ દેહ ત્યાગ કરે છે,તેમાંથી મન સહિત ઈન્દ્રિયોને ગ્રહણકરી જે બીજો દેહ ધારણ કરે છે તેમાં તેમને પોતાની સાથે લઇ જાય છે.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("The living entity in the material world carries his different conceptions of life from one body to another as the air carries aromas. Thus he takes one kind of body and again quits it to take another.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रोत्रं चक्षुः स्पर्शनं च रसनं घ्राणमेव च ।\nअधिष्ठाय मनश्चायं विषयानुपसेवते ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રોત્રં ચક્ષુઃ સ્પર્શનં ચ રસનં ઘ્રાણમેવ ચ,\nઅધિષ્ઠાય મનશ્ચાયં વિષયાનુપસેવતે.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Shrotram chakshuh sparshanam cha rasanam ghraanameva cha;\nAdhishthaaya manashchaayam vishayaanupasevate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" यह जीवात्मा श्रोत्र, चक्षु और त्वचा को तथा रसना, घ्राण और मन को आश्रय करके -अर्थात इन सबके सहारे से ही विषयों का सेवन करता है॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે જીવ કાન, આંખ, ત્વચા,જીભ,નાક વગેરે ઇન્દ્રિયો તથા મનનો આશ્રય કરીને વિષયોનો ઉપભોગ કરે છે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("The living entity, thus taking another gross body, obtains a certain type of ear, eye, tongue, nose and sense of touch, which are grouped about the mind. He thus enjoys a particular set of sense objects.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उत्क्रामन्तं स्थितं वापि भुञ्जानं वा गुणान्वितम्‌ ।\nविमूढा नानुपश्यन्ति पश्यन्ति ज्ञानचक्षुषः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉત્ક્રામન્તં સ્થિતં વાપિ ભુઞ્જાનં વા ગુણાન્વિતમ્,\nવિમૂઢા નાનુપશ્યન્તિ પશ્યન્તિ જ્ઞાનચક્ષુષઃ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Utkraamantam sthitam vaapi bhunjaanam vaa gunaanvitam;\nVimoodhaa naanupashyanti pashyanti jnaanachakshushah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("शरीर को छोड़कर जाते हुए को अथवा शरीर में स्थित हुए को अथवा विषयों को भोगते हुए को इस प्रकार तीनों गुणों से युक्त हुए को भी अज्ञानीजन नहीं जानते, केवल ज्ञानरूप नेत्रों वाले विवेकशील ज्ञानी ही तत्त्व से जानते हैं॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("બીજા દેહમાં જનારો કે દેહમાં નિવાસ કરનારો, શબ્દાદિ વિષયોનો ઉપભોગ કરનારો અથવા સુખદુખાદિ યુક્ત રહેનારો જે જીવ છે તેનું સત્સ્વરૂપ મૂઢજનોને દેખાતું નથી પણ જેમને જ્ઞાનચક્ષુ હોય છે તેમને જ દેખાય છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("The foolish cannot understand how a living entity can quit his body, nor can they understand what sort of body he enjoys under the spell of the modes of nature. But one whose eyes are trained in knowledge can see all this.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यतन्तो योगिनश्चैनं पश्यन्त्यात्मन्यवस्थितम्‌ ।\nयतन्तोऽप्यकृतात्मानो नैनं पश्यन्त्यचेतसः ॥");
        } else if (i == 0) {
            this.tv1.setText("યતન્તો યોગિનશ્ચૈનં પશ્યન્ત્યાત્મન્યવસ્થિતમ્,\nયતન્તોપ્યકૃતાત્માનો નૈનં પશ્યન્ત્યચેતસઃ.(૧૧)\n");
        } else if (2 == i) {
            this.tv1.setText("Yatanto yoginashchainam pashyantyaatmanyavasthitam;\nYatanto’pyakritaatmaano nainam pashyantyachetasah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यत्न करने वाले योगीजन भी अपने हृदय में स्थित इस आत्मा को तत्त्व से जानते हैं, किन्तु जिन्होंने अपने अन्तःकरण को शुद्ध नहीं किया है, ऐसे अज्ञानीजन तो यत्न करते रहने पर भी इस आत्मा को नहीं जानते॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("યત્ન કરનારા યોગીઓ પોતાનામાં રહેલા જીવાત્મા ને જુવેછે અને જેઓ અશુદ્ધ અંત:કરણવાળા અને અવિવેકી છે તેને એ જીવ નું સ્વરૂપ દેખાતું નથી.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("The endeavoring transcendentalists, who are situated in self-realization, can see all this clearly. But those whose minds are not developed and who are not situated in self-realization cannot see what is taking place, though they may try to.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदादित्यगतं तेजो जगद्भासयतेऽखिलम्‌ ।\nयच्चन्द्रमसि यच्चाग्नौ तत्तेजो विद्धि मामकम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યદાદિત્યગતં તેજો જગદ્ભાસયતેખિલમ્,\nયચ્ચન્દ્રમસિ યચ્ચાગ્નૌ તત્તેજો વિદ્ધિ મામકમ્.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Yadaadityagatam tejo jagad bhaasayate’khilam;\nYacchandramasi yacchaagnau tattejo viddhi maamakam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" सूर्य में स्थित जो तेज सम्पूर्ण जगत को प्रकाशित करता है तथा जो तेज चन्द्रमा में है और जो अग्नि में है- उसको तू मेरा ही तेज जान॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("સૂર્યમાં રહેલું તેજ સર્વ જગતને પ્રકાશિત કરેછે અને જે અગ્નિ તથા ચંદ્ર માં પણ રહેલું છે તે તેજ મારું છે એમ તું સમજ (૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("The splendor of the sun, which dissipates the darkness of this whole world, comes from Me. And the splendor of the moon and the splendor of fire are also from Me.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("गामाविश्य च भूतानि धारयाम्यहमोजसा ।\nपुष्णामि चौषधीः सर्वाः सोमो भूत्वा रसात्मकः ॥");
        } else if (i == 0) {
            this.tv1.setText("ગામાવિશ્ય ચ ભૂતાનિ ધારયામ્યહમોજસા,\nપુષ્ણામિ ચૌષધીઃ સર્વાઃ સોમો ભૂત્વા રસાત્મકઃ.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Gaam aavishya cha bhootaani dhaarayaamyaham ojasaa;\nPushnaami chaushadheeh sarvaah somo bhootwaa rasaatmakah");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और मैं ही पृथ्वी में प्रवेश करके अपनी शक्ति से सब भूतों को धारण करता हूँ और रसस्वरूप अर्थात अमृतमय चन्द्रमा होकर सम्पूर्ण ओषधियों को अर्थात वनस्पतियों को पुष्ट करता हूँ॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું જ આ પૃથ્વીમાં પ્રવેશ કરી મારા સામર્થ્યથી સર્વ ભૂતોને ધારણ કરું છું તથા રસાત્મક ચંદ્ર થઈને સર્વ ઔષધિઓને પોષું છું.(૧૩)  ");
        } else if (2 == i2) {
            this.tv2.setText("I enter into each planet, and by My energy they stay in orbit. I become the moon and thereby supply the juice of life to all vegetables.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहं वैश्वानरो भूत्वा प्राणिनां देहमाश्रितः ।\nप्राणापानसमायुक्तः पचाम्यन्नं चतुर्विधम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અહં વૈશ્વાનરો ભૂત્વા પ્રાણિનાં દેહમાશ્રિતઃ,\nપ્રાણાપાનસમાયુક્તઃ પચામ્યન્નં ચતુર્વિધમ્.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Aham vaishwaanaro bhootwaa praaninaam dehamaashritah;\nPraanaapaana samaayuktah pachaamyannam chaturvidham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" मैं ही सब प्राणियों के शरीर में स्थित रहने वाला प्राण और अपान से संयुक्त वैश्वानर अग्नि रूप होकर चार (भक्ष्य, भोज्य, लेह्य और चोष्य, ऐसे चार प्रकार के अन्न होते हैं, उनमें जो चबाकर खाया जाता है, वह 'भक्ष्य' है- जैसे रोटी आदि। जो निगला जाता है, वह 'भोज्य' है- जैसे दूध आदि तथा जो चाटा जाता है, वह 'लेह्य' है- जैसे चटनी आदि और जो चूसा जाता है, वह 'चोष्य' है- जैसे ईख आदि) प्रकार के अन्न को पचाता हूँ॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું પ્રાણીઓના દેહમાં પ્રવેશીને પ્રાણ, અપાન ઈત્યાદિ વાયુમાં મળીને જઠરાગ્નિ બની ચાર પ્રકારના અન્ન નું પાચન કરું છું.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("I am the fire of digestion in the bodies of all living entities, and I join with the air of life, outgoing and incoming, to digest the four kinds of foodstuff.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वस्य चाहं हृदि सन्निविष्टोमत्तः स्मृतिर्ज्ञानमपोहनं च ।\nवेदैश्च सर्वैरहमेव वेद्योवेदान्तकृद्वेदविदेव चाहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વસ્ય ચાહં હૃદિ સન્નિવિષ્ટોમત્તઃ સ્મૃતિર્જ્ઞાનમપોહનં ચ,\nવેદૈશ્ચ સર્વૈરહમેવ વેદ્યોવેદાન્તકૃદ્વેદવિદેવ ચાહમ્.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Sarvasya chaaham hridi sannivishto\nMattah smritir jnaanam apohanam cha;\nVedaischa sarvairahameva vedyo\nVedaantakrid vedavid eva chaaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" मैं ही सब प्राणियों के हृदय में अन्तर्यामी रूप से स्थित हूँ तथा मुझसे ही स्मृति, ज्ञान और अपोहन (विचार द्वारा बुद्धि में रहने वाले संशय, विपर्यय आदि दोषों को हटाने का नाम 'अपोहन' है) होता है और सब वेदों द्वारा मैं ही जानने योग्य (सर्व वेदों का तात्पर्य परमेश्वर को जानने का है, इसलिए सब वेदों द्वारा 'जानने के योग्य' एक परमेश्वर ही है) हूँ तथा वेदान्त का कर्ता और वेदों को जानने वाला भी मैं ही हूँ॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી હું સર્વના હદયમાં રહેલો છું. મારા વડે જ સ્મૃતિ અને જ્ઞાન તથા એ બંનેનો અભાવ ઉત્પન્ન થાય છે. સર્વ વેદો દ્વારા હું જ જાણવા યોગ્ય છું.વેદાંતનો સિદ્ધાંત કરનાર અને તેનો જ્ઞાતા પણ હું છું.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("I am seated in everyone’s heart, and from Me come remembrance, knowledge and forgetfulness. By all the Vedas, I am to be known. Indeed, I am the compiler of Vedanta, and I am the knower of the Vedas.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("द्वाविमौ पुरुषौ लोके क्षरश्चाक्षर एव च ।\nक्षरः सर्वाणि भूतानि कूटस्थोऽक्षर उच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("દ્વાવિમૌ પુરુષૌ લોકે ક્ષરશ્ચાક્ષર એવ ચ,\nક્ષરઃ સર્વાણિ ભૂતાનિ કૂટસ્થોક્ષર ઉચ્યતે.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Dwaavimau purushau loke ksharashchaakshara eva cha;\nKsharah sarvaani bhootaani kootastho’kshara uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  इस संसार में नाशवान और अविनाशी भी ये दो प्रकार  के पुरुष हैं। इनमें सम्पूर्ण भूतप्राणियों के शरीर तो नाशवान और जीवात्मा अविनाशी कहा जाता है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ લોકમાં ક્ષર અને અક્ષર અવિનાશી બે જ પુરુષ છે. સર્વ ભૂતોને ક્ષર કહેવામાં આવે છે અને કુટસ્થ-સર્વ ભૂતોની ઉત્પત્તિના કારણરૂપ ને અક્ષર કહેવામાં આવે છે.(૧૬)  ");
        } else if (2 == i2) {
            this.tv2.setText("There are two classes of beings, the fallible and the infallible. In the material world every living entity is fallible, and in the spiritual world every living entity is called infallible.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उत्तमः पुरुषस्त्वन्यः परमात्मेत्युदाहृतः ।\nयो लोकत्रयमाविश्य बिभर्त्यव्यय ईश्वरः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉત્તમઃ પુરુષસ્ત્વન્યઃ પરમાત્મેત્યુદાહૃતઃ,\nયો લોકત્રયમાવિશ્ય બિભર્ત્યવ્યય ઈશ્વરઃ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Uttamah purushastwanyah paramaatmetyudaahritah;\nYo lokatrayamaavishya bibhartyavyaya ishwarah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इन दोनों से उत्तम पुरुष तो अन्य ही है, जो तीनों लोकों में प्रवेश करके सबका धारण-पोषण करता है एवं अविनाशी परमेश्वर और परमात्मा- इस प्रकार कहा गया है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("ઉત્તમ પુરુષ તો આ બંનેથી અલગ છે. તેને પરમાત્મા કહેવામાં આવે છે. એ અવિનાશી ઈશ્વરરૂપ બની આ જગતત્રયમાં પ્રવેશી ને તેનું ધારણ-પોષણ કરે છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("Besides these two, there is the greatest living personality, the Supreme Soul, the imperishable Lord Himself, who has entered the three worlds and is maintaining them.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यस्मात्क्षरमतीतोऽहमक्षरादपि चोत्तमः ।\nअतोऽस्मि लोके वेदे च प्रथितः पुरुषोत्तमः ॥");
        } else if (i == 0) {
            this.tv1.setText("યસ્માત્ક્ષરમતીતોહમક્ષરાદપિ ચોત્તમઃ,\nઅતોસ્મિ લોકે વેદે ચ પ્રથિતઃ પુરુષોત્તમઃ.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Yasmaat ksharam ateeto’hamaksharaadapi chottamah;\nAto’smi loke vede cha prathitah purushottamah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि मैं नाशवान जड़वर्ग- क्षेत्र से तो सर्वथा अतीत हूँ और अविनाशी जीवात्मा से भी उत्तम हूँ, इसलिए लोक में और वेद में भी पुरुषोत्तम नाम से प्रसिद्ध हूँ॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું ક્ષરથી તો સર્વથા પર છું અને માયામાં સ્થિત અવિનાશી જીવાત્મા અક્ષરથી પણ ઉત્તમ છું. તેથી લોકોમાં અને વેદોમાં પુરુષોત્તમ નામથી પ્રસિદ્ધ છું.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("Because I am transcendental, beyond both the fallible and the infallible, and because I am the greatest, I am celebrated both in the world and in the Vedas as that Supreme Person.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यो मामेवमसम्मूढो जानाति पुरुषोत्तमम्‌ ।\nस सर्वविद्भजति मां सर्वभावेन भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("યો મામેવમસમ્મૂઢો જાનાતિ પુરુષોત્તમમ્,\nસ સર્વવિદ્ભજતિ માં સર્વભાવેન ભારત.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Yo maamevam asammoodho jaanaati purushottamam;\nSa sarvavidbhajati maam sarvabhaavena bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  भारत! जो ज्ञानी पुरुष मुझको इस प्रकार तत्त्व से पुरुषोत्तम जानता है, वह सर्वज्ञ पुरुष सब प्रकार से निरन्तर मुझ वासुदेव परमेश्वर को ही भजता है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! જે સંમોહથી રહિત મને એ પ્રકારે પુરુષોત્તમ રૂપે જાણે છે, તે સર્વજ્ઞ છે. અને તે સર્વ ભક્તિયોગથી મને ભજે છે.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Whoever knows Me as the Supreme Personality of Godhead, without doubting, is the knower of everything. He therefore engages himself in full devotional service to Me, O son of Bharata.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इति गुह्यतमं शास्त्रमिदमुक्तं मयानघ ।\nएतद्‍बुद्ध्वा बुद्धिमान्स्यात्कृतकृत्यश्च भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇતિ ગુહ્યતમં શાસ્ત્રમિદમુક્તં મયાનઘ,\nએતદ્બુદ્ધ્વા બુદ્ધિમાન્સ્યાત્કૃતકૃત્યશ્ચ ભારત.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Iti guhyatamam shaastram idamuktam mayaa’nagha;\nEtadbuddhwaa buddhimaan syaat kritakrityashcha bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे निष्पाप अर्जुन! इस प्रकार यह अति रहस्ययुक्त गोपनीय शास्त्र मेरे द्वारा कहा गया, इसको तत्त्व से जानकर मनुष्य ज्ञानवान और कृतार्थ हो जाता है॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે નિષ્પાપ  ! હે ભારત  ! મેં આ પ્રમાણે તને ગુહ્ય માં ગુહ્ય શાસ્ત્ર કહ્યું છે. એને જાણીને આત્મા જ્ઞાનવાન થાય છે અને કૃતાર્થ થાય છે.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("This is the most confidential part of the Vedic scriptures, O sinless one, and it is disclosed now by Me. Whoever understands this will become wise, and his endeavors will know perfection.(20)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay15.this, ShlokListPlay15.this);
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

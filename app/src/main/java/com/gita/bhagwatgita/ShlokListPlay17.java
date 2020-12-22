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

public class ShlokListPlay17 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay17.this.favflag == 0) {
                    ShlokListPlay17.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.favflag = 1;
                    shlokListPlay17.save();
                } else if (1 == ShlokListPlay17.this.favflag) {
                    ShlokListPlay17.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay17 shlokListPlay172 = ShlokListPlay17.this;
                    shlokListPlay172.favflag = 0;
                    shlokListPlay172.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay17.this.calll++;
                ShlokListPlay17.this.slkset();
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
                ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                shlokListPlay17.calll--;
                ShlokListPlay17.this.slkset();
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
        edit.putString("A17" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A17" + this.calll, "0");
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
        if (29 == this.calll) {
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
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.callf1 = 0;
                    shlokListPlay17.slkset();
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
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.callf1 = 2;
                    shlokListPlay17.slkset();
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
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.callf1 = 1;
                    shlokListPlay17.slkset();
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
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.callf2 = 0;
                    shlokListPlay17.slkset();
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
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.callf2 = 2;
                    shlokListPlay17.slkset();
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
                    ShlokListPlay17 shlokListPlay17 = ShlokListPlay17.this;
                    shlokListPlay17.callf2 = 1;
                    shlokListPlay17.slkset();
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
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 17");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १७ \nश्रद्धात्रयविभागयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૭ \nશ્રદ્ધાત્રય વિભાગ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 17\nshraddhatray vibhag yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, भौतिक प्रकृति के तीन गुणों से तीन प्रकार की श्रद्धा उतपन्न होती है। रजोगुण तथा तमोगुण में श्रद्धापूर्वक किये गये कर्मों से अस्थायी फल प्राप्त होते हैं, जबकि शास्त्र-सम्मत विधि से सतोगुण में रहकर कर्म हदय को शुद्ध करते हैं। ये भगवान कृष्ण के प्रति शुद्ध श्रद्धा तथा भक्ति उत्पन्न करने वाले हेट हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૭ માં સાત્વિક, રાજસિક, તામસિક -ત્રણ પ્રકારની શ્રદ્ધા ,આહાર, યજ્ઞ, તપ,અને દાન નું વર્ણન કરેલું છે.");
        } else if (2 == i) {
            this.tv1.setText("Seventeenth adhyay tells, There are three types of faith, corresponding to and evolving from the three modes of material nature. Acts performed by those whose faith is in passion and ignorance yield only impermanent, material results, whereas acts performed in goodness, in accord with scriptural injunctions, purify the heart and lead to pure faith in Lord Krishna and devotion to Him.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच- \nये शास्त्रविधिमुत्सृज्य यजन्ते श्रद्धयान्विताः।\nतेषां निष्ठा तु का कृष्ण सत्त्वमाहो रजस्तमः॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nયે શાસ્ત્રવિધિમુત્સૃજ્ય યજન્તે શ્રદ્ધયાન્વિતાઃ,\nતેષાં નિષ્ઠા તુ કા કૃષ્ણ સત્ત્વમાહો રજસ્તમઃ.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha-\nYe shaastravidhimutsrijya yajante shraddhayaanvitaah;\nTeshaam nishthaa tu kaa krishna sattwamaaho rajastamah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे कृष्ण! जो मनुष्य शास्त्र विधि को त्यागकर श्रद्धा से युक्त हुए देवादिका पूजन करते हैं, उनकी स्थिति फिर कौन-सी है? सात्त्विकी है अथवा राजसी किंवा तामसी?॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે - હે શ્રી કૃષ્ણ ! જે મનુષ્યો શાસ્ત્રવિધિનો ત્યાગ કરીને, શ્રદ્ધાયુક્ત થઇ દેવતાઓનું યજન  કરેછે  તેમની તે નિષ્ઠા કેવા પ્રકારની છે? સાત્વિક, રાજસ કે તામસ?(૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna inquired- O Krishna, what is the situation of those who do not follow the principles of scripture but worship according to their own imagination? Are they in goodness, in passion or in ignorance?|1|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच-\nत्रिविधा भवति श्रद्धा देहिनां सा स्वभावजा।\nसात्त्विकी राजसी चैव तामसी चेति तां श्रृणु॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nત્રિવિધા ભવતિ શ્રદ્ધા દેહિનાં સા સ્વભાવજા,\nસાત્ત્વિકી રાજસી ચૈવ તામસી ચેતિ તાં શ્રૃણુ.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha-\nTrividhaa bhavati shraddhaa dehinaam saa swabhaavajaa;\nSaattwikee raajasee chaiva taamasee cheti taam shrinu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्री भगवान्‌ बोले- मनुष्यों की वह शास्त्रीय संस्कारों से रहित केवल स्वभाव से उत्पन्न श्रद्धा (अनन्त जन्मों में किए हुए कर्मों के सञ्चित संस्कार से उत्पन्न हुई श्रद्धा ''स्वभावजा'' श्रद्धा कही जाती है।) सात्त्विकी और राजसी तथा तामसी- ऐसे तीनों प्रकार की ही होती है। उसको तू मुझसे सुन॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે - મનુષ્યની જે સ્વાભાવિક શ્રદ્ધા હોય છે તે સાત્વિક, રાજસ અને તામસ, એમ ત્રણ પ્રકારની હોય છે તે સાંભળ.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said- According to the modes of nature acquired by the embodied soul, one?s faith can be of three kinds?in goodness, in passion or in ignorance. Now hear about this.|2|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सत्त्वानुरूपा सर्वस्य श्रद्धा भवति भारत।\nश्रद्धामयोऽयं पुरुषो यो यच्छ्रद्धः स एव सः॥");
        } else if (i == 0) {
            this.tv1.setText("સત્ત્વાનુરૂપા સર્વસ્ય શ્રદ્ધા ભવતિ ભારત,\nશ્રદ્ધામયોયં પુરુષો યો યચ્છ્રદ્ધઃ સ એવ સઃ.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Sattwaanuroopaa sarvasya shraddhaa bhavati bhaarata;\nShraddhaamayo?yam purusho yo yacchraddhah sa eva sah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भारत! सभी मनुष्यों की श्रद्धा उनके अन्तःकरण के अनुरूप होती है। यह पुरुष श्रद्धामय है, इसलिए जो पुरुष जैसी श्रद्धावाला है, वह स्वयं भी वही है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત ! સર્વને પોત પોતાના પૂર્વ સંસ્કાર પ્રમાણે શ્રદ્ધા ઉત્પન્ન થાય છે, કારણ કે આ સંસારી જીવ શ્રદ્ધામય હોય છે તેથી મનુષ્ય જેવી શ્રદ્ધાવાળો થાય છે,તે તેવી જ યોગ્યતાનો કહેવાય છે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Bharata, according to one?s existence under the various modes of nature, one evolves a particular kind of faith. The living being is said to be of a particular faith according to the modes he has acquired.|3|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यजन्ते सात्त्विका देवान्यक्षरक्षांसि राजसाः।\nप्रेतान्भूतगणांश्चान्ये जयन्ते तामसा जनाः॥");
        } else if (i == 0) {
            this.tv1.setText("યજન્તે સાત્ત્વિકા દેવાન્યક્ષરક્ષાંસિ રાજસાઃ,\nપ્રેતાન્ભૂતગણાંશ્ચાન્યે યજન્તે તામસા જનાઃ.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Yajante saattwikaa devaan yaksharakshaamsi raajasaah;\nPretaan bhootaganaamshchaanye yajante taamasaa janaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सात्त्विक पुरुष देवों को पूजते हैं, राजस पुरुष यक्ष और राक्षसों को तथा अन्य जो तामस मनुष्य हैं, वे प्रेत और भूतगणों को पूजते हैं॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેઓ સાત્વિક હોય છે, તેઓ દેવોનું પૂજન કરે છે. જેઓ રાજસ હોય છે તેઓ યક્ષો-રાક્ષસોનું પૂજન કરે છે અને તામસ હોય છે તે ભૂતગણો- પ્રેતોનું પૂજન કરે છે.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("Men in the mode of goodness worship the demigods; those in the mode of passion worship the demons; and those in the mode of ignorance worship ghosts and spirits.|4|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अशास्त्रविहितं घोरं तप्यन्ते ये तपो जनाः।\nदम्भाहङ्‍कारसंयुक्ताः कामरागबलान्विताः॥");
        } else if (i == 0) {
            this.tv1.setText("અશાસ્ત્રવિહિતં ઘોરં તપ્યન્તે યે તપો જનાઃ,\nદમ્ભાહઙ્કારસંયુક્તાઃ કામરાગબલાન્વિતાઃ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Ashaastravihitam ghoram tapyante ye tapo janaah;\nDambhaahamkaarasamyuktaah kaamaraagabalaanvitaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो मनुष्य शास्त्र विधि से रहित केवल मनःकल्पित घोर तप को तपते हैं तथा दम्भ और अहंकार से युक्त एवं कामना, आसक्ति और बल के अभिमान से भी युक्त हैं॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("દંભ અને અહંકાર તેમજ કામ અને પ્રીતિના બળથી યુક્ત એવા જે જનો શાસ્ત્ર વિરુદ્ધ ઘોર તપ કરે છે;(૫)");
        } else if (2 == i2) {
            this.tv2.setText("Those men who practise terrific austerities not enjoined by the scriptures, given to hypocrisy and egoism, impelled by the force of lust and attachment.|5|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्शयन्तः शरीरस्थं भूतग्राममचेतसः।\nमां चैवान्तःशरीरस्थं तान्विद्ध्‌यासुरनिश्चयान्‌॥");
        } else if (i == 0) {
            this.tv1.setText("કર્ષયન્તઃ શરીરસ્થં ભૂતગ્રામમચેતસઃ,\nમાં ચૈવાન્તઃશરીરસ્થં તાન્વિદ્ધ્યાસુરનિશ્ચયાન્.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Karshayantah shareerastham bhootagraamamachetasah;\nMaam chaivaantahshareerastham taanviddhyaasuranishchayaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो शरीर रूप से स्थित भूत समुदाय को और अन्तःकरण में स्थित मुझ परमात्मा को भी कृश करने वाले हैं (शास्त्र से विरुद्ध उपवासादि घोर आचरणों द्वारा शरीर को सुखाना एवं भगवान्‌ के अंशस्वरूप जीवात्मा को क्लेश देना, भूत समुदाय को और अन्तर्यामी परमात्मा को ''कृश करना'' है।), उन अज्ञानियों को तू आसुर स्वभाव वाले जान॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("અને જે અવિવેકીજન દેહની ઈન્દ્રિયોને અને દેહની અંદર રહેતા મને પણ કૃશ બનાવે છે, તે આસુરી નિષ્ઠાવાળા છે એમ તું માન.(૬)");
        } else if (2 == i2) {
            this.tv2.setText(" Senseless, torturing all the elements in the body and Me also, who dwells in the body? know thou these to be of demoniacal resolves.|6|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आहारस्त्वपि सर्वस्य त्रिविधो भवति प्रियः।\nयज्ञस्तपस्तथा दानं तेषां भेदमिमं श्रृणु॥");
        } else if (i == 0) {
            this.tv1.setText("આહારસ્ત્વપિ સર્વસ્ય ત્રિવિધો ભવતિ પ્રિયઃ,\nયજ્ઞસ્તપસ્તથા દાનં તેષાં ભેદમિમં શ્રૃણુ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Aahaarastwapi sarvasya trividho bhavati priyah;\nYajnastapastathaa daanam teshaam bhedamimam shrinu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("भोजन भी सबको अपनी-अपनी प्रकृति के अनुसार तीन प्रकार का प्रिय होता है। और वैसे ही यज्ञ, तप और दान भी तीन-तीन प्रकार के होते हैं। उनके इस पृथक्‌-पृथक्‌ भेद को तू मुझ से सुन॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("પ્રત્યેકને મનગમતો આહાર પણ ત્રણ પ્રકારનો હોય છે. તે રીતે યજ્ઞ,તપ અને દાન પણ ત્રણ પ્રકારનાં હોય છે. તે દાનના ભેદ હું તને કહીશ સાંભળ.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("Even the food each person prefers is of three kinds, according to the three modes of material nature. The same is true of sacrifices, austerities and charity. Now hear of the distinctions between them.|7|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आयुः सत्त्वबलारोग्यसुखप्रीतिविवर्धनाः।\nरस्याः स्निग्धाः स्थिरा हृद्या आहाराः सात्त्विकप्रियाः॥");
        } else if (i == 0) {
            this.tv1.setText("આયુઃસત્ત્વબલારોગ્યસુખપ્રીતિવિવર્ધનાઃ,\nરસ્યાઃ સ્નિગ્ધાઃ સ્થિરા હૃદ્યા આહારાઃ સાત્ત્વિકપ્રિયાઃ.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Aayuh sattwabalaarogya sukha preetivi vardhanaah;\nRasyaah snigdhaah sthiraa hridyaa aahaaraah saattwikapriyaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("आयु, बुद्धि, बल, आरोग्य, सुख और प्रीति को बढ़ाने वाले, रसयुक्त, चिकने और स्थिर रहने वाले (जिस भोजन का सार शरीर में बहुत काल तक रहता है, उसको स्थिर रहने वाला कहते हैं।) तथा स्वभाव से ही मन को प्रिय- ऐसे आहार अर्थात्‌ भोजन करने के पदार्थ सात्त्विक पुरुष को प्रिय होते हैं॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("આયુષ્ય, બળ, સત્વ, આરોગ્ય,સુખ અને રુચિને વધારનારા રસદાર તથા ચીકાશવાળા, દેહને પૃષ્ટિ આપનારા અને હદયને પ્રસન્નતા આપે તેવા આહારો સાત્વિક મનુષ્યને પ્રિય હોય છે.(૮)  ");
        } else if (2 == i2) {
            this.tv2.setText("Foods dear to those in the mode of goodness increase the duration of life, purify one?s existence and give strength, health, happiness and satisfaction. Such foods are juicy, fatty, wholesome, and pleasing to the heart.|8|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कट्वम्ललवणात्युष्णतीक्ष्णरूक्षविदाहिनः।\nआहारा राजसस्येष्टा दुःखशोकामयप्रदाः॥");
        } else if (i == 0) {
            this.tv1.setText("કટ્વમ્લલવણાત્યુષ્ણતીક્ષ્ણરૂક્ષવિદાહિનઃ,\nઆહારા રાજસસ્યેષ્ટા દુઃખશોકામયપ્રદાઃ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Katvamlalavanaatyushna teekshna rooksha vidaahinah;\nAahaaraah raajasasyeshtaa duhkhashokaamayapradaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कड़वे, खट्टे, लवणयुक्त, बहुत गरम, तीखे, रूखे, दाहकारक और दुःख, चिन्ता तथा रोगों को उत्पन्न करने वाले आहार अर्थात्‌ भोजन करने के पदार्थ राजस पुरुष को प्रिय होते हैं॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("અતિશય કડવા,ખારા, ખાટા, ગરમ, તીખા, રુક્ષ, દાહક તથા દુઃખ, શોક અને રોગ ઉત્પન્ન કરે તેવા આહાર રાજસોને પ્રિય હોય છે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("Foods that are too bitter, too sour, salty, hot, pungent, dry and burning are dear to those in the mode of passion. Such foods cause distress, misery and disease.|9|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यातयामं गतरसं पूति पर्युषितं च यत्‌।\nउच्छिष्टमपि चामेध्यं भोजनं तामसप्रियम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("યાતયામં ગતરસં પૂતિ પર્યુષિતં ચ યત્,\nઉચ્છિષ્ટમપિ ચામેધ્યં ભોજનં તામસપ્રિયમ્.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Yaatayaamam gatarasam pooti paryushitam cha yat;\nUcchishtamapi chaamedhyam bhojanam taamasapriyam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो भोजन अधपका, रसरहित, दुर्गन्धयुक्त, बासी और उच्छिष्ट है तथा जो अपवित्र भी है, वह भोजन तामस पुरुष को प्रिय होता है॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("કાચુપાકું, ઉતરી ગયેલું, વાસી, ગંધાતું, એંઠું તથા અપવિત્ર અન્ન તામસી પ્રકૃતિના મનુષ્યને પ્રિય લાગે છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("Food prepared more than three hours before being eaten, food that is tasteless, decomposed and putrid, and food consisting of remnants and untouchable things is dear to those in the mode of darkness.|10|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle(" 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अफलाकाङ्क्षिभिर्यज्ञो विधिदृष्टो य इज्यते।\nयष्टव्यमेवेति मनः समाधाय स सात्त्विकः॥");
        } else if (i == 0) {
            this.tv1.setText("અફલાકાઙ્ક્ષિભિર્યજ્ઞો વિધિદૃષ્ટો ય ઇજ્યતે,\nયષ્ટવ્યમેવેતિ મનઃ સમાધાય સ સાત્ત્વિકઃ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Aphalaakaangkshibhiryajno vidhidrishto ya ijyate;\nYashtavyameveti manah samaadhaaya sa saattwikah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो शास्त्र विधि से नियत, यज्ञ करना ही कर्तव्य है- इस प्रकार मन को समाधान करके, फल न चाहने वाले पुरुषों द्वारा किया जाता है, वह सात्त्विक है॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("ફળની કામના ન રાખનાર મનુષ્ય, પોતાનું કર્તવ્ય છે એમ સમજીને મન થી નિશ્વય કરી જે શાસ્ત્રોકતવિધિ પ્રમાણે યજ્ઞ કરે છે તે સાત્વિક યજ્ઞ કહેવાય છે.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("Of sacrifices, the sacrifice performed according to the directions of scripture, as a matter of duty, by those who desire no reward, is of the nature of goodness.|11|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अभिसन्धाय तु फलं दम्भार्थमपि चैव यत्‌।\nइज्यते भरतश्रेष्ठ तं यज्ञं विद्धि राजसम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("અભિસંધાય તુ ફલં દમ્ભાર્થમપિ ચૈવ યત્,\nઇજ્યતે ભરતશ્રેષ્ઠ તં યજ્ઞં વિદ્ધિ રાજસમ્.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Abhisandhaaya tu phalam dambhaarthamapi chaiva yat;\nIjyate bharatashreshtha tam yajnam viddhi raajasam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु हे अर्जुन! केवल दम्भाचरण के लिए अथवा फल को भी दृष्टि में रखकर जो यज्ञ किया जाता है, उस यज्ञ को तू राजस जान॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભરતશ્રેષ્ઠ ! ફળની ઇચ્છાથી કે કેવળ દંભ કરવા માટે જે યજ્ઞ કરવામાં આવે છે તે રાજસયજ્ઞ કહેવામાં આવે છે, એમ તું સમજ.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("But the sacrifice performed for some material benefit, or for the sake of pride, O chief of the Bharatas, you should know to be in the mode of passion.|12|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("विधिहीनमसृष्टान्नं मन्त्रहीनमदक्षिणम्‌।\nश्रद्धाविरहितं यज्ञं तामसं परिचक्षते॥");
        } else if (i == 0) {
            this.tv1.setText("વિધિહીનમસૃષ્ટાન્નં મન્ત્રહીનમદક્ષિણમ્,\nશ્રદ્ધાવિરહિતં યજ્ઞં તામસં પરિચક્ષતે.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Vidhiheenam asrishtaannam mantraheenam adakshinam;\nShraddhaavirahitam yajnam taamasam parichakshate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("शास्त्रविधि से हीन, अन्नदान से रहित, बिना मन्त्रों के, बिना दक्षिणा के और बिना श्रद्धा के किए जाने वाले यज्ञ को तामस यज्ञ कहते हैं॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("શાસ્ત્રવિધિ રહિત, અન્નદાન રહિત, મંત્ર રહિત, દક્ષિણારહિત અને શ્રદ્ધારહિત જે યજ્ઞ કરવામાં આવે છે તે તામસ યજ્ઞ કહેવાય છે.(૧૩)  ");
        } else if (2 == i2) {
            this.tv2.setText("Any sacrifice performed without regard for the directions of scripture, without distribution of prasadam [spiritual food], without chanting of Vedic hymns and remunerations to the priests, and without faith is considered to be in the mode of ignorance.|13|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("देवद्विजगुरुप्राज्ञपूजनं शौचमार्जवम्‌।\nब्रह्मचर्यमहिंसा च शारीरं तप उच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("દેવદ્વિજગુરુપ્રાજ્ઞપૂજનં શૌચમાર્જવમ્,\nબ્રહ્મચર્યમહિંસા ચ શારીરં તપ ઉચ્યતે.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Devadwijagurupraajna poojanam shauchamaarjavam;\nBrahmacharyamahimsaa cha shaareeram tapa uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  देवता, ब्राह्मण, गुरु (यहाँ 'गुरु' शब्द से माता, पिता, आचार्य और वृद्ध एवं अपने से जो किसी प्रकार भी बड़े हों, उन सबको समझना चाहिए।) और ज्ञानीजनों का पूजन, पवित्रता, सरलता, ब्रह्मचर्य और अहिंसा- यह शरीर- सम्बन्धी तप कहा जाता है॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("દેવ, દ્વિજ, ગુરુ અને પ્રાજ્ઞનું પૂજન,પવિત્રતા,સરળતા,બ્રહ્મચર્ય અને અહિંસા એ શરીરસંબંધી તપ કહેવાય છે.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("Austerity of the body consists in worship of the Supreme Lord, the brahmanas, the spiritual master, and superiors like the father and mother, and in cleanliness, simplicity, celibacy and nonviolence.|14|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनुद्वेगकरं वाक्यं सत्यं प्रियहितं च यत्‌।\nस्वाध्यायाभ्यसनं चैव वाङ्‍मयं तप उच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("અનુદ્વેગકરં વાક્યં સત્યં પ્રિયહિતં ચ યત્,\nસ્વાધ્યાયાભ્યસનં ચૈવ વાઙ્મયં તપ ઉચ્યતે.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Anudwegakaram vaakyam satyam priyahitam cha yat;\nSwaadhyaayaabhyasanam chaiva vaangmayam tapa uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो उद्वेग न करने वाला, प्रिय और हितकारक एवं यथार्थ भाषण है (मन और इन्द्रियों द्वारा जैसा अनुभव किया हो, ठीक वैसा ही कहने का नाम 'यथार्थ भाषण' है।) तथा जो वेद-शास्त्रों के पठन का एवं परमेश्वर के नाम-जप का अभ्यास है- वही वाणी-सम्बन्धी तप कहा जाता है॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("કોઈનું મન ન દુભાય તેવું, સત્ય, મધુર, સર્વને પ્રિય અને હિતકારક એવું વચન બોલવું તથા યથાવિધિ વેદશાસ્ત્રનો અભ્યાસ કરવો તેને વાણીનું તપ કહેવામાં આવે છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Austerity of speech consists in speaking words that are truthful, pleasing, beneficial, and not agitating to others, and also in regularly reciting Vedic literature.|15|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मनः प्रसादः सौम्यत्वं मौनमात्मविनिग्रहः।\nभावसंशुद्धिरित्येतत्तपो मानसमुच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("મનઃપ્રસાદઃ સૌમ્યત્વં મૌનમાત્મવિનિગ્રહઃ,\nભાવસંશુદ્ધિરિત્યેતત્તપો માનસમુચ્યતે.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Manahprasaadah saumyatwam maunamaatmavinigrahah;\nBhaavasamshuddhirityetat tapo maanasamuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" मन की प्रसन्नता, शान्तभाव, भगवच्चिन्तन करने का स्वभाव, मन का निग्रह और अन्तःकरण के भावों की भलीभाँति पवित्रता, इस प्रकार यह मन सम्बन्धी तप कहा जाता है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("મનની પ્રસન્નતા, સૌજન્ય, મૌન,આત્મસંયમ અને અંત:કરણની શુદ્ધિને માનસિક તપ કહેવામાં આવે છે.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("And satisfaction, simplicity, gravity, self-control and purification of one?s existence are the austerities of the mind.|16|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रद्धया परया तप्तं तपस्तत्त्रिविधं नरैः।\nअफलाकाङ्क्षिभिर्युक्तैः सात्त्विकं परिचक्षते॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રધ્દયા પરયા તપ્તં તપસ્તત્ત્રિવિધં નરૈઃ,\nઅફલાકાઙ્ક્ષિભિર્યુક્તૈઃ સાત્ત્વિં પરિચક્ષતે.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Shraddhayaa parayaa taptam tapastattrividham naraih;\nAphalaakaangkshibhiryuktaih saattwikam parichakshate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("फल को न चाहने वाले योगी पुरुषों द्वारा परम श्रद्धा से किए हुए उस पूर्वोक्त तीन प्रकार के तप को सात्त्विक कहते हैं॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("ફળની આશા વગર તથા સમાહિત ચિત્તવાળા પુરુષે શ્રેષ્ઠ શ્રદ્ધાથી ઉપરોક્ત ત્રણ રીતે આચરેલું તપ સાત્વિક તપ કહેવાય છે.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("This threefold austerity, performed with transcendental faith by men not expecting material benefits but engaged only for the sake of the Supreme, is called austerity in goodness.|17|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सत्कारमानपूजार्थं तपो दम्भेन चैव यत्‌।\nक्रियते तदिह प्रोक्तं राजसं चलमध्रुवम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("સત્કારમાનપૂજાર્થં તપો દમ્ભેન ચૈવ યત્,\nક્રિયતે તદિહ પ્રોક્તં રાજસં ચલમધ્રુવમ્.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Satkaaramaanapoojaartham tapo dambhena chaiva yat;\nKriyate tadiha proktam raajasam chalamadhruvam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो तप सत्कार, मान और पूजा के लिए तथा अन्य किसी स्वार्थ के लिए भी स्वभाव से या पाखण्ड से किया जाता है, वह अनिश्चित ('अनिश्चित फलवाला' उसको कहते हैं कि जिसका फल होने न होने में शंका हो।) एवं क्षणिक फलवाला तप यहाँ राजस कहा गया है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("અને જે તપ પોતાની સ્તુતિ, માન તથા પૂજાના હેતુથી, કેવળ દંભથી કરવામાં આવે છે તેને રાજસ તપ કહેવાય છે.તે આ લોકમાં નાશવંત અને અનિશ્વિત ફળવાળું છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("Penance performed out of pride and for the sake of gaining respect, honor and worship is said to be in the mode of passion. It is neither stable nor permanent.|18|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मूढग्राहेणात्मनो यत्पीडया क्रियते तपः।\nपरस्योत्सादनार्थं वा तत्तामसमुदाहृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("મૂઢગ્રાહેણાત્મનો યત્પીડયા ક્રિયતે તપઃ,\nપરસ્યોત્સાદનાર્થં વા તત્તામસમુદાહૃતમ્.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Moodhagraahenaatmano yat peedayaa kriyate tapah;\nParasyotsaadanaartham vaa tattaamasamudaahritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो तप मूढ़तापूर्वक हठ से, मन, वाणी और शरीर की पीड़ा के सहित अथवा दूसरे का अनिष्ट करने के लिए किया जाता है- वह तप तामस कहा गया है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("ઉન્મત્તતાથી  દુરાગ્રહપૂર્વક પોતાના દેહને કષ્ટ આપી અથવા બીજાનું અહિત કે નાશ કરવાની કામનાથી જે તપ કરવામાં આવે છે તે તામસ તપ કહેવાય છે.(૧૯)  ");
        } else if (2 == i2) {
            this.tv2.setText("Penance performed out of foolishness, with self-torture or to destroy or injure others, is said to be in the mode of ignorance.|19|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दातव्यमिति यद्दानं दीयतेऽनुपकारिणे।\nदेशे काले च पात्रे च तद्दानं सात्त्विकं स्मृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("દાતવ્યમિતિ યદ્દાનં દીયતેનુપકારિણે,\nદેશે કાલે ચ પાત્રે ચ તદ્દાનં સાત્ત્વિકં સ્મૃતમ્.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Daatavyamiti yaddaanam deeyate?nupakaarine;\nDeshe kaale cha paatre cha taddaanam saattwikam smritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("   दान देना ही कर्तव्य है- ऐसे भाव से जो दान देश तथा काल (जिस देश-काल में जिस वस्तु का अभाव हो, वही देश-काल, उस वस्तु द्वारा प्राणियों की सेवा करने के लिए योग्य समझा जाता है।) और पात्र के (भूखे, अनाथ, दुःखी, रोगी और असमर्थ तथा भिक्षुक आदि तो अन्न, वस्त्र और ओषधि एवं जिस वस्तु का जिसके पास अभाव हो, उस वस्तु द्वारा सेवा करने के लिए योग्य पात्र समझे जाते हैं और श्रेष्ठ आचरणों वाले विद्वान्‌ ब्राह्मणजन धनादि सब प्रकार के पदार्थों द्वारा सेवा करने के लिए योग्य पात्र समझे जाते हैं।) प्राप्त होने पर उपकार न करने वाले के प्रति दिया जाता है, वह दान सात्त्विक कहा गया है॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("દાન કરવું એ આપણું કર્તવ્ય છે, એવા હેતુથી જે દાન પ્રત્યુપકાર નહિ કરી શકનાર સત્પાત્રને, પુણ્યક્ષેત્રમાં અને પર્વકાળે આપવામાં આવે છે તેને સાત્વિક દાન કહેવામાં આવેછે.(૨૦)  ");
        } else if (2 == i2) {
            this.tv2.setText("Charity given out of duty, without expectation of return, at the proper time and place, and to a worthy person is considered to be in the mode of goodness.|20|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्तु प्रत्युपकारार्थं फलमुद्दिश्य वा पुनः।\nदीयते च परिक्लिष्टं तद्दानं राजसं स्मृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("યત્તુ પ્રત્યુપકારાર્થં ફલમુદ્દિશ્ય વા પુનઃ,\nદીયતે ચ પરિક્લિષ્ટં તદ્દાનં રાજસં સ્મૃતમ્.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Yattu pratyupakaaraartham phalamuddishya vaa punah;\nDeeyate cha pariklishtam taddaanam raajasam smritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("किन्तु जो दान क्लेशपूर्वक (जैसे प्रायः वर्तमान समय के चन्दे-चिट्ठे आदि में धन दिया जाता है।) तथा प्रत्युपकार के प्रयोजन से अथवा फल को दृष्टि में (अर्थात्‌ मान बड़ाई, प्रतिष्ठा और स्वर्गादि की प्राप्ति के लिए अथवा रोगादि की निवृत्ति के लिए।) रखकर फिर दिया जाता है, वह दान राजस कहा गया है॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી જે કંઈ દાન પ્રતિઉપકાર માટે અથવા ફળને ઉદ્દેશી તથા કલેશ પામીને આપવામાં આવે તેને રાજસ દાન કહેવાય છે.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("But charity performed with the expectation of some return, or with a desire for fruitive results, or in a grudging mood, is said to be charity in the mode of passion.|21|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अदेशकाले यद्दानमपात्रेभ्यश्च दीयते।\nअसत्कृतमवज्ञातं तत्तामसमुदाहृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("અદેશકાલે યદ્દાનમપાત્રેભ્યશ્ચ દીયતે,\nઅસત્કૃતમવજ્ઞાતં તત્તામસમુદાહૃતમ્.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Adeshakaale yaddaanamapaatrebhyashcha deeyate;\nAsatkritamavajnaatam tattaamasamudaahritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो दान बिना सत्कार के अथवा तिरस्कारपूर्वक अयोग्य देश-काल में और कुपात्र के प्रति दिया जाता है, वह दान तामस कहा गया है॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે દાન સત્કારરહિત, અપમાન પૂર્વક, અપવિત્ર જગામાં તથા કાળમાં અને અપાત્રને અપાય છે તે તામસ દાન કહેવાય છે.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("And charity performed at an impure place, at an improper time, to unworthy persons, or without proper attention and respect is said to be in the mode of ignorance.|22|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ॐ तत्सदिति निर्देशो ब्रह्मणस्त्रिविधः स्मृतः।\nब्राह्मणास्तेन वेदाश्च यज्ञाश्च विहिताः पुरा॥");
        } else if (i == 0) {
            this.tv1.setText("ॐ તત્સદિતિ નિર્દેશો બ્રહ્મણસ્ત્રિવિધઃ સ્મૃત,\nબ્રાહ્મણાસ્તેન વેદાશ્ચ યજ્ઞાશ્ચ વિહિતાઃ પુરા.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Om tatsaditi nirdesho brahmanas trividhah smritah;\nBraahmanaastena vedaashcha yajnaashcha vihitaah puraa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("ॐ, तत्‌, सत्‌-ऐसे यह तीन प्रकार का सच्चिदानन्दघन ब्रह्म का नाम कहा है, उसी से सृष्टि के आदिकाल में ब्राह्मण और वेद तथा यज्ञादि रचे गए॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("ॐ, તત્ અને સત્ - એવા ત્રણપ્રકારના બ્રહ્મનાં નામો છે,તેમના યોગથી પૂર્વે આદિકાળમાં બ્રાહ્મણ, વેદ અને યજ્ઞ ઉત્પન્ન કરવામાં આવ્યા છે.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("From the beginning of creation, the three words om tat sat were used to indicate the Supreme Absolute Truth. These three symbolic representations were used by brahmanas while chanting the hymns of the Vedas and during sacrifices for the satisfaction of the Supreme.|23|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मादोमित्युदाहृत्य यज्ञदानतपः क्रियाः।\nप्रवर्तन्ते विधानोक्तः सततं ब्रह्मवादिनाम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માદોમિત્યુદાહૃત્ય યજ્ઞદાનતપઃક્રિયાઃ.\nપ્રવર્તન્તે વિધાનોક્તાઃ સતતં બ્રહ્મવાદિનામ્.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaadomityudaahritya yajnadaanatapahkriyaah;\nPravartante vidhaanoktaah satatam brahmavaadinaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इसलिए वेद-मन्त्रों का उच्चारण करने वाले श्रेष्ठ पुरुषों की शास्त्र विधि से नियत यज्ञ, दान और तपरूप क्रियाएँ सदा 'ॐ' इस परमात्मा के नाम को उच्चारण करके ही आरम्भ होती हैं॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("એટલેજ વેદવેત્તIઓની યથાવિધિ યજ્ઞ, દાન અને તપ વગેરે ક્રિયાઓ બ્રહ્મનાં  ॐ  ઉચ્ચાર સહિત સતત ચાલતી હોય છે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, transcendentalists undertaking performances of sacrifice, charity and penance in accordance with scriptural regulations begin always with om, to attain the Supreme.|24|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तदित्यनभिसन्दाय फलं यज्ञतपःक्रियाः।\nदानक्रियाश्चविविधाः क्रियन्ते मोक्षकाङ्क्षिभिः॥");
        } else if (i == 0) {
            this.tv1.setText("તદિત્યનભિસન્ધાય ફલં યજ્ઞતપઃક્રિયાઃ,\nદાનક્રિયાશ્ચ વિવિધાઃ ક્રિયન્તે મોક્ષકાઙ્ક્ષિ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Tadityanabhisandhaaya phalam yajnatapah kriyaah;\nDaanakriyaashcha vividhaah kriyante mokshakaangkshibhih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" तत्‌ अर्थात्‌ 'तत्‌' नाम से कहे जाने वाले परमात्मा का ही यह सब है- इस भाव से फल को न चाहकर नाना प्रकार के यज्ञ, तपरूप क्रियाएँ तथा दानरूप क्रियाएँ कल्याण की इच्छा वाले पुरुषों द्वारा की जाती हैं॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("મોક્ષની કામનાવાળા બ્રહ્મના તત્  નામનો ઉચ્ચાર કરી ને ફળની કામના ન રાખતાં યજ્ઞ અને તપરૂપ ક્રિયાઓ તથા વિવિધ દાન ક્રિયાઓ કરે છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Without desiring fruitive results, one should perform various kinds of sacrifice, penance and charity with the word tat. The purpose of such transcendental activities is to get free from material entanglement.|25|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सद्भावे साधुभावे च सदित्यतत्प्रयुज्यते।\nप्रशस्ते कर्मणि तथा सच्छब्दः पार्थ युज्यते॥");
        } else if (i == 0) {
            this.tv1.setText("સદ્ભાવે સાધુભાવે ચ સદિત્યેતત્પ્રયુજ્યતે,\nપ્રશસ્તે કર્મણિ તથા સચ્છબ્દઃ પાર્થ યુજ્યતે.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Sadbhaave saadhubhaave cha sadityetatprayujyate;\nPrashaste karmani tathaa sacchabdah paartha yujyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("'सत्‌'- इस प्रकार यह परमात्मा का नाम सत्यभाव में और श्रेष्ठभाव में प्रयोग किया जाता है तथा हे पार्थ! उत्तम कर्म में भी 'सत्‌' शब्द का प्रयोग किया जाता है॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ  ! સદ્દભાવમાં તથા સાધુભાવમાં સત્ એ પ્રમાણે એનો પ્રયોગ કરાય છે તથા માંગલિક કર્મમાં સત્ શબ્દનો પ્રયોગ કરવામાં આવે છે.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("The word Sat is used in the sense of reality and of goodness; and so also, O Arjuna, it is used in the sense of an auspicious act!|26|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यज्ञे तपसि दाने च स्थितिः सदिति चोच्यते।\nकर्म चैव तदर्थीयं सदित्यवाभिधीयते॥");
        } else if (i == 0) {
            this.tv1.setText("યજ્ઞે તપસિ દાને ચ સ્થિતિઃ સદિતિ ચોચ્યતે,\nકર્મ ચૈવ તદર્થીયં સદિત્યેવાભિધીયતે.(૨૭)\n");
        } else if (2 == i) {
            this.tv1.setText("Yajne tapasi daane cha sthitih saditi chochyate;\nKarma chaiva tadartheeyam sadityevaabhidheeyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तथा यज्ञ, तप और दान में जो स्थिति है, वह भी 'सत्‌' इस प्रकार कही जाती है और उस परमात्मा के लिए किया हुआ कर्म निश्चयपूर्वक सत्‌-ऐसे कहा जाता है॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("યજ્ઞમાં તપમાં તથા દાનમાં નિષ્ઠાથી સત્ એમ કહેવાય છે. તેમ જ તેને માટે કરવામાં આવતું કર્મ પણ એ જ પ્રમાણે કહેવાય છે.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("Steadfastness in sacrifice, austerity and gift, is also called Sat, and also action in connection with these (or for the sake of the Supreme) is called Sat.|27|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अश्रद्धया हुतं दत्तं तपस्तप्तं कृतं च यत्‌।\nअसदित्युच्यते पार्थ न च तत्प्रेत्य नो इह॥");
        } else if (i == 0) {
            this.tv1.setText("અશ્રદ્ધયા હુતં દત્તં તપસ્તપ્તં કૃતં ચ યત્,\nઅસદિત્યુચ્યતે પાર્થ ન ચ તત્પ્રેત્ય નો ઇહ.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Ashraddhayaa hutam dattam tapastaptam kritam cha yat;\nAsadityuchyate paartha na cha tatpretya no iha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! बिना श्रद्धा के किया हुआ हवन, दिया हुआ दान एवं तपा हुआ तप और जो कुछ भी किया हुआ शुभ कर्म है- वह समस्त 'असत्‌'- इस प्रकार कहा जाता है, इसलिए वह न तो इस लोक में लाभदायक है और न मरने के बाद ही॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! અશ્રદ્ધાથી હોમેલું, આપેલું, તપ કરેલું,તથા જે કંઈ કરેલું હોય તે અસત્ કહેવાય છે; કારણ કે તે આ લોકમાં કે પરલોકમાં ફળ આપતું નથી.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("Anything done as sacrifice, charity or penance without faith in the Supreme, O son of Pritha, is impermanent. It is called asat and is useless both in this life and the next.|28|");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay17.this, ShlokListPlay17.this);
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

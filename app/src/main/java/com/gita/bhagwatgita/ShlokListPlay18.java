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

public class ShlokListPlay18 extends AppCompatActivity implements TextToSpeech.OnInitListener {

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
                if (ShlokListPlay18.this.favflag == 0) {
                    ShlokListPlay18.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.favflag = 1;
                    shlokListPlay18.save();
                } else if (1 == ShlokListPlay18.this.favflag) {
                    ShlokListPlay18.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay18 shlokListPlay182 = ShlokListPlay18.this;
                    shlokListPlay182.favflag = 0;
                    shlokListPlay182.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay18.this.calll++;
                ShlokListPlay18.this.slkset();
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
                ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                shlokListPlay18.calll--;
                ShlokListPlay18.this.slkset();
                scrollView.pageScroll(33);

                tts.stop();
                btn_slock_silent.setVisibility(View.VISIBLE);
                btn_slock.setVisibility(View.GONE);
                btn_slocktras.setVisibility(View.GONE);
                btn_slocktras_silent.setVisibility(View.VISIBLE);


            }
        });
        @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
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
        edit.putString("A18" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A18" + this.calll, "0");
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
        if (76 == this.calll) {
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
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.callf1 = 0;
                    shlokListPlay18.slkset();
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
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.callf1 = 2;
                    shlokListPlay18.slkset();
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
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.callf1 = 1;
                    shlokListPlay18.slkset();
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
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.callf2 = 0;
                    shlokListPlay18.slkset();
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
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.callf2 = 2;
                    shlokListPlay18.slkset();
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
                    ShlokListPlay18 shlokListPlay18 = ShlokListPlay18.this;
                    shlokListPlay18.callf2 = 1;
                    shlokListPlay18.slkset();
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
            shlok36_37();
        } else if (37 == i3) {
            shlok38();
        } else if (38 == i3) {
            shlok39();
        } else if (39 == i3) {
            shlok40();
        } else if (40 == i3) {
            shlok41();
        } else if (41 == i3) {
            shlok42();
        } else if (42 == i3) {
            shlok43();
        } else if (43 == i3) {
            shlok44();
        } else if (44 == i3) {
            shlok45();
        } else if (45 == i3) {
            shlok46();
        } else if (46 == i3) {
            shlok47();
        } else if (47 == i3) {
            shlok48();
        } else if (48 == i3) {
            shlok49();
        } else if (49 == i3) {
            shlok50();
        } else if (50 == i3) {
            shlok51_52_53();
        } else if (51 == i3) {
            shlok54();
        } else if (52 == i3) {
            shlok55();
        } else if (53 == i3) {
            shlok56();
        } else if (54 == i3) {
            shlok57();
        } else if (55 == i3) {
            shlok58();
        } else if (56 == i3) {
            shlok59();
        } else if (57 == i3) {
            shlok60();
        } else if (58 == i3) {
            shlok61();
        } else if (59 == i3) {
            shlok62();
        } else if (60 == i3) {
            shlok63();
        } else if (61 == i3) {
            shlok64();
        } else if (62 == i3) {
            shlok65();
        } else if (63 == i3) {
            shlok66();
        } else if (64 == i3) {
            shlok67();
        } else if (65 == i3) {
            shlok68();
        } else if (66 == i3) {
            shlok69();
        } else if (67 == i3) {
            shlok70();
        } else if (68 == i3) {
            shlok71();
        } else if (69 == i3) {
            shlok72();
        } else if (70 == i3) {
            shlok73();
        } else if (71 == i3) {
            shlok74();
        } else if (72 == i3) {
            shlok75();
        } else if (73 == i3) {
            shlok76();
        } else if (74 == i3) {
            shlok77();
        } else if (75 == i3) {
            shlok78();
        } else if (76 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 18");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १८ \nमोक्षसंन्यासयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૮ \nમોક્ષ સંન્યાસ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 18\nmoksh sannyas yoga \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कृष्ण वैराग्य का अर्थ और मानवीय चेतना तथा कर्म पर प्रकृति के गुणों का प्रभाव समझाते हैं। वे ब्रह्मा -अनुभूति, भगवद्गीता की महिमा तथा भगवद्गीता के चरम निष्कर्ष को समझाते हैं।  यह चरम निष्कर्ष यह हैं की धर्म का सर्वोच्च मार्ग भगवान कृष्ण की परम शरणागति हैं जो पूर्ण प्रकाश प्रदान करने वाली हैं और मनुष्य को कृष्ण के नित्य धाम को वापस जाने में समर्थ बनती हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૮ માં સન્યાસ અને ત્યાગ વિષે સમજાવતાં કહે છે કે - કામ્ય કર્મો (ફળની ઈચ્છા થી કરાતાં કર્મો) ના ત્યાગ ને જ્ઞાનીઓ 'સંન્યાસ' કહે છે. અને સર્વ કર્મોના 'ફળ' ના ત્યાગ ને 'ત્યાગ' કહે છે. ત્યાગ ત્રણ પ્રકારનો છે, કર્તવ્ય તરીકે નિયત થયેલાં કર્મોનો મોહ-અજ્ઞાન વશ ત્યાગ તે તામસિક ત્યાગ કર્મો દુઃખરૂપ છે, એમ સમજી શારીરિક પીડાના ભયથી કર્મો નો ત્યાગ તે રાજસિક ત્યાગ, કર્તવ્ય કર્મ ને ધર્મ સમજી, આશક્તિ તથા ફળની ઈચ્છાનો ત્યાગ કરી કરેલો ત્યાગ તે સાત્વિક ત્યાગ. 'હું કર્તા છું'  એવો જેનામાં અહંકાર ભાવ નથી, અને ફળની ઇચ્છાથી જેની બુદ્ધિ લોપાતી નથી, તે જ્ઞાની સર્વ પ્રાણીઓને હણી નાખે, તો પણ ખરી રીતે તે મારતો નથી કે બંધન માં પડતો નથી.");
        } else if (2 == i) {
            this.tv1.setText("Eighteenth adhyay tells, Krishna explains the meaning of renunciation and the effects of the modes of nature on human consciousness and activity. He explains Brahman realization, the glories of the Bhagavad-gita, and the ultimate conclusion of the Gita: the highest path of religion is absolute, unconditional loving surrender unto Lord Krishna, which frees one from all sins, brings one to complete enlightenment, and enables one to return to Krishna's eternal spiritual abode.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nसन्न्यासस्य महाबाहो तत्त्वमिच्छामि वेदितुम्‌ ।\nत्यागस्य च हृषीकेश पृथक्केशिनिषूदन ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nસંન્યાસસ્ય મહાબાહો તત્ત્વમિચ્છામિ વેદિતુમ્,\nત્યાગસ્ય ચ હૃષીકેશ પૃથક્કેશિનિષૂદન.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha-\nSannyaasasya mahaabaaho tattwamicchaami veditum;\nTyaagasya cha hrisheekesha prithak keshinishoodana.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे महाबाहो! हे अन्तर्यामिन्‌! हे वासुदेव! मैं संन्यास और त्याग के तत्व को पृथक्‌-पृथक्‌ जानना चाहता हूँ॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે મહાબાહો ! હે ઋષિકેશ ! હે કેશિનીષૂદન ! હું ‘ સન્યાસ’ શબ્દનો ખરો અર્થ અને ’ ત્યાગ ’ શબ્દ નો પણ સત્ય અર્થ પૃથક જાણવા ઈચ્છું છું.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O mighty-armed one, I wish to understand the purpose of renunciation [tyaga] and of the renounced order of life [sannyasa], O killer of the Keshi demon, master of the senses.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nकाम्यानां कर्मणा न्यासं सन्न्यासं कवयो विदुः ।\nसर्वकर्मफलत्यागं प्राहुस्त्यागं विचक्षणाः ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nકામ્યાનાં કર્મણાં ન્યાસં સંન્યાસં કવયો વિદુઃ,\nસર્વકર્મફલત્યાગં પ્રાહુસ્ત્યાગં વિચક્ષણાઃ.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha-\nKaamyaanaam karmanaam nyaasam sannyaasam kavayoviduh;\nSarvakarmaphalatyaagam praahustyaagam vichakshanaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- कितने ही पण्डितजन तो काम्य कर्मों के (स्त्री, पुत्र और धन आदि प्रिय वस्तुओं की प्राप्ति के लिए तथा रोग-संकटादि की निवृत्ति के लिए जो यज्ञ, दान, तप और उपासना आदि कर्म किए जाते हैं, उनका नाम काम्यकर्म है।) त्याग को संन्यास समझते हैं तथा दूसरे विचारकुशल पुरुष सब कर्मों के फल के त्याग को (ईश्वर की भक्ति, देवताओं का पूजन, माता-पितादि गुरुजनों की सेवा, यज्ञ, दान और तप तथा वर्णाश्रम के अनुसार आजीविका द्वारा गृहस्थ का निर्वाह एवं शरीर संबंधी खान-पान इत्यादि जितने कर्तव्यकर्म हैं, उन सबमें इस लोक और परलोक की सम्पूर्ण कामनाओं के त्याग का नाम सब कर्मों के फल का त्याग है) त्याग कहते हैं॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન બોલ્યા : કેટલાક સુક્ષ્મદર્શી પંડિતો કામ્યકર્મો ના ત્યાગને ’સન્યાસ’કહે છે જયારે વિદ્ધાનો સર્વ કર્મોના ફળનો ત્યાગ કરવો એને ત્યાગ કહે છે.(૨)    ");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: The giving up of activities that are based on material desire is what great learned men call the renounced order of life [sannyasa]. And giving up the results of all activities is what the wise call renunciation [tyaga].(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्याज्यं दोषवदित्येके कर्म प्राहुर्मनीषिणः ।\nयज्ञदानतपःकर्म न त्याज्यमिति चापरे ॥");
        } else if (i == 0) {
            this.tv1.setText("ત્યાજ્યં દોષવદિત્યેકે કર્મ પ્રાહુર્મનીષિણઃ,\nયજ્ઞદાનતપઃકર્મ ન ત્યાજ્યમિતિ ચાપરે.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Tyaajyam doshavadityeke karma praahurmaneeshinah;\nYajnadaanatapah karma na tyaajyamiti chaapare.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  कई एक विद्वान ऐसा कहते हैं कि कर्ममात्र दोषयुक्त हैं, इसलिए त्यागने के योग्य हैं और दूसरे विद्वान यह कहते हैं कि यज्ञ, दान और तपरूप कर्म त्यागने योग्य नहीं हैं॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેટલાક પંડિતોનું કહેવું છે કે કર્મ માત્ર દોષયુક્ત હોય છે. આથી તેનો ત્યાગ કરવો.જયારે કેટલાક પંડિતો કહે છે કે યજ્ઞ,દાન.તપ વગેરે કર્મોનો ત્યાગ કરવો નહિ .(૩)");
        } else if (2 == i2) {
            this.tv2.setText("Some learned men declare that all kinds of fruitive activities should be given up as faulty, yet other sages maintain that acts of sacrifice, charity and penance should never be abandoned.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("निश्चयं श्रृणु में तत्र त्यागे भरतसत्तम ।\nत्यागो हि पुरुषव्याघ्र त्रिविधः सम्प्रकीर्तितः ॥");
        } else if (i == 0) {
            this.tv1.setText("નિશ્ચયં શ્રૃણુ મે તત્ર ત્યાગે ભરતસત્તમ,\nત્યાગો હિ પુરુષવ્યાઘ્ર ત્રિવિધઃ સંપ્રકીર્તિતઃ.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Nishchayam shrinu me tatra tyaage bharatasattama;\nTyaago hi purushavyaaghra trividhah samprakeertitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पुरुषश्रेष्ठ अर्जुन ! संन्यास और त्याग, इन दोनों में से पहले त्याग के विषय में तू मेरा निश्चय सुन। क्योंकि त्याग सात्विक, राजस और तामस भेद से तीन प्रकार का कहा गया है॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભરતશ્રેષ્ઠ ! એ ત્યાગ વિષે મારો ચોક્કસ મત શો છે તે તને કહું છું સાંભળ. હે પુરુષવ્યાઘ્ર ! ત્યાગ પણ ત્રણ પ્રકારનો છે.(૪) ");
        } else if (2 == i2) {
            this.tv2.setText("O best of the Bharatas, now hear My judgment about renunciation. O tiger among men, renunciation is declared in the scriptures to be of three kinds.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यज्ञदानतपःकर्म न त्याज्यं कार्यमेव तत्‌ ।\nयज्ञो दानं तपश्चैव पावनानि मनीषिणाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યજ્ઞદાનતપઃકર્મ ન ત્યાજ્યં કાર્યમેવ તત્,\nયજ્ઞો દાનં તપશ્ચૈવ પાવનાનિ મનીષિણામ્.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Yajnadaanatapah karma na tyaajyam kaaryameva tat;\n Yajno daanam tapashchaiva paavanaani maneeshinaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यज्ञ, दान और तपरूप कर्म त्याग करने के योग्य नहीं है, बल्कि वह तो अवश्य कर्तव्य है, क्योंकि यज्ञ, दान और तप -ये तीनों ही कर्म बुद्धिमान पुरुषों को (वह मनुष्य बुद्धिमान है, जो फल और आसक्ति को त्याग कर केवल भगवदर्थ कर्म करता है।) पवित्र करने वाले हैं॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("યજ્ઞ, દાન અને તપરૂપ કર્મ  ત્યાગ કરવા યોગ્ય નથી.તે કરવાજ જોઈએ. યજ્ઞ, દાન અને તપ  ફળની ઈચ્છા રહિત કરવામાં આવે તો તે મનુષ્યને પવિત્ર બનાવે છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("Acts of sacrifice, charity and penance are not to be given up; they must be performed. Indeed, sacrifice, charity and penance purify even the great souls.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतान्यपि तु कर्माणि सङ्‍गं त्यक्त्वा फलानि च ।\nकर्तव्यानीति में पार्थ निश्चितं मतमुत्तमम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("તાન્યપિ તુ કર્માણિ સઙ્ગં ત્યક્ત્વા ફલાનિ ચ,\nકર્તવ્યાનીતિ મે પાર્થ નિશ્ચિતં મતમુત્તમમ્.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Etaanyapi tu karmaani sangam tyaktwaa phalaani cha;\nKartavyaaneeti me paartha nishchitam matamuttamam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इसलिए हे पार्थ! इन यज्ञ, दान और तपरूप कर्मों को तथा और भी सम्पूर्ण कर्तव्यकर्मों को आसक्ति और फलों का त्याग करके अवश्य करना चाहिए, यह मेरा निश्चय किया हुआ उत्तम मत है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ !  એ યજ્ઞાદિ કર્મો પણ સંગનો તથા ફળનો ત્યાગ કરીને કરવા જોઈએ એવો મારો નિશ્વિત અને ઉત્તમ અભિપ્રાય છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("All these activities should be performed without attachment or any expectation of result. They should be performed as a matter of duty, O son of Pritha. That is My final opinion.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नियतस्य तु सन्न्यासः कर्मणो नोपपद्यते ।\nमोहात्तस्य परित्यागस्तामसः परिकीर्तितः ॥");
        } else if (i == 0) {
            this.tv1.setText("નિયતસ્ય તુ સંન્યાસઃ કર્મણો નોપપદ્યતે,\nમોહાત્તસ્ય પરિત્યાગસ્તામસઃ પરિકીર્તિતઃ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Niyatasya tu sannyaasah karmano nopapadyate;\nMohaattasya parityaagas taamasah parikeertitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" (निषिद्ध और काम्य कर्मों का तो स्वरूप से त्याग करना उचित ही है) परन्तु नियत कर्म का (इसी अध्याय के Shlok 48 की टिप्पणी में इसका अर्थ देखना चाहिए।) स्वरूप से त्याग करना उचित नहीं है। इसलिए मोह के कारण उसका त्याग कर देना तामस त्याग कहा गया है॥7॥\n");
        } else if (i2 == 0) {
            this.tv2.setText("નિયત કર્મોનો ત્યાગ કરવો યોગ્ય નથી.તેના મોહથી પરિત્યાગ કરવો તેને તામસ ત્યાગ કહેવાય છે.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("Prescribed duties should never be renounced. If one gives up his prescribed duties because of illusion, such renunciation is said to be in the mode of ignorance.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दुःखमित्येव यत्कर्म कायक्लेशभयात्त्यजेत्‌ ।\nस कृत्वा राजसं त्यागं नैव त्यागफलं लभेत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("દુઃખમિત્યેવ યત્કર્મ કાયક્લેશભયાત્ત્યજેત્,\nસ કૃત્વા રાજસં ત્યાગં નૈવ ત્યાગફલં લભેત્.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Duhkhamityeva yat karma kaayakleshabhayaat tyajet;\nSa kritwaa raajasam tyaagam naiva tyaagaphalam labhet.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कुछ कर्म है वह सब दुःखरूप ही है- ऐसा समझकर यदि कोई शारीरिक क्लेश के भय से कर्तव्य-कर्मों का त्याग कर दे, तो वह ऐसा राजस त्याग करके त्याग के फल को किसी प्रकार भी नहीं पाता॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મ દુઃખરૂપ છે, એમ માની શરીરના કલેશના ભયથી તેનો ત્યાગ કરવો તે રાજસ ત્યાગ કહેવાય છે. એ રીતે રાજસ ત્યાગ કરીને તે પુરુષ ત્યાગના ફળને પામતો નથી.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("Anyone who gives up prescribed duties as troublesome or out of fear of bodily discomfort is said to have renounced in the mode of passion. Such action never leads to the elevation of renunciation.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कार्यमित्येव यत्कर्म नियतं क्रियतेअर्जुन ।\nसङ्‍गं त्यक्त्वा फलं चैव स त्यागः सात्त्विको मतः ॥");
        } else if (i == 0) {
            this.tv1.setText("કાર્યમિત્યેવ યત્કર્મ નિયતં ક્રિયતેર્જુન,\nસઙ્ગં ત્યક્ત્વા ફલં ચૈવ સ ત્યાગઃ સાત્ત્વિકો મતઃ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Kaaryamityeva yatkarma niyatam kriyate’rjuna;\nSangam tyaktwaa phalam chaiva sa tyaagah saattwiko matah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! जो शास्त्रविहित कर्म करना कर्तव्य है- इसी भाव से आसक्ति और फल का त्याग करके किया जाता है- वही सात्त्विक त्याग माना गया है॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન આ કરવા યોગ્ય છે, એમ નિશ્વય કરીને સંગ તથા ફળનો ત્યાગ કરીને જે નિત્યકર્મ કરવામાં આવે છે તેને સાત્વિક ત્યાગ માનેલો છે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("O Arjuna, when one performs his prescribed duty only because it ought to be done, and renounces all material association and all attachment to the fruit, his renunciation is said to be in the mode of goodness.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न द्वेष्ट्यकुशलं कर्म कुशले नानुषज्जते ।\nत्यागी सत्त्वसमाविष्टो मेधावी छिन्नसंशयः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન દ્વેષ્ટ્યકુશલં કર્મ કુશલે નાનુષજ્જતે,\nત્યાગી સત્ત્વસમાવિષ્ટો મેધાવી છિન્નસંશયઃ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Na dweshtyakushalam karma kushale naanushajjate;\nTyaagee sattwasamaavishto medhaavee cchinnasamshayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो मनुष्य अकुशल कर्म से तो द्वेष नहीं करता और कुशल कर्म में आसक्त नहीं होता- वह शुद्ध सत्त्वगुण से युक्त पुरुष संशयरहित, बुद्धिमान और सच्चा त्यागी है॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("સાત્વિક ત્યાગી સત્વગુણથી વ્યાપ્ત થયેલા આત્મજ્ઞાન વાળો થાય છે તથા સર્વ શંકાઓથી રહિત હોય તેવા અશુભ કર્મનો દ્વેષ કરતો નથી.વળી તે વિહિત કર્મમાં આશક્ત થતો નથી.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("The intelligent renouncer situated in the mode of goodness, neither hateful of inauspicious work nor attached to auspicious work, has no doubts about work.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न हि देहभृता शक्यं त्यक्तुं कर्माण्यशेषतः ।\nयस्तु कर्मफलत्यागी स त्यागीत्यभिधीयते ॥");
        } else if (i == 0) {
            this.tv1.setText("ન હિ દેહભૃતા શક્યં ત્યક્તું કર્માણ્યશેષતઃ,\nયસ્તુ કર્મફલત્યાગી સ ત્યાગીત્યભિધીયતે.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Na hi dehabhritaa shakyam tyaktum karmaanyasheshatah;\nYastu karmaphalatyaagi sa tyaageetyabhidheeyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि शरीरधारी किसी भी मनुष्य द्वारा सम्पूर्णता से सब कर्मों का त्याग किया जाना शक्य नहीं है, इसलिए जो कर्मफल त्यागी है, वही त्यागी है- यह कहा जाता है॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("દેહધારી જીવાત્મા માટે સંપૂર્ણ રીતે કર્મનો ત્યાગ કરવો શક્ય નથી.માટે જે કર્મફળ નો ત્યાગ કરનારો છે, તે ત્યાગી એ પ્રમાણે કહેવાય છે.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("It is indeed impossible for an embodied being to give up all activities. But he who renounces the fruits of action is called one who has truly renounced.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनिष्टमिष्टं मिश्रं च त्रिविधं कर्मणः फलम्‌ ।\nभवत्यत्यागिनां प्रेत्य न तु सन्न्यासिनां क्वचित्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અનિષ્ટમિષ્ટં મિશ્રં ચ ત્રિવિધં કર્મણઃ ફલમ્,\nભવત્યત્યાગિનાં પ્રેત્ય ન તુ સંન્યાસિનાં ક્વચિત્.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Anishtamishtam mishram cha trividham karmanah phalam;\nBhavatyatyaaginaam pretya na tu sannyaasinaam kwachit.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कर्मफल का त्याग न करने वाले मनुष्यों के कर्मों का तो अच्छा, बुरा और मिला हुआ- ऐसे तीन प्रकार का फल मरने के पश्चात अवश्य होता है, किन्तु कर्मफल का त्याग कर देने वाले मनुष्यों के कर्मों का फल किसी काल में भी नहीं होता॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મફળના ત્યાગ ન કરનાર ને મૃત્યુ પછી કર્મનું અનિષ્ટ, ઇષ્ટ અને મિશ્ર એમ ત્રણ પ્રકારનું ફળ પ્રાપ્ત થાય છે. પરંતુ સંન્યાસીઓને કદી પણ ત્રણ પ્રકારનું ફળ પ્રાપ્ત થતું નથી.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("For one who is not renounced, the threefold fruits of action—desirable, undesirable and mixed—accrue after death. But those who are in the renounced order of life have no such result to suffer or enjoy.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पञ्चैतानि महाबाहो कारणानि निबोध मे ।\nसाङ्ख्ये कृतान्ते प्रोक्तानि सिद्धये सर्वकर्मणाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પઞ્ચૈતાનિ મહાબાહો કારણાનિ નિબોધ મે,\nસાંખ્યે કૃતાન્તે પ્રોક્તાનિ સિદ્ધયે સર્વકર્મણામ્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Panchaitaani mahaabaaho kaaranaani nibodha me;\nSaankhye kritaante proktaani siddhaye sarvakarmanaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे महाबाहो! सम्पूर्ण कर्मों की सिद्धि के ये पाँच हेतु कर्मों का अंत करने के लिए उपाय बतलाने वाले सांख्य-शास्त्र में कहे गए हैं, उनको तू मुझसे भलीभाँति जान॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો ! કર્મની સમાપ્તિવાળા વેદાંત શાસ્ત્રમાં સર્વ કર્મોથી સિદ્ધિ માટે આ પાંચ સાધનો કહેવામાં આવ્યા છે તે મારી પાસેથી સમજી લે.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("O mighty-armed Arjuna, according to the Vedanta there are five causes for the accomplishment of all action. Now learn of these from Me.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अधिष्ठानं तथा कर्ता करणं च पृथग्विधम्‌ ।\nविविधाश्च पृथक्चेष्टा दैवं चैवात्र पञ्चमम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અધિષ્ઠાનં તથા કર્તા કરણં ચ પૃથગ્વિધમ્,\nવિવિધાશ્ચ પૃથક્ચેષ્ટા દૈવં ચૈવાત્ર પઞ્ચમમ્.(૧૪");
        } else if (2 == i) {
            this.tv1.setText("Adhishthaanam tathaa kartaa karanam cha prithagvidham;\nVividhaashcha prithakcheshtaa daivam chaivaatra panchamam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस विषय में अर्थात कर्मों की सिद्धि में अधिष्ठान (जिसके आश्रय कर्म किए जाएँ, उसका नाम अधिष्ठान है) और कर्ता तथा भिन्न-भिन्न प्रकार के करण (जिन-जिन इंद्रियादिकों और साधनों द्वारा कर्म किए जाते हैं, उनका नाम करण है) एवं नाना प्रकार की अलग-अलग चेष्टाएँ और वैसे ही पाँचवाँ हेतु दैव (पूर्वकृत शुभाशुभ कर्मों के संस्कारों का नाम दैव है) है॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("સુખદુઃખાદિનો આશ્રય કરનાર દેહ, જીવાત્મા, જુદી જુદી ઇન્દ્રિયો, પ્રાણપાનાદિ વાયુના નાના પ્રકારની ક્રિયાઓ અને દૈવ (એટલેકે  વાયુ, સૂર્ય વગેરે ઇન્દ્રિયોના દેવતાઓ) આ પાંચ કારણો છે.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("The place of action [the body], the performer, the various senses, the many different kinds of endeavor, and ultimately the Supersoul—these are the five factors of action.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शरीरवाङ्‍मनोभिर्यत्कर्म प्रारभते नरः ।\nन्याय्यं वा विपरीतं वा पञ्चैते तस्य हेतवः॥");
        } else if (i == 0) {
            this.tv1.setText("શરીરવાઙ્મનોભિર્યત્કર્મ પ્રારભતે નરઃ,\nન્યાય્યં વા વિપરીતં વા પઞ્ચૈતે તસ્ય હેતવઃ.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Shareeravaangmanobhiryat karma praarabhate narah;\nNyaayyam vaa vipareetam vaa panchaite tasya hetavah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मनुष्य मन, वाणी और शरीर से शास्त्रानुकूल अथवा विपरीत जो कुछ भी कर्म करता है- उसके ये पाँचों कारण हैं॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("પુરુષ દેહ, મન અને વાણી વડે જે ધર્મરૂપ કે અધર્મ રૂપ પણ કર્મનો પ્રારંભ કરેછે, તે સર્વ કર્મોના આ પાંચ કારણો છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Whatever right or wrong action a man performs by body, mind or speech is caused by these five factors.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्रैवं सति कर्तारमात्मानं केवलं तु यः ।\nपश्यत्यकृतबुद्धित्वान्न स पश्यति दुर्मतिः ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્રૈવં સતિ કર્તારમાત્માનં કેવલં તુ યઃ,\nપશ્યત્યકૃતબુદ્ધિત્વાન્ન સ પશ્યતિ દુર્મતિઃ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Tatraivam sati kartaaram aatmaanam kevalam tu yah;\nPashyatyakritabuddhitwaan na sa pashyati durmatih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु ऐसा होने पर भी जो मनुष्य अशुद्ध बुद्धि (सत्संग और शास्त्र के अभ्यास से तथा भगवदर्थ कर्म और उपासना के करने से मनुष्य की बुद्धि शुद्ध होती है, इसलिए जो उपर्युक्त साधनों से रहित है, उसकी बुद्धि अशुद्ध है, ऐसा समझना चाहिए।) होने के कारण उस विषय में यानी कर्मों के होने में केवल शुद्ध स्वरूप आत्मा को कर्ता समझता है, वह मलीन बुद्धि वाला अज्ञानी यथार्थ नहीं समझता॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે સર્વ કર્મોમાં આ પ્રમાણે હોવા છતાં પણ જે શુદ્ધ આત્માને કર્તા માને છે, સમજે છે તે - દુર્મતિ, અસંસ્કારી બુદ્ધિને લીધે વાસ્તવિક રીતે જોતો નથી.(૧૬) ");
        } else if (2 == i2) {
            this.tv2.setText("Therefore one who thinks himself the only doer, not considering the five factors, is certainly not very intelligent and cannot see things as they are.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यस्य नाहङ्‍कृतो भावो बुद्धिर्यस्य न लिप्यते ।\nहत्वापि स इमाँल्लोकान्न हन्ति न निबध्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યસ્ય નાહંકૃતો ભાવો બુદ્ધિર્યસ્ય ન લિપ્યતે,\nહત્વાપિ સ ઇમા્લોકાન્ન હન્તિ ન નિબધ્યતે.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Yasya naahankrito bhaavo buddhiryasya na lipyate;\nHatwaapi sa imaam llokaan na hanti na nibadhyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस पुरुष के अन्तःकरण में 'मैं कर्ता हूँ' ऐसा भाव नहीं है तथा जिसकी बुद्धि सांसारिक पदार्थों में और कर्मों में लिपायमान नहीं होती, वह पुरुष इन सब लोकों को मारकर भी वास्तव में न तो मरता है और न पाप से बँधता है। (जैसे अग्नि, वायु और जल द्वारा प्रारब्धवश किसी प्राणी की हिंसा होती देखने में आए तो भी वह वास्तव में हिंसा नहीं है, वैसे ही जिस पुरुष का देह में अभिमान नहीं है और स्वार्थरहित केवल संसार के हित के लिए ही जिसकी सम्पूर्ण क्रियाएँ होती हैं, उस पुरुष के शरीर और इन्द्रियों द्वारा यदि किसी प्राणी की हिंसा होती हुई लोकदृष्टि में देखी जाए, तो भी वह वास्तव में हिंसा नहीं है क्योंकि आसक्ति, स्वार्थ और अहंकार के न होने से किसी प्राणी की हिंसा हो ही नहीं सकती तथा बिना कर्तृत्वाभिमान के किया हुआ कर्म वास्तव में अकर्म ही है, इसलिए वह पुरुष 'पाप से नहीं बँधता'।)॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું આ કર્મ કરું છું.એ પ્રકારની જેને ભાવના નથી, જેની બુદ્ધિ લેપાતી નથી તે જ્ઞાનનિષ્ઠ આ પ્રાણીઓનો વધ કરી  નાખે તો પણ તે વધ કરતો નથી.અને તે વધના દોષથી બંધાતો નથી.(૧૭)  ");
        } else if (2 == i2) {
            this.tv2.setText("One who is not motivated by false ego, whose intelligence is not entangled, though he kills men in this world, does not kill. Nor is he bound by his actions.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञानं ज्ञेयं परिज्ञाता त्रिविधा कर्मचोदना ।\nकरणं कर्म कर्तेति त्रिविधः कर्मसङ्ग्रहः ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞાનં જ્ઞેયં પરિજ્ઞાતા ત્રિવિધા કર્મચોદના,\nકરણં કર્મ કર્તેતિ ત્રિવિધઃ કર્મસંગ્રહઃ.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("gnaanam gneyam parijnaataa trividhaa karmachodanaa;\nKaranam karma karteti trividhah karmasangrahah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("ज्ञाता (जानने वाले का नाम 'ज्ञाता' है।), ज्ञान (जिसके द्वारा जाना जाए, उसका नाम 'ज्ञान' है। ) और ज्ञेय (जानने में आने वाली वस्तु का नाम 'ज्ञेय' है।)- ये तीनों प्रकार की कर्म-प्रेरणा हैं और कर्ता (कर्म करने वाले का नाम 'कर्ता' है।), करण (जिन साधनों से कर्म किया जाए, उनका नाम 'करण' है।) तथा क्रिया (करने का नाम 'क्रिया' है।)- ये तीनों प्रकार का कर्म-संग्रह है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("જ્ઞાન, જ્ઞેય અને જ્ઞાતા એ ત્રણ પ્રકારના કર્મનો પ્રેરક છે અને કરણ (મન અને બુદ્ધિ સહિત દશ ઇન્દ્રિયો ) કર્મ અને કર્તા એ પ્રકારે ત્રણ પ્રકારનો કર્મનો આશ્રય છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("Knowledge, the object of knowledge, and the knower are the three factors that motivate action; the senses, the work and the doer are the three constituents of action.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञानं कर्म च कर्ता च त्रिधैव गुणभेदतः ।\nप्रोच्यते गुणसङ्ख्याने यथावच्छ्णु तान्यपि ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞાનં કર્મ ચ કર્તા ચ ત્રિધૈવ ગુણભેદતઃ,\nપ્રોચ્યતે ગુણસંખ્યાને યથાવચ્છૃણુ તાન્યપિ.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("gnaanam karma cha kartaa cha tridhaiva gunabhedatah;\nProchyate gunasankhyaane yathaavacchrinu taanyapi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("गुणों की संख्या करने वाले शास्त्र में ज्ञान और कर्म तथा कर्ता गुणों के भेद से तीन-तीन प्रकार के ही कहे गए हैं, उनको भी तु मुझसे भलीभाँति सुन॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("સાંખ્યશાસ્ત્રમાં જ્ઞાન,કર્મ તથા કર્તા સત્વાદિ ત્રણ ગુણના ભેદથી ત્રણ પ્રકારના કહેવાય છે. તે ભેદ ને યથાર્થ રીતે તું સાંભળ.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("According to the three different modes of material nature, there are three kinds of knowledge, action and performer of action. Now hear of them from Me.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वभूतेषु येनैकं भावमव्ययमीक्षते ।\nअविभक्तं विभक्तेषु तज्ज्ञानं विद्धि सात्त्विकम् ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વભૂતેષુ યેનૈકં ભાવમવ્યયમીક્ષતે,\nઅવિભક્તં વિભક્તેષુ તજ્જ્ઞાનં વિદ્ધિ સાત્ત્વિકમ્.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Sarvabhooteshu yenaikam bhaavamavyayameekshate;\nAvibhaktam vibhakteshu tajjnaanam viddhi saattwikam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस ज्ञान से मनुष्य पृथक-पृथक सब भूतों में एक अविनाशी परमात्मभाव को विभागरहित समभाव से स्थित देखता है, उस ज्ञान को तू सात्त्विक जान॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે જ્ઞાનના યોગથી જીવ પરસ્પર ભેદવાળા સર્વ ભૂતોમાં અવિભક્ત એવા એક આત્મતત્વને જુએ છે તે જ્ઞાનને તું સાત્વિક જાણ.(૨૦) ");
        } else if (2 == i2) {
            this.tv2.setText("That knowledge by which one undivided spiritual nature is seen in all living entities, though they are divided into innumerable forms, you should understand to be in the mode of goodness.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पृथक्त्वेन तु यज्ज्ञानं नानाभावान्पृथग्विधान्‌ ।\nवेत्ति सर्वेषु भूतेषु तज्ज्ञानं विद्धि राजसम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પૃથક્ત્વેન તુ યજ્જ્ઞાનં નાનાભાવાન્પૃથગ્વિધાન્,\nવેત્તિ સર્વેષુ ભૂતેષુ તજ્જ્ઞાનં વિદ્ધિ રાજસમ્.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Prithaktwena tu yajjnaanam naanaabhaavaan prithagvidhaan;\nVetti sarveshu bhooteshu tajjnaanam viddhi raajasam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" किन्तु जो ज्ञान अर्थात जिस ज्ञान के द्वारा मनुष्य सम्पूर्ण भूतों में भिन्न-भिन्न प्रकार के नाना भावों को अलग-अलग जानता है, उस ज्ञान को तू राजस जान॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી પરસ્પર ભેદથી રહેલા સર્વ ભૂતોમાં એક બીજાથી ભિન્ન ઘણા આત્માઓને જે જ્ઞાન જાણે છે તે જ્ઞાનને તું રાજસ જ્ઞાન જાણ.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("That knowledge by which one sees that in every different body there is a different type of living entity you should understand to be in the mode of passion.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्तु कृत्स्नवदेकस्मिन्कार्ये सक्तमहैतुकम्‌।\nअतत्त्वार्थवदल्पंच तत्तामसमुदाहृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("યત્તુ કૃત્સ્નવદેકસ્મિન્કાર્યે સક્તમહૈતુકમ્,\nઅતત્ત્વાર્થવદલ્પં ચ તત્તામસમુદાહૃતમ્.(૨૨)\n");
        } else if (2 == i) {
            this.tv1.setText("Yattu kritsnavadekasmin kaarye saktamahaitukam;\nAtattwaarthavadalpam cha tattaamasamudaahritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु जो ज्ञान एक कार्यरूप शरीर में ही सम्पूर्ण के सदृश आसक्त है तथा जो बिना युक्तिवाला, तात्त्विक अर्थ से रहित और तुच्छ है- वह तामस कहा गया है॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી જે જ્ઞાન એક કર્મ માં પરિપૂર્ણ ની જેમ અભિનિવેશવાળું હેતુ વિનાનું તત્વાર્થ થી રહિત તથા અલ્પ વિષય વાળું છે તે જ્ઞાનને તામસ કહ્યું છે.(૨૨) ");
        } else if (2 == i2) {
            this.tv2.setText("And that knowledge by which one is attached to one kind of work as the all in all, without knowledge of the truth, and which is very meager, is said to be in the mode of darkness.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नियतं सङ्‍गरहितमरागद्वेषतः कृतम।\nअफलप्रेप्सुना कर्म यत्तत्सात्त्विकमुच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("નિયતં સઙ્ગરહિતમરાગદ્વેષતઃ કૃતમ્,\nઅફલપ્રેપ્સુના કર્મ યત્તત્સાત્ત્વિકમુચ્યતે.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Niyatam sangarahitam araagadweshatah kritam;\nAphalaprepsunaa karma yattat saattwikamuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कर्म शास्त्रविधि से नियत किया हुआ और कर्तापन के अभिमान से रहित हो तथा फल न चाहने वाले पुरुष द्वारा बिना राग-द्वेष के किया गया हो- वह सात्त्विक कहा जाता है॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("ફળની ઈચ્છા ન રાખતાં જે નિત્ય નૈમિત્તિક કર્મો, કર્તુત્વ ના અભિમાનના ત્યાગ પૂર્વક રાગ-દ્વેષ રહિત કરવામાં આવે છે તેને સાત્વિક કર્મ કહેવામાં આવે છે.(૨૩) ");
        } else if (2 == i2) {
            this.tv2.setText("That action which is regulated and which is performed without attachment, without love or hatred, and without desire for fruitive results is said to be in the mode of goodness.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्तु कामेप्सुना कर्म साहङ्‍कारेण वा पुनः।\nक्रियते बहुलायासं तद्राजसमुदाहृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("યત્તુ કામેપ્સુના કર્મ સાહઙ્કારેણ વા પુનઃ,\nક્રિયતે બહુલાયાસં તદ્રાજસમુદાહૃતમ્.(૨૪)\n");
        } else if (2 == i) {
            this.tv1.setText("Yattu kaamepsunaa karma saahankaarena vaa punah;\nKriyate bahulaayaasam tadraajasamudaahritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु जो कर्म बहुत परिश्रम से युक्त होता है तथा भोगों को चाहने वाले पुरुष द्वारा या अहंकारयुक्त पुरुष द्वारा किया जाता है, वह कर्म राजस कहा गया है॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી સ્વર્ગાદિ ફળની કામનાવાળા તથા અહંકાર વાળા મનુષ્યો દ્વારા બહુ પરિશ્રમ વડે જે કરાય છે, તે રાજસ કહ્યું છે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("But action performed with great effort by one seeking to gratify his desires, and enacted from a sense of false ego, is called action in the mode of passion.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनुबन्धं क्षयं हिंसामनवेक्ष्य च पौरुषम्‌ ।\nमोहादारभ्यते कर्म यत्तत्तामसमुच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("અનુબન્ધં ક્ષયં હિંસામનપેક્ષ્ય ચ પૌરુષમ્,\nમોહાદારભ્યતે કર્મ યત્તત્તામસમુચ્યતે.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Anubandham kshayam himsaam anavekshya cha paurusham;\nMohaadaarabhyate karma yattat taamasamuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कर्म परिणाम, हानि, हिंसा और सामर्थ्य को न विचारकर केवल अज्ञान से आरंभ किया जाता है, वह तामस कहा जाता है॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે કર્મ પરિણામ નો, હાનિનો, હિંસાનો તથા પોતાના સામર્થ્યનો વિચાર કર્યા વગર અવિવેકથી આરંભ કરવામાં આવે છે તેને તામસ કર્મ કહે છે. (૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("That action performed in illusion, in disregard of scriptural injunctions, and without concern for future bondage or for violence or distress caused to others is said to be in the mode of ignorance.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मुक्तसङ्‍गोऽनहंवादी धृत्युत्साहसमन्वितः ।\nसिद्धयसिद्धयोर्निर्विकारः कर्ता सात्त्विक उच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("મુક્તસઙ્ગોનહંવાદી ધૃત્યુત્સાહસમન્વિતઃ,\nસિદ્ધ્યસિદ્ધ્યોર્નિર્વિકારઃ કર્તા સાત્ત્વિક ઉચ્યતે.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Muktasango’nahamvaadi dhrityutsaahasamanvitah;\nSiddhyasiddhyor nirvikaarah kartaa saattwika uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कर्ता संगरहित, अहंकार के वचन न बोलने वाला, धैर्य और उत्साह से युक्त तथा कार्य के सिद्ध होने और न होने में हर्ष -शोकादि विकारों से रहित है- वह सात्त्विक कहा जाता है॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("ફળની ઈચ્છા વગરનો. ‘ હું કર્તા છું.’ એમ નહિ કહેનારો, ધૈર્ય તથા ઉત્સાહથી યુક્ત સિદ્ધિમાં અને અસિદ્ધિમાં વિકાર રહિત કર્મ કરનારો, સાત્વિક કહેવાય છે.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("One who performs his duty without association with the modes of material nature, without false ego, with great determination and enthusiasm, and without wavering in success or failure is said to be a worker in the mode of goodness.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रागी कर्मफलप्रेप्सुर्लुब्धो हिंसात्मकोऽशुचिः।\nहर्षशोकान्वितः कर्ता राजसः परिकीर्तितः॥");
        } else if (i == 0) {
            this.tv1.setText("રાગી કર્મફલપ્રેપ્સુર્લુબ્ધો હિંસાત્મકોશુચિઃ,\nહર્ષશોકાન્વિતઃ કર્તા રાજસઃ પરિકીર્તિતઃ.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Raagee karmaphalaprepsur lubdho himsaatmako’shuchih;\nHarshashokaanvitah kartaa raajasah parikeertitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो कर्ता आसक्ति से युक्त कर्मों के फल को चाहने वाला और लोभी है तथा दूसरों को कष्ट देने के स्वभाववाला, अशुद्धाचारी और हर्ष-शोक से लिप्त है वह राजस कहा गया है॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("રાગી, કર્મફળની ઇચ્છાવાળો, લોભી,હિંસા કરવાવાળો,અપવિત્ર તથા હર્ષ-શોકવાળા કર્તાને રાજસ કહેવામાં આવે છે.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("The worker who is attached to work and the fruits of work, desiring to enjoy those fruits, and who is greedy, always envious, impure, and moved by joy and sorrow, is said to be in the mode of passion.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आयुक्तः प्राकृतः स्तब्धः शठोनैष्कृतिकोऽलसः ।\nविषादी दीर्घसूत्री च कर्ता तामस उच्यते॥");
        } else if (i == 0) {
            this.tv1.setText("અયુક્તઃ પ્રાકૃતઃ સ્તબ્ધઃ શઠો નૈષ્કૃતિકોલસઃ,\nવિષાદી દીર્ઘસૂત્રી ચ કર્તા તામસ ઉચ્યતે.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Ayuktah praakritah stabdhah shatho naishkritiko’lasah;\nVishaadee deerghasootree cha kartaa taamasa uchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो कर्ता अयुक्त, शिक्षा से रहित घमंडी, धूर्त और दूसरों की जीविका का नाश करने वाला तथा शोक करने वाला, आलसी और दीर्घसूत्री (दीर्घसूत्री उसको कहा जाता है कि जो थोड़े काल में होने लायक साधारण कार्य को भी फिर कर लेंगे, ऐसी आशा से बहुत काल तक नहीं पूरा करता। ) है वह तामस कहा जाता है॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("અસ્થિર ચિત્તવાળો, અસંસ્કારી, ઉદ્ધત, શઠ, બીજાની આજીવિકાનો નાશ કરનાર, આળસુ,વિષાદ કરવાના સ્વભાવવાળો તથા કાર્યને લંબાવવાના સ્વભાવવાળો કર્તા તામસ કહેવાય છે.(૨૮)  ");
        } else if (2 == i2) {
            this.tv2.setText("The worker who is always engaged in work against the injunctions of the scripture, who is materialistic, obstinate, cheating and expert in insulting others, and who is lazy, always morose and procrastinating is said to be a worker in the mode of ignorance.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बुद्धेर्भेदं धृतेश्चैव गुणतस्त्रिविधं श्रृणु ।\nप्रोच्यमानमशेषेण पृथक्त्वेन धनंजय ॥");
        } else if (i == 0) {
            this.tv1.setText("બુદ્ધેર્ભેદં ધૃતેશ્ચૈવ ગુણતસ્ત્રિવિધં શ્રૃણુ,\nપ્રોચ્યમાનમશેષેણ પૃથક્ત્વેન ધનઞ્જય.(૨૯)\n");
        } else if (2 == i) {
            this.tv1.setText("Buddherbhedam dhriteshchaiva gunatastrividham shrinu;\nProchyamaanamasheshena prithaktwena dhananjaya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे धनंजय ! अब तू बुद्धि का और धृति का भी गुणों के अनुसार तीन प्रकार का भेद मेरे द्वारा सम्पूर्णता से विभागपूर्वक कहा जाने वाला सुन॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ધનંજય ! બુદ્ધિના તેમજ ધૈર્યના સત્વાદિક ગુણોથી ત્રણ પ્રકારના ભેદને સંપૂર્ણ પણે જુદાં જુદા કહેવાય છે, તે તું સાંભળ.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("O winner of wealth, now please listen as I tell you in detail of the different kinds of understanding and determination, according to the three modes of material nature.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रवत्तिं च निवृत्तिं च कार्याकार्ये भयाभये।\nबन्धं मोक्षं च या वेति बुद्धिः सा पार्थ सात्त्विकी ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રવૃત્તિં ચ નિવૃત્તિં ચ કાર્યાકાર્યે ભયાભયે,\nબન્ધં મોક્ષં ચ યા વેત્તિ બુદ્ધિઃ સા પાર્થ સાત્ત્વિકી.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Pravrittim cha nivrittim cha karyaakaarye bhayaabhaye;\nBandhammoksham cha yaa vetti buddhih saa paartha saattwikee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  हे पार्थ ! जो बुद्धि प्रवृत्तिमार्ग (गृहस्थ में रहते हुए फल और आसक्ति को त्यागकर भगवदर्पण बुद्धि से केवल लोकशिक्षा के लिए राजा जनक की भाँति बरतने का नाम 'प्रवृत्तिमार्ग' है।) और निवृत्ति मार्ग को (देहाभिमान को त्यागकर केवल सच्चिदानंदघन परमात्मा में एकीभाव स्थित हुए श्री शुकदेवजी और सनकादिकों की भाँति संसार से उपराम होकर विचरने का नाम 'निवृत्तिमार्ग' है।), कर्तव्य और अकर्तव्य को, भय और अभय को तथा बंधन और मोक्ष को यथार्थ जानती है- वह बुद्धि सात्त्विकी है ॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! જે બુદ્ધિ પ્રવૃતિને તથા નિવૃત્તિને તેમજ કાર્ય તથા અકાર્યને, ભય તથા અભયને, બંધન તથા મોક્ષને જાણે છે તે બુદ્ધિ સાત્વિક છે.(૩૦) ");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, that understanding by which one knows what ought to be done and what ought not to be done, what is to be feared and what is not to be feared, what is binding and what is liberating, is in the mode of goodness.(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        setTitle("Shlok 31");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यया धर्ममधर्मं च कार्यं चाकार्यमेव च।\nअयथावत्प्रजानाति बुद्धिः सा पार्थ राजसी॥");
        } else if (i == 0) {
            this.tv1.setText("યયા ધર્મમધર્મં ચ કાર્યં ચાકાર્યમેવ ચ,\nઅયથાવત્પ્રજાનાતિ બુદ્ધિઃ સા પાર્થ રાજસી.(૩૧)\n");
        } else if (2 == i) {
            this.tv1.setText("Yayaa dharmamadharmam cha kaaryam chaakaaryameva cha;\nAyathaavat prajaanaati buddhih saa paartha raajasee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे पार्थ! मनुष्य जिस बुद्धि के द्वारा धर्म और अधर्म को तथा कर्तव्य और अकर्तव्य को भी यथार्थ नहीं जानता, वह बुद्धि राजसी है॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! જે બુદ્ધિ ધર્મને તથા અધર્મને, કાર્ય તેમજ અકાર્યને યથાર્થ રીતે નહિ જાણે તે બુદ્ધિ રાજસી છે.(૩૧) ");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, that understanding which cannot distinguish between religion and irreligion, between action that should be done and action that should not be done, is in the mode of passion.(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        setTitle("Shlok 32");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अधर्मं धर्ममिति या मन्यते तमसावृता।\nसर्वार्थान्विपरीतांश्च बुद्धिः सा पार्थ तामसी॥");
        } else if (i == 0) {
            this.tv1.setText("અધર્મં ધર્મમિતિ યા મન્યતે તમસાવૃતા,\nસર્વાર્થાન્વિપરીતાંશ્ચ બુદ્ધિઃ સા પાર્થ તામસી.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Adharmam dharmamiti yaa manyate tamasaavritaa;\nSarvaarthaan vipareetaamshcha buddhih saa paartha taamasee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! जो तमोगुण से घिरी हुई बुद्धि अधर्म को भी 'यह धर्म है' ऐसा मान लेती है तथा इसी प्रकार अन्य संपूर्ण पदार्थों को भी विपरीत मान लेती है, वह बुद्धि तामसी है॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! તમોગુણથી ઢંકાયેલી જે બુદ્ધિ અધર્મને ધર્મ છે એમ માને છે તથા સર્વ પદાર્થોને વિપરીત માને છે, તે તામસી બુદ્ધિ છે.(૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("That understanding which considers irreligion to be religion and religion to be irreligion, under the spell of illusion and darkness, and strives always in the wrong direction, O Partha, is in the mode of ignorance.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("धृत्या यया धारयते मनःप्राणेन्द्रियक्रियाः।\nयोगेनाव्यभिचारिण्या धृतिः सा पार्थ सात्त्विकी ॥");
        } else if (i == 0) {
            this.tv1.setText("ધૃત્યા યયા ધારયતે મનઃપ્રાણેન્દ્રિયક્રિયાઃ,\nયોગેનાવ્યભિચારિણ્યા ધૃતિઃ સા પાર્થ સાત્ત્વિકી.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Dhrityaa yayaa dhaarayate manah praanendriyakriyaah;\nYogenaavyabhichaarinyaa dhritih saa paartha saattwikee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे पार्थ! जिस अव्यभिचारिणी धारण शक्ति (भगवद्विषय के सिवाय अन्य सांसारिक विषयों को धारण करना ही व्यभिचार दोष है, उस दोष से जो रहित है वह 'अव्यभिचारिणी धारणा' है।) से मनुष्य ध्यान योग के द्वारा मन, प्राण और इंद्रियों की क्रियाओं ( मन, प्राण और इंद्रियों को भगवत्प्राप्ति के लिए भजन, ध्यान और निष्काम कर्मों में लगाने का नाम 'उनकी क्रियाओं को धारण करना' है।) को धारण करता है, वह धृति सात्त्विकी है॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! ચિત્તવૃતિના નિરોધરૂપ યોગથી કામનાઓ ચલિત નહિ થનારી ધીરજથી મન, પ્રાણ અને ઇન્દ્રિયોની ક્રિયાને ધારણ કરે છે. તે ધૈર્ય સાત્વિક કહેવાય છે.(૩૩) ");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, that determination which is unbreakable, which is sustained with steadfastness by yoga practice, and which thus controls the activities of the mind, life and senses is determination in the mode of goodness.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यया तु धर्मकामार्थान्धत्या धारयतेऽर्जुन।\nप्रसङ्‍गेन फलाकाङ्क्षी धृतिः सा पार्थ राजसी॥");
        } else if (i == 0) {
            this.tv1.setText("યયા તુ ધર્મકામાર્થાન્ ધૃત્યા ધારયતેર્જુન,\nપ્રસઙ્ગેન ફલાકાઙ્ક્ષી ધૃતિઃ સા પાર્થ રાજસી.(૩૪)\n");
        } else if (2 == i) {
            this.tv1.setText("Yayaa tu dharmakaamaarthaan dhrityaa dhaarayate’rjuna;\nPrasangena phalaakaangkshee dhritih saa paartha raajasee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परंतु हे पृथापुत्र अर्जुन! फल की इच्छावाला मनुष्य जिस धारण शक्ति के द्वारा अत्यंत आसक्ति से धर्म, अर्थ और कामों को धारण करता है, वह धारण शक्ति राजसी है॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! વળી પ્રસંગાનુસાર ફળની કામનાવાળો થઇ જે ધૈર્ય વડે ધર્મ, કામ અને અર્થને પ્રાપ્ત કરે છે તે ધૈર્ય રાજસી છે.(૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("But that determination by which one holds fast to fruitive results in religion, economic development and sense gratification is of the nature of passion, O Arjuna.(34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        setTitle("Shlok 35");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यया स्वप्नं भयं शोकं विषादं मदमेव च।\nन विमुञ्चति दुर्मेधा धृतिः सा पार्थ तामसी॥");
        } else if (i == 0) {
            this.tv1.setText("યયા સ્વપ્નં ભયં શોકં વિષાદં મદમેવ ચ,\nન વિમુઞ્ચતિ દુર્મેધા ધૃતિઃ સા પાર્થ તામસી.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Yayaa swapnam bhayam shokam vishaadam madameva cha;\nNa vimunchati durmedhaa dhritih saa paartha taamasee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! दुष्ट बुद्धिवाला मनुष्य जिस धारण शक्ति के द्वारा निद्रा, भय, चिंता और दु:ख को तथा उन्मत्तता को भी नहीं छोड़ता अर्थात धारण किए रहता है- वह धारण शक्ति तामसी है॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! ભાગ્યહીન મનુષ્ય જે ધૈર્ય વડે સ્વપ્ન, ભય, વિષાદ તથા મદ ને પણ ત્યજતો નથી તે ધૈર્ય તામસી છે.(૩૫) ");
        } else if (2 == i2) {
            this.tv2.setText("And that determination which cannot go beyond dreaming, fearfulness, lamentation, moroseness and illusion—such unintelligent determination, O son of Pritha, is in the mode of darkness.(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36_37() {
        setTitle("Shlok 36,37");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सुखं त्विदानीं त्रिविधं श्रृणु मे भरतर्षभ।\nअभ्यासाद्रमते यत्र दुःखान्तं च निगच्छति॥\nयत्तदग्रे विषमिव परिणामेऽमृतोपमम्‌।\nतत्सुखं सात्त्विकं प्रोक्तमात्मबुद्धिप्रसादजम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("સુખં ત્વિદાનીં ત્રિવિધં શ્રૃણુ મે ભરતર્ષભ,\nઅભ્યાસાદ્રમતે યત્ર દુઃખાન્તં ચ નિગચ્છતિ. \nયત્તદગ્રે વિષમિવ પરિણામેમૃતોપમમ્,\nતત્સુખં સાત્ત્વિકં પ્રોક્તમાત્મબુદ્ધિપ્રસાદજમ્.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Sukham twidaaneem trividham shrinu me bharatarshabha;\nAbhyaasaadramate yatra duhkhaantam cha nigacchati.\nYattadagre vishamiva parinaame’mritopamam;\nTatsukham saattwikam proktam aatmabuddhiprasaadajam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  हे भरतश्रेष्ठ! अब तीन प्रकार के सुख को भी तू मुझसे सुन। जिस सुख में साधक मनुष्य भजन, ध्यान और सेवादि के अभ्यास से रमण करता है और जिससे दुःखों के अंत को प्राप्त हो जाता है, जो ऐसा सुख है, वह आरंभकाल में यद्यपि विष के तुल्य प्रतीत (जैसे खेल में आसक्ति वाले बालक को विद्या का अभ्यास मूढ़ता के कारण प्रथम विष के तुल्य भासता है वैसे ही विषयों में आसक्ति वाले पुरुष को भगवद्भजन, ध्यान, सेवा आदि साधनाओं का अभ्यास मर्म न जानने के कारण प्रथम 'विष के तुल्य प्रतीत होता' है) होता है, परन्तु परिणाम में अमृत के तुल्य है, इसलिए वह परमात्मविषयक बुद्धि के प्रसाद से उत्पन्न होने वाला सुख सात्त्विक कहा गया है॥36-37॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભરત શ્રેષ્ઠ ! હવે તું મારી પાસેથી ત્રણ પ્રકારનાં સુખને સાંભળ.જે સમાધિસુખમાં અભ્યાસથી રમણ કરે છે તથા દુઃખ ના અંત ને પામે છે.જે તે સુખ આરંભમાં વિષ જેવું પરંતુ પરિણામમાં અમૃત જેવું હોય તથા પોતાની નિર્મળ બુદ્ધિથી ઉત્પન્ન થયેલું હોય તે સુખને સાત્વિક કહ્યું છે.(૩૬,૩૭) ");
        } else if (2 == i2) {
            this.tv2.setText("O best of the Bharatas, now please hear from Me about the three kinds of happiness by which the conditioned soul enjoys, and by which he sometimes comes to the end of all distress. That which in the beginning may be just like poison but at the end is just like nectar and which awakens one to self-realization is said to be happiness in the mode of goodness.(36-37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        setTitle("Shlok 38");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("विषयेन्द्रियसंयोगाद्यत्तदग्रेऽमृतोपमम्‌।\nपरिणामे विषमिव तत्सुखं राजसं स्मृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("વિષયેન્દ્રિયસંયોગાદ્યત્તદગ્રેમૃતોપમમ્,\nપરિણામે વિષમિવ તત્સુખં રાજસં સ્મૃતમ્.(૩૮) ");
        } else if (2 == i) {
            this.tv1.setText("Vishayendriya samyogaad yattadagre’mritopamam;\nParinaame vishamiva tatsukham raajasam smritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो सुख विषय और इंद्रियों के संयोग से होता है, वह पहले- भोगकाल में अमृत के तुल्य प्रतीत होने पर भी परिणाम में विष के तुल्य (बल, वीर्य, बुद्धि, धन, उत्साह और परलोक का नाश होने से विषय और इंद्रियों के संयोग से होने वाले सुख को 'परिणाम में विष के तुल्य' कहा है) है इसलिए वह सुख राजस कहा गया है॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે તે સુખ વિષય તથા ઇન્દ્રિયોના સંયોગ થી ઉપજેલું છે તે આરંભમાં અમૃત જેવું લાગે છે પણ પછી પરિણામમાં વિષ જેવું લાગે છે તે સુખ ને રાજસ કહ્યું છે.(૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("That happiness which is derived from contact of the senses with their objects and which appears like nectar at first but poison at the end is said to be of the nature of passion.(38)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        setTitle("Shlok 39");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदग्रे चानुबन्धे च सुखं मोहनमात्मनः।\nनिद्रालस्यप्रमादोत्थं तत्तामसमुदाहृतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("યદગ્રે ચાનુબન્ધે ચ સુખં મોહનમાત્મનઃ,\nનિદ્રાલસ્યપ્રમાદોત્થં તત્તામસમુદાહૃતમ્.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Yadagre chaanubandhe cha sukham mohanamaatmanah;\nNidraalasyapramaadottham tattaamasamudaahritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो सुख भोगकाल में तथा परिणाम में भी आत्मा को मोहित करने वाला है, वह निद्रा, आलस्य और प्रमाद से उत्पन्न सुख तामस कहा गया है॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે સુખઆરંભમાં તથા પરિણામે બુદ્ધિને મોહમાં નાખનારું, નિંદ્રા, આળસ અને પ્રમાદથી ઉત્પન્ન થયેલું છે તે સુખ તામસ કહ્યું છે. (૩૯) ");
        } else if (2 == i2) {
            this.tv2.setText("And that happiness which is blind to self-realization, which is delusion from beginning to end and which arises from sleep, laziness and illusion is said to be of the nature of ignorance.(39)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        setTitle("Shlok 40");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न तदस्ति पृथिव्यां वा दिवि देवेषु वा पुनः।\nसत्त्वं प्रकृतिजैर्मुक्तं यदेभिःस्यात्त्रिभिर्गुणैः॥");
        } else if (i == 0) {
            this.tv1.setText("ન તદસ્તિ પૃથિવ્યાં વા દિવિ દેવેષુ વા પુનઃ,\nસત્ત્વં પ્રકૃતિજૈર્મુક્તં યદેભિઃ સ્યાત્ત્રિભિર્ગુણૈઃ.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Na tadasti prithivyaam vaa divi deveshu vaa punah;\nSattwam prakritijairmuktam yadebhih syaat tribhirgunaih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पृथ्वी में या आकाश में अथवा देवताओं में तथा इनके सिवा और कहीं भी ऐसा कोई भी सत्त्व नहीं है, जो प्रकृति से उत्पन्न इन तीनों गुणों से रहित हो॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("પૃથ્વીમાં કે પાતાળમાં અથવા સ્વર્ગમાં દેવોને વિષે પણ એવું તે કંઈ વિદ્યમાન નથી કે જે પ્રાણી અથવા પદાર્થ પ્રકૃતિ થી ઉત્પન્ન થયેલા આ સત્વાદિ ત્રણ ગુણોથી રહિત હોય.(૪૦)  ");
        } else if (2 == i2) {
            this.tv2.setText("There is no being existing, either here or among the demigods in the higher planetary systems, which is freed from these three modes born of material nature.(40)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        setTitle("Shlok 41");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ब्राह्मणक्षत्रियविशां शूद्राणां च परन्तप।\nकर्माणि प्रविभक्तानि स्वभावप्रभवैर्गुणैः॥");
        } else if (i == 0) {
            this.tv1.setText("બ્રાહ્મણક્ષત્રિયવિશાં શૂદ્રાણાં ચ પરંતપ,\nકર્માણિ પ્રવિભક્તાનિ સ્વભાવપ્રભવૈર્ગુણૈઃ(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Braahmanakshatriyavishaam shoodraanaam cha parantapa;\nKarmaani pravibhaktaani swabhaavaprabhavairgunaih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे परंतप! ब्राह्मण, क्षत्रिय और वैश्यों के तथा शूद्रों के कर्म स्वभाव से उत्पन्न गुणों द्वारा विभक्त किए गए हैं॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરંતપ ! બ્રાહ્મણ, ક્ષત્રીય, વૈશ્ય તથા શુદ્રોનાં કર્મોના પ્રકૃતિથી ઉત્પન્ન થયેલા ગુણો વડે જુદા જુદા વિભાગો પાડવામાં આવ્યા છે.(૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("Brahmanas, kshatriyas, vaishyas and shudras are distinguished by the qualities born of their own natures in accordance with the material modes, O chastiser of the enemy.(41)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42() {
        setTitle("Shlok 42");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शमो दमस्तपः शौचं क्षान्तिरार्जवमेव च।\nज्ञानं विज्ञानमास्तिक्यं ब्रह्मकर्म स्वभावजम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શમો દમસ્તપઃ શૌચં ક્ષાન્તિરાર્જવમેવ ચ,\nજ્ઞાનં વિજ્ઞાનમાસ્તિક્યં બ્રહ્મકર્મ સ્વભાવજમ્.(૪૨)\n");
        } else if (2 == i) {
            this.tv1.setText("Shamo damastapah shaucham kshaantiraarjavameva cha;\nJnaanam vijnaanam aastikyam brahmakarma swabhaavajam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" अंतःकरण का निग्रह करना, इंद्रियों का दमन करना, धर्मपालन के लिए कष्ट सहना, बाहर-भीतर से शुद्ध (गीता अध्याय 13 Shlok 7 की टिप्पणी में देखना चाहिए) रहना, दूसरों के अपराधों को क्षमा करना, मन, इंद्रिय और शरीर को सरल रखना, वेद, शास्त्र, ईश्वर और परलोक आदि में श्रद्धा रखना, वेद-शास्त्रों का अध्ययन-अध्यापन करना और परमात्मा के तत्त्व का अनुभव करना- ये सब-के-सब ही ब्राह्मण के स्वाभाविक कर्म हैं॥42॥");
        } else if (i2 == 0) {
            this.tv2.setText("શમ, દમ, તપ,શૌચ, ક્ષમા, સરલતા તેમજ જ્ઞાન, વિજ્ઞાન,આસ્તિક્યપણું એ સ્વભાવ જન્ય બ્રાહ્મણોનાં કર્મ છે.(૪૨) ");
        } else if (2 == i2) {
            this.tv2.setText("Peacefulness, self-control, austerity, purity, tolerance, honesty, knowledge, wisdom and religiousness—these are the natural qualities by which the brahmanas work.(42)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok43() {
        setTitle("Shlok 43");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शौर्यं तेजो धृतिर्दाक्ष्यं युद्धे चाप्यपलायनम्‌।\nदानमीश्वरभावश्च क्षात्रं कर्म स्वभावजम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("શૌર્યં તેજો ધૃતિર્દાક્ષ્યં યુદ્ધે ચાપ્યપલાયનમ્,\nદાનમીશ્વરભાવશ્ચ ક્ષાત્રં કર્મ સ્વભાવજમ્.(૪૩)");
        } else if (2 == i) {
            this.tv1.setText("Shauryam tejo dhritirdaakshyam yuddhe chaapyapalaayanam;\nDaanameeshwarabhaavashcha kshaatram karmaswabhaavajam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("शूरवीरता, तेज, धैर्य, चतुरता और युद्ध में न भागना, दान देना और स्वामिभाव- ये सब-के-सब ही क्षत्रिय के स्वाभाविक कर्म हैं॥43॥");
        } else if (i2 == 0) {
            this.tv2.setText("શૌર્ય, તેજ, ધીરજ, ચતુરાઈ અને યુદ્ધમાં પાછા ન હટવું, વળી દાન તથા ધર્મ અનુસાર પ્રજાપાલન એ ક્ષત્રીયનાં સ્વાભાવિક કર્મો છે. (૪૩)");
        } else if (2 == i2) {
            this.tv2.setText("Heroism, power, determination, resourcefulness, courage in battle, generosity and leadership are the natural qualities of work for the kshatriyas.(43)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok44() {
        setTitle("Shlok 44");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कृषिगौरक्ष्यवाणिज्यं वैश्यकर्म स्वभावजम्‌।\nपरिचर्यात्मकं कर्म शूद्रस्यापि स्वभावजम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("કૃષિગૌરક્ષ્યવાણિજ્યં વૈશ્યકર્મ સ્વભાવજમ્,\nપરિચર્યાત્મકં કર્મ શૂદ્રસ્યાપિ સ્વભાવજમ્(૪૪)");
        } else if (2 == i) {
            this.tv1.setText("Krishigaurakshyavaanijyam vaishyakarma swabhaavajam;\nParicharyaatmakam karma shoodrasyaapi swabhaavajam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("खेती, गोपालन और क्रय-विक्रय रूप सत्य व्यवहार (वस्तुओं के खरीदने और बेचने में तौल, नाप और गिनती आदि से कम देना अथवा अधिक लेना एवं वस्तु को बदलकर या एक वस्तु में दूसरी या खराब वस्तु मिलाकर दे देना अथवा अच्छी ले लेना तथा नफा, आढ़त और दलाली ठहराकर उससे अधिक दाम लेना या कम देना तथा झूठ, कपट, चोरी और जबरदस्ती से अथवा अन्य किसी प्रकार से दूसरों के हक को ग्रहण कर लेना इत्यादि दोषों से रहित जो सत्यतापूर्वक पवित्र वस्तुओं का व्यापार है उसका नाम 'सत्य व्यवहार' है।) ये वैश्य के स्वाभाविक कर्म हैं तथा सब वर्णों की सेवा करना शूद्र का भी स्वाभाविक कर्म है॥44॥");
        } else if (i2 == 0) {
            this.tv2.setText("ખેતી, ગૌરક્ષા અને વ્યાપાર એ વૈશ્યના સ્વાભાવિક કર્મ છે.અને આ ત્રણે વર્ણ ની સેવારૂપ કર્મ શુદ્રનું સ્વાભાવિક કર્મ છે.(૪૪)  ");
        } else if (2 == i2) {
            this.tv2.setText("Farming, cow protection and business are the natural work for the vaishyas, and for the shudras there is labor and service to others.(44)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok45() {
        setTitle("Shlok 45");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स्वे स्वे कर्मण्यभिरतः संसिद्धिं लभते नरः।\nस्वकर्मनिरतः सिद्धिं यथा विन्दति तच्छृणु॥");
        } else if (i == 0) {
            this.tv1.setText("સ્વે સ્વે કર્મણ્યભિરતઃ સંસિદ્ધિં લભતે નરઃ,\nસ્વકર્મનિરતઃ સિદ્ધિં યથા વિન્દતિ તચ્છૃણુ.(૪૫)");
        } else if (2 == i) {
            this.tv1.setText("Swe swe karmanyabhiratah samsiddhim labhate narah;\nSwakarmaniratah siddhim yathaa vindati tacchrinu.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  अपने-अपने स्वाभाविक कर्मों में तत्परता से लगा हुआ मनुष्य भगवत्प्राप्ति रूप परमसिद्धि को प्राप्त हो जाता है। अपने स्वाभाविक कर्म में लगा हुआ मनुष्य जिस प्रकार से कर्म करके परमसिद्धि को प्राप्त होता है, उस विधि को तू सुन॥45॥");
        } else if (i2 == 0) {
            this.tv2.setText("પોતાના સ્વાભાવિક કર્મમાં નિરત રહેલો મનુષ્ય સત્વ શક્તિને પામે છે.પોતાના કર્મમાં તત્પર રહેલો મનુષ્ય જે પ્રકારે મોક્ષની સિદ્ધિને પામે છે, તે તું સાંભળ.(૪૫)");
        } else if (2 == i2) {
            this.tv2.setText("By following his qualities of work, every man can become perfect. Now please hear from Me how this can be done.(45)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok46() {
        setTitle("Shlok 46");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यतः प्रवृत्तिर्भूतानां येन सर्वमिदं ततम्‌।\nस्वकर्मणा तमभ्यर्च्य सिद्धिं विन्दति मानवः॥");
        } else if (i == 0) {
            this.tv1.setText("યતઃ પ્રવૃત્તિર્ભૂતાનાં યેન સર્વમિદં તતમ્,\nસ્વકર્મણા તમભ્યર્ચ્ય સિદ્ધિં વિન્દતિ માનવઃ.(૪૬)");
        } else if (2 == i) {
            this.tv1.setText("Yatah pravrittirbhootaanaam yena sarvamidam tatam;\nSwakarmanaa tamabhyarchya siddhim vindati maanavah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस परमेश्वर से संपूर्ण प्राणियों की उत्पत्ति हुई है और जिससे यह समस्त जगत्‌ व्याप्त है (जैसे बर्फ जल से व्याप्त है, वैसे ही संपूर्ण संसार सच्चिदानंदघन परमात्मा से व्याप्त है), उस परमेश्वर की अपने स्वाभाविक कर्मों द्वारा पूजा करके (जैसे पतिव्रता स्त्री पति को सर्वस्व समझकर पति का चिंतन करती हुई पति के आज्ञानुसार पति के ही लिए मन, वाणी, शरीर से कर्म करती है, वैसे ही परमेश्वर को ही सर्वस्व समझकर परमेश्वर का चिंतन करते हुए परमेश्वर की आज्ञा के अनुसार मन, वाणी और शरीर से परमेश्वर के ही लिए स्वाभाविक कर्तव्य कर्म का आचरण करना 'कर्म द्वारा परमेश्वर को पूजना' है) मनुष्य परमसिद्धि को प्राप्त हो जाता है॥46॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેનાથી ભૂતોની ઉત્પતિ થાય છે તથા જેના વડે સર્વ વ્યાપ્ત થાય છે તેને પોતાના કર્મ વડે સંતુષ્ટ કરીને મનુષ્ય સિદ્ધિને પામે છે.(૪૬)");
        } else if (2 == i2) {
            this.tv2.setText("By worship of the Lord, who is the source of all beings and who is all-pervading, a man can attain perfection through performing his own work.(46)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok47() {
        setTitle("Shlok 47");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रेयान्स्वधर्मो विगुणः परधर्मात्स्वनुष्ठितात्‌।\nस्वभावनियतं कर्म कुर्वन्नाप्नोति किल्बिषम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("\nશ્રેયાન્સ્વધર્મો વિગુણઃ પરધર્માત્સ્વનુષ્ઠિતાત્,\nસ્વભાવનિયતં કર્મ કુર્વન્નાપ્નોતિ કિલ્બિષમ્.(૪૭)");
        } else if (2 == i) {
            this.tv1.setText("Shreyaanswadharmo vigunah paradharmaat swanushthitaat;\nSwabhaavaniyatam karma kurvannaapnoti kilbisham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" अच्छी प्रकार आचरण किए हुए दूसरे के धर्म से गुणरहित भी अपना धर्म श्रेष्ठ है, क्योंकि स्वभाव से नियत किए हुए स्वधर्मरूप कर्म को करता हुआ मनुष्य पाप को नहीं प्राप्त होता॥47॥");
        } else if (i2 == 0) {
            this.tv2.setText("સારી રીતે આચરેલા પરધર્મ કરતાં પોતાનો ગુણરહિત હોય તો પણ સ્વધર્મ શ્રેષ્ઠ છે.સ્વભાવજન્ય  શાસ્ત્રાનુસારકર્મ કરતો મનુષ્ય પાપને પામતો નથી.(૪૭)");
        } else if (2 == i2) {
            this.tv2.setText("It is better to engage in one’s own occupation, even though one may perform it imperfectly, than to accept another’s occupation and perform it perfectly. Duties prescribed according to one’s nature are never affected by sinful reactions.(47)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok48() {
        setTitle("Shlok 48");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सहजं कर्म कौन्तेय सदोषमपि न त्यजेत्‌।\nसर्वारम्भा हि दोषेण धूमेनाग्निरिवावृताः॥");
        } else if (i == 0) {
            this.tv1.setText("સહજં કર્મ કૌન્તેય સદોષમપિ ન ત્યજેત્,\nસર્વારમ્ભા હિ દોષેણ ધૂમેનાગ્નિરિવાવૃતાઃ.(૪૮)");
        } else if (2 == i) {
            this.tv1.setText("Sahajam karma kaunteya sadoshamapi na tyajet;\nSarvaarambhaa hi doshena dhoomenaagnirivaavritaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  अतएव हे कुन्तीपुत्र! दोषयुक्त होने पर भी सहज कर्म (प्रकृति के अनुसार शास्त्र विधि से नियत किए हुए वर्णाश्रम के धर्म और सामान्य धर्मरूप स्वाभाविक कर्म हैं उनको ही यहाँ स्वधर्म, सहज कर्म, स्वकर्म, नियत कर्म, स्वभावज कर्म, स्वभावनियत कर्म इत्यादि नामों से कहा है) को नहीं त्यागना चाहिए, क्योंकि धूएँ से अग्नि की भाँति सभी कर्म किसी-न-किसी दोष से युक्त हैं॥48॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય  !  વર્ણાશ્રમ અનુસાર સ્વાભાવિક ઉદ્દભવેલું કર્મ દોષવાળું હોય તો પણ ન ત્યજવું. કારણકે સર્વકર્મો ધુમાડાથી જેમ અગ્નિ ઢંકાયેલો રહે છે તેમ દોષ વડે ઢંકાયેલો રહે છે તેમ દોષ વડે ઢંકાયેલાં રહે છે.(૪૮)");
        } else if (2 == i2) {
            this.tv2.setText("Every endeavor is covered by some fault, just as fire is covered by smoke. Therefore one should not give up the work born of his nature, O son of Kunti, even if such work is full of fault.(48)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok49() {
        setTitle("Shlok 49");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("असक्तबुद्धिः सर्वत्र जितात्मा विगतस्पृहः।\nनैष्कर्म्यसिद्धिं परमां सन्न्यासेनाधिगच्छति॥");
        } else if (i == 0) {
            this.tv1.setText("અસક્તબુદ્ધિઃ સર્વત્ર જિતાત્મા વિગતસ્પૃહઃ,\nનૈષ્કર્મ્યસિદ્ધિં પરમાં સંન્યાસેનાધિગચ્છતિ.(૪૯)");
        } else if (2 == i) {
            this.tv1.setText("Asaktabuddhih sarvatra jitaatmaa vigatasprihah;\nNaishkarmyasiddhim paramaam sannyaasenaadhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  सर्वत्र आसक्तिरहित बुद्धिवाला, स्पृहारहित और जीते हुए अंतःकरण वाला पुरुष सांख्ययोग के द्वारा उस परम नैष्कर्म्यसिद्धि को प्राप्त होता है॥49॥");
        } else if (i2 == 0) {
            this.tv2.setText("સ્ત્રી-પુત્રાદિ સર્વ પદાર્થો વિષે આસક્તિ રહિત બુદ્ધિવાળો, અંત:કરણ ને વશ રાખનારો, વિષયો તરફ સ્પૃહા વિનાનો પુરુષ સંન્યાસ વડે પરમ નૈકર્મ્ય સિદ્ધિને પામે છે.(૪૯)");
        } else if (2 == i2) {
            this.tv2.setText("One who is self-controlled and unattached and who disregards all material enjoyments can obtain, by practice of renunciation, the highest perfect stage of freedom from reaction.(49)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok50() {
        setTitle("Shlok 50");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सिद्धिं प्राप्तो यथा ब्रह्म तथाप्नोति निबोध मे।\nसमासेनैव कौन्तेय निष्ठा ज्ञानस्य या परा॥");
        } else if (i == 0) {
            this.tv1.setText("સિદ્ધિં પ્રાપ્તો યથા બ્રહ્મ તથાપ્નોતિ નિબોધ મે,\nસમાસેનૈવ કૌન્તેય નિષ્ઠા જ્ઞાનસ્ય યા પરા.(૫૦)");
        } else if (2 == i) {
            this.tv1.setText("Siddhim praapto yathaa brahma tathaapnoti nibodha me;\nSamaasenaiva kaunteya nishthaa jnaanasya yaa paraa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" जो कि ज्ञान योग की परानिष्ठा है, उस नैष्कर्म्य सिद्धि को जिस प्रकार से प्राप्त होकर मनुष्य ब्रह्म को प्राप्त होता है, उस प्रकार को हे कुन्तीपुत्र! तू संक्षेप में ही मुझसे समझ॥50॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કાન્તેય  ! નિષ્કર્મ્યરૂપ સિદ્ધિને પ્રાપ્ત કરી વિદ્વાન પુરુષ જે પ્રકારે બ્રહ્મને પામે છે તે જ્ઞાનની પરમ નિષ્ઠા  છે. તે સંક્ષેપમાં જ મારી પાસેથી સાંભળ.(૫૦)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Kunti, learn from Me how one who has achieved this perfection can attain to the supreme perfectional stage, Brahman, the stage of highest knowledge, by acting in the way I shall now summarize.(50)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok51_52_53() {
        setTitle("Shlok 51,52,53");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बुद्ध्‌या विशुद्धया युक्तो धृत्यात्मानं नियम्य च।\nशब्दादीन्विषयांस्त्यक्त्वा रागद्वेषौ व्युदस्य च॥\nविविक्तसेवी लघ्वाशी यतवाक्कायमानस।\nध्यानयोगपरो नित्यं वैराग्यं समुपाश्रितः॥\nअहङकारं बलं दर्पं कामं क्रोधं परिग्रहम्‌।\nविमुच्य निर्ममः शान्तो ब्रह्मभूयाय कल्पते॥");
        } else if (i == 0) {
            this.tv1.setText("બુદ્ધ્યા વિશુદ્ધયા યુક્તો ધૃત્યાત્માનં નિયમ્ય ચ,\nશબ્દાદીન્ વિષયાંસ્ત્યક્ત્વા રાગદ્વેષૌ વ્યુદસ્ય ચ.\nવિવિક્તસેવી લઘ્વાશી યતવાક્કાયમાનસઃ,\nધ્યાનયોગપરો નિત્યં વૈરાગ્યં સમુપાશ્રિતઃ\nઅહઙ્કારં બલં દર્પં કામં ક્રોધં પરિગ્રહમ્,\nવિમુચ્ય નિર્મમઃ શાન્તો બ્રહ્મભૂયાય કલ્પતે.(૫૩)");
        } else if (2 == i) {
            this.tv1.setText("Buddhyaa vishuddhayaa yukto dhrityaatmaanam niyamya cha;\nShabdaadeen vishayaanstyaktwaa raagadweshau vyudasya cha.\nViviktasevee laghwaashee yatavaakkaayamaanasah;\nDhyaanayogaparo nityam vairaagyam samupaashritah.\nAhankaaram balam darpam kaamam krodham parigraham;\nVimuchya nirmamah shaanto brahmabhooyaaya kalpate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("विशुद्ध बुद्धि से युक्त तथा हलका, सात्त्विक और नियमित भोजन करने वाला, शब्दादि विषयों का त्याग करके एकांत और शुद्ध देश का सेवन करने वाला, सात्त्विक धारण शक्ति के (इसी अध्याय के Shlok 33 में जिसका विस्तार है) द्वारा अंतःकरण और इंद्रियों का संयम करके मन, वाणी और शरीर को वश में कर लेने वाला, राग-द्वेष को सर्वथा नष्ट करके भलीभाँति दृढ़ वैराग्य का आश्रय लेने वाला तथा अहंकार, बल, घमंड, काम, क्रोध और परिग्रह का त्याग करके निरंतर ध्यान योग के परायण रहने वाला, ममतारहित और शांतियुक्त पुरुष सच्चिदानन्दघन ब्रह्म में अभिन्नभाव से स्थित होने का पात्र होता है॥51-53॥");
        } else if (i2 == 0) {
            this.tv2.setText("શુદ્ધ બુદ્ધિ વડે યુક્ત પુરુષ સાત્વિક ધૈર્યથી આત્માને નિયમમાં રાખી, શબ્દાદિ વિષયોનો ત્યાગ કરીને તથા રાગદ્વેષનો ત્યાગ કરી બ્રહ્મભાવને પ્રાપ્ત કરે છે.એકાંત સેવનારો, અલ્પભોજન કરનારો, વાણી, દેહ તથા મનને વશમાં રાખનારો,દરરોજ ધ્યાન ધરનારો એ વૈરાગ્યનો આશ્રય કરીને રહે છે.તથા અહંકાર, બળ, દર્પ, કામ, ક્રોધ, પરિગ્રહ અને મમતા છોડીને શાંત રહે છે તે બ્રહ્મસાક્ષાત્કાર માટે યોગ્ય બને છે.(૫૧,૫૨,૫૩)");
        } else if (2 == i2) {
            this.tv2.setText("Being purified by his intelligence and controlling the mind with determination, giving up the objects of sense gratification, being freed from attachment and hatred, one who lives in a secluded place, who eats little, who controls his body, mind and power of speech, who is always in trance and who is detached, free from false ego, false strength, false pride, lust, anger, and acceptance of material things, free from false proprietorship, and peaceful—such a person is certainly elevated to the position of self-realization.(51-53)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok54() {
        setTitle("Shlok 54");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ब्रह्मभूतः प्रसन्नात्मा न शोचति न काङ्क्षति।\nसमः सर्वेषु भूतेषु मद्भक्तिं लभते पराम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("બ્રહ્મભૂતઃ પ્રસન્નાત્મા ન શોચતિ ન કાઙ્ક્ષતિ,\nસમઃ સર્વેષુ ભૂતેષુ મદ્ભક્તિં લભતે પરામ્.(૫૪)");
        } else if (2 == i) {
            this.tv1.setText("Brahmabhootah prasannaatmaa na shochati na kaangkshati;\nSamah sarveshu bhooteshu madbhaktim labhate paraam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" फिर वह सच्चिदानन्दघन ब्रह्म में एकीभाव से स्थित, प्रसन्न मनवाला योगी न तो किसी के लिए शोक करता है और न किसी की आकांक्षा ही करता है। ऐसा समस्त प्राणियों में समभाव वाला (गीता अध्याय 6 Shlok 29 में देखना चाहिए) योगी मेरी पराभक्ति को ( जो तत्त्व ज्ञान की पराकाष्ठा है तथा जिसको प्राप्त होकर और कुछ जानना बाकी नहीं रहता वही यहाँ पराभक्ति, ज्ञान की परानिष्ठा, परम नैष्कर्म्यसिद्धि और परमसिद्धि इत्यादि नामों से कही गई है) प्राप्त हो जाता है॥54॥");
        } else if (i2 == 0) {
            this.tv2.setText("બ્રહ્મરૂપ થયેલો પ્રસન્ન ચિત્તવાળો પદાર્થોનો શોક કરતો નથી. અપ્રાપ્ય પદાર્થની ઈચ્છા કરતો નથી. સર્વ ભૂતોમાં સમભાવ રાખનારો એ મારી પરાભક્તિને પામે છે.(૫૪)");
        } else if (2 == i2) {
            this.tv2.setText("One who is thus transcendentally situated at once realizes the Supreme Brahman and becomes fully joyful. He never laments or desires to have anything. He is equally disposed toward every living entity. In that state he attains pure devotional service unto Me.(54)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok55() {
        setTitle("Shlok 55");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भक्त्या मामभिजानाति यावान्यश्चास्मि तत्त्वतः।\nततो मां तत्त्वतो ज्ञात्वा विशते तदनन्तरम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("ભક્ત્યા મામભિજાનાતિ યાવાન્યશ્ચાસ્મિ તત્ત્વતઃ,\nતતો માં તત્ત્વતો જ્ઞાત્વા વિશતે તદનન્તરમ્.(૫૫)");
        } else if (2 == i) {
            this.tv1.setText("Bhaktyaa maamabhijaanaati yaavaanyashchaasmi tattwatah;\nTato maam tattwato jnaatwaa vishate tadanantaram.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उस पराभक्ति के द्वारा वह मुझ परमात्मा को, मैं जो हूँ और जितना हूँ, ठीक वैसा-का-वैसा तत्त्व से जान लेता है तथा उस भक्ति से मुझको तत्त्व से जानकर तत्काल ही मुझमें प्रविष्ट हो जाता है॥55॥");
        } else if (i2 == 0) {
            this.tv2.setText("ભક્તિ વડે હું ઉપાધિ ભેદોથી યુક્ત સ્વરૂપવાળો છું તે જે મને તત્વથી જાણે છે, તે ભક્તિ વડે મને તત્વથી જાણીને ત્યાર પછી મારા સ્વરૂપમાં પ્રવેશ કરે છે.(૫૫)");
        } else if (2 == i2) {
            this.tv2.setText("One can understand Me as I am, as the Supreme Personality of Godhead, only by devotional service. And when one is in full consciousness of Me by such devotion, he can enter into the kingdom of God.(55)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok56() {
        setTitle("Shlok 56");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वकर्माण्यपि सदा कुर्वाणो मद्व्यपाश्रयः।\nमत्प्रसादादवाप्नोति शाश्वतं पदमव्ययम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વકર્માણ્યપિ સદા કુર્વાણો મદ્વ્યપાશ્રયઃ,\nમત્પ્રસાદાદવાપ્નોતિ શાશ્વતં પદમવ્યયમ્.(૫૬)");
        } else if (2 == i) {
            this.tv1.setText("Sarvakarmaanyapi sadaa kurvaano madvyapaashrayah;\nMatprasaadaadavaapnoti shaashwatam padamavyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मेरे परायण हुआ कर्मयोगी तो संपूर्ण कर्मों को सदा करता हुआ भी मेरी कृपा से सनातन अविनाशी परमपद को प्राप्त हो जाता है॥56॥");
        } else if (i2 == 0) {
            this.tv2.setText("સદા સર્વ કર્મો કરતો રહેવા છતાં પણ મારો શરણાગત ભક્ત મારી કૃપાથી શાશ્વત અવિનાશી પદને પામે છે.(૫૬)");
        } else if (2 == i2) {
            this.tv2.setText("Though engaged in all kinds of activities, My pure devotee, under My protection, reaches the eternal and imperishable abode by My grace.(56)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok57() {
        setTitle("Shlok 57");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("चेतसा सर्वकर्माणि मयि सन्न्यस्य मत्परः।\nबुद्धियोगमुपाश्रित्य मच्चित्तः सततं भव॥");
        } else if (i == 0) {
            this.tv1.setText("ચેતસા સર્વકર્માણિ મયિ સંન્યસ્ય મત્પરઃ,\nબુદ્ધિયોગમુપાશ્રિત્ય મચ્ચિત્તઃ સતતં ભવ.(૫૭) ");
        } else if (2 == i) {
            this.tv1.setText("Chetasaa sarvakarmaani mayi sannyasya matparah;\nBuddhiyogam upaashritya macchittah satatam bhava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सब कर्मों को मन से मुझमें अर्पण करके (गीता अध्याय 9 Shlok 27 में जिसकी विधि कही है) तथा समबुद्धि रूप योग को अवलंबन करके मेरे परायण और निरंतर मुझमें चित्तवाला हो॥57॥");
        } else if (i2 == 0) {
            this.tv2.setText("વિવેકબુદ્ધિ વડે સર્વ કામો મને સમર્પણ કરી - મારા પરાયણ થઇ બુદ્ધિયોગનો આશ્રય કરીને નિરંતર મારા વિષે મનવાળો થા.(૫૭)");
        } else if (2 == i2) {
            this.tv2.setText("In all activities just depend upon Me and work always under My protection. In such devotional service, be fully conscious of Me.(57)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok58() {
        setTitle("Shlok 58");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मच्चित्तः सर्वदुर्गाणि मत्प्रसादात्तरिष्यसि।\nअथ चेत्वमहाङ्‍कारान्न श्रोष्यसि विनङ्क्ष्यसि॥");
        } else if (i == 0) {
            this.tv1.setText("મચ્ચિત્તઃ સર્વદુર્ગાણિ મત્પ્રસાદાત્તરિષ્યસિ,\nઅથ ચેત્ત્વમહઙ્કારાન્ન શ્રોષ્યસિ વિનઙ્ક્ષ્યસિ.(૫૮)");
        } else if (2 == i) {
            this.tv1.setText("Macchittah sarvadurgaani matprasaadaat tarishyasi;\nAtha chet twam ahankaaraan na shroshyasi vinangkshyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उपर्युक्त प्रकार से मुझमें चित्तवाला होकर तू मेरी कृपा से समस्त संकटों को अनायास ही पार कर जाएगा और यदि अहंकार के कारण मेरे वचनों को न सुनेगा तो नष्ट हो जाएगा अर्थात परमार्थ से भ्रष्ट हो जाएगा॥58॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારા વિષે ચિત્ત રાખવાથી, મારી કૃપાથી,તું સર્વ દુઃખોને તરી જઈશ. પરંતુ જો તું કદાચિત્ અહંકારથી મને સાંભળશે નહિ તો નાશ પામશે.(૫૮)");
        } else if (2 == i2) {
            this.tv2.setText("If you become conscious of Me, you will pass over all the obstacles of conditioned life by My grace. If, however, you do not work in such consciousness but act through false ego, not hearing Me, you will be lost.|58|");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok59() {
        setTitle("Shlok 59");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदहङ्‍कारमाश्रित्य न योत्स्य इति मन्यसे ।\nमिथ्यैष व्यवसायस्ते प्रकृतिस्त्वां नियोक्ष्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("યદહઙ્કારમાશ્રિત્ય ન યોત્સ્ય ઇતિ મન્યસે,\nમિથ્યૈષ વ્યવસાયસ્તે પ્રકૃતિસ્ત્વાં નિયોક્ષ્યતિ(૫૯)");
        } else if (2 == i) {
            this.tv1.setText("Yadahankaaram aashritya na yotsya iti manyase;\nMithyaisha vyavasaayaste prakritistwaam niyokshyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो तू अहंकार का आश्रय लेकर यह मान रहा है कि 'मैं युद्ध नहीं करूँगा' तो तेरा यह निश्चय मिथ्या है, क्योंकि तेरा स्वभाव तुझे जबर्दस्ती युद्ध में लगा देगा॥59॥");
        } else if (i2 == 0) {
            this.tv2.setText("અહંકારનો આશ્રય કરીને હું યુદ્ધ ન કરું એમ જો તું માનતો હો તો તારો નિશ્વય મિથ્યા છે, કારણકે તારો ક્ષત્રિય સ્વભાવ તને યુદ્ધમાં જોડશે.(૫૯)");
        } else if (2 == i2) {
            this.tv2.setText("If you do not act according to My direction and do not fight, then you will be falsely directed. By your nature, you will have to be engaged in warfare.(59)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok60() {
        setTitle("Shlok 60");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स्वभावजेन कौन्तेय निबद्धः स्वेन कर्मणा ।\nकर्तुं नेच्छसि यन्मोहात्करिष्यस्यवशोऽपि तत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સ્વભાવજેન કૌન્તેય નિબદ્ધઃ સ્વેન કર્મણા,\nકર્તું નેચ્છસિ યન્મોહાત્કરિષ્યસ્યવશોપિ તત્.(૬૦)");
        } else if (2 == i) {
            this.tv1.setText("Swabhaavajena kaunteya nibaddhah swena karmanaa;\nKartum necchasi yanmohaat karishyasyavasho’pi tat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे कुन्तीपुत्र! जिस कर्म को तू मोह के कारण करना नहीं चाहता, उसको भी अपने पूर्वकृत स्वाभाविक कर्म से बँधा हुआ परवश होकर करेगा॥60॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! સ્વભાવજન્ય પોતાના કર્મ વડે બંધાયેલો મોહવશ જે યુદ્ધ કરવાને તું ઈચ્છતો નથી તે પરવશ થઈને પણ તું કરીશ.(૬૦)");
        } else if (2 == i2) {
            this.tv2.setText("Under illusion you are now declining to act according to My direction. But, compelled by the work born of your own nature, you will act all the same, O son of Kunti.(60)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok61() {
        setTitle("Shlok 61");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ईश्वरः सर्वभूतानां हृद्देशेऽजुर्न तिष्ठति।\nभ्रामयन्सर्वभूतानि यन्त्रारुढानि मायया॥");
        } else if (i == 0) {
            this.tv1.setText("ઈશ્વરઃ સર્વભૂતાનાં હૃદ્દેશેર્જુન તિષ્ઠતિ,\nભ્રામયન્સર્વભૂતાનિ યન્ત્રારૂઢાનિ માયયા.(૬૧)");
        } else if (2 == i) {
            this.tv1.setText("Eeshwarah sarvabhootaanaam hriddeshe’rjuna tishthati;\nBhraamayan sarvabhootaani yantraaroodhaani maayayaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! शरीर रूप यंत्र में आरूढ़ हुए संपूर्ण प्राणियों को अन्तर्यामी परमेश्वर अपनी माया से उनके कर्मों के अनुसार भ्रमण कराता हुआ सब प्राणियों के हृदय में स्थित है॥61॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! ઈશ્વર યંત્રો પર બેસાડેલાં સર્વ ભૂતોને માયા વડે ભ્રમણ કરાવતાં સર્વ ભૂતોના હૃદયમાં રહે છે.(૬૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Lord is situated in everyone’s heart, O Arjuna, and is directing the wanderings of all living entities, who are seated as on a machine, made of the material energy.(61)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok62() {
        setTitle("Shlok 62");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तमेव शरणं गच्छ सर्वभावेन भारत।\nतत्प्रसादात्परां शान्तिं स्थानं प्राप्स्यसि शाश्वतम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("તમેવ શરણં ગચ્છ સર્વભાવેન ભારત,\nતત્પ્રસાદાત્પરાં શાન્તિં સ્થાનં પ્રાપ્સ્યસિ શાશ્વતમ્.(૬૨)");
        } else if (2 == i) {
            this.tv1.setText("Tameva sharanam gaccha sarvabhaavena bhaarata;\nTatprasaadaatparaam shaantim sthaanam praapsyasi shaashwatam");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भारत! तू सब प्रकार से उस परमेश्वर की ही शरण में (लज्जा, भय, मान, बड़ाई और आसक्ति को त्यागकर एवं शरीर और संसार में अहंता, ममता से रहित होकर एक परमात्मा को ही परम आश्रय, परम गति और सर्वस्व समझना तथा अनन्य भाव से अतिशय श्रद्धा, भक्ति और प्रेमपूर्वक निरंतर भगवान के नाम, गुण, प्रभाव और स्वरूप का चिंतन करते रहना एवं भगवान का भजन, स्मरण करते हुए ही उनके आज्ञा अनुसार कर्तव्य कर्मों का निःस्वार्थ भाव से केवल परमेश्वर के लिए आचरण करना यह 'सब प्रकार से परमात्मा के ही शरण' होना है) जा। उस परमात्मा की कृपा से ही तू परम शांति को तथा सनातन परमधाम को प्राप्त होगा॥62॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત  ! સર્વ પ્રકારે તે ઈશ્વરને જ શરણે તું જા જેની કૃપાથી તું પરમ શાંતિ તથા શાશ્વત સ્થાનને પામીશ.(૬૨)");
        } else if (2 == i2) {
            this.tv2.setText("O scion of Bharata, surrender unto Him utterly. By His grace you will attain transcendental peace and the supreme and eternal abode.(62)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok63() {
        setTitle("Shlok 63");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इति ते ज्ञानमाख्यातं गुह्याद्‍गुह्यतरं मया ।\nविमृश्यैतदशेषेण यथेच्छसि तथा कुरु ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇતિ તે જ્ઞાનમાખ્યાતં ગુહ્યાદ્ગુહ્યતરં મયા,\nવિમૃશ્યૈતદશેષેણ યથેચ્છસિ તથા કુરુ.(૬૩)");
        } else if (2 == i) {
            this.tv1.setText("Iti te jnaanamaakhyaatam guhyaad guhyataram mayaa;\nVimrishyaitadasheshena yathecchasi tathaa kuru.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस प्रकार यह गोपनीय से भी अति गोपनीय ज्ञान मैंने तुमसे कह दिया। अब तू इस रहस्ययुक्त ज्ञान को पूर्णतया भलीभाँति विचार कर, जैसे चाहता है वैसे ही कर॥63॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ પ્રમાણે મેં તને ગુહ્યથી અતિ ગુહ્ય ગીતાશાસ્ત્રરૂપી જ્ઞાન કહ્યું, એનો સંપૂર્ણપણે વિચાર કરીને જેમ તારી ઈચ્છા હોય તેમ તું કર.(૬૩)");
        } else if (2 == i2) {
            this.tv2.setText("Thus I have explained to you knowledge still more confidential. Deliberate on this fully, and then do what you wish to do.(63)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok64() {
        setTitle("Shlok 64");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वगुह्यतमं भूतः श्रृणु मे परमं वचः ।\nइष्टोऽसि मे दृढमिति ततो वक्ष्यामि ते हितम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વગુહ્યતમં ભૂયઃ શ્રૃણુ મે પરમં વચઃ,\nઇષ્ટોસિ મે દૃઢમિતિ તતો વક્ષ્યામિ તે હિતમ્.(૬૪)");
        } else if (2 == i) {
            this.tv1.setText("Sarvaguhyatamam bhooyah shrinu me paramam vachah;\nIshto’si me dridhamiti tato vakshyaami te hitam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संपूर्ण गोपनीयों से अति गोपनीय मेरे परम रहस्ययुक्त वचन को तू फिर भी सुन। तू मेरा अतिशय प्रिय है, इससे यह परम हितकारक वचन मैं तुझसे कहूँगा॥64॥");
        } else if (i2 == 0) {
            this.tv2.setText("ફરીથી સર્વથી અતિ ગુહ્ય પરમ વચનને તું સાંભળ, કેમ કે તું મને અતિપ્રિય છે. તેથી તને આ હિત કારક વચનો કહું છું.(૬૪)");
        } else if (2 == i2) {
            this.tv2.setText("Because you are My very dear friend, I am speaking to you My supreme instruction, the most confidential knowledge of all. Hear this from Me, for it is for your benefit.(64)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok65() {
        setTitle("Shlok 65");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मन्मना भव मद्भक्तो मद्याजी मां नमस्कुरु ।\nमामेवैष्यसि सत्यं ते प्रतिजाने प्रियोऽसि मे ॥");
        } else if (i == 0) {
            this.tv1.setText("મન્મના ભવ મદ્ભક્તો મદ્યાજી માં નમસ્કુરુ,\nમામેવૈષ્યસિ સત્યં તે પ્રતિજાને પ્રિયોસિ મે.(૬૫)");
        } else if (2 == i) {
            this.tv1.setText("Manmanaa bhava madbhakto madyaajee maam namaskuru;\nMaamevaishyasi satyam te pratijaane priyo’si me");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे अर्जुन! तू मुझमें मनवाला हो, मेरा भक्त बन, मेरा पूजन करने वाला हो और मुझको प्रणाम कर। ऐसा करने से तू मुझे ही प्राप्त होगा, यह मैं तुझसे सत्य प्रतिज्ञा करता हूँ क्योंकि तू मेरा अत्यंत प्रिय है॥65॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારામાં જ મન રાખ, મારો ભક્ત થા, મારું પૂજન કર, મને નમસ્કાર કર, એમ કરવાથી તું મને પામીશ એમ હું સત્ય પ્રતિજ્ઞા કરું છું કારણકે તું મને પ્રિય છે.(૬૫)");
        } else if (2 == i2) {
            this.tv2.setText("Always think of Me, become My devotee, worship Me and offer your homage unto Me. Thus you will come to Me without fail. I promise you this because you are My very dear friend.(65)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok66() {
        setTitle("Shlok 66");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वधर्मान्परित्यज्य मामेकं शरणं व्रज ।\nअहं त्वा सर्वपापेभ्यो मोक्षयिष्यामि मा शुचः ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વધર્માન્પરિત્યજ્ય મામેકં શરણં વ્રજ,\nઅહં ત્વા સર્વપાપેભ્યો મોક્ષયિષ્યામિ મા શુચઃ.(૬૬)");
        } else if (2 == i) {
            this.tv1.setText("Sarvadharmaan parityajya maamekam sharanam vraja;\nAham twaa sarvapaapebhyo mokshayishyaami maa shuchah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संपूर्ण धर्मों को अर्थात संपूर्ण कर्तव्य कर्मों को मुझमें त्यागकर तू केवल एक मुझ सर्वशक्तिमान, सर्वाधार परमेश्वर की ही शरण (इसी अध्याय के Shlok 62 की टिप्पणी में शरण का भाव देखना चाहिए।) में आ जा। मैं तुझे संपूर्ण पापों से मुक्त कर दूँगा, तू शोक मत कर॥66॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ ધર્મોનો ત્યાગ કરીને તું મને એકને જ શરણે આવ,હું તને સર્વ પાપોથી મુક્ત કરીશ.માટે તું શોક ન કર(૬૬)");
        } else if (2 == i2) {
            this.tv2.setText("Abandon all varieties of religion and just surrender unto Me. I shall deliver you from all sinful reactions. Do not fear.(66)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok67() {
        setTitle("Shlok 67");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इदं ते नातपस्काय नाभक्ताय कदाचन ।\nन चाशुश्रूषवे वाच्यं न च मां योऽभ्यसूयति ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇદં તે નાતપસ્કાય નાભક્તાય કદાચન,\nન ચાશુશ્રૂષવે વાચ્યં ન ચ માં યોભ્યસૂયતિ.(૬૭)");
        } else if (2 == i) {
            this.tv1.setText("Idam te naatapaskaaya naabhaktaaya kadaachana;\nNa chaashushrooshave vaachyam na cha maam yo’bhyasooyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तुझे यह गीत रूप रहस्यमय उपदेश किसी भी काल में न तो तपरहित मनुष्य से कहना चाहिए, न भक्ति-(वेद, शास्त्र और परमेश्वर तथा महात्मा और गुरुजनों में श्रद्धा, प्रेम और पूज्य भाव का नाम 'भक्ति' है।)-रहित से और न बिना सुनने की इच्छा वाले से ही कहना चाहिए तथा जो मुझमें दोषदृष्टि रखता है, उससे तो कभी भी नहीं कहना चाहिए॥67॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ ગીતાનો ક્યારે પણ તપરહીતને, ભક્તિરહીતને, શુશ્રુષારહીતને તથા જે મારી અસૂયા કરે છે તેવા મનુષ્યને ઉપદેશ કરવો નહિ.(૬૭)    ");
        } else if (2 == i2) {
            this.tv2.setText("This confidential knowledge may never be explained to those who are not austere, or devoted, or engaged in devotional service, nor to one who is envious of Me.(67)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok68() {
        setTitle("Shlok 68");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("य इमं परमं गुह्यं मद्भक्तेष्वभिधास्यति ।\nभक्तिं मयि परां कृत्वा मामेवैष्यत्यसंशयः ॥");
        } else if (i == 0) {
            this.tv1.setText("ય ઇમં પરમં ગુહ્યં મદ્ભક્તેષ્વભિધાસ્યતિ,\nભક્િતં મયિ પરાં કૃત્વા મામેવૈષ્યત્યસંશયઃ.(૬૮)");
        } else if (2 == i) {
            this.tv1.setText("Ya imam paramam guhyam madbhakteshvabhidhaasyati;\nBhaktim mayi paraam kritwaa maamevaishyatyasamshayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("  जो पुरुष मुझमें परम प्रेम करके इस परम रहस्ययुक्त गीताशास्त्र को मेरे भक्तों में कहेगा, वह मुझको ही प्राप्त होगा- इसमें कोई संदेह नहीं है॥68॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે આ પરમ ગુહ્યજ્ઞાનનો મારા ભક્તોને ઉપદેશ કરશે તે મારા વિષે પરમભક્તિ પ્રાપ્ત કરીને મને જ પામશે,એમાં સંશય નથી.(૬૮) ");
        } else if (2 == i2) {
            this.tv2.setText("For one who explains this supreme secret to the devotees, pure devotional service is guaranteed, and at the end he will come back to Me.(68)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok69() {
        setTitle("Shlok 69");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न च तस्मान्मनुष्येषु कश्चिन्मे प्रियकृत्तमः ।\nभविता न च मे तस्मादन्यः प्रियतरो भुवि ॥");
        } else if (i == 0) {
            this.tv1.setText("ન ચ તસ્માન્મનુષ્યેષુ કશ્ચિન્મે પ્રિયકૃત્તમઃ,\nભવિતા ન ચ મે તસ્માદન્યઃ પ્રિયતરો ભુવિ.(૬૯)");
        } else if (2 == i) {
            this.tv1.setText("Na cha tasmaanmanushyeshu kashchinme priyakrittamah;\nBhavitaa na cha me tasmaadanyah priyataro bhuvi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उससे बढ़कर मेरा प्रिय कार्य करने वाला मनुष्यों में कोई भी नहीं है तथा पृथ्वीभर में उससे बढ़कर मेरा प्रिय दूसरा कोई भविष्य में होगा भी नहीं॥69॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી મનુષ્યોમાં તેનાથી બીજો કોઈ પણ મોટું અતિ પ્રિય કરનાર થવાનો નથી તથા પૃથ્વીમાં તેના કરતાં બીજો વધારે પ્રિય પણ નથી.(૬૯)");
        } else if (2 == i2) {
            this.tv2.setText("There is no servant in this world more dear to Me than he, nor will there ever be one more dear.(69)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok70() {
        setTitle("Shlok 70");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्येष्यते च य इमं धर्म्यं संवादमावयोः ।\nज्ञानयज्ञेन तेनाहमिष्टः स्यामिति मे मतिः ॥");
        } else if (i == 0) {
            this.tv1.setText("અધ્યેષ્યતે ચ ય ઇમં ધર્મ્યં સંવાદમાવયોઃ,\nજ્ઞાનયજ્ઞેન તેનાહમિષ્ટઃ સ્યામિતિ મે મતિઃ.(૭૦)");
        } else if (2 == i) {
            this.tv1.setText("Adhyeshyate cha ya imam dharmyam samvaadamaavayoh;\nJnaanayajnena tenaaham ishtah syaamiti me matih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष इस धर्ममय हम दोनों के संवाद रूप गीताशास्त्र को पढ़ेगा, उसके द्वारा भी मैं ज्ञानयज्ञ (गीता अध्याय 4 Shlok 33 का अर्थ देखना चाहिए।) से पूजित होऊँगा- ऐसा मेरा मत है॥70॥");
        } else if (i2 == 0) {
            this.tv2.setText("તથા જે આપણા બે ના આ ધર્મયુક્ત સંવાદનું અધ્યયન કરશે, તેનાથી જ્ઞાનયજ્ઞ વડે હું પૂજાઈશ એવો મારો મત છે.(૭૦)");
        } else if (2 == i2) {
            this.tv2.setText("And I declare that he who studies this sacred conversation of ours worships Me by his intelligence.(70)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok71() {
        setTitle("Shlok 71");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रद्धावाननसूयश्च श्रृणुयादपि यो नरः ।\nसोऽपि मुक्तः शुभाँल्लोकान्प्राप्नुयात्पुण्यकर्मणाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રદ્ધાવાનનસૂયશ્ચ શ્રૃણુયાદપિ યો નરઃ,\nસોપિ મુક્તઃ શુભા્લોકાન્પ્રાપ્નુયાત્પુણ્યકર્મણામ્.(૭૧)");
        } else if (2 == i) {
            this.tv1.setText("Shraddhaavaan anasooyashcha shrinuyaadapi yo narah;\nSo’pi muktah shubhaamllokaan praapnuyaat punyakarmanaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो मनुष्य श्रद्धायुक्त और दोषदृष्टि से रहित होकर इस गीताशास्त्र का श्रवण भी करेगा, वह भी पापों से मुक्त होकर उत्तम कर्म करने वालों के श्रेष्ठ लोकों को प्राप्त होगा॥71॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે પુરુષ શ્રદ્ધાવાન તથા  ઈર્ષ્યા વિનાનો થઈને આ ગીતાશાસ્ત્રનું શ્રવણ કરે છે તે પણ મુક્ત થઈને પુણ્યકર્મ કરનારાને પ્રાપ્ત થતાં શુભ લોકોને પામે છે.(૭૧)");
        } else if (2 == i2) {
            this.tv2.setText("And one who listens with faith and without envy becomes free from sinful reactions and attains to the auspicious planets where the pious dwell.(71)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok72() {
        setTitle("Shlok 72");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कच्चिदेतच्छ्रुतं पार्थ त्वयैकाग्रेण चेतसा ।\nकच्चिदज्ञानसम्मोहः प्रनष्टस्ते धनञ्जय ॥");
        } else if (i == 0) {
            this.tv1.setText("કચ્ચિદેતચ્છ્રુતં પાર્થ ત્વયૈકાગ્રેણ ચેતસા,\nકચ્ચિદજ્ઞાનસંમોહઃ પ્રનષ્ટસ્તે ધનઞ્જય.(૭૨)");
        } else if (2 == i) {
            this.tv1.setText("Kacchid etacchrutam paartha twayaikaagrena chetasaa;\nKacchid ajnaanasammohah pranashtaste dhananjaya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे पार्थ! क्या इस (गीताशास्त्र) को तूने एकाग्रचित्त से श्रवण किया? और हे धनञ्जय! क्या तेरा अज्ञानजनित मोह नष्ट हो गया?॥72॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! તેં આ ગીતાશાસ્ત્ર એકાગ્ર ચિત્તથી સાંભળ્યું કે ? હે ધનંજય ! તારો અજ્ઞાન થી ઉત્પન્ન થયેલો મોહ નાશ પામ્યો કે ? (૭૨)");
        } else if (2 == i2) {
            this.tv2.setText("O son of Pritha, O conqueror of wealth, have you heard this with an attentive mind? And are your ignorance and illusions now dispelled?(72)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok73() {
        setTitle("Shlok 73");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nनष्टो मोहः स्मृतिर्लब्धा त्वप्रसादान्मयाच्युत ।\nस्थितोऽस्मि गतसंदेहः करिष्ये वचनं तव ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nનષ્ટો મોહઃ સ્મૃતિર્લબ્ધા ત્વત્પ્રસાદાન્મયાચ્યુત,\nસ્થિતોસ્મિ ગતસન્દેહઃ કરિષ્યે વચનં તવ.(૭૩)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha-\nNashto mohah smritirlabdhaa twatprasaadaanmayaachyuta;\nSthito’smi gata sandehah karishye vachanam tava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" अर्जुन बोले- हे अच्युत! आपकी कृपा से मेरा मोह नष्ट हो गया और मैंने स्मृति प्राप्त कर ली है, अब मैं संशयरहित होकर स्थिर हूँ, अतः आपकी आज्ञा का पालन करूँगा॥73॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે અચ્યુત ! આપની કૃપાથી મારો મોહ નાશ પામ્યો છે. મેં આત્મજ્ઞાનરૂપી સ્મૃતિ પ્રાપ્ત કરી છે. સંશયરહિત થઇ હું આપનું વચન પાળીશ.(૭૩)  ");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: My dear Krishna, O infallible one, my illusion is now gone. I have regained my memory by Your mercy. I am now firm and free from doubt and am prepared to act according to Your instructions.(73)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok74() {
        setTitle("Shlok 74");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nइत्यहं वासुदेवस्य पार्थस्य च महात्मनः ।\nसंवादमिममश्रौषमद्भुतं रोमहर्षणम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજય ઉવાચ-\nઇત્યહં વાસુદેવસ્ય પાર્થસ્ય ચ મહાત્મનઃ,\nસંવાદમિમમશ્રૌષમદ્ભુતં રોમહર્ષણમ્.(૭૪)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha-\nItyaham vaasudevasya paarthasya cha mahaatmanah;\nSamvaadam imam ashrausham adbhutam romaharshanam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संजय बोले- इस प्रकार मैंने श्री वासुदेव के और महात्मा अर्जुन के इस अद्‍भुत रहस्ययुक्त, रोमांचकारक संवाद को सुना॥74॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે : એ પ્રમાણે ભગવાન વાસુદેવનો તથા મહાત્મા અર્જુનનો અદ્દભુત અને રોમાંચિત કરે તેવો સંવાદ મેં સાંભળ્યો.(૭૪)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said: Thus have I heard the conversation of two great souls, Krishna and Arjuna. And so wonderful is that message that my hair is standing on end.(74)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok75() {
        setTitle("Shlok 75");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("व्यासप्रसादाच्छ्रुतवानेतद्‍गुह्यमहं परम्‌ ।\nयोगं योगेश्वरात्कृष्णात्साक्षात्कथयतः स्वयम्‌॥");
        } else if (i == 0) {
            this.tv1.setText("વ્યાસપ્રસાદાચ્છ્રુતવાનેતદ્ગુહ્યમહં પરમ્,\nયોગં યોગેશ્વરાત્કૃષ્ણાત્સાક્ષાત્કથયતઃ સ્વયમ્.(૭૫)");
        } else if (2 == i) {
            this.tv1.setText("Vyaasaprasaadaacchrutavaan etadguhyamaham param;\nYogam yogeshwaraat krishnaat saakshaat kathayatah swayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्री व्यासजी की कृपा से दिव्य दृष्टि पाकर मैंने इस परम गोपनीय योग को अर्जुन के प्रति कहते हुए स्वयं योगेश्वर भगवान श्रीकृष्ण से प्रत्यक्ष सुना॥75॥");
        } else if (i2 == 0) {
            this.tv2.setText("વ્યાસ ભગવાનની કૃપાથી આ પરમ ગુહ્ય યોગને  યોગેશ્વર શ્રી કૃષ્ણે સ્વયં કહ્યો તે મેં સાક્ષાત સાંભળ્યો.(૭૫)");
        } else if (2 == i2) {
            this.tv2.setText("By the mercy of Vyasa, I have heard these most confidential talks directly from the master of all mysticism, Krishna, who was speaking personally to Arjuna.(75)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok76() {
        setTitle("Shlok 76");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("राजन्संस्मृत्य संस्मृत्य संवादमिममद्भुतम्‌ ।\nकेशवार्जुनयोः पुण्यं हृष्यामि च मुहुर्मुहुः ॥");
        } else if (i == 0) {
            this.tv1.setText("રાજન્સંસ્મૃત્ય સંસ્મૃત્ય સંવાદમિમમદ્ભુતમ્,\nકેશવાર્જુનયોઃ પુણ્યં હૃષ્યામિ ચ મુહુર્મુહુઃ.(૭૬)");
        } else if (2 == i) {
            this.tv1.setText("Raajan samsmritya samsmritya samvaadam imam adbhutam;\nKeshavaarjunayoh punyam hrishyaami cha muhurmuhuh.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे राजन! भगवान श्रीकृष्ण और अर्जुन के इस रहस्ययुक्त, कल्याणकारक और अद्‍भुत संवाद को पुनः-पुनः स्मरण करके मैं बार-बार हर्षित हो रहा हूँ॥76॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે રાજન ! શ્રી કૃષ્ણ અને અર્જુનના આ પવિત્ર તથા અદ્દભુત સંવાદ ને સંભારી સંભારી ને  વારંવાર હું હર્ષ પામું છું.(૭૬)");
        } else if (2 == i2) {
            this.tv2.setText("O King, as I repeatedly recall this wondrous and holy dialogue between Krishna and Arjuna, I take pleasure, being thrilled at every moment.(76)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok77() {
        setTitle("Shlok 77");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तच्च संस्मृत्य संस्मृत्य रूपमत्यद्भुतं हरेः ।\nविस्मयो मे महान्‌ राजन्हृष्यामि च पुनः पुनः ॥");
        } else if (i == 0) {
            this.tv1.setText("તચ્ચ સંસ્મૃત્ય સંસ્મૃત્ય રૂપમત્યદ્ભુતં હરેઃ,\nવિસ્મયો મે મહાન્ રાજન્ હૃષ્યામિ ચ પુનઃ પુનઃ.(૭૭)");
        } else if (2 == i) {
            this.tv1.setText("Taccha samsmritya samsmritya roopamatyadbhutam hareh;\nVismayo me mahaan raajan hrishyaami cha punah punah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे राजन्‌! श्रीहरि (जिसका स्मरण करने से पापों का नाश होता है उसका नाम 'हरि' है) के उस अत्यंत विलक्षण रूप को भी पुनः-पुनः स्मरण करके मेरे चित्त में महान आश्चर्य होता है और मैं बार-बार हर्षित हो रहा हूँ॥77॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે રાજન ! વળી ભગવાન શ્રી કૃષ્ણના તે અતિ અદ્દભુત વિશ્વરૂપને સંભારી સંભારીને મને વિસ્મય થાય છે ને હું વારંવાર હર્ષ પામું છું.(૭૭)  ");
        } else if (2 == i2) {
            this.tv2.setText("O King, as I remember the wonderful form of Lord Krishna, I am struck with wonder more and more, and I rejoice again and again.(77)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok78() {
        setTitle("Shlok 78");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्र योगेश्वरः कृष्णो यत्र पार्थो धनुर्धरः ।\nतत्र श्रीर्विजयो भूतिर्ध्रुवा नीतिर्मतिर्मम ॥");
        } else if (i == 0) {
            this.tv1.setText("યત્ર યોગેશ્વરઃ કૃષ્ણો યત્ર પાર્થો ધનુર્ધરઃ,\nતત્ર શ્રીર્વિજયો ભૂતિર્ધ્રુવા નીતિર્મતિર્મમ.(૭૮)");
        } else if (2 == i) {
            this.tv1.setText("Yatra yogeshwarah krishno yatra paartho dhanurdharah;\nTatra shreervijayo bhootirdhruvaa neetirmatirmama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे राजन! जहाँ योगेश्वर भगवान श्रीकृष्ण हैं और जहाँ गाण्डीव-धनुषधारी अर्जुन है, वहीं पर श्री, विजय, विभूति और अचल नीति है- ऐसा मेरा मत है॥78॥");
        } else if (i2 == 0) {
            this.tv2.setText("જ્યાં યોગેશ્વર શ્રી કૃષ્ણ છે અને જ્યાં ધનુર્ધારી અર્જુન છે ત્યાં લક્ષ્મી,વિજય, ભૂતિ, ઐશ્વર્ય અને નિશ્વલ નીતિ સર્વદા વાસ કરે છે એવો મારો મત છે.(૭૮)");
        } else if (2 == i2) {
            this.tv2.setText("Wherever there is Krishna, the master of all mystics, and wherever there is Arjuna, the supreme archer, there will also certainly be opulence, victory, extraordinary power, and morality. That is my opinion..(78)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay18.this, ShlokListPlay18.this);
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

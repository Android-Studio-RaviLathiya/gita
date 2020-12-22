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

public class ShlokListPlay10 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay10.this.favflag == 0) {
                    ShlokListPlay10.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.favflag = 1;
                    shlokListPlay10.save();
                } else if (1 == ShlokListPlay10.this.favflag) {
                    ShlokListPlay10.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay10 shlokListPlay102 = ShlokListPlay10.this;
                    shlokListPlay102.favflag = 0;
                    shlokListPlay102.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay10.this.calll++;
                ShlokListPlay10.this.slkset();
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
                ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                shlokListPlay10.calll--;
                ShlokListPlay10.this.slkset();
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
        edit.putString("A10" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A10" + this.calll, "0");
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
        if (41 == this.calll) {
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
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.callf1 = 0;
                    shlokListPlay10.slkset();
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
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.callf1 = 2;
                    shlokListPlay10.slkset();
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
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.callf1 = 1;
                    shlokListPlay10.slkset();
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
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.callf2 = 0;
                    shlokListPlay10.slkset();
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
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.callf2 = 2;
                    shlokListPlay10.slkset();
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
                    ShlokListPlay10 shlokListPlay10 = ShlokListPlay10.this;
                    shlokListPlay10.callf2 = 1;
                    shlokListPlay10.slkset();
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
            shlok12_13();
        } else if (12 == i3) {
            shlok14();
        } else if (13 == i3) {
            shlok15();
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
            shlok23();
        } else if (22 == i3) {
            shlok24();
        } else if (23 == i3) {
            shlok25();
        } else if (24 == i3) {
            shlok26();
        } else if (25 == i3) {
            shlok27();
        } else if (26 == i3) {
            shlok28();
        } else if (27 == i3) {
            shlok29();
        } else if (28 == i3) {
            shlok30();
        } else if (29 == i3) {
            shlok31();
        } else if (30 == i3) {
            shlok32();
        } else if (31 == i3) {
            shlok33();
        } else if (32 == i3) {
            shlok34();
        } else if (33 == i3) {
            shlok35();
        } else if (34 == i3) {
            shlok36();
        } else if (35 == i3) {
            shlok37();
        } else if (36 == i3) {
            shlok38();
        } else if (37 == i3) {
            shlok39();
        } else if (38 == i3) {
            shlok40();
        } else if (39 == i3) {
            shlok41();
        } else if (40 == i3) {
            shlok42();
        } else if (41 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 10");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय १० \nविभूतियोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૧૦ \nવિભૂતિ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 10\nvibhuti yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, बल, सौंदर्य, ऐश्वर्य या उत्कृष्टता प्रदर्शित करने वाली समस्त अदभुत घटनाएँ, चाहे वे इस लोक में हों या आध्यात्मिक जगत में, कृष्ण की देवी शक्तियों एवं ऐश्वरियों की आंशिक अभिव्यक्तियाँ हैं। समस्त कारणों के कारण-स्वरूप तथा सर्वस्वरुप कृष्ण समस्त जीवों के परम पूजनीय हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧૦ 'જ્ઞાન' તથા 'શક્તિ' આદિનું મૂળ કારણ ઈશ્વર છે. સુખ દુઃખ જેવા અનેક વિવિધ ભાવો એનાથી જ ઉત્પન્ન થાય છે. જે જે વસ્તુ વિભૂતિ યુક્ત, ઐશ્વર્યયુક્ત અને કાંતિ યુક્ત છે તે સર્વ તેના તેજ ના 'અંશ' થી ઉપજેલી છે. તેના અંશ માત્ર થી સમગ્ર જગત ધારણ થયેલું છે.");
        } else if (2 == i) {
            this.tv1.setText("Tenth adhyay tells, All wondrous phenomena showing power, beauty, grandeur or sublimity, either in the material world or in the spiritual, are but partial manifestations of Krishna's divine energies and opulence. As the supreme cause of all causes and the support and essence of everything, Krishna is the supreme object of worship for all beings.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nभूय एव महाबाहो श्रृणु मे परमं वचः ।\nयत्तेऽहं प्रीयमाणाय वक्ष्यामि हितकाम्यया ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nભૂય એવ મહાબાહો શ્રૃણુ મે પરમં વચઃ,\nયત્તેહં પ્રીયમાણાય વક્ષ્યામિ હિતકામ્યયા.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nBhooya eva mahaabaaho shrinu me paramam vachah;\nYatte’ham preeyamaanaaya vakshyaami hitakaamyayaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान्‌ बोले- हे महाबाहो! फिर भी मेरे परम रहस्य और प्रभावयुक्त वचन को सुन, जिसे मैं तुझे अतिशय प्रेम रखने वाले के लिए हित की इच्छा से कहूँगा॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : હે મહાબાહો  ! ફરીથી તું મારા પરમ વચનો  સાંભળ; તને મારા ભાષણ થી સંતોષ થઇ રહ્યો છે એટલે જ તારું હિત કરવાની ઈચ્છાથી હું તને આગળ કહું છું.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: Listen again, O mighty-armed Arjuna. Because you are My dear friend, for your benefit I shall speak to you further, giving knowledge that is better than what I have already explained.(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न मे विदुः सुरगणाः प्रभवं न महर्षयः ।\nअहमादिर्हि देवानां महर्षीणां च सर्वशः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન મે વિદુઃ સુરગણાઃ પ્રભવં ન મહર્ષયઃ,\nઅહમાદિર્હિ દેવાનાં મહર્ષીણાં ચ સર્વશઃ.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Na me viduh suraganaah prabhavam na maharshayah;\nAhamaadirhi devaanaam maharsheenaam cha sarvashah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मेरी उत्पत्ति को अर्थात्‌ लीला से प्रकट होने को न देवता लोग जानते हैं और न महर्षिजन ही जानते हैं, क्योंकि मैं सब प्रकार से देवताओं का और महर्षियों का भी आदिकारण हूँ॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("દેવગણો તથા મહર્ષિઓને પણ મારા પ્રાદુર્ભાવની ખબર નથી, કેમ કે હું સર્વ રીતે દેવો અને મહર્ષિઓનું  આદિ કારણ છું.(૨)");
        } else if (2 == i2) {
            this.tv2.setText("Neither the hosts of demigods nor the great sages know My origin or opulences, for, in every respect, I am the source of the demigods and sages.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यो मामजमनादिं च वेत्ति लोकमहेश्वरम्‌ ।\nअसम्मूढः स मर्त्येषु सर्वपापैः प्रमुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યો મામજમનાદિં ચ વેત્તિ લોકમહેશ્વરમ્,\nઅસમ્મૂઢઃ સ મર્ત્યેષુ સર્વપાપૈઃ પ્રમુચ્યતે.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Yo maamajamanaadim cha vetti lokamaheshwaram;\nAsammoodhah sa martyeshu sarvapaapaih pramuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो मुझको अजन्मा अर्थात्‌ वास्तव में जन्मरहित, अनादि (अनादि उसको कहते हैं जो आदि रहित हो एवं सबका कारण हो) और लोकों का महान्‌ ईश्वर तत्त्व से जानता है, वह मनुष्यों में ज्ञानवान्‌ पुरुष संपूर्ण पापों से मुक्त हो जाता है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે મને અજન્મા, અનાદિ અને સર્વ લોકોનો મહાન અધિપતિ ઈશ્વર તત્વથી ઓળખે છે, તે મનુષ્યોમાં જ્ઞાનવાન પુરુષ સર્વ પાપોના બંધનમાંથી મુક્ત થઇ જાય છે.(૩)");
        } else if (2 == i2) {
            this.tv2.setText("He who knows Me as the unborn, as the beginningless, as the Supreme Lord of all the worlds—he only, undeluded among men, is freed from all sins.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4_5() {
        setTitle("Shlok 4,5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बुद्धिर्ज्ञानमसम्मोहः क्षमा सत्यं दमः शमः ।\nसुखं दुःखं भवोऽभावो भयं चाभयमेव च ॥\nअहिंसा समता तुष्टिस्तपो दानं यशोऽयशः ।\nभवन्ति भावा भूतानां मत्त एव पृथग्विधाः ॥");
        } else if (i == 0) {
            this.tv1.setText("બુદ્ધિર્જ્ઞાનમસંમોહઃ ક્ષમા સત્યં દમઃ શમઃ,\nસુખં દુઃખં ભવોભાવો ભયં ચાભયમેવ ચ.(૪)\nઅહિંસા સમતા તુષ્ટિસ્તપો દાનં યશોયશઃ,\nભવન્તિ ભાવા ભૂતાનાં મત્ત એવ પૃથગ્વિધાઃ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Buddhir jnaanamasammohah kshamaa satyam damah shamah;\nSukham duhkham bhavo’bhaavo bhayam chaabhayameva cha.\nAhimsaa samataa tushtistapo daanam yasho’yashah;\nBhavanti bhaavaa bhootaanaam matta eva prithagvidhaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("निश्चय करने की शक्ति, यथार्थ ज्ञान, असम्मूढ़ता, क्षमा, सत्य, इंद्रियों का वश में करना, मन का निग्रह तथा सुख-दुःख, उत्पत्ति-प्रलय और भय-अभय तथा अहिंसा, समता, संतोष तप (स्वधर्म के आचरण से इंद्रियादि को तपाकर शुद्ध करने का नाम तप है), दान, कीर्ति और अपकीर्ति- ऐसे ये प्राणियों के नाना प्रकार के भाव मुझसे ही होते हैं॥4-5॥");
        } else if (i2 == 0) {
            this.tv2.setText("બુદ્ધિ, તત્વજ્ઞાન, અસંમોહ, ક્ષમા, સત્ય, શમ, સુખ, દુઃખ, ઉત્પતિ, વિનાશ, ભય અભય અને.અહિંસા, સમતા, તુષ્ટિ, તપ, દાન,યશ, અપયશ વગેરે સર્વ ભિન્ન ભિન્ન પ્રકારના ભાવો પ્રાણીઓમાં મારા થકી જ ઉત્પન થાય છે.(૪-૫)");
        } else if (2 == i2) {
            this.tv2.setText("Intelligence, knowledge, freedom from doubt and delusion, forgiveness, truthfulness, control of the senses, control of the mind, happiness and distress, birth, death, fear, fearlessness, nonviolence, equanimity, satisfaction, austerity, charity, fame and infamy—all these various qualities of living beings are created by Me alone.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("महर्षयः सप्त पूर्वे चत्वारो मनवस्तथा ।\nमद्भावा मानसा जाता येषां लोक इमाः प्रजाः ॥");
        } else if (i == 0) {
            this.tv1.setText("મહર્ષયઃ સપ્ત પૂર્વે ચત્વારો મનવસ્તથા,\nમદ્ભાવા માનસા જાતા યેષાં લોક ઇમાઃ પ્રજાઃ.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Maharshayah sapta poorve chatwaaro manavastathaa;\nMadbhaavaa maanasaa jaataa yeshaam loka imaah prajaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सात महर्षिजन, चार उनसे भी पूर्व में होने वाले सनकादि तथा स्वायम्भुव आदि चौदह मनु- ये मुझमें भाव वाले सब-के-सब मेरे संकल्प से उत्पन्न हुए हैं, जिनकी संसार में यह संपूर्ण प्रजा है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("પ્રાચીન સપ્તર્ષિઓ અને તેમની પહેલાં થઇ ગયેલા બ્રહ્મદેવના સનતકુમાર આદિ ચાર માનસપુત્રો તથા ચૌદ મનુઓ મારામાં ભાવવાળા બધા જ મારા સંકલ્પથી ઉત્પન થયેલા છે. અને તેમનાથી જ જગતનાં સર્વ પ્રાણીઓ ઉત્પત્તિ થઇ છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("The seven great sages and before them the four other great sages and the Manus [progenitors of mankind] come from Me, born from My mind, and all the living beings populating the various planets descend from them.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतां विभूतिं योगं च मम यो वेत्ति तत्त्वतः ।\nसोऽविकम्पेन योगेन युज्यते नात्र संशयः ॥");
        } else if (i == 0) {
            this.tv1.setText("એતાં વિભૂતિં યોગં ચ મમ યો વેત્તિ તત્ત્વતઃ,\nસોવિકમ્પેન યોગેન યુજ્યતે નાત્ર સંશયઃ.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Etaam vibhootim yogam cha mama yo vetti tattwatah;\nSo’vikampena yogena yujyate naatra samshayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष मेरी इस परमैश्वर्यरूप विभूति को और योगशक्ति को तत्त्व से जानता है (जो कुछ दृश्यमात्र संसार है वह सब भगवान की माया है और एक वासुदेव भगवान ही सर्वत्र परिपूर्ण है, यह जानना ही तत्व से जानना है), वह निश्चल भक्तियोग से युक्त हो जाता है- इसमें कुछ भी संशय नहीं है॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે પુરુષ મારી પરમ અશ્વર્યરૂપ વિભૂતિને એટલેકે મારા વિસ્તારને અને યોગશક્તિને (ઉત્પન કરવાની શક્તિને) તત્વથી જાણે છે તે પુરુષ નિશ્વલ ધ્યાનયોગથી મારામાં ઐક્ય ભાવથી સ્થિત થઇ સમ્યગદર્શન ના યોગવાળો થાય છે, એમાં  સંશયને સ્થાન નથી.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("One who is factually convinced of this opulence and mystic power of Mine engages in unalloyed devotional service; of this there is no doubt.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8() {
        setTitle("Shlok 8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहं सर्वस्य प्रभवो मत्तः सर्वं प्रवर्तते ।\nइति मत्वा भजन्ते मां बुधा भावसमन्विताः ॥");
        } else if (i == 0) {
            this.tv1.setText("અહં સર્વસ્ય પ્રભવો મત્તઃ સર્વં પ્રવર્તતે,\nઇતિ મત્વા ભજન્તે માં બુધા ભાવસમન્વિતાઃ.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Aham sarvasya prabhavo mattah sarvam pravartate;\nIti matwaa bhajante maam budhaa bhaavasamanvitaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं वासुदेव ही संपूर्ण जगत्‌ की उत्पत्ति का कारण हूँ और मुझसे ही सब जगत्‌ चेष्टा करता है, इस प्रकार समझकर श्रद्धा और भक्ति से युक्त बुद्धिमान्‌ भक्तजन मुझ परमेश्वर को ही निरंतर भजते हैं॥8॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું – શ્રી કૃષ્ણ જ સંપૂર્ણ જગતની ઉત્પતિનું કારણ છું. મારા વડે જ સર્વ જગત પ્રવૃત થાય છે. એમ તત્વથી જાણીને શ્રદ્ધા- ભક્તિયુક્ત થયેલા જ્ઞાનીજનો મને –પરમેશ્વરને નિરંતર ભજે છે.(૮)");
        } else if (2 == i2) {
            this.tv2.setText("I am the source of all spiritual and material worlds. Everything emanates from Me. The wise who perfectly know this engage in My devotional service and worship Me with all their hearts.(8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मच्चित्ता मद्गतप्राणा बोधयन्तः परस्परम्‌ ।\nकथयन्तश्च मां नित्यं तुष्यन्ति च रमन्ति च ॥");
        } else if (i == 0) {
            this.tv1.setText("મચ્ચિત્તા મદ્ગતપ્રાણા બોધયન્તઃ પરસ્પરમ્,\nકથયન્તશ્ચ માં નિત્યં તુષ્યન્તિ ચ રમન્તિ ચ.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Macchittaa madgatapraanaa bodhayantah parasparam;\nKathayantashcha maam nityam tushyanti cha ramanti cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("निरंतर मुझमें मन लगाने वाले और मुझमें ही प्राणों को अर्पण करने वाले (मुझ वासुदेव के लिए ही जिन्होंने अपना जीवन अर्पण कर दिया है उनका नाम मद्गतप्राणाः है।) भक्तजन मेरी भक्ति की चर्चा के द्वारा आपस में मेरे प्रभाव को जानते हुए तथा गुण और प्रभाव सहित मेरा कथन करते हुए ही निरंतर संतुष्ट होते हैं और मुझ वासुदेव में ही निरंतर रमण करते हैं॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે જ્ઞાનીઓ નિરંતર મારામાં ચિત્ત રાખી, મારામય રહી મને સર્વસ્વ અર્પણ કરનારા ભક્તજન મારા વિષે બોધ આપતા ગુણ અને પ્રભાવ સાથે મારું કીર્તન કરતાં નિરંતર સંતુષ્ટ રહે છે અને મારામાં લીન રહે છે.(૯)");
        } else if (2 == i2) {
            this.tv2.setText("The thoughts of My pure devotees dwell in Me, their lives are fully devoted to My service, and they derive great satisfaction and bliss from always enlightening one another and conversing about Me.(9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तेषां सततयुक्तानां भजतां प्रीतिपूर्वकम्‌ ।\nददामि बद्धियोगं तं येन मामुपयान्ति ते ॥");
        } else if (i == 0) {
            this.tv1.setText("તેષાં સતતયુક્તાનાં ભજતાં પ્રીતિપૂર્વકમ્,\nદદામિ બુધ્દિયોગં તં યેન મામુપયાન્તિ તે.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Teshaam satatayuktaanaam bhajataam preetipoorvakam;\nDadaami buddhiyogam tam yena maamupayaanti te.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उन निरंतर मेरे ध्यान आदि में लगे हुए और प्रेमपूर्वक भजने वाले भक्तों को मैं वह तत्त्वज्ञानरूप योग देता हूँ, जिससे वे मुझको ही प्राप्त होते हैं॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("સદૈવ મારા ધ્યાનમાં રહેનારા અને પ્રીતિથી મને જ ભજનારા જ્ઞાનીજનો છે તેમને તત્વજ્ઞાનયોગથી હું પ્રાપ્ત થઇ શકું તેવો બુદ્ધિયોગ આપું છું.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("To those who are constantly devoted to serving Me with love, I give the understanding by which they can come to Me.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तेषामेवानुकम्पार्थमहमज्ञानजं तमः।\nनाशयाम्यात्मभावस्थो ज्ञानदीपेन भास्वता ॥");
        } else if (i == 0) {
            this.tv1.setText("તેષામેવાનુકમ્પાર્થમહમજ્ઞાનજં તમઃ,\nનાશયામ્યાત્મભાવસ્થો જ્ઞાનદીપેન ભાસ્વતા.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Teshaam evaanukampaartham aham ajnaanajam tamah;\nNaashayaamyaatmabhaavastho jnaanadeepena bhaaswataa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! उनके ऊपर अनुग्रह करने के लिए उनके अंतःकरण में स्थित हुआ मैं स्वयं ही उनके अज्ञानजनित अंधकार को प्रकाशमय तत्त्वज्ञानरूप दीपक के द्वारा नष्ट कर देता हूँ॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("તેમના પર અનુગ્રહ કરવા તેમના અંત:કરણમાં ઐક્યભાવથી સ્થિત થઈને પ્રકાશિત તત્વજ્ઞાનરૂપી દીપકના યોગથી તેમનો અજ્ઞાનજન્ય અંધકાર હું નષ્ટ કરું છું.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("To show them special mercy, I, dwelling in their hearts, destroy with the shining lamp of knowledge the darkness born of ignorance.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12_13() {
        setTitle("Shlok 12,13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nपरं ब्रह्म परं धाम पवित्रं परमं भवान्‌ ।\nपुरुषं शाश्वतं दिव्यमादिदेवमजं विभुम्‌ ॥\nआहुस्त्वामृषयः सर्वे देवर्षिर्नारदस्तथा ।\nअसितो देवलो व्यासः स्वयं चैव ब्रवीषि मे ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nપરં બ્રહ્મ પરં ધામ પવિત્રં પરમં ભવાન્,\nપુરુષં શાશ્વતં દિવ્યમાદિદેવમજં વિભુમ્.(૧૨)\nઆહુસ્ત્વામૃષયઃ સર્વે દેવર્ષિર્નારદસ્તથા,\nઅસિતો દેવલો વ્યાસઃ સ્વયં ચૈવ બ્રવીષિ મે.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nParam brahma param dhaama pavitram paramam bhavaan;\nPurusham shaashvatam divyam aadidevamajam vibhum.\nAahustwaam rishayah sarve devarshirnaaradastathaa;\nAsito devalo vyaasah swayam chaiva braveeshi me.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- आप परम ब्रह्म, परम धाम और परम पवित्र हैं, क्योंकि आपको सब ऋषिगण सनातन, दिव्य पुरुष एवं देवों का भी आदिदेव, अजन्मा और सर्वव्यापी कहते हैं। वैसे ही देवर्षि नारद तथा असित और देवल ऋषि तथा महर्षि व्यास भी कहते हैं और आप भी मेरे प्रति कहते हैं॥12-13॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે : હે વિભુ ! આપ પરમ બ્રહ્મ, પરમ ધામ અને પરમ પવિત્ર છો. આપ સનાતન દિવ્ય પુરુષ, દેવાધિદેવ આદિદેવ, શાશ્વત અને સર્વવ્યાપક છો.એટલા માટે જ દેવર્ષિ નારદ, અસિત, દેવલ, વ્યાસ વગેરે દેવર્ષિઓ આપને એ રીતે ઓળખે છે. અને આપ સ્વયં પણ મને એ જ વાત કરી રહ્યા છો.(૧૨-૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: You are the Supreme Personality of Godhead, the ultimate abode, the purest, the Absolute Truth. You are the eternal, transcendental, original person, the unborn, the greatest. All the great sages such as Narada, Asita, Devala and Vyasa confirm this truth about You, and now You Yourself are declaring it to me.(12-13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वमेतदृतं मन्ये यन्मां वदसि केशव ।\nन हि ते भगवन्व्यक्तिं विदुर्देवा न दानवाः ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વમેતદૃતં મન્યે યન્માં વદસિ કેશવ,\nન હિ તે ભગવન્ વ્યક્તિં વિદુર્દેવા ન દાનવાઃ.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Sarvametadritam manye yanmaam vadasi keshava;\nNa hi te bhagavan vyaktim vidurdevaa na daanavaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे केशव! जो कुछ भी मेरे प्रति आप कहते हैं, इस सबको मैं सत्य मानता हूँ। हे भगवन्‌! आपके लीलामय (गीता अध्याय 4 श्लोक 6 में इसका विस्तार देखना चाहिए) स्वरूप को न तो दानव जानते हैं और न देवता ही॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કેશવ ! આપ જે કંઈ મને કહી રહ્યા છો, તે સર્વ હું સત્ય માનું છું. હે ભગવાન ! દેવો અને દૈત્યો પણ આપનું સ્વરૂપ જાણી શક્યા નથી.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("O Krishna, I totally accept as truth all that You have told me. Neither the demigods nor the demons, O Lord, can understand Your personality.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स्वयमेवात्मनात्मानं वेत्थ त्वं पुरुषोत्तम ।\nभूतभावन भूतेश देवदेव जगत्पते ॥");
        } else if (i == 0) {
            this.tv1.setText("સ્વયમેવાત્મનાત્માનં વેત્થ ત્વં પુરુષોત્તમ,\nભૂતભાવન ભૂતેશ દેવદેવ જગત્પતે.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Swayamevaatmanaatmaanam vettha twam purushottama;\nBhootabhaavana bhootesha devadeva jagatpate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भूतों को उत्पन्न करने वाले! हे भूतों के ईश्वर! हे देवों के देव! हे जगत्‌ के स्वामी! हे पुरुषोत्तम! आप स्वयं ही अपने से अपने को जानते हैं॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કેશવ ! આપ જે કંઈ મને કહી રહ્યા છો, તે સર્વ હું સત્ય માનું છું. હે ભગવાન ! દેવો અને દૈત્યો પણ આપનું સ્વરૂપ જાણી શક્યા નથી.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("Indeed, You alone know Yourself by Your own internal potency, O Supreme Person, origin of all, Lord of all beings, God of gods, Lord of the universe!(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वक्तुमर्हस्यशेषेण दिव्या ह्यात्मविभूतयः ।\nयाभिर्विभूतिभिर्लोकानिमांस्त्वं व्याप्य तिष्ठसि ॥");
        } else if (i == 0) {
            this.tv1.setText("વક્તુમર્હસ્યશેષેણ દિવ્યા હ્યાત્મવિભૂતયઃ,\nયાભિર્વિભૂતિભિર્લોકાનિમાંસ્ત્વં વ્યાપ્ય તિષ્ઠસિ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Vaktum arhasyasheshena divyaa hyaatmavibhootayah;\nYaabhir vibhootibhir lokaanimaamstwam vyaapya tishthasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए आप ही उन अपनी दिव्य विभूतियों को संपूर्णता से कहने में समर्थ हैं, जिन विभूतियों द्वारा आप इन सब लोकों को व्याप्त करके स्थित हैं॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહારાજ  ! તમારી અનંત વિભૂતીઓમાંથી જેટલી વ્યાપક, શક્તિશાળી તથા તેજસ્વી હોય, તે બધી મને હવે જણાવો. હે અનંત ! તમારી જે વિભૂતિઓ ત્રણેલોકમાં વ્યાપ્ત થઇ રહી છે, તેમાંથી જે મુખ્ય અને પ્રસિદ્ધ છે તે મને કહો.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("Please tell me in detail of Your divine opulences by which You pervade all these worlds.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कथं विद्यामहं योगिंस्त्वां सदा परिचिन्तयन्‌ ।\nकेषु केषु च भावेषु चिन्त्योऽसि भगवन्मया ॥");
        } else if (i == 0) {
            this.tv1.setText("કથં વિદ્યામહં યોગિંસ્ત્વાં સદા પરિચિન્તયન્,\nકેષુ કેષુ ચ ભાવેષુ ચિન્ત્યોસિ ભગવન્મયા.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Katham vidyaamaham yogimstwaam sadaa parichintayan;\nKeshu keshu cha bhaaveshu chintyo’si bhagavanmayaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे योगेश्वर! मैं किस प्रकार निरंतर चिंतन करता हुआ आपको जानूँ और हे भगवन्‌! आप किन-किन भावों में मेरे द्वारा चिंतन करने योग्य हैं?॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે યોગેશ્વર ! સતત આપનું ચિંતન કરનારો હું આપને કયી રીતે જાણી શકું? હે ભગવન્  ! આપ કયા કયા ભાવોમાં મારા વડે ચિંતન કરવા યોગ્ય છો ? (૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("O Krishna, O supreme mystic, how shall I constantly think of You, and how shall I know You? In what various forms are You to be remembered, O Supreme Personality of Godhead?(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("विस्तरेणात्मनो योगं विभूतिं च जनार्दन ।\nभूयः कथय तृप्तिर्हि श्रृण्वतो नास्ति मेऽमृतम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("વિસ્તરેણાત્મનો યોગં વિભૂતિં ચ જનાર્દન,\nભૂયઃ કથય તૃપ્તિર્હિ શ્રૃણ્વતો નાસ્તિ મેમૃતમ્.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Vistarenaatmano yogam vibhootim cha janaardana;\nBhooyah kathaya triptirhi shrinvato naasti me’mritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे जनार्दन! अपनी योगशक्ति को और विभूति को फिर भी विस्तारपूर्वक कहिए, क्योंकि आपके अमृतमय वचनों को सुनते हुए मेरी तृप्ति नहीं होती अर्थात्‌ सुनने की उत्कंठा बनी ही रहती है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે જનાર્દન ! તમારો એ યોગ અને વિભૂતિ મને ફરી વિસ્તારપૂર્વક કહો, કેમ કે તમારી અમૃતમય વાણી ગમે તેટલી વાર સાંભળવા છતાં મને તૃપ્તિ થતી નથી.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("O Janardana, again please describe in detail the mystic power of Your opulences. I am never satiated in hearing about You, for the more I hear the more I want to taste the nectar of Your words.(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nहन्त ते कथयिष्यामि दिव्या ह्यात्मविभूतयः ।\nप्राधान्यतः कुरुश्रेष्ठ नास्त्यन्तो विस्तरस्य मे ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nહન્ત તે કથયિષ્યામિ દિવ્યા હ્યાત્મવિભૂતયઃ,\nપ્રાધાન્યતઃ કુરુશ્રેષ્ઠ નાસ્ત્યન્તો વિસ્તરસ્ય મે.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nHanta te kathayishyaami divyaa hyaatmavibhootayah;\nPraadhaanyatah kurushreshtha naastyanto vistarasya me.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे कुरुश्रेष्ठ! अब मैं जो मेरी दिव्य विभूतियाँ हैं, उनको तेरे लिए प्रधानता से कहूँगा; क्योंकि मेरे विस्तार का अंत नहीं है॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે : હે કુરુશ્રેષ્ઠ ! હવે મારી પ્રમુખ વિભૂતિઓ હું તને કહીશ કારણ કે મારા વિસ્તારનો અંત નથી.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("The Supreme Personality of Godhead said: Yes, I will tell you of My splendorous manifestations, but only of those which are prominent, O Arjuna, for My opulence is limitless.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अहमात्मा गुडाकेश सर्वभूताशयस्थितः ।\nअहमादिश्च मध्यं च भूतानामन्त एव च ॥");
        } else if (i == 0) {
            this.tv1.setText("અહમાત્મા ગુડાકેશ સર્વભૂતાશયસ્થિતઃ,\nઅહમાદિશ્ચ મધ્યં ચ ભૂતાનામન્ત એવ ચ.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Ahamaatmaa gudaakesha sarvabhootaashayasthitah;\nAhamaadishcha madhyam cha bhootaanaamanta eva cha.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! मैं सब भूतों के हृदय में स्थित सबका आत्मा हूँ तथा संपूर्ण भूतों का आदि, मध्य और अंत भी मैं ही हूँ॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ગુડાકેશ ! સર્વ ભૂતોના અંતરમાં રહેલો સર્વનો આત્મા હું છું. સર્વ ભૂતોનો આદિ,મધ્ય અને તેનો અંત પણ હું છું.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("I am the Supersoul, O Arjuna, seated in the hearts of all living entities. I am the beginning, the middle and the end of all beings.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आदित्यानामहं विष्णुर्ज्योतिषां रविरंशुमान्‌ ।\nमरीचिर्मरुतामस्मि नक्षत्राणामहं शशी ॥");
        } else if (i == 0) {
            this.tv1.setText("આદિત્યાનામહં વિષ્ણુર્જ્યોતિષાં રવિરંશુમાન્,\nમરીચિર્મરુતામસ્મિ નક્ષત્રાણામહં શશી.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Aadityaanaamaham vishnur jyotishaam raviramshumaan;\nMareechirmarutaamasmi nakshatraanaamaham shashee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं अदिति के बारह पुत्रों में विष्णु और ज्योतियों में किरणों वाला सूर्य हूँ तथा मैं उनचास वायुदेवताओं का तेज और नक्षत्रों का अधिपति चंद्रमा हूँ॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! અદિતિના બાર પુત્રોમાં વિષ્ણુ અર્થાત વામન અવતાર હું છું. પ્રકાશવંતોમાં સૂર્ય હું છું.ઓગણપચાસ વાયુદેવતાઓમાં મરીચિ નામનો વાયુદેવ હું છું અને નક્ષત્રોમાં નાક્ષત્રાધીપતિ ચંદ્રમા હું છું.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("Of the Adityas I am Vishnu, of lights I am the radiant sun, of the Maruts I am Marici, and among the stars I am the moon.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वेदानां सामवेदोऽस्मि देवानामस्मि वासवः ।\nइंद्रियाणां मनश्चास्मि भूतानामस्मि चेतना ॥");
        } else if (i == 0) {
            this.tv1.setText("વેદાનાં સામવેદોસ્મિ દેવાનામસ્મિ વાસવઃ,\nઇન્દ્રિયાણાં મનશ્ચાસ્મિ ભૂતાનામસ્મિ ચેતના.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Vedaanaam saamavedo’smi devaanaam asmi vaasavah;\nIndriyaanaam manashchaasmi bhootaanaamasmi chetanaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं वेदों में सामवेद हूँ, देवों में इंद्र हूँ, इंद्रियों में मन हूँ और भूत प्राणियों की चेतना अर्थात्‌ जीवन-शक्ति हूँ॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("વેદોમાં સામવેદ હું છું, દેવોમાં ઇન્દ્ર હું છું, ઇંદ્રિયોમાં મન હું છું અને પ્રાણીમાત્રમાં મૂળ જીવકળા હું છું.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("Of the Vedas I am the Sama Veda; of the demigods I am Indra, the king of heaven; of the senses I am the mind; and in living beings I am the living force [consciousness].(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("रुद्राणां शङ्‍करश्चास्मि वित्तेशो यक्षरक्षसाम्‌ ।\nवसूनां पावकश्चास्मि मेरुः शिखरिणामहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("રુદ્રાણાં શઙકરશ્ચાસ્મિ વિત્તેશો યક્ષરક્ષસામ્,\nવસૂનાં પાવકશ્ચાસ્મિ મેરુઃ શિખરિણામહમ્.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Rudraanaam shankarashchaasmi vittesho yaksharakshasaam;\nVasoonaam paavakashchaasmi meruh shikharinaamaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं एकादश रुद्रों में शंकर हूँ और यक्ष तथा राक्षसों में धन का स्वामी कुबेर हूँ। मैं आठ वसुओं में अग्नि हूँ और शिखरवाले पर्वतों में सुमेरु पर्वत हूँ॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("અગિયાર રુદ્રોમાં શંકર હું છું, યક્ષ તથા રાક્ષસોમાં ધનનો સ્વામી કુબેર હું છું,આઠ વસુઓમાં અગ્નિ હું છું અને શિખરબંધ પર્વતોમાં મેરુ પર્વત હું છું.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("Of all the Rudras I am Lord Shiva, of the Yakshas and Rakshasas I am the Lord of wealth [Kuvera], of the Vasus I am fire [Agni], and of mountains I am Meru.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पुरोधसां च मुख्यं मां विद्धि पार्थ बृहस्पतिम्‌ ।\nसेनानीनामहं स्कन्दः सरसामस्मि सागरः ॥");
        } else if (i == 0) {
            this.tv1.setText("પુરોધસાં ચ મુખ્યં માં વિદ્ધિ પાર્થ બૃહસ્પતિમ્,\nસેનાનીનામહં સ્કન્દઃ સરસામસ્મિ સાગરઃ.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Purodhasaam cha mukhyam maam viddhipaartha brihaspatim;\nSenaaneenaamaham skandah sarasaamasmi saagarah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पुरोहितों में मुखिया बृहस्पति मुझको जान। हे पार्थ! मैं सेनापतियों में स्कंद और जलाशयों में समुद्र हूँ॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ ! પુરોહીતમાં દેવતાઓના પુરોહિત બૃહસ્પતિ મને જાણ. સેનાપતિઓમાં કાર્તિકસ્વામી હું છું અને જળાશયોમાં સાગર હું છું.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Of priests, O Arjuna, know Me to be the chief, Brihaspati. Of generals I am Kartikeya, and of bodies of water I am the ocean.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("महर्षीणां भृगुरहं गिरामस्म्येकमक्षरम्‌ ।\nयज्ञानां जपयज्ञोऽस्मि स्थावराणां हिमालयः ॥");
        } else if (i == 0) {
            this.tv1.setText("મહર્ષીણાં ભૃગુરહં ગિરામસ્મ્યેકમક્ષરમ્,\nયજ્ઞાનાં જપયજ્ઞોસ્મિ સ્થાવરાણાં હિમાલયઃ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Maharsheenaam bhriguraham giraamasmyekamaksharam;\nYajnaanaam japayajno’smi sthaavaraanaam himaalayah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं महर्षियों में भृगु और शब्दों में एक अक्षर अर्थात्‌‌ ओंकार हूँ। सब प्रकार के यज्ञों में जपयज्ञ और स्थिर रहने वालों में हिमालय पहाड़ हूँ॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("સિદ્ધ મહર્ષિઓમાં ભૃગુ હું છું. વાણીમાં એકાક્ષર અર્થાત ઓમકાર હું છું , સર્વ પ્રકારના યજ્ઞોમાં જપયજ્ઞ હું છું અને અચળ વસ્તુઓમાં હિમાલય હું છું.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Of the great sages I am Bhrgu; of vibrations I am the transcendental om. Of sacrifices I am the chanting of the holy names [japa], and of immovable things I am the Himalayas.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अश्वत्थः सर्ववृक्षाणां देवर्षीणां च नारदः ।\nगन्धर्वाणां चित्ररथः सिद्धानां कपिलो मुनिः ॥");
        } else if (i == 0) {
            this.tv1.setText("અશ્વત્થઃ સર્વવૃક્ષાણાં દેવર્ષીણાં ચ નારદઃ,\nગન્ધર્વાણાં ચિત્રરથઃ સિધ્દાનાં કપિલો મુનિઃ.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Ashwatthah sarvavrikshaanaam devarsheenaam cha naaradah;\nGandharvaanaam chitrarathah siddhaanaam kapilo munih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं सब वृक्षों में पीपल का वृक्ष, देवर्षियों में नारद मुनि, गन्धर्वों में चित्ररथ और सिद्धों में कपिल मुनि हूँ॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ વૃક્ષોમાં પીપળો હું છું, દેવર્ષિઓમાં નારદ હું છું, ગંધર્વોમાં ચિત્રરથ હું છું અને સીદ્ધોમાં કપિલમુનિ હું છું.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("Of all trees I am the banyan tree, and of the sages among the demigods I am Narada. Of the Gandharvas I am Citraratha, and among perfected beings I am the sage Kapila.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("उच्चैःश्रवसमश्वानां विद्धि माममृतोद्धवम्‌ ।\nएरावतं गजेन्द्राणां नराणां च नराधिपम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ઉચ્ચૈઃશ્રવસમશ્વાનાં વિધ્દિ મામમૃતોદ્ભવમ્,\nઐરાવતં ગજેન્દ્રાણાં નરાણાં ચ નરાધિપમ્.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Ucchaihshravasamashwaanaam viddhi maamamritodbhavam;\nAiraavatam gajendraanaam naraanaam cha naraadhipam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("घोड़ों में अमृत के साथ उत्पन्न होने वाला उच्चैःश्रवा नामक घोड़ा, श्रेष्ठ हाथियों में ऐरावत नामक हाथी और मनुष्यों में राजा मुझको जान॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("અશ્વોમાં ક્ષીરસાગરમાંથી નીકળેલો ઉચૈ:શ્રવા અશ્વ હું છું, ઉત્તમ હાથીઓમાં ઐરાવત નામનો હાથી હું છું અને મનુષ્યોમાં રાજા હું છું એમ સમાજ.(૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("Of horses know Me to be Uccaihsrava, produced during the churning of the ocean for nectar. Of lordly elephants I am Airavata, and among men I am the monarch.(27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("आयुधानामहं वज्रं धेनूनामस्मि कामधुक्‌ ।\nप्रजनश्चास्मि कन्दर्पः सर्पाणामस्मि वासुकिः ॥");
        } else if (i == 0) {
            this.tv1.setText("આયુધાનામહં વજ્રં ધેનૂનામસ્મિ કામધુક્,\nપ્રજનશ્ચાસ્મિ કન્દર્પઃ સર્પાણામસ્મિ વાસુકિઃ.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Aayudhaanaamaham vajram dhenoonaamasmi kaamadhuk;\nPrajanashchaasmi kandarpah sarpaanaamasmi vaasukih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं शस्त्रों में वज्र और गौओं में कामधेनु हूँ। शास्त्रोक्त रीति से सन्तान की उत्पत्ति का हेतु कामदेव हूँ और सर्पों में सर्पराज वासुकि हूँ॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("આયુધોમાં વજ્ર હું છું, ગાયોમાં કામધેનું હું છું, પ્રજાને ઉત્પન કરનાર કામદેવ હું છું, સર્પોમાં વાસુકિ સર્પ હું છું.(૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("Of weapons I am the thunderbolt; among cows I am the surabhi. Of causes for procreation I am Kandarpa, the god of love, and of serpents I am Vasuki.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनन्तश्चास्मि नागानां वरुणो यादसामहम्‌ ।\nपितॄणामर्यमा चास्मि यमः संयमतामहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અનન્તશ્ચાસ્મિ નાગાનાં વરુણો યાદસામહમ્,\nપિતૃણામર્યમા ચાસ્મિ યમઃ સંયમતામહમ્.(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Anantashchaasmi naagaanaam varuno yaadasaamaham;\nPitreenaamaryamaa chaasmi yamah samyamataamaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं नागों में (नाग और सर्प ये दो प्रकार की सर्पों की ही जाति है।) शेषनाग और जलचरों का अधिपति वरुण देवता हूँ और पितरों में अर्यमा नामक पितर तथा शासन करने वालों में यमराज मैं हूँ॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("નાગોમાં નાગરાજ અનંત હું છું, જળદેવતાઓમાં વરુણ હું છું, પિતૃઓમાં અર્યમા નામના પિતૃદેવ હું છું અને નિયમન કરનારામાં યમ હું છું.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("Of the many-hooded Nagas I am Ananta, and among the aquatics I am the demigod Varuna. Of departed ancestors I am Aryama, and among the dispensers of law I am Yama, the lord of death.(29)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok30() {
        setTitle("Shlok 30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("प्रह्लादश्चास्मि दैत्यानां कालः कलयतामहम्‌ ।\nमृगाणां च मृगेन्द्रोऽहं वैनतेयश्च पक्षिणाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("પ્રહ્લાદશ્ચાસ્મિ દૈત્યાનાં કાલઃ કલયતામહમ્,\nમૃગાણાં ચ મૃગેન્દ્રોહં વૈનતેયશ્ચ પક્ષિણામ્.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Prahlaadashchaasmi daityaanaam kaalah kalayataamaham;\nMrigaanaam cha mrigendro’ham vainateyashcha pakshinaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं दैत्यों में प्रह्लाद और गणना करने वालों का समय (क्षण, घड़ी, दिन, पक्ष, मास आदि में जो समय है वह मैं हूँ) हूँ तथा पशुओं में मृगराज सिंह और पक्षियों में गरुड़ हूँ॥30॥");
        } else if (i2 == 0) {
            this.tv2.setText("દૈત્યોમાં પ્રહલાદ હું છું, ગણતરીઓમાં કાળ હું છું, પશુઓમાં સિંહ હું છું અને પક્ષીઓમાં ગરુડ હું છું.(૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("Among the Daitya demons I am the devoted Prahlada, among subduers I am time, among beasts I am the lion, and among birds I am Garuda.(30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        setTitle("Shlok 31");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पवनः पवतामस्मि रामः शस्त्रभृतामहम्‌ ।\nझषाणां मकरश्चास्मि स्रोतसामस्मि जाह्नवी ॥");
        } else if (i == 0) {
            this.tv1.setText("પવનઃ પવતામસ્મિ રામઃ શસ્ત્રભૃતામહમ્,\nઝષાણાં મકરશ્ચાસ્મિ સ્રોતસામસ્મિ જાહ્નવી.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Pavanah pavataamasmi raamah shastrabhritaamaham;\nJhashaanaam makarashchaasmi srotasaamasmi jaahnavee.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं पवित्र करने वालों में वायु और शस्त्रधारियों में श्रीराम हूँ तथा मछलियों में मगर हूँ और नदियों में श्री भागीरथी गंगाजी हूँ॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("પવિત્ર કરનારા પદાર્થોમાં હું છું, શસ્ત્રધારીઓમાં રામ હું છું, જળચરોમાં મગર હું છું અને નદીઓમાં ગંગા હું છું.(૩૧)");
        } else if (2 == i2) {
            this.tv2.setText("Of purifiers I am the wind, of the wielders of weapons I am Rama, of fishes I am the shark, and of flowing rivers I am the Ganges.(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        setTitle("Shlok 32");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्गाणामादिरन्तश्च मध्यं चैवाहमर्जुन ।\nअध्यात्मविद्या विद्यानां वादः प्रवदतामहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્ગાણામાદિરન્તશ્ચ મધ્યં ચૈવાહમર્જુન,\nઅધ્યાત્મવિદ્યા વિદ્યાનાં વાદઃ પ્રવદતામહમ્.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Sargaanaamaadirantashcha madhyam chaivaaham arjuna;\nAdhyaatmavidyaa vidyaanaam vaadah pravadataamaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! सृष्टियों का आदि और अंत तथा मध्य भी मैं ही हूँ। मैं विद्याओं में अध्यात्मविद्या अर्थात्‌ ब्रह्मविद्या और परस्पर विवाद करने वालों का तत्व-निर्णय के लिए किया जाने वाला वाद हूँ॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! સૃષ્ટિનો આદિ, અંત અને મધ્ય હું છું, સર્વ વિદ્યાઓમાં અધ્યાત્મવીદ્યા-બ્રહ્મવિધા હું છું, વાદવિવાદ કરનારાઓમાં વાદ હું છું.(૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("Of all creations I am the beginning and the end and also the middle, O Arjuna. Of all sciences I am the spiritual science of the self, and among logicians I am the conclusive truth.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अक्षराणामकारोऽस्मि द्वंद्वः सामासिकस्य च ।\nअहमेवाक्षयः कालो धाताहं विश्वतोमुखः ॥");
        } else if (i == 0) {
            this.tv1.setText("અક્ષરાણામકારોસ્મિ દ્વન્દ્વઃ સામાસિકસ્ય ચ,\nઅહમેવાક્ષયઃ કાલો ધાતાહં વિશ્વતોમુખઃ.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Aksharaanaamakaaro’smi dwandwah saamaasikasya cha;\nAhamevaakshayah kaalo dhaataaham vishwatomukhah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं अक्षरों में अकार हूँ और समासों में द्वंद्व नामक समास हूँ। अक्षयकाल अर्थात्‌ काल का भी महाकाल तथा सब ओर मुखवाला, विराट्स्वरूप, सबका धारण-पोषण करने वाला भी मैं ही हूँ॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("અક્ષરોમાં ‘ અ ‘કાર હું છું, સમાસોમાં દ્વંદ સમાસ હું છું તથા અક્ષયકાળ અને વિરાટ સ્વરૂપ ધરી સર્વને ધારણ –પોષણ કરનારો પણ હું છું.(૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("Of letters I am the letter A, and among compound words I am the dual compound. I am also inexhaustible time, and of creators I am Brahma.(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("मृत्युः सर्वहरश्चाहमुद्भवश्च भविष्यताम्‌ ।\nकीर्तिः श्रीर्वाक्च नारीणां स्मृतिर्मेधा धृतिः क्षमा ॥");
        } else if (i == 0) {
            this.tv1.setText("મૃત્યુઃ સર્વહરશ્ચાહમુદ્ભવશ્ચ ભવિષ્યતામ્,\nકીર્તિઃ શ્રીર્વાક્ચ નારીણાં સ્મૃતિર્મેધા ધૃતિઃ ક્ષમા.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Mrityuh sarvaharashchaaham udbhavashcha bhavishyataam;\nKeertih shreervaakcha naareenaam smritirmedhaadhritih kshamaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं सबका नाश करने वाला मृत्यु और उत्पन्न होने वालों का उत्पत्ति हेतु हूँ तथा स्त्रियों में कीर्ति (कीर्ति आदि ये सात देवताओं की स्त्रियाँ और स्त्रीवाचक नाम वाले गुण भी प्रसिद्ध हैं, इसलिए दोनों प्रकार से ही भगवान की विभूतियाँ हैं), श्री, वाक्‌, स्मृति, मेधा, धृति और क्षमा हूँ॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વનું મૃત્યુ હું છું, ભવિષ્યમાં થનારાં પ્રાણીઓની ઉત્પતિનો તેમજ ઉન્નતિનો હેતુ હું છું, નારી વિભૂતિઓમાં કીર્તિ, લક્ષ્મી, વાણી, સ્મૃતિ, બુદ્ધિ, ધૃતિ અને ક્ષમા પણ હું જ છું.(૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("I am all-devouring death, and I am the generating principle of all that is yet to be. Among women I am fame, fortune, fine speech, memory, intelligence, steadfastness and patience.(34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        setTitle("Shlok 35");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बृहत्साम तथा साम्नां गायत्री छन्दसामहम्‌ ।\nमासानां मार्गशीर्षोऽहमृतूनां कुसुमाकरः॥");
        } else if (i == 0) {
            this.tv1.setText("બૃહત્સામ તથા સામ્નાં ગાયત્રી છન્દસામહમ્,\nમાસાનાં માર્ગશીર્ષોહમૃતૂનાં કુસુમાકરઃ.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Brihatsaama tathaa saamnaam gaayatree cchandasaamaham;\nMaasaanaam maargasheersho’hamritoonaam kusumaakarah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("तथा गायन करने योग्य श्रुतियों में मैं बृहत्साम और छंदों में गायत्री छंद हूँ तथा महीनों में मार्गशीर्ष और ऋतुओं में वसंत मैं हूँ॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("ગાયન કરવા યોગ્ય શ્રુતિઓમાં બૃહ્ત્સામ હું છું, છંદોમાં ગાયત્રીછંદ હું છું, મહિનાઓમાં માર્ગશીષ માસ હું છું અને ઋતુઓમાં વસંતઋતુ હું છું.(૩૫)");
        } else if (2 == i2) {
            this.tv2.setText("Of the hymns in the Sama Veda I am the Brihat-sama, and of poetry I am the Gayatri. Of months I am Margasirsa [November-December], and of seasons I am flower-bearing spring.(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36() {
        setTitle("Shlok 36");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("द्यूतं छलयतामस्मि तेजस्तेजस्विनामहम्‌ ।\nजयोऽस्मि व्यवसायोऽस्मि सत्त्वं सत्त्ववतामहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("દ્યૂતં છલયતામસ્મિ તેજસ્તેજસ્વિનામહમ્,\nજયોસ્મિ વ્યવસાયોસ્મિ સત્ત્વં સત્ત્વવતામહમ્.(૩૬)");
        } else if (2 == i) {
            this.tv1.setText("Dyootam cchalayataamasmi tejastejaswinaamaham;\nJayo’smi vyavasaayo’smi sattwam sattwavataamaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं छल करने वालों में जूआ और प्रभावशाली पुरुषों का प्रभाव हूँ। मैं जीतने वालों का विजय हूँ, निश्चय करने वालों का निश्चय और सात्त्विक पुरुषों का सात्त्विक भाव हूँ॥36॥");
        } else if (i2 == 0) {
            this.tv2.setText("છલ કરનારાઓમાં ધૃત (જુગાર) હું છું, પ્રભાવશાળી પુરુષોનો પ્રભાવ હું છું, જીતનારાઓનો વિજય હું છું, નિશ્વય કરનારાઓનો નિશ્વય હું છું, સાત્વિક પુરુષોની સાત્વિકતા હું છું.(૩૬)");
        } else if (2 == i2) {
            this.tv2.setText("I am also the gambling of cheats, and of the splendid I am the splendor. I am victory, I am adventure, and I am the strength of the strong.(36)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok37() {
        setTitle("Shlok 37");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वृष्णीनां वासुदेवोऽस्मि पाण्डवानां धनञ्जयः ।\nमुनीनामप्यहं व्यासः कवीनामुशना कविः ॥");
        } else if (i == 0) {
            this.tv1.setText("વૃષ્ણીનાં વાસુદેવોસ્મિ પાણ્ડવાનાં ધનંજયઃ,\nમુનીનામપ્યહં વ્યાસઃ કવીનામુશના કવિઃ.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Vrishneenaam vaasudevo’smi paandavaanaam dhananjayah;\nMuneenaamapyaham vyaasah kaveenaamushanaa kavih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वृष्णिवंशियों में (यादवों के अंतर्गत एक वृष्णि वंश भी था) वासुदेव अर्थात्‌ मैं स्वयं तेरा सखा, पाण्डवों में धनञ्जय अर्थात्‌ तू, मुनियों में वेदव्यास और कवियों में शुक्राचार्य कवि भी मैं ही हूँ॥37॥");
        } else if (i2 == 0) {
            this.tv2.setText("વૃષ્ણિવંશીઓમાં વાસુદેવ  હું છું અને પાંડવોમાં અર્જુન હું છું, મુનિઓમાં વેદવ્યાસ હું છું અને કવિઓમાં શુક્રાચાર્ય હું છું.(૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("Of the descendants of Vrishni I am Vasudeva, and of the Pandavas I am Arjuna. Of the sages I am Vyasa, and among great thinkers I am Usana.(37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        setTitle("Shlok 38");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दण्डो दमयतामस्मि नीतिरस्मि जिगीषताम्‌ ।\nमौनं चैवास्मि गुह्यानां ज्ञानं ज्ञानवतामहम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("દણ્ડો દમયતામસ્મિ નીતિરસ્મિ જિગીષતામ્,\nમૌનં ચૈવાસ્મિ ગુહ્યાનાં જ્ઞાનં જ્ઞાનવતામહમ્.(૩૮)");
        } else if (2 == i) {
            this.tv1.setText("Dando damayataamasmi neetirasmi jigeeshataam;\nMaunam chaivaasmi guhyaanaam jnaanam jnaanavataamaham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं दमन करने वालों का दंड अर्थात्‌ दमन करने की शक्ति हूँ, जीतने की इच्छावालों की नीति हूँ, गुप्त रखने योग्य भावों का रक्षक मौन हूँ और ज्ञानवानों का तत्त्वज्ञान मैं ही हूँ॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("દમન કરનારાઓની દમનશક્તિ હું છું, જય મેળવવાની ઈચ્છાવાળાઓની નીતિ હું છું, ગુપ્ત રાખવાના ભાવમાં મૌન હું છું અને જ્ઞાનીઓનું તત્વજ્ઞાન પણ હું છું.(૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("Among all means of suppressing lawlessness I am punishment, and of those who seek victory I am morality. Of secret things I am silence, and of the wise I am the wisdom.(38)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        setTitle("Shlok 39");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यच्चापि सर्वभूतानां बीजं तदहमर्जुन ।\nन तदस्ति विना यत्स्यान्मया भूतं चराचरम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યચ્ચાપિ સર્વભૂતાનાં બીજં તદહમર્જુન,\nન તદસ્તિ વિના યત્સ્યાન્મયા ભૂતં ચરાચરમ્.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Yachchaapi sarvabhootaanaam beejam tadahamarjuna;\nNa tadasti vinaa yatsyaanmayaa bhootam charaacharam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और हे अर्जुन! जो सब भूतों की उत्पत्ति का कारण है, वह भी मैं ही हूँ, क्योंकि ऐसा चर और अचर कोई भी भूत नहीं है, जो मुझसे रहित हो॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન ! સર્વ ભૂતોની ઉત્પત્તિનું કારણ હું છું, મારા સિવાયના ચરાચર ભૂતો કોઈ જ નથી.(૩૯)");
        } else if (2 == i2) {
            this.tv2.setText("Furthermore, O Arjuna, I am the generating seed of all existences. There is no being—moving or nonmoving—that can exist without Me.(39)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        setTitle("Shlok 40");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नान्तोऽस्ति मम दिव्यानां विभूतीनां परन्तप ।\nएष तूद्देशतः प्रोक्तो विभूतेर्विस्तरो मया ॥");
        } else if (i == 0) {
            this.tv1.setText("નાન્તોસ્તિ મમ દિવ્યાનાં વિભૂતીનાં પરંતપ,\nએષ તૂદ્દેશતઃ પ્રોક્તો વિભૂતેર્વિસ્તરો મયા.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Naanto’sti mama divyaanaam vibhooteenaam parantapa;\nEsha tooddeshatah prokto vibhootervistaro mayaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे परंतप! मेरी दिव्य विभूतियों का अंत नहीं है, मैंने अपनी विभूतियों का यह विस्तार तो तेरे लिए एकदेश से अर्थात्‌ संक्षेप से कहा है॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પરંતપ ! મારી દિવ્ય વિભૂતિઓનો અંત નથી. મારી જે વિભૂતિઓનો વિસ્તાર છે તે મેં તને ટૂંકમાં કહી સંભળાવ્યો.(૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("O mighty conqueror of enemies, there is no end to My divine manifestations. What I have spoken to you is but a mere indication of My infinite opulences.(40)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        setTitle("Shlok 41");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यद्यद्विभूतिमत्सत्त्वं श्रीमदूर्जितमेव वा ।\nतत्तदेवावगच्छ त्वं मम तेजोंऽशसम्भवम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યદ્યદ્વિભૂતિમત્સત્ત્વં શ્રીમદૂર્જિતમેવ વા,\nતત્તદેવાવગચ્છ ત્વં મમ તેજોંશસંભવમ્.(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Yadyad vibhootimat sattwam shreemadoorjitameva vaa;\nTattadevaavagaccha twam mama tejom’shasambhavam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो-जो भी विभूतियुक्त अर्थात्‌ ऐश्वर्ययुक्त, कांतियुक्त और शक्तियुक्त वस्तु है, उस-उस को तू मेरे तेज के अंश की ही अभिव्यक्ति जान॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાર્થ  !   જે પણ  વિભૂતિયુક્ત, અશ્વર્યયુક્ત, શોભાયુક્ત, કે અન્ય પ્રભાવથી યુક્ત હોય તે મારા તેજના અંશરૂપ છે એમ તું જાણ.(૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("Know that all opulent, beautiful and glorious creations spring from but a spark of My splendor.(41)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42() {
        setTitle("Shlok 42");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथवा बहुनैतेन किं ज्ञातेन तवार्जुन ।\nविष्टभ्याहमिदं कृत्स्नमेकांशेन स्थितो जगत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અથવા બહુનૈતેન કિં જ્ઞાતેન તવાર્જુન,\nવિષ્ટભ્યાહમિદં કૃત્સ્નમેકાંશેન સ્થિતો જગત્. (૪૨)");
        } else if (2 == i) {
            this.tv1.setText("Athavaa bahunaitena kim jnaatena tavaarjuna;\nVishtabhyaahamidam kritsnamekaamshena sthito jagat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अथवा हे अर्जुन! इस बहुत जानने से तेरा क्या प्रायोजन है। मैं इस संपूर्ण जगत्‌ को अपनी योगशक्ति के एक अंश मात्र से धारण करके स्थित हूँ॥42॥");
        } else if (i2 == 0) {
            this.tv2.setText("અથવા હે અર્જુન ! મેં જે આ ઘણી વાતો તને સંભળાવી તે જાણવાનું પ્રયોજન શું છે? હું આ સંપૂર્ણ જગતને મારી યોગમાયાના એક અંશ માત્રથી ધારણ કરી રહ્યો છું, માટે મને જ તત્વથી જાણવો જોઈએ.(૪૨)");
        } else if (2 == i2) {
            this.tv2.setText("But what need is there, Arjuna, for all this detailed knowledge? With a single fragment of Myself I pervade and support this entire universe.(42)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay10.this, ShlokListPlay10.this);
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

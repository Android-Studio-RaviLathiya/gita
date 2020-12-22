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

public class ShlokListPlay5 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay5.this.favflag == 0) {
                    ShlokListPlay5.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.favflag = 1;
                    shlokListPlay5.save();
                } else if (1 == ShlokListPlay5.this.favflag) {
                    ShlokListPlay5.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay5 shlokListPlay52 = ShlokListPlay5.this;
                    shlokListPlay52.favflag = 0;
                    shlokListPlay52.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay5.this.calll++;
                ShlokListPlay5.this.slkset();
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
                ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                shlokListPlay5.calll--;
                ShlokListPlay5.this.slkset();
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
        btn_slock_silent = findViewById(R.id.btn_slock_silent);
        btn_slocktras_silent = findViewById(R.id.btn_slocktras_silent);


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
        edit.putString("A5" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A5" + this.calll, "0");
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
        if (28 == this.calll) {
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
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.callf1 = 0;
                    shlokListPlay5.slkset();
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
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.callf1 = 2;
                    shlokListPlay5.slkset();
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
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.callf1 = 1;
                    shlokListPlay5.slkset();
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
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.callf2 = 0;
                    shlokListPlay5.slkset();
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
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.callf2 = 2;
                    shlokListPlay5.slkset();
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
                    ShlokListPlay5 shlokListPlay5 = ShlokListPlay5.this;
                    shlokListPlay5.callf2 = 1;
                    shlokListPlay5.slkset();
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
            shlok8_9();
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
            shlok27_28();
        } else if (27 == i3) {
            shlok29();
        } else if (28 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 5");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ०५ \nकर्मसंन्यासयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૫ \nકર્મ સન્યાસ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 05\nkarma sanyas yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की, ज्ञानी पुरुष दिव्य ज्ञानी की अग्नि से शुद्ध होकर बाह्यतः सारे कर्म करता है, किन्तु अन्तर में उन कर्मो के फल का परित्याग करता हुआ शान्ति, विरक्ति, सहिष्णुताइस अध्याय में कहा है की, ज्ञानी पुरुष दिव्य ज्ञानी की अग्नि से शुद्ध होकर बाह्यतः सारे कर्म करता है, किन्तु अन्तर में उन कर्मो के फल का परित्याग करता हुआ शान्ति, विरक्तिइस अध्याय में कहा है की, ज्ञानी पुरुष दिव्य ज्ञानी की अग्नि से शुद्ध होकर बाह्यतः सारे कर्म करता है, किन्तु अन्तर में उन कर्मो के फल का परित्याग करता हुआ शान्ति, विरक्ति, सहिष्णुता, आध्यात्मिक द्रष्टि तथा आनन्द की प्राप्ति करता है। सहिष्णुता, आध्यात्मिक द्रष्टि तथा आनन्द की प्राप्ति करता है। आध्यात्मिक द्रष्टि तथा आनन्द की प्राप्ति करता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૫ માં માત્ર કર્મ ના સન્યાસ (ત્યાગ) ની રીત શીખવવા યોગતત્વ નો પ્રારંભ કરેલો છે. પ્રકૃતિ (માયા) કાર્ય કરે છે, આ સમજી લઇ ,\"હું કશું કરતો નથી પણ ઇન્દ્રિયો તેમના વિષયો માં પ્રવૃત થાય છે.\" એમ વિચારવાનું કહે છે. ઇન્દ્રિયો ને તેમના વિષય માં થી કેમ પછી ખેંચી લેવી, તે માટેની વિધિ નું વર્ણન કરેલ છે.  આમ જ્ઞાન થી જ કર્મ નો ત્યાગ કરી શકાય છે.");
        } else if (2 == i) {
            this.tv1.setText("Fifth adhyay tells, Outwardly performing all actions but inwardly renouncing their fruits, the wise man, purified by the fire of transcendental knowledge, attains peace, detachment, forbearance, spiritual vision and bliss.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        setTitle("Shlok 1");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nसन्न्यासं कर्मणां कृष्ण पुनर्योगं च शंससि ।\nयच्छ्रेय एतयोरेकं तन्मे ब्रूहि सुनिश्चितम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nસંન્યાસં કર્મણાં કૃષ્ણ પુનર્યોગં ચ શંસસિ\nયચ્છ્રેય તયોરેકં તન્મે બ્રૂહિ સુનિશ્ચિતમ્.(૧)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nSannyaasam karmanaam krishna punar yogam cha shamsasi;\nYacchreya etayorekam tanme broohi sunishchitam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- हे कृष्ण! आप कर्मों के संन्यास की और फिर कर्मयोग की प्रशंसा करते हैं। इसलिए इन दोनों में से जो एक मेरे लिए भलीभाँति निश्चित कल्याणकारक साधन हो, उसको कहिए॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુને કહ્યું - હે કૃષ્ણ ! આપ એક તરફ કર્મ ના ત્યાગ ના વખાણ કરો છો અને બીજી તરફ કર્મયોગ ના વખાણ કરો છો.તો એ બે માંથી જે કલ્યાણકારી હોય તે મને કહો.(૧)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: O Krishna, first of all You ask me to renounce work, and then again You recommend work with devotion. Now will You kindly tell me definitely which of the two is more beneficial?(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        setTitle("Shlok 2");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nसन्न्यासः कर्मयोगश्च निःश्रेयसकरावुभौ ।\nतयोस्तु कर्मसन्न्यासात्कर्मयोगो विशिष्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nસંન્યાસઃ કર્મયોગશ્ચ નિઃશ્રેયસકરાવુભૌ,\nતયોસ્તુ કર્મસંન્યાસાત્કર્મયોગો વિશિષ્યતે.(૨)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nSannyaasah karmayogashcha nihshreyasakaraa vubhau;\nTayostu karmasannyaasaat karmayogo vishishyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- कर्म संन्यास और कर्मयोग- ये दोनों ही परम कल्याण के करने वाले हैं, परन्तु उन दोनों में भी कर्म संन्यास से कर्मयोग साधन में सुगम होने से श्रेष्ठ है॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન બોલ્યા: કર્મો નો ત્યાગ અને કર્મયોગ બન્ને કલ્યાણકારક છે,પરંતુ એ બન્નેમાં કર્મો ના ત્યાગથી કર્મયોગ શ્રેષ્ઠ છે. (૨)");
        } else if (2 == i2) {
            this.tv2.setText("The Personality of Godhead replied: The renunciation of work and work in devotion are both good for liberation. But, of the two, work in devotional service is better than renunciation of work.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        setTitle("Shlok 3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञेयः स नित्यसन्न्यासी यो न द्वेष्टि न काङ्‍क्षति ।\nनिर्द्वन्द्वो हि महाबाहो सुखं बन्धात्प्रमुच्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞેયઃ સ નિત્યસંન્યાસી યો ન દ્વેષ્ટિ ન કાઙ્ક્ષતિ,\nનિર્દ્વન્દ્વો હિ મહાબાહો સુખં બન્ધાત્પ્રમુચ્યતે.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Jneyah sa nityasannyaasi yo na dweshti na kaangkshati;\nNirdwandwo hi mahaabaaho sukham bandhaat pramuchyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जो पुरुष न किसी से द्वेष करता है और न किसी की आकांक्षा करता है, वह कर्मयोगी सदा संन्यासी ही समझने योग्य है क्योंकि राग-द्वेषादि द्वंद्वों से रहित पुरुष सुखपूर्वक संसार बंधन से मुक्त हो जाता है॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો ! જે કોઈનો દ્વેષ કરતો નથી, જે કોઈ અભિલાષા રાખતો નથી, તેને નિત્ય સંન્યાસી જાણવો. આવો રાગ દ્વેષ વિનાનો મનુષ્ય દ્વંદ્વરહિત બની સંસાર બંધનમાંથી સુખપૂર્વક મુક્ત થાય છે. (૩)");
        } else if (2 == i2) {
            this.tv2.setText("One who neither hates nor desires the fruits of his activities is known to be always renounced. Such a person, free from all dualities, easily overcomes material bondage and is completely liberated, O mighty-armed Arjuna.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("साङ्‍ख्ययोगौ पृथग्बालाः प्रवदन्ति न पण्डिताः ।\nएकमप्यास्थितः सम्यगुभयोर्विन्दते फलम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સાંખ્યયોગૌ પૃથગ્બાલાઃ પ્રવદન્તિ ન પણ્ડિતાઃ,\nએકમપ્યાસ્થિતઃ સમ્યગુભયોર્વિન્દતે ફલમ્.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Saankhyayogau prithagbaalaah pravadanti na panditaah;\nEkam apyaasthitah samyag ubhayor vindate phalam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उपर्युक्त संन्यास और कर्मयोग को मूर्ख लोग पृथक्‌-पृथक् फल देने वाले कहते हैं न कि पण्डितजन, क्योंकि दोनों में से एक में भी सम्यक्‌ प्रकार से स्थित पुरुष दोनों के फलरूप परमात्मा को प्राप्त होता है॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંન્યાસ અને કર્મયોગ ફળની દ્રષ્ટિએ અલગ અલગ છે એમ અજ્ઞાનીઓ માને છે, પરંતુ જ્ઞાનીઓ એમ કહેતા નથી.બન્નેમાંથી એક નું પણ ઉત્તમ રીતે અનુષ્ઠાન કરનાર બંનેના ફળ ને પ્રાપ્ત કરે છે.(૪)");
        } else if (2 == i2) {
            this.tv2.setText("Only the ignorant speak of devotional service [karma-yoga] as being different from the analytical study of the material world [Sankhya]. Those who are actually learned say that he who applies himself well to one of these paths achieves the results of both.(4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यत्साङ्‍ख्यैः प्राप्यते स्थानं तद्यौगैरपि गम्यते ।\nएकं साङ्‍ख्यं च योगं च यः पश्यति स पश्यति ॥");
        } else if (i == 0) {
            this.tv1.setText("યત્સાંખ્યૈઃ પ્રાપ્યતે સ્થાનં તદ્યોગૈરપિ ગમ્યતે,\nએકં સાંખ્યં ચ યોગં ચ યઃ પશ્યતિ સ પશ્યતિ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Yatsaankhyaih praapyate sthaanam tad yogair api gamyate;\nEkam saankhyam cha yogam cha yah pashyati sa pashyati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("ज्ञान योगियों द्वारा जो परमधाम प्राप्त किया जाता है, कर्मयोगियों द्वारा भी वही प्राप्त किया जाता है। इसलिए जो पुरुष ज्ञानयोग और कर्मयोग को फलरूप में एक देखता है, वही यथार्थ देखता है॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે મોક્ષપદ જ્ઞાનયોગ દ્વારા પ્રાપ્ત થઇ શકે છે,તે જ પદ નિષ્કામ કર્મયોગ દ્વારા પણ પ્રાપ્ત કરી શકાય છે.એ માટે જ સાંખ્ય તથા કર્મયોગ ને જે એકજ સમજે છે તે સાચો જ્ઞાની છે.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("One who knows that the position reached by means of analytical study can also be attained by devotional service, and who therefore sees analytical study and devotional service to be on the same level, sees things as they are.(5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सन्न्यासस्तु महाबाहो दुःखमाप्तुमयोगतः ।\nयोगयुक्तो मुनिर्ब्रह्म नचिरेणाधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("સંન્યાસસ્તુ મહાબાહો દુઃખમાપ્તુમયોગતઃ,\nયોગયુક્તો મુનિર્બ્રહ્મ નચિરેણાધિગચ્છતિ.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Sannyaasastu mahaabaaho duhkham aaptuma yogatah;\nYogayukto munir brahma na chirenaadhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" परन्तु हे अर्जुन! कर्मयोग के बिना संन्यास अर्थात्‌ मन, इन्द्रिय और शरीर द्वारा होने वाले सम्पूर्ण कर्मों में कर्तापन का त्याग प्राप्त होना कठिन है और भगवत्स्वरूप को मनन करने वाला कर्मयोगी परब्रह्म परमात्मा को शीघ्र ही प्राप्त हो जाता है॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે મહાબાહો ! કર્મયોગ ના અનુષ્ઠાન વગર સંન્યાસ પ્રાપ્ત કરવો કઠીન છે. જયારે કર્મયોગી મુનિ જલદીથી સંન્યાસ પ્રાપ્ત કરી બ્રહ્મ ને પામે છે.(૬)");
        } else if (2 == i2) {
            this.tv2.setText("Merely renouncing all activities yet not engaging in the devotional service of the Lord cannot make one happy. But a thoughtful person engaged in devotional service can achieve the Supreme without delay.(6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7() {
        setTitle("Shlok 7");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("योगयुक्तो विशुद्धात्मा विजितात्मा जितेन्द्रियः ।\nसर्वभूतात्मभूतात्मा कुर्वन्नपि न लिप्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યોગયુક્તો વિશુદ્ધાત્મા વિજિતાત્મા જિતેન્દ્રિયઃ,\nસર્વભૂતાત્મભૂતાત્મા કુર્વન્નપિ ન લિપ્યતે.(૭)");
        } else if (2 == i) {
            this.tv1.setText("Yogayukto vishuddhaatmaa vijitaatmaa jitendriyah;\nSarvabhootaatmabhootaatmaa kurvannapi na lipyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसका मन अपने वश में है, जो जितेन्द्रिय एवं विशुद्ध अन्तःकरण वाला है और सम्पूर्ण प्राणियों का आत्मरूप परमात्मा ही जिसका आत्मा है, ऐसा कर्मयोगी कर्म करता हुआ भी लिप्त नहीं होता॥7॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મયોગ ના આચરણ થી જેનું અંત:કરણ શુદ્ધ થઇ ગયું છે,જે મનને વશ કરનારો,ઈન્દ્રિયોને જીતનારો છે.અને જેનો આત્મા સર્વ ભૂતો નો આત્મા બની ગયો છે,તે મનુષ્ય કર્મો કરે છે છતાં તેનાથી લેપાતો નથી.(૭)");
        } else if (2 == i2) {
            this.tv2.setText("One who works in devotion, who is a pure soul, and who controls his mind and senses is dear to everyone, and everyone is dear to him. Though always working, such a man is never entangled.(7)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok8_9() {
        setTitle("Shlok 8,9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नैव किंचित्करोमीति युक्तो मन्येत तत्ववित्‌ ।\nपश्यञ्श्रृण्वन्स्पृशञ्जिघ्रन्नश्नन्गच्छन्स्वपंश्वसन्‌ ॥\nप्रलपन्विसृजन्गृह्णन्नुन्मिषन्निमिषन्नपि ॥\nइन्द्रियाणीन्द्रियार्थेषु वर्तन्त इति धारयन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("નૈવ કિંચિત્કરોમીતિ યુક્તો મન્યેત તત્ત્વવિત્,\nપશ્યન્ શ્રૃણવન્સ્પૃશઞ્જિઘ્રન્નશ્નન્ગચ્છન્સ્વપન્ શ્વસન્.(૮)\nપ્રલપન્વિસૃજન્ગૃહ્ણન્નુન્મિષન્નિમિષન્નપિ,\nઇન્દ્રિયાણીન્દ્રિયાર્થેષુ વર્તન્ત ઇતિ ધારયન્.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Naiva kinchit karomeeti yukto manyeta tattwavit;\nPashyan shrunvan sprishan jighran nashnan gacchan swapan shwasan.\nPralapan visrijan grihnan nunmishan nimishannapi;\nIndriyaaneendriyaartheshu vartanta iti dhaarayan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" तत्व को जानने वाला सांख्ययोगी तो देखता हुआ, सुनता हुआ, स्पर्श करता हुआ, सूँघता हुआ, भोजन करता हुआ, गमन करता हुआ, सोता हुआ, श्वास लेता हुआ, बोलता हुआ, त्यागता हुआ, ग्रहण करता हुआ तथा आँखों को खोलता और मूँदता हुआ भी, सब इन्द्रियाँ अपने-अपने अर्थों में बरत रही हैं- इस प्रकार समझकर निःसंदेह ऐसा मानें कि मैं कुछ भी नहीं करता हूँ॥8-9॥");
        } else if (i2 == 0) {
            this.tv2.setText("યોગયુક્ત બનેલો તત્વજ્ઞાની પોતે જોતાં, સાંભળતાં, સ્પર્શ કરતાં, સુંઘતાં, ખાતાં, પીતાં, ચાલતાં, નિંદ્રા લેતાં, શ્વાસોશ્વાસ લેતાં, બોલતાં, ત્યાગ કરતાં, ગ્રહણ કરતાં. આંખ ઉઘડતાં મીંચતાં,હોવા છતાં,ઇન્દ્રિયો પોત પોતાના વિષય માં પ્રવૃત થાય છે એમ સમજીને હું કંઈ કરતો નથી એમ નિશ્વયપૂર્વક માને છે.((૮-૯)");
        } else if (2 == i2) {
            this.tv2.setText("A person in the divine consciousness, although engaged in seeing, hearing, touching, smelling, eating, moving about, sleeping and breathing, always knows within himself that he actually does nothing at all. Because while speaking, evacuating, receiving, or opening or closing his eyes, he always knows that only the material senses are engaged with their objects and that he is aloof from them.(8-9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ब्रह्मण्याधाय कर्माणि सङ्‍गं त्यक्त्वा करोति यः ।\nलिप्यते न स पापेन पद्मपत्रमिवाम्भसा ॥");
        } else if (i == 0) {
            this.tv1.setText("બ્રહ્મણ્યાધાય કર્માણિ સઙ્ગં ત્યક્ત્વા કરોતિ યઃ,\nલિપ્યતે ન સ પાપેન પદ્મપત્રમિવામ્ભસા.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Brahmanyaadhaaya karmaani sangam tyaktwaa karoti yah;\nLipyate na sa paapena padmapatram ivaambhasaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष सब कर्मों को परमात्मा में अर्पण करके और आसक्ति को त्याग कर कर्म करता है, वह पुरुष जल से कमल के पत्ते की भाँति पाप से लिप्त नहीं होता॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે મનુષ્ય ફળ ની ઈચ્છા નો ત્યાગ કરી સર્વ ફળ બ્ર્હ્માપર્ણ બુદ્ધિ થી કરે છે, એ કમળપત્ર જેમ પાણી માં રહેવા છતાં ભીંજાતું નથી, તેમ પાપ વડે લેપાતો નથી.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("One who performs his duty without attachment, surrendering the results unto the Supreme Lord, is unaffected by sinful action, as the lotus leaf is untouched by water.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कायेन मनसा बुद्धया केवलैरिन्द्रियैरपि ।\nयोगिनः कर्म कुर्वन्ति संग त्यक्त्वात्मशुद्धये ॥");
        } else if (i == 0) {
            this.tv1.setText("કાયેન મનસા બુદ્ધ્યા કેવલૈરિન્દ્રિયૈરપિ,\nયોગિનઃ કર્મ કુર્વન્તિ સઙ્ગં ત્યક્ત્વાત્મશુદ્ધયે.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Kaayena manasaa buddhyaa kevalair indriyair api;\nYoginah karma kurvanti sangam tyaktwaatmashuddhaye.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कर्मयोगी ममत्वबुद्धिरहित केवल इन्द्रिय, मन, बुद्धि और शरीर द्वारा भी आसक्ति को त्याग कर अन्तःकरण की शुद्धि के लिए कर्म करते हैं॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("યોગીઓ માત્ર મન,બુદ્ધિ અને ઇન્દ્રિયોથી ફળની આસક્તિ છોડી દઈઆત્માની શુદ્ધિ માટે કર્મો કરે છે.(૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("The yogis, abandoning attachment, act with body, mind, intelligence and even with the senses, only for the purpose of purification.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("युक्तः कर्मफलं त्यक्त्वा शान्तिमाप्नोति नैष्ठिकीम्‌ ।\nअयुक्तः कामकारेण फले सक्तो निबध्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યુક્તઃ કર્મફલં ત્યક્ત્વા શાન્તિમાપ્નોતિ નૈષ્ઠિકીમ્,\nઅયુક્તઃ કામકારેણ ફલે સક્તો નિબધ્યતે.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Yuktah karmaphalam tyaktwaa shaantim aapnoti naishthikeem;\nAyuktah kaamakaarena phale sakto nibadhyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कर्मयोगी कर्मों के फल का त्याग करके भगवत्प्राप्ति रूप शान्ति को प्राप्त होता है और सकामपुरुष कामना की प्रेरणा से फल में आसक्त होकर बँधता है॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મયોગી મનુષ્ય કર્મફળને ત્યજીને સત્વશુદ્ધિના ક્રમથી થયેલી શાંતિને પ્રાપ્ત કરેછે. જયારે સકામ મનુષ્ય કામના વડે ફળની આસક્તિ રાખી બંધનમાં પડે છે.(૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("The steadily devoted soul attains unadulterated peace because he offers the result of all activities to Me; whereas a person who is not in union with the Divine, who is greedy for the fruits of his labor, becomes entangled.(12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वकर्माणि मनसा संन्यस्यास्ते सुखं वशी ।\nनवद्वारे पुरे देही नैव कुर्वन्न कारयन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વકર્માણિ મનસા સંન્યસ્યાસ્તે સુખં વશી,\nનવદ્વારે પુરે દેહી નૈવ કુર્વન્ન કારયન્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Sarvakarmaani manasaa sannyasyaaste sukham vashee;\nNavadwaare pure dehee naiva kurvan na kaarayan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अन्तःकरण जिसके वश में है, ऐसा सांख्य योग का आचरण करने वाला पुरुष न करता हुआ और न करवाता हुआ ही नवद्वारों वाले शरीर रूप घर में सब कर्मों को मन से त्यागकर आनंदपूर्वक सच्चिदानंदघन परमात्मा के स्वरूप में स्थित रहता है॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("દેહને વશ કરનારો મનુષ્ય સર્વ કર્મોને માનસિક રીતે ત્યાગીને નવ દરવાજા વાળા નગરમાં સુખપૂર્વક રહે છે.તે કંઈ જ કરતો નથી અને કંઈ જ કરાવતો નથી.(૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("When the embodied living being controls his nature and mentally renounces all actions, he resides happily in the city of nine gates [the material body], neither working nor causing work to be done.(13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न कर्तृत्वं न कर्माणि लोकस्य सृजति प्रभुः ।\nन कर्मफलसंयोगं स्वभावस्तु प्रवर्तते ।");
        } else if (i == 0) {
            this.tv1.setText("ન કર્તૃત્વં ન કર્માણિ લોકસ્ય સૃજતિ પ્રભુઃ,\nન કર્મફલસંયોગં સ્વભાવસ્તુ પ્રવર્તતે.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Na kartritwam na karmaani lokasya srijati prabhuh;\nNa karmaphala samyogam swabhaavas tu pravartate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परमेश्वर मनुष्यों के न तो कर्तापन की, न कर्मों की और न कर्मफल के संयोग की रचना करते हैं, किन्तु स्वभाव ही बर्त रहा है॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("આત્મા દેહાદિક ના કર્તાપણાને ઉત્પન કરતો નથી,કર્મોને ઉત્પન કરતો નથી કે કર્મફળ ના સંયોગ ને ઉત્પન કરતો નથી,પરંતુ તે અવિદ્યારૂપ માયાનો જ સર્વ ખેલ છે.(૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("The embodied spirit, master of the city of his body, does not create activities, nor does he induce people to act, nor does he create the fruits of action. All this is enacted by the modes of material nature.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("नादत्ते कस्यचित्पापं न चैव सुकृतं विभुः ।\nअज्ञानेनावृतं ज्ञानं तेन मुह्यन्ति जन्तवः ॥");
        } else if (i == 0) {
            this.tv1.setText("નાદત્તે કસ્યચિત્પાપં ન ચૈવ સુકૃતં વિભુઃ,\nઅજ્ઞાનેનાવૃતં જ્ઞાનં તેન મુહ્યન્તિ જન્તવઃ.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Naadatte kasyachit paapam na chaiva sukritam vibhuh;\nAjnaanenaavritam jnaanam tena muhyanti jantavah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("सर्वव्यापी परमेश्वर भी न किसी के पाप कर्म को और न किसी के शुभकर्म को ही ग्रहण करता है, किन्तु अज्ञान द्वारा ज्ञान ढँका हुआ है, उसी से सब अज्ञानी मनुष्य मोहित हो रहे हैं॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("પરમેશ્વર કોઈનાં પાપ કે પુણ્યને પોતાના શિરે વહોરી લેતા નથી,પરંતુ જ્ઞાન અજ્ઞાન વડે ઢંકાયેલું છે. તેને લીધે સર્વ જીવો મોહ પામે છે.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Nor does the Supreme Lord assume anyone’s sinful or pious activities. Embodied beings, however, are bewildered because of the ignorance which covers their real knowledge.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ज्ञानेन तु तदज्ञानं येषां नाशितमात्मनः ।\nतेषामादित्यवज्ज्ञानं प्रकाशयति तत्परम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("જ્ઞાનેન તુ તદજ્ઞાનં યેષાં નાશિતમાત્મનઃ,\nતેષામાદિત્યવજ્જ્ઞાનં પ્રકાશયતિ તત્પરમ્.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Jnaanena tu tad ajnaanam yeshaam naashitam aatmanah;\nTeshaam aadityavaj jnaanam prakaashayati tatparam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("परन्तु जिनका वह अज्ञान परमात्मा के तत्व ज्ञान द्वारा नष्ट कर दिया गया है, उनका वह ज्ञान सूर्य के सदृश उस सच्चिदानन्दघन परमात्मा को प्रकाशित कर देता है॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("વળી જેમનું એ અજ્ઞાન આત્માના જ્ઞાન વડે નાશ પામેલું છે,તેમનું તે જ્ઞાન સૂર્યની જેમ પરબ્રહ્મને પ્રકાશિત કરેછે.(૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("When, however, one is enlightened with the knowledge by which nescience is destroyed, then his knowledge reveals everything, as the sun lights up everything in the daytime.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तद्‍बुद्धयस्तदात्मानस्तन्निष्ठास्तत्परायणाः ।\nगच्छन्त्यपुनरावृत्तिं ज्ञाननिर्धूतकल्मषाः ॥");
        } else if (i == 0) {
            this.tv1.setText("તદ્બુદ્ધયસ્તદાત્માનસ્તન્નિષ્ઠાસ્તત્પરાયણાઃ,\nગચ્છન્ત્યપુનરાવૃત્તિં જ્ઞાનનિર્ધૂતકલ્મષાઃ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Tadbuddhayas tadaatmaanas tannishthaas tatparaayanaah;\nGacchantyapunaraavrittim jnaana nirdhoota kalmashaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिनका मन तद्रूप हो रहा है, जिनकी बुद्धि तद्रूप हो रही है और सच्चिदानन्दघन परमात्मा में ही जिनकी निरंतर एकीभाव से स्थिति है, ऐसे तत्परायण पुरुष ज्ञान द्वारा पापरहित होकर अपुनरावृत्ति को अर्थात परमगति को प्राप्त होते हैं॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે પરબ્રહ્મમાં જ જેમની બુદ્ધિ સ્થિત થઇ છે તે બ્રહ્મ જ તેમનો આત્મા છે.તેમનામાં જ તેમની સંપૂર્ણ નિષ્ઠા છે. તેઓ તેમના જ પરાયણ બની જાય છે.જ્ઞાન વડે જેમનાં પાપકર્મો નાશ પામેછે તેઓ જન્મમરણના ચક્કર માં પડતા નથી.(૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("When one’s intelligence, mind, faith and refuge are all fixed in the Supreme, then one becomes fully cleansed of misgivings through complete knowledge and thus proceeds straight on the path of liberation.(17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("विद्याविनयसम्पन्ने ब्राह्मणे गवि हस्तिनि ।\nशुनि चैव श्वपाके च पण्डिताः समदर्शिनः ॥");
        } else if (i == 0) {
            this.tv1.setText("વિદ્યાવિનયસંપન્ને બ્રાહ્મણે ગવિ હસ્તિનિ,\nશુનિ ચૈવ શ્વપાકે ચ પણ્ડિતાઃ સમદર્શિનઃ.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Vidyaavinaya sampanne braahmane gavi hastini;\nShuni chaiva shvapaake cha panditaah samadarshinah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वे ज्ञानीजन विद्या और विनययुक्त ब्राह्मण में तथा गौ, हाथी, कुत्ते और चाण्डाल में भी समदर्शी (इसका विस्तार गीता अध्याय 6 श्लोक 32 की टिप्पणी में देखना चाहिए।) ही होते हैं॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે જ્ઞાનીજન વિદ્યા અને વિનય આદિના ગુણોવાળા છે તે પંડિત, બ્રાહ્મણ, ગાય, હાથી, કુતરો, ચંડાળ વગેરે સર્વમાં સમાન દ્રષ્ટિવાળા હોય છે.(૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("The humble sages, by virtue of true knowledge, see with equal vision a learned and gentle brahmana, a cow, an elephant, a dog and a dog-eater [outcaste].(18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इहैव तैर्जितः सर्गो येषां साम्ये स्थितं मनः ।\nनिर्दोषं हि समं ब्रह्म तस्माद् ब्रह्मणि ते स्थिताः ॥");
        } else if (i == 0) {
            this.tv1.setText("ઇહૈવ તૈર્જિતઃ સર્ગો યેષાં સામ્યે સ્થિતં મનઃ\n,નિર્દોષં હિ સમં બ્રહ્મ તસ્માદ્બ્રહ્મણિ તે સ્થિતાઃ.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Ihaiva tairjitah sargo yeshaam saamye sthitam manah;\nNirdosham hi samam brahma tasmaad brahmani te sthitaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिनका मन समभाव में स्थित है, उनके द्वारा इस जीवित अवस्था में ही सम्पूर्ण संसार जीत लिया गया है क्योंकि सच्चिदानन्दघन परमात्मा निर्दोष और सम है, इससे वे सच्चिदानन्दघन परमात्मा में ही स्थित हैं॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેમનું મન સમત્વ(પરમાત્મા) માં રહ્યું છે તે સમદર્શી મનુષ્યે આ જન્મમાં જ સંસારને જીતી લીધો છે. કારણ કે બ્રહ્મ દોષથી રહિત અને સમાન હોવાથી એ મનુષ્ય બ્રહ્મમાં સ્થિત રહે છે.(૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("Those whose minds are established in sameness and equanimity have already conquered the conditions of birth and death. They are flawless like Brahman, and thus they are already situated in Brahman.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न प्रहृष्येत्प्रियं प्राप्य नोद्विजेत्प्राप्य चाप्रियम्‌ ।\nस्थिरबुद्धिरसम्मूढो ब्रह्मविद् ब्रह्मणि स्थितः ॥");
        } else if (i == 0) {
            this.tv1.setText("ન પ્રહૃષ્યેત્પ્રિયં પ્રાપ્ય નોદ્વિજેત્પ્રાપ્ય ચાપ્રિયમ્,\nસ્થિરબુદ્ધિરસમ્મૂઢો બ્રહ્મવિદ્બ્રહ્મણિ સ્થિતઃ.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Na prahrishyet priyam praapya nodwijet praapya chaapriyam;\nSthirabuddhir asammoodho brahmavid brahmani sthitah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष प्रिय को प्राप्त होकर हर्षित नहीं हो और अप्रिय को प्राप्त होकर उद्विग्न न हो, वह स्थिरबुद्धि, संशयरहित, ब्रह्मवेत्ता पुरुष सच्चिदानन्दघन परब्रह्म परमात्मा में एकीभाव से नित्य स्थित है॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેની બુદ્ધિ સ્થિર થયેલી છે,જેનું અજ્ઞાન નાશ પામ્યું છે અને જે બ્રહ્મમાં સ્થિર થયો છે એવો બ્રહ્મવેત્તા મનુષ્ય તે પ્રિય પદાર્થો મેળવીને હર્ષ પામતો નથી અને અપ્રિય પદાર્થો પામીને દુઃખી થતો નથી.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("A person who neither rejoices upon achieving something pleasant nor laments upon obtaining something unpleasant, who is self-intelligent, who is unbewildered, and who knows the science of God, is already situated in transcendence.(20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("बाह्यस्पर्शेष्वसक्तात्मा विन्दत्यात्मनि यत्सुखम्‌ ।\nस ब्रह्मयोगयुक्तात्मा सुखमक्षयमश्नुते ॥");
        } else if (i == 0) {
            this.tv1.setText("બાહ્યસ્પર્શેષ્વસક્તાત્મા વિન્દત્યાત્મનિ યત્સુખમ્,\nસ બ્રહ્મયોગયુક્તાત્મા સુખમક્ષયમશ્નુતે.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Baahyasparsheshwasaktaatmaa vindatyaatmani yat sukham;\nSa brahma yoga yuktaatmaa sukham akshayam ashnute.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("बाहर के विषयों में आसक्तिरहित अन्तःकरण वाला साधक आत्मा में स्थित जो ध्यानजनित सात्विक आनंद है, उसको प्राप्त होता है, तदनन्तर वह सच्चिदानन्दघन परब्रह्म परमात्मा के ध्यानरूप योग में अभिन्न भाव से स्थित पुरुष अक्षय आनन्द का अनुभव करता है॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("ઇન્દ્રિયોના સ્પર્શથી ઉત્પન થનાર સુખોમાં આસક્તિ રહિત ચિત્તવાળો મનુષ્ય આત્મામાં રહેલા સુખને પામે છે.એવો પરબ્રહ્મ સ્વરૂપ પ્રાપ્ત થયેલો મનુષ્ય અક્ષય સુખ નો અનુભવ કરે છે.(૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("Such a liberated person is not attracted to material sense pleasure but is always in trance, enjoying the pleasure within. In this way the self-realized person enjoys unlimited happiness, for he concentrates on the Supreme.(21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये हि संस्पर्शजा भोगा दुःखयोनय एव ते ।\nआद्यन्तवन्तः कौन्तेय न तेषु रमते बुधः ॥");
        } else if (i == 0) {
            this.tv1.setText("યે હિ સંસ્પર્શજા ભોગા દુઃખયોનય એવ તે,\nઆદ્યન્તવન્તઃ કૌન્તેય ન તેષુ રમતે બુધઃ.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Ye hi samsparshajaa bhogaa duhkhayonaya eva te;\nAadyantavantah kaunteya na teshu ramate budhah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो ये इन्द्रिय तथा विषयों के संयोग से उत्पन्न होने वाले सब भोग हैं, यद्यपि विषयी पुरुषों को सुखरूप भासते हैं, तो भी दुःख के ही हेतु हैं और आदि-अन्तवाले अर्थात अनित्य हैं। इसलिए हे अर्जुन! बुद्धिमान विवेकी पुरुष उनमें नहीं रमता॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે  કાંન્તેય  ! ઇન્દ્રિયો અને વિષયોના સ્પર્શથી ઉત્પન થયેલા જે ભોગો છે તે સર્વ ઉત્પતિ અને નાશ ને વશ હોવાથી દુઃખના કારણરૂપ છે.એટલા માટે જ્ઞાનીજનો તેમાં પ્રીતિ રાખતા નથી.(૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("An intelligent person does not take part in the sources of misery, which are due to contact with the material senses. O son of Kunti, such pleasures have a beginning and an end, and so the wise man does not delight in them.(22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("शक्नोतीहैव यः सोढुं प्राक्शरीरविमोक्षणात्‌ ।\nकामक्रोधोद्भवं वेगं स युक्तः स सुखी नरः ॥");
        } else if (i == 0) {
            this.tv1.setText("શક્નોતીહૈવ યઃ સોઢું પ્રાક્શરીરવિમોક્ષણાત્,\nકામક્રોધોદ્ભવં વેગં સ યુક્તઃ સ સુખી નરઃ.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Shaknoteehaiva yah sodhum praak shareera vimokshanaat;\nKaamakrodhodbhavam vegam sa yuktah sa sukhee narah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो साधक इस मनुष्य शरीर में, शरीर का नाश होने से पहले-पहले ही काम-क्रोध से उत्पन्न होने वाले वेग को सहन करने में समर्थ हो जाता है, वही पुरुष योगी है और वही सुखी है॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("શરીર નો નાશ થવા પહેલાં જે મનુષ્ય કામ અને ક્રોધથી ઉત્પન થયેલા વેગને સહન કરી શકે છે તે મનુષ્ય આ લોકમાં યોગી છે અને તે સાચો સુખી છે.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("Before giving up this present body, if one is able to tolerate the urges of the material senses and check the force of desire and anger, he is well situated and is happy in this world.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("योऽन्तःसुखोऽन्तरारामस्तथान्तर्ज्योतिरेव यः ।\nस योगी ब्रह्मनिर्वाणं ब्रह्मभूतोऽधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("યોન્તઃસુખોન્તરારામસ્તથાન્તર્જ્યોતિરેવ યઃ,\nસ યોગી બ્રહ્મનિર્વાણં બ્રહ્મભૂતોધિગચ્છતિ.(૨૪)\n");
        } else if (2 == i) {
            this.tv1.setText("Yo’ntah sukho’ntaraaraamas tathaantarjyotir eva yah;\nSa yogee brahma nirvaanam brahmabhooto’dhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष अन्तरात्मा में ही सुखवाला है, आत्मा में ही रमण करने वाला है तथा जो आत्मा में ही ज्ञान वाला है, वह सच्चिदानन्दघन परब्रह्म परमात्मा के साथ एकीभाव को प्राप्त सांख्य योगी शांत ब्रह्म को प्राप्त होता है॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે અંતરાત્મા માં સુખનો અનુભવ કરે છે તથા આત્મા માં જ રમણ કરે છે,જેના અંતરાત્મામાં જ્ઞાન રૂપી પ્રકાશ પથરાઈ ગયો છે તે યોગી બ્રહ્મસ્વરૂપ બની પરબ્રહ્મમાં જ નિર્વાણ પામેછે.(૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("One whose happiness is within, who is active and rejoices within, and whose aim is inward is actually the perfect mystic. He is liberated in the Supreme, and ultimately he attains the Supreme.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("लभन्ते ब्रह्मनिर्वाणमृषयः क्षीणकल्मषाः ।\nछिन्नद्वैधा यतात्मानः सर्वभूतहिते रताः ॥");
        } else if (i == 0) {
            this.tv1.setText("લભન્તે બ્રહ્મનિર્વાણમૃષયઃ ક્ષીણકલ્મષાઃ,\nછિન્નદ્વૈધા યતાત્માનઃ સર્વભૂતહિતે રતાઃ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Labhante brahma nirvaanam rishayah ksheenakalmashaah;\nCchinnadwaidhaa yataatmaanah sarvabhootahite rataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिनके सब पाप नष्ट हो गए हैं, जिनके सब संशय ज्ञान द्वारा निवृत्त हो गए हैं, जो सम्पूर्ण प्राणियों के हित में रत हैं और जिनका जीता हुआ मन निश्चलभाव से परमात्मा में स्थित है, वे ब्रह्मवेत्ता पुरुष शांत ब्रह्म को प्राप्त होते हैं॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેના પાપાદિ  દોષો નાશ પામ્યા છે,જેના સંશયો છેદાઈ ગયા છે,જેમનાં મન-ઇન્દ્રિયો વશમાં થઇ ગયા છે અને જે પ્રાણીમાત્રના હિત માટે તત્પર છે,એવા ઋષિઓ બ્રહ્મનિર્વાણને પામે છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are beyond the dualities that arise from doubts, whose minds are engaged within, who are always busy working for the welfare of all living beings, and who are free from all sins achieve liberation in the Supreme.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कामक्रोधवियुक्तानां यतीनां यतचेतसाम्‌ ।\nअभितो ब्रह्मनिर्वाणं वर्तते विदितात्मनाम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કામક્રોધવિયુક્તાનાં યતીનાં યતચેતસામ્,\nઅભિતો બ્રહ્મનિર્વાણં વર્તતે વિદિતાત્મનામ્.(૨૬)\n");
        } else if (2 == i) {
            this.tv1.setText("Kaamakrodhaviyuktaanaam yateenaam yatachetasaam;\nAbhito brahma nirvaanam vartate viditaatmanaam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("काम-क्रोध से रहित, जीते हुए चित्तवाले, परब्रह्म परमात्मा का साक्षात्कार किए हुए ज्ञानी पुरुषों के लिए सब ओर से शांत परब्रह्म परमात्मा ही परिपूर्ण है॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેઓ કામ-ક્રોધથી રહિત છે,જેમણે ચિત્તને વશમાં રાખ્યું છે,અને જેઓ આત્મસાક્ષાત્કાર પામેલા છે એવા યોગીઓ સર્વ અવસ્થામાં પરબ્રહ્મ પરમાત્માને પ્રાપ્ત થાય છે.(૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("Those who are free from anger and all material desires, who are self-realized, self-disciplined and constantly endeavoring for perfection, are assured of liberation in the Supreme in the very near future.(26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27_28() {
        setTitle("Shlok 27,28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स्पर्शान्कृत्वा बहिर्बाह्यांश्चक्षुश्चैवान्तरे भ्रुवोः ।\nप्राणापानौ समौ कृत्वा नासाभ्यन्तरचारिणौ ॥\nयतेन्द्रियमनोबुद्धिर्मुनिर्मोक्षपरायणः ।\nविगतेच्छाभयक्रोधो यः सदा मुक्त एव सः ॥");
        } else if (i == 0) {
            this.tv1.setText("સ્પર્શાન્કૃત્વા બહિર્બાહ્યાંશ્ચક્ષુશ્ચૈવાન્તરે ભ્રુવોઃ,\nપ્રાણાપાનૌ સમૌ કૃત્વા નાસાભ્યન્તરચારિણૌ.(૨૭)\nયતેન્દ્રિયમનોબુદ્ધિર્મુનિર્મોક્ષપરાયણઃ,\nવિગતેચ્છાભયક્રોધો યઃ સદા મુક્ત એવ સઃ.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Sparsaan kritwaa bahir baahyaamschakshus chaivaantare bhruvoh;\nPraanaapaanau samau kritwaa naasaabhyantara chaarinau.\nYatendriya manobuddhir munir mokshaparaayanah;\nVigatecchaabhaya krodho yah sadaa mukta eva sah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("बाहर के विषय-भोगों को न चिन्तन करता हुआ बाहर ही निकालकर और नेत्रों की दृष्टि को भृकुटी के बीच में स्थित करके तथा नासिका में विचरने वाले प्राण और अपानवायु को सम करके, जिसकी इन्द्रियाँ मन और बुद्धि जीती हुई हैं, ऐसा जो मोक्षपरायण मुनि (परमेश्वर के स्वरूप का निरन्तर मनन करने वाला।) इच्छा, भय और क्रोध से रहित हो गया है, वह सदा मुक्त ही है॥27-28॥");
        } else if (i2 == 0) {
            this.tv2.setText("બહારના વિષયોને વૈરાગ્ય દ્વારા બહાર કાઢીને તથા દ્રષ્ટિને ભ્રમરની મધ્યમાં સ્થિર કરીને નાકની અંદર ગતિ કરનારા પ્રાણ તથા અપાનવાયુને સમાન કરીને. જેણે ઇન્દ્રિયો, મન તથા બુદ્ધિ વશ કર્યા છે તથા જેનાં ઈચ્છા,ભય અને ક્રોધ દુર થયાંછે એવા મુનિ મોક્ષપરાયણ છે તે સદા મુક્ત જ છે.(૨૭-૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("Shutting out all external sense objects, keeping the eyes and vision concentrated between the two eyebrows, suspending the inward and outward breaths within the nostrils, and thus controlling the mind, senses and intelligence, the transcendentalist aiming at liberation becomes free from desire, fear and anger. One who is always in this state is certainly liberated.(27-28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29() {
        setTitle("Shlok 29");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("भोक्तारं यज्ञतपसां सर्वलोकमहेश्वरम्‌ ।\nसुहृदं सर्वभूतानां ज्ञात्वा मां शान्तिमृच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("ભોક્તારં યજ્ઞતપસાં સર્વલોકમહેશ્વરમ્,\nસુહૃદં સર્વભૂતાનાં જ્ઞાત્વા માં શાન્તિમૃચ્છતિ(૨૯)");
        } else if (2 == i) {
            this.tv1.setText("Bhoktaaram yajnatapasaam sarvaloka maheshwaram;\nSuhridam sarvabhootaanaam jnaatwaa maam shaantim ricchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" मेरा भक्त मुझको सब यज्ञ और तपों का भोगने वाला, सम्पूर्ण लोकों के ईश्वरों का भी ईश्वर तथा सम्पूर्ण भूत-प्राणियों का सुहृद् अर्थात स्वार्थरहित दयालु और प्रेमी, ऐसा तत्व से जानकर शान्ति को प्राप्त होता है॥29॥");
        } else if (i2 == 0) {
            this.tv2.setText("સર્વ યજ્ઞ અને તપનો ભોક્તા,સર્વ લોકોનો મહેશ્વર અને સર્વ ભૂતોનો પરમ મિત્ર હું જ છું. એ રીતે જે જાણે છે તે શાંતિને પ્રાપ્ત થાય છે.(૨૯)");
        } else if (2 == i2) {
            this.tv2.setText("A person in full consciousness of Me, knowing Me to be the ultimate beneficiary of all sacrifices and austerities, the Supreme Lord of all planets and demigods, and the benefactor and well-wisher of all living entities, attains peace from the pangs of material miseries.(29)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay5.this, ShlokListPlay5.this);
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

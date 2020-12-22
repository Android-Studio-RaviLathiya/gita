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

public class ShlokListPlay4 extends AppCompatActivity implements TextToSpeech.OnInitListener {
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
                if (ShlokListPlay4.this.favflag == 0) {
                    ShlokListPlay4.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.favflag = 1;
                    shlokListPlay4.save();
                } else if (1 == ShlokListPlay4.this.favflag) {
                    ShlokListPlay4.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay4 shlokListPlay42 = ShlokListPlay4.this;
                    shlokListPlay42.favflag = 0;
                    shlokListPlay42.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShlokListPlay4.this.calll++;
                ShlokListPlay4.this.slkset();
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
                ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                shlokListPlay4.calll--;
                ShlokListPlay4.this.slkset();
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
        edit.putString("A4" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A4" + this.calll, "0");
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
        if (39 == this.calll) {
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
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.callf1 = 0;
                    shlokListPlay4.slkset();
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
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.callf1 = 2;
                    shlokListPlay4.slkset();
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
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.callf1 = 1;
                    shlokListPlay4.slkset();
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
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.callf2 = 0;
                    shlokListPlay4.slkset();
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
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.callf2 = 2;
                    shlokListPlay4.slkset();
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
                    ShlokListPlay4 shlokListPlay4 = ShlokListPlay4.this;
                    shlokListPlay4.callf2 = 1;
                    shlokListPlay4.slkset();
                }
            });
        }
        int i3 = this.calll;
        if (i3 == 0) {
            shlok0();
        } else if (1 == i3) {
            shlok1_2_3();
        } else if (2 == i3) {
            shlok4();
        } else if (3 == i3) {
            shlok5();
        } else if (4 == i3) {
            shlok6();
        } else if (5 == i3) {
            shlok7_8();
        } else if (6 == i3) {
            shlok9();
        } else if (7 == i3) {
            shlok10();
        } else if (8 == i3) {
            shlok11();
        } else if (9 == i3) {
            shlok12();
        } else if (10 == i3) {
            shlok13();
        } else if (11 == i3) {
            shlok14();
        } else if (12 == i3) {
            shlok15();
        } else if (13 == i3) {
            shlok16();
        } else if (14 == i3) {
            shlok17();
        } else if (15 == i3) {
            shlok18();
        } else if (16 == i3) {
            shlok19();
        } else if (17 == i3) {
            shlok20();
        } else if (18 == i3) {
            shlok21();
        } else if (19 == i3) {
            shlok22();
        } else if (20 == i3) {
            shlok23();
        } else if (21 == i3) {
            shlok24();
        } else if (22 == i3) {
            shlok25();
        } else if (23 == i3) {
            shlok26();
        } else if (24 == i3) {
            shlok27();
        } else if (25 == i3) {
            shlok28();
        } else if (26 == i3) {
            shlok29_30();
        } else if (27 == i3) {
            shlok31();
        } else if (28 == i3) {
            shlok32();
        } else if (29 == i3) {
            shlok33();
        } else if (30 == i3) {
            shlok34();
        } else if (31 == i3) {
            shlok35();
        } else if (32 == i3) {
            shlok36();
        } else if (33 == i3) {
            shlok37();
        } else if (34 == i3) {
            shlok38();
        } else if (35 == i3) {
            shlok39();
        } else if (36 == i3) {
            shlok40();
        } else if (37 == i3) {
            shlok41();
        } else if (38 == i3) {
            shlok42();
        } else if (39 == i3) {
            shlokEND();
        }
    }

    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 4");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय  ०४ \nज्ञानकर्मसंन्यासयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૪ \nજ્ઞાન કર્મ સન્યાસ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 04\ngnan karma sanyas yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("इस अध्याय में कहा है की आत्मा  ईश्वर तथा इन दोनों से सम्बन्धित दिव्य ज्ञान शुदध करने तथा मोक्ष प्रदान करने वाला है। ऐसा ज्ञान कर्मयोग हा फल है। भगवान गीता के प्राचीन इतहास, इस भौतिक जगत में बारम्बार अपने अवतरण की महत्ता तथा गुरु के पास जाने की आवश्यकता का उपदेश देते हैं।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૪ માં જ્ઞાન અને કર્મ બંને ના સન્યાસ (ત્યાગ) વિષે કહ્યું છે. ઈશ્વર અધર્મ નો નાશ કરવા મનુષ્ય રૂપે (દેવરૂપે) અવતાર લે છે, જેને અજ્ઞાની લોકો ભગવાન માનવા તૈયાર નથી. જેથી તેનામાં અસંખ્ય સંશયો પેદા થાય છે, જેને આત્મ જ્ઞાનની તલવારથી કાપી નાખી કર્મયોગ નું પાલન (યુદ્ધ) કરવાનું શીખવે છે. આત્મ-જ્ઞાની ને કર્મ નું બંધન રહેતું નથી.");
        } else if (2 == i) {
            this.tv1.setText("Forth adhyay tells, Transcendental knowledge-the spiritual knowledge of the soul, of God, and their relationship-is both purifying and liberating. Such knowledge is the fruit of selfless devotional action (karma-yoga). The Lord explains the remote history of the Gita, the purpose and significance of His periodic descents to the material world, and the necessity of approaching a guru, a realized teacher.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1_2_3() {
        setTitle("Shlok 1,2,3");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्री भगवानुवाच\nइमं विवस्वते योगं प्रोक्तवानहमव्ययम्‌ ।\nविवस्वान्मनवे प्राह मनुरिक्ष्वाकवेऽब्रवीत्‌ ॥\nएवं परम्पराप्राप्तमिमं राजर्षयो विदुः ।\nस कालेनेह महता योगो नष्टः परन्तप ॥\nस एवायं मया तेऽद्य योगः प्रोक्तः पुरातनः ।\nभक्तोऽसि मे सखा चेति रहस्यं ह्येतदुत्तमम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nઇમં વિવસ્વતે યોગં પ્રોક્તવાનહમવ્યયમ્,\nવિવસ્વાન્ મનવેપ્રાહમનુરિક્ષ્વાકવેબ્રવીત્.(૧)\nએવં પરમ્પરાપ્રાપ્તમિમં રાજર્ષયો વિદુ:,\nસ કાલેનેહ મહતા યોગો નષ્ટઃ પરન્તપ.(૨)\nસ એવાયં મયા તેદ્ય યોગઃ પ્રોક્તઃ પુરાતનઃ,\nભક્તોસિ મે સખા ચેતિ રહસ્યં હ્યેતદુત્તમમ્.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nImam vivaswate yogam proktavaan aham avyayam;\nVivaswaan manave praaha manur ikshwaakave’braveet.\nEvam paramparaa praaptam imam raajarshayo viduh;\nSa kaaleneha mahataa yogo nashtah parantapa.\nSa evaayam mayaa te’dya yogah proktah puraatanah;\nBhakto’si me sakhaa cheti rahasyam hyetad uttamam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- मैंने इस अविनाशी योग को सूर्य से कहा था, सूर्य ने अपने पुत्र वैवस्वत मनु से कहा और मनु ने अपने पुत्र राजा इक्ष्वाकु से कहा. हे परन्तप अर्जुन! इस प्रकार परम्परा से प्राप्त इस योग को राजर्षियों ने जाना, किन्तु उसके बाद वह योग बहुत काल से इस पृथ्वी लोक में लुप्तप्राय हो गया. तू मेरा भक्त और प्रिय सखा है, इसलिए वही यह पुरातन योग आज मैंने तुझको कहा है क्योंकि यह बड़ा ही उत्तम रहस्य है अर्थात गुप्त रखने योग्य विषय है॥1-2-3॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે છે -મેં આ અવિનાશી યોગ સૌપ્રથમ સૂર્યને કહ્યો હતો. સૂર્યે એના પુત્ર મનુને કહ્યો અને મનુએ એના પુત્ર ઈક્ષ્વાકુને કહ્યો. હે અર્જુન, આ રીતે પરંપરાથી ચાલ્યો આવતો આ યોગ ઋષિઓએ જાણ્યો. પરંતુ કાળક્રમે એ યોગ નષ્ટ પામ્યો છે. તું મારો પ્રિય ભક્ત અને મિત્ર છે એથી આજે આ જ્ઞાનને મેં તારી આગળ પ્રકટ કર્યું.(૧-૨-૩)");
        } else if (2 == i2) {
            this.tv2.setText("The Personality of Godhead, Lord Sri Krishna, said: I instructed this imperishable science of yoga to the sun-god, Vivasvan, and Vivasvan instructed it to Manu, the father of mankind, and Manu in turn instructed it to Ikshavaku.This supreme science was thus received through the chain of disciplic succession, and the saintly kings understood it in that way. But in course of time the succession was broken, and therefore the science as it is appears to be lost. That very ancient science of the relationship with the Supreme is today told by Me to you because you are My devotee as well as My friend and can therefore understand the transcendental mystery of this science.(1-2-3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4() {
        setTitle("Shlok 4");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अर्जुन उवाच\nअपरं भवतो जन्म परं जन्म विवस्वतः ।\nकथमेतद्विजानीयां त्वमादौ प्रोक्तवानिति ॥");
        } else if (i == 0) {
            this.tv1.setText("અર્જુન ઉવાચ-\nઅપરં ભવતો જન્મ પરં જન્મ વિવસ્વતઃ,\nકથમેતદ્વિજાનીયાં ત્વમાદૌ પ્રોક્તવાનિતિ.(૪)");
        } else if (2 == i) {
            this.tv1.setText("Arjuna Uvaacha:\nAparam bhavato janma param janma vivaswatah;\nKatham etadvijaaneeyaam twam aadau proktavaan iti.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अर्जुन बोले- आपका जन्म तो अर्वाचीन-अभी हाल का है और सूर्य का जन्म बहुत पुराना है अर्थात कल्प के आदि में हो चुका था। तब मैं इस बात को कैसे समूझँ कि आप ही ने कल्प के आदि में सूर्य से यह योग कहा था?॥4॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુન કહે છે-હે કેશવ, તમારો જન્મ તો હમણાં થયો જ્યારે સૂર્ય તો બહુ પહેલેથી વિદ્યમાન છે. તો મને સંશય થાય છે કે તમે સૂર્યને આ યોગ સૃષ્ટિના આરંભમાં કેવી રીતે કહ્યો ? (૪)");
        } else if (2 == i2) {
            this.tv2.setText("Arjuna said: The sun-god Vivasvān is senior by birth to You. How am I to understand that in the beginning You instructed this science to him? (4)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok5() {
        setTitle("Shlok 5");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रीभगवानुवाच\nबहूनि मे व्यतीतानि जन्मानि तव चार्जुन ।\nतान्यहं वेद सर्वाणि न त्वं वेत्थ परन्तप ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રી ભગવાનુવાચ-\nબહૂનિ મે વ્યતીતાનિ જન્માનિ તવ ચાર્જુન,\nતાન્યહં વેદ સર્વાણિ ન ત્વં વેત્થ પરન્તપ.(૫)");
        } else if (2 == i) {
            this.tv1.setText("Sri Bhagavaan Uvaacha:\nBahooni me vyateetaani janmaani tava chaarjuna;\nTaanyaham veda sarvaani na twam vettha parantapa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्री भगवान बोले- हे परंतप अर्जुन! मेरे और तेरे बहुत से जन्म हो चुके हैं। उन सबको तू नहीं जानता, किन्तु मैं जानता हूँ॥5॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રી ભગવાન કહે છે -હે અર્જુન, તારા અને મારા અનેક જન્મ થઈ ચુક્યા છે. પરંતુ ફરક એટલો છે કે મને એ બધા યાદ છે અને તેને એ યાદ નથી રહ્યા.(૫)");
        } else if (2 == i2) {
            this.tv2.setText("The Personality of Godhead said: Many, many births both you and I have passed. I can remember all of them, but you cannot, O subduer of the enemy! (5)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok6() {
        setTitle("Shlok 6");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अजोऽपि सन्नव्ययात्मा भूतानामीश्वरोऽपि सन्‌ ।\nप्रकृतिं स्वामधिष्ठाय सम्भवाम्यात्ममायया ॥");
        } else if (i == 0) {
            this.tv1.setText("અજોપિ સન્નવ્યયાત્મા ભૂતાનામીશ્વરોપિ સન્,\nપ્રકૃતિં સ્વામધિષ્ઠાય સંભવામ્યાત્મમાયયા.(૬)");
        } else if (2 == i) {
            this.tv1.setText("Ajo’pi sannavyayaatmaa bhootaanaam eeshwaro’pi san;\nPrakritim swaam adhishthaaya sambhavaamyaatmamaayayaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("मैं अजन्मा और अविनाशीस्वरूप होते हुए भी तथा समस्त प्राणियों का ईश्वर होते हुए भी अपनी प्रकृति को अधीन करके अपनी योगमाया से प्रकट होता हूँ॥6॥");
        } else if (i2 == 0) {
            this.tv2.setText("હું અજન્મા અને અવિનાશી છું. સર્વ ભૂતોનો ઈશ્વર છું. છતાં પ્રકૃતિનો આધાર લઈને પ્રકટ થાઉં છું. (૬)");
        } else if (2 == i2) {
            this.tv2.setText("Although I am unborn and My transcendental body never deteriorates, and although I am the Lord of all living entities, I still appear in every millennium in My original transcendental form. (6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7_8() {
        setTitle("Shlok 7,8");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदा यदा हि धर्मस्य ग्लानिर्भवति भारत ।\nअभ्युत्थानमधर्मस्य तदात्मानं सृजाम्यहम्‌ ॥\nपरित्राणाय साधूनां विनाशाय च दुष्कृताम्‌ ।\nधर्मसंस्थापनार्थाय सम्भवामि युगे युगे ॥");
        } else if (i == 0) {
            this.tv1.setText("યદા યદા હિ ધર્મસ્ય ગ્લાનિર્ભવતિ ભારત,\nઅભ્યુત્થાનમધર્મસ્ય તદાત્માનં સૃજામ્યહમ્.(૭)\nપરિત્રાણાય સાધૂનાં વિનાશાય ચ દુષ્કૃતામ્,\nધર્મસંસ્થાપનાર્થાય સંભવામિ યુગે યુગે.(૮)");
        } else if (2 == i) {
            this.tv1.setText("Yadaa yadaa hi dharmasya glaanir bhavati bhaarata;\nAbhyutthaanam adharmasya tadaatmaanam srijaamyaham.\nParitraanaaya saadhoonaam vinaashaaya cha dushkritaam;\nDharma samsthaapanaarthaaya sambhavaami yuge yuge.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे भारत! जब-जब धर्म की हानि और अधर्म की वृद्धि होती है, तब-तब ही मैं अपने रूप को रचता हूँ अर्थात साकार रूप से लोगों के सम्मुख प्रकट होता हूँ. साधु पुरुषों का उद्धार करने के लिए, पाप कर्म करने वालों का विनाश करने के लिए और धर्म की अच्छी तरह से स्थापना करने के लिए मैं युग-युग में प्रकट हुआ करता हूँ ॥7-8॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ભારત, જ્યારે જ્યારે ધર્મનો નાશ થઈ જાય છે અને અધર્મનો વ્યાપ વધે છે ત્યારે હું અવતાર ધારણ કરું છું. સાધુપુરુષોનું રક્ષણ, દુષ્કર્મીઓનો વિનાશ તથા ધર્મની સંસ્થાપનાના હેતુ માટે યુગે યુગે હું પ્રકટ થાઉં છું.(૭-૮)");
        } else if (2 == i2) {
            this.tv2.setText("Whenever and wherever there is a decline in religious practice, O descendant of Bharata, and a predominant rise of irreligion—at that time I descend Myself. To deliver the pious and to annihilate the miscreants, as well as to reestablish the principles of religion, I Myself appear, millennium after millennium. (7-8)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok9() {
        setTitle("Shlok 9");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("जन्म कर्म च मे दिव्यमेवं यो वेत्ति तत्वतः ।\nत्यक्तवा देहं पुनर्जन्म नैति मामेति सोऽर्जुन ॥");
        } else if (i == 0) {
            this.tv1.setText("જન્મ કર્મ ચ મે દિવ્યમેવં યો વેત્તિ તત્ત્વતઃ,\nત્યક્ત્વા દેહં પુનર્જન્મ નૈતિ મામેતિ સોર્જુન.(૯)");
        } else if (2 == i) {
            this.tv1.setText("Janma karma cha me divyam evam yo vetti tattwatah;\nTyaktwa deham punarjanma naiti maameti so’rjuna.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! मेरे जन्म और कर्म दिव्य अर्थात निर्मल और अलौकिक हैं- इस प्रकार जो मनुष्य तत्व से जान लेता है, वह शरीर को त्याग कर फिर जन्म को प्राप्त नहीं होता, किन्तु मुझे ही प्राप्त होता है॥9॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારા જન્મ અને કર્મ દિવ્ય તથા અલૌકિક છે. જે મનુષ્ય એનો પાર પામી જાય છે એ મૃત્યુ પછી મને પામે છે. એ જન્મ-મરણના ચક્રમાં નથી ફસાતો. (૯)");
        } else if (2 == i2) {
            this.tv2.setText("One who knows the transcendental nature of My appearance and activities does not, upon leaving the body, take his birth again in this material world, but attains My eternal abode, O Arjuna. (9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        setTitle("Shlok 10");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("वीतरागभय क्रोधा मन्मया मामुपाश्रिताः ।\nबहवो ज्ञानतपसा पूता मद्भावमागताः ॥");
        } else if (i == 0) {
            this.tv1.setText("વીતરાગભયક્રોધા મન્મયા મામુપાશ્રિતાઃ,\nબહવો જ્ઞાનતપસા પૂતા મદ્ભાવમાગતાઃ.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Veetaraagabhayakrodhaa manmayaa maam upaashritaah;\nBahavo jnaana tapasaa pootaa madbhaavam aagataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पहले भी, जिनके राग, भय और क्रोध सर्वथा नष्ट हो गए थे और जो मुझ में अनन्य प्रेमपूर्वक स्थित रहते थे, ऐसे मेरे आश्रित रहने वाले बहुत से भक्त उपर्युक्त ज्ञान रूप तप से पवित्र होकर मेरे स्वरूप को प्राप्त हो चुके हैं॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેના રાગ, દ્વેષ, ભય તથા ક્રોધનો નાશ થયો છે અને જે  અનન્યભાવથી મારું ચિંતન કરે છે તે જીવાત્મા તપ અને જ્ઞાનથી પવિત્ર થઈને મારી પાસે પહોંચે છે.(૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("Being freed from attachment, fear and anger, being fully absorbed in Me and taking refuge in Me, many, many persons in the past became purified by knowledge of Me—and thus they all attained transcendental love for Me. (10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        setTitle("Shlok 11");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ये यथा माँ प्रपद्यन्ते तांस्तथैव भजाम्यहम्‌ ।\nमम वर्त्मानुवर्तन्ते मनुष्याः पार्थ सर्वशः ॥");
        } else if (i == 0) {
            this.tv1.setText("યે યથા માં પ્રપદ્યન્તે તાંસ્તથૈવ ભજામ્યહમ્,\nમમ વર્ત્માનુવર્તન્તે મનુષ્યાઃ પાર્થ સર્વશઃ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Ye yathaa maam prapadyante taamstathaiva bhajaamyaham;\nMama vartmaanuvartante manushyaah paartha sarvashah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे अर्जुन! जो भक्त मुझे जिस प्रकार भजते हैं, मैं भी उनको उसी प्रकार भजता हूँ क्योंकि सभी मनुष्य सब प्रकार से मेरे ही मार्ग का अनुसरण करते हैं॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, જે ભક્ત મારું જે પ્રમાણે ચિંતન કરે છે તેને હું તેવી રીતે મળું છું. શ્રેયના જુદા જુદા માર્ગોથી મનુષ્ય મારી પાસે જ આવે છે. (૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("As all surrender unto Me, I reward them accordingly. Everyone follows My path in all respects, O son of Pritha. (11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12() {
        setTitle("Shlok 12");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("काङ्‍क्षन्तः कर्मणां सिद्धिं यजन्त इह देवताः ।\nक्षिप्रं हि मानुषे लोके सिद्धिर्भवति कर्मजा ॥");
        } else if (i == 0) {
            this.tv1.setText("કાઙ્ક્ષન્તઃ કર્મણાં સિદ્ધિં યજન્ત ઇહ દેવતાઃ,\nક્ષિપ્રં હિ માનુષે લોકે સિદ્ધિર્ભવતિ કર્મજા.(૧૨)");
        } else if (2 == i) {
            this.tv1.setText("Kaangkshantah karmanaam siddhim yajanta iha devataah;\nKshipram hi maanushe loke siddhir bhavati karmajaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस मनुष्य लोक में कर्मों के फल को चाहने वाले लोग देवताओं का पूजन किया करते हैं क्योंकि उनको कर्मों से उत्पन्न होने वाली सिद्धि शीघ्र मिल जाती है॥12॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ લોકમાં કર્મફળની કામના રાખનાર દેવોનું પૂજન કરે છે કારણ કે એમ કરવાથી કર્મફળની સિદ્ધિ શીઘ્ર થાય છે. (૧૨)");
        } else if (2 == i2) {
            this.tv2.setText("Men in this world desire success in fruitive activities, and therefore they worship the demigods. Quickly, of course, men get results from fruitive work in this world. (12)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok13() {
        setTitle("Shlok 13");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("चातुर्वर्ण्यं मया सृष्टं गुणकर्मविभागशः ।\nतस्य कर्तारमपि मां विद्धयकर्तारमव्ययम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("ચાતુર્વર્ણ્યં મયા સૃષ્ટં ગુણકર્મવિભાગશઃ,\nતસ્ય કર્તારમપિ માં વિદ્ધ્યકર્તારમવ્યયમ્.(૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Chaaturvarnyam mayaa srishtam gunakarma vibhaagashah;\nTasya kartaaram api maam viddhyakartaaram avyayam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("ब्राह्मण, क्षत्रिय, वैश्य और शूद्र- इन चार वर्णों का समूह, गुण और कर्मों के विभागपूर्वक मेरे द्वारा रचा गया है। इस प्रकार उस सृष्टि-रचनादि कर्म का कर्ता होने पर भी मुझ अविनाशी परमेश्वर को तू वास्तव में अकर्ता ही जान॥13॥");
        } else if (i2 == 0) {
            this.tv2.setText("વર્ણોની રચના (બ્રાહ્મણ, ક્ષત્રિય, વૈશ્ય અને શુદ્ર - એ ચાર) કર્મ તથા ગુણના આધાર પર મેં જ કરેલી છે. એ કર્મોનો હું જ કર્તા છું છતાં મને તું અકર્તા જાણ. (૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("According to the three modes of material nature and the work associated with them, the four divisions of human society are created by Me. And although I am the creator of this system, you should know that I am yet the nondoer, being unchangeable. (13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        setTitle("Shlok 14");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न मां कर्माणि लिम्पन्ति न मे कर्मफले स्पृहा ।\nइति मां योऽभिजानाति कर्मभिर्न स बध्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("ન માં કર્માણિ લિમ્પન્તિ ન મે કર્મફલે સ્પૃહા,\nઇતિ માં યોભિજાનાતિ કર્મભિર્ન સ બધ્યતે.(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Na maam karmaani limpanti na me karmaphale sprihaa;\nIti maam yo’bhijaanaati karmabhir na sa badhyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कर्मों के फल में मेरी स्पृहा नहीं है, इसलिए मुझे कर्म लिप्त नहीं करते- इस प्रकार जो मुझे तत्व से जान लेता है, वह भी कर्मों से नहीं बँधता॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("કારણ કે એ કર્મો મને બાધ્ય કરતા નથી. કેમ કે મને કર્મફળની કોઈ ઈચ્છા નથી. જે મારા રહસ્યને આ પ્રકારે જાણી લે છે તે કર્મના બંધનોથી મુક્ત થઈ જાય છે. (૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("There is no work that affects Me; nor do I aspire for the fruits of action. One who understands this truth about Me also does not become entangled in the fruitive reactions of work. (14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        setTitle("Shlok 15");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एवं ज्ञात्वा कृतं कर्म पूर्वैरपि मुमुक्षुभिः ।\nकुरु कर्मैव तस्मात्वं पूर्वैः पूर्वतरं कृतम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("એવં જ્ઞાત્વા કૃતં કર્મ પૂર્વૈરપિ મુમુક્ષુભિઃ,\nકુરુ કર્મૈવ તસ્માત્ત્વં પૂર્વૈઃ પૂર્વતરં કૃતમ્.(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Evam jnaatwaa kritam karma poorvair api mumukshubhih;\nKuru karmaiva tasmaat twam poorvaih poorvataram kritam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("पूर्वकाल में मुमुक्षुओं ने भी इस प्रकार जानकर ही कर्म किए हैं, इसलिए तू भी पूर्वजों द्वारा सदा से किए जाने वाले कर्मों को ही कर॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("પહેલાંના સમયમાં મુમુક્ષુઓ આ પ્રમાણે કર્મ કરતા હતા. એથી હે અર્જુન, તું પણ એમની માફક કર્મનું અનુષ્ઠાન કર.(૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("All the liberated souls in ancient times acted with this understanding of My transcendental nature. Therefore you should perform your duty, following in their footsteps. (15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        setTitle("Shlok 16");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("किं कर्म किमकर्मेति कवयोऽप्यत्र मोहिताः ।\nतत्ते कर्म प्रवक्ष्यामि यज्ज्ञात्वा मोक्ष्यसेऽशुभात्‌॥");
        } else if (i == 0) {
            this.tv1.setText("કિં કર્મ કિમકર્મેતિ કવયોપ્યત્ર મોહિતાઃ,\nતત્તે કર્મ પ્રવક્ષ્યામિ યજ્જ્ઞાત્વા મોક્ષ્યસેશુભાત્.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Kim karma kim akarmeti kavayo’pyatra mohitaah;\nTat te karma pravakshyaami yajjnaatwaa mokshyase’shubhaat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कर्म क्या है? और अकर्म क्या है? इस प्रकार इसका निर्णय करने में बुद्धिमान पुरुष भी मोहित हो जाते हैं। इसलिए वह कर्मतत्व मैं तुझे भलीभाँति समझाकर कहूँगा, जिसे जानकर तू अशुभ से अर्थात कर्मबंधन से मुक्त हो जाएगा॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મ કોને કહેવાય અને અકર્મ કોને કહેવાય તે નક્કી કરવામાં મોટા મોટા વિદ્વાનો પણ ગોથું ખાઈ જાય છે. હું તને કર્મ વિશે સમજાવું જેથી તું કર્મબંધન અને (યુદ્ધભૂમિમાં અત્યારે તને થયેલ) ક્લેશમાંથી મુક્ત થશે. (૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("Even the intelligent are bewildered in determining what is action and what is inaction. Now I shall explain to you what action is, knowing which you shall be liberated from all misfortune. (16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17() {
        setTitle("Shlok 17");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मणो ह्यपि बोद्धव्यं बोद्धव्यं च विकर्मणः ।\nअकर्मणश्च बोद्धव्यं गहना कर्मणो गतिः ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મણો હ્યપિ બોદ્ધવ્યં બોદ્ધવ્યં ચ વિકર્મણઃ,\nઅકર્મણશ્ચ બોદ્ધવ્યં ગહના કર્મણો ગતિઃ.(૧૭)");
        } else if (2 == i) {
            this.tv1.setText("Karmano hyapi boddhavyam boddhavyam cha vikarmanah;\nAkarmanashcha boddhavyam gahanaa karmano gatih.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कर्म का स्वरूप भी जानना चाहिए और अकर्मण का स्वरूप भी जानना चाहिए तथा विकर्म का स्वरूप भी जानना चाहिए क्योंकि कर्म की गति गहन है॥17॥");
        } else if (i2 == 0) {
            this.tv2.setText("કર્મ, અકર્મ અને વિકર્મ - એ ત્રણેય વિશે જાણવું જરૂરી છે કારણ કે કર્મની ગતિ અતિશય ગહન છે. (૧૭)");
        } else if (2 == i2) {
            this.tv2.setText("The intricacies of action are very hard to understand. Therefore one should know properly what action is, what forbidden action is, and what inaction is. (17)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok18() {
        setTitle("Shlok 18");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कर्मण्य कर्म यः पश्येदकर्मणि च कर्म यः ।\nस बुद्धिमान्मनुष्येषु स युक्तः कृत्स्नकर्मकृत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કર્મણ્યકર્મ યઃ પશ્યેદકર્મણિ ચ કર્મ યઃ,\nસ બુદ્ધિમાન્ મનુષ્યેષુ સ યુક્તઃ કૃત્સ્નકર્મકૃત્.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Karmanyakarma yah pashyed akarmani cha karma yah;\nSa buddhimaan manushyeshu sa yuktah kritsnakarmakrit.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो मनुष्य कर्म में अकर्म देखता है और जो अकर्म में कर्म देखता है, वह मनुष्यों में बुद्धिमान है और वह योगी समस्त कर्मों को करने वाला है॥18॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે મનુષ્ય કર્મમાં અકર્મને જુએ છે તથા અકર્મમાં કર્મનું દર્શન કરે છે તે બુદ્ધિમાન છે. એ જ્ઞાનથી મંડિત થઈને તે પોતાના સર્વ કાર્યો કરે છે. (૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("One who sees inaction in action, and action in inaction, is intelligent among men, and he is in the transcendental position, although engaged in all sorts of activities. (18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        setTitle("Shlok 19");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यस्य सर्वे समारम्भाः कामसंकल्पवर्जिताः ।\nज्ञानाग्निदग्धकर्माणं तमाहुः पंडितं बुधाः ॥");
        } else if (i == 0) {
            this.tv1.setText("યસ્ય સર્વે સમારમ્ભાઃ કામસઙ્કલ્પવર્જિતાઃ,\nજ્ઞાનાગ્નિદગ્ધકર્માણં તમાહુઃ પણ્ડિતં બુધાઃ.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Yasya sarve samaarambhaah kaamasankalpa varjitaah;\nJnaanaagni dagdhakarmaanam tam aahuh panditam budhaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसके सम्पूर्ण शास्त्रसम्मत कर्म बिना कामना और संकल्प के होते हैं तथा जिसके समस्त कर्म ज्ञानरूप अग्नि द्वारा भस्म हो गए हैं, उस महापुरुष को ज्ञानीजन भी पंडित कहते हैं॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેના વડે આરંભાયેલા સર્વ કાર્યો કામનાથી મુક્ત છે તથા જેના બધા કર્મો યજ્ઞરૂપી અગ્નિમાં બળીને ભસ્મ થઈ ગયા છે, તેને જ્ઞાનીઓ પંડિત કહે છે. (૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("One is understood to be in full knowledge whose every endeavor is devoid of desire for sense gratification. He is said by sages to be a worker for whom the reactions of work have been burned up by the fire of perfect knowledge. (19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20() {
        setTitle("Shlok 20");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("त्यक्त्वा कर्मफलासङ्गं नित्यतृप्तो निराश्रयः ।\nकर्मण्यभिप्रवृत्तोऽपि नैव किंचित्करोति सः ॥");
        } else if (i == 0) {
            this.tv1.setText("ત્યક્ત્વા કર્મફલાસઙ્ગં નિત્યતૃપ્તો નિરાશ્રયઃ,\nકર્મણ્યભિપ્રવૃત્તોપિ નૈવ કિઞ્ચિત્કરોતિ સઃ.(૨૦)");
        } else if (2 == i) {
            this.tv1.setText("Tyaktwaa karmaphalaasangam nityatripto niraashrayah;\nKarmanyabhipravritto’pi naiva kinchit karoti sah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो पुरुष समस्त कर्मों में और उनके फल में आसक्ति का सर्वथा त्याग करके संसार के आश्रय से रहित हो गया है और परमात्मा में नित्य तृप्त है, वह कर्मों में भलीभाँति बर्तता हुआ भी वास्तव में कुछ भी नहीं करता॥20॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે પુરુષ કર્મફળની આસક્તિનો સંપૂર્ણ ત્યાગ કરીને પરમ તૃપ્ત અને આશ્રયની આકાંક્ષાથી રહિત હોય છે તે કર્મમાં જોડાયેલો હોવા છતાં એનાથી લેપાયેલો નથી.(૨૦)");
        } else if (2 == i2) {
            this.tv2.setText("Abandoning all attachment to the results of his activities, ever satisfied and independent, he performs no fruitive action, although engaged in all kinds of undertakings. (20)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok21() {
        setTitle("Shlok 21");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("निराशीर्यतचित्तात्मा त्यक्तसर्वपरिग्रहः ।\nशारीरं केवलं कर्म कुर्वन्नाप्नोति किल्बिषम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("નિરાશીર્યતચિત્તાત્મા ત્યક્તસર્વપરિગ્રહઃ,\nશારીરં કેવલં કર્મ કુર્વન્નાપ્નોતિ કિલ્બિષમ્.(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Niraasheer yatachittaatmaa tyaktasarvaparigrahah;\nShaareeram kevalam karma kurvannaapnoti kilbisham.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसका अंतःकरण और इन्द्रियों सहित शरीर जीता हुआ है और जिसने समस्त भोगों की सामग्री का परित्याग कर दिया है, ऐसा आशारहित पुरुष केवल शरीर-संबंधी कर्म करता हुआ भी पापों को नहीं प्राप्त होता॥21॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે તૃષ્ણારહિત થઈને, પોતાના મન અને ઈન્દ્રિયોનો કાબૂ કરી કેવળ શરીરનિર્વાહને માટે જ કર્મો કરે છે તે પાપથી લેપાતો નથી. (૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("Such a man of understanding acts with mind and intelligence perfectly controlled, gives up all sense of proprietorship over his possessions, and acts only for the bare necessities of life. Thus working, he is not affected by sinful reactions. (21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22() {
        setTitle("Shlok 22");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदृच्छालाभसंतुष्टो द्वंद्वातीतो विमत्सरः ।\nसमः सिद्धावसिद्धौ च कृत्वापि न निबध्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("યદૃચ્છાલાભસન્તુષ્ટો દ્વન્દ્વાતીતો વિમત્સરઃ,\nસમઃ સિદ્ધાવસિદ્ધૌ ચ કૃત્વાપિ ન નિબધ્યતે.(૨૨)");
        } else if (2 == i) {
            this.tv1.setText("Yadricchaalaabhasantushto dwandwaateeto vimatsarah;\nSamah siddhaavasiddhau cha kritwaapi na nibadhyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जो बिना इच्छा के अपने-आप प्राप्त हुए पदार्थ में सदा संतुष्ट रहता है, जिसमें ईर्ष्या का सर्वथा अभाव हो गया हो, जो हर्ष-शोक आदि द्वंद्वों से सर्वथा अतीत हो गया है- ऐसा सिद्धि और असिद्धि में सम रहने वाला कर्मयोगी कर्म करता हुआ भी उनसे नहीं बँधता॥22॥");
        } else if (i2 == 0) {
            this.tv2.setText("કોઈ ઈચ્છા કર્યા વગર સહજ રીતે જે મળે તેમાં સંતુષ્ઠ રહેનાર, ઈર્ષાથી પર, સુખદુઃખાદિ દ્વંદ્વોથી મુક્ત, તથા વિજય કે હાનિમાં સમતા રાખનાર મનુષ્ય કર્મ કરવા છતાં તેમાં બંધાતો નથી. (૨૨)");
        } else if (2 == i2) {
            this.tv2.setText("He who is satisfied with gain which comes of its own accord, who is free from duality and does not envy, who is steady in both success and failure, is never entangled, although performing actions. (22)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok23() {
        setTitle("Shlok 23");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("गतसङ्‍गस्य मुक्तस्य ज्ञानावस्थितचेतसः ।\nयज्ञायाचरतः कर्म समग्रं प्रविलीयते ॥");
        } else if (i == 0) {
            this.tv1.setText("ગતસઙ્ગસ્ય મુક્તસ્ય જ્ઞાનાવસ્થિતચેતસઃ,\nયજ્ઞાયાચરતઃ કર્મ સમગ્રં પ્રવિલીયતે.(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Gatasangasya muktasya jnaanaavasthitachetasah;\nYajnaayaacharatah karma samagram pravileeyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसकी आसक्ति सर्वथा नष्ट हो गई है, जो देहाभिमान और ममता से रहित हो गया है, जिसका चित्त निरन्तर परमात्मा के ज्ञान में स्थित रहता है- ऐसा केवल यज्ञसम्पादन के लिए कर्म करने वाले मनुष्य के सम्पूर्ण कर्म भलीभाँति विलीन हो जाते हैं॥23॥");
        } else if (i2 == 0) {
            this.tv2.setText("જે અનાસક્ત રહીને પરમાત્માનું ચિંતન કરતાં યજ્ઞભાવથી બધા કર્મો કરે છે, તેના બધા જ કર્મો નાશ પામે છે.(૨૩)");
        } else if (2 == i2) {
            this.tv2.setText(" To one who is devoid of attachment, who is liberated, whose mind is established in knowledge, who works for the sake of sacrifice (for the sake of God), the whole action is dissolved.(23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24() {
        setTitle("Shlok 24");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ब्रह्मार्पणं ब्रह्म हविर्ब्रह्माग्रौ ब्रह्मणा हुतम्‌ ।\nब्रह्मैव तेन गन्तव्यं ब्रह्मकर्मसमाधिना ॥");
        } else if (i == 0) {
            this.tv1.setText("બ્રહ્માર્પણં બ્રહ્મહવિર્બ્રહ્માગ્નૌ બ્રહ્મણા હુતમ્,\nબ્રહ્મૈવ તેન ગન્તવ્યં બ્રહ્મકર્મસમાધિના.(૨૪)");
        } else if (2 == i) {
            this.tv1.setText("Brahmaarpanam brahmahavirbrahmaagnau brahmanaa hutam;\nBrahmaiva tena gantavyam brahmakarmasamaadhinaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिस यज्ञ में अर्पण अर्थात स्रुवा आदि भी ब्रह्म है और हवन किए जाने योग्य द्रव्य भी ब्रह्म है तथा ब्रह्मरूप कर्ता द्वारा ब्रह्मरूप अग्नि में आहुति देना रूप क्रिया भी ब्रह्म है- उस ब्रह्मकर्म में स्थित रहने वाले योगी द्वारा प्राप्त किए जाने योग्य फल भी ब्रह्म ही हैं॥24॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેમ કે યજ્ઞમાં અર્પણ કરાતી વસ્તુ બ્રહ્મ છે, અર્પણ કરવાનું સાધન બ્રહ્મ છે, જેને એ અર્પણ કરવામાં આવે છે તે બ્રહ્મ છે તથા જે અર્પણ કરનાર છે તે પણ બ્રહ્મ છે. જે આ રીતે કર્મ કરતી વખતે બ્રહ્મમાં સ્થિત હોય તે યોગી બ્રહ્મને પ્રાપ્ત કરી લે છે. (૨૪)");
        } else if (2 == i2) {
            this.tv2.setText("Brahman is the oblation; Brahman is the melted butter (ghee); by Brahman is the oblation poured into the fire of Brahman; Brahman verily shall be reached by him who always sees Brahman in action.(24)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok25() {
        setTitle("Shlok 25");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("दैवमेवापरे यज्ञं योगिनः पर्युपासते ।\nब्रह्माग्नावपरे यज्ञं यज्ञेनैवोपजुह्वति ॥");
        } else if (i == 0) {
            this.tv1.setText("દૈવમેવાપરે યજ્ઞં યોગિનઃ પર્યુપાસતે,\nબ્રહ્માગ્નાવપરે યજ્ઞં યજ્ઞેનૈવોપજુહ્વતિ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Daivam evaapare yajnam yoginah paryupaasate;\nBrahmaagnaavapare yajnam yajnenaivopajuhwati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("दूसरे योगीजन देवताओं के पूजनरूप यज्ञ का ही भलीभाँति अनुष्ठान किया करते हैं और अन्य योगीजन परब्रह्म परमात्मारूप अग्नि में अभेद दर्शनरूप यज्ञ द्वारा ही आत्मरूप यज्ञ का हवन किया करते हैं। ॥25॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેટલાક યોગીઓ યજ્ઞ વડે દેવતાઓને પૂજે છે, જ્યારે કેટલાક જ્ઞાનીઓ, (જ્ઞાનરૂપી યજ્ઞ માં ) બ્રહ્માગ્નિના અગ્નિમાં પોતાના આત્માની આહૂતિ આપે છે.(૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Some Yogis perform sacrifice to the gods alone, while others (who have realised the Self) offer the Self as sacrifice by the Self in the fire of Brahman alone.(25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26() {
        setTitle("Shlok 26");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रोत्रादीनीन्द्रियाण्यन्ये संयमाग्निषु जुह्वति।\nशब्दादीन्विषयानन्य इन्द्रियाग्निषु जुह्वति ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રોત્રાદીનીન્દ્રિયાણ્યન્યે સંયમાગ્નિષુ જુહ્વતિ,\nશબ્દાદીન્વિષયાનન્ય ઇન્દ્રિયાગ્નિષુ જુહ્વતિ.(૨૬)");
        } else if (2 == i) {
            this.tv1.setText("Shrotraadeeneendriyaanyanye samyamaagnishu juhwati;\nShabdaadeen vishayaananya indriyaagnishu juhwati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("अन्य योगीजन श्रोत्र आदि समस्त इन्द्रियों को संयम रूप अग्नियों में हवन किया करते हैं और दूसरे योगी लोग शब्दादि समस्त विषयों को इन्द्रिय रूप अग्नियों में हवन किया करते हैं॥26॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેટલાક પોતાની શ્રવણેન્દ્રિને સંયમના અગ્નિમાં હોમે છે, કેટલાક શબ્દાદિ વિષયોને ઈન્દ્રિયરૂપી અગ્નિમાં હોમે છે, (૨૬)");
        } else if (2 == i2) {
            this.tv2.setText("Some [the unadulterated brahmacaris] sacrifice the hearing process and the senses in the fire of mental control, and others [the regulated householders] sacrifice the objects of the senses in the fire of the senses. (26)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok27() {
        setTitle("Shlok 27");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("सर्वाणीन्द्रियकर्माणि प्राणकर्माणि चापरे ।\nआत्मसंयमयोगाग्नौ जुह्वति ज्ञानदीपिते ॥");
        } else if (i == 0) {
            this.tv1.setText("સર્વાણીન્દ્રિયકર્માણિ પ્રાણકર્માણિ ચાપરે,\nઆત્મસંયમયોગાગ્નૌ જુહ્વતિ જ્ઞાનદીપિતે.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Sarvaaneendriya karmaani praanakarmaani chaapare;\nAatmasamyamayogaagnau juhwati jnaanadeepite.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" दूसरे योगीजन इन्द्रियों की सम्पूर्ण क्रियाओं और प्राणों की समस्त क्रियाओं को ज्ञान से प्रकाशित आत्म संयम योगरूप अग्नि में हवन किया करते हैं (सच्चिदानंदघन परमात्मा के सिवाय अन्य किसी का भी न चिन्तन करना ही उन सबका हवन करना है।)॥27॥");
        } else if (i2 == 0) {
            this.tv2.setText("તો વળી કેટલાક ઈન્દ્રિયો તથા પ્રાણની સમસ્ત ક્રિયાઓને આત્મસંયમરૂપી યોગાગ્નિમાં હોમે છે. (૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("Others, who are interested in achieving self-realization through control of the mind and senses, offer the functions of all the senses, and of the life breath, as oblations into the fire of the controlled mind. (27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28() {
        setTitle("Shlok 28");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("द्रव्ययज्ञास्तपोयज्ञा योगयज्ञास्तथापरे ।\nस्वाध्यायज्ञानयज्ञाश्च यतयः संशितव्रताः ॥");
        } else if (i == 0) {
            this.tv1.setText("દ્રવ્યયજ્ઞાસ્તપોયજ્ઞા યોગયજ્ઞાસ્તથાપરે,\nસ્વાધ્યાયજ્ઞાનયજ્ઞાશ્ચ યતયઃ સંશિતવ્રતાઃ.(૨૮)");
        } else if (2 == i) {
            this.tv1.setText("Dravyayajnaas tapoyajnaa yogayajnaastathaapare;\nSwaadhyaayajnaana yajnaashcha yatayah samshitavrataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कई पुरुष द्रव्य संबंधी यज्ञ करने वाले हैं, कितने ही तपस्या रूप यज्ञ करने वाले हैं तथा दूसरे कितने ही योगरूप यज्ञ करने वाले हैं, कितने ही अहिंसादि तीक्ष्णव्रतों से युक्त यत्नशील पुरुष स्वाध्यायरूप ज्ञानयज्ञ करने वाले हैं॥28॥");
        } else if (i2 == 0) {
            this.tv2.setText("કોઈ આ રીતે દ્રવ્યયજ્ઞ કરે છે, કોઈ તપ યજ્ઞ કરે છે, કોઈ કર્મ દ્વારા યજ્ઞ કરે છે તો કોઈ નિયમવ્રતોનું પાલન કરીને સ્વાધ્યાય દ્વારા જ્ઞાનયજ્ઞ કરે છે. (૨૮)");
        } else if (2 == i2) {
            this.tv2.setText("Some again offer wealth, austerity and Yoga as sacrifice, while the ascetics of self-restraint and rigid vows offer study of scriptures and knowledge as sacrifice.(28)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok29_30() {
        setTitle("Shlok 29,30");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अपाने जुह्वति प्राणं प्राणेऽपानं तथापरे ।\nप्राणापानगती रुद्ध्वा प्राणायामपरायणाः ॥\nअपरे नियताहाराः प्राणान्प्राणेषु जुह्वति ।\nसर्वेऽप्येते यज्ञविदो यज्ञक्षपितकल्मषाः ॥");
        } else if (i == 0) {
            this.tv1.setText("અપાને જુહ્વતિ પ્રાણ પ્રાણેપાનં તથાપરે,\nપ્રાણાપાનગતી રુદ્ધ્વા પ્રાણાયામપરાયણાઃ.(૨૯)\nઅપરે નિયતાહારાઃ પ્રાણાન્પ્રાણેષુ જુહ્વતિ,\nસર્વેપ્યેતે યજ્ઞવિદો યજ્ઞક્ષપિતકલ્મષાઃ.(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Apaane juhwati praanam praane’paanam tathaa’pare;\nPraanaapaana gatee ruddhwaa praanaayaamaparaayanaah.\nApare niyataahaaraah praanaan praaneshu juhwati;\nSarve’pyete yajnavido yajnakshapita kalmashaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" दूसरे कितने ही योगीजन अपान वायु में प्राणवायु को हवन करते हैं, वैसे ही अन्य योगीजन प्राणवायु में अपान वायु को हवन करते हैं तथा अन्य कितने ही नियमित आहार (गीता अध्याय 6 श्लोक 17 में देखना चाहिए।) करने वाले प्राणायाम परायण पुरुष प्राण और अपान की गति को रोककर प्राणों को प्राणों में ही हवन किया करते हैं। ये सभी साधक यज्ञों द्वारा पापों का नाश कर देने वाले और यज्ञों को जानने वाले हैं॥29-30॥");
        } else if (i2 == 0) {
            this.tv2.setText("કેટલાક યોગીજન અપાનવાયુમાં પ્રાણને હોમે છે જ્યારે કેટલાક પ્રાણમાં અપાનવાયુને હોમે છે. કેટલાક પ્રાણ અને અપાનની ગતિને કાબૂમાં કરી પ્રાણાયામ કરે છે. કેટલાક આહાર પર કાબૂ કરી પોતાના બધા જ પ્રાણને પ્રાણમાં હોમે છે. આ રીતે સાધક પોતપોતાની રીતે પાપોનો નાશ કરવા યજ્ઞનું અનુષ્ઠાન કરે છે. (૨૯-૩૦)");
        } else if (2 == i2) {
            this.tv2.setText("Others offer as sacrifice the outgoing breath in the incoming, and the incoming in the outgoing, restraining the courses of the outgoing and the incoming breaths, solely absorbed in the restraint of the breath. Others who regulate their diet offer life-breaths in life-breaths; all these are knowers of sacrifice, whose sins are all destroyed by sacrifice(29-30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31() {
        setTitle("Shlok 31");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यज्ञशिष्टामृतभुजो यान्ति ब्रह्म सनातनम्‌ ।\nनायं लोकोऽस्त्ययज्ञस्य कुतोऽन्यः कुरुसत्तम ॥");
        } else if (i == 0) {
            this.tv1.setText("યજ્ઞશિષ્ટામૃતભુજો યાન્તિ બ્રહ્મ સનાતનમ્,\nનાયં લોકોસ્ત્યયજ્ઞસ્ય કુતોન્યઃ કુરુસત્તમ.(૩૧)");
        } else if (2 == i) {
            this.tv1.setText("Yajnashishtaamritabhujo yaanti brahma sanaatanam;\nNaayam loko’styayajnasya kuto’nyah kurusattama.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे कुरुश्रेष्ठ अर्जुन! यज्ञ से बचे हुए अमृत का अनुभव करने वाले योगीजन सनातन परब्रह्म परमात्मा को प्राप्त होते हैं। और यज्ञ न करने वाले पुरुष के लिए तो यह मनुष्यलोक भी सुखदायक नहीं है, फिर परलोक कैसे सुखदायक हो सकता है?॥31॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, યજ્ઞશિષ્ઠ અન્ન ખાનારને સનાતન બ્રહ્મની પ્રાપ્તિ થાય છે. જે એ પ્રમાણે યજ્ઞનું અનુષ્ઠાન નથી કરતા તેમને માટે આ મૃત્યુલોક સુખકારક નથી થતો. તો પછી પરલોક તો સુખદાયી ક્યાંથી થાય ? (૩૧)");
        } else if (2 == i2) {
            this.tv2.setText(" Those who eat the remnants of the sacrifice, which are like nectar, go to the eternal Brahman. This world is not for the man who does not perform sacrifice; how then can he have the other, O Arjuna?(31)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok32() {
        setTitle("Shlok 32");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एवं बहुविधा यज्ञा वितता ब्रह्मणो मुखे ।\nकर्मजान्विद्धि तान्सर्वानेवं ज्ञात्वा विमोक्ष्यसे ॥");
        } else if (i == 0) {
            this.tv1.setText("એવં બહુવિધા યજ્ઞા વિતતા બ્રહ્મણો મુખે,\nકર્મજાન્વિદ્ધિ તાન્સર્વાનેવં જ્ઞાત્વા વિમોક્ષ્યસે.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Evam bahuvidhaa yajnaa vitataa brahmano mukhe;\nKarmajaan viddhi taan sarvaan evam jnaatwaa vimokshyase.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसी प्रकार और भी बहुत तरह के यज्ञ वेद की वाणी में विस्तार से कहे गए हैं। उन सबको तू मन, इन्द्रिय और शरीर की क्रिया द्वारा सम्पन्न होने वाले जान, इस प्रकार तत्व से जानकर उनके अनुष्ठान द्वारा तू कर्म बंधन से सर्वथा मुक्त हो जाएगा॥32॥");
        } else if (i2 == 0) {
            this.tv2.setText("વેદમાં બ્રહ્મા દ્વારા આવા અનેક યજ્ઞોનું વર્ણન કરવામાં આવ્યું છે. આ સર્વે યજ્ઞો મન, ઈન્દ્રિય અને શરીર દ્વારા ફળની ઈચ્છાથી કરવામાં આવે છે. એ પ્રમાણે જાણવાથી તું કર્મબંધનથી મુક્ત થઈશ. (૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("Thus, various kinds of sacrifices are spread out before Brahman (literally at the mouth or face of Brahman). Know them all as born of action, and knowing thus, thou shalt be liberated.(32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33() {
        setTitle("Shlok 33");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रेयान्द्रव्यमयाद्यज्ञाज्ज्ञानयज्ञः परन्तप ।\nसर्वं कर्माखिलं पार्थ ज्ञाने परिसमाप्यते ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રેયાન્દ્રવ્યમયાદ્યજ્ઞાજ્જ્ઞાનયજ્ઞઃ પરન્તપ,\nસર્વં કર્માખિલં પાર્થ જ્ઞાને પરિસમાપ્યતે.(૩૩)");
        } else if (2 == i) {
            this.tv1.setText("Shreyaan dravyamayaadyajnaaj jnaanayajnah parantapa;\nSarvam karmaakhilam paartha jnaane parisamaapyate.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे परंतप अर्जुन! द्रव्यमय यज्ञ की अपेक्षा ज्ञान यज्ञ अत्यन्त श्रेष्ठ है तथा यावन्मात्र सम्पूर्ण कर्म ज्ञान में समाप्त हो जाते हैं॥33॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે અર્જુન, દ્રવ્યયજ્ઞની તુલનામાં જ્ઞાનયજ્ઞ શ્રેષ્ઠ છે. કેમ કે પૂર્ણ જ્ઞાનમાં બધા જ કર્મો સમાઈ જાય છે. (૩૩)");
        } else if (2 == i2) {
            this.tv2.setText("Superior is wisdom-sacrifice to sacrifice with objects, O Parantapa! All actions in their entirety, O Arjuna, culminate in knowledge!(33)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok34() {
        setTitle("Shlok 34");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तद्विद्धि प्रणिपातेन परिप्रश्नेन सेवया ।\nउपदेक्ष्यन्ति ते ज्ञानं ज्ञानिनस्तत्वदर्शिनः ॥");
        } else if (i == 0) {
            this.tv1.setText("તદ્વિદ્ધિ પ્રણિપાતેન પરિપ્રશ્નેન સેવયા,\nઉપદેક્ષ્યન્તિ તે જ્ઞાનં જ્ઞાનિનસ્તત્ત્વદર્શિનઃ.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Tadviddhi pranipaatena pariprashnena sevayaa;\nUpadekshyanti te jnaanam jnaaninas tattwadarshinah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("उस ज्ञान को तू तत्वदर्शी ज्ञानियों के पास जाकर समझ, उनको भलीभाँति दण्डवत्‌ प्रणाम करने से, उनकी सेवा करने से और कपट छोड़कर सरलतापूर्वक प्रश्न करने से वे परमात्म तत्व को भलीभाँति जानने वाले ज्ञानी महात्मा तुझे उस तत्वज्ञान का उपदेश करेंगे॥34॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ સત્યને બરાબર જાણી ચુકેલ જ્ઞાની પુરુષને તું પ્રણામ કરી, વાર્તાલાપ દ્વારા કે સેવાથી પ્રસન્ન કર. તે તને જ્ઞાન પ્રદાન કરશે. (૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("Know that by long prostration, by question and by service, the wise who have realised the Truth will instruct thee in (that) knowledge.(34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        setTitle("Shlok 35");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यज्ज्ञात्वा न पुनर्मोहमेवं यास्यसि पाण्डव ।\nयेन भुतान्यशेषेण द्रक्ष्यस्यात्मन्यथो मयि ॥");
        } else if (i == 0) {
            this.tv1.setText("યજ્જ્ઞાત્વા ન પુનર્મોહમેવં યાસ્યસિ પાણ્ડવ,\nયેન ભૂતાન્યશેષેણ દ્રક્ષ્યસ્યાત્મન્યથો મયિ.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Yajjnaatwaa na punarmoham evam yaasyasi paandava;\nYena bhootaanyasheshena drakshyasyaatmanyatho mayi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जिसको जानकर फिर तू इस प्रकार मोह को नहीं प्राप्त होगा तथा हे अर्जुन! जिस ज्ञान द्वारा तू सम्पूर्ण भूतों को निःशेषभाव से पहले अपने में और पीछे मुझ सच्चिदानन्दघन परमात्मा में देखेगा।॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે પાંડવ, આ રીતે જ્ઞાન પામ્યા પછી તને મોહ નહીં થાય અને તું તારા પોતામાં તથા અન્ય જીવોમાં મને (પરમાત્માને) નિહાળી શકીશ.(૩૫)");
        } else if (2 == i2) {
            this.tv2.setText("Knowing that, thou shalt not, O Arjuna, again become deluded like this; and by that thou shalt see all beings in thy Self and also in Me!(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36() {
        setTitle("Shlok 36");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अपि चेदसि पापेभ्यः सर्वेभ्यः पापकृत्तमः ।\nसर्वं ज्ञानप्लवेनैव वृजिनं सन्तरिष्यसि ॥");
        } else if (i == 0) {
            this.tv1.setText("અપિ ચેદસિ પાપેભ્યઃ સર્વેભ્યઃ પાપકૃત્તમઃ,\nસર્વં જ્ઞાનપ્લવેનૈવ વૃજિનં સન્તરિષ્યસિ.(૩૬)");
        } else if (2 == i) {
            this.tv1.setText("Api chedasi paapebhyah sarvebhyah paapakrittamah;\nSarvam jnaanaplavenaiva vrijinam santarishyasi.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यदि तू अन्य सब पापियों से भी अधिक पाप करने वाला है, तो भी तू ज्ञान रूप नौका द्वारा निःसंदेह सम्पूर्ण पाप-समुद्र से भलीभाँति तर जाएगा॥36॥");
        } else if (i2 == 0) {
            this.tv2.setText("જો તું અધમાધમ પાપી હોઈશ તો પણ જ્ઞાન રૂપી નાવમાં બેસીને પાપના સમુદ્રને પાર કરી જઈશ.(૩૬)");
        } else if (2 == i2) {
            this.tv2.setText("Even if thou art the most sinful of all sinners, yet thou shalt verily cross all sins by the raft of knowledge.(36)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok37() {
        setTitle("Shlok 37");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यथैधांसि समिद्धोऽग्निर्भस्मसात्कुरुतेऽर्जुन ।\nज्ञानाग्निः सर्वकर्माणि भस्मसात्कुरुते तथा ॥");
        } else if (i == 0) {
            this.tv1.setText("યથૈધાંસિ સમિદ્ધોગ્નિર્ભસ્મસાત્કુરુતેર્જુન,\nજ્ઞાનાગ્નિઃ સર્વકર્માણિ ભસ્મસાત્કુરુતે તથા.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Yathaidhaamsi samiddho’gnir bhasmasaat kurute’rjuna;\nJnaanaagnih sarvakarmaani bhasmasaat kurute tathaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("क्योंकि हे अर्जुन! जैसे प्रज्वलित अग्नि ईंधनों को भस्ममय कर देता है, वैसे ही ज्ञानरूप अग्नि सम्पूर्ण कर्मों को भस्ममय कर देता है॥37॥");
        } else if (i2 == 0) {
            this.tv2.setText("જેવી રીતે પ્રજ્વલિત થયેલ અગ્નિ કાષ્ઠને બાળી નાખે છે તેવી રીતે જ્ઞાનનો અગ્નિ બધા કર્મોને ભસ્મ કરી નાખે છે. (૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("As the blazing fire reduces fuel to ashes, O Arjuna, so does the fire of knowledge reduce all actions to ashes!(37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38() {
        setTitle("Shlok 38");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("न हि ज्ञानेन सदृशं पवित्रमिह विद्यते ।\nतत्स्वयं योगसंसिद्धः कालेनात्मनि विन्दति ॥");
        } else if (i == 0) {
            this.tv1.setText("ન હિ જ્ઞાનેન સદૃશં પવિત્રમિહ વિદ્યતે,\nતત્સ્વયં યોગસંસિદ્ધઃ કાલેનાત્મનિ વિન્દતિ.(૩૮)");
        } else if (2 == i) {
            this.tv1.setText("Na hi jnaanena sadrisham pavitram iha vidyate;\nTat swayam yogasamsiddhah kaalenaatmani vindati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस संसार में ज्ञान के समान पवित्र करने वाला निःसंदेह कुछ भी नहीं है। उस ज्ञान को कितने ही काल से कर्मयोग द्वारा शुद्धान्तःकरण हुआ मनुष्य अपने-आप ही आत्मा में पा लेता है॥38॥");
        } else if (i2 == 0) {
            this.tv2.setText("જ્ઞાનથી અધિક પવિત્ર આ સંસારમાં બીજું કશું જ નથી. યોગમાં સિદ્ધ થયેલ પુરુષ આ જ્ઞાનને પ્રાપ્ત કરે છે.(૩૮)");
        } else if (2 == i2) {
            this.tv2.setText("Verily there is no purifier in this world like knowledge. He who is perfected in Yoga finds it in the Self in time.(38)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok39() {
        setTitle("Shlok 39");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("श्रद्धावाँल्लभते ज्ञानं तत्परः संयतेन्द्रियः ।\nज्ञानं लब्धवा परां शान्तिमचिरेणाधिगच्छति ॥");
        } else if (i == 0) {
            this.tv1.setText("શ્રદ્ધાવા્લભતે જ્ઞાનં તત્પરઃ સંયતેન્દ્રિયઃ,\nજ્ઞાનં લબ્ધ્વા પરાં શાન્તિમચિરેણાધિગચ્છતિ.(૩૯)");
        } else if (2 == i) {
            this.tv1.setText("Shraddhaavaan labhate jnaanam tatparah samyatendriyah;\nJnaanam labdhvaa paraam shaantim achirenaadhigacchati.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("जितेन्द्रिय, साधनपरायण और श्रद्धावान मनुष्य ज्ञान को प्राप्त होता है तथा ज्ञान को प्राप्त होकर वह बिना विलम्ब के- तत्काल ही भगवत्प्राप्तिरूप परम शान्ति को प्राप्त हो जाता है॥39॥");
        } else if (i2 == 0) {
            this.tv2.setText("શ્રદ્ધાવાન અને જિતેન્દ્રિય મનુષ્ય જ્ઞાન (સત્ય-પરમ-જ્ઞાન) ને પ્રાપ્ત કરે છે, અને આ જ્ઞાન થી તે તરત જ શાંતિ પ્રાપ્ત કરે છે.(૩૯)");
        } else if (2 == i2) {
            this.tv2.setText("The man who is full of faith, who is devoted to it, and who has subdued all the senses, obtains (this) knowledge; and, having obtained the knowledge, he goes at once to the supreme peace.(39)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok40() {
        setTitle("Shlok 40");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अज्ञश्चश्रद्दधानश्च संशयात्मा विनश्यति ।\nनायं लोकोऽस्ति न परो न सुखं संशयात्मनः ॥");
        } else if (i == 0) {
            this.tv1.setText("અજ્ઞશ્ચાશ્રદ્દધાનશ્ચ સંશયાત્મા વિનશ્યતિ,\nનાયં લોકોસ્તિ ન પરો ન સુખં સંશયાત્મનઃ.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Ajnashchaashraddhadhaanashcha samshayaatmaa vinashyati;\nNaayam loko’sti na paro na sukham samshayaatmanah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("विवेकहीन और श्रद्धारहित संशययुक्त मनुष्य परमार्थ से अवश्य भ्रष्ट हो जाता है। ऐसे संशययुक्त मनुष्य के लिए न यह लोक है, न परलोक है और न सुख ही है॥40॥");
        } else if (i2 == 0) {
            this.tv2.setText("જ્યારે આત્મજ્ઞાન વિનાનો, શ્રદ્ધાહીન તથા સંશયી મનુષ્ય એ જ્ઞાન મેળવી શકતો નથી અને વિનાશ પામે છે. તેવા મનુષ્યને આ લોક કે પરલોકમાં ક્યાંય સુખ મળતું નથી.(૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("The ignorant, the faithless, the doubting self proceeds to destruction; there is neither this world nor the other nor happiness for the doubting.(40");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        setTitle("Shlok 41");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("योगसन्नयस्तकर्माणं ज्ञानसञ्न्निसंशयम्‌ ।\nआत्मवन्तं न कर्माणि निबध्नन्ति धनञ्जय ॥");
        } else if (i == 0) {
            this.tv1.setText("યોગસંન્યસ્તકર્માણં જ્ઞાનસંછિન્નસંશયમ્,\nઆત્મવન્તં ન કર્માણિ નિબધ્નન્તિ ધનઞ્જય.(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Yogasannyasta karmaanam jnaanasamcchinnasamshayam;\nAatmavantam na karmaani nibadhnanti dhananjaya.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे धनंजय! जिसने कर्मयोग की विधि से समस्त कर्मों का परमात्मा में अर्पण कर दिया है और जिसने विवेक द्वारा समस्त संशयों का नाश कर दिया है, ऐसे वश में किए हुए अन्तःकरण वाले पुरुष को कर्म नहीं बाँधते॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ધનંજય, જેણે યોગ દ્વારા પોતાના સમસ્ત કર્મોનો ત્યાગ કર્યો છે અને જ્ઞાન વડે જેણે પોતાના સંશયો છેદી નાખ્યા છે તેવા આત્મનિષ્ઠ પુરુષને કર્મ બંધનકર્તા નથી થતું. (૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("He who has renounced actions by Yoga, whose doubts are rent asunder by knowledge, and who is self-possessed,—actions do not bind him, O Arjuna!(41)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42() {
        setTitle("Shlok 42");
        load();
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्मादज्ञानसम्भूतं हृत्स्थं ज्ञानासिनात्मनः ।\nछित्वैनं संशयं योगमातिष्ठोत्तिष्ठ भारत ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્માદજ્ઞાનસંભૂતં હૃત્સ્થં જ્ઞાનાસિનાત્મનઃ,\nછિત્ત્વૈનં સંશયં યોગમાતિષ્ઠોત્તિષ્ઠ ભારત.(૪૨)");
        } else if (2 == i) {
            this.tv1.setText("Tasmaad ajnaanasambhootam hritstham jnaanaasinaatmanah;\nCchittwainam samshayam yogam aatishthottishtha bhaarata.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसलिए हे भरतवंशी अर्जुन! तू हृदय में स्थित इस अज्ञानजनित अपने संशय का विवेकज्ञान रूप तलवार द्वारा छेदन करके समत्वरूप कर्मयोग में स्थित हो जा और युद्ध के लिए खड़ा हो जा॥42॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી હે ભારત, તારા હૃદયને જેણે શોકથી હણી નાખ્યું છે એવા અજ્ઞાનથી પેદા થયેલ સંશયને તું જ્ઞાનરૂપી શસ્ત્રથી છેદી નાખ અને યોગમાં સ્થિત થઈ યુદ્ધ માટે તૈયાર થઈ જા.(૪૨)");
        } else if (2 == i2) {
            this.tv2.setText("Therefore, with the sword of knowledge (of the Self) cut asunder the doubt of the self born of ignorance, residing in thy heart, and take refuge in Yoga; arise, O Arjuna!(42)");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay4.this, ShlokListPlay4.this);
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

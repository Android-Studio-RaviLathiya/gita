package com.gita.bhagwatgita;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ShlokListPlay1 extends AppCompatActivity implements TextToSpeech.OnInitListener {

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
    private NotificationManager myNotificationManager;
    Typeface myfonts;
    Typeface myfonts1;
    private int notificationIdOne = 111;
    private int numMessagesOne = 0;
    LinearLayout rellay1;
    TextView tv1;
    TextView tv2;
    Button btn_slock, btn_slocktras, btn_slock_silent, btn_slocktras_silent;
    TextToSpeech tts;



    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.shlok_list_play);

        this.adView = (AdView) findViewById(R.id.adView);
        btn_slock = findViewById(R.id.btn_slock);
        btn_slocktras = findViewById(R.id.btn_slocktras);
        btn_slock_silent = findViewById(R.id.btn_slock_silent);
        btn_slocktras_silent = findViewById(R.id.btn_slocktras_silent);

        this.adView.loadAd(new AdRequest.Builder().build());
        Intent intent = getIntent();
        this.calll = intent.getExtras().getInt("pos");
        this.calllang = intent.getExtras().getInt("callf");
        int i = this.calllang;
        this.callf1 = i;
        this.callf2 = i;
        this.tv1 = (TextView) findViewById(R.id.tv1);
        this.tv2 = (TextView) findViewById(R.id.tv2);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton7);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imageButton6);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButton8);
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
                if (ShlokListPlay1.this.favflag == 0) {
                    ShlokListPlay1.this.imgfav.setBackgroundResource(R.drawable.favoritestars);
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.favflag = 1;
                    shlokListPlay1.save();
                } else if (1 == ShlokListPlay1.this.favflag) {
                    ShlokListPlay1.this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
                    ShlokListPlay1 shlokListPlay12 = ShlokListPlay1.this;
                    shlokListPlay12.favflag = 0;
                    shlokListPlay12.save();
                }
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ShlokListPlay1.this.calll++;
                ShlokListPlay1.this.slkset();
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
                ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                shlokListPlay1.calll--;
                ShlokListPlay1.this.slkset();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(ShlokListPlay1.this, ShlokListPlay1.this);
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


    public void save() {
        SharedPreferences.Editor edit = getSharedPreferences("Favorite", 0).edit();
        int i = this.calll;
        String valueOf = String.valueOf(this.favflag);
        edit.putString("A1" + i, valueOf);
        edit.commit();
    }

    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("Favorite", 0);
        this.favflagsave = sharedPreferences.getString("A1" + this.calll, "0");
        this.favflag = Integer.parseInt(this.favflagsave);
        int i = this.favflag;
        if (i == 0) {
            this.imgfav.setBackgroundResource(R.drawable.fvoritestaroutline);
        } else if (1 == i) {
            this.imgfav.setBackgroundResource(R.drawable.favoritestars);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        tts.stop();
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
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.callf1 = 0;
                    shlokListPlay1.slkset();
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
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.callf1 = 2;
                    shlokListPlay1.slkset();
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
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.callf1 = 1;
                    shlokListPlay1.slkset();
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
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.callf2 = 0;
                    shlokListPlay1.slkset();
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
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.callf2 = 2;
                    shlokListPlay1.slkset();
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
                    ShlokListPlay1 shlokListPlay1 = ShlokListPlay1.this;
                    shlokListPlay1.callf2 = 1;
                    shlokListPlay1.slkset();
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
            shlok4_5_6();
        } else if (5 == i3) {
            shlok7_8_9();
        } else if (6 == i3) {
            shlok10();
        } else if (7 == i3) {
            shlok11();
        } else if (8 == i3) {
            shlok12_13();
        } else if (9 == i3) {
            shlok14();
        } else if (10 == i3) {
            shlok15();
        } else if (11 == i3) {
            shlok16();
        } else if (12 == i3) {
            shlok17_18();
        } else if (13 == i3) {
            shlok19();
        } else if (14 == i3) {
            shlok20_21();
        } else if (15 == i3) {
            shlok22_23();
        } else if (16 == i3) {
            shlok24_25();
        } else if (17 == i3) {
            shlok26_27();
        } else if (18 == i3) {
            shlok28_29_30();
        } else if (19 == i3) {
            shlok31_32();
        } else if (20 == i3) {
            shlok33_34();
        } else if (21 == i3) {
            shlok35();
        } else if (22 == i3) {
            shlok36_37();
        } else if (23 == i3) {
            shlok38_39_40();
        } else if (24 == i3) {
            shlok41();
        } else if (25 == i3) {
            shlok42_43_44_45();
        } else if (26 == i3) {
            shlok46();
        } else if (27 == i3) {
            shlok47();
        } else if (28 == i3) {
            shlokEND();
        }


    }


    private void shlokEND() {
        this.rellay1.setVisibility(4);
        this.tv2.setVisibility(4);
        this.imgfav.setVisibility(4);
        setTitle("Adhyay 1");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अध्याय ०१ \nअर्जुनविषादयोग \nसमाप्त");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય ૦૧ \nઅર્જુન વિષાદ યોગ \nસમાપ્ત\n");
        } else if (2 == i) {
            this.tv1.setText("Adhyay 01\n Arjun vishad yog \nThe End");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok0() {
        load();
        setTitle("Introduction");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पहले अधयाय में शक्तिशाली योद्धा अर्जुन युद्धाभिमुख विपक्षी सेनाओं में अपने निकट सम्बधियों, शिक्षकों तथा मित्रो को युद्ध में अपना-अपना जीवन उत्सर्ग करने क लिए उधत देखता है। वह शोक तथा करुणा से अभिभूत होकर अपनी शक्ति खो देता है उसका मन मोहग्रस्त हो जाता है और वह युद्ध करने के अपने संकल्प को त्याग देता है।");
        } else if (i == 0) {
            this.tv1.setText("અધ્યાય-૧ માં ગીતા ની પ્રસ્તાવના છે. કૌરવોએ પાંડવોનો રાજ્યભાગ નો અધિકાર ના મંજુર કર્યો, કૃષ્ણ ની સમજાવટ પણ નિષ્ફળ રહી અને યુદ્ધ ના મંડાણ થયાં. રણ ભૂમિ ની વચ્ચે રથમાં અર્જુન સામા પક્ષમાં સગાં, મિત્રો અને ગુરૂ ને જોઈ શોક-વિષાદ માં આવી જઈ, યુદ્ધ નહી કરવાનો નિશ્ચય કરે છે.");
        } else if (2 == i) {
            this.tv1.setText("In First adhyay, As the opposing armies stand poised for battle, Arjuna, the mighty warrior, sees his intimate relatives, teachers and friends in both armies ready to fight and sacrifice their lives. Overcome by grief and pity, Arjuna fails in strength, his mind becomes bewildered, and he gives up his determination to fight.");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok1() {
        load();
        setTitle("Shlok 1");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("धृतराष्ट्र उवाच\nधर्मक्षेत्रे कुरुक्षेत्रे समवेता युयुत्सवः ।\nमामकाः पाण्डवाश्चैव किमकुर्वत संजय ॥");
        } else if (i == 0) {
            this.tv1.setText("ધૃતરાષ્ટ્ર ઉવાચ-\nધર્મક્ષેત્રે કુરુક્ષેત્રે સમવેતા યુયુત્સવઃ\nમામકાઃ પાણ્ડવાશ્ચૈવ કિમકુર્વત સંજય (૧)");
        } else if (2 == i) {
            this.tv1.setText("Dhritaraashtra Uvaacha:\nDharmakshetre kurukshetre samavetaa yuyutsavah;\nMaamakaah paandavaashchaiva kim akurvata sanjaya");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("धृतराष्ट्र बोले-\nहे संजय! धर्मभूमि कुरुक्षेत्र में एकत्रित, युद्ध की इच्छावाले मेरे और पाण्डु के पुत्रों ने क्या किया?॥1॥");
        } else if (i2 == 0) {
            this.tv2.setText("ધૃતરાષ્ટ્ર બોલ્યા-\nહે સંજય, ધર્મભૂમિ કુરુક્ષેત્રમાં યુદ્ધની ઇચ્છાથી એકત્ર થયેલા મારા અને પાંડુના પુત્રોએ શું કર્યું ? (૧)");
        } else if (2 == i2) {
            this.tv2.setText("Dhritarashtra said - \nO Sanjaya, after my sons and the sons of Pandu assembled in the place of pilgrimage at Kurukshetra, desiring to fight, what did they do?(1)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok2() {
        load();
        setTitle("Shlok 2");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nदृष्टवा तु पाण्डवानीकं व्यूढं दुर्योधनस्तदा ।\nआचार्यमुपसंगम्य राजा वचनमब्रवीत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજય ઉવાચ-\nદૃષ્ટ્વા તુ પાણ્ડવાનીકં વ્યૂઢં દુર્યોધનસ્તદા,\nઆચાર્યમુપસંગ્મ્ય રાજા વચનમબ્રવીત્ (૨)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nDrishtwaa tu paandavaaneekam vyudham duryodhanastadaa;\nAachaaryam upasamgamya raajaa vachanam abraveet");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("संजय बोले- \nउस समय राजा दुर्योधन ने व्यूहरचनायुक्त पाण्डवों की सेना को देखा और द्रोणाचार्य के पास जाकर यह वचन कहा॥2॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય બોલ્યા - \nહે રાજન, પાંડવોની સેનાને જોઇને રાજા દુર્યોધન દ્રોણાચાર્યની પાસે જઇને બોલ્યા. (૨)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said: O King, after looking over the army arranged in military formation by the sons of Pandu, King Duryodhana went to his teacher and spoke the following words.(2)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok3() {
        load();
        setTitle("Shlok 3");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पश्यैतां पाण्डुपुत्राणामाचार्य महतीं चमूम्‌ ।\nव्यूढां द्रुपदपुत्रेण तव शिष्येण धीमता ॥");
        } else if (i == 0) {
            this.tv1.setText("પશ્યૈતાં પાણ્ડુપુત્રાણામાચાર્ય મહતીં ચમૂમ્, \nવ્યૂઢાં દ્રુપદપુત્રેણ તવ શિષ્યેણ ધીમત।.(૩)");
        } else if (2 == i) {
            this.tv1.setText("Pashyaitaam paanduputraanaam aachaarya mahateem chamoom;\nVyoodhaam drupadaputrena tava shishyena dheemataa");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे आचार्य! आपके बुद्धिमान्‌ शिष्य द्रुपदपुत्र धृष्टद्युम्न द्वारा व्यूहाकार खड़ी की हुई पाण्डुपुत्रों की इस बड़ी भारी सेना को देखिए॥3॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે આચાર્ય, આપના શિષ્ય દ્રુપદપુત્ર ધૃષ્ટદ્યુમ્ન દ્વારા ગોઠવાયેલી પાંડવોની આ વિશાળ સેનાને જુઓ. (૩)");
        } else if (2 == i2) {
            this.tv2.setText("O my teacher, behold the great army of the sons of Pandu, so expertly arranged by your intelligent disciple the son of Drupada.(3)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok4_5_6() {
        load();
        setTitle("Shlok 4,5,6");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अत्र शूरा महेष्वासा भीमार्जुनसमा युधि ।\nयुयुधानो विराटश्च द्रुपदश्च महारथः ॥\nधृष्टकेतुश्चेकितानः काशिराजश्च वीर्यवान्‌ ।\nपुरुजित्कुन्तिभोजश्च शैब्यश्च नरपुङवः ॥ \nयुधामन्युश्च विक्रान्त उत्तमौजाश्च वीर्यवान्‌ ।\nसौभद्रो द्रौपदेयाश्च सर्व एव महारथाः ॥");
        } else if (i == 0) {
            this.tv1.setText("અત્ર શૂરા મહેષ્વાસા ભીમાર્જુનસમા યુધિ,\nયુયુધાનો વિરાટશ્ચ દ્રુપદશ્ચ મહારથઃ\nધૃષ્ટકેતુશ્ચેકિતાનઃ કાશિરાજશ્ચ વીર્યવાન્,\nપુરુજિત્કુન્તિભોજશ્ચ શૈબ્યશ્ચ નરપુન્ગવઃ\nયુધામન્યુશ્ચ વિક્રાન્ત ઉત્તમૌજાશ્ચ વીર્યવાન્,\nસૌભદ્રો દ્રૌપદેયાશ્ચ સર્વ એવ મહારથાઃ(૪-૫-૬)");
        } else if (2 == i) {
            this.tv1.setText("Atra shooraa maheshwaasaa bheemaarjunasamaa yudhi;\nYuyudhaano viraatashcha drupadashcha mahaarathah.\nDhrishtaketush chekitaanah kaashiraajashcha veeryavaan;\nPurujit kuntibhojashcha shaibyashcha narapungavah.\nYudhaamanyushcha vikraanta uttamaujaashcha veeryavaan;\nSaubhadro draupadeyaashcha sarva eva mahaarathaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इस सेना में बड़े-बड़े धनुषों वाले तथा युद्ध में भीम और अर्जुन के समान शूरवीर सात्यकि और विराट तथा महारथी राजा द्रुपद, धृष्टकेतु और चेकितान तथा बलवान काशिराज, पुरुजित, कुन्तिभोज और मनुष्यों में श्रेष्ठ शैब्य, पराक्रमी युधामन्यु तथा बलवान उत्तमौजा, सुभद्रापुत्र अभिमन्यु एवं द्रौपदी के पाँचों पुत्र- ये सभी महारथी हैं॥4-5-6॥");
        } else if (i2 == 0) {
            this.tv2.setText("એમાં ભીમ અને અર્જુનના સમાન યુયુધાન (સાત્યકિ), રાજા વિરાટ,  મહારાજા દ્રુપદ, ધૃષ્ટકેતુ, ચેકિતાન, કાશીરાજ, પુરુજિત, કુંતીભોજ તથા નરશ્રેષ્ઠ શૈબ્ય જેવા કેટલાય પરાક્રમી શૂરવીર યોદ્ધાઓ છે.પાંડવોની સેનામાં વિક્રાન્ત, યુધામન્યુ, વીર્યવાન ઉત્તમૌજા, સુભદ્રાપુત્ર અભિમન્યુ \nતથા દ્રૌપદીના પુત્રો - એ બધા મહારથીઓનો સમાવેશ થાય છે. (૪-૫-૬)");
        } else if (2 == i2) {
            this.tv2.setText("Here in this army are many heroic bowmen equal in fighting to Bhima and Arjuna: great fighters like Yuyudhana, Virata and Drupada.There are also great, heroic, powerful fighters like Dhrishtaketu, Cekitana, Kasiraja, Purujit, Kuntibhoja and Saibya.There are the mighty Yudhamanyu, the very powerful Uttamauja, the son of Subhadra and the sons of Draupadi. All these warriors are great chariot fighters.(4-5-6)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok7_8_9() {
        load();
        setTitle("Shlok 7,8,9");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अस्माकं तु विशिष्टा ये तान्निबोध द्विजोत्तम ।\nनायका मम सैन्यस्य सञ्ज्ञार्थं तान्ब्रवीमि ते ॥\nभवान्भीष्मश्च कर्णश्च कृपश्च समितिञ्जयः ।\nअश्वत्थामा विकर्णश्च सौमदत्तिस्तथैव च ॥\nअन्ये च बहवः शूरा मदर्थे त्यक्तजीविताः ।\nनानाशस्त्रप्रहरणाः सर्वे युद्धविशारदाः ॥");
        } else if (i == 0) {
            this.tv1.setText("અસ્માકં તુ વિશિષ્ટા યે તાન્નિબોધ દ્વિજોત્તમ,\nનાયકા મમ સૈન્યસ્ય સંજ્ઞાર્થં તાન્બ્રવીમિ તે.(૭)\nભવાન્ભીષ્મશ્ચ કર્ણશ્ચ કૃપશ્ચ સમિતિજ્જયઃ,\nઅશ્વત્થાત્મા વિકર્ણશ્ચ સૌમદત્તિસ્તથૈવ ચ.(૮)\nઅન્યે ચ બહવઃ શૂરા મદર્થે ત્યક્તજીવિતાઃ,\nનાનાશસ્ત્રપ્રહરણાઃ સર્વે યુધ્દવિશારદાઃ(૯)");
        } else if (2 == i) {
            this.tv1.setText("Asmaakam tu vishishtaa ye taan nibodha dwijottama;\nNaayakaah mama sainyasya samjnaartham taan braveemi te.\nBhavaan bheeshmashcha karnashcha kripashcha samitinjayah;\nAshwatthaamaa vikarnashcha saumadattis tathaiva cha.\nAnye cha bahavah shooraa madarthe tyaktajeevitaah;\nNaanaashastrapraharanaah sarve yuddhavishaaradaah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे ब्राह्मणश्रेष्ठ! अपने पक्ष में भी जो प्रधान हैं, उनको आप समझ लीजिए। आपकी जानकारी के लिए मेरी सेना के जो-जो सेनापति हैं, उनको बतलाता हूँ. आप-द्रोणाचार्य और पितामह भीष्म तथा कर्ण और संग्रामविजयी कृपाचार्य तथा वैसे ही अश्वत्थामा, विकर्ण और सोमदत्त का पुत्र भूरिश्रवा और भी मेरे लिए जीवन की आशा त्याग देने वाले बहुत-से शूरवीर अनेक प्रकार के शस्त्रास्त्रों से सुसज्जित और सब-के-सब युद्ध में चतुर हैं.॥7-8-9॥");
        } else if (i2 == 0) {
            this.tv2.setText("હવે હે દ્વિજોત્તમ, આપણી સેનાના યોદ્ધાઓ વિશે હું તમને કહું. આપણી સેનામાં તમારા ઉપરાંત પિતામહ ભીષ્મ, કર્ણ, કૃપાચાર્ય, અશ્વત્થામા, વિકર્ણ અને સૌમદત્ત જેવા મહાન યોદ્ધાઓ છે. એમના સિવાય આપણી સેનામાં યુદ્ધકળામાં નિપુણ હોય, શસ્ત્રાસ્ત્રવિદ્યામાં માહિર હોય એવા અનેક યોદ્ધાઓ છે, જેઓ મારે માટે પોતાના જાનની બાજી લગાવવા તૈયાર છે. (૭-૮-૯)");
        } else if (2 == i2) {
            this.tv2.setText("But for your information, O best of the brahmanas, let me tell you about the captains who are especially qualified to lead my military force.There are personalities like you, Bhishma, Karna, Kripa, Asvatthama, Vikarna and the son of Somadatta called Bhurisrava, who are always victorious in battle.There are many other heroes who are prepared to lay down their lives for my sake. All of them are well equipped with different kinds of weapons, and all are experienced in military science.(7-8-9)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok10() {
        load();
        setTitle("Shlok 10");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अपर्याप्तं तदस्माकं बलं भीष्माभिरक्षितम्‌ ।\nपर्याप्तं त्विदमेतेषां बलं भीमाभिरक्षितम्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("અપર્યાપ્તં તદસ્માકં બલં ભીષ્માભિરક્ષિતમ્,\nપર્યાપ્તં ત્વિદમેતેષાં બલં ભીમાભિરક્ષિતમ્.(૧૦)");
        } else if (2 == i) {
            this.tv1.setText("Aparyaaptam tad asmaakam balam bheeshmaabhirakshitam;\nParyaaptam twidam eteshaam balam bheemaabhirakshitam.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("भीष्म पितामह द्वारा रक्षित हमारी वह सेना सब प्रकार से अजेय है और भीम द्वारा रक्षित इन लोगों की यह सेना जीतने में सुगम है॥10॥");
        } else if (i2 == 0) {
            this.tv2.setText("પિતામહ ભીષ્મ દ્વારા રક્ષાયેલ આપણી સેનાનું બળ અસીમ અને અખૂટ છે,જયારે આપણી સાથેની તુલનામાં, ભીમ દ્વારા રક્ષાયેલી પાંડવોની સેનાનું બળ સીમિત છે. (૧૦)");
        } else if (2 == i2) {
            this.tv2.setText("Our strength is immeasurable, and we are perfectly protected by Grandfather Bhishma, whereas the strength of the Pandavas, carefully protected by Bhima, is limited.(10)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok11() {
        load();
        setTitle("Shlok 11");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अयनेषु च सर्वेषु यथाभागमवस्थिताः ।\nभीष्ममेवाभिरक्षन्तु भवन्तः सर्व एव हि ॥");
        } else if (i == 0) {
            this.tv1.setText("અયનેષુ ચ સર્વેષુ યથાભાગમવસ્થિતા:,\nભીષ્મમેવાભિરક્ષન્તુ ભવન્તઃ સર્વ એવ હિ.(૧૧)");
        } else if (2 == i) {
            this.tv1.setText("Ayaneshu cha sarveshu yathaabhaagam avasthitaah;\nBheeshmam evaabhirakshantu bhavantah sarva eva hi");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" इसलिए सब मोर्चों पर अपनी-अपनी जगह स्थित रहते हुए आप लोग सभी निःसंदेह भीष्म पितामह की ही सब ओर से रक्षा करें॥11॥");
        } else if (i2 == 0) {
            this.tv2.setText("એથી સર્વ યોદ્ધાઓ, પોતપોતાના નિયુક્ત કરેલ સ્થાન પર રહી સર્વ પ્રકારે આપણા સેનાપતિ એવા પિતામહ ભીષ્મની રક્ષા કરો. (૧૧)");
        } else if (2 == i2) {
            this.tv2.setText("All of you must now give full support to Grandfather Bhishma, as you stand at your respective strategic points of entrance into the phalanx of the army.(11)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok12_13() {
        load();
        setTitle("Shlok 12,13");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तस्य सञ्जनयन्हर्षं कुरुवृद्धः पितामहः ।\nसिंहनादं विनद्योच्चैः शंख दध्मो प्रतापवान्‌ ॥\nततः शंखाश्च भेर्यश्च पणवानकगोमुखाः ।\nसहसैवाभ्यहन्यन्त स शब्दस्तुमुलोऽभवत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("તસ્ય સંજનયન્હર્ષં કુરુવૃદ્ધઃ પિતામહઃ,\nસિંહનાદં વિનદ્યોચ્ચૈઃ શઙ્ખં દધ્મૌ પ્રતાપવાન્ (૧૨)\nતતઃ શઙ્ખાશ્ચભેર્યશ્ચ પણવાનકગોમુખાઃ,\nસહસૈવાભ્યહન્યન્ત સ શબ્દસ્તુમુલોભવત્ (૧૩)");
        } else if (2 == i) {
            this.tv1.setText("Tasya sanjanayan harsham kuruvriddhah pitaamahah;\nSimhanaadam vinadyocchaih shankham dadhmau prataapavaan.\nTatah shankhaashcha bheryashcha panavaanakagomukhaah;\nSahasaivaabhyahanyanta sa shabdastumulo’bhavat.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("कौरवों में वृद्ध बड़े प्रतापी पितामह भीष्म ने उस दुर्योधन के हृदय में हर्ष उत्पन्न करते हुए उच्च स्वर से सिंह की दहाड़ के समान गरजकर शंख बजाया. इसके पश्चात शंख और नगाड़े तथा ढोल, मृदंग और नरसिंघे आदि बाजे एक साथ ही बज उठे। उनका वह शब्द बड़ा भयंकर हुआ.॥12-13॥");
        } else if (i2 == 0) {
            this.tv2.setText("તે સમયે વરિષ્ટ કુરુ એવા પિતામહ ભીષ્મે જોરથી સિંહનાદ કર્યો અને શંખનાદ કર્યો, જેથી દુર્યોધનના હૃદયમાં હર્ષની લાગણી થઈ. તે પછી અનેક મહારથીઓએ પોતાના શંખ, નગારા, ઢોલ વગેરે વગાડ્યા. (૧૨-૧૩)");
        } else if (2 == i2) {
            this.tv2.setText("Then Bhishma, the great valiant grandsire of the Kuru dynasty, the grandfather of the fighters, blew his conchshell very loudly, making a sound like the roar of a lion, giving Duryodhana joy. After that, the conchshells, drums, bugles, trumpets and horns were all suddenly sounded, and the combined sound was tumultuous.(12-13)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok14() {
        load();
        setTitle("Shlok 14");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("ततः श्वेतैर्हयैर्युक्ते महति स्यन्दने स्थितौ ।\nमाधवः पाण्डवश्चैव दिव्यौ शंखौ प्रदध्मतुः ॥");
        } else if (i == 0) {
            this.tv1.setText("તતઃ શ્વેતૈર્હયૈર્યુક્તે મહતિ સ્યન્દને સ્થિતો,\nમાધવઃ પાણ્ડવશ્ચૈવ દિવ્યૌ શઙ્ખૌ પ્રદધ્મતુઃ(૧૪)");
        } else if (2 == i) {
            this.tv1.setText("Tatah shvetair hayair yukte mahati syandane sthitau;\nMaadhavah paandavashchaiva divyau shankhau pradadhmatuh");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसके अनन्तर सफेद घोड़ों से युक्त उत्तम रथ में बैठे हुए श्रीकृष्ण महाराज और अर्जुन ने भी अलौकिक शंख बजाए॥14॥");
        } else if (i2 == 0) {
            this.tv2.setText("એ બધાના સ્વરોથી વાતાવરણમાં ભયાનક નાદ થયો. એ સમયે સફેદ ઘોડાઓથી શોભતા ભવ્ય રથમાં વિરાજમાન ભગવાન શ્રીકૃષ્ણ અને પાંડુપુત્ર અર્જુને પોતપોતાના શંખ વગાડ્યા. (૧૪)");
        } else if (2 == i2) {
            this.tv2.setText("On the other side, both Lord Krishna and Arjuna, stationed on a great chariot drawn by white horses, sounded their transcendental conchshells.(14)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok15() {
        load();
        setTitle("Shlok 15");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("पाञ्चजन्यं हृषीकेशो देवदत्तं धनञ्जयः ।\nपौण्ड्रं दध्मौ महाशंख भीमकर्मा वृकोदरः ॥");
        } else if (i == 0) {
            this.tv1.setText("પાંચ જન્યં હૃષીકેશો દેવદત્તં ધનંજયઃ,\nપૌણ્ડ્રં દધ્મૌ મહાશઙ્ખં ભીમકર્મા વૃકોદરઃ(૧૫)");
        } else if (2 == i) {
            this.tv1.setText("Paanchajanyam hrisheekesho devadattam dhananjayah;\nPaundram dadhmau mahaashankham bheemakarmaa vrikodarah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" श्रीकृष्ण महाराज ने पाञ्चजन्य नामक, अर्जुन ने देवदत्त नामक और भयानक कर्मवाले भीमसेन ने पौण्ड्र नामक महाशंख बजाया॥15॥");
        } else if (i2 == 0) {
            this.tv2.setText("ભગવાન ઋષિકેશે (શ્રી કૃષ્ણે) પાંચજન્ય શંખ વગાડ્યો અને ધનંજય (અર્જુને) દેવદત્ત શંખ વગાડ્યો. ભીમે પોતાનો પૌડ્રક નામના શંખનો ધ્વનિ કર્યો.(શંખ વગાડ્યો)  (૧૫)");
        } else if (2 == i2) {
            this.tv2.setText("Lord Krishna blew His conchshell, called Pancajanya; Arjuna blew his, the Devadatta; and Bhima, the voracious eater and performer of herculean tasks, blew his terrific conchshell, called Paundra.(15)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok16() {
        load();
        setTitle("Shlok 16");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अनन्तविजयं राजा कुन्तीपुत्रो युधिष्ठिरः ।\nनकुलः सहदेवश्च सुघोषमणिपुष्पकौ ॥");
        } else if (i == 0) {
            this.tv1.setText("અનન્તવિજયં રાજા કુન્તીપુત્રો યુધિષ્ઠિરઃ,\nનકુલઃ સહદેવશ્ચ સુઘોષમણિપુષ્પકૌ.(૧૬)");
        } else if (2 == i) {
            this.tv1.setText("Anantavijayam raajaa kunteeputro yudhishthirah;\nNakulah sahadevashcha sughoshamanipushpakau.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" कुन्तीपुत्र राजा युधिष्ठिर ने अनन्तविजय नामक और नकुल तथा सहदेव ने सुघोष और मणिपुष्पक नामक शंख बजाए॥16॥");
        } else if (i2 == 0) {
            this.tv2.setText("કુંતીપુત્ર મહારાજા યુધિષ્ઠિરે પોતાના અનંતવિજય નામના શંખનો, નકુલે સુઘોષ અને સહદેવે મણિપુષ્પક નામના શંખનો ધ્વનિ કર્યો. (શંખ વગાડ્યો)  (૧૬)");
        } else if (2 == i2) {
            this.tv2.setText("King Yudhishthira, the son of Kunti, blew his conchshell, the Ananta-vijaya, and Nakula and Sahadeva blew the Sughosa and Manipuspaka.(16)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok17_18() {
        load();
        setTitle("Shlok 17,18");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("काश्यश्च परमेष्वासः शिखण्डी च महारथः ।\nधृष्टद्युम्नो विराटश्च सात्यकिश्चापराजितः ॥\nद्रुपदो द्रौपदेयाश्च सर्वशः पृथिवीपते ।\nसौभद्रश्च महाबाहुः शंखान्दध्मुः पृथक्पृथक्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("કાશ્યશ્ચ પરમેષ્વાસઃ શિખણ્ડી ચ મહારથઃ,\nધૃષ્ટદ્યુમ્નો વિરાટશ્ચ સાત્યકિશ્ચાપરાજિતઃ.(૧૭)\nદ્રુપદો દ્રૌપદેયાશ્ચ સર્વશઃ પૃથિવીપતે,\nસૌભદ્રશ્ચ મહાબાહુઃ શઙ્ખાન્દધ્મુઃ પૃથક્પૃથક્.(૧૮)");
        } else if (2 == i) {
            this.tv1.setText("Kaashyashcha parameshwaasah shikhandee cha mahaarathah;\nDhrishtadyumno viraatashcha saatyakishchaaparaajitah.\nDrupado draupadeyaashcha sarvashah prithiveepate;\nSaubhadrashcha mahaabaahuh shankhaan dadhmuh prithak prithak.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("श्रेष्ठ धनुष वाले काशिराज और महारथी शिखण्डी एवं धृष्टद्युम्न तथा राजा विराट और अजेय सात्यकि, राजा द्रुपद एवं द्रौपदी के पाँचों पुत्र और बड़ी भुजावाले सुभद्रा पुत्र अभिमन्यु- इन सभी ने, हे राजन्‌! सब ओर से अलग-अलग शंख बजाए॥17-18॥");
        } else if (i2 == 0) {
            this.tv2.setText("ધનુર્ધર કાશિરાજ, મહારથી શિખંડી, ધૃષ્ટદ્યુમ્ન્ય, વિરાટરાજ, અજેય એવા સાત્યકિ, મહારાજા દ્રુપદ, અભિમન્યુ તથા દ્રૌપદીના અન્ય પુત્રોએ પોતપોતાના શંખોનો ધ્વનિ કર્યો. (૧૭-૧૮)");
        } else if (2 == i2) {
            this.tv2.setText("That great archer the King of Kasi, the great fighter Sikhandi, Dhrishtadyumna, Virata, the unconquerable Satyaki, Drupada, the sons of Draupadi, and the others, O King, such as the mighty-armed son of Subhadra, all blew their respective conchshells.(17-18)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok19() {
        load();
        setTitle("Shlok 19");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("स घोषो धार्तराष्ट्राणां हृदयानि व्यदारयत्‌ ।\nनभश्च पृथिवीं चैव तुमुलो व्यनुनादयन्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("સ ઘોષો ધાર્તરાષ્ટ્રાણાં હૃદયાનિ વ્યદારયત્,\nનભશ્ચ પૃથિવીં ચૈવ તુમુલો વ્યનુનાદયન્.(૧૯)");
        } else if (2 == i) {
            this.tv1.setText("Sa ghosho dhaartaraashtraanaam hridayaani vyadaarayat;\nNabhashcha prithiveem chaiva tumulo vyanunaadayan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और उस भयानक शब्द ने आकाश और पृथ्वी को भी गुंजाते हुए धार्तराष्ट्रों के अर्थात आपके पक्षवालों के हृदय विदीर्ण कर दिए॥19॥");
        } else if (i2 == 0) {
            this.tv2.setText("શંખોના મહાધ્વનિથી આકાશ અને ધરા પર મોટો શોર થયો. એ સાંભળીને ધૃતરાષ્ટ્રના પુત્રોના (કૌરવોના) હૃદયમાં જાણે હલચલ થઈ. (૧૯)");
        } else if (2 == i2) {
            this.tv2.setText("The blowing of these different conchshells became uproarious. Vibrating both in the sky and on the earth, it shattered the hearts of the sons of Dhritarashtra.(19)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok20_21() {
        load();
        setTitle("Shlok 20,21");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अथ व्यवस्थितान्दृष्ट्वा धार्तराष्ट्रान्‌ कपिध्वजः ।\nप्रवृत्ते शस्त्रसम्पाते धनुरुद्यम्य पाण्डवः ॥ \nहृषीकेशं तदा वाक्यमिदमाह महीपते ।\nअर्जुन उवाचः\nसेनयोरुभयोर्मध्ये रथं स्थापय मेऽच्युत ॥");
        } else if (i == 0) {
            this.tv1.setText("અથ વ્યવસ્થિતાન્ દૃષ્ટ્વા ધાર્તરાષ્ટ્રાન્કપિધ્વજઃ,\nપ્રવૃત્તે શસ્ત્રસંપાતે ધનુરુદ્યમ્ય પાણ્ડવઃ(૨૦)\nહૃષીકેશં તદા વાક્યમિદમાહ મહીપતે,\nઅર્જુનઉવાચ :\nસેનયોરુભયોર્મધ્યે રથં સ્થાપય મેચ્યુત(૨૧)");
        } else if (2 == i) {
            this.tv1.setText("Atha vyavasthitaan drishtwaa dhaartaraashtraan kapidhwajah;\nPravritte shastrasampaate dhanurudyamya paandavah.\nHrisheekesham tadaa vaakyamidamaaha maheepate;\nArjuna Uvaacha:\nSenayor ubhayormadhye ratham sthaapaya me’chyuta.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे राजन्‌! इसके बाद कपिध्वज अर्जुन ने मोर्चा बाँधकर डटे हुए धृतराष्ट्र-संबंधियों को देखकर, उस शस्त्र चलने की तैयारी के समय धनुष उठाकर हृषीकेश श्रीकृष्ण महाराज से यह वचन कहा- हे अच्युत! मेरे रथ को दोनों सेनाओं के बीच में खड़ा कीजिए॥20-21॥");
        } else if (i2 == 0) {
            this.tv2.setText("અર્જુને, કે જેના રથ પર હનુમાનજી વિરાજમાન હતા, તેણે પોતાનું ગાંડિવ (ધનુષ્ય) તૈયાર કરી ભગવાન ઋષિકેશને કહ્યું. હે અચ્યુત (હે કૃષ્ણ), મારો રથ બંને સેનાઓની મધ્યમાં લઈ ચાલો જેથી હું બંને પક્ષના યોદ્ધાઓને સારી પેઠે જોઈ શકું. (૨૦-૨૧)");
        } else if (2 == i2) {
            this.tv2.setText("At that time Arjuna, the son of Pandu, seated in the chariot bearing the flag marked with Hanuman, took up his bow and prepared to shoot his arrows. O King, after looking at the sons of Dhritarashtra drawn in military array, Arjuna then spoke to Lord Krishna these words.Arjuna said: O infallible one, please draw my chariot between the two armies so that I may see those present here, who desire to fight, and with whom I must contend in this great trial of arms.(20-21)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok22_23() {
        load();
        setTitle("Shlok 22,23");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यावदेतान्निरीक्षेऽहं योद्धुकामानवस्थितान्‌ ।\nकैर्मया सह योद्धव्यमस्मिन् रणसमुद्यमे ॥\nयोत्स्यमानानवेक्षेऽहं य एतेऽत्र समागताः ।\nधार्तराष्ट्रस्य दुर्बुद्धेर्युद्धे प्रियचिकीर्षवः ॥\n");
        } else if (i == 0) {
            this.tv1.setText("યાવદેતાન્નિરીક્ષેહં યોધ્દુકામાનવસ્થિતાન્,\nકૈર્મયા સહ યોધ્દવ્યમસ્મિન્રણસમુદ્યમે.(૨૨)\nયોત્સ્યમાનાનવેક્ષેહં ય એતેત્ર સમાગતાઃ,\nધાર્તરાષ્ટ્રસ્ય દુર્બુદ્ધેર્યુદ્ધે પ્રિયચિકીર્ષવઃ(૨૩)");
        } else if (2 == i) {
            this.tv1.setText("Yaavad etaan nireekshe’ham yoddhukaamaan avasthitaan;\nKair mayaa saha yoddhavyam asmin ranasamudyame.\nYotsyamaanaan avekshe’ham ya ete’tra samaagataah;\nDhaartaraashtrasya durbuddher yuddhe priyachikeershavah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("और जब तक कि मैं युद्ध क्षेत्र में डटे हुए युद्ध के अभिलाषी इन विपक्षी योद्धाओं को भली प्रकार देख न लूँ कि इस युद्ध रूप व्यापार में मुझे किन-किन के साथ युद्ध करना योग्य है, तब तक उसे खड़ा रखिए. दुर्बुद्धि दुर्योधन का युद्ध में हित चाहने वाले जो-जो ये राजा लोग इस सेना में आए हैं, इन युद्ध करने वालों को मैं देखूँगा॥22-23॥");
        } else if (i2 == 0) {
            this.tv2.setText("મારે જોવું છે કે દુર્બુદ્ધિથી ભરેલ દુર્યોધનનો સાથ આપવા માટે યુદ્ધભૂમિમાં કયા કયા યોદ્ધાઓ ભેગા થયા છે. અને કોની સાથે મારે યુદ્ધ કરવાનું છે? (૨૨-૨૩)");
        } else if (2 == i2) {
            this.tv2.setText("Let me see those who have come here to fight, wishing to please the evil-minded son of Dhritarashtra.(22-23)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok24_25() {
        load();
        setTitle("Shlok 24,25");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nएवमुक्तो हृषीकेशो गुडाकेशेन भारत ।\nसेनयोरुभयोर्मध्ये स्थापयित्वा रथोत्तमम्‌ ॥\nभीष्मद्रोणप्रमुखतः सर्वेषां च महीक्षिताम्‌ ।\nउवाच पार्थ पश्यैतान्‌ समवेतान्‌ कुरूनिति ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજયઉવાચ - \nએવમુક્તો હૃષીકેશો ગુડાકેશેન ભારત,\nસેનયોરુભયોર્મધ્યે સ્થાપયિત્વા રથોત્તમમ્.(૨૪)\nભીષ્મદ્રોણપ્રમુખતઃ સર્વેષાં ચ મહીક્ષિતામ્,\nઉવાચ પાર્થ પશ્યૈતાન્સમવેતાન્કુરૂનિતિ.(૨૫)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nEvamukto hrisheekesho gudaakeshena bhaarata;\nSenayor ubhayormadhye sthaapayitwaa rathottamam.\nBheeshmadronapramukhatah sarveshaam cha maheekshitaam;\nUvaacha paartha pashyaitaan samavetaan kuroon iti.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" संजय बोले- हे धृतराष्ट्र! अर्जुन द्वारा कहे अनुसार महाराज श्रीकृष्णचंद्र ने दोनों सेनाओं के बीच में भीष्म और द्रोणाचार्य के सामने तथा सम्पूर्ण राजाओं के सामने उत्तम रथ को खड़ा कर इस प्रकार कहा कि हे पार्थ! युद्ध के लिए जुटे हुए इन कौरवों को देख॥24-25॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે છે-હે ભારત (ધૃતરાષ્ટ્ર), ગુડાકેશ (અર્જુન)ના વચનો સાંભળી ભગવાન ઋષિકેશે એમનો રથ બંને સેનાની મધ્યમાં લાવીને ઊભો રાખ્યો. રથ જ્યારે પિતામહ ભીષ્મ, આચાર્ય દ્રોણ તથા અન્ય પ્રમુખ યોદ્ધાઓની સામે આવીને ઊભો રહ્યો ત્યારે ભગવાન કૃષ્ણે અર્જુનને કહ્યું, અર્જુન, વિપક્ષમાં યુદ્ધ માટે તૈયાર થયેલા યોદ્ધાઓને બરાબર જોઈ લે. (૨૪-૨૫)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said: O descendant of Bharata, having thus been addressed by Arjuna, Lord Krishna drew up the fine chariot in the midst of the armies of both parties.In the presence of Bhishma, Drona and all the other chieftains of the world, the Lord said, Just behold, Partha, all the Kurus assembled here.(24-25)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok26_27() {
        load();
        setTitle("Shlok 26,27");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("तत्रापश्यत्स्थितान्‌ पार्थः पितृनथ पितामहान्‌ ।\nआचार्यान्मातुलान्भ्रातृन्पुत्रान्पौत्रान्सखींस्तथा ॥\nश्वशुरान्‌ सुहृदश्चैव सेनयोरुभयोरपि ।\nतान्समीक्ष्य स कौन्तेयः सर्वान्‌ बन्धूनवस्थितान्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("તત્રાપશ્યત્સ્થિતાન્પાર્થઃ પિતૃનથ પિતામહાન્,\nઆચાર્યાન્માતુલાન્ભ્રાતૃન્પુત્રાન્પૌત્રાન્સખીંસ્તથા.(૨૬)\nશ્વશુરાન્સુહૃદશ્ચૈવ સેનયોરુભયોરપિ,\nતાન્સમીક્ષ્ય સ કૌન્તેયઃ સર્વાન્બન્ધૂનવસ્થિતાન્.(૨૭)");
        } else if (2 == i) {
            this.tv1.setText("Tatraapashyat sthitaan paarthah pitrin atha pitaamahaan;\nAachaaryaan maatulaan bhraatrun putraan pautraan sakheemstathaa.\nShvashuraan suhridashchaiva senayorubhayorapi;\nTaan sameekshya sa kaunteyah sarvaan bandhoon avasthitaan.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("इसके बाद पृथापुत्र अर्जुन ने उन दोनों ही सेनाओं में स्थित ताऊ-चाचों को, दादों-परदादों को, गुरुओं को, मामाओं को, भाइयों को, पुत्रों को, पौत्रों को तथा मित्रों को, ससुरों को और सुहृदों को भी देखा. उन उपस्थित सम्पूर्ण बंधुओं को देखकर वे कुंतीपुत्र अर्जुन अत्यन्त करुणा से युक्त होकर शोक करते हुए यह वचन बोले. ॥26-27॥");
        } else if (i2 == 0) {
            this.tv2.setText("પાર્થે બંને સેનાઓનું નિરીક્ષણ કર્યું તો એમાં પોતાના પિતરાઈ ભાઈઓ, પિતામહ, આચાર્ય, મામા, પુત્ર, પૌત્રો, મિત્રો, સ્નેહીજનો તથા હિતચિંતકોને ઊભેલા જોયા. એ બધા ને જોઈને અર્જુનનું મન ઉદ્વિગ્ન થઈ ગયું. વિષાદથી ભરેલ મને એણે, ભગવાન કૃષ્ણને કહ્યું,(૨૬-૨૭)");
        } else if (2 == i2) {
            this.tv2.setText("There Arjuna could see, within the midst of the armies of both parties, his fathers, grandfathers, teachers, maternal uncles, brothers, sons, grandsons, friends, and also his fathers-in-law and well-wishers.When the son of Kunti, Arjuna, saw all these different grades of friends and relatives, he became overwhelmed with compassion and spoke thus.(26-27)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok28_29_30() {
        load();
        setTitle("Shlok 28,29,30");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("कृपया परयाविष्टो विषीदत्रिदमब्रवीत्‌ ।\nअर्जुन उवाच\nदृष्टेवमं स्वजनं कृष्ण युयुत्सुं समुपस्थितम्‌ ॥\nसीदन्ति मम गात्राणि मुखं च परिशुष्यति । \nवेपथुश्च शरीरे में रोमहर्षश्च जायते ॥\nगाण्डीवं स्रंसते हस्तात्वक्चैव परिदह्यते ।\nन च शक्नोम्यवस्थातुं भ्रमतीव च मे मनः ॥\n");
        } else if (i == 0) {
            this.tv1.setText("કૃપયા પરયાવિષ્ટો વિષીદન્નિદમબ્રવીત્,\nઅર્જુનઉવાચ :\nદૃષ્ટ્વેમં સ્વજનં કૃષ્ણ યુયુત્સું સમુપસ્થિતમ્.(૨૮)\nસીદન્તિ મમ ગાત્રાણિ મુખં ચ પરિશુષ્યતિ,\nવેપથુશ્ચ શરીરે મે રોમહર્ષશ્ચ જાયતે.(૨૯)\nગાણ્ડીવં સ્રંસતે હસ્તાત્ત્વક્ચૈવ પરિદહ્યતે,\nન ચ શક્નોમ્યવસ્થાતું ભ્રમતીવ ચ મે મનઃ(૩૦)");
        } else if (2 == i) {
            this.tv1.setText("Kripayaa parayaa’vishto visheedannidam abraveet;\nArjuna Uvaacha:\nDrishtwemam swajanam krishna yuyutsum samupasthitam.\nSeedanti mama gaatraani mukham cha parishushyati;\nVepathushcha shareere me romaharshashcha jaayate.\nGaandeevam sramsate hastaat twak chaiva paridahyate;\nNa cha shaknomyavasthaatum bhramateeva cha me manah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे कृष्ण, युद्ध क्षेत्र में डटे हुए युद्ध के अभिलाषी इस स्वजनसमुदाय को देखकर मेरे अंग शिथिल हुए जा रहे हैं और मुख सूखा जा रहा है तथा मेरे शरीर में कम्प एवं रोमांच हो रहा है. हाथ से गांडीव धनुष गिर रहा है और त्वचा भी बहुत जल रही है तथा मेरा मन भ्रमित-सा हो रहा है, इसलिए मैं खड़ा रहने को भी समर्थ नहीं हूँ.॥28-29-30॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કૃષ્ણ, યુદ્ધ ભૂમિમાં હું સગાં સંબંધી અને હિતેચ્છુઓને લડવા માટે તત્પર ઊભેલા જોઈ રહ્યો છું. એમની સાથે યુદ્ધ કરવાની માત્ર-કલ્પના કરતાં જ, મારાં અંગ ઠંડા પડી રહ્યા છે, મારું મોં સુકાઈ રહ્યું છે, મારું શરીર અને અંગેઅંગ કાંપી રહ્યા છે. મારા હાથમાંથી ગાંડીવ જાણે સરકી રહ્યું છે. મારી ત્વચામાં દાહ થઈ રહ્યો હોય એવું મને લાગે છે. મારું ચિત્ત ભમી રહ્યું હોય એવું મને લાગે છે અને મારાથી ઊભા પણ રહેવાતું નથી. (૨૮,૨૯,૩૦)");
        } else if (2 == i2) {
            this.tv2.setText(" Seeing these, my kinsmen, O Krishna, arrayed, eager to fight, My limbs fail and my mouth is parched up, my body quivers and my hairs stand on end!The (bow) “Gandiva” slips from my hand and my skin burns all over; I am unable even to stand, my mind is reeling, as it were.(28-29-30)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok31_32() {
        load();
        setTitle("Shlok 31,32");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("निमित्तानि च पश्यामि विपरीतानि केशव ।\nन च श्रेयोऽनुपश्यामि हत्वा स्वजनमाहवे ॥\nन काङ्‍क्षे विजयं कृष्ण न च राज्यं सुखानि च ।\nकिं नो राज्येन गोविंद किं भोगैर्जीवितेन वा ॥");
        } else if (i == 0) {
            this.tv1.setText("નિમિત્તાનિ ચ પશ્યામિ વિપરીતાનિ કેશવ,\nન ચ શ્રેયોનુપશ્યામિ હત્વા સ્વજનમાહવે.(૩૧)\nન કાઙ્ક્ષે વિજયં કૃષ્ણ ન ચ રાજ્યં સુખાનિ ચ,\nકિં નો રાજ્યેન ગોવિન્દ કિં ભોગૈર્જીવિતેન વા.(૩૨)");
        } else if (2 == i) {
            this.tv1.setText("Nimittaani cha pashyaami vipareetaani keshava;\nNa cha shreyo’nupashyaami hatwaa swajanam aahave.\nNa kaangkshe vijayam krishna na cha raajyam sukhaani cha;\nKim no raajyena govinda kim bhogair jeevitena vaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे केशव! मैं लक्षणों को भी विपरीत ही देख रहा हूँ तथा युद्ध में स्वजन-समुदाय को मारकर कल्याण भी नहीं देखता.  हे कृष्ण! मैं न तो विजय चाहता हूँ और न राज्य तथा सुखों को ही। हे गोविंद! हमें ऐसे राज्य से क्या प्रयोजन है अथवा ऐसे भोगों से और जीवन से भी क्या लाभ है?॥31-32॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે કેશવ, મને અમંગલ લક્ષણો દેખાઈ રહ્યા છે. મારા સ્વજન અને હિતેચ્છુઓને મારવામાં મને કોઈ કલ્યાણનું કામ હોય એમ નથી લાગતું, હે કૃષ્ણ, મને ન તો યુદ્ધમાં વિજય મેળવવાની ઈચ્છા છે, ન તો રાજ્યગાદી મેળવવાની કે અન્ય સુખોની કામના છે. (૩૧-૩૨)");
        } else if (2 == i2) {
            this.tv2.setText("And I see adverse omens, O Kesava! I do not see any good in killing my kinsmen in battle. For I desire neither victory, O Krishna, nor pleasures nor kingdoms! Of what avail is a dominion to us, O Krishna, or pleasures or even life?(31-32)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok33_34() {
        load();
        setTitle("Shlok 33,34");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("येषामर्थे काङक्षितं नो राज्यं भोगाः सुखानि च ।\nत इमेऽवस्थिता युद्धे प्राणांस्त्यक्त्वा धनानि च ॥\nआचार्याः पितरः पुत्रास्तथैव च पितामहाः ।\nमातुलाः श्वशुराः पौत्राः श्यालाः संबंधिनस्तथा ॥\n");
        } else if (i == 0) {
            this.tv1.setText("યેષામર્થે કાઙ્ક્ષિતં નો રાજ્યં ભોગાઃ સુખાનિ ચ,\nત ઇમેવસ્થિતા યુદ્ધે પ્રાણાંસ્ત્યક્ત્વા ધનાનિ ચ.(33)\nઆચાર્યાઃ પિતરઃ પુત્રાસ્તથૈવ ચ પિતામહાઃ,\nમાતુલાઃ શ્ચશુરાઃ પૌત્રાઃ શ્યાલાઃ સમ્બન્ધિનસ્તથા.(૩૪)");
        } else if (2 == i) {
            this.tv1.setText("Yeshaam arthe kaangkshitam no raajyam bhogaah sukhaani cha;\nTa ime’vasthitaa yuddhe praanaamstyaktwaa dhanaani cha.\nAachaaryaah pitarah putraastathaiva cha pitaamahaah;\nMaatulaah shwashuraah pautraah shyaalaah sambandhinas tathaa.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हमें जिनके लिए राज्य, भोग और सुखादि अभीष्ट हैं, वे ही ये सब धन और जीवन की आशा को त्यागकर युद्ध में खड़े हैं. गुरुजन, ताऊ-चाचे, लड़के और उसी प्रकार दादे, मामे, ससुर, पौत्र, साले तथा और भी संबंधी लोग हैं ॥33-34॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે ગોવિંદ, સ્વજનો અને હિતેચ્છુઓને મારીને મળનાર રાજ્ય અને ભોગોને ભોગવીને અમારે શું કરવું છે ? અરે, તેમને હણ્યા પછી અમારા જીવનનો પણ શું અર્થ બાકી રહેશે ? જેને માટે(મારા ગુરુજન, પિતા, પુત્ર, પૌત્રો, શ્વસુર પક્ષના સગાસંબંધીઓ)  આ વૈભવ, રાજ્ય અને ભોગની કામના અમે કરીએ છીએ તેઓ સ્વયં આ યુદ્ધભૂમિમાં પોતાના પ્રાણોનું બલિદાન આપવા ઊભેલા છે. (૩૩-૩૪)");
        } else if (2 == i2) {
            this.tv2.setText("Those for whose sake we desire kingdoms, enjoyments and pleasures, stand here in battle, having renounced life and wealth.Teachers, fathers, sons and also grandfathers, grandsons, fathers-in-law, maternal uncles, brothers-in-law and relatives,(33-34)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok35() {
        load();
        setTitle("Shlok 35");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("एतान्न हन्तुमिच्छामि घ्नतोऽपि मधुसूदन ।\nअपि त्रैलोक्यराज्यस्य हेतोः किं नु महीकृते ॥\n");
        } else if (i == 0) {
            this.tv1.setText("એતાન્ન હન્તુમિચ્છામિ ઘ્નતોપિ મધુસૂદન,\nઅપિ ત્રૈલોક્યરાજ્યસ્ય હેતોઃ કિં નુ મહીકૃતે.(૩૫)");
        } else if (2 == i) {
            this.tv1.setText("Etaan na hantum icchaami ghnato’pi madhusoodana;\nApi trailokya raajyasya hetoh kim nu maheekrite.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे मधुसूदन! मुझे मारने पर भी अथवा तीनों लोकों के राज्य के लिए भी मैं इन सबको मारना नहीं चाहता, फिर पृथ्वी के लिए तो कहना ही क्या है?॥35॥");
        } else if (i2 == 0) {
            this.tv2.setText("આ બધાને ત્રિભુવનના (ત્રણે ભુવન ના) રાજ્ય માટે પણ મારવાની કલ્પના હું કરી શકું એમ નથી તો ધરતીના ટુકડા માટે એમને શા માટે મારવા ? ભલેને તેઓ અમને મારી નાખે.(૩૫) ");
        } else if (2 == i2) {
            this.tv2.setText("These I do not wish to kill, though they kill me, O Krishna, even for the sake of dominion over the three worlds, leave alone killing them for the sake of the earth!(35)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok36_37() {
        load();
        setTitle("Shlok 36,37");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("निहत्य धार्तराष्ट्रान्न का प्रीतिः स्याज्जनार्दन ।\nपापमेवाश्रयेदस्मान्‌ हत्वैतानाततायिनः ॥\nतस्मान्नार्हा वयं हन्तुं धार्तराष्ट्रान्स्वबान्धवान्‌ ।\nस्वजनं हि कथं हत्वा सुखिनः स्याम माधव ॥");
        } else if (i == 0) {
            this.tv1.setText("નિહત્ય ધાર્તરાષ્ટ્રાન્નઃ કા પ્રીતિઃ સ્યાજ્જનાર્દન,\nપાપમેવાશ્રયેદસ્માન્હત્વૈતાનાતતાયિનઃ.(૩૬)\nતસ્માન્નાર્હા વયં હન્તું ધાર્તરાષ્ટ્રાન્સ્વબાન્ધવાન્,\nસ્વજનં હિ કથં હત્વા સુખિનઃ સ્યામ માધવ.(૩૭)");
        } else if (2 == i) {
            this.tv1.setText("Nihatya dhaartaraashtraan nah kaa preetih syaaj janaardana;\nPaapam evaashrayed asmaan hatwaitaan aatataayinah.\nTasmaan naarhaa vayam hantum dhaartaraashtraan swabaandhavaan;\nSwajanam hi katham hatwaa sukhinah syaama maadhava.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("हे जनार्दन! धृतराष्ट्र के पुत्रों को मारकर हमें क्या प्रसन्नता होगी? इन आततायियों को मारकर तो हमें पाप ही लगेगा. अतएव हे माधव! अपने ही बान्धव धृतराष्ट्र के पुत्रों को मारने के लिए हम योग्य नहीं हैं क्योंकि अपने ही कुटुम्ब को मारकर हम कैसे सुखी होंगे?॥36-37॥");
        } else if (i2 == 0) {
            this.tv2.setText("ધૃતરાષ્ટ્રના પુત્રોને મારવાથી અમને શું પ્રસન્નતા મળશે. હે જનાર્દન, સ્વજનોની હત્યા કરવાથી તો કેવળ પાપ જ મળશે. એટલે એમને મારવા ઉચિત નથી. એમને મારી ને હું કેવી રીતે સુખી થઈશ?  (૩૬-૩૭)");
        } else if (2 == i2) {
            this.tv2.setText("By killing these sons of Dhritarashtra, what pleasure can be ours, O Janardana? Only sin will accrue by killing these felons.Therefore, we should not kill the sons of Dhritarashtra, our relatives; for, how can we be happy by killing our own people, O Madhava (Krishna)?(36-37)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok38_39_40() {
        load();
        setTitle("Shlok 38,39,40");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यद्यप्येते न पश्यन्ति लोभोपहतचेतसः ।\nकुलक्षयकृतं दोषं मित्रद्रोहे च पातकम्‌ ॥\nकथं न ज्ञेयमस्माभिः पापादस्मान्निवर्तितुम्‌ ।\nकुलक्षयकृतं दोषं प्रपश्यद्भिर्जनार्दन ॥\nकुलक्षये प्रणश्यन्ति कुलधर्माः सनातनाः ।\nधर्मे नष्टे कुलं कृत्स्नमधर्मोऽभिभवत्युत ॥");
        } else if (i == 0) {
            this.tv1.setText("યદ્યપ્યેતે ન પશ્યન્તિ લોભોપહતચેતસઃ,\nકુલક્ષયકૃતં દોષં મિત્રદ્રોહે ચ પાતકમ્.(૩૮)\nકથં ન જ્ઞેયમસ્માભિઃ પાપાદસ્માન્નિવર્તિતુમ્,\nકુલક્ષયકૃતં દોષં પ્રપશ્યદ્ભિર્જનાર્દન.(૩૯)\nકુલક્ષયે પ્રણશ્યન્તિ કુલધર્માઃ સનાતનાઃ,\nધર્મે નષ્ટે કુલં કૃત્સ્નમધર્મોભિભવત્યુત.(૪૦)");
        } else if (2 == i) {
            this.tv1.setText("Yadyapyete na pashyanti lobhopahatachetasah;\nKulakshayakritam dosham mitradrohe cha paatakam.\nKatham na jneyam asmaabhih paapaad asmaan nivartitum;\nKulakshayakritam dosham prapashyadbhir janaardana.\nKulakshaye pranashyanti kuladharmaah sanaatanaah;\nDharme nashte kulam kritsnam adharmo’bhibhavatyuta.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("यद्यपि लोभ से भ्रष्टचित्त हुए ये लोग कुल के नाश से उत्पन्न दोष को और मित्रों से विरोध करने में पाप को नहीं देखते, तो भी हे जनार्दन! कुल के नाश से उत्पन्न दोष को जानने वाले हम लोगों को इस पाप से हटने के लिए क्यों नहीं विचार करना चाहिए? कुल के नाश से सनातन कुल-धर्म नष्ट हो जाते हैं तथा धर्म का नाश हो जाने पर सम्पूर्ण कुल में पाप भी बहुत फैल जाता है॥38-39-40॥");
        } else if (i2 == 0) {
            this.tv2.setText("હે માધવ, એમની (દુર્યોધન અને કૌરવોની) મતિ તો રાજ્યના લોભથી ભ્રષ્ટ થઈ ગઈ છે. પોતાના કુળનો વિનાશ કરવામાં તથા મિત્રોનો દ્રોહ કરવામાં એમનેકોઈ પણ પ્રકારનો ક્ષોભ થતો નથી. પરંતુ હે જનાર્દન, અમે અમારા કુળનો વિનાશ શા માટે થવા દઈએ. એવું ઘોર પાતકનું કામ કરવામાં અમે શા માટે પ્રવૃત્ત થઈએ. કુળનો વિનાશ થતાં કુળધર્મોનો નાશ થાય છે. અને કુળધર્મનો નાશ થતાં અધર્મ વ્યાપે છે. (૩૮-૩૯-૪૦)");
        } else if (2 == i2) {
            this.tv2.setText("Though they, with intelligence overpowered by greed, see no evil in the destruction of families, and no sin in hostility to friends, Why should not we, who clearly see evil in the destruction of a family, learn to turn away from this sin, O Janardana (Krishna)? In the destruction of a family, the immemorial religious rites of that family perish; on the destruction of spirituality, impiety overcomes the whole family. (38-39-40)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok41() {
        load();
        setTitle("Shlok 41");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("अधर्माभिभवात्कृष्ण प्रदुष्यन्ति कुलस्त्रियः ।\nस्त्रीषु दुष्टासु वार्ष्णेय जायते वर्णसंकरः ॥");
        } else if (i == 0) {
            this.tv1.setText("અધર્માભિભવાત્કૃષ્ણ પ્રદુષ્યન્તિ કુલસ્ત્રિયઃ,\nસ્ત્રીષુ દુષ્ટાસુ વાર્ષ્ણેય જાયતે વર્ણસઙ્કરઃ.(૪૧)");
        } else if (2 == i) {
            this.tv1.setText("Adharmaabhibhavaat krishna pradushyanti kulastriyah;\nStreeshu dushtaasu vaarshneya jaayate varnasankarah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" हे कृष्ण! पाप के अधिक बढ़ जाने से कुल की स्त्रियाँ अत्यन्त दूषित हो जाती हैं और हे वार्ष्णेय! स्त्रियों के दूषित हो जाने पर वर्णसंकर उत्पन्न होता है॥41॥");
        } else if (i2 == 0) {
            this.tv2.setText("અધર્મ વ્યાપવાથી કુળની સ્ત્રીઓમાં દોષ આવે છે. અને હે વાષ્ણેય(કૃષ્ણ), એવું થવાથી વર્ણધર્મ નષ્ટ થઈ જાય છે. વર્ણધર્મનો નાશ થતાં વર્ણસંકર પ્રજા ઉત્પન્ન થાય છે. (૪૧)");
        } else if (2 == i2) {
            this.tv2.setText("By prevalence of impiety, O Krishna, the women of the family become corrupt and, women becoming corrupted, O Varsneya (descendant of Vrishni), there arises intermingling of castes!(41)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok42_43_44_45() {
        load();
        setTitle("Shlok 42,43,44,45");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संकरो नरकायैव कुलघ्नानां कुलस्य च ।\nपतन्ति पितरो ह्येषां लुप्तपिण्डोदकक्रियाः ॥\nदोषैरेतैः कुलघ्नानां वर्णसंकरकारकैः ।\nउत्साद्यन्ते जातिधर्माः कुलधर्माश्च शाश्वताः ॥\nउत्सन्नकुलधर्माणां मनुष्याणां जनार्दन ।\nनरकेऽनियतं वासो भवतीत्यनुशुश्रुम ॥\nअहो बत महत्पापं कर्तुं व्यवसिता वयम्‌ ।\nयद्राज्यसुखलोभेन हन्तुं स्वजनमुद्यताः ॥");
        } else if (i == 0) {
            this.tv1.setText("સઙ્કરો નરકાયૈવ કુલઘ્નાનાં કુલસ્ય ચ,\nપતન્તિ પિતરો હ્યેષાં લુપ્તપિણ્ડોદકક્રિયાઃ.(૪૨)\nદોષૈરેતૈઃ કુલઘ્નાનાં વર્ણસઙ્કરકારકૈઃ,\nઉત્સાદ્યન્તે જાતિધર્માઃ કુલધર્માશ્ચ શાશ્વતાઃ.(૪૩)\nઉત્સન્નકુલધર્માણાં મનુષ્યાણાં જનાર્દન,\nનરકેનિયતં વાસો ભવતીત્યનુશુશ્રુમ.(૪૪)\nઅહો બત મહત્પાપં કર્તું વ્યવસિતા વયમ્,\nયદ્રાજ્યસુખલોભેન હન્તું સ્વજનમુદ્યતાઃ(૪૫)");
        } else if (2 == i) {
            this.tv1.setText("Sankaro narakaayaiva kulaghnaanaam kulasya cha;\nPatanti pitaro hyeshaam luptapindodakakriyaah.\nDoshair etaih kulaghnaanaam varnasankarakaarakaih;\nUtsaadyante jaatidharmaah kuladharmaashcha shaashwataah.\nUtsannakuladharmaanaam manushyaanaam janaardana;\nNarake’niyatam vaaso bhavateetyanushushruma.\nAho bata mahat paapam kartum vyavasitaa vayam;\nYadraajya sukhalobhena hantum swajanam udyataah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText("वर्णसंकर कुलघातियों को और कुल को नरक में ले जाने के लिए ही होता है। लुप्त हुई पिण्ड और जल की क्रिया वाले अर्थात श्राद्ध और तर्पण से वंचित इनके पितर लोग भी अधोगति को प्राप्त होते हैं. इन वर्णसंकरकारक दोषों से कुलघातियों के सनातन कुल-धर्म और जाति-धर्म नष्ट हो जाते हैं. हे जनार्दन! जिनका कुल-धर्म नष्ट हो गया है, ऐसे मनुष्यों का अनिश्चितकाल तक नरक में वास होता है, ऐसा हम सुनते आए हैं.  हा! शोक! हम लोग बुद्धिमान होकर भी महान पाप करने को तैयार हो गए हैं, जो राज्य और सुख के लोभ से स्वजनों को मारने के लिए उद्यत हो गए हैं.॥42-43-44-45॥");
        } else if (i2 == 0) {
            this.tv2.setText("એવા સંતાનો એમના પિતૃઓનું શ્રાદ્ધ વગેરે કર્મ કરતાં નથી. એથી પિતૃઓની દુર્ગતિ થાય છે. તેમનો ઉદ્ધાર ન થવાથી તેઓ નરકમાં જાય છે. કુલધર્મ અને વર્ણધર્મથી નષ્ટ થયેલ એવા મનુષ્યને અનિશ્ચિત સમય સુધી નરકમાં વાસ કરવો પડે છે, એવું મેં સાંભળ્યું છે. એથી હે કેશવ, મને સમજાતું નથી કે અમે આવું પાપકર્મ કરવા માટે શા માટે અહીં ઉપસ્થિત થયા છીએ? રાજ્ય અને સુખ મેળવવા માટે અમારા જ સ્વજનોને હણવા માટે અમે કેમ વ્યાકુળ બન્યા છીએ ? (૪૨-૪૫)");
        } else if (2 == i2) {
            this.tv2.setText("Confusion of castes leads to hell the slayers of the family, for their forefathers fall, deprived of the offerings of rice-ball and water. By these evil deeds of the destroyers of the family, which cause confusion of castes, the eternal religious rites of the caste and the family are destroyed.. We have heard, O Janardana, that inevitable is the dwelling for an unknown period in hell for those men in whose families the religious practices have been destroyed!  Alas! We are involved in a great sin in that we are prepared to kill our kinsmen through greed for the pleasures of a kingdom.(42-43-44-45)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok46() {
        load();
        setTitle("Shlok 46");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("यदि मामप्रतीकारमशस्त्रं शस्त्रपाणयः ।\nधार्तराष्ट्रा रणे हन्युस्तन्मे क्षेमतरं भवेत्‌ ॥");
        } else if (i == 0) {
            this.tv1.setText("યદિ મામપ્રતીકારમશસ્ત્રં શસ્ત્રપાણયઃ,\nધાર્તરાષ્ટ્રા રણે હન્યુસ્તન્મે ક્ષેમતરં ભવેત્.(૪૬)");
        } else if (2 == i) {
            this.tv1.setText("Yadi maam aprateekaaram ashastram shastrapaanayah;\nDhaartaraashtraa rane hanyus tanme kshemataram bhavet.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" यदि मुझ शस्त्ररहित एवं सामना न करने वाले को शस्त्र हाथ में लिए हुए धृतराष्ट्र के पुत्र रण में मार डालें तो वह मारना भी मेरे लिए अधिक कल्याणकारक होगा॥46॥");
        } else if (i2 == 0) {
            this.tv2.setText("મને લાગે છે કે યુદ્ધ કરવા કરતાં તો બહેતર છે કે હું શસ્ત્રોનો ત્યાગ કરી દઉં. ભલે ધૃતરાષ્ટ્રના પુત્રો મને નિઃશસ્ત્ર અવસ્થામાં યુદ્ધભૂમિમાં મારી નાખે. (૪૬)");
        } else if (2 == i2) {
            this.tv2.setText("If the sons of Dhritarashtra, with weapons in hand, should slay me in battle, unresisting and unarmed, that would be better for me.(46)");
        }
    }

    /* access modifiers changed from: package-private */
    public void shlok47() {
        load();
        setTitle("Shlok 47");
        int i = this.callf1;
        if (1 == i) {
            this.tv1.setText("संजय उवाच\nएवमुक्त्वार्जुनः सङ्‍ख्ये रथोपस्थ उपाविशत्‌ ।\nविसृज्य सशरं चापं शोकसंविग्नमानसः ॥");
        } else if (i == 0) {
            this.tv1.setText("સંજય ઉવાચ - \nએવમુક્ત્વાર્જુનઃ સંખ્યે રથોપસ્થ ઉપાવિશત્,\nવિસૃજ્ય સશરં ચાપં શોકસંવિગ્નમાનસઃ.(૪૭)");
        } else if (2 == i) {
            this.tv1.setText("Sanjaya Uvaacha:\nEvamuktwaa’rjunah sankhye rathopastha upaavishat;\nVisrijya sasharam chaapam shokasamvignamaanasah.");
        }
        int i2 = this.callf2;
        if (1 == i2) {
            this.tv2.setText(" संजय बोले- \nरणभूमि में शोक से उद्विग्न मन वाले अर्जुन इस प्रकार कहकर, बाणसहित धनुष को त्यागकर रथ के पिछले भाग में बैठ गए॥47॥");
        } else if (i2 == 0) {
            this.tv2.setText("સંજય કહે છે -\nએમ કહીને ઉદ્વિગ્ન મનથી ભરેલ અર્જુન પોતાના ગાંડિવનો પરિત્યાગ કરીને રથમાં પાછળ બેસી ગયો.(૪૭)");
        } else if (2 == i2) {
            this.tv2.setText("Sanjaya said:\nHaving thus spoken in the midst of the battlefield, Arjuna, casting away his bow and arrow, sat down on the seat of the chariot with his mind overwhelmed with sorrow.(47)");
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

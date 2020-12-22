package com.gita.bhagwatgita;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Questions extends AppCompatActivity {
    AdView adView;
    TextView tvans1;
    TextView tvans10;
    TextView tvans2;
    TextView tvans3;
    TextView tvans4;
    TextView tvans5;
    TextView tvans6;
    TextView tvans7;
    TextView tvans8;
    TextView tvans9;
    TextView tvque1;
    TextView tvque10;
    TextView tvque2;
    TextView tvque3;
    TextView tvque4;
    TextView tvque5;
    TextView tvque6;
    TextView tvque7;
    TextView tvque8;
    TextView tvque9;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_questions);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.adView = (AdView) findViewById(R.id.adView);
        this.adView.loadAd(new AdRequest.Builder().build());
        this.tvque1 = (TextView) findViewById(R.id.tvque1);
        this.tvans1 = (TextView) findViewById(R.id.tvans1);
        this.tvque2 = (TextView) findViewById(R.id.tvque2);
        this.tvans2 = (TextView) findViewById(R.id.tvans2);
        this.tvque3 = (TextView) findViewById(R.id.tvque3);
        this.tvans3 = (TextView) findViewById(R.id.tvans3);
        this.tvque4 = (TextView) findViewById(R.id.tvque4);
        this.tvans4 = (TextView) findViewById(R.id.tvans4);
        this.tvque5 = (TextView) findViewById(R.id.tvque5);
        this.tvans5 = (TextView) findViewById(R.id.tvans5);
        this.tvque6 = (TextView) findViewById(R.id.tvque6);
        this.tvans6 = (TextView) findViewById(R.id.tvans6);
        this.tvque7 = (TextView) findViewById(R.id.tvque7);
        this.tvans7 = (TextView) findViewById(R.id.tvans7);
        this.tvque8 = (TextView) findViewById(R.id.tvque8);
        this.tvans8 = (TextView) findViewById(R.id.tvans8);
        this.tvque9 = (TextView) findViewById(R.id.tvque9);
        this.tvans9 = (TextView) findViewById(R.id.tvans9);
        this.tvque1.setText("What is Shreemad-Bhagavad-Geeta?");
        this.tvans1.setText("Shreemad-Bhagavad-Geeta is the essence of the Vedas and Upanishads. It is a universal scripture applicable to people of all temperaments, for all times. It is a book with sublime thoughts and practical instructions on Yoga, Devotion, Vedanta and Action.The Shreemad-Bhagavad-Geeta is the eternal message of spiritual wisdom from ancient India. The word Geeta means song and the word Bhagavad means God, often the Shreemad-Bhagavad-Geeta is called the Song of God.");
        this.tvque2.setText("What is Background of the Shreemad-Bhagavad-Geeta?");
        this.tvans2.setText("Dhritarashtra and Pandu were two brothers, born in the royal Kuru dynasty. The former, who was older, was visually challenged, and so Pandu was officially crowned as King of the Kuru clan. Pandu had five sons, called the Pandavas, while Dhritarashtra had a hundred sons, called the Kauravas. The Pandavas, Yudhishthir, Bhim, Arjun, Nakul and Sahadev, were brilliant, each one possessing at least one excellent trait. The eldest son of Dhritarashtra, Duryodhan, also his dearest, held a long, deep-seated hatred for the Pandavas. Pandu died young, after which Dhritashtra then ascended the throne. Once the princes came of age, there arose a dispute as to who would become the next rulers - whether it should be Yudhishthir or Duryodhan. All the elders, gurus and those wielding high authority in the dynasty, unanimously averred that Yudhishthir, being the son of the King, was the rightful heir to the throne. But Duryodhan differed, saying that he was the son of the older brother and hence, solely held the right to the throne. The dispute prolonged without a solution in sight, leading to a bifurcation of the kingdom. This is where the tale of the Mahabharata actually started. Duryodhan tried everything to destroy the Pandavas - he even tried to kill them in the infamous wax house incident. But Krishna's grace always prevailed over the Pandavas and they were saved each and every time.");
        this.tvque3.setText("What is the size of the Shreemad-Bhagavad-Geeta?");
        this.tvans3.setText("Shreemad Bhagavad-Geeta is composed of 700 Sanskrit verses contained within 18 chapters, divided into three sections each consisting of six chapters. They are Karma Yoga the yoga of actions. Bhakti Yoga the yoga of devotion and Jnana Yoga the yoga of knowledge.");
        this.tvque4.setText("Who originally spoke the Shreemad-Bhagavad-Geeta?");
        this.tvans4.setText("Lord Krishna originally spoke the Shreemad-Bhagavad-Geeta?.");
        this.tvque5.setText("Where was the Shreemad-Bhagavad-Geeta originally spoken?");
        this.tvans5.setText("Kuruksetra (India)");
        this.tvque6.setText("What is the historical epic Mahabharta?");
        this.tvans6.setText("The Mahabharata is the most voluminous book the world has ever known. The Mahabharata covers the history of the earth from the time of creation in relation to India. Composed in 100,000 rhyming quatrain couplets the Mahabharata is seven times the size of the Illiad written by Homer.");
        this.tvque7.setText("Who is Vedavyasa?");
        this.tvans7.setText("Vedavyasa is the divine saint and incarnation who authored the Shreemad Bhagavatam, Vedanta Sutra, the 108 Puranas, composed and divided the Vedas into the Rik, Yajur, Artharva and Sama Vedas, and wrote the the great historical treatise Mahabharata known as the fifth Veda. His full name is Krishna Dvaipayana Vyasa and he was the son of sage Parasara and mother Satyavati.");
        this.tvque8.setText("Why is Shreemad often written before the Shreemad-Bhagavad-Geeta?");
        this.tvans8.setText("The word Shreemad is a title of great respect. This is given because the Shreemad-Bhagavad-Gita reveals the essence of all spiritual knowledge.");
        this.tvque9.setText("What was the original language of the Shreemad-Bhagavad-Geeta?");
        this.tvans9.setText("Sanskrit (India)");

        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            this.adView.setVisibility(8);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }
}

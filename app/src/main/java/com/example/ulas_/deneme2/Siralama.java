package com.example.ulas_.deneme2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

public class Siralama extends AppCompatActivity {
    private MediaPlayer mp;
    public Uri video_path;
    TextView Profil;
    Button Mod1,Mod2;
    ListView Liste;
    Database vt;
    public String kuladMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siralama);
        Profil = (TextView) findViewById(R.id.Profil);
         Mod1 = (Button) findViewById(R.id.Listele);
        Mod2 = (Button) findViewById(R.id.Listele2);
        Liste = (ListView) findViewById(R.id.Liste);
        kuladMain= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("kullaniciAdi","Ziyaretçi");
//Veri tabanı class'ını MainActivity'e bağlıyoruz.
        vt=new Database(Siralama.this,kuladMain);
//Verileri Oyun Moduna Göre Listeliyoruz GMOD ve Profil Nesnesine de tek Gmod a göre atıyoruz
        Mod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> Veriler = vt.Listele("1");//çıktı olan diziyi diziye eşitledik
                ArrayAdapter<String> Adapter = new ArrayAdapter<String>(Siralama.this, android.R.layout.simple_list_item_1, android.R.id.text1, Veriler);
                //diziyi listview e bağlamak için arrayadapter kullandık.
                Liste.setAdapter(Adapter);//bağladık ve aktartır.
                Profil.setText(vt.Profil("1"));
            }
        });
        Mod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> Veriler = vt.Listele("2");
                ArrayAdapter<String> Adapter = new ArrayAdapter<String>(Siralama.this, android.R.layout.simple_list_item_1, android.R.id.text1, Veriler);
                Liste.setAdapter(Adapter);
                Profil.setText(vt.Profil("2"));
            }
        });
    }
}

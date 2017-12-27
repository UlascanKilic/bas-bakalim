package com.example.ulas_.deneme2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class OyunSonu extends AppCompatActivity {
    public Intent getSonuc;
    public  TextView tvSonuc,tvMod,tvSeviye;
    public Typeface tf;
    public String modKontrol,sonucString,modString,seviyeString,tecubeString,kuladMain;
    public Bundle veriler;
    public Button anaSayfa,siralama;
    public static MainActivity ma;
    public int tecrube,oyunSonuTecrube,seviye,sonuc;
    public Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_sonu);
        kuladMain= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("kullaniciAdi","Ziyaretçi");
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((width),(int)(height*.3));
        final Intent anaSayfaIntent = new Intent(OyunSonu.this, MainActivity.class);
        getSonuc=getIntent();
        anaSayfa=(Button)findViewById(R.id.btnAnaSayfa);
        siralama=(Button)findViewById(R.id.btnSiralama);
        veriler = getSonuc.getExtras();
        ma=new MainActivity();
        tvSonuc=(TextView)findViewById(R.id.txtSonuc);
        tvMod=(TextView)findViewById(R.id.txtOyunModu);
        tvSeviye=(TextView)findViewById(R.id.txtTecrube);
        tf= Typeface.createFromAsset(getAssets(), "fonts/MV Boli.ttf");
        tvMod.setTypeface(tf);
        modKontrol=veriler.getString("oyunmodu");
      db=new Database(OyunSonu.this,kuladMain);
        switch (modKontrol){
           case "oyunmodunormal" :
               sonuc = veriler.getInt("OyunModuNormalSonuc");
               sonucString=String.valueOf(sonuc);
               db.Ekle(kuladMain,sonucString,"1");
               modString="Normal";
               break;
           case "oyunmoducehennem":
               sonuc = veriler.getInt("OyunModuCehennemSonuc");
               sonucString=String.valueOf(sonuc);
               db.Ekle(kuladMain,sonucString,"2");
               modString="Cehennem";
               break;

        }
        tecrube = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getInt("tecrube", 0);
        tecrube+=sonuc;
        seviye=tecrube/5;
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit().putInt("tecrube", tecrube).commit();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit().putInt("seviye", seviye).commit();
        tecubeString=String.valueOf(tecrube);
        seviyeString=String.valueOf(seviye);

        tvSonuc.setText("Puan : "+sonucString);
        tvMod.setText("Oyun Modu : "+modString);
        tvSeviye.setText("Seviye :"+seviyeString+" Tecrube :"+tecubeString);

        anaSayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mpCalisiyorMu();
                startActivity(anaSayfaIntent);
            }
        });
        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(OyunSonu.this,Siralama.class));
            }
        });
    }
    public  void mpCalisiyorMu(){
        if(ma.mp.isPlaying()){

            ma.mp.stop();
            ma.mp.reset();
        }
    }
}

package com.example.ulas_.deneme2;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    public Database db;
    public TextView tvKullaniciAdi,tvSeviye;
    public static MediaPlayer mp;
    public  Boolean  isFirstRun,muzikKontrol;
    public  String kuladMain,temaSec;
    public ImageView imgTema;
    public int seviye;
    private Button btnBasla,btnSirala,btnCikis;
    public Bundle veriler;
    public Intent getKontrol;
    public  Typeface tf;
    final SpringAnimation animAX=new SpringAnimation(btnCikis, DynamicAnimation.TRANSLATION_X);
    final SpringAnimation animAY=new SpringAnimation(btnCikis, DynamicAnimation.TRANSLATION_Y);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvKullaniciAdi=(TextView)findViewById(R.id.kullaniciAdi);
        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);
       kuladMain= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("kullaniciAdi","Ziyaretçi");
        seviye = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getInt("seviye", 0);
        temaSec= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("temaSecimi","southpark");
        tf=Typeface.createFromAsset(getAssets(), "fonts/MV Boli.ttf");
        getKontrol=getIntent();
        veriler = getKontrol.getExtras();
        tvKullaniciAdi.setTypeface(tf);

        if (isFirstRun)
        {
            Toast.makeText(MainActivity.this,kuladMain,Toast.LENGTH_LONG).show();
             tvKullaniciAdi.setText(kuladMain);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit().putBoolean("isfirstrun", false).commit();
           startActivity(new Intent(MainActivity.this,KayitOl.class));
        }
        else{
            tvKullaniciAdi.setText("Hoşgeldin "+kuladMain+"!");
            Toast.makeText(MainActivity.this,kuladMain,Toast.LENGTH_LONG).show();
          //  startActivity(new Intent(MainActivity.this,KayitOl.class));

        }
       tvSeviye=(TextView)findViewById(R.id.txtSeviye);
        btnSirala=(Button) findViewById(R.id.buttonYuksekSkor);
        btnCikis =(Button) findViewById(R.id.buttonCikis);
        imgTema=(ImageView)findViewById(R.id.imgTema);
        btnBasla=(Button) findViewById(R.id.buttonBasla);

        final Intent intentKategoriActivity = new Intent(MainActivity.this, Kategoriler.class);
                switch (temaSec){
                    case "got":
                        mp=MediaPlayer.create(this,R.raw.gotmuzik);
                        break;
                    case "southpark":
                        mp=MediaPlayer.create(this,R.raw.sp2sarki);
                        break;
                    case "lotr":mp=MediaPlayer.create(this,R.raw.lotrmuzik);
                        break;
                }
                mp.start();
        mp.setLooping(true);




        tvSeviye.setText(seviye+" Seviye");
        kafaTema();

        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                if(mp.isPlaying()){

                    mp.stop();
                    mp.reset();
                }
            }
        });
        btnBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentKategoriActivity);
            }

        });
        imgTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TemaSec.class));
            }
        });
        btnSirala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Siralama.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        seviye = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getInt("seviye", 0);
        tvSeviye.setText(seviye+" Seviye");



        kafaTema();


    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public  void kafaTema(){
        temaSec= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("temaSecimi","southpark");
        switch (temaSec){
            case "got":
                imgTema.setBackground((Drawable)getResources().getDrawable(R.mipmap.john80));
                break;
            case "southpark":
                imgTema.setBackground((Drawable)getResources().getDrawable(R.mipmap.cartman80));
                break;
            case "lotr":
                imgTema.setBackground((Drawable)getResources().getDrawable(R.mipmap.frodo80));
                break;
        }
    }

}

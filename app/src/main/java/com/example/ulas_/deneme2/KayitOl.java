package com.example.ulas_.deneme2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KayitOl extends AppCompatActivity {
   Typeface tf;
    Button btn;
    TextView tv;
    EditText et;
    String kulad;
    int tecrube;
    Database db;
    boolean kontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        btn=(Button)findViewById(R.id.btnKullaniciKaydet);
        et=(EditText)findViewById(R.id.editTxtKullaniciAdi);
        tv=(TextView)findViewById(R.id.txtHosgeldin);
        tf= Typeface.createFromAsset(getAssets(), "fonts/MV Boli.ttf");
        tv.setTypeface(tf);

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit().putBoolean("sayfaKontrol",true).commit();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit().putInt("tecrube", 0).commit();

        kontrol = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("sayfaKontrol", false);
        db=new Database(KayitOl.this);
        final Intent intent = new Intent(this, MainActivity.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    kulad=et.getText().toString();
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .edit().putString("kullaniciAdi",kulad).commit();
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .edit().putBoolean("sayfaKontrol",false).commit();

                    db=new Database(KayitOl.this,kulad);
                    startActivity(intent);
                }
        });


    }
}

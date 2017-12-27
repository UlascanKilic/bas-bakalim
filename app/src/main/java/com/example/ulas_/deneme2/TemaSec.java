package com.example.ulas_.deneme2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TemaSec extends AppCompatActivity {
Button btnSouth,btnGOT,btnLOTR;
    public int seviye;
    String temaSecimi;
    public static MainActivity ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema_sec);
        btnSouth=(Button)findViewById(R.id.btnTemaSouthPark);
        btnGOT=(Button)findViewById(R.id.btnTemaGOT);
        btnLOTR=(Button)findViewById(R.id.btnTemaLOTR);
        seviye = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getInt("seviye", 0);
        ma=new MainActivity();

       if(seviye<20){
           btnGOT.setEnabled(false);
           btnLOTR.setEnabled(false);
           btnGOT.setBackground((Drawable)getResources().getDrawable(R.mipmap.gotkilitli));
           btnLOTR.setBackground((Drawable)getResources().getDrawable(R.mipmap.lotrkilitli));
       }
       else if(seviye <90){
           btnLOTR.setEnabled(false);
           btnGOT.setBackground((Drawable)getResources().getDrawable(R.mipmap.gottema));
           btnLOTR.setBackground((Drawable)getResources().getDrawable(R.mipmap.lotrkilitli));
       }

        btnSouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             temaSecimi="southpark";
                TemaKaydet();
                mpCalisiyorMu();

               Yonlendir();

            }
        });
        btnGOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temaSecimi="got";
                TemaKaydet();
                mpCalisiyorMu();
                Yonlendir();
            }
        });
        btnLOTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temaSecimi="lotr";
                TemaKaydet();
                mpCalisiyorMu();
                Yonlendir();
            }
        });


    }
    public void TemaKaydet(){
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit().putString("temaSecimi",temaSecimi).commit();
    }
    public void Yonlendir(){
        startActivity(new Intent(TemaSec.this,MainActivity.class));
    }
    public  void mpCalisiyorMu(){
        if(ma.mp.isPlaying()){

            ma.mp.stop();
            ma.mp.reset();
        }
    }
}


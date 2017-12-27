package com.example.ulas_.deneme2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Kategoriler extends AppCompatActivity {
    Button btnNormal,btnCehennem;
    String temaSec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriler);
        btnNormal=(Button) findViewById(R.id.buttonNormal);
        btnCehennem=(Button) findViewById(R.id.buttonCehennem);
        ArkaPlanBelirle();
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Kategoriler.this,OyunModuNormal.class));
            }
        });
        btnCehennem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Kategoriler.this,OyunModuCehennem.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArkaPlanBelirle();
    }

    public void ArkaPlanBelirle(){
        temaSec= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("temaSecimi","southpark");
        switch (temaSec){
            case "got":
                btnNormal.setBackground((Drawable)getResources().getDrawable(R.mipmap.gotnormal));
                btnCehennem.setBackground((Drawable)getResources().getDrawable(R.mipmap.gotcehennem));
                break;
            case "southpark":
                btnNormal.setBackground((Drawable)getResources().getDrawable(R.mipmap.normalkategori));
                btnCehennem.setBackground((Drawable)getResources().getDrawable(R.mipmap.spcehennem));
                break;
            case "lotr":
                btnNormal.setBackground((Drawable)getResources().getDrawable(R.mipmap.lotrnormal));
                btnCehennem.setBackground((Drawable)getResources().getDrawable(R.mipmap.lotrcehennem));
                break;
        }
    }
}

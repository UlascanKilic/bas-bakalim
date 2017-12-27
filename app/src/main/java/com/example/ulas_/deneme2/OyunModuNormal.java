package com.example.ulas_.deneme2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class OyunModuNormal extends AppCompatActivity {
    public Context context;
    public Runner runner;
    public FrameLayout fl;
    public ConstraintLayout cl;
    public Byte kontrol=0,gif10Kontrol=0;
    TextView tvPuan, tvTiklama,tvHediye,timerText;
    ImageButton btnCartmanOrta,btnCartmanSag,btnCartmanSol;
    public int puan=0,tiklama=0,hediye=0,sonuc=0;
    public float hiz=700;
    public String puanString,tiklamaString,hediyeString,temaSec;
    public Handler mainHandler;
    public ImageView sayacTikla,sayacTiklama;
    public Drawable imageHitler,imageNormal,hediyeResim,imageArkaPlan,imageSayacTikla,imageSayacTiklama;
    public Random cartmanBelirle,hitlerBelirle,hediyeBelirle;

class Animasyonlar{
        FlingAnimation flingAnimationSol
                = new FlingAnimation(btnCartmanSol, DynamicAnimation.Y);
        FlingAnimation flingAnimationOrta
                = new FlingAnimation(btnCartmanOrta, DynamicAnimation.Y);
        FlingAnimation flingAnimationSag
                = new FlingAnimation(btnCartmanSag, DynamicAnimation.Y);
        public void FlingTanimla(){
            flingAnimationSol.setStartVelocity(hiz);
            flingAnimationSol.setFriction(0.02f);
            flingAnimationOrta.setStartVelocity(hiz);
            flingAnimationOrta.setFriction(0.02f);
            flingAnimationSag.setStartVelocity(hiz);
            flingAnimationSag.setFriction(0.02f);
        }
        public void FlingSolBaslat(){
            if(btnCartmanSol.getY()>1200) {
                flingAnimationSol.cancel();
                btnCartmanSol.setY(0);
                btnCartmanSol.setVisibility(View.VISIBLE);

                if(hediyeBelirle.nextInt(100)%10==0){
                  btnCartmanSol.setImageDrawable(hediyeResim);
                }
                else if(hitlerBelirle.nextInt(345)%7==0){
                    btnCartmanSol.setImageDrawable(imageHitler);
                }
                else{
                    btnCartmanSol.setImageDrawable(imageNormal);
                }
                flingAnimationSol.start();
            }
            else {
                flingAnimationSol.start();
            }
        }
        public void FlingOrtaBaslat(){
            if(btnCartmanOrta.getY()>1200) {
                flingAnimationOrta.cancel();
                btnCartmanOrta.setY(0);
                btnCartmanOrta.setVisibility(View.VISIBLE);
                ButtonBelirle(btnCartmanOrta);
                flingAnimationOrta.start();
            }
            else {
                flingAnimationOrta.start();
            }
        }
        public void FlingSagBaslat(){
            if(btnCartmanSag.getY()>1200) {
                flingAnimationSag.cancel();
                btnCartmanSag.setY(0);
                btnCartmanSag.setVisibility(View.VISIBLE);
                ButtonBelirle(btnCartmanSag);
                flingAnimationSag.start();
            }
            else {
                flingAnimationSag.start();
            }
        }
    }
    class Runner extends Thread {
        Animasyonlar animasyon=new Animasyonlar();

        View v;

        @Override
        public void run() {
            while(kontrol!=1){

              hediyeBelirle=new Random();
              cartmanBelirle=new Random();
                hitlerBelirle=new Random();


                try {
                    Thread.sleep(350);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }


                final int cartman=cartmanBelirle.nextInt(4);

                    final Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {

                                animasyon.FlingTanimla();
                                switch (cartman) {
                                    case 1:
                                        animasyon.FlingSolBaslat();
                                        break;
                                    case 2:
                                        animasyon.FlingOrtaBaslat();
                                        break;
                                    case 3:
                                        animasyon.FlingSagBaslat();
                                        break;
                                }



                        }
                    };
                    mainHandler.post(myRunnable);
            }
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyna);
        cl=(ConstraintLayout)findViewById(R.id.layoutBaba);
        temaSec= getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("temaSecimi","southpark");

        switch (temaSec){
            case "got":
                GotTemasi();

                break;
            case "southpark":
               SouthParkTemasi();

                break;
            case "lotr":
                LotrTemasi();

                break;
        }



        cl.setBackground(imageArkaPlan);
        Tanimlamalar();
        sayacTikla.setBackground(imageSayacTikla);
        sayacTiklama.setBackground(imageSayacTiklama);
        final Intent intent = new Intent(OyunModuNormal.this, OyunSonu.class);

        runner = new Runner();
        runner.start();

        new CountDownTimer(100000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText(millisUntilFinished / 1000+" Saniye Kaldı "  );
            }

            public  void onFinish() {
               kontrol=1;
                timerText.setVisibility(View.INVISIBLE);
               sonuc=(puan*3)+(hediye*2)-(tiklama*2);
                intent.putExtra("oyunmodu","oyunmodunormal");
                intent.putExtra("OyunModuNormalSonuc",sonuc);
                startActivity(intent);

            }

        }.start();

        btnCartmanSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnCartmanSol.getDrawable()==hediyeResim){
                    switch (temaSec)
                    {
                        case "southpark":
                            switch (hediyeBelirle.nextInt(5)){
                                case 1:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman802);
                                    break;
                                case 2:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman803);
                                    break;
                                case 3:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman804);
                                    break;
                                case 4:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman80);
                                    break;
                            }
                            break;
                        case "got":
                            switch (hediyeBelirle.nextInt(4)){
                                case 1:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.khalesi80);
                                    break;
                                case 2:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.nedstark80);
                                    break;
                                case 3:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.stannis80);
                                    break;
                            }
                                break;
                        case "lotr":
                            switch (hediyeBelirle.nextInt(5)){
                                case 1:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.legolas80);
                                    break;
                                case 2:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.gloin80);
                                    break;
                                case 3:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.elrond80);
                                    break;
                                case 4:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.thorin80);
                                    break;
                            }
                            break;


                    }
                    hediye+=1;
                    puan+=5;
                    hiz+=10;
                }
               else if(btnCartmanSol.getDrawable()==imageHitler){
                    TiklamaArttir();
                }else{
                    PuanArttir();
                }
                PuanYaz();
                btnCartmanSol.setVisibility(View.INVISIBLE);

            }
        });
        btnCartmanOrta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(btnCartmanOrta.getDrawable()==imageHitler){
                    TiklamaArttir();
                }else{
                    PuanArttir();
                }
                PuanYaz();
                btnCartmanOrta.setVisibility(View.INVISIBLE);

            }
        });
        btnCartmanSag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnCartmanSag.getDrawable()==imageHitler){
                    TiklamaArttir();
                }else{
                    PuanArttir();
                }
                PuanYaz();

                btnCartmanSag.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void Tanimlamalar(){
        sayacTikla=(ImageView)findViewById(R.id.sayacTikla);
        sayacTiklama=(ImageView)findViewById(R.id.sayacTiklama);
        fl=(FrameLayout)findViewById(R.id.anaSayfaFrame);
        hediyeResim=(Drawable)getResources().getDrawable(R.mipmap.hediyetikla);
        tvHediye=(TextView) findViewById(R.id.textHediye);
        tvPuan=(TextView) findViewById(R.id.textPuan);
        tvTiklama=(TextView) findViewById(R.id.textTiklama);
        btnCartmanSol=(ImageButton) findViewById(R.id.buttonTiklaSol);
        btnCartmanSag=(ImageButton) findViewById(R.id.buttonTiklaSag);
        btnCartmanOrta=(ImageButton) findViewById(R.id.buttonTiklaOrta);
        timerText=(TextView) findViewById(R.id.textSayac);
        mainHandler = new Handler(this.getMainLooper());

    }
    public  void PuanYaz(){
        hediyeString=Integer.toString(hediye);
        puanString=Integer.toString(puan);
        tiklamaString=Integer.toString(tiklama);
        tvTiklama.setText(tiklamaString);
        tvHediye.setText(hediyeString);
        tvPuan.setText(puanString);
    }
    public void PuanArttir(){
        puan+=1;
        hiz+=20;
    }
    public void ButtonBelirle(ImageButton b){
        if(hitlerBelirle.nextInt(345)%3==0){
            b.setImageDrawable(imageHitler);
        }
        else{
            b.setImageDrawable(imageNormal);
        }
    }
    public  void TiklamaArttir(){
        tiklama+=1;
        hiz-=18;
    }
    public void SouthParkTemasi(){
        imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman80);
        imageArkaPlan=(Drawable)getResources().getDrawable(R.mipmap.sparkaplan);
        imageHitler=(Drawable)getResources().getDrawable(R.mipmap.cartman80hitler);
        imageSayacTikla=(Drawable)getResources().getDrawable(R.mipmap.sayacresimcartman);
        imageSayacTiklama=(Drawable)getResources().getDrawable(R.mipmap.tiklamasayac);

    }
    public void GotTemasi(){
        imageNormal=(Drawable)getResources().getDrawable(R.mipmap.john80);
        imageArkaPlan=(Drawable)getResources().getDrawable(R.mipmap.gotarkaplan);
        imageHitler=(Drawable)getResources().getDrawable(R.mipmap.cersei80);
        imageSayacTikla=(Drawable)getResources().getDrawable(R.mipmap.sayacresimjohn);
        imageSayacTiklama=(Drawable)getResources().getDrawable(R.mipmap.cerseitiklamasayac);


    }
    public void LotrTemasi(){
        imageNormal=(Drawable)getResources().getDrawable(R.mipmap.frodo80);
        imageArkaPlan=(Drawable)getResources().getDrawable(R.mipmap.lotrarkaplan);
        imageHitler=(Drawable)getResources().getDrawable(R.mipmap.smeagol80);
        imageSayacTikla=(Drawable)getResources().getDrawable(R.mipmap.frodosayac);
        imageSayacTiklama=(Drawable)getResources().getDrawable(R.mipmap.smeagolsayac);
    }

    }


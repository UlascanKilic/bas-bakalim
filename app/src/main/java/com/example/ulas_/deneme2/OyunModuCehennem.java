package com.example.ulas_.deneme2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.SpringAnimation;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static android.support.animation.SpringForce.DAMPING_RATIO_LOW_BOUNCY;
import static android.support.animation.SpringForce.STIFFNESS_LOW;

public class OyunModuCehennem extends AppCompatActivity {
    ImageButton btnCartmanSagCapraz,btnCartmanSoldanSaga,btnCartmanSagdanAsagi,btnCartmanYukardanAsagi;
    public Handler mainHandler;
    public Runner runner;
    public int X=0;
    public TextView tvPuan, tvTiklama,tvHediye,timerText;
    public byte kontrol=0;
    public int FlingSagYukariYINT,sonuc=0;
    public String puanString,tiklamaString,hediyeString,temaSec;
    public Drawable imageHitler,imageNormal,hediyeResim,imageArkaPlan,imageSayacTikla,imageSayacTiklama;
    public ImageView sayacTikla,sayacTiklama;
    public  int puan=0,tiklama=0,hediye=0,VelocityHiz=1250;
    public Random cartmanBelirle,FlingRandom,hitlerBelirle,hediyeBelirle;
    public ConstraintLayout cl;
    public CountDownTimer countdown;
    public Intent intent;

    class Animasyonlar{


        FlingAnimation flingAnimationYukardanAsagi
                = new FlingAnimation(btnCartmanYukardanAsagi, DynamicAnimation.Y);

        FlingAnimation flingAnimationSagY
                = new FlingAnimation(btnCartmanSagCapraz, DynamicAnimation.Y);

        FlingAnimation flingAnimationSagX
                = new FlingAnimation(btnCartmanSagCapraz, DynamicAnimation.X);

        FlingAnimation flingAnimationSoldanSaga
                = new FlingAnimation(btnCartmanSoldanSaga, DynamicAnimation.X);

        FlingAnimation flingAnimationSagAsagiY
                = new FlingAnimation(btnCartmanSagdanAsagi, DynamicAnimation.Y);

        FlingAnimation flingAnimationSagAsagiX
                = new FlingAnimation(btnCartmanSagdanAsagi, DynamicAnimation.X);


        public void FlingTanimla(){

            flingAnimationSagY.setStartVelocity(-1500);
            flingAnimationSagY.setFriction(0.02f);
            flingAnimationSagX.setStartVelocity(450);
            flingAnimationSagX.setFriction(0.02f);

            flingAnimationSoldanSaga.setStartVelocity(VelocityHiz);
            flingAnimationSoldanSaga.setFriction(0.02f);

            flingAnimationSagAsagiY.setStartVelocity(VelocityHiz);
            flingAnimationSagAsagiY.setFriction(0.02f);
            flingAnimationSagAsagiX.setStartVelocity(700);
            flingAnimationSagAsagiX.setFriction(0.02f);

            flingAnimationYukardanAsagi.setStartVelocity(VelocityHiz);
            flingAnimationYukardanAsagi.setFriction(0.02f);
            hitlerBelirle=new Random();
            FlingRandom=new Random();
            hediyeBelirle=new Random();

        }

        public void FlingSagCapraz(){


            if(btnCartmanSagCapraz.getY()<0) {

                btnCartmanSagCapraz.setVisibility(View.VISIBLE);
                flingAnimationSagY.cancel();
                flingAnimationSagX.cancel();
                FlingSagYukariYINT=(600+(int) (Math.random()*1500));
                btnCartmanSagCapraz.setY(FlingSagYukariYINT);
                btnCartmanSagCapraz.setX(FlingRandom.nextInt(300));
                if(hitlerBelirle.nextInt(345)%7==0){
                    btnCartmanSagCapraz.setImageDrawable(imageHitler);
                }
                else{
                    btnCartmanSagCapraz.setImageDrawable(imageNormal);
                }
                flingAnimationSagY.start();
                flingAnimationSagX.start();
            }
            else {
                flingAnimationSagY.start();
                flingAnimationSagX.start();

            }
        }

        public  void FlingSoldanSaga(){


            if(btnCartmanSoldanSaga.getX()>900){
                btnCartmanSoldanSaga.setVisibility(View.VISIBLE);
                flingAnimationSoldanSaga.cancel();
                btnCartmanSoldanSaga.setX(0);
                btnCartmanSoldanSaga.setY(FlingRandom.nextInt(1100));
                if(hitlerBelirle.nextInt(345)%7==0){
                    btnCartmanSoldanSaga.setImageDrawable(imageHitler);
                }
                else{
                    btnCartmanSoldanSaga.setImageDrawable(imageNormal);
                }
                flingAnimationSoldanSaga.start();
            }
            else {
                flingAnimationSoldanSaga.start();
            }
        }
        public  void FlingSagdanAsagi(){

            if(btnCartmanSagdanAsagi.getY()>1500) {
              btnCartmanSagdanAsagi.setVisibility(View.VISIBLE);
                flingAnimationSagAsagiY.cancel();
                flingAnimationSagAsagiX.cancel();
                if(hitlerBelirle.nextInt(345)%7==0){
                    btnCartmanSagdanAsagi.setImageDrawable(imageHitler);
                }
                else{
                    btnCartmanSagdanAsagi.setImageDrawable(imageNormal);
                }
                btnCartmanSagdanAsagi.setY(FlingRandom.nextInt(400));
                btnCartmanSagdanAsagi.setX(FlingRandom.nextInt(400));
                flingAnimationSagAsagiY.setStartVelocity(FlingRandom.nextInt(1500));
                flingAnimationSagAsagiX.setStartVelocity(FlingRandom.nextInt(700));

                flingAnimationSagAsagiY.start();
                flingAnimationSagAsagiX.start();
            }
            else {
                flingAnimationSagAsagiY.start();
                flingAnimationSagAsagiX.start();

            }
        }
        public  void FlingYukardanAsagi(){

            if(btnCartmanYukardanAsagi.getY()>1500){
                btnCartmanYukardanAsagi.setVisibility(View.VISIBLE);
              flingAnimationYukardanAsagi.cancel();
                btnCartmanYukardanAsagi.setY(0);
                btnCartmanYukardanAsagi.setX(FlingRandom.nextInt(850));
                if(hediyeBelirle.nextInt(100)%10==0){
                    btnCartmanYukardanAsagi.setImageDrawable(hediyeResim);
                }
                else if(hitlerBelirle.nextInt(345)%7==0){
                    btnCartmanYukardanAsagi.setImageDrawable(imageHitler);
                }
                else{
                    btnCartmanYukardanAsagi.setImageDrawable(imageNormal);
                }
                flingAnimationYukardanAsagi.start();
            }
            else
            {
                flingAnimationYukardanAsagi.start();
            }
        }

    }
    class Runner extends Thread {
        OyunModuCehennem.Animasyonlar animasyon=new OyunModuCehennem.Animasyonlar();
        View v;

        @Override
        public void run() {
            while(kontrol!=1){
                try {
                    Thread.sleep(350);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                animasyon.FlingTanimla();
                cartmanBelirle=new Random();
                final Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        switch (cartmanBelirle.nextInt(5)) {
                            case 1:
                                animasyon.FlingSagCapraz();
                                break;
                            case 2:
                                animasyon.FlingSoldanSaga();
                                break;
                            case 3:
                                animasyon.FlingSagdanAsagi();
                                break;
                            case 4:
                                animasyon.FlingYukardanAsagi();
                                break;
                        }
                        }
                };
                mainHandler.post(myRunnable);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_modu_cehennem);
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
     countdown= new CountDownTimer(100000, 1000) {

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

        cl=(ConstraintLayout)findViewById(R.id.layoutBaba);
        cl.setBackground(imageArkaPlan);
        btnCartmanSagCapraz=(ImageButton) findViewById(R.id.buttonTiklaSagCapraz);
        btnCartmanSoldanSaga=(ImageButton) findViewById(R.id.buttonTiklaSoldanSaga);
        btnCartmanSagdanAsagi=(ImageButton) findViewById(R.id.buttonTiklaSagdanAsagi);
        btnCartmanYukardanAsagi=(ImageButton) findViewById(R.id.buttonTiklaYukardanAsagi);
        hediyeResim=(Drawable)getResources().getDrawable(R.mipmap.hediyetikla);
        sayacTikla=(ImageView)findViewById(R.id.sayacTikla);
        sayacTiklama=(ImageView)findViewById(R.id.sayacTiklama);
        timerText=(TextView)findViewById(R.id.timerText);
        tvHediye=(TextView) findViewById(R.id.textHediye);
        tvPuan=(TextView) findViewById(R.id.textPuan);
        tvTiklama=(TextView) findViewById(R.id.textTiklama);

         intent = new Intent(OyunModuCehennem.this, OyunSonu.class);


        mainHandler = new Handler(this.getMainLooper());
        sayacTikla.setImageDrawable(imageSayacTikla);
        sayacTiklama.setImageDrawable(imageSayacTiklama);
        runner = new Runner();
        runner.start();
        btnCartmanSagCapraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         btnCartmanSagCapraz.setVisibility(View.INVISIBLE);
                if(btnCartmanSagCapraz.getDrawable()==imageHitler){
                    tiklama+=1;
                    HitlerKontrol();

                }else{
                    puan+=1;

                }
                PuanYaz();
            }
        });
        btnCartmanSoldanSaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCartmanSoldanSaga.setVisibility(View.INVISIBLE);
                if(btnCartmanSoldanSaga.getDrawable()==imageHitler){
                    tiklama+=1;
                    HitlerKontrol();

                }else{
                    puan+=1;

                }
                PuanYaz();
            }
        });
        btnCartmanSagdanAsagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCartmanSagdanAsagi.setVisibility(View.INVISIBLE);
                if(btnCartmanSagdanAsagi.getDrawable()==imageHitler){
                    tiklama+=1;
                    HitlerKontrol();

                }else{
                    puan+=1;

                }
                PuanYaz();

            }
        });
        btnCartmanYukardanAsagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCartmanYukardanAsagi.setVisibility(View.INVISIBLE);
                if(btnCartmanYukardanAsagi.getDrawable()==hediyeResim){

                    switch (temaSec)
                    {
                        case "southpark":
                            switch (hediyeBelirle.nextInt(5)){
                                case 1:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman80);
                                    break;
                                case 2:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman802);
                                    break;
                                case 3:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman803);
                                    break;
                                case 4:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.cartman804);
                                    break;
                            }
                            break;
                        case "got":
                            switch (hediyeBelirle.nextInt(4)){
                                case 1:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.khalesi80);
                                    break;
                                case 2:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.stannis80);
                                    break;
                                case 3:
                                    imageNormal=(Drawable)getResources().getDrawable(R.mipmap.nedstark80);
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

                }
                else if(btnCartmanYukardanAsagi.getDrawable()==imageHitler){
                    tiklama+=1;
                  HitlerKontrol();

                }else{
                    puan+=1;
                }
                PuanYaz();
            }
        });
    }
    public  void PuanYaz(){
        hediyeString=Integer.toString(hediye);
        puanString=Integer.toString(puan);
        tiklamaString=Integer.toString(tiklama);
        tvTiklama.setText(tiklamaString);
        tvHediye.setText(hediyeString);
        tvPuan.setText(puanString);
    }
    public void HitlerKontrol(){
        if(tiklama==3){
            kontrol=1;
            sonuc=(puan*3)+(hediye*2)-(tiklama*2);
            intent.putExtra("oyunmodu","oyunmoducehennem");
            intent.putExtra("OyunModuCehennemSonuc",sonuc);
            countdown.cancel();
            startActivity(intent);
        }
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

package com.example.ulas_.deneme2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ulas_ on 12/24/2017.
 */

public class Database extends SQLiteOpenHelper {
    //Bu Tanımlamalar olmadanda program çalışır ama hardcoded olmasını istemiyoruz.
    private static String V_Name = "Oyun";
    private static Integer V_Surum = 1;
    private static String V_Tablo = "Puanlar";
    final private static String ID = "id";
    final private static String NAME = "name";
    final private static String POINT = "point";
    final private static String GMOD = "gmod";
    private static boolean dgr = false;

     private static String V_Profil = "Atsa";
    SQLiteDatabase dbr = this.getReadableDatabase();
    SQLiteDatabase dbw = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    public Database(Context context,String Nameilk) {
        super(context, V_Name, null,V_Surum);
    V_Profil=Nameilk;
        olustur();

    }
    public Database(Context context) {
        super(context, V_Name, null,V_Surum);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + V_Tablo + " ( "+ ID +" int auto increment, "+ NAME +" text not null, " + POINT + " integer not null, " + GMOD + " int not null ) ");

        Log.e("Database"," Oluşturuldu");
    }

    public void olustur()
    {
        Ekle(V_Profil,"0","3");
        Log.e("Database"," 3");
        Ekle(V_Profil,"0","2");
        Log.e("Database"," 2");
        Ekle(V_Profil,"0","1");
        Log.e("Database"," 1");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table If Exists " + V_Tablo);//Tabloyu dropluyo
        onCreate(db);//Yeniden oluşturuyor
        Log.e("Database","Güncellendi.");
    }
    public void Ekle(String Name, String Point, String Gmod)
    {
        Cursor listele = dbw.rawQuery("SELECT *  FROM " + V_Tablo + " where gmod = '" + Gmod + "' and name = '" + Name + "' and point < " + Point,null);
        //listele cursor'una girilen verilerin eşit ve puanın büyük olup olmadığını sorgulatan sql kodunu okutuyoruz.
        if (listele.moveToFirst())//eğer 1 kere okumuşsa
        {
            Guncelle(Name,Point, Gmod);//güncelle
        }
        else//eğer hiç okumamışsa sorguyu içi boş ise ve girilen veriden büyük veri yok ise
        {
            //böyle bir veri varmı diye tekrar kontrol ediyoruz. eğer veri yok ise veriyi insert ile ekletiyoruz ama eğer veri var ise daha öncekinden küçük olduğu için ekletmiyoruz.
            Cursor listele2 = dbw.rawQuery("SELECT *  FROM " + V_Tablo + " where gmod = '" + Gmod + "' and name = '" + Name + "'",null);
            if (listele2.moveToFirst()) {

         }
            else
            {                Log.e("kayit","yok.");

//veri hiç yoksa eklediğimiz kısım
                cv.put(NAME, Name.trim());
                cv.put(POINT, Point.trim());
                cv.put(GMOD, Gmod.trim());//databasedeki verileri kullanıcının girdiği verilere eşitliyoruz.
                dbw.insert(V_Tablo, null, cv);//insert işlemini yaptığımız metod.
                Log.e("Veri", " Eklendi  + Name + Point + Gmod");}
        }

    }
    public void Sil(String Name)//verileri sildiğimiz kısım upgrade'de anlatıyorum
    {onUpgrade(dbw,1,2);}

    public void Guncelle (String sName, String sPoint, String sGmod)
    {//verileri aldık.
        cv.put(POINT, sPoint); //kullanıcının girdiğine eşiştledik
        String strSQL = "UPDATE " +  V_Tablo + " SET point = " + sPoint + " WHERE name = '"+ sName + "' and gmod = '" + sGmod + "'";
        dbw.execSQL(strSQL);//update sorgusunu çalıştırdık.
        Log.e("Veri"," Güncellendi" + sName + sPoint + sGmod);
    }
    List<String> Listele(String GMOD)
    {//veriler adında bir dizi tanımladık.
        List<String> Veriler =  new ArrayList<String>();//listele adında bir cursor tanımladık ve veritabanından ilk 10 kişinin seçilen oyun modundaki tabloasunu çektik
        Cursor listele = dbw.rawQuery("SELECT *  FROM " + V_Tablo + " where gmod = " + GMOD + " ORDER BY point DESC LIMIT 10 ",null);
        int i = 0;
        if (listele.moveToFirst())//eğer ilk veri gelmişse
        {
            do
            {//veriler dizisine ekle sıralamasını adını ve puanını
                i++; Veriler.add(i + ". " + listele.getString(1) + ", Puanı = " + listele.getInt(2));
            }
            while (listele.moveToNext());//eğer hala geliyor sa do while kullanıp tekrar döngüye gir.
            if (i <= 10)
            {
                do
                {
                    i++;   Veriler.add("Henüz Kayıt Oluşturulmadı");
                }while (i <= 10);

            }
        }
        Log.e("Veriler"," Listelendi");
        return Veriler;//çıktı olarak diziyi ver.

    }

    String Profil(String GMOD)
    {//girilen oyun modundaki ve profil ismine göre o kişinin ismini puanını ve oyun modunu döndüren metod
        String son = "";
        Cursor sonuc = dbw.rawQuery("Select * From " + V_Tablo + " where gmod = " + GMOD + " and name = '" + V_Profil + "' ", null, null);
        sonuc.moveToNext();//eğer değer dönmüşse
        if (sonuc == null)//ve değer boş ise
        {//buraya çok gerek yok.
            Log.e("Veriler", " Profil Verileri Gelmedi");
            //kullanıcı ilk girişişnde oncreate de kendini 1 kere yaratıcağı için her türlü dolu gelicek.
        }
        else
        {//çalıştı ve verileri eşitledi.
            String nm = sonuc.getString(1);
            Integer pnt = sonuc.getInt(2);
            Integer gmod = sonuc.getInt(3);
            son = nm + " " + pnt.toString() + " " + gmod.toString();
            Log.e("Veriler", " Profil Verileri Geldi");
        }
        return son;//verileri döndürdük.
    }
}

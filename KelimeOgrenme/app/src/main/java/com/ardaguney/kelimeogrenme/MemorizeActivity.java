package com.ardaguney.kelimeogrenme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MemorizeActivity extends AppCompatActivity {
String language;
ArrayList<String> words;
ArrayList<String> words2;
    Random random;
    TextView word1text , word2text;
    int randomint;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);
        //get Language
        Intent intent = getIntent();
        language = intent.getStringExtra("language");
        //get words
        words = new ArrayList<>();
        words2 = new ArrayList<>();
        getdata();
        //randomwords
         word1text = findViewById(R.id.textView);
         word2text = findViewById(R.id.textView2);
         random = new Random();
         try {
             randomint = random.nextInt(words.size());
             word1text.setText(words.get(randomint));
             word2text.setText(words2.get(randomint));
         }catch (Exception  k){
             k.printStackTrace();

         }

    }
    public void getdata(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Database",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + language ,null);
            int  wordIx = cursor.getColumnIndex("word");
            int  word2Ix = cursor.getColumnIndex("word2");
            String word, word2 ;
            while (cursor.moveToNext()) {
                word = cursor.getString(wordIx);
                word2 = cursor.getString(word2Ix);
                words.add(word);
                words2.add(word2);
            }
            cursor.close();

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage().toString());
        }



    }
    public void okword(View view){

         randomint = random.nextInt(words.size());
        word1text.setText(words.get(randomint));
        word2text.setText(words2.get(randomint));
    }

}
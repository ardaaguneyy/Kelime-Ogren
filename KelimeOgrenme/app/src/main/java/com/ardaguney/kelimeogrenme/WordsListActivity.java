package com.ardaguney.kelimeogrenme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WordsListActivity extends AppCompatActivity {
    String language;
    ListView listView;
    ArrayList<String> words;
    ArrayAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        Intent intent =getIntent();
        language = intent.getStringExtra("Language");
        //show list
        listView = findViewById(R.id.listview);
        words = new ArrayList<>();
        showlist();
        adapter = new ArrayAdapter(WordsListActivity.this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(adapter);

    }


    public void showlist(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Database",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + language ,null);
            int  wordIx = cursor.getColumnIndex("word");
            int  word2Ix = cursor.getColumnIndex("word2");
            String word, word2 ;
            while (cursor.moveToNext()) {
                word = cursor.getString(wordIx);
                word2 = cursor.getString(word2Ix);
                words.add("   " + word + "   :  " + word2);
            }
            cursor.close();

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage().toString());
        }
        
    }



}
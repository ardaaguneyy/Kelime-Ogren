package com.ardaguney.kelimeogrenme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    String language ;
    int wordnumber = 0;
    TextView wordnumbertext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Get Language
        Intent intent = getIntent();
        language = intent.getStringExtra("language");
        System.out.println(language);

        //Set_main_flag
        setmainflag();
        //numberofwords
        wordnumbertext = findViewById(R.id.numberofwords);
        showwordsnumber();

    }

    public void memorizebutton(View view){
        Intent intent = new Intent(MainActivity2.this,MemorizeActivity.class);
        intent.putExtra("language",language);
        startActivity(intent);


    }
    public void addword(View view){

        EditText wordedittext, word2edittext;
        String word , word2 ;
        wordedittext = findViewById(R.id.wordedittext);
        word2edittext = findViewById(R.id.wordedittext2);

        word = wordedittext.getText().toString();
        word2 = word2edittext.getText().toString();

        if (!word.matches("") && !word2.matches("")){
            // veri tabanına ekle

            DataBaseManagement dataBaseManagement = new DataBaseManagement();
            dataBaseManagement.OpenDatabase(MainActivity2.this);
            dataBaseManagement.AddDatabase(language,word,word2);

            Toast.makeText(MainActivity2.this, "Kelime Eklendi", Toast.LENGTH_SHORT).show();
            wordedittext.setText("");
            word2edittext.setText("");
            showwordsnumber();
        }

    }
    public void setmainflag(){
        ImageView imageView = findViewById(R.id.imageView);
        if (language.matches("German")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.germanflag);
            imageView.setImageDrawable(myDrawable);
        }else{
            Drawable myDrawable = getResources().getDrawable(R.drawable.englandflag);
            imageView.setImageDrawable(myDrawable);

        }


    }
    public void showwordsnumber(){
        try {
            wordnumber = 0;
            SQLiteDatabase database = this.openOrCreateDatabase("Database",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + language ,null);
            int  wordIx = cursor.getColumnIndex("word");
            while (cursor.moveToNext()) {
                wordnumber+=1 ;
            }
            cursor.close();
            wordnumbertext.setText("Kelime sayısı : " + wordnumber);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage().toString());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.menulayout,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.words_menu_button ){
            Intent intent = new Intent(getApplicationContext(),WordsListActivity.class);
            intent.putExtra("Language",language);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.change_language){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
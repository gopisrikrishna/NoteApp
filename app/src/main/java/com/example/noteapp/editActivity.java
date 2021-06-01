package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class editActivity extends AppCompatActivity {
    int noteid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText edit =(EditText)findViewById(R.id.edit);
        Intent intent =getIntent();
        noteid =intent.getIntExtra("noteid",-1);
        if(noteid != -1){
            edit.setText(MainActivity.notes.get(noteid));
        }else{
            MainActivity.notes.add("");
            noteid =MainActivity.notes.size() -1;
            MainActivity.aa.notifyDataSetChanged();
        }
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteid,String.valueOf(charSequence));
                MainActivity.aa.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.noteapp" , Context.MODE_PRIVATE);
                HashSet<String> set =new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
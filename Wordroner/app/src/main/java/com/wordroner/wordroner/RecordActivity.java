package com.wordroner.wordroner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;


public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        final Button btn_record = (Button) findViewById(R.id.btn_record);

        btn_record.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                ArrayList<String> words = new ArrayList<String>(
                        Arrays.asList("123", "123", "a", "the", "cat", "cat", "cat", "dog", "apple", "a", "starbucks")); //this is for test

                //
                //add google speech API here
                //

                WordMap wordmap = new WordMap(words);

                String uid = "none";
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    uid = user.getUid();
                }

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(uid);


                myRef.child("object").setValue(wordmap.ExtractWords());
                //myRef.child("words").child("3").setValue("hello world2");


                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}

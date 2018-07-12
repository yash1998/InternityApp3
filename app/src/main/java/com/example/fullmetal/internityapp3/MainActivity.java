package com.example.fullmetal.internityapp3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{

    EditText login_username, login_password;
    String username, password, name, stream, post;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);

        myRef = FirebaseDatabase.getInstance().getReference("members");
    }

    public void login_into_app(View v){
        username = login_username.getText().toString();
        password = login_password.getText().toString();

        if(username.contentEquals("") && password.contentEquals("")){
            Toast.makeText(this, "Please Enter Username and Password!", Toast.LENGTH_SHORT).show();
        }
        else if(username.contentEquals("") && !password.contentEquals("")){
            Toast.makeText(this, "Please Enter Username!", Toast.LENGTH_SHORT).show();
        }
        else if(!username.contentEquals("") && password.contentEquals("")){
            Toast.makeText(this, "Please Enter Password!", Toast.LENGTH_SHORT).show();
        }
        else{
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot childSnapShot: dataSnapshot.getChildren()){
                        if(childSnapShot.child("username").getValue().toString().contentEquals(username) && childSnapShot.child("password").getValue().toString().contentEquals(password)){
                            name = childSnapShot.child("name").getValue().toString();
                            stream = childSnapShot.child("stream").getValue().toString();
                            post = childSnapShot.child("post").getValue().toString();

                            Log.i("yash", ""+name+" "+stream+" "+post);

                            if(post.contentEquals("coach")){
                                startActivity(new Intent(MainActivity.this, CoachActivity.class));
                            }
                            if(post.contentEquals("intern")){
                                startActivity(new Intent(MainActivity.this, InternActivity.class));
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.i("yash","exited" + databaseError.toString());
                }
            });
        }
    }
}
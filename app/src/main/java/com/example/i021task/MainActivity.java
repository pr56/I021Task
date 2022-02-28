package com.example.i021task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://i021task-b5626-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView emailTxt = (TextView) findViewById(R.id.emailfield);
        TextView passwordTxt = (TextView) findViewById(R.id.passwordField);

        MaterialButton loginButton = (MaterialButton) findViewById(R.id.loginBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailVar = emailTxt.getText().toString();
                final String passwordVar = passwordTxt.getText().toString();

                myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(emailVar))
                        {

                            final String getPassword = snapshot.child(emailVar).child("password").getValue(String.class);
//
                            if(  getPassword.equals(passwordVar))
                            {

                                Toast.makeText(MainActivity.this, "logged in" ,Toast.LENGTH_SHORT).show();
                                Intent  i=new Intent(MainActivity.this, ForumActivity.class);
                                i.putExtra("user", emailVar);
                                startActivity(i);
//
                            }
                            else
                            {

                                Toast.makeText(MainActivity.this, "login failed. Please try again" ,Toast.LENGTH_SHORT).show();

                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                        final String getEmail = snapshot.child(emailVar).child("email").getValue(String.class);
//                        final String getPassword = snapshot.child(passwordVar).child("password").getValue(String.class);
//                        Toast.makeText(MainActivity.this, "logged in" ,Toast.LENGTH_SHORT).show();
//
//                        if(getEmail.equals(emailVar) && getPassword.equals(passwordVar))
//                        {
//
//                            Toast.makeText(MainActivity.this, "logged in" ,Toast.LENGTH_SHORT).show();
//                            openForumActivity();
//                        }
//                        else
//                        {
//
//                            Toast.makeText(MainActivity.this, "login failed. Please try again" ,Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
////                    }
//                });
//                if(emailTxt.getText().toString().equals("admin") && passwordTxt.getText().toString().equals("123"))
//                {
//                    Toast.makeText(MainActivity.this, "logged in" ,Toast.LENGTH_SHORT).show();
//                    openForumActivity();
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "logged failed" ,Toast.LENGTH_SHORT).show();
//
//                }
            }

            public void openForumActivity()
            {
                startActivity( new Intent(MainActivity.this, ContactActivity.class));
//                startActivity(openPage);
            }
        });


    }
}
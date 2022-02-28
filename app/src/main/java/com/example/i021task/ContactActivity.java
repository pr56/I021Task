package com.example.i021task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {

    DatabaseReference myRef =   FirebaseDatabase.getInstance("https://i021task-b5626-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    String key = myRef.push().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        MaterialButton forumBtn = (MaterialButton) findViewById(R.id.studentFormBtn);
        MaterialButton logoutBtn = (MaterialButton) findViewById(R.id.logoutBtn);
        MaterialButton submitInquiry = (MaterialButton) findViewById(R.id.submitBtn);

        final EditText nameField = findViewById(R.id.nameField);
        final EditText emailField =findViewById(R.id.email);
        final EditText question =findViewById(R.id.inquiryField);

        submitInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nameTxt = nameField.getText().toString();
                final String emailTxt = emailField.getText().toString();
                final String questionTxt = question.getText().toString();

                if(nameTxt.isEmpty() && emailTxt.isEmpty() && questionTxt.isEmpty() )
                {
                    Toast.makeText(ContactActivity.this, "Insert all details" ,Toast.LENGTH_SHORT).show();

                }
                else
                {
                    myRef.child("asking").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            myRef.child("asking").child(key).child("name").setValue(nameTxt);
                            myRef.child("asking").child(key).child("question").setValue(questionTxt);


                            Toast.makeText(ContactActivity.this, "We will reach out to you shortly" ,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }


//


            }
        });

        forumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openForumActivity();
            }

            public void openForumActivity()
            {
                startActivity( new Intent(ContactActivity.this, ForumActivity.class));
//                startActivity(openPage);
            }


        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { goToHomePage();}

            public void goToHomePage()
            {
                startActivity( new Intent(ContactActivity.this, MainActivity.class));
            }
        });

    }
}
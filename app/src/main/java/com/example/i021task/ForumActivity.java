package com.example.i021task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {



    public DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    DatabaseReference myRef =   FirebaseDatabase.getInstance("https://i021task-b5626-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    String key = myRef.push().getKey();

    ListView listView;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String > arrayAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        listView = (ListView) findViewById(R.id.listviewcard);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList );
        listView.setAdapter(arrayAdapter);

        myRef.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String info = snapshot.getValue(Student.class).toString();
                arrayList.add(info);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        MaterialButton contactBtn = (MaterialButton) findViewById(R.id.contactBtn);
        MaterialButton logoutBtn = (MaterialButton) findViewById(R.id.logoutBtn);
        Button sendMessageBtn = (Button) findViewById(R.id.messageBtn);

        Intent intent = getIntent();
        final String value = intent.getStringExtra("user");



        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText info = (EditText) findViewById(R.id.messageField);
                final String infoText = info.getText().toString();

                myRef.child("messages").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        myRef.child("messages").child(key).child("name").setValue(value);
                        myRef.child("messages").child(key).child("question").setValue(infoText);
                        myRef.child("messages").child(key).child("id").setValue(key);

                        Toast.makeText(ForumActivity.this, "Message added" ,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactActivity();
            }

            public void openContactActivity()
            {
                startActivity( new Intent(ForumActivity.this, ContactActivity.class));
            }


        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { goToHomePage();}

            public void goToHomePage()
            {
                startActivity( new Intent(ForumActivity.this, MainActivity.class));
            }
        });




    }


}
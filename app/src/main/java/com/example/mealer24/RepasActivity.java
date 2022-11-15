package com.example.mealer24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class RepasActivity extends AppCompatActivity {
    private TextView mesRepas_in_text;
    private ListView listeDeRepas;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonVoirLeMenuDuJour;
    private DatabaseReference db;
    private DatabaseReference dbRepas;
    private LinkedList<Repas> lesRepas;
    private ArrayAdapter<Repas> adapter;

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);

        //gets the email of active user
        userEmail = getIntent().getStringExtra("email");

        mesRepas_in_text=findViewById(R.id.texteMesRepas);
        listeDeRepas=findViewById(R.id.listeDeRepas);
        buttonAdd=findViewById(R.id.addRepas_btn);
        buttonRemove=findViewById(R.id.deleteRepas_btn);
        buttonVoirLeMenuDuJour=findViewById(R.id.repasDuJour_btn);

        db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, userEmail);
        //path for the cuisinier's repas
        dbRepas = db.child("mesRepas");

        lesRepas = new LinkedList<Repas>();


        buttonVoirLeMenuDuJour.setOnClickListener(this::sendToAddRepasPage);
    }

    @Override
    protected void onStart() {
        super.onStart();

        displayRepas();

    }



    private void displayRepas(){
        dbRepas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lesRepas.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Repas repas = dataSnapshot.getValue(Repas.class);
                    lesRepas.add(repas);
                }
                //creates adapter
                RepasList repasAdapter = new RepasList(RepasActivity.this, lesRepas);

                //attach the adapter to the repas list view
                listeDeRepas.setAdapter(repasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    //show popup dialog box


    //update repas



    //remove repas


    //Method to redirect user to add repas page.
    public void sendToAddRepasPage(View view){

        Intent intent = new Intent(this, AddRepasActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }




}
package com.example.mealer24;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * This is the home screen for a client account where
 * they can see the best chef depending on their ratings,
 * their previous orders, the order status of their active (no status by chef) orders
 * and where they can order and search for meals
 * */

public class HomeScreenClient extends HomeScreen {
    private Button order_meal;
    private ListView viewListOfMeals;
    private Button order_status;
    private ArrayList<Cuisinier> listDeCuisinier;
    private SearchView lookUpBarView;
    private String userEmail;
    private Button my_orders;

    private ArrayList<CuisinierEtRepasInfo> displayListChoice;
    private ArrayList<Repas> listeDeRepasDansUnCuisinier;
    private DatabaseReference cuisinierDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_client);

        //gets the email of active user
        userEmail = getIntent().getStringExtra("email");
        viewListOfMeals = findViewById(R.id.listOfMealsId);
        order_status = findViewById(R.id.OrderRequests);
        lookUpBarView = findViewById(R.id.repasSearchId);
        listDeCuisinier = new ArrayList<Cuisinier>();
        order_meal = findViewById(R.id.OrderMeal);
        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

        cuisinierDatabase = FirebaseDatabase.getInstance().getReference("Users").child("Cuisiniers");
        putSetAllCuisinierInfo();
        displayListChoice = new ArrayList<CuisinierEtRepasInfo>();
        listeDeRepasDansUnCuisinier = new ArrayList<Repas>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mealmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();

        searchView.setQueryHint("Que voulez-vous manger");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //called when the user types in text and press enter
            public boolean onQueryTextSubmit(String query) {
                displayListChoice.clear();
                //for each cuisinier, go through each repas.
                for (Cuisinier i:listDeCuisinier){
                    List<Repas> listOfRepas = i.getListOfAllRepas();
                    //find query through nom de repas, type de repas, type de cuisinie
                    for (Repas j:listOfRepas){
                        if (j.getNomDuRepas()==query || j.getTypeDeCuisine()==query||j.getTypeDeRepas()==query){
                            CuisinierEtRepasInfo addThisToList = new CuisinierEtRepasInfo(j,i);
                            displayListChoice.add(addThisToList);
                        }

                    }

                }

                // put adapter for the display

                //non
                MealLayout mealAdapter = new MealLayout(HomeScreenClient.this, displayListChoice);

                //attach the adapter to the repas list view
                 viewListOfMeals.setAdapter(mealAdapter);

                return false;
            }
            //call when user is typing on ever change
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        }) ;
        return super.onCreateOptionsMenu(menu);
    }

    public void putSetAllCuisinierInfo() {
        cuisinierDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDeCuisinier.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Cuisinier currentCuisinier = dataSnapshot.getValue(Cuisinier.class);

                    //bringing all active cuisisinier locally
                    if (currentCuisinier.getStatusOfCook()=="Travaille"){
                        listDeCuisinier.add(currentCuisinier);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
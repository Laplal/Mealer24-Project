package com.example.mealer24;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the home screen for a chefs account where
 * they can see the best chef depending on their ratings
 * and prepare meals and see all their order request as well as their previous meals
 * */
public class HomeScreenChef extends HomeScreen {
    private Button prepare_meal;
    private Button best_chefs;
    private Button order_requests;
    private Button previous_meals;

    private Button my_meals;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_chef);

        best_chefs = findViewById(R.id.BestChefs);
        order_requests = findViewById(R.id.OrderRequests);
        previous_meals = findViewById(R.id.PrevMeals);
        prepare_meal = findViewById(R.id.Meal);

        logoutBtn.setOnClickListener(view -> logoutUser());

    }
}

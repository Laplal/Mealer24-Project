package com.example.mealer24;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MealLayout extends ArrayAdapter<CuisinierEtRepasInfo> {
    /**
     * to DO: Adapter in homesSvreenClient
     *
     * This class gets an array of things in the adpater. The thing is of datadtye: CuisnierEtMealInfo
     *
     * This class allows us to customize the list view of repas with cuisinier Info
     * see activity_meal_layout.xml to customize the layout
     */
    private SparseBooleanArray selectedItem;

    private Activity context;

    List<CuisinierEtRepasInfo> lesInfosMeals;

    public MealLayout(Activity context,List<CuisinierEtRepasInfo> lesInfosMeals){
        super(context,R.layout.activity_meal_layout,lesInfosMeals);
        this.context = context;
        this.lesInfosMeals = lesInfosMeals;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.activity_meal_layout, null, true);

        TextView textViewRepasName = (TextView) listViewItem.findViewById(R.id.repasNameId);
        TextView textViewPrix = (TextView) listViewItem.findViewById(R.id.repasPrixId);
        TextView textViewRepasType = (TextView) listViewItem.findViewById(R.id.repasTypeId);
        TextView textViewCuisineType = (TextView) listViewItem.findViewById(R.id.typeCuisineId);
        TextView textViewIngredients = (TextView) listViewItem.findViewById(R.id.ingredientsId);
        TextView textViewAllergies = (TextView) listViewItem.findViewById(R.id.allergiesId);
        TextView textViewRepasDescription = (TextView) listViewItem.findViewById(R.id.repasDescriptionId);
        TextView textViewCookName = (TextView) listViewItem.findViewById(R.id.cuisinierNomId);
        TextView textViewCookAddress = (TextView) listViewItem.findViewById(R.id.cuisinierAddressId);
        TextView textViewCuisinierDescription = (TextView) listViewItem.findViewById(R.id.cuisinerDescriptionId);

        /**
         * Set the info for each Text view for one item of datatype CuisinierEtRepasInfo
         *

        */
        CuisinierEtRepasInfo cri = lesInfosMeals.get(position);
        //okay here we go x 10
        textViewRepasName.setText(cri.getRepasNom());
        textViewPrix.setText("$"+cri.getLePrix().toString());
        textViewRepasType.setText(cri.getTypeDeRepas());
        textViewCuisineType.setText(cri.getTypeDeCuisine());
        textViewIngredients.setText(cri.getListeIngredients());
        textViewAllergies.setText(cri.getListeAllergies());
        textViewRepasDescription.setText(cri.getDescriptionRepas());
        textViewCookName.setText(cri.getNomCuisinier());
        textViewCookAddress.setText(cri.getCuisinierAddress());
        textViewCuisinierDescription.setText(cri.getCuisinierDescription());

        return listViewItem;
    }

}
package com.example.recipesapp.search;

import com.example.recipesapp.model.Meals;

import java.util.List;

public interface SearchView {

    void showLoading();
    void hideLoading();
   void setMeals(List<Meals.Meal> meals);
   void onErrorLoading(String message);

}

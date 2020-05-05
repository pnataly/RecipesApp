
package com.example.recipesapp.detail;

import com.example.recipesapp.model.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meals);
    void onErrorLoading(String message);
}
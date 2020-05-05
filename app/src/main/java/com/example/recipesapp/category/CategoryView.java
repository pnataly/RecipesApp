
package com.example.recipesapp.category;

import com.example.recipesapp.model.Meals;
import java.util.List;

public interface CategoryView {

    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);

}
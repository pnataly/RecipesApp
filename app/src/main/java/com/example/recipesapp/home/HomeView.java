
package com.example.recipesapp.home;


import com.example.recipesapp.model.Categories;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);

}


package com.example.recipesapp.api;

import com.example.recipesapp.model.Categories;
import com.example.recipesapp.model.Meals;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("random.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealsByCategory(@Query("c") String category);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String name);

    @GET("filter.php")
    Call<Meals> getMealsByIngredient(@Query("i") String ingredient);
}

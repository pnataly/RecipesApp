
package com.example.recipesapp.search;


import androidx.annotation.NonNull;

import com.example.recipesapp.Utils.Utils;
import com.example.recipesapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {

    private SearchView view;

    public SearchPresenter(SearchView view) {
        this.view = view;
    }

    void getMealsByIngredient(String ingredient){

        view.showLoading();
        Call<Meals> calls = Utils.getApi().getMealsByIngredient(ingredient);

        calls.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {

                    view.setMeals(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

}

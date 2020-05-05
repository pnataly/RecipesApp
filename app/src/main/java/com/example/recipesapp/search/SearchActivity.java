
package com.example.recipesapp.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.R;
import com.example.recipesapp.Utils.Utils;
import com.example.recipesapp.adapter.RecyclerViewMealByCategory;
import com.example.recipesapp.detail.DetailActivity;
import com.example.recipesapp.model.Meals;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.ingredient)
    TextView textSearch;

    private String textToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        SearchPresenter searchPresenter = new SearchPresenter(this);

        Intent intent = getIntent();
        textToSearch = intent.getStringExtra("text_to_search");
        textSearch.setText("Recipes for " + textToSearch);
        searchPresenter.getMealsByIngredient(textToSearch);
    }
    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerViewMealByCategory recyclerViewAdapter = new RecyclerViewMealByCategory(this, meals);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(recyclerViewAdapter);
        if (recyclerViewAdapter.getItemCount() == 0){
            textSearch.setText("No Result");
            textSearch.setTextSize(25);
        }
        recyclerViewAdapter.notifyDataSetChanged();

        recyclerViewAdapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("detail", mealName.getText().toString());
            startActivity(intent);
        });
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error ", message);
    }
}

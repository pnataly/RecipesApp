package com.example.recipesapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.recipesapp.R;
import com.example.recipesapp.Utils.Utils;
import com.example.recipesapp.adapter.RecyclerViewHomeAdapter;
import com.example.recipesapp.category.CategoryActivity;
import com.example.recipesapp.model.Categories;
import com.example.recipesapp.search.SearchActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView{


    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerViewCategory;

    @BindView(R.id.edit_search)
    EditText textToSearch;

    @BindView(R.id.search_button)
    ImageView searchButton;

    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this);
        homePresenter.getCategories();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = textToSearch.getText().toString().trim();
                if(!search.isEmpty() && search != null){
                    Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                    intent.putExtra("text_to_search", search);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }



    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter recyclerViewAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(recyclerViewAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setClipToPadding(false);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        recyclerViewAdapter.notifyDataSetChanged();

        recyclerViewAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra("category", (Serializable)category);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }
}


package com.example.recipesapp.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.recipesapp.R;
import com.example.recipesapp.adapter.ViewPagerCategoryAdapter;
import com.example.recipesapp.model.Categories;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tableLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initActionBar();

        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>)intent.getSerializableExtra("category");
        int position = intent.getIntExtra("position", 0);

        //TODO 11. Declare fragment viewPager adapter
        ViewPagerCategoryAdapter viewPagerCategoryAdapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        viewPager.setAdapter(viewPagerCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        viewPagerCategoryAdapter.notifyDataSetChanged();

    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}

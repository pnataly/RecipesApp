
package com.example.recipesapp.adapter;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipesapp.category.CategoryFragment;
import com.example.recipesapp.model.Categories;

import java.util.List;


public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {

    private List<Categories.Category> categories;

    public ViewPagerCategoryAdapter(FragmentManager fm, List<Categories.Category> categories) {
        super(fm);
        this.categories = categories;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("name", categories.get(i).getStrCategory());
        args.putString("description", categories.get(i).getStrCategoryDescription());
        args.putString("image", categories.get(i).getStrCategoryThumb());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getStrCategory();
    }


}



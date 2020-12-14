package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import AllListForder.AllList;
import AllListForder.Object.InfoLogin;
import AllListForder.Object.ItemSell;
import View.CategoryFragment.ShowListCategoryFragment;
import View.homeFragment.HomeFragment;
import View.newsFeedFragment.NewsFeedFragment;
import View.notificationFragment.NotificationFragment;
import View.userFragment.UserFragment;

public class MainActivity extends AppCompatActivity implements AllList {
    private ActivityMainBinding mainBinding;
    private ItemSell itemSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        INFO_LOGIN_LIST.add(new InfoLogin(0,0,false));
        getFragment(HomeFragment.newInstance());
        Intent getIntend = getIntent();
        Boolean checkInternet = getIntend.getBooleanExtra("CheckInternet", true);
        if (checkInternet == false) {
            Toast.makeText(this, "Kiem tra lai ket noi", Toast.LENGTH_LONG).show();
        }
        mainBinding.mainFunctionBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.function_home: {
                        mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
                        getFragment(HomeFragment.newInstance());
                        break;
                    }
                    case R.id.function_category: {
                        mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
                        getFragment(ShowListCategoryFragment.newInstance());
                        break;
                    }
                    case R.id.function_News: {
                        mainBinding.mainSearchBar.setVisibility(View.GONE);
                        getFragment(NewsFeedFragment.newInstance());
                        break;
                    }
                    case R.id.function_Notification: {
                        mainBinding.mainSearchBar.setVisibility(View.GONE);
                        getFragment(NotificationFragment.newInstance());
                        break;
                    }
                    case R.id.function_User: {
                        mainBinding.mainSearchBar.setVisibility(View.GONE);
                        getFragment(UserFragment.newInstance());
                        break;
                    }
                    default:
                        break;
                }
                return true;
            }
        });

    }

    public ItemSell getItemSell() {
        return itemSell;
    }

    public void setItemSell(ItemSell itemSell) {
        this.itemSell = itemSell;
    }

    public EditText getEt(){
        return mainBinding.etSearchItemInMain;
    }
    public void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
    }
    public  void setVisibleSearchBar(boolean visibleSearchBar){
        if (visibleSearchBar){
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
        }else {
            mainBinding.mainSearchBar.setVisibility(View.GONE);
        }
    }

}
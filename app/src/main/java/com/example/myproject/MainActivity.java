package com.example.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.example.myproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import AllListForder.AllKeyLocal;
import AllListForder.AllList;
import AllListForder.Object.ItemSell;
import View.CategoryFragment.ShowListCategoryFragment;
import View.HomeFragment.HomeFragment;
import View.NewsFeedFragment.NewsFeedFragment;
import View.NotificationFragment.NotificationFragment;
import View.UserFragment.UserFragment;

public class MainActivity extends AppCompatActivity implements AllList, AllKeyLocal, View.OnClickListener {
    private ActivityMainBinding mainBinding;
    private ItemSell itemSell;
    private String local;
    private String MainLocal;
    private String typeCategory;
    private final ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };
    private ResideMenu resideMenu;
    private ResideMenuItem homeItem;
    private ResideMenuItem categoryItem;
    private ResideMenuItem newsFeedItem;
    private ResideMenuItem notificationItem;
    private ResideMenuItem userItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getFragment(HomeFragment.newInstance());
        Tovuti.from(this).monitor(new Monitor.ConnectivityListener() {
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast) {
                if (isConnected) {
                    Intent getIntend = getIntent();
                    Boolean checkInternet = getIntend.getBooleanExtra("CheckInternet", true);
                    if (checkInternet == false) {
                        Toast.makeText(MainActivity.this, "Kiem tra lai ket noi", Toast.LENGTH_LONG).show();
                    }
                    setUpMenu();
                } else {
                    AlertDialog checkInternetAnalog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle(getString(R.string.dialogTile))
                            .setMessage(getString(R.string.notifyCheckInternet))
                            .setPositiveButton(getString(R.string.returnConnect), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton(getString(R.string.cancelNotify), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    System.exit(0);
                                }
                            }).create();
                    checkInternetAnalog.show();
                }
            }
        });
    }

    private void setUpMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_backgroud);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);

        resideMenu.setMenuListener(menuListener);

        homeItem = new ResideMenuItem(this, R.drawable.homeimg, getString(R.string.function_home));
        categoryItem = new ResideMenuItem(this, R.drawable.categoryimg, getString(R.string.function_Category));
        newsFeedItem = new ResideMenuItem(this, R.drawable.newsimg, getString(R.string.function_new));
        notificationItem = new ResideMenuItem(this, R.drawable.notificationimg, getString(R.string.function_Notification));
        userItem = new ResideMenuItem(this, R.drawable.userimg, getString(R.string.function_user));

        homeItem.setOnClickListener(this);
        categoryItem.setOnClickListener(this);
        newsFeedItem.setOnClickListener(this);
        notificationItem.setOnClickListener(this);
        userItem.setOnClickListener(this);

        resideMenu.addMenuItem(homeItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(categoryItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(newsFeedItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(notificationItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(userItem, ResideMenu.DIRECTION_LEFT);

        mainBinding.btnBarMenuInMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Tovuti.from(this).stop();
    }

    public String getMainLocal() {
        return MainLocal;
    }

    public void setMainLocal(String mainLocal) {
        MainLocal = mainLocal;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ItemSell getItemSell() {
        return itemSell;
    }

    public void setItemSell(ItemSell itemSell) {
        this.itemSell = itemSell;
    }

    public EditText getEt() {
        return mainBinding.etSearchItemInMain;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
    }

    public void setVisibleSearchBar(boolean visibleSearchBar) {
        if (visibleSearchBar) {
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
        } else {
            mainBinding.mainSearchBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == homeItem) {
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
            getFragment(HomeFragment.newInstance());
        } else if (v == categoryItem) {
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
            setLocal(TOY_AND_MOM);
            setTypeCategory(TYPE_MAIN);
            getFragment(ShowListCategoryFragment.newInstance());
        } else if (v == newsFeedItem) {
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
            getFragment(NewsFeedFragment.newInstance());
        } else if (v == notificationItem) {
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
            getFragment(NotificationFragment.newInstance());
        } else if (v == userItem) {
            mainBinding.mainSearchBar.setVisibility(View.VISIBLE);
            getFragment(UserFragment.newInstance());
        }
        resideMenu.closeMenu();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
}
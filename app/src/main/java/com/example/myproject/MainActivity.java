package com.example.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

import AllListForder.AllKeyLocal;
import AllListForder.AllList;
import AllListForder.Object.ItemSell;
import View.CategoryFragment.ShowListCategoryFragment;
import View.HomeFragment.HomeFragment;
import View.UserFragment.UserFragment;
import View.newsFeedFragment.NewsFeedFragment;
import View.notificationFragment.NotificationFragment;

public class MainActivity extends AppCompatActivity implements AllList, AllKeyLocal {
    private ActivityMainBinding mainBinding;
    private ItemSell itemSell;
    private String local;
    private String MainLocal;
    private String typeCategory;

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
                                    setLocal(TOY_AND_MOM);
                                    setTypeCategory(TYPE_MAIN);
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

}
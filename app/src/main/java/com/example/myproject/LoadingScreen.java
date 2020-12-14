package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import AllListForder.AllList;
import AllListForder.Object.EventInHome;
import AllListForder.Object.ItemSell;
import AllListForder.Object.MainAdsImg;
import AllListForder.Object.MainCategory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import support_functions.CheckInternet;
import support_functions.Classify_item_list;
import support_functions.GetApiSP;
import support_functions.GetJson;
import support_functions.GetOBJAPI;

public class LoadingScreen extends AppCompatActivity implements AllList {
    private int Request_Permission_Code = 10;
    private String URL_LINK_ADS_HOME = "https://demo8357538.mockable.io/DemoHomeAds";
    private String URL_LINK_HOME_EVENT = "https://demo8357538.mockable.io/demoHomeEvent";
    private String URL_LINK_ALL_ITEM_SELL = "https://demo8357538.mockable.io/DemoSanPham";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, Request_Permission_Code);
        loadData();
    }

    private void loadData() {
        if (CheckInternet.checkInterNet(this)) {
            //network enabled
            new GetData().execute();
        } else {
            //network disable
            AlertDialog checkInternetAnalog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.dialogTile))
                    .setMessage(getString(R.string.notifyCheckInternet))
                    .setPositiveButton(getString(R.string.returnConnect), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loadData();
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

    class GetData extends AsyncTask<Void, Integer, Integer> {
        String AdsInHomeJSON = "";
        String EventInHomeJson = "";
        String TotalItemJson = "";
        Intent intent;
        Retrofit retrofit;
        GetApiSP getApiSP;
        private Call<List<ItemSell>> callListItemSell;
        private Call<List<EventInHome>> callListEventInHome;
        private Call<List<MainAdsImg>> callListMainAdsInHome;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            intent = new Intent(getBaseContext(), MainActivity.class);
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://demo8357538.mockable.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            getApiSP = retrofit.create(GetApiSP.class);

            callListItemSell = getApiSP.ITEM_SELLS_CALL();
            callListEventInHome = getApiSP.EVENT_IN_HOME_CALL();
            callListMainAdsInHome = getApiSP.MAIN_ADS_IN_HOME_CALL();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            getCateList();

            GetOBJAPI.getOBJItemSell(callListItemSell,LoadingScreen.this);
            GetOBJAPI.getOBJEventInHome(callListEventInHome,LoadingScreen.this);
            GetOBJAPI.getOBJMainAdsImg(callListMainAdsInHome,LoadingScreen.this);
//            try {
//                URL urlAdsHome = new URL(URL_LINK_ADS_HOME);
//                URLConnection urlConnectionAdsHome = urlAdsHome.openConnection();
//                InputStream inputStreamAdsHome = urlConnectionAdsHome.getInputStream();
//
//                URL urlEventHome = new URL(URL_LINK_HOME_EVENT);
//                URLConnection urlConnectionEventHome = urlEventHome.openConnection();
//                InputStream inputStreamEventHome = urlConnectionEventHome.getInputStream();
//
//                URL urlTotalItemSell = new URL(URL_LINK_ALL_ITEM_SELL);
//                URLConnection urlConnectionTotalItemSell = urlTotalItemSell.openConnection();
//                InputStream inputStreamTotalItemSell = urlConnectionTotalItemSell.getInputStream();
//
//                AdsInHomeJSON = readUrl(inputStreamAdsHome, AdsInHomeJSON);
//                EventInHomeJson = readUrl(inputStreamEventHome, EventInHomeJson);
//                TotalItemJson = readUrl(inputStreamTotalItemSell, TotalItemJson);
//
//                intent.putExtra("CheckInternet", true);
//            } catch (Exception e) {
//                intent.putExtra("CheckInternet", false);
//                e.printStackTrace();
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            //intent.putExtra("JsonAdsInHome", AdsInHomeJSON);
//            GetJson.getADSJson(AdsInHomeJSON);
//            GetJson.getEventHomeJson(EventInHomeJson);
//            GetJson.getTotalItemJson(TotalItemJson);

            Classify_item_list.Classify_list();
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Request_Permission_Code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String readUrl(InputStream inputStream, String json) {
        try {
            int byteCharacter;
            while ((byteCharacter = inputStream.read()) != -1) {
                json += (char) byteCharacter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    private void getCateList() {
        MAIN_CATEGORY_LIST.clear();
        MAIN_CATEGORY_LIST.add(new MainCategory(0, "toy_and_mom", getString(R.string.toy_and_mom), R.drawable.toy_and_mom));
        MAIN_CATEGORY_LIST.add(new MainCategory(1, "phone_and_tablet", getString(R.string.phone_and_tablet), R.drawable.phone_and_tablet));
        MAIN_CATEGORY_LIST.add(new MainCategory(2, "cosmetic", getString(R.string.cosmetic), R.drawable.cosmetic));
        MAIN_CATEGORY_LIST.add(new MainCategory(3, "electric_appliances", getString(R.string.electric_appliances), R.drawable.electric_appliances));
        MAIN_CATEGORY_LIST.add(new MainCategory(4, "women_fashion", getString(R.string.women_fashion), R.drawable.women_fasion));
        MAIN_CATEGORY_LIST.add(new MainCategory(5, "men_fashion", getString(R.string.men_fashion), R.drawable.men_fashion));
        MAIN_CATEGORY_LIST.add(new MainCategory(6, "jewelry", getString(R.string.jewelry), R.drawable.jewelry));
        MAIN_CATEGORY_LIST.add(new MainCategory(7, "laptop_pc", getString(R.string.laptop_pc), R.drawable.laptop_pc));
        MAIN_CATEGORY_LIST.add(new MainCategory(8, "car_moto", getString(R.string.car_moto), R.drawable.moto_car));

    }
}
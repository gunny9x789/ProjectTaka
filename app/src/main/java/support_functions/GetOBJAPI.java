package support_functions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import AllListForder.AllList;
import AllListForder.Object.EventInHome;
import AllListForder.Object.ItemSell;
import AllListForder.Object.MainAdsImg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetOBJAPI implements AllList {
    public static void getOBJItemSell(Call<List<ItemSell>> callListItemSell, Context context) {
        List<ItemSell> list;
        callListItemSell.enqueue(new Callback<List<ItemSell>>() {
            @Override
            public void onResponse(Call<List<ItemSell>> call, Response<List<ItemSell>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<ItemSell> itemSellsList = response.body();
                Log.e("tag", itemSellsList.get(20).getNameItemSell());
                ALL_ITEM_SELL_LIST.addAll(itemSellsList);
                Log.e("SizeItemList", ALL_ITEM_SELL_LIST.size() + "");
                Log.e("test1", ALL_ITEM_SELL_LIST.get(20).getNameItemSell());
            }

            @Override
            public void onFailure(Call<List<ItemSell>> call, Throwable t) {
                Log.e("Fail3", t.getMessage());
                Toast.makeText(context, "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getOBJEventInHome(Call<List<EventInHome>> callListEventInHome, Context context) {
        callListEventInHome.enqueue(new Callback<List<EventInHome>>() {
            @Override
            public void onResponse(Call<List<EventInHome>> call, Response<List<EventInHome>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<EventInHome> eventInHomeList = response.body();
                EVENT_IN_HOME_LIST.addAll(eventInHomeList);
                Log.e("SizeEventList", EVENT_IN_HOME_LIST.size() + "");
                Log.e("test2", EVENT_IN_HOME_LIST.get(0).getUrlMainEventImg());
            }

            @Override
            public void onFailure(Call<List<EventInHome>> call, Throwable t) {
                Log.e("Fail3", t.getMessage());
                Toast.makeText(context, "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getOBJMainAdsImg(Call<List<MainAdsImg>> callListMainAdsInHome, Context context) {
        callListMainAdsInHome.enqueue(new Callback<List<MainAdsImg>>() {
            @Override
            public void onResponse(Call<List<MainAdsImg>> call, Response<List<MainAdsImg>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<MainAdsImg> mainAdsImgList = response.body();
                MAIN_ADS_IMG_LIST.addAll(mainAdsImgList);
                Log.e("SizeADS", MAIN_ADS_IMG_LIST.size() + "");
                Log.e("test3", MAIN_ADS_IMG_LIST.get(0).getUrlIMG());
            }

            @Override
            public void onFailure(Call<List<MainAdsImg>> call, Throwable t) {
                Log.e("Fail3", t.getMessage());
                Toast.makeText(context, "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}

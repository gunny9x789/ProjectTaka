package support_functions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import AllListForder.AllList;
import AllListForder.Object.AvatarURL;
import AllListForder.Object.EventInHome;
import AllListForder.Object.ItemSell;
import AllListForder.Object.MainAdsImg;

public class GetJson implements AllList {
    public static void getADSJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idADSInHome = jsonObject.getInt("idADSInHome");
                String codeMainAdsId = jsonObject.getString("codeMainAdsId");
                String local = jsonObject.getString("local");
                String urlIMG = jsonObject.getString("urlIMG");
                MAIN_ADS_IMG_LIST.add(new MainAdsImg(idADSInHome, codeMainAdsId, local, urlIMG));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getEventHomeJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("idHomeEvent");
                String codeEvent = jsonObject.getString("codeEvent");
                String UrlMainEventImg = jsonObject.getString("UrlMainEventImg");
                String UrlSideEventImg1 = jsonObject.getString("UrlSideEventImg1");
                String UrlSideEventImg2 = jsonObject.getString("UrlSideEventImg2");
                String UrlSideEventImg3 = jsonObject.getString("UrlSideEventImg3");
                String UrlSideEventImg4 = jsonObject.getString("UrlSideEventImg4");
                EVENT_IN_HOME_LIST.add(new EventInHome(id, codeEvent, UrlMainEventImg,
                        UrlSideEventImg1, UrlSideEventImg2, UrlSideEventImg3, UrlSideEventImg4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTotalItemJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idItemSell = jsonObject.getInt("idItemSell");
                String CodeMainCateId = jsonObject.getString("CodeMainCateId");
                String CodeSideCateId = jsonObject.getString("CodeSideCateId");
                String nameItemSell = jsonObject.getString("nameItemSell");
                List<AvatarURL> avatarItemSellList = new ArrayList<>();
                JSONArray avatarItemSell = jsonObject.getJSONArray("avatarItemSell");
                for (int j = 0; j < avatarItemSell.length(); j++) {
                    JSONObject jsonObject1 = avatarItemSell.getJSONObject(j);
                    AvatarURL UrlImg = new AvatarURL(jsonObject1.getString("UrlImg"));
                    avatarItemSellList.add(UrlImg);
                }
                String sale = jsonObject.getString("sale");
                int salePercent = jsonObject.getInt("salePercent");
                int priceDontSale = jsonObject.getInt("priceDontSale");
                int priceSale = jsonObject.getInt("priceSale");
                int totalItem = jsonObject.getInt("totalItem");
                int totalItemSold = jsonObject.getInt("totalItemSold");
                int itemSoldInMonth = jsonObject.getInt("itemSoldInMonth");
                int idUserSell = jsonObject.getInt("idUserSell");
                String trademark = jsonObject.getString("trademark");
                String characteristics = jsonObject.getString("characteristics");
                String EventCode = jsonObject.getString("EventCode");
                String daySell = jsonObject.getString("DaySell");
                ALL_ITEM_SELL_LIST.add(new ItemSell(idItemSell, CodeMainCateId, CodeSideCateId, nameItemSell, idUserSell, trademark, avatarItemSellList,
                        sale, salePercent, priceDontSale, priceSale, totalItem, totalItemSold, itemSoldInMonth, characteristics, EventCode, daySell));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

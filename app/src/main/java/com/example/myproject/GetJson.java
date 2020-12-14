package com.example.myproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AllListForder.MainAdsList;
import AllListForder.Object.MainAdsImg;

public class GetJson implements MainAdsList {
    public static void getADSJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i= 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String codeMainAdsId = jsonObject.getString("codeMainAdsId");
                String local = jsonObject.getString("local");
                String urlIMG = jsonObject.getString("urlIMG");
                MAIN_ADS_IMG_LIST.add(new MainAdsImg(id,codeMainAdsId,local,urlIMG));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

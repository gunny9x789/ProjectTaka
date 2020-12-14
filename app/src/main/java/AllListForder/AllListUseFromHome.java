package AllListForder;

import java.util.ArrayList;
import java.util.List;

import AllListForder.Object.EventInHome;
import AllListForder.Object.ItemSell;
import AllListForder.Object.MainAdsImg;

public interface AllListUseFromHome {
    List<EventInHome> EVENT_IN_HOME_LIST = new ArrayList<>();
    List<MainAdsImg> MAIN_ADS_IMG_LIST = new ArrayList<>();
    List<ItemSell> ITEM_SALE_IN_DAY_LIST = new ArrayList<>();
    List<ItemSell> ITEM_YOUR_MAY_LIKE_LIST = new ArrayList<>();
    List<ItemSell> ITEM_HOT_DEAL = new ArrayList<>();
    List<ItemSell> ITEM_BEST_PRICE = new ArrayList<>();
    List<ItemSell> ITEM_NEW = new ArrayList<>();

}

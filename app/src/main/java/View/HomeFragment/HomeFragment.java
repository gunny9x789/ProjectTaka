package View.HomeFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.HomeFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import AllListForder.AllList;
import AllListForder.Object.ItemSell;
import View.HomeFragment.Adapter.AdaperRCVItemShowInHome;
import View.HomeFragment.Adapter.AdapteMainADS;
import View.HomeFragment.Adapter.AdapterRCVItemSaleInDay;
import View.HomeFragment.Adapter.AdapterRCVItemYourMayLike;
import View.HomeFragment.Adapter.OnItemRCVClickListener;
import View.homeFragment.adapter.AdapterEventHome;
import View.showItemFragment.ShowItemDetailFragment;


public class HomeFragment extends Fragment implements AllList {

    HomeFragmentBinding homeFragmentBinding;
    private AdapteMainADS adapteMainADS;
    private AdapterEventHome adapterEventHome;
    private AdapterRCVItemSaleInDay adapterRCVItemSaleInDay;
    private AdapterRCVItemYourMayLike adapterRCVItemYourMayLike;
    private AdaperRCVItemShowInHome adaperRCVItemShowInHome;
    private Timer timer;
    private MainActivity mainActivity;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        adapteMainADS = new AdapteMainADS(MAIN_ADS_IMG_LIST, getActivity().getBaseContext());
        homeFragmentBinding.VpMainAdsHomeFragment.setAdapter(adapteMainADS);
        homeFragmentBinding.CIMainAdsHomeFragment.setViewPager(
                homeFragmentBinding.VpMainAdsHomeFragment
        );
        adapteMainADS.registerDataSetObserver(homeFragmentBinding.CIMainAdsHomeFragment.getDataSetObserver());
        //autoSide();

        adapterEventHome = new AdapterEventHome(EVENT_IN_HOME_LIST, getActivity().getBaseContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext(), RecyclerView.VERTICAL, false);
        homeFragmentBinding.rcvHomeEventShow.setAdapter(adapterEventHome);
        homeFragmentBinding.rcvHomeEventShow.setLayoutManager(layoutManager);

        adapterRCVItemSaleInDay = new AdapterRCVItemSaleInDay();
        adapterRCVItemSaleInDay.setData(getList(ITEM_SALE_IN_DAY_LIST));
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity().getBaseContext(), RecyclerView.HORIZONTAL, false);
        homeFragmentBinding.rcvItemSaleInDay.setLayoutManager(layoutManager1);
        homeFragmentBinding.rcvItemSaleInDay.setAdapter(adapterRCVItemSaleInDay);
        adapterRCVItemSaleInDay.setItemClickListener(new OnItemRCVClickListener() {
            @Override
            public void onItemClick(ItemSell itemSell) {
                mainActivity.setItemSell(itemSell);
                mainActivity.getFragment(ShowItemDetailFragment.newInstance());
            }
        });

        adapterRCVItemYourMayLike = new AdapterRCVItemYourMayLike();
        adapterRCVItemYourMayLike.setData(getList(ITEM_YOUR_MAY_LIKE_LIST));
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getBaseContext(), RecyclerView.HORIZONTAL, false);
        homeFragmentBinding.rcvItemYouMayLike.setLayoutManager(layoutManager2);
        homeFragmentBinding.rcvItemYouMayLike.setAdapter(adapterRCVItemYourMayLike);
        adapterRCVItemYourMayLike.setItemClickListener(new OnItemRCVClickListener() {
            @Override
            public void onItemClick(ItemSell itemSell) {
                mainActivity.setItemSell(itemSell);
                mainActivity.getFragment(ShowItemDetailFragment.newInstance());
            }
        });

        adaperRCVItemShowInHome = new AdaperRCVItemShowInHome();
        adaperRCVItemShowInHome.setData(getList(ITEM_HOT_DEAL));
        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(getActivity().getBaseContext(), 3, RecyclerView.VERTICAL, false);
        homeFragmentBinding.rcvShowListSpItemInHome.setLayoutManager(layoutManager3);
        homeFragmentBinding.rcvShowListSpItemInHome.setAdapter(adaperRCVItemShowInHome);
        adaperRCVItemShowInHome.setItemClickListener(new OnItemRCVClickListener() {
            @Override
            public void onItemClick(ItemSell itemSell) {
                mainActivity.setItemSell(itemSell);
                mainActivity.getFragment(ShowItemDetailFragment.newInstance());
            }
        });

        homeFragmentBinding.tvHotDealInHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperRCVItemShowInHome.setData(getList(ITEM_HOT_DEAL));
                homeFragmentBinding.rcvShowListSpItemInHome.setAdapter(adaperRCVItemShowInHome);
            }
        });
        homeFragmentBinding.tvBestPriceInHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperRCVItemShowInHome.setData(getList(ITEM_BEST_PRICE));
                homeFragmentBinding.rcvShowListSpItemInHome.setAdapter(adaperRCVItemShowInHome);
            }
        });
        homeFragmentBinding.tvNewItemInHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperRCVItemShowInHome.setData(getList(ITEM_NEW));
                homeFragmentBinding.rcvShowListSpItemInHome.setAdapter(adaperRCVItemShowInHome);
            }
        });
        return homeFragmentBinding.getRoot();
    }

    private void autoSide() {
        if (MAIN_ADS_IMG_LIST == null || MAIN_ADS_IMG_LIST.isEmpty() || homeFragmentBinding.VpMainAdsHomeFragment == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = homeFragmentBinding.VpMainAdsHomeFragment.getCurrentItem();
                        int total = MAIN_ADS_IMG_LIST.size() - 1;
                        if (currentItem < total) {
                            currentItem++;
                            homeFragmentBinding.VpMainAdsHomeFragment.setCurrentItem(currentItem);
                        } else {
                            homeFragmentBinding.VpMainAdsHomeFragment.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 5000);
    }

    private List<ItemSell> getList(List<ItemSell> itemSells) {
        List<ItemSell> itemSellList = new ArrayList<>();
        for (int i = 0; i < itemSells.size(); i++) {
            itemSellList.add(itemSells.get(i));
        }
        return itemSellList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}

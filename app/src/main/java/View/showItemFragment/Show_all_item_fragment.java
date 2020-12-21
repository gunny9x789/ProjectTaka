package View.showItemFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.ShowAllListItemFragmentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import AllListForder.AllKeyLocal;
import AllListForder.AllList;
import AllListForder.Object.ItemSell;
import AllListForder.Object.MainAdsImg;
import View.HomeFragment.Adapter.OnItemRCVClickListener;
import View.HomeFragment.HomeFragment;
import View.showItemFragment.Adapter.AdapterRCVShowAllItem;
import View.showItemFragment.Adapter.AdapterShowAdsInShowAllItem;
import support_functions.PaginationListenerGrid;

public class Show_all_item_fragment extends Fragment implements AllList, AllKeyLocal {
    private final int currentPage = 1;
    private ShowAllListItemFragmentBinding showAllListItemFragmentBinding;
    private MainActivity mainActivity;
    private AdapterRCVShowAllItem adapterRCVShowAllItem;
    private boolean isLoading;
    private boolean isLastPage;
    private int totalPage;

    public static Show_all_item_fragment newInstance() {

        Bundle args = new Bundle();

        Show_all_item_fragment fragment = new Show_all_item_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showAllListItemFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.show_all_list_item_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setVisibleSearchBar(false);

        for (int i = 0; i < MAIN_ADS_IMG_LIST.size(); i++) {
            MainAdsImg mainAdsImg = MAIN_ADS_IMG_LIST.get(i);
            flipPerImage(mainAdsImg.getUrlIMG());
        }

        adapterRCVShowAllItem = new AdapterRCVShowAllItem();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getBaseContext(), 3, RecyclerView.VERTICAL, false);
        showAllListItemFragmentBinding.rcvShowAllItem.setLayoutManager(gridLayoutManager);
        adapterRCVShowAllItem.setItemClickListener(new OnItemRCVClickListener() {
            @Override
            public void onItemClick(ItemSell itemSell) {
                mainActivity.setMainLocal(LOCAL_HOME);
                mainActivity.setItemSell(itemSell);
                mainActivity.getFragment(ShowItemDetailFragment.newInstance());
            }
        });
        if (mainActivity.getLocal().equals(SALE_IN_HOME)) {
            setData(ITEM_SALE_IN_DAY_LIST);
        } else if (mainActivity.getLocal().equals(YOU_MAY_LIKE)) {
            setData(ITEM_YOUR_MAY_LIKE_LIST);
        }
        showAllListItemFragmentBinding.rcvShowAllItem.addOnScrollListener(new PaginationListenerGrid(gridLayoutManager) {
            @Override
            public void loadMoreItemGrid() {

            }

            @Override
            public boolean isLoadingGrid() {
                return isLoading;
            }

            @Override
            public boolean isLastPageGrid() {
                return isLastPage;
            }
        });

        showAllListItemFragmentBinding.imgIconBackInShowAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.getMainLocal().equals(LOCAL_HOME)) {
                    mainActivity.getFragment(HomeFragment.newInstance());
                    mainActivity.setVisibleSearchBar(true);
                }
            }
        });
        showAllListItemFragmentBinding.iconSearchInShowAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.setVisibleSearchBar(true);
                showAllListItemFragmentBinding.showAllTileBar.setVisibility(View.GONE);
            }
        });
        showAllListItemFragmentBinding.rcvShowAllItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mainActivity.setVisibleSearchBar(false);
                showAllListItemFragmentBinding.showAllTileBar.setVisibility(View.VISIBLE);
                return false;
            }
        });
        showAllListItemFragmentBinding.svShowAllItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mainActivity.setVisibleSearchBar(false);
                showAllListItemFragmentBinding.showAllTileBar.setVisibility(View.VISIBLE);
                return false;
            }
        });
        return showAllListItemFragmentBinding.getRoot();
    }

    private void setData(List<ItemSell> itemSells) {
        adapterRCVShowAllItem.setDataAdapter(getListItem(itemSells));
        showAllListItemFragmentBinding.rcvShowAllItem.setAdapter(adapterRCVShowAllItem);
    }

    private List<ItemSell> getListItem(List<ItemSell> itemSells) {
        List<ItemSell> itemSellList = new ArrayList<>();
        if (itemSells.size() < 10) {
            for (int i = 0; i < itemSells.size(); i++) {
                itemSellList.add(itemSells.get(i));
            }
        } else if (itemSells.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                itemSellList.add(itemSells.get(i));
            }
        }
        return itemSellList;
    }

    public void flipPerImage(String urlImage) {
        ImageView imageView = new ImageView(getContext());
        Picasso.get().load(urlImage)
                .placeholder(R.drawable.dont_loading_img)
                .error(R.drawable.dont_loading_img)
                .into(imageView);
        showAllListItemFragmentBinding.VfMainAdsShowAllFragment.addView(imageView);
        showAllListItemFragmentBinding.VfMainAdsShowAllFragment.setFlipInterval(5000);
        showAllListItemFragmentBinding.VfMainAdsShowAllFragment.setAutoStart(true);

        showAllListItemFragmentBinding.VfMainAdsShowAllFragment.setInAnimation(getContext(), android.R.anim.slide_in_left);
        showAllListItemFragmentBinding.VfMainAdsShowAllFragment.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }
}

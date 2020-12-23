package View.showItemFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.ShowAllListItemFragmentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import AllListForder.AllKeyLocal;
import AllListForder.AllList;
import AllListForder.Object.ItemSell;
import AllListForder.Object.MainAdsImg;
import View.HomeFragment.Adapter.OnItemRCVClickListener;
import View.HomeFragment.HomeFragment;
import View.showItemFragment.Adapter.AdapterRCVShowAllItem;

public class Show_all_item_fragment extends Fragment implements AllList, AllKeyLocal {
    private int currentPage = 1;
    private ShowAllListItemFragmentBinding showAllListItemFragmentBinding;
    private MainActivity mainActivity;
    private AdapterRCVShowAllItem adapterRCVShowAllItem;
    private int totalPage;
    private List<ItemSell> MainListItemShow;

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
        MainListItemShow = new ArrayList<>();

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
                mainActivity.setMainLocal(SHOW_ALL_ITEM);
                mainActivity.setItemSell(itemSell);
                mainActivity.getFragment(ShowItemDetailFragment.newInstance());
            }
        });
        if (mainActivity.getLocal().equals(SALE_IN_HOME)) {
            setMainList(ITEM_SALE_IN_DAY_LIST);
            setData(MainListItemShow, currentPage);
            totalPage = getTotalPage(MainListItemShow);
        } else if (mainActivity.getLocal().equals(YOU_MAY_LIKE)) {
            setMainList(ITEM_YOUR_MAY_LIKE_LIST);
            setData(MainListItemShow, currentPage);
            totalPage = getTotalPage(MainListItemShow);
        }else if (mainActivity.getLocal().equals(HOT_DEAL_ITEM)){
            setMainList(ITEM_HOT_DEAL);
            setData(MainListItemShow,currentPage);
            totalPage =getTotalPage(MainListItemShow);
        }else if (mainActivity.getLocal().equals(BEST_PRICE_ITEM)){
            setMainList(ITEM_NEW);
            setData(MainListItemShow,currentPage);
            totalPage = getTotalPage(MainListItemShow);
        }else if (mainActivity.getLocal().equals(NEW_ITEM)){
            setMainList(ITEM_NEW);
            setData(MainListItemShow,currentPage);
            totalPage = getTotalPage(MainListItemShow);
        }
        showAllListItemFragmentBinding.tvCurrentTotalPage.setText(currentPage + "/" + totalPage);

        showAllListItemFragmentBinding.backPageInShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage == 1) {
                    Toast.makeText(getActivity().getBaseContext(), getString(R.string.dont_loading), Toast.LENGTH_SHORT).show();
                } else if (currentPage > 1) {
                    currentPage -= 1;
                    setData(MainListItemShow,currentPage);
                    showAllListItemFragmentBinding.tvCurrentTotalPage.setText(currentPage + "/" + totalPage);
                }
            }
        });

        showAllListItemFragmentBinding.nextPageInShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage == totalPage) {
                    Toast.makeText(getActivity().getBaseContext(), getString(R.string.dont_loading), Toast.LENGTH_SHORT).show();
                } else if (currentPage < totalPage) {
                    currentPage += 1;
                    setData(MainListItemShow,currentPage);
                    showAllListItemFragmentBinding.tvCurrentTotalPage.setText(currentPage + "/" + totalPage);
                }
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

        showAllListItemFragmentBinding.popupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity().getBaseContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.sort_item_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.newItemMenu: {
                                showAllListItemFragmentBinding.popupMenu.setText(getString(R.string.newItem));
                                break;
                            }
                            case R.id.hotItemMenu: {
                                showAllListItemFragmentBinding.popupMenu.setText(getString(R.string.hotItem));
                                break;
                            }
                            case R.id.lowPriceMenu: {
                                showAllListItemFragmentBinding.popupMenu.setText(getString(R.string.lowPrice));
                                break;
                            }
                            case R.id.hightPriceMenu: {
                                showAllListItemFragmentBinding.popupMenu.setText(getString(R.string.hightPrice));
                                break;
                            }

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return showAllListItemFragmentBinding.getRoot();
    }
    private void setMainList(List<ItemSell> itemSells){
        MainListItemShow.clear();
        MainListItemShow.addAll(itemSells);
    }
    private void setData(List<ItemSell> itemSells, int currentPage) {
        adapterRCVShowAllItem.setDataAdapter(getListItem(itemSells, currentPage));
        showAllListItemFragmentBinding.rcvShowAllItem.setAdapter(adapterRCVShowAllItem);
    }

    private int getTotalPage(List<ItemSell> itemSells) {
        int total = 1;
        if (itemSells.size() <= 15) {
            total = 1;
        } else if (itemSells.size() > 15 && itemSells.size() % 15 == 0) {
            total = itemSells.size() / 15;
        } else if (itemSells.size() > 15 && itemSells.size() % 15 != 0) {
            total = (itemSells.size() / 15) + 1;
        }
        return total;
    }

    private List<ItemSell> getListItem(List<ItemSell> itemSells, int currentPage) {
        List<ItemSell> itemSellList = new ArrayList<>();
        if (itemSells.size() <= currentPage * 15) {
            for (int i = (currentPage - 1) * 15; i < itemSells.size(); i++) {
                itemSellList.add(itemSells.get(i));
            }
        } else if (itemSells.size() > currentPage * 15) {
            for (int i = (currentPage - 1) * 15; i < (currentPage - 1) * 15 + 15; i++) {
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

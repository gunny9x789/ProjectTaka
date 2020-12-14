package View.CategoryFragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.databinding.ListCategoryFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import AllListForder.AllList;
import View.CategoryFragment.Adapter.AdsCategoriAdapter;
import View.CategoryFragment.Adapter.MainCategoryRCVAdapter;


public class ShowListCategoryFragment extends Fragment implements AllList {

    ListCategoryFragmentBinding listCategoryFragmentBinding;
    private List<Integer> listItemAdsCategory;
    private AdsCategoriAdapter adsCategoriAdapter;
    private MainCategoryRCVAdapter mainCategoryRCVAdapter;
    private Timer timer;

    public static ShowListCategoryFragment newInstance() {

        Bundle args = new Bundle();

        ShowListCategoryFragment fragment = new ShowListCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listCategoryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.list_category_fragment, container, false);
        listItemAdsCategory = new ArrayList<>();
        listItemAdsCategory.add(R.drawable.ads_in_category_1);
        listItemAdsCategory.add(R.drawable.ads_in_category_2);
        listItemAdsCategory.add(R.drawable.ads_in_category_3);


        mainCategoryRCVAdapter = new MainCategoryRCVAdapter(MAIN_CATEGORY_LIST);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        listCategoryFragmentBinding.rcvMainCategory.setLayoutManager(layoutManager);
        listCategoryFragmentBinding.rcvMainCategory.setAdapter(mainCategoryRCVAdapter);

        adsCategoriAdapter = new AdsCategoriAdapter(listItemAdsCategory, getActivity().getBaseContext());
        listCategoryFragmentBinding.pagerAdsSideCategory.setAdapter(adsCategoriAdapter);
        listCategoryFragmentBinding.CIAdsSideCategory.setViewPager(listCategoryFragmentBinding.pagerAdsSideCategory);
        adsCategoriAdapter.registerDataSetObserver(listCategoryFragmentBinding.CIAdsSideCategory.getDataSetObserver());

        autoSlide();

        return listCategoryFragmentBinding.getRoot();
    }

    private void autoSlide() {
        if (listItemAdsCategory == null || listItemAdsCategory.isEmpty() || listCategoryFragmentBinding.pagerAdsSideCategory == null) {
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
                        int currentItem = listCategoryFragmentBinding.pagerAdsSideCategory.getCurrentItem();
                        int totalItem = listItemAdsCategory.size() - 1;
                        if (currentItem < totalItem){
                            currentItem++;
                            listCategoryFragmentBinding.pagerAdsSideCategory.setCurrentItem(currentItem);
                        }else {
                            listCategoryFragmentBinding.pagerAdsSideCategory.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
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

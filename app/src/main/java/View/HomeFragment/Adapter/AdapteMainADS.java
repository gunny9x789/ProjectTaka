package View.homeFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import AllListForder.Object.MainAdsImg;

public class AdapteMainADS extends PagerAdapter {
    private List<MainAdsImg> mainAdsLists;
    private Context context;

    public AdapteMainADS(List<MainAdsImg> mainAdsLists, Context context) {
        this.mainAdsLists = mainAdsLists;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (mainAdsLists != null) {
            return mainAdsLists.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_main_ads_home, container, false);
        ImageView imageView = view.findViewById(R.id.img_item_main_ads_home);

        MainAdsImg mainAdsImg = mainAdsLists.get(position);
        Picasso.get()
                .load(mainAdsImg.getUrlIMG())
                .placeholder(R.drawable.dont_loading_img)
                .error(R.drawable.dont_loading_img)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

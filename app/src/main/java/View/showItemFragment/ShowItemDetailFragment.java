package View.showItemFragment;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.ShowItemDetailFragmentBinding;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import AllListForder.CheckLogin;
import AllListForder.Object.InfoLogin;
import AllListForder.Object.ItemSell;
import View.homeFragment.HomeFragment;
import View.showItemFragment.Adapter.AdapterRCVShowListImgDetailItem;
import View.showItemFragment.Adapter.ShowImgItemDetailClick;

public class ShowItemDetailFragment extends Fragment implements CheckLogin {

    private ShowItemDetailFragmentBinding showItemDetailBinding;
    private MainActivity mainActivity;
    private AdapterRCVShowListImgDetailItem adapterRCVShowListImgDetailItem;

    public static ShowItemDetailFragment newInstance() {
        Bundle args = new Bundle();
        ShowItemDetailFragment fragment = new ShowItemDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showItemDetailBinding = DataBindingUtil.inflate(inflater, R.layout.show_item_detail_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        final DecimalFormatSymbols syms = new DecimalFormatSymbols();
        syms.setGroupingSeparator(',');
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,###,###", syms);
        ItemSell itemSell = mainActivity.getItemSell();
        List<String> listUrlImgae = itemSell.getAvatarItemSell();
        mainActivity.setVisibleSearchBar(false);

        showItemDetailBinding.tvShowDetailNameItem.setText(itemSell.getNameItemSell());
        Picasso.get().load(listUrlImgae.get(0))
                .placeholder(R.drawable.dont_loading_img)
                .error(R.drawable.dont_loading_img)
                .into(showItemDetailBinding.showImgDetailItem);

        adapterRCVShowListImgDetailItem = new AdapterRCVShowListImgDetailItem();
        adapterRCVShowListImgDetailItem.SetDataAdapter(listUrlImgae);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext(), RecyclerView.VERTICAL, false);
        showItemDetailBinding.rcvListImgDetailItem.setAdapter(adapterRCVShowListImgDetailItem);
        showItemDetailBinding.rcvListImgDetailItem.setLayoutManager(layoutManager);
        adapterRCVShowListImgDetailItem.setClick(new ShowImgItemDetailClick() {
            @Override
            public void onClick(String linkUrl) {
                Picasso.get().load(linkUrl)
                        .placeholder(R.drawable.dont_loading_img)
                        .error(R.drawable.dont_loading_img)
                        .into(showItemDetailBinding.showImgDetailItem);
            }
        });

        if (itemSell.getSale().equals("yes")) {
            showItemDetailBinding.tvShowDetailPriceItemSell.setText(myFormatter.format(itemSell.getPriceSale()) + " đ");
            showItemDetailBinding.tvShowDetailSalePercent.setVisibility(View.VISIBLE);
            showItemDetailBinding.tvShowDetailPriceDontSale.setVisibility(View.VISIBLE);
            showItemDetailBinding.tvShowDetailPriceDontSale.setText(myFormatter.format(itemSell.getPriceDontSale()) + " đ");
            showItemDetailBinding.tvShowDetailSalePercent.setText("-" + itemSell.getSalePercent() + "%");
        } else {
            showItemDetailBinding.tvShowDetailPriceItemSell.setText(myFormatter.format(itemSell.getPriceDontSale()) + " đ");
            showItemDetailBinding.tvShowDetailSalePercent.setVisibility(View.GONE);
            showItemDetailBinding.tvShowDetailPriceDontSale.setVisibility(View.GONE);
        }
        showItemDetailBinding.tvShowDetailCharacteristics.setText(itemSell.getCharacteristics());
        showItemDetailBinding.imgIconBackInShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFragment(HomeFragment.newInstance());
                mainActivity.setVisibleSearchBar(true);
            }
        });
        showItemDetailBinding.btnBuyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoLogin infoLogin = INFO_LOGIN_LIST.get(INFO_LOGIN_LIST.size() - 1);
                if (infoLogin.getInfoLogin()) {
                    Toast.makeText(getActivity().getBaseContext(), "Đã mua", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle(getString(R.string.dialogTile))
                            .setMessage(getString(R.string.notifyCheckLogin))
                            .setPositiveButton(getString(R.string.Login), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    INFO_LOGIN_LIST.add(new InfoLogin(0, 0, true));
                                    Toast.makeText(getActivity().getBaseContext(), "Đã login", Toast.LENGTH_LONG).show();
                                }
                            }).setNegativeButton(getString(R.string.cancelNotify), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create();
                    alertDialog.show();
                }
            }
        });
        showItemDetailBinding.iconSearchInShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.setVisibleSearchBar(true);
                showItemDetailBinding.detailTileBar.setVisibility(View.GONE);
                showItemDetailBinding.svShowDetail.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mainActivity.setVisibleSearchBar(false);
                        showItemDetailBinding.detailTileBar.setVisibility(View.VISIBLE);
                        return false;
                    }
                });
            }
        });
        return showItemDetailBinding.getRoot();
    }
}

package View.UserFragment.ManagerItemSell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.ManagerItemSellsFragmentBinding;

import View.UserFragment.UserFragment;

public class ManagerItemSellFragment extends Fragment {
    private ManagerItemSellsFragmentBinding managerItemSellsFragmentBinding;
    private MainActivity mainActivity;

    public static ManagerItemSellFragment newInstance() {

        Bundle args = new Bundle();

        ManagerItemSellFragment fragment = new ManagerItemSellFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        managerItemSellsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.manager_item_sells_fragment, container, false);
        mainActivity = (MainActivity) getActivity();

        managerItemSellsFragmentBinding.backSettingManagerItemSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFragment(UserFragment.newInstance());
            }
        });
        managerItemSellsFragmentBinding.managerItemSellSold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFragment(ManagerItemYourSellFragment.newInstance());
            }
        });
        managerItemSellsFragmentBinding.managerItemSellShowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFragment(ManagerItemSellByMeFragment.newInstance());
            }
        });
        return managerItemSellsFragmentBinding.getRoot();
    }
}

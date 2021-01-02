package View.UserFragment;

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
import com.example.myproject.databinding.SettingUserFragmentBinding;

import java.util.List;

import AllListForder.AllKeyLocal;
import AllListForder.AllList;
import AllListForder.Object.InfoLogin;
import View.HomeFragment.HomeFragment;
import support_functions.SqlLiteHelper;

public class SettingFragment extends Fragment implements AllList, AllKeyLocal {
    SettingUserFragmentBinding settingUserFragmentBinding;
    SqlLiteHelper sqlLiteHelper;
    MainActivity mainActivity;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingUserFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.setting_user_fragment, container, false);
        sqlLiteHelper = new SqlLiteHelper(getContext());
        mainActivity = (MainActivity) getActivity();

        settingUserFragmentBinding.logoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlLiteHelper.addCheckLoginTable(new InfoLogin(0, NONE_USER, USER_LOGOUT));
                List<InfoLogin> infoLoginList = sqlLiteHelper.getAllListCheckLogin();
                INFO_LOGIN_LIST.clear();
                INFO_LOGIN_LIST.addAll(infoLoginList);
                mainActivity.getFragment(HomeFragment.newInstance());
            }
        });
        settingUserFragmentBinding.backSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFragment(UserFragment.newInstance());
            }
        });
        return settingUserFragmentBinding.getRoot();
    }
}

package View.UserFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myproject.LoginActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.UserFragmentBinding;
import com.squareup.picasso.Picasso;

import AllListForder.AllKeyLocal;
import AllListForder.AllList;
import AllListForder.Object.InfoLogin;
import AllListForder.Object.User;
import support_functions.SqlLiteHelper;

public class UserFragment extends Fragment implements AllList, AllKeyLocal {
    UserFragmentBinding userFragmentBinding;
    SqlLiteHelper sqlLiteHelper;
    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false);
        sqlLiteHelper = new SqlLiteHelper(getContext());

        InfoLogin infoLogin = INFO_LOGIN_LIST.get(INFO_LOGIN_LIST.size() - 1);
        if (infoLogin.getInfoLogin().equals(USER_LOGOUT)) {
            userFragmentBinding.tvLogReg.setVisibility(View.VISIBLE);
            userFragmentBinding.userAllChoice.setVisibility(View.GONE);
            userFragmentBinding.tvNameUser.setVisibility(View.GONE);
        } else if (infoLogin.getInfoLogin().equals(USER_LOGIN)) {
            userFragmentBinding.tvLogReg.setVisibility(View.GONE);
            String accountLoginName = infoLogin.getNameUserLogin();
            User userLoginNow = getUserLogin(accountLoginName);
            userFragmentBinding.userAllChoice.setVisibility(View.VISIBLE);
            userFragmentBinding.tvNameUser.setVisibility(View.VISIBLE);
            userFragmentBinding.tvNameUser.setText(userLoginNow.getAccountName());
            if (userLoginNow.getAvatar().trim().isEmpty() || userLoginNow.getAvatar() == null) {
                userFragmentBinding.imgAvatarUser.setBackgroundResource(R.drawable.ic_baseline_account_circle_24);
            } else {
                Picasso.get().load(userLoginNow.getAvatar())
                        .placeholder(R.drawable.dont_loading_img)
                        .error(R.drawable.dont_loading_img)
                        .into(userFragmentBinding.imgAvatarUser);
            }
        }
        userFragmentBinding.tvLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return userFragmentBinding.getRoot();
    }

    private User getUserLogin(String accountName) {
        User user = null;
        for (int i = 0; i < USER_LIST.size(); i++) {
            user = USER_LIST.get(i);
            if (accountName.toLowerCase().equals(user.getAccountName().toLowerCase())) {
                return user;
            }
        }
        return user;
    }
}

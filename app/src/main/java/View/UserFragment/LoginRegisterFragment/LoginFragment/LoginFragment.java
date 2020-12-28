package View.UserFragment.LoginRegisterFragment.LoginFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myproject.LoginActivity;
import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.LoginFragmentBinding;

import View.UserFragment.LoginRegisterFragment.RegisterFragment.RegisterFragment;


public class LoginFragment extends Fragment {
    LoginFragmentBinding loginFragmentBinding;
    LoginActivity loginActivity;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        loginActivity = (LoginActivity) getActivity();
        loginFragmentBinding.btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        loginFragmentBinding.tvLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity.getFragment(RegisterFragment.newInstance());
            }
        });
        return loginFragmentBinding.getRoot();
    }

}

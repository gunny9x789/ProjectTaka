package View.UserFragment.LoginRegisterFragment.RegisterFragment;

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
import com.example.myproject.R;
import com.example.myproject.databinding.RegisterFragmentBinding;

import View.UserFragment.LoginRegisterFragment.LoginFragment.LoginFragment;


public class RegisterFragment extends Fragment {
    RegisterFragmentBinding registerFragmentBinding;
    LoginActivity loginActivity;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        loginActivity = (LoginActivity) getActivity();
        registerFragmentBinding.btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity.getFragment(LoginFragment.newInstance());
            }
        });
        return registerFragmentBinding.getRoot();
    }

}

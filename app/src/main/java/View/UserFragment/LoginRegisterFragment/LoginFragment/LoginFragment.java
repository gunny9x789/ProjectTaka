package View.userFragment.LoginRegisterFragment.LoginFragment;

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

import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.databinding.LoginFragmentBinding;

import View.userFragment.LoginRegisterFragment.RegisterFragment.RegisterFragment;

public class LoginFragment extends Fragment {
    LoginFragmentBinding loginFragmentBinding;

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

        loginFragmentBinding.btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        loginFragmentBinding.tvLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(RegisterFragment.newInstance());
            }
        });
        return loginFragmentBinding.getRoot();
    }
    public void getFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.log_reg_fragment, fragment).commit();
    }
}

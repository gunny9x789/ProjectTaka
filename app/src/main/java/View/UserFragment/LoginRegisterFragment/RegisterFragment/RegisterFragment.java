package View.userFragment.LoginRegisterFragment.RegisterFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myproject.R;
import com.example.myproject.databinding.RegisterFragmentBinding;

import View.userFragment.LoginRegisterFragment.LoginFragment.LoginFragment;

public class RegisterFragment extends Fragment {
    RegisterFragmentBinding registerFragmentBinding;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment,container,false);

        registerFragmentBinding.btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(LoginFragment.newInstance());
            }
        });
        return registerFragmentBinding.getRoot();
    }
    public void getFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.log_reg_fragment, fragment).commit();
    }
}

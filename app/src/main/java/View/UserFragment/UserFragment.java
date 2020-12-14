package View.userFragment;

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

public class UserFragment extends Fragment {
    UserFragmentBinding userFragmentBinding;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.user_fragment,container,false);
        userFragmentBinding.tvLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return  userFragmentBinding.getRoot();
    }
}

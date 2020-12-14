package View.newsFeedFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myproject.R;
import com.example.myproject.databinding.NewsFeedFragmentBinding;

public class NewsFeedFragment extends Fragment {
    NewsFeedFragmentBinding newsFeedFragmentBinding;

    public static NewsFeedFragment newInstance() {
        Bundle args = new Bundle();
        NewsFeedFragment fragment = new NewsFeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsFeedFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.news_feed_fragment,container,false);
        newsFeedFragmentBinding.imgSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsFeedFragmentBinding.titleNewBar.setVisibility(View.GONE);
                newsFeedFragmentBinding.searchBarNewFeed.setVisibility(View.VISIBLE);
            }
        });
        newsFeedFragmentBinding.imgBackSearchBarNewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsFeedFragmentBinding.titleNewBar.setVisibility(View.VISIBLE);
                newsFeedFragmentBinding.searchBarNewFeed.setVisibility(View.GONE);
            }
        });
        return newsFeedFragmentBinding.getRoot();
    }
}

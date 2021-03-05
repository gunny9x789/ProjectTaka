package View.NotificationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.databinding.NotificationFragmentBinding;

import java.util.List;

import AllListForder.Object.Notification;
import View.NotificationFragment.adapter.AdapterRCVNotification;
import View.NotificationFragment.adapter.ShowNotification;
import support_functions.SqlLiteHelper;

public class NotificationFragment extends Fragment {
    NotificationFragmentBinding notificationFragmentBinding;
    private AdapterRCVNotification adapterRCVNotification;
    private SqlLiteHelper sqlLiteHelper;
    private List<Notification> notificationList;

    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        notificationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.notification_fragment, container, false);
        sqlLiteHelper = new SqlLiteHelper(getContext());
        notificationList = sqlLiteHelper.getAllListNotification();

        adapterRCVNotification = new AdapterRCVNotification();
        adapterRCVNotification.setDataNotification(notificationList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext(), RecyclerView.HORIZONTAL, false);
        notificationFragmentBinding.showTileNotificationRCV.setLayoutManager(layoutManager);
        notificationFragmentBinding.showTileNotificationRCV.setAdapter(adapterRCVNotification);

        adapterRCVNotification.setClickNotification(new ShowNotification() {
            @Override
            public void onNotificationClick(String notification) {
                notificationFragmentBinding.tvShowNotification.setText(notification);
            }
        });
        return notificationFragmentBinding.getRoot();
    }
}

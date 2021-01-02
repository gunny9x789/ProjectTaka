package View.UserFragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import AllListForder.Object.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRCVManagerUser extends RecyclerView.Adapter<AdapterRCVManagerUser.ViewHolder> {
    List<User> userList;

    public void setDataUserList(List<User> dataUserList) {
        this.userList = dataUserList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_manager_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userList.get(position);
        String avatarUrl = user.getAvatar();
        if (avatarUrl.trim().isEmpty() || avatarUrl == null) {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            Picasso.get().load(avatarUrl)
                    .placeholder(R.drawable.dont_loading_img)
                    .error(R.drawable.dont_loading_img)
                    .into(holder.circleImageView);
        }
        holder.tvAccName.setText(": " + user.getAccountName());
        holder.tvAccPass.setText(": " + user.getAccountPass());
        holder.tvFistNameUser.setText(": " + user.getUserFistName());
        holder.tvLastNameUser.setText(": " + user.getUserLastName());
        holder.tvEmail.setText(": " + user.getUserEmail());
        holder.tvPhone.setText(": " + user.getUserPhone());
        holder.tvAddress.setText(": " + user.getAddress());
        holder.tvType.setText(": " + user.getAccountType());
    }

    @Override
    public int getItemCount() {
        if (userList == null || userList.isEmpty()) {
            return 0;
        }
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView circleImageView;
        private final TextView tvAccName;
        private final TextView tvAccPass;
        private final TextView tvFistNameUser;
        private final TextView tvLastNameUser;
        private final TextView tvEmail;
        private final TextView tvPhone;
        private final TextView tvAddress;
        private final TextView tvType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.img_managerUser_avatar);
            tvAccName = itemView.findViewById(R.id.manager_user_tv_name_account);
            tvAccPass = itemView.findViewById(R.id.manager_user_tv_pass_account);
            tvFistNameUser = itemView.findViewById(R.id.manager_user_tv_FirstName_User);
            tvLastNameUser = itemView.findViewById(R.id.manager_user_tv_LastName_User);
            tvEmail = itemView.findViewById(R.id.manager_user_tv_Email_account);
            tvPhone = itemView.findViewById(R.id.manager_user_tv_phone_account);
            tvAddress = itemView.findViewById(R.id.manager_user_tv_address_account);
            tvType = itemView.findViewById(R.id.manager_user_tv_type_account);
        }
    }
}

package View.CategoryFragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;

import java.util.List;

import AllListForder.Object.MainCategory;

public class MainCategoryRCVAdapter extends RecyclerView.Adapter<MainCategoryRCVAdapter.ViewHolder> {

    List<MainCategory> mainCategoryList;

    public MainCategoryRCVAdapter(List<MainCategory> mainCategoryList) {
        this.mainCategoryList = mainCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_main_category,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MainCategory mainCategory = mainCategoryList.get(position);

        holder.tvMainCategoryName.setText(mainCategory.getMainCategoryName());
        holder.imgMainCategory.setImageResource(mainCategory.getMainCategoryAvatar());

    }

    @Override
    public int getItemCount() {
        return mainCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMainCategory;
        TextView tvMainCategoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMainCategory = itemView.findViewById(R.id.img_item_main_category);
            tvMainCategoryName = itemView.findViewById(R.id.tv_name_item_main_category);

        }
    }
}

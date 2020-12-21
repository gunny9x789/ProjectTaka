package support_functions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListenerLinear extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager linearLayoutManager;

    public PaginationListenerLinear(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if (isLoadingLinear() || isLatePageLinear()) {
            return;
        }
        if (firstVisibleItemPosition >= 0 && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
            loadMoreItemLinear();
        }
    }

    public abstract void loadMoreItemLinear();

    public abstract boolean isLoadingLinear();

    public abstract boolean isLatePageLinear();
}

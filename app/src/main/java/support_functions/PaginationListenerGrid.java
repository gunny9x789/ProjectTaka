package support_functions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListenerGrid extends RecyclerView.OnScrollListener {
    private final GridLayoutManager gridLayoutManager;

    public PaginationListenerGrid(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int vibileItemCount = gridLayoutManager.getChildCount();
        int totalItemCount = gridLayoutManager.getItemCount();
        int firstVisiblePosition = gridLayoutManager.findFirstVisibleItemPosition();

        if (isLoadingGrid() || isLastPageGrid()) {
            return;
        }
        if (firstVisiblePosition >= 0 && (vibileItemCount + firstVisiblePosition) >= totalItemCount) {
            loadMoreItemGrid();
        }
    }

    public abstract void loadMoreItemGrid();

    public abstract boolean isLoadingGrid();

    public abstract boolean isLastPageGrid();
}

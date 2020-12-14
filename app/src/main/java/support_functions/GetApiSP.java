package support_functions;

import java.util.List;

import AllListForder.Object.ItemSell;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetApiSP {
    @GET("DemoSanPham")
    Call<List<ItemSell>> ITEM_SELLS_CALL();
}

package AllListForder.Object;

public class ItemBuy {
    private int id, idItem, priceItem, purchased, idUserSell, idUserbuy;
    private String itemName, avatarItem, timeBuy;

    public ItemBuy(int id, int idItem, int priceItem, int purchased, int idUserSell, int idUserbuy, String itemName, String avatarItem, String timeBuy) {
        this.id = id;
        this.idItem = idItem;
        this.priceItem = priceItem;
        this.purchased = purchased;
        this.idUserSell = idUserSell;
        this.idUserbuy = idUserbuy;
        this.itemName = itemName;
        this.avatarItem = avatarItem;
        this.timeBuy = timeBuy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(int priceItem) {
        this.priceItem = priceItem;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public int getIdUserSell() {
        return idUserSell;
    }

    public void setIdUserSell(int idUserSell) {
        this.idUserSell = idUserSell;
    }

    public int getIdUserbuy() {
        return idUserbuy;
    }

    public void setIdUserbuy(int idUserbuy) {
        this.idUserbuy = idUserbuy;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAvatarItem() {
        return avatarItem;
    }

    public void setAvatarItem(String avatarItem) {
        this.avatarItem = avatarItem;
    }

    public String getTimeBuy() {
        return timeBuy;
    }

    public void setTimeBuy(String timeBuy) {
        this.timeBuy = timeBuy;
    }
}

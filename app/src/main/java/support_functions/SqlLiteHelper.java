package support_functions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import AllListForder.Object.InfoLogin;
import AllListForder.Object.ItemBuy;
import AllListForder.Object.SQLKey;
import AllListForder.Object.User;

public class SqlLiteHelper extends SQLiteOpenHelper implements SQLKey {
    private static final String DB_NAME = "UserList.db";
    private static final String DB_USER_TABLE = "User";
    private static final String DB_CHECK_LOGIN_TABLE = "CheckLogin";
    private static final String DB_ITEMBUY = "ItemBuy";
    private static final int DB_VERSION = 1;

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    private Cursor cursorUser;
    private Cursor cursorCheckLogin;
    private Cursor cursorItemBuy;


    public SqlLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateUserTable = "CREATE TABLE User(" +
                "idUser INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "accountType TEXT," +
                "accountName TEXT," +
                "accountPass TEXT," +
                "userFistName TEXT," +
                "userLastName TEXT," +
                "userEmail TEXT," +
                "userPhone TEXT," +
                "sex TEXT," +
                "sourceAvatar TEXT," +
                "avatar TEXT," +
                "address TEXT)";
        String queryCreateCheckLoginTable = "CREATE TABLE CheckLogin(" +
                "idCheckLogin INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nameUserLogin TEXT," +
                "infoLogin TEXT)";

        String queryCreateItemBuyTable = "CREATE TABLE ItemBuy(" +
                "idTrade INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "idItemBuy INTEGER," +
                "idItemName TEXT," +
                "priceOnceItem INTEGER," +
                "purchased INTEGER," +
                "totalItemPrice INTEGER," +
                "avatarItem TEXT," +
                "timeBuy TEXT," +
                "nameAccountSell TEXT," +
                "nameAccountBuy TEXT)";
        db.execSQL(queryCreateUserTable);
        db.execSQL(queryCreateCheckLoginTable);
        db.execSQL(queryCreateItemBuyTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);
        }
    }

    public void addUserTable(User user) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(USER_TYPE, user.getAccountType());
        contentValues.put(USER_ACCOUNT, user.getAccountName());
        contentValues.put(USER_PASS, user.getAccountPass());
        contentValues.put(USER_FIST_NAME, user.getUserFistName());
        contentValues.put(USER_LAST_NAME, user.getUserLastName());
        contentValues.put(USER_EMAIL, user.getUserEmail());
        contentValues.put(USER_PHONE, user.getUserPhone());
        contentValues.put(USER_SEX, user.getSex());
        contentValues.put(USER_SOURCE_AVATAR, user.getSourceAvatar());
        contentValues.put(USER_AVATAR, user.getAvatar());
        contentValues.put(USER_ADDRESS, user.getAddress());

        sqLiteDatabase.insert(DB_USER_TABLE, null, contentValues);
        closeDB();
    }

    public void addCheckLoginTable(InfoLogin infoLogin) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(NAME_USER_LOGIN, infoLogin.getNameUserLogin());
        contentValues.put(INFO_LOGIN, infoLogin.getInfoLogin());

        sqLiteDatabase.insert(DB_CHECK_LOGIN_TABLE, null, contentValues);
        closeDB();
    }

    public void addItemBuy(ItemBuy itemBuy) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(ID_ITEM_BUY, itemBuy.getIdItem());
        contentValues.put(ID_ITEM_NAME, itemBuy.getItemName());
        contentValues.put(PRICE_ONCE_ITEM, itemBuy.getPriceOnceItem());
        contentValues.put(PURCHASED, itemBuy.getPurchased());
        contentValues.put(TOTAL_PRICE_ITEM, itemBuy.getTotalItemPrice());
        contentValues.put(ITEM_AVATAR, itemBuy.getAvatarItem());
        contentValues.put(ACCOUNT_NAME_SELL_ITEM, itemBuy.getNameAccountSell());
        contentValues.put(ACCOUNT_NAME_SELL_BUY, itemBuy.getNameAccountBuy());
        contentValues.put(TIME_BUY, itemBuy.getTimeBuy());

        sqLiteDatabase.insert(DB_ITEMBUY, null, contentValues);
        closeDB();
    }

    public void editUserTable(User user) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(USER_TYPE, user.getAccountType());
        contentValues.put(USER_ACCOUNT, user.getAccountName());
        contentValues.put(USER_PASS, user.getAccountPass());
        contentValues.put(USER_FIST_NAME, user.getUserFistName());
        contentValues.put(USER_LAST_NAME, user.getUserLastName());
        contentValues.put(USER_EMAIL, user.getUserEmail());
        contentValues.put(USER_PHONE, user.getUserPhone());
        contentValues.put(USER_SOURCE_AVATAR, user.getSourceAvatar());
        contentValues.put(USER_SEX, user.getSex());
        contentValues.put(USER_AVATAR, user.getAvatar());
        contentValues.put(USER_ADDRESS, user.getAddress());

        sqLiteDatabase.update(DB_USER_TABLE, contentValues, "idUser=?",
                new String[]{String.valueOf(user.getIdUser())});
        closeDB();
    }

    public void delUser(int idUser) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_USER_TABLE, "idUser=?", new String[]{String.valueOf(idUser)});
        closeDB();
    }

    public void delAllUser() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_USER_TABLE, null, null);
        closeDB();
    }

    public List<User> getAllListUser() {
        List<User> userList = new ArrayList<>();
        User user;

        sqLiteDatabase = getReadableDatabase();
        cursorUser = sqLiteDatabase.query(false, DB_USER_TABLE, null
                , null, null, null, null, null, null);
        while (cursorUser.moveToNext()) {
            int id = cursorUser.getInt(cursorUser.getColumnIndex(ID_USER));
            String userType = cursorUser.getString(cursorUser.getColumnIndex(USER_TYPE));
            String userAccount = cursorUser.getString(cursorUser.getColumnIndex(USER_ACCOUNT));
            String userPass = cursorUser.getString(cursorUser.getColumnIndex(USER_PASS));
            String userFistName = cursorUser.getString(cursorUser.getColumnIndex(USER_FIST_NAME));
            String userLastName = cursorUser.getString(cursorUser.getColumnIndex(USER_LAST_NAME));
            String userEmail = cursorUser.getString(cursorUser.getColumnIndex(USER_EMAIL));
            String userPhone = cursorUser.getString(cursorUser.getColumnIndex(USER_PHONE));
            String userSex = cursorUser.getString(cursorUser.getColumnIndex(USER_SEX));
            String userSourceAvatar = cursorUser.getString(cursorUser.getColumnIndex(USER_SOURCE_AVATAR));
            String userAvatar = cursorUser.getString(cursorUser.getColumnIndex(USER_AVATAR));
            String userAddress = cursorUser.getString(cursorUser.getColumnIndex(USER_ADDRESS));

            user = new User(id, userType, userAccount, userPass, userFistName, userLastName, userEmail
                    , userPhone, userAddress, userSex, userSourceAvatar, userAvatar);
            userList.add(user);
        }
        closeDB();
        return userList;
    }

    public List<InfoLogin> getAllListCheckLogin() {
        List<InfoLogin> infoLoginList = new ArrayList<>();
        InfoLogin infoLogin;
        sqLiteDatabase = getReadableDatabase();
        cursorCheckLogin = sqLiteDatabase.query(false, DB_CHECK_LOGIN_TABLE, null
                , null, null, null, null, null, null);

        while (cursorCheckLogin.moveToNext()) {
            int id = cursorCheckLogin.getInt(cursorCheckLogin.getColumnIndex(ID_CHECK_LOGIN));
            String nameUserLogin = cursorCheckLogin.getString(cursorCheckLogin.getColumnIndex(NAME_USER_LOGIN));
            String infoLogin1 = cursorCheckLogin.getString(cursorCheckLogin.getColumnIndex(INFO_LOGIN));
            infoLogin = new InfoLogin(id, nameUserLogin, infoLogin1);
            infoLoginList.add(infoLogin);
        }
        closeDB();
        return infoLoginList;
    }

    public List<ItemBuy> getAllListItemBuy() {
        List<ItemBuy> itemBuyList = new ArrayList<>();
        ItemBuy itemBuy;
        sqLiteDatabase = getReadableDatabase();
        cursorItemBuy = cursorCheckLogin = sqLiteDatabase.query(false, DB_ITEMBUY, null
                , null, null, null, null, null, null);

        while (cursorItemBuy.moveToNext()) {
            int idTrade = cursorItemBuy.getInt(cursorItemBuy.getColumnIndex(ID_TRADE));
            int idItemSell = cursorItemBuy.getInt(cursorItemBuy.getColumnIndex(ID_ITEM_BUY));
            String itemName = cursorItemBuy.getString(cursorItemBuy.getColumnIndex(ID_ITEM_NAME));
            int priceOnceItem = cursorItemBuy.getInt(cursorItemBuy.getColumnIndex(PRICE_ONCE_ITEM));
            int purchased = cursorItemBuy.getInt(cursorItemBuy.getColumnIndex(PURCHASED));
            int totalItemPrice = cursorItemBuy.getInt(cursorItemBuy.getColumnIndex(TOTAL_PRICE_ITEM));
            String itemAvatar = cursorItemBuy.getString(cursorItemBuy.getColumnIndex(ITEM_AVATAR));
            String timeBuy = cursorItemBuy.getString(cursorItemBuy.getColumnIndex(TIME_BUY));
            String nameAccountSell = cursorItemBuy.getString(cursorItemBuy.getColumnIndex(ACCOUNT_NAME_SELL_ITEM));
            String nameAccountBuy = cursorItemBuy.getString(cursorItemBuy.getColumnIndex(ACCOUNT_NAME_SELL_BUY));
            itemBuy = new ItemBuy(idTrade, idItemSell, priceOnceItem, totalItemPrice, purchased, nameAccountSell
                    , nameAccountBuy, itemName, itemAvatar, timeBuy);
            itemBuyList.add(itemBuy);
        }
        closeDB();
        return itemBuyList;
    }

    private void closeDB() {
        if (contentValues != null) contentValues.clear();
        if (cursorUser != null) cursorUser.close();
        if (cursorCheckLogin != null) cursorCheckLogin.close();
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }
}

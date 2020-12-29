package support_functions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import AllListForder.Object.SQLKey;
import AllListForder.Object.User;

public class SqlLiteHelper extends SQLiteOpenHelper implements SQLKey {
    private static final String DB_NAME = "UserList.db";
    private static final String DB_USER_TABLE = "User";
    private static final int DB_VERSION = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    private Cursor cursor;


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
                "avatar TEXT," +
                "address TEXT)";
        db.execSQL(queryCreateUserTable);
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
        contentValues.put(USER_AVATAR, user.getAvatar());
        contentValues.put(USER_ADDRESS, user.getAddress());

        sqLiteDatabase.insert(DB_USER_TABLE, null, contentValues);
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
        contentValues.put(USER_SEX, user.getSex());
        contentValues.put(USER_AVATAR, user.getAvatar());
        contentValues.put(USER_ADDRESS, user.getAddress());

        sqLiteDatabase.update(DB_USER_TABLE, contentValues, "idUser=?",
                new String[]{String.valueOf(user.getIdUser())});
        closeDB();
    }

    public void delUser(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_USER_TABLE, "idUser=?", new String[]{String.valueOf(id)});
        closeDB();
    }

    public void delAllUser(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_USER_TABLE, null, null);
        closeDB();
    }

    public List<User> getAllListUser() {
        List<User> userList = new ArrayList<>();
        User user;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_USER_TABLE, null
                , null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID_USER));
            String userType = cursor.getString(cursor.getColumnIndex(USER_TYPE));
            String userAccount = cursor.getString(cursor.getColumnIndex(USER_ACCOUNT));
            String userPass = cursor.getString(cursor.getColumnIndex(USER_PASS));
            String userFistName = cursor.getString(cursor.getColumnIndex(USER_FIST_NAME));
            String userLastName = cursor.getString(cursor.getColumnIndex(USER_LAST_NAME));
            String userEmail = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
            String userPhone = cursor.getString(cursor.getColumnIndex(USER_PHONE));
            String userSex = cursor.getString(cursor.getColumnIndex(USER_SEX));
            String userAvatar = cursor.getString(cursor.getColumnIndex(USER_AVATAR));
            String userAddress = cursor.getString(cursor.getColumnIndex(USER_ADDRESS));

            user = new User(id, userType, userAccount, userPass, userFistName, userLastName, userEmail
                    , userPhone, userAddress, userSex, userAvatar);
            userList.add(user);
        }
        closeDB();
        return userList;
    }

    private void closeDB() {
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }
}

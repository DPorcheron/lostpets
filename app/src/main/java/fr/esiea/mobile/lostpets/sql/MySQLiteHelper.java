package fr.esiea.mobile.lostpets.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by david on 03/11/2014.
 */
//This class manage SQLite requests
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_ZIPCODE = "zipcode";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PHONE = "phone";

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USER + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LASTNAME + " text not null, "
            + COLUMN_FIRSTNAME + " text not null, "
            + COLUMN_ADDRESS + " text not null, "
            + COLUMN_ZIPCODE + " text not null, "
            + COLUMN_CITY + " text not null, "
            + COLUMN_PHONE + " text not null"
            + " );";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

}

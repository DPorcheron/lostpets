package fr.esiea.mobile.lostpets.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import fr.esiea.mobile.lostpets.model.User;
import fr.esiea.mobile.lostpets.sql.MySQLiteHelper;

/**
 * Created by david on 03/11/2014.
 */
//This class is the DAO for the SQLite database
public class UserDataSource {
    // Database fields
    private SQLiteDatabase m_database;
    private MySQLiteHelper m_dbHelper;
    private String[] allColumns = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_LASTNAME,
            MySQLiteHelper.COLUMN_FIRSTNAME,
            MySQLiteHelper.COLUMN_ADDRESS,
            MySQLiteHelper.COLUMN_ZIPCODE,
            MySQLiteHelper.COLUMN_CITY,
            MySQLiteHelper.COLUMN_PHONE,
    };

    public UserDataSource(Context context) {
        m_dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        m_database = m_dbHelper.getWritableDatabase();
    }

    public void close() {
        m_database.close();
    }

    public long insertUser(String lastName, String firstName, String address, String zipCode, String city, String phone) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ID, 0);
        values.put(MySQLiteHelper.COLUMN_LASTNAME, lastName);
        values.put(MySQLiteHelper.COLUMN_FIRSTNAME, firstName);
        values.put(MySQLiteHelper.COLUMN_ADDRESS, address);
        values.put(MySQLiteHelper.COLUMN_ZIPCODE, zipCode);
        values.put(MySQLiteHelper.COLUMN_CITY, city);
        values.put(MySQLiteHelper.COLUMN_PHONE, phone);

        return m_database.insert(MySQLiteHelper.TABLE_USER, null, values);
    }

    public int updateUser(int id, User user){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_LASTNAME, user.getM_userLastName());
        values.put(MySQLiteHelper.COLUMN_FIRSTNAME, user.getM_userFirstName());
        values.put(MySQLiteHelper.COLUMN_ADDRESS, user.getM_userAddress());
        values.put(MySQLiteHelper.COLUMN_ZIPCODE, user.getM_userZipCode());
        values.put(MySQLiteHelper.COLUMN_CITY, user.getM_userCity());
        values.put(MySQLiteHelper.COLUMN_PHONE, user.getM_userPhone());
        return m_database.update(MySQLiteHelper.TABLE_USER, values, MySQLiteHelper.COLUMN_ID + " = " +id, null);
    }

    public User getUserById(int id) {
        User user = null;

        if (m_database != null) {
            Cursor cursor = m_database.query(MySQLiteHelper.TABLE_USER,
                    allColumns, MySQLiteHelper.COLUMN_ID + " LIKE " + id, null, null, null, null);
            user = cursorToUser(cursor);

            // Make sure to close the cursor
            cursor.close();
        }

        return user;
    }

    private User cursorToUser(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }

        cursor.moveToFirst();

        User user = new User();
        user.setM_userId(cursor.getInt(0));
        user.setM_userLastName(cursor.getString(1));
        user.setM_userFirstName(cursor.getString(2));
        user.setM_userAddress(cursor.getString(3));
        user.setM_userZipCode(cursor.getString(4));
        user.setM_userCity(cursor.getString(5));
        user.setM_userPhone(cursor.getString(6));

        return user;
    }
}

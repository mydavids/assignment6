package com.yusirydavids.barsystem.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yusirydavids.barsystem.domain.Administrator;
import com.yusirydavids.barsystem.repository.AdministratorRepository;

/**
 * Created by Yusiry Davids on 4/24/2016.
 */
public class AdministratorRepostioryImpl extends SQLiteOpenHelper implements AdministratorRepository {

    public static final String TABLE_ADMIN = "Administrator";
    public static final String DATABASE_NAME = "barsystem";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private String name;
    private String surname;
    private String password;
    private String idNumber;

    public static final String COLUMN_IDNUMBER = "idNumber";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_PASSWORD = "password";

    //DB Creation SQL Statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ADMIN + "("
            + COLUMN_IDNUMBER + " text primary key, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_SURNAME + " text not null, "
            + COLUMN_PASSWORD + " text not null);";

    public AdministratorRepostioryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public AdministratorRepostioryImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }

    @Override
    public void create(Administrator admin) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDNUMBER, admin.getIdNumber());
        values.put(COLUMN_NAME, admin.getName());
        values.put(COLUMN_SURNAME, admin.getSurname());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        db.insert(TABLE_ADMIN, null, values);
    }

    @Override
    public Administrator findById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN,
                new String[]{COLUMN_IDNUMBER, COLUMN_NAME, COLUMN_SURNAME, COLUMN_PASSWORD},
                COLUMN_IDNUMBER + " =?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor!=null)
            cursor.moveToFirst();

        Administrator administrator = new Administrator();
        administrator.setIdNumber(cursor.getString(0));
        administrator.setName(cursor.getString(1));
        administrator.setSurname(cursor.getString(2));
        administrator.setPassword(cursor.getString(3));

        return administrator;
    }

    @Override
    public void update(Administrator admin) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, admin.getName());
        values.put(COLUMN_SURNAME, admin.getSurname());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        db.update(
                TABLE_ADMIN, values, COLUMN_IDNUMBER + "=?",
                new String[]{String.valueOf(admin.getIdNumber())}
        );
    }

    @Override
    public void delete(Administrator admin) {
        db.delete(TABLE_ADMIN,
                COLUMN_IDNUMBER + "=?",
                new String[]{String.valueOf(admin.getIdNumber())});
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);
    }
}

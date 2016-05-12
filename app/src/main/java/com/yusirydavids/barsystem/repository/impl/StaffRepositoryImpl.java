package com.yusirydavids.barsystem.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yusirydavids.barsystem.domain.Staff;
import com.yusirydavids.barsystem.repository.StaffRepository;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class StaffRepositoryImpl extends SQLiteOpenHelper implements StaffRepository {

    public static final String TABLE_STAFF = "Staff";
    public static final String DATABASE_NAME = "barsystem";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private String name;
    private String surname;
    private String password;
    private String idNumber;
    private String accessLevel;

    public static final String COLUMN_ID = "idNumber";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACCESS_LEVEL = "accessLevel";

    //DB Creation SQL Statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_STAFF + "("
            + COLUMN_ID + " text primary key, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_SURNAME + " text not null, "
            + COLUMN_PASSWORD + " text not null, "
            + COLUMN_ACCESS_LEVEL + " text not null, ";

    public StaffRepositoryImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close(){ this.close(); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
        onCreate(db);
    }

    @Override
    public void create(Staff staff) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, staff.getIdNumber());
        values.put(COLUMN_NAME, staff.getName());
        values.put(COLUMN_SURNAME, staff.getSurname());
        values.put(COLUMN_PASSWORD, staff.getPassword());
        values.put(COLUMN_ACCESS_LEVEL, staff.getAccessLevel());
        db.insert(TABLE_STAFF, null, values);

    }

    @Override
    public Staff findById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STAFF,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_SURNAME, COLUMN_PASSWORD, COLUMN_ACCESS_LEVEL},
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null)
            cursor.moveToFirst();

        Staff staff = new Staff();
        staff.setIdNumber(cursor.getString(0));
        staff.setName(cursor.getString(1));
        staff.setSurname(cursor.getString(2));
        staff.setPassword(cursor.getString(3));
        staff.setAccessLevel(cursor.getString(4));

        return staff;
    }

    @Override
    public void update(Staff staff) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, staff.getName());
        values.put(COLUMN_SURNAME, staff.getSurname());
        values.put(COLUMN_PASSWORD, staff.getPassword());
        values.put(COLUMN_ACCESS_LEVEL, staff.getAccessLevel());
        db.update(TABLE_STAFF, values,
                COLUMN_ID + " =?",
                new String[]{String.valueOf(staff.getIdNumber())});

    }

    @Override
    public void delete(Staff staff) {
        db.delete(TABLE_STAFF, COLUMN_ID + "=?",
                new String[]{String.valueOf(staff.getIdNumber())});
    }

    @Override
    public ArrayList<Staff> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Staff> staffs = new ArrayList<>();
        open();
        Cursor cursor = db.query(TABLE_STAFF, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                final Staff staff = new Staff.Builder()
                        .idNumber(cursor.getString(0))
                        .name(cursor.getString(1))
                        .surname(cursor.getString(2))
                        .password(cursor.getString(3))
                        .accessLevel(cursor.getString(4))
                        .build();
                staffs.add(staff);
            }while (cursor.moveToNext());
        }
        return staffs;
    }
}

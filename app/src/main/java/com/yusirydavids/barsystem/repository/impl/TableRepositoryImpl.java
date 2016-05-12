package com.yusirydavids.barsystem.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.TabHost;

import com.yusirydavids.barsystem.domain.Table;
import com.yusirydavids.barsystem.repository.TableRepository;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class TableRepositoryImpl extends SQLiteOpenHelper implements TableRepository{

    public static final String TABLE_TABLE = "Table";
    public static final String DATABASE_NAME = "barsystem";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private String id;
    private int tableNumber;
    private int seating;
    private String location;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TABLE_NUMBER = "tablenumber";
    public static final String COLUMN_SEATING = "seating";
    public static final String COLUMN_LOCATION = "location";

    //DB Creation SQL Statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_TABLE + "("
            + COLUMN_ID + " text primary key, "
            + COLUMN_TABLE_NUMBER + " text not null, "
            + COLUMN_SEATING + " text not null, "
            + COLUMN_LOCATION + " text not null);";

    public TableRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close(){ this.close(); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLE);
        onCreate(db);
    }

    @Override
    public void create(Table table) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, table.getId());
        values.put(COLUMN_TABLE_NUMBER, table.getTableNumber());
        values.put(COLUMN_SEATING, table.getSeating());
        values.put(COLUMN_LOCATION, table.getLocation());
        db.insert(TABLE_TABLE, null, values);
    }

    @Override
    public Table findById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TABLE,
                new String[]{COLUMN_ID, COLUMN_TABLE_NUMBER, COLUMN_SEATING, COLUMN_LOCATION},
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},
                null, null, null , null);

        if(cursor != null)
            cursor.moveToFirst();

        Table table = new Table();
        table.setId(cursor.getString(0));
        table.setTableNumber(Integer.parseInt(cursor.getString(1)));
        table.setSeating(Integer.parseInt(cursor.getString(2)));
        table.setLocation(cursor.getColumnName(3));

        return table;
    }

    @Override
    public void update(Table table) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TABLE_NUMBER, table.getTableNumber());
        values.put(COLUMN_SEATING, table.getSeating());
        values.put(COLUMN_LOCATION, table.getLocation());
        db.update(TABLE_TABLE, values, COLUMN_ID + " =?",
                new String[]{String.valueOf(table.getId())}
        );
    }

    @Override
    public void delete(Table table) {
        db.delete(TABLE_TABLE, COLUMN_ID + " =?",
                new String[]{String.valueOf(table.getId())});
    }
}

package com.yusirydavids.barsystem.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yusirydavids.barsystem.domain.Order;
import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.repository.OrderRepository;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class OrderRepositoryImpl extends SQLiteOpenHelper implements OrderRepository {

    public static final String TABLE_ORDER = "Order";
    public static final String DATABASE_NAME = "barsystem";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private String id;
    private String date;
    private double amount;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNT = "amount";

    //DB Cresation SQL Statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ORDER + "("
            + COLUMN_ID + " text primary key, "
            + COLUMN_DATE + " text not null, "
            + COLUMN_AMOUNT + " int not null);";

    public OrderRepositoryImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }


    @Override
    public void create(Order order) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, order.getId());
        values.put(COLUMN_DATE, order.getDate());
        values.put(COLUMN_AMOUNT, order.getAmount());
        db.insert(TABLE_ORDER, null, values);
    }

    @Override
    public Order findById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ORDER,
                new String[]{COLUMN_ID, COLUMN_DATE, COLUMN_AMOUNT},
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null)
            cursor.moveToFirst();

        Order order = new Order();
        order.setId(cursor.getString(0));
        order.setDate(cursor.getString(1));
        order.setAmount(Integer.parseInt(cursor.getString(2)));

        return order;
    }

    @Override
    public void update(Order order) {

    ContentValues values = new ContentValues();
        values.put(COLUMN_ID, order.getId());
        values.put(COLUMN_DATE, order.getDate());
        values.put(COLUMN_AMOUNT, order.getAmount());
        db.update(
                TABLE_ORDER, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(order.getId())}
        );

    }


    @Override
    public void delete(Order order) {
        db.delete(TABLE_ORDER, COLUMN_ID + "=?",
                new String[]{String.valueOf(order.getId())});
    }

    @Override
    public ArrayList<Stock> findItems(Order order) {
        ArrayList<Stock> stocks = new ArrayList<>();
        stocks = order.getStock();
        return stocks;
    }

    @Override
    public ArrayList<Order> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Order> orders = new ArrayList<>();
        open();
        Cursor cursor = db.query(TABLE_ORDER, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                final Order order = new Order.Builder()
                        .id(cursor.getString(0))
                        .date(cursor.getString(1))
                        .amount(Double.parseDouble(cursor.getString(2)))
                        .build();
                orders.add(order);
            }while (cursor.moveToNext());
        }
        return orders;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }
}

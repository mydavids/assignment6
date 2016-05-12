package com.yusirydavids.barsystem.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Double2;
import android.util.Log;

import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.repository.StockRepository;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class StockRepositoryImpl extends SQLiteOpenHelper implements StockRepository{

    public static final String TABLE_STOCK = "Stock";
    public static final String DATABASE_NAME = "barsystem";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private String stockID;
    private String name;
    private double price;
    private int amountInStock;
    private String description;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DESCRIPTION = "description";

    //DB Creation SQL Statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_STOCK + "("
            + COLUMN_ID + " text not null, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_PRICE + " real not null, "
            + COLUMN_AMOUNT + " integer not null, "
            + COLUMN_DESCRIPTION + "text not null);";


    public StockRepositoryImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }

    public void close() { this.close(); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
        onCreate(db);

    }

    @Override
    public void create(Stock stock) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, stock.getId());
        values.put(COLUMN_NAME, stock.getName());
        values.put(COLUMN_PRICE, stock.getPrice());
        values.put(COLUMN_AMOUNT, stock.getAmountInStock());
        values.put(COLUMN_DESCRIPTION, stock.getDescription());
        db.insert(TABLE_STOCK, null, values);
    }

    @Override
    public Stock findById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STOCK,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PRICE, COLUMN_AMOUNT, COLUMN_DESCRIPTION},
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();

        Stock stock = new Stock();
        stock.setId(cursor.getString(0));
        stock.setName(cursor.getString(1));
        stock.setPrice(Double.parseDouble(cursor.getString(2)));
        stock.setAmountInStock(Integer.parseInt(cursor.getString(3)));
        stock.setDescription(cursor.getString(4));

        return stock;
    }

    @Override
    public void update(Stock stock) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, stock.getName());
        values.put(COLUMN_PRICE, stock.getPrice());
        values.put(COLUMN_AMOUNT, stock.getAmountInStock());
        values.put(COLUMN_DESCRIPTION, stock.getDescription());
        db.update(TABLE_STOCK, values, COLUMN_ID + " =?",
                new String[]{String.valueOf(stock.getId())}
        );
    }

    @Override
    public void delete(Stock stock) {
        db.delete(TABLE_STOCK,
                COLUMN_ID + " =?",
                new String[]{String.valueOf(stock.getId())});
    }

    @Override
    public ArrayList<Stock> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Stock> stocks = new ArrayList<>();
        open();
        Cursor cursor = db.query(TABLE_STOCK, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                final Stock stock = new Stock.Builder()
                        .id(cursor.getString(0))
                        .name(cursor.getString(1))
                        .price(Float.parseFloat(cursor.getString(2)))
                        .amountInStock(Integer.parseInt(cursor.getString(3)))
                        .description(cursor.getString(4))
                        .build();
                stocks.add(stock);
            }while (cursor.moveToNext());
        }
        return stocks;
    }
}

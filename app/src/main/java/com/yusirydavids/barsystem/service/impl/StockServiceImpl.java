package com.yusirydavids.barsystem.service.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.repository.StockRepository;
import com.yusirydavids.barsystem.repository.impl.StockRepositoryImpl;
import com.yusirydavids.barsystem.service.StockService;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class StockServiceImpl extends Service implements StockService{

    final private StockRepository repository;
    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private static StockServiceImpl service = null;

    public static StockServiceImpl getInstance(){
        if(service == null)
            service = new StockServiceImpl();
        return service;
    }

    private StockServiceImpl(){
        repository = new StockRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public boolean addStock(Stock stock) {
        try{
            repository.create(stock);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStock(Stock stock) {
        try{
            repository.update(stock);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStock(Stock stock) {
        try{
            repository.delete(stock);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Stock> getAllStock() {
        try{
            ArrayList<Stock> stock = new ArrayList<>();
            stock = repository.findAll();
            return stock;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public class ActivateServiceLocalBinder extends Binder {
        public StockServiceImpl getService(){
            return StockServiceImpl.this;
        }

    }
}

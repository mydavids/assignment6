package com.yusirydavids.barsystem.service.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yusirydavids.barsystem.domain.Order;
import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.repository.OrderRepository;
import com.yusirydavids.barsystem.repository.impl.OrderRepositoryImpl;
import com.yusirydavids.barsystem.service.OrderService;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class OrderServiceImpl extends Service implements OrderService {

    final private OrderRepository repository;
    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private static OrderServiceImpl service = null;

    public static OrderServiceImpl getInstance(){
        if(service == null)
            service = new OrderServiceImpl();
        return service;
    }

    private OrderServiceImpl(){
        repository = new OrderRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public boolean addOrder(Order order) {
        try{
            repository.create(order);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Order order) {
        try{
            repository.delete(order);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public double calculateTotal(Order order) {
        try {
            double total = 0;
            Order currentOrder = repository.findById(order.getId());
            ArrayList<Stock> stocks = currentOrder.getStock();
            for (Stock stock : stocks) {
                total = +stock.getPrice();
            }
            return total;
        }
        catch(Exception e)    {
            e.printStackTrace();
            return 0;
        }
    }



    @Override
    public ArrayList<Stock> getAllItemsInOrder(Order order) {
        try{
            ArrayList<Stock> stock = new ArrayList<>();
            stock = repository.findItems(order);
            return stock;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addItemToOrder(Stock stock, Order order) {
        try{
            order.getStock().add(stock);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public OrderServiceImpl getService(){
            return OrderServiceImpl.this;
        }
    }
}

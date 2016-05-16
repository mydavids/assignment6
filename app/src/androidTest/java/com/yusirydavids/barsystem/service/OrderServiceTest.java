package com.yusirydavids.barsystem.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.yusirydavids.barsystem.domain.Order;
import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.service.impl.OrderServiceImpl;

import junit.framework.Assert;

import org.apache.tools.ant.taskdefs.condition.Or;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class OrderServiceTest extends AndroidTestCase{
    private OrderServiceImpl orderService;
    private boolean isBound;
    Order order = new Order();
    private String id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), OrderServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);

        ArrayList<Stock> stock = new ArrayList<>();
        Stock stock1 = new Stock();
        stock.add(stock1);
        //Create
        order = new Order.Builder()
                .id("1")
                .date("02/08/2015")
                .amount(45.55)
                .stock(stock)
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            OrderServiceImpl.ActivateServiceLocalBinder binder
                    = (OrderServiceImpl.ActivateServiceLocalBinder) service;
            orderService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Test
    public void testAddOrder(){
        ArrayList<Stock> stock = new ArrayList<>();
        Stock stock1 = new Stock();
        stock.add(stock1);
        //Create
        order = new Order.Builder()
                .id("1")
                .date("02/08/2015")
                .amount(45.55)
                .stock(stock)
                .build();

        boolean isAdded = orderService.addOrder(order);
        Assert.assertEquals(true, isAdded);
    }

    @Test
    public void testDeleteOrder(){
        Order order = new Order.Builder()
                .id("1")
                .build();

        boolean isDeleted = orderService.deleteOrder(order);
        Assert.assertEquals(true, isDeleted);
    }

    @Test
    public void testCalculateTotal(){
        ArrayList<Stock> stock = new ArrayList<>();
        Stock stock1 = new Stock.Builder().stockID("1")
                .name("Stock1")
                .price(50.00)
                .build();

        Stock stock2 = new Stock.Builder().stockID("2")
                .name("Stock2")
                .price(50.00)
                .build();


        stock.add(stock1);
        stock.add(stock2);
        //Create
        order = new Order.Builder()
                .id("1")
                .date("02/08/2015")
                .stock(stock)
                .build();

        double total = orderService.calculateTotal(order);
        Assert.assertEquals(100.00, total);
    }

    @Test
    public void testGetAllItemsOrdered(){

        ArrayList<Stock> stock = new ArrayList<>();
        Stock stock1 = new Stock.Builder().stockID("1")
                .name("Stock1")
                .price(50.00)
                .build();

        Stock stock2 = new Stock.Builder().stockID("2")
                .name("Stock2")
                .price(50.00)
                .build();

        stock.add(stock1);
        stock.add(stock2);

        Order order = new Order.Builder()
                .id("1")
                .date("02/08/2015")
                .stock(stock)
                .build();

        Assert.assertNotNull(orderService.getAllItemsInOrder(order));
    }

    @Test
    public void testAddItemToOrder(){
        ArrayList<Stock> stock = new ArrayList<>();
        Stock stock1 = new Stock.Builder().stockID("1")
                .name("Stock1")
                .price(50.00)
                .build();

        Order order = new Order.Builder()
                .id("1")
                .date("02/08/2015")
                .stock(stock)
                .build();

        boolean isAdded = orderService.addItemToOrder(stock1, order);
        Assert.assertEquals(true, isAdded);

    }

}

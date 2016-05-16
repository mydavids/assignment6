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


}

package com.yusirydavids.barsystem.factory.impl;

import com.yusirydavids.barsystem.domain.Order;
import com.yusirydavids.barsystem.factory.OrderFactory;

import java.util.UUID;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class OrderFactoryImpl implements OrderFactory {
    private static OrderFactoryImpl factory = null;

    private OrderFactoryImpl(){

    }

    public static OrderFactoryImpl getInstance(){
        if(factory == null)
            factory = new OrderFactoryImpl();
        return factory;
    }

    @Override
    public Order createOrder(String date, double amount) {
        Order order = new Order.Builder()
                .id(UUID.randomUUID().toString())
                .date(date)
                .amount(amount)
                .build();
        return order;
    }
}

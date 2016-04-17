package com.yusirydavids.barsystem.factory.impl;

import com.yusirydavids.barsystem.domain.Waiter;
import com.yusirydavids.barsystem.factory.WaiterFactory;

import java.util.UUID;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class WaiterFactoryImpl implements WaiterFactory {

    private static WaiterFactoryImpl factory = null;

    private WaiterFactoryImpl(){

    }

    public static WaiterFactoryImpl getInstance(){
        if(factory == null)
            factory = new WaiterFactoryImpl();
        return factory;
    }

    @Override
    public Waiter createWaiter(String name, String surname, String idNumber) {
        Waiter waiter = new Waiter.Builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .surname(surname)
                .idNumber(idNumber)
                .build();
        return waiter;
    }
}

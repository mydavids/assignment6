package com.yusirydavids.barsystem.factory.impl;

import com.yusirydavids.barsystem.domain.Manager;
import com.yusirydavids.barsystem.factory.ManagerFactory;

import java.util.UUID;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class ManagerFactoryImpl implements ManagerFactory{

    private static ManagerFactoryImpl factory = null;

    private ManagerFactoryImpl(){

    }

    public static ManagerFactoryImpl getInstance(){
        if(factory == null)
            factory = new ManagerFactoryImpl();
        return factory;
    }

    @Override
    public Manager createManager(String name, String surname, String idNumber) {
        Manager manager = new Manager.Builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .surname(surname)
                .idNumber(idNumber)
                .build();
        return manager;
    }
}

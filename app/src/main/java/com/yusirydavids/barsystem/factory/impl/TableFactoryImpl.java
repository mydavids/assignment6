package com.yusirydavids.barsystem.factory.impl;

import com.yusirydavids.barsystem.domain.Table;
import com.yusirydavids.barsystem.factory.TableFactory;

import java.util.UUID;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class TableFactoryImpl implements TableFactory {

    private static TableFactoryImpl factory = null;

    private TableFactoryImpl(){

    }

    public static TableFactoryImpl getInstance(){
        if(factory == null)
            factory = new TableFactoryImpl();
        return factory;
    }

    @Override
    public Table createTable(int tableNumber, int seating, String location) {
        Table table = new Table.Builder()
                .id(UUID.randomUUID().toString())
                .tableNumber(tableNumber)
                .seating(seating)
                .location(location)
                .build();
        return table;
    }
}

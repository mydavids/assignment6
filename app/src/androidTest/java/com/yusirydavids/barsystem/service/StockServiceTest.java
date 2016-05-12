package com.yusirydavids.barsystem.service;

import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;

import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.service.impl.OrderServiceImpl;
import com.yusirydavids.barsystem.service.impl.StockServiceImpl;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class StockServiceTest extends AndroidTestCase {

    private StockServiceImpl stockService;
    private boolean isBound;
    Stock stock = new Stock();
    private String id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), OrderServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);

        //Create
        stock = new
    }
}

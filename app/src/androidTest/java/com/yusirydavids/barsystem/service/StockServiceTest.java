package com.yusirydavids.barsystem.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.service.impl.OrderServiceImpl;
import com.yusirydavids.barsystem.service.impl.StockServiceImpl;

import junit.framework.Assert;

import org.testng.annotations.Test;

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
        stock = new Stock.Builder()
                .id("123")
                .name("IceCream")
                .amountInStock(30)
                .price(40.0)
                .description("Ice cream")
                .build();

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StockServiceImpl.ActivateServiceLocalBinder binder
                    = (StockServiceImpl.ActivateServiceLocalBinder) service;
            stockService = binder.getService();
            isBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Test
    public void testGetAllStock(){
        Assert.assertNotNull(stockService.getAllStock());
    }

    @Test
    public void testCreateStock(){
        Stock stock = new Stock.Builder()
                .id("E01")
                .name("Coke")
                .price(40.00)
                .amountInStock(100)
                .description("Soda")
                .build();
        boolean isSave = stockService.addStock(stock);
        Assert.assertEquals(true, isSave);

    }

   @Test
   public void testUpdateStock(){
        Stock stock = new Stock.Builder()
                .id("E01")
                .name("Coca-Cola")
                .price(40.00)
                .amountInStock(100)
                .description("Soda")
                .build();
       boolean isUpdated = stockService.updateStock(stock);
       Assert.assertEquals(true, isUpdated);
   }

    @Test
    public void testRemoveStock(){
        Stock stock = new Stock.Builder()
                .id("E01")
                .name("Coca-Cola")
                .build();
        boolean isRemoved = stockService.deleteStock(stock);
        Assert.assertEquals(true, isRemoved);
    }

}

package com.yusirydavids.barsystem;

import com.yusirydavids.barsystem.domain.Stock;
import com.yusirydavids.barsystem.factory.StockFactory;
import com.yusirydavids.barsystem.factory.impl.StockFactoryImpl;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class StockTest {

    private StockFactory factory;

    @Before
    public void setUp(){
        factory = StockFactoryImpl.getInstance();
    }

    @Test
    public void testStockCreation(){
        Stock stock = factory.createStock("SE12", "Smirnoff", 35.00, 50, "Smirnoff Spin");
        Assert.assertEquals(stock.getStockID(), "SE12");
        Assert.assertEquals(stock.getName(), "Smirnoff");
        Assert.assertEquals(stock.getPrice(),35.00);
        Assert.assertEquals(stock.getAmountInStock(),50);
        Assert.assertEquals(stock.getDescription(),"Smirnoff Spin");
        Assert.assertNotNull(stock.getId());
    }

    @Test
    public void testStockUpdate(){
        Stock stock = factory.createStock("SE12", "Smirnoff", 35.00, 50, "Smirnoff Spin");
        Assert.assertEquals(stock.getStockID(), "SE12");
        Assert.assertEquals(stock.getName(), "Smirnoff");
        Assert.assertEquals(stock.getPrice(),35.00);
        Assert.assertEquals(stock.getAmountInStock(),50);
        Assert.assertEquals(stock.getDescription(),"Smirnoff Spin");
        Assert.assertNotNull(stock.getId());

        Stock updateStock = new Stock.Builder().copy(stock).amountInStock(75).build();
        Assert.assertEquals(updateStock.getAmountInStock(), 75);
        Assert.assertEquals(stock.getStockID(), updateStock.getStockID());
        Assert.assertEquals(stock.getName(),updateStock.getName());
        Assert.assertEquals(stock.getPrice(), updateStock.getPrice());
        Assert.assertEquals(stock.getDescription(), updateStock.getDescription());
        Assert.assertEquals(stock.getId(), updateStock.getId());
    }
}

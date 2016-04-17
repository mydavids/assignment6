package com.yusirydavids.barsystem.factory;

import com.yusirydavids.barsystem.domain.Stock;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public interface StockFactory {
    public Stock createStock(String stockID, String name, double price, int amountInStock, String description);
}

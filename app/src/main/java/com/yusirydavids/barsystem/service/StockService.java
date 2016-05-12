package com.yusirydavids.barsystem.service;

import com.yusirydavids.barsystem.domain.Stock;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public interface StockService {
    boolean addStock(Stock stock);
    boolean updateStock(Stock stock);
    boolean deleteStock(Stock stock);
    ArrayList<Stock> getAllStock();
}

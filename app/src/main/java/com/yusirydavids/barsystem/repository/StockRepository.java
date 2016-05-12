package com.yusirydavids.barsystem.repository;

import com.yusirydavids.barsystem.domain.Stock;

import java.util.ArrayList;

/**
 * Created by Yusiry Davids on 4/24/2016.
 */
public interface StockRepository {
    void create(Stock stock);
    Stock findById(String id);
    void update(Stock stock);
    void delete(Stock stock);
    ArrayList<Stock> findAll();
}

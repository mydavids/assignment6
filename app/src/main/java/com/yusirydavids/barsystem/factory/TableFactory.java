package com.yusirydavids.barsystem.factory;

import com.yusirydavids.barsystem.domain.Table;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public interface TableFactory {
    public Table createTable(int tableNumber, int seating, String location);

}

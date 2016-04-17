package com.yusirydavids.barsystem.factory;

import com.yusirydavids.barsystem.domain.Waiter;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public interface WaiterFactory {
    Waiter createWaiter(String name, String surname, String idNumber);
}

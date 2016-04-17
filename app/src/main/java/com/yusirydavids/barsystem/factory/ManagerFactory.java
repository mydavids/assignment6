package com.yusirydavids.barsystem.factory;

import com.yusirydavids.barsystem.domain.Manager;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public interface ManagerFactory {
    Manager createManager(String name, String surname, String idNumber);
}

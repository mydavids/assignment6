package com.yusirydavids.barsystem;

import junit.framework.TestSuite;

import org.junit.*;
/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class SuiteTest {

    @Test
    public void SuiteTest() {
        Class[] testClasses = {ManagerTest.class, OrderTest.class, StockTest.class, TableTest.class, WaiterTest.class};
        TestSuite suite = new TestSuite(testClasses);
    }

}

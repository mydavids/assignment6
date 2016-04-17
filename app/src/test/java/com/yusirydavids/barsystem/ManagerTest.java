package com.yusirydavids.barsystem;

import com.yusirydavids.barsystem.domain.Manager;
import com.yusirydavids.barsystem.factory.ManagerFactory;
import com.yusirydavids.barsystem.factory.impl.ManagerFactoryImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class ManagerTest {

    private ManagerFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = ManagerFactoryImpl.getInstance();
    }

    @Test
    public void testManagerCreation(){
        Manager manager = factory.createManager("Mike", "James", "9405321111332");
        Assert.assertEquals(manager.getName(), "Mike");
        Assert.assertEquals(manager.getSurname(), "James");
        Assert.assertEquals(manager.getIdNumber(),"9405321111332");
        Assert.assertNotNull(manager.getId());
    }

    @Test
    public void testManagerUpdate(){
        Manager manager = factory.createManager("Mike", "James", "9405321111332");
        Assert.assertEquals(manager.getName(), "Mike");
        Assert.assertEquals(manager.getSurname(), "James");
        Assert.assertEquals(manager.getIdNumber(),"9405321111332");
        Assert.assertNotNull(manager.getId());

        Manager updateManager = new Manager.Builder().copy(manager).name("Adam").build();
        Assert.assertEquals(updateManager.getName(), "Adam");
        Assert.assertEquals(manager.getSurname(), updateManager.getSurname());
        Assert.assertEquals(manager.getIdNumber(),updateManager.getIdNumber());
        Assert.assertEquals(manager.getId(), updateManager.getId());

    }



}

package com.yusirydavids.barsystem;

import com.yusirydavids.barsystem.domain.Staff;
import com.yusirydavids.barsystem.factory.WaiterFactory;
import com.yusirydavids.barsystem.factory.impl.WaiterFactoryImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class StaffTest {

    private WaiterFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = WaiterFactoryImpl.getInstance();
    }

    @Test
    public void testWaiterCreation(){
        Staff staff = factory.createWaiter("Amy", "Susan", "9502115678999");
        Assert.assertEquals(staff.getName(), "Amy");
        Assert.assertEquals(staff.getSurname(), "Susan");
        Assert.assertEquals(staff.getIdNumber(),"9502115678999");
        Assert.assertNotNull(staff.getId());
    }

    @Test
    public void testWaiterUpdate(){
        Staff staff = factory.createWaiter("Amy", "Susan", "9502115678999");
        Assert.assertEquals(staff.getName(), "Amy");
        Assert.assertEquals(staff.getSurname(), "Susan");
        Assert.assertEquals(staff.getIdNumber(),"9502115678999");
        Assert.assertNotNull(staff.getId());

        Staff updateStaff = new Staff.Builder().copy(staff).name("Carmen").build();
        Assert.assertEquals(updateStaff.getName(), "Carmen");
        Assert.assertEquals(staff.getSurname(), updateStaff.getSurname());
        Assert.assertEquals(staff.getIdNumber(), updateStaff.getIdNumber());
        Assert.assertEquals(staff.getId(), updateStaff.getId());

    }
}

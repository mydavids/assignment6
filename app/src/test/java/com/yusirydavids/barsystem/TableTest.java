package com.yusirydavids.barsystem;

import com.yusirydavids.barsystem.domain.Table;
import com.yusirydavids.barsystem.factory.TableFactory;
import com.yusirydavids.barsystem.factory.impl.TableFactoryImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yusiry Davids on 4/17/2016.
 */
public class TableTest {

    private TableFactory factory;

    @Before
    public void setUp(){
        factory = TableFactoryImpl.getInstance();
    }

    @Test
    public void testTableCreation(){
        Table table = factory.createTable(1, 5, "Outdoors");
        Assert.assertEquals(table.getTableNumber(), 1);
        Assert.assertEquals(table.getSeating(), 5);
        Assert.assertEquals(table.getLocation(),"Outdoors");
        Assert.assertNotNull(table.getId());
    }

    @Test
    public void testTableUpdate(){
        Table table = factory.createTable(1, 5, "Outdoors");
        Assert.assertEquals(table.getTableNumber(), 1);
        Assert.assertEquals(table.getSeating(), 5);
        Assert.assertEquals(table.getLocation(),"Outdoors");
        Assert.assertNotNull(table.getId());

        Table updateTable = new Table.Builder().copy(table).location("Indoors").build();
        Assert.assertEquals(updateTable.getLocation(), "Indoors");
        Assert.assertEquals(table.getTableNumber(), updateTable.getTableNumber());
        Assert.assertEquals(table.getSeating(),updateTable.getSeating());
        Assert.assertEquals(table.getId(), updateTable.getId());
    }
}

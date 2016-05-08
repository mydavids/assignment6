package com.yusirydavids.barsystem.repository;

import android.test.AndroidTestCase;
import android.util.Log;

import com.yusirydavids.barsystem.domain.Administrator;
import com.yusirydavids.barsystem.repository.impl.AdministratorRepostioryImpl;

import junit.framework.Assert;

/**
 * Created by Yusiry Davids on 4/24/2016.
 */
public class AdministratorRepoTest extends AndroidTestCase {
    private static final String TAG="DBTest";

    public void testAdmin() throws Exception{
        AdministratorRepository repo = new AdministratorRepostioryImpl(this.getContext());

        Administrator admin = new Administrator();
        admin.setIdNumber("94");
        admin.setName("Shane");
        admin.setSurname("Blue");
        admin.setPassword("1123");
        repo.create(admin);
    }

    public void testRead() throws Exception {
        AdministratorRepository repo= new AdministratorRepostioryImpl(this.getContext());
        Administrator admin = repo.findById("94");
        Log.w(this.getClass().getName(), "The results from the server is " + admin.getName() + " " + admin.getSurname());
        System.out.println("Blah");
        Assert.assertEquals("Shane", admin.getName());


    }
}

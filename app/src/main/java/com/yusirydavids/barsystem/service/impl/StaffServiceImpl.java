package com.yusirydavids.barsystem.service.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.yusirydavids.barsystem.domain.Staff;
import com.yusirydavids.barsystem.repository.StaffRepository;
import com.yusirydavids.barsystem.repository.impl.StaffRepositoryImpl;
import com.yusirydavids.barsystem.service.StaffService;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class StaffServiceImpl extends Service implements StaffService{

    final private StaffRepository repository;
    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private static StaffServiceImpl service = null;

    public static StaffServiceImpl getInstance(){
        if(service == null)
            service = new StaffServiceImpl();
        return service;
    }

    private StaffServiceImpl(){
        repository = new StaffRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public boolean addStaff(Staff staff) {
        try{
            repository.create(staff);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStaff(Staff staff) {
        try{
            repository.update(staff);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStaff(Staff staff) {
        try{
            repository.delete(staff);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Staff> getAllStaff() {
        try{
            ArrayList<Staff> staff = new ArrayList<>();
            staff = repository.findAll();
            return staff;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public class ActivateServiceLocalBinder extends Binder {
        public StaffServiceImpl getService(){
            return StaffServiceImpl.this;
        }

    }
}

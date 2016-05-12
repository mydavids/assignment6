package com.yusirydavids.barsystem.service.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yusirydavids.barsystem.domain.Staff;
import com.yusirydavids.barsystem.repository.StaffRepository;
import com.yusirydavids.barsystem.repository.impl.StaffRepositoryImpl;
import com.yusirydavids.barsystem.service.LoginService;

import java.util.Set;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class LoginServiceImpl extends Service implements LoginService {

    final private StaffRepository repository;
    private final IBinder localBinder = new ActiveServiceLocalBinder();
    private static LoginServiceImpl service = null;

    public static LoginServiceImpl getInstance(){
        if(service == null)
            service = new LoginServiceImpl();
        return service;
    }

    private LoginServiceImpl(){
        repository = new StaffRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public boolean isAUser(String userName, String password) {
        Staff staff = repository.findById(userName);
        if(staff.getIdNumber().equalsIgnoreCase(userName) && staff.getPassword().equals(password))
            return true;
        else
            return false;

    }

    @Override
    public boolean hasPermissions(String userName) {
        //Not implemented
        return false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActiveServiceLocalBinder extends Binder {
        public LoginServiceImpl getService(){
            return LoginServiceImpl.this;
        }
    }
}

package com.yusirydavids.barsystem.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.yusirydavids.barsystem.domain.Staff;
import com.yusirydavids.barsystem.service.impl.LoginServiceImpl;

/**
 * Created by Yusiry on 5/12/2016.
 */
public class LoginServiceTest extends AndroidTestCase {

    private LoginServiceImpl loginService;
    private boolean isBound;
    Staff staff = new Staff();
    private String id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), LoginServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);

        //Create
        staff = new Staff.Builder()
                .idNumber("1234567890")
                .name("Alex")
                .surname("Johnson")
                .password("123ert")
                .accessLevel("1")
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoginServiceImpl.ActiveServiceLocalBinder binder
                    = (LoginServiceImpl.ActiveServiceLocalBinder) service;
            loginService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}

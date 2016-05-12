package com.yusirydavids.barsystem.service;

/**
 * Created by Yusiry on 5/12/2016.
 */
public interface LoginService {
    boolean isAUser(String userName, String password);
    boolean hasPermissions(String userName);
}

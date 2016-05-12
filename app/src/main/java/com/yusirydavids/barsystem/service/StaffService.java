package com.yusirydavids.barsystem.service;

import com.yusirydavids.barsystem.domain.Staff;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public interface StaffService {
    boolean addStaff(Staff staff);
    boolean updateStaff(Staff staff);
    boolean deleteStaff(Staff staff);
    ArrayList<Staff> getAllStaff();

}

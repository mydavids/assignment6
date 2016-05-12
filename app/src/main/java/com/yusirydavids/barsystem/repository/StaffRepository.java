package com.yusirydavids.barsystem.repository;

import com.yusirydavids.barsystem.domain.Staff;

import java.util.ArrayList;

/**
 * Created by Yusiry Davids on 4/24/2016.
 */
public interface StaffRepository {
    void create(Staff staff);
    Staff findById(String id);
    void update(Staff staff);
    void delete(Staff staff);
    ArrayList<Staff> findAll();
}

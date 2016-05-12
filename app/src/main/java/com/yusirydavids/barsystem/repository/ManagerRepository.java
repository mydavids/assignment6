package com.yusirydavids.barsystem.repository;

import com.yusirydavids.barsystem.domain.Manager;

/**
 * Created by Yusiry Davids on 4/24/2016.
 */
public interface ManagerRepository {
    void create(Manager manager);
    Manager findById(String id);
    void update(Manager manager);
    void delete(Manager manager);
}

package com.yusirydavids.barsystem.repository;

import com.yusirydavids.barsystem.domain.Administrator;

/**
 * Created by Yusiry Davids on 4/24/2016.
 */
public interface AdministratorRepository {
    void create(Administrator admin);
    Administrator findById(String id);
    void update(Administrator admin);
    void delete(Administrator admin);
}

package com.yusirydavids.barsystem.service;

import com.yusirydavids.barsystem.domain.Order;
import com.yusirydavids.barsystem.domain.Stock;

import java.util.ArrayList;

/**
 * Created by Yusiry on 5/12/2016.
 */
public interface OrderService {
    boolean addOrder(Order order);
    boolean deleteOrder(Order order);
    double calculateTotal(Order order);
    ArrayList<Stock> getAllItemsInOrder(Order order);
    boolean addItemToOrder(Stock stock, Order order);
}

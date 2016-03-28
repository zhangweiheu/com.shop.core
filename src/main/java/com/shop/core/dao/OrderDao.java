package com.shop.core.dao;

import com.shop.core.model.Order;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
public interface OrderDao {
    List<Order> listAllOrder(int offset, int pageSize);

    int countAllOrderByAttr(Order order);

    List<Order> listOrdersByAttr(int offset, int pageSize, Order order);

    Order findOrderById(int Oid);

    List<Order> findOrderByUid(int Uid);

    int deleteOrdersById(int Oid);

    int updateOrder(Order order);

    int saveOrder(Order order);
}

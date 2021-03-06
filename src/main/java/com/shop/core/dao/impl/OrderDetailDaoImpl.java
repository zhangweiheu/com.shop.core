package com.shop.core.dao.impl;

import com.shop.core.dao.OrderDetailDao;
import com.shop.core.mapper.OrderDetailMapper;
import com.shop.core.model.OrderDetail;
import com.shop.core.model.OrderDetailCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Repository
public class OrderDetailDaoImpl implements OrderDetailDao {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> listOrderDetailByAttr(OrderDetail orderDetail) {
        return orderDetailMapper.selectByCondition(convertOrderDetailAttr2Condition(null, null, orderDetail));
    }

    @Override
    public int countAllOrderByAttr(OrderDetail orderDetail) {
        return orderDetailMapper.countByCondition(convertOrderDetailAttr2Condition(null, null, orderDetail));
    }

    @Override
    public List<OrderDetail> listCart(int offset, int pageSize, OrderDetail orderDetail) {
        return orderDetailMapper.selectByCondition(convertOrderDetailAttr2Condition(offset, pageSize, orderDetail));
    }

    @Override
    public OrderDetail findOrderDetailById(int ODid) {
        return orderDetailMapper.selectById(ODid);
    }

    @Override
    public int deleteOrderDetailById(int ODid) {
        return orderDetailMapper.deleteById(ODid);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateByIdSelective(orderDetail);
    }

    @Override
    public int saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertSelective(orderDetail);
    }


    private OrderDetailCondition convertOrderDetailAttr2Condition(Integer offset, Integer pageSize, OrderDetail orderDetail) {
        OrderDetailCondition condition = new OrderDetailCondition();

        if (null != offset) {
            condition.setLimitOffset(offset);
        }

        if (null != pageSize) {
            condition.setLimitSize(pageSize);
        }

        if (null == orderDetail) {
            return condition;
        }

        if (null != orderDetail.getId()) {
            condition.createCriteria().andIdEqualTo(orderDetail.getId());
        }


        if (null != orderDetail.getGoodsId()) {
            condition.createCriteria().andGoodsIdEqualTo(orderDetail.getGoodsId());
        }

        if (null != orderDetail.getOrderId()) {
            condition.createCriteria().andOrderIdEqualTo(orderDetail.getOrderId());
        }

        if (null != orderDetail.getUid()) {
            condition.createCriteria().andUidEqualTo(orderDetail.getUid());
        }
        return condition;
    }
}

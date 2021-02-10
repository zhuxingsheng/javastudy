package com.jack.mapstruct.mapper.factory;

import com.jack.mapstruct.domain.Order;
import com.jack.mapstruct.entity.OrderEntity;

/**
 * @description: default order factory
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-28 16:25
 */
public class DefaultOrderFactory implements OrderFactory{

    @Override
    public Order createOrder(OrderEntity orderEntity) {
        return new Order();
    }
}

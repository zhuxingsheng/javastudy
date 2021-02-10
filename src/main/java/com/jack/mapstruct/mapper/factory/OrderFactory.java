package com.jack.mapstruct.mapper.factory;

import com.jack.mapstruct.domain.Order;
import com.jack.mapstruct.entity.OrderEntity;
import org.mapstruct.ObjectFactory;

/**
 * @description: order factory
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 14:51
 */
public interface OrderFactory {

    @ObjectFactory
    Order createOrder(OrderEntity orderEntity);
}

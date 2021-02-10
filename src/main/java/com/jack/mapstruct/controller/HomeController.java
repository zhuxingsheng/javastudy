package com.jack.mapstruct.controller;

import com.google.gson.Gson;
import com.jack.mapstruct.domain.Order;
import com.jack.mapstruct.entity.OrderEntity;
import com.jack.mapstruct.entity.OrderItemEntity;
import com.jack.mapstruct.entity.UserEntity;
import com.jack.mapstruct.mapper.OrderMapper;
import com.jack.mapstruct.mapper.OrderMapperInterceptor;
import com.jack.mapstruct.mapper.factory.DefaultOrderFactory;
import com.jack.mapstruct.model.OrderModel;
import com.jack.mapstruct.util.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description: home controller
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 10:08
 */
public class HomeController {

    private OrderMapper orderMapper = OrderMapper.INSTANCE;

    OrderMapperInterceptor orderMapperInterceptor = Mappers.getMapper( OrderMapperInterceptor.class );

    @BeforeEach
    public void init() {
        orderMapperInterceptor.setOrderFactory(new DefaultOrderFactory());
    }


    /**
     * entity 转 domain 再转model
     */
    @Test
    public void test() {

        UserEntity userEntity = DataHelper.getUserEntity();

        OrderEntity orderEntity = DataHelper.getOrderEntity();

        List<OrderItemEntity> orderItemEntities = DataHelper.getOrderItemEntities(orderEntity.getId());


        // entity转domain
        Order order = orderMapper.map(orderEntity, orderItemEntities, userEntity);

        OrderModel orderModel = orderMapper.mapToModel(order);

        Gson gson = new Gson();
        System.err.println(gson.toJson(orderModel));

    }
}

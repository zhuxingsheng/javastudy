package com.jack.mapstruct.mapper;

import com.jack.mapstruct.domain.Order;
import com.jack.mapstruct.entity.OrderEntity;
import com.jack.mapstruct.mapper.factory.OrderFactory;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ObjectFactory;

/**
 * @description: order mapper interceptor
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-28 16:06
 */
@Mapper
public abstract class OrderMapperInterceptor {

    protected final static String UPDATE_ORDER= "UPDATE_ORDER";
    protected final static String FUNC_UPDATE_ORDER= "FUNC_UPDATE_ORDER";

    //public static final OrderMapperInterceptor INSTANCE = Mappers.getMapper(OrderMapperInterceptor.class);

    @Setter
    private static OrderFactory orderFactory;

    @Named(UPDATE_ORDER)
    @ObjectFactory
    protected Order createOrder(OrderEntity entity)
    {
        return orderFactory.createOrder(entity);
    }
}

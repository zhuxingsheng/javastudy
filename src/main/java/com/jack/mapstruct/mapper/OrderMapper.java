package com.jack.mapstruct.mapper;

import com.jack.mapstruct.domain.Order;
import com.jack.mapstruct.entity.OrderEntity;
import com.jack.mapstruct.entity.OrderItemEntity;
import com.jack.mapstruct.entity.UserEntity;
import com.jack.mapstruct.model.OrderModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description: order mapper
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-22 14:55
 */
@Mapper(uses = {OrderMapperInterceptor.class},config = GlobalConfig.class)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @BaseFieldsMapping
    @Mapping(target = "user", source = "buyer")
    @Mapping(target = "items", ignore = true)
    Order map(OrderEntity main, UserEntity buyer);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order map(OrderEntity entity);


    @BeanMapping(qualifiedByName = {OrderMapperInterceptor.UPDATE_ORDER})
    @BaseFieldsMapping
    @Mapping(target = "user", source = "buyer")
    @Mapping(target = "items", source = "itemEntities")
    Order map(OrderEntity main, List<? extends OrderItemEntity> itemEntities, UserEntity buyer);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.fullName")
    @Mapping(target = "dateText", source = "date", dateFormat = "MM-dd-yyyy")
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "totalQuantity", ignore = true)
    OrderModel mapToModel(Order order);

}

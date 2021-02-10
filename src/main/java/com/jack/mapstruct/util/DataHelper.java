package com.jack.mapstruct.util;

import com.google.common.collect.Lists;
import com.jack.mapstruct.domain.OrderStatus;
import com.jack.mapstruct.domain.User;
import com.jack.mapstruct.entity.OrderEntity;
import com.jack.mapstruct.entity.OrderItemEntity;
import com.jack.mapstruct.entity.UserEntity;
import com.jack.mapstruct.medical.entity.MedicineOrderEntity;
import com.jack.mapstruct.medical.entity.MedicineOrderItemEntity;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: data helper
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-26 10:37
 */
@UtilityClass
public class DataHelper {


    public MedicineOrderEntity getMedicineOrderEntity() {
        return MedicineOrderEntity.builder().id(IdHelper.next()).userId(IdHelper.next())
                .number("001").date(LocalDate.now()).status(OrderStatus.WAIT_FOR_PAYMENT.toString())
                .doctor("Doctor Su").prescribedAt(LocalDate.now()).patient("Mr. X").treatment("take pills")
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now())
                .build();
    }

    public List<MedicineOrderItemEntity> getMedicineOrderItemEntities(Long orderId) {
        List<MedicineOrderItemEntity> itemEntities = Lists.newArrayList();
        itemEntities.add(MedicineOrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Red Pill").unitPrice(BigDecimal.valueOf(10)).quantity(10).prescribed(false)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        itemEntities.add(MedicineOrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Blue Pill").unitPrice(BigDecimal.valueOf(100)).quantity(2).prescribed(true)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        itemEntities.add(MedicineOrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Green Pill").unitPrice(BigDecimal.valueOf(50)).quantity(18).prescribed(false)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        return itemEntities;
    }


    public OrderEntity getOrderEntity() {
        return OrderEntity.builder().id(IdHelper.next()).userId(IdHelper.next())
                .number("001").date(LocalDate.now()).status(OrderStatus.FINISHED.toString())
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now())
                .build();
    }

    public List<OrderItemEntity> getOrderItemEntities(Long orderId) {
        List<OrderItemEntity> itemEntities = Lists.newArrayList();
        itemEntities.add(OrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Bread").unitPrice(BigDecimal.valueOf(10)).quantity(10)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        itemEntities.add(OrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Wine").unitPrice(BigDecimal.valueOf(100)).quantity(2)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        itemEntities.add(OrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Meat").unitPrice(BigDecimal.valueOf(50)).quantity(18)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        itemEntities.add(OrderItemEntity.builder().id(IdHelper.next()).orderId(orderId)
                .productName("Apple").unitPrice(BigDecimal.valueOf(5)).quantity(32)
                .createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build());
        return itemEntities;
    }


    public UserEntity getUserEntity() {
        return UserEntity.builder().id(IdHelper.next()).firstName("jack").lastName("chu").age(10)
                .language("zh").createdBy(User.SYSTEM_ID).createdAt(LocalDateTime.now()).build();
    }
}

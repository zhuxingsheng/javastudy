package com.jack.mapstruct.medical.mapper;

import com.jack.mapstruct.entity.UserEntity;
import com.jack.mapstruct.mapper.OrderMapper;
import com.jack.mapstruct.medical.domain.MedicineOrder;
import com.jack.mapstruct.medical.entity.MedicineOrderEntity;
import com.jack.mapstruct.medical.entity.MedicineOrderItemEntity;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description: mapper
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-15 22:03
 */
public abstract class MedicineOrderMapper {

    public static MedicineOrderMapper INSTANCE = Mappers.getMapper(MedicineOrderMapper.class);

    private OrderMapper orderMapper = OrderMapper.INSTANCE;

    public MedicineOrder map(@NonNull MedicineOrderEntity entity, List<MedicineOrderItemEntity> itemEntities, UserEntity buyer) {
        return (MedicineOrder) orderMapper.map(entity, itemEntities, buyer);
    }
}

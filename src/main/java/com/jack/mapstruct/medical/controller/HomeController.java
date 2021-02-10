package com.jack.mapstruct.medical.controller;

import com.jack.mapstruct.entity.UserEntity;
import com.jack.mapstruct.medical.domain.MedicineOrder;
import com.jack.mapstruct.medical.entity.MedicineOrderEntity;
import com.jack.mapstruct.medical.entity.MedicineOrderItemEntity;
import com.jack.mapstruct.medical.mapper.MedicineOrderMapper;
import com.jack.mapstruct.util.DataHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @description: medical home controller
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-08-15 20:54
 */
public class HomeController {

    /**
     * entity -> domain -> model
     */
    @Test
    public void test(){


    }

    private MedicineOrder getMdeicineOrder() {
        UserEntity userEntity = DataHelper.getUserEntity();
        MedicineOrderEntity medicineOrderEntity = DataHelper.getMedicineOrderEntity();
        List<MedicineOrderItemEntity> medicineOrderItemEntity = DataHelper.getMedicineOrderItemEntities(medicineOrderEntity.getId());
        return MedicineOrderMapper.INSTANCE.map(medicineOrderEntity,medicineOrderItemEntity,userEntity);
    }
}

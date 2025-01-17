/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.zy.medicineclass.service.mapstruct;


import cn.px.core.mapstruct.TreeWrapper;
import cn.px.zy.medicineclass.service.dto.MedicineClassDTO;
import cn.px.zy.medicineclass.domain.MedicineClass;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 *  MedicineClassWrapper
 * @author 品讯科技
 * @version 2022-11-15
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {} )
public interface MedicineClassWrapper extends TreeWrapper<MedicineClassDTO, MedicineClass> {

    MedicineClassWrapper INSTANCE = Mappers.getMapper(MedicineClassWrapper.class);
}


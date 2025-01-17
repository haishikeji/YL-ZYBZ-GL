/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.prescription_management.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.px.bz.prescription_management.service.dto.BzPrescriptionMedicineDTO;
import cn.px.bz.prescription_management.domain.BzPrescriptionMedicine;
import cn.px.bz.prescription_management.mapper.BzPrescriptionMedicineMapper;

/**
 * 药方管理-中药Service
 * @author 品讯科技
 * @version 2022-11-18
 */
@Service
@Transactional
public class BzPrescriptionMedicineService extends ServiceImpl<BzPrescriptionMedicineMapper, BzPrescriptionMedicine> {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public BzPrescriptionMedicineDTO findById(String id) {
		return baseMapper.findById ( id );
	}

	/**
	 * 查询列表
	 * @param prescriptionId
	 * @return
	 */
	public List <BzPrescriptionMedicineDTO> findList(String prescriptionId) {
		return  baseMapper.findList (prescriptionId);
	}

}

/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.prescription_management.service;

import cn.px.sys.constant.CommonConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.px.bz.prescription_management.service.dto.BzPrescriptionManageDTO;
import cn.px.bz.prescription_management.service.dto.BzPrescriptionMedicineDTO;
import cn.px.bz.prescription_management.service.mapstruct.BzPrescriptionManageWrapper;
import cn.px.bz.prescription_management.service.mapstruct.BzPrescriptionMedicineWrapper;
import cn.px.bz.prescription_management.domain.BzPrescriptionManage;
import cn.px.bz.prescription_management.domain.BzPrescriptionMedicine;
import cn.px.bz.prescription_management.mapper.BzPrescriptionManageMapper;

/**
 * 药方管理Service
 * @author 品讯科技
 * @version 2022-11-18
 */
@Service
@Transactional
public class BzPrescriptionManageService extends ServiceImpl<BzPrescriptionManageMapper, BzPrescriptionManage> {
	/**
	* 子表service
	*/
	@Autowired
	private BzPrescriptionMedicineService bzPrescriptionMedicineService;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public BzPrescriptionManageDTO findById(String id) {
		BzPrescriptionManageDTO bzPrescriptionManageDTO = BzPrescriptionManageWrapper.INSTANCE.toDTO ( super.getById ( id ) );
		bzPrescriptionManageDTO.setBzPrescriptionMedicineDTOList(bzPrescriptionMedicineService.findList(id));
		return bzPrescriptionManageDTO;
	}

	/**
	 * 保存或者更新
	 * @param  bzPrescriptionManageDTO
	 * @return
	 */
	public void saveOrUpdate(BzPrescriptionManageDTO bzPrescriptionManageDTO) {
		BzPrescriptionManage bzPrescriptionManage =  BzPrescriptionManageWrapper.INSTANCE.toEntity ( bzPrescriptionManageDTO );
		super.saveOrUpdate (bzPrescriptionManage);
		for (BzPrescriptionMedicineDTO bzPrescriptionMedicineDTO : bzPrescriptionManageDTO.getBzPrescriptionMedicineDTOList ()){
			if ( CommonConstants.DELETED.equals ( bzPrescriptionMedicineDTO.getDelFlag()) ){
				bzPrescriptionMedicineService.removeById ( bzPrescriptionMedicineDTO.getId () );
			}else{
				BzPrescriptionMedicine bzPrescriptionMedicine = BzPrescriptionMedicineWrapper.INSTANCE.toEntity ( bzPrescriptionMedicineDTO );
				bzPrescriptionMedicine.setPrescriptionId ( bzPrescriptionManage.getId () );
				bzPrescriptionMedicineService.saveOrUpdate ( bzPrescriptionMedicine );
			}
		}
	}

	/**
	 * 删除
	 * @param  id
	 * @return
	 */
	public void removeById(String id) {
		super.removeById ( id );
		bzPrescriptionMedicineService.lambdaUpdate ().eq ( BzPrescriptionMedicine::getPrescriptionId, id ).remove ();
	}

	/**
	 * 根据名称去数据库比对，有则返回id，没有则新增并返回id
	 * @param name
	 * @return
	 */
	public String findByName(String name) {
		return baseMapper.findByName (name);
	}

}

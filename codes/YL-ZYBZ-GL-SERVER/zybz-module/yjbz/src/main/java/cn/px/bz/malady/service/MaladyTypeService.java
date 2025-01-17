/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.malady.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import cn.px.core.service.TreeDTOService;
import cn.px.bz.malady.service.dto.MaladyTypeDTO;
import cn.px.bz.malady.domain.MaladyType;
import cn.px.bz.malady.mapper.MaladyTypeMapper;

/**
 * 病症分类Service
 * @author 品讯科技
 * @version 2022-11-10
 */
@Service
@Transactional
public class MaladyTypeService extends TreeDTOService<MaladyTypeMapper, MaladyType, MaladyTypeDTO> {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public MaladyTypeDTO findById(String id) {
		return baseMapper.findById ( id );
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	public List <MaladyTypeDTO> listDTO() {
		return  baseMapper.findList ();
	}

	public boolean saveOrUpdate(MaladyType maladyType) {
		return super.saveOrUpdate (maladyType);
	}

	public boolean removeWithChildrenById(String id) {
		return super.removeWithChildrenById (id);
	}

}

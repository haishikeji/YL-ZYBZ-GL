/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.disease_management.controller;

import javax.validation.Valid;

import cn.px.core.query.QueryWrapperGenerator;
import cn.px.aop.logging.annotation.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.px.bz.disease_management.service.dto.BzDiseaseManageDTO;
import cn.px.bz.disease_management.service.BzDiseaseManageService;

import java.util.List;

/**
 * 病症管理Controller
 * @author 品讯科技
 * @version 2022-11-21
 */
@Api(tags ="病症管理")
@RestController
@RequestMapping(value = "/bz/disease_management/bzDiseaseManage")
public class BzDiseaseManageController {

	@Autowired
	private BzDiseaseManageService bzDiseaseManageService;

	/**
	 * 病症管理列表数据
	 */
	@ApiLog("查询病症管理列表数据")
	@ApiOperation(value = "查询病症管理列表数据")
	//@PreAuthorize("hasAuthority('bz:disease_management:bzDiseaseManage:list')")
	@GetMapping("list")
	public ResponseEntity<IPage<BzDiseaseManageDTO>> list(BzDiseaseManageDTO bzDiseaseManageDTO, Page<BzDiseaseManageDTO> page) throws Exception {
		QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition (bzDiseaseManageDTO, BzDiseaseManageDTO.class);
		IPage<BzDiseaseManageDTO> result = bzDiseaseManageService.findPage (page, queryWrapper);
		return ResponseEntity.ok (result);
	}


	/**
	 * 根据Id获取病症管理数据
	 */
	@ApiLog("根据Id获取病症管理数据")
	@ApiOperation(value = "根据Id获取病症管理数据")
	//@PreAuthorize("hasAnyAuthority('bz:disease_management:bzDiseaseManage:view','bz:disease_management:bzDiseaseManage:add','bz:disease_management:bzDiseaseManage:edit')")
	@GetMapping("queryById")
	public ResponseEntity<BzDiseaseManageDTO> queryById(String id) {
		return ResponseEntity.ok ( bzDiseaseManageService.findById ( id ) );
	}
	/**
	 * 根据Id获取施治信息
	 */
	@ApiLog("根据Id获取施治信息")
	@ApiOperation(value = "根据Id获取施治信息")
	//@PreAuthorize("hasAnyAuthority('bz:management:bzDiseaseManage:queryBySymptomId')")
	@GetMapping("queryBySymptomId")
	public ResponseEntity<List<BzDiseaseManageDTO>> queryBySymptomId(String Typeid,String symptom) {
		List<BzDiseaseManageDTO> result = bzDiseaseManageService.findBySymptomId ( Typeid,symptom );
		return ResponseEntity.ok (result);
	}
	/**
	 * 保存病症管理
	 */
	@ApiLog("保存病症管理")
	@ApiOperation(value = "保存病症管理")
	@PreAuthorize("hasAnyAuthority('bz:disease_management:bzDiseaseManage:add','bz:disease_management:bzDiseaseManage:edit')")
	@PostMapping("save")
	public  ResponseEntity <String> save(@Valid @RequestBody BzDiseaseManageDTO bzDiseaseManageDTO) {
		//新增或编辑表单保存
		bzDiseaseManageService.saveOrUpdate (bzDiseaseManageDTO);
        return ResponseEntity.ok ( "保存病症管理成功" );
	}


	/**
	 * 删除病症管理
	 */
	@ApiLog("删除病症管理")
	@ApiOperation(value = "删除病症管理")
	@PreAuthorize("hasAuthority('bz:disease_management:bzDiseaseManage:del')")
	@DeleteMapping("delete")
	public ResponseEntity <String> delete(String ids) {
		String idArray[] = ids.split(",");
		for(String id: idArray){
			bzDiseaseManageService.removeById ( id );
		}
		return ResponseEntity.ok( "删除病症管理成功" );
	}


}

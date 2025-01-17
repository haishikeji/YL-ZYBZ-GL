/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.zy.chinesemedicine.controller;

import javax.validation.Valid;
import com.google.common.collect.Lists;
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
import cn.px.zy.chinesemedicine.service.dto.ChineseMedicineDTO;
import cn.px.zy.chinesemedicine.service.mapstruct.ChineseMedicineWrapper;
import cn.px.zy.chinesemedicine.service.ChineseMedicineService;

/**
 * 药材管理Controller
 * @author 品讯科技
 * @version 2022-11-28
 */

@Api(tags ="药材管理")
@RestController
@RequestMapping(value = "/zy/chinesemedicine/chineseMedicine")
public class ChineseMedicineController {

	@Autowired
	private ChineseMedicineService chineseMedicineService;

	@Autowired
	private ChineseMedicineWrapper chineseMedicineWrapper;

	/**
	 * 药材管理列表数据
	 */
	@ApiLog("查询药材管理列表数据")
	@ApiOperation(value = "查询药材管理列表数据")
	@PreAuthorize("hasAuthority('zy:chinesemedicine:chineseMedicine:list')")
	@GetMapping("list")
	public ResponseEntity<IPage<ChineseMedicineDTO>> list(ChineseMedicineDTO chineseMedicineDTO, Page<ChineseMedicineDTO> page) throws Exception {
		QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition (chineseMedicineDTO, ChineseMedicineDTO.class);
		IPage<ChineseMedicineDTO> result = chineseMedicineService.findPage (page, queryWrapper);
		return ResponseEntity.ok (result);
	}


	/**
	 * 根据Id获取药材管理数据
	 */
	@ApiLog("根据Id获取药材管理数据")
	@ApiOperation(value = "根据Id获取药材管理数据")
	@PreAuthorize("hasAnyAuthority('zy:chinesemedicine:chineseMedicine:view','zy:chinesemedicine:chineseMedicine:add','zy:chinesemedicine:chineseMedicine:edit')")
	@GetMapping("queryById")
	public ResponseEntity<ChineseMedicineDTO> queryById(String id) {
		return ResponseEntity.ok ( chineseMedicineService.findById ( id ) );
	}

	/**
	 * 保存药材管理
	 */
	@ApiLog("保存药材管理")
	@ApiOperation(value = "保存药材管理")
	@PreAuthorize("hasAnyAuthority('zy:chinesemedicine:chineseMedicine:add','zy:chinesemedicine:chineseMedicine:edit')")
	@PostMapping("save")
	public  ResponseEntity <String> save(@Valid @RequestBody ChineseMedicineDTO chineseMedicineDTO) {
		//新增或编辑表单保存
		chineseMedicineService.saveOrUpdate (chineseMedicineWrapper.toEntity (chineseMedicineDTO));
        return ResponseEntity.ok ( "保存药材管理成功" );
	}


	/**
	 * 删除药材管理
	 */
	@ApiLog("删除药材管理")
	@ApiOperation(value = "删除药材管理")
	@PreAuthorize("hasAuthority('zy:chinesemedicine:chineseMedicine:del')")
	@DeleteMapping("delete")
	public ResponseEntity <String> delete(String ids) {
		String idArray[] = ids.split(",");
        chineseMedicineService.removeByIds ( Lists.newArrayList ( idArray ) );
		return ResponseEntity.ok( "删除药材管理成功" );
	}

}

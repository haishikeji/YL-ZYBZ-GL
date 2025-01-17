/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.malady.service.dto;

import cn.px.bz.outpatient.service.dto.OutpatientDTO;
import javax.validation.constraints.NotNull;
import cn.px.core.service.dto.TreeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 病症分类DTO
 * @author 品讯科技
 * @version 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MaladyTypeDTO extends TreeDTO<MaladyTypeDTO> {

	private static final long serialVersionUID = 1L;

	/**
     * 门诊id
     */
	@NotNull(message="门诊id不能为空")
	private OutpatientDTO mz;
	/**
     * 备注
     */
	private String more;


}

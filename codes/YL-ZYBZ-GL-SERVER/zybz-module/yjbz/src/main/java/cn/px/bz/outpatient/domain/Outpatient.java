/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.outpatient.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 门诊分类Entity
 * @author 品讯科技
 * @version 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bz_outpatient_department")
public class Outpatient extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 分类
     */
	private String type;

}

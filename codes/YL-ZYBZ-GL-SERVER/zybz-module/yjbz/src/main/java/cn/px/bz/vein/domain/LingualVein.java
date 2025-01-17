/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.vein.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 舌脉管理Entity
 * @author 品讯科技
 * @version 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bz_Lingual_vein")
public class LingualVein extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 舌脉
     */
	private String name;

}

/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.zy.meridians.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 经脉管理Entity
 * @author 品讯科技
 * @version 2022-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zy_meridians")
public class Meridians extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 经脉
     */
	private String meridians;

}

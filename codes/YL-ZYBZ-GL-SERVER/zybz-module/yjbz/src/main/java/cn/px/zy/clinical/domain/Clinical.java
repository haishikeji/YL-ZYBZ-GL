/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.zy.clinical.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 临床应用Entity
 * @author 品讯科技
 * @version 2022-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zy_clinical")
public class Clinical extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 临床应用
     */
	private String clinical;

}

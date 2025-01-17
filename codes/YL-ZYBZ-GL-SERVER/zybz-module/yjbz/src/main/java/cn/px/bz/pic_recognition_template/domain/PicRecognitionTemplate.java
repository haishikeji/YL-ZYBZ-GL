/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.pic_recognition_template.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 图片识别模板管理Entity
 * @author 品讯科技
 * @version 2022-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pic_recognition_template")
public class PicRecognitionTemplate extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 模板名称
     */
	private String name;
	/**
     * 模板key
     */
	private String keyVal;

}

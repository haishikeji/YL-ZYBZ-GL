/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.pic_recognition_template.service.mapstruct;


import cn.px.core.mapstruct.EntityWrapper;
import cn.px.bz.pic_recognition_template.service.dto.PicRecognitionTemplateDTO;
import cn.px.bz.pic_recognition_template.domain.PicRecognitionTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 *  PicRecognitionTemplateWrapper
 * @author 品讯科技
 * @version 2022-11-29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {} )
public interface PicRecognitionTemplateWrapper extends EntityWrapper<PicRecognitionTemplateDTO, PicRecognitionTemplate> {

    PicRecognitionTemplateWrapper INSTANCE = Mappers.getMapper(PicRecognitionTemplateWrapper.class);
}


/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.disease_management.mapper;

;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
;
import cn.px.bz.disease_management.service.dto.BzDiseaseSymptomDTO;
import cn.px.bz.disease_management.domain.BzDiseaseSymptom;

/**
 * 病症管理_症状MAPPER接口
 * @author 品讯科技
 * @version 2022-11-21
 */
public interface BzDiseaseSymptomMapper extends BaseMapper<BzDiseaseSymptom> {

    /**
     * 根据id获取病症管理_症状
     * @param id
     * @return
     */
    BzDiseaseSymptomDTO findById(String id);

    /**
     * 获取病症管理_症状列表
     *
     * @param BzDiseaseManageId
     * @return
     */
    List <BzDiseaseSymptomDTO> findList(String BzDiseaseManageId);

}

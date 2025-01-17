/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.px.bz.outpatient.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.px.bz.outpatient.domain.Outpatient;
import cn.px.bz.outpatient.mapper.OutpatientMapper;

/**
 * 门诊分类Service
 * @author 品讯科技
 * @version 2022-11-10
 */
@Service
@Transactional
public class OutpatientService extends ServiceImpl<OutpatientMapper, Outpatient> {

}

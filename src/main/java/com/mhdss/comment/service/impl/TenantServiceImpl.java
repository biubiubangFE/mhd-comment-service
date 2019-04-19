package com.mhdss.comment.service.impl;

import com.mhdss.comment.constant.BaseStatus;
import com.mhdss.comment.constant.TenantStatus;
import com.mhdss.comment.dao.TenantDAO;
import com.mhdss.comment.dataobject.TenantDO;
import com.mhdss.comment.dto.TenantDTO;
import com.mhdss.comment.query.TenantQuery;
import com.mhdss.comment.service.TenantService;
import com.mhdss.comment.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantDAO tenantDAO;

    @Override
    public List<TenantDTO> queryTenantList() {

        TenantQuery tenantQuery = new TenantQuery();
        List<TenantDO> tenantDOList = tenantDAO.selectList(tenantQuery);
        List<TenantDTO> tenantDTOS = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(tenantDOList)) {

            for (TenantDO tenantDO : tenantDOList) {
                TenantDTO dto = new TenantDTO();
                dto.setCode(tenantDO.getCode());
                dto.setCheckUrl(tenantDO.getCheckUrl());
                dto.setEndTime(tenantDO.getEndTime());
                Long nowTime = DateUtil.getCurrentTimeStamp();

                if (nowTime > tenantDO.getEndTime()) {
                    dto.setTenantStatus(TenantStatus.EXPIRE);
                }

                if (!BaseStatus.NORMAL.getStatus().equals(tenantDO.getStatus())) {
                    dto.setTenantStatus(TenantStatus.ERROR);
                }
                tenantDTOS.add(dto);
            }
        }
        return tenantDTOS;
    }
}

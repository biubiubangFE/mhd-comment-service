package com.mhdss.comment.service;

import com.mhdss.comment.dto.TenantDTO;

import java.util.List;

public interface TenantService {

    List<TenantDTO> queryTenantList();
}

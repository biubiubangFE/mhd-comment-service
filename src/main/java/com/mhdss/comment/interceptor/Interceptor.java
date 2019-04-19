package com.mhdss.comment.interceptor;

import com.mhdss.comment.constant.TenantStatus;
import com.mhdss.comment.dto.AuthAgent;
import com.mhdss.comment.dto.TenantDTO;
import com.mhdss.comment.dto.TenantUserDTO;
import com.mhdss.comment.exception.HeaderMissException;
import com.mhdss.comment.exception.NotLoginException;
import com.mhdss.comment.exception.TenantException;
import com.mhdss.comment.service.TenantService;
import com.mhdss.comment.utils.ResponseData;
import com.mhdss.comment.utils.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "dc.auth")
public class Interceptor implements InitializingBean, HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    private static final ParameterizedTypeReference<ResponseData<TenantUserDTO>> TENANT_USER_TYPE =
            new ParameterizedTypeReference<ResponseData<TenantUserDTO>>() {
            };

    private static final String SESSION_KEY_NAME = "sessionKey";
    private static final String TENANT_CODE_NAME = "mhdssTenantCode";

    @Autowired
    private TenantService tenantService;
    @Autowired
    private AuthAgent authAgent;
    @Autowired
    private RestTemplate restTemplate;

    private String[] includes;

    private static Map<String, TenantDTO> tenantDTOMap;

    @Override
    public void afterPropertiesSet() {
        List<TenantDTO> tenantDTOs = tenantService.queryTenantList();

        tenantDTOMap = tenantDTOs.stream().collect(Collectors.toMap(TenantDTO::getCode, e -> e));

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle==================== {}");

        //获取用户sessionkey,tenantCode

        String sessionKey = request.getHeader(SESSION_KEY_NAME);
        String tenantCode = request.getHeader(TENANT_CODE_NAME);
        logger.error("sessionkey={},tenantKey={}", sessionKey, tenantCode);

        if (StringUtils.isAnyBlank(tenantCode, sessionKey)) {
            throw new HeaderMissException(sessionKey, tenantCode);
        }

        TenantDTO tenantDTO = tenantDTOMap.get(tenantCode);

        if (null == tenantDTO) {
            logger.error("tenant 过期或者 异常");
            throw new TenantException("不存在对应租户", null);
        } else if (!tenantDTO.getTenantStatus().equals(TenantStatus.NORMAL)) {
            TenantStatus tenantStatus = tenantDTO.getTenantStatus();
            if (tenantStatus.equals(TenantStatus.EXPIRE)) {
                throw new TenantException("租户过期，请联系管理员", TenantStatus.EXPIRE.getStatus());
            } else {
                throw new TenantException("租户异常，请联系管理员", TenantStatus.ERROR.getStatus());
            }
        }

        String checkUrl = tenantDTO.getCheckUrl();
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("sessionKey", sessionKey);

        ResponseEntity<ResponseData<TenantUserDTO>> entity = restTemplate.exchange(checkUrl, HttpMethod.GET, RestTemplateUtil.buildJsonRequestEntity(requestMap), TENANT_USER_TYPE);

        if (null != entity) {
            ResponseData<TenantUserDTO> responseData = entity.getBody();
            if (null != responseData && responseData.getErrcode() == 0) {
                TenantUserDTO data = responseData.getData();
                if (null != data) {
                    authAgent.setTenantId(tenantDTO.getId());
                    authAgent.setIp(getLocalIp(request));
                    authAgent.setNickName(data.getNickName());
                    authAgent.setNickName(data.getAvatarUrl());
                    authAgent.setUserId(data.getUserId());
                    return true;
                }

            }
        }
        throw new NotLoginException("", "");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }


    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

}

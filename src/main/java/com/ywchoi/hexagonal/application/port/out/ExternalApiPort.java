package com.ywchoi.hexagonal.application.port.out;

import com.ywchoi.hexagonal.domain.model.ExternalData;

/**
 * 외부 API와 통신하기 위한 포트 인터페이스
 */
public interface ExternalApiPort {
    
    /**
     * 외부 API에서 데이터를 가져옴
     * 
     * @param resourceId 요청할 리소스 ID
     * @return 외부 API에서 가져온 데이터
     */
    ExternalData fetchExternalData(String resourceId);
}
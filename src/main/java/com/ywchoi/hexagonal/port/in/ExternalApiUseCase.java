package com.ywchoi.hexagonal.port.in;

import com.ywchoi.hexagonal.domain.model.ExternalData;

/**
 * 외부 API 데이터를 가져오고 처리하는 유스케이스 인터페이스
 */
public interface ExternalApiUseCase {
    
    /**
     * 외부 API에서 데이터를 가져와 가공하여 반환
     * 
     * @param resourceId 요청할 리소스 ID
     * @return 가공된 외부 데이터
     */
    ExternalData getProcessedExternalData(String resourceId);
}
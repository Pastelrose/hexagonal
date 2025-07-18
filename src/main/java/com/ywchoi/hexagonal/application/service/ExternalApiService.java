package com.ywchoi.hexagonal.application.service;

import com.ywchoi.hexagonal.domain.ExternalData;
import com.ywchoi.hexagonal.application.port.in.ExternalApiUseCase;
import com.ywchoi.hexagonal.application.port.out.ExternalApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 외부 API 유스케이스 구현체
 */
@Service
@RequiredArgsConstructor
public class ExternalApiService implements ExternalApiUseCase {

    private final ExternalApiPort externalApiPort;
    
    @Override
    public ExternalData getProcessedExternalData(String resourceId) {
        // 외부 API에서 데이터 가져오기
        ExternalData rawData = externalApiPort.fetchExternalData(resourceId);
        
        // 데이터 가공 처리
        return rawData.processData();
    }
}
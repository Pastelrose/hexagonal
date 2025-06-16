package com.ywchoi.hexagonal.adapter.out;

import com.ywchoi.hexagonal.domain.model.ExternalData;
import com.ywchoi.hexagonal.port.out.ExternalApiPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 외부 API와 통신하는 어댑터 구현체
 */
@Component
public class ExternalApiAdapter implements ExternalApiPort {

    private final RestTemplate restTemplate;
    private final String externalApiUrl = "https://api.example.com/data/";
    
    public ExternalApiAdapter() {
        this.restTemplate = new RestTemplate();
    }
    
    @Override
    public ExternalData fetchExternalData(String resourceId) {
        // 실제 구현에서는 RestTemplate을 사용하여 외부 API 호출
        // 여기서는 간단한 예시로 가상의 데이터를 반환
        
        // 실제 구현 예시 (주석 처리):
        // ExternalApiResponseDto response = restTemplate.getForObject(
        //     externalApiUrl + resourceId, ExternalApiResponseDto.class);
        // return mapToExternalData(response);
        
        // 테스트용 가상 데이터 반환
        return new ExternalData(
            resourceId,
            "외부 API 제목 " + resourceId,
            "외부 API에서 가져온 콘텐츠입니다. ID: " + resourceId,
            "external-api"
        );
    }
}
package com.ywchoi.hexagonal.adapter.in.web;

import com.ywchoi.hexagonal.adapter.in.web.dto.ExternalApiResponse;
import com.ywchoi.hexagonal.domain.model.ExternalData;
import com.ywchoi.hexagonal.port.in.ExternalApiUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 외부 API 데이터를 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor
public class ExternalApiController {

    private final ExternalApiUseCase externalApiUseCase;
    
    /**
     * 외부 API에서 데이터를 가져와 가공하여 반환하는 엔드포인트
     * 
     * @param resourceId 요청할 리소스 ID
     * @return 가공된 외부 데이터
     */
    @GetMapping("/{resourceId}")
    public ResponseEntity<ExternalApiResponse> getExternalData(@PathVariable String resourceId) {
        ExternalData processedData = externalApiUseCase.getProcessedExternalData(resourceId);
        return ResponseEntity.ok(new ExternalApiResponse(processedData));
    }
    
    /**
     * 외부 API에 데이터를 요청하는 POST 엔드포인트
     * 
     * @param resourceId 요청할 리소스 ID
     * @return 가공된 외부 데이터
     */
    @PostMapping("/request/{resourceId}")
    public ResponseEntity<ExternalApiResponse> requestExternalData(@PathVariable String resourceId) {
        ExternalData processedData = externalApiUseCase.getProcessedExternalData(resourceId);
        return ResponseEntity.ok(new ExternalApiResponse(processedData));
    }
}
package com.ywchoi.hexagonal.adapter.in.web.dto;

import com.ywchoi.hexagonal.domain.model.ExternalData;
import lombok.Getter;

/**
 * 외부 API 응답을 위한 DTO 클래스
 */
@Getter
public class ExternalApiResponse {
    private final String id;
    private final String title;
    private final String content;
    private final String source;
    private final String processedAt;
    
    public ExternalApiResponse(ExternalData data) {
        this.id = data.getId();
        this.title = data.getTitle();
        this.content = data.getContent();
        this.source = data.getSource();
        this.processedAt = java.time.LocalDateTime.now().toString();
    }
}
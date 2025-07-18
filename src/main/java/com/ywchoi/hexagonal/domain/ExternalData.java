package com.ywchoi.hexagonal.domain;

import lombok.Getter;

/**
 * 외부 API에서 가져온 데이터를 표현하는 도메인 모델
 */
@Getter
public class ExternalData {
    private final String id;
    private final String title;
    private final String content;
    private final String source;
    
    public ExternalData(String id, String title, String content, String source) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.source = source;
    }
    
    /**
     * 외부 데이터를 가공하여 새로운 ExternalData 객체를 생성
     */
    public ExternalData processData() {
        String processedTitle = "[가공됨] " + this.title;
        String processedContent = this.content + " (가공 완료)";
        return new ExternalData(this.id, processedTitle, processedContent, this.source);
    }
}
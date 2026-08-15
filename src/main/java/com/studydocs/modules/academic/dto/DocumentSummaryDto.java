package com.studydocs.modules.academic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentSummaryDto {
    private String id;
    private String title;
    private String thumbnail;
    private String category;
    private String school;
    private Integer pageCount;
    private String year;
    private Integer likeCount;
    private Integer commentCount;
    @Builder.Default
    private Boolean isLiked = false;
    @Builder.Default
    private Boolean isBookmarked = false;
}

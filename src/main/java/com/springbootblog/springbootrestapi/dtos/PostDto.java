package com.springbootblog.springbootrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Set;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @Nullable
    private Long id;
    private String tittle;
    private String description;
    private String content;
    @Nullable
    private Set<CommentDto> comments;
}

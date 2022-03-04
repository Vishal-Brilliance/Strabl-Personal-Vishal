package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.TagsDto;
import com.strabl.sdk.domain.entity.Tags;

public class TagsMapper {


    public TagsMapper() {
    }

    public static TagsDto toTagsDto(Tags tags){
        return TagsDto.builder()
                .id(tags.getId() != null ? tags.getId() : 0)
                .name(tags.getName())
                .build();
    }
}

package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.UniversityCreateRequest;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.entity.University;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    University toEntity(UniversityCreateRequest request);

    UniversityResponse toResponse(University entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UniversityCreateRequest request, @MappingTarget University entity);
}

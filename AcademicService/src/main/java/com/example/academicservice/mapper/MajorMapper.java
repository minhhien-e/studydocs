package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.MajorCreateRequest;
import com.example.academicservice.dto.request.MajorUpdateRequest;
import com.example.academicservice.dto.response.MajorResponse;
import com.example.academicservice.entity.Major;
import org.mapstruct.*;

/**
 * MapStruct mapper để convert giữa Major entity và DTOs
 */
@Mapper(componentModel = "spring")
public interface MajorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Major toEntity(MajorCreateRequest request);

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    MajorResponse toResponse(Major entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(MajorUpdateRequest request, @MappingTarget Major entity);
}


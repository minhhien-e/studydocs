package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.SubjectCreateRequest;
import com.example.academicservice.dto.request.SubjectUpdateRequest;
import com.example.academicservice.dto.response.SubjectResponse;
import com.example.academicservice.entity.Subject;
import org.mapstruct.*;

/**
 * MapStruct mapper để convert giữa Subject entity và DTOs
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Subject toEntity(SubjectCreateRequest request);

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    SubjectResponse toResponse(Subject entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(SubjectUpdateRequest request, @MappingTarget Subject entity);
}

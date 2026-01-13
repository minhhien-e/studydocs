package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.DepartmentCreateRequest;
import com.example.academicservice.dto.request.DepartmentUpdateRequest;
import com.example.academicservice.dto.response.DepartmentResponse;
import com.example.academicservice.entity.Department;
import org.mapstruct.*;

/**
 * MapStruct mapper để convert giữa Department entity và DTOs
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "faculty", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Department toEntity(DepartmentCreateRequest request);

    @Mapping(target = "facultyId", source = "faculty.id")
    @Mapping(target = "facultyName", source = "faculty.name")
    DepartmentResponse toResponse(Department entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(DepartmentUpdateRequest request, @MappingTarget Department entity);
}


package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.FacultyCreateRequest;
import com.example.academicservice.dto.request.FacultyUpdateRequest;
import com.example.academicservice.dto.response.FacultyResponse;
import com.example.academicservice.entity.Faculty;
import org.mapstruct.*;

/**
 * MapStruct mapper để convert giữa Faculty entity và DTOs
 */
@Mapper(componentModel = "spring")
public interface FacultyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "university", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Faculty toEntity(FacultyCreateRequest request);

    @Mapping(target = "universityId", source = "university.id")
    @Mapping(target = "universityName", source = "university.name")
    FacultyResponse toResponse(Faculty entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(FacultyUpdateRequest request, @MappingTarget Faculty entity);
}


package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.DepartmentCreateRequest;
import com.example.academicservice.dto.request.DepartmentUpdateRequest;
import com.example.academicservice.dto.response.DepartmentResponse;
import com.example.academicservice.entity.Department;
import com.example.academicservice.entity.Faculty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T21:09:28+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toEntity(DepartmentCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Department department = new Department();

        department.setCode( request.getCode() );
        department.setDescription( request.getDescription() );
        department.setName( request.getName() );

        return department;
    }

    @Override
    public DepartmentResponse toResponse(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentResponse departmentResponse = new DepartmentResponse();

        departmentResponse.setFacultyId( entityFacultyId( entity ) );
        departmentResponse.setFacultyName( entityFacultyName( entity ) );
        departmentResponse.setCode( entity.getCode() );
        departmentResponse.setDescription( entity.getDescription() );
        departmentResponse.setId( entity.getId() );
        departmentResponse.setIsActive( entity.getIsActive() );
        departmentResponse.setName( entity.getName() );
        departmentResponse.setSlug( entity.getSlug() );

        return departmentResponse;
    }

    @Override
    public void updateEntityFromRequest(DepartmentUpdateRequest request, Department entity) {
        if ( request == null ) {
            return;
        }

        if ( request.getCode() != null ) {
            entity.setCode( request.getCode() );
        }
        if ( request.getDescription() != null ) {
            entity.setDescription( request.getDescription() );
        }
        if ( request.getIsActive() != null ) {
            entity.setIsActive( request.getIsActive() );
        }
        if ( request.getName() != null ) {
            entity.setName( request.getName() );
        }
    }

    private Long entityFacultyId(Department department) {
        if ( department == null ) {
            return null;
        }
        Faculty faculty = department.getFaculty();
        if ( faculty == null ) {
            return null;
        }
        Long id = faculty.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityFacultyName(Department department) {
        if ( department == null ) {
            return null;
        }
        Faculty faculty = department.getFaculty();
        if ( faculty == null ) {
            return null;
        }
        String name = faculty.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

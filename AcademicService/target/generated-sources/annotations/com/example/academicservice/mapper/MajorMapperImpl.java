package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.MajorCreateRequest;
import com.example.academicservice.dto.request.MajorUpdateRequest;
import com.example.academicservice.dto.response.MajorResponse;
import com.example.academicservice.entity.Department;
import com.example.academicservice.entity.Major;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T21:09:24+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class MajorMapperImpl implements MajorMapper {

    @Override
    public Major toEntity(MajorCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Major major = new Major();

        major.setCode( request.getCode() );
        major.setDescription( request.getDescription() );
        major.setName( request.getName() );

        return major;
    }

    @Override
    public MajorResponse toResponse(Major entity) {
        if ( entity == null ) {
            return null;
        }

        MajorResponse majorResponse = new MajorResponse();

        majorResponse.setDepartmentId( entityDepartmentId( entity ) );
        majorResponse.setDepartmentName( entityDepartmentName( entity ) );
        majorResponse.setCode( entity.getCode() );
        majorResponse.setDescription( entity.getDescription() );
        majorResponse.setId( entity.getId() );
        majorResponse.setIsActive( entity.getIsActive() );
        majorResponse.setName( entity.getName() );
        majorResponse.setSlug( entity.getSlug() );

        return majorResponse;
    }

    @Override
    public void updateEntityFromRequest(MajorUpdateRequest request, Major entity) {
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

    private Long entityDepartmentId(Major major) {
        if ( major == null ) {
            return null;
        }
        Department department = major.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityDepartmentName(Major major) {
        if ( major == null ) {
            return null;
        }
        Department department = major.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

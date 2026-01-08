package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.UniversityCreateRequest;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.entity.University;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T21:09:23+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UniversityMapperImpl implements UniversityMapper {

    @Override
    public University toEntity(UniversityCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        University university = new University();

        university.setAddress( request.getAddress() );
        university.setCode( request.getCode() );
        university.setDescription( request.getDescription() );
        university.setEmail( request.getEmail() );
        university.setName( request.getName() );
        university.setPhone( request.getPhone() );

        return university;
    }

    @Override
    public UniversityResponse toResponse(University entity) {
        if ( entity == null ) {
            return null;
        }

        UniversityResponse universityResponse = new UniversityResponse();

        universityResponse.setAddress( entity.getAddress() );
        universityResponse.setCode( entity.getCode() );
        universityResponse.setId( entity.getId() );
        universityResponse.setIsActive( entity.getIsActive() );
        universityResponse.setName( entity.getName() );
        universityResponse.setSlug( entity.getSlug() );

        return universityResponse;
    }

    @Override
    public void updateEntityFromRequest(UniversityCreateRequest request, University entity) {
        if ( request == null ) {
            return;
        }

        if ( request.getAddress() != null ) {
            entity.setAddress( request.getAddress() );
        }
        if ( request.getCode() != null ) {
            entity.setCode( request.getCode() );
        }
        if ( request.getDescription() != null ) {
            entity.setDescription( request.getDescription() );
        }
        if ( request.getEmail() != null ) {
            entity.setEmail( request.getEmail() );
        }
        if ( request.getName() != null ) {
            entity.setName( request.getName() );
        }
        if ( request.getPhone() != null ) {
            entity.setPhone( request.getPhone() );
        }
    }
}

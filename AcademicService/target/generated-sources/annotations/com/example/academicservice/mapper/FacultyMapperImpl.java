package com.example.academicservice.mapper;

import com.example.academicservice.dto.request.FacultyCreateRequest;
import com.example.academicservice.dto.request.FacultyUpdateRequest;
import com.example.academicservice.dto.response.FacultyResponse;
import com.example.academicservice.entity.Faculty;
import com.example.academicservice.entity.University;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T21:09:26+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Override
    public Faculty toEntity(FacultyCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Faculty faculty = new Faculty();

        faculty.setCode( request.getCode() );
        faculty.setDescription( request.getDescription() );
        faculty.setName( request.getName() );

        return faculty;
    }

    @Override
    public FacultyResponse toResponse(Faculty entity) {
        if ( entity == null ) {
            return null;
        }

        FacultyResponse facultyResponse = new FacultyResponse();

        facultyResponse.setUniversityId( entityUniversityId( entity ) );
        facultyResponse.setUniversityName( entityUniversityName( entity ) );
        facultyResponse.setCode( entity.getCode() );
        facultyResponse.setDescription( entity.getDescription() );
        facultyResponse.setId( entity.getId() );
        facultyResponse.setIsActive( entity.getIsActive() );
        facultyResponse.setName( entity.getName() );
        facultyResponse.setSlug( entity.getSlug() );

        return facultyResponse;
    }

    @Override
    public void updateEntityFromRequest(FacultyUpdateRequest request, Faculty entity) {
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

    private Long entityUniversityId(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }
        University university = faculty.getUniversity();
        if ( university == null ) {
            return null;
        }
        Long id = university.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityUniversityName(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }
        University university = faculty.getUniversity();
        if ( university == null ) {
            return null;
        }
        String name = university.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

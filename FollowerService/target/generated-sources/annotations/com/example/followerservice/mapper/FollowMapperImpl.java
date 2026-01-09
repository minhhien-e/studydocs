package com.example.followerservice.mapper;

import com.example.followerservice.dto.request.FollowRequest;
import com.example.followerservice.dto.response.FollowResponse;
import com.example.followerservice.entity.Follow;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-09T12:40:43+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public Follow toEntity(FollowRequest request) {
        if ( request == null ) {
            return null;
        }

        Follow follow = new Follow();

        follow.setFollowerId( request.getFollowerId() );
        follow.setFollowingId( request.getFollowingId() );

        return follow;
    }

    @Override
    public FollowResponse toResponse(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        FollowResponse followResponse = new FollowResponse();

        followResponse.setId( follow.getId() );
        followResponse.setFollowerId( follow.getFollowerId() );
        followResponse.setFollowingId( follow.getFollowingId() );
        followResponse.setCreatedAt( follow.getCreatedAt() );

        return followResponse;
    }
}

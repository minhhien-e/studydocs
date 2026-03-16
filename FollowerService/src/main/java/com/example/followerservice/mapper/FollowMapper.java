package com.example.followerservice.mapper;

import com.example.followerservice.dto.request.FollowRequest;
import com.example.followerservice.dto.response.FollowResponse;
import com.example.followerservice.entity.Follow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    Follow toEntity(FollowRequest request);

    FollowResponse toResponse(Follow follow);
}

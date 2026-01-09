package com.example.followerservice.repository;

import com.example.followerservice.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {
    
    // Kiểm tra xem user A có đang follow user B không
    boolean existsByFollowerIdAndFollowingId(UUID followerId, UUID followingId);
    
    // Tìm bản ghi follow cụ thể
    Optional<Follow> findByFollowerIdAndFollowingId(UUID followerId, UUID followingId);

    // Lấy danh sách những người mà userId đang follow (Following)
    List<Follow> findByFollowerId(UUID followerId);

    // Lấy danh sách những người đang follow userId (Followers)
    List<Follow> findByFollowingId(UUID followingId);

    // Đếm số lượng following
    long countByFollowerId(UUID followerId);

    // Đếm số lượng followers
    long countByFollowingId(UUID followingId);
}

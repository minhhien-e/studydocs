-- Sample follow relationships for testing
-- User IDs (use your actual user IDs from UserService)

-- User 1 follows User 2, 3, 4
INSERT INTO follows (id, follower_id, following_id, created_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440001',
     '550e8400-e29b-41d4-a716-446655440000',
     '6ba7b810-9dad-11d1-80b4-00c04fd430c8',
     NOW());

INSERT INTO follows (id, follower_id, following_id, created_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440002',
     '550e8400-e29b-41d4-a716-446655440000',
     '7c9e6679-7425-40de-944b-e07fc1f90ae7',
     NOW());

INSERT INTO follows (id, follower_id, following_id, created_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440003',
     '550e8400-e29b-41d4-a716-446655440000',
     '886313e1-3b8a-5372-9b90-0c9aee199e5d',
     NOW());

-- User 2 follows User 1
INSERT INTO follows (id, follower_id, following_id, created_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440004',
     '6ba7b810-9dad-11d1-80b4-00c04fd430c8',
     '550e8400-e29b-41d4-a716-446655440000',
     NOW());

-- User 3 follows User 1, 2
INSERT INTO follows (id, follower_id, following_id, created_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440005',
     '7c9e6679-7425-40de-944b-e07fc1f90ae7',
     '550e8400-e29b-41d4-a716-446655440000',
     NOW());

INSERT INTO follows (id, follower_id, following_id, created_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440006',
     '7c9e6679-7425-40de-944b-e07fc1f90ae7',
     '6ba7b810-9dad-11d1-80b4-00c04fd430c8',
     NOW());
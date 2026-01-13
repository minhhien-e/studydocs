package com.example.followerservice.exception;

/**
 * Follow service error codes: reserved range 800..899.
 *
 * <p>Notes:
 * <ul>
 *   <li>Success should use {@code errorCode = null}.</li>
 *   <li>Client maps {@code errorCode} to a user-friendly message.</li>
 * </ul>
 */
public final class FollowErrorCodes {
    private FollowErrorCodes() {}

    /** Follow relationship not found. */
    public static final int FOLLOW_NOT_FOUND = 801;

    /** Already following this user (duplicate). */
    public static final int ALREADY_FOLLOWING = 802;

    /** Cannot follow yourself. */
    public static final int CANNOT_FOLLOW_SELF = 803;

    /** User not found (follower or following). */
    public static final int USER_NOT_FOUND = 804;

    /** Invalid UUID format. */
    public static final int INVALID_UUID = 805;

    /** Follower not found. */
    public static final int FOLLOWER_NOT_FOUND = 806;

    /** Following user not found. */
    public static final int FOLLOWING_NOT_FOUND = 807;

    /** Follow by ID not found. */
    public static final int FOLLOW_BY_ID_NOT_FOUND = 808;

    /** Invalid follower ID. */
    public static final int INVALID_FOLLOWER_ID = 809;

    /** Invalid following ID. */
    public static final int INVALID_FOLLOWING_ID = 810;

    /** Invalid follow creation time. */
    public static final int INVALID_FOLLOW_CREATION_TIME = 811;

    /** Follower ID is null or empty. */
    public static final int FOLLOWER_ID_NULL_OR_EMPTY = 812;

    /** Following ID is null or empty. */
    public static final int FOLLOWING_ID_NULL_OR_EMPTY = 813;

    /** Duplicate follow relationship. */
    public static final int DUPLICATE_FOLLOW = 816;

    /** Unknown follow error (fallback). */
    public static final int FOLLOW_UNKNOWN = -1;
}

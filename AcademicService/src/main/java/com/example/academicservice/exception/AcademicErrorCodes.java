package com.example.academicservice.exception;

/**
 * Academic service error codes: reserved range 200..299.
 *
 * <p>Notes:
 * <ul>
 *   <li>Success should use {@code errorCode = null}.</li>
 *   <li>Client maps {@code errorCode} to a user-friendly message.</li>
 * </ul>
 */
public final class AcademicErrorCodes {
    private AcademicErrorCodes() {}

    /** University not found. */
    public static final int UNIVERSITY_NOT_FOUND = 201;

    /** Faculty not found. */
    public static final int FACULTY_NOT_FOUND = 202;

    /** Department not found. */
    public static final int DEPARTMENT_NOT_FOUND = 203;

    /** Major not found. */
    public static final int MAJOR_NOT_FOUND = 204;

    /** University slug already exists (duplicate). */
    public static final int UNIVERSITY_SLUG_EXISTS = 205;

    /** Faculty slug already exists (duplicate). */
    public static final int FACULTY_SLUG_EXISTS = 206;

    /** Department slug already exists (duplicate). */
    public static final int DEPARTMENT_SLUG_EXISTS = 207;

    /** Major slug already exists (duplicate). */
    public static final int MAJOR_SLUG_EXISTS = 208;

    /** University ID mismatch (resource doesn't belong to specified university). */
    public static final int UNIVERSITY_ID_MISMATCH = 209;

    /** Faculty ID mismatch (resource doesn't belong to specified faculty/university). */
    public static final int FACULTY_ID_MISMATCH = 210;

    /** Department ID mismatch (resource doesn't belong to specified department/faculty/university). */
    public static final int DEPARTMENT_ID_MISMATCH = 211;

    /** Major ID mismatch (resource doesn't belong to specified major/department/faculty/university). */
    public static final int MAJOR_ID_MISMATCH = 212;

    /** Invalid UUID format. */
    public static final int INVALID_UUID = 213;

    /** Unknown academic error (fallback). */
    public static final int UNKNOWN_ACADEMIC_ERROR = -1;
}


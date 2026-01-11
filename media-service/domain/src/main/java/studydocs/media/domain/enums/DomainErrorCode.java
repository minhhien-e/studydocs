package studydocs.media.domain.enums;

public enum DomainErrorCode {
    /* ===================== NOT FOUND ===================== */
    FILE_NOT_FOUND(0, DomainErrorCategory.NOT_FOUND),
    FILE_NOT_SUPPORTED(1, DomainErrorCategory.BUSINESS_RULE),
    FILE_INVALID_FORMAT(2, DomainErrorCategory.BUSINESS_RULE),
    FILE_NAME_INVALID(3, DomainErrorCategory.BUSINESS_RULE),
    FILE_SIZE_INVALID(4, DomainErrorCategory.BUSINESS_RULE),
    TOTAL_PAGES_INVALID(5, DomainErrorCategory.BUSINESS_RULE),
    FILE_CREATION_TIME_INVALID(6, DomainErrorCategory.BUSINESS_RULE),
    STORAGE_LOCATION_INVALID(7, DomainErrorCategory.BUSINESS_RULE),
    CONCURRENT_UPDATE(99, DomainErrorCategory.BUSINESS_RULE);

    private final Integer value;
    private final DomainErrorCategory category;

    DomainErrorCode(Integer value, DomainErrorCategory category) {
        this.value = value;
        this.category = category;
    }

    public Integer getValue() {
        return value;
    }

    public DomainErrorCategory getCategory() {
        return category;
    }
}

package studydocs.notification.infrastructure.exception.mapper;

import org.junit.jupiter.api.Test;
import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;
import studydocs.notification.infrastructure.exception.HttpException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainToHttpExceptionMapperTest {

    private final DomainToHttpExceptionMapper mapper = new DomainToHttpExceptionMapper();

    @Test
    void shouldMapNotFoundExceptionsTo404() {
        assertMapping(DomainErrorCode.NOTIFICATION_NOT_FOUND, 404);
        assertMapping(DomainErrorCode.TEMPLATE_NOT_FOUND, 404);
        assertMapping(DomainErrorCode.RECIPIENT_NOT_FOUND, 404);
        assertMapping(DomainErrorCode.SENDER_NOT_FOUND, 404);
        assertMapping(DomainErrorCode.RECIPIENTS_NOT_FOUND, 404);
    }

    @Test
    void shouldMapAccessDeniedTo403() {
        assertMapping(DomainErrorCode.ACCESS_DENIED, 403);
    }

    @Test
    void shouldMapBadRequestExceptionsTo400() {
        assertMapping(DomainErrorCode.INVALID_NOTIFICATION_STATUS, 400);
        assertMapping(DomainErrorCode.TEMPLATE_ALREADY_EXISTS, 400);
        assertMapping(DomainErrorCode.INVALID_TEMPLATE_DESCRIPTION, 400);
        // ... add other 400 cases as needed or spot check
        assertMapping(DomainErrorCode.INVALID_TEMPLATE_BODY, 400);
    }

    @Test
    void shouldMapGenericErrorTo500() {
        assertMapping(DomainErrorCode.GENERIC_ERROR, 500);
    }

    private void assertMapping(DomainErrorCode errorCode, int expectedStatus) {
        DomainException domainException = new TestDomainException(errorCode);
        HttpException httpException = mapper.map(domainException);

        assertNotNull(httpException);
        assertEquals(expectedStatus, httpException.getStatusCode());
        assertEquals(errorCode.getValue(), httpException.getErrorCode());
        assertEquals("Test message", httpException.getMessage());
    }

    static class TestDomainException extends DomainException {
        public TestDomainException(DomainErrorCode errorCode) {
            super("Test message", errorCode);
        }
    }
}

package studydocs.notificationservice.shared.exception.business;


import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class BusinessRuleViolationException extends HttpException {
    public BusinessRuleViolationException(String message) {
        super(400, ErrorCode.BUSINESS_RULE_VIOLATION, message);
    }
}

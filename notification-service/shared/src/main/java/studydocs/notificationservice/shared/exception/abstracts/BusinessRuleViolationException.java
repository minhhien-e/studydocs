package studydocs.notificationservice.shared.exception.abstracts;


import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class BusinessRuleViolationException extends HttpException {
    public BusinessRuleViolationException(String message) {
        super(400, ErrorCode.BUSINESS_RULE_VIOLATION, message);
    }
}

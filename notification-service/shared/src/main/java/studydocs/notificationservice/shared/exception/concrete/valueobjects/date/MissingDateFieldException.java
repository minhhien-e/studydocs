package studydocs.notificationservice.shared.exception.concrete.valueobjects.date;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingDateFieldException extends RequiredFieldMissingException {
    private static final String format = "Thời gian %s của %s";
    public MissingDateFieldException(String fieldName,String domainName) {
        super(String.format(format, fieldName, domainName));
    }
}

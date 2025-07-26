package studydocs.notificationservice.shared.exception.concrete.valueobjects.name;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingNameFieldException extends RequiredFieldMissingException {
    private static final String format = "Tên của %s";

    public MissingNameFieldException(String domainName) {
        super(String.format(format, domainName));
    }
}

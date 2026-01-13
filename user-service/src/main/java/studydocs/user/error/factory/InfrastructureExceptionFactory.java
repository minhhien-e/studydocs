package studydocs.user.error.factory;

import studydocs.user.error.ErrorCode;
import studydocs.user.error.exception.InfrastructureException;

public class InfrastructureExceptionFactory {

    public static InfrastructureException custom(ErrorCode code, String method) {
        return new InfrastructureException(code, method);
    }

    public static InfrastructureException custom(ErrorCode code, String method, Throwable cause) {
        return new InfrastructureException(code, method, cause);
    }
}

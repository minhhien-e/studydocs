package studydocs.notificationservice.infrastructure.inbound.swagger.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface StandardApiResponses {
    SuccessfulResponse successExample();

    ErrorResponse[] errorExamples() default {};
}

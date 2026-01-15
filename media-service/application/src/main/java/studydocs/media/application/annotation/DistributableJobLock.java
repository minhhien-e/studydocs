package studydocs.media.application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributableJobLock {
    String name();

    long lockAtMostFor();

    long lockAtLeastFor() default 0;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}

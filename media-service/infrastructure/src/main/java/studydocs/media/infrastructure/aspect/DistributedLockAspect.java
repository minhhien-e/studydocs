package studydocs.media.infrastructure.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import studydocs.media.application.annotation.DistributableJobLock;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAspect {

    private final LockProvider lockProvider;

    @Around("@annotation(distributableJobLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributableJobLock distributableJobLock) throws Throwable {
        LockConfiguration lockConfiguration = new LockConfiguration(
                Instant.now(),
                distributableJobLock.name(),
                Duration.of(distributableJobLock.lockAtMostFor(), distributableJobLock.timeUnit().toChronoUnit()),
                Duration.of(distributableJobLock.lockAtLeastFor(), distributableJobLock.timeUnit().toChronoUnit()));

        return lockProvider.lock(lockConfiguration)
                .map(lock -> {
                    try {
                        log.debug("Acquired lock for job: {}", distributableJobLock.name());
                        return proceed(joinPoint);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                        log.debug("Released lock for job: {}", distributableJobLock.name());
                    }
                }).orElseGet(() -> {
                    log.debug("Job {} skipped. Could not acquire lock.", distributableJobLock.name());
                    return null;
                });
    }

    private Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}

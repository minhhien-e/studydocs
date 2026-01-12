package studydocs.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayRepairConfig {

    @Bean
    public FlywayMigrationStrategy repairStrategy() {
        return flyway -> {
            // Repair metadata to fix failed migration entries (checksum mismatches, failed
            // statuses)
            flyway.repair();
            // Then proceed with migration
            flyway.migrate();
        };
    }
}

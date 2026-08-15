package com.studydocs.config;

import com.studydocs.modules.academic.entity.UniversityEntity;
import com.studydocs.modules.academic.repository.UniversityRepository;
import com.studydocs.modules.user.entity.RoleEntity;
import com.studydocs.modules.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Tự động khởi tạo dữ liệu hạt giống (Roles & Universities) khi ứng dụng chạy lần đầu.
 *
 * @author StudyDocs Team
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UniversityRepository universityRepository;

    @Override
    public void run(String... args) {
        // Khởi tạo các vai trò hệ thống nếu chưa có
        if (roleRepository.count() == 0) {
            log.info("Seeding initial System Roles...");
            roleRepository.save(RoleEntity.builder().name("ROLE_ADMIN").description("Administrator").build());
            roleRepository.save(RoleEntity.builder().name("ROLE_USER").description("Standard Student User").build());
        }

        // Khởi tạo danh sách các Trường Đại học mặc định nếu chưa có
        if (universityRepository.count() == 0) {
            log.info("Seeding initial Universities...");
            universityRepository.save(UniversityEntity.builder()
                    .code("HUST")
                    .name("Đại học Bách khoa Hà Nội")
                    .englishName("Hanoi University of Science and Technology")
                    .logoUrl("https://upload.wikimedia.org/wikipedia/vi/a/a1/Logo_Hust.png")
                    .build());
            universityRepository.save(UniversityEntity.builder()
                    .code("VNU")
                    .name("Đại học Quốc gia Hà Nội")
                    .englishName("Vietnam National University, Hanoi")
                    .logoUrl("https://vnu.edu.vn/upload/logo.png")
                    .build());
            universityRepository.save(UniversityEntity.builder()
                    .code("NEU")
                    .name("Đại học Kinh tế Quốc dân")
                    .englishName("National Economics University")
                    .logoUrl("https://neu.edu.vn/logo.png")
                    .build());
            universityRepository.save(UniversityEntity.builder()
                    .code("HCMUT")
                    .name("Đại học Bách khoa TP.HCM")
                    .englishName("Ho Chi Minh City University of Technology")
                    .logoUrl("https://hcmut.edu.vn/logo.png")
                    .build());
        }
    }
}

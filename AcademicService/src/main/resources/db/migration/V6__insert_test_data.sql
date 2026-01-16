-- Migration V6: Insert sample data for testing

-- 1. Insert University (HCMUTE)
-- ID: 9a6d4726-d983-42e7-8b53-46115930269f
INSERT INTO universities (id, name, slug, code, is_active, description)
VALUES ('9a6d4726-d983-42e7-8b53-46115930269f', 
        'Đại học Sư phạm Kỹ thuật TP.HCM', 
        'dai-hoc-su-pham-ky-thuat-tp-hcm', 
        'HCMUTE', 
        true,
        'Trường đại học trọng điểm về kỹ thuật tại Việt Nam');

-- 2. Insert Faculty (CNTT)
-- ID: f47ac10b-58cc-4372-a567-0e02b2c3d479
INSERT INTO faculties (id, university_id, name, slug, code, is_active)
VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 
        '9a6d4726-d983-42e7-8b53-46115930269f', 
        'Khoa Công nghệ Thông tin', 
        'khoa-cong-nghe-thong-tin', 
        'FIT', 
        true);

-- 3. Insert Department (CNPM)
-- ID: 13a1e349-3424-4c4f-9556-91e03889b940
INSERT INTO departments (id, faculty_id, name, slug, code, is_active)
VALUES ('13a1e349-3424-4c4f-9556-91e03889b940', 
        'f47ac10b-58cc-4372-a567-0e02b2c3d479', 
        'Bộ môn Công nghệ Phần mềm', 
        'bo-mon-cong-nghe-phan-mem', 
        'SE', 
        true);

-- 4. Insert Subject (Lập trình Java)
-- ID: c8f1e621-e374-4b52-b892-7a4d5c90b8e1
INSERT INTO subjects (id, department_id, name, slug, code, is_active)
VALUES ('c8f1e621-e374-4b52-b892-7a4d5c90b8e1', 
        '13a1e349-3424-4c4f-9556-91e03889b940', 
        'Lập trình Java căn bản', 
        'lap-trinh-java-can-ban', 
        'JAVA001', 
        true);

-- 5. Insert Subject Document (Sample Document)
-- ID: d290f1ee-6c54-4b01-90e6-d701748f0851
-- Document ID (External): 3fa85faa-5717-4562-b3fc-2c963f66afa6
INSERT INTO subject_documents (id, subject_id, document_id, description, is_active)
VALUES ('d290f1ee-6c54-4b01-90e6-d701748f0851', 
        'c8f1e621-e374-4b52-b892-7a4d5c90b8e1', 
        '3fa85faa-5717-4562-b3fc-2c963f66afa6', -- ID from your example URL
        'Tài liệu bài giảng Java Chapter 1', 
        true);

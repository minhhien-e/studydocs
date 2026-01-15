# Danh sách mã lỗi File Service

Tài liệu này mô tả các mã lỗi hệ thống, giá trị được quy định bằng giá trị gốc + 300.

| Mã lỗi | Giá trị | Mô tả |
| :--- | :--- | :--- |
| **NOT FOUND** | | |
| `FILE_NOT_FOUND` | 300 | Không tìm thấy tệp tin. |
| **BUSINESS RULE** | | |
| `FILE_NOT_SUPPORTED` | 301 | Định dạng tệp tin không được hỗ trợ. |
| `FILE_INVALID_FORMAT` | 302 | Định dạng tệp tin không hợp lệ. |
| `FILE_NAME_INVALID` | 303 | Tên tệp tin không hợp lệ. |
| `FILE_SIZE_INVALID` | 304 | Kích thước tệp tin không hợp lệ. |
| `TOTAL_PAGES_INVALID` | 305 | Tổng số trang không hợp lệ. |
| `FILE_CREATION_TIME_INVALID` | 306 | Thời gian tạo tệp tin không hợp lệ. |
| `STORAGE_LOCATION_INVALID` | 307 | Storage Location không hợp lệ. |
| `FILE_EMPTY` | 308 | Tệp tin rỗng. |
| **SYSTEM ERROR** | | |
| `UPLOAD_FAILED` | 309 | Upload tệp tin thất bại. |
| `DELETE_FAILED` | 310 | Xóa tệp tin thất bại. |
| `CONCURRENT_UPDATE` | 399 | Dữ liệu đã bị thay đổi bởi người khác (Optimistic Locking). |

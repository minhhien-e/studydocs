# Danh sách mã lỗi Notification Service

Tài liệu này mô tả các mã lỗi hệ thống, giá trị được quy định bằng giá trị gốc + 600.

| Mã lỗi | Giá trị | Mô tả |
| :--- | :--- | :--- |
| **NOT FOUND** | | |
| `NOTIFICATION_NOT_FOUND` | 600 | Không tìm thấy thông báo. |
| `TEMPLATE_BY_ID_NOT_FOUND` | 601 | Không tìm thấy mẫu thông báo theo ID. |
| `TEMPLATE_BY_NAME_NOT_FOUND` | 602 | Không tìm thấy mẫu thông báo theo tên. |
| `RECIPIENT_NOT_FOUND` | 603 | Không tìm thấy người nhận. |
| `RECIPIENTS_NOT_FOUND` | 604 | Không tìm thấy danh sách người nhận. |
| `SENDER_NOT_FOUND` | 605 | Không tìm thấy người gửi. |
| `USER_NOTIFICATION_PROFILE_NOT_FOUND` | 606 | Không tìm thấy hồ sơ thông báo người dùng. |
| `NOTIFICATION_RECIPIENT_NOT_FOUND` | 607 | Không tìm thấy thông tin người nhận trong thông báo. |
| **VALIDATION** | | |
| `INVALID_NOTIFICATION_STATUS` | 608 | Trạng thái thông báo không hợp lệ. |
| `INVALID_NOTIFICATION_TYPE` | 609 | Loại thông báo không hợp lệ. |
| `INVALID_NOTIFICATION_CHANNEL` | 610 | Kênh thông báo không hợp lệ. |
| `INVALID_NOTIFICATION_CREATION_TIME` | 611 | Thời gian tạo thông báo không hợp lệ. |
| `INVALID_NOTIFICATION_DELETION_TIME` | 612 | Thời gian xóa thông báo không hợp lệ. |
| `INVALID_TEMPLATE_NAME` | 613 | Tên mẫu thông báo không hợp lệ. |
| `INVALID_TEMPLATE_SUBJECT` | 614 | Tiêu đề mẫu thông báo không hợp lệ. |
| `INVALID_TEMPLATE_BODY` | 615 | Nội dung mẫu thông báo không hợp lệ. |
| `INVALID_TEMPLATE_DESCRIPTION` | 616 | Mô tả mẫu thông báo không hợp lệ. |
| `TEMPLATE_DESCRIPTION_NULL_OR_EMPTY` | 617 | Mô tả mẫu thông báo bị chi trống. |
| `TEMPLATE_DESCRIPTION_TOO_SHORT` | 618 | Mô tả mẫu thông báo quá ngắn. |
| `TEMPLATE_DESCRIPTION_TOO_LONG` | 619 | Mô tả mẫu thông báo quá dài. |
| `INVALID_TEMPLATE_CHANNEL` | 620 | Kênh mẫu thông báo không hợp lệ. |
| `INVALID_TEMPLATE_CREATION_TIME` | 621 | Thời gian tạo mẫu thông báo không hợp lệ. |
| `INVALID_TEMPLATE_UPDATE_TIME` | 622 | Thời gian cập nhật mẫu thông báo không hợp lệ. |
| `INVALID_TEMPLATE_TYPE` | 623 | Loại mẫu thông báo không hợp lệ. |
| `INVALID_BODY_DATA` | 624 | Dữ liệu nội dung không hợp lệ. |
| `INVALID_PERSONALIZED_DATA` | 625 | Dữ liệu cá nhân hóa không hợp lệ. |
| `INVALID_NOTIFICATION_SNAPSHOT_SUBJECT` | 626 | Snapshot tiêu đề thông báo không hợp lệ. |
| `INVALID_NOTIFICATION_SNAPSHOT_BODY` | 627 | Snapshot nội dung thông báo không hợp lệ. |
| `INVALID_FCM_TOKEN` | 628 | Token FCM không hợp lệ. |
| `INVALID_EMAIL_ADDRESS` | 637 | Địa chỉ email không hợp lệ. |
| `INVALID_PHONE_NUMBER` | 638 | Số điện thoại không hợp lệ. |
| **CONFLICT** | | |
| `TEMPLATE_ALREADY_EXISTS` | 629 | Mẫu thông báo đã tồn tại. |
| `DUPLICATE_FCM_TOKEN` | 630 | Token FCM bị trùng lặp. |
| `NOTIFICATION_ALREADY_SOFT_DELETED` | 631 | Thông báo đã bị xóa mềm trước đó. |
| `NOTIFICATION_RECIPIENT_ALREADY_EXISTS` | 632 | Người nhận thông báo đã tồn tại. |
| `USER_NOTIFICATION_PROFILE_ALREADY_EXISTS` | 633 | Hồ sơ thông báo người dùng đã tồn tại. |
| `NOTIFICATION_NOT_SOFT_DELETED` | 634 | Thông báo chưa bị xóa mềm. |
| `NOTIFICATION_RECIPIENT_DELETED` | 635 | Người nhận thông báo đã bị xóa. |
| **FORBIDDEN** | | |
| `ACCESS_DENIED` | 636 | Từ chối truy cập. |

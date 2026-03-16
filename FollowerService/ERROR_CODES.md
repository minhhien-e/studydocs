# Danh sách mã lỗi Follower Service

Tài liệu này mô tả các mã lỗi hệ thống, giá trị được quy định bằng giá trị gốc + 800.

| Mã lỗi | Giá trị | Mô tả |
| :--- | :--- | :--- |
| **NOT FOUND** | | |
| `FOLLOW_NOT_FOUND` | 801 | Không tìm thấy mối quan hệ follow. |
| `USER_NOT_FOUND` | 804 | Không tìm thấy user (follower hoặc following). |
| `FOLLOWER_NOT_FOUND` | 806 | Không tìm thấy người follow. |
| `FOLLOWING_NOT_FOUND` | 807 | Không tìm thấy người được follow. |
| `FOLLOW_BY_ID_NOT_FOUND` | 808 | Không tìm thấy mối quan hệ follow theo ID. |
| **VALIDATION** | | |
| `INVALID_UUID` | 805 | UUID format không hợp lệ. |
| `INVALID_FOLLOWER_ID` | 809 | Follower ID không hợp lệ. |
| `INVALID_FOLLOWING_ID` | 810 | Following ID không hợp lệ. |
| `INVALID_FOLLOW_CREATION_TIME` | 811 | Thời gian tạo mối quan hệ follow không hợp lệ. |
| `FOLLOWER_ID_NULL_OR_EMPTY` | 812 | Follower ID bị null hoặc rỗng. |
| `FOLLOWING_ID_NULL_OR_EMPTY` | 813 | Following ID bị null hoặc rỗng. |
| **CONFLICT** | | |
| `ALREADY_FOLLOWING` | 802 | Đã follow người này rồi (duplicate follow). |
| `CANNOT_FOLLOW_SELF` | 803 | Không thể follow chính mình. |
| `DUPLICATE_FOLLOW` | 816 | Mối quan hệ follow bị trùng lặp. |
| **UNKNOWN** | | |
| `FOLLOW_UNKNOWN` | 899 | Lỗi follow không xác định (fallback). |

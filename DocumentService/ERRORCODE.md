# Error Codes - Document Service

### Range: 400-499

| Code | Message | Description |
|---|---|---|
| -1 | Internal Server Error | Unexpected server error. |
| 400 | Bad Request | Validation failed or invalid input. |
| 401 | Document Not Found | Resource (Document) not found with given ID. |
| 402 | Invalid File Type | File extension/MIME type not supported. |
| 403 | File Size Exceeded | File size exceeds maximum limit. |
| 404 | User Not Found | User associated with action not found. |
| 405 | Invalid Title | Title is missing or empty. |
| 406 | Permission Denied | User does not have permission for this action. |
| 407 | Remote Upload Failed | Failed to upload file to storage service. |

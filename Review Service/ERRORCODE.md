# Error Codes - Review Service

### Range: 500-599

| Code | Message | Description |
|---|---|---|
| -1 | Internal Server Error | Unexpected server error. |
| 500 | Bad Request | Validation failed or invalid input. |
| 501 | Review Not Found | Review resource not found. |
| 502 | Invalid Document ID | Target document does not exist. |
| 503 | Invalid Rating | Rating value is out of range (1-5). |
| 504 | Remote Service Error | Error calling external dependencies. |
| 505 | Duplicate Review | User has already reviewed this document. |
| 506 | Content Too Short | Review content does not meet minimum length. |
| 507 | User Not Authorized | Authentication failed or missing. |
| 508 | Permission Denied | User denied access to resource. |

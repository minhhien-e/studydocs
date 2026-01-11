# Document Service

## Overview
Service for managing documents in the StudyDocs system.

## Configuration
- **Port:** 8085
- **Database:** MySQL (URL configurable via `SPRING_DATASOURCE_URL`)
- **Error Codes:**
    - Standard: 400-499
    - Unknown/Internal: -1

## API Endpoints
- Admin Stats: `/api/v1/documents/admin/stats`
- Documents: `/api/v1/documents`

## Environment Variables
- `SERVER_PORT`: Service port (default 8085)
- `SPRING_DATASOURCE_URL`: JDBC URL for database connection

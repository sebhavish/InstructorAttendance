# Institute Instructor Attendance System
## How to Run:
- Prerequisites -
    - Docker
    - Docker Compose
    - Git (optional)
- Clone the project from GitHub:
    ```shell 
    git clone <project-url>
    ```
- Go to the project's root directory - `path/to/attendance/`
- Now run this docker compose command
    ```shell
    docker compose up
    ```
- That's it now you can access the API at port - 8080
- To stop run this docker compose command
    ```shell
    docker compose down
    ```
## API Documentation:
- You can find the swagger documentation of this project at [this location](resources/api-doc.yaml).
- Alternatively, you can access swagger ui at this [url](http://localhost:8080/swagger-ui.html), while the project is running.
- You can access the postman collection at [this location](resources/InstructorAttendance.postman_collection.json).

## Tech Stack:
| Area       | Name      |
|------------|-----------|
| Language   | Java      |
| FrameWork  | Spring    |
| ORM        | Hibernate |
| Database   | Postgres  |
| Logging    | Slf4J     |
| Containers | Docker    |
| Build Tool | Maven     |

## Important Scenarios to Consider
- To be able to query and compute monthly attendance time easily and handle edge cases, when an instructor checks in at 9:00 PM and checks out
  at next day 3:00 AM, then two attendance records will be inserted in the db. One at 9:00 PM to 11:59 PM and other at 12:00 AM to 3:00 AM.
- Instructor won't be able to check in again, if he already checked in previously.
- Instructor won't be able to check out again, if he already checked out previously.
- If the time between check in and check out is more than 24 hrs, the instructor won't be able to check out, and may
  need to contact admin / manager.
- While generating reports if the instructor is not yet checked out by the month end time, then
  - current time will be considered as the check out time, if current time is less than month end time
  - month end time will be considered as the check out time, if the current time is greater than month end time

# ChatApp Backend

The ChatApp Backend is a Java Spring Boot application that provides functionality for creating chat groups, joining groups, and sending/retrieving messages within those groups. The application uses a SQLite database to store the data and exposes a RESTful JSON API over HTTP(s) for communication with the client.

## Project Structure
The project has the following structure:

Chatapp-backend

```
├─ src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.chatapp/
│   │   │       ├── config/
│   │   │       │   ├── ModelMapperConfig.java
│   │   │       │   └── WebConfig.java
│   │   │       ├── controllers/
│   │   │       │   ├── BaseController.java
│   │   │       │   ├── ChatController.java
│   │   │       │   └── RestControllerAdvice.java
│   │   │       ├── dto/
│   │   │       │   ├── GroupRequest.java
│   │   │       │   ├── GroupResponse.java
│   │   │       │   ├── HttpErrorResponse.java
│   │   │       │   ├── MessageRequest.java
│   │   │       │   └── MessageResponse.java
│   │   │       │   └── UserResponse.java
│   │   │       ├── entities/
│   │   │       │   ├── Group.java
│   │   │       │   ├── Message.java
│   │   │       │   └── User.java
│   │   │       ├── exception/
│   │   │       │   ├── NotFoundException.java
│   │   │       ├── repositories/
│   │   │       │   ├── GroupRepository.java
│   │   │       │   └── MessageRepository.java
│   │   │       │   ├── UserRepository.java
│   │   │       ├── services/
│   │   │       │   ├── GroupService.java
│   │   │       │   ├── GroupServiceImpl.java
│   │   │       │   └── MessageService.java
│   │   │       │   └── MessageServiceImpl.java
│   │   │       │   └── UserService.java
│   │   │       │   └── UserServiceImpl.java
│   │   │       └── ChatAppApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/
│           └── com.example.chatapp/
│               ├── controller/
│               │   ├── ChatControllerTests.java
│               ├── ChatApplicationTests.java
└── pom.xml
```

## Endpoints
The application provides the following endpoints for interacting with chat groups and messages:

- `POST /api/v1/users`: Creates a new user.
- `POST /api/v1/users?userId=`: Gets a specific user.
- `POST /api/v1/groups`: Creates a new chat group.
- `GET /api/v1/groups`: Retrieves a list of all chat groups.
- `GET /api/v1/groups/{groupName}`: Retrieves one chat group.
- `GET /api/v1/groups/{groupName}/users`: Retrieves specific chat group's user list.
- `POST /api/v1/groups/{groupId}/users?username=`: Allows a user to join a specific chat group.
- `DELETE /api/v1/groups/{groupId}/users?username=`: Deletes a user from a specific chat group.
- `POST /api/v1/groups/{groupId}/messages`: Sends a new message to a chat group.
- `GET /api/v1/groups/{groupId}/messages`: Retrieves all messages within a chat group.


## Database
The application utilizes a SQLite database to store chat groups and messages. The database schema includes tables for User, Group and Message, which are mapped to their respective Java models.

## Tests
The project includes unit tests to ensure the correctness of controllers, repositories, and services. The test classes can be found under the src/test directory and are organized based on the corresponding code components.

To run the tests, you can execute the following command in the project root directory:


```
$ mvn test
```
The tests will be executed, and the results will be displayed in the console.


# OnePercent

OnePercent is a goal-oriented task management application designed to help you achieve your goals efficiently. Unlike a traditional to-do list, OnePercent is optimized to break your goals into actionable tasks, ensuring every step you take leads you closer to success.

---

## Features

- **Goal Setting**: Define your goals with clarity and purpose.
- **Task Management**: Create tasks linked to specific goals to stay on track.
- **Progress Tracking**: Monitor your progress towards achieving your goals.
- **Priority Optimization**: Focus on tasks that have the highest impact on your goals.
- **User-Friendly Interface**: Simple and intuitive design for better productivity.

---

## Getting Started

### Prerequisites

To run OnePercent locally, ensure you have the following installed:

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://www.docker.com/)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/onepercent.git
   cd onepercent
   ```

2. Build the application:
   ```bash
   ./mvnw clean install
   ```

3. Run the application using Docker:
   ```bash
   docker-compose up
   ```

4. Access the application at `http://localhost:8080`.

---

## Usage

1. **Create Goals**: Start by adding your long-term or short-term goals.
2. **Add Tasks**: Break each goal into smaller tasks.
3. **Track Progress**: Mark tasks as complete and see how they contribute to your goals.
4. **Stay Focused**: Use priority suggestions to focus on what matters most.

---

## Project Structure

```
onepercent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.onepercent.goaltracker/
│   │   │   │   ├── controllers/    # REST API Controllers
│   │   │   │   ├── services/       # Business Logic
│   │   │   │   ├── repositories/   # Data Access Layer
│   │   │   │   ├── entities/       # JPA Entities
│   │   │   │   ├── dtos/           # Data Transfer Objects
│   │   │   │   └── mappers/        # Entity-DTO Mapping
│   ├── test/                       # Unit and Integration Tests
├── docker-compose.yml              # Docker Configuration
├── pom.xml                         # Maven Project Configuration
└── README.md                       # Project Documentation
```

---

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: PostgreSQL
- **Containerization**: Docker
- **Build Tool**: Maven

---

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Submit a pull request.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Acknowledgments

OnePercent is inspired by the principle of continuous improvement—achieving big goals by consistently making small, impactful steps every day. Special thanks to everyone who contributed to this project!

# Smart Refrigerator App

Welcome to the **Smart Refrigerator App**, a clever tool that uses AI to whip up recipes based on what’s in your fridge. It’s all about reducing waste and sparking kitchen creativity!

## Technologies Used

- **Java 21**: Powers the app with solid performance.
- **Spring Boot 3.4.4**: Makes web app development a breeze.
- **Spring Data JPA**: Handles database tasks smoothly.
- **Spring AI Ollama**: Brings AI recipe magic to life.
- **H2 Database**: Lightweight database for dev and testing.
- **Flyway**: Keeps database changes under control.
- **Lombok**: Cuts down on repetitive code.
- **Maven**: Manages builds and dependencies.

## Getting Started

To run the app locally, just follow these steps:

1. **Clone the repo**:
   ```bash
   git clone https://github.com/NathanAlmeida2006/Refrigerator-IA.git
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

3. **Launch the app**:
   ```bash
   mvn spring-boot:run
   ```

4. **Check it out** at `http://localhost:8080`.

**Note**: You’ll need Java 21 and Maven installed. Also, set up Ollama locally for the AI features to work.

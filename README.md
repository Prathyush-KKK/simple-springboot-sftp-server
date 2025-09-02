# Embedded SFTP Server for Local Development

A lightweight, standalone Spring Boot application that starts an embedded SFTP server on your local machine. This project is designed to act as a simple, configurable file transfer endpoint for testing integration projects without needing a dedicated, system-wide SFTP server.

It was specifically created to simulate the Digital Gate file server for the **OSM HOTO Adapter** demo.

## Features

-   **Embedded SFTP Server:** No external software or installation required. Runs on any machine with Java.
-   **Fully Configurable:** Easily change the port, user, password, and root directory via `application.properties`.
-   **Automatic Directory Creation:** Automatically creates the necessary subdirectories (`/uploads`, `/control/ready`) on startup.
-   **Lightweight:** Minimal dependencies, fast startup, and low resource usage.
-   **Secure:** Uses the robust Apache MINA SSHD library for the underlying SFTP protocol.

## Prerequisites

Before you begin, ensure you have the following installed:

-   Java 8 or higher
-   Apache Maven 3.x or higher

## Getting Started

Follow these steps to build and run the SFTP server:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Prathyush-KKK/simple-springboot-sftp-server
    cd sftp-server
    ```

2.  **Build the project:**
    Use Maven to compile the project and create an executable JAR file.
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    Start the server from the command line.
    ```bash
    java -jar target/sftp-server-1.0.0-SNAPSHOT.jar
    ```
    or
    ```bash
    mvn spring-boot:run
    ```

You will see log output confirming that the server has started, including the port it's running on and the user credentials to use.

## Configuration

All configuration is managed in the `src/main/resources/application.properties` file.

```properties
# SFTP Server configuration
# The port the SFTP server will listen on.
sftp.server.port=2222

# The username for SFTP login.
sftp.server.user=hoto_user

# The password for SFTP login.
sftp.server.password=sftp_pass

# The root directory for the SFTP server. This will be created in the
# same directory where you run the JAR file. All SFTP paths will be relative to this.
sftp.server.root.dir=/sftp_root

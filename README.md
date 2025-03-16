# Java Technologies Laboratory Experience 🚀

In this series of laboratories, I gained hands-on experience in a wide range of Java technologies, focusing on both algorithmic problem-solving and real-world applications.

## Graph Theory & Algorithms 📊

I started by exploring **graph theory**, including:
- **Wheel graphs** construction
- **Cycle detection algorithms**
- **Adjacency matrices** for representing graphs

## Resource Allocation Problems ⚙️

I then worked on resource allocation problems, such as:
- **Client scheduling**
- **Location-based client allocation**  
  I developed **efficient scheduling solutions** to optimize these processes.

## Graph Coloring & Scheduling 🗓️

I delved into:
- **Graph coloring** for conflict-free scheduling
- **Attraction scheduling** using graph algorithms to avoid conflicts and improve optimization

## Data Manipulation with Java Streams 🔄

As I progressed, I:
- Mastered **Java Streams** for efficient data manipulation
- Integrated **file handling** and **Excel processing** tasks

## Building User Interfaces with JavaFX 🎨

I learned how to create **dynamic user interfaces** using **JavaFX**, enhancing the visual aspect of applications.

## Multi-Threading & Database Operations 💻

I tackled:
- **Multi-threading** for parallel processing
- **Database operations using JDBC** for efficient data handling
- **ORM with JPA** to manage database interactions

## Client-Server Communication 🌐

I explored **client-server communication** using **socket programming** to understand networking and data exchange between systems.

## Building a Spring Boot Application with REST APIs 💼

The culmination of my work involved building a **Spring Boot application** with:
- **REST APIs** for interaction with front-end systems
- **Swagger** for API documentation

## Advanced Topics: Bytecode & Localization 🔧

I also worked on advanced topics such as:
- **Manipulating bytecode with ASM**
- **Localizing applications** using **Java's resource bundles**

---

This comprehensive learning path has provided me with a **strong foundation** in software development, problem-solving, and applying advanced programming techniques in various domains. 💡

---
---
---

# Laboratory 1: Wheel Graph and Algorithm for Finding Cycles

In this laboratory, I worked on creating an adjacency matrix for a wheel graph and implemented an algorithm to find all cycles in the graph. The key tasks and concepts I learned are:

**Key Concepts:**
- **Wheel Graphs**: A wheel graph \( W_n \) is a graph that consists of a cycle with an additional central vertex connected to all vertices of the cycle.
- **Adjacency Matrix**: I learned how to represent the connections between vertices of a graph using an adjacency matrix.
- **Cycle Detection**: I implemented an algorithm to find all the cycles in a wheel graph and validated that the number of cycles matches the theoretical formula \( n^2 - 3n + 3 \).

**Technologies Used:**
- **Java**: I used Java for implementing the wheel graph, the adjacency matrix, and the cycle detection algorithm.
- **Data Structures**: I used arrays for the adjacency matrix and lists for storing cycles, focusing on efficient data management for graph-related tasks.

## Lessons Learned:
- How to construct a graph in memory using an adjacency matrix.
- How to design algorithms for graph traversal and cycle detection.
- How to validate the correctness of the algorithm by comparing the output with a known mathematical formula.

---

# Laboratory 2: Scheduling and Location-based Client Allocation

In this laboratory, I worked on a scheduling and location-based client allocation problem. The main tasks involved managing time intervals for clients, vehicles, and depots.

**Key Concepts:**
- **Client Scheduling**: I learned to manage time intervals for clients, ensuring that their visits fit within the available time slots.
- **Location-based Client Allocation**: I learned how to allocate clients to vehicles and depots based on their locations.

**Technologies Used:**
- **Java**: I used Java to manage client scheduling, allocation logic, and time intervals.
- **TimeInterval Class**: I created and managed time intervals to represent available times for clients and vehicles.
- **Location Class**: I used the Location class to represent the physical locations of clients and vehicles.

## Lessons Learned:
- How to manage and allocate resources (clients, vehicles) based on time intervals and locations.
- Understanding the importance of efficient allocation in scheduling problems.
- Implementing object-oriented principles for managing complex systems like clients, vehicles, and depots.

---

# Laboratory 3: Graph Coloring and Attraction Scheduling

In this laboratory, I worked on graph coloring and scheduling activities based on time and popularity. The key focus was on assigning colors to different attractions to ensure that no two attractions scheduled for the same day clash.

**Key Concepts:**
- **Graph Coloring**: I applied graph coloring algorithms to assign colors to attractions based on the days they are available, ensuring that no two attractions with the same type are scheduled on the same day.
- **Attraction Scheduling**: I created a timetable for different attractions and solved the problem of scheduling visits to them based on their availability.

**Technologies Used:**
- **Java**: I implemented graph coloring and attraction scheduling using Java.
- **LocalTime & LocalDate**: I used the `LocalTime` and `LocalDate` classes to handle time and dates efficiently.
- **Maps**: I used `HashMap` to store and manage the timetable for each attraction.

## Lessons Learned:
- How to apply graph coloring algorithms to real-world problems like scheduling and attraction visits.
- How to manage and schedule activities efficiently using time and location data.
- How to implement heuristic algorithms for graph coloring to optimize scheduling.

---
# **LAB 4: Java Streams and Collections**

In this laboratory, we focused on utilizing Java Streams and Collections to filter, sort, and manipulate data. We learned how to:
- **Work with Java Streams** for processing lists and collections efficiently.
- **Filter and sort data** using Java's Stream API.
- **Use Lambda expressions** and method references to enhance readability and functionality of code.
- **Leverage Java's collection framework**, such as List, Set, and Map, to store and process data effectively.
- **Handle class inheritance** by working with different types of objects in a collection (e.g., `Driver`, `Passenger`).
- **Design and implement matching algorithms** using graph theory concepts.

Technologies used:
- **Java 8+**
- **Java Streams API**
- **Lambda Expressions**
- **Collections Framework (List, Set, Map)**

---

# **LAB 5: File Handling and Excel Integration**

This lab introduced us to file handling and Excel document manipulation in Java. We learned how to:
- **Generate random employee data** and create directories and files programmatically.
- **Create and manage Excel files** using Apache POI.
- **Read and write Excel files**, and process data to generate meaningful reports.
- **Work with file directories**, including creating subdirectories and writing files to them.
- **Map employee abilities** to form maximal groups based on shared skills, utilizing sets and maps for data manipulation.

Technologies used:
- **Java I/O (File Handling)**
- **Apache POI for Excel manipulation**
- **Collections Framework (Set, Map)**
- **Random Data Generation (Faker library)**

---

# **LAB 6: JavaFX UI and Game Design**

In this laboratory, we learned how to build graphical user interfaces (GUIs) using **JavaFX**. The focus was on creating interactive applications. Key takeaways:
- **JavaFX for building user interfaces** with panels, layouts, and controls.
- **Event-driven programming** to handle user interactions.
- **Managing GUI components** (e.g., buttons, panels) and linking them to application logic.
- **Application design patterns** for structuring interactive games or tools.
- **Working with graphical canvases** and dynamic resizing of window elements.

Technologies used:
- **JavaFX**
- **Event Handling**
- **Scene and Stage Management**
- **Canvas and Graphics API**

---

# **LAB 7: Multi-threading and Synchronization in Java**

This laboratory focused on **multi-threading** and **synchronization** in Java. We explored how to manage multiple threads working concurrently on a shared task. The main learnings were:
- **Creating and managing multiple threads** in Java.
- **Synchronization techniques** to avoid race conditions in a multi-threaded environment.
- **Utilizing threads for concurrent execution** of tasks such as token extraction in a game simulation.
- **Using locks and time limits** to control thread execution and handle timing constraints.
- **Sorting and ranking players** based on game scores in a concurrent environment.

Technologies used:
- **Java Threads and Concurrency**
- **Synchronized Blocks/Methods**
- **Thread Synchronization using Locks**
- **Random Data Generation (Faker library)**

---
# **LAB 8: Database Operations with CSV and Graph Manipulation**

In this laboratory, we worked with **CSV files** and connected them to a **relational database** using JDBC. The key points learned were:
- **Reading CSV data** using Apache Commons CSV.
- **Database interaction**: connecting to a SQL database, performing CRUD operations.
- **Handling database transactions** and ensuring data integrity.
- **Creating entities and relationships** (e.g., `Author`, `Genre`, and `Book`) in the database.
- **Graph-based data modeling**: organizing books in a graph structure, performing **equitable coloring** and partitioning the books into reading lists.

Technologies used:
- **JDBC (Java Database Connectivity)**
- **Apache Commons CSV**
- **Graph Theory (BooksGraph)**

---

# **LAB 9: Object-Relational Mapping (ORM) with JPA**

In this lab, we explored **Object-Relational Mapping (ORM)** using **Java Persistence API (JPA)**. The objectives were:
- **Using JPA repositories** to manage entities (`Author`, `Book`, `PublishingHouse`) and handle database operations.
- **Entity management**: creating and persisting entities using annotations (`@Entity`, `@Id`, `@OneToMany`, etc.).
- **Transaction management**: ensuring operations are wrapped in a transaction context using **EntityTransaction**.
- **JPA Query Language (JPQL)** to query data and perform operations on entities.

Technologies used:
- **JPA (Java Persistence API)**
- **Hibernate (JPA Implementation)**
- **Repository Pattern**

---

# **LAB 10: Sockets Programming for Game Client-Server Communication**

This laboratory focused on **sockets programming** for creating a **client-server** game application. We learned how to:
- **Create a server** using **ServerSocket** to listen for incoming client connections and handle multiple clients.
- **Handle communication** between a **game client** and **game server** using **TCP sockets**.
- **Implement client-server communication** protocols, where the client sends commands to the server (e.g., creating a game, placing a ship, checking results, etc.) and receives responses.
- **Multithreading** to manage multiple client connections and interactions concurrently.

Technologies used:
- **Java Sockets (TCP/IP)**
- **Multithreading (for handling multiple clients)**
- **BufferedReader** and **PrintWriter** for handling I/O operations

---

# **LAB 11: Spring Boot Application with REST and Swagger**

In this laboratory, we developed a **Spring Boot application** that exposes a REST API and integrates **Swagger** for API documentation. The objectives were:
- **Spring Boot setup**: Creating a Spring Boot application with `@SpringBootApplication` annotation and running it with `SpringApplication.run()`.
- **Dependency Injection**: Using `@Autowired` to inject dependencies such as the `BookService`.
- **REST API**: Exposing a REST API with **`RestTemplate`** for making HTTP requests.
- **Swagger Integration**: Enabling Swagger UI for easy API documentation and testing.

Technologies used:
- **Spring Boot**
- **Spring Web (RestTemplate)**
- **Swagger 2** for API documentation
- **Java Beans and Dependency Injection**

---

# **LAB 12: Java Reflection and Bytecode Manipulation with ASM**

In this lab, we worked on **Java Reflection** and **bytecode manipulation** using the **ASM library**. The key takeaways were:
- **Reflection**: Using reflection to inspect and modify classes, methods, and fields during runtime.
- **Java Compilation**: Compiling Java files programmatically using the **Java Compiler API**.
- **Bytecode Analysis and Injection**: Using ASM to analyze `.class` files and inject custom bytecode into methods.
- **Custom Class Modification**: Modifying class files by adding custom logic (e.g., injecting print statements into specific methods).

Technologies used:
- **Java Reflection API**
- **ASM Library for Bytecode Manipulation**
- **Java Compiler API** for programmatic compilation

---

# **LAB 13: Internationalization and Localization in Java**

In this laboratory, we explored **localization** in Java applications. The objectives included:
- **Resource Bundles**: Using **`ResourceBundle`** to load language-specific messages and enable multiple locales.
- **Locales Management**: Implementing commands to display available locales and set the application’s locale dynamically.
- **Command-line Interaction**: Building a command-line interface to interact with the program and display localized messages based on the user's locale.

Technologies used:
- **Java ResourceBundle** for localization
- **Java Locale** for managing different languages and country-specific variants
- **Command-line interface** for interacting with the program


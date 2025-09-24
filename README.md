# Pharmacy Project

A simple Java-based pharmacy management system designed to handle basic operations such as managing products, tracking inventory, and recording sales.
The project is structured as a plain Java application that can be run directly in an IDE (like IntelliJ IDEA) or from the command line.

## Getting Started
**1. Clone the Repository**
```bash
git clone https://github.com/Kally-Dossa/PharmacyProject.git
cd PharmacyProject
```

**2. Open in IntelliJ IDEA**

- Open IntelliJ IDEA → File → Open → select the `PharmacyProject` folder.

- Set the Project SDK to your installed JDK (Java 17+ recommended).

- Build the project → Build → Build Project.

- Run the `Main` class (e.g., `com.example.pharmacy.Main`).

**3. Run from the Command Line**
If you prefer to run without an IDE:
```bash
# Compile all Java files into an 'out' folder
javac -d out $(find src -name "*.java")

# Run the main class (replace with your actual package + class)
java -cp out com.example.pharmacy.Main
```

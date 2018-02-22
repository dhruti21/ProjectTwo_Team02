# ProjectTwo_Team02

## GRADER - Steps To Run

### Run the JAR

TBD - Compiled JAR needs to be c/p to root.

## DEVELOPMENT TEAM - Steps To Run

### 1. Install Maven
Debian Linux: `sudo apt-get install maven`\
Mac: `brew install maven`\
Windows: [Maven Windows Guide](https://maven.apache.org/guides/getting-started/windows-prerequisites.html) or use IDE import whole project.

### 2. Build Project

`cd` to your project directory then run: `mvn clean package`

### 3. Run The JAR
#### Server
`java -jar Server/target/Server-1.0-SNAPSHOT-jar-with-dependencies.jar `
#### Client (Have to run server first)
`java -jar Client/target/Client-1.0-SNAPSHOT-jar-with-dependencies.jar` 

# Text Reader Java Application

## Description
This is a simple Java Swing application that mimics a text reading interface, similar to an early 2000s AIM window. It uses a GUI where you can enter text into an input field. When the Enter key is pressed, the text message is timestamped and logged in a text area above the input field. The application also uses macOS's native `say` command to convert the text to speech, speaking it out loud in a voice of your choice.

## Features

- Simple and intuitive GUI built with Java Swing
- Real-time text logging with time stamps in local AM/PM format
- Text-to-speech functionality using macOS's native `say` command

## Requirements

- macOS with Java JDK installed (developed and tested on OpenJDK 20)
- Maven for building the project
- For text-to-speech, macOS's native `say` command is used, so the application is specific to macOS environments.

## Installation and Usage

### Building with Maven

This project uses Maven for dependency management and building. To build the project, navigate to the project directory in your terminal and run:

```bash
mvn clean package
This will generate an executable jar file in the target directory.

### Running the Application

After building the project, navigate to the `target` directory where the executable jar file is located. Run the following command to launch the application:

```bash
java -jar yourartifact-1.0.jar
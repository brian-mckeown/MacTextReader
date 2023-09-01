# Text Reader Java Application

## Description
This is a simple Java Swing application that mimics a text reading interface, similar to an early 2000s AIM window. It uses a GUI where you can enter text into an input field. When the Enter key is pressed, the text message is timestamped and logged in a text area above the input field. The application also uses macOS's native `say` command to convert the text to speech, speaking it out loud in a voice of your choice.

## Features

- Simple and intuitive GUI built with Java Swing
- Real-time text logging with time stamps in local AM/PM format
- Text-to-speech functionality using macOS's native `say` command

## Requirements

- macOS with Java JDK installed (developed and tested on OpenJDK 20)
- For text-to-speech, macOS's native `say` command is used, so the application is specific to macOS environments.

## Installation and Usage

1. Download the `TextReader.jar` file.
2. Double-click the jar file to run the application, or run `java -jar TextReader.jar` in your terminal.
3. A window will appear with a text input field at the bottom and an empty text area above it.
4. Type your message into the text field and press Enter.
5. The message will be logged with a timestamp above, and macOS's native `say` command will read the text out loud.

### Building with Maven

This project uses Maven for dependency management and building. To build the project, navigate to the project directory in your terminal and run:

```bash
mvn clean package

## Contributors

- [Brian McKeown](https://github.com/brian-mckeown)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

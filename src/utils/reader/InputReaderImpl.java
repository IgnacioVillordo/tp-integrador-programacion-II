package utils.reader;

import utils.logger.Logger;

import java.util.Scanner;

public class InputReaderImpl implements InputReader {
    private final Scanner scanner;
    private final Logger logger;

    public InputReaderImpl(Scanner scanner, Logger logger) {
        this.scanner = scanner;
        this.logger = logger;
    }

    @Override
    public String read() {
        return this.scanner.nextLine();
    }

    @Override
    public String updateValue(String currentValue, String message) {
        this.logger.println(message);
        this.logger.println("Valor actual: " + currentValue);
        this.logger.print("Nuevo valor (Enter para mantener): ");
        String newValue = scanner.nextLine();

        if (newValue.isBlank()) {
            newValue = currentValue;
        }
        return newValue;
    }

    @Override
    public String updateValue(String currentValue) {
        this.logger.println("Valor actual: " + currentValue);
        this.logger.print("Nuevo valor (Enter para mantener): ");
        String newValue = scanner.nextLine();

        if (newValue.isBlank()) {
            newValue = currentValue;
        }
        return newValue;
    }
}

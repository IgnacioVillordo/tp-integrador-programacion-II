package utils.reader;

public interface InputReader {
    String read();
    String updateValue(String currentValue, String message);
    String updateValue(String currentValue);
}

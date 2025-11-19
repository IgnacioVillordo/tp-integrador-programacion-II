package utils.logger;

import java.io.PrintStream;

public class LoggerImpl implements Logger {
    private final PrintStream out;

    public LoggerImpl(PrintStream out) {
        this.out = out;
    }

    public void println(Object... values) {
        for (Object v : values) {
            out.print(v);
        }
        out.println();
    }

    @Override
    public void print(Object... values) {
        for (Object v : values) {
            out.print(v);
        }
    }
}

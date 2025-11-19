package utils.logger;

public class LoggerImpl implements Logger {
    private final System sistem;

    public LoggerImpl(System sistem) {
        this.sistem = sistem;
    }

    public void println(Object... values) {
        for (Object v : values) {
            System.out.print(v);
        }
        System.out.println();
    }

    @Override
    public void print(Object... values) {
        for (Object v : values) {
            System.out.print(v);
        }
    }
}

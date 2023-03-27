package exercise;

public interface Connection {
    void connect();
    void write(String string);
    void disconnect();
    String getCurrentState();
}

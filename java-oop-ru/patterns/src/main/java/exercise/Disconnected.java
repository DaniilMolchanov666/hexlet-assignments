package exercise;

public class Disconnected implements Connection{

    TcpConnection tcp;

    public Disconnected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    @Override
    public void connect() {

    }

    @Override
    public void write(String string) {
        System.out.println("Error! Impossible to write when connection is disconnected!");
    }

    @Override
    public void disconnect() {

    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }
}

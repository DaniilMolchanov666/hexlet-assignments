package exercise;

public class Connected implements Connection{

    TcpConnection tcp;

    public Connected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    @Override
    public void connect() {

    }

    @Override
    public void write(String string) {
    }

    @Override
    public void disconnect() {
        
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }
}

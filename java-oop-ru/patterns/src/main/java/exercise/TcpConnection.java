package exercise;
import java.util.ArrayList;

class TcpConnection implements Connection{
    Connection connect;
    ArrayList<Connected> connectArray = new ArrayList<>();
    ArrayList<Disconnected> disconnectArray = new ArrayList<>();

    public TcpConnection(String ip, int port) {
    }

    public String getCurrentState() {
        return connect.getCurrentState();
    }

    @Override
    public void connect() {
        if(!connectArray.isEmpty()) {
            System.out.println("Error! Connection already connected!");

        }
        Connected connected = new Connected(this);
        connectArray.add(connected);
        this.connect = connected;
    }

    @Override
    public void write(String string) {
        connect.write(string);
    }

    @Override
    public void disconnect() {
        if(!disconnectArray.isEmpty()) {
            System.out.println("Error! Connection already disconnected!");

        }
        Disconnected dis = new Disconnected(this);
        disconnectArray.add(dis);
        this.connect = dis;
    }
}

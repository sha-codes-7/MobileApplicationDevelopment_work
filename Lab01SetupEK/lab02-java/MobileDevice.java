public class MobileDevice extends Device implements Connectable {
    private boolean connected;

    public MobileDevice(String name, int battery) {
        super(name, battery);
        this.connected = false;
    }

    @Override
    public void connect() {
        this.connected = true;
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public String summary() {
        return super.summary() + " | Connected: " + this.connected;
    }
}
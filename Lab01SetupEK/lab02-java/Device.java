public class Device {
    private final String name;
    private final int battery;

    public Device(String name, int battery) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Device name cannot be null or empty.");
        }
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("Battery level must be between 0 and 100.");
        }
        this.name = name;
        this.battery = battery;
    }

    public String getName() {
        return name;
    }

    public int getBattery() {
        return battery;
    }

    public String summary() {
        return name + " | Battery: " + battery + "%";
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static String batteryStatus(int battery) {
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("Battery level must be between 0 and 100.");
        }
        if (battery < 20) {
            return "LOW";
        } else {
            return "READY";
        }
    }

    public static double averageBattery(int[] readings) {
        if (readings == null || readings.length == 0) {
            throw new IllegalArgumentException("Readings array cannot be null or empty.");
        }
        double sum = 0;
        for (int b : readings) {
            if (b < 0 || b > 100) {
                throw new IllegalArgumentException("Battery reading out of range: " + b);
            }
            sum += b;
        }
        return sum / readings.length;
    }

    public static void main(String[] args) {
        System.out.println("=== CSC303 Lab 02 - Java Ready ===");

        // --- Task 2A: Worked Example ---
        System.out.println("\n--- Task 2A: Declarations and Arithmetic ---");
        String deviceName = "Sensor-A";
        int battery = 15;
        boolean connected = false;
        double ratio = 90 / 60.0;
        System.out.println(deviceName + " | battery=" + battery);
        System.out.println("Connected: " + connected);
        System.out.println("Ratio: " + ratio);
        System.out.println("Phone-B".equals(deviceName));

        // --- Task 2B & 2C: Processing Battery Readings ---
        System.out.println("\n--- Task 2B & 2C: Processing Battery Readings ---");
        int[] readings = {15, 80, 45};
        int lowCount = 0;

        for (int r : readings) {
            String status = batteryStatus(r);
            System.out.println("Reading: " + r + " -> Status: " + status);
            if ("LOW".equals(status)) {
                lowCount++;
            }
        }
        System.out.println("Number of LOW readings: " + lowCount);
        double avg = averageBattery(readings);
        System.out.printf(Locale.ROOT, "Average: %.2f%n", avg);

        // --- Task 3: Arrays and Collections ---
        System.out.println("\n--- Task 3: Arrays and Collections ---");
        List<String> names = new ArrayList<>();
        names.add("Sensor-A");
        names.add("Phone-B");
        names.add("Tablet-C");

        System.out.println("Initial string names:");
        for (String name : names) {
            System.out.println("  " + name);
        }
        System.out.println("names.size(): " + names.size());
        System.out.println("names.contains(\"Phone-B\"): " + names.contains("Phone-B"));

        names.remove("Tablet-C"); // Remove by value outside traversal loop
        System.out.println("After removing 'Tablet-C':");
        for (String name : names) {
            System.out.println("  " + name);
        }
        System.out.println("Remaining size: " + names.size());

        // --- Task 4: Classes and Encapsulation ---
        System.out.println("\n--- Task 4: Classes and Encapsulation ---");
        List<Device> devices = new ArrayList<>();
        devices.add(new Device("Sensor-A", 15));
        devices.add(new Device("Phone-B", 80));
        devices.add(new Device("Tablet-C", 45));

        System.out.println("Device list size: " + devices.size());
        for (Device d : devices) {
            System.out.println(d.summary() + " -> Status: " + batteryStatus(d.getBattery()));
        }

        // --- Task 5: Polymorphism & Interfaces ---
        System.out.println("\n--- Task 5: Inheritance and Interfaces ---");
        MobileDevice phone = new MobileDevice("Phone-B", 80);
        Connectable connection = phone;
        System.out.println("Is connected initially: " + connection.isConnected());
        connection.connect();
        System.out.println("Is connected after connect(): " + connection.isConnected());
        Device item = phone;
        System.out.println("Polymorphic summary: " + item.summary());

        // --- Task 7: Inventory Integration ---
        System.out.println("\n--- Task 7: Integrated Inventory Summaries ---");
        devices.set(1, phone); // Replace Phone-B with connected MobileDevice
        for (Device d : devices) {
            System.out.println(d.summary());
        }

        // --- Task 6: Executing Test Matrix (T1 - T8) ---
        System.out.println("\n--- Task 6: Executing Test Matrix (T1 - T8) ---");

        System.out.println("[T1] batteryStatus boundaries:");
        int[] t1Inputs = {0, 19, 20, 100};
        for (int val : t1Inputs) {
            System.out.println("  batteryStatus(" + val + ") = " + batteryStatus(val));
        }

        System.out.println("[T2] batteryStatus invalid inputs:");
        int[] t2Inputs = {-1, 101};
        for (int val : t2Inputs) {
            try {
                batteryStatus(val);
            } catch (IllegalArgumentException e) {
                System.out.println("  batteryStatus(" + val + ") threw IllegalArgumentException: " + e.getMessage());
            }
        }

        System.out.println("[T3] averageBattery valid array:");
        System.out.printf(Locale.ROOT, "  Average: %.2f%n", averageBattery(new int[]{15, 80, 45}));

        System.out.println("[T4] averageBattery invalid arrays:");
        try {
            averageBattery(new int[]{});
        } catch (IllegalArgumentException e) {
            System.out.println("  Empty array threw: " + e.getMessage());
        }
        try {
            averageBattery(null);
        } catch (IllegalArgumentException e) {
            System.out.println("  Null array threw: " + e.getMessage());
        }
        try {
            averageBattery(new int[]{15, 101});
        } catch (IllegalArgumentException e) {
            System.out.println("  Out-of-range array element threw: " + e.getMessage());
        }

        System.out.println("[T5] Valid Device Creation:");
        Device validDev = new Device("Sensor-A", 15);
        System.out.println("  Created: " + validDev.summary());

        System.out.println("[T6] Invalid Device Constructor inputs:");
        try { new Device(null, 50); } catch (IllegalArgumentException e) { System.out.println("  Null name: " + e.getMessage()); }
        try { new Device("  ", 50); } catch (IllegalArgumentException e) { System.out.println("  Blank name: " + e.getMessage()); }
        try { new Device("Dev", -1); } catch (IllegalArgumentException e) { System.out.println("  Battery -1: " + e.getMessage()); }
        try { new Device("Dev", 101); } catch (IllegalArgumentException e) { System.out.println("  Battery 101: " + e.getMessage()); }

        System.out.println("[T7] MobileDevice lifecycle:");
        MobileDevice md = new MobileDevice("Test-Phone", 50);
        System.out.println("  Initial state: " + md.isConnected());
        md.connect();
        System.out.println("  After 1st connect(): " + md.isConnected());
        md.connect();
        System.out.println("  After 2nd connect(): " + md.isConnected());

        System.out.println("[T8] String Parsing Loop:");
        String[] inputs = {"80", "abc", "120"};
        for (String text : inputs) {
            try {
                int value = Integer.parseInt(text);
                System.out.println("  '" + text + "' parsed successfully: " + batteryStatus(value));
            } catch (NumberFormatException ex) {
                System.out.println("  '" + text + "' -> NumberFormatException: enter a whole number");
            } catch (IllegalArgumentException ex) {
                System.out.println("  '" + text + "' -> IllegalArgumentException: " + ex.getMessage());
            }
        }
    }
}

public class VehicleManagement {
    public static void main(String[] args) {
        // Create an array of Vehicles with a mix of Cars and Trucks
        Vehicle[] vehicles = new Vehicle[4];
        vehicles[0] = new Car("Toyota", "Camry", 180, 5);
        vehicles[1] = new Truck("Ford", "F-150", 140, 10000);
        vehicles[2] = new Car("Honda", "Accord", 190, 5);
        vehicles[3] = new Truck("Volvo", "FH16", 120, 20000);

        // Display the details of all vehicles
        System.out.println("Original Vehicles:");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }

        // For vehicles that are insurable, calculate and display the insurance premium
        System.out.println("\nInsurance Premiums for Insurable Vehicles:");
        for (Vehicle v : vehicles) {
            if (v instanceof Insurable) {
                Insurable insurableVehicle = (Insurable) v;
                System.out.println(v.getMake() + " " + v.getModel() + " - Insurance: $" 
                    + insurableVehicle.calculateInsurance());
            }
        }

        // Extract Car objects from the vehicles array and sort them by max speed
        Car[] cars = java.util.Arrays.stream(vehicles)
                            .filter(v -> v instanceof Car)
                            .toArray(Car[]::new);
        java.util.Arrays.sort(cars);

        System.out.println("\nSorted Cars by Max Speed:");
        for (Car c : cars) {
            System.out.println(c);
        }
    }
}

// Abstract class representing a general vehicle
abstract class Vehicle {
    private String make;
    private String model;
    private double maxSpeed;

    public Vehicle(String make, String model, double maxSpeed) {
        this.make = make;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    // Abstract method to calculate fuel consumption (e.g., liters per 100 km)
    public abstract double getFuelConsumption();

    @Override
    public String toString() {
        return make + " " + model + " [Max Speed: " + maxSpeed 
            + " km/h, Fuel Consumption: " + getFuelConsumption() + " L/100km]";
    }
}

// Interface for vehicles that can be insured
interface Insurable {
    double calculateInsurance();
}

// Car class extends Vehicle, implements Comparable and Insurable
class Car extends Vehicle implements Comparable<Car>, Insurable {
    private int numberOfSeats;

    public Car(String make, String model, double maxSpeed, int numberOfSeats) {
        super(make, model, maxSpeed);
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public double getFuelConsumption() {
        // Dummy formula: base consumption decreased by more seats (for example purposes)
        return 8.0 - (numberOfSeats * 0.1);
    }

    // Calculate insurance premium based on max speed and number of seats
    @Override
    public double calculateInsurance() {
        return (getMaxSpeed() * 10) + (numberOfSeats * 50);
    }

    // Compare cars by their max speed
    @Override
    public int compareTo(Car other) {
        return Double.compare(this.getMaxSpeed(), other.getMaxSpeed());
    }

    @Override
    public String toString() {
        return "Car: " + getMake() + " " + getModel() + " [Max Speed: " 
            + getMaxSpeed() + " km/h, Seats: " + numberOfSeats 
            + ", Fuel Consumption: " + getFuelConsumption() + " L/100km]";
    }
}

// Truck class extends Vehicle
class Truck extends Vehicle {
    private double loadCapacity; // in kilograms

    public Truck(String make, String model, double maxSpeed, double loadCapacity) {
        super(make, model, maxSpeed);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public double getFuelConsumption() {
        // Dummy formula: trucks consume more fuel with higher load capacity
        return 15.0 + (loadCapacity / 10000);
    }

    @Override
    public String toString() {
        return "Truck: " + getMake() + " " + getModel() + " [Max Speed: " 
            + getMaxSpeed() + " km/h, Load Capacity: " + loadCapacity 
            + " kg, Fuel Consumption: " + getFuelConsumption() + " L/100km]";
    }
}

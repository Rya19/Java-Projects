public class CarDealership {
    public static void main(String[] args) {
        // Create an array of Car objects (inventory)
        Car[] inventory = new Car[3];
        
        // Instantiate Car objects with different details
        inventory[0] = new Car("Toyota", "Camry", 2020, 24000);
        inventory[1] = new Car("Ford", "Mustang", 2021, 35000);
        inventory[2] = new Car("Honda", "Civic", 2019, 20000);
        
        // Set features for each car
        inventory[0].setFeatureAt(0, "Bluetooth");
        inventory[0].setFeatureAt(1, "Sunroof");
        
        inventory[1].setFeatureAt(0, "Leather Seats");
        inventory[1].setFeatureAt(1, "GPS");
        inventory[1].setFeatureAt(2, "Sunroof");
        
        inventory[2].setFeatureAt(0, "Backup Camera");
        inventory[2].setFeatureAt(1, "Bluetooth");
        
        // Display information for each car in the inventory
        for (Car car : inventory) {
            car.displayCarInfo();
        }
        
        // Search for cars with a specific feature (e.g., "Sunroof")
        Car.searchByFeature(inventory, "Sunroof");
        
        // Display the total number of cars created
        System.out.println("Total number of cars: " + Car.getCarCount());
    }
}



public class Car {
    // Private instance variables
    private String make;
    private String model;
    private int year;
    private double price;
    private String[] features; // Array for features such as "Sunroof", "GPS", etc.
    
    // Private static variable to count Car objects
    private static int carCount = 0;
    
    // Constructor
    public Car(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.features = new String[4];  // Allow up to 4 features per car
        carCount++;  // Increment car count for each new car
    }
    
    // Getters and Setters for make, model, year, and price
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Getter for features array
    public String[] getFeatures() {
        return features;
    }
    
    // Method to set a feature at a specific index
    public void setFeatureAt(int index, String feature) {
        if (index >= 0 && index < features.length) {
            features[index] = feature;
        } else {
            System.out.println("Index out of bounds for features array.");
        }
    }
    
    // Instance method to display car information
    public void displayCarInfo() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: $" + price);
        System.out.print("Features: ");
        for (String feature : features) {
            if (feature != null) {
                System.out.print(feature + " ");
            }
        }
        System.out.println("\n");
    }
    
    // Static method to get the total number of Car objects created
    public static int getCarCount() {
        return carCount;
    }
    
    // Static method to search for cars with a specific feature
    public static void searchByFeature(Car[] cars, String searchFeature) {
        System.out.println("Cars with feature '" + searchFeature + "':");
        boolean found = false;
        for (Car car : cars) {
            // Loop through each car's features array
            for (String feature : car.features) {
                if (feature != null && feature.equalsIgnoreCase(searchFeature)) {
                    car.displayCarInfo();
                    found = true;
                    break;  // Stop checking other features for this car
                }
            }
        }
        if (!found) {
            System.out.println("No cars found with feature '" + searchFeature + "'.");
        }
    }
}

import java.util.ArrayList;
import java.util.Collections;

public class Project01Q2 {

    public static void main(String[] args) {
        ArrayList<Ship> Ship_Array = new ArrayList<>();
        Ship_Array.add(new CruiseShip("Disney Magic", "1998", 2400));

        Ship_Array.add(new CargoShip("Black Pearl", "1800", 50000));

        Ship_Array.add(new TankerShip("Lolipop", "1960", "Oil", 500000.0));

        for (Ship ships : Ship_Array) {
            System.out.println(ships);
            System.out.println("----------------------------");
        }

        // Before Sorting the CruiseShip objects
        ArrayList<CruiseShip> cruiseShips = new ArrayList<>();
        cruiseShips.add(new CruiseShip("Disney Magic1", "1998", 2400));

        cruiseShips.add(new CruiseShip("Disney Magic2", "2002", 2500));

        cruiseShips.add(new CruiseShip("Disney Magic3", "2011", 400));

        System.out.println("Before sorting three objects of CriseShips:");
        for (CruiseShip cs : cruiseShips) {
            System.out.println(cs);
        }

        Collections.sort(cruiseShips);

        // After Sorting the CruiseShip objects
        System.out.println("\nAfter sorting three objects of CriseShips:");
        for (CruiseShip cs : cruiseShips) {
            System.out.println(cs);
        }
    }
}

class Ship {
    //priv
    private String Ship_Name;
    private String Ship_Year;

    //pub
    public Ship(String Ship_Name, String Ship_Year) {
        this.Ship_Name = Ship_Name;
        this.Ship_Year = Ship_Year;
    }

    public String getShip_Name() { 
        return Ship_Name; 
    }
    public String getShip_Year() { 
        return Ship_Year; 
    }

    @Override
    public String toString() {
        return "Ship_Name: " + Ship_Name + "\nShip_Year: " + Ship_Year;
    }
}


class CruiseShip extends Ship implements Comparable<CruiseShip> {
    //priv
    private int Max_Passengers;

    //pub
    public CruiseShip(String Ship_Name, String Ship_Year, int Max_Passengers) {
        super(Ship_Name, Ship_Year);
        this.Max_Passengers = Max_Passengers;
    }

    public void setMaxPassengers(int Max_Passengers) {
        this.Max_Passengers = Max_Passengers; 
    }
    public int getMaxPassengers() { 
        return Max_Passengers; 
    }

    @Override
    public String toString() {
        return super.toString() + "\nMaximum passengers: " + Max_Passengers;
    }

    @Override
    public int compareTo(CruiseShip other) {
        return Integer.compare(this.Max_Passengers, other.Max_Passengers);
    }
}


class CargoShip extends Ship implements Comparable<CargoShip> {
    //priv
    private int Ship_Capacity;

    //pub
    public CargoShip(String Ship_Name, String Ship_Year, int Ship_Capacity) {
        super(Ship_Name, Ship_Year);
        this.Ship_Capacity = Ship_Capacity;
    }

    public void setCapacity(int Ship_Capacity) { 
        this.Ship_Capacity = Ship_Capacity; 
    }
    public int getCapacity() { 
        return Ship_Capacity; 
    }


    @Override
    public String toString() {
        return super.toString() + "\nCargo Ship_Capacity: " + Ship_Capacity + " tons";
    }

    @Override
    public int compareTo(CargoShip other) {
        return Integer.compare(this.Ship_Capacity, other.Ship_Capacity);
    }
}

class TankerShip extends Ship implements Comparable<TankerShip> {
    //priv
    private String liquidType;
    private double Ship_Capacity;

    //pub
    public TankerShip(String Ship_Name, String Ship_Year, String liquidType, double Ship_Capacity) {
        super(Ship_Name, Ship_Year);
        this.liquidType = liquidType;
        this.Ship_Capacity = Ship_Capacity;
    }

    public void setLiquidType(String liquidType) { 
        this.liquidType = liquidType; 
    }
    public String getLiquidType() { 
        return liquidType; 
    }

    public void setCapacity(double Ship_Capacity) { 
        this.Ship_Capacity = Ship_Capacity; 
    }
    public double getCapacity() { 
        return Ship_Capacity; 
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: " + liquidType + "\nCapacity: " + Ship_Capacity;
    }

    @Override
    public int compareTo(TankerShip other) {
        return this.liquidType.compareTo(other.liquidType);
    }
}
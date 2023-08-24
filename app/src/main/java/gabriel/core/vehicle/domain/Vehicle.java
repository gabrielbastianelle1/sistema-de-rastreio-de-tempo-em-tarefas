package gabriel.core.vehicle.domain;

public class Vehicle {
    private final String plate;
    private final String brand;
    private final String model;
    private final int year;

    public Vehicle(String plate, String brand, String model, int year) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "{plate: " + plate + ", brand: " + brand + ", model: " + model + ", year: " + year + "}";
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((plate == null) ? 0 : plate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        if (plate == null) {
            if (other.plate != null)
                return false;
        } else if (!plate.equals(other.plate))
            return false;
        return true;
    }
}

package gabriel.core.user.interfaces;

import java.util.Collection;

import gabriel.core.vehicle.domain.Vehicle;
import gabriel.core.vehicle.repository.VehicleRepository;

public abstract class ListVehicleAbstraction {
    protected final VehicleRepository vehicleRepository;

    protected ListVehicleAbstraction(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public abstract Collection<Vehicle> listVehicles();

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

}

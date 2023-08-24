package gabriel.core.user.usecases;

import java.util.Collection;

import gabriel.core.user.interfaces.ListVehicleAbstraction;
import gabriel.core.vehicle.domain.Vehicle;
import gabriel.core.vehicle.repository.VehicleRepository;

public class ListVehicles extends ListVehicleAbstraction {

    public ListVehicles(VehicleRepository vehicleRepository) {
        super(vehicleRepository);
    }

    @Override
    public Collection<Vehicle> listVehicles() {
        return this.vehicleRepository.findAll();
    }

}

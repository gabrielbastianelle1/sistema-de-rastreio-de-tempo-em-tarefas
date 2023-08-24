package gabriel.core.vehicle.repository;

import java.util.Collection;

import gabriel.core.vehicle.domain.Vehicle;

public interface VehicleRepository {
    public Collection<Vehicle> findAll();
}

package gabriel.user;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gabriel.core.user.interfaces.ListVehicleAbstraction;
import gabriel.core.user.usecases.ListVehicles;
import gabriel.core.vehicle.domain.Vehicle;
import gabriel.core.vehicle.repository.VehicleRepository;

public class ListVehiclesTest {

    private final VehicleRepository vehicleRepository = mock(VehicleRepository.class);
    private final ListVehicleAbstraction listVehicleAbstraction = mock(ListVehicleAbstraction.class,
            withSettings().useConstructor(this.vehicleRepository));

    @BeforeEach
    public void initAll() {
        when(this.vehicleRepository.findAll()).thenReturn(new ArrayList<Vehicle>() {
            {
                add(new Vehicle("xxx-999", "ford", "ka", 2000));
            }
        });
    }

    // @Test
    // public void testListVehicles_JustMocks() {
    // ListVehicleAbstraction abs = mock(ListVehicleAbstraction.class,
    // withSettings().useConstructor(this.vehicleRepository));

    // when(abs.listVehicles()).thenReturn(new ArrayList<Vehicle>() {
    // {
    // add(new Vehicle("xxx-999", "ford", "ka", 2000));
    // }
    // });

    // Vehicle[] actual = abs.listVehicles().toArray(new Vehicle[0]);
    // Vehicle[] expected = new Vehicle[] { new Vehicle("xxx-999", "ford", "ka",
    // 2000) };

    // assertArrayEquals(expected, actual);
    // }

    @Test
    public void testListVehicles_RealClass() {
        ListVehicles listVehicles = new ListVehicles(vehicleRepository);

        Vehicle[] actual = listVehicles.listVehicles().toArray(new Vehicle[0]);
        Vehicle[] expected = new Vehicle[] { new Vehicle("xxx-999", "ford", "ka", 2000) };

        assertArrayEquals(expected, actual);
    }

    // @Test
    // public void testListVehicles_EmptyArray() {
    // Collection<Vehicle> vehicles = vehicleRepository.findAll();

    // Vehicle[] actual = vehicles.toArray(new Vehicle[0]);
    // Vehicle[] expected = new Vehicle[] {};

    // assertArrayEquals(expected, actual);
    // }

}

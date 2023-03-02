package com.prasanna.parkinglot;

import com.prasanna.parkinglot.exception.CarNotFoundExceptions;
import com.prasanna.parkinglot.exception.NoSlotsAvailableException;
import com.prasanna.parkinglot.model.Car;
import com.prasanna.parkinglot.model.ParkingLot;
import com.prasanna.parkinglot.model.Slot;
import com.prasanna.parkinglot.service.ParkingLotService;
import com.prasanna.parkinglot.strategy.DefaultParkingStrategy;
import com.prasanna.parkinglot.strategy.ParkingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingLotApplicationTests {
	ParkingLotService parkingLotService;
	@BeforeEach
	void setUp() {
	 ParkingLot parkingLot = new ParkingLot(100);
	 ParkingStrategy parkingStrategy = new DefaultParkingStrategy();
	 parkingLotService = new ParkingLotService(parkingLot, parkingStrategy);
	 parkingLotService.createParkingLot(10);
	}

	@Test
	void testParkingLotFlow(){
		Slot slot1 = parkingLotService.parkCar(new Car("TN 43 1234", "black"));
		Slot slot2 = parkingLotService.parkCar(new Car("TN 42 1233", "black"));
		Slot slot3 = parkingLotService.parkCar(new Car("TN 41 2234", "blue"));
		Slot slot4 = parkingLotService.parkCar(new Car("TN 56 4233", "blue"));

		Assertions.assertEquals(2, parkingLotService.getSlotForGivenColor("blue").size());

		parkingLotService.unParkCar(slot3.getSlotNumber());

		Assertions.assertEquals(1, parkingLotService.getSlotForGivenColor("blue").size());
        Assertions.assertEquals(2, parkingLotService.getSlotForGivenColor("black").size());

		Assertions.assertEquals(slot4, parkingLotService.getSlotForRegisterNumber("TN 56 4233"));
		Assertions.assertEquals(slot1, parkingLotService.getSlotForRegisterNumber("TN 43 1234"));

		Assertions.assertThrows(CarNotFoundExceptions.class ,() -> parkingLotService.getSlotForRegisterNumber("TN 56 423"));
	}

}

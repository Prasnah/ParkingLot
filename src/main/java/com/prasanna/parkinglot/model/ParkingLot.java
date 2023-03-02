package com.prasanna.parkinglot.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public class ParkingLot {
    private static final Integer MAXIMUM_CAPACITY = 10000;

    private int capacity;
    Map<Integer, Slot> parkingLotRepo;

    public ParkingLot(int capacity) {
        if (capacity <= 0 || capacity > MAXIMUM_CAPACITY) {
            // Invalid capacity exception
        }
        this.capacity = capacity;
        parkingLotRepo = new HashMap<>();
    }

    private Slot assignCar(final Integer slotNumber, final Car car) {
        if (slotNumber > this.capacity) {
            // exception
        }
        Map<Integer, Slot> allSlots = getSlots();
        if (!allSlots.containsKey(slotNumber)) {
            // exeption
        }
        Slot slot = new Slot(slotNumber, car);
        slot.setAvailability(false);
        slot.setParkedCar(car);
        allSlots.put(slotNumber, slot);
        return slot;
    }

    private void unassignCar(final Integer slotNumber) {
        if (slotNumber > this.capacity) {
            // exception
        }
        Map<Integer, Slot> allSlots = getSlots();
        if (!allSlots.containsKey(slotNumber)) {
            // exeption
        }
        Slot slot = allSlots.get(slotNumber);
        slot.setAvailability(true);
        slot.setParkedCar(null);
    }

    public Map<Integer, Slot> getSlots() {
        return parkingLotRepo;
    }
}

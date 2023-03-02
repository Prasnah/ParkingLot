package com.prasanna.parkinglot.service;

import com.prasanna.parkinglot.exception.CarNotFoundExceptions;
import com.prasanna.parkinglot.exception.InvalidSlotNumberException;
import com.prasanna.parkinglot.exception.NoSlotsAvailableException;
import com.prasanna.parkinglot.exception.SlotNotFoundException;
import com.prasanna.parkinglot.model.Car;
import com.prasanna.parkinglot.model.ParkingLot;
import com.prasanna.parkinglot.model.Slot;
import com.prasanna.parkinglot.strategy.ParkingStrategy;
import lombok.AllArgsConstructor;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ParkingLotService {

    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final int capacity) {
        if (parkingLot.getCapacity() < capacity) {
            throw new InvalidSlotNumberException();
        }
        for (int i = 0; i < capacity; i++) {
            parkingLot.getParkingLotRepo().put(i + 1, new Slot(i + 1, null));
        }
    }

    public Slot parkCar(final Car car) {
        Map<Integer, Slot> allSlots = parkingLot.getSlots();
        List<Slot> allAvailableSlots = allSlots.values()
                .stream()
                .filter(Slot::isAvailability)
                .filter(slot -> slot.getParkedCar() == null)
                .toList();
        Slot slot = parkingStrategy.parkingStrategy(allAvailableSlots);
        if (slot == null) {
            throw new NoSlotsAvailableException();
        }
        slot.setAvailability(false);
        slot.setParkedCar(car);

        allSlots.put(slot.getSlotNumber(), slot);
        return slot;
    }

    public void unParkCar(final Integer slotNumber) {
        Map<Integer, Slot> allSlots = parkingLot.getSlots();
        if (!allSlots.containsKey(slotNumber)) {
            throw new SlotNotFoundException();
        }
        Slot slot = allSlots.get(slotNumber);
        if (slot == null) {
            throw new NoSlotsAvailableException();
        }
        slot.setParkedCar(null);
        slot.setAvailability(true);
    }

    public Slot getSlotForRegisterNumber(final String regNumber) {
        Map<Integer, Slot> allSlots = parkingLot.getSlots();
        Optional<Slot> slotForRegNo = allSlots.values().stream()
                .filter(slot -> slot.getParkedCar() != null)
                .filter(slot -> slot.getParkedCar().getCarNo().equals(regNumber))
                .findFirst();
        if (!slotForRegNo.isPresent()) {
            throw new CarNotFoundExceptions();
        }
        return slotForRegNo.get();
    }

    public List<Slot> getSlotForGivenColor(String color) {
        Map<Integer, Slot> allSlots = parkingLot.getSlots();
        return allSlots.values().stream()
                .filter(slot -> slot.getParkedCar() != null)
                .filter(slot -> slot.getParkedCar().getCarColor().equals(color))
                .toList();
    }
}

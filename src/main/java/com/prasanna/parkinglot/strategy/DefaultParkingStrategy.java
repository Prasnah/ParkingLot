package com.prasanna.parkinglot.strategy;

import com.prasanna.parkinglot.model.Slot;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public Slot parkingStrategy(List<Slot> allSlots) {
        if (allSlots.size() == 0) {
            // exception
        }
        return allSlots.get(0);
    }
}

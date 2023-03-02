package com.prasanna.parkinglot.strategy;

import com.prasanna.parkinglot.model.Slot;

import java.util.List;

public interface ParkingStrategy {

    public Slot parkingStrategy(List<Slot> allSlots);
}

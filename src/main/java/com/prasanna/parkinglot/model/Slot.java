package com.prasanna.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Slot {
    private Integer slotNumber;
    @Setter
    private Car parkedCar;

    @Setter
    private boolean availability;

    public Slot(final Integer slotNumber, final Car parkedCar) {
        this.slotNumber = slotNumber;
        this.parkedCar = parkedCar;
        this.availability = true;
    }
}

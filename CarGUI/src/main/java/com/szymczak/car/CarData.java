package com.szymczak.car;

public record CarData(
        // Car Data
        String name,
        String regNumber,
        int weight,
        int maxSpeed,
        Position position,

        // Gearbox Data
        String gearboxName,
        int gearboxPrice,
        int gearboxWeight,
        int gear,

        // Engine Data
        String engineName,
        int enginePrice,
        int engineWeight,
        int engineRevs,

        // Clutch Data
        String clutchName,
        int clutchPrice,
        int clutchWeight,
        boolean isClutchPressed
) {
}

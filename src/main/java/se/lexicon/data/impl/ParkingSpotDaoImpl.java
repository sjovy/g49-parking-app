package se.lexicon.data.impl;

import se.lexicon.data.ParkingSpotDao;
import se.lexicon.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao{

    private List<ParkingSpot> storage = new ArrayList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if (parkingSpot == null) throw new IllegalArgumentException("Parking spot Data is null.");
        Optional<ParkingSpot> parkingSpotOptional = find(parkingSpot.getSpotNumber());
        if (parkingSpotOptional.isPresent()) throw new IllegalArgumentException("Parking spot is duplicate");
        storage.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        for (ParkingSpot parkingSpot : storage) {
            if (parkingSpot.getSpotNumber() == spotNumber) {
                return Optional.of(parkingSpot);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
        if (!parkingSpotOptional.isPresent()) return false;
        storage.remove(parkingSpotOptional.get());
        return true;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        List<ParkingSpot> areaSpots = new ArrayList<>();
        for (ParkingSpot parkingSpot : storage) {
            if (parkingSpot.getAreaCode() == areaCode) {
                areaSpots.add(parkingSpot);
                return areaSpots;
            }
        }
        return null;
    }

    @Override
    public void occupyParkingSpot(int spotNumber) {
    Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
    if (!parkingSpotOptional.isPresent()) throw new IllegalArgumentException("Parking spot not found.");
    ParkingSpot foundParkingSpot = parkingSpotOptional.get();
    foundParkingSpot.occupy();
    }

    @Override
    public void vacateParkingSpot(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
        if (!parkingSpotOptional.isPresent()) throw new IllegalArgumentException("Parking spot not found.");
        ParkingSpot foundParkingSpot = parkingSpotOptional.get();
        if (!foundParkingSpot.isOccupied()) throw new IllegalStateException("Parking spot is already vacant.");
        foundParkingSpot.vacate();
    }
}

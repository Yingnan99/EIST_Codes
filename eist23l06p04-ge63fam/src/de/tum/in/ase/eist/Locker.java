package de.tum.in.ase.eist;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import static de.tum.in.ase.eist.ThreadUtils.takeSomeTime;

public class Locker {
    private static int counter = 0;
    private final int id;
    private Optional<Swimmer> occupant;
    private final ReentrantLock mutex;

    public Locker() {
        this.id = counter++;
        this.occupant = Optional.empty();
        this.mutex = new ReentrantLock();
    }

    // TODO 1
    public void storeClothes(Swimmer newOccupant) {
        // TODO 1
        // Acquire the lock
        this.mutex.lock();

        // Simulate some processing time
        takeSomeTime();

        // Set the new occupant
        this.occupant = Optional.of(newOccupant);

        System.out.printf("Swimmer %d has stored his/her clothes in locker %d\n", this.occupant.get().getId(), this.id);
    }


    public void retrieveClothes() {
        // TODO 1
        System.out.printf("Swimmer %d has retrieved his/her clothes from locker %d\n", this.occupant.get().getId(), this.id);

        // Clear the occupant
        this.occupant = Optional.empty();

        // Release the lock
        this.mutex.unlock();
    }

    public Optional<Swimmer> getOccupant() {
        return occupant;
    }

    public ReentrantLock getMutex() {
        return mutex;
    }
}

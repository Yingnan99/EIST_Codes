package de.tum.in.ase.eist.orderCooking;

import java.util.concurrent.Semaphore;

public class Buffer {
    private PartyMenu[] data;
    private Semaphore free;
    private Semaphore occupied;
    int first = 0, last = 0;

    public Buffer(int maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(
                    "Buffer must have positive size!");
        // TODO 3.1 Initialize the variables
        data = new PartyMenu[maxSize];
        free = new Semaphore(maxSize);
        occupied = new Semaphore(0);
    }

    public PartyMenu consume() throws InterruptedException {
        // TODO 3.2 Implement the consume method as described
        PartyMenu result;
        occupied.acquire();
        synchronized (this) {
            result = data[first];
            first = (first + 1) % data.length;
        }
        free.release();
        return result;
    }

    public void produce(PartyMenu partyMenu) throws InterruptedException {
        // TODO 3.3 Implement the produce method as described
        free.acquire();
        synchronized (this) {
            data[last] = partyMenu;
            last = (last + 1) % data.length;
        }
        occupied.release();
    }

    public PartyMenu[] getData() {
        return data;
    }
}

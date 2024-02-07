package de.tum.in.ase.eist.push;

public final class WeatherMainView implements Observer {

    private final WeatherController controller;
    private final WeatherModel model;

    public WeatherMainView(WeatherController controller, WeatherModel model) {
        this.controller = controller;
        this.model = model;
        // TODO: subscribe this view to the updates from model
    }

    public void display() {
        // TODO come up with a funny message
    }

    public void update(final int temperature) {
        // TODO: implement update state function
    }
}

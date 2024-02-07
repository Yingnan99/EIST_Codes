package de.tum.in.ase.eist;

public class ThermoAdapter implements ThermoInterface {
    private FahrenheitThermo thermo;

    public ThermoAdapter() {
        this.thermo = new FahrenheitThermo();
    }

    public ThermoAdapter(FahrenheitThermo thermo) {
        this.thermo = thermo;
    }

    public double getTempC() {
        return (thermo.getFahrenheitTemperature() - 32.0) * 5.0 / 9.0;
    }

    public FahrenheitThermo getThermo() {
        return thermo;
    }

    public void setThermo(FahrenheitThermo thermo) {
        this.thermo = thermo;
    }
}

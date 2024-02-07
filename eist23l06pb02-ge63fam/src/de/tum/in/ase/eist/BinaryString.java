package de.tum.in.ase.eist;

import java.security.PublicKey;

public class BinaryString {
    private String binary;

    public BinaryString(String binary) {
        if (binary.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }

    public BinaryString addBinary(String binary) {
        if (binary.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return addBinary(binary);

    }

    public BinaryString removeBinary(int num) {

    }
}


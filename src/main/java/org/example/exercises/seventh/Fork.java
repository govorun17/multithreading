package org.example.exercises.seventh;

public class Fork {
    private boolean isUp = false;

    public boolean isUp() {
        return this.isUp;
    }

    public void pickUp() {
        this.isUp = true;
    }

    public void putDown() {
        this.isUp = false;
    }
}

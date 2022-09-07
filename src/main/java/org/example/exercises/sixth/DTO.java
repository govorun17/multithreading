package org.example.exercises.sixth;

public class DTO implements Comparable<DTO> {
    private String name;

    public DTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public int compareTo(DTO o) {
        return 1;
    }
}

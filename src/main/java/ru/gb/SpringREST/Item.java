package ru.gb.SpringREST;

import lombok.Getter;

@Getter
public class Item {
    private int index;
    private String name;

    public Item(int index, String name) {
        this.index = index;
        this.name = name;
    }
}

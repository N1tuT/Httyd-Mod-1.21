package net.nitu.httydmod.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum HobgoblinVariant {
    PURPLE(0),
    BLUE(1);


    private static final HobgoblinVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(HobgoblinVariant::getId)).toArray(HobgoblinVariant[]::new);

    private final int id;

    HobgoblinVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HobgoblinVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

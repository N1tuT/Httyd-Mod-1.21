package net.nitu.httydmod.entity.custom.hobgobbler;

import java.util.Arrays;
import java.util.Comparator;

public enum HobgobblerVariant {
    PURPLE(0),
    BLUE(1);


    private static final HobgobblerVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(HobgobblerVariant::getId)).toArray(HobgobblerVariant[]::new);

    private final int id;

    HobgobblerVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HobgobblerVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

package net.nitu.httydmod.entity.custom.slug;

import java.util.Arrays;
import java.util.Comparator;

public enum SlugVariant {
    BROWN(0),
    RED(1),
    GREEN(2);

    private static final SlugVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SlugVariant::getId)).toArray(SlugVariant[]::new);

    private final int id;

    SlugVariant(int id) { this.id = id; }

    public int getId() { return id; }

    public static SlugVariant byId(int id) { return BY_ID[id % BY_ID.length]; }
}

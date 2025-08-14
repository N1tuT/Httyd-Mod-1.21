package net.nitu.httydmod.entity.custom.nightterror;

import java.util.Arrays;
import java.util.Comparator;

public enum NightTerrorVariant {
    BLACK(0),
    WHITE(1);

    private static final NightTerrorVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(NightTerrorVariant::getId)).toArray(NightTerrorVariant[]::new);

    private final int id;

    NightTerrorVariant(int id) { this.id = id; }

    public int getId() { return id; }

    public static final NightTerrorVariant byId(int id) { return BY_ID[id % BY_ID.length]; }
}

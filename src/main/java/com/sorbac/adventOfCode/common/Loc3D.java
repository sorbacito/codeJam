package com.sorbac.adventOfCode.common;

public record Loc3D(long x, long y, long z) {
    public Loc3D move(Loc3D other) {
        return move(other.x, other.y, other.z);
    }

    public Loc3D move(long dx, long dy, long dz) {
        return new Loc3D(x + dx, y + dy, z + dz);
    }
}

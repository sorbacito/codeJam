package com.sorbac.leetcode.easy.binSearch.firstBadVersion;

public class VersionControl {
    protected final int badVersion;

    public VersionControl(int badVersion) {
        this.badVersion = badVersion;
    }

    protected boolean isBadVersion(int i) {
        return i >= badVersion;
    }
}

package com.sorbac.leetcode.easy.binSearch.firstBadVersion;

public class Solution extends VersionControl{
    public Solution(int badVersion) {
        super(badVersion);
    }

    public static void main(String[] args) {
        new Solution(1702766719).firstBadVersion(2126753390);
    }

    public int firstBadVersion(int n) {
        long left = 1;
        long right = n;
        while(left <= right) {
            int mid = (int)((left + right)/2);
            if(isBadVersion(mid)) {
                if(!isBadVersion(mid - 1)) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

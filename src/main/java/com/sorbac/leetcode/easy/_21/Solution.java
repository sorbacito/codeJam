package com.sorbac.leetcode.easy._21;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode current = null;
        while (list1 != null || list2 != null) {
            int curVal;
            if (list1 == null) {
                curVal = list2.val;
                list2 = list2.next;
            } else if (list2 == null) {
                curVal = list1.val;
                list1 = list1.next;
            } else {
                if (list1.val < list2.val) {
                    curVal = list1.val;
                    list1 = list1.next;
                } else {
                    curVal = list2.val;
                    list2 = list2.next;
                }
            }
            if (current == null) {
                current = new ListNode(curVal);
                head = current;
            } else {
                current.next = new ListNode(curVal);
                current = current.next;
            }
        }
        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

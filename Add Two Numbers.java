/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private ListNode getMeNumber(ListNode l1, ListNode l2){
        // to convert into doubly linked list
        LinkedList<Integer> list1 = new LinkedList();
        LinkedList<Integer> list2 = new LinkedList();
        // to store the sum
        LinkedList<Integer> sumList = new LinkedList();
        // reverse of sum
        ListNode head = null; 
        ListNode last = null; 
        ListNode node1 = l1;
        ListNode node2 = l2;
        
        // node1 and node2 both are running together
        while(node1 != null && node2 != null){
            list1.add(node1.val);
            list2.add(node2.val);
            node1 = node1.next;
            node2 = node2.next;
        }
        // if node2 becomes null
        while(node1 != null){
            list1.add(node1.val);
            node1 = node1.next;
        }
        // if node1 becomes null
        while(node2 != null){
            list2.add(node2.val);
            node2 = node2.next;
        }
        
        // reversing the doubly linked list
        Collections.reverse(list1);
        Collections.reverse(list2);
        
        // to traverse in reverse order
        Iterator itr1 = list1.descendingIterator();
        Iterator itr2 = list2.descendingIterator();
        // it will tell if there is any carryover
        boolean carryOver = false;
        int sum = 0;

        while(itr1.hasNext() && itr2.hasNext()){
            int n1 = (Integer) itr1.next(); 
            int n2 = (Integer) itr2.next();
            sum = n1 + n2;
            sum = (carryOver == true) ? sum + 1 : sum;
            sumList.addFirst(sum % 10);
            carryOver = (sum / 10) == 0 ? false : true;
        }

        sum = 0;

        // if list2 becomes null
        while(itr1.hasNext()){
            sum = (Integer) itr1.next();
            sum = (carryOver == true) ? sum + 1 : sum;
            sumList.addFirst(sum % 10);
            carryOver = (sum / 10) == 0 ? false : true;
        }

        // if list1 becomes null
        while(itr2.hasNext()){
            sum = (Integer) itr2.next();
            sum = (carryOver == true) ? sum + 1 : sum;
            sumList.addFirst(sum % 10);
            carryOver = (sum / 10) == 0 ? false : true;
        }

        // to handle the edge case 
        if(itr1.hasNext() == false || itr2.hasNext() == false){
            if(carryOver == true)
                sumList.addFirst(1);
        }

        // reversing the sum
        itr1 = sumList.descendingIterator();

        while(itr1.hasNext()){
            int n = (Integer) itr1.next();
            ListNode temp = new ListNode(n, null);

            if(head == null){
                head = last = temp;
                continue;
            }

            last.next = temp;
            last = last.next;
        }

        return head;

    }
 
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return getMeNumber(l1, l2);
     

        
    }
}

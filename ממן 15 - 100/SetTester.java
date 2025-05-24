public class SetTester {
    public static void main(String[] args) {
        Set s1 = new Set();
        Set s2 = new Set();
        
        /* constructor*/
        System.out.print("Testing numOfElements: ");
        System.out.println("New set should have zero elements: " + getOutputText(s1.numOfElements() == 0));
        s1.addToSet(1);
        System.out.println("After adding one element, the set should have exactly one element: " + getOutputText(s1.numOfElements() == 1));
        
        /* addToSet */
        System.out.print("Testing addToSet: ");
        s1 = new Set();
        s1.addToSet(7);
        s1.addToSet(7);
        s1.addToSet(6);
        s1.addToSet(-1);
        System.out.println("Check that only positive odd numbers are added to the set without duplications: " + getOutputText(s1.numOfElements() == 1));
        
        /* isEmpty */
        System.out.print("Testing isEmpty: ");
        s1 = new Set();
        System.out.println("Check if new set is empty: " + getOutputText(s1.isEmpty()));
        s1.addToSet(5);
        System.out.println("Check that the set isn't empty after adding an element: " + getOutputText(!s1.isEmpty()));

        /* isMember */
        System.out.print("Testing isMember: ");
        s1 = new Set();
        s1.addToSet(5);
        System.out.println("Check that the set {5} doesn't contain the element 0: " + getOutputText(!s1.isMember(0)));
        System.out.println("Check that the set {5} contains the element 5: " + getOutputText(s1.isMember(5)));

        /* removeFromSet */
        System.out.print("Testing removeFromSet: ");
        s1 = new Set();
        s1.addToSet(5);
        s1.addToSet(3);
        s1.removeFromSet(5);
        System.out.println("Set {3,5} after removing element 5 should be {3}. Yours is: " + s1 + " - " + getOutputText("{3}".equals(s1.toString())));
        s1 = new Set();
        s1.addToSet(5);
        s1.addToSet(3);
        s1.removeFromSet(1);
        System.out.println("Set {3,5} after removing element 1 - " + getOutputText("{3,5}".equals(s1.toString()) || "{5,3}".equals(s1.toString())));

        /* equals */
        System.out.print("Testing equals: ");
        s1 = new Set();
        s1.addToSet(3);
        s1.addToSet(5); 
        s2 = new Set();
        s2.addToSet(5);
        s2.addToSet(3);
        System.out.println("Sets " + s1 + " and " + s2 + " should be equal: " + getOutputText(s1.equals(s2)));

        /* subSet */
        System.out.print("Testing subSet: ");
        s1 = new Set();
        s1.addToSet(1);
        s1.addToSet(3);
        s1.addToSet(9);
        s1.addToSet(17);
        s2 = new Set();
        s2.addToSet(1);
        System.out.println("Set " + s2 + " should be a sub set of set " + s1 + ": " + getOutputText(s1.subSet(s2))); 

        /* intersection */
        System.out.print("Testing intersection: ");
        s1 = new Set();
        s1.addToSet(1);
        s1.addToSet(3);
        s2 = new Set();
        s2.addToSet(7);
        s2.addToSet(3);
        Set s3 = s1.intersection(s2);
        System.out.println("Intersection between sets " + s1 + " and " + s2 + " should be: {3} - " + getOutputText("{3}".equals(s3.toString())));

        /* union */
        System.out.print("Testing union: ");
        s1 = new Set();
        s1.addToSet(1);
        s1.addToSet(3);
        s2 = new Set();
        s2.addToSet(9);
        s2.addToSet(3);
        s3 = s1.union(s2);
        System.out.println("union between sets " + s1 + " and " + s2 + " - " + getOutputText("{1,3,9}".equals(s3.toString()) || "{1,9,3}".equals(s3.toString()) ||
            "{3,1,9}".equals(s3.toString()) || "{3,9,1}".equals(s3.toString()) || "{9,1,3}".equals(s3.toString()) || "{9,3,1}".equals(s3.toString())));

        /* difference */
        System.out.print("Testing difference: ");
        s1 = new Set();
        s1.addToSet(1);
        s1.addToSet(3);
        s2 = new Set();
        s2.addToSet(1);
        s2.addToSet(7);
        s2.addToSet(3);
        s3 = s1.difference(s2);
        System.out.println("difference between sets " + s1 + " and " + s2 + " should be: {} - " + getOutputText("{}".equals(s3.toString())));
    }
    
    private static String getOutputText(boolean result) {
        return result ? "Succees" : "Fail";
    }
}

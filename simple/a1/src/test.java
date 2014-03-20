import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import i.RaiseListInteger;

import java.util.List;

public class test {
    static public void main(String[] args) {
        for (RaiseListInteger e : new RaiseListInteger[]{new a1()}) {
            test(e);
        }
    }

    static class testCase {
        String name;
        List<Integer> aIn;
        List<Integer> bIn;
        boolean expectedReturn;
        List<Integer> aOut;
        List<Integer> bOut;

        testCase(List<Integer> aIn, List<Integer> bIn) {
            this.name = String.format("(%s, %s) : false", asString(aIn), asString(bIn));
            this.aIn = aIn;
            this.bIn = bIn;
            this.expectedReturn = false;
            this.aOut = null;
            this.bOut = null;
        }

        static private String asString(List<Integer> aIn) {
            return "[" + Joiner.on(", ").join(aIn) + "]";
        }

        testCase(List<Integer> aIn, List<Integer> bIn, List<Integer> aOut, List<Integer> bOut) {
            this.name = String.format("(%s, %s) -> (%s, %s)", asString(aIn), asString(bIn), asString(aOut), asString(bOut));
            this.aIn = aIn;
            this.bIn = bIn;
            this.expectedReturn = true;
            this.aOut = aOut;
            this.bOut = bOut;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static testCase[] cases;

    static {
        cases = new testCase[]{
            new testCase(
                Lists.newArrayList(1),
                Lists.newArrayList(2)
            ),
            new testCase(
                Lists.newArrayList(1, 2, 3),
                Lists.newArrayList(4, 5, 6),
                Lists.newArrayList(1, 2, 3, 4),
                Lists.newArrayList(5, 6)
            ),
            new testCase(
                Lists.newArrayList(1, 2, 3, 4),
                Lists.newArrayList(4, 5, 6),
                Lists.newArrayList(1, 2, 3, 4, 4),
                Lists.newArrayList(5, 6)
            ),
            new testCase(
                Lists.newArrayList(1, 0, 2, 3, 4),
                Lists.newArrayList(4, 5, 6),
                Lists.newArrayList(1, 0, 2, 3, 4, 4),
                Lists.newArrayList(5, 6)
            ),
            new testCase(
                Lists.newArrayList(4, 5, 6),
                Lists.newArrayList(1, -1, 2, 3, 4),
                Lists.newArrayList(5, 6),
                Lists.newArrayList(1, -1, 2, 3, 4, 4)               
            ),       
        };
    }

    static private void test(RaiseListInteger e) {
        for (testCase tc : cases) {
            boolean result = e.raiseAverages(tc.aIn, tc.bIn);
            report(result == tc.expectedReturn, "return value", tc.toString());
            if (result && tc.expectedReturn) {
                report(tc.aIn.containsAll(tc.aOut), "aOut", tc.toString());
                report(tc.bIn.containsAll(tc.bOut), "bOut", tc.toString());
            }
        }
    }


    static private void report(boolean result, String message, String name) {
        System.out.println((result ? "PASS" : "FAIL") + " | " + name + " | " + message);
    }
}

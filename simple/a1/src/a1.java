import java.util.List;

public class a1 implements i.RaiseListInteger {
    public boolean raiseAverages(List<Integer> a, List<Integer> b) {
        int aAvg = average(a);
        int bAvg = average(b);

        if (aAvg == bAvg)
            return false;

        if (aAvg > bAvg)
            return tryMove(a, aAvg, b, bAvg);

        return tryMove(b, bAvg, a, aAvg);
    }

    boolean tryMove(List<Integer> higher, int higherAvg, List<Integer> lower, int lowerAvg) {
        for (Integer e : higher)
            if (e < higherAvg && e > lowerAvg) {
                higher.remove(e);
                lower.add(e);
                return true;
            }
        return false;
    }

    int average(List<Integer> a) {
        int total = 0;
        for (Integer e : a) {
            total += e;
        }
        return total / a.size();
    }
}

package pathfinder;

import pathfinder.datastructures.Path;
import java.util.Comparator;

/**
 * A class that implements on comparator where comparing the two paths with unknown data type.
 */
public class PathComparator implements Comparator<Path<?>> {

    /**
     * Compare the two unknown data type paths where based on the traveling cost.
     *
     * @param p1 the first path to be compared.
     * @param p2 the second path to be compared.
     * @return return negative number if p1 cost less than that of p2,
     *         return positive number if p1 cost greater than that of p2,
     *         return 0 if p1 and p2 have same traveling cost.
     * @throws RuntimeException if p1 or p2 is null.
     */
    @Override
    public int compare(Path<?> p1, Path<?> p2) {
        if (p1 == null || p2 == null) {
            throw new RuntimeException("Path can;t be null");
        }
        return Double.compare(p1.getCost(), p2.getCost());
    }
}

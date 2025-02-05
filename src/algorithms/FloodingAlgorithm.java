package algorithms;

import simulator.NeighborInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements a flooding routing algorithm that converges.
 */
public class FloodingAlgorithm extends Algorithm {

    // IMPORTANT: You can maintain a state, e.g., a flag.
    private boolean state = false;
    @Override
    public List<NeighborInfo> selectNeighbors(String origin, String destination, String previousHop,
                                              List<NeighborInfo> neighbors) {
        if (!state) {
            state = true;
            List<NeighborInfo> chosen = neighbors.stream()
                    // Make sure that we do not route back to the previous hop.
                    .filter(n -> !n.address.equals(previousHop))
                    .collect(Collectors.toList());
            // Return the chosen nodes.
            return chosen;
        }
        return new ArrayList<>();
    }

    @Override
    public Algorithm copy() {
        return new FloodingAlgorithm();
    }

    @Override
    public String getName() {
        return "Flooding";
    }
}

package algorithms;

import simulator.NeighborInfo;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumCostAlgorithm extends Algorithm {

    // IMPORTANT: Use this random number generator.
    Random rand = new Random(6391238);

    // IMPORTANT: You can maintain a state, e.g., a set of neighbors.
    private Set<String> exclusionSet = new HashSet<>();

    @Override
    public List<NeighborInfo> selectNeighbors(String origin, String destination, String previousHop,
                                              List<NeighborInfo> neighbors) {
        List<NeighborInfo> chosen = neighbors.stream()
                // Make sure that we do not route back to the previous hop.
                .filter(n -> !n.address.equals(previousHop))
                .filter(n -> !exclusionSet.contains(n.address))
                .collect(Collectors.toList());

        exclusionSet.add(previousHop);

        NeighborInfo neighborInfo;
        if (!chosen.isEmpty()) {
            neighborInfo = chosen.stream().min(Comparator.comparingInt(x -> x.cost)).get();
        } else {
            neighborInfo = new ArrayList<>(neighbors).get(rand.nextInt(neighbors.size()));
        }

        exclusionSet.add(neighborInfo.address);

        List<NeighborInfo> resultingList = new ArrayList<>();
        resultingList.add(neighborInfo);
        return resultingList;
    }

    @Override
    public Algorithm copy() {
        return new MinimumCostAlgorithm();
    }

    @Override
    public String getName() {
        return "MinimumCost";
    }
}

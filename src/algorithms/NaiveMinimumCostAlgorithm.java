package algorithms;

import simulator.NeighborInfo;

import java.util.*;
import java.util.stream.Collectors;

public class NaiveMinimumCostAlgorithm extends Algorithm {

    @Override
    public List<NeighborInfo> selectNeighbors(String origin, String destination, String previousHop,
                                              List<NeighborInfo> neighbors) {
        List<NeighborInfo> chosen = neighbors.stream()
                // Make sure that we do not route back to the previous hop.
                .filter(n -> !n.address.equals(previousHop))
                .collect(Collectors.toList());
        // Return the chosen nodes.
        NeighborInfo neighborInfo = chosen.stream().min(Comparator.comparingInt(x -> x.cost)).get();
        List<NeighborInfo> resultingList = new ArrayList<>();
        resultingList.add(neighborInfo);
        return resultingList;
    }

    @Override
    public Algorithm copy() {
        return new NaiveMinimumCostAlgorithm();
    }

    @Override
    public String getName() {
        return "NaiveMinimumCost";
    }
}

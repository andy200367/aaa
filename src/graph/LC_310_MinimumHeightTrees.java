package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_310_MinimumHeightTrees {

    public static void main(String[] args) {
        LC_310_MinimumHeightTrees cls = new LC_310_MinimumHeightTrees();
        int[][] edges = new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } };
        List<Integer> res = cls.findMinHeightTrees(4, edges);
        System.out.println(res.toString()); // [1]

        edges = new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } };
        res = cls.findMinHeightTrees(6, edges);
        System.out.println(res.toString()); // [3,4]

    }

    /**
     * 
     * @param n   nodes, labeled from 0 to n-1
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i){
            adj.add(new HashSet<>()); //create n empty sets
        }
        for (int[] edge : edges) {//undirected graph
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            if (adj.get(i).size() == 1){
                leaves.add(i);
            }
        }
        
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i); //i is leaf, so remove it
                if (adj.get(j).size() == 1) {
                    newLeaves.add(j);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }

}

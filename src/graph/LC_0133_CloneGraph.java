package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_0133_CloneGraph {

    public static void main(String[] args) {
        LC_0133_CloneGraph cls = new LC_0133_CloneGraph();
        UndirectedGraphNode testCase = createTestCase();
        UndirectedGraphNode clonedGraph = cls.cloneGraph(testCase);
        System.out.println();
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    private Map<Integer, UndirectedGraphNode> map = new HashMap<>();

    /**
     * DFS clone
     * @param node
     * @return
     */
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null)
            return null;

        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(newNode.label, newNode);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            newNode.neighbors.add(clone(neighbor));
        }
        return newNode;
    }

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };

    private static UndirectedGraphNode createTestCase() {
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        node2.neighbors.add(node2);

        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        node1.neighbors.add(node2);

        UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        node0.neighbors.add(node1);
        node0.neighbors.add(node2);

        return node0;
    }
}

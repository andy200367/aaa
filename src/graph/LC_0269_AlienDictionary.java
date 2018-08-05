package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.
 * 
 * Input: words[] = {"baa", "abcd", "abca", "cab", "cad"} Output: Order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", therefore
 * 'b' is before 'a' in output. Similarly we can find other orders.
 * 
 * Input: words[] = {"caa", "aaa", "aab"} Output: Order of characters is 'c', 'a', 'b'
 */
public class LC_0269_AlienDictionary {

    public static void main(String[] args) {
        printOrder(new String[]{ "baa", "abcd", "abca", "cab", "cad" });
        printOrder(new String[]{ "caa", "aaa", "aab" });
    }
    
    private static void printOrder(String[] words) {
        // put all unique characters into charSet
        Set<Character> charSet = new HashSet<>();
        for (String word : words) {
            charSet.addAll(word.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }
        
        Graph graph = new Graph(charSet.size());

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {//create edge basing on mismatch
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
                    break;
                }
            }
        }
        graph.topologicalSort();
    }    
    
    private static class Graph {
        private final List<Integer>[] adjacencyList; // each list stores edges starting from a vertex at that index

        private Graph(int n) {
            adjacencyList = new List[n];
            for (int vertexIndex = 0; vertexIndex < n; vertexIndex++) {
                adjacencyList[vertexIndex] = new LinkedList<>();
            }
        }

        void addEdge(int start, int end) {
            adjacencyList[start].add(end);
        }

        private int getNumOfVertices() {
            return adjacencyList.length;
        }

        /**
         * 
         * @param curVtx    current vertex
         * @param visited   boolean array, indicate each vertex visited or not 
         * @param stack     
         */
        private void topologicalSortUtil(int curVtx, boolean[] visited, Stack<Integer> stack) {
            visited[curVtx] = true; // Mark the current node as visited.
            for (int adjacentVertex : adjacencyList[curVtx]) {
                if (!visited[adjacentVertex]) {
                    topologicalSortUtil(adjacentVertex, visited, stack);
                }
            }
            stack.push(curVtx);//push current, after following are pushed
        }

        void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[getNumOfVertices()]; // init all vertices to false(default)

            for (int i = 0; i < getNumOfVertices(); i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }

            // Print contents of stack
            while (!stack.isEmpty()) {
                System.out.print((char) ('a' + stack.pop()) + " ");
            }
            System.out.println();
        }
    }

}

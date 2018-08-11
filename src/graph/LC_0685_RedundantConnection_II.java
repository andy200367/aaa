package graph;

import java.util.Arrays;

/**
 * edge {parent, child}
 * node value start from 1
 */
public class LC_0685_RedundantConnection_II {

    public static void main(String[] args) {
        LC_0685_RedundantConnection_II cls = new LC_0685_RedundantConnection_II();
        int[] res = null;
        
//        int[] res = cls.findRedundantDirectedConnection(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } });
//        System.out.println(Arrays.toString(res));// [2,3]
//
//        res = cls.findRedundantDirectedConnection(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 }, { 1, 5 } });
//        System.out.println(Arrays.toString(res));// [4,1]
//        
//        res = cls.findRedundantDirectedConnection(new int[][] { { 3, 4 }, { 4, 1 }, { 2, 3 }, { 1, 2 }, { 5, 1 } });
//        System.out.println(Arrays.toString(res));// [4,1]
//        
//        res = cls.findRedundantDirectedConnection(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 1 }, { 1, 4 } });
//        System.out.println(Arrays.toString(res));// [3,1]

        //rx created test case
//        res = cls.findRedundantDirectedConnection(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 2 } });
//        System.out.println(Arrays.toString(res));// [3,1]

        //edges[i][1] = 0
        res = cls.findRedundantDirectedConnection(new int[][] {{2,1},{3,1},{4,2},{1,4}});
        System.out.println(Arrays.toString(res));// expect [2,1]
        
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        //overwritten, store last parent, child pair
        int[] can1 = { -1, -1 };
        int[] can2 = { -1, -1 };
        
        int[] parent = new int[edges.length + 1];//because idx:0 is not used
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {//so far, not found previous parent
                parent[edges[i][1]] = edges[i][0];
            } else { //found 2 parents (only one node possible, except root)
                can1 = new int[] { parent[edges[i][1]], edges[i][1] }; //previous edge
                can2 = new int[] { edges[i][0], edges[i][1] };   //current edge
                edges[i][1] = 0;  //invalid 2nd edge of 2 parent node, otherwise, findRoot cannot find circle 
            }
        }
        for (int i = 0; i <= edges.length; i++) {//init, set all node parent to itself
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int father = edges[i][0], child = edges[i][1];
            int root = findRoot(parent, father);
            if ( root == child) {// found circle
                if (can1[0] == -1) {//no nodes have 2 parents
                    return edges[i];  // last added edge   (case 1.1)
                }
                return can1; // previous parent edge   (case 1.2)
            }
            parent[child] = father;
        }
        return can2;  // no circle, return candidate 2  (case 2)
    }
    
    /**
     * find root of i
     * @param parent
     * @param i
     * @return
     */
    int findRoot(int[] parent, int i) {
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

}

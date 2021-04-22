/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.



Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]


Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ui, vi <= n

 */

class Solution685 {

    //三种情况
    // https://www.youtube.com/watch?v=lnmJT5b4NlM&t=411s
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length + 1;
        int[] parent = new int[n];
        int[] rank   = new int[n];
        int[] rootArray   = new int[n];
        int[] edge1 = null;
        int[] edge2 = null;


        for (int[] edge: edges) {
            int root = edge[0];
            int node = edge[1];
            if (parent[node] != 0) {
                edge1 = new int[]{parent[node], node};
                edge2 = new int[]{root, node};
                edge[0] = -1;
                edge[1] = -1;
            }
            parent[node] = root;
        }

        for (int[] edge: edges) {
            int root = edge[0];
            int node = edge[1];

            if (root == -1 || node == -1) {
                continue;
            }
            if (rootArray[root] == 0) {
                rootArray[root] = root;
            }
            if (rootArray[node] == 0) {
                rootArray[node] = node;
            }
            int parentI = find(rootArray, root);
            int parentII = find(rootArray, node);
            if (parentI == parentII) {
                return edge1 == null? edge: edge1;
            }
            union(rootArray, rank, root, node);
        }
        return edge2;
    }

    int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    boolean union(int[] parent, int[] rank, int i, int j) {
        int iP = find(parent, i);
        int jP = find(parent, j);
        if (iP == jP) {
            return false;
        }
        if (rank[iP] < rank[jP]) {
            parent[iP] = jP;
        } else if (rank[iP] > rank[jP]) {
            parent[jP] = iP;
        } else {
            parent[iP] = jP;
            rank[jP]++;
        }
        return true;
    }



}
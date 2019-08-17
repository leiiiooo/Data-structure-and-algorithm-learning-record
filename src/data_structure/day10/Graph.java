package data_structure.day10;

import java.util.LinkedList;

/**
 * 使用邻接矩阵表示图
 */
public class Graph {
    public static final int MAX_WEIGHT = Integer.MAX_VALUE;

    int verticesNum;
    boolean[] verticesVisited;
    int[] vertices;
    int[][] matrix;

    public Graph(int verticesNum) {
        this.verticesNum = verticesNum;
        verticesVisited = new boolean[verticesNum];
        vertices = new int[verticesNum];
        matrix = new int[verticesNum][verticesNum];
        //初始化，出度、入度顶点
        for (int i = 0; i < verticesNum; i++) {
            vertices[i] = i;
        }
    }

    /**
     * 计算v1到v2的权重
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        int weight = matrix[v1][v2];
        return weight == MAX_WEIGHT ? -1 : weight;
    }

    /**
     * 获取顶点
     *
     * @param v1
     * @return
     */
    public int[] getVertices() {
        return vertices;
    }

    /**
     * 获取出度
     *
     * @param v
     * @return
     */
    public int getOutDegree(int v) {
        int outDegree = 0;
        for (int i = 0; i < verticesNum; i++) {
            if (matrix[v][i] != 0 && matrix[v][i] != MAX_WEIGHT) {
                outDegree++;
            }
        }

        return outDegree;
    }

    /**
     * 获取入度
     *
     * @param v
     * @return
     */
    public int getInDegree(int v) {
        int inDegree = 0;
        for (int i = 0; i < verticesNum; i++) {
            if (matrix[i][v] != 0 && matrix[i][v] != MAX_WEIGHT) {
                inDegree++;
            }
        }

        return inDegree;
    }

    /**
     * 获取第一个邻接节点
     *
     * @param v
     * @return
     */
    public int getFirstNeighBor(int v) {
        return getNextNeighbor(v, -1);
    }

    /**
     * 获取到顶点v的邻接点index的下一个邻接点
     *
     * @param v
     * @param offset
     * @return
     */
    public int getNextNeighbor(int v, int offset) {
        int index = -1;
        for (int i = offset + 1; i < verticesNum; i++) {
            if (matrix[v][i] != 0 && matrix[v][i] != MAX_WEIGHT) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * 深度优先
     */
    public void dfs() {
        for (int i = 0; i < verticesVisited.length; i++) {
            verticesVisited[i] = false;
        }

        for (int i = 0; i < verticesVisited.length; i++) {
            if (!verticesVisited[i]) {
                System.out.println("visited depth " + i);
                dfs(i);
            }
        }
    }

    private void dfs(int i) {
        verticesVisited[i] = true;
        int index = getFirstNeighBor(i);
        while (index != -1) {
            if (!verticesVisited[index]) {
                System.out.println("visited depth " + index);
                dfs(index);
            }

            index = getNextNeighbor(i, index);
        }
    }

    /**
     * 广度优先
     */
    public void bfs() {
        for (int i = 0; i < verticesVisited.length; i++) {
            verticesVisited[i] = false;
        }

        for (int i = 0; i < verticesNum; i++) {
            if (!verticesVisited[i]) {
                verticesVisited[i] = true;
                System.out.println("visited breadth " + i);
                bfs(i);
            }
        }
    }

    private void bfs(int i) {
        LinkedList<Integer> list = new LinkedList();
        int firstNeighBor = getFirstNeighBor(i);
        if (firstNeighBor == -1) {
            return;
        }

        if (!verticesVisited[firstNeighBor]) {
            verticesVisited[firstNeighBor] = true;
            System.out.println("visited breadth " + firstNeighBor);
            list.offer(firstNeighBor);
        }

        int nextNeighbor = getNextNeighbor(i, firstNeighBor);
        while (nextNeighbor != -1) {
            if (!verticesVisited[nextNeighbor]) {
                verticesVisited[nextNeighbor] = true;
                System.out.println("visited breadth " + nextNeighbor);
                list.offer(nextNeighbor);
            }
            nextNeighbor = getNextNeighbor(i, nextNeighbor);
        }

        while (!list.isEmpty()) {
            int point = list.pop();
            bfs(point);
        }
    }
}

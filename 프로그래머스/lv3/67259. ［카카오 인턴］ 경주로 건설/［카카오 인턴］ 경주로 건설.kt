import java.util.*;

var N = 0

val dx = arrayOf( 0, 1, 0, -1 )
val dy = arrayOf( 1, 0, -1, 0 )

class Solution {
    fun isWall(x: Int, y: Int): Boolean {
        if (x < 0 || x >= N || y < 0 || y >= N) return true
        return false
    }
    
    fun solution(board: Array<IntArray>): Int {
        N = board.size    
    
        // 해당 위치 도달에 드는 최소비용
        val visited = Array(N) { IntArray(N) { Int.MAX_VALUE } }
        
        val pq = PriorityQueue<Node>()
        for (i in 0 until 2) {
            val nx = dx[i]
            val ny = dy[i]
            if (board[nx][ny] == 1) continue
            
            pq.add(Node(nx, ny, 100, i))
        }
        
        while (!pq.isEmpty()) {
            val (x, y, cost, dir) = pq.poll()

            // 같은 위치 도달에 대해 최저 비용보다 400원까지 비싸더라도 
            // 다음 방향이 직선방향이면 더 낮은 cost로 갈 수 있으므로 예외처리
            if (visited[x][y] < cost - 400) continue
            
            if (visited[x][y] > cost) visited[x][y] = cost
            
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (isWall(nx, ny) || board[nx][ny] == 1) continue
                
                if (i == dir) pq.add(Node(nx, ny, cost + 100, i))
                else pq.add(Node(nx, ny, cost + 600, i))
            }
        }
        
        return visited[N - 1][N - 1]
    }
}

data class Node (
    val x: Int,
    val y: Int,
    val cost: Int,
    val dir: Int
): Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.cost - other.cost
    }
}
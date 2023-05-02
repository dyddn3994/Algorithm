import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 입력값 Node에 따라 list에 저장 후 정렬, pq가 더 빠르려나?
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            list.add(new Node(routes[i][0], i, true));
            list.add(new Node(routes[i][1], i, false));
        }
        Collections.sort(list);
        
        Set<Integer> set = new HashSet<>(); // 카메라를 만난 차
        List<Integer> startedCarList = new ArrayList<>(); // 출발 시작했고 도착하지 않은 차
        
        for (Node node : list) {
            // 출발이면 출발리스트에 add
            if (node.isStart) startedCarList.add(node.carNum);
            else {
                // 이미 카메라를 만난 차
                if (set.contains(node.carNum)) continue; 
                
                set.addAll(startedCarList);
                startedCarList.clear();
                answer++;
            }
        }
        
        return answer;
    }
}

class Node implements Comparable<Node> {
    int pos;
    int carNum;
    boolean isStart;
    Node (int pos, int carNum, boolean isStart) {
        this.pos = pos;
        this.carNum = carNum;
        this.isStart = isStart;
    }
    
    @Override
    public int compareTo(Node node) {
        if (this.pos == node.pos) {
            if (isStart) return -1;
            else return 1;
        }
        return this.pos - node.pos;
    }
}
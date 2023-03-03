const fs = require('fs');
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split('\n');

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

function isWall(x, y) {
  if (x < 0 || x >= N || y < 0 || y >= M) return true;
  return false;
}

class Node {
  constructor(x, y, dist, isBreak) {
    this.x = x;
    this.y = y;
    this.dist = dist;
    this.isBreak = isBreak;
    this.next = null;
  }
}

// 딕셔너리로 큐를 만들었을때 풀리지 않으면 linkedlist 형식으로 해결
class Queue {
  constructor() {
    this.front = null;
    this.rear = null;
  }

  isEmpty() {
    if (this.rear === null) return true;
    else return false;
  }

  push(node) {
    if (this.isEmpty()) {
      this.front = node;
    }
    else {
      this.rear.next = node;
    }
    this.rear = node;
  }

  shift() {
    let tmp = this.front;
    if (this.front === this.rear) {
      this.front = null;
      this.rear = null;
    }
    else {
      this.front = this.front.next;
    }
    return tmp;
  }
}

function bfs(x, y) {
  const queue = new Queue();
  queue.push(new Node(x, y, 1, false));
  visited[x][y][0] = true;

  while(!queue.isEmpty()) {
    const node = queue.shift();
    x = node.x;
    y = node.y;
    const dist = node.dist;
    const isBreak = node.isBreak;

    // 이렇게 해주어야 메모리초과가 나지 않음
    if (visited[x][y][isBreak]) continue;
    visited[x][y][isBreak] = true;

    // 다음 이동에 대해 목적지 찾기를 하면 N: 1, M: 1일때 오류
    if (x === N-1 && y === M-1) {
      // 목적지면 종료
      min = dist;
      return;
    }

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (isWall(nx, ny)) continue;

      
      if (!visited[nx][ny][1] && arr[nx][ny] === 1 && !isBreak) {
        // 벽을 만났지만 아직까지 벽을 부수지 않았을 때 이번 벽을 부숨
        // visited[nx][ny][1] = true;
        queue.push(new Node(nx, ny, dist+1, true));
      }
      else if (!visited[nx][ny][0] && arr[nx][ny] === 0) {
        // 벽이 아닐때 (벽을 뚫은 상태로 벽이 아닌 곳을 들렀을 때여도 벽을 뚫은 상태의 visited로 해야함)
        // 다음 방문을 visited로 하면 메모리 초과.. 왜지?
        // visited[nx][ny][isBreak] = true;
        queue.push(new Node(nx, ny, dist+1, isBreak));
      }
    }
  }
}

// 입력
const [N, M] = input[0].split(' ').map(Number); // 행 열 크기
const arr = new Array(N); // 입력 배열
for (let i = 0; i < N; i++) {
  arr[i] = input[i+1].trim().split('').map(Number);
}
let min = -1; // 최단경로

// visited를 3차 배열로 선언하여 벽을 뚫고 지나왔을때 방문한 곳인지, 뚫지않고 방문한 곳인지 확인(메모리 초과 방지)
// [x][y][0]: 벽 뚫지않고 방문, [x][y][1]: 벽 뚫고 방문
visited = Array.from({ length: N }, () => 
  Array.from({ length: M }, () => 
    Array.from({ length: 2 }, () => false)
  )
);

bfs(0, 0);

console.log(min);
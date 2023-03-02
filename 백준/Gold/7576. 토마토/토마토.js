const fs = require('fs');
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split('\n');

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

function isWall(x, y) {
  if (x < 0 || x >= M || y < 0 || y >= N) return true;
  return false;
}

// 입력
const [N, M] = input[0].split(' ').map(Number); // 열, 행 크기
const arr = new Array(M); // 입력 배열
const dist = new Array(M); // 이동 거리 배열
for (let i = 0; i < M; i++) {
  arr[i] = input[i+1].split(' ').map(Number)
  dist[i] = new Array(N).fill(0);
}
let max = 1;

bfs();

// 익지 못한 토마토가 있을때 출력값 변경
for (let i = 0; i < M; i++) {
  for (let j = 0; j < N; j++) {
    if (arr[i][j] !== -1 && dist[i][j] === 0) max = 0;
  }
}

console.log(max-1);

// 토마토가 있는 위치에서 동시에 bfs
function bfs() {
  // queue 처리를 위한 배열. shift를 쓰면 배열에서 0번째를 빼서 사용하므로 속도가 느림. 인덱스로 임시 처리.
  const xQueue = [];
  const yQueue = [];
  let xIdx = 0;
  let yIdx = 0;

  // 토마토가 있는 위치 찾기
  for (let i = 0; i < M; i++) {
    for (let j = 0; j < N; j++) {
      if (arr[i][j] === 1) {
        dist[i][j] = 1;
        xQueue.push(i);
        yQueue.push(j);
      }
    }
  }

  while (xQueue.length > xIdx && yQueue.length > yIdx) {
    const x = xQueue[xIdx++];
    const y = yQueue[yIdx++];
    
    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (!isWall(nx, ny) && arr[nx][ny] !== -1 && dist[nx][ny] === 0) {
        dist[nx][ny] = dist[x][y] + 1;
        if (max < dist[nx][ny]) max = dist[nx][ny];
        xQueue.push(nx);
        yQueue.push(ny);
      }
    }
  }
}
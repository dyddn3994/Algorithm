const fs = require('fs');
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split('\n');

// 선거구 정하기
// 선택한 상황에 대해 dfs, 끝나면 그 선거구에서 추가로 구역 추가해서 재귀 호출
function select(selected, idx) {
  visited.fill(false);
  let cnt = dfs(selected[0], selected); // 선택된 구역
  if (cnt === selected.length) {
    const notSelected = []; // 선택되지 않은 구역
    for (let i = 2; i <= N; i++) {
      if (selected.indexOf(i) === -1) notSelected.push(i);
    }
    
    // 선택된 구역이 없으면 예외
    if (notSelected.length !== 0) {
      visited.fill(false);
      cnt = dfs(notSelected[0], notSelected);

      // 두 선거구 모두 연결되어 있으므로 인구 차 계산
      if (cnt === notSelected.length) {
        let popul1 = 0, popul2 = 0;
        selected.forEach(i => popul1 += population[i]);
        notSelected.forEach(i => popul2 += population[i]);
  
        const sub = Math.abs(popul1 - popul2);
        if (min > sub) min = sub;
      }
    }

  }

  for (let i = idx; i <= N; i++) {
    select(selected.concat(i), i+1)
  }
}

// 선택된 구역에 대해 구역이 닿아있는지 확인
function dfs(v, selected) {
  let cnt = 1; // 방문한 선거구 내 구역 수
  visited[v] = true;
  const thisAdj = adj[v]; // 해당 구역의 인접 구역 정보
  thisAdj.forEach(i => {
    if (!visited[i] && selected.includes(i)) {
      cnt += dfs(i, selected);
    }
  })

  return cnt;
}

// 입력
const N = Number.parseInt((input[0].trim())); // 구역 수
const population = input[1].trim().split(' ').map(Number); // 구역 별 인구 수
population.unshift(0);
const adj = new Array(N+1); // 인접 정보 배열
for (let i = 1; i <= N; i++) {
  adj[i] = input[i + 1].trim().split(' ').map(Number);
  adj[i].shift();
}

const visited = new Array(N + 1).fill(false);
let min = Number.MAX_VALUE;

// 1을 기준으로 선거구 정하고 DFS해서 연결되어 있는지 확인
select([1], 2);

if (min === Number.MAX_VALUE) min = -1;

console.log(min);
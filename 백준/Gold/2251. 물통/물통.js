const fs = require('fs');
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split('\n');

// a, b, c: A, B, C에 들어있는 물의 양
function dfs(a, b, c) {
  visited[a][b][c] = true;
	if (a === 0) {
		// a가 비어있으면 set에 c에 있는 물의 양 저장
		set.add(c);
	}
	
  if (a) {
    if (b < B) {
      const p = Math.min(a, B-b);
      if (!visited[a-p][b+p][c]) dfs(a-p, b+p, c);
    }
    if (c < C) {
      const p = Math.min(a, C-c);
      if (!visited[a-p][b][c+p]) dfs(a-p, b, c+p);
    }
  }

  if (b) {
    if (a < A) {
      const p = Math.min(b, A-a);
      if (!visited[a+p][b-p][c]) dfs(a+p, b-p, c);
    }
    if (c < C) {
      const p = Math.min(b, C-c);
      if (!visited[a][b-p][c+p]) dfs(a, b-p, c+p);
    }
  }

  if (c) {
    if (a < A) {
      const p = Math.min(c, A-a);
      if (!visited[a+p][b][c-p]) dfs(a+p, b, c-p);
    }
    if (b < B) {
      const p = Math.min(c, B-b);
      if (!visited[a][b+p][c-p]) dfs(a, b+p, c-p);
    }
  }

	// if (b < B) {
	// 	for (let i = 0; i < 3; i++) {
	// 		const p = Math.min(water, capacity[i], B-b);
  //     if (!visited[b+p][c]) dfs(water-p, b+p, c);
	// 	}
	// }
	// if (c < C) {
	// 	for (let i = 0; i < 3; i++) {
	// 		const p = Math.min(water, capacity[i], C-c);
	// 		if (!visited[b][c+p]) dfs(water-p, b, c+p);
	// 	}
	// }
}

// 입력
const capacity = input[0].trim().split(' ').map(Number); // A, B, C의 용량
const A = capacity[0];
const B = capacity[1];
const C = capacity[2];

const set = new Set(); // 결과 저장용 set
// a와 b와 c의 물 양 상태에 따른 visited
const visited = Array.from({length: 201}, () =>
  Array.from({length: 201}, () => 
    Array.from({length: 201}), () => false
  )
)

dfs(0, 0, C);

// 결과 오름차순 정렬
const resArr = Array.from(set).map(Number).sort((a, b) => (a - b)); 
resArr.forEach(n => process.stdout.write(n + ' '));
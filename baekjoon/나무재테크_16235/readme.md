# 백준 16235번 나무재테크 문제 풀이

문제유형은 시뮬레이션입니다.

### 전체소스

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 나무재테크_16235 {
	static class Ground {
		LinkedList<Tree> treeList;
		int energy;
		int addEnergy;
		
		public Ground(int energy, int addEnergy) {
			super();
			this.energy = energy;
			this.addEnergy = addEnergy;
			treeList = new LinkedList<Tree>();
		}
	}
	
	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;
		
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	//땅의 크기
	static int N;
	//나무개수
	static int M;
	//년수
	static int K;
	static Ground[][] map;
	static int[][] deadMap;
	static int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Ground[N+1][N+1];
		deadMap = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = new Ground(5, Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			Tree tree = new Tree(r, c, age);
			map[r][c].treeList.add(tree);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Collections.sort(map[i][j].treeList);
			}
		}
		
		for (int tc = 0; tc < K; tc++) {
			//봄
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					for (Iterator iterator = map[i][j].treeList.iterator(); iterator.hasNext();) {
						Tree tree = (Tree) iterator.next();
						
						if(map[i][j].energy >= tree.age) {
							map[i][j].energy -= tree.age;
							tree.age++;
						}else {
							deadMap[i][j] += (int) (tree.age / 2);
							iterator.remove();
						}
					}
				}
			}
			
			//여름
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j].energy += deadMap[i][j];
					deadMap[i][j] = 0;
				}
			}
			
			//가을
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					for (Tree tree : map[i][j].treeList) {
						if(tree.age % 5 == 0) {
							for (int k = 0; k < pos.length; k++) {
								int nr = tree.r + pos[k][0];
								int nc = tree.c + pos[k][1];
								
								if(posCheck(nr, nc)) {
									map[nr][nc].treeList.addFirst(new Tree(nr, nc, 1));
								}
							}
						}
					}
				}
			}
			
			//겨울
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j].energy += map[i][j].addEnergy;
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ans += map[i][j].treeList.size();
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= N && col >= 1 && col <= N;
	}
}

```



### 각각의 땅을 관리할 클래스

```java
static class Ground {
    LinkedList<Tree> treeList;
    int energy;
    int addEnergy;

    public Ground(int energy, int addEnergy) {
        super();
        this.energy = energy;
        this.addEnergy = addEnergy;
        treeList = new LinkedList<Tree>();
    }
}
```

각각의 자리에서 나무들을 관리하기 위해 Ground라는 내부 클래스를 선언해주었습니다.

treeList : 나무를 관리합니다. 삽입, 삭제가 빈번히 일어나기 때문에 LinkedList로 선언

energy : 각각의 자리의 양분을 나타냅니다.

addEnergy : 겨울마다 추가될 에너지를 나타냅니다.

### 나무 클래스

```java
static class Tree implements Comparable<Tree> {
    int r;
    int c;
    int age;

    public Tree(int r, int c, int age) {
        super();
        this.r = r;
        this.c = c;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}
```

나무 클래스입니다. 문제에서 요구한 제일 나이가 적은 나무가 먼저 양분을 섭취하게 만들기 위해 Comparable을 나이 오름차순 순으로 구현합니다.

r : 나무의 행 위치

c : 나무의 열 위치

age : 나무의 나이

### 변수 선언

```java
//땅의 크기
static int N;
//나무개수
static int M;
//년수
static int K;
static Ground[][] map;
static int[][] deadMap;
static int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
```

N : 땅의 크기

M : 나무의 개수

K : 년수

map : 각각의 자리를 Ground객체로 관리할 2차원 배열

deadMap : 죽은 나무의 양분을 모아서 한번에 처리하기 위한 2차원 int 배열

pos : 8방으로 새로운 나무를 추가할 때 사용할 위치 좌표 배열

### 범위체크 메소드

```java
static boolean posCheck(int row, int col) {
	return row >= 1 && row <= N && col >= 1 && col <= N;
}
```
좌표가 맵안에 들어있는지 검사하는 메소드입니다.

### 값 입력 처리

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine());

N = Integer.parseInt(st.nextToken());
M = Integer.parseInt(st.nextToken());
K = Integer.parseInt(st.nextToken());

map = new Ground[N+1][N+1];
deadMap = new int[N+1][N+1];

for (int i = 1; i <= N; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = 1; j <= N; j++) {
        map[i][j] = new Ground(5, Integer.parseInt(st.nextToken()));
    }
}

for (int i = 0; i < M; i++) {
    st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int age = Integer.parseInt(st.nextToken());
    Tree tree = new Tree(r, c, age);
    map[r][c].treeList.add(tree);
}
```

나무의 자리가 1부터 시작하기 때문에 map을 생성할 때 +1을 해줍니다.

각각의 자리를 최초 양분을 5가지고 있기 때문에 5를 초기값으로 설정해줍니다.

### 배열 정렬

```java
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        Collections.sort(map[i][j].treeList);
    }
}
```
문제의 설명에 나무는 어린나무부터 양분을 먹는다는 조건이 있었기 때문에 최초 1회 정렬을 진행합니다.

### 봄

```java
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        for (Iterator iterator = map[i][j].treeList.iterator(); iterator.hasNext();) {
            Tree tree = (Tree) iterator.next();

            if(map[i][j].energy >= tree.age) {
                map[i][j].energy -= tree.age;
                tree.age++;
            }else {
                deadMap[i][j] += (int) (tree.age / 2);
                iterator.remove();
            }
        }
    }
}
```
각각의 땅을 반복하며 정렬된 나무들이 양분을 흡수하고 나이를 증가하는 처리를 해줍니다.

양분이 부족할 경우 미리 선언해둔 현재 땅과 동일한 인덱스의 deadMap에 양분을 먹지 못한 나무 나이 / 2 만큼 양분을 더한 후 iterator를 사용해 객체를 지워줍니다. 일반적인 for 또는 foreach를 사용해 객체를 지울 경우 인덱스 문제가 발생하기 때문에 이러한 삭제 처리를 할경우 iterator를 사용해줍니다.

### 여름

```java
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        map[i][j].energy += deadMap[i][j];
        deadMap[i][j] = 0;
    }
}
```
각각의 땅을 순회하며 deadMap에 저장된 양분을 땅에 추가한 후 사용한 deadMap을 초기화 해줍니다.

여기서 deadMap을 사용한 이유는 봄에 죽은 나무를 따로 체크해둔 후 여름에 각각의 나무를 찾아서 양분화 시키는 것이 cost가 더 많이 들 것이라고 판단해서입니다.

### 가을

```java
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        for (Tree tree : map[i][j].treeList) {
            if(tree.age % 5 == 0) {
                for (int k = 0; k < pos.length; k++) {
                    int nr = tree.r + pos[k][0];
                    int nc = tree.c + pos[k][1];

                    if(posCheck(nr, nc)) {
                        map[nr][nc].treeList.addFirst(new Tree(nr, nc, 1));
                    }
                }
            }
        }
    }
}
```
각각의 땅을 순회하며 나무들 중 나이가 5의 배수인 나무가 있을 경우 주위 8방의 땅으로 1살인 나무를 추가해줍니다. 여기서 PriorityQueue나 ArrayList를 사용해 추가할때마다 정렬이 일어나면 많은 비용이 소요되어 시간초과가 나기때문에 LinkedList를 사용해 Colection의 가장 앞에 나무를 추가해 정렬을 유지시켜주면 최초 1번 이후 정렬을 할 필요가 없어지기때문에 비용을 아낄 수 있습니다.

#### 겨울

```java
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        map[i][j].energy += map[i][j].addEnergy;
    }
}
```
겨울에는 입력받은 각각의 땅마다 추가해야할 양분을 추가해줍니다. 최초의 땅 클래스를 설계할 당시 addEnergy라는 속성을 만들어두었기 때문에 해당 속성을 그대로 energy에 추가해줍니다.

### 답 계산

```java
int ans = 0;
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        ans += map[i][j].treeList.size();
    }
}
```
문제에서 주어진 년수만큼 계절을 반복한 후 땅을 순회하며 남아있는 나무들의 숫자를 모두 합하면 답을 구할 수 있습니다.
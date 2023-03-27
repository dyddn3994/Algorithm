import java.io.*;
import java.util.*;

public class Main {

    static int F; // 친구 관계 수
    static Map<String, String> friends; // 친구, 부모 인덱스 맵
    static Map<String, Integer> friendCnt; // 친구 수 맵

    static boolean isAdded(String friend) {
        for (String f : friends.keySet()) {
            if (f.equals(friend)) return true;
        }
        return false;
    }

    static void makeSet(String friend) {
        friends.put(friend, friend);
        friendCnt.put(friend, 1);
    }

    static String findSet(String friend) {
        String parent = friends.get(friend);
        if (!friends.get(parent).equals(parent)) {
            friends.put(friend, findSet(parent));
        }

        return friends.get(friend);
    }

    static void union(String f1, String f2) {
        String p1 = findSet(f1);
        String p2 = findSet(f2);

        int p1FriendCnt = friendCnt.get(p1);
        int p2FriendCnt = friendCnt.get(p2);

        friends.put(p2, p1);
        friendCnt.put(p1, p1FriendCnt + p2FriendCnt);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            // 입력
            F = Integer.parseInt(br.readLine());
            friends = new HashMap<>();
            friendCnt = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!friends.containsKey(f1)) makeSet(f1);
                if (!friends.containsKey(f2)) makeSet(f2);

                if (!findSet(f1).equals(findSet(f2))) union(f1, f2);

                sb.append(friendCnt.get(findSet(f1)))
                    .append("\n");
            }
        }

        System.out.println(sb);
    }
}
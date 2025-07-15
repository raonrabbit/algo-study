import java.util.*;

class Solution {
    public static List<Set<Integer>> groups = new ArrayList<>();
    public static Map<Integer, Set<Integer>> sets = new HashMap<>();

    public static int bannedLength;

    public void DFS(Set<Integer> curGroup, int depth){
        if(depth == bannedLength){
            boolean inGroup;
            for(Set<Integer> group : groups){
                inGroup = true;
                for(int n : curGroup){
                    if(!group.contains(n)){
                        inGroup = false;
                        break;
                    }
                }
                if(inGroup) return;
            }

            Set<Integer> temp = new HashSet<>();
            temp.addAll(curGroup);
            groups.add(temp);

            return;
        }

        for(int n : sets.get(depth)){
            if(curGroup.contains(n)) continue;

            curGroup.add(n);
            DFS(curGroup, depth + 1);
            curGroup.remove(n);
        }

    }

    public int solution(String[] user_id, String[] banned_id) {
        char[][] names = new char[user_id.length][];
        int userLength = user_id.length;
        bannedLength = banned_id.length;

        for(int i = 0; i < userLength; i++){
            names[i] = user_id[i].toCharArray();
        }

        for(int i = 0; i < bannedLength; i++){
            char[] bannedCharArray = banned_id[i].toCharArray();

            for(int n = 0; n < userLength; n++){
                if(user_id[n].length() != bannedCharArray.length) continue;

                boolean same = true;
                for(int t = 0; t < user_id[n].length(); t++){
                    if(bannedCharArray[t] == '*') continue;
                    if(bannedCharArray[t] != user_id[n].charAt(t)) {
                        same = false;
                        break;
                    }
                }

                if(same) {
                    sets.computeIfAbsent(i, k -> new HashSet<>()).add(n);
                }
            }
        }

        DFS(new HashSet<>(), 0);

        return groups.size();
    }
}
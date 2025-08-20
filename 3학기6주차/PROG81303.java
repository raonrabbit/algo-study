import java.util.*;

class Solution {
    public static class User {
        public int number;
        public boolean isDeleted;

        public User nextUser;
        public User prevUser;

        public User(int number, User prevUser) {
            this.number = number;
            this.prevUser = prevUser;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        User currentUser = new User(0, null);
        User prev = currentUser;
        for(int i = 1; i < n; i++){
            User user = new User(i, prev);
            prev.nextUser = user;
            prev = user;
        }

        for(int i = 0; i < k; i++){
            currentUser = currentUser.nextUser;
        }

        Stack<User> deleted = new Stack<>();
        boolean[] deletedArray = new boolean[n];

        for(String command : cmd){
            String[] c = command.split(" ");
            int amount;
            switch(c[0]){
                case "U":
                    amount = Integer.parseInt(c[1]);
                    for(int i = 0; i < amount; i++){
                        currentUser = currentUser.prevUser;
                    }
                    break;

                case "D":
                    amount = Integer.parseInt(c[1]);
                    for(int i = 0; i < amount; i++){
                        currentUser = currentUser.nextUser;
                    }
                    break;

                case "C":
                    User prevUser = currentUser.prevUser;
                    User nextUser = currentUser.nextUser;

                    deleted.push(currentUser);
                    deletedArray[currentUser.number] = true;
                    if(nextUser != null) {
                        currentUser = nextUser;
                        if(prevUser == null) {
                            currentUser.prevUser = null;
                            break;
                        }

                        prevUser.nextUser = nextUser;
                        nextUser.prevUser = prevUser;
                    } else {
                        currentUser = prevUser;
                        prevUser.nextUser = null;
                    }
                    break;

                case "Z":
                    User redoUser = deleted.pop();
                    deletedArray[redoUser.number] = false;

                    if(redoUser.prevUser == null){
                        redoUser.prevUser = null;
                        redoUser.nextUser.prevUser = redoUser;
                        break;
                    }

                    if(redoUser.nextUser == null){
                        redoUser.prevUser.nextUser = redoUser;
                        break;
                    }

                    redoUser.prevUser.nextUser = redoUser;
                    redoUser.nextUser.prevUser = redoUser;
                    break;
            }
        }

        for(int i = 0; i < n; i++){
            if(deletedArray[i]) sb.append('X');
            else sb.append('O');
        }

        return sb.toString();
    }
}
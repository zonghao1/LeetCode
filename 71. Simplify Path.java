//Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
//
//        In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are
//        treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
//
//        The canonical path should have the following format:
//
//        The path starts with a single slash '/'.
//        Any two directories are separated by a single slash '/'.
//        The path does not end with a trailing '/'.
//        The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
//        Return the simplified canonical path.
//
//
//
//        Example 1:
//
//        Input: path = "/home/"
//        Output: "/home"
//        Explanation: Note that there is no trailing slash after the last directory name.
//        Example 2:
//
//        Input: path = "/../"
//        Output: "/"
//        Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

import java.util.ArrayDeque;
import java.util.Deque;

class Solution71 {
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        int i = 0;
        String[] each = path.split("/");
        while (i < path.length()) {
            if (path.charAt(i) == '/') {
                i++;
            }
            else if (path.charAt(i) == '.') {
                int count = 0;
                int currLength = 0;
                while (i < path.length() && path.charAt(i) != '/' ) {
                    i++;
                    currLength++;
                    if (path.charAt(i-1) == '.') {
                        count++;
                    }
                }
                if (currLength == 1 && count == 1) {
                    continue;
                } else if (currLength == 2 && count == 2) {
                    if (!deque.isEmpty()) {
                        deque.pollLast();
                    }
                } else {
                    deque.offerLast(path.substring(i - currLength, i));
                }
            } else {
                int count = 0;
                while (i < path.length() && path.charAt(i) != '/' ) {
                    i++;
                    count++;
                }
                deque.offerLast(path.substring(i - count, i));
            }
        }
        StringBuilder sb = new StringBuilder();
        if (deque.size() == 0) {
            return "/";
        }
        while (!deque.isEmpty()) {
            sb.append('/');
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
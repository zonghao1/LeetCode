/*
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.



Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:

Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]


Constraints:

1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j] <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
 */

import java.util.*;

class Solution721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, String> parentMap = new HashMap<>();

        for (List<String> account: accounts) {
            String name = account.get(0);
            String parent = null;
            List<String> union = new ArrayList<>();
            for (int i = 1; i < account.size(); i++) {
                if (find(parentMap, account.get(i)) != null) {
                    parent = find(parentMap, account.get(i));
                    union.add(parent);
                }
            }


            if (parent == null) {
                name = name + "," + parentMap.size();
                for (int i = 1; i < account.size(); i++) {
                    parentMap.put(account.get(i), name);
                }
                parentMap.put(name, name);
            } else {

                for (String toUnion: union) {
                    parentMap.put(toUnion, parent);
                }
                for (int i = 1; i < account.size(); i++) {
                    parentMap.put(account.get(i), parent);
                }
            }


        }

        Map<String, List<String>> map = new HashMap<>();
        for (Map.Entry<String, String> entry: parentMap.entrySet()) {
            String email = entry.getKey();
            String name = find(parentMap, entry.getValue());
            boolean flag = false;
            for (char c: email.toCharArray()) {
                if (c == ',') flag = true;
            }
            if (flag || name.equals(email)) continue;

            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(email);
        }
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> list = entry.getValue();
            String name = entry.getKey().split(",")[0];
            Collections.sort(list);
            list.add(0, name);
            ret.add(list);
        }
        return ret;




    }

    String find(Map<String, String> parentMap, String email) {
        if (!parentMap.containsKey(email)) {
            return null;
        }
        if (!parentMap.get(email).equals(email)) {
            parentMap.put(email, find(parentMap, parentMap.get(email)));
        }
        return parentMap.get(email);

    }




}
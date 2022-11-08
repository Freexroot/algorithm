/*
package leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/letter-case-permutation/
class Solution {
    public List<String> letterCasePermutation(String s) {
        porcess(s, 0);
    }

    public List<String> porcess(String s, int i) {
        if (s.length() <= i) {
            return null;
        }
        List<String> s1 = new ArrayList<>();
        for (int i1 = 0; i1 < s.length(); i1++) {
            s1.addAll(porcess(s, i1)) ;
        }
    }

    public String Uppercase(String s,int i){

        return "";
    }
}

public class code01 {
    public static void main(String[] args) {

    }
}

*/

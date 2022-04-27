package dp;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author LiJing
 * @creat 2022-04-14-21:11
 */
public class LongestStrChain {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] dp = new int[words.length];
        int res = 0;
        for (int i = 0; i < words.length ; ++i) {
            String a = words[i];
            for (int j = i+1; j < words.length; j++) {
                String b = words[j];
                if(pre(a,b)){
                    dp[j]=Math.max(dp[i]+1,dp[j]);
                    res = Math.max(dp[j],res);
                }
            }
        }
        return res+1;
    }

    private boolean pre(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        if ((m + 1) != n) return false;
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) i++;
            j++;
        }
        return i == m;
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[][] ans = new int[m][m];
        if(m ==1){
            return triangle.get(0).get(0);

        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0;j <= i;j++){
                triangle.get(i).set(j,triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j),triangle.get(i + 1).get(j + 1))) ;
            }
        }

        return ans[0][0];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        wordDict.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        String temp = s;
        for (int i = 0; i < wordDict.size(); i++) {
            if(s.contains(wordDict.get(i))){
                temp = temp.replace(wordDict.get(i)," ");
                temp = temp.trim();
            }
        }
        if(temp.equals("")){
            return true;
        }

        wordDict.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.length() + o2.length();
            }
        });
        for (int i = 0; i < wordDict.size(); i++) {
            if(s.contains(wordDict.get(i))){
                s = s.replace(wordDict.get(i)," ");
                s = s.trim();
            }
        }
        if(s.equals("")){
            return true;
        }else {
            return  false;
        }
    }
    public int trap(int[] height) {
        int[] dp = new int[height.length];
        int[] sum = new int[height.length];
        dp[0] = 0;
        dp[1] = 0;
        sum[0] = height[0];
        sum[1] = height[0] +height[1];

        for (int i = 2; i < height.length; i++) {
            sum[i] = sum[i-1] + height[i];
            if(height[i]<height[i-1]){
                dp[i] = dp[i-1];
                continue;
            }
            for (int j = 0; j < i ; j++) {
                if(height[j]>=height[i]){
                    dp[i] = Math.max(dp[j] + (i-j-1)*height[i]-(sum[i-1]-sum[j]),dp[i]);
                }else{
                    dp[i] = Math.max(dp[j] + (i-j-1)*height[j]-(sum[i-1]-sum[j]),dp[i]);
                }
            }
        }
        return dp[height.length - 1];
    }
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int count = 1;
        for(int i:nums){
            if(map.containsKey(i)){
                map.put(i,2);
            }else{
                map.put(i,1);
            }
        }
        for(Integer i:map.keySet()){
            if(map.get(i)==1){
                 return i;
            }
        }
        return -1;
    }
    public int nthUglyNumber(int n) {
        if(n==1){
            return 1;
        }
        int s2 = 1;
        int s3 = 1;
        int s5 = 1;
        int[] sn = new int[n + 1];
        for(int i = 2;i<=n;i++){
            int num2 = sn[s2]*2;
            int num3 = sn[s3]*3;
            int num5 = sn[s5]*5;
            sn[i] = Math.min(Math.min(num2, num3), num5);
            if (sn[i] == num2) {
                s2++;
            }
            if (sn[i] == num3) {
                s3++;
            }
            if (sn[i] == num5) {
                s5++;
            }
        }
        return sn[n];
    }
    public int numTrees(int n) {
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int dp[] = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 3; i < n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n-1];
    }
    @Test
    public  void test() {
        String[] word= {"bc","ca"};
        StringBuffer s = new StringBuffer("cbca");
        nthUglyNumber(6);
        int t[] = new int[]{4,2,0,3,2,5};
        List<String> words = Arrays.asList(word);
        System.out.println(trap(t));
//        List<List<Integer>> test = new ArrayList<>();
//        LongestStrChain longestStrChain = new LongestStrChain();
//        System.out.println(longestStrChain.longestStrChain(words));
    }
}

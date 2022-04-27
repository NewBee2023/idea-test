package 指针;

import org.junit.Test;

import java.util.*;

/**
 * @author LiJing
 * @creat 2022-04-16-11:16
 */
public class solution {
    public void moveZeroes(int[] nums) {
        if(nums.length==1) return;
        int i = 0,j=0;
        while(i<nums.length&&j<nums.length){
            while(i<nums.length&&nums[i]!=0){
                i++;
            }
            while(j<nums.length&&nums[j]==0){
                j++;
            }
            if(i==nums.length||j==nums.length)break;
            if(i<j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                i++;
                j++;
            }else {
               j++;
            }
        }

    }
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for(int i = 0;i<numbers.length;i++){
            for (int j = i + 1; j < numbers.length; j++) {
                if((numbers[i]+numbers[j])==target){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
    public String reverseWords(String s) {
        int left = 0;
        int right = -1 ;
        char temp = 0;
        int templeft = 0;
        char[] s1 = s.toCharArray();
        for(int i = 0;i<s1.length;i++){
            if(s1[i]==' '){
                left = templeft ;
                templeft = i + 1;
                right = i ;
                while(left<right){
                    temp = s1[left];
                    s1[left] = s1[right-1];
                    s1[right-1] = temp;
                    left++;
                    right--;
                }
            }
            if(i==s1.length-1){
                left = templeft ;
                templeft = i + 1;
                right = i ;
                while(left<right){
                    temp = s1[left];
                    s1[left] = s1[right];
                    s1[right] = temp;
                    left++;
                    right--;
                }
            }

        }
        return String.valueOf(s1);
    }
    public boolean canThreePartsEqualSum(int[] arr) {
            int i = 0;
            int s1 = 0;

            for (;i<arr.length - 1; i++) {
                s1 += arr[i];
                for (int j = arr.length - 1; i<j-1; j--) {
                    int s2 = 0;
                    s2 += arr[j];
                    if(s1==s2&&s1==sum(i+1,j-1,arr)){
                        return  true;
                    }
                }
            }
            return false;
    }

    public int sum(int s,int e,int[] arr){
        int sum = 0;
        for(;s<=e;s++){
            sum+=arr[s];
        }
        return sum;
    }
    public String longestCommonPrefix(String[] strs) {
        int n = strs[0].length();
        String s = "";
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        int i = 0;
        for (; i < strs[0].length(); i++) {
            if(strs[0].charAt(i)!=strs[strs.length-1].charAt(i)){
                break;
            }
        }
        s = s + strs[0].substring(0,i);
        return s;
    }
    public int lengthOfLongestSubstring(String s) {
        if(s==null) return  0;
        Set set = new HashSet();
        int right = -1 ;
        int left = 0;
        int len = 1;
        for(int i = 0;i<s.length();){
            while(i<s.length()&&!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                i++;
            }
            len = Math.max(i-left,len);
            set.remove(s.charAt(left));
            left++;

        }
        return len;
    }
    public boolean checkInclusion(String s1, String s2) {
        byte[] ss1 = s1.getBytes();
        Arrays.sort(ss1);
        int mid = 0;
        for (int i = 0; i < s2.length(); i++) {
            if(s2.charAt(i)==s1.charAt(0)){
                mid = i;
                break;
            }
        }
        int start = Math.max(0,mid-s1.length());
        for (int i = start; i + s1.length()<= s2.length(); i++) {
//            if(ss1.length == 1) return ss1[0];
            byte[] ss2 = s2.substring(i,i+s1.length()).getBytes();
            Arrays.sort(ss2);
            if(Arrays.equals(ss1,ss2)){
                return true;
            }
        }
        return false;
    }
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length-1;
//        Map<Integer,Integer> map = new HashMap();
//        for (int i = 0; i < n; i++) {
//            map.put(nums[i],map.getOrDefault(nums[i],map.get(nums[i])+1));
//        }
        int t[] = new int[2*n+2];

        int i = 0;
        for (int j = 0; j < 2*n+2 && i < nums.length; ) {
            t[j] = nums[i++];
            while(i < nums.length&&nums[i - 1]==nums[i]){
                t[j] += nums[i];//既是a0又是s0
                i++;
            }
            if(i < nums.length&&nums[i]-nums[i-1]==1){
                j++;
            }else {
                j += 2;
            }
        }
        int a  = t[0];
        int b = Math.max(a,t[1]);
        int sum = 0;
        for (int k = 2; k < t.length; k++) {
            sum = Math.max(a + t[k], b);
            a = b;
            b = sum;
        }
        return sum;
    }
    int temp = 0;
    public int maxAreaOfIsland(int[][] grid) {
        Integer count = 0;
        for(int i = 0; i < grid.length;i++){
            for(int j =0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                   temp = 0;
                    count =  Math.max(back(grid,i,j),count);
//                    System.out.println(0);
                }
            }
        }
        return count;
    }

    public Integer back(int[][] image, int sr, int sc){
       if(sr<0||sc<0||sr>=image.length||sc>=image[sr].length||image[sr][sc]==0)
       { return 0;}
            temp++;
            image[sr][sc] = 0;
            back(image,sr+1,sc);
            back(image,sr,sc+1);
            back(image,sr-1,sc);
            back(image,sr,sc-1);
            return temp;
    }

    public int canJump(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int count = 0;
        int far = nums[0];
        int end = 0;
        for ( int i = 0; i < nums.length - 1;i++) {

           far =Math.max(far,nums[i]+i);
           if(i==end){
               end = far;
               count++;
           }
        }
        return count;
    }

     //* Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }
     public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
         if(root1==null){
             return root2;
         }
         if(root2==null){
             return root1;
         }
        TreeNode root = new TreeNode();
         root.val = root1.val + root2.val;
         root.left = mergeTrees(root1.left,root2.left);
         root.right = mergeTrees(root1.right,root2.right);
         return root;

    }
    public int maxSubArray(int[] nums) {

        return getInfo(nums, 0, nums.length - 1).msum;
    }
    class Status{
        int lsum;
        int rsum;
        int isum;
        int msum;
        public Status(int lSum, int rSum, int mSum, int iSum){
            this.lsum = lSum;
            this.rsum = rSum;
            this.msum = mSum;
            this.isum = iSum;
        }
    }
    public Status getInfo(int[] a,int s,int e){
        if(s==e){
            return new Status(a[s], a[s], a[s], a[s]);
        }
        int mid = (e-s)/2+s;
        Status lsub =  getInfo(a, s, mid);
        Status rsub =  getInfo(a, mid, e);
        return pushUp(lsub, rsub);
    }
    public Status pushUp(Status l, Status r) {
        int iSum = l.isum + r.isum;
        int lSum = Math.max(l.lsum, l.isum + r.lsum);
        int rSum = Math.max(r.rsum, r.isum + l.rsum);
        int mSum = Math.max(Math.max(l.msum, r.msum), l.rsum + r.lsum);
        return new Status(lSum, rSum, mSum, iSum);
    }
    public int maxSubarraySumCircular(int[] nums) {
        if(nums.length==1)return nums[0];
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res,maxSubArray(nums,i,i+nums.length));
        }
        return res;
    }
    public int maxSubArray(int[] nums,int s,int e) {
        int n = nums.length;
        int a = nums[s];
        int sn;
        int res = a;
        for (int i = s + 1; i < e; i++) {
            sn = Math.max(nums[i%n],a+nums[i%n]);
            res = Math.max(res,sn);
            a = sn;
        }
        return res;
    }
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                }else {
                    mat[i][j] = -1;
                }
            }
        }
        int[] dx = new int[]{-1,0,0,1};
        int[] dy = new int[]{0,-1,1,0};
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int X = cur[0] + dx[i];
                int Y = cur[1] + dy[i];
                if(X>=0&&Y>=0&X<m&&Y<n&&mat[X][Y]==-1){
                    mat[X][Y] = mat[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{X,Y});
                }
            }
        }

        return mat;
    }
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int[] dx = new int[]{-1,0,0,1};
        int[] dy = new int[]{0,-1,1,0};
        int count=0;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];
                if(x>=0&&y>=0&&x<m&&y<n&&grid[x][y]==1){
                    grid[x][y] = 2;
                    queue.offer(new int[]{x,y});
                }
            }
            count++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    return  -1;
                }
            }
        }
        return count;
    }

    public int maxProduct(int[] nums) {
        if(nums.length == 1) return  nums[0];
        int a = nums[0];
        int b = a;
        int smax = a;
        int smin = a;
        int res = a;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>0){
                smax = Math.max(nums[i],nums[i]*a);
                smin =nums[i]*b;
            }else if(nums[i]<0){
                smax = nums[i]*b;
                smin = Math.min(nums[i],nums[i]*a);
            }else {
                smax = 0;
                smin = 0;
            }
            res = Math.max(res,smax);
            a = smax ;
            b = smin ;
        }
        return  res;
    }
    public int getMaxLen(int[] nums) {
        if(nums.length == 1&&nums[0]==0) return  nums[0];
        if(nums.length == 1&&nums[0]!=0) return  1;
        int a;
        int b;
        if(nums[0]>0){
            a = 1;
            b = 1;
        }else if(nums[0]<0){
            a = -1;
            b = -1;
        }else {
            a = 0;
            b = 0;
        }
        int smax = a;
        int smin = a;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>0){
                smax = 1+a;         //if(nums[i]>0){
                smin = b>0?b+1:0;                //smax = Math.max(2,2*a);
            }else if(nums[i]<0){                       //smin =2*b;
                smax = b>0?b+1:0;        //}else if(nums[i]<0){
                smin = a+1;               //smax = (-2)*b;
            }else {                                    //smin = Math.min((-2),(-2)*a);
                smax = 0;
                smin = 0;
            }
            res = Math.max(res,smax);
            a = smax ;
            b = smin ;
        }
        return res;
    }
    class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        Stack<ListNode> listNodeStack = new Stack<>();
        ListNode temp = head;
        while(temp!=null){
            listNodeStack.push(temp);
            temp = temp.next;
        }
        ListNode newHead = listNodeStack.pop();
        temp = newHead;
        while (!listNodeStack.isEmpty()){
            temp.next = listNodeStack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return newHead;
    }
    public int maxScoreSightseeingPair(int[] values) {
        int b = values[0];
        int a = values[0];
        if(values.length==2){
            return values[0]+values[1]-1;
        }
        int sum = 0;
        for (int i = 1; i < values.length; i++) {
            a = Math.max(a,values[i] - i);
            b = Math.max(b,values[i] + i);
            sum = Math.max(values[i] + i + a,sum);
        }
        return sum;
    }
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper(int n, int k, int startIndex){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++){
            path.add(i);
            combineHelper(n, k, i + 1);
            path.removeLast();
        }
    }

    public int maxProfit(int[] prices, int fee) {
        if(prices.length==1){
            return 0;
        }

        int b = -prices[0] -fee;
        int c = 0;
        int a = 0;
        int a1 = b;
        int b1 = c - prices[1] - fee;
        int c1 = b+prices[1];
        int sn = Math.max(0,prices[1]-prices[0]-fee);
        for(int i = 2;i<prices.length;i++){
            a = a1;
            b = b1;
            c = c1;
            a1 = Math.max(a, Math.max(b,c));
            b1 = c - prices[i] - fee;
            c1 = Math.max(b+prices[i],a+prices[i]);
        }
        return Math.max(a,c);
    }
    @Test
    public void test(){
        int[] t = new int[]{1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(t,2));

        String s = "avs";
        s.toLowerCase();
        s.charAt(0);
        StringBuffer sb = new StringBuffer("svf");



//        int[] t = new int[]{-83,0,13,25,58,8,54,72,-80,6,53,-47,-73,62,-86,-29,-23,-30,-86,49,40,-76,-76,-83,-86,-4,17,-23,-84,-27,27,13,44,-50};
//        int[] t1 = new int[]{0,2,1,-6,6,-7,9,1,2,0,1};
//        String s1 = "ab";
//        String s2 = "ba";
//
////        moveZeroes(t);
//        String[] strs = new String[]{"a","b"};
//
//        int[][] image = new int[][]{{0,0,0},{0,1,0},{1,1,1}};
//        TreeNode root1 = new TreeNode(1);
//        TreeNode root2 = new TreeNode(1);
//        root2.left = new TreeNode(2);
//        mergeTrees(root1,root2);
//        canJump(t);

//        System.out.println(Arrays.deepToString(updateMatrix(image)));
//        longestCommonPrefix(strs);
//        System.out.println(getMaxLen(t));

//        System.out.println(canThreePartsEqualSum(t1));
    }
}

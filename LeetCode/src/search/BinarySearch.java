package search;

import org.junit.Test;

/**
 * @author LiJing
 * @creat 2022-04-14-22:23
 */
public class BinarySearch {
    public int search(int[] nums, int target) {
        if(nums[0]>target||nums[nums.length-1]<target) return -1;
        return Binary(nums,target,0,nums.length-1);
    }

    public int Binary(int[] nums,int target,int s,int e){
        if(s>e) return -1;
        if(nums[(s+e)/2]==target){
            return (s+e)/2;
        }else if(nums[(s+e)/2]>target){
            return Binary(nums,target,s,(s+e)/2-1);
        }else{
            return Binary(nums,target,(s+e)/2+1,e);
        }
    }
    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        int x = 0;
        for(int i = nums.length-k;i<nums.length;i++,x++){
            res[x] = nums[i];
        }
        for(int i = 0;i < nums.length-k;i++,x++){
            res[x] = nums[i];
        }

        for(int i = 0,j=0;i < nums.length;i++,j++){
            nums[i] = res[j];
        }
    }
    @Test
    public void test(){

        int[] nums = new int[]{1,2,3,4,5,6,7};
        rotate(nums,3);
        System.out.println(search(nums,2));
    }
}

/**
 * @author 崔秦
 * @version	1.0
 */
//尝试的思路：操作数组中的数字 判断数组的数字的值；获得所有不为零的元素的索引值，然后由零的个数计算出他们新的索引值然后创建新的数组输出
//然后没有赋值的系统默认为零，如果移动的不是零，可以设定默认值是移动的值以后再给新的数组赋值
public class MoveZero {
	public static void main(String[] args)
	{
		int[] nums = {0,1,0,3,12};
		int[] newArr = new int[nums.length];
		int num = 0;
		for(int i = 0; i<nums.length; i++)
		{
			if(nums[i]!=0)
			{
				newArr[num] = nums[i];
				num++;
			}
		}
		
		nums = newArr;
		//下面是测试新的数组的元素值的代码
		for(int book:nums)
		{
			System.out.println(book);
		}
	}
}

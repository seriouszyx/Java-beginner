import java.util.*;

/**
 * @author 崔秦
 */
//思路：改变的只是数组的索引值而不是数组的元素，因此可以用元素当做标记获得一开始的序号
//从索引值开始判断：第四个去掉，然后将后面的全部按顺序提到前面重新排序，两个循环：第一个是取出后面的到新的数组，第二个
//是将前面的取出到新的数组的后面，然后重新自我赋值，第二个元素为零的时候停止
//最终思路：递归公式：由规律，由前往后推，设置序号(0~n-1),一共n个人，数第m-1数，得知第二次循环开始的人原来序号是m%n(有负数)，然后重新制作序号，
/*
 * 	k		0
 * 	k+1		1
 *  ...
 *  0		m-1-k
 *  ..
 *  k-2		m-2
 *  k-1		m-1
 * 
 * 
 * 	参考博客：https://blog.csdn.net/xs1997/article/details/53888408
 */
//输出输入流
public class NotFour {
	public static int fn(int n)
	{
		int m = 4;
		if(n == 1)
		{
			return 0;
		}
		else
		{
			return (fn(n-1)+m)%n;//数学公式：尝试的模拟过程无法实现高位
		}
	}
	public static void main(String[] args)
	{
		int num = 0;//获取最后的数组的元素值
		System.out.println("请输入组的人数");
		Scanner sc = new Scanner(System.in);
		//sc.useDelimiter("\n");
		while(sc.hasNextInt())
		{
			//输入的N值
			int N = sc.nextInt();
			
			System.out.println("最后的组长是原来序号为"+(fn(N)+1));
		}
	}
}
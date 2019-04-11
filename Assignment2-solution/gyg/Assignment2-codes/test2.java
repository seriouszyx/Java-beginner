import java.util.Scanner;
import java.util.Arrays;

class MoveZero{
	 public static void  Move(int[] nums){
		int i;
		int num = 0; //璁板綍闈�0鐨勪綅缃�
		for (i = 0; i < nums.length; i++)
		{
			if (nums[i] == 0)
		    {
				continue;//涓�0鍒欒烦杩�
		    }
		    else
		    {
		    	nums[num] = nums[i];
		    	num = num + 1;
		    }
		}
		for (i = num; i<nums.length; i++)
		{
			nums[i] = 0;
		}
		System.out.println(Arrays.toString(nums));
	}
}

class Nofour{
	Nofour(int N){
		int Team[] = new int[N];
		for(int i = 0; i < Team.length; i++) {
			Team[i] = i + 1;
		}
		int index = 0; 
		int loop = 1;
		while(N!=0) {
			 if(index==Team.length) {
				 index=0;
			 } 
			if(loop == 4 && Team[index] != 0) {
				Team[index] = 0; 
				N--;
				loop++;
				if(loop == 5) {
					loop = 1;
				};
			}
			if(Team[index] != 0) {
				loop++;
				if(loop == 5) {
					loop = 1;
				}
			}
			index++; 
		}
		System.out.println(index);
	}
}
public class test2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请选输入0选择移动0，输入1选择选组长");
		int select = sc.nextInt();
		if(select == 0) {
			Scanner zerosc = new Scanner(System.in);
			System.out.println("请输入数组，以空格隔开");
			String str = zerosc.nextLine().toString();
			String arr[] = str.split(" ");
			int a[] = new int[arr.length];
			for(int j = 0; j < a.length; j++)
			{
				a[j] = Integer.parseInt(arr[j]);
			}
			MoveZero move = new MoveZero();
			move.Move(a);
		} else {
			System.out.println("请输入人数");
			Scanner nofoursc = new Scanner(System.in);
			int num = nofoursc.nextInt();
			Nofour nofour =new Nofour(num);
		}
	}
 
}
import java.util.*;

public class SelectLeader {
	
	public static void main(String[] args) {
		System.out.println("请输入竞选组长的总人数：");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		
		Boolean[] isLeader = new Boolean[number];
		for(int i = 0; i < isLeader.length; i++) {
			isLeader[i] = true; //开始的时候每个人都没被淘汰，所以为true
		}
		
		int j = 0, k = 0; //k表示被淘汰的人数，j用来数数。
		for(int i = 0; i < isLeader.length; i++) {
			
			if(k < isLeader.length - 1) {//只有淘汰人数大于一的时候才执行。
				if(isLeader[i]) {
					j++;//只有没被淘汰的人才被数
				}
				if(j == 4) {
					isLeader[i] = false;
					j = 0;
					k++;
				}
				if(i == (isLeader.length - 1)) {
				    i = -1;
			    }
			}else {
				break;
			}
		}
		for(int i = 0; i < isLeader.length; i++) {
			if(isLeader[i]) {
				System.out.println("组长是编号为：" + (i + 1) + " 的人，让我们恭喜他。");
			}
		}
		//System.out.println(Arrays.toString(isLeader));
	}
}

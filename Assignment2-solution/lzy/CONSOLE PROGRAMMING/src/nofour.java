import java.lang.*;
import java.io.*;
import java.util.Scanner;
public class nofour {
	public static void main(String[] args) {
		System.out.print("人数： ");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		
		Boolean[] num = new Boolean[number];
		for(int i = 0; i < num.length; i++) {
			num[i] = true;
		}
		
		int j = 0, k = 0;
		for(int i = 0; i < num.length; i++) {
			if(k < num.length - 1) {
				if(num[i]) {
					j++;
				}
				if(j == 4) {
					num[i] = false;
					j = 0;
					k++;
				}
				if(i == (num.length - 1)) {
				    i = -1;
			    }
			}
			else {
				break;
			}
		}
		for(int i = 0; i < num.length; i++) {
			if(num[i]) {
				System.out.println("组长原始序号 ： " + (i + 1));
			}
		}
	}
}

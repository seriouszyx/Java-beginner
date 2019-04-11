import java.lang.*;
import java.io.*;

public class zeroproblem {
	 public static void main(String[] args) {
		 
		 int size=5;
		 int m=0,n=0,i,j=0;
		 int[] number = new int[size];
		 int[] zero=new int[size];
		 int[] nozero = new int[size];
		 number[0]=0;
		 number[1]=1;
		 number[2]=0;
		 number[3]=3;
		 number[4]=12; 
		 System.out.print("[ ");
		 for(i=0;i<size;i++) {
			 System.out.print(number[i]+" ");
		 }
		 System.out.println("]");
		 for(i=0;i<size;i++) {
			 if(number[i]==0) {
				 zero[m]=0;
				 m++;
			 }
				 
			 else
			 {  
				 nozero[j]=number[i];
				 j++;
			 }
		 }
		 System.out.print("[ ");
		 for(int k=0;k<j;k++) {
			 System.out.print(nozero[k]+" ");
		 }
		 for(int k=0;k<m;k++) {
			 System.out.print(zero[k]+" "); 
		 }
		 System.out.println("]");	 
	 }
}

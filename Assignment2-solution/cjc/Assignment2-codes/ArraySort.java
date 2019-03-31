import java.util.Arrays;

public class ArraySort {

	public static void main(String[] args) {
		int[] array_1 = {0, 1, 0, 3, 12};
	    int[] array_2 = new int[5];
	    int j = 0;
	    for(int i = 0; i < array_1.length; i++) {
	    	if(array_1[i] != 0) {
	    		array_2[j] = array_1[i];
	    		j++;
	    	}
	    }
	    System.out.print(Arrays.toString(array_1) + "经过排序之后的结果为：");
	    System.out.println(Arrays.toString(array_2));
	}
	
}

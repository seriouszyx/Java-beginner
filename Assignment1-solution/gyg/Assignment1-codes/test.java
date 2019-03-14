


class Age{
	int num;
}

public class test {
	public static void main(String[] args) {
		Age people1 = new Age();
		Age people2 = new Age();
		people1.num = 2;
		people2.num = 3;
		System.out.println("people1 = "+ people1.num+"  "+ "people2 = "+people2.num);
		people2 = people1;
		people1.num = 0;
		System.out.println("people1 = "+ people1.num+"  "+ "people2 = "+people2.num);
	}
}
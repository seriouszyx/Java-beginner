## 简答题
1.	包含八种基本数据类型：
	*	byte
	*	short
	*	int
	*	long
	*	char
	*	float
	*	double
	*	boolean
以及引用数据类型:
	*	String	字符串型
	*	Array	数组型
自动装箱：可以把一个基本类型直接赋给对应的包装类变量或者Object变量
自动拆箱：直接把包装类对象直接赋给一个对应的基本类型变量
```
public static void main(String[] args)
	{
		//直接把基本类型变量直接赋给Integer包装类或者Object类型变量
		Integer inObj = 5;
		Object boolObj = true;
		//直接把Integer(包装类对象)赋给int(基本类型)变量
		int it = inObj;
		if(boolObj instanceof Boolean)
		{
			//object --> boolean 来实现自动拆箱(自动拆装箱类型必须一一对应)
			boolean b = (Boolean)boolObj;
			System.out.println(b);
		}
```
2.	*	接口类似于系统的总纲，制定了系统各模块应该遵循的标准，不能经常被改写，否则会辐射型的影响，大部分类都要改变；而抽象类体现的是一种模板设计，是作为多个子类的抽象父类，是中间产品。
	*	接口只能包含抽象方法和默认方法，不能使用普通方法；抽象类可以包含普通方法
	*	接口不包含构造器，因而只能定义静态常量；抽象类中包含构造器，可以定义普通成员变量和静态常量
	*	接口不包含初始化块；抽象类包含初始化块
	*	抽象类只能有一个直接父类；而一个类可以直接实现多个接口
3.	*	字符串直接量，JVM会使用常量池管理
	*	对于String s = new String("a");JVM会先使用常量池管理直接量，然后调用呢String类的构造器创建一个新的对象，对象会被保存在堆内存中
	*	而常量池中的字符a是一个可以引用的对象，因此一共创建了两个对象
```
public class 常量池 {
	public static void main(String[] args)
	{
		//介绍JVM管理字符串直接量
		//直接引用常量池中的"abc"
		String s1 = "abc";
		String s2 = "a";
		String s3 = "c";
		//字符串在编译的时候就确定下来，也是直接引用常量池中的"abc"
		String s4 = "ab" + "c";
		String s5 = "a" + "b" +"c";
		//不能在编译的时候确定下来的是调用变量值
		String s6 = s1 + s2 + s3;
		//new 一个新的对象
		//引用堆内存中新创建的对象
		String s7 = new String("abc");
		
		System.out.println((s1 == s4)+"\n"+(s1 == s5)+"\n"+(s1 == s6)+"\n"+(s1 == s7));
	/**
	 * 	直接引用的常量池的值的变量比较输出为true，因为它们实际上是引用的同一个字符串对象，编译时就可以确定
	 * 	new String()对象是在运行时创造出来的，运行时被保存在内存区，不会放入常量池中
	 */
	}
}
```
!(常量池)[常量池.png]

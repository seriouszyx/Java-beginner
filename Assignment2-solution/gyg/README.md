## Java支持的数据类型有哪些？[什么是自动拆装箱？](https://www.jb51.net/article/111847.htm)
+ 1.基本数据类型
+Java基本类型共有八种，基本类型可以分为三类：
字符类型char
布尔类型boolean
整数类型byte、short、int、long
浮点数类型float、double。
**Java中的数值类型不存在无符号的，它们的取值范围是固定的，不会随着机器硬件环境或者操作系统的改变而改变.**
+ 2、包装类型

| 基本数据类型 | 包装类 |
| :-----:|:-----: |
|byte|Byte|
| boolen|Boolen |
| short  | Short |
| char  |Character |
| int   |integer  |
| long  |Long |
| float    |Float |
| double   |Double |
1. 定义
   + **拆箱** 就是把Long，Integer，Double，Float 等将基本数据类型的首字母大写的相应的引用类型转化为基本数据类型的动作就叫拆箱。
   + **装箱**就是把byte ，int ，short， long ，double，float，boolean，char 这些Java的基本数据类型在定义数据类型时不声明为相对应的引用类型，在编译器的处理下自动转化为引用类型的动作就叫做装箱。
   + 因为这里的装箱和拆箱是自动进行的非人为转换，所以就称作为自动装箱和拆箱。
2. 原理
   + 自动装箱时编译器调用valueOf将原始类型值转换成对象，同时自动拆箱时，编译器通过调用类似intValue(),doubleValue()这类的方法将对象转换成原始类型值
2. 自动装箱、拆箱中的坑
   +  第1段代码，基础类型a与包装类b进行==比较，这时b会拆箱，直接比较值，所以会打印true
   +  第2段代码，二个包装类型，都被赋值了100，所以根据我们之前的解析，这时会进行装箱，调用Integer的valueOf方法，生成2个Integer对象，引用类型==比较，直接比较对象指针，这里我们先给出结论，最后会分析原因，打印 true
   + 跟上面第2段代码类似，只不过赋值变成了200，直接说结论，打印 false
```java
   public void testAutoBox2() {
        //1
        int a = 100;
        Integer b = 100;
        System.out.println(a == b)；
        //2
        Integer c = 100;
       Integer d = 100;
       System.out.println(c == d);
       //3   
       c = 200;
       d = 200;
        System.out.println(c == d);
   }
```
```java
    public static Integer valueOf(int i) {
       if (i >= IntegerCache.low && i <= IntegerCache.high)
         return IntegerCache.cache[i + (-IntegerCache.low)];
     return new Integer(i);
   }
```
+ IntegerCache的范围是-128到128,200超出了IntegerCache所以返回return new Integer(i)，所以两者不相等
## [接口和抽象类的区别是什么？](https://blog.csdn.net/My_name_is_ZwZ/article/details/80001121)
1. 抽象类是用abstract修饰的类叫做抽象类。
   + 有抽象方法的类一定是抽象类，但是抽象类中不一定有抽象方法
   + 抽象类不能有对象，（不能用new此关键字来创建抽象类的对象）
   + 抽象类中的抽象方法必须在子类中被重写
   + 抽象类生来就注定它是要被继承的，如果没有任何一个类去继承它的话，那么也就失去了它的意义；抽象方法生来就是要被重写的，而且是必须重写。
2. 接口通过interface关键字来完成
   + 接口中的所有属性默认为：public static final 
   + 接口中的所有方法默认为：public abstract
   + 接口不再像类一样用关键字 extends去“继承”，而是用 implements 去“实现”
   + 例如：A类实现了B接口，那么成为A为B接口的实现类。而类与类之间的继承的话，叫做A类继承了B类，其中B类即为A类的父类
3. 区别
   + 抽象类描述的是“是不是”的问题，而接口描述的是“有没有”的问题；
   + 在Java中类的继承是“单继承”，可以“多对一”，但是不允许“一对多”。而一个类却可以同时实现多个接口；
## String s = new String(“abc”); 创建了几个对象？为什么？
+ 创建了一个对象，String相当于是确定等号右边的类型，而等号右边("abc")相当于是把abc连起来成为一个字符串，左边的s就是对右边这个String("abc")的引用，因此只创建了一个对象。

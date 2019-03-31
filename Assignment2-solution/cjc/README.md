### Java支持的数据类型有哪些？什么是自动拆装箱？
answer:
1. Java中共有8种基本数据类型，包括4 种整型、2 种浮点型、1 种字符型、1 种布尔型<br/>
- **byte**：8位、有符号。最大值127（-2^6）、最小值-128（2^7）。默认值为0；
- **short**：16位、有符号。最大值-32768（-2^14）、最小值（2^15）。默认值为0；
- **int**：32位、有符号。最大值2,147,483,647（2*30）、最小值-2,147,483,647（-2^31）。默认值为0；
- **long**：64位、有符号。最大值2^62，最小值-2^63。默认值为0L；
- **float**：32位，单精度。近似范围1.4e-045〜3.4e + 038，该数据不能用来表示精确的值（货币）。默认值为：**0.0f**；
- **double**：64位，双精度。近似范围4.9e-324〜1.8e + 308，该数据同样不能表示精确的值。默认值为：**0.0d**；
- **boolean**：只有两种取值：true和false。默认为：false；
- **char**：只能取单一的字符，并用单引号包住。
2. 引用类型，引用类型指向对象。

3. 自动拆装箱：自动装箱就是Java自动将原始数据类型值转化成对应的对象，比如将`int`类型转化成`Integer`对象，这个过程叫做装箱。反之，将`Integer`对象转化成`int`类型值，这个过程叫做拆箱。因为这里的装拆箱是非人为转化，所以就称为自动装箱和拆箱。原始类型byte,short,char,int,long,float,double和boolean对应的封装类为Byte,Short,Character,Integer,Long,Float,Double,Boolean。
>自动拆装箱的目的：
>让我们再Java的变量赋值或者对象调用等情况下使用原始数据类型过着对象类型更加直接。说白了就是为了简化代码，方便使用。因为再java1.5版本之前是没有自动装箱和拆箱的，在赋值的、方法调用的时候就会变得可“麻烦”：
```java
// 赋值的时候

// before java1.5
Integer iObject = Integer.valueOf(3);
Int iPrimitive = iObject.intValue();

// after java1.5
Integer iObject = 3;
int iPrimitive = iObject;


// 方法调用的时候

public static Integer show(Integer iParam){ //参数是Integer对象
   System.out.println("autoboxing example - method invocation i: " + iParam);
   return iParam;
}

// before java1.5
int result = show(3); //因为返回的是Integer对象类型，所以还需要进行数据类型转换

// after java1.5
show(3); //自动拆装箱功能自动为我们转化数据类型。
```
>自动装箱的弊端：
>在一个循环中可能会创建多余的对象。影响程序性能加重垃圾回收的工作量。
```java
Integer sum = 0;
 for(int i=1000; i<5000; i++){
   sum+=i;
}
//在这个过程中，每一次循环就会执行如下的代码：
//int result = sum.intValue() + i;
//Integer sum = new Integer(result);
//也就是说每一次循环就会产生一个Integer对象，整个循环下来就足足产生了4000个无用的Integer对象。可怕。
```
>[参考](https://droidyue.com/blog/2015/04/07/autoboxing-and-autounboxing-in-java/)

### 接口和抽象类的区别是什么？
answer：
- 抽象类：使用abstract修饰符修饰的**类**。
- 接口：是抽象方法的**集合**。
- 区别：<br/>
1. 默认方法的实现：<br/>抽象类中可以有已经实现的方法，也可以有被abstract修饰的方法。有抽象方法的类必须也是抽象的。<br/>而接口就根本不存在方法的实现。
```java
// 抽象类：
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {
    // abstract method
    abstract void service(ServletRequest req, ServletResponse res);
 
    void init() { //已经实现的方法
        // Its implementation
    }
    // other method related to Servlet
}

//接口：
public interface Externalizable extends Serializable {
    // 可以看出都是没有**实现的方法**，但不用abstract修饰。
    void writeExternal(ObjectOutput out) throws IOException;
 
    void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
}
```
2. 实现：<br/>**抽象类**，子类使用**extends**关键字来继承抽象类。在**子类不是抽象类的情况下**，子类需要提供抽象类中所有声明的方法的实现。<br/>**接口**，子类使用关键字**implements**来实现接口，它需要提供接口所有声明的方法的实现。（**注意**，实现接口的类就必须实现接口中所有声明的方法，如果类没有能力就不要“接”，但是对于继承抽象类的子类来说，它就可以不要用实现（子类再标记成抽象类就ok了，让子类的子类一并实现它爸爸和它爷爷的所有方法））。
3. 构造器：<br/>抽象类可以有构造器，而接口不能有构造器。<br/>抽象类毕竟是类，它可以有构造方法，但是接口是一对没有实现方法的集合，肯定没有构造函数，没有构造器。
4. 修饰符：<br/>抽象类中的方法可以有public、protected和default，但是接口的方法默认修饰符是public，不能用其他修饰符（这些方法都需要实现）。抽象类中甚至可以没有抽象的方法，全是已经实现的方法。

5. 多继承：<br/>类的继承没有多继承，子类只有一个父类，但可以实现多个接口，接口可以继承多个接口。
6. 速度：<br/>抽象方法的速度要比接口的速度快，因为接口需要时间去寻找在类中实现的方法。
7. 新方法的实现：<br/>对于抽象类，抽象类中可以添加一个实现了的方法，即默认实现，这样它的子类就不需要再去实现，这样它的子类就不需要改动。<br/>但是接口中只能添加抽象的方法，然后实现它的所欲类都必须改动，去实现新增的方法。
![再来个图](https://i.loli.net/2019/03/18/5c8f09b3d7c44.png)

### String s = new String(“abc”); 创建了几个对象？为什么？
answer：
创建了一个对象，`=`左边`String s`是要求java虚拟机分配空间给引用变量，并将此变量命名为“s”。此变量被永远固定为`String`类型。`=`右边`new String(“abc”)`表示创建一个对象，要求Java虚拟机分配**堆**空间给新建的String对象。“=”表示将“s”引用变量与对象连接起来。还是相当于s是遥控器，String对象是电视机，“=”相当于将配置遥控器让遥控器可以操作电视机。

### 为什么 Java 被称作是“平台无关的语言”？
answer：
- 首先先说一下什么是**平台**。<br/>
平台是CPU和操作系统的整体。不同的CPU使用相同或不同的指令集，而不同的操作系统支持不同的CPU，（但是现在主流的操作系统都支持主流的CPU），例如 windows和liunx都支持Intel和AMD的复杂指令集，但并不支持PowerPC所使用的精简指令集，而早期的MAC电脑使用的是PowerPC处理器，所以也就无法在MAC下直接安装windows。
- 平台无关的语言，也就是说可以再不同的平台上无障碍运行，即一次编写处处运行。而Java之所以能做到，有赖于它的执行方式，即先编译后解释。就是说在一个平台写好的程序（.java文件）通过编译会生成.class文件（字节码文件），而这个字节码是所有平台都可以通过JVM（Java虚拟机）来解释的，不同平台的不同的JVM会解释生成不同的但适合本平台的机器指令，然后执行处相同的结果。就这样Java通过.class文件，这个中间的小东东实现了跨平台。类似于科幻电影中的宇宙通用语言一样，有一个宇宙通用语言之后，各个文明的人们只需要都去学习这一门语言，就可以实现与宇宙任何一个人交流（当然前提是对方也会通用语言）。.class就相当于那个通用语言。
[参考内容](https://blog.csdn.net/LB_fighting/article/details/52880079)

### JDK 和 JRE 的区别是什么？
answer：
- 首先解释JDK，JDK是针对开发人员的软件开发包。它提供了软件的开发环境和运行环境。
- 而JRE(Java Runtime Environment)是面向java程序的使用者，是Java的运行环境。只有JVM是无法让class文件运行的，在解释class文件的时候JVM需要调用解释所需要的**类库**，而这个类库在jre目录里的bin文件中。
- 看一下JDK的安装目录，会发现有很多文件夹，其中就有jre，而jre里面就是bin文件，和lib文件，bin文件里面可以认为是JVM，而bin里面就是类库。大致有这样一个关系，jdk包含jre，而jre包含jvm。但是仔细一卡会发现，其实外面还有一个jre文件，这个jre文件是与jdk文件同一级别的，并不是jdk文件的子文件。为什么要装两套jre呢？原因就是jdk里面的工具好多都是Java写的，所以这些工具也算Java应用程序，因此要使用jdk所附的工具来开发程序，就需要自行配置一套jre来用。
[参考资料](https://www.cnblogs.com/myitm/archive/2011/05/03/2035942.html)

### 什么是值传递和引用传递？
[参考内容](https://www.zhihu.com/question/31203609/answer/50992895)
- 值传递：形参是实参的一个拷贝，再方法中对形参的值进行改变并不影响外部实参的值。也就是说，值传递是单向的，参数的值只能再方法中为所欲为，不能传出。
- 引用传递：形参就是实参的别名，对形参的操作就是对实参的操作。形参上面存放的是实参变量的地址，对形参进行操作，就会通过这个地址找到实参，对实参进行同样的操作。也就是说被调函数对形参的任何操作都会被处理成间接寻址。正因为如此，被调函数对参数做任何操作都会影响主调函数中实参变量。从实参，形参在内存中存放地址的角度说明问题的本质，容易理解：
```c++
#include<iostream>
using namespace std;
//值传递
 void change1(int n){
    cout<<"值传递--函数操作地址"<<&n<<endl;         //显示的是拷贝的地址而不是源地址 
    n++;
}

//引用传递
void change2(int & n){
    cout<<"引用传递--函数操作地址"<<&n<<endl; 
    n++;
}
 } 
int main(){
    int n=10;
    cout<<"实参的地址"<<&n<<endl;
    change1(n); 
    cout<<"after change1() n="<<n<<endl;
    change2(n);
    cout<<"after change2() n="<<n<<endl;
    return true;
}
```
结果为![result](https://i.loli.net/2019/03/16/5c8cdfc1eeab0.png)
从结果可以看出，采用值传递的时候，函数操作地址并不是实参本身，所以对它进行操作并不能改变实参的值；但是引用传递操作的地址就是实参地址，所以对它的操作影响就自然的加到了对实参的影响上去。
- 但是在Java中只有值传递，没有引用传递。最容易动摇这个观点的就在——java基本数据类型传递和引用传递这个过程。
1. 基本类型传递时：看下面的例子：
```java
package com.zejian.test;

public class CallByValue {
	
    private static int x=10;
	
    public static void updateValue(int value){
        value = 3 * value;
    }
	
    public static void main(String[] args) {
        System.out.println("调用前x的值："+x);    // 调用前x的值：10
        updateValue(x);
        System.out.println("调用后x的值："+x);    // 调用后x的值：10
    }	
}
```
很显然，在这种情况中，是值传递，value只是x的一个拷贝，value = 3 * value之后，是value变成了30而不是x；
2. 引用类型的传递过程：
```java
package com.zejian.test;
public class User {
    private String name;
    private int age;
    public User(String name, int age) {
        this.name=name;
        this.age=age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

//执行类如下：
package com.zejian.test;

public class CallByValue {
    private static User user=null;
    public static void updateUser(User student){
        student.setName("Lishen");
        student.setAge(18);
    }
	
	
    public static void main(String[] args) {
        user = new User("zhangsan",26);
        System.out.println("调用前user的值："+user.toString());
        updateUser(user);
        System.out.println("调用后user的值："+user.toString());
    }
}
```
调用前user的值：User [name=zhangsan, age=26]
调用后user的值：User [name=Lishen, age=18]<br/>
这个结果很容易让人以为这是按引用调用（引用传递）的。其实不然，让我们来分析一下这个过程，user这个对象引用指向User对象，而`updateUser(user)`操作之后，引用参数student拷贝了user的值，此时student这个引用也指向User对象。如果大哥比方的话，相当于，User对象是个电视机，而uer对象引用就是这个电视机的遥控器，调用 `updateUser(user)` 方法之后，这个遥控器被仿制了而且是高仿的，它也能对电视机为所欲为。所以，在user和student都指向同意个User对象的情况下，谁对它操作都是有用的（当然这个谁都能操作是由范围的，updateUser(user)被调用过之后，student及准备等死吧，它没有机会在对电视机怎么样了）<br/>
所以上面的就不是按引用传递。因为，student和user都可以对User操作，而不是student找到user然后让user去帮他操作User。
关于值传递和引用传递的例子在这[戳我](https://www.cnblogs.com/yanlingyin/archive/2011/12/07/2278961.html)<br/>
其他参考如下[戳我呗](https://blog.csdn.net/javazejian/article/details/51192130)[还有我](https://www.zhihu.com/question/31203609/answer/50992895)
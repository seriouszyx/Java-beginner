## Java支持的数据类型有哪些？什么是自动拆装箱？
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
+ **拆箱** 
就是把Long，Integer，Double，Float 等将基本数据类型的首字母大写的相应的引用类型转化为基本数据类型的动作就叫拆箱。
+ **装箱**
就是把byte ，int ，short， long ，double，float，boolean，char 这些Java的基本数据类型在定义数据类型时不声明为相对应的引用类型，在编译器的处理下自动转化为引用类型的动作就叫做装箱。
## 接口和抽象类的区别是什么？
+ 抽象类是用abstract修饰的类叫做抽象类。
+   有抽象方法的类一定是抽象类，但是抽象类中不一定有抽象方法
+   抽象类不能有对象，（不能用new此关键字来创建抽象类的对象）
+   抽象类中的抽象方法必须在子类中被重写

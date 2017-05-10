/**
  * Created by ahan on 10/05/2017.
  */


/**
  * 1、重写一个非抽象方法必须使用override修饰符。
2、只有主构造函数才可以往基类的构造函数里写参数。
3、在子类中重写超类的抽象方法时，你不需要使用override关键字。


  http://www.runoob.com/scala/scala-classes-objects.html

  * @param xc
  * @param yc
  */

class Point(val xc: Int, val yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点: " + x);
    println ("y 的坐标点: " + y);
  }
}

class Location(override val xc: Int, override val yc: Int, val zc:Int) extends Point(xc, yc) {

  var z: Int = zc;

  def move(dx: Int, dy: Int, dz: Int): Unit = {
    x = x + dx;
    y = y + dy;
    z = z + dz;

    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
    println ("z 的坐标点 : " + z);
  }
}

class Person {
  var name = ""
  override def toString = getClass.getName + "[name=" + name + "]"
}

class Employee extends Person {
  var salary = 0.0
  override def toString = super.toString + "[salary=" + salary + "]"
}



object Test {


  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  def main(args: Array[String]) {
    val pt = new Point(10, 20);
    val loc = new Location(10, 20, 15);
    // 移到一个新的位置
    pt.move(10, 10);

    loc.move(10, 10, 5);
    //loc.move(10, 10);

    val fred = new Employee
    fred.name = "Fred"
    fred.salary = 50000
    println(fred)

    println(matchTest(3))
  }
}

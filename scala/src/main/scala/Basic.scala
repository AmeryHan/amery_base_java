/**
  * Created by ahan on 09/05/2017.
  */

object Basic {
  def main(args: Array[String]): Unit = {
    var myVar = 10;
    val myVal = "Hello, Scala!";

    println(myVal);
    println(myVar);

    var a = 10;
    var b = 20;
    var c = 25;
    var d = 25;
    println("a + b = " + (a + b) );
    println("a - b = " + (a - b) );
    println("a * b = " + (a * b) );
    println("b / a = " + (b / a) );
    println("b % a = " + (b % a) );
    println("c % a = " + (c % a) );

    println("a == b = " + (a == b) );
    println("a != b = " + (a != b) );
    println("a > b = " + (a > b) );
    println("a < b = " + (a < b) );
    println("b >= a = " + (b >= a) );
    println("b <= a = " + (b <= a) );

    var e = true;
    var f = false;
    println("---------");

    println("a && b = " + (e&&f) );

    println("a || b = " + (e||f) );

    println("!(a && b) = " + !(e && f) );

    // 定义整型 List
    val x = List(1,2,3,4)

    // 定义 Set
    var x1 = Set(1,3,5,7)

    // 定义 Map
    val x2 = Map("one" -> 1, "two" -> 2, "three" -> 3)

    // 创建两个不同类型元素的元组
    val x3 = (10, "Runoob")

    // 定义 Option
    val x5:Option[Int] = Some(5)
  }
}
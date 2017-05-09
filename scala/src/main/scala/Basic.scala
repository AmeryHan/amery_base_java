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
  }
}
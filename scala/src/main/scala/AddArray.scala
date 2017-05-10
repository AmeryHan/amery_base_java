
import scala.Array._

/**
  * Created by ahan on 09/05/2017.
  */
object AddArray {
  def addInt(a:Int, b:Int) : Int = {
    var sum: Int = 0
    sum = a + b

    return sum;
  }

  var factor = 3;
  val multiplier = (i:Int) => i * factor;

  def main(args: Array[String]): Unit = {
    println("return:" + addInt(5, 7));
    println("bibao ", + multiplier(10))
    println("bibao " + multiplier(10))

    val buf = new StringBuilder;
    buf += 'a';
    buf ++= "bcd";
    buf += 'e';

    println(buf.toString())

    var myList = Array(1.9, 2.9, 3.4, 3.5)

    for (x <- myList) {
      println(x);
    }

    var total = 0.0;
    for (i <- 0 to (myList.length - 1)) {
      total += myList(i);
    }
    println("total= " + total);

    var max = myList(0);
    for ( i <- 1 to myList.length - 1) {
      if (myList(i) > max) {
        max = myList(i);
      }
    }
    println("max=" + max);


    var myList1 = range(10, 20, 2)
    var myList2 = range(10,20)

    // 输出所有数组元素
    for ( x <- myList1 ) {
      println( " " + x )
    }
    println()
    for ( x <- myList2 ) {
      println( " " + x )
    }

    val ita = Iterable(20, 40, 2, 50, 69, 90);
    println("max value=" + ita.max);


  }
}


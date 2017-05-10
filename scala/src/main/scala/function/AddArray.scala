package function

/**
  * Created by ahan on 09/05/2017.
  */
object add {
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


  }
}


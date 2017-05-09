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

  def main(args: Array[String]): Unit = {
    println("return:" + addInt(5, 7));
  }
}


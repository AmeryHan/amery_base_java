/**
  * Created by ahan on 10/05/2017.
  */

import scala.util.matching.Regex
object RegTest {

  def main(args: Array[String]): Unit = {

    var pattern = "Scala".r
    var str = "Scala is Scalable and cool"

    println(pattern findFirstIn str)

    pattern = new Regex("(S|s)cala")  // 首字母可以是大写 S 或小写 s
    str = "Scala is scalable and cool"

    println((pattern findAllIn str).mkString(","))   // 使用逗号 , 连接返回结果

    pattern = "(S|s)cala".r;
    str = "Scala is scalable and cool"

    println(pattern replaceFirstIn(str, "Java"));

    pattern = new Regex("abl[ae]\\d+")
    str = "ablaw is able1 and cool"

    println((pattern findAllIn str).mkString(","))

  }

}


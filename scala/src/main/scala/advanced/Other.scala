import java.io.{FileNotFoundException, FileReader, IOException}

/**
  * Created by ahan on 10/05/2017.
  */

object Other {

  //注入方法
  def apply(user: String, domain: String) = {
    user + "@" + domain;

  }

  def unapply(str: String) : Option[(String, String)] = {
    //val parts = str.split("@");
    val parts = str split "@"
    if (parts.length == 2) {
      Some(parts(0), parts(1));
    } else {
      None
    }
  }

  def main(args: Array[String]): Unit = {

    try {
      val f = new FileReader("input.txt")
    } catch {
      case ex: FileNotFoundException => {
        println("Missing file exception")
      }
      case ex: IOException => {
        println("IO Exception")
      }
    }

    println ("Apply 方法 : " + apply("Zara", "gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara@gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara Ali"));


  }

}


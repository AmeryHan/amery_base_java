package yjmyzz

/**
  * Created by ahan on 09/05/2017.
  */
class Hello {

  def sayHello(x: String): Unit = {
    println("hello amery, I am \t " + x);
  }

}

final case class Symbol private (name: String) {
  override def toString: String = "'" + name
}

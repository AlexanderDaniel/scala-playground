package lachdrache.collections

import org.specs2.mutable.Specification

class FoldSpec extends Specification {

  "foldLeft, /: and fold" should {
    val numbers = 0 to 10
    val f: (Int, Int) => Int = _+_

    "foldLeft" in {
      val result = numbers.foldLeft(0)(f)
      result === 55
    }

    "fold" in {
      val result = numbers.fold(0)(f)
      result === 55
    }

    "/:" in {
      val result = (0 /: numbers)(f)
      result === 55
    }

  }
}
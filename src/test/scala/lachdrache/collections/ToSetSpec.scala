package lachdrache.collections

import org.specs2.mutable.Specification

/**
 * https://twitter.com/connerdelights/status/440523169976172546
 */
class ToSetSpec extends Specification {

  "toSet" should {
    "return set of list" in {
      Set(1,2) === List(1,2).toSet
    }
  }

  "toSet()" should {
    "return false" in {
      false === List(1,2).toSet()
    }
  }

}
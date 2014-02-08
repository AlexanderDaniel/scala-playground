package lachdrache.regex

import org.specs2.mutable.Specification

class RegexSpec extends Specification {

  "regex" should {
    "with matcher" in {
      val exceededRegex = """.*_EXCEEDED.*""".r
      val result = "LIMIT_EXCEEDED" match {
        case exceededRegex() => true
        case _ => false
      }
      result === true
    }
  }
}

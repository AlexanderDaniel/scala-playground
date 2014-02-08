package lachdrache.specs2

import org.specs2.mutable.Specification

/**
 * [[http://etorreborre.github.io/specs2/guide/org.specs2.guide.Matchers.html]]
 */
class MatcherSpec extends Specification {

  "various matchers" should {

    (1 must beEqualTo(1)).eg
  }
}

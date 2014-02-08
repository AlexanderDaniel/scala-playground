package lachdrache.specs2

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

/**
 * [[http://etorreborre.github.io/specs2/guide/org.specs2.guide.Matchers.html]]
 */
class CustomMatcherSpec extends Specification with Mockito {

  "beBetween from specs2 doc" should {
    def beBetween(i: Int, j: Int) = be_>=(i) and be_<=(j)

    (3 must beBetween (1,3)).eg

    trait Validator {
      def validNumber(n: Int): Boolean
    }
    case class Service(validator: Validator) {
      def validate(n: Int): Boolean =
        validator.validNumber(n)
    }

    "beBetween with Mockito" in {
      // given
      val validatorMock = mock[Validator]
      validatorMock.validNumber(beBetween(1,3)) returns true
      val service = Service(validatorMock)

      // when & then
      service.validate(1) === true
    }
  }

}

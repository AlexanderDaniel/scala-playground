package lachdrache.lambdas

import org.scalatest.FunSpec

class LambdaSpec extends FunSpec {

  describe("lambda which is executed immediately") {
    it("inc") {
      assertResult(4) {
        ((x: Int) => x+1)(3)
      }
    }
    // http://www.codecommit.com/blog/ruby/monads-are-not-metaphors
    // I do not understand the extra braces
    it("example from daniel") {
      def foo(bar: String) = {
        ({ () => println(bar) })()
        ({ () => bar.length })()
      }
      assertResult(4) {
        foo("alex")
      }
    }
  }
}
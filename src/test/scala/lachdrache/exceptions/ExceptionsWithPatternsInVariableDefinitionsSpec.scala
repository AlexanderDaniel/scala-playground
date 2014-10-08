package lachdrache.exceptions

import org.scalatest.FunSpec

import scala.util.Try

class ExceptionsWithPatternsInVariableDefinitionsSpec extends FunSpec {


  describe("tuple deconstructed with exception") {

    it("everything is fine") {
      val (long1: Long, long2: Long) = try {
        (1L, 2L)
      } catch {
        case _: Throwable => (1L, None)
      }
      assert(long1 === 1L)
      assert(long2 === 2L)
    }

    it("exception is thrown WTF") {
      intercept[MatchError] {
        val (long1: Long, long2: Long) = try {
          if (true)
            sys.error("error")
          else
            (1L, 2L)
        } catch {
          case _: Throwable => (1L, None)
        }
      }
    }

    it("fixing it with type annotation") {
      val (long1: Long, long2: Long): (Long, Long) = try {
        if (true)
          sys.error("error")
        else
          (1L, 2L)
      } catch {
        case _: Throwable => (11L, 12L) // None would not compile anymore
      }
      assert(long1 === 11L)
      assert(long2 === 12L)
    }

    it("patterns in variable definitions are a pattern match as described in Programming in Scala 2nd edition 15.7") {
      intercept[MatchError] {
        (try {
          if (true)
            sys.error("error")
          else
            (1L, 2L)
        } catch {
          case _: Throwable => (11L, None)
        }) match {
          case (long1: Long, long2: Long) => (long1, long2)
        }
      }
    }

    it("using Try does not help either") {
      intercept[MatchError] {
        val (long1: Long, long2: Long) = Try {
          if (true)
            sys.error("error")
          else
            (1L, 2L)
        }.getOrElse((11L, None))
      }
    }

  }
}

package lachdrache

import org.specs2.mutable.Specification

class ScalaCollLibSpec extends Specification {

  "mapValues on Map" should {
    {
      tstMap.mapValues(_.size) === Map(0 -> 1, 1 -> 1, 2 -> 2)
    }.eg
  }

  "values on Map" should {
    {
      tstMap.values.toSeq === Seq("0", "1", "10")
    }.eg
  }

  "maps are not ordered" should {
    val booksByNumber = Seq(0, 0, 1, 2, 2, 2)

    {
      booksByNumber.groupBy(identity) === Map(0 -> Seq(0, 0), 1 -> Seq(1), 2 -> Seq(2, 2, 2))
      booksByNumber.groupBy(identity).mapValues(_.size) === Map(0 -> 2, 1 -> 1, 2 -> 3)
      booksByNumber.groupBy(identity).mapValues(_.size).values.toSeq === Seq(3,1,2)
    }.eg
  }

  private val tstMap = Map(0 -> "0", 1 -> "1", 2 -> "10")

}

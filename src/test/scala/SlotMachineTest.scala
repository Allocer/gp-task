import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.Inspectors._

class SlotMachineTest extends FlatSpec with Matchers {

  "player" should "call SlotMachine and get result" in {
    val result = new SlotMachine(99).spin()

    result should have length 3
    forAll(result) { x => x should be >= 1 }
    forAll(result) { x => x should be < 16 }
  }

  "player " should "win game in given times" in {
    val winRatio = 0.5
    val games = 100000

    val expectedWins = games * winRatio

    val result: Seq[Boolean] = (1 to games).map(_ => {
      val gameResult: Seq[Int] = new SlotMachine((winRatio * 100).toInt).spin()
      val isWin: Boolean = gameResult.tail.forall(n => n == gameResult.head)
      isWin
    })

    val actualWin = result.count(p => p == true).toDouble

    actualWin shouldBe expectedWins +- (expectedWins * 0.003)


  }

}

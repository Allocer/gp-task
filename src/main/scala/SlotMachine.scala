import scala.util.Random

class SlotMachine(ratio: Int) {


  def spin(): Seq[Int] = {
    //0.0044 => 0.1
    //10% => true

    val number = Random.between(1, 101)

    val isWin = number >= 1 && number <= ratio

    isWin match {
      case true => {
        val winNumber = Random.between(1, 16)
        Seq.fill(3)(winNumber)
      }
      case false => {
        chooseLostNumbers
      }
    }
  }

  private def chooseLostNumbers: Seq[Int] = {
    val result = Seq.fill(3)(Random.between(1, 16))
    if (result.tail.forall(p => p == result.head)) chooseLostNumbers
    else result
  }
}

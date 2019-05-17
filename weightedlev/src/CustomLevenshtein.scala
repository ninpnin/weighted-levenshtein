import scala.math

object CL {

  val n = 100
  val exponent = 2//1.5
  val weights: Vector[Double] = (for (i <- 0 to n) yield { 1.0 / math.pow(exponent, i * 0.5) }).toVector

  def levenshtein(str1: String, str2: String, reverse: Boolean): Double = {
    val string1 = if (reverse) str1.reverse else str1
    val string2 = if (reverse) str2.reverse else str2

    if (str1.length > str2.length) {
      levenshtein(string1, string2)
    } else if (str1.length == str2.length) {
      val d1 = levenshtein(string1, string2)
      val d2 = levenshtein(string1, string2)
      Math.min(d1,d2)
    } else {
      levenshtein(string2, string1)
    }
  }

  def levenshtein(str1: String, str2: String): Double = {
    def min(nums: Double*): Double = nums.min

    val lenStr1 = str1.length
    val lenStr2 = str2.length

    val d: Array[Array[Double]] = Array.ofDim(lenStr1 + 1, lenStr2 + 1)

    for (i <- 1 to lenStr1) d(i)(0) = weights(i-1) + d(i-1)(0)
    for (j <- 1 to lenStr2) d(0)(j) = weights(j-1) + d(0)(j-1)

    for (i <- 1 to lenStr1; j <- 1 to lenStr2) {
      val weightDeletion: Double = weights(j-1)
      val weightInsertion: Double = weights(j)
      val cost: Double = if (str1(i - 1) == str2(j - 1)) 0.0 else weightDeletion

      d(i)(j) = min(
        d(i-1)(j  ) + weightInsertion,     // deletion
        d(i  )(j-1) + weightDeletion,     // insertion
        d(i-1)(j-1) + cost   // substitution
      )
    }

    d(lenStr1)(lenStr2)
  }

  println(levenshtein("moi", "mia"))

}
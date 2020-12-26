import java.io.PrintWriter

object DataGenerator extends App {
  
  def cleanUp(s: String): String = {
    s.replaceAll("[^a-zA-Z0-9äöÄÖåÅ,\\.\\?!:']", "")
    s.replaceAll(",", " ,")
    .replaceAll(":", " :")
    .replaceAll("\\.", " .")
    .replaceAll("!", " !")
    .replaceAll("\\?", " ?")
  }

  def levenshtein(s1: String, s2: String, b: Boolean) = CL.levenshtein(s1,s2,b)

  val useTestData = false
  val filename = if (useTestData) "testidata.txt" else "data/text/whole_dataset.txt"
  val sourceStr = scala.io.Source.fromFile(filename).getLines().toVector.reduce(_ + " " + _)
  val cleanStr = cleanUp(sourceStr)

  // whether words are reversed for distance calculation 
  val reverse = false
  val numberOfWords = 30000
  val maxDimension = 10000
  val distinctWords = cleanStr.split(" ")
    .map(word => word.toLowerCase )
    .distinct
    .toVector
    .take(numberOfWords)

  // print out a small sample of the data
  println(distinctWords.take(10))

  // Calculate number of words in case dataset size is smaller than numberOfWords
  /*
  val wordCount = distinctWords.length
  println(wordCount)
  val distances: Array[Array[Double]] = Array.ofDim(wordCount, Math.min(wordCount, maxDimension))

  val YMAX = Math.min(distinctWords.length, maxDimension)
  for (i <- 0 until distinctWords.length; j <- 0 until YMAX) {
    val w1 = distinctWords(i)
    val w2 = distinctWords(j)
    distances(i)(j) = levenshtein(w1,w2, reverse)
    if (i % 31 == 0 && j == YMAX - 1) {
      println("row doned", i, j)
      println(w1)
      val distW1 = distances(i) zip distinctWords
      val srted = distW1.sortBy(_._1)
      val closestWrds = srted.unzip._2.toVector.take(20)
      val closestDists = srted.unzip._1.toVector.take(20)
      println(closestWrds, closestDists)
    }
  }

  def arrayToRow(a: Array[Double]) = a.map(_.toString().take(8)).reduce(_ + "; " + _)
  */
  println ("Start printing process...")
  println("Print word list...")
  println("len " + distinctWords.length)

  val wordFilename = "data/reverse-" + reverse + "/words.txt"
  val wordPrinter = new PrintWriter(wordFilename)

  for (word <- distinctWords.dropRight(1))
    wordPrinter.print(word + "; ")
  wordPrinter.print(distinctWords.last + " \n")
  wordPrinter.close()

  /*
  println("Print distances...")
  val outputFilename = "data/reverse-" + reverse + "/distances/distanceMatrix"
  var printer = new PrintWriter(outputFilename + "0.csv")

  var i = 0
  for (arr <- distances) {
  	//val row = arrayToRow(arr)
  	//printer.println(row)
    for (num <- arr.dropRight(1)) {
      printer.print(num + "; ")
    }
    printer.print(arr.last)
    printer.print("\n")
    if (i % 50 == 0) println("row", i)
  	if (i % 500 == 0 && i > 0) {
      printer.close()
  		println("FILE DONE", i / 500)
      val fileindex = i / 500
      printer = new PrintWriter(outputFilename + fileindex + ".csv")
    }
  	i += 1
  }
  printer.close()
  println("DONE!")
  */
}

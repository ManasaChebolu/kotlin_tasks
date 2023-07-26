import java.io.File
import kotlinx.coroutines.*
// main method
fun main() = runBlocking {

        val scores = mapOf("good" to 1, "great" to 2, "bad" to -1, "terrible" to -2)
        val files = listOf("C:/Users/manasa.ch/Downloads/sample1.txt", "C:/Users/manasa.ch/Downloads/sample2.txt", "C:/Users/manasa.ch/Downloads/sample3.txt",
                "C:/Users/manasa.ch/Downloads/sample4.txt", "C:/Users/manasa.ch/Downloads/sample5.txt")
        val jobList = mutableListOf<Job>()
    try {
        for (file in files) {
            jobList += launch { processFile(file, scores) }
        }
        jobList.joinAll()
    }catch (e:Exception) {
        println("File not found")
    }
}

// function to process a file
fun processFile(file: String, scores: Map<String, Int>) {
    try {
        val words = File(file).readText().lowercase().split(" ")
        val countOfEachWord = count(words, scores)
        val totalscore = totalScore(countOfEachWord, scores)
        println("Filename: $file")
        println("Number of occurrences: ${countOfEachWord.values.sum()}")
        println("TotalScore of that file: $totalscore")
        renameFile(file, totalscore)
    }catch (e:Exception) {
        println("Error!!")
    }
}

// Occurrence of each word
fun count(words: List<String>, scores: Map<String, Int>): HashMap<String, Int> {
    val scoreMap= hashMapOf<String,Int>()
    for(word in words) {
        if(word in scores) {
            scoreMap[word]= scoreMap.getOrDefault(word,0) +1
        }
    }
    return scoreMap
}

// function to calculate total score
fun totalScore(countOfEachWord: HashMap<String, Int>,scores: Map<String, Int>) :Int{
    var score=0
    for(word in scores.keys) {
        score += scores.getOrDefault(word, 0) * countOfEachWord.getOrDefault(word, 0)
    }
    return score
}

//function to rename file
fun renameFile(file:String,totalscore: Int) {
    val newFileName= when {
            (totalscore > 0) -> "${file.substringBeforeLast(".txt")} Positive.txt"
            (totalscore<0)-> "${file.substringBeforeLast(".txt")}Negative.txt"
            else -> "${file.substringBeforeLast(".txt")}Neutral.txt"
    }
    if(File(file).renameTo(File(newFileName))) {
        println("Renamed Successfully")
    }  else {
        println("Failed to rename file")
    }
}
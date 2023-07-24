import java.util.*
import kotlin.collections.ArrayList

val scan=Scanner(System.`in`)
// Movie class to store the attributes
data class Movie(val title:String, val year:Int, val rating:Double, val language:String)

class IMDB {
    // function to add the attributes into mutable list
    fun addAttributes(movieList: MutableList<Movie>) {
        print("Enter movie title : ")
        val title = readln()
        print("Enter released year: ")
        val year=scan.nextInt()
        print("Enter IMDB rating : ")
        val rating=scan.nextDouble()
        print("Enter language of movie:")
        val language= readln()
        movieList.add(Movie(title, year, rating, language))
    }

    //function to search the attributes
    fun searchAttributes(userSearch: Any, movieList: MutableList<Movie>):ArrayList<Movie>{
        val movie= ArrayList<Movie>()
        for( i in movieList) {
            if(i.title.lowercase().contains(userSearch.toString().lowercase()) ||
                    i.year.toString().contains(userSearch.toString().lowercase())||
                    i.rating.toString().contains(userSearch.toString().lowercase())||
                    i.language.lowercase().contains(userSearch.toString().lowercase())) {
                movie.add(i)
            }
        }
        return movie
    }

    // function to get the list of movies whose rating is greater than the user input
    fun movieRating(rate: Double, movieList: MutableList<Movie>):ArrayList<Movie> {
        val movieRatingList= ArrayList<Movie>()
        for(i in movieList) {
            if(i.rating> rate) {
                movieRatingList.add(i)
            }
        }
        return movieRatingList
    }
}
// Main Method
fun main() {
    val imdb = IMDB()
    val movieList:MutableList<Movie> = mutableListOf( )
    //Add Attributes
    do {
        imdb.addAttributes(movieList)
        print("Do you want to continue: ")
    }while(readln()=="yes")
    // Search Attributes
    print("Enter the attribute do you want to search: ")
    val searchList = imdb.searchAttributes(readln(),movieList)
    searchList.forEach { println(it.title) }
    // Check the rating
    print("Enter the rating to check: ")
    val ratingList = imdb.movieRating(scan.nextDouble(),movieList)
    ratingList.forEach { println(it.title) }
}
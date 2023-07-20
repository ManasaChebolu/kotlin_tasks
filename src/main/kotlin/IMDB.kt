import java.util.*
import kotlin.collections.ArrayList

val scan=Scanner(System.`in`)
val movieList:MutableList<Movie> = mutableListOf()
data class Movie(val title:String, val year:Int, val rating:Double, val language:String)

class IMDB {
    fun search(userSearch: Any):ArrayList<Movie>{
        val movie= ArrayList<Movie>()
        for( i in movieList) {
            if(i.title.lowercase().contains(userSearch.toString().lowercase()) ||
                    i.year.toString().contains(userSearch.toString().lowercase())||
                    i.rating.toString().contains(userSearch.toString().lowercase())||
                    i.language.lowercase().contains(userSearch.toString().lowercase()))
                movie.add(i)
        }
        return movie
    }
    fun movieRating(rate:Double):ArrayList<Movie> {
        val movieRatingList= ArrayList<Movie>()
        for(i in movieList)
            if(i.rating> rate) movieRatingList.add(i)
        return movieRatingList
    }
}
fun main() {
    val imdb=IMDB()
    movieList.add(Movie("Mem Famous",2023,8.2,"Telugu"))
    movieList.add(Movie("HIT",2020,7.6,"Telugu"))
    movieList.add(Movie("Vada chennai",2018,8.4,"Tamil"))
    movieList.add(Movie("the meg",2002,5.6,"English"))
    movieList.add(Movie("Shang chi",2021,8.4,"English"))
    val search= readln()?.trim()
    if(search!=null) {
        val searchList = imdb.search(search)
        searchList.forEach { println(it.title) }
    }
    val ratingList =imdb.movieRating(scan.nextDouble())
    ratingList.forEach{println(it.title)}
}
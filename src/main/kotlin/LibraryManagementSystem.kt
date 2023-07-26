import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.List
val scanObj=Scanner(System.`in`)
//Class with contains all details of books
data class Book(val isbn:String, val title:String, val author:String, val year:Int, var available:Boolean)

class Library {
    //Function to add books
    fun addBook(books: MutableList<Book>):String {
         return  try {
            print("Enter book isbn number: ")
            val isbn = readln().lowercase()
            print("Enter book title: ")
            val title = readln().lowercase()
            print("Enter book author: ")
            val author = readln().lowercase()
            print("Enter publish year: ")
            val year = scanObj.nextInt()
            print("Enter Book is available or not: ")
            val available = scanObj.nextBoolean()
            books.add(Book(isbn, title, author, year, available))
              "Book Added!!"
         }catch (e:InputMismatchException) {
            "Please enter correct datatype"
         }
    }
    //Function to remove book by ISBN
    fun removeBookByIsbn(books: MutableList<Book>){
            print("Enter book ISBN number: ")
            val isbn = readln().toString().lowercase()
            for (i in 0..<books.size)
                if (isbn == books[i].isbn) {
                    books.removeAt(i)
                    println("Book Removed")
                    break
                }
    }
    //Function to search books by author name
    fun searchBookByAuthorName(books: MutableList<Book>) :List<Book>{
        print("Enter book author : ")
        val author= readln().lowercase()
        return books.filter { it.author==author}
    }
    // Function to search available books by title
    fun searchAvailableBooksByTitle(books: MutableList<Book>):List<Book> {
        print("Enter book title: ")
        val title= readln().lowercase()
        return books.filter { it.title==title && it.available }
    }
    //Function to borrow book
    fun borrowBook(books: MutableList<Book>) {
        print("Enter book ISBN number: ")
        val isbn= readln().toString().lowercase()
       val borrowList=books.filter { it.isbn==isbn }
        for(i in 0..<books.size) {
            if(borrowList[0].isbn==books[i].isbn) {
                books[i].available=false
            }
        }
    }
    //Function to return book
    fun returnBook(books: MutableList<Book>) {
        print("Enter book ISBN number: ")
        val isbn = readln().toString().lowercase()
        val borrowList=books.filter { it.isbn == isbn }
        for(i in 0..<books.size) {
            if(borrowList[0].isbn==books[i].isbn) {
                books[i].available=true
            }
        }
    }
    //Function to print all books
    fun printAllBooks(books: MutableList<Book>) {
        println(books)
    }
    //Function to get the oldest book
    fun getOldestBook(books: MutableList<Book>): Any {
        books.sortBy { it.year }
        return if(books.isEmpty()) {
                     "null"
               } else{
                   books[0]
             }
    }
    //function to get available books by author
    fun getAvailableBooksByAuthor(books: MutableList<Book>, author: String):List<Book> {
       return books.filter{it.author==author.lowercase() && it.available}
    }
    //function to process each book
    fun processBooks(books: MutableList<Book>) = runBlocking{
        async {
            for(book in books) {
                println(book)
                delay(1000)
            }
        }
    }
}
//Main Method
fun main() {
    val library=Library()
    val books= mutableListOf<Book>()
    do {
        println("1.addBook,2.removeBook,3.searchBookByAuthor,4.searchAvailableBooksByTitle,5.borrowBook,6.returnBook,7.printAllBooks,8.getOldestBook,9.getAvailableBooksByAuthor,10.processBook")
        when(scanObj.nextInt()) {
            1 -> println(library.addBook(books))
            2 -> library.removeBookByIsbn(books)
            3 -> println(library.searchBookByAuthorName(books))
            4 -> println(library.searchAvailableBooksByTitle(books))
            5 -> library.borrowBook(books)
            6 -> library.returnBook(books)
            7 -> library.printAllBooks(books)
            8 -> println(library.getOldestBook(books))
            9 -> println(library.getAvailableBooksByAuthor(books,"Jane Austen"))
            10-> library.processBooks(books)
        }
        print("Do you want to continue: ")
    }while (readln()=="yes")
}

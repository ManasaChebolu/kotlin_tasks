import java.util.*
val scanner=Scanner(System.`in`)
var arraylist = ArrayList<ShoppingCartItems>()
class ShoppingCartItems(val item:String,val  price:Int) {
    companion object {
        fun addingItems():String {
            print("Enter item : ")
            val item = readln()
            print("Enter price : ")
            val price = scanner.nextInt()
            arraylist.add(ShoppingCartItems(item,price))
            return "Added"
        }
        fun removeItems():String {
            print("Enter index of an item : ")
            val index = scanner.nextInt()
            arraylist.removeAt(index)
            return "Removed"
        }
        fun totalPrice(): Int {
            return arraylist.sumOf { it.price }
        }
        fun discountedPrice(): Double {
            val discount = 10.0
            return (totalPrice() * (1 - discount / 100))
        }
    }
}
fun main() {
    arraylist.add(ShoppingCartItems("Shoes",999))
    arraylist.add(ShoppingCartItems("Watch",499))
    do {
        println("1.add items ,2.remove items, 3.total price without discount ,4.Discounted price")
        when(scanner.nextInt()) {
            1 -> println(ShoppingCartItems.addingItems())
            2 -> println(ShoppingCartItems.removeItems())
            3 -> println("Total price without discount : ${ShoppingCartItems.totalPrice().toString()}")
            4 -> println("Discounted price : ${ShoppingCartItems.discountedPrice().toString()}")
        }
        println("Do you want to continue")
        val userChoice= readLine()
    }while(userChoice.equals("yes"))
}

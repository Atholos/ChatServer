import java.time.LocalTime

//Creates a Chat message
class ChatMessage(private val message: String, val user: String){
    private val time = LocalTime.now() //gets the local time

    override fun toString(): String {
        return "$user @ $time : $message" //returns the following print value
    }
}
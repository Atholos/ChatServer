//TopChatter in the chat, calculates the ammount of messages sent by the each user
object TopChatter: ChatHistoryObserver {
    val topChatter = mutableMapOf<String, Int>()//List of chatters

    override fun newMessage(message: ChatMessage) {
        val user = message.user //gets the user who sent the message

        if (topChatter.contains(user)) {
            var value = topChatter.getValue(user)
            value += 1                                  //Increases the value
            topChatter.set(user, value)                 //sets the new value to the user
        } else {
            topChatter.put(user, 1)
        }
        val sortedMap = topChatter.toList().sortedByDescending{(key, value) -> value} //converts the Map to a List and sorts it by descending number
        sortedMap.take(4).forEach{n ->println(n)}   //takes the 4 first values and prints them.
    }
}
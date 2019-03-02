//Observable singleton ChatHistory, stores the sent messages in a list.
object ChatHistory: ChatHistoryObservable{
    var chatHistory = mutableListOf<ChatMessage>() //List of the history
    val observers = mutableListOf<ChatHistoryObserver>() //List of Observers

    fun insert(newMessage: ChatMessage) {
            chatHistory.add(newMessage)
            notifyObserver(newMessage)
        }
    override fun notifyObserver(message: ChatMessage){ //Sends a message to all Observers.
        observers.forEach { it.newMessage(message) }
    }
    override fun registerObserver(observer: ChatHistoryObserver) { //registers the Observers to the list above.
        observers.add(observer)
    }
    override fun deregisterObserver(observer: ChatHistoryObserver) { //removes the Observer when they quit the program.
        observers.remove(observer)
    }
}
interface ChatHistoryObservable{ //all funktions in the Observable singleton
    fun registerObserver(observer: ChatHistoryObserver)
    fun deregisterObserver(observer: ChatHistoryObserver)
    fun notifyObserver(message:ChatMessage)
}
interface ChatHistoryObserver{ //the funktion for all the observers
    fun newMessage(message:ChatMessage)
}
//Shows every chat message in the console below
object ChatConsole: ChatHistoryObserver{
    override fun newMessage(message: ChatMessage) { //Observer function to ChatHistory
        println(message)
    }
}
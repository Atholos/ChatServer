import java.net.ServerSocket

class ChatServer {

    fun serve(){    val serverSocket = ServerSocket(0) //Gets the first available port
        println("port: " +serverSocket.localPort) //prints the available port

        println("Connecting.....")
        ChatHistory.registerObserver(ChatConsole) //registers ChatConsole an Observer
        ChatHistory.registerObserver(TopChatter) //registers TopChatter an Observer

        while (true){
            val accept = serverSocket.accept()  //accepts the given socket
            println("NEW Connection")
            val ci = CommandInterpreter(accept)         //applies the accpet in CimmanInterpreter
            val t = Thread(CommandInterpreter(accept))  //creates the Thread for CI
            ChatHistory.registerObserver(ci)            //Registers CI as an Observer
            t.start()
        }
    }
}
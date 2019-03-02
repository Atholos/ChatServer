import java.io.PrintStream
import java.lang.Exception
import java.net.Socket
import java.util.*

class CommandInterpreter(private val accept: Socket): Runnable, ChatHistoryObserver {
    private var input = Scanner(accept.getInputStream())
    private var output = PrintStream(accept.getOutputStream())
    private val users = Users
    private var done = false
    private var theUser = ""

    override fun newMessage(message: ChatMessage) {
        output.println(message)
    }
    override fun run(){
        try{
        output.println("quit if you want to exit or for help = : , sorry to say but the first command doesn't count" )
        while (!done){
            val commandline = input.nextLine()
            if(commandline.startsWith(":")){ //Checks what the command is
                if(commandline.startsWith(":usern")) {
                    val name = commandline.substringAfter(":usern") //checks the username after :usern
                    if ((Users.listofUsers.contains(name)) == false && name.isNotBlank() && theUser == "") { //final check before adding the user, it requires that theUser is blank, the text is not Blank and that the user does not exist
                        users.insertUser(name)
                        theUser = name
                    }
                    else output.println("Username exists ,there wasn't one or you already have a username!")
                }
                else if(commandline.startsWith(":help")){
                    output.println(":usern will create a username, it requires a name after, for example. :usern Kitty")
                    output.println(":history shows all chat messages")
                    output.println(":users shows all existing users")
                    output.println(":quit, logs the user out and shuts the program")
            }
                else if(commandline.startsWith(":history")){
                    (ChatHistory.chatHistory.forEach{output.println(it)})
                }
                else if(commandline.startsWith(":users")) (Users.listofUsers.forEach{output.println(it)})
                else if(commandline.startsWith(":quit")) done = true //hops out of the while loop
                else output.println("try :help") //if the user tries a command that starts with ":" and is not any of the above, it gives the println
            }
            if (theUser != "" ) {
                if(commandline.startsWith(":")){} //Does nothing when user types any of the commands above
                    else if(commandline.isBlank()){} // Does nothing when the user leaves it blank
                    else {
                    ChatHistory.insert((ChatMessage(commandline, theUser))) //adds the message only if it doesn't start with ":" or it's blank/empty
                }

            }else output.println("Please create a username for example :usernKat")
        }} catch(e: Exception) {
            println("The program has been forcefully closed.")
        }
        output.close()              //closes the program
        users.removeUser(theUser)   //removes the user
    }
    }
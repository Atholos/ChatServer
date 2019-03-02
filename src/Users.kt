//Creation of list of Users, includes adding, removing and printing the list
object Users{
    val listofUsers = HashSet<String>() //Set List of users (no dublicates allowed)

    fun insertUser(username: String) {
        if (listofUsers.add(username)) {
            listofUsers.add(username)
        }
        else {
            println(username+ " exists.")
        }
    }
    fun removeUser(username: String) {
        val user =listofUsers.remove(username)
        if(user == true)println(username + " has been removed")
        else println(username + " has been removed")
    }
    override fun toString(): String {
        return listofUsers.joinToString()  //converts the list to a string and prints it
    }
}
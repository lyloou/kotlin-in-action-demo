package com.lyloou.chapter4.companionobject

data class Person(val name: String) {
    companion object Loader {
        fun fromString(jsonText: String): Person = Person(jsonText)
    }
}

fun main(args: Array<String>) {
//    testPerson()
    testUser()

}

fun testUser() {
    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val facebookUser = User.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(facebookUser.nickname)
}

private fun testPerson() {
    println(Person.fromString("asdf"))
    println(Person.fromString("asldkfj"))
}


class User(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
                User(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
                User(getFacebookName(accountId))

        private fun getFacebookName(accountId: Int): String {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return "facebook$accountId"
        }
    }
}

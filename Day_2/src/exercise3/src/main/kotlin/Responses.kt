package com.example.kotlincourse

sealed class Responses {
    class Success(private val code: Int) : Responses() {
        private val message = "The request processed successfully"

        override fun toString(): String {
            return "Success:\n  Code: $code\n  Message: $message"
        }
    }
    abstract class Error(open val code: Int) : Responses() {
        abstract val description: String
        abstract val title: String
        class UserNotIdentifiedError(override val code: Int) : Error(code) {
            override val title = "The user is not identified"
            override val description = "Can't identify user"
        }
        class SessionExpiredError(override val code: Int) : Error(code) {
            override val title = "The session is expired"
            override val description = "User session is expired"
        }
        class NoConnectionError(override val code: Int) : Error(code) {
            override val title = "No connection"
            override val description = "There are no connection"
        }
        class DeviceVerificationError(override val code: Int) : Error(code) {
            override val title = "The device has failed the verification"
            override val description = "There are error with device verification"
        }
        class UnknownError(override val code: Int) : Error(code) {
            override val title = "Error code: $code"
            override val description = "Unknown error. Please, try again later"
        }

        override fun toString(): String {
            return "${this::class.simpleName}:\n  Code: $code\n  Title: $title\n  Description : $description"
        }
    }


}
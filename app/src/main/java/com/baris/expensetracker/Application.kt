package com.baris.expensetracker

import android.app.Application
import android.net.http.HttpResponseCache.install


fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    // Install required plugins
    install(ContentNegotiation) {
        json()
    }

    routing {
        route("/expenses") {
            // List all expenses
            get {
                call.respond(expenseList)
            }

            // Add a new expense
            post {
                val newExpense = call.receive<Expense>()
                expenseList.add(newExpense)
                call.respond(mapOf("message" to "Expense added successfully!"))
            }
        }
    }
}

// Dummy database
val expenseList = mutableListOf<Expense>()

// Data model
@Serializable
data class Expense(val id: Int, val name: String, val amount: Double)
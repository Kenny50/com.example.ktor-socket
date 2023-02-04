package com.example.plugins

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        webSocket("/ws") { // websocketSession
            println("receive")
            var counter = 0
            while (true){
                outgoing.send(Frame.Text(imgs[counter % imgs.size]))
                counter++
                delay(100)
            }
        }
    }
}

val imgs = listOf(
    "https://images.unsplash.com/photo-1552872673-9b7b99711ebb",
    "https://images.unsplash.com/photo-1606228281437-dc226988dc3a",
    "https://images.unsplash.com/photo-1475924156734-496f6cac6ec1",
    "https://images.unsplash.com/photo-1606481021733-5e269f7d87f6",
    "https://images.unsplash.com/photo-1519575706483-221027bfbb31",
    "https://images.unsplash.com/photo-1530981711668-84c7d5aee08f",
    "https://images.unsplash.com/photo-1550475966-70af27831597",
    "https://images.unsplash.com/photo-1524781289445-ddf8f5695861",
    "https://images.unsplash.com/photo-1496267472830-2eb2b7e0942d",
    "https://images.unsplash.com/photo-1422493757035-1e5e03968f95"
)
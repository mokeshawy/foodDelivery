package com.tru.websocket

interface WebSocketHelper {

    var onMessageReceived: ((String) -> Unit)?

    fun connect()
    fun sendMessage(message: String)
    fun close()
}
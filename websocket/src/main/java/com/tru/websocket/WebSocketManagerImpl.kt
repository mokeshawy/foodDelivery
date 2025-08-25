package com.tru.websocket

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber
import javax.inject.Inject

class WebSocketManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    WebSocketHelper {

    override var onMessageReceived: ((String) -> Unit)? = null

    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    private val listener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            Timber.i("WebSocket opened")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            onMessageReceived?.invoke(text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            onMessageReceived?.invoke(bytes.utf8())
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Timber.i("WebSocket closing: $code / $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Timber.i("WebSocket closed: $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Timber.e("WebSocket error: ${t.message}")
        }
    }


    override fun connect() {
        val request = Request.Builder()
            .url(context.getString(R.string.socket_url))
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    override fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    override fun close() {
        webSocket?.close(1000, "User closed connection")
    }
}

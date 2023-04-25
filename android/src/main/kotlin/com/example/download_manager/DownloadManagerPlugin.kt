package com.example.download_manager
import android.app.DownloadManager
import android.app.DownloadManager.Request
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.getSystemService
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


/** DownloadManagerPlugin */
class DownloadManagerPlugin: FlutterPlugin, MethodChannel.MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context : Context
  private var TAG = "DownloadManagerPlugin"
  private var CHANNEL = "com.example.download_manager/download_manager"

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, CHANNEL)
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
    when(call.method) {
      "downloadFile" -> {
        val url = call.argument<String>("url")!!
        val filename = call.argument<String>("filename")!!
        downloadFile(url, filename)
        result.success(true)
      }
      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun downloadFile(url: String, filename: String){
    Log.d(TAG, "downloadFile called for url: $url, filename: $filename")
    val downloadManager = getSystemService(context, DownloadManager::class.java)
    downloadManager?.enqueue(Request(Uri.parse(url)).apply {
      setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
      setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
    })
  }
}

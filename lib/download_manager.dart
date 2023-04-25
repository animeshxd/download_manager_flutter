import 'package:flutter/services.dart';

const platform = MethodChannel('com.example.download_manager/download_manager');

Future<void> downloadFile({
  required String url,
  required String filename,
}) async {
  await platform.invokeMethod(
    "downloadFile",
    {
      'url': url,
      'filename': filename,
    },
  );
}

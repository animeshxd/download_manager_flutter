import 'package:download_manager/download_manager.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin download Manager app'),
        ),
        body: Center(
          child: ElevatedButton.icon(
            onPressed: () async {
              var url =
                  "https://github.com/animeshxd/download_manager_method_channel/archive/refs/heads/master.zip";
              await downloadFile(url: url, filename: "master.zip");
            },
            icon: const Icon(Icons.download),
            label: const Text('Download'),
          ),
        ),
      ),
    );
  }
}

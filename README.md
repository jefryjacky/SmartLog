# SmartLog

# Usage 

1. Simple setup, put code below in onCreate of your application class
```kotlin
  SmartLog.config = SmartLogConfig.Builder()
            .addPrinter(AndroidLogCatPrinter())
            .build()
``` 

2. Setup with file logging, put code below in onCreate of your application class
```kotlin
  val folder = "SmartLog"
        val dir = getExternalFilesDir(folder)?: File("${filesDir}/$folder/")
        if(!dir.exists()){
            dir.mkdir()
        }

   SmartLog.config = SmartLogConfig.Builder()
            .addPrinter(AndroidLogCatPrinter())
            .addPrinter(FileLogPrinter(dir))
            .build()
``` 

3. How to log
```kotlin
   SmartLog.d("hello world") // then it will print class name, current thread and message
   SmartLog.tag("custom tag").d("hello world") // then it will print custom, current thread and message
``` 


### License
<pre>
Copyright 2020 Jefry Jacky

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>

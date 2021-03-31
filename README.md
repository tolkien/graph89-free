# Graph89-free

Graph89 is an emulator targeting the Android platform for TI89, TI89T, TI92, TI92+, V200, TI84+, TI84+SE, TI83, TI83+ and TI83+SE calculators.

this code is clone from Dritan Hashorva's code.
(https://bitbucket.org/dhashoandroid/graph89-free.git)

In Feb 15, 2021, 
I just imported his code in Android Studio 4.1.2

Mar 1, 2021, Independence Movement Day of Korea
Todo: when trying release build, there are 2 errors.
 I have to fix it

	Task :app:lintVitalRelease
	app\src\main\res\layout\settings.xml:16: Error: SeekBarPreference must extend android.view.View [Instantiatable]  
	 <com.graph89.controls.SeekBarPreference
	app\src\main\res\layout\settings.xml:94: Error: AmbilWarnaPreference must extend android.view.View [Instantiatable]  
	 <com.graph89.controls.AmbilWarnaPreference
	      
Explanation for issues of type "Instantiatable":  
    Activities, services, broadcast receivers etc. registered in the manifest file (or for custom views, in a layout file) must be "instantiatable" by the system, which means that the class must be public, it must have an empty public constructor, and if it's an inner class, it must be a static inner class.

Mar 14, 2021
 1. apply "Refactor -> Migrate AppCompat ...".
   it seems to be better than before.
   and I was helped from
   https://dreamaz.tistory.com/63 and
   https://stackoverflow.com/questions/4388068/how-disable-remove-android-activity-label-and-label-bar/4388099
 2. run "Analyze -> Inspect Code...".
   there are a lot of warning I have to fix
 3. change minSdkVersion from 9 to 17
   lint reports that there are some layout elements of API 11 or higher used.
 4. add READ_EXTERNAL_STORAGE check and request handler at FilePickerActivity
   It was helped from https://stackoverflow.com/questions/33162152/storage-permission-error-in-marshmallow

 Mar 16, 2021
 1. on android 11, takescreenshot feature is partially working
  see https://stackoverflow.com/questions/61406818/filenotfoundexception-open-failed-eperm-operation-not-permitted-during-saving
      https://darkstart.tistory.com/66
      https://stackoverflow.com/questions/56904485/how-to-save-an-image-in-android-q-using-mediastore
 2. change minSdkVersion from 17 to 19, compileSdkVersion from 26 to 29

Mar 20, 2021
 1. fix takescreenshot feature on android 11.

Mar 21, 2021
 1. fix takescreenshot feature on android 8.
  see https://boilerplate.tistory.com/43
     https://stackoverflow.com/questions/7887078/android-saving-file-to-external-storage
     https://www.simplifiedcoding.net/android-save-bitmap-to-gallery/

Mar 26, 2021
 1. do "Refactor -> Migrate to AndroidX..."
 2. suppress warning about com.graph89.controls.SeekBarPreference,
        com.graph89.controls.AmbilWarnaPreference
        using tools:ignore="ResAuto,Instantiatable" in settings.xml
 3. fix many lint warnings
 
Mar 27, 2021
 1. remove com.graph89.controls.AmbilWarnaPreference

Mar 31, 2021
 1. remove graph89/controls/SeekBarPreference.java

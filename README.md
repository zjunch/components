# components 此demo 为 记录 组件化module


总结处理方式：
1.gradle.properties 添加  对应module 设置，  
    isNeedFriendMoudle=true   //是否是app的引用module  ,true 则当作moudle应用，false 则作为单独的app
    isNeedMineMoudle=true    
    isNeedDiscoverMoudle=true
    isNeedHomeMoudle=true
    
2.设置module 的 apply plugin，下面依HomeMoudle为例，其他module 依次调整
    if (isNeedHomeMoudle.toBoolean()) {
      apply plugin: 'com.android.library'
    } else {
      apply plugin: 'com.android.application'
    }

3.设置module 的manifest
  在各modlue 设置的 的src 下创建 名为的moudle的包名，复制对应的manifest 做相应调整，之后在gradle.build的android 目录下添加对应代码，下面依HomeMoudle为例

    sourceSets{
            main{
                if (isNeedHomeMoudle.toBoolean()) {
                    manifest.srcFile 'src/moudle/AndroidManifest.xml'
                } else {
                    manifest.srcFile 'src/main/AndroidManifest.xml'
                }
            }
        }

      原manifest如下：
      
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.android.home">
        <application
            android:name="com.android.commonlibrary.base.ZApp"
            android:label="@string/app_name"
            android:allowBackup="true"
            android:supportsRtl="true"
            android:theme="@style/AppTheme">
            <activity android:name=".HomeMainActivity">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />

                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
        </application>
    </manifest>

    module下manifest如下：
    <?xml version="1.0" encoding="utf-8"?>
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="com.android.home">

      <application
          android:name="com.android.commonlibrary.base.ZApp"
          android:label="@string/app_name"
          android:allowBackup="true"
          android:supportsRtl="true"
          android:theme="@style/AppTheme">
          <activity android:name=".HomeMainActivity">
          </activity>
      </application>
  4.在各moudle下添加路由：
   

</manifest>


  
  

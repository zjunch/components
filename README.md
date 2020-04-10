# components 此demo 为 记录 组件化module
 
    描述： 共6个moudle,其中commonlib 为公共库，所有moule 都需引入；除此之外还有有app, home,discover ,friend,mine,  
     app 为主moudle ,其他四个类似 app的四个功能模块，

总结处理方式：
1.gradle.properties 添加  对应module 设置，

    isNeedFriendMoudle=true   //是否是app的引用module  ,true 则当作moudle应用，false 则作为单独的app
    
    isNeedMineMoudle=true    
    
    isNeedDiscoverMoudle=true
    
    isNeedHomeMoudle=true
    
    app module需要引入的module  
    
    dependencies {
        ....
        implementation project(':commonlib')
        
        if(isNeedHomeMoudle.toBoolean()){
            implementation project(":home")
        }
        if(isNeedDiscoverMoudle.toBoolean()){
            implementation project(":discover")
        }
        if(isNeedFriendMoudle.toBoolean()){
            implementation project(":friends")
        }

        if(isNeedMineMoudle.toBoolean()){
            implementation project(":mine")
        }
    }
    
2.设置对应 module 的 apply plugin和appid，

    下面依HomeMoudle为例，其他module 依次调整
    if (isNeedHomeMoudle.toBoolean()) {
      apply plugin: 'com.android.library'
    } else {
      apply plugin: 'com.android.application'
    }
    
    defaultConfig {
        if (!isNeedMineMoudle.toBoolean()) {
            applicationId configs.mineId
        }
        minSdkVersion configs.minSdkVersion
        targetSdkVersion configs.targetSdkVersion
        versionCode configs.versionCode
        versionName configs.versionName
    }

3.设置module 的manifest
            
    在各modlue 设置的 的src 下创建 名为的moudle的包名，复制对应的manifest 做相应调整，之后在gradle.build的android 目录下添加对应代码，下面依         HomeMoudle为例

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
     </manifest>
  4.在各moudle下添加路由： 依阿里路由为例
  
        commonlib  引入  
        dependencies {
         api 'com.alibaba:fastjson:1.2.9'
         api 'com.alibaba:arouter-api:1.4.1'
        }
       
        
        其他moudle  引入
         dependencies {
            annotationProcessor  'com.alibaba:arouter-compiler:1.2.2'//ARouter
         }
         
        然后在
        defaultConfig{
            ......
            javaCompileOptions {
                annotationProcessorOptions {
                    arguments = [AROUTER_MODULE_NAME: project.getName()]
                }
            }
        }
        
   5.设置理由跳转 ：Commonlib 新建ZRouterConstants 存放路由常量
   
    public class ZRouterConstants {
        public static final String MAIN_ACTIVITY="/app/main";
        public static final String FIND_MAIN_ACTIVITY="/find/main";
        public static final String HOME_MAIN_ACTIVITY="/home/main";
        public static final String DISCOVER_MAIN_ACTIVITY="/discover/main";
        public static final String MINE_MAIN_ACTIVITY="/mine/main";
    }
    
    页面设置跳转
    @Route(path = ZRouterConstants.MAIN_ACTIVITY)
    
    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        findViewById(R.id.tvApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  跳转并携带参数
                ARouter.getInstance().build(ZRouterConstants.HOME_MAIN_ACTIVITY)//跳转至home模块
                        .withString("key3", "test")
                        .navigation();
            }
        });
    }
}
    

   
  
  

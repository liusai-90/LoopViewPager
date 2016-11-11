# LoopViewPager
Android LoopViewPager 轮播图控件

#添加权限
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

#添加依赖
```xml
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.itcastsh:loopviewpager:1.1.1'
}
```

#可配置的属性
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="LoopViewPager">
        <!-- 轮播间隔时间 -->
        <attr name="intervalTime" format="integer|reference" />
        <!-- 是否可以手动滚动 -->
        <attr name="scrollEnable" format="boolean" />
        <!-- 触摸是否停止 -->
        <attr name="touchEnable" format="boolean" />
    </declare-styleable>
    <declare-styleable name="LoopDotsView">
        <!-- 圆点形状 -->
        <attr name="dotShape">
            <enum name="rectangle" value="1" />
            <enum name="oval" value="2"/>
        </attr>
        <!-- 圆点宽度 -->
        <attr name="dotWidth" format="integer|dimension|reference" />
        <!-- 圆点高度 -->
        <attr name="dotHeight" format="integer|dimension|reference" />
        <!-- 圆点距离 -->
        <attr name="dotRange" format="integer|dimension|reference" />
        <!-- 圆点颜色 -->
        <attr name="dotColor" format="color|reference" />
        <!-- 圆点选中颜色 -->
        <attr name="dotSelectColor" format="color|reference" />
        <!-- 圆点资源 -->
        <attr name="dotResource" format="reference" />
        <!-- 圆点选中资源 -->
        <attr name="dotSelectResource" format="reference" />
    </declare-styleable>
</resources>
```

#代码示例
###XML
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.itheima.loopviewpager.LoopViewPager
        android:id="@+id/lvp_pager"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:intervalTime="3000">

        <com.itheima.loopviewpager.LoopTitleView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|center_horizontal"
            android:background="#55000000"
            android:gravity="left|center_vertical"
            android:padding="10dp"
            android:textColor="#FF0000"
            android:textSize="16sp" />

        <com.itheima.loopviewpager.LoopDotsView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|right"
            android:layout_margin="10dp"
            app:dotHeight="10dp"
            app:dotRange="10dp"
            app:dotResource="@mipmap/dot_normal"
            app:dotSelectResource="@mipmap/dot_selected"
            app:dotWidth="10dp" />

    </com.itheima.loopviewpager.LoopViewPager>

</LinearLayout>
```
###Java
```java
    private String[] imageData;
    private List<String> titleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo3);
        imageData = new String[]{
                "http://d.hiphotos.baidu.com/image/h%3D200/sign=72b32dc4b719ebc4df787199b227cf79/58ee3d6d55fbb2fb48944ab34b4a20a44723dcd7.jpg",
                "http://pic.4j4j.cn/upload/pic/20130815/31e652fe2d.jpg",
                "http://pic.4j4j.cn/upload/pic/20130815/5e604404fe.jpg",
                "http://pic.4j4j.cn/upload/pic/20130909/681ebf9d64.jpg",
                "http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg",
        };
        titleData = new ArrayList<>();
        for (int i = 0; i < imageData.length; i++) {
            titleData.add("我的轮播的标题" + (i + 1));
        }
        ((LoopViewPager) findViewById(R.id.lvp_pager)).setImgAndTitleData(imageData, titleData);
    }
```

#运行效果
![image](https://github.com/itcastsh/LoopViewPager/blob/master/simpledemo.gif)

#BUG反馈
反馈邮箱：873259480@qq.com

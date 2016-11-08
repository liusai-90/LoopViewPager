# LoopViewPager
Android LoopViewPager 轮播图控件

#权限
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```
#添加依赖
```java
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
}

dependencies {
	  compile 'com.github.itcastsh:loopviewpager:1.0.0'
	  compile 'com.github.bumptech.glide:glide:3.7.0'
}
```
#代码示例
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
```java
    private int[] imageData;
    private List<String> titleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo3);
        imageData = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6};
        titleData = new ArrayList<>();
        for (int i = 0; i < imageData.length; i++) {
            titleData.add("我的轮播的标题" + (i + 1));
        }
        ((LoopViewPager) findViewById(R.id.lvp_pager)).setImgAndTitleData(imageData, titleData);
    }
```
#运行效果
![image](https://github.com/itcastsh/LoopViewPager/blob/master/simpledemo.gif)
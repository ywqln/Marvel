# 项目笔记

## 疑问
- [ ] 1.mvvm include bind:user="@{user}" user命名规则、含义，和引入的文件中data节点下的name是否必须一致.
- [ ] 2.Cannot find the setter for attribute 'android:onItemSelected' with parameter type lambda on android.support.design.widget.BottomNavigationView.


## MVVM
- 优点：减少了Activity中赋值，findviewById的代码量；更多的关注业务，更新界面数据显示变得方便。
- 缺点：ViewModel与View的耦合度提高了，但是View变得不好复用

## adb常用命令
    1.进入shell：adb shell
    2.安装Apk程序：adb install -r D:\应用程序.apk
    3.安装Apk程序之push向手机写入文件：adb push <local> <remote>   例如：adb push D:\file.txt/system/temp
    4.从手机获取文件：adb pull <remote><local>
    5.卸载apk:adb uninstall -r D:\应用程序.apk
    6.显示当前连接设备:adb devices
    7.重启设备:adb reboot
# 关于真心话大冒险的随机插件
> 这是一个即将有大更新的插件（对以下数据格式会有大变动）
## 如何使用
在插件的数据目录(random.json，会自行生成）仿写以下指令
```
{
  "0": "获得了豁免权",//自带的，可修改
  "1": "最想实现的三个愿望是什么?",
  "max": 1//即你写到哪里就改多大数据，主要是连续的整数
}
```
然后本插件的指令是
```
r
```
来获取一个冒险！
## Todo（画大饼中）
* 增加权限系统以在线修改
* 记录沙雕群友的记录历史
* 可能的数据格式
  * random.json
  ```
  {
    "num":"图片/文本/语音"
  }
  ```
  * data/0.{txt/jpg/...}
  > 由隔壁[获取完整后缀支持](https://github.com/javakam/FileOperator/blob/master/library_core/src/main/java/ando/file/core//FileUtils.kt)

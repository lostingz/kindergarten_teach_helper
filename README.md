# kindergarten_teach_helper

###关于项目
---
写教案，经常要交一些读后感什么的各种文档，
于是就有个这个项目，项目很简单，操作在控制台中，选择对应的系统：
目前支持的有
 
 #### 系统列表 
                
1. 教案生成系统
2. 课件生成系统
3. 图片下载系统
4. 读后感生成系统 

###说明
系统采用爬虫实现，选择对应大类如教案后
选择班级类型：大班、中班、小班、托班

班级后选择教案类型：科学、语言、音乐、社会、主题、亲子、半日、健康

选择对应的要爬取得内容即可将对应的文档（读后感类似，看看代码吧）
爬取到指定的目录中，目前教案和读后感会生成word文档，教案word中支持包含图片，
图片下载系统是对百度图片做的爬虫，输入关键字和需要图片个数
会按照关键字创建目录存储在不同的目录下。
项目也加了前台UI不过还没有作全，只有教案部分

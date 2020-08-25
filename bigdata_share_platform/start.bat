title sharePlatform
::参考https://blog.csdn.net/qq_38974634/article/details/81710382
::java -jar sharePlatform.jar > log.file 2>&1 &
::参考https://tieba.baidu.com/p/6210448600 nohup是linux下得命令
::nohup java -jar sharePlatform.jar &
::参考https://blog.csdn.net/tb9125256/article/details/83239571
@echo off
start javaw -jar sharePlatform.jar > log.file 2>&1
exit
@echo off
::im 表示指定的进程名称，例如javaw.exe
taskkill -f -t -im javaw.exe
exit
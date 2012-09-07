import datetime
import time
import sys
import easygui
print ("hello world")
try:
    minute=int(input("输入倒计时分钟数\n"))
    nowtime = datetime.datetime.now()
    exttime = nowtime + datetime.timedelta(minutes = minute)
    for i in range(0,minute-1):
        rem=minute-i
        print("还有"+'%d'%rem+"分钟")
        time.sleep(60)
    easygui.msgbox("时间到了")
except Exception:
    exc=sys.exc_info()
    for e in exc:
        print(e)

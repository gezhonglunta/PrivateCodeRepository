import datetime
import time
print ("hello world")
try:
    minute=int(input("输入倒计时分钟数\n"))
    nowtime = datetime.datetime.now()
    exttime = nowtime + datetime.timedelta(minutes = minute)
    for i in range(1,minute-1):
        time.sleep(60)
    print("时间到了...")
except:
    print("输入的数字不正确")

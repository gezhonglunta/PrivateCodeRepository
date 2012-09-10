import urllib.request
import socket
import os
import logging

logger = logging.getLogger()
hdlr = logging.FileHandler("forum.txt")
logger.addHandler(hdlr)
logger.setLevel(logging.NOTSET)

def requestUrl(url,proxy,showResult):
    try:
        if logger is not None:
            logger.info("####"+url+"##"+proxy+"####")
        charcode="gbk"
        req = urllib.request.Request(url)
        req.add_header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.83 Safari/537.1")
        req.add_header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        req.add_header("Accept-Charset",charcode)
        req.add_header("Connection","keep-alive")
        if proxy is not None:
            req.set_proxy(proxy,"http")
        result=urllib.request.urlopen(req)
        print(result.info())
        if logger is not None:
            logger.info(result.getcode())
        if showResult:
            data=result.read()
            print(data.decode(charcode,"ignore"))
        result.close()
        return True
    except Exception as e:
        print(e)
        if logger is not None:
            logger.info(e)
        return False
    finally:
        logger.info("####END####")

def readFromFile(file):
    f=open(file)
    for line in f:
        line=line.replace("\r","")
        line=line.replace("\n","")
        yield line
    f.close()

socket.setdefaulttimeout(60)
#url="http://thepiratebay.se/"
url={"http://www.zhenyouge.com/?fromuid=121120","http://www.kk369.info/?fromuid=56059"}
#url={"http://www.baidu.com","http://www.google.com.hk"}
for line in readFromFile("D:\Work\GitHub\PrivateCodeRepository\Data\代理.csv"):
    for u in url:
        requestUrl(u,line,False)
os.system("shutdown /s /f /t 60")

import urllib.request
import socket

def requestUrl(url,proxy,showResult):
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
    if showResult:
        data=result.read()
        print(data.decode(charcode,"ignore"))
    result.close()

socket.setdefaulttimeout(60)
#url="http://thepiratebay.se/"
url="http://www.zhenyouge.com/?fromuid=121120"
requestUrl(url,None,False)

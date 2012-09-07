import http.client
con=http.client.HTTPConnection("www.baidu.com")
con.request("GET","")
r1=con.getresponse();
print(r1.reason)
r1.close()

import MySQLdb

try:
    conn1=MySQLdb.connect(host='192.168.37.30',user='root',passwd='OHciFI_si2',port=3306,charset='utf8')
    cur1=conn.cursor()
    
    cur1.close()
    conn1.close()
except MySQLdb.Error as e:
     print("MySQLdb.Error")

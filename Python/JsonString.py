import json
import string
jsonObj={"ret":"True"}
print(json.dumps(jsonObj))
line=""
while(True):
    line=input("属性,值,[类型];exit结束\n")
    if line == 'exit':
        break
    ls=line.split(",")
    lenth=len(ls)
    print(ls)
    name=None
    value=None
    if lenth > 0:
        name=ls[0]
    if lenth > 1:
        if lenth > 2:
            vtype=ls[2]
            if "string".startswith(vtype):
                value=ls[1]
            elif "int".startswith(vtype):
                value=string.atoi(ls[i])
            elif "bool".startswith(vtype):
                if ls[i]=="true":
                    value=True
                else:
                    value=False
        else:
            value=ls[1]
            jsonObj[name]=value
            pass
        pass
    pass
print(json.dumps(jsonObj))
            

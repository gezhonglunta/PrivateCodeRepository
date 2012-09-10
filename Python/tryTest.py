import logging

logger = logging.getLogger()
hdlr = logging.FileHandler("log.txt")
logger.addHandler(hdlr)
logger.setLevel(logging.NOTSET)

def methodA(ids,message):
    try:
        logger.info("####try####")
        x = int("##")
    except Exception as e:
        logger.info("####Exception####")
    finally:
        logger.info("####"+'%d'%ids+"####END####")

def methodB(message):
    logger = logging.getLogger()
    hdlr = logging.FileHandler("log.txt")
    logger.addHandler(hdlr)
    logger.setLevel(logging.NOTSET)
    try:
        print(message)
        logger.info(message)
        int(message)
    except Exception as e:
        print(e)
        logger.info(e)
    finally:
        print("END")
        logger.info("END")

def log(mess):
    logger.info("Begin:")
    logger.info(mess)

ids={1,2,3}
mess={"一","二","三"}
log("1")
log("2")
log("3")
log("4")

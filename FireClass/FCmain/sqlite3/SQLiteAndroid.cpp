
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string>
#include <jni.h>
#include "platform/FCLog.h"
#include "jni/JniHelper.h"

static int _initialized = 1;

#define CLASS_PATH "com/example/lib/FCJSDCardSQLite"

static void splitFilename (std::string& str)
{
	size_t found = 0;
	found=str.find_last_of("/\\");
	if (found != std::string::npos)
	{
		str = str.substr(found+1);
	}
}

void sqlInit(const char *fullpath)
{
	// if (fullpath == NULL || strlen(fullpath) == 0) return;

	// if( ! _initialized ) {
	// 	JniMethodInfo t;

 //        if (JniHelper::getStaticMethodInfo(t, CLASS_PATH, "init", "(Ljava/lang/String;)Z")) {
 //        	std::string strDBFilename = fullpath;
 //        	splitFilename(strDBFilename);
 //        	jstring jdbName = t.env->NewStringUTF(strDBFilename.c_str());
 //            jboolean ret = t.env->CallStaticBooleanMethod(t.classID, t.methodID, jdbName);
 //            t.env->DeleteLocalRef(jdbName);
 //            t.env->DeleteLocalRef(t.classID);
 //            if (ret) {
 //                _initialized = 1;
 //            }
 //        }	
	// }
}

/** sets an item in the SQLite */
void sqlSetItem(const char* tableName, const char* key, const char* value)
{
	// assert(_initialized);
	
    JniMethodInfo t;

    if (JniHelper::getStaticMethodInfo(t, CLASS_PATH, "setItem", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
        jstring jtableName = t.env->NewStringUTF(tableName);
        jstring jkey = t.env->NewStringUTF(key);
        jstring jvalue = t.env->NewStringUTF(value);
        t.env->CallStaticVoidMethod(t.classID, t.methodID, jtableName, jkey, jvalue);
        t.env->DeleteLocalRef(jtableName);
        t.env->DeleteLocalRef(jkey);
        t.env->DeleteLocalRef(jvalue);
        t.env->DeleteLocalRef(t.classID);
    }
}

/** gets an item from the SQLite */
const char* sqlGetItem(const char* tableName, const char* key)
{
	// assert(_initialized);
    FCLog("这里显示正常3");
    JniMethodInfo t;
    const char * pStr;
    if (JniHelper::getStaticMethodInfo(t, CLASS_PATH, "getItem", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;")) {
        jstring jtableName = t.env->NewStringUTF(tableName);
        jstring jkey = t.env->NewStringUTF(key);
        jstring ret = (jstring)t.env->CallStaticObjectMethod(t.classID, t.methodID, jtableName, jkey);
        pStr = JniHelper::jstring2string(ret).c_str();
        t.env->DeleteLocalRef(ret);
        t.env->DeleteLocalRef(jtableName);
        t.env->DeleteLocalRef(jkey);
        t.env->DeleteLocalRef(t.classID);
    }
    
    
    
    return pStr;
}

/** removes an item from the SQLite */
void sqlRemoveItem(const char* tableName, const char* key)
{
	// assert(_initialized);
    JniMethodInfo t;

    if (JniHelper::getStaticMethodInfo(t, CLASS_PATH, "removeItem", "(Ljava/lang/String;Ljava/lang/String;)V")) {
        jstring jtableName = t.env->NewStringUTF(tableName);
        jstring jkey = t.env->NewStringUTF(key);
        t.env->CallStaticVoidMethod(t.classID, t.methodID, jtableName, jkey);
        t.env->DeleteLocalRef(jtableName);
        t.env->DeleteLocalRef(jkey);
        t.env->DeleteLocalRef(t.classID);
    }
}

void sqlExec(const char* sql)
{
    // assert(_initialized);
    JniMethodInfo t;
    FCLog("这里正常sqlExec");
    if (JniHelper::getStaticMethodInfo(t, CLASS_PATH, "exec", "(Ljava/lang/String;)V")) {
        FCLog("这里jinlaisqlExec");
        jstring jsql = t.env->NewStringUTF(sql);
        FCLog("这里显示正常1");
        t.env->CallStaticVoidMethod(t.classID, t.methodID, jsql);
        FCLog("这里显示正常2");
        t.env->DeleteLocalRef(jsql);
        t.env->DeleteLocalRef(t.classID);
    }
    FCLog("这里显示正常4");
}

void sqlFree()
{
    if(_initialized) {
        JniMethodInfo t;
        if (JniHelper::getStaticMethodInfo(t, CLASS_PATH, "destory", "()V"))
        {
            t.env->CallStaticVoidMethod(t.classID, t.methodID);
            t.env->DeleteLocalRef(t.classID); 
        }
        
        _initialized = 0;
    }
}


#include "FCSocket_andr.h"
#include "JniHelper.h"
#include <jni.h>
// #include "../FCLog.h"

#define CLASSID  "com/example/fileclass/FCClientSocket_Java"

FCSocket_andr::FCSocket_andr(void)
{
}


FCSocket_andr::~FCSocket_andr(void)
{
}

FCSocket * FCSocket_andr::create()
{
	FCSocket_andr * m_socket = new FCSocket_andr();
	if(m_socket)
	{
		m_socket->init();
	}
	return (FCSocket*) m_socket;
}


bool FCSocket_andr::init()
{
	return true;
}

void FCSocket_andr::send(const char * data)
{
	JniMethodInfo minfo;//¶¨ÒåJniº¯ÊýÐÅÏ¢½á¹¹Ìå   
    //getStaticMethodInfo ´Îº¯Êý·µ»ØÒ»¸öboolÖµ±íÊ¾ÊÇ·ñÕÒµ½´Ëº¯Êý   
    bool isHave = JniHelper::getStaticMethodInfo(minfo,CLASSID, "send","(Ljava/lang/String;)V");  
  
    if (!isHave) {  
        // FCLog("jni:´Ëº¯Êý²»´æÔÚ"); 
    }else{  
        // FCLog("jni:´Ëº¯Êý´æÔÚ");  
        //µ÷ÓÃ´Ëº¯Êý   
        jstring jst = minfo.env->NewStringUTF(data);  
        minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID,jst);  
    }
    // FCLog("jni-javaº¯ÊýÖ´ÐÐÍê±Ï");  
}


void FCSocket_andr::setText(const char * data)
{
    JniMethodInfo minfo;
    bool isHave = JniHelper::getStaticMethodInfo(minfo,CLASSID, "setText","(Ljava/lang/String;)V");  
  
    if (!isHave) {  
        // FCLog("jni:´Ëº¯Êý²»´æÔÚ"); 
    }else{  
        // FCLog("jni:´Ëº¯Êý´æÔÚ");  
        //µ÷ÓÃ´Ëº¯Êý   
        jstring jst = minfo.env->NewStringUTF(data);  
        minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID,jst);  
    }
    // FCLog("jni-javaº¯ÊýÖ´ÐÐÍê±Ï");  
}
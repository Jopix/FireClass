#include "../FCLog.h"
#include <android/log.h>
#include <stdio.h>
#include <jni.h>

#define MAX_LEN        kMaxLogLen+1

void FCLog( const char * pszFormat, ...)
{
    bool flag = true;
// #if defined(COCOS2D_DEBUG) || defined(FR_DEVELOP)
//     flag = true;
// #endif
    if (flag)
    {
        char buf[MAX_LEN];

        va_list args;
        va_start(args, pszFormat);        
        vsnprintf(buf, MAX_LEN, pszFormat, args);
        va_end(args);

        __android_log_print(ANDROID_LOG_DEBUG, "cocos2d-x debug info",  buf);
    }
}
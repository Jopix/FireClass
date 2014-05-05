//
//  FCLog.mm
//  FireClass
//
//  Created by chenxia on 14-4-8.
//  Copyright (c) 2014年 Jopix. All rights reserved.
//

#include "FCLog.h"
#include <stdarg.h>
#include <stdio.h>

#import <UIKit/UIAlert.h>


void FCLog(const char * pszFormat, ...)
{
#if FCLOG_DEBUG
    printf("Cocos2d: ");
    char szBuf[kMaxLogLen];
    
    va_list ap;
    va_start(ap, pszFormat);
    vsnprintf(szBuf, kMaxLogLen, pszFormat, ap);
    va_end(ap);
    printf("%s", szBuf);
    printf("\n");
#endif
    
}
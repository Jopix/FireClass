#ifndef __FC_FCLOG_H__
#define __FC_FCLOG_H__

#define FCLOG_DEBUG 1


static const int kMaxLogLen = 16*1024;

void FCLog(const char * pszFormat, ...);


#endif


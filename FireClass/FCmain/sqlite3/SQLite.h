
#ifndef __CC_SQLITE_H__
#define __CC_SQLITE_H__


void sqlInit(const char *fullpath);

/** sets an item in certain TABLE */
void sqlSetItem(const char* tableName, const char* key, const char* value);

/** gets an item from the sqlite */
const char* sqlGetItem(const char* tableName, const char* key);

/** removes an item from the sqlite */
void sqlRemoveItem(const char* tableName, const char* key);

void sqlExec(const char* sql);

/** Frees the allocated resources */
void sqlFree();


#endif // __CC_SQLITE_H__

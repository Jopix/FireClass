
#include "FCToC.h"
#include "FCSocketManager.h"
#include "FCUserSystemManager.h"



void addDevice(const char * dev)
{
	FCSocketManager::shareInstance()->getSocket()->setText(dev);
}

int checkUserNameAndPassWord(const char * username, const char * password){
    
    return FCUserSystemManager::shareInstance()->checkUserNameAndPassWord(username, password);
}

const char * getData(const char * type)
{
    return FCUserSystemManager::shareInstance()->getData(type);
}


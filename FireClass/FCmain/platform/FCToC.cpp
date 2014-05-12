
#include "FCToC.h"
#include "FCSocketManager.h"
#include "../usersystem/FCUserSystemManager.h"

void FCToC::addDevice(const char * dev)
{
	FCSocketManager::shareInstance()->getSocket()->setText(dev);
}

int FCToC::checkUserNameAndPassWord(const char * username, const char * password){
    
    return FCUserSystemManager::shareInstance()->checkUserNameAndPassWord(username, password);
}

// const char * FCToC::getData(const char * type)
// {
//     return FCUserSystemManager::shareInstance()->getData(type);
// }


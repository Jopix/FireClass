
#include "FCToC.h"
#include "FCSocketManager.h"

static void addDevice(const char * dev)
{
	FCSocketManager::shareInstance()->getSocket()->setText(dev);
}
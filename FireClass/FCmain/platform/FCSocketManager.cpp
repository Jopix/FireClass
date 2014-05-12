#include "FCSocketManager.h"
#if FC_PLATFORM == FC_ANDROID
#include "android/FCSocket_andr.h"
#endif

static FCSocketManager * m_shareInstance = nullptr;

FCSocketManager * FCSocketManager::shareInstance()
{
	if(m_shareInstance == nullptr)
	{
		m_shareInstance = new FCSocketManager();
		m_shareInstance->init();
	}
	return m_shareInstance;
}


FCSocketManager::FCSocketManager()
{
}

FCSocketManager::~FCSocketManager()
{
}


void FCSocketManager::init()
{
#if FC_PLATFORM == FC_ANDROID
	m_socket = FCSocket_andr::create();
#endif

}

FCSocket * FCSocketManager::getSocket()
{
    return m_socket;
}

#ifndef __FC_FCSOCKETMANAGER_H__
#define __FC_FCSOCKETMANAGER_H__

#include "FCSocket.h"
#include "FCConfig.h"


class FCSocketManager
{
public:
	static FCSocketManager * shareInstance();

	~FCSocketManager();
    
    FCSocket * getSocket();

private:
	void init();
	FCSocketManager();
	FCSocket * m_socket;
};

#endif
#ifndef __FC_FCSocket_andr_H__
#define __FC_FCSocket_andr_H__

#include "../FCSocket.h"

class FCSocket_andr : public FCSocket
{
public:
	FCSocket_andr();
	~FCSocket_andr();

	static FCSocket * create();

	virtual bool init();
	virtual void send(const char * data);
	virtual void setText(const char * data);

};

#endif
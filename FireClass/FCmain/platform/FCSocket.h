#ifndef __FC_FCSOCKET_H__
#define __FC_FCSOCKET_H__

class FCSocket
{
public:
	
	FCSocket();
	~FCSocket();
	static FCSocket * create();
	virtual bool init();
	virtual void send(const char * data);
	virtual void setText(const char * data);

private:

};
#endif
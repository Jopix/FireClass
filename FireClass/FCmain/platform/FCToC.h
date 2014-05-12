#ifndef __FC_FCToC__
#define __FC_FCToC__


//platform

class FCToC
{
public:
	//platform
	static void addDevice(const char * dev);


	//UserSystem
	static int checkUserNameAndPassWord(const char * username, const char * password);
	// static const char * getUserData(const char * type);
};





#endif
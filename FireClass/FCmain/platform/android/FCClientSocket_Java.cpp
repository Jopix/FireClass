
#include <jni.h>
#include "../FCToC.h"

JNIEXPORT void JNICALL Java_com_example_fileclass_FCClientSocket_1Java_addDevice
  (JNIEnv * env, jclass, jstring){
  	const char* pdata = env->GetStringUTFChars(jstring,NULL);
  	addDevice(pdata);
  }
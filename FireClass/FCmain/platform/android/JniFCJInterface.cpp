#include <android/log.h>
#include <string.h>
#include "jni/JniHelper.h"
#include "../FCToC.h"

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_Interface_FCJInterface
 * Method:    checkUserNameAndPassWord
 * Signature: (Landroid/R/string;Landroid/R/string;)I
 */
 jint Java_com_example_Interface_FCJInterface_checkUserNameAndPassWord(JNIEnv * env, jclass zthis, jstring j_username, jstring j_password) 
  {
  	const char* username = env->GetStringUTFChars(j_username, NULL);
    const char* password = env->GetStringUTFChars(j_password, NULL);
    int ret = FCToC::checkUserNameAndPassWord(username, password);
		env->ReleaseStringUTFChars(j_username, username);
    env->ReleaseStringUTFChars(j_password, password);
    return ret;
  }

/*
 * Class:     com_example_Interface_FCJInterface
 * Method:    getData
 * Signature: (Landroid/R/string;)Landroid/R/string;
 */
// jstring Java_com_example_Interface_FCJInterface_getData(JNIEnv * env, jclass zthis, jstring j_type)
// {
//     const char * type = env->GetStringUTFChars(j_type, NULL);
//     const char * tmpret = FCToC::getUserData(type);    
//     return JniHelper::stoJstring(tmpret);
// }

#ifdef __cplusplus
}
#endif
//
//  FCUserSystemManager.h
//  FireClass
//
//  Created by chenxia on 14-5-6.
//  Copyright (c) 2014年 Jopix. All rights reserved.
//

#ifndef __FireClass__FCUserSystemManager__
#define __FireClass__FCUserSystemManager__

#include <string>

using std::string;

class FCUserSystemManager
{
public:
    
    ~FCUserSystemManager();
    
    static FCUserSystemManager * shareInstance();
    
    
    int checkUserNameAndPassWord(const char * username, const char * password);
    const char * getData(const char * type);
    
    
private:
    FCUserSystemManager();
    string _sUserName;
    string _sPassWord;
};




#endif /* defined(__FireClass__FCUserSystemManager__) */

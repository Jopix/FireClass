//
//  FCUserSystemManager.cpp
//  FireClass
//
//  Created by chenxia on 14-5-6.
//  Copyright (c) 2014年 Jopix. All rights reserved.
//

#include "FCUserSystemManager.h"
#include <cstdlib>

static FCUserSystemManager * s_shareUserSystem = NULL;

FCUserSystemManager::FCUserSystemManager()
{
    
}

FCUserSystemManager * FCUserSystemManager::shareInstance()
{
    if (s_shareUserSystem) {
        s_shareUserSystem = new FCUserSystemManager();
    }
    
    return s_shareUserSystem;
}


FCUserSystemManager::~FCUserSystemManager(){
    
}


int FCUserSystemManager::checkUserNameAndPassWord(const char *username, const char *password){
    
    
    _sUserName = string(username);
    _sPassWord = string(password);
    
    return 0;
}

const char * FCUserSystemManager::getData(const char *type)
{
    return type;
}
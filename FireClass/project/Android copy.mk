LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := fireclass

LOCAL_MODULE_FILENAME := libfireclass

LOCAL_SRC_FILES := ../../../scripting/lua/cocos2dx_support/CCLuaValue.cpp \
				   ../../../scripting/lua/cocos2dx_support/CCLuaStack.cpp \
				   ../../../scripting/lua/cocos2dx_support/CCLuaEngine.cpp \
                   ../../../scripting/lua/cocos2dx_support/Cocos2dxLuaLoader.cpp \
                   ../../../scripting/lua/cocos2dx_support/LuaCocos2d.cpp \
                   ../../../scripting/lua/cocos2dx_support/tolua_fix.c \
                   ../../../scripting/lua/cocos2dx_support/platform/android/CCLuaJavaBridge.cpp \
				   ../../../scripting/lua/cocos2dx_support/rmi/CDELuaMessageHandler.cpp \
				   ../../../scripting/lua/cocos2dx_support/rmi/CDELuaRmiObject.cpp \
				   ../../../scripting/lua/cocos2dx_support/rmi/CLuaCdeOutgoing.cpp \
				   ../../../scripting/lua/cocos2dx_support/rmi/CLuaCdeSerializestream.cpp \
				   ../../../scripting/lua/cocos2dx_support/rmi/CLuaSessionManager.cpp \
                   ../../Classes/AppDelegate.cpp \
				   ../../Classes/ClientAppInit.cpp \
				   ../../Classes/FRKeypadManager.cpp \
				   ../../Classes/ResolutionManager.cpp \
				   ../../Classes/FRHttpClient.cpp \
				   ../../Classes/FRHttpServer.cpp \
				   ../../Classes/mongoose/mongoose.c \
				   ../../Classes/FRZip.cpp \
				   ../../Classes/FRMD5.cpp \
				   ../../Classes/CCDate.cpp \
				   ../../Classes/PackMap.cpp \
				   ../../Classes/FRFile.cpp \
				   ../../Classes/Fanren.cpp \
				   ../../Classes/FREngineUtil.cpp \
				   ../../Classes/md5/md5.c \
				   ../../Classes/minizip/ioapi.c \
				   ../../Classes/minizip/mztools.c \
				   ../../Classes/minizip/zip.c \
				   ../../Classes/minizip/unzip.c \
				   ../../Classes/MapAStar/AStar/AStar.cpp \
                   ../../Classes/MapAStar/AStar/AStarNode.cpp \
				   ../../Classes/MapAStar/AStar/BinaryHeap.cpp \
				   ../../Classes/MapAStar/AStar/Link.cpp \
				   ../../Classes/MapAStar/AStar/MapGrid.cpp \
				   ../../Classes/MapAStar/AStar/PathNode.cpp \
				   ../../Classes/MapAStar/FRTileMap.cpp \
                   ../../Classes/MapAStar/MapAStar.cpp \
                   ../../Classes/CrashManager/AndroidModuleUtils.c \
                   ../../Classes/CrashManager/CrashManagerAndroid.cpp \
                   ../../Classes/CrashManager/jni/CrashJNI.cpp


INCLUDE_PATHS = $(LOCAL_PATH)/../../Classes \
				$(LOCAL_PATH)/../../Classes/minizip \
				$(LOCAL_PATH)/../../Classes/CocoSib \
				$(LOCAL_PATH)/../../Classes/MapAStar \
				$(LOCAL_PATH)/../../Classes/MapAStar/AStar \
				$(LOCAL_PATH)/../../Classes/CrashManager \
				$(LOCAL_PATH)/../../Classes/md5 \
				$(LOCAL_PATH)/../../../UserSystem \
                $(LOCAL_PATH)/../../../boost/include \
				$(LOCAL_PATH)/../../../RMIEngine/include \
				$(LOCAL_PATH)/../../../FRComponent \
				$(LOCAL_PATH)/../../../SPXParser \
				$(LOCAL_PATH)/../../../scripting/lua/tolua \
				$(LOCAL_PATH)/../../../scripting/lua/luajit2/include \
				$(LOCAL_PATH)/../../../scripting/lua/cocos2dx_support

LOCAL_C_INCLUDES := $(INCLUDE_PATHS)
LOCAL_EXPORT_C_INCLUDES := $(INCLUDE_PATHS)

LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocosdenshion_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocos_extension_static
LOCAL_WHOLE_STATIC_LIBRARIES += rmiengine_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocos_tolua_static
LOCAL_WHOLE_STATIC_LIBRARIES += luajit_static
LOCAL_WHOLE_STATIC_LIBRARIES += user_system_static
LOCAL_WHOLE_STATIC_LIBRARIES += frcomponent_static
LOCAL_WHOLE_STATIC_LIBRARIES += spxparser_static

LOCAL_LDLIBS := -lgcc
#LOCAL_LDLIBS += -fuse-ld=bfd
LOCAL_LDLIBS += -L$(LOCAL_PATH)/../../../boost/lib 		-lboost_system_android

LOCAL_CFLAGS := -Wno-psabi
LOCAL_EXPORT_CFLAGS := -Wno-psabi

include $(BUILD_SHARED_LIBRARY)

$(call import-module, cocos2dx)
$(call import-module, cocos2dx/platform/third_party/android/prebuilt/libcurl)
$(call import-module, CocosDenshion/android)
$(call import-module, extensions)
$(call import-module, RMIEngine/lib)
$(call import-module, scripting/lua/proj.android/jni)
$(call import-module, scripting/lua/luajit2/proj.android)
$(call import-module, UserSystem/android)
$(call import-module, FRComponent)
$(call import-module, SPXParser)

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := fireclass

LOCAL_MODULE_FILENAME := libfireclass

LOCAL_SRC_FILES := 	./main.cpp \
					../../../FCmain/platform/android/FCLog.cpp \
					../../../FCmain/platform/FCSocket.cpp \
					../../../FCmain/platform/android/jni/JniHelper.cpp \
					../../../FCmain/platform/android/FCSocket_andr.cpp \
					../../../FCmain/platform/FCSocketManager.cpp \
					../../../FCmain/platform/android/JniFCJInterface.cpp \
					../../../FCmain/platform/FCToC.cpp \
					../../../FCmain/usersystem/FCUserSystemManager.cpp \
					../../../FCmain/sqlite3/SQLiteAndroid.cpp

INCLUDE_PATHS = $(LOCAL_PATH)/../../../FCmain \
				$(LOCAL_PATH)/../../../FCmain/usersystem \
				$(LOCAL_PATH)/../../../FCmain/platform \
				$(LOCAL_PATH)/../../../FCmain/platform/android/jni \
				$(LOCAL_PATH)/../../../FCmain/platform/android \
				$(LOCAL_PATH)/../../../FCmain/sqlite3 

LOCAL_C_INCLUDES := $(INCLUDE_PATHS)
LOCAL_EXPORT_C_INCLUDES := $(INCLUDE_PATHS)

LOCAL_LDLIBS := -lgcc
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

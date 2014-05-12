LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := fireclass

LOCAL_MODULE_FILENAME := libfireclass

LOCAL_SRC_FILES := 	../../../FCmain/platform/FCSocket.cpp \
					../../../FCmain/platform/android/jni/JniHelper.cpp \
					../../../FCmain/platform/android/FCSocket_andr.cpp \
					../../../FCmain/platform/FCSocketManager.cpp \
					../../../FCmain/platform/FCToC.cpp

INCLUDE_PATHS = $(LOCAL_PATH)/../../../FCmain/platform \
				$(LOCAL_PATH)/../../../FCmain/platform/android/jni \
				$(LOCAL_PATH)/../../../FCmain/platform/android


LOCAL_C_INCLUDES := $(INCLUDE_PATHS)
LOCAL_EXPORT_C_INCLUDES := $(INCLUDE_PATHS)

LOCAL_LDLIBS := -lgcc
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := user_system_static

LOCAL_MODULE_FILENAME := libusersystem

LOCAL_SRC_FILES :=	../FRProduct.cpp \
					../FRReportBug.cpp \
					UserSystemManager.cpp \
					FRPlatform.cpp \
					FRAnalyzer.cpp \
					FRAudioManager.cpp \
					FRPushNotificationCenter.cpp \
					FRSocialManager.cpp \
					FRCameraManager.cpp \
					jni_notification/FRPushNotification.cpp \
					jni_notification/FRJPushManager.cpp \
					jni_social/FRSocialCallback.cpp \
					jni_analytics/CApparkJNI.cpp \
					jni_analytics/CFlurryJNI.cpp \
					jni_utils/CUtilsJNI.cpp \
					jni/UserSystemStatus.cpp \
					jni/UserSystemAndroid.cpp \
					jni/UserSystemCallback.cpp \
					jni/UserSystemJNICallback.cpp


LOCAL_C_INCLUDES := $(LOCAL_PATH) \
					$(LOCAL_PATH)/.. \
					$(LOCAL_PATH)/../../RMIEngine/include \
					$(LOCAL_PATH)/jni_analytics \
					$(LOCAL_PATH)/../../cocos2dx \
					$(LOCAL_PATH)/../../fanren2/Classes \
					$(LOCAL_PATH)/../../cocos2dx/platform/android/jni \
					$(LOCAL_PATH)/../../UserSystem/android/shared/jsoncpp/include \
					$(LOCAL_PATH)/../../scripting/lua/tolua \
					$(LOCAL_PATH)/../../scripting/lua/luajit2/include \
					$(LOCAL_PATH)/../../scripting/lua/cocos2dx_support

LOCAL_CPPFLAGS := $(BUILD_FLAGS)
LOCAL_EXPORT_CPPFLAGS := $(BUILD_FLAGS)

LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES += json_cpp_static

include $(BUILD_STATIC_LIBRARY)

$(call import-module, cocos2dx)
$(call import-module, UserSystem/android/shared/jsoncpp)
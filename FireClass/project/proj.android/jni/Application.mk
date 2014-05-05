APP_STL := gnustl_static

APP_CFLAGS := $(BUILD_FLAGS)
APP_CPPFLAGS := $(BUILD_FLAGS) -fsigned-char -frtti -std=c++11

RELEASE_FLAGS = -DFR_RELEASE 

APP_CFLAGS += $(RELEASE_FLAGS)
APP_CPPFLAGS += $(RELEASE_FLAGS)

#APP_OPTIM := debug

APP_ABI := armeabi armeabi-v7a

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
LOCAL_ARM_NEON := true
endif

APP_PLATFORM := android-9
NDK_TOOLCHAIN_VERSION := 4.6

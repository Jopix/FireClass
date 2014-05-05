APP_STL := gnustl_static

BUILD_FLAGS = -fexceptions -Wno-deprecated
# -D__STDC_CONSTANT_MACROS -Wl,-Map=test.map -g

APP_CFLAGS := $(BUILD_FLAGS)
APP_CPPFLAGS := $(BUILD_FLAGS) -fsigned-char -frtti -std=c++11

RELEASE_FLAGS = -DFR_RELEASE -DCOCOS2D_RELEASE=1

APP_CFLAGS += $(RELEASE_FLAGS)
APP_CPPFLAGS += $(RELEASE_FLAGS)

#APP_OPTIM := debug

APP_ABI := armeabi armeabi-v7a

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
LOCAL_ARM_NEON := true
endif

APP_PLATFORM := android-9
NDK_TOOLCHAIN_VERSION := 4.6

#include "JNIClass.h"
#include <iostream>
#include <stdio.h>
#include <stdint.h>
#include <sys/types.h>
#include <sys/sysctl.h>

JNIEXPORT jstring JNICALL Java_JNIClass_getProcInfo(JNIEnv *env, jobject)
{
	char buffer[1024];
    	size_t size=sizeof(buffer);
    	if (sysctlbyname("machdep.cpu.brand_string", &buffer, &size, NULL, 0) < 0) {
        	perror("sysctl");
    	}

	std::string info = buffer;
	return env->NewStringUTF(info.c_str());
}

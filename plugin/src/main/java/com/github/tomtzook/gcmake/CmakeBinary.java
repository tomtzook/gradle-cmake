package com.github.tomtzook.gcmake;

import org.gradle.api.file.Directory;
import org.gradle.api.file.RegularFile;
import org.gradle.api.provider.Provider;

public interface CmakeBinary extends Binary {

    Provider<RegularFile> getCmakeLists();
    Provider<Directory> getOutputDir();
    Provider<String> getGenerator();
}

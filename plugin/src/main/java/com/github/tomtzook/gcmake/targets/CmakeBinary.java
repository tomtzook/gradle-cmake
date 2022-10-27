package com.github.tomtzook.gcmake.targets;

import com.github.tomtzook.gcmake.generator.CmakeGenerator;
import org.gradle.api.file.Directory;
import org.gradle.api.file.RegularFile;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Provider;

public interface CmakeBinary extends Binary {

    Provider<RegularFile> getCmakeLists();
    Provider<Directory> getOutputDir();
    Provider<CmakeGenerator> getGenerator();
    ListProperty<String> getCmakeArgs();
    ListProperty<String> getGeneratorArgs();
}

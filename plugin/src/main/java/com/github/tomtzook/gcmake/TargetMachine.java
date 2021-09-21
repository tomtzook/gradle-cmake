package com.github.tomtzook.gcmake;

import org.gradle.api.file.RegularFile;
import org.gradle.api.provider.Provider;

public interface TargetMachine {

    String getName();

    Provider<RegularFile> getToolchainFile();
}

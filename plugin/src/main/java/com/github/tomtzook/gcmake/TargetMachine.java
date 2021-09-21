package com.github.tomtzook.gcmake;

import org.gradle.api.file.RegularFileProperty;

public interface TargetMachine {

    String getName();

    RegularFileProperty getToolchainFile();
}

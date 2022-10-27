package com.github.tomtzook.gcmake.targets;

import org.gradle.api.file.RegularFileProperty;

public interface TargetMachine {

    String getName();

    RegularFileProperty getToolchainFile();
}

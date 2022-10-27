package com.github.tomtzook.gcmake.targets;

import org.gradle.api.file.RegularFileProperty;

public class DefaultTargetMachine implements TargetMachine {

    private final String mName;
    private final RegularFileProperty mToolchainFile;

    public DefaultTargetMachine(String name, RegularFileProperty toolchainFile) {
        mName = name;
        mToolchainFile = toolchainFile;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public RegularFileProperty getToolchainFile() {
        return mToolchainFile;
    }
}

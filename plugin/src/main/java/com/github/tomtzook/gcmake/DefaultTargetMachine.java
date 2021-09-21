package com.github.tomtzook.gcmake;

import org.gradle.api.file.RegularFile;
import org.gradle.api.provider.Provider;

public class DefaultTargetMachine implements TargetMachine {

    private final String mName;
    private final Provider<RegularFile> mToolchainFile;

    public DefaultTargetMachine(String name, Provider<RegularFile> toolchainFile) {
        mName = name;
        mToolchainFile = toolchainFile;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public Provider<RegularFile> getToolchainFile() {
        return mToolchainFile;
    }
}

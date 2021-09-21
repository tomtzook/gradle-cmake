package com.github.tomtzook.gcmake;

import org.gradle.api.file.RegularFile;
import org.gradle.api.provider.Provider;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DefaultTargetMachine implements TargetMachine, Serializable {

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

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeObject(mName);
        if (mToolchainFile.isPresent()) {
            s.writeBoolean(true);
            s.writeObject(mToolchainFile.get().getAsFile());
        } else {
            s.writeBoolean(false);
        }
    }
}

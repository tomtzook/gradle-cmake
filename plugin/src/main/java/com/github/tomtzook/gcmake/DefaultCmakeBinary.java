package com.github.tomtzook.gcmake;

import org.gradle.api.Task;
import org.gradle.api.file.RegularFile;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.api.provider.Provider;

public class DefaultCmakeBinary implements CmakeBinary {

    private final String mName;
    private final TargetMachine mMachine;
    private final Property<Task> mCompileTask;
    private final Provider<RegularFile> mCmakeLists;

    public DefaultCmakeBinary(ObjectFactory objectFactory, String name, TargetMachine machine, Provider<RegularFile> cmakeLists) {
        mName = name;
        mMachine = machine;
        mCompileTask = objectFactory.property(Task.class);
        mCmakeLists = cmakeLists;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public TargetMachine getTargetMachine() {
        return mMachine;
    }

    @Override
    public Property<Task> getCompileTask() {
        return mCompileTask;
    }

    @Override
    public Provider<RegularFile> getCmakeLists() {
        return mCmakeLists;
    }
}

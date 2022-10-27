package com.github.tomtzook.gcmake;

import com.github.tomtzook.gcmake.generator.CmakeGenerator;
import org.gradle.api.Task;
import org.gradle.api.file.Directory;
import org.gradle.api.file.RegularFile;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.api.provider.Provider;

public class DefaultCmakeBinary implements CmakeBinary {

    private final String mName;
    private final TargetMachine mMachine;
    private final Provider<RegularFile> mCmakeLists;
    private final Provider<Directory> mOutputDir;
    private final Property<Task> mCompileTask;
    private final Provider<CmakeGenerator> mGenerator;

    public DefaultCmakeBinary(ObjectFactory objectFactory, String name, TargetMachine machine,
                              Provider<RegularFile> cmakeLists, Provider<Directory> outputDir,
                              Provider<CmakeGenerator> generator) {
        mName = name;
        mMachine = machine;
        mCmakeLists = cmakeLists;
        mOutputDir = outputDir;
        mCompileTask = objectFactory.property(Task.class);
        mGenerator = generator;
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

    @Override
    public Provider<Directory> getOutputDir() {
        return mOutputDir;
    }

    @Override
    public Provider<CmakeGenerator> getGenerator() {
        return mGenerator;
    }
}

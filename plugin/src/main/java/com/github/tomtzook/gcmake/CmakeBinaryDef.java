package com.github.tomtzook.gcmake;

import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.SetProperty;

import javax.inject.Inject;

public abstract class CmakeBinaryDef {

    private final String mName;

    @Inject
    public CmakeBinaryDef(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public abstract RegularFileProperty getCmakeListsFile();
    public abstract SetProperty<TargetMachine> getTargetMachines();
}

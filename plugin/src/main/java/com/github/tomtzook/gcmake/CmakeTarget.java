package com.github.tomtzook.gcmake;

import com.github.tomtzook.gcmake.generator.CmakeGenerator;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.provider.SetProperty;

import javax.inject.Inject;

public abstract class CmakeTarget {

    private final String mName;

    @Inject
    public CmakeTarget(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public abstract RegularFileProperty getCmakeLists();
    public abstract SetProperty<TargetMachine> getTargetMachines();
    public abstract Property<CmakeGenerator> getGenerator();
}

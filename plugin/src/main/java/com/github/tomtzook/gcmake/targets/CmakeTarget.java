package com.github.tomtzook.gcmake.targets;

import com.github.tomtzook.gcmake.generator.CmakeGenerator;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.ListProperty;
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
    public abstract ListProperty<String> getCmakeArgs();
    public abstract ListProperty<String> getGeneratorArgs();
}

package com.github.tomtzook.gcmake.generator;

import org.gradle.api.Action;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskProvider;

public class CmakeGeneratorImpl implements CmakeGenerator {

    private final String mName;
    private final Property<Class<? extends CmakeGeneratorBuildTask>> mBuildTaskClass;

    public CmakeGeneratorImpl(String name, Property<Class<? extends CmakeGeneratorBuildTask>> buildTaskClass) {
        mName = name;
        mBuildTaskClass = buildTaskClass;
    }

    @Override
    public String getName() {
        return mName;
    }

    public Property<Class<? extends CmakeGeneratorBuildTask>> getBuildTaskClass() {
        return mBuildTaskClass;
    }

    @Override
    public TaskProvider<? extends CmakeGeneratorBuildTask> registerBuildTask(String name, TaskContainer container, Action<? super CmakeGeneratorBuildTask> configure) {
        if (!mBuildTaskClass.isPresent()) {
            throw new IllegalStateException("BuildTask class not configured");
        }

        return container.register(name, mBuildTaskClass.get(), configure);
    }
}

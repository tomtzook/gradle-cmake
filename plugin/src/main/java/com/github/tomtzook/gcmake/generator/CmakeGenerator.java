package com.github.tomtzook.gcmake.generator;

import org.gradle.api.Action;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskProvider;

public interface CmakeGenerator {
    String getName();
    TaskProvider<? extends CmakeGeneratorBuildTask> registerBuildTask(String name,
                                                                      TaskContainer container,
                                                                      Action<? super CmakeGeneratorBuildTask> configure);
}

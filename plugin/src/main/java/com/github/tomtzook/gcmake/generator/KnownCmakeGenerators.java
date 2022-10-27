package com.github.tomtzook.gcmake.generator;

import com.github.tomtzook.gcmake.tasks.MakeBuildTask;
import com.github.tomtzook.gcmake.tasks.NinjaBuildTask;
import org.gradle.api.Action;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskProvider;

public enum KnownCmakeGenerators implements CmakeGenerator {
    UNIX_MAKEFILES("Unix Makefiles") {
        @Override
        public TaskProvider<? extends CmakeGeneratorBuildTask> registerBuildTask(String name,
                                                                                 TaskContainer container,
                                                                                 Action<? super CmakeGeneratorBuildTask> configure) {
            return container.register(name, MakeBuildTask.class, configure);
        }
    },
    NINJA("Ninja") {
        @Override
        public TaskProvider<? extends CmakeGeneratorBuildTask> registerBuildTask(String name,
                                                                                 TaskContainer container,
                                                                                 Action<? super CmakeGeneratorBuildTask> configure) {
            return container.register(name, NinjaBuildTask.class, configure);
        }
    }
    ;

    private final String mGeneratorName;

    KnownCmakeGenerators(String generatorName) {
        mGeneratorName = generatorName;
    }

    @Override
    public String getName() {
        return mGeneratorName;
    }
}

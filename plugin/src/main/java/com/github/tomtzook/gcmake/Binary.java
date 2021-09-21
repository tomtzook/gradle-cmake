package com.github.tomtzook.gcmake;

import org.gradle.api.Task;
import org.gradle.api.component.SoftwareComponent;
import org.gradle.api.provider.Provider;

public interface Binary extends SoftwareComponent {

    @Override
    String getName();

    TargetMachine getTargetMachine();

    Provider<Task> getCompileTask();
}

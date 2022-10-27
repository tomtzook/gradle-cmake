package com.github.tomtzook.gcmake.generator;

import org.gradle.api.Task;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;

public interface CmakeGeneratorBuildTask extends Task {

    @Input
    ListProperty<String> getArgs();
    @OutputDirectory
    DirectoryProperty getBuildDir();
}

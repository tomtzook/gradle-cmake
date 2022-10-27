package com.github.tomtzook.gcmake.generator;

import org.gradle.api.Task;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.OutputDirectory;

public interface CmakeGeneratorBuildTask extends Task {

    @OutputDirectory
    DirectoryProperty getBuildDir();
}

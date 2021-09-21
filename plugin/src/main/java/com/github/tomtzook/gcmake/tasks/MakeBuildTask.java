package com.github.tomtzook.gcmake.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.Directory;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFile;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.internal.ExecAction;
import org.gradle.process.internal.ExecActionFactory;

import javax.inject.Inject;

public abstract class MakeBuildTask extends DefaultTask {

    @OutputDirectory
    public abstract DirectoryProperty getBuildDir();

    @Inject
    protected ExecActionFactory getExecActionFactory() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void run() {
        Directory buildDir = getBuildDir().get();

        ExecAction execAction = getExecActionFactory().newExecAction();
        execAction.workingDir(buildDir.getAsFile());
        execAction.executable("make");

        execAction.execute();
    }
}

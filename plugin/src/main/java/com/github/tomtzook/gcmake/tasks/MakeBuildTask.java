package com.github.tomtzook.gcmake.tasks;

import com.github.tomtzook.gcmake.generator.CmakeGeneratorBuildTask;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.Directory;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.internal.ExecAction;
import org.gradle.process.internal.ExecActionFactory;

import javax.inject.Inject;

public abstract class MakeBuildTask extends DefaultTask implements CmakeGeneratorBuildTask {

    @Input
    public abstract ListProperty<String> getArgs();

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

        if (getArgs().isPresent()) {
            execAction.args(getArgs().get());
        }

        execAction.execute();
    }
}

package com.github.tomtzook.gcmake.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.Directory;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFile;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.internal.ExecAction;
import org.gradle.process.internal.ExecActionFactory;

import javax.inject.Inject;

public abstract class CmakeBuildTask extends DefaultTask {

    private static final String CMAKE_TOOLCHAIN_PARAM = "-DCMAKE_TOOLCHAIN_FILE=%s";

    @InputFiles
    public abstract RegularFileProperty getCmakeListsFile();

    @InputFiles
    @Optional
    public abstract RegularFileProperty getToolchainFile();

    @OutputDirectory
    public abstract DirectoryProperty getOutputDir();

    @Inject
    protected ExecActionFactory getExecActionFactory() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void run() {
        RegularFile cmakeLists = getCmakeListsFile().get();
        Directory buildDir = getOutputDir().get();

        ExecAction execAction = getExecActionFactory().newExecAction();
        execAction.workingDir(buildDir.getAsFile());
        execAction.executable("cmake");

        if (getToolchainFile().isPresent()) {
            RegularFile toolchainFile = getToolchainFile().get();
            execAction.args(String.format(CMAKE_TOOLCHAIN_PARAM, toolchainFile.getAsFile().getAbsolutePath()));
        }

        execAction.args(cmakeLists.getAsFile().getAbsoluteFile().getParent());

        execAction.execute();
    }
}

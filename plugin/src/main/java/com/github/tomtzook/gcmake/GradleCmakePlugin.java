package com.github.tomtzook.gcmake;

import com.github.tomtzook.gcmake.generator.CmakeGenerator;
import com.github.tomtzook.gcmake.generator.CmakeGeneratorBuildTask;
import com.github.tomtzook.gcmake.generator.KnownCmakeGenerators;
import com.github.tomtzook.gcmake.tasks.CmakeBuildTask;
import com.github.tomtzook.gcmake.tasks.MakeBuildTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.tasks.Delete;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskProvider;

import java.util.HashSet;
import java.util.Set;


public class GradleCmakePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        TaskContainer tasks = project.getTasks();
        ObjectFactory objectFactory = project.getObjects();

        TargetMachineFactory targetMachineFactory = new DefaultTargetMachineFactory(objectFactory);
        project.getExtensions().add(TargetMachineFactory.class, "machines", targetMachineFactory);
        TargetMachine hostMachine = targetMachineFactory.getHost();

        GradleCmakeExtension extension = project.getExtensions().create("cmake", GradleCmakeExtension.class);
        extension.getOutputDir().convention(project.getLayout().getBuildDirectory().dir("cmake"));

        project.afterEvaluate((p)-> {
            extension.getTargets().all((target)-> {
                Set<TargetMachine> targetMachines = new HashSet<>();
                if (!target.getTargetMachines().isPresent()) {
                    targetMachines.add(hostMachine);
                } else {
                    Set<TargetMachine> set = target.getTargetMachines().get();
                    if (set.isEmpty()) {
                        targetMachines.add(hostMachine);
                    } else {
                        targetMachines.addAll(set);
                    }
                }

                for (TargetMachine targetMachine : targetMachines) {
                    String name = String.format("%s_%s", target.getName(), targetMachine.getName());
                    DefaultCmakeBinary binary = new DefaultCmakeBinary(objectFactory,
                            name, targetMachine,
                            target.getCmakeLists(),
                            extension.getOutputDir().dir(String.format("%s/%s",
                                    target.getName(), targetMachine.getName())),
                            target.getGenerator());

                    project.getComponents().add(binary);
                }
            });
        });

        project.getComponents().withType(DefaultCmakeBinary.class, (binary)-> {
            CmakeGenerator generator = binary.getGenerator().getOrElse(KnownCmakeGenerators.UNIX_MAKEFILES);

            TaskProvider<CmakeBuildTask> cmake = tasks.register(String.format("cmake%s", binary.getName()),
                    CmakeBuildTask.class,
                    (task) -> {
                        task.getCmakeListsFile().set(binary.getCmakeLists());
                        task.getToolchainFile().set(binary.getTargetMachine().getToolchainFile());
                        task.getGenerator().set(generator);
                        task.getOutputDir().set(binary.getOutputDir());
                    });

            String generatorTaskName = String.format("runGenerator%s",
                    generator.getName().replace(' ', '_'));
            TaskProvider<? extends Task> generatorTask = generator.registerBuildTask(generatorTaskName, tasks,
                    (task) -> {
                task.dependsOn(cmake);
                task.getBuildDir().set(cmake.get().getOutputDir());
            });

            binary.getCompileTask().set(generatorTask);
        });

        tasks.register("cmakeClean", Delete.class,
                (task) -> {
                    task.delete(extension.getOutputDir());
                });

        tasks.register("cmakeBuild",
                (task) -> {
                    project.getComponents().withType(Binary.class, (binary)-> {
                        task.dependsOn(binary.getCompileTask());
                    });
                });
    }
}

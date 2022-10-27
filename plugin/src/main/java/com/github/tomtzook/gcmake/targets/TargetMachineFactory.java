package com.github.tomtzook.gcmake.targets;

import org.gradle.api.NamedDomainObjectContainer;

public interface TargetMachineFactory {

    TargetMachine getHost();

    NamedDomainObjectContainer<TargetMachine> getCustomMachines();
}

package com.github.tomtzook.gcmake;

import org.gradle.api.NamedDomainObjectContainer;

public interface TargetMachineFactory {

    TargetMachine getHost();

    NamedDomainObjectContainer<TargetMachine> getCustomMachines();
}

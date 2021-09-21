package com.github.tomtzook.gcmake;

import com.castle.util.os.Platform;
import com.castle.util.os.System;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.model.ObjectFactory;

public class DefaultTargetMachineFactory implements TargetMachineFactory {

    private final ObjectFactory mObjectFactory;
    private final NamedDomainObjectContainer<TargetMachine> mCustomMachines;

    public DefaultTargetMachineFactory(ObjectFactory objectFactory) {
        mObjectFactory = objectFactory;
        mCustomMachines = mObjectFactory.domainObjectContainer(TargetMachine.class,
                (name)-> new DefaultTargetMachine(name, mObjectFactory.fileProperty()));
    }

    @Override
    public TargetMachine getHost() {
        Platform platform = System.platform();
        String name = String.format("%s-%s",
                platform.getOperatingSystem().name().toLowerCase(),
                platform.getArchitecture().name().toLowerCase());

        return new DefaultTargetMachine(name, mObjectFactory.fileProperty());
    }

    @Override
    public NamedDomainObjectContainer<TargetMachine> getCustomMachines() {
        return mCustomMachines;
    }
}

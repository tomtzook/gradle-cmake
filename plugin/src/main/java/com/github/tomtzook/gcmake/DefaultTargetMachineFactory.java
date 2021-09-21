package com.github.tomtzook.gcmake;

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
        return new DefaultTargetMachine("host", mObjectFactory.fileProperty());
    }

    @Override
    public NamedDomainObjectContainer<TargetMachine> getCustomMachines() {
        return mCustomMachines;
    }
}

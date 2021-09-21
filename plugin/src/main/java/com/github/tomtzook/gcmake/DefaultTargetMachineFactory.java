package com.github.tomtzook.gcmake;

import org.gradle.api.model.ObjectFactory;

public class DefaultTargetMachineFactory implements TargetMachineFactory {

    private final ObjectFactory mObjectFactory;

    public DefaultTargetMachineFactory(ObjectFactory objectFactory) {
        mObjectFactory = objectFactory;
    }

    @Override
    public TargetMachine host() {
        return new DefaultTargetMachine("host", mObjectFactory.fileProperty());
    }
}

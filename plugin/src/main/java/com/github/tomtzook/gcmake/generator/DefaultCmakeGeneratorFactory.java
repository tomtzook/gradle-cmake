package com.github.tomtzook.gcmake.generator;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.model.ObjectFactory;

public class DefaultCmakeGeneratorFactory implements CmakeGeneratorFactory {

    private final NamedDomainObjectContainer<CmakeGenerator> mCustomGenerators;

    public DefaultCmakeGeneratorFactory(ObjectFactory objectFactory) {
        mCustomGenerators = objectFactory.domainObjectContainer(CmakeGenerator.class,
                (name)-> new CmakeGeneratorImpl(name, objectFactory.property(null)));
    }

    @Override
    public CmakeGenerator getUnixMakefiles() {
        return KnownCmakeGenerators.UNIX_MAKEFILES;
    }

    @Override
    public CmakeGenerator getNinja() {
        return KnownCmakeGenerators.NINJA;
    }

    @Override
    public NamedDomainObjectContainer<CmakeGenerator> getCustomGenerators() {
        return mCustomGenerators;
    }
}

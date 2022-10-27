package com.github.tomtzook.gcmake.generator;

import org.gradle.api.NamedDomainObjectContainer;

public interface CmakeGeneratorFactory {

    CmakeGenerator getUnixMakefiles();
    CmakeGenerator getNinja();

    NamedDomainObjectContainer<CmakeGenerator> getCustomGenerators();
}

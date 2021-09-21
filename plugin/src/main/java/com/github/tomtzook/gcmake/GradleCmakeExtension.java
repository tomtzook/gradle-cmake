package com.github.tomtzook.gcmake;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.file.DirectoryProperty;

public abstract class GradleCmakeExtension {

    public abstract NamedDomainObjectContainer<CmakeTarget> getTargets();

    public void targets(final Action<? super NamedDomainObjectContainer<CmakeTarget>> action) {
        action.execute(getTargets());
    }

    public abstract DirectoryProperty getOutputDir();
}

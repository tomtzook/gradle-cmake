package com.github.tomtzook.gcmake;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.file.DirectoryProperty;

public abstract class GradleCmakeExtension {

    public abstract DirectoryProperty getOutputDir();
    public abstract NamedDomainObjectContainer<CmakeBinaryDef> getTargets();

    public void targets(final Action<? super NamedDomainObjectContainer<CmakeBinaryDef>> action) {
        action.execute(getTargets());
    }
}

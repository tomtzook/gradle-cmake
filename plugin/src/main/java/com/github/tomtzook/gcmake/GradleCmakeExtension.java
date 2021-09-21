package com.github.tomtzook.gcmake;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.file.DirectoryProperty;

public abstract class GradleCmakeExtension {

    public abstract NamedDomainObjectContainer<CmakeBinaryDef> getBinaries();

    public void binaries(final Action<? super NamedDomainObjectContainer<CmakeBinaryDef>> action) {
        action.execute(getBinaries());
    }

    public abstract DirectoryProperty getOutputDir();
}

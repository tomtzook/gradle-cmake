# gradle-cmake
A Gradle integration plugin for CMake


Given a directory structure:
```
project
│   build.gradle 
│
└───src
│   │   CMakeLists.txt
│   │   ...
```

Configure `build.gradle` with the `CMakeLists.txt` file to compile for the local machine:
```groovy
plugins {
  id 'io.github.tomtzook.gradle-cmake'
}

cmake {
    targets {
        example_project {
            cmakeLists.set(file('src/CMakeLists.txt'))
            targetMachines.add(machines.host)
        }
    }
}

tasks.clean.dependsOn tasks.cmakeClean
tasks.build.dependsOn tasks.cmakeBuild
```

Now run `gradle build` to build the code, which will run `cmake` and `make`.

To allow cross-compiling, configure `customMachines` using `CMAKE_TOOLCHAIN_FILE`:
```groovy
machines.customMachines.register("linux-aarch64") {
    it.toolchainFile.set(project.file('aarch64-linux-gnu-gcc.cmake'))
}

def compileMachineTargets = [machines.host]
compileMachineTargets.addAll(machines.customMachines)

cmake {
    targets {
        example_project {
            cmakeLists.set(file('src/CMakeLists.txt'))
            targetMachines.addAll(compileMachineTargets)
        }
    }
}
```

Where `aarch64-linux-gnu-gcc.cmake` configures the cross compiler:
```cmake
set(CMAKE_SYSTEM_NAME Linux)
set(CMAKE_SYSTEM_PROCESSOR aarch64)

set(CMAKE_C_COMPILER "aarch64-linux-gnu-gcc")
set(CMAKE_CXX_COMPILER "aarch64-linux-gnu-g++")
set(CMAKE_AR "aarch64-linux-gnu-ar" CACHE FILEPATH Archiver)
set(CMAKE_RANLIB "aarch64-linux-gnu-ranlib" CACHE FILEPATH Indexer)

set(CMAKE_FIND_ROOT_PATH /usr/aarch64-linux-gnu)

set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)

set(CMAKE_C_FLAGS "-march=armv8-a")
set(CMAKE_CXX_FLAGS "-march=armv8-a")

# cache flags
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS}" CACHE STRING "c flags")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}" CACHE STRING "c++ flags")
```

![JMat: Matrices for Java](https://1.bp.blogspot.com/-HBB4foZmv4I/YPxmtma_HJI/AAAAAAAAAcY/TuLtrSs18VwJPzXNnTyyxJwWRYMOUY3dACLcBGAsYHQ/s928/Matrices+for+Java+Banner.png)
JMat is a library developed for Java which allows for the use and manipulation of matrices. The library handles fundamental mathematical operations on matrices, and also implements further functionality including discretisation, I/O, and sorting.

## JMat Core

The core JMat package implements the basic matrix functionality for the whole library using three main types of matrix object: the generic matrix (`GMatrix`), the comparable matrix (`CMatrix`), and the double matrix (`DMatrix`). Using these matrix objects, use and manipulation of matrices is possible.

## Building JMat

To build the JMat library, first clone the Git main branch (which holds the latest version of JMat) using the command:

	$ git clone https://github.com/JoshMcDonagh/JMat.git

JMat uses Gradle as the build system, with each JMat package contained in sub-folders (there is only one that currently exists in this version):

* *jmat.core* - this is the library package which contains the fundamental matrix functionality.

Within the Git foler cloned, use the following command to build the JMat library and run all of the JUnit tests:

	$ gradle build

Alternatively, access https://github.com/JoshMcDonagh/JMat/releases for the latest version of the JMat library.

## License

```
Copyright 2021 Joshua McDonagh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

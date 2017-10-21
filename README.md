# CCC Template Project

This repository contains a common code to be used in the Catalysts Coding Contest. The module 'sample' demonstrates its usage.

The need for this originated from the recurring way the tasks and levels are designed in this contest.

The project is intended to be used with Intellij IDEA. Support for executing JUnit 5 tests is mandatory.

## Usage

The project defines some conventions that, if adhered, make it a very easy and fast task to add a new level.

- Add a new module for your current contest like vienna_2017
- Implement the `at.ccc.common.api.Level` interface in a class.

The name of this class will be used to lookup the level files. The level files are expected to be under `resources` a folder named after the lowercase variant of your level name. If your level-class is called `Sample`, put your level files in a folder named `sample`. All files in this directory will be processed. Output will be written file-by-file in a sub-folder named `results`.

See the sample module for a working example.
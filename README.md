# Spark Utils [![Build Status](https://travis-ci.org/andersonkmi/spark-utils.svg?branch=master)](https://travis-ci.org/andersonkmi/spark-utils)
Library with common objects used in other Spark/Scala projects

## 1. Introduction

This project contains some shared classes/objects used in other Spark projects I have developed so far and since I got
tired of copying and pasting the same bits of code, I realized it was about time to create this repo.

## 2. Description

Some of the classes found here are related to:
- Command line argument parsing
- AWS S3 download/upload functions
- Timer utils
- Decompressing data (zip format)
- File utils

## 3. Build and deploy

In order to build and deploy this library, perform the following commands:

```scala
$ sbt compile
$ sbt publishLocal
```

## 4. Changelog

All changes are listed in [CHANGELOG.md](CHANGELOG.md)

## 5. References

General-purpose links and information are listed here.

- https://stackoverflow.com/questions/41031317/passing-spark-dataset-as-an-function-argument
- https://www.scala-sbt.org/release/docs/Publishing.html
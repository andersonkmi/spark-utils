# Changelog
All notable changes to this project will be documented in this file.

## [Unreleased]

## [v1.2.6]

### Changed
- Refactoring to DataUtils object

## [v1.2.5]

### Changed
- DataFormat enumeration type to String

## [v1.2.4]

### Changed
- Data format enumeration values

## [v1.2.3]

### Removed
- sysout call(s)

## [v1.2.2]

### Added
- Data format enumeration

### Removed
- AWS S3 support

## [v1.2.1]

### Changed
- Updating to AWS SDK v2.

## [v1.2.0]

### Changed
- Updating to AWS SDK v2 - working in progress.

## [v1.1.0]

### Changed
- Improved logic.

## [v1.0.9]

### Changed
- Improved logic.

## [v1.0.8]

### Changed
- Fixed incorrect exception class name.

## [v1.0.7]

### Added
- Parsed args validation method. If an argument is missing its value, it throws an exception.
- Added unit test when args list is empty

## [v1.0.6]

### Changed
- If string array passed is null, returns an empty map

## [v1.0.5]

### Added
- Utility methods for file manipulation  

## [v1.0.4]

### Removed
- Updated to Scala 2.12  

## [v1.0.3]

### Removed
- Dependency with Databricks CSV. 

### Changed
- Request headers argument to get method.

## v1.0.2
### Changed
- Logging for data set handling object.
- Added persistence for datasets.
- Timing information is stored in an array.


[Unreleased]: https://github.com/andersonkmi/spark-utils/compare/v1.0.2...HEAD
[v1.0.3]: https://github.com/andersonkmi/spark-utils/compare/v1.0.2...v1.0.3
[v1.0.4]: https://github.com/andersonkmi/spark-utils/compare/v1.0.3...v1.0.4
[v1.0.5]: https://github.com/andersonkmi/spark-utils/compare/v1.0.4...v1.0.5
[v1.0.6]: https://github.com/andersonkmi/spark-utils/compare/v1.0.5...v1.0.6
[v1.0.7]: https://github.com/andersonkmi/spark-utils/compare/v1.0.6...v1.0.7
[v1.0.8]: https://github.com/andersonkmi/spark-utils/compare/v1.0.7...v1.0.8
[v1.0.9]: https://github.com/andersonkmi/spark-utils/compare/v1.0.8...v1.0.9
[v1.1.0]: https://github.com/andersonkmi/spark-utils/compare/v1.1.0...v1.0.9
[v1.2.0]: https://github.com/andersonkmi/spark-utils/compare/v1.2.0...v1.1.0
[v1.2.1]: https://github.com/andersonkmi/spark-utils/compare/v1.2.1...v1.2.0
[v1.2.2]: https://github.com/andersonkmi/spark-utils/compare/v1.2.2...v1.2.1
[v1.2.3]: https://github.com/andersonkmi/spark-utils/compare/v1.2.3...v1.2.2
[v1.2.4]: https://github.com/andersonkmi/spark-utils/compare/v1.2.4...v1.2.3
[v1.2.5]: https://github.com/andersonkmi/spark-utils/compare/v1.2.5...v1.2.4
[v1.2.6]: https://github.com/andersonkmi/spark-utils/compare/v1.2.6...v1.2.5
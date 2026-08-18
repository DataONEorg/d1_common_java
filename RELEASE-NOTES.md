# d1_common_java Release Notes

## 2.5.1 (2026-08-18)
- Intent: Deliver a bug-fixed patch release following 2.5.0.
- Bug fixes: Fixed an encoding issue that prevented content containing special characters from being correctly retrieved from HTTP multipart requests (issue #13, PR #14).

## 2.5.0 (2026-07-06)

- Intent: Deliver a modernization and maintenance release focused on Java 17/21/25 compatibility, security hardening, and release pipeline updates.
- Security fixes: Fixed XML External Entity (XXE) vulnerabilities caused by external DTD references and external ENTITY references during XML parsing; all users should upgrade to `2.5.0` or later.
- New features: Java runtime/build modernization for current LTS and current-feature JVMs (issue `#4`), refreshed build and deploy workflow defaults, migration to `maven.dataone.org` release/snapshot distribution, updated format bundle (`formats 1.28`), and adoption of released `d1_test_resources 2.4.0`.
- Bug fixes: Fixed unescaped semicolon handling in URL query/fragment parsing (issue `#8`, PR `#9`) and resolved marshalling/styleSheet-era compatibility pain points from post-Java 8 behavior (issue `#3`).
- Dependency and tooling updates: Upgraded core dependencies and plugins including `log4j`, `commons-lang3`, `commons-io`, servlet compatibility updates, and modern Maven compiler/jar plugin versions.

## 2.4.2 (2020-12-01)

- Intent: Improve object-format publication pipeline and finalize another patch release.
- New features: Added XSL transformation to derive v1 list from v2 and generated object format list from source as part of release flow.
- Bug fixes: Release/version bump consistency and POM formatting cleanup tied to merged feature/release branches.

## 2.4.1 (2020-11-30)

- Intent: Deliver a security-focused maintenance patch release off 2.4.0.
- New features: Dependency hardening rollout via merged security patch branch and release branch workflow.
- Bug fixes: Updated vulnerable dependency set including `commons-fileupload`, `commons-beanutils`, and `commons-collections4`; included release/publish workflow corrections and formatting cleanup.

## 2.4.0 (2020-11-30)

- Intent: Aggregate long-running trunk/2.3-era functional updates into a 2.4 baseline and formal release branch.
- New features: Expanded object format catalog substantially (R/SAS/MATLAB, EML 2.2.0, additional metacat-aligned formats, and MDQ/gzip updates; refs include #8239 and #8754); improved authorization-subject lookup behavior; relaxed redirect filtering to support broader 3xx handling when redirects are allowed.
- Bug fixes: Exception deserialization now prioritizes `identifier` attribute and includes related tests; improved HTML exception response condensation/configuration (#8227); upgraded servlet alignment with Tomcat 7 expectations (#8086); fixed duplicate BaseException string-writer behavior and reduced noisy configuration stack traces.

## 2.3.0 (2016-11-08)

- Intent: Complete marshalling modernization from JiBX toward JAXB and clean obsolete build dependencies.
- New features: Added JAXB annotations/object factories and refactored `TypeMarshaller` to JAXB-based operation; introduced marshaller/unmarshaller caching and validation/schema updates; added `MarshallingException` and removed JiBX exceptions from method signatures (#7836, #7844, #7845, #7846, #7852, #7832).
- Bug fixes: Added null safety in equals/hashCode for JAXB workflows; removed obsolete ant/JiBX/BCEL build dependencies; fixed build and dependency issues (including missing `log4j` direct dependency).

## 2.2.0 (2016-06-14)

- Intent: Deliver object-format list updates and continue release stream progression.
- New features: Added TSV/object-format inventory updates copied from CN metacat context; refreshed schema-generation source settings.
- Bug fixes: Corrected object-format totals/count metadata and release prep updates (including #7693/#7745 references).

## 2.1.0 (2016-02-24)

- Intent: Stabilize the v2 line and prep 2.1 release artifacts.
- New features: Updated schema-generation source and API documentation references; branch prep toward 2.1 release stream (#7493, #7648 context).
- Bug fixes: Added regression coverage for `AuthUtils` stack-overflow subject-info scenario (#7604); release engineering corrections during branch/tag process.

## 2.0.0 (2015-11-24)

- Intent: Introduce the major v2 API/type generation line and deprecation strategy for mutable system metadata workflows.
- New features: Added v2 schemas/types and v2 service interfaces (`CNView`, `MNView`, `MNPackage`, v2 `SystemMetadata`, v2 object format/log types); introduced capability APIs (`getNodeCapabilities`/`getCapabilities`); added node property extensions and updateSystemMetadata patterns (issues including #3729, #3744, #4029, #4030, #4173, #4258).
- Bug fixes: Resource leak removal in multipart resolver path (#2211); broad code cleanup/import maintenance; dependency and build compatibility improvements for v2 generation.

## 1.2.2 (2014-11-26)

- Intent: Correct version lineage and modernize dependency declarations.
- New features: Updated `d1_test_resources` dependency target (to upcoming 1.3.0 at the time) and improved dependency declaration clarity.
- Bug fixes: Cleaned undeclared transitive dependencies; updated Apache HttpComponents versions; corrected release sequencing mismatch where a downstream artifact referenced an untagged 1.2.1 revision.

## 1.2.1 (2014-10-27)

- Intent: Perform point-release progression after 1.2.0.
- New features: No major functional additions documented in-range.
- Bug fixes: Primarily version increment/tag consistency for branch maintenance.

## 1.2.0 (2014-05-02)

- Intent: Grow utility robustness, type-comparison capabilities, and dependency hygiene for the 1.2 line.
- New features: Added checksum comparison helper (`areChecksumsEqual`); expanded `TypeCompareUtil` capabilities (including subtype reporting/sorting); promoted select utility visibility and comparator support.
- Bug fixes: Resource-leak fix noted in tag metadata (#5311); null handling fixes in `AuthUtils` and encoding utilities; dependency scope and javadoc/reporting build stabilization.

## 1.1.3 (2013-07-01)

- Intent: Prepare for CCI-1.2 release and improve administrator authorization behavior.
- New features: Included `cn.administrators` subjects in service-method restriction evaluation (Redmine #3845).
- Bug fixes: Corrected related debug logging for administrator-subject augmentation and finalized branch versioning for 1.1.3 (#3822/#3840).

## 1.1.2 (2013-06-04)

- Intent: Reduce dependency footprint for downstream users.
- New features: Dependency scope adjustments to minimize transitive dependency exposure.
- Bug fixes: Build/dependency hygiene improvements; no major runtime defect called out.

## 1.1.1 (2013-02-22)

- Intent: Provide a targeted compatibility patch for temporary-file handling.
- New features: Portable temp-file strategy that allows OS-managed temp directory selection.
- Bug fixes: Eliminated hard-coded `/tmp` behavior to resolve Windows interoperability issue (Bugzilla #5869).

## 1.1.0 (2013-01-08)

- Intent: Advance to schema/API 1.1 capabilities and broaden supported formats/utilities.
- New features: Added query interfaces/types and v1.1 schema alignment; expanded object format metadata (MIME type/extensions, issue set including #1861/#2995/#2996/#2997/#3135); added object-format CSV/XLS transformation assets; cloning utilities in `AccessUtil`.
- Bug fixes: Stream/resource cleanup moved into `finally` blocks; improved subject/group validation rules (for example no group-in-group nesting, Redmine #2833); retained and propagated prior NodeList utility fix.

## 1.0.2 (2012-07-03)

- Intent: Deliver a focused post-1.0.1 maintenance release.
- New features: No major new API features.
- Bug fixes: Fixed `NodelistUtil.selectNode(..)` runtime incompatibility by replacing `TreeSet` usage with `HashSet` where `Node` was not `Comparable`.

## 1.0.1 (2012-06-05)

- Intent: Cut the 1.0.1 general-availability release from the RC line.
- New features: GA packaging of accumulated RC changes.
- Bug fixes: Release/version/dependency alignment for final 1.0.1 artifacts.

# lcnc-code-generator

Codegen framework libraries for the Nubo Native Platform (NNP) low-code platform.

| Module | Coordinates |
|---|---|
| entitygenerator | `com.nubons.nnp.lcnc.codegen:entitygenerator` |
| errorhandler | `com.nubons.nnp.lcnc.codegen.errorhandler:errorhandler` |
| resterrorhandler | `com.nubons.nnp.lcnc.codegen.errorhandler:resterrorhandler` |
| mapperfactory | `com.nubons.nnp.lcnc.codegen:mapperfactory` |

## Using the packages

Add the public Maven repository (no token needed) to your `pom.xml`:

```xml
<repositories>
  <repository>
    <id>nnp-codegen</id>
    <url>https://nubo-native-platform.github.io/SD-ELC-EG-Codegen-Framework/maven/</url>
  </repository>
</repositories>
```

The same packages are also listed under this repository's **Packages** tab (GitHub Packages, which requires a token to download).

## Building

Requires JDK 21.

```bash
mvn verify          # build and test all modules
mvn install         # install all modules to your local ~/.m2
```

## Releasing

Every push to `main` builds all modules and publishes any module whose version isn't published yet.
To release a module, bump its `<version>` in its `pom.xml` and merge to `main`.

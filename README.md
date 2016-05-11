# gradle-plugin-maven-pass

:ambulance: This is a rescue project

This is a plugin to obtain settings.xml credentials.

Add dependency below to your project:

```
buildscript {
  dependencies {
    classpath group: 'info.boaventura', name: 'gradle-build-maven-credentials', version: '1.0.0'
  }
}
apply plugin: 'info.boaventura.maven.credentials'
```

This will inject the very first username and password found in maven settings.xml credentials.

- `Username: ${mvncredentials.username}`
- `Password: ${mvncredentials.password}`

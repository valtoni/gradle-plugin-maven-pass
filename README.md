# gradle-plugin-maven-pass
This is a plugin to obtain settings.xml credentials.

Add dependency below to your project:

```
  dependencies {
    classpath group: 'info.boaventura', name: 'gradle-build-maven-credentials', version: '1.0.0-SNAPSHOT'
  }
```

This will inject the very first username and password found in maven settings.xml credentials.

- `Username: ${mvncredentials.username}`
- `Password: ${mvncredentials.password}`

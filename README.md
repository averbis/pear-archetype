# pear-archetype [![Build Status](https://travis-ci.com/averbis/pear-archetype.svg?branch=master)](https://travis-ci.com/averbis/pear-archetype) ![](https://img.shields.io/maven-central/v/de.averbis.textanalysis/pear-archetype.svg?style=flat)


Project template to bootstrap UIMA annotators written in Java and package them as a PEAR package. 

## Prerequisites

- JDK 1.8 or later
- Maven

## Generate a new UIMA Java Annotator Project
New Java UIMA annotator projects with PEAR packaging can be generated with the following maven command:

```
mvn archetype:generate -DarchetypeGroupId=de.averbis.textanalysis -DarchetypeArtifactId=pear-archetype -DarchetypeVersion=2.0.1
```
You need to define a [groupId, artifactId and version](https://maven.apache.org/guides/mini/guide-naming-conventions.html) for your UIMA annotator project. 

1. Specify a `groupId` for the project. It must follow [Java's package name rules](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html).
```
Define value for property 'groupId': com.example
```

2. Define an `artifactId` for the project. It should consist of lowercase letters and must not contain special characters or dots.
```
Define value for property 'artifactId': my-annotator
```

3. Define a `version` for your UIMA annotator. 
```
Define value for property 'version' 1.0-SNAPSHOT: 1.0
```

4. Define a `package` for your UIMA Java annotator. It must follow [Java's package name rules](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html).
```
Define value for property 'package': com.example.nlp
```

5. Define a `annotatorClassName` for your annotator. It should always begin with a capital letter and must not contain any special characters or space characters. If there are multiple words in the `annotatorClassName`, then each word should start with a capital letter. 
```
Define value for property 'annotatorClassName' Myannotator: MyAnnotator
```

6. Review and confirm the annotator configuration:

```
Confirm properties configuration:
groupId: com.example
artifactId: my-annotator
version: 1.0
package: com.example.nlp
annotatorClassName: MyAnnotator
 Y: : y
```

(optional) Likewise the project can be generated in non-interactive mode with the following command:

```
mvn archetype:generate -DgroupId=com.example -DartifactId=my-annotator -Dversion=1.0 -Dpackage=com.example.nlp -DannotatorClassName=MyAnnotator -DarchetypeGroupId=de.averbis.textanalysis -DarchetypeArtifactId=pear-archetype -DarchetypeVersion=2.0.1 -DinteractiveMode=false
```


## Result

This will generate a UIMA annotator project structure within the `my-annotator` directory:

```
my-annotator/
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── nlp
    │   │               └── MyAnnotator.java
    │   └── resources
    │       ├── com
    │       │   └── example
    │       │       └── nlp
    │       │           └── MyAnnotatorTypesystem.xml
    │       └── META-INF
    │           └── org.apache.uima.fit
    │               └── types.txt
    └── test
        └── java
            └── com
                └── example
                    └── nlp
                        └── PearPackageIT.java

```


## Build the generated Annotator Project

Navigate into the project directory:
```
cd my-annotator
```
Build the annotator project with maven:
```
mvn clean install
```
This will assemble a PEAR package in the target directory.

```
target/my-annotator-1.0.pear
```

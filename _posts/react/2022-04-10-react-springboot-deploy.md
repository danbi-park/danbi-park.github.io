---
title:  "war로 압축하여 배포하기"
excerpt: "spring security + react + proxy"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - react
tags:
  - react
  - deploy
  - basic
last_modified_at: 2022-04-10
---

개발 환경 
- Framework  : Spring boot  
- Build Tool : Gradle  
- IDEA       : Intellj   
 
폴더 구조
```
  admin
   ├─ frontend
   │   ├─config
   │   ├─public
   │   ├─scripts
   │   └─src
   └─ mydatazmonitoring
       └─src
           └─ main
               ├─java
               └─resources
```

```gradle
plugins {
	id 'org.springframework.boot' version '2.6.3'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
	id 'war'
}

war {
	archiveBaseName = 'root'
	archiveFileName = 'root.war'
	archiveVersion = "0.0.0"
}

processResources {
	duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.EXCLUDE
}

def webappDir = "D:/admin/frontend"

sourceSets {
	main {
		resources {
			srcDirs = ["$webappDir/build", "$projectDir/src/main/resources"]
		}
	}
}

processResources {
	dependsOn "buildReact"
	duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.EXCLUDE
}

task buildReact(type: Exec) {
	dependsOn "installReact"
	workingDir "$webappDir"
	inputs.dir "$webappDir"
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
		commandLine "npm.cmd", "run-script", "build"
	} else {
		commandLine "npm", "run-script", "build"
	}
}

task installReact(type: Exec) {
	workingDir "$webappDir"
	inputs.dir "$webappDir"
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
		commandLine "npm.cmd", "audit", "fix"
		commandLine 'npm.cmd', 'install'
	} else {
		commandLine "npm", "audit", "fix"
		commandLine 'npm', 'install'
	}
}

```

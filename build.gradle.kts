import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  kotlin("jvm") version Globals.kotlinVersion
  kotlin("plugin.spring") version Globals.kotlinVersion
  id("com.github.ben-manes.versions") version Globals.Gradle.Plugin.versionsVersion
}

group = Globals.Project.groupId
version = Globals.Project.version

sourceSets {
  main {
    java.srcDir("src/main/kotlin")
  }
  test {
    java.srcDir("src/test/kotlin")
  }
}

java {
  sourceCompatibility = Globals.javaVersion
  targetCompatibility = Globals.javaVersion
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))
  implementation(kotlin("test-junit"))
  implementation(kotlin("test-junit5") as String) {
    exclude(group = "org.junit.vintage", module = "*")
  }

  testImplementation(platform("org.junit:junit-bom:${Globals.junitJupiterVersion}"))
  testImplementation("org.junit.vintage:junit-vintage-engine")
  testImplementation("org.junit.jupiter:junit-jupiter")

  testImplementation("org.assertj:assertj-core:${Globals.assertjVersion}")
}

tasks {
  withType(Wrapper::class.java) {
    gradleVersion = Globals.Gradle.wrapperVersion
    distributionType = Wrapper.DistributionType.BIN
  }

  withType<KotlinCompile>().configureEach {
    kotlinOptions {
      freeCompilerArgs += "-Xjsr305=strict"
      jvmTarget = "${Globals.javaVersion}"
    }
  }

  withType<Test> {
    useJUnitPlatform()
    testLogging {
      showExceptions = true
      showStandardStreams = true
      events(PASSED, SKIPPED, FAILED)
    }
  }

  named("clean") {
    doLast {
      delete(
          project.buildDir,
          "${project.projectDir}/out"
      )
    }
  }
}

defaultTasks("clean", "test")

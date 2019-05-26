import org.gradle.api.JavaVersion

object Globals {
  object Project {
    const val artifactId = "strategy-pattern-example"
    const val groupId = "com.github.daggerok"
    const val version = "1.1-SNAPSHOT"
  }

  val javaVersion = JavaVersion.VERSION_1_8

  const val kotlinVersion = "1.3.31"
  const val assertjVersion = "3.12.2"
  const val junitJupiterVersion = "5.5.0-M1"
  const val junitPlatformVersion = "1.5.0-M1"

  object Gradle {
    const val wrapperVersion = "5.4.1"

    object Plugin {
      const val versionsVersion = "0.21.0"
    }
  }
}

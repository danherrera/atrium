import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    kotlin("multiplatform")
    id("build-logic.kotlin-conventions")
    id("ch.tutteli.gradle.plugins.spek")
}

if (name != "atrium-specs") {
    apply(plugin = "ch.tutteli.gradle.plugins.kotlin.module.info")
}


val spekVersion: String by rootProject.extra

spek {
    if (rootProject.name != "gradle-kotlin-dsl-accessors") {
        version = spekVersion
    }
}

//TODO 1.2.0 remove once we moved away from spec to kotlin-test
if (name == "atrium-logic" || name == "atrium-verbs" || name == "atrium-verbs-internal") {
    the<ch.tutteli.gradle.plugins.junitjacoco.JunitJacocoPluginExtension>()
        .allowedTestTasksWithoutTests.set(listOf("jsNodeTest"))
}

kotlin {
    jvm {
        // for module-info.java and Java sources in test
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform {
                includeEngines("spek2", "junit-jupiter")
            }
        }
    }

    //TODO 1.2.0 switch from LEGACY to IR
    js(LEGACY) {
        nodejs {
            testTask {
                useMocha {
                    // timeout in milliseconds,
                    // Windows regularly has a timeout with the default which
                    // at the time of writing was 2000
                    timeout = "10000"
                }
            }
        }
    }

    sourceSets {
        configureLanguageSettings()

        commonMain {
            dependencies {
                api(kotlin("reflect"))
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

// this is necessary due to some crazy kotlin plugin voodo. If we define this in the rootProject itself,
// then it does not work.
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.configure<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension> {
        lockFileDirectory = rootProject.projectDir.resolve("gradle")
    }
}

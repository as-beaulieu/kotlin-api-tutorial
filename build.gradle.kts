import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.collections.listOf

plugins {
	id("org.springframework.boot") version "2.6.9"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.kotlinspring"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	//web
	implementation("org.springframework.boot:spring-boot-starter-web")

	//web
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//validator
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//logging
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")


	//db
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
//    runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("io.mockk:mockk:1.10.4")
	testImplementation("com.ninja-squad:springmockk:3.0.1")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sourceSets {
	test {
		//Gradle 7.1+
		java {
			setSrcDirs(listOf("src/test/integrated", "src/test/unit"))
		}


		//before 7.1 gradle
//		withConvention(KotlinSourceSet::class) {
//			kotlin.setSrcDirs(listOf("src/test/integrated", "src/test/unit"))
//		}
	}
}
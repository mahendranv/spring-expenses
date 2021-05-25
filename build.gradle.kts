import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	id("com.netflix.dgs.codegen") version "4.6.4"
}

group = "com.ex2.gql"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
	packageName = "com.ex2.gql.dgmodels"
	schemaPaths = mutableListOf("${projectDir}/src/main/resources/schema")
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")

	// https://mvnrepository.com/artifact/com.netflix.graphql.dgs/graphql-dgs-spring-boot-starter
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:3.12.1")

	// JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.0")

	// H2 Database
	runtimeOnly("com.h2database:h2")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

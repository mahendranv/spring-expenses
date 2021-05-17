import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
}

group = "com.ex2.gql"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

//generateJava{
//	schemaPaths = ["${projectDir}/src/main/resources/schema"]
//	packageName = 'com.ex2.gql.data'
//	generateClient = true
//}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")

	// https://mvnrepository.com/artifact/com.netflix.graphql.dgs/graphql-dgs-spring-boot-starter
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:3.12.1")

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

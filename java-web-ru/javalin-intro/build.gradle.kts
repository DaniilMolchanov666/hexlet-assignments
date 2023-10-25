plugins {
    application
    id("com.adarshr.test-logger") version "3.2.0"
}

application {
    mainClass.set("exercise.App")
}

group = "exercise"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // BEGIN
    
    // END
    // https://mvnrepository.com/artifact/io.javalin/javalin
    implementation("io.javalin:javalin:5.6.1")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.konghq:unirest-java:3.13.0")
}

tasks.test {
    useJUnitPlatform()
}

testlogger {
  showStandardStreams = true
}

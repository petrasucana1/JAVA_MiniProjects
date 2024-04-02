plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.apache.poi:poi-ooxml:5.2.2")
    //implementation("org.apache.logging.log4j:log4j-core:2.17.1")
    implementation ("org.freemarker:freemarker:2.3.31");
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    manifest {
        attributes["Main-Class"] = "company.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}


tasks.test {
    useJUnitPlatform()
}
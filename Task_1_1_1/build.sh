mkdir -p build/classes doc
javac -d build/classes src/main/java/ru/nsu/etronin/task111/*.java
javadoc -d doc src/main/java/ru/nsu/etronin/task111/*.java
java -cp build/classes ru.nsu.etronin.task111.Main

Experiment on parsing Twig with Antlr
=====================================

This is an proof of concept on how Twig could be parsed with Antlr as Lexing and Parsing-Engine, plus, me learning more about parsing and grammars in general.

It is *not* a useful project or library.

The grammar and code specificly tests some properties that where found to be problematic in the existing JTwig grammar (ie. deep array and property access or calculations inside array accesses) in the most basic and simple way. It ignores a lot of the features that makes JTwig so flexible (ie. array.style access to Lists, Maps, Objects, Property-Style access to Maps, Object-Properties, Object-Methods).

Usage
-----
The project consists of
 - [the grammar – Twig.g4](src/main/antlr4/de/mazdermind/playground/antlrtwig/grammar/Twig.g4)
 - [evaluation code](src/main/java/de/mazdermind/playground/antlrtwig/evaluator/)
 - [a set of tests](src/test/java/de/mazdermind/playground/antlrtwig/evaluator/expression)

To run the Tests use your IDE or run
```
mvn test
```

To generate the Java-Code from the grammar-file run
```
mvn generate-sources
```

Usage in your IDE
-----------------
In your IDE of choice, mark `target/generated-sources/antlr4` as sources-root, so your IDE automatically picks up the generated parser and lexer classes.

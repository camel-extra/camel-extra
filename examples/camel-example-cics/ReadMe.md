# CICS Sample

This route uses CICS Transaction Gateway Camel Component to invoke a COBOL Program in a Mainframe

How to run:

1) in one shell just type:

```
  mvn camel:run
```

This Camel route invokes every minute a Cobol program in a CICS Server. The program returns the timestamp.

Ostendo
=======
Listens for OP_RETURN messages on the Bitcoin blockchain and prints them to standard output. All OP_RETURN messages are displayed, including messages contained within unconfirmed transactions. The application is written in Java and leverages the bitcoinj library. This application leverages the main production bitcoin network. If you wish to use testnet instead, this can be configured in PeerListener.java.

Prerequisites:
-------------------------

* JDK 1.6 or higher
* Maven
* For additional dependency information refer to pom.xml

Build Instructions:
-------------------------

Building an executable jar:
> mvn package

The standalone executable jar can be found at: ./target/ostendo-1.0-SNAPSHOT-jar-with-dependencies.jar

Usage:
-------------------------

Running as an executable jar:

> java -jar target/ostendo-1.0-SNAPSHOT-jar-with-dependencies.jar

Log information will be output to ./logs/error.log

Example Output:
-------------------------

```
| Hex Value | UTF-8 Value |
| 69643b6a65666662657a6f732e6964 | id;jeffbezos.id |
| 69643b6c61727279706167652e6964 | id;larrypage.id |
| 69643b6a6f686e656c746f6e2e6964 | id;johnelton.id |
| 69643b6a6f6e726f6d65726f2e6964 | id;jonromero.id |
| 6a134153435249424553504f4f4c30315049454345 | ASCRIBESPOOL01PIECE |
```

Test Execution:
-------------------------

Execute all unit tests:
> mvn test

Limitations:
-------------------------

* Some messages may be obsfucated, this application simply converts the hex value to a UTF-8 string.
* Levereges a MemoryBlockStore for storing blockchain information.

License
-------------------------

The MIT License (MIT)

Copyright (c) 2015 com.gmail.lifeofreilly

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



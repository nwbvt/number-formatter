# number-formatter

A Clojure app for printing out the (American) English form of numbers up to 1E63-1
Note: This is using the American number system, as defined http://www.merriam-webster.com/table/dict/number.htm
No milliards or crazy upping names every 10^6, for those more used to the European number systems

## Usage

To run this through leiningen, execute <code>lein run n</code> where n is the number you want formatted.
To run this as a jar,
* build the jar through <code>lein uberjar</code>,
* then execute <code>java -jar target/number-formatter-0.1.0-SNAPSHOT-standalone.jar n</code>

## License

Copyright © 2013 Nick Brown

Distributed under the Eclipse Public License, the same as Clojure.

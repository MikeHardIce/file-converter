# file-converter

Command line tool to convert files into various formats.

Current conversions:
+ json-yaml (json to yaml)
+ edn-yaml (edn to yaml)

Todo:
+ add more complex test files
+ json-yaml
+ edn-yaml, yaml-edn
+ yaml-xml, xml-yaml, json-xml, xml-json
+ csv-orc, orc-csv
+ accept folders containing files
+ image conversion
+ would be interesting to convert transitively depending on what is available,
  meaning json-xml -> json-yaml, yaml-xml

## Installation

No package available yet.

## Usage

    $ java -jar file-converter-0.1.0-standalone.jar json-yaml <your-json-file> <output-yaml-file>

    or

    $ java -jar file-converter-0.1.0-standalone.jar json-yaml <your-json-file>

    to print to the terminal


## License

```
MIT License

Copyright (c) 2023 MikeHardIce

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
```

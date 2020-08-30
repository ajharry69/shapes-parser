# Shapes Parser
A parser that will parse a contrived syntax language (a made-up syntax) and return an object representation of the input string (a tree of objects).

## Parser Rules
These are the rules of the parser.
- The shape's syntax language consists of shapes: **_squares_** and **_circles_**.
- Each shape has a start symbol, a label, zero or more inner shapes, and the end symbol. Examples: `[1]` or  `(HELLO)`, `(BOY[12])`, `(APPLE(MAN)[65])`
- The square bracket symbol denotes the square shape - starts with the symbol `[` and ends with the symbol `]`. Example: `[123]`
- The parenthesis symbol denotes the circle shape - starts with the symbol `(` and ends with the symbol `)`.  Example: `(CIRCLE)`
- A shape must have a label.
- Squares must be labeled with  **numbers only**.
- Squares can only contain other squares.
- Circles must be labeled with **uppercase letters only**.
- Circles can contain squares or other circles.
- If the input is invalid, throw an exception.

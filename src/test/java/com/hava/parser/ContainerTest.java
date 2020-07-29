package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeInputException;
import com.hava.parser.exceptions.MalformedShapeInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ContainerTest {

    @Test
    public void emptyContainerInstance_returnsEmptyListOfShapes() {
        Container container = new Container();
        assertEquals(0, container.getShapes().size());
    }

    @Test
    public void parseInputContainingUnexpectedCharacters_throwsInvalidShapeInputException() {
        // unexpected/unsupported characters are: @#$
        Container container = new Container();
        assertThrows(InvalidShapeInputException.class, () -> container.parse("@#$"));
        assertThrows(InvalidShapeInputException.class, () -> container.parse("[@#$"));
        assertThrows(InvalidShapeInputException.class, () -> container.parse("#[@(#$"));
    }

    @Test
    public void parseInputContainingAStartLabelWithoutACorrespondingEndLabel_throwsMalformedShapeInputException() {
        Container container = new Container();
        // an opening square bracket must have a closing square bracket
        assertThrows(MalformedShapeInputException.class, () -> container.parse("[13)"));
        assertThrows(MalformedShapeInputException.class, () -> container.parse("([13)"));
        assertThrows(MalformedShapeInputException.class, () -> container.parse("([13)]"));
        assertThrows(MalformedShapeInputException.class, () -> container.parse("]([13)]"));

        // in between opening and closing square brackets, there must only be numbers
        assertThrows(MalformedShapeInputException.class, () -> container.parse("[]"));
        assertThrows(MalformedShapeInputException.class, () -> container.parse("[ ]"));
        assertThrows(MalformedShapeInputException.class, () -> container.parse("[2D]"));
        assertThrows(MalformedShapeInputException.class, () -> container.parse("[E2D]"));
    }

    @Test
    public void parseValidStringInput() throws Exception {
        Container container = new Container();

        assertEquals("[[ 12 ]]", container.parse("[12]").toString());
        assertEquals("[[ 12 [[ 34 ]] ]]", container.parse("[12[34]]").toString());
//        assertEquals("[[ 12 [[ 34 [[ 67 ]] ]] ]]", container.parse("[12[34[67]]]").toString());
//        assertEquals("[[ 12 [[ 34 ], [ 56 ]] ]]", container.parse("[12[34][56]]").toString());
    }

}

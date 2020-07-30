package com.hava.parser;

import com.hava.parser.exceptions.InvalidShapeEndLabelException;
import com.hava.parser.exceptions.InvalidShapeException;
import com.hava.parser.exceptions.InvalidShapeStartLabelException;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class ShapeTest {
    @Test
    public void shapeCreatedWithoutInnerShapes_returnsEmptyInnerShapes() throws Exception {
        Shape shape = new Shape("LABEL", '[', ']');
        assertEquals(0, shape.getInnerShapes().size());
    }

    @Test
    public void shapeInstanceWithNullOrEmptyStringLabel_throwsInvalidShapeException() {
        assertThrows(InvalidShapeException.class, () -> new Shape(null, '[', ']'));
        assertThrows(InvalidShapeException.class, () -> new Shape("", '(', ')'));
    }

    @Test
    public void shapeInstanceWithUnsupportedStartLabel_throwsInvalidShapeStartLabelException() {
        // supported start labels are '[' for Square or '(' for circle
        assertThrows(InvalidShapeStartLabelException.class, () -> new Shape("null", '#', ')'));
        assertThrows(InvalidShapeStartLabelException.class, () -> new Shape("null", '$', ')'));
        assertThrows(InvalidShapeStartLabelException.class, () -> new Shape("LABEL", '#', ']'));
        assertThrows(InvalidShapeStartLabelException.class, () -> new Shape("null", '\'', ']'));
    }

    @Test
    public void shapeInstanceWithUnsupportedEndLabel_throwsInvalidShapeEndLabelException() {
        // supported start labels are '[' for Square or '(' for circle
        assertThrows(InvalidShapeEndLabelException.class, () -> new Shape("null", '[', '#'));
        assertThrows(InvalidShapeEndLabelException.class, () -> new Shape("null", '[', '$'));
        assertThrows(InvalidShapeEndLabelException.class, () -> new Shape("LABEL", '(', '#'));
        assertThrows(InvalidShapeEndLabelException.class, () -> new Shape("null", '(', '\''));
    }

    @Test
    public void shapeToString() throws Exception {
        assertEquals("( LABEL )", new Shape("LABEL", '(', ')').toString());
        assertEquals("[ 123 ]", new Shape("123", '[', ']').toString());
    }

    @Test
    public void shapeEquality() throws Exception {
        int uid = 1;
        Shape shape = new Shape("12", '[', ']', Collections.emptyList(), uid);
        assertEquals(shape, new Shape("12", '[', ']', Collections.emptyList(), uid));
        assertNotEquals(shape, new Shape("12", '[', ']', Collections.emptyList(), uid + 1));
        // creating instance of shape without passing uid param (always) creates a unique shape
        assertNotEquals(new Shape("12", '[', ']', Collections.emptyList()), new Shape("12", '[', ']', Collections.emptyList()));
    }
}

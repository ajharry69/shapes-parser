package com.xently.parser;

import com.xently.parser.exceptions.InvalidShapeEndLabelException;
import com.xently.parser.exceptions.InvalidShapeException;
import com.xently.parser.exceptions.InvalidShapeStartLabelException;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeTest {
    @Test
    public void shapeCreatedWithoutInnerShapes_returnsEmptyInnerShapes() throws RuntimeException {
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
    public void shapeToString() throws RuntimeException {
        assertEquals("( LABEL )", new Shape("LABEL", '(', ')').toString());
        assertEquals("[ 123 ]", new Shape("123", '[', ']').toString());
    }

    @Test
    public void shapeEquality() throws RuntimeException {
        int uid = 1;
        Shape shape = new Shape("12", '[', ']', Collections.emptyList(), uid);
        assertEquals(shape, new Shape("12", '[', ']', Collections.emptyList(), uid));
        assertNotEquals(shape, new Shape("12", '[', ']', Collections.emptyList(), uid + 1));
        // creating instance of shape without passing uid param (always) creates a unique shape
        assertNotEquals(new Shape("12", '[', ']', Collections.emptyList()), new Shape("12", '[', ']', Collections.emptyList()));
    }

    @Test
    public void addInnerShapes_returnsListOfShapes_thatIsAnUpdatedVersionCurrentShapesInnerShapesList() throws RuntimeException {
        Shape shape = new Shape("12", '[', ']');
        List<Shape> innerShapes = shape.addInnerShapes(new Square("123"), new Circle("ABC"));
        assertEquals(2, shape.getInnerShapes().size());
        assertEquals(innerShapes, shape.getInnerShapes());

        // will add at least 5 more inner shapes to the shape above
        for (int i = 1; i <= 5; i++) {
            // for every even number of i, add two more inner shapes
            List<Shape> _innerShapes = i % 2 == 0 ? shape.addInnerShapes(new Square("" + i), new Square("" + (i * 2)))
                    : shape.addInnerShapes(new Square("" + i));
            assertEquals(_innerShapes, shape.getInnerShapes());
        }

        assertEquals(9, shape.getInnerShapes().size());
    }
}

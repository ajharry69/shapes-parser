package com.xently.parser;

import com.xently.parser.exceptions.InvalidInnerShapeException;
import com.xently.parser.exceptions.InvalidShapeInputException;
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
        assertThrows(InvalidShapeInputException.class, () -> container.parse(null));
        assertThrows(InvalidShapeInputException.class, () -> container.parse(""));
        assertThrows(InvalidShapeInputException.class, () -> container.parse("@#$"));
        assertThrows(InvalidShapeInputException.class, () -> container.parse("[@#$"));
        assertThrows(InvalidShapeInputException.class, () -> container.parse("#[@(#$"));
    }

    @Test
    public void parseWithValidSquareStringInput() throws RuntimeException {
        Container container = new Container();

        assertThrows(InvalidInnerShapeException.class, () -> container.parse("[[12]]"));
        assertEquals("{[ 12 ]}", container.parse("[12]").toString());
        // spaces will be striped off prior to shape(s) building(actual parsing)
        assertEquals("{[ 123 ]}", container.parse("[123 ]").toString());
        assertEquals("{[ 123 ], [ 45 ]}", container.parse("[123][45]").toString());
        assertEquals("{[ 12 {[ 34 ]} ]}", container.parse("[12[34]]").toString());
        // spaces will be striped off prior to shape(s) building(actual parsing)
        assertEquals("{[ 12 {[ 34 ]} ]}", container.parse("[1 2[3 4]]").toString());
        assertEquals("{[ 121 {[ 34 ], [ 567 ]} ]}", container.parse("[121[34][567]]").toString());
        assertEquals("{[ 12 {[ 341 ], [ 56 ], [ 78 ]} ]}", container.parse("[12[341][56][78]]").toString());
        assertEquals("{[ 12 {[ 341 ], [ 56 ], [ 78 ], [ 516 ], [ 718 ], [ 526 ], [ 728 ]} ]}", container.parse("[12[341][56][78][516][718][526][728]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 67 ]} ]} ]}", container.parse("[12[34[67]]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 67 ], [ 89 ], [ 90 ]} ]} ]}", container.parse("[12[34[67][89][90]]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 67 {[ 1 ]} ]} ]} ]}", container.parse("[12[34[67[1]]]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 67 {[ 1 {[ 32 ]} ]} ]} ]} ]}", container.parse("[12[34[67[1[32]]]]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 11 ], [ 67 {[ 1 {[ 32 ]} ]} ]} ]} ]}", container.parse("[12[34[11][67[1[32]]]]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 3 ], [ 67 {[ 1 ], [ 2 ]} ]} ]} ]}", container.parse("[12[34[67[1][2]][3]]]").toString());
        assertEquals("{[ 12 {[ 34 {[ 3 ], [ 67 {[ 1 ], [ 2 ]} ]} ]} ], [ 999 ]}", container.parse("[12[34[67[1][2]][3]]][999]").toString());
    }

    @Test
    public void parseWithValidCircleStringInput() throws RuntimeException {
        Container container = new Container();

        assertThrows(InvalidInnerShapeException.class, () -> container.parse("((12))"));
        assertEquals("{( 12 )}", container.parse("(12)").toString());
        // spaces will be striped off prior to shape(s) building(actual parsing)
        assertEquals("{( 123 )}", container.parse("(123 )").toString());
        assertEquals("{( 123 ), ( 45 )}", container.parse("(123)(45)").toString());
        assertEquals("{( 12 {( 34 )} )}", container.parse("(12(34))").toString());
        // spaces will be striped off prior to shape(s) building(actual parsing)
        assertEquals("{( 12 {( 34 )} )}", container.parse("(1 2(3 4))").toString());
        assertEquals("{( 121 {( 34 ), ( 567 )} )}", container.parse("(121(34)(567))").toString());
        assertEquals("{( 12 {( 341 ), ( 56 ), ( 78 )} )}", container.parse("(12(341)(56)(78))").toString());
        assertEquals("{( 12 {( 341 ), ( 56 ), ( 78 ), ( 516 ), ( 718 ), ( 526 ), ( 728 )} )}", container.parse("(12(341)(56)(78)(516)(718)(526)(728))").toString());
        assertEquals("{( 12 {( 34 {( 67 )} )} )}", container.parse("(12(34(67)))").toString());
        assertEquals("{( 12 {( 34 {( 67 ), ( 89 ), ( 90 )} )} )}", container.parse("(12(34(67)(89)(90)))").toString());
        assertEquals("{( 12 {( 34 {( 67 {( 1 )} )} )} )}", container.parse("(12(34(67(1))))").toString());
        assertEquals("{( 12 {( 34 {( 67 {( 1 {( 32 )} )} )} )} )}", container.parse("(12(34(67(1(32)))))").toString());
        assertEquals("{( 12 {( 34 {( 11 ), ( 67 {( 1 {( 32 )} )} )} )} )}", container.parse("(12(34(11)(67(1(32)))))").toString());
        assertEquals("{( 12 {( 34 {( 3 ), ( 67 {( 1 ), ( 2 )} )} )} )}", container.parse("(12(34(67(1)(2))(3)))").toString());
        assertEquals("{( 12 {( 34 {( 3 ), ( 67 {( 1 ), ( 2 )} )} )} ), ( 999 )}", container.parse("(12(34(67(1)(2))(3)))(999)").toString());
//        assertEquals("{( ABC {[ 321 ]} )}", container.parse("(ABC[321])").toString());
    }

    @Test
    public void parseWithValidCombinedCircleAndSquareStringInput() throws RuntimeException {
        Container container = new Container();

        assertEquals("{( ABC ), [ 123 ]}", container.parse("(ABC)[123]").toString());
        assertEquals("{( ABC ), [ 123 {[ 34 ]} ]}", container.parse("(ABC)[123[34]]").toString());
        assertEquals("{( ABC {( DE )} ), [ 123 {[ 34 ]} ]}", container.parse("(ABC(DE))[123[34]]").toString());
        assertEquals("{( ABC {[ 321 ]} ), [ 123 {[ 34 ]} ]}", container.parse("(ABC[321])[123[34]]").toString());
        assertEquals("{( ABC {[ 321 {[ 99 ]} ]} ), [ 123 {[ 34 ]} ]}", container.parse("(ABC[321[99]])[123[34]]").toString());
        assertEquals("{( ABC {[ 321 ]} )}", container.parse("(ABC[321])").toString());
        // example from the test
        assertEquals("{[ 12 ], ( BALL {( INK {( CHARLIE ), [ 1 {[ 35 ]} ]} )} )}", container.parse("[12](BALL(INK[1[35]](CHARLIE)))").toString());
        assertThrows(Exception.class, () -> container.parse("[72(HELLO)]"));
        // square must be labeled with numbers
        assertThrows(Exception.class, () -> container.parse("[allow]"));
        // only uppercase letters are allowed
        assertThrows(Exception.class, () -> container.parse("(allow)"));
        assertThrows(Exception.class, () -> container.parse("[13B)"));
        assertThrows(Exception.class, () -> container.parse("([13B)"));
    }
}

package com.xently.technobrain

import org.junit.Assert.assertEquals
import org.junit.Test
import com.xently.technobrain.TechnoBrain.reversePreservingSpecialCharacters

class TechnoBrainTest {

    @Test
    fun reversePreservingSpecialCharacters() {
        assertEquals("p,j\$i", reversePreservingSpecialCharacters("i,j\$p"))
        assertEquals("k,j\$pi", reversePreservingSpecialCharacters("i,j\$pk"))
        assertEquals("aslauqEtress(", reversePreservingSpecialCharacters("assertEquals("))
        assertEquals(
            "a2ser2Eq4asl(u, t + s)",
            reversePreservingSpecialCharacters("assertEquals(4, 2 + 2)")
        )
    }
}
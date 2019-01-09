package com.netcracker.study.general;

import com.netcracker.study.objects.borders.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneralTest {
    @Test
    public void testCreatePassage(){
        BordersField border = new BordersField(5,7);
        border.placePassage(2,4, BordersField.Direction.RIGHT);
        Assertions.assertTrue(border.getBorderReference(2,4, BordersField.Direction.RIGHT).getClass() == Passage.class);
    }
}

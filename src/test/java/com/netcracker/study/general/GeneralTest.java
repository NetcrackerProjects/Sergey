package com.netcracker.study.general;

import com.netcracker.study.objects.borders.Passage;
import com.netcracker.study.general.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneralTest {

    @Test
    public void testCreatePassage(){

        //given
        BordersField border = new BordersField(5,7);

        //when
        border.placePassage(2, 4, Direction.RIGHT);

        //then
        Assertions.assertSame(border.getBorderReference(2, 4, Direction.RIGHT).getClass(), Passage.class);

    }
}

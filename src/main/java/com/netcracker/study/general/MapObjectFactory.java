package com.netcracker.study.general;

import com.netcracker.study.objects.borders.BorderObject;
import com.netcracker.study.objects.borders.Wall;

public class MapObjectFactory {

    protected BorderObject convertSymbolToBorderObject(char symbol) {
        switch (symbol) {
            case ' ':
                return new Wall(0);
            case '|':
                return new Wall(1);
            case  '-':
                return new Wall(1);
        }
        throw new IllegalArgumentException("Found illegal symbol while reading for borders: " + symbol);
    }
}

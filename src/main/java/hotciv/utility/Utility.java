/*
 * Copyright (C) 2018 Henrik BÃ¦rbak Christensen, baerbak.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotciv.utility;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * At 09 May 2018
 *
 * @author Henrik Baerbak Christensen, CS @ AU
 */
public class Utility {
    public static Iterator<Position> get8neighborhoodIterator(Position center) {
        List<Position> list = new ArrayList<>();
        // Define the 'delta' to add to the row for the 8 positions
        int[] rowDelta = new int[] {-1, -1, 0, +1, +1, +1, 0, -1};
        // Define the 'delta' to add to the colum for the 8 positions
        int[] columnDelta = new int[] {0, +1, +1, +1, 0, -1, -1, -1};

        for (int index = 0; index < rowDelta.length; index++) {
            int row = center.getRow() + rowDelta[index];
            int col = center.getColumn() + columnDelta[index];
            if (row >= 0 && col >= 0
                    && row < GameConstants.WORLDSIZE
                    && col < GameConstants.WORLDSIZE)
                list.add(new Position(row, col));
        }
        return list.iterator();
    }

    public static void layoutToTileConvert(GameImpl g, String[] layout) {
        // Conversion...
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                if ( tileChar == 'd' ) { type = GameConstants.DESERT; }
                Position p = new Position(r,c);
                g.createTileAt(p, type);
            }
        }
    }
}
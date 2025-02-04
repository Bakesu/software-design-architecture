package hotciv.framework;

/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class GameConstants {
  // The size of the world is set permanently to a 16x16 grid 
  public static final int WORLDSIZE = 16;
  public static final int WORLD_STARTING_AGE = -4000;
  // Valid unit types
  public static final String ARCHER    = "archer";
  public static final String LEGION    = "legion";
  public static final String SETTLER   = "settler";
  public static final String CARAVAN = "caravan";
  //Unit defense/attack
  public static final int ARCHER_DEFENSE = 3;
  public static final int ARCHER_ATTACK = 2;
  public static final int LEGION_DEFENSE = 2;
  public static final int LEGION_ATTACK = 4;
  public static final int SETTLER_DEFENSE = 3;
  public static final int SETTLER_ATTACK = 0;
  public static final int CARAVAN_DEFENSE = 4;
  public static final int CARAVAN_ATTACK = 1;
  // Unit move counts
  public static final Object ARCHER_MOVES = 1;
  public static final Object LEGION_MOVES = 1;
  public static final Object SETTLER_MOVES = 1;
  public static final Object CARAVAN_MOVES = 2;
  //Unit costs
  public static final int ARCHER_COST = 10;
  public static final int LEGION_COST = 15;
  public static final int SETTLER_COST = 30;
  public static final int CARAVAN_COST = 30;
  // Valid terrain types
  public static final String PLAINS    = "plains";
  public static final String OCEANS    = "ocean";
  public static final String FOREST    = "forest";
  public static final String HILLS     = "hills";
  public static final String MOUNTAINS = "mountain";
  public static final String DESERT    = "desert";
  // Valid production balance types
  public static final String productionFocus = "hammer";
  public static final String foodFocus = "apple";
}

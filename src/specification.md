//SE SIDE 458 I BOGEN

Red is the first player in turn  **OK**
 - Blue is second, then red again. **OK**

Red's city is at (1,1)  **OK**
- Red doesn't have a city elsewhere in the start of the game **OK**

Blue has a city at (4,1) **OK**
- Blue doesn't have a city elsewhere in the start of the game**OK**

All tiles are plains except (1,0), (0,1), (2,2) **OK**

- there is oceans at (1,0)  **OK**

- there is hills at (0,1) **OK**

- there is mountains at (2,2) **OK**

There is a red archer at (2,0) **OK**

There is a blue legion at (3,2) **OK**

There is a red settler at (4,3) **OK**

Units can move **OK**

- units cannot move more or less than 1 **OK**

- units cannot move over ocean **OK**

- units cannot move over mountain  **OK**

- a unit that moves to the same position retains its movecount **OK**

Red cannot move Blue's units  **OK**

- Blue cannot move Red's units **OK**

cities produce 6 'production' after a round has ended  **OK**

cities' population size is always 1  **OK**

players can change workforce balance  **OK**

a player can choose production of a unit **Ok**
 
after Red, it is Blue that is in turn  **OK**

Red wins in year 3000 BC  **OK**

-  The starting year is 4000 BC **OK**

- After all players turns, continue 100 years.   **OK**
 
Red's unit attack and destroy Blue's unit  **OK**  

Units should have one (1) legal move per turn **OK**

a city will produce a unit if enough treasury is stored  **OK**

- Can only produce units in available tiles around red city **OK**

a unit will appear in a non-occupied spot when production is done **OK**

Units can conquer cities **OK**

Units have a attacking and defensive strength. **OK**

Units cannot teamkill **OK**

BETACIV:
Game ages according to BetaCiv **OK**

Player red wins by conquest **OK**

GAMMACIV:
Red settler can build city **OK**

Red archer can fortify and un-fortify **OK**

Wrong player in turn cannot performUnitAction **OK**

DELTACIV:
Map with cities and tiles is generated correctly **OK**

Refactored some strategy tests to use unit tests instead **OK**

THETACIV:
Desert tiles on 0,7 - 7,4 - 11,6 **OK**

Customizable world strategy units should spawn camel :) **OK**

Ordinary units cannot walk on Desert tiles **OK**

Caravan
-cost 30 **OK**
-move 2 **OK**
-def 4 **OK**
-atck 1 **OK**

Caravan can walk on desert tiles **OK**

cities can produce a caravan **OK**

caravan can 'populate' (unit action) **OK**


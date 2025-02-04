package hotciv.common;/*
 * Copyright (C) 2018 Henrik Bærbak Christensen, baerbak.com
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

import hotciv.framework.City;
import hotciv.framework.Unit;

/** A simple 'name service' that allows the server
 * side to map object identities to servant objects.
 *
 * @author Henrik Baerbak Christensen, CS @ AU
 */
public interface NameService {

    /** Put a city into the name service under given id
     *  @param objectId ID of the object
     * @param city the servant object
     */
    void putCity(String objectId, City city);

    /** Get a city.
     *
     * @param objectId the id of the servant object to get.
     * @return the future game with this id.
     */
    City getCity(String objectId);

    /** Put a game into the name service under given id
     *
     * @param objectId ID of the object
     * @param unit the servant object
     */
    void putUnit(String objectId, Unit unit);

    /** Get a game.
     *
     * @param objectId the id of the servant object to get
     * @return the servant object
     */
    Unit getUnit(String objectId);
}

/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.visitor;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.operation.Operation;
import com.sk89q.worldedit.operation.RegionFunction;
import com.sk89q.worldedit.regions.Region;

/**
 * Utility class to apply region functions to {@link com.sk89q.worldedit.regions.Region}.
 */
public class RegionVisitor implements Operation {

    private final Region region;
    private final RegionFunction function;
    private int affected = 0;

    public RegionVisitor(Region region, RegionFunction function) {
        this.region = region;
        this.function = function;
    }

    /**
     * Get the number of affected objects.
     *
     * @return the number of affected
     */
    public int getAffected() {
        return affected;
    }

    @Override
    public Operation resume() throws WorldEditException {
        for (Vector pt : region) {
            if (function.apply(pt)) {
                affected++;
            }
        }

        return null;
    }

    @Override
    public void cancel() {
    }

}

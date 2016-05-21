/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.sponge.registry;

import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.world.registry.State;
import net.minecraft.block.properties.PropertyDirection;
import org.spongepowered.api.block.trait.BlockTrait;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class SpongeBlockState<T extends Comparable<T>> implements State {
    private final BlockTrait<T> blockTrait;

    private final Map<String, SpongeBlockStateValue<T>> values = new HashMap<>();

    public SpongeBlockState(BlockTrait<T> blockTrait) {
        this.blockTrait = blockTrait;

        for (T value : blockTrait.getPossibleValues()) {
            // TODO Convert to Sponge
            values.put(value.toString(), new SpongeBlockStateValue<>(blockTrait, value));
        }
    }

    @Override
    public Map<String, SpongeBlockStateValue<T>> valueMap() {
        return values;
    }

    @Nullable
    @Override
    public SpongeBlockStateValue<T> getValue(BaseBlock block) {
        for (SpongeBlockStateValue<T> value : values.values()) {
            if (value.isSet(block)) {
                return value;
            }
        }

        return null;
    }

    @Override
    public boolean hasDirection() {
        // TODO Convert to Sponge
        return blockTrait instanceof PropertyDirection;
    }
}
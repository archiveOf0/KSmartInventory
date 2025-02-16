/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.kingOf0.smartinventory.smartinventory.holder;

import com.kingOf0.smartinventory.smartinventory.InventoryContents;
import com.kingOf0.smartinventory.smartinventory.Page;
import com.kingOf0.smartinventory.smartinventory.SmartHolder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that implements {@link SmartHolder}.
 */
public final class SmartInventoryHolder implements SmartHolder {

    /**
     * the contents.
     */
    @NotNull
    private final InventoryContents contents;

    @NotNull
    @Override
    public InventoryContents getContents() {
        return contents;
    }

    /**
     * the active.
     */
    private boolean active = true;

    public SmartInventoryHolder(@NotNull InventoryContents contents) {
        this.contents = contents;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return this.contents.getTopInventory();
    }

    @NotNull
    @Override
    public Page getPage() {
        return this.contents.page();
    }

    @NotNull
    @Override
    public Player getPlayer() {
        return this.contents.player();
    }

    @NotNull
    @Override
    public Plugin getPlugin() {
        return this.getPage().inventory().getPlugin();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}

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

package com.kingOf0.smartinventory.smartinventory.event;

import com.kingOf0.smartinventory.smartinventory.InventoryContents;
import com.kingOf0.smartinventory.smartinventory.event.abs.OpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents page open events.
 */
public final class PgOpenEvent implements OpenEvent {

    /**
     * the contents.
     */
    @NotNull
    private final InventoryContents contents;

    /**
     * the event.
     */
    @NotNull
    private final InventoryOpenEvent event;

    /**
     * the plugin.
     */
    @NotNull
    private final Plugin plugin;

    public PgOpenEvent(@NotNull InventoryContents contents, @NotNull InventoryOpenEvent event, @NotNull Plugin plugin) {
        this.contents = contents;
        this.event = event;
        this.plugin = plugin;
    }

    @Override
    public void cancel() {
        this.event.setCancelled(true);
    }

    @Override
    public void close() {
        Bukkit.getScheduler().runTask(this.plugin, () ->
                this.contents.page().close(this.contents.player()));
    }

    @NotNull
    @Override
    public InventoryContents contents() {
        return this.contents;
    }

    @NotNull
    @Override
    public InventoryOpenEvent getEvent() {
        return this.event;
    }
}

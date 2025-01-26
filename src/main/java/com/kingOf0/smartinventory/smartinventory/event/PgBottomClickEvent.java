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
import com.kingOf0.smartinventory.smartinventory.event.abs.BottomClickEvent;
import com.tcoded.folialib.FoliaLib;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents page bottom click events.
 */
public final class PgBottomClickEvent implements BottomClickEvent {

    /**
     * the contents.
     */
    @NotNull
    private final InventoryContents contents;

    /**
     * the event.
     */
    @NotNull
    private final InventoryClickEvent event;

    /**
     * the plugin.
     */
    @NotNull
    private final Plugin plugin;

    /**
     * the folialib.
     */
    @NotNull
    private final FoliaLib foliaLib;


    public PgBottomClickEvent(@NotNull InventoryContents contents, @NotNull InventoryClickEvent event, @NotNull Plugin plugin, @NotNull FoliaLib foliaLib) {
        this.contents = contents;
        this.event = event;
        this.plugin = plugin;
        this.foliaLib = foliaLib;
    }

    @Override
    public void cancel() {
        this.event.setCancelled(true);
    }

    @Override
    public void close() {
        foliaLib.getScheduler().runAtEntity(this.contents.player(), task ->
                this.contents.page().close(this.contents.player()));
    }

    @NotNull
    @Override
    public InventoryContents contents() {
        return this.contents;
    }

    @NotNull
    @Override
    public InventoryClickEvent getEvent() {
        return this.event;
    }
}

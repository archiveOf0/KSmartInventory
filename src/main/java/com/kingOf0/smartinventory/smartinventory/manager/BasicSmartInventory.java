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

package com.kingOf0.smartinventory.smartinventory.manager;

import com.kingOf0.smartinventory.smartinventory.InventoryOpener;
import com.kingOf0.smartinventory.smartinventory.SmartInventory;
import com.kingOf0.smartinventory.smartinventory.util.CancellableRunnable;
import com.tcoded.folialib.FoliaLib;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * an implementation for {@link SmartInventory}.
 */
public final class BasicSmartInventory implements SmartInventory {

    /**
     * the openers.
     */
    private final Collection<InventoryOpener> openers = new ArrayList<>();

    /**
     * the plugin.
     */
    @NotNull
    private final Plugin plugin;

    @NotNull
    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public @NotNull FoliaLib getFoliaLib() {
        return new FoliaLib((JavaPlugin) plugin);
    }

    /**
     * the tasks.
     */
    private final Map<UUID, CancellableRunnable> tasks = new ConcurrentHashMap<>();

    static {
        try {
            Class.forName("com.kingOf0.smartinventory.smartinventory.event.PlgnDisableEvent");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BasicSmartInventory(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @NotNull
    @Override
    public Collection<InventoryOpener> getOpeners() {
        return openers;
    }

    @NotNull
    @Override
    public Map<UUID, CancellableRunnable> getTasks() {
        return tasks;
    }

    @Override
    public @NotNull Optional<CancellableRunnable> getTask(@NotNull UUID uniqueId) {
        return SmartInventory.super.getTask(uniqueId);
    }
}

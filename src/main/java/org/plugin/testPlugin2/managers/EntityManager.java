package org.plugin.testPlugin2.managers;

import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    public static final EntityManager instance = new EntityManager();

    public List<Entity> zombies = new ArrayList<>();
}


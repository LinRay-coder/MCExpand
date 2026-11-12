# MCExpand

A **Fabric** mod for **Minecraft** that expands the game with new equipment tiers and building blocks.

- **Zero Mixin, zero third-party hard dependencies** — only Fabric Loader + Fabric API required, naturally low conflict potential
- **Bilingual** — full `zh_cn` / `en_us` localization
- **Datagen-driven** — all models, recipes, loot tables, tags, and language files are auto-generated

## Installation

1. Install [Fabric Loader](https://fabricmc.net/)  and the matching [Fabric API](https://modrinth.com/mod/fabric-api) 
2. Download `MCExpand-<MC version>-<mod version>.jar` (e.g. `MCExpand-26.3-1.0.0.jar`) from [Releases](../../releases)
3. Drop the jar into `.minecraft/mods/`
4. Launch — no prerequisite mods needed

## Features

### Equipment & Tools (3 sets × 10 pieces = 30 items)
Three new material tiers, each with distinct balance:

| Material | Balance |
|---|---|
| **Lapis Lazuli** | Durability-focused; high enchantability, iron-level stats |
| **Emerald** | ≈ Diamond tier; trades off some durability for easier villager acquisition |
| **Amethyst** | Mid-tier between iron and diamond; crafted with **Amethyst Blocks** for elevated stats |

Each set includes: Helmet, Chestplate, Leggings, Boots, Sword, Spear, Pickaxe, Axe, Shovel, Hoe

### Building Blocks
- **31 custom stone variants**: base / stairs / slab / wall + smooth / cut / chiseled variants
- **3 simplified sets**: End Stone / Purpur / Nether Brick (base + stairs / slab / wall)
- Mining tier: requires stone pickaxe or better

## Building from Source

```bash
cd fabric-mod
./gradlew runDatagen   # generate data first
./gradlew build        # then build (do NOT chain with runDatagen)
```

Output jar is at `fabric-mod/build/libs/` (e.g. `MCExpand-26.3-1.0.0.jar`).

## License

GPL v3 — see [LICENSE](LICENSE).
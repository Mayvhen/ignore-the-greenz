# See Through Leaves

Client-side Fabric mod for Minecraft 26.3 (Fabric Loader 0.19.5, Java 25).

Your crosshair ignores leaves – and now also grass, ferns, bushes, flowers and similar decoration – so you can punch the log hidden behind the foliage. Mobs behind them become targetable too. Only the client's own raycast is changed; nothing is needed on the server.

## Build

1. Install JDK 25.
2. In this folder run `./gradlew build` (`gradlew.bat build` on Windows).
3. Take `../output/see-through-leaves-1.1.0.jar` (not the `-sources` one) and put it in your `mods` folder together with Fabric API 0.163.0+26.3.

To test directly: `./gradlew runClient`.

## Layout

The repository (`ignore-leaf`) only holds two folders:

```
ignore-leaf/
├── source_code/   this Gradle project (build files, gradlew, src/main, src/client)
└── output/        everything Gradle produces, created on first build
```

- `src/main` – common mod resources (`fabric.mod.json`).
- `src/client` – client entrypoint, mixins and mixin config.
- `../output` – compiled classes and jars; the finished mod jar sits directly in `output/`, the sources jar stays in `output/libs/`. It is generated build output and is never committed (this clone ignores it through `.git/info/exclude`, because the repository root deliberately has no `.gitignore`).

## How it works

`ClipContextMixin` hooks `ClipContext.getBlockShape`. When the raycast uses the OUTLINE shape (the one used for the crosshair) and `CrosshairFilter` says the block is decorative foliage in the client world, it returns an empty shape, so the ray passes through.

## What gets ignored

`CrosshairFilter` (`src/client/java/dev/seethroughleaves/client/CrosshairFilter.java`) decides. Mostly it leans on vanilla tags, so it follows datapacks and future blocks automatically:

- `#minecraft:leaves` – every kind of leaf.
- `#minecraft:replaceable_by_trees` – short/tall grass, ferns, dry grass, bushes, flowers, vines, glow lichen, hanging roots, leaf litter, pale moss carpet, nether sprouts and roots, seagrass, shelf mushrooms.
- `#minecraft:small_flowers` – dandelion, poppy, tulips and the rest.
- Plus a short hand-written list: red shrub, pink petals, wildflowers, moss carpet, twisting and weeping vines.

Left targetable on purpose, because they are useful: crops and their stems, saplings and propagules, mushrooms, sweet berries, sugar cane, bamboo, dripleaves, cave vines (glow berries), spore blossoms, cactus, azaleas and lily pads. Add or remove entries in the `DECORATIVE` set (or the tag checks) in that one file to change the behaviour.

# See Through Leaves

Client-side Fabric mod for Minecraft 26.3 (Fabric Loader 0.19.5, Java 25).

Your crosshair ignores every block in the `#minecraft:leaves` tag, so you can punch the log hidden behind the foliage. Mobs behind leaves become targetable too. Only the client's own raycast is changed; nothing is needed on the server.

## Build

1. Install JDK 25.
2. In this folder run `./gradlew build` (`gradlew.bat build` on Windows).
3. Take `build/libs/see-through-leaves-1.0.0.jar` (not the `-sources` one) and put it in your `mods` folder together with Fabric API 0.163.0+26.3.

To test directly: `./gradlew runClient`.

## How it works

`ClipContextMixin` hooks `ClipContext.getBlockShape`. When the raycast uses the OUTLINE shape (the one used for the crosshair) and the block is leaves in the client world, it returns an empty shape, so the ray passes through.

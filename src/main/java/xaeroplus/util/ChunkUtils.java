package xaeroplus.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import xaeroplus.Globals;

import static net.minecraft.world.World.NETHER;
import static net.minecraft.world.World.OVERWORLD;

public class ChunkUtils {

    /**
     * Caching helpers
     **/
    public static long chunkPosToLong(final ChunkPos chunkPos) {
        return chunkPos.toLong();
    }

    public static long chunkPosToLong(final int x, final int z) {
        return ChunkPos.toLong(x, z);
    }

    public static ChunkPos longToChunkPos(final long l) {
        return new ChunkPos(l);
    }

    public static int posToChunkPos(final int i) {
        return i >> 4;
    }

    public static int longToChunkX(final long l) {
        return ChunkPos.getPackedX(l);
    }

    public static int longToChunkZ(final long l) {
        return ChunkPos.getPackedZ(l);
    }
    /** Player position util functions **/

    public static double getPlayerX() {
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            RegistryKey<World> dim = mc.world.getRegistryKey();
            // when player is in the nether or the custom dimension is the nether, perform coordinate translation
            if ((dim == NETHER || Globals.getCurrentDimensionId() == NETHER) && dim != Globals.getCurrentDimensionId()) {
                if (Globals.getCurrentDimensionId() == OVERWORLD) {
                    return mc.player.getX() * 8.0;
                } else if (Globals.getCurrentDimensionId() == NETHER && dim == OVERWORLD) {
                    return mc.player.getX() / 8.0;
                }
            }
            return mc.player.getX();
        } catch (final Exception e) {
            return 0;
        }
    }
    public static double getPlayerZ() {
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            RegistryKey<World> dim = mc.world.getRegistryKey();
            // when player is in the nether or the custom dimension is the nether, perform coordinate translation
            if ((dim == NETHER || Globals.getCurrentDimensionId() == NETHER) && dim != Globals.getCurrentDimensionId()) {
                if (Globals.getCurrentDimensionId() == OVERWORLD) {
                    return mc.player.getZ() * 8.0;
                } else if (Globals.getCurrentDimensionId() == NETHER && dim == OVERWORLD) {
                    return mc.player.getZ() / 8.0;
                }
            }
            return mc.player.getZ();
        } catch (final Exception e) {
            return 0;
        }
    }
    public static int actualPlayerChunkX() {
        try {
            return MinecraftClient.getInstance().player.getChunkPos().x;
        } catch (final NullPointerException e) {
            return 0;
        }
    }
    public static int getPlayerChunkX() {
        return coordToChunkCoord(getPlayerX());
    }
    public static int actualPlayerChunkZ() {
        try {
            return MinecraftClient.getInstance().player.getChunkPos().z;
        } catch (final NullPointerException e) {
            return 0;
        }
    }
    public static int getPlayerChunkZ() {
        return coordToChunkCoord(getPlayerZ());
    }
    public static int actualPlayerRegionX() {
        return actualPlayerChunkX() >> 5;
    }
    public static int getPlayerRegionX() {
        return getPlayerChunkX() >> 5;
    }
    public static int actualPlayerRegionZ() {
        return actualPlayerChunkZ() >> 5;
    }
    public static int getPlayerRegionZ() {
        return getPlayerChunkZ() >> 5;
    }
    public static RegistryKey<World> getActualDimension() {
        try {
            return MinecraftClient.getInstance().world.getRegistryKey();
        } catch (final Exception e) {
            return OVERWORLD;
        }
    }

    /** MC Coordinate conversion functions **/

    public static int coordToChunkCoord(final double coord) {
        return ((int)coord) >> 4;
    }
    public static int coordToRegionCoord(final double coord) {
        return ((int)coord) >> 9;
    }
    public static int chunkCoordToCoord(final int chunkCoord) {
        return chunkCoord << 4;
    }
    public static int chunkCoordToRegionCoord(final int chunkCoord) {
        return chunkCoord >> 5;
    }
    public static int regionCoordToChunkCoord(final int regionCoord) {
        return regionCoord << 5;
    }
    public static int regionCoordToCoord(final int regionCoord) {
        return regionCoord << 9;
    }

    /**
     * MCRegion Format:
     *   Region = 32x32 Chunks
     *   Chunk = 16x16 Blocks
     *
     * XaeroRegion Format:
     *   MapRegion = 8x8 MapTileChunks
     *   MapTileChunk = 4x4 Tiles
     *   Tile = 16x16 Blocks
     */

    public static int coordToMapRegionCoord(final int coord) {
        return coord >> 9;
    }
    public static int mapRegionCoordToCoord(final int mapRegionCoord) {
        return mapRegionCoord << 9;
    }
    public static int mapTileChunkCoordToMapRegionCoord(final int mapTileChunkCoord) {
        return mapTileChunkCoord >> 3;
    }
    public static int mapRegionCoordToMapTileChunkCoord(final int mapRegionCoord) {
        return mapRegionCoord << 3;
    }
    public static int mapTileCoordToMapTileChunkCoord(final int mapTileCoord) {
        return mapTileCoord >> 2;
    }
    public static int mapTileChunkCoordToMapTileCoord(final int mapTileChunkCoord) {
        return mapTileChunkCoord << 2;
    }
    public static int mapTileCoordToCoord(final int mapTileCoord) {
        return mapTileCoord << 4;
    }
    public static int coordToMapTileCoord(final int coord) {
        return coord >> 4;
    }
    public static int mapTileCoordToMapRegionCoord(final int mapTileCoord) {
        return mapTileCoord >> 6;
    }
    public static int mapRegionCoordToMapTileCoord(final int mapRegionCoord) {
        return mapRegionCoord << 6;
    }
    public static int mapTileChunkCoordToCoord(final int mapTileChunkCoord) {
        return mapTileChunkCoord << 6;
    }
    public static int coordToMapTileChunkCoord(final int coord) {
        return coord >> 6;
    }
    public static int chunkCoordToMapRegionCoord(final int chunkCoord) {
        return chunkCoord >> 5;
    }
    public static int mapRegionCoordToChunkCoord(final int mapRegionCoord) {
        return mapRegionCoord << 5;
    }
    public static int chunkCoordToMapTileChunkCoord(final int chunkCoord) {
        return chunkCoord >> 2;
    }
    public static int mapTileChunkCoordToChunkCoord(final int mapTileChunkCoord) {
        return mapTileChunkCoord << 2;
    }
    public static int chunkCoordToMapTileCoord(final int chunkCoord) {
        return chunkCoord;
    }
    public static int mapTileCoordToChunkCoord(final int mapTileCoord) {
        return mapTileCoord;
    }
    public static int regionCoordToMapRegionCoord(final int regionCoord) {
        return regionCoord;
    }
    public static int mapRegionCoordToRegionCoord(final int mapRegionCoord) {
        return mapRegionCoord;
    }
    public static int regionCoordToMapTileChunkCoord(final int regionCoord) {
        return regionCoord << 3;
    }
    public static int mapTileChunkCoordToRegionCoord(final int mapTileChunkCoord) {
        return mapTileChunkCoord >> 3;
    }
    public static int regionCoordToMapTileCoord(final int regionCoord) {
        return regionCoord << 6;
    }
    public static int mapTileCoordToRegionCoord(final int mapTileCoord) {
        return mapTileCoord >> 6;
    }
    public static int chunkCoordToMapTileChunkCoordLocal(final int chunkCoord) {
        return chunkCoordToMapTileChunkCoord(chunkCoord) & 7;
    }
    public static int chunkCoordToMapTileCoordLocal(final int chunkCoord) {
        return chunkCoordToMapTileCoord(chunkCoord) & 3;
    }
}

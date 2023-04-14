package pe.bazan.luis.plugins.thebridgepe.instances;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class TheBridgeGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunkData = this.createChunkData(world);
        this.setBiomeGrid(biome, chunkData);

        super.generateBedrock(null, random, chunkX, chunkZ, chunkData);
        return chunkData;
    }

    public void setBiomeGrid(BiomeGrid biome, ChunkData paramChunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < paramChunkData.getMaxHeight(); y++) {
                    biome.setBiome(x, y, z, Biome.DESERT);
                }
            }
        }
    }
}

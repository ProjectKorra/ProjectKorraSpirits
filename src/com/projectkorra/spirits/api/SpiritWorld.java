package com.projectkorra.spirits.api;

import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;

import net.minecraft.server.v1_12_R1.IChunkProvider;
import net.minecraft.server.v1_12_R1.IDataManager;
import net.minecraft.server.v1_12_R1.MethodProfiler;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.WorldData;
import net.minecraft.server.v1_12_R1.WorldProvider;

public class SpiritWorld extends World {

	protected SpiritWorld(IDataManager idatamanager, WorldData worlddata, WorldProvider worldprovider, MethodProfiler methodprofiler, boolean flag, ChunkGenerator gen, Environment env) {
		super(idatamanager, worlddata, worldprovider, methodprofiler, flag, gen, env);
	}

	@Override
	protected IChunkProvider n() {
		return null;
	}

	@Override
	protected boolean isChunkLoaded(int arg0, int arg1, boolean arg2) {
		return false;
	}

}

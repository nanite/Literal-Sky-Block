package dev.latvian.mods.literalskyblock.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.latvian.mods.literalskyblock.SkyBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import org.joml.Matrix4f;

public class SkyBlockEntityRenderer implements BlockEntityRenderer<SkyBlockEntity> {
	public SkyBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(SkyBlockEntity entity, float delta, PoseStack poseStack, MultiBufferSource source, int light1, int light2) {
		renderCube(entity, poseStack.last().pose(), source.getBuffer(entity instanceof SkyBlockEntity e ? (RenderType) e.projection.renderType : RenderType.endGateway()));
	}

	private void renderCube(SkyBlockEntity entity, Matrix4f matrix, VertexConsumer buffer) {
		renderFace(entity, matrix, buffer, 0F, 1F, 0F, 1F, 1F, 1F, 1F, 1F, Direction.SOUTH);
		renderFace(entity, matrix, buffer, 0F, 1F, 1F, 0F, 0F, 0F, 0F, 0F, Direction.NORTH);
		renderFace(entity, matrix, buffer, 1F, 1F, 1F, 0F, 0F, 1F, 1F, 0F, Direction.EAST);
		renderFace(entity, matrix, buffer, 0F, 0F, 0F, 1F, 0F, 1F, 1F, 0F, Direction.WEST);
		renderFace(entity, matrix, buffer, 0F, 1F, 0F, 0F, 0F, 0F, 1F, 1F, Direction.DOWN);
		renderFace(entity, matrix, buffer, 0F, 1F, 1F, 1F, 1F, 1F, 0F, 0F, Direction.UP);
	}

	private void renderFace(SkyBlockEntity entity, Matrix4f matrix, VertexConsumer buffer, float f, float g, float h, float i, float j, float k, float l, float m, Direction direction) {
		if (entity.shouldRenderFace(direction)) {
			buffer.addVertex(matrix, f, h, j);
			buffer.addVertex(matrix, g, h, k);
			buffer.addVertex(matrix, g, i, l);
			buffer.addVertex(matrix, f, i, m);
		}
	}

	@Override
	public int getViewDistance() {
		return 256;
	}
}

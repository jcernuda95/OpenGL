package renderEngine;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import shaders.StaticShader;
import shaders.TerrainShader;
import terrains.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000.0f;

    private Matrix4f projectionMatrix;

    private StaticShader shader = new StaticShader();
    private EntityRenderer render;

    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
    private List<Terrain> terrains = new ArrayList<>();


    public MasterRenderer() {
        GL11.glEnable((GL11.GL_CULL_FACE));
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        render = new EntityRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    public void render(Light sun, Camera camera){
        this.prepare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        render.render(entities);
        shader.stop();

        entities.clear();

        terrainShader.start();;
        terrainShader.loadLight(sun);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();

        terrains.clear();
    }

    public void processTerrain(Terrain terrain){
        terrains.add(terrain);
    }

    public void processEntity(Entity entity){
        TexturedModel model = entity.getModel();
        List<Entity> batch = entities.get(model);
        if(batch != null){
            batch.add(entity);
        }
        else{
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(model, newBatch);
        }
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glClearColor(0, 0f, 1, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

    private void createProjectionMatrix(){
        projectionMatrix = new Matrix4f().perspective((float) Math.toRadians(FOV), DisplayManger.getWindowWidth()/(float)DisplayManger.getWindowHeight(), NEAR_PLANE, FAR_PLANE);
    }

    public void cleanUp(){

        shader.cleanUp();
        terrainShader.cleanUp();
    }

}

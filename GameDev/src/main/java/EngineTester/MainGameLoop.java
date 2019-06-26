package EngineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import instance.Voxel;
import models.TexturedModel;
import org.joml.Vector3f;
import renderEngine.*;
import shaders.StaticShader;
import org.lwjgl.opengl.GL;
import models.RawModel;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;

public class MainGameLoop {

    private static long window;

    public static void main(String[] args) {

        window = DisplayManger.createDisplay();
        GL.createCapabilities();

        Loader loader = new Loader();

        Camera camera = new Camera();
        Light light = new Light(new Vector3f(3,5,-1), new Vector3f(3,3,3));
        MasterRenderer renderer = new MasterRenderer();

//        RawModel treeModel = OBJLoader.loadObjModel("tree", loader);
//        ModelTexture treeTexture = new ModelTexture(loader.loadTexture("tree"), 15, 0f);
//        TexturedModel treeTexturedModel = new TexturedModel(treeModel, treeTexture);
//
//        RawModel dragonModel = OBJLoader.loadObjModel("dragon", loader);
//        ModelTexture dragonTexture = new ModelTexture(loader.loadTexture("gold"), 15, 0f);
//        TexturedModel dragonTexturedModel = new TexturedModel(dragonModel, dragonTexture);
//
//        Entity dragon = new Entity(dragonTexturedModel, new Vector3f(0, 0, -20), 0,0,0,1);
//
//        Terrain terrain = new Terrain(0,0, new ModelTexture(loader.loadTexture("grass"), 1.0f, 0.0f), loader, 800);
//        Terrain terrain2 = new Terrain(1,0, new ModelTexture(loader.loadTexture("grass"), 1.0f, 0.0f), loader, 800);
//        ;
//        List<Entity> trees = new ArrayList<Entity>();
//        int num_trees = 20000;
//        Random random = new Random();
//
//        for(int i = 0; i < num_trees; i++){
//            float x = random.nextFloat() * 800;
//            float y = 0;
//            float z = random.nextFloat() * 800;
//
//            trees.add(new Entity(treeTexturedModel, new Vector3f(x, y, z), 0,random.nextFloat() * 180,0,1));
//        }

        List<Voxel> voxelTerrain = new ArrayList<Voxel>();
        int size = 10;
        for(int i = 0; i < size; i++){
            for(int j=0; j < size; j++){
                voxelTerrain.add(new Voxel(loader, "texture_grass", new Vector3f(j*1.1f, 0, i*0.51f), 1));
            }
            i++;
        }

        while ( !glfwWindowShouldClose(window) ) {
            camera.move();

//            renderer.processEntity(dragon);
//            dragon.increaseRotation(0, 0.1f, 0);
//
//            for(Entity tree: trees){
//                renderer.processEntity(tree);
//            }
//            renderer.processTerrain(terrain);
//            renderer.processTerrain(terrain2);

            for(Voxel voxel: voxelTerrain){
                renderer.processEntity(voxel.entity);
            }

            renderer.render(light, camera);

            glfwSwapBuffers(window); // swap the color buffers
            glfwPollEvents(); // Poll for window events.
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManger.closeDisplay();
    }
}

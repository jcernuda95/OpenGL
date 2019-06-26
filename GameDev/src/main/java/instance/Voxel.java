package instance;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.joml.Vector3f;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class Voxel{

    public Entity entity;

    public Voxel(Loader loader, String textureName, Vector3f position, float scale) {
        RawModel model = OBJLoader.loadObjModel("cube", loader);
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(textureName), 15, 0f);
        TexturedModel texturedModel = new TexturedModel(model, modelTexture);

        entity = new Entity(texturedModel, position, 0, 0, 0, scale);
    }
}

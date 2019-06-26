package shaders;

import entities.Camera;
import entities.Light;
import org.joml.Matrix4f;
import toolbox.Maths;

public class TerrainShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/main/java/shaders/terrainVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/terrainFragmentShader.glsl";

    private int location_transformMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPos;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformMatrix = super.getUniformLocation("transformMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPos = super.getUniformLocation("lightPos");
        location_lightColour = super.getUniformLocation("lightColour");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
        super.bindAttribute(2, "normals");
    }

    public void loadTransformMatrix(Matrix4f transformMatrix){
        super.loadMatrix(location_transformMatrix, transformMatrix);
    }

    public void loadProjectionMatrix(Matrix4f projectionMatrix){
        super.loadMatrix(location_projectionMatrix, projectionMatrix);
    }

    public void loadViewMatrix(Camera camera){
        super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
    }

    public void loadLight(Light light){
        super.loadVector(location_lightPos, light.getPosition());
        super.loadVector(location_lightColour, light.getColour());
    }

    public void loadShineVariables(float damper, float reflectivity){
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }
}

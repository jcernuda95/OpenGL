package entities;

import org.joml.Vector3f;
import renderEngine.DisplayManger;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private Vector3f position;
    private float pitch;
    private float yaw;
    private float roll;

    public float speed = 0.02f;
    public float rotationSpeed = 0.05f;


    public Camera() {
        position = new Vector3f(0,5,0);
        yaw = 180;
        pitch = 30;
    }

    public void move(){
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_W) == GLFW_PRESS){
            position.z -= speed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_D) == GLFW_PRESS){
            position.x += speed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_A) == GLFW_PRESS){
            position.x -= speed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_S) == GLFW_PRESS){
            position.z += speed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_Q) == GLFW_PRESS){
            position.y += speed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_E) == GLFW_PRESS){
            position.y -= speed;
        }

        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_R) == GLFW_PRESS){
            yaw -= rotationSpeed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_T) == GLFW_PRESS){
            yaw += rotationSpeed;
        }

        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_F) == GLFW_PRESS){
            pitch -= rotationSpeed;
        }
        if(glfwGetKey(DisplayManger.getWindow(), GLFW_KEY_G) == GLFW_PRESS){
            pitch += rotationSpeed;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}

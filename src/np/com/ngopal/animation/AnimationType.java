package np.com.ngopal.animation;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Narayan G. Maharjan<me@ngopal.com.np>
 * Created on Sep 6, 2013, 7:42:12 PM
 */
public enum AnimationType {
    FADE_OUT,
    FLAP_RIGHT,
    FLATTERN_OUT,
    FLY_FROM_DOWN,
    FLY_FROM_UP,
    ROTATE_RIGHT,
    SPEED_LEFT,
    SPEED_RIGHT,
    TRANSITION_DOWN,
    TRANSITION_LEFT,
    TRANSITION_RIGHT,
    TRANSITION_TOP,
    ZOOM_IN,
    POP_OUT;

    public String getName() {
        return toString();
    }
}

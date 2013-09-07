/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.control.cell;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;
import np.com.ngopal.animation.AnimationPack;
import np.com.ngopal.animation.AnimationType;

/**
 *
 * @author Narayan G. Maharjan <me@ngopal.com.np>
 * Created on Sep 6, 2013, 9:45:01 PM
 */
public abstract class AbstractAnimatedListCell<T> extends ListCell<T> {
    /**
     * For persisting oldIndex
     */
    protected static int oldIndex;

    protected AnimationType[] types;

    /**
     * Instance of AnimationPack
     */
    protected AnimationPack anim;

    public abstract ObjectProperty<StringConverter<T>> converterProperty();

    public abstract StringConverter<T> getConverter();

    protected abstract KeyFrame[] getKeyFrames(AnimationType[] types);

    private void animate() {
        if (anim != null && anim.getKeyFrames().size() >= 0
                && (anim.getTimeline().getStatus() == Timeline.Status.STOPPED
                || anim.getTimeline().getStatus() == Timeline.Status.PAUSED)) {
            anim.getTimeline().playFromStart();
//            if (oldIndex < getIndex() && Math.abs(oldIndex - getIndex()) == 1) {
//                anim.getTimeline().playFromStart();
//            }
        }
    }

    @Override
    protected void updateItem(T t, boolean bln) {
        //overriding the super interface
        super.updateItem(t, bln);
        if (t == null) {
            return;
        }
        if (converterProperty() != null && getConverter() != null) {
            setText(getConverter().toString(t));
        } else {
            setText(t.toString());
        }
        //Adding Animation to the ListCell
        anim = new AnimationPack(this);
        if (anim.getKeyFrames().size() == 0) {
            KeyFrame[] f = getKeyFrames(types);

            if (f != null) {
                anim.getKeyFrames().addAll(f);
            }
        }
        //Checking when to play Animation
        animate();
//        oldIndex = getIndex();
    }
}

package np.com.ngopal.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.util.Duration;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Narayan G. Maharjan<me@ngopal.com.np>
 * Created on Sep 6, 2013, 10:11:08 PM
 */
public class AnimationPack {
    public Node node;

    final Timeline ti = new Timeline();

    final Timeline rti = new Timeline();

    public int animDuration = 600;

    public Shear sh;

    public Rotate rotate;

    public AnimationPack(Node n) {
        node = n;
    }

    public ListView getListView() {
        if (node instanceof ListCell) {
            return ((ListCell)node).getListView();
        }
        return null;
    }

    public Cell getCell() {
        try {
            return (Cell)node;
        } catch (ClassCastException cce) {
            System.out.println(cce);
            return null;
        }


    }

    public KeyFrame[] getFlatternOut(KeyFrame... k) {
        return new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(node.scaleXProperty(), 0)),
            new KeyFrame(Duration.millis(0), new KeyValue(node.scaleYProperty(), 0.9)),
            new KeyFrame(Duration.millis(animDuration * 0.4), new KeyValue(node.scaleXProperty(), 0.001)),
            new KeyFrame(Duration.millis(animDuration * 0.6),
            new KeyValue(node.scaleXProperty(), 1.2, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.scaleYProperty(), 1)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.scaleXProperty(), 1, Interpolator.EASE_BOTH))};

    }

    public KeyFrame[] getPopOut(KeyFrame... k) {
        if (rotate == null) {
            rotate = new Rotate();
            rotate.setAxis(Rotate.Y_AXIS);
            rotate.pivotYProperty().bind(getCell().heightProperty().divide(2));
            node.getTransforms().add(rotate);
        }
        return new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(node.scaleXProperty(), 0.4, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(0), new KeyValue(node.scaleYProperty(), 0.4, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration * 0.8),
            new KeyValue(node.scaleXProperty(), 1.3, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration * 0.8),
            new KeyValue(node.scaleYProperty(), 1.3, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.scaleXProperty(), 1, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.scaleYProperty(), 1, Interpolator.EASE_BOTH)),};
//            new KeyFrame(Duration.millis(0), new KeyValue(rotate.angleProperty(), -90, Interpolator.EASE_BOTH)),
        //            new KeyFrame(Duration.millis(0), new KeyValue(rotate.pivotZProperty(), -90, Interpolator.EASE_BOTH)),
//            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.angleProperty(), 0, Interpolator.EASE_IN)),};

    }

    public KeyFrame[] getFlapRight(KeyFrame... k) {
        rotate = new Rotate();
        rotate.setAxis(Rotate.Y_AXIS);
//        rotate.pivotXProperty().bind(0);
        rotate.pivotYProperty().bind(getCell().heightProperty().divide(2));
        if (!node.getTransforms().contains(rotate)) {
            node.getTransforms().add(rotate);
        }
        return getZoomIn(0.9d, concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(rotate.angleProperty(), -90, Interpolator.EASE_BOTH)),
            //            new KeyFrame(Duration.millis(0), new KeyValue(rotate.pivotZProperty(), -90, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.angleProperty(), 0, Interpolator.EASE_IN)),}));
    }

    public KeyFrame[] getFlyFromUp(KeyFrame... k) {
        rotate = new Rotate();
        rotate.setAxis(Rotate.X_AXIS);
        rotate.pivotXProperty().bind(getCell().widthProperty().divide(2));
        rotate.pivotYProperty().bind(getCell().heightProperty().divide(2));
        if (!node.getTransforms().contains(rotate)) {
            node.getTransforms().add(rotate);
        }

        return getZoomIn(0.7d, concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(rotate.angleProperty(), 90, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(0), new KeyValue(node.translateYProperty(),
            -getCell().prefHeightProperty().get(),
            Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(0), new KeyValue(rotate.pivotZProperty(), 90, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.angleProperty(), 0, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.translateYProperty(), 0,
            Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.pivotZProperty(), 0, Interpolator.EASE_IN)),}));
    }

    public KeyFrame[] getFlyFromDown(KeyFrame... k) {
        rotate = new Rotate();
        rotate.setAxis(Rotate.X_AXIS);
//        rotate.setPivotZ(-50);
        rotate.pivotXProperty().bind(getCell().widthProperty().divide(2));
        rotate.pivotYProperty().bind(getCell().heightProperty().divide(2));
        if (!node.getTransforms().contains(rotate)) {
            node.getTransforms().add(rotate);
        }
        return getZoomIn(0.7d, concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(rotate.angleProperty(), -90, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(0), new KeyValue(rotate.pivotZProperty(), -90, Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.angleProperty(), 0, Interpolator.EASE_IN)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.pivotZProperty(), 0, Interpolator.EASE_IN)),}));
    }

    public KeyFrame[] getRotateYRight(KeyFrame... k) {
        rotate = new Rotate();
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.pivotXProperty().bind(getCell().widthProperty().divide(2));
        rotate.pivotYProperty().bind(getCell().heightProperty().divide(2));
        if (!node.getTransforms().contains(rotate)) {
            node.getTransforms().add(rotate);
        }
        return getZoomIn(0.7d, concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(rotate.angleProperty(), -180)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(rotate.angleProperty(), 0)),}));
    }

    public KeyFrame[] getSpeedLeft(KeyFrame... k) {

        sh = new Shear();
        sh.setPivotX(100);
        sh.setPivotY(35);
        sh.setY(0);

        if (!node.getTransforms().contains(sh)) {
            node.getTransforms().add(sh);
        }
        return concat(k, getTransitionLeft(
                new KeyFrame(Duration.millis(0), new KeyValue(sh.xProperty(), -0.5, Interpolator.EASE_IN)),
                new KeyFrame(Duration.millis(animDuration), new KeyValue(sh.xProperty(), 0, Interpolator.EASE_OUT))));
    }

    public KeyFrame[] getSpeedRight(KeyFrame... k) {



        if (sh == null) {
            sh = new Shear();
            sh.setPivotX(100);
            sh.setPivotY(35);
            sh.setY(0);
            node.getTransforms().add(sh);
        }

        return concat(k, getTransitionRight(
                new KeyFrame(Duration.millis(0), new KeyValue(sh.xProperty(), 0.5)),
                new KeyFrame(Duration.millis(animDuration), new KeyValue(sh.xProperty(), 0))));
    }

    public KeyFrame[] getTransitionTop(KeyFrame... k) {
        return concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0),
            new KeyValue(node.translateYProperty(), Math.max(getCell().getHeight(), 50))),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.translateYProperty(), 0)),});
    }

    public KeyFrame[] getTransitionDown(KeyFrame... k) {
        return new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(node.translateYProperty(), Math.min(-getCell().getHeight(),
            -50))),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.translateYProperty(), 0)),};
    }

    public KeyFrame[] getTransitionLeft(KeyFrame... k) {
        return concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0),
            new KeyValue(node.translateXProperty(), Math.max(getCell().getWidth(), 200))),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.translateXProperty(), 0)),});
    }

    public KeyFrame[] getTransitionRight(KeyFrame... k) {
        return concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(node.translateXProperty(), Math.min(-getCell().getWidth(),
            -200))),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.translateXProperty(), 0)),});
    }

    public KeyFrame[] getZoomIn(double from, KeyFrame... k) {
        return concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(node.scaleXProperty(), from)),
            new KeyFrame(Duration.millis(0), new KeyValue(node.scaleYProperty(), from)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.scaleXProperty(), 1)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.scaleYProperty(), 1)),});
    }

    public KeyFrame[] getFadeOut(KeyFrame... k) {
        return concat(k, new KeyFrame[]{
            new KeyFrame(Duration.millis(0), new KeyValue(node.opacityProperty(), 0)),
            new KeyFrame(Duration.millis(animDuration), new KeyValue(node.opacityProperty(), 1))
        });
    }

    public static <T> T[] concat(T[] first, T[] second) {
        if (first == null && second != null) {
            return second;
        } else if (second == null && first != null) {
            return first;
        }
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public ObservableList<KeyFrame> getKeyFrames() {
        return ti.getKeyFrames();
    }

    public Timeline getTimeline() {
        return ti;
    }

    public Timeline getReversedTimeline() {
        Duration start = null;
        Duration end = null;
        for (KeyFrame f : ti.getKeyFrames()) {
            Duration dur = f.getTime();
            if (start == null && end == null) {
                start = end = dur;
            }
            if (dur.greaterThan(end)) {
                end = dur;
            }
            if (dur.lessThan(start)) {
                start = dur;
            }
        }

        for (KeyFrame f : ti.getKeyFrames()) {
            Duration dur = f.getTime();
            rti.getKeyFrames().add(new KeyFrame(
                    Duration.millis((1 - (dur.toMillis() / end.toMillis())) * end.toMillis()), f.getValues().toArray(
                    new KeyValue[f.getValues().size()])));
        }

        return rti;
    }
}

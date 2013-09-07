package np.com.ngopal.control.cell;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.animation.KeyFrame;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import np.com.ngopal.animation.AnimationType;

/**
 *
 * @author Narayan G. Maharjan <me@ngopal.com.np>
 * Created on Sep 6, 2013, 9:45:01 PM
 */
public class AnimatedListCell<T> extends AbstractAnimatedListCell<T> {
    private ObjectProperty<StringConverter<T>> converter;

    public AnimatedListCell(StringConverter converter, AnimationType... types) {
        this.types = types;
        this.converter = new SimpleObjectProperty(this, "converter");
        this.getStyleClass().add("anim-list-cell");
        this.setConverter(converter);
    }

    public AnimatedListCell(AnimationType... types) {
        this(new DefaultStringConverter(), types);
    }

    @Override
    public ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    @Override
    public final StringConverter<T> getConverter() {
        return converter.get();
    }

    private void setConverter(
            StringConverter<T> v) {
        converterProperty().set(v);
    }

    /**
     * Get cellfactory of AbstractAnimatedListCell for ListView
     *
     * @param type
     * @return
     */
    public static Callback<ListView<String>, ListCell<String>> forListView(final AnimationType... type) {
        return new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(
                    ListView<String> p) {
                return new AnimatedListCell<>(new DefaultStringConverter(), type);
            }
        };
    }

    /**
     * Get cellfactory of AbstractAnimatedListCell for ListView with StringConverter
     *
     * @param <T>
     * @param sc
     * @param type
     * @return
     */
    public static <T extends Object> Callback<ListView<T>, ListCell<T>> forListView(
            final StringConverter<T> sc, final AnimationType... type) {
        return new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(
                    ListView<T> p) {
                return new AnimatedListCell<>(sc, type);
            }
        };


    }

    /**
     * For getting the KeyFrames of specific AnimationType
     *
     * @param types
     * @return
     */
    @Override
    protected KeyFrame[] getKeyFrames(AnimationType[] types) {
        if (types == null) {
            return null;
        }
        KeyFrame[] frames = null;
        for (AnimationType type : types) {
            switch (type) {
                case FADE_OUT:
                    frames = anim.getFadeOut(frames);
                    break;
                case FLAP_RIGHT:
                    frames = anim.getFlapRight(frames);
                    break;
                case FLATTERN_OUT:
                    frames = anim.getFlatternOut(frames);
                    break;
                case FLY_FROM_DOWN:
                    frames = anim.getFlyFromDown(frames);
                    break;
                case FLY_FROM_UP:
                    frames = anim.getFlyFromUp(frames);
                    break;
                case ROTATE_RIGHT:
                    frames = anim.getRotateYRight(frames);
                    break;
                case SPEED_LEFT:
                    frames = anim.getSpeedLeft(frames);
                    break;
                case SPEED_RIGHT:
                    frames = anim.getSpeedRight(frames);
                    break;
                case TRANSITION_DOWN:
                    frames = anim.getTransitionDown(frames);
                    break;
                case TRANSITION_LEFT:
                    frames = anim.getTransitionLeft(frames);
                    break;
                case TRANSITION_RIGHT:
                    frames = anim.getTransitionRight(frames);
                    break;
                case TRANSITION_TOP:
                    frames = anim.getTransitionTop(frames);
                    break;
                case ZOOM_IN:
                    frames = anim.getZoomIn(0, frames);
                    break;
                case POP_OUT:
                    frames = anim.getPopOut(frames);
                    break;

            }
        }
        return frames;

    }

    @Override
    protected void updateItem(T t, boolean bln) {
        //overriding the super interface
        super.updateItem(t, bln);

    }
}

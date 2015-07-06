package nucleus.manager;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a base class for all presenters. Subclasses can override
 * {@link #onCreate}, {@link #onDestroy}, {@link #onSave},
 * {@link #onTakeView}, {@link #onDropView}.
 * <p/>
 * {@link Presenter.OnDestroyListener} can also be used by external classes
 * to be notified about the need of freeing resources.
 *
 * @param <ViewType> a type of view to return with {@link #getView()}.
 */
public class Presenter<ViewType> {

    /**
     * activity 第一次create到主动finish之前。只会调用一次。用于数据加载。避免横竖屏，内存回收导致重复加载
     * @param savedState
     */
    protected void onCreate(Bundle savedState) {
    }

    /**
     * activity$OnCreate的回调,但执行延迟到OnCreate之后。
     */
    protected void onCreateView(ViewType view) {
    }


    /**
     * Activity$OnDestroy的回调
     */
    protected void onDestroy() {
    }

    /**
     * = =,经常调用.用于保存状态
     * @param state a non-null bundle which should be used to put presenter's state info.
     */
    protected void onSave(Bundle state) {
    }

    /**
     * Activity$OnResume的回调
     *
     * @param view a view that should be taken
     */
    protected void onTakeView(ViewType view) {
    }

    /**
     * Activity$onPause的回调
     */
    protected void onDropView() {
    }

    /**
     * Activity$onActivityResult的回调
     */
    protected void onResult(int requestCode, int resultCode, Intent data) {
    }



    /**
     * A callback to be invoked when a presenter is about to be destroyed.
     */
    public interface OnDestroyListener {
        /**
         * Called before {@link Presenter#onDestroy()}.
         */
        void onDestroy();
    }

    /**
     * Adds a listener observing {@link #onDestroy}.
     *
     * @param listener a listener to add.
     */
    public void addOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.add(listener);
    }

    /**
     * Removed a listener observing {@link #onDestroy}.
     *
     * @param listener a listener to remove.
     */
    public void removeOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.remove(listener);
    }

    ViewType view;
    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();

    /**
     * Returns a current view attached to presenter.
     *
     * @return a current attached view.
     */
    public ViewType getView() {
        return view;
    }

    /**
     * Creates a presenter.
     * This method is called from {@link nucleus.manager.DefaultPresenterManager#provide} and should not be called directly.
     */
    public void create(Bundle bundle) {
        onCreate(bundle);
    }

    /**
     * Destroys a presenter.
     * This method is called from {@link nucleus.manager.DefaultPresenterManager#destroy(Presenter)} and should not be called directly.
     */
    public void destroy() {
        for (OnDestroyListener listener : onDestroyListeners)
            listener.onDestroy();
        onDestroy();
    }

    /**
     * Saves a presenter.
     * This method is called from {@link nucleus.manager.DefaultPresenterManager#save} and should not be called directly.
     */
    public void save(Bundle state) {
        onSave(state);
    }

    /**
     * Attaches a view to a presenter. Call it from the view, after it has been initialized and its state has been restored.
     * Good places for calling {@link #takeView} are:
     * {@link android.app.Activity#onResume}, {@link android.view.View#onAttachedToWindow}, {@link android.app.Fragment#onResume}
     *
     * @param view a view to attach.
     */
    public void takeView(ViewType view) {
        this.view = view;
        onTakeView(view);
    }

    /**
     * Detaches a presenter from a view. Call it for a view, at the beginning of the destruction phase.
     * Good places for calling {@link #dropView} are:
     * {@link android.app.Activity#onPause}, {@link android.view.View#onDetachedFromWindow}, {@link android.app.Fragment#onPause}
     */
    public void dropView() {
        onDropView();
        this.view = null;
    }

    public void createView(ViewType view) {
        this.view = view;
        onCreateView(view);
    }

    /**
     *
     * Detaches a presenter from a view. Call it for a view, at the beginning of the destruction phase.
     * Good places for calling {@link #dropView} are:
     * {@link android.app.Activity#onPause}, {@link android.view.View#onDetachedFromWindow}, {@link android.app.Fragment#onPause}
     */
    public void result(int requestCode, int resultCode, Intent data){
        onResult(requestCode,resultCode,data);
    }

    /**
     * Returns a number of onDestroy listeners.
     *
     * @return a number of onDestroy listeners.
     * @hide testing facility
     */
    public int onDestroyListenerCount() {
        return onDestroyListeners.size();
    }
}

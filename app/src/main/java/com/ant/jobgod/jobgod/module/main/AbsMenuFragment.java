package com.ant.jobgod.jobgod.module.main;

import android.support.annotation.IdRes;

import nucleus.manager.Presenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Mr.Jude on 2015/7/11.
 */
public abstract class AbsMenuFragment<P extends Presenter> extends NucleusFragment<P> {

    public abstract int getMenu();
    public abstract void onMenuSelect(@IdRes int id);

}

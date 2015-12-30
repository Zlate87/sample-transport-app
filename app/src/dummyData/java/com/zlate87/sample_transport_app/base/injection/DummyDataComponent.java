package com.zlate87.sample_transport_app.base.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component interface.
 */
@Singleton
@Component(modules = DummyDataModule.class)
public interface DummyDataComponent extends BaseComponent {

}

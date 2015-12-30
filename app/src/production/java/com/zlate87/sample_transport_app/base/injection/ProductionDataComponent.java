package com.zlate87.sample_transport_app.base.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component interface.
 */
@Singleton
@Component(modules = ProductionModule.class)
public interface ProductionDataComponent extends BaseComponent {

}

package __TestingFromScrath__.generated;

import ej.wadapps.management.BackgroundServicesList;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.components.registry.BundleActivator;

@SuppressWarnings("all")
public class TestingFromScrathActivator implements BundleActivator {

	some.pack.name.NewClass some__pack__name__NewClass;

	@Override
	public void initialize() {
		this.some__pack__name__NewClass = new some.pack.name.NewClass();
	}

	@Override
	public void link() {
		BackgroundServicesList backgroundserviceslist = ServiceLoaderFactory.getServiceLoader().getService(BackgroundServicesList.class);
		backgroundserviceslist.add(this.some__pack__name__NewClass);

	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
		BackgroundServicesList backgroundserviceslist = ServiceLoaderFactory.getServiceLoader().getService(BackgroundServicesList.class);
		backgroundserviceslist.remove(this.some__pack__name__NewClass);

	}

}
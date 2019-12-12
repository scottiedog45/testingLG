package __TestingFromScrath__.generated;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.components.registry.BundleRegistry;
import ej.components.registry.util.BundleRegistryHelper;

import ej.kf.FeatureEntryPoint;

@SuppressWarnings("all")
public class TestingFromScrathEntryPoint implements FeatureEntryPoint {

	@Override
	public void start() {
		BundleRegistry registry = ServiceLoaderFactory.getServiceLoader().getService(BundleRegistry.class);
		BundleRegistryHelper.startup(registry);
	}

	@Override
	public void stop() {
		BundleRegistry registry = ServiceLoaderFactory.getServiceLoader().getService(BundleRegistry.class);
		registry.stop();
	}
}
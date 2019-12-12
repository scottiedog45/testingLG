package __TestingFromScrath__.generated;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import ej.components.registry.BundleActivator;
import ej.components.registry.impl.AbstractRegistry;
import ej.components.registry.util.BundleRegistryHelper;


/**
 * Registry that loads activator from Wadapps applications.
 * <p>
 * An application named <code>[xxx]</code> contains a bundle list that is named <code>[xxx].services.bundles</code> in
 * <code>properties</code> folder. For each application (respecting order), this file is loaded and the activators are
 * started. The registry contains a list of activators to starts if there is no applications.
 */
@SuppressWarnings("all")
public class WadappsRegistry extends AbstractRegistry {
	private static final String APPLICATIONS_LIST_FILE = "/applications.list";
	private final String[] applications;
	private static final String[] BUNDLES = {
			"__TestingFromScrath__.generated.TestingFromScrathActivator",
"ej.wadapps.management.activators.DefaultManagementActivator"
	};

	/**
	 * Create a default registry, which load all applications defined in the "/applications.list" resource.
	 */
	public WadappsRegistry() {
		List<String> applications = new ArrayList<>();
		Properties properties = new Properties();
		try(InputStream stream = WadappsRegistry.class.getResourceAsStream(APPLICATIONS_LIST_FILE)) {
			if (stream != null) {
				boolean endOfFile = false;
				do {
					// read line
					StringBuilder lineBuilder = new StringBuilder();
					boolean dropComment = false;
					while (true) {
						int readByte = stream.read();
						if (readByte == -1) {
							// end of file
							endOfFile = true;
							break;
						} else if (readByte == '\n') {
							// end of line
							break;
						} else {
							if (!dropComment) {
								if (readByte == '#') {
									dropComment = true;
								} else {
									lineBuilder.append((char) readByte);
								}
							}
						}
					}
					String line = lineBuilder.toString().trim();
					if (!line.isEmpty()) {
						applications.add(line);
					}
				} while (!endOfFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.applications = applications.toArray(new String[applications.size()]);
	}

	/**
	 * Create a registry which contains the given applications.
	 *
	 * @param applications
	 *            applications whose activators will be started.
	 */
	public WadappsRegistry(String[] applications) {
		this.applications = applications;
	}

	@Override
	protected BundleActivator[] loadBundles() {
		if (applications == null || applications.length == 0) {
			return getBundleActivators();
		} else {
			return getBundleActivators(this.applications);
		}
	}

	private static BundleActivator[] getBundleActivators() {
		Collection<BundleActivator> bundles = new ArrayList<>();
		for (String bundleName : BUNDLES) {
			BundleActivator bundle = BundleRegistryHelper.loadBundle(bundleName);
			bundles.add(bundle);
		}
		return bundles.toArray(new BundleActivator[bundles.size()]);
	}

	private static BundleActivator[] getBundleActivators(String[] applications) {
		List<BundleActivator> bundles = new ArrayList<>();

		for (String application : applications) {
			String bundlesFile = "/properties/" + application + ".services.bundles";
			Properties properties = new Properties();
			try (InputStream stream = WadappsRegistry.class.getResourceAsStream(bundlesFile)){
				properties.load(stream);
				Set<String> bundlesName = properties.stringPropertyNames();
				for (String bundleName : bundlesName) {
					BundleActivator bundle = BundleRegistryHelper.loadBundle(bundleName);
					bundles.add(bundle);
				}
			} catch (IOException e) {
				throw new RuntimeException("Cannot read " + bundlesFile, e);
			}
		}
		return bundles.toArray(new BundleActivator[bundles.size()]);
	}
}